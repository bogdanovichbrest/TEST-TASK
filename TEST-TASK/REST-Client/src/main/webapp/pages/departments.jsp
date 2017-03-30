<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Departments</title>
</head>
<body>
	<div align="center">
		<a href=" ">Manage Employees</a>
	</div>
	<table border="2" align="center">
		<tr>
			<th>Department id</th>
			<th>Department name</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="department" items="${Departments}">
			<tr>
				<td>${department.departmentID}</td>
				<td>${department.departmentName}</td>
				<td>
					<div>
						<a
							href="managedepartments/editdepartment?departmentID=${department.departmentID}&departmentName=${department.departmentName}"><img
							alt="Edit" src="pages/images/edit_icon_15.png" border="0"></a>
						<a
							href="managedepartments/delete?departmentID=${department.departmentID}"><img
							alt="Delete" src="pages/images/delete_icon_15.png" border="0"></a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div align="center">
		<p>
			Add new Department <a href="managedepartments/editdepartment"><img
				alt="add" src="pages/images/add_icon_15.png" border="0"></a>
		</p>
	</div>

</body>
</html>