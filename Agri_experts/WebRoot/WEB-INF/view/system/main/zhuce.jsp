<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/css_zhuce/public.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/css_zhuce/login.css"/>
    
    <script src="${ctx }/resources/js/jquery-1.12.4.min.js"></script>
    <script src="${ctx }/resources/js/function.js"></script>
</head>
<script type="text/javascript">
		function checkForm(){
			var unname = document.getElementById("unname").value;
			var password = document.getElementById("password").value;
			var repassword = document.getElementById("repassword").value;
			var introduce = document.getElementById("introduce").value;
			$("#error").html("");
		
			if(unname=="" || password==""|| repassword==""  || introduce==""){
				alert("信息填写不完整！");
				return false;
			}else if(password != repassword){
				alert("确认密码与原密码不一致！");
				return false;
			}
			if(unname !="" && password != "" && repassword != "" && password == repassword && introduce != ""){
				alert("注册成功！请登录");
				return true;
			}
			
		}
		function change(img){
			img.src="getcode?"+new Date().getTime();
		}
</script>


<style>
	.reg p .error{
		width:310px;
		display:inline-block;
		border :1px solid #ff855a;
		background-color:#ffe8e0;
	}
	.reg form {
    width: 400px;
    height: 580px;
    background: #fff;
}

</style>
<body>
<div class="reg">
    <form action="${ctx}/userRegister/userRegisterToLogin.action" method="post" onsubmit="return checkForm()">
    <h1><a href="#"><img src="${ctx }/resources/images/市级专家.jpg" width="30px" height="30px"></a></h1>
        <p><h2>用户注册</h2></p>
      
        <p><input style="width:300px;" type="text" id ="unname" name="unname" onfocus ="FocusItem(this)" onBlur="CheckItem(this)"  value="" placeholder="请输入登录名"><span></span></p>
       
        <!-- <p><input style="width:300px;" type="text" id="name" name="name" onfocus ="FocusItem(this)" onBlur="CheckItem(this)" value="" placeholder="请输入姓名"></p> -->
        <p><input style="width:300px;" type="password"  id = "password" name="password" onfocus ="FocusItem(this)" onBlur="CheckItem(this)" value="" placeholder="请输入密码"><span></span></p>
        <p><input style="width:300px;" type="password"  id = "repassword" name="repassword" onfocus ="FocusItem(this)" onBlur="CheckItem(this)"  value="" placeholder="请确认密码"><span></span></p>
    <!-- 
         <input  id="user" type="radio" name="userType" value="0"><strong>普通用户</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input style="margin-left:60px;" id="expert" type="radio" name="userType" value="1"><strong>专家</strong>
      -->
        <!-- 
        <p><input style="width:300px;" type="text" name="eprof" value="" placeholder="请输入职称"></p>
           
        
        <p style="margin-left:44px;margin-top:20px;" class="txtL txt"><input style="width: 100px;" type="text" id="veryCode" name="veryCode" onfocus ="FocusItem(this)" onBlur="CheckItem(this)" value="" placeholder="验证码"><img
                src="getcode"  alt="如看不清楚，请换一张" onclick="change(this)"><span align="center"></span></p> -->
         <p id = "errorRegister"></p>
         <p><textarea style="width:300px;"wrap="physical"  rows="7"  type="text" name="introduce" id="introduce" value="" placeholder="请输入个人简介"  onfocus ="FocusItem(this)" onBlur="CheckItem(this)"></textarea><span></span></p>
       
       	<input type="submit" name="" value="注册" style="margin-top:10px;margin-right:50px; margin-left:65px; width: 280px;background: #C10000;font-size: 20px;border: none; color: #fff;">
        <p class="txtL txt" style="margin-top:30px;">&nbsp;&nbsp;&nbsp;&nbsp;没有专家账号？-><a href="${ctx }/userRegister/expertRegister.action">
            专家注册
        </a></p>
      
      
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>-->
        
        </form>
</div>
</body>
</html>