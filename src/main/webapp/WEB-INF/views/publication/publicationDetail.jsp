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
                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${publication.imageUrl}&type=1">
                    </td>
                    <td>
                        <div class="text">
                            <div>${publication.title}</div>
                            <div>${publication.authors}</div>
                            <div>${publication.organization}</div>
                            <div>
                                <c:if test="${publication.textUrl != ''}">
                                        <span>
                                            <a href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${publication.textUrl}">Paper</a>
                                        </span>
                                </c:if>
                                <c:if test="${publication.slideUrl != ''}">
                                        <span>
                                            <a href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${publication.slideUrl}">Slide</a>
                                        </span>
                                </c:if>
                                <c:if test="${publication.videoUrl != ''}">
                                        <span>
                                            <a href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${publication.videoUrl}">Video</a>
                                        </span>
                                </c:if>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                    <div>${publication.description}</div>
                    </td>
                </tr>

            </table>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>