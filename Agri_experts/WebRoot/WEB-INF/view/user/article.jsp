<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width" />
    <meta name="author" content="www.yanshisan.cn" />
    <meta name="robots" content="all" />
    <title>农业资讯</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/layui/css/layui.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/master.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/gloable.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/nprogress.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/blog.css" />
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
                        <li><a href="${ctx}/userView/Userindex.action">首页</a></li>
                        <li><a href="${ctx}/userView/UserArticle.action">资讯</a></li>
                        <li><a href="${ctx}/userView/UserOnlineMessage.action">在线咨询</a></li>
                        <li><a href="${ctx}/userView/userDiagnose.action">远程问诊</a></li>
                        <li><a href="${ctx}/userView/UserPersonCen.action">个人中心</a></li>
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
        <div class="container-fixed">
		    <div class="col-content">
		        <div class="inner">
		            <article class="article-list bloglist" id="LAY_bloglist" >
		           	<c:forEach var="information" items="${informations}">
						<section class="article-item zoomIn article">       
							<div class="fc-flag">置顶</div>   
							<h5 class="title">       
								<span class="fc-blue">【原创】</span>
								<a href="${ctx}/userView/UserArticleDetails.action?title=${information.title}">${information.title}</a>   
							</h5>   
							<div class="time">    
								<span class="day" >${fn:substring(information.date, 8, 10)}</span>
								<span class="month fs-18" >${fn:substring(information.date, 5, 7)}<span class="fs-14">月</span></span>       
								<span class="year fs-18 ml10" >${fn:substring(information.date, 0, 4)}</span>   
							</div>   
							<div class="content">       
								<a class="cover img-light">           
									<img alt="资讯图片" src="${ctx}/file/downloadShowFile.action?path=${information.wentiphoto}"/>       
								</a>
								${information.content}  
							</div>   
							<div class="read-more">       
								<a href="${ctx}/userView/UserArticleDetails.action?title=${information.title}" class="fc-black f-fwb">查看详情</a>   
							</div>    
							<aside class="f-oh footer">       
								<div class="f-fl tags">           
									<span class="fa fa-tags fs-16"></span>           
									<a class="tag">${information.area}</a>       
								</div>  
								
								<div class="f-fl tags">           
									<span class="fa fa-tags fs-16"></span>           
									<a class="tag">${information.province}</a>       
								</div>  
								<div class="f-fl tags">           
									<span ></span>           
									<a class="tag">${information.city}</a>       
								</div>  
								<div class="f-fl tags">           
									<span ></span>           
									<a class="tag">${information.district}</a>       
								</div>  
							</aside>
						</section>
						</c:forEach>
		            </article>
		        </div>
		    </div>
		    <div class="col-other">
		        <div class="inner">
					<div class="other-item" id="categoryandsearch">
						
			    		<div class="search">
					        <label class="search-wrap">
					            <input type="text" id="title" name="title" placeholder="输入文章标题关键字搜索" />
					            <span class="search-icon">
					                <i class="fa fa-search" id="doSearch"></i>
					            </span>
					        </label>
					        <ul class="search-result" id="InfoResult">
					        	<li><a  id="result" style="color:red;"></a></li>
					        </ul>
			    		</div>
			    		<ul class="category mt20" id="category">
				        	<li data-index="0" class="slider"></li>
				        	<li data-index="1"><a style="color:green;margin-top:30px;">摘选文章</a></li>
				        	<c:forEach var="informationslimit" items="${informationslimits}" varStatus="i">
				            <li data-index="${i.count+1}"><a href="${ctx}/userView/UserArticleDetails.action?title=${informationslimit.title}">${informationslimit.title}</a></li>
				           	</c:forEach> 
			    		</ul>
					</div>
					<div class="blog-mask animated layui-hide"></div>
					<div class="other-item">
					    <h5 class="other-item-title">热门文章</h5>
					    <div class="inner">
					        <ul class="hot-list-article">
					        	<c:forEach var="information" items="${informations}">
					                <li> <a href="${ctx}/userView/UserArticleDetails.action?title=${information.title}">${information.title}</a></li>
					             </c:forEach>
					        </ul>
					    </div>
					</div>
		        </div>
		    </div>
		</div>
    </div>
    <footer class="grid-footer">
        <div class="footer-fixed">
            <div class="copyright">
                <div class="info">
                    <div class="contact">
                        <a href="https://wpa.qq.com/msgrd?v=3&uin=164152834&site=qq&menu=yes" class="qq" target="_blank" title="164152834"><i class="fa fa-qq"></i></a>
                    </div>
                    <p class="mt05">
                        Copyright &copy; 农业专家咨询系统版权所有
                    </p>
                </div>
            </div>
        </div>
    </footer>
     <script src="${pageContext.request.contextPath}/resources/user/layui/layui.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/user/js/yss/article.js"></script>
    <script>
    //查询操作实现
    layui.use(['jquery'], function () {
    var $ = layui.jquery;
    $("#doSearch").click(function(){
    	var title=$("#title").val();
  	 	$.get({
    		url:"${ctx}/userRestController/SearchTitle.action",
    		data:{"title":$("#title").val()},
    		success:function(data){
    			if(data == ""){
    				alert("未查询到相关文章");
    			}else{
    				for(var i=0; i<data.length;i++){
        				var title = data[i].title;
        					$("#InfoResult").css("display","block");
            				$("#result").html(title); 
            				$("#result").attr("href","${ctx}/userView/UserArticleDetails.action?title="+title); 
        				}
    			}
    			}
    	});
    });  
});
    </script>
    <script src="${pageContext.request.contextPath}/resources/user/js/yss/gloable.js"></script>
    <script src="${pageContext.request.contextPath}/resources/user/js/plugins/nprogress.js"></script>
    <script>NProgress.start();</script>
    <script> 
        window.onload = function () {
            NProgress.done();
        };
    </script>
</body>
</html>
