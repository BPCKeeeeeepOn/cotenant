package com.youyu.cotenant.common;

public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 操作失败
     */
    DEFAULT_ERROR(110110, "操作失败"),

    /**
     * 系统错误
     */
    SYS_ERROR(402, "系统错误"),
    /**
     * 参数错误
     */
    PARAMS_ERROR(403, "参数错误"),

    /**
     * 无效token
     */
    INVALID_AUTHTOKEN(405, "无效的Token"),

    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PWD_ERROR(406, "用户名或密码错误"),

    /**
     * 验证码发送失败
     */
    SEND_SMS_CODE_FAILED(407, "验证码发送失败"),

    /**
     * 验证码错误
     */
    SMS_CODE_ERROR(408, "验证码错误"),

    /**
     * 没有该用户
     */
    NOT_USER(410, "没有该用户"),

    /**
     * 用户已存在
     */
    USER_EXISZTS(420, "用户已存在"),


    /**
     * 旧密码错误
     */
    OLD_PASSWORD_ERROR(470, "旧密码错误"),

    /**
     * 补全个人信息
     */
    USER_INFO_ERROR(490, "请补全您的个人信息"),

    /**
     * 个人信息状态审核
     */
    USER_INFO_VERIFY(495, "您的审毕业证/学生证状态审核中"),

    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器异常"),

    /**
     * 没有该租房团
     */
    NO_COTENANT_GROUP(100010, "未找到该租房团"),

    /**
     * 已参加过租房团
     */
    EXIST_COTENANT_GROUP(100030, "已加入租房团或已建团"),

    /**
     * 该租房团人数已满
     */
    COTENANT_GROUP_FULL(100050, "该租房团人数已满"),

    /**
     * 无权限操作
     */
    NO_PERMISSION_OPERATION(100070, "抱歉，您无权限操作"),

    /**
     * 用户信息审核未通过
     */
    UNPASS_USER(100090, "抱歉，您的审毕业证/学生证核未通过,请重新上传"),

    /**
     * 即时消息发送失败
     */
    SEND_MSG_ERROR(100200, "消息发送失败，请稍后重试");


    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
