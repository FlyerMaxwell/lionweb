<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>LION</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/statics/css/style.css" type="text/css">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/statics/images/favicon.ico" type="image/x-icon" />
</head>
<script type="text/javascript">
    //单张展示图片高度
    var height=540;
    //加载window.onload
    function addEventLoad(func){
        var oldonload = window.onload;
        if(typeof(window.onload) != 'funciton'){
            window.onload = func;
        }
        else {
            window.onload = function(){
                oldonload();
                func();
            }
        }
    }
    //初始化小图片及图片标题样式
    function classNormal(picbtn,pictxt){
        var picbtns = document.getElementById(picbtn).getElementsByTagName("li");
        var pictxts = document.getElementById(pictxt).getElementsByTagName("li");
        for(var i = 0; i < picbtns.length; i++){
            picbtns[i].className = "normal";
            pictxts[i].className = "normal";
        }
    }
    //当前显示的小图片及图片标题样式
    function classCurrent(picbtn,pictxt,n){
        var picbtns = document.getElementById(picbtn).getElementsByTagName("li");
        var pictxts = document.getElementById(pictxt).getElementsByTagName("li");
        picbtns[n].className = "current";
        pictxts[n].className = "current";
    }
    //移动图片
    function movePic(pic,final_x,final_y,interval){
        var elem = document.getElementById(pic);
        var xpos = parseInt(elem.style.left);
        var ypos = parseInt(elem.style.top);
        if(elem.movement){
            clearTimeout(elem.movement);
        }
        if (!elem.style.left) {
            elem.style.left = "0px";
        }
        if (!elem.style.top) {
            elem.style.top = "0px";
        }
        if (xpos == final_x && ypos == final_y) {
            return true;
        }

        //直接将目标位置赋值给图片当前位置。也可以采用以下代码产生动画


        //以下代码：按间隔时间、平均移动的距离，缓慢移动图片到目标位置，产生动画效果
        var dist;
        if(xpos < final_x){
            dist = Math.ceil((final_x - xpos)/10);
            xpos += dist;
        }
        if(xpos > final_x){
            dist = Math.ceil((xpos - final_x)/10);
            xpos -= dist;
        }
        if(ypos < final_y){
            dist = Math.ceil((final_y - ypos)/10);
            ypos += dist;
        }
        if(ypos > final_y){
            dist = Math.ceil((ypos - final_y)/10);
            ypos -= dist;
        }
        elem.style.left = xpos + "px";
        elem.style.top = ypos + "px";
        var repeat = "movePic('" + pic + "'," + final_x + "," + final_y + "," + interval + ")";
        elem.movement = setTimeout(repeat,interval);
    }
    //当鼠标移动到小图片上时切换图片
    function changePic(){
//      TODO 用自定义getElementByIdx_x替代getElementById
        if(!document.getElementById('picfocus')) return false;
        document.getElementById('picfocus').onmouseover = function(){autokey = true};
        document.getElementById('picfocus').onmouseout = function(){autokey = false};
        var picbtns = document.getElementById("picbtn").getElementsByTagName("li");
        var picnums = picbtns.length;
        picbtns[0].onmouseover = function(){
            movePic('pic',0,0,5);
            classNormal('picbtn','pictxt');
            classCurrent('picbtn','pictxt',0);
        }
        if(picnums >= 2){
            picbtns[1].onmouseover = function(){
                movePic('pic',0,-height,5);
                classNormal('picbtn','pictxt');
                classCurrent('picbtn','pictxt',1);
            }
        }
        if(picnums >= 3){
            picbtns[2].onmouseover = function(){
                movePic('pic',0,-height*2,5);
                classNormal('picbtn','pictxt');
                classCurrent('picbtn','pictxt',2);
            }
        }
        if(picnums >= 4){
            picbtns[3].onmouseover = function(){
                movePic('pic',0,-height*3,5);
                classNormal('picbtn','pictxt');
                classCurrent('picbtn','pictxt',3);
            }
        }
    }
    //自动切换图片
    var autokey = false;
    setInterval('autoChange()',5000);
    function autoChange(){
        if(autokey) return false;
        var picbtns = document.getElementById("picbtn").getElementsByTagName("li");
        var len = picbtns.length;
        for(var i = 0; i < len; i++){
            if(picbtns[i].className == "current"){
                var currentNum = i;
            }
        }
        if(currentNum == 0 && len >= 1){
            movePic('pic',0,-height,5);
            classNormal('picbtn','pictxt');
            classCurrent('picbtn','pictxt',1);
        }
        if(currentNum == 1 && len >= 2){
            movePic('pic',0,-height*2,5);
            classNormal('picbtn','pictxt');
            classCurrent('picbtn','pictxt',2);
        }
        if(currentNum == 2 && len >= 3){
            movePic('pic',0,-height*3,5);
            classNormal('picbtn','pictxt');
            classCurrent('picbtn','pictxt',3);
        }
        if(currentNum == 3 && len >= 4){
            movePic('pic',0,0,5);
            classNormal('picbtn','pictxt');
            classCurrent('picbtn','pictxt',0);
        }
    }
    addEventLoad(changePic);
</script>

<%--<%@ include file="/statics/html/header.html" %>--%>
<body>
<jsp:include page="header.jsp" flush="true"></jsp:include>
<div id="body">
    <h3 class="large-category">HOT NEWS !</h3>
    <div class="content-display">
        <div class="container">
            <%--最初显示为news,publications,projects最近五项--%>
            <%--<div class="row">--%>
                <%--<a class="more" href="<%=request.getContextPath() %>/news/">more</a>--%>
                <%--<h2>Recent News</h2>--%>
                <%--<ul>--%>
                    <%--<c:forEach items="${newsList}" var="news">--%>
                        <%--<li>--%>
                            <%--<a href="<%=request.getContextPath() %>/news/newsDetail?id=${news.id}">${news.title}</a>--%>
                        <%--</li>--%>
                    <%--</c:forEach>--%>
                <%--</ul>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
                <%--<a class="more" href="<%=request.getContextPath() %>/publication/">more</a>--%>
                <%--<h2>Recent Publication</h2>--%>
                <%--<ul>--%>
                    <%--<c:forEach items="${publicationList}" var="publication">--%>
                        <%--<li>--%>
                            <%--<a href="<%=request.getContextPath() %>/publication/publicationDetail?id=${publication.id}">${publication.title}</a>--%>
                        <%--</li>--%>
                    <%--</c:forEach>--%>
                <%--</ul>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
                <%--<a class="more" href="<%=request.getContextPath() %>/project/">more</a>--%>
                <%--<h2>Recent Project</h2>--%>
                <%--<ul>--%>
                    <%--<c:forEach items="${projectList}" var="project">--%>
                        <%--<li>--%>
                            <%--<a href="<%=request.getContextPath() %>/project/projectDetail?id=${project.id}"> ${project.title}</a>--%>
                        <%--</li>--%>
                    <%--</c:forEach>--%>
                <%--</ul>--%>
            <%--</div>--%>
                <div id="picfocus">
                    <div id="piclist">
                        <div id="pic" style="left:0px; top:0px;">
                            <ul>
                                <c:forEach items="${newsList}" var="news">
                                    <li>
                                        <a href="<%=request.getContextPath() %>/news/newsDetail?id=${news.id}" target="_blank">
                                            <img border="0" alt="" src="<%=request.getContextPath() %>/resource/showImage?imagePath=${news.imageUrl}&type=1"/>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div id="pictxtbg"></div>
                        <div id="pictxt">
                            <ul>
                                <li class="current">${newsList[0].title}</li>
                                <c:forEach items="${newsList}" begin="1" var="news">
                                    <li class="normal">${news.title}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div id="picbtn">
                        <ul>
                            <li class="current">
                                <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${newsList[0].imageUrl}&type=1"/>
                            </li>
                            <c:forEach items="${newsList}" begin="1" var="news">
                                <li class="normal">
                                    <img src="<%=request.getContextPath() %>/resource/showImage?imagePath=${news.imageUrl}&type=1"/>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>
