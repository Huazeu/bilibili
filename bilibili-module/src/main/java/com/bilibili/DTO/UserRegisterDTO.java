package com.bilibili.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank(message = "用户名不能留空哦！")
    private String username;

    @NotBlank(message = "密码是必填的，快输入吧！")
    private String password;

    @Email(message = "邮箱格式不对，请检查一下！")
    private String email;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式有误，应该是11位数字哦！")
    private String phone;

    // getter 和 setter 方法
}
