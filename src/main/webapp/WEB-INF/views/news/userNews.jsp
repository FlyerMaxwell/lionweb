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
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <div class="content">
        <ul>
            <a href="/news/addNews?username=${username}" class="add">New News</a>
        </ul>
        <div>
            <table width="100%" border="0" id="list">
                <c:choose>
                    <c:when test="${empty newsList}">
                        <div align="left">
                            <span>No News!</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${newsList}" var="news">
                            <tr height="100px" width="100%">
                                <td width="30%">
                                    <img src="/resource/showImage?imagePath=${news.imageUrl}" width="200px"
                                         height="150px">
                                </td>
                                </td>

                                <td width="20%">
                                    <a href="/news/newsDetail?username=${user.userName}&id=${news.id}">${news.title}</a>
                                </td>
                                <c:if test="${username!=null}">
                                    <%--编辑操作--%>
                                    <td width="7%">
                                        <a href="/news/editNews?username=${user.userName}&id=${news.id}">edit</a>
                                    </td>
                                    <%--删除操作--%>
                                    <td width="7%">
                                        <a href="/news/deleteNewsInfo?username=${user.userName}&id=${news.id}">delete</a>
                                    </td>
                                </c:if>
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