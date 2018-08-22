package com.redis.spring.pipelines;

import com.redis.java.RedisJava;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *  使用java api  流水线操作 redis 命令
 *  expire key seconds 为给定 key 设置过期时间 以秒为单位
 *  expireat key timestamp expireat 的作用和 expire 类似，都用于为 key 设置过期时间。 不同在于 expireat 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
 *  pexpire key milliseconds 设置 key 的过期时间亿以毫秒计。
 *  pexpireat key milliseconds-timestamp 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计
 */
public class PipelinedJava {
    public static void main(String[] args) {
        getAllKey();
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

            pipeline.pexpire("pipeline_key_" + j ,10000L);
        }
        //这里只执行同步，不返回结果
        pipeline.sync();
        //将返回执行过的命令返回的list列表结果
        List list= pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("java API 耗时：" + (end - start) + "毫秒");
    }
    /**
     *  获取所有的key值
     */
    public static void getAllKey(){
        Jedis jedis = RedisJava.getRedis();
        //获取所有的key值
        Set<String> stringSet = jedis.keys("*");
        System.out.println("所有的key值：" + stringSet);
        Iterator<String> integer = stringSet.iterator();
        while (integer.hasNext()){
            String type = jedis.type(integer.next());
            System.out.println(type + ":" + integer.next());
        }
    }
}
