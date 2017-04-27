package by.bogdanovich.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import by.bogdanovich.dao.AppDAO;
import by.bogdanovich.dao.DepartmentAppDAO;

@Configuration
@ComponentScan(basePackages = "by.bogdanovich")
@EnableWebMvc
public class TestConfig {
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource testDataSource()
	{
		DriverManagerDataSource testDS = new DriverManagerDataSource();
		testDS.setDriverClassName("org.h2.Driver");
		testDS.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		testDS.setUsername("sa");
		testDS.setPassword("");
		
		return testDS;
		
	}
	
	@Bean
	public AppDAO dao()
	{
		return new DepartmentAppDAO();
	}
	

	public TestConfig() {
		// TODO Auto-generated constructor stub
	}

}
