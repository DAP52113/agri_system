<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <title>在线提问</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/layui/css/layui.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/master.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/gloable.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/nprogress.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/message.css" /> 
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
                        <li><a href="${ctx}/userView/UserOnlineMessage.action">在线提问</a></li>
                        <!-- 跳转到另外一个子系统项目 -->
                        <li><a href="${ctx}/userView/UserConsultOnline.action">在线咨询</a></li>
                        <li><a href="${ctx}/userView/userDiagnose.action">远程问诊</a></li>
                        <li><a href="${ctx}/userView/UserPersonCen.action">个人中心</a></li>
                    </ul>
                </nav>
                <a href="${pageContext.request.contextPath}/index.jsp" class="blog-user">
                    <i class="fa fa-share"></i>
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
            <h1>在线提问</h1>
            <p>
                实时交流，解决农业问题！
            </p>
        </section>
        <div class="textarea-wrap message" id="textarea-wrap">
					<form class="layui-form" action="${ctx}/userView/UserMessageAddRequest.action" method="post">
					  <div class="layui-form-item">
					    <label class="layui-form-label">提问标题</label>
					    <div class="layui-input-block">
					      <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					
					  <div class="layui-form-item">
					    <label class="layui-form-label">选择省份</label>
					    <div class="layui-input-block">
					      <select name="province" lay-verify="required" required id="province" lay-filter="province">
					        <option value="">请选择省份</option>
					      </select>
					    </div>
					  </div>
					    <div class="layui-form-item">
					    <label class="layui-form-label">选择城市</label>
					    <div class="layui-input-block" >
					      <select name="city" lay-verify="required" required  id="city" lay-filter="city">
					        <option value="">请选择城市</option>
					       
					      </select>
					    </div>
					  </div>
					    <div class="layui-form-item">
					    <label class="layui-form-label">选择地区</label>
					    <div class="layui-input-block">
					      <select name="district" lay-verify="required" required id="district" lay-filter="district">
					        <option value="">请选择地区</option>
					      
					      </select>
					    </div>
					  </div>
					
					  <div class="layui-form-item">
					    <label class="layui-form-label">所属领域</label>
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
					    <label class="layui-form-label">咨询内容</label>
					    <div class="layui-input-block">
					      <textarea name="content" placeholder="请输入内容" required class="layui-textarea"></textarea>
					    </div>
					  </div>
				
					<div class="layui-upload-drag" id="test10" style="margin-left:108px;">
						  	<i class="layui-icon">&#xe62f;</i>
						   <p>点击上传问题图片，或将图片拖拽到此处</p>
						  <div class="layui-hide" id="uploadDemoView">
						    <img  alt="上传成功" style="max-width: 196px" >
						    <input type="hidden" name="photo" id="photo">
  						  </div>
					</div>
				
					  <div class="layui-form-item" style="margin-top:20px;">
					    <div class="layui-input-block">
					      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
					      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					    </div>
					  </div>
					</form>
             <div id="error" style="color:red;">${error }</div>
      </div>
    </div>
    <div class="f-cb"></div>
    <div class="mt20">
        <ul class="message-list" id="message-list">
        <c:forEach var="consult" items="${consults}">
         
            <li class="zoomIn article">
            	<input type="hidden" name="cno" id="cno" value="${consult.cno}">
                <div class="comment-parent">
                    <a name="remark-1"></a>
                    <img alt="咨询图片" src="${ctx}/file/downloadShowFile.action?path=${consult.photo}"/>
                    <div class="info">
                        <span class="username">${consult.counselor}</span>
                    </div>
                    <div class="comment-content">
                    ${consult.title}
                    </div>
                    <div class="comment-content">
                    ${consult.content}
                    </div>
                    <p class="info info-footer">
                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                        <span>${consult.province}</span> 
                        &nbsp;&nbsp;&nbsp;
                        
                        <span>${consult.city}</span> 
                        &nbsp;&nbsp;&nbsp;
                        <span>${consult.district}</span>
                         &nbsp;&nbsp;&nbsp;
                          <span>${consult.area}</span>
                        <span class="comment-time"><fmt:formatDate value="${consult.date}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                        <a href="javascript:;" class="btn-reply" data-targetid="1" data-targetname="${consult.counselor}">回复</a>
                    </p>
                </div>
                <hr />
              <c:forEach var="wordsInfo" items="${wordsInfos}" varStatus="i">
               		<c:if test="${wordsInfo.forarticleid eq consult.cno   &&  wordsInfo.foruser eq consult.counselor}"> 
                <div class="comment-child"> 
                    <a name="reply-1"></a> 
                    <div class="info"> 
                        <span class="username">${wordsInfo.username}</span> 
                        <span style="padding-right:0;margin-left:-5px;">回复</span> 
                        <span class="username">${wordsInfo.foruser}</span> 
                        <span>${wordsInfo.content}</span> 
                    </div> 
                    <p class="info"> 
                        <span class="comment-time"><fmt:formatDate value="${wordsInfo.date}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></span> 
                        <a href="javascript:;" class="btn-reply" data-targetid="2" data-targetname="${wordsInfo.username}">回复</a>
                    </p> 
                </div> 
                 </c:if>
                </c:forEach>
                
                <div class="replycontainer layui-hide"> 
                    <form class="layui-form" action="${ctx}/userView/WordsformRequest.action" method="post"> 
                        <input type="hidden" name="foruser" value="${consult.counselor}"> 
                        <input type="hidden" name="forarticleid" value="${consult.cno}"> 
                        
                        <input type="hidden" name="username" value="${user.unname }"> 
                        <div class="layui-form-item"> 
                            <textarea name="content" lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;"></textarea> 
                        </div> 
                        <div class="layui-form-item"> 
                        <!-- lay-submit="formReply" -->
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
    
    <script src="${pageContext.request.contextPath}/resources/user/layui/layui.js"></script>
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
    <script src="${pageContext.request.contextPath}/resources/user/js/yss/gloable.js"></script>
    <script src="${pageContext.request.contextPath}/resources/user/js/plugins/nprogress.js"></script>
 
    <script src="${pageContext.request.contextPath}/resources/user/js/pagemessage.js"></script>
	<script src="${ctx}/resources/assets/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${ctx}/resources/assets/data.js"></script>
	<script type="text/javascript" src="${ctx}/resources/assets/province.js"></script>
</body>
</html>
