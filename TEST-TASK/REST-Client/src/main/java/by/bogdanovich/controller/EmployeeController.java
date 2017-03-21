package by.bogdanovich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

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

	public EmployeeController() {

	}

}
