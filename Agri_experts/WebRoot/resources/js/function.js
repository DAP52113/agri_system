


function FocusItem(obj){
	
	if($(obj).attr('name') == 'veryCode'){
		$(obj).next().next().html('').removeClass('error');
	}else{
		$(obj).next('span').html('').removeClass('error');
	}
	
}

function CheckItem(obj){
	
	var msgBox = $(obj).next('span');
	switch($(obj).attr('name')){
	case "unname":
		if(obj.value == ""){
			msgBox.html("注册用户名不能为空！");
			msgBox.addClass('error');
		}else{
			var url = "userNameCheck.action?unname="+encodeURI($(obj).val());
			$.get(url,function(data){
				if(data =="error"){
					msgBox.html("用户名不能使用！");
					msgBox.addClass('error');
				}else{
					alert("恭喜用户名可以使用！");
					msgBox.html().removeClass('error');
					
				}
			});
	
		}
		break;
	case "password" :
		if(obj.value == ""){
			msgBox.html("注册密码不能为空！");
			msgBox.addClass('error');
		}
		break;
	case "repassword":
		if(obj.value == ""){
			msgBox.html("确认密码不能为空！");
			msgBox.addClass('error');
		}else {
			if($(obj).val() != $("input[name=password]").val()){
				msgBox.html("确认密码与注册密码不一致！");
				msgBox.addClass('error');
			}
		}
		break;
	case "introduce":
		if(obj.value == ""){
			msgBox.html("个人简介不能为空！");
			msgBox.addClass('error');
		}
		break;
	case "veryCode":
		var numshow = $(obj).next().next('span');
		if(obj.value == ""){
			numshow.html("验证码不能为空！");
			numshow.addClass('error');
		}else{
			var url = "checkusernum?veryCode="+encodeURI($(obj).val());
			$.get(url,function(data){
				if(data == "false"){
					numshow.html("验证码输入错误！");
					numshow.addClass('error');
				}else{
					numshow.html().removeClass('error');
				}
			});
		}
		break;
	}

}

function FocusItemExpert(obj){
	
	if($(obj).attr('name') == 'veryCode'){
		$(obj).next().next().html('').removeClass('error');
	}else{
		$(obj).next('span').html('').removeClass('error');
	}
	
}
function CheckItemExpert(obj){
	
	var msgBox = $(obj).next('span');
	switch($(obj).attr('name')){
	case "userName":
		if(obj.value == ""){
			msgBox.html("注册专家用户名不能为空!");
			msgBox.addClass('error');
		}else{
			var url = "expertnamecheck.action?userName="+encodeURI($(obj).val());
			$.get(url,function(data){
				if(data =="error"){
					msgBox.html("专家登陆名不符合手机号规范！");
					msgBox.addClass('error');
				}else{
					alert("恭喜专家用户名可以使用！");
					msgBox.html().removeClass('error');
				}
			});
		}
		break;
	case "name":
		if(obj.value ==""){
			msgBox.html("注册姓名不能为空！");
			msgBox.addClass('error');
		}
		break;
	case "password" :
		if(obj.value == ""){
			msgBox.html("注册密码不能为空！");
			msgBox.addClass('error');
		}
		break;
	case "repassword":
		if(obj.value == ""){
			msgBox.html("确认密码不能为空！");
			msgBox.addClass('error');
		}else {
			if($(obj).val() != $("input[name=password]").val()){
				msgBox.html("确认密码与注册密码不一致！");
				msgBox.addClass('error');
			}
		}
		break;
	case "eprof":
		if(obj.value ==""){
			msgBox.html("专家职称不能为空！");
			msgBox.addClass('error');
		}
		break;

	}
}


