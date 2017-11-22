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
        <h2>Add New Project</h2>
        <form method="POST" action="<%=request.getContextPath() %>/project/addProjectInfo?username=${username}"
              enctype="multipart/form-data">
            <label class="hint">
                fields with * are required,while other are optional
            </label>
            <label for="title"> <span>Title *</span>
                <input type="text" name="title" id="title">
            </label>
            <label for="authors"> <span>Authors*</span>
                <input type="text" name="authors" id="authors">
            </label>
            <label for="organization"> <span>Organization*</span>
                <input type="text" name="organization" id="organization">
            </label>
            <label for="description"> <span>Description*</span>
                <textarea name="description" id="description" cols="10" rows="3"></textarea>
            </label>

            <label for="image"> <span>Image</span>

                <a href="javascript:;" class="file">
                    <input type="file" name="image" id="image">
                </a>
            </label>
            <label for="text"> <span>Text</span>

                <a href="javascript:;" class="file">
                    <input type="file" name="text" id="text">
                </a>
            </label>
            <label for="slide"> <span>slide</span>

                <a href="javascript:;" class="file">
                    <input type="file" name="slide" id="slide">
                </a>
            </label>
            <label for="video"> <span>video</span>

                <a href="javascript:;" class="file">
                    <input type="file" name="video" id="video">
                </a>
            </label>

            <div class="multiple-choice">
                <label for="members">
                    <span>Members*</span>
                    <input type="text" name="members" id="members">
                    <div id="checkbox-pre"></div>
                </label>
                <span id="checkbox">
                <c:forEach items="${users}" var="user">
                    <input type="checkbox" value="${user.id}"
                           name="${user.userName}" onclick="display('checkbox','members','checkbox-pre')"/>
                    ${user.userName}<br/>
                </c:forEach>
                </span>
            </div>

            <div class="multiple-choice">
                <label for="refs">
                    <span>Publications*</span>
                    <input type="text" name="refs" id="refs">
                    <div id="checkbox-pre1"></div>
                </label>
                <span id="checkbox1">
                <c:forEach items="${publications}" var="publication">
                    <input type="checkbox" value="${publication.id}"
                           name="${publication.title}" onclick="display('checkbox1','refs','checkbox-pre1')"/>
                    ${publication.title}<br/>
                </c:forEach>
                </span>
            </div>

            <input type="submit" value="" id="submit">
        </form>
    </div>

</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>

<script>
    function display(checkbox,list,pre) {
        var objform = document.getElementById(checkbox);
        var objtext = document.getElementById(list);
        var objdisplay = document.getElementById(pre);

        var memberlist = objform.getElementsByTagName("input");
        var idstr = "";
        //remove all childs
        while (objdisplay.hasChildNodes()) {
            objdisplay.removeChild(objdisplay.firstChild);
        }

        //add new childs
        for (var i = 0; i < memberlist.length; i++) {
            if (memberlist[i].checked == true) {
                var objp = document.createElement("p");
                objp.innerText = memberlist[i].name;
                idstr = idstr + String(memberlist[i].value) + ",";
                objdisplay.appendChild(objp);
            }
        }
        idstr = idstr.substring(0, idstr.length - 1);
        objtext.value = idstr;
    }
</script>