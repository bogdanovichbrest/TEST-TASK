package by.bogdanovich.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import by.bogdanovich.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestConfig.class)
public class EmployeeControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void init() throws Exception
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testGetAllEmployees() throws Exception
	{
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index")).andExpect(model().attributeExists("Employees"));
		mockMvc.perform(get("/?department=0")).andExpect(status().isOk()).andExpect(view().name("index")).andExpect(model().attributeExists("Employees","MiddleSalary"));
	}
	
	@Test
	public void testEditEmployee() throws Exception
	{
		mockMvc.perform(get("/edit?id=0&firstname=Alexander&patronymic=Sergeevich&lastname=Bogdanovich&birthdate=1992-01-04&departmentid=0&departmentname=Software%20Engeneering&salary=1200")).andExpect(status().isOk()).andExpect(view().name("edit")).andExpect(model().attributeExists("employee","Departments"));
	}
	
	@Test
	public void testSaveEmployee() throws Exception
	{
		mockMvc.perform(post("/save").param("id", "0").param("firstname", "Alexander").param("patronymic", "Sergeevich").param("lastname", "Bogdanovich").param("birthdate", "1992-01-04").param("departmentid", "1").param("salary", "1200")).andExpect(redirectedUrl("/")).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testDeleteEmployee() throws Exception
	{
		mockMvc.perform(post("/save").param("id", "").param("firstname", "TEST").param("patronymic", "TEST").param("lastname", "TEST").param("birthdate", "1992-01-04").param("departmentid", "1").param("salary", "1200")).andExpect(redirectedUrl("/")).andExpect(status().is3xxRedirection());
		Employee[] employees = (Employee[]) mockMvc.perform(get("/")).andReturn().getModelAndView().getModelMap().get("Employees");
		mockMvc.perform(get("/delete?id="+employees[employees.length-1].getId())).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));

	}
	
	@Test
	public void testFindEmployees() throws Exception
	{
		mockMvc.perform(post("/find").param("check", "off").param("date", "1992-01-01").param("date2", "")).andExpect(status().isOk()).andExpect(model().attributeExists("BackToAllList","Employees"));	
		mockMvc.perform(post("/find").param("check", "on").param("date", "1992-01-01").param("date2", "1994-04-01")).andExpect(status().isOk()).andExpect(model().attributeExists("BackToAllList","Employees"));
	}

	public EmployeeControllerTest() {
		// TODO Auto-generated constructor stub
	}

}
