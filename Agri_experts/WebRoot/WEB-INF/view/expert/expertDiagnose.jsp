<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width" />
    <meta name="author" content="www.yanshisan.cn" />
    <meta name="robots" content="all" />
    <title>远程回复页面</title>
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
            <h1>一对一回复</h1>
            <p>
                专家远程回复，解决用户咨询问题！
            </p>
        </section>
    </div>
    <div class="f-cb"></div>
    <div class="mt20">
        <ul class="message-list" id="message-list">
        <c:forEach var="diagnose" items="${diagnoses}" varStatus="i">
            <li class="zoomIn article">
            	<input type="hidden" name="dno" id="dno" value="${diagnose.dno}">
                <div class="comment-parent">
                    <a name="remark-1"></a>
                    <img alt="咨询图片" src="${ctx}/file/downloadShowFile.action?path=${diagnose.photo}"/>
                    <div class="info">
                        <span class="username">${diagnose.counselor}</span>
                        <span class="username" style="color:green;float:right;">${i.count}</span>
                    </div>
                    <div class="comment-content">
                    ${diagnose.title}
                    </div>
                    <div class="comment-content">
                    ${diagnose.content}
                    </div>
                    <p class="info info-footer">
                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                        <span class="comment-time"><fmt:formatDate value="${diagnose.indate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                        <a href="javascript:;" class="btn-reply" data-targetid="1" data-targetname="${diagnose.counselor}">回复</a>
                    </p>
                </div>
                <hr />
            
              <c:forEach var="diagnosereply" items="${diagnosereplies}" varStatus="i">
              <!-- 如果回复的文章id和咨询的id相等，且回复的用户与发布的用户相等则显示 -->
               		<c:if test="${diagnosereply.fordiagnoseid eq diagnose.dno   &&  diagnosereply.replyuser eq diagnose.counselor}"> 
                <div class="comment-child"> 
                    <a name="reply-1"></a> 
                    <div class="info"> 
                        <span class="username">${diagnosereply.expert}</span> 
                        <span style="padding-right:0;margin-left:-5px;">回复</span> 
                        <span class="username">${diagnosereply.replyuser}</span> 
                        <span>${diagnosereply.content}</span> 
                    </div> 
                    <p class="info"> 
                        <span class="comment-time"><fmt:formatDate value="${diagnosereply.date}" pattern="yyyy-MM-dd HH:mm:ss" /></span> 
                        <a href="javascript:;" class="btn-reply" data-targetid="2" data-targetname="${diagnosereply.expert}">回复</a>
                    </p> 
                </div> 
                 </c:if>
                </c:forEach>
                
                <div class="replycontainer layui-hide"> 
                    <form class="layui-form" action="${ctx}/expertView/DiagnoseRepliesform.action" method="post"> 
                        <input type="hidden" name="replyuser" value="${diagnose.counselor}"> 
                        <input type="hidden" name="fordiagnoseid" value="${diagnose.dno}"> 
                        <input type="hidden" name="expert" value="${expert.eno }"> 
                        <div class="layui-form-item"> 
                            <textarea name="content" lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;"></textarea> 
                        </div> 
                        <div class="layui-form-item"> 
                   
                            <button class="layui-btn layui-btn-xs" lay-submit="formReply" lay-filter="formReply">提交</button> 
                        </div> 
                    </form> 
                </div>
          
            </li>
      </c:forEach>
        </ul>
    </div>
</div>
    </div>
    <div style="color:red;text-align:center;">${inserterror}</div>
    <footer class="grid-footer">
        <div class="footer-fixed">
            <div class="copyright">
                <div class="info">
                    <div class="contact">
                       <a href="https://wpa.qq.com/msgrd?v=3&uin=930054439&site=qq&menu=yes" class="qq" target="_blank" title="930054439"><i class="fa fa-qq"></i></a>
                    </div>
                    <p class="mt05">
                        Copyright &copy; 农业专家咨询系统版权所有
                    </p>
                </div>
            </div>
        </div>
    </footer>
    
    <script src="${pageContext.request.contextPath}/resources/expert/layui/layui.js"></script>
    <script>
    	layui.use([ 'jquery', 'layer', 'form', 'table','laydate','layedit','upload' ], function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form;
		var table = layui.table;
		var laydate=layui.laydate;
		var layedit=layui.layedit;
		var upload=layui.upload;
		
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
		      $('#photo').val(res.data.src);
		    }
		  });
		
		  form.on('submit(formDemo)', function(data){
			    layer.msg('咨询请求提交成功');
			    return true;
			  });
		
			});
</script>
	<script type="text/javascript" src="${ctx}/resources/assets/data.js"></script>
	<script type="text/javascript" src="${ctx}/resources/assets/province.js"></script>
	<script type="text/javascript">
        var defaults = {
            s1: 'province',
            s2: 'city',
            s3: 'district',
            v1: null,
            v2: null,
            v3: null
        };
 
</script>
    <script src="${pageContext.request.contextPath}/resources/expert/js/yss/gloable.js"></script>
    <script src="${pageContext.request.contextPath}/resources/expert/js/plugins/nprogress.js"></script>
    <script>NProgress.start();</script>
    <script src="${pageContext.request.contextPath}/resources/expert/js/pagemessage.js"></script>
    <script>NProgress.start();</script>
    <script> 
        window.onload = function () {
            NProgress.done();
        };
    </script>
</body>
</html>
