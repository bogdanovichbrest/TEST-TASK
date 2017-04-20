<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add/Edit department</title>
</head>
<body>
	<table border="2" align="center">
		<tr>
			<th>Department ID</th>
			<th>Department Name</th>
			<th><div>Action</div></th>
		</tr>

		<tr>
			<form action="savedepartment" method="post">
				<td><input name="departmentID" type="text" readonly="readonly" size="4"
					value="${department.departmentID}"></td>
				<td><input name="departmentName" type="text" size="15"
					value="${department.departmentName}" required></td>
				<td>
					<div>

						<input type="image" alt="Save"
							src="../pages/images/save_icon_15.png" border="0">
					</div>
				</td>
			</form>
		</tr>
	</table>
</body>
</html>