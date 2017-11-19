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
    <div class="content-display">
        <div class="container">
            <table>
                <tr>
                    <td class="detail">
                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${news.imageUrl}&type=1">
                    </td>
                    <td>
                        <div class="text">
                            <div>${news.title}</div>
                            <div>${news.createTime}</div>
                            <c:if test="${news.textUrl != ''}">
                                <div>
                                    <a href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${news.textUrl}">Text</a>
                                </div>
                            </c:if>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div>${news.description}</div>
                    </td>
                </tr>

            </table>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>