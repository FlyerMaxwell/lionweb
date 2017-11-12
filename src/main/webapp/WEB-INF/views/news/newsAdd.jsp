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
        <h2>Add New News</h2>
        <form method="POST" action="<%=request.getContextPath() %>/news/addNewsInfo?username=${username}" enctype="multipart/form-data">
            <label for="title"> <span>Title *</span>
                <input type="text" name="title" id="title">
            </label>
            <label for="description"> <span>Description*</span>
                <input type="text" name="description" id="description">
            </label>
            <label for="image"> <span>Image*</span>

                <a href="javascript:;" class="file">
                    <input type="file" name="image" id="image" >
                </a>
            </label>
            <label for="text"> <span>Text*</span>

                <a href="javascript:;" class="file">
                    <input type="file" name="text" id="text" >
                </a>
            </label>
            <input type="submit" value="" id="submit">
        </form>
    </div>

</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>