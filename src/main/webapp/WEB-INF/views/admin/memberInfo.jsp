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
    <div class="content">
        <ul>
            <a href="<%=request.getContextPath() %>/admin/addMember?admin=${username}" class="add">New Member</a>
        </ul>
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
                                    <img src="/resource/showImage?imagePath=${user.imageUrl}" width="200px"
                                         height="150px">
                                </td>
                                <td width="20%">
                                    <a href="/admin/memberDetail?id=${user.id}">${user.userName}</a>
                                </td>

                                <c:if test="${userType==0}">
                                    <%--编辑操作--%>
                                    <td width="7%">
                                        <a href="/admin/editMember?admin=${username}&id=${user.id}">edit</a>
                                    </td>
                                    <%--删除操作--%>
                                    <td width="7%">
                                        <a href="/admin/deleteMemberInfo?admin=${username}&id=${user.id}">delete</a>
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