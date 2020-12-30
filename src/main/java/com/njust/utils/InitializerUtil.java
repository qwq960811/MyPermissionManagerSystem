package com.njust.utils;

import org.springframework.stereotype.Component;

/**
 * @ Description   :  初始化配置代理类
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 16:02$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 16:02$
 * @ Version       :  1.0
 */
@Component
public class InitializerUtil {
    private TokenSetting tokenSetting;

    public InitializerUtil(TokenSetting tokenSetting) {
        JwtTokenUtil.setTokenSetting(tokenSetting);
    }
}
