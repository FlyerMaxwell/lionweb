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
               href="<%=request.getContextPath() %>/user/userProfile?username=${username}">${username}|</a>
            <a class="mdl-navigation__link mdl-color-text--black" href="<%=request.getContextPath() %>/user/loginOut">Log out</a>
        </c:when>
        <c:when test="${username == null}">
            <a class="mdl-navigation__link mdl-color-text--pink-400" href="<%=request.getContextPath() %>/login">Log in</a>
        </c:when>
    </c:choose>
</div>

<div id="header">
    <a href="<%=request.getContextPath() %>/index" class="logo"><img src="<%=request.getContextPath() %>/statics/images/logo.png" alt=""></a>

    <ul class="main-nav">
        <li class="selected" >
            <a href="<%=request.getContextPath() %>/index" onclick="change()">home</a>
        </li>

        <li>
            <a href="<%=request.getContextPath() %>/news" onclick="change()">News</a>
        </li>
        <li>
            <a href="<%=request.getContextPath() %>/project/" onclick="change()">Projects</a>
        </li>
        <li>
            <a href="<%=request.getContextPath() %>/publication" onclick="change()">Publications</a>
        </li>
        <li>
            <a href="<%=request.getContextPath() %>/member" onclick="change()">Members</a>
        </li>


    </ul>
</div>

<script>
    function change() {
        var obj=document.getElementsByClassName("main-nav");
        for(var i=0;i<obj.length;i++){
            obj[i].removeClass('selected');
        }
        this.addClass('selected');
    }
</script>


