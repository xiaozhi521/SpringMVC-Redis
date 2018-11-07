package com.redis.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *  手写实现redis set get 操作
 *
 *  @author mqf
 */
public class MyRedis {
    Socket socket;
    InputStream reader;
    OutputStream writer;
    public MyRedis(String host, Integer port) throws IOException {
        socket = new Socket(host,port);
        writer = socket.getOutputStream();
        reader = socket.getInputStream();
    }
//    *<number of arguments> CR LF
//    $<number of bytes of argument 1> CR LF
//    <argument data> CR LF
//...
//    $<number of bytes of argument N> CR LF
//    <argument data> CR LF
    public void set(String key,String value) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        //参数数量
        stringBuilder.append("*3").append("\r\n");
        //第一个参数的长度
        stringBuilder.append("$3").append("\r\n");
        //第一个参数的值
        stringBuilder.append("SET").append("\r\n");

        //第二个参数的长度，是byte的长度，不是 String的长度，中文占3个byte长度
        stringBuilder.append("$").append(key.getBytes().length).append("\r\n");
        //第二个参数的值
        stringBuilder.append(key).append("\r\n");


        //第三个参数的长度，是byte的长度，不是 String的长度，中文占3个byte长度
        stringBuilder.append("$").append(value.getBytes().length).append("\r\n");
        //第三个参数的值
        stringBuilder.append(value).append("\r\n");

        //发送到服务器端
        writer.write(stringBuilder.toString().getBytes());

        //接收redis服务器端的响应
        byte[] response = new byte[1024];
        int a = reader.read(response);
        System.out.println(a);
        System.out.println(stringBuilder.toString());
    }

    public String get(String key) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*3").append("\r\n");
        stringBuilder.append("$3").append("\r\n");
        stringBuilder.append("SET").append("\r\n");

        stringBuilder.append("$").append(key.getBytes().length).append("\r\n");
        stringBuilder.append(key).append("\r\n");


        //发送到服务器端
        writer.write(stringBuilder.toString().getBytes());

        //接收redis服务器端的响应
        byte[] response = new byte[1024];
        int a = reader.read(response);
        return new String(response);
    }

//    *3
//    $3
//    SET
//    $5
//    hello
//    $3
//    mqf
    public static void main(String[] args) throws Exception {
        MyRedis myRedis = new MyRedis("127.0.0.1",6379);
        myRedis.set("hello","mqf");
        System.out.println(myRedis.get("hello"));
    }
}
