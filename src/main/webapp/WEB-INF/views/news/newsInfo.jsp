<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="/statics/css/style.css" type="text/css">
</head>
<body>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <div class="content">
        <div>
            <table width="1000px" border="0" id="list">
                <c:choose>
                    <c:when test="${empty newsList}">
                        <div align="left">
                            <span>No News!</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${newsList}" var="news">
                            <tr height="100px">
                                <td width="30%">
                                    <img src="/resource/showImage?imagePath=${news.imageUrl}" width="200px" height="150px">
                                </td>
                                <td width="70%">
                                    <a href="/news/newsDetail?username=${user.userName}&id=${news.id}">${news.title}</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>