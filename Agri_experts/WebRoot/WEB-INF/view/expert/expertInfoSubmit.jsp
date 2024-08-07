<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width" />
    <meta name="author" content="www.yanshisan.cn" />
    <meta name="robots" content="all" />
    <title>在线咨询</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/layui/css/layui.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/master.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/gloable.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/nprogress.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/message.css" /> 
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
        <div class="container-fixed">
    <div class="container-inner">
        <section class="msg-remark">
            <h1>信息发布</h1>
            <p>
                专家发布农业资讯信息！
            </p>
        </section>
        <div class="textarea-wrap message" id="textarea-wrap">
					<form class="layui-form" action="${ctx}/expertView/ExpertMessageAddRequest.action" method="post">
					  <div class="layui-form-item">
					    <label class="layui-form-label">信息标题</label>
					    <div class="layui-input-block">
					      <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					
					  <div class="layui-form-item">
					    <label class="layui-form-label">所属省份</label>
					    
					    <div class="layui-input-block">
					      <select id="province" name="province" lay-verify="required" lay-search lay-filter="province">
					        <option value="">省份</option>
					      </select>
					    </div>
					    
					  </div>
					    <div class="layui-form-item">
					    <label class="layui-form-label">所属城市</label>
					    <div class="layui-input-block" >
					      <select   id="city"  name="city"  lay-verify="required" lay-search lay-filter="city">
					        <option value="">城市</option>
					       
					      </select>
					    </div>
					  </div>
					    <div class="layui-form-item">
					    <label class="layui-form-label">所属地区</label>
					    <div class="layui-input-block">
					      <select  id="district" name="district"  lay-verify="required" lay-search>
					        <!-- lay-filter="district" lay-verify="required" required  -->
					      <option value="">地区</option>
					      </select>
					    </div>
					  </div>
					
					  <div class="layui-form-item">
					    <label class="layui-form-label">资讯领域</label>
					    <div class="layui-input-block" required>
					      <input type="radio" name="area" value="种植" title="种植" checked>
					      <input type="radio" name="area" value="养殖" title="养殖">
					      <input type="radio" name="area" value="园艺" title="园艺">
					      <input type="radio" name="area" value="植保" title="植保">
					      <input type="radio" name="area" value="农业工程" title="农业工程">
					      <input type="radio" name="area" value="农业经济" title="农业经济">
					      <input type="radio" name="area" value="政策法规" title="政策法规">
					      <input type="radio" name="area" value="其他" title="其他" >
					    </div>
					  </div>
	
					
					<div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">信息内容</label>
					    <div class="layui-input-block">
					      <textarea class="layui-textarea layui-hide" name="content"  required   id="content"></textarea>
					    </div>
					  </div>
				
					<div class="layui-upload-drag" id="test10" style="margin-left:108px;">
						  	<i class="layui-icon">&#xe62f;</i>
						   <p>点击上传问题图片，或将图片拖拽到此处</p>
						  <div class="layui-hide" id="uploadDemoView">
						    <img  alt="上传成功" style="max-width: 196px" >
						    <input type="hidden" name="wentiphoto" id="wentiphoto">
  						  </div>
					</div>
				
					  <div class="layui-form-item" style="margin-top:20px;">
					    <div class="layui-input-block">
					      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
					      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					    </div>
					  </div>
					</form>
             
             <!--  <div id="error" style="color:red;">${error}</div>-->
      </div>
    </div>
    <div class="f-cb" id="error">${error}</div>
   
    <div class="mt20">
        <ul class="message-list" id="message-list">
        <c:forEach var="disableYesInformation" items="${disableYesInformations}">
         
            <li class="zoomIn article">
            	<input type="hidden" name="id" id="id" value="${disableYesInformation.id}">
                <div class="comment-parent">
                    <a name="remark-1"></a>
                    <img alt="咨询图片" src="${ctx}/file/downloadShowFile.action?path=${disableYesInformation.wentiphoto}"/>
                    <div class="info">
                        <span class="username">${disableYesInformation.eno}</span>
                        <span class="username" style="float:right; color:green;">已通过审核</span>
                    </div>
                
                    <div class="comment-content">
                    ${disableYesInformation.title}
                    </div>
                    <div class="comment-content">
                    ${disableYesInformation.content}
                    </div>
                    <p class="info info-footer">
                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                        <span>${disableYesInformation.province}</span> 
                        &nbsp;&nbsp;&nbsp;
                        
                        <span>${disableYesInformation.city}</span> 
                        &nbsp;&nbsp;&nbsp;
                        <span>${disableYesInformation.district}</span>
                         &nbsp;&nbsp;&nbsp;
                          <span>${disableYesInformation.area}</span>
                        <span class="comment-time">${disableYesInformation.date}</span>
                        
                    </p>
                </div>
                <hr />
            </li>
      </c:forEach>
        </ul>
    </div>
    
        <div class="mt20">
        <ul class="message-list" id="message-list">
        <c:forEach var="disableNoInformation" items="${disableNoInformations}">
         
            <li class="zoomIn article">
            	<input type="hidden" name="id" id="id" value="${disableNoInformation.id}">
                <div class="comment-parent">
                    <a name="remark-1"></a>
                    <img alt="咨询图片" src="${ctx}/file/downloadShowFile.action?path=${disableNoInformation.wentiphoto}"/>
                    <div class="info">
                        <span class="username">${disableNoInformation.eno}</span>
                        <span class="username" style="float:right; color:red;">审核不通过</span>
                    </div>
                
                    <div class="comment-content">
                    ${disableNoInformation.title}
                    </div>
                    <div class="comment-content">
                    ${disableNoInformation.content}
                    </div>
                    <p class="info info-footer">
                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                        <span>${disableNoInformation.province}</span> 
                        &nbsp;&nbsp;&nbsp;
                        
                        <span>${disableNoInformation.city}</span> 
                        &nbsp;&nbsp;&nbsp;
                        <span>${disableNoInformation.district}</span>
                         &nbsp;&nbsp;&nbsp;
                          <span>${disableNoInformation.area}</span>
                        <span class="comment-time">${disableNoInformation.date}</span>
                        
                    </p>
                </div>
                <hr />
            </li>
      </c:forEach>
        </ul>
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

    <script src="${pageContext.request.contextPath}/resources/user/js/yss/gloable.js"></script>
    <script src="${pageContext.request.contextPath}/resources/user/js/plugins/nprogress.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/user/js/pagemessage.js"></script>
    <script>NProgress.start();</script>
    
     
    <script> 
        window.onload = function () {
            NProgress.done();
        };
    </script>
    
</body>
  <script src="${pageContext.request.contextPath}/resources/user/layui/layui.js"></script>
    	<script src="${ctx}/resources/chat/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/data.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/province.js"></script>


    <script>
   
    layui.use([ 'jquery', 'layer', 'form', 'table','laydate','layedit','upload' ], function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form;
		var table = layui.table;
		var laydate=layui.laydate;
		var layedit=layui.layedit;
		var upload=layui.upload;


		 //自定义工具栏
		  	//初始化富文本编辑器
			var editIndex = layedit.build('content', {
		    tool: ['face', 'link', 'unlink', '|', 'left', 'center', 'right']
		    ,height: 180
		  });
		//拖拽上传
		  upload.render({
		    elem: '#test10'
		    ,url: '${ctx}/file/uploadFile.action' //改成您自己的上传接口
		    ,method : "post"  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
		    ,acceptMime:'images/*'
		    ,field:"mf"
		    ,done: function(res,upload){
		      layer.msg('上传成功');
		      layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', "${ctx}/file/downloadShowFile.action?path="+res.data.src);
		      $('#wentiphoto').val(res.data.src);
		    }
		  });
		
		  form.on('submit(formDemo)', function(data){
			  	layedit.sync(editIndex);
			    layer.msg('信息发布成功');
			    return true;
			  });
		
			});
</script>

 
</html>
