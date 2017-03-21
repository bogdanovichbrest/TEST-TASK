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
	<table border="2" align="center">
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
				<td><a href = "employee/department/${employee.department.departmentID}">${employee.department.departmentName}</a></td>
				<td>${employee.salary}</td>
				<td>
					<div>
						<a
							href="employee/edit?id=${employee.id}&firstname=${employee.firstName}&patronymic=${employee.patronymic}&lastname=${employee.lastName}&birthdate=${employee.birthDate}&departmentid=${employee.department.departmentID}&departmentname=${employee.department.departmentName}&salary=${employee.salary}"><img
							alt="Edit" src="pages/images/edit_icon_15.png" border="0"></a>
						<a href="employee/delete?id=${employee.id}"><img alt="Delete"
							src="pages/images/delete_icon_15.png" border="0"></a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div align="center">
		<p>
			Add new Employee <a href="employee/edit"><img alt="add"
				src="pages/images/add_icon_15.png" border="0"></a>
		</p>
	</div>
</body>
</html>