<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2017/10/20
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <script src="<%=request.getContextPath() %>/statics/js/jquery-3.2.1.js"></script>
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/statics/images/favicon.ico" type="image/x-icon" />
</head>

<script>
    var urlstr = location.href;
    var urlstatus=false;
    $("#main-nav li").each(function () {
        if ((urlstr + '/').indexOf($(this).attr('href')) > -1&&$(this).attr('href')!="") {
            $(this).addClass('selected');
            urlstatus = true;
        } else {
            $(this).removeClass('selected');
        }
    });
    if (!urlstatus) {$("#main-nav li").eq(0).addClass('selected');}
</script>

<div id="nav">
    <c:choose>
        <c:when test="${username != null}">
            <a class="mdl-navigation__link mdl-color-text--pink-400"
               href="<%=request.getContextPath() %>/user/userProfile?username=${username}">${username}|</a>
            <a class="mdl-navigation__link mdl-color-text--black" href="<%=request.getContextPath() %>/user/loginOut">Log out</a>
        </c:when>
        <c:when test="${username == null}">
            <a class="mdl-navigation__link mdl-color-text--pink-400" href="<%=request.getContextPath() %>/login">Log in</a>
        </c:when>
    </c:choose>
</div>

<div id="header">
    <%--<a href="<%=request.getContextPath() %>/index" class="logo"><img src="<%=request.getContextPath() %>/statics/images/logo.png" alt=""></a>--%>

    <ul id="main-nav">
        <li>
            <a href="<%=request.getContextPath() %>/index" >home</a>
        </li>

        <li>
            <a href="<%=request.getContextPath() %>/news" >News</a>
        </li>
        <li>
            <a href="<%=request.getContextPath() %>/project/" >Projects</a>
        </li>
        <li>
            <a href="<%=request.getContextPath() %>/publication">Publications</a>
        </li>
        <li>
            <a href="<%=request.getContextPath() %>/member" >Members</a>
        </li>


    </ul>
</div>

<%--<script>--%>
    <%--function change() {--%>
        <%--$("ul.main-nav li:active").removeClass('selected');--%>
        <%--$(this).addClass('selected');--%>
    <%--}--%>
<%--</script>--%>
<%--<script>--%>
<%--$(function(){--%>
    <%--$("#main-nav li").click(function() {--%>
        <%--$(this).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式--%>
        <%--$(this).addClass('selected');                            // 添加当前元素的样式--%>
        <%--});--%>
<%--});--%>
<%--</script>--%>


