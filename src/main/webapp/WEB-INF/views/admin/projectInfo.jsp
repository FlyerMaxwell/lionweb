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
<c:if test="${sessionScope.userType!=0}">
    <jsp:forward page="../access.jsp"></jsp:forward>
</c:if>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <div class="content">
        <div class="container">
            <table>
                <c:choose>
                    <c:when test="${empty projects}">
                        <div align="left">
                            <span>No Project!</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${labels}" var="label" varStatus="status">
                            <c:if test="${! empty projects[status.index]}">
                                <tr class="project-title">
                                    <td colspan="2"><div>${label.name}</div></td>
                                </tr>
                                <c:forEach items="${projects[status.index]}" var="project">
                                    <tr class="list-display">
                                        <td class="pub-picture">
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
                                            </div>

                                            <c:if test="${username!=null&&userType==0}">
                                                <%--编辑操作--%>
                                                <span class="edit">
                                                    <a class="edit"
                                       href="<%=request.getContextPath() %>/project/editProject?username=${username}&panel=0&id=${project.id}">edit</a>
                                                </span>
                                                <%--删除操作--%>
                                                <span class="delete">
                                                    <a class="delete" href="#" onclick="sure(${project.id})">delete</a>
                                                </span>
                                                <%--在标签内上移project--%>
                                                <span class="up">
                                                    <a class="up" href="<%=request.getContextPath() %>/admin/upProject?labelId=${label.id}&id=${project.id}">up</a>
                                                </span>
                                                <%--在标签内下移project--%>
                                                <span class="down">
                                                    <a class="down" href="<%=request.getContextPath() %>/admin/downProject?labelId=${label.id}&id=${project.id}">down</a>
                                                </span>
                                            </c:if>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                    <%--<c:when test="${empty projects}">--%>
                        <%--<div align="left">--%>
                            <%--<span>No Project!</span>--%>
                        <%--</div>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<c:forEach items="${projects}" var="project">--%>
                            <%--<tr class="list-display">--%>
                                <%--<td class="pub-picture">--%>
                                    <%--<img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${project.imageUrl}&type=1">--%>
                                <%--</td>--%>
                                <%--<td class="text-display">--%>
                                    <%--<div class="text">--%>
                                        <%--<div class="title">--%>
                                            <%--<a href="<%=request.getContextPath() %>/project/projectDetail?id=${project.id}">${project.title}</a>--%>
                                            <%--<c:if test="${project.access == 1}">--%>
                                                <%--<span class="access">--%>
                                                    <%--Group--%>
                                                <%--</span>--%>
                                            <%--</c:if>--%>
                                        <%--</div>--%>
                                        <%--<div>Participants:${project.authors}</div>--%>
                                        <%--<div>Sponsors:${project.organization}</div>--%>
                                    <%--</div>--%>

                                    <%--<c:if test="${username!=null&&userType==0}">--%>
                                        <%--&lt;%&ndash;编辑操作&ndash;%&gt;--%>
                                        <%--<span class="edit">--%>
                                        <%--<a class="edit" href="<%=request.getContextPath() %>/project/editProject?username=${username}&panel=0&id=${project.id}">edit</a>--%>
                                    <%--</span>--%>
                                        <%--&lt;%&ndash;删除操作&ndash;%&gt;--%>
                                        <%--<span class="delete">--%>
                                            <%--<a class="delete" href="#" onclick="sure(${project.id})">delete</a>--%>
                                        <%--</span>--%>
                                    <%--</c:if>--%>
                                <%--</td>--%>

                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                    <%--</c:otherwise>--%>
                </c:choose>
            </table>
        </div>
    </div>

</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>
<script>
    function sure(itemId) {
        var conf=confirm("Confirm Deletion?");
        if(conf==true){
            window.location.href="<%=request.getContextPath() %>/project/deleteProjectInfo?username=${username}&panel=0&id="+itemId;
        }
    }
</script>