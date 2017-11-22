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
        <h2>Add New Member</h2>
        <form method="POST" action="<%=request.getContextPath() %>/admin/addMemberInfo?adminName=${username}" enctype="multipart/form-data">
            <label class="hint">
                fields with * are required,while other are optional
            </label>
            <label for="image"> <span>Image</span>
                <a href="javascript:;" class="file">
                    <input type="file" name="image" id="image" >
                </a>
            </label>
            <label for="username"> <span>Username *</span>
                <input type="text" name="username" id="username">
            </label>
            <label for="email"> <span>Email *</span>
                <input type="text" name="email" id="email">
            </label>
            <label for="phone"> <span>Telephone *</span>
                <input type="text" name="phone" id="phone">
            </label>
            <label for="description"> <span>Description *</span>
                <textarea name="description" id="description" cols="10" rows="3"></textarea>
            </label>
            <label for="detail"> <span>Detail</span>
                <textarea name="detail" id="detail" cols="30" rows="10"></textarea>
            </label>
            <label><span>Gender *</span>
                <input id="gender0" type="radio" name="gender" value="1" checked="checked"/>
                <label for="gender0">Female</label>
                <input id="gender1" type="radio" name="gender" value="2"/>
                <label for="gender1">Male</label>
            </label>
            <label><span>User Type *</span>
                <input id="type0" type="radio" name="type" value="0"/>
                <label for="type0">Admin</label>
                <input id="type1" type="radio" name="type" value="1" checked="checked"/>
                <label for="type1">Normal</label>
            </label>
            <label><span>User State *</span>
                <input id="state0" type="radio" name="state" value="0" checked="checked"/>
                <label for="state0">unlocked</label>
                <input id="state1" type="radio" name="state" value="1"/>
                <label for="state1">locked</label>
            </label>
            <input type="submit" value="" id="submit">
        </form>
    </div>

</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>