package by.bogdanovich.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.bogdanovich.dao.AppDAO;
import by.bogdanovich.model.Department;

@RestController("/department")
@RequestMapping("/")
public class DepartmentControler {
	@Autowired
	@Qualifier("dao")
	private AppDAO dao;

	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public List<Department> getAllDepartments() {
		return dao.getAllDepartments();
	}

	@RequestMapping(value = "/department", method = RequestMethod.PUT)
	public void updateDepartment(@RequestBody Department department) {
		dao.updateDepartment(department);
	}

	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public void addDepartment(@RequestBody Department department) {
		dao.addDepartment(department);
	}

	@RequestMapping(value = "/department/{departmentID}")
	public void deleteDepartment(@PathVariable Integer departmentID) {
		dao.deleteDepartment(dao.findDepartmentById(departmentID));
	}

	public DepartmentControler() {
	}

}
