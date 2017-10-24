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
            <a href="/publication/addPublication" class="add">New Publication</a>
        </ul>
        <div>
            <table width="1000px" border="0" id="list">
                <c:choose>
                    <c:when test="${empty publications}">
                        <div align="left">
                            <span>No Publication!</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${publications}" var="publication">
                            <tr height="100px">
                                <td width="20%">
                                    <img src="/statics/images/button-submit.png">
                                </td>
                                <td width="80">
                                    ${publication.title}
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