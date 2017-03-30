package by.bogdanovich.dao;

import java.sql.Date;
import java.util.List;

import by.bogdanovich.model.Department;
import by.bogdanovich.model.Employee;

public interface AppDAO {
	/**
	 * Gets list of employees from database.
	 * @return List<Employee>
	 */
	public List<Employee> getAllEmployees();

	/**
	 * Gets list of employees in specified department(departmentID) from database.
	 * @param departmentID
	 * @return List<Employee>
	 */
	public List<Employee> getAllEmployeesInDepartment(Integer departmentID);

	/**
	 * Gets all employees who was birth in specified date from database.
	 * @param date
	 * @return List<Employee>
	 */
	public List<Employee> getAllEmployeesByDate(Date date);

	/**
	 * Gets all employees who was birth between specified dates from database.
	 * @param date1
	 * @param date2
	 * @return List<Employee>
	 */
	public List<Employee> getAllEmployeesBetwenDates(Date date1, Date date2);

	/**
	 * Gets all departments list from database.
	 * @return List<Department>
	 */
	public List<Department> getAllDepartments();

	/**
	 * Inserts new employee record into database.
	 * @param employee
	 */
	public void addEmployee(Employee employee);

	/**
	 * Updates employee record in database.
	 * @param employee
	 */
	public void updateEmployee(Employee employee);

	/**
	 * Deletes employee record from database by specified id.
	 * @param id
	 */
	public void deleteEmployee(Integer id);

	/**
	 * Inserts new department record into database.
	 * @param department
	 */
	public void addDepartment(Department department);

	/**
	 * Updates department record in database.
	 * @param department
	 */
	public void updateDepartment(Department department);

	/**
	 * Deletes department record from database.
	 * @param department
	 */
	public void deleteDepartment(Department department);
	
	/**
	 * Finds employee record  in database by specified id.
	 * @param id
	 * @return employee
	 */
	public Employee findEmployeeById(Integer id);

	/**
	 * Finds department record  in database by specified departmentID.
	 * @param departmentId
	 * @return department 
	 */
	public Department findDepartmentById(Integer departmentId);
}
