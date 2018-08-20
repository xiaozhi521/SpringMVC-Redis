package com.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *  Redis 在 2.8.9 版本添加了 HyperLogLog 结构
 *  Redis HyperLogLog 是用来做技术统计算法。
 *  优点是：在输入元素的数量或者体积非常大时，计算技术所需的空间总是固定的、并且是很小的
 *  每个 HyperLogLog 键只需花费12KB 内存，就可以计算接近 2^64 个不同元素的基数。
 *  这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比。
 *  但是，HyperLogLog 只会根据输入元素来计算基数，而不会存储输入元素本身，所以 HyperLogLog 不能像集合那样，返回输入的各个元素
 *
 *  什么是基数？
 *      比如数据集 {1, 3, 5, 7, 5, 7, 8}， 那么这个数据集的基数集为 {1, 3, 5 ,7, 8}, 基数(不重复元素)为5。
 *      基数估计就是在误差可接受的范围内，快速计算基数。
 */
public class HyperLogLogRedis {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        //添加基数  等同于命令  pfadd key value[...] ， value 可以是一个或多个
        redisTemplate.opsForHyperLogLog().add("HyperLogLog","a","b","c","d","a");
        redisTemplate.opsForHyperLogLog().add("HyperLogLog2","a");
        redisTemplate.opsForHyperLogLog().add("HyperLogLog2","z");
        // 获取给定 HyperLogLog 的基数估算值 等同于命令 pfcount key[key...]
        long size = redisTemplate.opsForHyperLogLog().size("HyperLogLog");
        System.out.println("HyperLogLog 长度："+ size);
        size = redisTemplate.opsForHyperLogLog().size("HyperLogLog2");
        System.out.println("HyperLogLog2 长度："+ size);
        size  = redisTemplate.opsForHyperLogLog().size("HyperLogLog","HyperLogLog2");
        System.out.println("HyperLogLog 与 HyperLogLog2 长度："+ size);
        //合并基数  命令 pfmerge destkey sourcekey[sourcekey...]
        redisTemplate.opsForHyperLogLog().union("des_key","HyperLogLog","HyperLogLog2");
        System.out.println("des_key 长度："+ size);
    }
}
