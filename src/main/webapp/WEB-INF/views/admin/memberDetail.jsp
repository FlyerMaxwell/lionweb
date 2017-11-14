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
                    </td>
                </tr>
                <tr >
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