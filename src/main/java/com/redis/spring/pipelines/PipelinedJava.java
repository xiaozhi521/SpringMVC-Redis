package com.redis.spring.pipelines;

import com.redis.java.RedisJava;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 *  使用java api  流水线操作 redis 命令
 */
public class PipelinedJava {
    public static void main(String[] args) {
        testPipelined();
    }

    /**
     *  java API 耗时：606毫秒
     */
    public static void testPipelined(){
        Jedis jedis = RedisJava.getRedis();
        long start = System.currentTimeMillis();
        //开启流水线命令
        Pipeline pipeline = jedis.pipelined();
        //测试 10 万条的 读写2个操作
        for (int i = 0; i < 100000; i++){
            int j = i + 1;
            pipeline.set("pipeline_key_" + j,"pipeline_value_" + j);
            pipeline.get("pipeline_key_" + j);
        }
        //这里只执行同步，不返回结果
        pipeline.sync();
        //将返回执行过的命令返回的list列表结果
        List list= pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("java API 耗时：" + (end - start) + "毫秒");
    }
}
