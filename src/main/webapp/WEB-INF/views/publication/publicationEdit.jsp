<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2017/10/20
  Time: 17:43
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
        <%--<img src="images/telephone.jpg" alt="">--%>
        <h2>Edit Publication</h2>
        <form method="POST"
              action="<%=request.getContextPath() %>/publication/editPublicationInfo?username=${username}&panel=${panel}&id=${publication.id}"
              enctype="multipart/form-data">
            <label class="hint">
                Fields with * are required,while other are optional
            </label>
            <label for="title"> <span>Title *</span>
                <input type="text" name="title" id="title" value="${publication.title}">
            </label>
            <label for="authors"> <span>Authors*</span>
                <input type="text" name="authors" id="authors" value="${publication.authors}">
            </label>
            <label for="organization"> <span>Conference/<br/>Magazine *</span>
                <input type="text" name="organization" id="organization" value="${publication.organization}">
            </label>
            <label for="description"> <span>Abstract *</span>
                <textarea name="description" id="description" cols="10" rows="3">${publication.description}</textarea>
            </label>
            <label for="image"> <span>Image<br/>(scale 4:3)</span>

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
                    <span>Members *</span>
                    <input type="text" name="members" id="members">
                    <div id="checkbox-pre">
                    </div>
                </label>
                <span id="checkbox">
                <c:forEach items="${users}" var="user">
                    <input type="checkbox" value="${user.id}" id="${user.id}"
                           name="${user.userName}" onclick="display()"/>
                    ${user.userName}(${user.realName})<br/>
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
    function display() {
        var objform = document.getElementById("checkbox");
        var objtext = document.getElementById("members");
        var objdisplay = document.getElementById("checkbox-pre");

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
    function scanCheckbox(list){
        for(var i=0;i<list.length;i++){
            document.getElementById(list[i]).checked=true;
        }
    }
//    initialize checkbox
    scanCheckbox(${oldAuthorList});
    display()
</script>