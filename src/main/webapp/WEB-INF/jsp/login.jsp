<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<html>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2018/5/12
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/common.jsp" %>
<html>
<head>
    <title>Login Spring MVC</title>
</head>
<body>
<h2>登录</h2>
<form method="post" action="/login/login.mvc">
    用户名：<input type="text" name="userName" id="userName" value="muQingFeng">
    密码：<input type="password" name="pwd" id="pwd" value="123456">
    <input type="submit" value="登录">
</form>
</body>
</html>
