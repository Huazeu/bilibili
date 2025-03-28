package com.bilibili.vo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



/**
 * 用户注册 VO
 */
@Data
public class RegisterVO {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3到20个字符之间")
    private String username;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Size(min = 11, max = 11, message = "手机号必须为11位")
    private String phone;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6到20个字符之间")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String checkPassword;
    @NotBlank(message = "验证码不能为空")
    private String verificationCode;

}
