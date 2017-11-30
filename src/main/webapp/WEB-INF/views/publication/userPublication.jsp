<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2017/10/20
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/statics/images/favicon.ico" type="image/x-icon" />
</head>
<body>
<c:if test="${sessionScope.userType==null}">
    <jsp:forward page="../access.jsp"></jsp:forward>
</c:if>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <div class="content">
        <ul>
            <span class="add">Add Publication</span>
            <a href="<%=request.getContextPath() %>/publication/addPublication?username=${user.userName}" class="add">+</a>
        </ul>
        <div class="container">
            <table>
                <c:choose>
                    <c:when test="${empty publications}">
                        <div align="left">
                            <span>No Publication!</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${publications}" var="publication">
                            <tr class="list-display">
                                <td class="pub-picture">
                                    <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${publication.imageUrl}&type=1">
                                </td>
                                <td class="text-display">
                                    <div class="text">
                                        <div class="title">
                                            <a href="<%=request.getContextPath() %>/publication/publicationDetail?id=${publication.id}">${publication.title}</a>
                                        </div>
                                        <div>${publication.authors}</div>
                                        <div>${publication.organization}</div>

                                    </div>


                                <c:if test="${username!=null}">
                                    <%--编辑操作--%>
                                    <span class="edit">
                                            <a class="edit" href="<%=request.getContextPath() %>/publication/editPublication?username=${username}&id=${publication.id}">edit</a>
                                    </span>
                                    <%--删除操作--%>
                                    <span class="delete">
                                        <a class="delete" href="#" onclick="sure(${publication.id})">delete</a>
                                    </span>
                                </c:if>
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
<script>
    function sure(itemId) {
        var conf=confirm("Confirm Deletion?");
        if(conf==true){
            window.location.href="<%=request.getContextPath() %>/publication/deletePublicationInfo?username=${username}&id="+itemId;
        }
    }
</script>