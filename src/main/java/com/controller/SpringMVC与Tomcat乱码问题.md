中文乱码总结：
#### 一、页面编码
```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
``` 
#### 二、URL 中的乱码
改tomcat中server.xml中Connector的port=“8080”，加上一个 URIEncoding=”utf-8”
```xml
    <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" 
               URIEncoding=”utf-8” />
    <!-- A "Connector" using the shared thread pool-->
```
#### 三、修改web.xml ,增加编码过滤器
```xml
    <filter>
        <filter-name>springUtf8Encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>springUtf8Encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```
#### 四、数据库连接
```xml
jdbc:mysql://localhost:3306/mp?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC
```
**以上四种在Tomcat9下，SpringMVC 中文未产生乱码；但是在 Tomcat7 下产生了乱码，需要用下面的方法解决**

#### 五、URL中文参数乱码
##### 1、springMVC
```java
//编码
url = URLEncoder.encode(url, "UTF-8");
//解码
url = URLDecoder.decode(url, "UTF-8");
```
##### 2、struts等其它
```java
String name = new String(str.getBytes("iso8859_1"),"UTF-8");
```