package by.bogdanovich.controllers;

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
import by.bogdanovich.model.Department;

/**
 * This is controller class to manage departments.
 * @author Alexander Bogdanovich 
 * @version 1.0.0
 */
@RestController("/department")
@RequestMapping("/")
public class DepartmentControler {
	@Autowired
	@Qualifier("dao")
	private AppDAO dao;
	@Autowired
	private Logger logger;

	/**
	 * Get list of departments.
	 * @param request
	 * @return List<Department>
	 */
	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public List<Department> getAllDepartments(HttpServletRequest request) {
		logger.debug(this.getClass().getName()+" GET request received to URL " + request.getRequestURI());
		return dao.getAllDepartments();
	}

	/**
	 * Updatese  department with data from request.
	 * @param department
	 * @param request
	 */
	@RequestMapping(value = "/department", method = RequestMethod.PUT)
	public void updateDepartment(@RequestBody Department department, HttpServletRequest request) {
		logger.debug(this.getClass().getName()+" PUT request received to URL " + request.getRequestURI());
		dao.updateDepartment(department);
	}

	/**
	 * Adds new department with data from request.
	 * @param department
	 * @param request
	 */
	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public void addDepartment(@RequestBody Department department, HttpServletRequest request) {
		logger.debug(this.getClass().getName()+" POST request received to URL " + request.getRequestURI());
		dao.addDepartment(department);
	}

	/**
	 * Deletes department by departmentID from path variable.
	 * @param departmentID
	 * @param request
	 */
	@RequestMapping(value = "/department/{departmentID}", method = RequestMethod.DELETE)
	public void deleteDepartment(@PathVariable Integer departmentID, HttpServletRequest request) {
		logger.debug(this.getClass().getName()+" DELETE request received to URL " + request.getRequestURI());
		dao.deleteDepartment(dao.findDepartmentById(departmentID));
	}

	public DepartmentControler() {
	}

}
