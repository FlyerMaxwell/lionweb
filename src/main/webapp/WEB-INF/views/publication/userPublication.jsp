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
    <link rel="stylesheet" href="/statics/css/style.css" type="text/css">
</head>
<body>
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <div class="content">
        <ul>
            <a href="/publication/addPublication?username=${user.userName}" class="add">New Publication</a>
        </ul>
        <div>
            <table width="100%" border="0" id="list">
                <c:choose>
                    <c:when test="${empty publications}">
                        <div align="left">
                            <span>No Publication!</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${publications}" var="publication">
                            <tr height="100px" width="100%">
                                <td width="20%">
                                    <img src="/statics/images/button-submit.png">
                                </td>
                                <td width="20%">
                                        ${publication.title}
                                </td>

                                <%--编辑操作--%>
                                <td width="7%">
                                    <a href="/publication/editPublication?username=${user.userName}&id=${publication.id}">edit</a>
                                </td>
                                <%--管理员可进行删除操作--%>
                                <c:if test="${user.userType == 0}">
                                    <td width="7%">
                                        <a href="/publication/deletePublicationInfo?username=${user.userName}&id=${publication.id}">delete</a>
                                    </td>
                                </c:if>
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