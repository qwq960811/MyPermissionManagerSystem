package com.njust;



import com.njust.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CompanyFrameApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() {

    }


    @Test
    public void testRedisConnection(){
        redisService.set("name","123");
        System.out.println(redisService.get("name"));
    }



}
