package com.njust.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/27$ 22:33$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/27$ 22:33$
 * @ Version       :  1.0
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {
    //ctrl + O

    private String jwtToken;

    public CustomUsernamePasswordToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return jwtToken;
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }
}
