package bilibili.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 *
 */
@Getter
public enum ResultCodeEnum {
    // 通用状态码
    SUCCESS(200, "成功啦OwO✨"),
    FAIL(201, "失败了QwQ"),
    SERVICE_ERROR(202, "服务异常啦>_<"),
    DATA_ERROR(203, "数据出问题了Orz"),
    ILLEGAL_REQUEST(204, "非法请求哦(ง •̀_•́)ง"),
    REPEAT_SUBMIT(205, "重复提交啦哼(￣^￣)"),
    ARGUMENT_VALID_ERROR(206, "参数校验出错啦Σ(っ °Д °;)っ"),
    SIGN_ERROR(207, "签名错误啦(⊙_⊙)"),
    LOGIN_AUTH(208, "还没登陆哦(´･ω･`)"),
    PERMISSION(209, "没有权限啦(ノ｀Д´)ノ"),
    SIGN_OVERDUE(210, "签名过期了呢(つд⊂)"),

    // 用户认证相关
    ACCOUNT_ERROR(211, "账号不对哦(´･_･`)"),
    PASSWORD_ERROR(212, "密码错了啦(>_<)"),
    PHONE_CODE_ERROR(213, "手机验证码不对呢(╥_╥)"),
    LOGIN_MOBILE_ERROR(214, "账号不对啦(´･_･`)"), // 与ACCOUNT_ERROR含义相同，可考虑合并
    ACCOUNT_STOP(215, "账号被停用啦QwQ"),
    USER_NOT_EXIST(245, "用户不存在哦(´･ω･`)"),
    EMAIL_ALREADY_REGISTERED(246, "邮箱已经注册啦(￣ヘ￣)"),
    PHONE_ALREADY_REGISTERED(247, "手机号已经被用了呢(ノ￣▽￣)"),
    USERNAME_ALREADY_EXISTS(248, "用户名已经存在啦(╥_╥)"),
    USER_REGISTER_FAILED(249, "用户注册失败了呢(>_<)"),
    USER_UPDATE_FAILED(250, "用户更新失败了呢(>_<)"),
    USER_DELETE_FAILED(251, "用户删除失败啦(╥_╥)"),
    USER_NOT_FOUND(252, "用户没找到哦(´･ω･`)"),
    USER_ALREADY_EXISTS(253, "用户已经存在啦(╥_╥)"),
    USER_NOT_ACTIVE(254, "用户未激活哦(´･ω･`)"),
    USER_LOCKED(255, "用户被锁定啦(╥_╥)"),
    USER_DISABLED(256, "用户被禁用啦(╥_╥)"),
    USER_EXPIRED(257, "用户账号已过期啦(╥_╥)"),
    USER_CREDENTIALS_EXPIRED(258, "用户凭证已过期啦(╥_╥)"),
    USER_ACCOUNT_DISABLED(259, "用户账号已被禁用啦(╥_╥)"),

    // 视频管理相关
    VOD_FILE_ID_ERROR(217, "声音媒体id不对啦(>_<)"),
    VIDEO_UPLOAD_FAILED(248, "视频上传失败了QwQ"),
    VIDEO_NOT_EXIST(249, "视频不见啦(´･ω･`)"),
    VIDEO_DELETED(250, "视频被删掉啦(╥_╥)"),
    VIDEO_UPDATE_FAILED(251, "视频更新失败了呢(>_<)"),
    VIDEO_DELETE_FAILED(252, "视频删除失败啦(ノ｀Д´)ノ"),
    NODE_ERROR(216, "这个节点有子节点，不能删哦(￣^￣)"),

    // 账户与交易相关
    ACCOUNT_LESS(219, "余额不够啦QwQ"),
    ACCOUNT_LOCK_ERROR(220, "余额锁定失败了(⊙_⊙)"),
    ACCOUNT_UNLOCK_ERROR(221, "余额解锁失败啦(>_<)"),
    ACCOUNT_MINUSLOCK_ERROR(222, "余额扣减失败了呢(╥_╥)"),
    ACCOUNT_LOCK_REPEAT(223, "重复锁定啦(￣ヘ￣)"),
    ORDER_SUBMIT_REPEAT(224, "订单不能重复提交哦(ノ￣▽￣)"),
    NO_BUY_NOT_SEE(225, "没买不能看哦(´･ω･`)"),
    REPEAT_BUY_ERROR(226, "重复购买啦，请确认后再下单(￣^￣)"),
    EXIST_NO_EXPIRE_LIVE(227, "还有未过期的直播哦(*^▽^*)"),

    // 互动相关
    COMMENT_POST_FAILED(253, "评论发不出去啦QwQ"),
    LIKE_FAILED(254, "点赞失败了呢(>_<)"),
    COLLECT_FAILED(255, "收藏失败啦(╥_╥)"),
    DANMAKU_SEND_FAILED(256, "弹幕发送失败了QwQ"),

    // 搜索和推荐相关
    SEARCH_NO_RESULT(257, "什么都没搜到哦(´･ω･`)"),
    RECOMMEND_LIST_FAILED(258, "推荐列表拿不到啦(>_<)"),

    // 通知相关
    MESSAGE_SEND_FAILED(259, "消息发送失败了QwQ"),
    MESSAGE_READ(260, "消息已读啦(*^▽^*)"),
    MESSAGE_DELETE_FAILED(261, "消息删除失败啦(ノ｀Д´)ノ"),

    // 调度相关
    XXL_JOB_ERROR(218, "调度操作失败啦(>_<)");


    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
