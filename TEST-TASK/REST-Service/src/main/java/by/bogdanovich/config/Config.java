package by.bogdanovich.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "by.bogdanovich")
public class Config {
	@Bean
	public AutowiredAnnotationBeanPostProcessor postProcessor() {
		return new AutowiredAnnotationBeanPostProcessor();
	}

	@Bean
	public DriverManagerDataSource dataSource() throws FileNotFoundException, IOException {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		Properties connectionProperties = new Properties();
		connectionProperties.load(new FileInputStream(new File("src/main/resources/jdbc.properties")));
		ds.setDriverClassName(connectionProperties.getProperty("driver"));
		ds.setUrl(connectionProperties.getProperty("url"));
		ds.setUsername(connectionProperties.getProperty("username"));
		ds.setPassword(connectionProperties.getProperty("password"));
		return ds;

	}

}
