package by.bogdanovich.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;import org.springframework.beans.factory.support.GenericTypeAwareAutowireCandidateResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import by.bogdanovich.model.Department;
import by.bogdanovich.model.Employee;

@Controller("/")
@RequestMapping("/")
public class EmployeeController {
	private RestTemplate template = new RestTemplate();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllEmployees(ModelMap model)
	{
		Employee employees[] = template.getForObject("http://localhost:8080/REST-Service/employee", Employee[].class);
		model.addAttribute("Employees", employees);
		return "index";
	}
	
	@RequestMapping("employee/edit")
	public String editEmployee(HttpServletRequest request, ModelMap model)
	{
		if(request.getParameter("id")!=null)
		{
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(request.getParameter("id")));
		employee.setFirstName(request.getParameter("firstname"));
		employee.setPatronymic(request.getParameter("patronymic"));
		employee.setLastName(request.getParameter("lastname"));
		employee.setBirthDate(Date.valueOf(request.getParameter("birthdate")));
		employee.setDepartment(new Department(Integer.parseInt(request.getParameter("departmentid")), request.getParameter("departmentname")));
		employee.setSalary(Integer.parseInt(request.getParameter("salary")));
		model.addAttribute("employee", employee);
		}
		Department departments[] = template.getForObject("http://localhost:8080/REST-Service/department", Department[].class);
		model.addAttribute("Departments",departments);
		
		return "edit";
	}
	
	@RequestMapping("employee/save")
	public String saveEmployee(HttpServletRequest request)
	{
		Employee employee = new Employee();
		employee.setFirstName(request.getParameter("firstname"));
		employee.setPatronymic(request.getParameter("patronymic"));
		employee.setLastName(request.getParameter("lastname"));
		employee.setBirthDate(Date.valueOf(request.getParameter("birthdate")));
		employee.setDepartment(new Department(Integer.parseInt(request.getParameter("departmentid")),""));
		employee.setSalary(Integer.parseInt(request.getParameter("salary")));
		if(request.getParameter("id")!="")
		{
			employee.setId(Integer.parseInt(request.getParameter("id")));
			template.put("http://localhost:8080/REST-Service/employee/update", employee, Employee.class);
		}
		else
		{
			template.postForObject("http://localhost:8080/REST-Service/employee/add", employee, Employee.class);
		}
		return "redirect: ../";
	}
	
	@RequestMapping("employee/delete")
	public String deleteEmployee(HttpServletRequest request)
	{
		template.delete("http://localhost:8080/REST-Service/employee/delete/" + request.getParameter("id"));
		return "redirect: ../";
	}
	
	@RequestMapping("employee/department/{departmentID}")
	public String getAllEmployeesInDepartment(@PathVariable Integer departmentID, ModelMap model)
	{
		Employee employees[] = template.getForObject("http://localhost:8080/REST-Service/employee/departmentID/" + departmentID, Employee[].class);
		model.addAttribute("Employees", employees);
		return "index";
		
	}

	public EmployeeController() {

	}

}
