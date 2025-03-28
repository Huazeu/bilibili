package com.bilibili.controller;

import bilibili.exception.BlibiliException;
import bilibili.result.Result;
import bilibili.result.ResultCodeEnum;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bilibili.DTO.UserDTO;
import com.bilibili.DTO.UserRegisterDTO;
import com.bilibili.model.user.CoinAccount;
import com.bilibili.model.user.Membership;
import com.bilibili.model.user.User;
import com.bilibili.service.CoinAccountService;
import com.bilibili.service.MembershipService;
import com.bilibili.service.UserService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "用户相关接口")
@Validated
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private CoinAccountService coinAccountService;

    @Autowired
    private MembershipService membershipService;

    /**
     * 用户注册接口
     * 同时在 user、coin_account、membership 表中创建记录
     */
    @Operation(summary = "用户注册", description = "注册新用户并初始化相关表")
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public Result  register(@RequestBody @Valid  UserRegisterDTO dto)
    {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String email = dto.getEmail();
        String phone = dto.getPhone();
        // 检查用户名是否已存在
        User existingUser = userService.getOne(new QueryWrapper<User>().eq("username", dto.getUsername()));
        if (existingUser != null) {
            return Result.fail(ResultCodeEnum.USER_ALREADY_EXISTS);
        }
        // 检查邮箱是否已注册
        existingUser = userService.getOne(new QueryWrapper<User>().eq("email", dto.getEmail()));
        if (existingUser != null) {
            return Result.fail(ResultCodeEnum.EMAIL_ALREADY_REGISTERED);
        }
        // 检查手机号是否已注册
        existingUser = userService.getOne(new QueryWrapper<User>().eq("phone", dto.getPhone()));
        if (existingUser!= null) {
            return Result.fail(ResultCodeEnum.PHONE_ALREADY_REGISTERED);
        }
        // 创建用户信息
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(DigestUtils.sha256Hex(password)); // SHA-256 加密密码
        user.setEmail(email);
        user.setPhone(phone);
        user.setLevel(0);        // 默认等级
        user.setExperience(0);   // 默认经验值
        user.setBiliCoin(200);     // 默认 B 币
        user.setVipLevel(0);     // 默认会员等级
        user.setStatus(0);       // 默认状态
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // 保存用户记录
        userService.save(user);

        // 初始化 B 币账户
        CoinAccount coinAccount = new CoinAccount();
        coinAccount.setUserId(user.getId());
        coinAccount.setBalance(200); // 初始余额为 0
        coinAccount.setUpdatedAt(LocalDateTime.now());
        coinAccountService.save(coinAccount);

        // 初始化会员信息
        Membership membership = new Membership();
        membership.setUserId(user.getId());
        membership.setLevel(0); // 默认会员等级为 0
        membership.setStartDate(LocalDateTime.now());
        membership.setEndDate(LocalDateTime.of(2099, 12, 31, 23, 59, 59)); // 永久有效
        membership.setAutoRenew(0); // 默认不自动续费
        membershipService.save(membership);

        return Result.ok(ResultCodeEnum.SUCCESS);
    }

    /** 用户登录接口 */
    @Operation(summary = "用户登录", description = "通过用户名和密码登录，返回 Token")
    @PostMapping("/doLogin")
    public Result doLogin(
            @RequestParam @NotBlank(message = "用户名不能为空") String username,
            @RequestParam @NotBlank(message = "密码不能为空") String password) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            return Result.fail(ResultCodeEnum.USER_NOT_EXIST);
        }

        // 验证密码
        String hashedPassword = DigestUtils.sha256Hex(password);
        if (!hashedPassword.equals(user.getPasswordHash())) {
            return Result.fail(ResultCodeEnum.PASSWORD_ERROR);
        }

        // 生成登录 Token
        StpUtil.login(user.getId(),new SaLoginParameter().setTimeout(60 * 60 * 24 * 7));
        Map<String, String> data = new HashMap<>();
        data.put("userId", String.valueOf(user.getId()));
        return Result.ok(data);
    }

    /** 检查登录状态 */
    @Operation(summary = "查询登录状态", description = "检查当前会话是否已登录")
    @GetMapping("/isLogin")
    public Result<String> isLogin() {
        boolean isLogin = StpUtil.isLogin();
        return Result.ok( "当前会话是否登录：" + isLogin);
    }

    /** 用户登出 */
    @Operation(summary = "用户登出", description = "退出当前登录会话")
    @SaCheckLogin
    @PostMapping("/logout")
    public Result<String> logout() {
        StpUtil.logout();
        return Result.ok("退出登录成功");
    }

    /** 获取用户信息 */
    @Operation(summary = "获取用户信息", description = "查询当前登录用户的信息")
    @SaCheckLogin
    @GetMapping("/info")
    public Result<UserDTO> getUserInfo() {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = userService.getById(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        if (user == null) {
throw new BlibiliException(ResultCodeEnum.LOGIN_AUTH);
        }
        return Result.ok(userDTO);
    }
}
