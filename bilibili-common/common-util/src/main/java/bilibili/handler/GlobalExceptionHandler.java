package bilibili.handler;

import bilibili.exception.BlibiliException;
import bilibili.result.Result;
import bilibili.result.ResultCodeEnum;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler   {

    // 提取公共方法处理 BindingResult
    private Map<String, Object> getErrorMap(BindingResult result) {
        Map<String, Object> errorMap = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(error -> {
            log.error("field: " + error.getField() + ", msg:" + error.getDefaultMessage());
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error("未捕获的异常: " + e.getMessage(), e);
        return Result.build(null, ResultCodeEnum.SERVICE_ERROR);
    }

    @ExceptionHandler(BlibiliException.class)
    @ResponseBody
    public Result error(BlibiliException e) {
        return Result.build(null, e.getCode(), e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result illegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常: " + e.getMessage(), e);
        return Result.build(null, ResultCodeEnum.ARGUMENT_VALID_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result accessDeniedException(AccessDeniedException e) {
        log.error("访问拒绝: " + e.getMessage(), e);
        return Result.build(null, ResultCodeEnum.PERMISSION);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindException(BindException e) {
        Map<String, Object> errorMap = getErrorMap(e.getBindingResult());
        return Result.build(errorMap, ResultCodeEnum.ARGUMENT_VALID_ERROR);
    }



    // 处理 MethodArgumentNotValidException（仅保留此方法）
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
            log.error("参数校验失败 - field: {}, msg: {}", field, message);
        });
        return Result.build(errors, ResultCodeEnum.ARGUMENT_VALID_ERROR);
    }

    // 处理 ConstraintViolationException（可选，适用于 @RequestParam 等场景）
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
            log.error("参数校验失败 - field: {}, msg: {}", propertyPath, message);
        });
        return Result.build(errors, ResultCodeEnum.ARGUMENT_VALID_ERROR);
    }



}
