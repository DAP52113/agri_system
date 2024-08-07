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
    <title>农业专家系统-专家</title>
    <link href="${pageContext.request.contextPath}/resources/expert/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/expert/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/expert/css/index_style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/expert/css/animate.min.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="menu" class="hover_animation menu_open" data-mark="false">
        <span></span>
        <span></span>
        <span></span>
    </div>
    <div id="navgation" class="navgation navgation_close">
        <ul class="point">
            <li><a href="${ctx}/expertView/Expertindex.action">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
            <li><a href="${ctx}/expertView/ExpertPersonCen.action">个人中心</a></li>
            <li><a href="${ctx}/expertView/ExpertArticleSubmit.action">信息发布</a></li>
             
            <li><a href="${ctx}/expertView/ExpertOnlineReply.action">在线回复</a></li>
            
            
             <li><a href="${ctx}/expertView/ExpertDiagnose.action">远程回复</a></li>
        </ul>
        <div class="logo"><a>农业专家系统</a></div>
    </div>
    <div class="section" id="section1">
        <div class="fp-tablecell">
            <div class="page1">
                <div class="nav wow zoomIn" data-wow-duration="2s">
                    <h1>农业专家咨询系统</h1>
                    <p>欢迎来到农业专家咨询系统--专家端</p>
                    <a class="layui-btn layui-btn-normal" style="margin-top: 20px" href="${ctx}/expertView/ExpertPersonCen.action">欢迎${expert.ename}进入</a>
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
                            <h1>农业资讯</h1>
                            <!--这里使用数据库查询农业咨询-->
                            <p>
                                在这里可以查看部分专家发布的农业咨询信息
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
                    
                    
    <%--                
                    <div class="layui-row">
                        <div class="layui-col-xs12 layui-col-sm4 layui-col-md4  wow fadeInUp" style="padding: 0 10px">
                            <div class="single-news">
                                <div class="news-head" id="carimgDiv">
                                显示图片src="${ctx}/file/downloadShowFile.action?path=${information.wentiphoto}" 标题:${information.title}
                                <input type="hidden"  id="infoid" >
                                <img alt="资讯图片" id="imageInfo" />
                                </div>
                                <div class="news-content">
                                    <h4 id="titleInfo">
                                    </h4>
                                    <div class="date" id="dateInfo">
                                    </div>
                                    <p id="contentInfo">
                                    </p>
                                    
                                    <a  class="layui-btn"  style="margin-top:10px"  id="viewNews">
                                       阅读更多
                                    </a>
                                </div>
                               </div>
                              </div>
                            </div>  --%>
                            
                            
                           <div id="pageInfor" style="text-align:center; margin-top:20px;"></div>
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
                                    <li class="wow fadeInLeft"><a href="${ctx}/expertView/ExpertOnlineReply.action">在线回复</a></li>
                                    <li class="wow fadeInRight"><a href="${ctx}/expertView/ExpertPersonCen.action">个人中心</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 <!--  
    <div class="section" id="section4">
        <div class="fp-tablecell">
            <div class="page2">
                <div class="warp-box">
                 <h1>系统公告</h1>
                     <c:forEach var="expert" items="${experts}">
                     <div class="layui-col-xs12 layui-col-sm4 layui-col-md4  wow fadeInUp" data-wow-delay=".2s" style="padding: 0 10px" >
                           
                            <div class="single-news">
                                <div class="news-head">
                                
                                    <img width="400px" height="300px" alt="专家图片" src="${ctx}/file/downloadShowFile.action?path=${expert.photo}"  />
                                </div>
                                <div class="news-content">
                                    <h4>
                                        <a >
                                            专家名称 ：${expert.ename }	
                                        </a>
                                    </h4>
                                    <div class="date" >
                             
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
    -->
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
                                <li class="active"><a href="${ctx}/expertView/ExpertArticleSubmit.action"><i class="fa fa-book"></i>信息发布</a></li>
                                <li class="active"><a href="${ctx}/expertView/ExpertPersonCen.action"><i class="fa fa-comments"></i>个人中心</a></li>
                                <li class="active"><a href="${ctx}/expertView/ExpertOnlineReply.action"><i class="fa fa-share"></i>在线回复</a></li>
                                <li class="active"><a href="${ctx}/expertView/ExpertDiagnose.action"><i class="fa fa-snowflake-o"></i>远程回复</a></li>
                            </ul>                   
                        </div>
                    </div>
                    <div class="layui-col-xs12 layui-col-sm12 layui-col-md4">
                        <div class="single-widget contact">
                            <h2>联系方式</h2>
                            <ul class="list">
                                <li><i class="fa fa-map"></i>地址: 山东省城阳区长城路700号</li>
                                <li><i class="fa fa-qq"></i>QQ: 1684221057 </li>
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
			发布时间:<span id="view_createtime"></span><span style="display: inline-block;width: 20px" ></span>
			所属领域:<span id="view_createarea"></span><span style="display: inline-block;width: 20px" ></span>
			发布地点:<span id="view_createaddress"></span><span style="display: inline-block;width: 20px" ></span>

		</div>
		<hr>
		<div id="view_content"></div>
		
	</div>
	<script src="${ctx}/resources/chat/js/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/expert/layui/layui.js"></script>
	<script>

		
		layui.use(['laypage', 'layer'], function(){
			  var laypage = layui.laypage
			  ,layer = layui.layer;
			//总页数大于页码总数
			  laypage.render({
			    elem: 'pageInfor'
			    ,count: 10 //数据总数 
			    ,limit:3
			    ,curr: location.hash.replace('#!p=', '') //获取hash值为fenye的当前页
			    ,jump: function(obj){
			      console.log(obj);
			      var p = obj.curr;//获取用户点击的页码
			      var limit = obj.limit;//获取用户设置的每页显示多少条数据 
			      
			      $.get("${ctx}/ExpertRest/loadInformationsLimit.action",{p:p,limit:limit},function(data){
			    	//datas = JSON.parse(data);//转为JSON
			    
			    	for(var i = 0;i<data.length;i++){
			    		//设置多个div追加
			    		$("#imageInfo").attr("src","${ctx}/file/downloadShowFile.action?path="+data[i].wentiphoto); 
			    		//$("#titleInfo").append('<a>'+"标题:"+data[i].title+'</a>'); 
			    		$("#titleInfo").html("标题:"+data[i].title);
			    		$("#dateInfo").html(data[i].date);
			    		$("#contentInfo").html(data[i].content);
			    		$("#infoid").html(data[i].id);

			    	}
			      })
			      
			      
			      
			      
			    }
			  });
				  });  


	</script>
	<script type="text/javascript">
	//显示文章内容事件
	  //弹出一个页面层
	//  layui.use(['form','element','layer','jquery'],function(){
	//	  var $ = layui.jquery;
		//  var element = layui.element;
		//  var layer = layui.layer;
		//  var from = layui.from;
  		//$('#test1').on('click', function(){
		   //layer.open({
		    //  type: 1,
		   //   area: ['600px', '360px'],
		    //  shadeClose: true, //点击遮罩关闭
		  //    content: $("#desk_viewNewsDiv"),
			//	success:function(layero,index){
					//alert("查看咨询内容");
				//注意事项：使用时需要将其layui 的数据放到所使用的函数中，同事对于id的传值需要设计回调函数
			//	}
		 //   }); 
  			function viewNews(id){
  			  layui.use(['form','element','layer','jquery'],function(){
  				  var $ = layui.jquery;
  				  var element = layui.element;
  				  var layer = layui.layer;
  				  var from = layui.from;
  				var laypage = layui.laypage;
  
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
  							$("#view_createarea").html(informations.area);
  							$("#view_createaddress").html(informations.city+" "+informations.district);
  							
  						
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
  				      content: '\<\div style="padding:20px;">农业专家咨询系统是把专家系统知识应用于农业领域的一项计算机技术。专家系统是人工智能的一个分支，主要目的是要使计算机在各个领域中起人类专家的作用。它是一种智能程序子系统，内部具有大量专家水平的领域知识和经验，能利用仅人类专家可用的知识和解决问题的方法来解决该领域的问题。它是一种计算机程序，可以用专家的水平（有时超过专家）完成一般的、模仿人类的解题策略，并与这个问题所特有的大量实际知识和经验知识结合起来。地址: 山东省青岛市城阳区长城路路700号，QQ: 1684221057，邮箱: 1684221057@qq.com\<\/div>'
  				    });
  			 });
  			}
	</script>
	
    <script src="${pageContext.request.contextPath}/resources/expert/js/wow.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/expert/js/index.js"></script>
    
</body>
</html>