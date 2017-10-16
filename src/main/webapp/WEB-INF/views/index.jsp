<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Laboratory Website Template</title>
    <link rel="stylesheet" href="/statics/css/style.css" type="text/css">
</head>
<body>
<div id="header">
    <a href="<%=request.getContextPath() %>/index" class="logo"><img src="/statics/images/logo.png" alt=""></a>
    <ul>
        <li class="selected">
            <a href="<%=request.getContextPath() %>/index">home</a>
        </li>

        <li>
            <a href="about.html">News</a>
        </li>
        <li>
            <a href="location.html">Projects</a>
        </li>
        <li>
            <a href="contact.html">Publications</a>
        </li>
        <li>
            <a href="blog.html">Members</a>
        </li>
        <li>
            <a href="blog.html">Activities</a>
        </li>

        <li id="login">
            <a href="<%=request.getContextPath() %>/user.htm">login</a></li>


    </ul>
</div>

<div id="footer">
    <div>
        <p>
            <span>Lab of Innovation on Networking.</span><a href="#" >Terms of Service</a> | <a href="#" >Privacy Policy</a>
        </p>
        <ul>
            <li id="facebook">
                <a href="#">facebook</a>
            </li>
            <li id="twitter">
                <a href="#">twitter</a>
            </li>
            <li id="googleplus">
                <a href="#">googleplus</a>
            </li>
            <li id="rss">
                <a href="#" >rss</a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>