package com.njust.constants;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 16:42$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 16:42$
 * @ Version       :  1.0
 */
public class Constant {
    public static final String JWT_USER_NAME = "jwt-user-name-key";

    public static final String JWT_ROLES_KEY = "jwt-roles-key_";

    public static final String JWT_PERMISSIONS_KEY = "jwt-permissions-key_";

    public static final String ACCESS_TOKEN = "authorization";

    /**
     * 主动去刷新 token key(适用场景 比如修改了用户的角色/权限去刷新token)
     */
    public static final String JWT_REFRESH_KEY="jwt-refresh-key_";
    /**
     * 标记用户是否已经被锁定
     17.2 加入异常提示枚举
     17.3 自定义 HashedCredentialsMatcher（这个步骤可以结合后面的实战课程对应的功能理解）
     */
    public static final String ACCOUNT_LOCK_KEY="account-lock-key_";
    /**
     * 标记用户是否已经删除
     */
    public static final String DELETED_USER_KEY="deleted-user-key_";

    /**
     * 用户鉴权缓存key
     */
    public static final String IDENTIFY_CACHE_KEY="shiro-cache:com.njust.shiro.CustomRealm.authorizationCache";

}
