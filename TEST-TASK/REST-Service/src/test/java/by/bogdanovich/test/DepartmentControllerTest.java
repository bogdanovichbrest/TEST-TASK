package by.bogdanovich.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import by.bogdanovich.model.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestConfig.class)
@FixMethodOrder(MethodSorters.JVM)
public class DepartmentControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	@Autowired
	private DriverManagerDataSource dataSource;
	private JdbcTemplate template;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void init() throws Exception
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		template = new JdbcTemplate(dataSource);
		template.execute("CREATE TABLE IF NOT EXISTS DEPARTMENTS(DEPARTMENTID INT NOT NULL, DEPARTMENTNAME VARCHAR(255))");
	}
	
	
	@Test
	public void testAddDepartment() throws Exception
	{
		Department test = new Department(0, "TESTING");
		String json = mapper.writeValueAsString(test);
		mockMvc.perform(post("/department").contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk());
		test =  new Department(1, "TESTING2");
		json = mapper.writeValueAsString(test);
		mockMvc.perform(post("/department").contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk());
	}
	
	
	@Test
	public void testGetAllDepartments() throws Exception
	{
		mockMvc.perform(get("/department").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		Department department[] = mapper.readValue(mockMvc.perform(get("/department")).andReturn().getResponse().getContentAsString(), Department[].class);
		assertEquals(2, department.length);
		assertEquals("0", department[0].getDepartmentID().toString());
		assertEquals("1", department[1].getDepartmentID().toString());
	}
	
	@Test
	public void testUpdateDepartment() throws Exception
	{
		Department test = new Department(0, "TESTING_UPDATE");
		String json = mapper.writeValueAsString(test);
		mockMvc.perform(put("/department").contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk());
		Department department[] = mapper.readValue(mockMvc.perform(get("/department")).andReturn().getResponse().getContentAsString(), Department[].class);
		assertEquals("TESTING2", department[0].getDepartmentName());
		assertEquals("TESTING_UPDATE", department[1].getDepartmentName());
	}
	
	@Test
	public void testDeleteDepartment() throws Exception
	{
		mockMvc.perform(delete("/department/0")).andExpect(status().isOk());
		Department department[] = mapper.readValue(mockMvc.perform(get("/department")).andReturn().getResponse().getContentAsString(), Department[].class);
		assertEquals(1, department.length);
		assertEquals("TESTING2", department[0].getDepartmentName());
	}

	public DepartmentControllerTest() {
		// TODO Auto-generated constructor stub
	}

}
