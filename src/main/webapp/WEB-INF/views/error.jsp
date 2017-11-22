<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2017/10/18
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/statics/images/favicon.ico" type="image/x-icon" />
</head>
<body>
<jsp:include page="header.jsp" flush="true"></jsp:include>

<div id="section">
    <div>
        <div>
            <h1>${Msg}</h1>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>
