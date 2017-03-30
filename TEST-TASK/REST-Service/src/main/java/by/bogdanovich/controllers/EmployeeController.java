package by.bogdanovich.controllers;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.bogdanovich.dao.AppDAO;
import by.bogdanovich.model.Employee;

/**
 * This is controller class to manage employees.
 * @author Alexander Bogdanovich 
 * @version 1.0.0
 */
@RestController("/employee")
@RequestMapping("/")
public class EmployeeController {
	@Autowired
	@Qualifier("dao")
	private AppDAO dao;
	@Autowired
	private Logger logger;
	
	/**
	 * Get list of employees.
	 * @param request
	 * @return List<Employee>
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public List<Employee> getAllEmployees(HttpServletRequest request)
	{
		logger.debug(this.getClass().getName()+" GET request received to URL " + request.getRequestURI());
		return dao.getAllEmployees();
		
	}
	
	/**
	 * Get employees in specified department(departmentID) from path variable.
	 * @param departmentID
	 * @param request
	 * @return List<Employee>
	 */
	@RequestMapping(value = "/employee/departmentID/{departmentID}", method = RequestMethod.GET)
	public List<Employee> getAllEmployeesInDepartment(@PathVariable Integer departmentID, HttpServletRequest request)
	{
		logger.debug(this.getClass().getName()+" GET request received to URL " + request.getRequestURI());
		return dao.getAllEmployeesInDepartment(departmentID);
		
	}
	
	/**
	 * Get all employees who was birth in specified(path variable) date.
	 * @param birthDate
	 * @param request
	 * @return List<Employee>
	 */
	@RequestMapping(value = "/employee/birthDate/{birthDate}", method = RequestMethod.GET)
	public List<Employee> getAllEgetAllEmployeesByDate(@PathVariable Date birthDate, HttpServletRequest request)
	{
		logger.debug(this.getClass().getName()+" GET request received to URL " + request.getRequestURI());
		return dao.getAllEmployeesByDate(birthDate);
		
	}
	
	/**
	 * Get all employees who was birth between specified(path variables) dates.
	 * @param date1
	 * @param date2
	 * @param request
	 * @return List<Employee>
	 */
	@RequestMapping(value = "/employee/betweenDates/{date1}/{date2}", method = RequestMethod.GET)
	public List<Employee> getAllEgetAllBetweenDates(@PathVariable Date date1, @PathVariable Date date2, HttpServletRequest request)
	{
		logger.debug(this.getClass().getName()+" GET request received to URL " + request.getRequestURI());
		return dao.getAllEmployeesBetwenDates(date1, date2);
	}
	
	/**
	 * Adds new employee from request body.
	 * @param employee
	 * @param request
	 */
	@RequestMapping(value = "employee/add", method = RequestMethod.POST)
	public void addEmployee(@RequestBody Employee employee, HttpServletRequest request)
	{
		logger.debug(this.getClass().getName()+" POST request received to URL " + request.getRequestURI());
		dao.addEmployee(employee);
	}
	
	/**
	 * Updatese information about employee from request body.
	 * @param employee
	 * @param request
	 */
	@RequestMapping(value = "employee/update", method = RequestMethod.PUT)
	public void updateEmployee(@RequestBody Employee employee, HttpServletRequest request)
	{
		logger.debug(this.getClass().getName()+" PUT request received to URL " + request.getRequestURI());
		dao.updateEmployee(employee);
	}
	
	/**
	 * Deletes employee by id(path variable).
	 * @param id
	 * @param request
	 */
	@RequestMapping(value = "employee/delete/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable Integer id, HttpServletRequest request)
	{
		logger.debug(this.getClass().getName()+" DELETE request received to URL " + request.getRequestURI());
	dao.deleteEmployee(id);	
	}
	
	public EmployeeController() {

	}

}
