<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-Hans-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width" />
    <title>农业专家咨询系统-用户</title>
    <link href="${pageContext.request.contextPath}/resources/user/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/user/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/user/css/index_style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/user/css/animate.min.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="menu" class="hover_animation menu_open" data-mark="false">
        <span></span>
        <span></span>
        <span></span>
    </div>
    <div id="navgation" class="navgation navgation_close">
        <ul class="point">
            <li><a href="${ctx}/userView/Userindex.action">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
            <li><a href="${ctx}/userView/UserPersonCen.action">个人中心</a></li>
            <li><a href="${ctx}/userView/UserArticle.action">农业咨讯</a></li>
            <li><a href="${ctx}/userView/UserOnlineMessage.action">在线提问</a></li>
            <!-- 跳转到另一个子系统项目 -->
             <li><a href="${ctx}/userView/UserConsultOnline.action">在线咨询</a></li>
             <li><a href="${ctx}/userView/userDiagnose.action">远程问诊</a></li>
        </ul>
        <div class="logo"><a>农业专家咨询系统</a></div>
    </div>
    <div class="section" id="section1">
        <div class="fp-tablecell">
            <div class="page1">
                <div class="nav wow zoomIn" data-wow-duration="2s">
                    <h1>农业专家咨询系统</h1>
                    <p>欢迎来到农业专家咨询系统--用户端</p>
                    <a class="layui-btn layui-btn-normal" style="margin-top: 20px" href="${ctx}/userView/UserArticle.action">欢迎${user.unname}进入</a>
                </div>
                <a class="next wow fadeInUp" data-wow-duration="2s" id="next"></a>
            </div>
        </div>
    </div>
    <div class="section" id="section2">
        <div class="fp-tablecell">
            <div class="page2">
                <div class="warp-box">
                    <div class="new-article">
                        <div class="inner wow fadeInDown" data-wow-delay=".2s">
                            <h1>热门资讯</h1>
                            <!--这里使用数据库查询农业咨询-->
                            <p>
                                在这里可以查看部分农业咨询信息
                                <br>专家推荐
                            </p>
                        </div>
                    </div>
                    
                    <div class="layui-row">
                   		 <c:forEach var="information" items="${informations}">
                        <div class="layui-col-xs12 layui-col-sm4 layui-col-md4  wow fadeInUp" style="padding: 0 10px">
                            <div class="single-news">
                                <div class="news-head" id="carimgDiv">
                                <!-- 显示图片 -->
                                <img alt="资讯图片" src="${ctx}/file/downloadShowFile.action?path=${information.wentiphoto}"/>
                                </div>
                                <div class="news-content">
                                    <h4>
                                        <a >
                                            标题:${information.title}
                                        </a>
                                    </h4>
                                    <!--双击查看信息设置-->
                                    <div class="date">
                                        ${information.date}
                                    </div>
                                    <!--  
                                    <p>
                                        ${information.content}
                                    </p>
                                    -->
                                    <a  class="layui-btn"  onclick="viewNews(${information.id})">
                                       阅读更多
                                    </a>
                                </div>
                               </div>
                              </div>
                                 </c:forEach>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="section" id="section3">
        <div class="fp-tablecell">
            <div class="page3">
                <div class="warp-box">
                    <div class="warp">
                        <div class="inner">
                            <div class="links">
                                <ul>
                                    <li class="wow fadeInLeft"><a href="${ctx}/userView/UserConsultOnline.action">在线咨询</a></li>
                                    <li class="wow fadeInRight"><a href="${ctx}/userView/UserOnlineMessage.action">在线提问</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="section" id="section4">
        <div class="fp-tablecell">
            <div class="page2">
                <div class="warp-box">
                     <c:forEach var="expert" items="${experts}">
                     <div class="layui-col-xs12 layui-col-sm4 layui-col-md4  wow fadeInUp" data-wow-delay=".2s" style="padding: 0 10px" >
                            <div class="single-news">
                                <div class="news-head">
                                <!-- 显示图片 -->
                                    <img width="400px" height="300px" alt="专家图片" src="${ctx}/file/downloadShowFile.action?path=${expert.photo}"  />
                                </div>
                                <div class="news-content">
                                    <h4>
                                    <!-- 显示专家名称 -->
                                        <a >
                                            专家名称 ：${expert.ename }	
                                        </a>
                                    </h4>
                                    <div class="date" >
                                  <!-- 显示专家领域 -->
                                  	领域 ：${expert.area }	
                                    </div>
                                    <p >
                                        省份：${expert.province } &nbsp; &nbsp;城市：${expert.city }   &nbsp;&nbsp;地区：${expert.district }
                                    </p>
                                    <a  class="btn">
                                      职称:${expert. eprof}
                                    </a>
                                </div>
                            </div>
                        </div>
                             </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <footer class="footer wow fadeInUp" id="contact">
        <div class="footer-top">
            <div class="container">
                <div class="layui-row">
                    <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
                        <div class="single-widget about">
                            <div class="footer-logo">
                                <h2>农业专家咨询系统</h2>
                            </div>
                            <p>欢迎了解农业专家咨询系统</p>
                            <div class="button">
                                <a onclick="aboutAsystem()" class="btn layui-btn layui-btn-normal">关于我们</a>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
                        <div class="single-widget">
                            <h2>相关链接</h2>
                            <ul class="social-icon">
                                <li class="active"><a href="${ctx}/userView/UserOnlineMessage.action"><i class="fa fa-book"></i>在线咨询</a></li>
                                <li class="active"><a href="${ctx}/userView/UserPersonCen.action"><i class="fa fa-comments"></i>个人中心</a></li>
                                <li class="active"><a href="${ctx}/userView/UserArticle.action"><i class="fa fa-share"></i>信息浏览</a></li>
                                <li class="active"><a href="${ctx}/userView/userDiagnose.action"><i class="fa fa-snowflake-o"></i>远程问诊</a></li>
                            </ul>                   
                        </div>
                    </div>
                    <div class="layui-col-xs12 layui-col-sm12 layui-col-md4">
                        <div class="single-widget contact">
                            <h2>联系方式</h2>
                            <ul class="list">
                                <li><i class="fa fa-map"></i>地址: 山东省青岛市城阳区长城路700号</li>
                                <li><i class="fa fa-qq"></i>青岛农业大学 </li>
                                <li><i class="fa fa-envelope"></i>邮箱: 1684221057@qq.com</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="copyright">
            <div class="container">
                <div class="layui-row">
                    <div class="layui-col-xs12 layui-col-sm12 layui-col-md12 text-center">
                        <p>Copyright &copy; 农业专家咨询系统版权所有</p>
                    </div>
                </div>
            </div>
        </div>  
    </footer>
    <!-- 查看公告的div -->
	<div id="desk_viewNewsDiv" style="padding: 10px;display: none;">
		<h2 id="view_title" align="center"></h2>
		<hr>
		<div style="text-align: right;">
		<!-- inline-block表示不可以独占一行数据 -->
			发布人:<span id="view_opername"></span>  <span style="display: inline-block;width: 20px" ></span>
			发布时间:<span id="view_createtime"></span>
		</div>
		<hr>
		<div id="view_content"></div>
	</div>
	<script src="${ctx}/resources/chat/js/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/user/layui/layui.js"></script>
	<script type="text/javascript">
  			function viewNews(id){
  			  layui.use(['form','element','layer','jquery'],function(){
  				  var $ = layui.jquery;
  				  var element = layui.element;
  				  var layer = layui.layer;
  				  var from = layui.from;
  				//使用AJAX
  				$.get("${ctx}/information/loadInformationsById.action",{id:id},function(informations){
  					layer.open({
  						type:1,
  						title:'查看公告',
  						content:$("#desk_viewNewsDiv"),
  						area:['800px','550px'],
  						success:function(index){
  							$("#view_title").html(informations.title);
  							$("#view_opername").html(informations.eno);
  							$("#view_createtime").html(informations.date);
  							$("#view_content").html(informations.content);
  						}
  					});
  				})
  			 });	
  			}
  			//关于专家系统设计
  			function aboutAsystem(){
  			  layui.use(['form','element','layer','jquery'],function(){
  				  var $ = layui.jquery;
  				  var element = layui.element;
  				  var layer = layui.layer;
  				  var from = layui.from;
  				 layer.open({
  				      type: 1,
  				   	  title:'关于农业专家咨询系统',
  				      area: ['600px', '360px'],
  				      shadeClose: true, //点击遮罩关闭
  				      content: '\<\div style="padding:20px;">农业专家咨询系统是把专家系统知识应用于农业领域的一项计算机技术。专家系统是人工智能的一个分支，主要目的是要使计算机在各个领域中起人类专家的作用。它是一种智能程序子系统，内部具有大量专家水平的领域知识和经验，能利用仅人类专家可用的知识和解决问题的方法来解决该领域的问题。它是一种计算机程序，可以用专家的水平（有时超过专家）完成一般的、模仿人类的解题策略，并与这个问题所特有的大量实际知识和经验知识结合起来。地址: 山东省青岛市城阳区长城路700号，邮箱: 1684221057@qq.com\<\/div>'
  				    });
  			 });
  			}
	</script>
	<script src="${pageContext.request.contextPath}/resources/user/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/resources/user/js/wow.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/user/js/index.js"></script>
    
</body>
</html>