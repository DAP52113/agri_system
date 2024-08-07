<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>聊天</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	 <link href="${pageContext.request.contextPath}/resources/newChat/styles/login.css" rel="stylesheet" type="text/css">
	  <script src="${pageContext.request.contextPath}/resources/newChat/Scripts/jquery-1.11.3.js" rel="script" type="text/javascript"></script>
	      <script src="${pageContext.request.contextPath}/resources/newChat/Scripts/login.js" rel="script" type="text/javascript"></script>
</head>
<body>
<div class="background-image">

</div>

<div id="content">

    <div class="login-content">
        <div class="login-head-img">

            <span>专家咨询系统在线咨询入口</span>
        </div>
        <form class="loginForm" action="${ctx}/newuserchat/loginchat.action" method="post">
            <table >
                <tr>
                    <td>
            <input name="username" type="text" placeholder="请输入您的咨询账号">
                    </td>
                </tr>
                <tr>
                    <td>
                  
        		<input name="userintroduce" type="text" placeholder="昵称">
                    </td>
                </tr>
                <tr>
                    <td class="button">
                        <span class="login">登录</span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>