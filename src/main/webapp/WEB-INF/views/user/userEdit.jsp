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
<c:if test="${sessionScope.userType==null}">
    <jsp:forward page="../access.jsp"></jsp:forward>
</c:if>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <div class="content">
        <h2>Edit Member</h2>
        <form method="POST" action="<%=request.getContextPath() %>/user/editUserInfo?username=${username}"
              enctype="multipart/form-data">
            <label class="hint">
                Fields with * are required,while other are optional
            </label>
            <label for="realName"> <span>Real Name *</span>
                <input type="text" name="realName" id="realName" value="${user.realName}">
            </label>
            <label for="email"> <span>Email *</span>
                <input type="text" name="email" id="email" value="${user.userEmail}">
            </label>
            <label for="phone"> <span>Telephone</span>
                <input type="text" name="phone" id="phone" value="${user.userPhone}">
            </label>
            <label for="web"> <span>Web Page</span>
                <input type="text" name="web" id="web" value="${user.webUrl}">
            </label>
            <label for="description"> <span>Title*<br/>(Your school and role,<br/>eg:SEIEE,PhD,<br/><200 bytes)</span>
                <textarea name="description" id="description" cols="10" rows="3">${user.description}</textarea>
            </label>
            <label for="detail"> <span>Biography*<br/>(Your own description,such as academic experience and research experience)</span>
                <textarea name="detail" id="detail" cols="30" rows="10">${user.detail}</textarea>
            </label>
            <label for="prospect"><span>CV<br/>(Your recent plan,<br/><500 bytes)</span>
                <input type="text" name="prospect" id="prospect" value="${user.prospect}">
            </label>
            <label for="cv"> <span>CV File</span>
                <a href="javascript:;" class="file">
                    <input type="file" name="cv" id="cv" >
                </a>
            </label>
            <label><span>Gender *</span>
                <input id="gender0" type="radio" name="gender" value="1"/>
                <label for="gender0">Female</label>
                <input id="gender1" type="radio" name="gender" value="2"/>
                <label for="gender1">Male</label>
            </label>
            <label><span>User Role *</span>
                <input id="role0" type="radio" name="role" value="0"/>
                <label for="role0">Professor</label>
                <input id="role1" type="radio" name="role" value="1"/>
                <label for="role1">graduate</label>
                <input id="role2" type="radio" name="role" value="2"/>
                <label for="role2">undergraduate</label>
                <input id="role3" type="radio" name="role" value="2"/>
                <label for="role3">alumni</label>
            </label>
            <input type="submit" value="" id="submit">
        </form>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>

</body>
</html>

<script>
    function checkRadio(radioName,radioValue) {
        var rObj=document.getElementsByName(radioName);
        for(var i=0;i<rObj.length;i++){
            if(rObj[i].value==radioValue){
                rObj[i].checked="checked";
            }
        }
    }
    checkRadio("gender",${user.userSex});
    checkRadio("role",${user.userRole});
</script>
