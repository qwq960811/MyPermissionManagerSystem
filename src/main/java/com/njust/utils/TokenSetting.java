package com.njust.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @ Description   :  jwt配置读取类
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 15:56$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 15:56$
 * @ Version       :  1.0
 */
@Configuration
@ConfigurationProperties(prefix="jwt")
@Data
public class TokenSetting {
    private String secretKey;
    private Duration accessTokenExpireTime;
    private Duration refreshTokenExpireTime;
    private Duration refreshTokenExpireAppTime;
    private String issuer;
}
