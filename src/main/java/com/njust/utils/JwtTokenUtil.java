package com.njust.utils;

import com.alibaba.druid.util.StringUtils;
import com.njust.constants.Constant;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 15:56$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 15:56$
 * @ Version       :  1.0
 */
@Slf4j
public class JwtTokenUtil {

    private static String secretKey;
    private static Duration accessTokenExpireTime;
    private static Duration refreshTokenExpireTime;
    private static Duration refreshTokenExpireAppTime;
    private static String issuer;

    public static void setTokenSetting(TokenSetting tokenSetting){
        secretKey = tokenSetting.getSecretKey();
        accessTokenExpireTime = tokenSetting.getAccessTokenExpireTime();
        refreshTokenExpireTime = tokenSetting.getRefreshTokenExpireTime();
        refreshTokenExpireAppTime = tokenSetting.getRefreshTokenExpireAppTime();
        issuer = tokenSetting.getIssuer();
    }

    /**
     * 生成 access_token
     * @param subject
     * @param claims
     * @return
     */
    public static String getAccessToken(String subject, Map<String,Object> claims){
        return generateToken(issuer,subject,claims,accessTokenExpireTime.toMillis(),secretKey);
    }

    /**
     * -
     * 生成 PC refresh_token（过期时间短一些）
     * @param subject
     * @param claims
     * @return
     */
    public static String getRefreshToken(String subject,Map<String,Object> claims){
        return generateToken(issuer,subject,claims,refreshTokenExpireTime.toMillis(),secretKey);
    }

    /**
     * 生成 APP refresh_token
     * @param subject
     * @param claims
     * @return
     */
    public static String getRefreshAppToken(String subject,Map<String,Object> claims){
        return generateToken(issuer,subject,claims,refreshTokenExpireAppTime.toMillis(),secretKey);
    }



    /**
     * 签发token
     * @param issuer 签发人
     * @param subject 代表这个JWT的主体，即它的所有人 一般是用户id
     * @param claims 存储在JWT里面的信息 一般放些用户的权限/角色信息
     * @param ttlMillis 有效时间(毫秒)
     * @param secret
     * @return
     */
    public static String generateToken(String issuer, String subject, Map<String, Object> claims, long ttlMillis,
                                       String secret){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] signingKey = DatatypeConverter.parseBase64Binary(secret);
        JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ","JWT");
        if(null!=claims){
            builder.setClaims(claims);
        }
        if (!StringUtils.isEmpty(subject)) {
            builder.setSubject(subject);
        }
        if (!StringUtils.isEmpty(issuer)) {
            builder.setIssuer(issuer);
        }
        builder.setIssuedAt(now);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        builder.signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }


    /**
     * 从令牌中获取数据声明
     * @param token
     * @return
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims=null;
        try {
            claims =
                    Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                            .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            if(e instanceof ClaimJwtException){
                claims=((ClaimJwtException) e).getClaims();
            }
        }
        return claims;
    }

    /**
     * 从token中获取用户id
     * @param token
     * @return
     */
    public static String getUserId(String token){
        String userId=null;
        try {
            Claims claimsFromToken = getClaimsFromToken(token);
            userId = claimsFromToken.getSubject();
        } catch (Exception e){
            log.error("error={}", e);
        }
        return userId;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        String username = null;
        try {
            Claims claimsFromToken = getClaimsFromToken(token);
            username = (String) claimsFromToken.get(Constant.JWT_USER_NAME);
        } catch (Exception e){
            log.error("error={}", e);
        }
        return username;
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            log.error("error={}",e);
            return true;
        }
    }

    /**
     * 判断token是否有效
     * @param token
     * @return
     */
    public static Boolean validateToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        return (null!=claimsFromToken && !isTokenExpired(token));
    }

    /**
     * 获取token剩余过期时间
     * @param token
     * @return
     */
    public static long getRemainingTime(String token){
        long result=0;
        try {
            long nowMillis = System.currentTimeMillis();
            result= getClaimsFromToken(token).getExpiration().getTime()-nowMillis;
        } catch (Exception e) {
            log.error("error={}",e);
        }
        return result;
    }




}
