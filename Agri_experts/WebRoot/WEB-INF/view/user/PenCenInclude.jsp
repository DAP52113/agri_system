<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>个人中心</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/public.css" media="all" />
</head>
<body class="childrenBody">
<form action="${ctx}/userSession/UserSavePwd.action" method="post" class="layui-form layui-row" onsubmit="return checkForm()">
	<div class="layui-col-md3 layui-col-xs12 user_right" style="padding-left:40px;">
		<input type="hidden"  name ="id" value="${newUser.id }"/>
		<div class="layui-upload-list">
			<img alt="用户头像图片" width="320px" height="300px" src="${ctx}/file/downloadShowFile.action?path=${newUser.uphoto}" class="layui-upload-img layui-circle userFaceBtn userAvatar"  id="showUserImg">
			<input type="hidden" name="uphoto" id="uphoto" value="${newUser.uphoto}">
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button style="margin-left:36px;" type="button" class="layui-btn layui-btn-primary userFaceBtn" id="carimgDiv"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>
	</div>
	<div class="layui-col-md6 layui-col-xs12" style="padding-left:150px;">
		<div class="layui-form-item">
			<label class="layui-form-label">用&nbsp;&nbsp;户&nbsp;&nbsp;名</label>
			<div class="layui-input-block">
				<input type="text" value="${user.unname}" disabled class="layui-input layui-disabled" id="unname" name="unname">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
			<div class="layui-input-block">
				<input type="password"  onblur="queryPwd()" id="userpsw" name="userpsw"  placeholder="请输入原密码"  lay-verify="required" class="layui-input realName">
			<span id="userPwdInfo"></span>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">修改密码</label>
			<div class="layui-input-block">
				<input type="password" value="" name="upsw" id="upsw" placeholder="请输入修改密码" lay-verify="required" class="layui-input realName">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-block">
				<input type="password" value="" name="surePwd" id="surePwd" placeholder="请输入确认密码" lay-verify="required"  class="layui-input realName">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">自我简介</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea myself" id="introduce" name="introduce">${newUser.introduce}</textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="changeUser">修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
			<span id="error" style="color:red;">${error}</span>
			<span  style="color:red;">${updateError}</span>
			<span  style="color:green;">${success}</span>
		</div>
	</div>
</form>
<script src="${ctx}/resources/assets/jquery-3.4.1.js"></script>
<script src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
		// 查询原密码
		function queryPwd(){
			$("#userPwdInfo").html("");
			$.get({
				url:"${ctx}/userRestController/userChangePwd.action",
				data:{"userpsw":$("#userpsw").val()},
				success:function(data){
					if(data.toString()==="原密码输入正确"){
						$("#userPwdInfo").css("color","green");
					}else if(data.toString()==="原密码输入错误"){
						$("#userPwdInfo").css("color","red");
					}
					$("#userPwdInfo").html(data);
				}
			})
		}
		//确认密码与新密码进行判断
		function checkForm(){
			 Object.innerHTML = "HTML";// 设置  
			 var innerHTML = Object.innerHTML;// 获取 
			var surePwd=document.getElementById("surePwd").value;//获取表单中的元素值
			var upsw=document.getElementById("upsw").value;
			 if(upsw != surePwd){
				 alert("两次输入的密码不一致，请重新输入");
				//document.getElementById("error").innerHTML="两次密码填写不一致！";
				return false;
			}
			 alert("修改成功");
			return true;
		}
		layui.use('upload', function(){
			  var upload = layui.upload;
			  var uploadInst = upload.render({
			        elem: '#carimgDiv', 
			        url: '${ctx}/file/uploadFile.action',
			        acceptMime:'images/*',
			        field:"mf",
			        done: function(res, index, upload){
			        	$('#showUserImg').attr('src',"${ctx}/file/downloadShowFile.action?path="+res.data.src);
			        	alert("确定修改吗");
			            $('#uphoto').val(res.data.src);
			        }
			  });
			});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/js/userInfo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/js/cacheUserInfo.js"></script>
</body>
</html>