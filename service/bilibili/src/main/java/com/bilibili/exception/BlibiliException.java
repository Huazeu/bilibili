package com.bilibili.exception;

import com.bilibili.result.ResultCodeEnum;
import lombok.Getter;

/**
 * 自定义全局异常类
 *
 */
@Getter
public class BlibiliException extends RuntimeException {

    private final Integer code;

    private final String message;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param code
     * @param message
     */
    public BlibiliException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public BlibiliException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "BiliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
