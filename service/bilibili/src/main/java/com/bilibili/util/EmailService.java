package com.bilibili.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 验证码有效期（分钟）
    private final long CODE_EXPIRATION_MINUTES = 5;

    // Redis中存储验证码的key前缀
    private static final String EMAIL_CODE_PREFIX = "email:code:";

    /**
     * 发送验证码
     * @param email 接收验证码的邮箱
     */
    public void sendVerificationCode(String email) {
        // 生成6位随机验证码
        String code = String.valueOf(new Random().nextInt(900000) + 100000);

        // 将验证码保存到Redis，设置过期时间
        String redisKey = EMAIL_CODE_PREFIX + email;
        redisTemplate.opsForValue().set(redisKey, code, CODE_EXPIRATION_MINUTES, TimeUnit.MINUTES);

        // 发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2214770953@qq.com");
        message.setTo(email);
        message.setSubject("哔哩哔哩注册验证码");
        message.setText("您的验证码是：" + code + "，有效期" + CODE_EXPIRATION_MINUTES + "分钟。请勿泄露给他人。");

        mailSender.send(message);
    }

    /**
     * 验证邮箱验证码
     * @param email 邮箱
     * @param inputCode 用户输入的验证码
     * @return 验证结果
     */
    public boolean verifyCode(String email, String inputCode) {
        if (inputCode == null || inputCode.trim().isEmpty()) {
            return false;
        }

        String redisKey = EMAIL_CODE_PREFIX + email;
        String storedCode = redisTemplate.opsForValue().get(redisKey);

        // 验证码不存在或已过期
        if (storedCode == null) {
            return false;
        }

        // 验证码匹配
        boolean isValid = inputCode.equals(storedCode);

        // 验证通过后删除验证码（一次性使用）
        if (isValid) {
            redisTemplate.delete(redisKey);
        }

        return isValid;
    }
}
