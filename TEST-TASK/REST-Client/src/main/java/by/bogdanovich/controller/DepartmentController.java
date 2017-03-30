package by.bogdanovich.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import by.bogdanovich.model.Department;

/**
 * This is controller class to manage departments.
 * @author Alexander Bogdanovich
 * @version 1.0.0
 *
 */
@Controller("/managedepartments")
@RequestMapping("/")
public class DepartmentController {
	@Autowired
	private Logger logger;
	private RestTemplate template = new RestTemplate();
	
	/**
	 * Get list of departments from REST-service
	 * @param request
	 * @param model
	 * @return URL string
	 */
	@RequestMapping("/managedepartments")
	public String getAllDepartments(HttpServletRequest request, ModelMap model)
	{
		logger.debug(this.getClass().getName()+" GET request recieved to URL "+request.getRequestURI());
		Department[] departments = template.getForObject("http://localhost:8080/REST-Service/department", Department[].class);
		model.addAttribute("Departments", departments);
		return "departments";
	}
	
	/**
	 * Sends request to REST service to delete department by ID (contains in request param)
	 * @param request
	 * @return URL string
	 */
	@RequestMapping("/managedepartments/delete")
	public String deleteDepartment(HttpServletRequest request)
	{
		logger.debug(this.getClass().getName()+" GET request recieved to URL "+request.getRequestURI()+" with parameter 'departmentID' = "+request.getParameter("departmentID"));
		template.delete("http://localhost:8080/REST-Service/department/"+request.getParameter("departmentID"));
		return "redirect:/managedepartments";
	}
	
	/**
	 * Redirection to  'edit department' page. If request parameter departmentID not null - adds to model map Department object with parameters from request.
	 * @param request
	 * @param model
	 * @return URL string
	 */
	@RequestMapping("managedepartments/editdepartment")
	public String editDepartment(HttpServletRequest request, ModelMap model)
	{
		logger.debug(this.getClass().getName()+" GET request recieved to URL "+request.getRequestURI());
		if(request.getParameter("departmentID")!=null)
		{
			Department department = new Department();
			department.setDepartmentID(Integer.parseInt(request.getParameter("departmentID")));
			department.setDepartmentName(request.getParameter("departmentName"));
			model.addAttribute("department", department);
		}
		return "editdepartment";
	}
	
	/**
	 * Sends POST/PUT request to REST service to save/update department. 
	 * @param request
	 * @return URL string
	 */
	@RequestMapping("managedepartment/savedepartment")
	public String saveDepartment(HttpServletRequest request)
	{
		logger.debug(this.getClass().getName()+" GET request recieved to URL "+request.getRequestURI());
		Department department = new Department();
		department.setDepartmentName(request.getParameter("departmentName"));
		if(request.getParameter("departmentID")!=null)
		{
			department.setDepartmentID(Integer.parseInt(request.getParameter("departmentID")));
			template.put("http://localhost:8080/REST-Service/department", department, Department.class);
		}
		else
		{
			template.postForObject("http://localhost:8080/REST-Service/department", department, Department.class);
		}
		return "redirect:/managedepartments";
	}

	public DepartmentController() {
		// TODO Auto-generated constructor stub
	}

}
