package by.bogdanovich.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.bogdanovich.dao.AppDAO;
import by.bogdanovich.model.Employee;

@RestController("/employee")
@RequestMapping("/")
public class EmployeeController {
	@Autowired
	@Qualifier("dao")
	private AppDAO dao;
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public List<Employee> getAllEmployees()
	{
		return dao.getAllEmployees();
		
	}
	
	@RequestMapping(value = "/employee/departmentID/{departmentID}", method = RequestMethod.GET)
	public List<Employee> getAllEmployeesInDepartment(@PathVariable Integer departmentID)
	{
		return dao.getAllEmployeesInDepartment(departmentID);
		
	}
	
	@RequestMapping(value = "/employee/birthDate/{birthDate}", method = RequestMethod.GET)
	public List<Employee> getAllEgetAllEmployeesByDate(@PathVariable Date birthDate)
	{
		return dao.getAllEmployeesByDate(birthDate);
		
	}
	
	@RequestMapping(value = "/employee/betweenDates/{date1}/{date2}", method = RequestMethod.GET)
	public List<Employee> getAllEgetAllBetweenDates(@PathVariable Date date1, @PathVariable Date date2)
	{
		return dao.getAllEmployeesBetwenDates(date1, date2);
	}
	
	@RequestMapping(value = "employee/add", method = RequestMethod.POST)
	public void addEmployee(@RequestBody Employee employee)
	{
		dao.addEmployee(employee);
	}
	
	@RequestMapping(value = "employee/update", method = RequestMethod.PUT)
	public void updateEmployee(@RequestBody Employee employee)
	{
		dao.updateEmployee(employee);
	}
	
	@RequestMapping(value = "employee/delete/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable Integer id)
	{
	dao.deleteEmployee(id);	
	}
	
	public EmployeeController() {

	}

}
