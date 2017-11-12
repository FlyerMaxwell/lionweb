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
<div id="body">
    <div class="content-display">
        <div class="container">
        <table>
            <c:choose>
                <c:when test="${empty users}">
                    <div align="left">
                        <span>No User!</span>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td class="photo">
                                <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${user.imageUrl}&type=0">
                            </td>
                            <td>
                                <div class="text">
                                    <div>${user.userName}</div>
                                    <div>${user.userEmail}</div>
                                    <div>${user.description}</div>
                                </div>
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