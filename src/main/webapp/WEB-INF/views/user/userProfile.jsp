<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2017/10/18
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
</head>
<body>
<jsp:include page="../header.jsp" flush="true"></jsp:include>

<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>

</html>
