<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="/statics/css/style.css" type="text/css">
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
            <span class="add">Add Label</span>
            <a href="<%=request.getContextPath() %>/label/addLabel" class="add">+</a>
        </ul>
        <div class="container">
            <table>
                <c:choose>
                    <c:when test="${empty labels}">
                        <div align="left">No Label!</div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${labels}" var="label">
                            <tr class="list-display">
                                <td class="text-display">
                                    <div class="text">
                                        ${label.name}
                                    </div>
                                    <c:if test="${username!=null&&userType==0}">
                                        <%--编辑操作--%>
                                        <span class="edit">
                                        <a class="edit" href="<%=request.getContextPath() %>/label/editLabel?username=${username}&id=${label.id}">edit</a>
                                    </span>
                                        <%--删除操作--%>
                                        <span class="delete">
                                        <a class="delete" href="#" onclick="sure(${label.id})">delete</a>
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
            window.location.href="<%=request.getContextPath() %>/label/deleteLabelInfo?username=${username}&id="+itemId;
        }
    }
</script>