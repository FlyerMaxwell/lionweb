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
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <div class="content-display">
        <div class="container">
        <table>
            <c:choose>
                <c:when test="${empty projects}">
                    <div align="left">
                        <span>No Project!</span>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${projects}" var="project">
                        <tr class="list-display">
                            <td class="picture">
                                <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${project.imageUrl}&type=1">
                            </td>
                            <td class="text-display">
                                <div class="text">
                                    <div>
                                        <a href="<%=request.getContextPath() %>/project/projectDetail?id=${project.id}">${project.title}</a>
                                    </div>
                                    <div>${project.authors}</div>
                                    <div>${project.organization}</div>
                                </div>
                            </td>
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