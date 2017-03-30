package by.bogdanovich.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import by.bogdanovich.model.Department;
import by.bogdanovich.model.Employee;

/**
 * This is controller class to manage employees.
 * @author Alexander Bogdanovich
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping("/")
public class EmployeeController {
	private RestTemplate template = new RestTemplate();
	@Autowired
	private Logger logger;

	/**
	 * Sends GET request to REST service to get employee list. If request parameter 'department' not null - gets all employees in specified department and calculate middle salary in this department.
	 * @param model
	 * @param request
	 * @return URL string
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllEmployees(ModelMap model, HttpServletRequest request) {
		if (request.getParameter("department") == null) {
			logger.debug(this.getClass().getName()+" GET request received to URL " + request.getRequestURI());
			Employee employees[] = template.getForObject("http://localhost:8080/REST-Service/employee",
					Employee[].class);
			model.addAttribute("Employees", employees);
		} else {
			logger.debug(this.getClass().getName()+" GET request received to URL "+request.getRequestURI() + " with parameter 'department' = " + request.getParameter("department"));
			Employee employees[] = template.getForObject(
					"http://localhost:8080/REST-Service/employee/departmentID/" + request.getParameter("department"),
					Employee[].class);
			model.addAttribute("Employees", employees);
			double middleSalary = 0;
			for (int i = 0; i < employees.length; i++) {
				middleSalary = middleSalary + employees[i].getSalary();
			}
			middleSalary = middleSalary / employees.length;
			model.addAttribute("MiddleSalary",
					"<div align=\"center\"><p>Middle salary: " + middleSalary + "</p></div>");
		}

		return "index";
	}

	/**
	 * Redirection to  'edit' page. If request parameter id not null - adds to model map Employee object with parameters from request.
	 * @param request
	 * @param model
	 * @return URL string
	 */
	@RequestMapping("/edit")
	public String editEmployee(HttpServletRequest request, ModelMap model) {
		logger.debug(this.getClass().getName()+" GET request received to URL "+request.getRequestURI());
		if (request.getParameter("id") != null) {
			Employee employee = new Employee();
			employee.setId(Integer.parseInt(request.getParameter("id")));
			employee.setFirstName(request.getParameter("firstname"));
			employee.setPatronymic(request.getParameter("patronymic"));
			employee.setLastName(request.getParameter("lastname"));
			employee.setBirthDate(Date.valueOf(request.getParameter("birthdate")));
			employee.setDepartment(new Department(Integer.parseInt(request.getParameter("departmentid")),
					request.getParameter("departmentname")));
			employee.setSalary(Integer.parseInt(request.getParameter("salary")));
			model.addAttribute("employee", employee);
		}
		Department departments[] = template.getForObject("http://localhost:8080/REST-Service/department",
				Department[].class);
		model.addAttribute("Departments", departments);

		return "edit";
	}

	/**
	 * Sends POST/PUT request to REST service to save/update employee. 
	 * @param request
	 * @return URL string
	 */
	@RequestMapping("/save")
	public String saveEmployee(HttpServletRequest request) {
		logger.debug(this.getClass().getName()+" GET request recieved to URL "+request.getRequestURI());
		Employee employee = new Employee();
		employee.setFirstName(request.getParameter("firstname"));
		employee.setPatronymic(request.getParameter("patronymic"));
		employee.setLastName(request.getParameter("lastname"));
		employee.setBirthDate(Date.valueOf(request.getParameter("birthdate")));
		employee.setDepartment(new Department(Integer.parseInt(request.getParameter("departmentid")), ""));
		employee.setSalary(Integer.parseInt(request.getParameter("salary")));
		if (request.getParameter("id") != "") {
			employee.setId(Integer.parseInt(request.getParameter("id")));
			template.put("http://localhost:8080/REST-Service/employee/update", employee, Employee.class);
		} else {
			template.postForObject("http://localhost:8080/REST-Service/employee/add", employee, Employee.class);
		}
		return "redirect:/";
	}

	/**
	 * Sends request to REST service to delete employee by ID (contains in request param)
	 * @param request
	 * @return URL string
	 */
	@RequestMapping("/delete")
	public String deleteEmployee(HttpServletRequest request) {
		logger.debug(this.getClass().getName()+" GET request recieved to URL "+request.getRequestURI());
		template.delete("http://localhost:8080/REST-Service/employee/delete/" + request.getParameter("id"));
		return "redirect:/";
	}

	/**
	 * Sends request to REST service to find employee(s) by birth date (between dates). Date(s) contains in request parameter(s).
	 * @param request
	 * @param model
	 * @return URL string
	 */
	@RequestMapping("/find")
	public String findEmployees(HttpServletRequest request, ModelMap model) {
		Employee employees[];
		String backToAllList = "<a href = \"\">Back to all employees list</a>";
		model.addAttribute("BackToAllList", backToAllList);
		if (request.getParameter("date2") != "") {
			logger.debug(this.getClass().getName()+" GET request recieved to URL with parameters 'date' = "+request.getParameter("date") + ", 'date2' = "+request.getParameter("date2"));
			employees = template.getForObject("http://localhost:8080/REST-Service/employee/betweenDates/"
					+ request.getParameter("date") + "/" + request.getParameter("date2"), Employee[].class);
			model.addAttribute("Employees", employees);
		} else {
			logger.debug(this.getClass().getName()+" GET request recieved to URL with parameter 'date' = "+request.getParameter("date"));
			employees = template.getForObject(
					"http://localhost:8080/REST-Service/employee/birthDate/" + request.getParameter("date"),
					Employee[].class);
			model.addAttribute("Employees", employees);
		}
		return "/index";
	}

	public EmployeeController() {

	}

}
