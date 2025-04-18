/**
 * @ Tool：IntelliJ IDEA
 * @ Author：wkl
 * @ Date：2025-04-15-2:05
 * @ Version：1.0
 * @ Description：
 */

package com.bilibili.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bilibili.exception.BlibiliException;
import com.bilibili.model.user.User;
import com.bilibili.result.Result;
import com.bilibili.result.ResultCodeEnum;
import com.bilibili.service.UserService;
import com.bilibili.util.EmailService;
import com.bilibili.util.PasswordHashing;
import com.bilibili.vo.user.LoginVO;
import com.bilibili.vo.user.RegisterVO;
import com.bilibili.vo.user.UserVO;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import static com.bilibili.constant.SystemConstant.USER_INFO;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RedisTemplate redisTemplate;

    //TODO 获取用户信息
    @GetMapping("/getUserInfo")
    @SaCheckLogin
    public Result<UserVO> getUserInfo() {
        Long userId = StpUtil.getLoginIdAsLong();
        String userInfoKey = USER_INFO + userId;
        UserVO userVO = (UserVO) redisTemplate.opsForValue().get(userInfoKey);
        if (userVO == null) {
            User user = userService.getById(userId);
                userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);
                redisTemplate.opsForValue().set(userInfoKey, userVO);
        }
        return Result.ok(userVO);
    }

    @PostMapping("sendCode")
    public Result sendVerificationCode(@RequestParam String email) {
        // 检查邮箱是否已注册
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            throw new BlibiliException(ResultCodeEnum.EMAIL_ALREADY_REGISTERED);
        }

        // 发送验证码
        emailService.sendVerificationCode(email);
        return Result.ok("验证码发送成功");
    }


    /**
     * 用户登录
     */
    @SneakyThrows
    @PostMapping("login")
    public Result<UserVO> login(@RequestBody LoginVO loginVO) {
        String email = loginVO.getEmail();
        String password = loginVO.getPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        User user = userService.getOne(queryWrapper);
        if(user==null){
            throw new BlibiliException(ResultCodeEnum.USER_NOT_EXIST);
         }
        String encrypted = PasswordHashing.hashPassword(password);
        if(!encrypted.equals(user.getPasswordHash())){
            throw new BlibiliException(ResultCodeEnum.PASSWORD_ERROR);
        }
        StpUtil.login(user.getId());
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        Long id = userVO.getId();
        String UserInfoKey=USER_INFO+id;
        redisTemplate.opsForValue().set(UserInfoKey,userVO);
        StpUtil.login(id);
        return Result.ok(userVO);
    }
    @SneakyThrows
    @PostMapping("register")
    public Result register(@RequestBody RegisterVO registerVO)  {
        String email = registerVO.getEmail();
        String username = registerVO.getUsername();
        String password = registerVO.getPassword();
        String checkPassword = registerVO.getCheckPassword();
        String verificationCode = registerVO.getVerificationCode();
        // 验证码校验
        if (!emailService.verifyCode(email, verificationCode)) {
            throw new BlibiliException(200, "邮箱验证码错误或已过期");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username); QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("email",email);
        User user = userService.getOne(queryWrapper);
        User user2 = userService.getOne(queryWrapper2);
        if(user!=null){
            throw new BlibiliException(ResultCodeEnum.USER_ALREADY_EXISTS);
        }
        if(user2!=null){
            throw new BlibiliException(ResultCodeEnum.EMAIL_ALREADY_REGISTERED);
        }
        if(!password.equals(checkPassword)){
            throw new BlibiliException(200,"两次密码不一致");
        }
        User newUser = new User();
        BeanUtils.copyProperties(registerVO,newUser);
        String encrypted = PasswordHashing.hashPassword(password);
        newUser.setPasswordHash(encrypted);
        boolean save = userService.save(newUser);
        if(save){
            return Result.ok(ResultCodeEnum.SUCCESS);
        }throw new BlibiliException(ResultCodeEnum.USER_REGISTER_FAILED);
    }
}
