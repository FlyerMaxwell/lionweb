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
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <ul>
        <a href="<%=request.getContextPath() %>/admin/addMember" class="add">New Member</a>
    </ul>
    <div class="content">
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
                                            <a href="<%=request.getContextPath() %>/admin/memberDetail?id=${user.id}">${user.userName}</a>
                                        </div>
                                        <div>${user.userEmail}</div>
                                        <div>${user.description}</div>
                                    </div>
                                    <c:if test="${username!=null&&userType==0}">
                                        <%--编辑操作--%>
                                        <span width="7%">
                                            <a href="<%=request.getContextPath() %>/admin/editMember?id=${user.id}">edit</a>
                                        </span>
                                        <%--删除操作--%>
                                        <span width="7%">
                                            <a href="<%=request.getContextPath() %>/admin/deleteMemberInfo?admin=${username}&id=${user.id}">delete</a>
                                        </span>
                                    </c:if>
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