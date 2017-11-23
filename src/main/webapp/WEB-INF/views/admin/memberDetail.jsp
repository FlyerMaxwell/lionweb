<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
</head>
<body>
<c:if test="${sessionScope.userType!=0}">
    <jsp:forward page="../access.jsp"></jsp:forward>
</c:if>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
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
                <tr >
                    <td colspan="2">
                        ${user.detail}
                    </td>
                </tr>
                <c:if test="${user.cvUrl!=null && user.cvUrl!=''}">
                    <tr>
                        <td colspan="2">
                            Here is my <a href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${user.cvUrl}">CV</a>!
                        </td>
                    </tr>
                </c:if>

            </table>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>