<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/statics/images/favicon.ico" type="image/x-icon" />
</head>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/statics/editor/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/statics/editor/kindeditor/lang/zh_CN.js"></script>
<script>
    var editor;
    KindEditor.ready(function(K) {
        editor=window.editor = K.create("textarea[id='text']", {
            resizeType: 1,
            allowPreviewEmoticons:false,
            allowImageUpload:true,
            allowFileManager:true,
            //上传图片和管理文件的java代码，放在jsp文件中？KindEditor的示例代码
            uploadJson:'<%=request.getContextPath() %>/statics/editor/kindeditor/jsp/upload_json.jsp',
            fileManagerJson:'<%=request.getContextPath() %>/statics/editor/kindeditor/jsp/file_manager_json.jsp',
            //图片上传或失去焦点后，将上传内容同步到textarea中
            afterUpload:function () {
                this.sync();
            },
            afterBlur:function () {
                this.sync();
            },
            items:[
                'fontname','fontsize','|','forecolor','hilitecolor','bold','italic','underline',
                'removeformat','|','justifyleft','justifycenter','justifyright','insertorderedlist',
                'insertunorderedlist','|','emoticons','link','media','|','image'
            ]
        });
    });
    var options = {
        cssPath : '<%=request.getContextPath() %>/statics/css/style.css',
        filterMode : true
    };
</script>
<body>
<c:if test="${sessionScope.userType==null}">
    <jsp:forward page="../access.jsp"></jsp:forward>
</c:if>
<div id="body">
    <jsp:include page="../sideMenu.jsp" flush="true"></jsp:include>
    <div class="content">
        <h2>Add New News</h2>
        <form method="POST" action="<%=request.getContextPath() %>/news/addNewsInfo?username=${username}" enctype="multipart/form-data">
            <label class="hint">
                fields with * are required,while other are optional
            </label>
            <label for="title"> <span>Title *</span>
                <input type="text" name="title" id="title">
            </label>
            <label for="description"> <span>Description</span>
                <input type="text" name="description" id="description">
            </label>
            <label for="image"> <span>Image</span>

                <a href="javascript:;" class="file">
                    <input type="file" name="image" id="image" >
                </a>
            </label>
            <%--<label for="text"> <span>Text*</span>--%>

                <%--<a href="javascript:;" class="file">--%>
                    <%--<input type="file" name="text" id="text" >--%>
                <%--</a>--%>
            <%--</label>--%>
            <label for="text"> <span>Text</span>
                <textarea id="text" name="text" class="ckeditor" style="width:95%; height:580px;visibility:hidden;"></textarea>
                <br/>
            </label>
            <input type="submit" value="" id="submit">
        </form>
    </div>

</div>
<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>