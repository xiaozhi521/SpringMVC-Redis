<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2018/5/13
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Spring MVC表单处理</title>
</head>
<body>
<%@ include file="/WEB-INF/common.jsp" %>
<h2>Student Information </h2>

<h2>第一种方式： ${message }</h2>
<h2>第二种方式： ${message1 }</h2>
<form method="POST" action="/nonannotation/addStudentOK.mvc">

    <table>
        <td>
            <tr >名字:</tr>
            <input name="name" id="name"/>
        </td>
        <td>
            <tr >年龄:</tr>
            <input name="age" id="age"/>
        </td>
        <td>
            <tr >编号:</tr>
            <input name="id" id="id"/>
        </td>

        <tr>
            <td colspan="2">
                <input type="submit" value="提交表单"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <%--<input type="button" onclick="addStudentOK()" value="保存"/>--%>
                <a href="/nonannotation/addStudentOK.mvc?age=1&age=34&id=23">保存</a>
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    function addStudentOK() {
        var name = document.getElementById("name");
        var age = document.getElementById("age");
        var id = document.getElementById("id");
        console.log(name);
        console.log(age);
        console.log(id);
        return;
        window.open("/nonannotation/addStudentOK.mvc?age=" + name + "&age=" + age + "&id=" + id);
    }
</script>
</html>
