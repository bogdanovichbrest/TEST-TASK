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
	<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
		// set the visibility of an object to visible  
		function show(obj) {
			var theObj = document.getElementById(obj).style;
			theObj.display = "inline-block";
		}

		// set the visibility of an object to hidden  
		function hide(obj) {
			var theObj = document.getElementById(obj).style;
			theObj.display = "none";
		}
	</script>
	
	<form action="find" method="post">
	<div align = "center">
		<span>Find employees: </span>
		<p><input name="check" type="radio" onclick="hide('date2')">By date:      </p>
		<p><input name="check" type="radio" onclick="show('date2')" checked>Between dates:</p>
		<input id="date" name = "date" type="text" placeholder="yyyy-mm-dd"
			pattern="\d{4}-\d{2}-\d{2}"> <input id="date2" name = "date2" type="text"
			placeholder="yyyy-mm-dd" pattern="\d{4}-\d{2}-\d{2}"> <input
			type="submit" value="Find">
			</div>
	</form>

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
				<td><a href="?department=${employee.department.departmentID}">${employee.department.departmentName}</a></td>
				<td>${employee.salary}</td>
				<td>
					<div>
						<a
							href="edit?id=${employee.id}&firstname=${employee.firstName}&patronymic=${employee.patronymic}&lastname=${employee.lastName}&birthdate=${employee.birthDate}&departmentid=${employee.department.departmentID}&departmentname=${employee.department.departmentName}&salary=${employee.salary}"><img
							alt="Edit" src="pages/images/edit_icon_15.png" border="0"></a>
						<a href="delete?id=${employee.id}"><img alt="Delete"
							src="pages/images/delete_icon_15.png" border="0"></a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	${MiddleSalary}
	<div align="center">
		<p>
			Add new Employee <a href="edit"><img alt="add"
				src="pages/images/add_icon_15.png" border="0"></a>
		</p>
	</div>
</body>
</html>