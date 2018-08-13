package com.redis.spring;

import com.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *  基于 RedisTemplate 、 基于连接池的操作，但是并不能能保证每次使用  RedisTemplate 是操作同一个对 Redis 的连接，
 *  比如下面这两行代码：
 *   redisTemplate.opsForValue().set("student" + student.getId(),student);
 *   Student student1 = (Student) redisTemplate.opsForValue().get("student1");
 *   解决这种问题--使用 SessionCallback 或者 RedisCallback
 *  使用Redis 保存角色类对象
 */
public class RedisSaveObject {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);

        Student student = new Student();
        student.setId(1);
        student.setAge(18);
        student.setName("mqf");
        redisTemplate.opsForValue().set("student" + student.getId(),student);
        Student student1 = (Student) redisTemplate.opsForValue().get("student1");
        System.out.println(student1.toString());
    }


}
