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
<c:if test="${sessionScope.userType!=0}">
    <jsp:forward page="../access.jsp"></jsp:forward>
</c:if>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>

    <div class="content">
        <ul>
            <span class="add">Add Member</span>
            <a href="<%=request.getContextPath() %>/admin/addMember" class="add">+</a>
        </ul>
        <div class="container">
            <table class="member">
                <c:choose>
                    <c:when test="${empty professorList && empty graduateList 
                    && empty undergraduateList && empty alumniList}">
                        <div align="left">
                            <span>No User!</span>
                        </div>
                    </c:when>
                    <c:otherwise>

                        <c:if test="${!empty professorList}">
                            <tr class="member-title"><td colspan="2"><div>Professor</div></td></tr>
                            <c:forEach items="${professorList}" var="professor">
                                <tr class="list-display">
                                    <td class="photo">
                                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${professor.imageUrl}&type=0">
                                    </td>
                                    <td class="text-display">
                                        <div class="text">
                                            <div>Username: ${professor.userName}</div>
                                            <div class="title">
                                                <a href="<%=request.getContextPath() %>/admin/memberDetail?id=${professor.id}">Real
                                                    Name: ${professor.realName}</a></div>
                                            <div>${professor.description}</div>
                                            <div class="email">Email: ${professor.userEmail}</div>
                                            <c:if test="${professor.userPhone!=null && professor.userPhone!=''}">
                                                <div class="phone">Tel: ${professor.userPhone}</div>
                                            </c:if>
                                            <c:if test="${professor.webUrl!=null && professor.webUrl!=''}">
                                                <div class="web_page">Web page :${professor.webUrl}</div>
                                            </c:if>
                                        </div>
                                        <c:if test="${username!=null&&userType==0}">
                                            <%--编辑操作--%>
                                            <span class="edit">
                                                <a class="edit" href="<%=request.getContextPath() %>/admin/editMember?id=${professor.id}">edit</a>
                                            </span>
                                            <%--删除操作--%>
                                            <span class="delete">
                                            <%--<a href="<%=request.getContextPath() %>/admin/deleteMemberInfo?admin=${username}&id=${user.id}">delete</a>--%>
                                                <a class="delete" href="#" onclick="sure(${professor.id})">delete</a>
                                            </span>
                                            <span class="reset">
                                                <a class="reset" href="#" onclick="confirmReset(${professor.id})">reset</a>
                                            </span>
                                            <span class="up">
                                                <a class="up" href="<%=request.getContextPath() %>/admin/upMember?id=${professor.id}">up</a>
                                            </span>
                                            <span class="down">
                                                <a class="down" href="<%=request.getContextPath() %>/admin/downMember?id=${professor.id}">down</a>
                                            </span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

                        <c:if test="${!empty graduateList}">
                            <tr class="member-title"><td colspan="2"><div>Graduate</div></td></tr>
                            <c:forEach items="${graduateList}" var="graduate">
                                <tr class="list-display">
                                    <td class="photo">
                                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${graduate.imageUrl}&type=0">
                                    </td>
                                    <td class="text-display">
                                        <div class="text">
                                            <div>Username: ${graduate.userName}</div>
                                            <div class="title">
                                                <a href="<%=request.getContextPath() %>/admin/memberDetail?id=${graduate.id}">Real
                                                    Name: ${graduate.realName}</a></div>
                                            <div>${graduate.description}</div>
                                            <div class="email">Email: ${graduate.userEmail}</div>
                                            <c:if test="${graduate.userPhone!=null && graduate.userPhone!=''}">
                                                <div class="phone">Tel: ${graduate.userPhone}</div>
                                            </c:if>
                                            <c:if test="${graduate.webUrl!=null && graduate.webUrl!=''}">
                                                <div class="web_page">Web page :${graduate.webUrl}</div>
                                            </c:if>
                                        </div>
                                        <c:if test="${username!=null&&userType==0}">
                                            <%--编辑操作--%>
                                            <span class="edit">
                                                <a class="edit" href="<%=request.getContextPath() %>/admin/editMember?id=${graduate.id}">edit</a>
                                            </span>
                                            <%--删除操作--%>
                                            <span class="delete">
                                            <%--<a href="<%=request.getContextPath() %>/admin/deleteMemberInfo?admin=${username}&id=${user.id}">delete</a>--%>
                                                <a class="delete" href="#" onclick="sure(${graduate.id})">delete</a>
                                            </span>
                                            <span class="reset">
                                                <a class="reset" href="#" onclick="confirmReset(${graduate.id})">reset</a>
                                            </span>
                                            <span class="up">
                                                <a class="up" href="<%=request.getContextPath() %>/admin/upMember?id=${graduate.id}">up</a>
                                            </span>
                                            <span class="down">
                                                <a class="down" href="<%=request.getContextPath() %>/admin/downMember?id=${graduate.id}">down</a>
                                            </span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

                        <c:if test="${!empty undergraduateList}">
                            <tr class="member-title"><td colspan="2"><div>Undergraduate</div></td></tr>
                            <c:forEach items="${undergraduateList}" var="undergraduate">
                                <tr class="list-display">
                                    <td class="photo">
                                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${undergraduate.imageUrl}&type=0">
                                    </td>
                                    <td class="text-display">
                                        <div class="text">
                                            <div>Username: ${undergraduate.userName}</div>
                                            <div class="title">
                                                <a href="<%=request.getContextPath() %>/admin/memberDetail?id=${undergraduate.id}">Real
                                                    Name: ${undergraduate.realName}</a></div>
                                            <div>${undergraduate.description}</div>
                                            <div class="email">Email: ${undergraduate.userEmail}</div>
                                            <c:if test="${undergraduate.userPhone!=null && undergraduate.userPhone!=''}">
                                                <div class="phone">Tel: ${undergraduate.userPhone}</div>
                                            </c:if>
                                            <c:if test="${undergraduate.webUrl!=null && undergraduate.webUrl!=''}">
                                                <div class="web_page">Web page :${undergraduate.webUrl}</div>
                                            </c:if>
                                        </div>
                                        <c:if test="${username!=null&&userType==0}">
                                            <%--编辑操作--%>
                                            <span class="edit">
                                                <a class="edit" href="<%=request.getContextPath() %>/admin/editMember?id=${undergraduate.id}">edit</a>
                                            </span>
                                            <%--删除操作--%>
                                            <span class="delete">
                                            <%--<a href="<%=request.getContextPath() %>/admin/deleteMemberInfo?admin=${username}&id=${user.id}">delete</a>--%>
                                                <a class="delete" href="#" onclick="sure(${undergraduate.id})">delete</a>
                                            </span>
                                            <span class="reset">
                                                <a class="reset" href="#" onclick="confirmReset(${undergraduate.id})">reset</a>
                                            </span>
                                            <span class="up">
                                                <a class="up" href="<%=request.getContextPath() %>/admin/upMember?id=${undergraduate.id}">up</a>
                                            </span>
                                            <span class="down">
                                                <a class="down" href="<%=request.getContextPath() %>/admin/downMember?id=${undergraduate.id}">down</a>
                                            </span>

                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

                        <c:if test="${!empty alumniList}">
                            <tr class="member-title"><td colspan="2"><div>Alumni</div></td></tr>
                            <c:forEach items="${alumniList}" var="alumni">
                                <tr class="list-display">
                                    <td class="photo">
                                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${alumni.imageUrl}&type=0">
                                    </td>
                                    <td class="text-display">
                                        <div class="text">
                                            <div>Username: ${alumni.userName}</div>
                                            <div class="title">
                                                <a href="<%=request.getContextPath() %>/admin/memberDetail?id=${alumni.id}">Real
                                                    Name: ${alumni.realName}</a></div>
                                            <div>${alumni.description}</div>
                                            <div class="email">Email: ${alumni.userEmail}</div>
                                            <c:if test="${alumni.userPhone!=null && alumni.userPhone!=''}">
                                                <div class="phone">Tel: ${alumni.userPhone}</div>
                                            </c:if>
                                            <c:if test="${alumni.webUrl!=null && alumni.webUrl!=''}">
                                                <div class="web_page">Web page :${alumni.webUrl}</div>
                                            </c:if>
                                        </div>
                                        <c:if test="${username!=null&&userType==0}">
                                            <%--编辑操作--%>
                                            <span class="edit">
                                                <a class="edit" href="<%=request.getContextPath() %>/admin/editMember?id=${alumni.id}">edit</a>
                                            </span>
                                            <%--删除操作--%>
                                            <span class="delete">
                                            <%--<a href="<%=request.getContextPath() %>/admin/deleteMemberInfo?admin=${username}&id=${user.id}">delete</a>--%>
                                                <a class="delete" href="#" onclick="sure(${alumni.id})">delete</a>
                                            </span>
                                            <span class="reset">
                                                <a class="reset" href="#" onclick="confirmReset(${alumni.id})">reset</a>
                                            </span>
                                            <span class="up">
                                                <a class="up" href="<%=request.getContextPath() %>/admin/upMember?id=${alumni.id}">up</a>
                                            </span>
                                            <span class="down">
                                                <a class="down" href="<%=request.getContextPath() %>/admin/downMember?id=${alumni.id}">down</a>
                                            </span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

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
        var conf = confirm("Confirm Deletion?");
        if (conf == true) {
            window.location.href = "<%=request.getContextPath() %>/admin/deleteMemberInfo?admin=${username}&id=" + itemId;
        }
    }
    function confirmReset(itemId){
        var conf=confirm("Confirm Reset User Password?");
        if (conf==true){
            window.location.href="<%=request.getContextPath() %>/admin/resetPassword?id="+itemId;
        }
    }
</script>