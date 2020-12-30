package com.njust.shiro;

import com.njust.service.RedisService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collection;

/**
 * @ Description   :  redis缓存管理器
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/28$ 22:29$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/28$ 22:29$
 * @ Version       :  1.0
 */
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisService redisService;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {

        return new RedisCache<>(redisService);
    }
}
