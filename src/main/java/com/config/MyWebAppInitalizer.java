package com.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitalizer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //spring IOC 容器配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //可以返回spring 的java 配置文件数组
        return new Class[0];
    }

    //DispatcherServlet 的 URL 映射关系配置
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //可以返回spring 的java 配置文件数组
        return new Class[0];
    }

    //DispatcherServlet 的 拦截内容
    @Override
    protected String[] getServletMappings() {
//        return new String[0];
        return new String[]{"*.mvc"};
    }
}
