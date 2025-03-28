package com.bilibili.DTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户数据传输对象（DTO）
 */
@Data
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String avatar;
    private String signature;
    private Integer gender;
    private LocalDateTime birthDate;
    private Integer level;
    private Integer experience;
    private Integer biliCoin;
    private Integer vipLevel;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;
}
