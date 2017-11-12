<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
</head>
<body>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div class="content">
    <div>
        <table width="100%" border="0" id="list">
            <c:choose>
                <c:when test="${empty users}">
                    <div align="left">
                        <span>No Members!</span>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${users}" var="user">
                        <tr height="100px" width="100%">
                            <td width="30%">
                                <img src="/resource/showImage?imagePath=${user.imageUrl}" alt="No Photo!" width="200px" height="150px">
                            </td>
                            <td width="20%">
                                <a href="/member/memberDetail?id=${user.id}">${user.userName}</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </table>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>