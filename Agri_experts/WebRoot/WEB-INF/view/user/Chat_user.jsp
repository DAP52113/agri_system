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
<meta name="description" content="用户在线咨询">
<title>用户聊天窗口实现</title>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/chat/js/sockjs.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chat/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chat/css/chat.css" type="text/css" media="all" />
</head>

<body>
<!--顶部开始-->
<div class="top">
    <div class="inTop">
        <p>用户聊天页面</p>
        <span><strong>在线提交问题</strong></span>
    </div>
</div>
<!--顶部end-->
<!--头部开始-->
<div class="header">
    <div class="inHeader">
        <h1>聊天室</h1>
        <div class="style">
            <p>当前登录用户：${sessionScope.user!=null?sessionScope.user.unname:"请登录" }&nbsp;&nbsp;&nbsp;<button id="exitBtn">退出或重新登录</button></p>
        </div>
    </div>
</div>
<!--头部end-->
<!--聊天区域开始-->
<div class="chatArea" id="chatArea">
    <div class="inChatArea">
        <div id="chatSidebar" class="chatSidebar">
            <h2 id="chatOnline">好友列表</h2>
            <ul id="chatUserList">
            	<c:forEach var="userlist" items="${userlists}">
         	    <li onclick='showChat("${userlist.unname}")'><img style="margin-top: 8px;width: 37px;height: 37px;" alt="用户图片" src="${ctx}/file/downloadShowFile.action?path=${userlist.uphoto}"/><span style="padding-bottom:10px;">&nbsp;&nbsp;&nbsp;&nbsp;${userlist.unname}</span></li>
                </c:forEach>
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
            <div class="down" hidden="hidden" id="chattext">
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
 
			//给退出聊天绑定事件
			$("#exitBtn").on("click",function(){
			    location.href="${pageContext.request.contextPath}/index.jsp";
			});
			
			function getLocalTime(nS) {     
			    return  new Date(parseInt(nS) * 1000).Format("yyyy-MM-dd hh:mm");  
			 }     
			Date.prototype.Format = function (fmt) { //author: meizz 
				    var o = {
				        "M+": this.getMonth() + 1, //月份 
				        "d+": this.getDate(), //日 
				        "h+": this.getHours(), //小时 
				        "m+": this.getMinutes(), //分 
				        "s+": this.getSeconds(), //秒 
				        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
				        "S": this.getMilliseconds() //毫秒 
				    };
				    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
				    for (var k in o)
				    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
				    return fmt;
				}

			
			var items = 0;var timer = null;//设置了一个定时器，items为总条数
			
 			//选择聊天对象
			function showChat(data){
 				$("#chattext").show();//打开输入框
 				clearInterval(timer);
				var friendName = data; // 获取跟谁聊天
				
				$("#nick").html('您正在与'+friendName+'聊天');
				var myName ='${sessionScope.user.unname}';//自己
				//alert(myName);
				$("#msg").val("");
				$.ajax({
					url:"${ctx}/userRestController/queryChat.action",
					data:{
						"send":myName, 
						"receive":friendName,
					},
					dataType:"json",
					type:"post",
					success:function(data){
						items=data.length;
						$("#contentUl").html('');
						for(var i=0;i<data.length; i++){
							var date = getLocalTime(data[i].date/1000);
							//alert(getLocalTime(date/1000));
							var content = data[i].content;
							var send = data[i].send;
							if(send == myName){//本人说的话
								
								$("#contentUl").append('<li style="text-align:right;">'+'<b>'+date+'</b>'+'<span>'+content+'</span>'+'<em>'+send+'</em>'+'</br>'+'</li>');
							}else{
								//好友说的话
								$("#contentUl").append('<li style="text-align:left;">'+'<b>'+date+'</b>'+'<em>'+send+'</em>'+'<span>'+content+'</span>'+'</br>'+'</li>');
							}	
						}
						$("#up").scrollTop(9999);
					}
				});
				//执行定时器
				timer = setInterval(function(){
					$.ajax({
						url:"${ctx}/userRestController/queryChat.action",
						data:{
							"send":myName, 
							"receive":friendName,
						},
						dataType:"json",
						type:"post",
						success:function(data){
							//进行对比
							if(data.length == items){
								console.log("无新消息");
							}else{
								//刷新消息
							
								$("#contentUl").html('');
								for(var i=0;i<data.length; i++){
									var date = getLocalTime(data[i].date/1000);
									//alert(getLocalTime(date/1000));
									var content = data[i].content;
									var send = data[i].send;
									if(send == myName){//本人说的话
										
										$("#contentUl").append('<li style="text-align:right;">'+'<b>'+date+'</b>'+'<span>'+content+'</span>'+'<em>'+send+'</em>'+'</br>'+'</li>');
									}else{
										//好友说的话
										$("#contentUl").append('<li style="text-align:left;">'+'<b>'+date+'</b>'+'<em>'+send+'</em>'+'<span>'+content+'</span>'+'</br>'+'</li>');
									}	
								}
								$("#up").scrollTop(9999);
							}
						}
						
					})
				},2000)
				
				
				//发送消息
				$("#sendBtn").on("click",function(){
					var msg=$("#msg").val();//内容
					var myName ='${sessionScope.user.unname}';//自己
					var friendName = data;
					//var date = new Date();
					//var year = date.getFullYear();
					//var month = date.getMonth()+1;
					//var day = date.getDay();
					//var Hour=date.getHours();
					//var Minute = date.getMinutes();
					//var Qdate = year+'-'+month+'-'+day+' '+Hour+':'+Minute;
					//var friendName = $("#nick").val();//昵称聊天
			        if(msg==""){
			        	alert("不可以发送空白信息");
			            return;
			        }else{
			        	$.ajax({
							url:"${ctx}/userRestController/insertChatMessage.action",
							data:{
								"send":myName, 
								"receive":friendName,
								"content":msg,
							},
							dataType:"text",
							type:"post",
							success:function(date){
							
								if(data == null){
									alert("发送失败");
								}else{
									//alert("发送成功");
									$("#contentUl").append('<li style="text-align:right;">'+'<b>'+date+'</b>'+'<span>'+msg+'</span>'+'<em>'+myName+'</em>'+'</br>'+'</li>');
									   //发送完消息，清空输入框
						            $("#msg").val("");
								}
							}
			            })
			        }
				});
			}

 
</script>
</html>