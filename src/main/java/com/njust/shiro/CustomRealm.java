package com.njust.shiro;

import com.njust.constants.Constant;
import com.njust.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/28$ 14:39$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/28$ 14:39$
 * @ Version       :  1.0
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomUsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String accessToken = (String) principals.getPrimaryPrincipal();
        log.info("doGetAuthorizationInfo...accessToken={}",accessToken);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken(accessToken);
        if(claimsFromToken.get(Constant.JWT_ROLES_KEY) != null){
            simpleAuthorizationInfo.addRoles((Collection<String>) claimsFromToken.get(Constant.JWT_ROLES_KEY));
        }
        if(claimsFromToken.get(Constant.JWT_PERMISSIONS_KEY) != null) {
            simpleAuthorizationInfo.addStringPermissions((Collection<String>)claimsFromToken.get(Constant.JWT_PERMISSIONS_KEY));
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomUsernamePasswordToken customToken = (CustomUsernamePasswordToken)token;
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(customToken.getPrincipal(),
                customToken.getCredentials(),this.getName());

        return simpleAuthenticationInfo;
    }
}
