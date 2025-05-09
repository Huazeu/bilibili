package com.bilibili.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息 VO
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;
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
}
