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
    <ul>
        <a href="<%=request.getContextPath() %>/admin/addMember" class="add">New Member</a>
    </ul>
    <div class="content">
        <div class="container">
            <table class="member">
                <c:choose>
                    <c:when test="${empty users}">
                        <div align="left">
                            <span>No User!</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${users}" var="user">
                            <tr class="list-display">
                                <td class="photo">
                                    <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${user.imageUrl}&type=0">
                                </td>
                                <td class="text-display">
                                    <div class="text">
                                        <div>Username: ${user.userName}</div>
                                        <div><a href="<%=request.getContextPath() %>/admin/memberDetail?id=${user.id}">Real Name: ${user.realName}</a></div>
                                        <div>${user.description}</div>
                                        <div class="email">Email: ${user.userEmail}</div>
                                        <c:if test="${user.userPhone!=null && user.userPhone!=''}">
                                            <div class="phone">Tel: ${user.userPhone}</div>
                                        </c:if>
                                        <c:if test="${user.webUrl!=null && user.webUrl!=''}">
                                            <div class="web_page">Web page :${user.webUrl}</div>
                                        </c:if>
                                    </div>
                                    <c:if test="${username!=null&&userType==0}">
                                        <%--编辑操作--%>
                                        <span width="7%">
                                            <a href="<%=request.getContextPath() %>/admin/editMember?id=${user.id}">edit</a>
                                        </span>
                                        <%--删除操作--%>
                                        <span width="7%">
                                            <%--<a href="<%=request.getContextPath() %>/admin/deleteMemberInfo?admin=${username}&id=${user.id}">delete</a>--%>
                                            <a href="#" onclick="sure(${user.id})">delete</a>
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
            window.location.href="<%=request.getContextPath() %>/admin/deleteMemberInfo?admin=${username}&id="+itemId;
        }
    }
</script>