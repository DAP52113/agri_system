<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width" />
    <meta name="author" content="www.yanshisan.cn" />
    <meta name="robots" content="all" />
    <title>专家个人中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/layui/css/layui.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/master.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/gloable.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/nprogress.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/about.css" />
</head>
<body>
    <div class="header">
    </div>
    <header class="gird-header">
        <div class="header-fixed">
            <div class="header-inner">
                <a href="javascript:void(0)" class="header-logo" id="logo">Agricultural Expert Consultation System</a>
                <nav class="nav" id="nav">
                    <ul>
		            <li><a href="${ctx}/expertView/Expertindex.action">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
		            <li><a href="${ctx}/expertView/ExpertPersonCen.action">个人中心</a></li>
		            <li><a href="${ctx}/expertView/ExpertArticleSubmit.action">信息发布</a></li>
		            <li><a href="${ctx}/expertView/ExpertOnlineReply.action">在线回复</a></li>
		             <li><a href="${ctx}/expertView/ExpertDiagnose.action">远程回复</a></li>
                    </ul>
                </nav>
                <a href="${pageContext.request.contextPath}/index.jsp" class="blog-user">
                     <i class="fa fa-share">注销</i>
                </a>
                <a class="phone-menu">
                    <i></i>
                    <i></i>
                    <i></i>
                </a>
            </div>
        </div>
    </header>
    <div class="doc-container" id="doc-container">
        <div class="about-banner" id="container">
		    <header class="l-top hasAnim arrow-holder">
		    </header>
		    <div class="about-title">
		        <h1>个人中心</h1>
		        <p>欢迎来到用户个人中心！！！</p>
		    </div>
		</div>
		<div class="container-fixed">
		    <div class="container-inner">
		        <article>
		            <section>
		            
						<h1>个人中心设置</h1>
		
				<jsp:include page="expertPenCenInclude.jsp" ></jsp:include>
		            </section>
		        </article>
		    </div>
		</div>
    </div>
    <footer class="grid-footer">
        <div class="footer-fixed">
            <div class="copyright">
                <div class="info">
                    <div class="contact">
                        <a href="https://wpa.qq.com/msgrd?v=3&uin=164322857&site=qq&menu=yes" class="qq" target="_blank" title="164322857"><i class="fa fa-qq"></i></a>
                    </div>
                    <p class="mt05">
                        Copyright &copy; 农业专家咨询系统版权所有
                    </p>
                </div>
            </div>
        </div>
    </footer>
 
    <script src="${pageContext.request.contextPath}/resources/expert/js/yss/gloable.js"></script>
    <script src="${pageContext.request.contextPath}/resources/expert/js/plugins/nprogress.js"></script>
    <script>NProgress.start();</script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
 	<script src="${pageContext.request.contextPath}/resources/expert/js/plugins/blogbenoitboucart.min.js"></script>
    <script> 
        window.onload = function () {
            NProgress.done();
        };
    </script>
</body>
</html>
