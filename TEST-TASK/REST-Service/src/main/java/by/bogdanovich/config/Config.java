package by.bogdanovich.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import by.bogdanovich.dao.AppDAO;
import by.bogdanovich.dao.DepartmentAppDAO;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.bogdanovich")
public class Config {
	@Bean
	public AutowiredAnnotationBeanPostProcessor postProcessor() {
		return new AutowiredAnnotationBeanPostProcessor();
	}

	@Bean(name="dataSource")
	public DriverManagerDataSource dataSource() throws FileNotFoundException, IOException {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		Properties connectionProperties = new Properties();
		connectionProperties.load(Config.class.getResourceAsStream("/connection.properties"));
		ds.setDriverClassName(connectionProperties.getProperty("driver"));
		ds.setUrl(connectionProperties.getProperty("url"));
		ds.setUsername(connectionProperties.getProperty("username"));
		ds.setPassword(connectionProperties.getProperty("password"));
		return ds;

	}
	
	@Bean(name = "dao")
	public AppDAO setupDAO()
	{
		return new DepartmentAppDAO();
	}

}
