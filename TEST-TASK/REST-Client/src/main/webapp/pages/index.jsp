<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Department App</title>
</head>
<body>
	<table border="2">
		<tr>
			<th>ID</th>
			<th>Firstname</th>
			<th>Patronymic</th>
			<th>Lastname
			<th>Birth Date</th>
			<th>Department</th>
			<th>Salary</th>
			<th><div>Actions</div></th>
		</tr>
		<c:forEach var="employee" items="${Employees}">
		<tr>
		<td>${employee.id}</td>
		<td>${employee.firstName}</td>
		<td>${employee.patronymic}</td>
		<td>${employee.lastName}</td>
		<td>${employee.birthDate}</td>
		<td>${employee.department.departmentName}</td>
		<td>${employee.salary}</td>
		<td><div>${employee.id}</div></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>