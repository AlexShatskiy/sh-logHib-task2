<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>index</title>
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<form action="loginForm" method="get">
		<input type="submit" value="logIn" />
	</form>
	
	<form action="showAllUsers" method="get">
		<input type="submit" value="allUsers" />
	</form>
</body>
</html>