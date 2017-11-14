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
                    <td class="picture">
                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${project.imageUrl}&type=1">
                    </td>
                    <td>
                        <div class="text">
                            <div>${project.title}</div>
                            <div>${project.authors}</div>
                            <div>${project.organization}</div>
                            <div>
                                <c:if test="${project.textUrl != ''}">
                                        <span>
                                            <a href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${project.textUrl}">Text</a>
                                        </span>
                                </c:if>
                                <c:if test="${project.slideUrl != ''}">
                                        <span>
                                            <a href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${project.slideUrl}">Slide</a>
                                        </span>
                                </c:if>
                                <c:if test="${project.videoUrl != ''}">
                                        <span>
                                            <a href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${project.videoUrl}">Video</a>
                                        </span>
                                </c:if>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div>${project.description}</div>
                    </td>
                </tr>

            </table>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>