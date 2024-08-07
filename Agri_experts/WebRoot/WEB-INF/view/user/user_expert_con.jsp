<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
    String baseUrlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="在线聊天室">
<meta name="description" content="专家在线咨询">
<title>用户专家咨询</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/chatbeuser/js/sockjs.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chatbeuser/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chatbeuser/css/chat.css" type="text/css" media="all" />
</head>
<body>
<div class="top">
    <div class="inTop">
        <p>专家在线回复页面</p>
        <span><strong>在线咨询</strong></span>
    </div>
</div>
<div class="header">
    <div class="inHeader">
        <h1>聊天室</h1>
        <div class="style">
            <p>&nbsp;&nbsp;&nbsp;<button id="exitBtn"><a href="${ctx }/login/toLogin.action">退出或重新登录</a></button></p>
        </div>
    </div>
</div>
<div class="chatArea" id="chatArea">
    <div class="inChatArea">
        <div id="chatSidebar" class="chatSidebar">
            <h2 id="chatOnline">好友列表</h2>
            <ul id="chatUserList" >
            </ul>
        </div>
        <div class="chatCon">
         <span id="nick" style="color:green; font-size:16px;margin-left:250px;"></span>
            <div class="up" id="up">
                <ul id="contentUl">
                </ul>
            </div>
            <div class="down"  id="chattext">
                <textarea class="textInfo" id="msg" title="直接发送"></textarea>
                <button class="btn" id="sendBtn"></button>
            </div>
        </div>
    </div>
</div>
<!--聊天区域结束-->
<div class="footer">
    <p>版权所有 @农业专家咨询系统</p>
</div>
</body>
<script src="${ctx}/resources/assets/jquery-3.4.1.js"></script>
<script type="text/javascript">
 	var ws;
 	var username = '${sessionScope.expert.eno}' ? '${sessionScope.expert.eno}' :'${sessionScope.user.unname}' ; 
 	//var user = '${sessionScope.user.unname}'; 
 	var msg;
 	var ws_url = "ws://192.168.43.40:8080/Agri_experts/chatroomtouser";
	$(function(){
		ws_connect();
		$("#sendBtn").click(function(){
			ws_sendMsg();
		});
		//动态绑定，通过body作为父元素，on设置，第一个参数为click为点击事件，其次是函数绑定
		$("body").on('click','.userNameliststr',function(){
			$("#msg").val("@"+$(this).attr("id")+":");
		});
	});
	$("#uploadImg").on("click",function(){
		ws_sendimage();
	});
	function ws_connect(){
		if('WebSocket' in window){
			ws = new WebSocket(ws_url+'?eno='+username);
		}
		ws.open = function (){
			console.log('连接成功');
		};
		ws.onmessage = function (message){
				console.log(message.data);
				msg =  JSON.parse(message.data);
				if(msg.msgType=="s"){
					$("#contentUl").append("<li><b>"+msg.msgInfo+"</b></li>");
					var userListhtml = "";
					var userList = msg.userlists;
					var i;
					for(i =  0;i<userList.length;i++){
						userListhtml =  userList[i];
						$("#chatUserList").append('<li>'+'<span class="userNameliststr" id="'+userListhtml+'" style="padding-bottom:10px;color:red;">'+userListhtml+'</span>'+'</li>');
					}
				}else if(msg.msgType=="e"){
					if(msg.msgRecipient){
						$("#contentUl").append("<li><b>"+msg.msgDateStr+"</b><em>"+msg.msgOriginator+"->"+msg.msgRecipient+"</em><span>"+msg.msgInfo+"</span></li>");
					}else{
						$("#contentUl").append("<li><b>"+msg.msgDateStr+"</b><em>"+msg.msgOriginator+"</em><span>"+msg.msgInfo+"</span></li>");
					}
				}
		};
	}
	function ws_sendMsg(){
		var sendMsgContent =  $("#msg").val();
		ws.send(sendMsgContent);
		sendMsgContent =  $("#msg").val("");
	};
	function ws_sendimage(){
		var imgCont =  $("#img")[0].files[0];//转换为JavaScript对象
		if(imgCont){
			var reader = new FileReader();//新建一个reader对象
			reader.readAsArrayBuffer(imgCont);
			reader.onload = function loaded(evt){//转换为流格式
				ws.send(evt.target.result);
			}
			$("#img").val("");
		}else{
			$("#img").val("");//初始化
			return;
		}
	};
	



 
			

 
</script>



</html>