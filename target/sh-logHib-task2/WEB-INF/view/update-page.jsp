
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>loginIn</title>
</head>
<body>
	<form:form action="updateUser" modelAttribute="loginData">
	
		Login: <form:input placeholder="login" path="login"/>
		<form:errors path="login"/>

		<br />
		<br />
		
		OldPassword: <form:input placeholder="password" path="password"/>

		<br />
		<br />
	
		NewPassword: <form:password placeholder="password" path="password" />
        <form:errors path="password"/>
        <br />
		<br />
		
		Name: <form:input placeholder="name" path="name" />
		<form:errors path="name"/>
		<br />
		<br />
		
		Surname: <form:input placeholder="surname" path="surname" />

		<br />
		<br />

		Age: <form:input placeholder="age" path="age" />
        <form:errors path="age"/>
		<br />
		<br />
		From Gender:
	
        <form:radiobuttons path="gender"
			items="${genderOptions}" />
		<br />
		<br />
 
		From Country:
		
		<form:select path="country">
			<form:options items="${countryOptions}" />
		</form:select>

		<br />
		<br />
		<input type="hidden" name="userId" value="${loginData.getUserId()}">
		<input type="submit" value="Submit" />

	</form:form>
</body>
</html>