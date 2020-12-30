package com.njust.shiro;

import com.njust.constants.Constant;
import com.njust.exception.BusinessException;
import com.njust.exception.code.BaseResponseCode;
import com.njust.service.RedisService;
import com.njust.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ Description   :  自定义认证器的核心匹配类
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/28$ 15:03$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/28$ 15:03$
 * @ Version       :  1.0
 */
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) token;
        String accessToken = (String) customUsernamePasswordToken.getPrincipal();
        String userId = JwtTokenUtil.getUserId(accessToken);
        //判断用户是否被锁定
        if(redisService.hasKey(Constant.ACCOUNT_LOCK_KEY + userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        //判断用户是否被删除
        if(redisService.hasKey(Constant.DELETED_USER_KEY + userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }
        //判断token 是否通过校验
        if(!JwtTokenUtil.validateToken(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        return true;
    }
}
