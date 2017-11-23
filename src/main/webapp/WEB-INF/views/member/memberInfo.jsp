<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/statics/images/favicon.ico" type="image/x-icon" />
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
                                    <div>
                                        <a href="<%=request.getContextPath() %>/member/memberDetail?id=${user.id}">${user.userName}</a>
                                    </div>
                                    <c:if test="${user.webUrl!=null && user.webUrl!=''}">
                                        <div class="web_page">${user.webUrl}</div>
                                    </c:if>
                                    <div class="phone">${user.userPhone}</div>
                                    <div class="email">${user.userEmail}</div>
                                        <%--<div class="web">Web Page:<a></a></div>--%>
                                    <c:if test="${user.description!=null && user.description!=''}">
                                        <div>${user.description}</div>
                                    </c:if>
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