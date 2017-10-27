<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2017/10/20
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="nav">
    <c:choose>
        <c:when test="${username != null}">
            <a class="mdl-navigation__link mdl-color-text--pink-400"
               href="/user/userProfile?username=${username}">${username}|</a>
            <c:if test="${username == 'admin'}">
                <a class="mdl-navigation__link mdl-color-text--black" href="/admin/manageCenter">Admin Gate|</a>
            </c:if>
            <a class="mdl-navigation__link mdl-color-text--black" href="/user/loginOut">Log out</a>
        </c:when>
        <c:when test="${username == null}">
            <a class="mdl-navigation__link mdl-color-text--pink-400" href="/user">Log in</a>
        </c:when>
    </c:choose>
</div>

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
            <a href="/publication">Publications</a>
        </li>
        <li>
            <a href="blog.html">Members</a>
        </li>
        <li>
            <a href="blog.html">Activities</a>
        </li>

    </ul>
</div>
