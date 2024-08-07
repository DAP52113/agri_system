<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>咨询聊天页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <c:forEach items="${messages }" var="message">
    <c:choose>
    <c:when test="${message.messagesender!=senderUser.userid }">
   
                <li>
                    <div class="current-chat-image">
                
                        <img src="${pageContext.request.contextPath}/resources/newChat/images/${reciverUser.userphoto }">
                    </div>
                    <div class="current-chat-box">
                        <div class="current-chat-info"><span>${reciverUser.username } ${message.messagedate }</span></div>
                        <div class="current-chat-content">
                           <span>
                               ${message.messageinfo }
                           </span>
                        </div>
                    </div>
                </li>
                </c:when>
                <c:otherwise>
                 <li>
                    <div class="current-chat-right-image">
                        <img src="${pageContext.request.contextPath}/resources/newChat/images/${senderUser.userphoto }">
                    </div>
                    <div class="current-chat-right-box">
                        <div class="current-chat-right-info"><span>${message.messagedate } &nbsp; &nbsp;${senderUser.username } </span></div>
                        <div class="current-chat-right-content">
                           <span> ${message.messageinfo }</span>
                        </div>
                    </div>
                </li>
                </c:otherwise>
                 </c:choose>
            </c:forEach>
  </body>
</html>
