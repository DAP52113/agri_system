<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>专家个人中心</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/expert/css/public.css" media="all" />
</head>
<body class="childrenBody">
<form action="${ctx}/expertView/ExpertSavePwd.action" method="post" class="layui-form layui-row" onsubmit="return checkForm()">
	<div class="layui-col-md3 layui-col-xs12 user_right" style="padding-left:40px;padding-top:100px;">
		<input type="hidden"  name ="eid" value="${newExpert.eid}"/>
		<div class="layui-upload-list">
			<img alt="专家头像图片" width="320px" height="300px" src="${ctx}/file/downloadShowFile.action?path=${newExpert.photo}" class="layui-upload-img layui-circle userFaceBtn userAvatar"  id="showUserImg">
			<input type="hidden" name="photo" id="photo" value="${newExpert.photo}">
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button style="margin-left:36px;" type="button" class="layui-btn layui-btn-primary userFaceBtn" id="carimgDiv"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>

	</div>
	<div class="layui-col-md6 layui-col-xs12" style="padding-left:150px;">
		<div class="layui-form-item">
			<label class="layui-form-label">用&nbsp;&nbsp;户&nbsp;&nbsp;名</label>
			<div class="layui-input-block">
				<input type="text" value="${expert.eno}" disabled class="layui-input layui-disabled" id="eno" name="eno">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
			<div class="layui-input-block">
				<input type="password"  onblur="queryPwd()" id="expertpsw" name="expertpsw"  placeholder="请输入原密码"  lay-verify="required" class="layui-input realName">
			<span id="userPwdInfo"></span>
			</div>
			
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
			<div class="layui-input-block">
				<input type="text"  id="ename" name="ename" value="${newExpert.ename}" disabled placeholder="请输入姓名"  lay-verify="required" class="layui-input realName">
			</div>
			
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">修改密码</label>
			<div class="layui-input-block">
				<input type="password"  name="epsw" id="epsw" placeholder="请输入修改密码" lay-verify="required" class="layui-input realName">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-block">
				<input type="password"  name="surePwd" id="surePwd" placeholder="请输入确认密码" lay-verify="required"  class="layui-input realName">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
			<div class="layui-input-block">
				<input type="text"  id="eprof" name="eprof" value="${newExpert.eprof}" disabled placeholder="请输入职称"  lay-verify="required" class="layui-input realName">
		
			</div>
		</div>
		
	 			<div class="layui-form-item">
					    <label class="layui-form-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
					    <div class="layui-input-block" required >
					      <input type="text" class="layui-input realName" name="esex" disabled value="${newExpert.esex}" title="${newExpert.esex}" >
					       
					    </div>
				</div>
		
			  <div class="layui-form-item">
					    <label class="layui-form-label">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</label>
					    <div class="layui-input-block">
					     <input type="text" class="layui-input realName" name="province" disabled value="${newExpert.province}" >
					    </div>
				 </div>
			 <div class="layui-form-item">
					    <label class="layui-form-label">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</label>
					    <div class="layui-input-block" >
					     <input type="text" class="layui-input realName" name="city" disabled value="${newExpert.city}" >
					    </div>
			 </div>
			 <div class="layui-form-item">
					    <label class="layui-form-label">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区</label>
					    <div class="layui-input-block">
					      <input type="text" class="layui-input realName" name="district" disabled value="${newExpert.district}" >
					    </div>
			 </div>
						<div class="layui-form-item">
							<label class="layui-form-label">专家领域:</label>
							<div class="layui-input-block">
								 <input type="text" class="layui-input realName" name="area" disabled value="${newExpert.area}" >
							</div>
						</div>	
		

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="changeUser">修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
			<span id="error" style="color:red;">${error}</span>
			<span  style="color:red;">${updateError}</span>
			
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
				url:"${ctx}/ExpertRest/expertChangePwd.action",
				data:{"expertpsw":$("#expertpsw").val()},
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
			var epsw=document.getElementById("epsw").value;
			 if(epsw != surePwd){
				 alert("两次输入的密码不一致，请重新输入");
				//document.getElementById("error").innerHTML="两次密码填写不一致！";
				return false;
			}
			 alert("修改成功");
			return true;
		}

		
		layui.use('upload', function(){
			  var upload = layui.upload;
			   
			  //执行实例
			  var uploadInst = upload.render({
			        elem: '#carimgDiv', 
			        url: '${ctx}/file/uploadFile.action',
			        acceptMime:'images/*',
			        field:"mf",
			        done: function(res, index, upload){
			        	$('#showUserImg').attr('src',"${ctx}/file/downloadShowFile.action?path="+res.data.src);
			        	alert("确定修改吗");
			            $('#photo').val(res.data.src);
			        }
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


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/js/userInfo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/js/cacheUserInfo.js"></script>
</body>
</html>