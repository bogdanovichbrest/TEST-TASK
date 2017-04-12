package by.bogdanovich.config;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/**
 * 
 *This class is a configuration of the project and contains beans definitions.
 * @author Alexander Bogdanovich
 * @version 1.0.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.bogdanovich")
public class Config extends WebMvcConfigurerAdapter{

	@Bean
	public InternalResourceViewResolver setupResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/pages/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	@Bean
	public Logger appLogger() throws IOException
	{
		Logger logger = Logger.getLogger("by.bogdanovich");
		logger.setLevel(Level.DEBUG);
		logger.addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n")));
		logger.addAppender(new DailyRollingFileAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n"), "logs\\REST-Client.log", "yyyy-MM-dd"));
		return logger;
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
	}

	public Config() {

	}

}
