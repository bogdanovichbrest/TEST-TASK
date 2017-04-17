package by.bogdanovich.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestConfig.class)
public class EmployeeControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void init()
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
	public void testSaveEmployee()
	{
		mockMvc.perform(post("/save"))
	}

	public EmployeeControllerTest() {
		// TODO Auto-generated constructor stub
	}

}
