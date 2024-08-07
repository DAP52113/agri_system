<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html>
<head>
<title>聊天页面</title>

<link href="${pageContext.request.contextPath}/resources/newChat/layui/css/layui.css"
	type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/newChat/styles/main.css"
	rel="stylesheet" type="text/css">
	

<script
	src="${pageContext.request.contextPath}/resources/newChat/Scripts/jquery-1.11.3.js"
	rel="script" type="text/javascript"></script>
	

<script src="${pageContext.request.contextPath}/resources/newChat/Scripts/iscroll.js"
	rel="script" type="text/javascript"></script>
	
<script type="text/javascript">
/**
 * Created by Simple on 2017/6/19.
 */
$(document)
		.ready(
				function() {

					Date.prototype.Format = function(fmt) { // author: meizz
						var o = {
							"M+" : this.getMonth() + 1, // 月份
							"d+" : this.getDate(), // 日
							"h+" : this.getHours(), // 小时
							"m+" : this.getMinutes(), // 分
							"s+" : this.getSeconds(), // 秒
							"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
							"S" : this.getMilliseconds()
						// 毫秒
						};
						var userId = $(".loginId").val();
						if (/(y+)/.test(fmt))
							fmt = fmt.replace(RegExp.$1,
									(this.getFullYear() + "")
											.substr(4 - RegExp.$1.length));
						for ( var k in o)
							if (new RegExp("(" + k + ")").test(fmt))
								fmt = fmt
										.replace(
												RegExp.$1,
												(RegExp.$1.length == 1) ? (o[k])
														: (("00" + o[k])
																.substr(("" + o[k]).length)));
						return fmt;
					}

					var websocket = null;
					// 判断当前浏览器是否支持WebSocket
					if ('WebSocket' in window) {
						var loginId = $('#loginId').val();
					
						websocket = new WebSocket(
								"ws://192.168.90.208:8080/Agri_experts/webServer/"
										+ loginId);
					
					} else {
						alert("对不起！你的浏览器不支持webSocket")
					}
					// 连接发生错误的回调方法
					///websocket.onerror = function() {
						
					//	alert("发生错误");
				//	};
					// 连接成功建立的回调方法
					websocket.onopen = function(event) {
						/*
						 * $.post("getChatInfo.action", { "loginId" :
						 * $(".loginId").val() }, function(data) {
						 * 
						 * for ( var i = 0; i < data.length; i++) { var $li =
						 * $(".chat-list ul li[data-id='" + data[i].userId +
						 * "']"); if ($li.length == 0) { setChatList(data[i]); }
						 * else { var obj = $li.find(".cue-circle2");
						 * obj.prev().html(data[i].messageInfo);
						 * changeCueCircle(obj, data[i].count); } } }, "json")
						 */
						getOnline();
						noticeFriends();
					};
					function getOnline() {
						var userId = $('#loginId').val();
						
					//	$.ajax({
					//		type:"post",
					//		url:"online.action",
					//		data:{
					//			"loginId" : userId
					//		},
					//	success:function(data){
					//		console.log(data);
					//	}
					//	});
					
						$.ajax({
							url:"online.action",
							type:"post",
							data:{
								"loginId": userId
							},
							success:function(data){
								alert(data);
							}
					});
					}
						//$.post("online.action", {
						//	"loginId" : userId
						/*})
							 * , function(data) {
							 * 
							 * $("list ul
							 * li[data-id="+data.messageInfo+"]").find("img").removeClass("gray"); },
							 * "json")
							 */
						
					//}
					function noticeFriends() {
						var userId = $('#loginId').val();
						$.post("noticeFriends.action", {
							"loginId" : userId
						})
					}
					function outLine() {
						var userId = $('#loginId').val();
			//	$.ajax({
				//			url:"outline.action",
			//				type:"post",
				//			data:{
			//	//				"loginId": userId
			//			},
				//	});
						
						
						$.post("outline.action", {
							"loginId" : userId
						})
					}
					function setChatList(data) {
						var str = '<li data-id="' + data.userid + '">'
								+ '<div class="chat-list-friend-image">'
								+ '<img src="images/' + data.userphoto
								+ '"></div>'
								+ '<div class="chat-list-friend-info">'
								+ '<span class="chat-list-friend-name">'
								+ data.username + '</span>'
								+ '<span class="friend-list-friend-message">'
								+ data.messageinfo
								+ '</span><span class="cue-circle2">'
								+ data.count + '</span></div></li>'
						$(".chat-list ul").append(str);
						setTimeout(function() {
							chatScroll.refresh();
						}, 0);

						changeCueCircle(null, 0);
					}
					// 接收到消息的回调方法
					websocket.onmessage = function(event) {
						setMessageInnerHTML($.parseJSON(event.data));

					};
					
					//websocket.onclose = function (e) {
						//  console.log('websocket 断开: ' + e.code + ' ' + e.reason + ' ' + e.wasClean)
					//	  console.log(e);
					//	  outLine();
					//	};
					// 连接关闭的回调方法
					//websocket.onclose = function() {
						// setMessageInnerHTML("断开连接");
				//		console.log('websocket 断开：' + e.code + ' ' + e.reason + ' ' + e.wasClean)
					//	outLine();
				//	};
					// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，
					// 防止连接还没断开就关闭窗口，server端会抛异常。
					
					window.onbeforeunload = function(){
						websocket.close();
						}
					

					// 将消息显示在网页上
					function setMessageInnerHTML(data) {

						if (data.messagetype == '1') {
							if (data.messagesender == $(".chat-body")
									.attr("id")) {
								setTextByLeft(data.messageinfo,
										data.messagedate);
								$.post("updateMessage.action", {
									"messagereciver" : $('#loginId').val(),
									"messagesender" : $(".chat-body")
											.attr("id")
								});
								var obj = $(
										".chat-list ul li[data-id='"
												+ data.messagesender + "']")
										.find(".cue-circle2");
								obj.prev().html(data.messageinfo);
							} else {
								if ($(".chat-list ul li[data-id='"
										+ data.messagesender + "']").length < 1) {
									var friendObj = $("#list ul li[data-id='"
											+ data.messagesender + "']");

									var mydata = {
										"userId" : data.messagesender,
										"username" : friendObj.find(
												".friend-name").text(),
										"userphoto" : friendObj.find(
												".userImage").val(),
										"messageinfo" : "",
										"count" : 0
									};

									setChatList(mydata);
								}
								var obj = $(
										".chat-list ul li[data-id='"
												+ data.messagesender + "']")
										.find(".cue-circle2");
								var count = parseInt(obj.html()) + 1;
								obj.prev().html(data.messageinfo);
								changeCueCircle(obj, count);
							}
							$(".cue")[0].play();
						}
						if (data.messagetype == '2') {
							$("#list ul li[data-id=" + data.messageinfo + "]")
									.find("img").removeClass("gray");
						}
						if (data.messagetype == '3') {
							alert(data.messageinfo);
							$("#list ul li[data-id=" + data.messageinfo + "]")
									.find("img").addClass("gray");
						}
					}
					;
					// 关闭连接
					function closeWebSocket() {
						websocket.close();
					}

					// $("#chat-body-content").load("chatlist.action");
					new IScroll('#list', {
						scrollbars : 'custom',
						mouseWheel : true,
						interactiveScrollbars : true,
						shrinkScrollbars : 'scale',
						fadeScrollbars : true
					});
					// chat-body-content
					var myScroll = new IScroll('#chat-body-content', {
						scrollbars : 'custom',
						mouseWheel : true,
						interactiveScrollbars : true,
						shrinkScrollbars : 'scale',
						fadeScrollbars : true
					});
					var chatScroll = new IScroll('#chat-list', {
						scrollbars : 'custom',
						mouseWheel : true,
						interactiveScrollbars : true,
						shrinkScrollbars : 'scale',
						fadeScrollbars : true
					});
					function setTextByLeft(message, time) {
						var str = ' <li><div class="current-chat-image">'
								+ '<img src="'
								+ $(".chat-friend-image img")[0].src
								+ '"></div>' + '<div class="current-chat-box">'
								+ ' <div class="current-chat-info"><span>'
								+ time + ' &nbsp; &nbsp;'
								+ $("chat-friend-name span").html()
								+ ' </span></div>'
								+ '<div class="current-chat-content">'
								+ ' <span>' + message
								+ '</span></div></div></li>'
						$("#chat-body-content ul").append(str);
						setTimeout(
								function() {
									myScroll.refresh();
									myScroll.scrollTo(0, myScroll.maxScrollY,
											500, null);
								}, 0);
					}
					var layedit, layer_index;
					layui.use('layedit', function() {
						layedit = layui.layedit;
						layer_index = layedit.build('chat-edit', {
							height : 110,
							tool : [ 'face' ]
						}); // 建立编辑器
					});
					$(".chat-submit").click(function() {
						var content = layedit.getContent(layer_index);
						var time = getTime();
						var userId = $('#loginId').val();
						
						var reciver = $(".chat-body").attr("id");
						var type = 1;
						$('#LAY_layedit_1').contents().find('body').html('');
						setTextByRight(content, time)
						updateChatInfo(content);
						$.post("sendMessage.action", {
							"messagedate" : time,
							"messageinfo" : content,
							"messagesender" : userId,
							"messagereciver" : reciver,
							"messagetype" : type
						});
					})
					function updateChatInfo(content) {
						if ($(".chat-list ul li[data-id='"
								+ $(".chat-body").attr("id") + "']").length < 1) {
							var friendObj = $("#list ul li[data-id='"
									+ $(".chat-body").attr("id") + "']");

							var mydata = {
								"userid" : $(".chat-body").attr("id"),
								"username" : friendObj.find(".friend-name")
										.text(),
								"userphoto" : friendObj.find(".userImage")
										.val(),
								"messageinfo" : content,
								"count" : 0
							};

							setChatList(mydata);
						}

					}

					function getTime() {
						var date = new Date().Format("yyyy-MM-dd hh:mm:ss");
						return date;
					}
					function setTextByRight(content, time) {
						var str = ' <li><div class="current-chat-right-image">'
								+ '<img src="images/'
								+ $(".userphoto").val()
								+ '"></div>'
								+ '<div class="current-chat-right-box">'
								+ ' <div class="current-chat-right-info"><span>'
								+ time + ' &nbsp; &nbsp;'
								+ $(".userName").html() + ' </span></div>'
								+ '<div class="current-chat-right-content">'
								+ ' <span>' + content
								+ '</span></div></div></li>'
						$("#chat-body-content ul").append(str);
						setTimeout(
								function() {
									myScroll.refresh();
									myScroll.scrollTo(0, myScroll.maxScrollY,
											500, null);
								}, 0);
					}
					$(".list li").each(function() {
						$(this).dblclick(function() {
							var image = $(this).find("img").attr("src");
							var name = $(this).find(".friend-name").text();
							var reciverId = $(this).attr("data-id");
							alertChatBody(image, name, reciverId)
						})
					})
					$(".chat-list ul").on(
							"dblclick",
							'li',
							function() {

								var image = $(this).find("img").attr("src");
								var name = $(this).find(
										".chat-list-friend-name").text();
								var reciverId = $(this).attr("data-id");
								alertChatBody(image, name, reciverId)
							})
					$(".chat-item ul").on(
							"click",
							"li",
							function() {
								var image = $(this).find("img").attr("src");
								var name = $(this).find(
										".chat-item-friend-name span").text();
								var reciverId = $(this).attr("data-id");
								alertChatBody(image, name, reciverId);
							})
					function alertChatBody(image, name, reciverId) {

						$(".chat-body").attr("id", reciverId);

						$(".chat-item ul li").removeClass("chat-item-active");
						if ($(".chat-item ul li[data-id=" + reciverId + "]").length == 0) {
							$(".chat-item ul")
									.append(
											' <li data-id="'
													+ reciverId
													+ '"  class="chat-item-active">'
													+ '<div class="chat-item-friend-image"><img src="'
													+ image
													+ '" ></div>'
													+ '<div class="chat-item-friend-name"><span>'
													+ name + '</span></div>'
													+ '</li>');
						} else {
							$(".chat-item ul li[data-id=" + reciverId + "]")
									.addClass("chat-item-active");
						}
						if ($(".chat-item ul li").length > 1) {
							$(".chat-item").show();
							$("#chat-content").width(800);
						} else {
							$(".chat-item").hide();
							$("#chat-content").width(600)
						}
						$(".chat-head").find(".chat-friend-image img")[0].src = image;
						$(".chat-head").find(".chat-friend-name span").text(
								name);
						if (!$("#chat-content").is("visiable")) {
							$("#chat-content").show();

						}

						$("#chat-body-content ul").load(
								"messages.action",
								{
									messagesender : $('#loginId').val(),
									messagereciver : reciverId
								},
								function() {
									setTimeout(
											function() {
												myScroll.refresh();
												myScroll.scrollTo(0,
														myScroll.maxScrollY,
														500, null);
											}, 0);
								})
						$.post("updateMessage.action", {
							"messagereciver" : loginId,
							"messagesender" : reciverId
						});
						var obj = $(
								".chat-list ul li[data-id='" + reciverId + "']")
								.find(".cue-circle2");
						changeCueCircle(obj, 0);

					}
					$(".content-head ul li")
							.each(
									function() {
										$(this)
												.click(
														function() {
															var index = $(
																	".content-head ul li")
																	.index(
																			$(this));
															
															$(
																	".active-box span")
																	.eq(index)
																	.addClass(
																			"active")
																	.siblings()
																	.removeClass(
																			"active");
															$(".content-body")
																	.children()
																	.eq(index)
																	.show()
																	.siblings()
																	.hide();
										
															if(index==0){
																$(".content-body")
																.children().eq(index).find("ul li .gray").each(function() {
																	$(this).parent().parent().appendTo($(".content-body")
																			.children().eq(index).find("ul"))
																})
																
																
															}
														})
									})

					function changeCueCircle(obj, count) {
						if (obj != null) {
							obj.html(count);
							if (count == 0) {
								obj.hide();
							} else {
								obj.show();
							}
						}
						var total = 0;
						$(".cue-circle2").each(function() {
							if ($(this).html() == 0) {
								$(this).hide();
							}
							total += parseInt($(this).html());
						})
						$(".cue-circle").html(total);
						if ($(".cue-circle").html() == 0) {

							$(".cue-circle").hide();
						} else {
							$(".cue-circle").show();
						}

					}
					changeCueCircle(null, 0);
					$(".chat-close")
							.click(
									function() {
										if ($(".chat-item").is(":visible")) {
											var $removeObj = $(".chat-item ul li[data-id="
													+ $(".chat-body")
															.attr("id") + "]");
											var obj = $removeObj.prev();
											if (obj.length > 0) {
												obj.click();
											} else {
												$removeObj.next().click();
											}

											$removeObj.remove();
											if ($(".chat-item ul").children().length == 1) {
												$(".chat-item").hide();
												$("#chat-content").width(600);
											}

										} else {
											$(".chat-item ul").html("");
											$("#chat-content").hide();
											$(".chat-body").attr("id", '');
										}
									})
				})
		


</script>	
	
	<!-- 
<script src="${pageContext.request.contextPath}/resources/newChat/Scripts/main.js"
	rel="script" type="text/javascript"></script>
	 -->
<script
	src="${pageContext.request.contextPath}/resources/newChat/layui/lay/dest/layui.all.js"
	type="text/javascript"></script>
</head>
<body>
	<audio class="cue"
		src="${pageContext.request.contextPath}/resources/newChat/audio/cue.mp3" preload="auto"></audio>
	<input id="loginId" name="loginId"   type="hidden" value="${userchat.userid}">
	<div id="chat-content" class="shadow" hidden>
		<div class="chat-item">
			<ul>

			</ul>
		</div>

		<div class="chat-body">

			<div class="chat-head">
				<div class="chat-friend-image">
					<img src="${pageContext.request.contextPath}/resources/newChat/images/001.jpg">
				</div>
				<div class="chat-friend-name">
					<span>恋旧</span>
				</div>
			</div>
			<div class="chat-body-content" id="chat-body-content">
				<ul>

				</ul>
			</div>
			<div class="chat-edit" id="chat-edit" contenteditable="true"></div>
			<div class="chat-button">
				<span class="chat-close">关闭</span><span class="chat-submit">发送</span>
			</div>
		</div>

	</div>
	<div id="content" class="shadow">
		<div class="content-head">
			<input type="hidden" class="userphoto" value="${userchat.userphoto }">
			<span class="userName">${userchat.username }</span> <span
				class="userAutoGraph">${userchat.userintroduce }</span>
			<ul>
				<li><img src="${pageContext.request.contextPath}/resources/newChat/images/01.png"></li>
				<li><img src="${pageContext.request.contextPath}/resources/newChat/images/03.png"></li>
				<li><img src="${pageContext.request.contextPath}/resources/newChat/images/02.png"><span class="cue-circle">2</span></li>
			</ul>
			<div class="active-box">
				<span></span><span></span><span  class="active"></span>
			</div>
		</div>
		
		<div class="content-body">
			<div class="list" id="list"  hidden>
				<ul>
					<c:forEach items="${friendlist}" var="userchat">
						<li data-id="${userchat.userid }">
							<div class="friend-image">
								<input type="hidden" class="userImage" value="${userchat.userphoto }">
								<img class="gray" src="${pageContext.request.contextPath}/resources/newChat/images/${userchat.userphoto }">
							</div>
							<div class="friend-info">
								<span class="friend-name">${userchat.username }</span>
								
								 <span
									class="friend-autoGraph">${userchat.userintroduce }</span>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div  class="many-chat" id="many-chat"  hidden>
				<span style="margin:30px 30px;font-size:24px;display: inline-block;">暂时未开发</span>
			</div>
			<div class="chat-list" id="chat-list">
				<ul>
					<c:forEach items="${chatFriends }" var="chatFriend">
						<li  data-id="${chatFriend.userid }">
							<div class="chat-list-friend-image">
								<img src="${pageContext.request.contextPath}/resources/newChat/images/${chatFriend.userphoto }">
							</div>
							<div class="chat-list-friend-info">
								<span class="chat-list-friend-name">${chatFriend.username }</span>
								 <span class="friend-list-friend-message">${chatFriend.messageInfo }
								 </span>
								 <span class="cue-circle2">${chatFriend.count }</span>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>