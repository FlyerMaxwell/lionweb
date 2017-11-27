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
        <h2>Change Photo</h2>
        <form method="POST" action="<%=request.getContextPath() %>/user/editPhotoInfo?&username=${username}"
              enctype="multipart/form-data">
            <label for="image"> <span>Image<br/>(scale 3:4)</span>
                <a href="javascript:;" class="file">
                    <input type="file" name="image" id="image">
                </a>
            </label>
            <input type="submit" value="" id="submit">
        </form>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>