<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改密码页面</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
	
</head>
<body class="childrenBody">
<form  action="${ctx}/desk/SavePwd.action" method="post" class="layui-form layui-row changePwd"  onsubmit="return checkForm()">
	<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
		<div class="layui-input-block layui-red pwdTips">新密码必须两次输入一致才能提交</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<input type="hidden" id="id" />
			<div class="layui-input-block">
				<input type="text" value="${admin.adminname}" name="adminname" id="adminname" disabled class="layui-input layui-disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">旧密码</label>
			<div class="layui-input-block">
				<input type="password" name="adminpsw" id="adminpsw" onblur="a1()"  placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input pwd">
				<span id="adminpswInfo"></span>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码</label>
			<div class="layui-input-block">
				<input type="password" name="NewPwd" id="NewPwd" placeholder="请输入新密码" lay-verify="required|newPwd" id="oldPwd" class="layui-input pwd">
				
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-block">
				<input type="password" name="surePwd" id="surePwd"      placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
				<span id="error" style="color:red;">${error}</span>
				<span  style="color:red;">${updateError}</span>
				<span  style="color:green;">${success}</span>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="changePwd">立即修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript">
		//验证旧密码
		function a1(){
			$.get({
				url:"${ctx}/Change/CheckPwd.action",
				data:{"adminpsw":$("#adminpsw").val()},
				success:function(data){
					alert(data);
					if(data.toString()==='原密码输入正确'){
						$("#adminpswInfo").css("color","green");
					}else if(data.toString()==='原密码输入错误'){
						$("#adminpswInfo").css("color","red");
					}
					
					$("#adminpswInfo").html(data);
				}
			})
}
		
		//确认密码与新密码进行判断
		function checkForm(){
			 Object.innerHTML = "HTML";// 设置  
			 var innerHTML = Object.innerHTML;// 获取 
			var surePwd=document.getElementById("surePwd").value;//获取表单中的元素值
			var NewPwd=document.getElementById("NewPwd").value;
			var adminpsw=document.getElementById("adminpsw").value;
			if(surePwd=="" || NewPwd=="" || adminpsw==""){
				document.getElementById("error").innerHTML="信息填写不完整！";
				return false;
			}else if(NewPwd != surePwd){
				document.getElementById("error").innerHTML="两次密码填写不一致！";
				return false;
			}
			return true;
		}


</script>
<script src="${ctx}/resources/assets/jquery-3.4.1.js"></script>

</body>
</html>