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
<title>专家在线咨询窗口实现</title>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/chat/js/sockjs.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chat/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chat/css/chat.css" type="text/css" media="all" />
</head>

<body>
<!--顶部开始-->
<div class="top">
    <div class="inTop">
        <p>专家在线回复页面</p>
        <span><strong>在线咨询</strong></span>
    </div>
</div>
<!--顶部end-->
<!--头部开始-->
<div class="header">
    <div class="inHeader">
        <h1>聊天室</h1>
        <div class="style">
            <p>当前登录用户：${sessionScope.expert!=null?sessionScope.expert.eno:"请登录" }&nbsp;&nbsp;&nbsp;<button id="exitBtn">退出或重新登录</button></p>
        </div>
    </div>
</div>
<!--头部end-->
<!--聊天区域开始-->
<div class="chatArea" id="chatArea">
    <div class="inChatArea">
        <div id="chatSidebar" class="chatSidebar">
            <h2 id="chatOnline">好友列表</h2>
            <ul id="chatUserList" >
            	<!--<li onclick='showChat("")'> <img style="margin-top: 8px;width: 37px;height: 37px;"  src=""/><span id="nickname_user"  style="padding-bottom:10px;">&nbsp;&nbsp;&nbsp;&nbsp;</span> -->
         	    
                
            </ul>
        </div>
       
        <div class="chatCon">
        <!-- 聊天昵称 -->
         <span id="nick" style="color:green; font-size:16px;margin-left:250px;"></span>
            
            <div class="up" id="up">
                <ul id="contentUl">
                <!-- 
                     <li><b></b><em></em><span></span></li>
                    --> 
                </ul>
            </div>
            <div class="down"  id="chattext">
                <textarea class="textInfo" id="msg" title="直接发送"></textarea>
                <button class="btn" id="sendBtn"></button>
            </div>
        </div>
         <!-- 
        <div class="ad">
            <ul id="userlist">
                <li><a onclick='showChat("张三")'>张三</a></li>
                <li><a onclick='showChat("李四")'>李四</a></li>
             </ul>
             
            <ul id="broadlist">
                <li><a onclick='showChat("张三")'>张三</a></li>
                <li><a onclick='showChat("李四")'>李四</a></li>
             </ul>
            <!--  <iframe src="" width="315" height="635" scrolling="no" frameborder="no" />
        </div>
        -->
    </div>
</div>
<!--聊天区域结束-->
<div class="footer">
    <p>版权所有 @农业专家系统</p>
</div>
</body>

<script src="${ctx}/resources/assets/jquery-3.4.1.js"></script>

<script type="text/javascript">
 
 	var ws;

 	var username = '${sessionScope.expert.eno}'; 
 	var msg;
 	var ws_url = "ws://192.168.90.208:8080/Agri_experts/chatroom";
	$(function(){
		ws_connect();
		$("#sendBtn").click(function(){
			ws_sendMsg();
		});
//点击事件

		//动态绑定，通过body作为父元素，on设置，第一个参数为click为点击事件，其次是函数绑定
		$("body").on('click','.userNameliststr',function(){
			
			$("#msg").val("@"+$(this).attr("id")+":");
		});

		
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
				for(i in userList){
					userListhtml = userList[i];
				}
				$("#chatUserList").append('<li>'+'<span class="userNameliststr" id="'+userListhtml+'" style="padding-bottom:10px;color:red;">'+userListhtml+'</span>'+'</li>');
				//$("#chatUserList").html(userListhtml); 
			}else if(msg.msgType=="e"){
				if(msg.msgRecipient){
					$("#contentUl").append("<li><b>"+msg.msgDateStr+"</b><em>"+msg.msgOriginator+"->"+msg.msgRecipient+"</em><span>"+msg.msgInfo+"</span></li>");
				}else{
					$("#contentUl").append("<li><b>"+msg.msgDateStr+"</b><em>"+msg.msgOriginator+"</em><span>"+msg.msgInfo+"</span></li>");
				}
				
			}
			//$("#contentUl").append("<li><b></b><em>"+username+"</em><span>"+getMessage+"</span></li>");
		};
		
		
	
		
	}
	
	function ws_sendMsg(){
		var sendMsgContent =  $("#msg").val();
		ws.send(sendMsgContent);
		//清空输入框
		sendMsgContent =  $("#msg").val("");
	};
	
	function ws_sendimage(){
		
	};
	



 
			

 
</script>
</html>