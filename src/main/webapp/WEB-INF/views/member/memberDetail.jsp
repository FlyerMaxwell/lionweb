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
<jsp:include page="../header.jsp" flush="true"></jsp:include>
<div id="body">
    <div class="content">
        <div class="container">
            <table class="member">
                <tr height="100px">
                    <td class="portrait">
                        <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${user.imageUrl}&type=0">
                    </td>
                    <td>
                        <div class="text">
                            <div>${user.realName}</div>
                            <div>${user.description}</div>
                            <div class="email">Email: ${user.userEmail}</div>
                            <c:if test="${user.userPhone!=null && user.userPhone!=''}">
                                <div class="phone">Tel: ${user.userPhone}</div>
                            </c:if>
                            <c:if test="${user.webUrl!=null && user.webUrl!=''}">
                                <div class="web_page">Web page :${user.webUrl}</div>
                            </c:if>

                        </div>
                    </td>
                </tr>
                <tr><td colspan="2"><br/></td></tr>
                <c:if test="${user.detail!=null && user.detail!=''}">
                <tr>
                    <td colspan="2">
                        <h3>
                            Biography
                        </h3>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        ${user.detail}
                    </td>
                </tr>
                </c:if>
                <tr><td colspan="2"><br/></td></tr>
                <br/>
                <c:if test="${user.cvUrl!=null && user.cvUrl!=''}">
                    <tr>
                        <td colspan="2">
                            <h3>CV</h3>
                        </td>
                    </tr>
                    <c:if test="${user.prospect!=null && user.prospect!=''}">
                        <tr>
                            <td colspan="2">
                                ${user.prospect}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="2">
                            Here is my <a href="<%=request.getContextPath() %>/resource/downloadFile?filePath=${user.cvUrl}">CV</a>!
                        </td>
                    </tr>
                </c:if>

            </table>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>