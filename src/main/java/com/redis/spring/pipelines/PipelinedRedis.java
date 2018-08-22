package com.redis.spring.pipelines;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.List;

public class PipelinedRedis {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        SessionCallback sessionCallback = new SessionCallback() {
            public Object execute(RedisOperations ops) throws DataAccessException {
                for (int i = 0; i < 100000; i++){
                    int j = i + 1;
                    ops.boundValueOps("pipeline_key_" + j).set("pipeline_value_" + j);
                    ops.boundValueOps("pipeline_key_" + j).get();
                    ops.boundValueOps("pipeline_key_" + j).persist();
                }
                return null;
            }
        };
        long start = System.currentTimeMillis();
        //执行redis 流水线命令
        List list = redisTemplate.executePipelined(sessionCallback);
        long end = System.currentTimeMillis();
        System.out.println("spring 耗时：" + (end - start) + "毫秒");
    }
}
