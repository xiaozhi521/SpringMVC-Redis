package com.redis.spring.pub_sub;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Scanner;

/**
 *  消息发布订阅
 */
public class RedisMessageListener implements MessageListener {
    private RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        return applicationContext.getBean(RedisTemplate.class);
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //获取消息
        byte[] body = message.getBody();
        //使用值序列化转换
        String msgBody = (String) getRedisTemplate().getValueSerializer().deserialize(body);
        System.err.println(msgBody);
        //获取channel
        byte[] channel = message.getChannel();
        //使用字符串序列化转换
        String channelStr = (String) getRedisTemplate().getStringSerializer().deserialize(channel);
        System.err.println(channelStr);
        //渠道名称转换
        String bytes = new String(pattern);
        System.err.println(bytes);
    }

    public static void main(String[] args) {
        System.out.print("请输入:");
        String showInfo=new Scanner(System.in).next();
        System.out.println(showInfo);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate redisTemplate =  applicationContext.getBean(RedisTemplate.class);
        String channel = "chat";
        redisTemplate.convertAndSend(channel,showInfo);



    }
}
