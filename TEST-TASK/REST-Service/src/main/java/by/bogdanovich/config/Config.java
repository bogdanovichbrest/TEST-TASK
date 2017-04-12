package by.bogdanovich.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import by.bogdanovich.dao.AppDAO;
import by.bogdanovich.dao.DepartmentAppDAO;

/**
 * This class is a configuration of the project and contains beans definitions.
 * @Alexander Bogdanovich
 * @version 1.0.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.bogdanovich")
public class Config {
	@Bean
	public AutowiredAnnotationBeanPostProcessor postProcessor() {
		return new AutowiredAnnotationBeanPostProcessor();
	}

	@Bean(name = "dataSource")
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
	public AppDAO setupDAO() {
		return new DepartmentAppDAO();
	}

	@Bean
	public Logger appLogger() throws IOException {
		Logger logger = Logger.getLogger("by.bogdanovich");
		logger.setLevel(Level.DEBUG);
		logger.addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n")));
		logger.addAppender(new DailyRollingFileAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n"), "logs\\REST-Service.log", "yyyy-MM-dd"));
		return logger;
	}

}
