<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
</head>
<body>

<form action="${ctx}/androidaction/androidlogin.action" method="post">
<br>
	name:<input type="text" name="unname">
<br>password:<input type="text" name="password">
<br><input type="submit" value="login">
</form>
</body>
</html>