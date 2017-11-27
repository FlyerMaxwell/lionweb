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
                    <c:when test="${empty newsList}">
                        <div align="left">
                            <span>No News!</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${newsList}" var="news">
                            <tr class="list-display">
                                <%--<td class="picture">--%>
                                    <%--<img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${news.imageUrl}&type=1">--%>
                                <%--</td>--%>
                                <td class="text-display">
                                    <div class="text">
                                        <div>
                                            <a href="<%=request.getContextPath() %>/news/newsDetail?id=${news.id}">${news.title}</a>
                                        </div>
                                        <%--<div>${news.createTime}</div>--%>
                                    </div>

                                <c:if test="${username!=null&&userType==0}">
                                    <%--编辑操作--%>
                                    <span width="7%">
                                        <a href="<%=request.getContextPath() %>/news/editNews?username=${username}&panel=0&id=${news.id}">edit</a>
                                    </span>
                                    <%--删除操作--%>
                                    <span width="7%">
                                        <a href="#" onclick="sure(${news.id})">delete</a>
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
            window.location.href="<%=request.getContextPath() %>/news/deleteNewsInfo?username=${username}&panel=0&id="+itemId;
        }
    }
</script>