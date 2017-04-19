package by.bogdanovich.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import by.bogdanovich.model.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestConfig.class)
public class DepartmentControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void init() throws Exception
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testGetAllDepartments() throws Exception
	{
		mockMvc.perform(get("/managedepartments")).andExpect(status().isOk()).andExpect(view().name("departments"));
	}
	
	@Test
	public void testEditDepartment() throws Exception
	{
		mockMvc.perform(get("/managedepartments/editdepartment")).andExpect(status().isOk()).andExpect(view().name("editdepartment")).andExpect(model().attributeExists("department"));
	}
	
	@Test
	public void testSaveDepartment() throws Exception
	{
		mockMvc.perform(post("/managedepartments/savedepartment").param("departmentID", "0").param("departmentName", "Software Testing")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/managedepartments"));
	}

	public DepartmentControllerTest() {
		// TODO Auto-generated constructor stub
	}

}
