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
<c:if test="${sessionScope.username==null}">
    <jsp:forward page="../access.jsp"></jsp:forward>
</c:if>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <div class="content">
        <h2>Change Password</h2>
        <form method="POST" action="<%=request.getContextPath() %>/user/editPasswordInfo?&username=${username}"
              enctype="multipart/form-data">
            <label for="oldPass"> <span>Old Password</span>
                <input type="password" name="oldPass" id="oldPass">
            </label>
            <label for="newPass0"> <span>New Password</span>
                <input type="password" name="newPass0" id="newPass0">
            </label>
            <label for="newPass1"> <span>Confirm<br/>New Password</span>
                <input type="password" name="newPass1" id="newPass1">
            </label>

            <input type="submit" value="" id="submit">
        </form>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>