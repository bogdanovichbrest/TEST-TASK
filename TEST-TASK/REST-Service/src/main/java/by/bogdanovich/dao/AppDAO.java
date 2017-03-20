package by.bogdanovich.dao;

import java.sql.Date;
import java.util.List;

import by.bogdanovich.model.Department;
import by.bogdanovich.model.Employee;

public interface AppDAO {
	public List<Employee> getAllEmployees();

	public List<Employee> getAllEmployeesInDepartment(Integer departmentID);

	public List<Employee> getAllEmployeesByDate(Date date);

	public List<Employee> getAllEmployeesBetwenDates(Date date1, Date date2);

	public List<Department> getAllDepartments();

	public void addEmployee(Employee employee);

	public void updateEmployee(Employee employee);

	public void deleteEmployee(Employee employee);

	public void addDepartment(Department department);

	public void updateDepartment(Department department);

	public void deleteDepartment(Department department);
	
	public Employee findEmployeeById(Integer id);

	public Department findDepartmentById(Integer departmentId);
}
