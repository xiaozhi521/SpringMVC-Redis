<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>Hello Spring MVC</title>
</head>
<body>
<h2>${message}</h2>
<h2>${ee}</h2>
<h2 onclick="addStudent(1)">增加学生信息(使用配置文件方式)</h2>
<button onclick="addStudent(2)">增加学生信息(继承 baseController 类方式)</button>

<button onclick="student()">模拟测试ModelAndView</button>

<form:form method="GET" style="display:none;" action="/staticPage.mvc">
    <table>
        <tr>
            <td>
                <input type="submit" value="获取HTML页面"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
<script>
    function student() {
        window.open("/student.mvc");
    }
    function addStudent(index) {
        if(index == 0){
            window.open("/addStudent.mvc?name=123");
        }else{
            window.open("/nonannotation/addStudent.mvc?name=123");
        }
    }
</script>
</html>
