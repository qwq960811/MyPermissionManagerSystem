package com.njust.exception.code;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/23$ 21:26$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/23$ 21:26$
 * @ Version       :  1.0
 */
public enum BaseResponseCode implements ResponseCodeInterface{
    SUCCESS(0,"操作成功"),
    SYSTEM_ERROR(5000001, "系统异常请稍后再试"),
    DATA_ERROR(4000001,"传入数据异常"),
    VALIDATE_ERROR(4000002, "参数校验异常"),
    NO_ACCOUNT_ERROR(4000003, "用户不存在"),
    USER_LOCK(4000004, "当前用户被锁定，请联系系统管理员"),
    PASSWORD_ERROR(4000005, "用户密码错误"),
    TOKEN_NOT_NULL(4010001, "认证token不能为空，请重新登录获取"),
    SHIRO_AUTHENTICATION_ERROR(4010001,"token认证失败，请重新登录获取最新的token"),
    ACCOUNT_LOCK(4010001,"该账号被锁定,请联系系统管理员"),
    ACCOUNT_HAS_DELETED_ERROR(4010001,"该账号已被删除，请联系系统管理员"),
    TOKEN_PAST_DUE(4010002,"token失效,请刷新token"),
    NO_PERMISSION(4030001, "无权限访问")
    ;

    private final int code;
    private final String msg;

    BaseResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
