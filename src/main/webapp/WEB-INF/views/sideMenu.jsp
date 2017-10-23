<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2017/10/23
  Time: 1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar">
    <h3>${username}'s Profile</h3>
    <ul>
        <li>
            <a href="/user/userProfile">My Info</a>
        </li>
        <li>
            <a href="/user/userProfile">My Project</a>
        </li>
        <li>
            <a href="/publication">My Publication</a>
        </li>
    </ul>
</div>