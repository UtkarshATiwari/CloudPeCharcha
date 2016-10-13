package com.niit.cloudpecharcha.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.niit.cloudpecharcha.DAO.UsersDAO;
import com.niit.cloudpecharcha.DAO.UsersDAOImpl;
import com.niit.cloudpecharcha.model.Users;


@Configuration
@ComponentScan(basePackages ={"com.niit.cloudpecharcha"})
//@EnableTransactionManagement
//@EnableWebMvc

public class ApplicationContextConfig extends WebMvcConfigurerAdapter {
	
	 @Bean(name = "dataSource")
	    public BasicDataSource getOracleDataSource() {
	    	BasicDataSource dataSource= new BasicDataSource();
	    	dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    	dataSource.setUrl("jdbc:oracle:thin:@Utkarsh:1521:xe");
	    	dataSource.setUsername("ankur");
	    	dataSource.setPassword("ankur");
			return dataSource;
	
	 		}
	 
	 private Properties getHibernateProperties() {
	    	Properties properties = new Properties();
	    	properties.setProperty("hibernate.show_sql", "true");
	    	//properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    	properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	    	return properties;
	    }
	 
	 @Autowired
	    @Bean(name = "mysessionFactory")
	    public SessionFactory getSessionFactory(BasicDataSource dataSource) {
	    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    	sessionBuilder.addProperties(getHibernateProperties());
	    	sessionBuilder.addAnnotatedClasses(Users.class);
//	    	sessionBuilder.addAnnotatedClasses(Blogs.class);
//	    	sessionBuilder.addAnnotatedClasses(Forums.class);
//	    	sessionBuilder.addAnnotatedClasses(Events.class);
//	    	sessionBuilder.addAnnotatedClasses(Placement.class);
	    	//sessionBuilder.addAnnotatedClasses(Account.class);
	    	return sessionBuilder.buildSessionFactory();
	    }
	 
	 @Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(
				SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(
					sessionFactory);

			return transactionManager;
		}
	 
	 @Autowired
	    @Bean(name = "userDAO")
	    public UsersDAO getUsersDAO(SessionFactory mysessionFactory) {
	    	return new UsersDAOImpl(mysessionFactory);
	    }
}