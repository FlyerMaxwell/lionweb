<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
    <div class="content">
        <div>
            <table width="1000px" border="0" id="list">
                <tr height="100px">
                    <td width="20%">
                        <img src="/resource/showImage?imagePath=${user.imageUrl}" width="400px" height="300px">
                    </td>
                    <td width="80%">
                        ${user.userName}
                    </td>
                </tr>
                <tr height="100px">
                    <td width="100%" >
                        ${user.description}
                    </td>
                </tr><tr height="100px">
                <td width="100%" >
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