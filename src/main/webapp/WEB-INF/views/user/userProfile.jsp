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
<c:if test="${sessionScope.userType==null}">
    <jsp:forward page="../access.jsp"></jsp:forward>
</c:if>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <div class="content">
        <div class="container">
            <table>
                <tr height="100px">
                    <td class="photo">
                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${user.imageUrl}&type=0">
                    </td>
                    <td>
                        <div class="text">
                            <div>${user.userName}</div>
                            <div>${user.userEmail}</div>
                            <div>${user.description}</div>
                        </div>
                        <c:if test="${username!=null&&username==user.userName}">
                            <%--编辑操作--%>
                            <span width="7%">
                                <a href="<%=request.getContextPath() %>/user/editUser?username=${username}">edit</a>
                            </span>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        ${user.detail}
                    </td>
                </tr>

            </table>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>