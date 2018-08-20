package com.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *  通过Spring 操作 redis 的链表结构
 */
public class ListRedis {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        String key = "list";
        printValueList(redisTemplate,key);
        // 删除链表，可以反复测试
        redisTemplate.delete(key);
        // 把 node5 插入到列表中
        redisTemplate.opsForList().leftPush(key,"node3");
        List<String> nodeList = new ArrayList<>();
        for (int i = 0; i < 5 ; i++){
            nodeList.add("node" + i);
        }
        // 想当于 lpush 把多个值从左值入链表
        redisTemplate.opsForList().leftPushAll(key,nodeList);
        printValueList(redisTemplate,key);
        // 从右边插入一个节点
        redisTemplate.opsForList().rightPush(key,"right1");
        //获取下标为零的节点
        printValueList(redisTemplate,key);
        //获取链表的长度
        Long size = redisTemplate.opsForList().size(key);
        System.out.println("List 长度：" + size);
        //从左边弹出一个节点
        String leftPopValue = (String) redisTemplate.opsForList().leftPop(key);
        System.out.println("leftPop:"+leftPopValue);
        //注意：需要使用更为底层的命令才能操作linsert 操作
        //使用 linsert 命令在node2 前插入一个节点
        redisTemplate.getConnectionFactory().getConnection().lInsert(key.getBytes("UTF-8"),
                RedisListCommands.Position.BEFORE, "node2".getBytes("UTF-8"), "before_node".getBytes("UTF-8"));

        // 使用 linsert 命令在 node2 后插入一个节点
        redisTemplate.getConnectionFactory().getConnection().lInsert(key.getBytes("UTF-8"),RedisListCommands.Position.AFTER,
                "node2".getBytes("UTF-8"),"after_node".getBytes("UTF-8"));

        //判断list 是否存在，如果存在则从左边插入head节点
        redisTemplate.opsForList().leftPushIfPresent(key,"head----leftPushIfPresent");
        //判断list 是否存在，如果存在则从右边插入head节点
        redisTemplate.opsForList().rightPushIfPresent(key,"end----rigthPushIfPresent");
        //从左到右，或者下标从 0 到 10 的节点元素
        List listValues = redisTemplate.opsForList().range(key,0,10);
        nodeList.clear();
        for(int i = 1;i <=3 ; i++){
            nodeList.add("node");
        }
        //在链表左边插入三个值为node 的节点
        redisTemplate.opsForList().leftPushAll(key,nodeList);
        //从左到右删除至多三个node节点
        redisTemplate.opsForList().remove(key,3,"node");
        //给链表下标为 0 的下标设置新值
        redisTemplate.opsForList().set(key,0,"new_node_value");
        //获取链表的长度
        size = redisTemplate.opsForList().size(key);
        System.out.println("key length2:" + size);
        printValueList(redisTemplate,key);
        System.out.println("第一个值："+redisTemplate.opsForList().range(key,0,0));
    }

    public static List getListValue(RedisTemplate redisTemplate,String key){
        long size = redisTemplate.opsForList().size(key);
        List list = redisTemplate.opsForList().range(key,0,size);
        return list;
    }
    public static void printValueList(RedisTemplate redisTemplate,String key){
        long size = redisTemplate.opsForList().size(key);
        System.out.println("key length:" + size);
        List list = redisTemplate.opsForList().range(key,0,size);
        System.out.println(list);
    }
}
