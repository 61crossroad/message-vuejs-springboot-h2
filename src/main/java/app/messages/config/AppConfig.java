package app.messages.config;

import java.util.Arrays;

import javax.sql.DataSource;

import app.messages.web.AuditingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("app.messages")
@EnableTransactionManagement
public class AppConfig {
	
	private DataSource dataSource;
	
	public AppConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan("app.messages");
		return sessionFactoryBean;
	}
	
	@Bean
	public FilterRegistrationBean<AuditingFilter> auditingFilterRegistrationBean() {
		FilterRegistrationBean<AuditingFilter> registration = new FilterRegistrationBean<>();
		AuditingFilter filter = new AuditingFilter();
		registration.setFilter(filter);
		registration.setOrder(Integer.MAX_VALUE);
		// registration.setUrlPatterns(Arrays.asList("/messages/*"));
		registration.setUrlPatterns(Arrays.asList("/*"));
		return registration;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
	/* @Bean
	public MessageRepository messageRepository() {
		return new MessageRepository();
	}
	
	@Bean
	MessageService messageService() {
		return new MessageService(messageRepository());
	} */
}
