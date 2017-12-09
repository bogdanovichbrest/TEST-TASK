package by.bogdanovich.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import by.bogdanovich.model.Department;
import by.bogdanovich.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestConfig.class)
//@FixMethodOrder(MethodSorters.JVM)
public class EmployeeControllerTest {
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
		template.execute("DELETE FROM DEPARTMENTS WHERE 1=1");
		template.execute("CREATE TABLE IF NOT EXISTS EMPLOYEES(ID INT NOT NULL AUTO_INCREMENT, FIRSTNAME VARCHAR(255), PATRONYMIC VARCHAR(255), LASTNAME VARCHAR(255), SALARY INT, BIRTHDATE DATE NOT NULL, DEPARTMENTID INT)");
		template.execute("INSERT INTO DEPARTMENTS VALUES(0, 'Software Testing')");
		template.execute("INSERT INTO DEPARTMENTS VALUES(1, 'Software Development')");
	}
	
	@Test
	public void startTest() throws Exception
	{
		testAddEmployee();
		testGetAllEmployees();
		testGetAllEmployeesInDepartment();
		testGetAllEmployeesByDate();
		testGetAllBetweenDates();
		testUpdateEmployee();
		testDeleteEmployee();
		
	}
	
	
	public void testAddEmployee() throws Exception
	{
		Employee employee = new Employee(null, new Department(0, "Software Testing"), 1000, "Vasilij", "Vasilievich", "Pupkin", Date.valueOf("1990-01-01"));
		String json = mapper.writeValueAsString(employee);
		System.out.println(json);
		mockMvc.perform(post("/employee/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk());
		employee =  new Employee(null, new Department(1, "Software Development"), 1100, "Ivan", "Ivanovich", "Ivanov", Date.valueOf("1991-02-02"));
		json = mapper.writeValueAsString(employee);
		mockMvc.perform(post("/employee/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk());
		employee = new Employee(null, new Department(0, "Software Testing"), 1200, "Alexander", "Simionovich", "Borodach", Date.valueOf("1992-03-03"));
		json = mapper.writeValueAsString(employee);
		mockMvc.perform(post("/employee/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk());
	}
	
	
	public void testGetAllEmployees() throws Exception
	{
		mockMvc.perform(get("/employee")).andExpect(status().isOk());
		Employee employee[] = mapper.readValue(mockMvc.perform(get("/employee")).andReturn().getResponse().getContentAsString(), Employee[].class);
		assertEquals(3, employee.length);
		assertEquals("Vasilij", employee[0].getFirstName());
		assertEquals("Alexander", employee[1].getFirstName());
		assertEquals("Ivan", employee[2].getFirstName());
	}
	
	
	public void testGetAllEmployeesInDepartment() throws Exception
	{
		mockMvc.perform(get("/employee/departmentID/0")).andExpect(status().isOk());
		Employee employee[] = mapper.readValue(mockMvc.perform(get("/employee/departmentID/0")).andReturn().getResponse().getContentAsString(), Employee[].class);
		assertEquals(2, employee.length);
		assertEquals("Vasilij", employee[0].getFirstName());
		assertEquals("Alexander", employee[1].getFirstName());
	}
	
	
	public void testGetAllEmployeesByDate() throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException, Exception
	{
		Employee employees[] = mapper.readValue(mockMvc.perform(get("/employee/birthDate/1992-03-03")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Employee[].class);
		assertEquals(1, employees.length);
		assertEquals("Alexander", employees[0].getFirstName());
	}
	

	public void testGetAllBetweenDates() throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException, Exception
	{
		Employee employees[] = mapper.readValue(mockMvc.perform(get("/employee/betweenDates/1991-02-02/1992-03-03")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Employee[].class);
		assertEquals(2, employees.length);
		assertEquals("Ivan", employees[0].getFirstName());
		assertEquals("Alexander", employees[1].getFirstName());
	}
	

	public void testUpdateEmployee() throws Exception
	{
		Employee employee =  mapper.readValue(mockMvc.perform(get("/employee")).andReturn().getResponse().getContentAsString(), Employee[].class)[0];
		employee.setSalary(1200);
		String json = mapper.writeValueAsString(employee);
		mockMvc.perform(put("/employee/update").contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk());
		Employee empl[] = mapper.readValue(mockMvc.perform(get("/employee")).andReturn().getResponse().getContentAsString(), Employee[].class);
		assertEquals("1200", empl[0].getSalary().toString());
	}
	

	public void testDeleteEmployee() throws Exception
	{
		mockMvc.perform(delete("/employee/delete/2")).andExpect(status().isOk());
		Employee employees[] = mapper.readValue(mockMvc.perform(get("/employee")).andReturn().getResponse().getContentAsString(), Employee[].class);
		assertEquals(2, employees.length);
	}

	public EmployeeControllerTest() {
		// TODO Auto-generated constructor stub
	}

}
