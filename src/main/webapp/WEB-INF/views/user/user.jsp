<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2017/9/26
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("path", request.getContextPath());
%>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LION</title>
    <link rel="stylesheet" type="text/css" href="/statics/css/styles.css">
</head>
<body>
<div id="header">
    <a href="<%=request.getContextPath() %>/index" class="logo"><img src="/statics/images/logo.png" alt=""></a>
</div>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>Welcome</h1>

            <form class="form" action="/user/login" method="post">
                <input type="text" placeholder="Username" id="userName" name="userName">
                <input type="password" placeholder="Password" id="password" name="password">
                <input type="submit" value="login" id="login-button"/>
            </form>
        </div>

        <ul class="bg-bubbles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>

<script src="/statics/js/jquery-2.1.1.min.js" type="text/javascript"></script>

</body>
</html>