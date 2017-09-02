<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>main</title>
	<script type="text/javascript">
		function really(){
			var change = confirm('delete?');
			if (change == false){
				window.history.back();
			}
		}
	</script>
</head>
<body>

    <a href="index.jsp">index</a>

	<form action="showAllUsers" method="get">
		<input type="submit" value="allUsers" />
	</form>
	
 	<h2 ><b>${messageUpdate}</b></h2>
 

	<h2 ><b>${messageDelete}</b></h2>
		
	<c:if test="${not empty loginDatas}">
		<table>
			<tr>
				<th>login</th>
				<th>password</th>
				<th>name</th>
				<th>surname</th>
				<th>age</th>
				<th>gender</th>
				<th>country</th>
			</tr>
			<c:forEach items="${loginDatas}" var="loginData">
				<tr> 
					<td>${loginData.getLogin()}</td>
					<td>${loginData.getPassword()}</td> 
					<td>${loginData.getName()}</td> 
					<td>${loginData.getSurname()}</td> 
					<td>${loginData.getAge()}</td> 
					<td>${loginData.getGender()}</td> 
					<td>${loginData.getCountry()}</td>
					<td>
						<form action="deleteUser" method="get">
							<input type="hidden" name="userId" value="${loginData.getUserId()}">
							<input type="submit" value="delite" onclick="really()"/>
						</form>
					</td>
					<td>
						<form action="updateForm" method="get">
							<input type="hidden" name="userId" value="${loginData.getUserId()}">
							<input type="submit" value="update" />
						</form>
					</td> 
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${not empty loginData}">	
	    <h2>User added: </h2> ${loginData.login}
	</c:if>
</body>
</html>