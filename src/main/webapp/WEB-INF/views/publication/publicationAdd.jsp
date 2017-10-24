<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2017/10/20
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="/statics/css/style.css" type="text/css">
</head>
<body>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <div class="content">
        <img src="images/telephone.jpg" alt="">
        <h2>Add New Publication</h2>
        <form method="POST" action="/publication/addPublicationInfo" enctype="multipart/form-data">
            <label for="title"> <span>Title *</span>
                <input type="text" name="title" id="title">
            </label>
            <label for="authors"> <span>Authors*</span>
                <input type="text" name="authors" id="authors">
            </label>
            <label for="publication"> <span>Publication*</span>
                <input type="text" name="publication" id="publication">
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