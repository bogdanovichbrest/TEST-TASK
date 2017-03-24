package by.bogdanovich.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import by.bogdanovich.model.Department;
import by.bogdanovich.model.Employee;

@Controller
@RequestMapping("/")
public class EmployeeController {
	private RestTemplate template = new RestTemplate();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllEmployees(ModelMap model, HttpServletRequest request)
	{
		if(request.getParameter("department")==null)
		{
		Employee employees[] = template.getForObject("http://localhost:8080/REST-Service/employee", Employee[].class);
		model.addAttribute("Employees", employees);
		}
		else
		{
			//model.clear();
			Employee employees[] = template.getForObject("http://localhost:8080/REST-Service/employee/departmentID/" + request.getParameter("department"), Employee[].class);
			model.addAttribute("Employees", employees);
			double middleSalary = 0;
			for(int i = 0; i < employees.length; i++)
			{
				middleSalary = middleSalary + employees[i].getSalary();
			}
			middleSalary = middleSalary/employees.length;
			model.addAttribute("MiddleSalary", "<div align=\"center\"><p>Middle salary: "+middleSalary+"</p></div>");
		}
		return "index";
	}
	
	@RequestMapping("/edit")
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
	
	@RequestMapping("/save")
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
		return "redirect:/";
	}
	
	@RequestMapping("/delete")
	public String deleteEmployee(HttpServletRequest request)
	{
		template.delete("http://localhost:8080/REST-Service/employee/delete/" + request.getParameter("id"));
		return "redirect:/";
	}
	
	@RequestMapping("/find")
	public String findEmployees(HttpServletRequest request, ModelMap model)
	{
		Employee employees[];
		if(request.getParameter("date2")!="")
		{
			employees = template.getForObject("http://localhost:8080/REST-Service/employee/betweenDates/"+request.getParameter("date")+"/"+request.getParameter("date2"), Employee[].class);
			model.addAttribute("Employees", employees);
		}
		else
		{
			employees = template.getForObject("http://localhost:8080/REST-Service/employee/birthDate/"+request.getParameter("date"), Employee[].class);
			model.addAttribute("Employees", employees);
		}
		return "redirect:/";
	}
	

	public EmployeeController() {

	}

}
