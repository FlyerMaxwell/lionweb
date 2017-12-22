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
    <h3 class="large-category">PROJECTS</h3>
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
                        <c:if test="${project.access==0 || sessionScope.userType!=null}">
                            <tr class="list-display">
                                <td class="pro-picture">
                                    <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${project.imageUrl}&type=1">
                                </td>
                                <td class="text-display">
                                    <div class="text">
                                        <div class="title">
                                            <a href="<%=request.getContextPath() %>/project/projectDetail?id=${project.id}">${project.title}</a>
                                            <c:if test="${project.access == 1}">
                                                <span class="access">
                                                    Group
                                                </span>
                                            </c:if>
                                        </div>
                                        <div>Participants:${project.authors}</div>
                                        <div>Sponsors:${project.organization}</div>
                                        <div>
                                            <c:if test="${project.textUrl != ''}">
                                                <span>
                                                    <a class="download" href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${project.textUrl}">PDF</a>
                                                </span>
                                            </c:if>
                                            <c:if test="${project.slideUrl != ''}">
                                                <span>
                                                    <a class="download" href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${project.slideUrl}">Slide</a>
                                                </span>
                                            </c:if>
                                            <c:if test="${project.videoUrl != ''}">
                                                <span>
                                                    <a class="download" href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${project.videoUrl}">Video</a>
                                                </span>
                                            </c:if>

                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:if>

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