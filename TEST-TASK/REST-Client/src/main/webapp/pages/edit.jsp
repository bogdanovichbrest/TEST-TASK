<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add/Edit employee</title>
</head>
<body>
	<table border="2" align="center">
		<tr>
			<th>ID</th>
			<th>Firstname</th>
			<th>Patronymic</th>
			<th>Lastname</th>
			<th>Birth Date</th>
			<th>Department</th>
			<th>Salary</th>
			<th><div>Action</div></th>
		</tr>

		<tr>
			<form action="save" method="post">
				<td><input name="id" type="text" readonly="readonly" size="4"
					value="${employee.id}"></td>
				<td><input name="firstname" type="text" size="15"
					value="${employee.firstName}" required></td>
				<td><input name="patronymic" type="text" size="15"
					value="${employee.patronymic}"></td>
				<td><input name="lastname" type="text" size="15"
					value="${employee.lastName}" required></td>
				<td><input name="birthdate" type="text" placeholder = "yyyy-mm-dd"
					pattern="\d{4}-\d{2}-\d{2}" size="10" value="${employee.birthDate}"
					required></td>
				<td><select name="departmentid">
						<c:forEach var="department" items="${Departments}">
							<option value=${department.departmentID}>${department.departmentName}</option>
						</c:forEach>
				</select></td>
				<td><input name="salary" type="text" pattern="[0-9]{1,10}"
					size="10" value="${employee.salary}" required></td>
				<td>
					<div>

						<input type="image" alt="Save"
							src="/pages/images/save_icon_15.png" border="0">
					</div>
				</td>
			</form>
		</tr>
	</table>
</body>
</html>