<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Laboratory Website Template</title>
    <link rel="stylesheet" href="/statics/css/style.css" type="text/css">
</head>
<body>
<div id="header">
    <a href="index.jsp" class="logo"><img src="/statics/images/logo.jpg" alt=""></a>
    <ul>
        <li class="selected">
            <a href="<%=request.getContextPath() %>/index">home</a>
        </li>

        <li>
            <a href="about.html">News</a>
        </li>
        <li>
            <a href="services.html">Research</a>
        </li>
        <li>
            <a href="location.html">Projects</a>
        </li>
        <li>
            <a href="contact.html">Publications</a>
        </li>
        <li>
            <a href="blog.html">Members</a>
        </li>
        <li>
            <a href="blog.html">Activities</a>
        </li>

        <li id="login">
            <a href="<%=request.getContextPath() %>/user/login.htm">login</a></li>


    </ul>
</div>
<div id="section">
    <div>
        <div>
            <h1>accurate.reliable.fast</h1>
            <p>
                When it comes to finding answers to questions and getting results. Illumelabs is number one. No other diagnostic laboratory is faster, more reliable and accurate. Choose Illumelabs.
            </p>
        </div>
    </div>
</div>
<div id="featured">
    <div>
        <div class="article">
            <h2>Welcome to Illumelabs Diagnostic Center</h2>
            <img src="statics/images/diagnostic-center.jpg" alt="">
            <p>
                This website template hahs been collect from <a href="http://www.mycodes.net/" title="源码之家">源码之家</a> for you, for free. You can replace all this text with your own text.
            </p>
            <p>
                You can remove any link to our website from this website template, you're free to use this website template without linking back to us.
            </p>
            <p>
                f you're having problems editing this website template, then don't hesitate to ask for help on the <a href="http://www.mycodes.net/" title="网站模板">网站模板</a>.
            </p>
        </div>
        <ul>
            <li>
                <h3>hematology</h3>
                <div>
                    <p>
                        Blood extraction using seismic energy for painless testing.
                    </p>
                    <a href="blog.html" class="more">read more</a>
                </div>
                <img src="statics/images/hematology.jpg" alt="">
            </li>
            <li>
                <h3>urine &amp; drug testing</h3>
                <div>
                    <p>
                        Accurate and secure testing of urine for diseases and drugs and medicines.
                    </p>
                    <a href="blog.html" class="more">read more</a>
                </div>
                <img src="statics/images/urine-and-drug-testing.jpg" alt="">
            </li>
            <li>
                <h3>x-ray</h3>
                <div>
                    <p>
                        Fast and clear x-ray results. You’ll be assisted by our friendly staff all the way.
                    </p>
                    <a href="blog.html" class="more">read more</a>
                </div>
                <img src="statics/images/x-ray.jpg" alt="">
            </li>
            <li>
                <h3>pathology and dna</h3>
                <div>
                    <p>
                        State of the art testing for DNA that’s sure to be have fast and accurate results.
                    </p>
                    <a href="blog.html" class="more">read more</a>
                </div>
                <img src="statics/images/pathology-and-dna.jpg" alt="">
            </li>
        </ul>
    </div>
</div>
<div id="footer">
    <div>
        <p>
            <span>2023 &copy; Illumelabs Diagnostic Center.</span><a href="#" >Terms of Service</a> | <a href="#" >Privacy Policy</a>
        </p>
        <ul>
            <li id="facebook">
                <a href="#">facebook</a>
            </li>
            <li id="twitter">
                <a href="#">twitter</a>
            </li>
            <li id="googleplus">
                <a href="#">googleplus</a>
            </li>
            <li id="rss">
                <a href="#" >rss</a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>