<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/statics/images/favicon.ico" type="image/x-icon"/>
</head>
<body>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <div class="content-display">
        <div class="container">
            <table>
                <tr>

                    <td colspan="2">
                        <div class="text">
                            <h2>${project.title}</h2>
                            <h3>Participants: ${project.authors}</h3>
                            <h3>Sponsors: ${project.organization}</h3>

                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="detail">
                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${project.imageUrl}&type=1">
                    </td>
                    <td>
                        <div>${project.description}</div>
                        <div><br/></div>
                        <div>
                            <c:if test="${project.textUrl != ''}">
                                        <span>
                                            <a class="download"
                                               href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${project.textUrl}">PDF</a>
                                        </span>
                            </c:if>
                            <c:if test="${project.slideUrl != ''}">
                                        <span>
                                            <a class="download"
                                               href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${project.slideUrl}">Slide</a>
                                        </span>
                            </c:if>
                            <c:if test="${project.videoUrl != ''}">
                                        <span>
                                            <a class="download"
                                               href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${project.videoUrl}">Video</a>
                                        </span>
                            </c:if>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="richText">
                        <div>${project.richText}</div>
                    </td>
                </tr>
                <tr><td colspan="2"><br/></td> </tr>

            </table>
        </div>

        <div class="container">
            <c:if test="${!empty publications}">
                <div class="bar">
                    <h3 class="bar">Related Publications</h3>
                </div>
                <table>

                    <c:forEach items="${publications}" var="publication">
                        <tr>
                            <td class="pub-picture">
                                <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${publication.imageUrl}&type=1">
                            </td>
                            <td>
                                <div class="text">
                                    <div>
                                        <a href="<%=request.getContextPath() %>/publication/publicationDetail?id=${publication.id}">${publication.title}</a>
                                    </div>
                                    <div>${publication.authors}</div>
                                    <div>${publication.organization}</div>

                                </div>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>