package com.redis.spring;

import com.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

/**
 *  为了使得所有的操作来自于同一个连接，可以使用 SessionCallback 或者 RedisCallback 这两个接口
 *  RedisCallback 是比较底层的封装，其使用不是很友好所以更多的时候会使用 SessionCallback 这个接口
 *  通过  SessionCallback 接口，就可以把多个命令放入到同一个 Redis 连接中去执行。
 *  它解决了 “RedisTemplate 是操作同一个对 Redis 的连接”问题
 */
public class SessionCallBackRedisSaveObject {
    public static void main(String[] args) {
        testSesssionCallBackRedisSaveObject();
    }
    public static void testSesssionCallBackRedisSaveObject(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        final Student student = new Student();
        student.setId(2);
        student.setAge(18);
        student.setName("mqf_2");
        SessionCallback sessionCallback = new SessionCallback<Student>() {
            @Override
            public Student execute(RedisOperations operations) throws DataAccessException {
                operations.boundValueOps("student2").set(student);
                return (Student) operations.boundValueOps("student2").get();
            }
        };

        Student saveStudent = (Student) redisTemplate.execute(sessionCallback);
        System.out.println(saveStudent.toString());
    }
}
