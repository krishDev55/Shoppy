package com.ShoppyCart.config;

import org.springframework.context.annotation.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import com.ShoppyCart.entity.BankAccount;
import com.ShoppyCart.entity.BankTransaction;
import com.ShoppyCart.entity.Cart;
import com.ShoppyCart.entity.Categery;
import com.ShoppyCart.entity.Order;
import com.ShoppyCart.entity.Products;
import com.ShoppyCart.entity.Rating;
import com.ShoppyCart.entity.UpdUser;
import com.ShoppyCart.entity.User;
import com.ShoppyCart.entity.Vendor;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateTemplateConfig  {
//	@Value( "${spring.jpa.database-platform}")
//	private String dialect;
	
	
	  @Bean
	     DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/shoppy");
	        dataSource.setUsername("root");
	        dataSource.setPassword("root");
	        return dataSource;
	    }

	    @Bean
	    public SessionFactory sessionFactory() {
	        LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource());
	        sessionFactory.addAnnotatedClasses(Products.class,
	        									Categery.class,
	        									User.class,
	        									Cart.class,
	        									Order.class,
	        									Vendor.class,
	        									Rating.class,
	        									BankTransaction.class,
	        									BankAccount.class,
	        									UpdUser.class
	        									
	        									
	        								); // Replace with your package
	        sessionFactory.addProperties(hibernateProperties());
	        return  sessionFactory.buildSessionFactory();
	    }

//	    @Bean
	     HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
	     System.out.println("Session factory object is : "+ sessionFactory.getProperties());
	     
	     HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
	     hibernateTemplate.setCheckWriteOperations(false);
	     
	        return hibernateTemplate;
	    }

	    private Properties hibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
	        properties.put("hibernate.show_sql", "true");
	        properties.put("hibernate.hbm2ddl.auto", "update");
	        return properties;
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	------------------------------------------------------------------------
	

	/*
	 * HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
	 * 
	 * HibernateTemplate temp= new HibernateTemplate();
	 * temp.setSessionFactory(sessionFactory); temp.setCheckWriteOperations(false);
	 * 
	 * return temp; }
	 * 
	 * 
	 * @Bean LocalSessionFactoryBean sessionFactory() { LocalSessionFactoryBean
	 * sessionFact= new LocalSessionFactoryBean(); SessionFactoryDelegatingImpl se=
	 * new SessionFactoryDelegatingImpl(null);
	 * sessionFact.setDataSource(dataSource()); Properties proparty = new
	 * Properties(); proparty.put("hibernate.dialect",
	 * "org.hibernate.dialect.MySQL8Dialect"); proparty.put("hibernate.show_sql",
	 * true); proparty.put("hibernate.format_sql", true);
	 * proparty.put("hibernate.hbm2ddl.auto", "update");
	 * 
	 * sessionFact.setHibernateProperties(proparty);
	 * 
	 * sessionFact.setAnnotatedPackages("com.ShoppyCart.entity"); //
	 * sessionFact.setPackagesToScan("com.ShoppyCart.entity");
	 * 
	 * 
	 * return sessionFact;
	 * 
	 * }
	 * 
	 * @Bean DriverManagerDataSource dataSource() { DriverManagerDataSource ds1= new
	 * DriverManagerDataSource();
	 * ds1.setDriverClassName("com.mysql.cj.jdbc.Driver");
	 * ds1.setUrl("jdbc:mysql://localhost:3306/shoppy"); ds1.setUsername("root");
	 * ds1.setPassword("root"); return ds1;
	 * 
	 * }
	 * 
	 * @Bean public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	 * LocalContainerEntityManagerFactoryBean factory = new
	 * LocalContainerEntityManagerFactoryBean();
	 * factory.setDataSource(dataSource());
	 * factory.setPackagesToScan("com.example.entity"); // Replace with your package
	 * factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); return factory;
	 * }
	 * 
	 * // @Bean // public JpaTransactionManager
	 * transactionManager(EntityManagerFactory entityManagerFactory) { // return new
	 * JpaTransactionManager(entityManagerFactory); // }
	 */
	
}
