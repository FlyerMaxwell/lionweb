<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
</head>

<%--<%@ include file="/statics/html/header.html" %>--%>
<body>
<jsp:include page="header.jsp" flush="true"></jsp:include>
<div id="body">
    <div class="content-display">
        <div class="container">
            <div class="row">
                <a class="more" href="<%=request.getContextPath() %>/news/">more</a>
                <h2>Recent News</h2>
                <ul>
                    <c:forEach items="${newsList}" var="news">
                        <li>
                            <a href="<%=request.getContextPath() %>/news/newsDetail?id=${news.id}">${news.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="row">
                <a class="more" href="<%=request.getContextPath() %>/publication/">more</a>
                <h2>Recent Publication</h2>
                <ul>
                    <c:forEach items="${publicationList}" var="publication">
                        <li>
                            <a href="<%=request.getContextPath() %>/publication/publicationDetail?id=${publication.id}">${publication.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="row">
                <a class="more" href="<%=request.getContextPath() %>/project/">more</a>
                <h2>Recent Project</h2>
                <ul>
                    <c:forEach items="${projectList}" var="project">
                        <li>
                            <a href="<%=request.getContextPath() %>/project/projectDetail?id=${project.id}"> ${project.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>