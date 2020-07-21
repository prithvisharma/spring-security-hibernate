package com.springsecurity;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.springsecurity.repository"})
@ComponentScan(basePackages = {"com.springsecurity"})
public class DatabaseConfig {

	@Bean
	public DataSource setDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring security");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
	
	//creating entity manager
	@Bean										  
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entity = new LocalContainerEntityManagerFactoryBean();
		
		//datasource to entity
		entity.setDataSource(setDataSource());
		//add packages where autowire issues can come
		entity.setPackagesToScan("com.springsecurity.controller","com.springsecurity.repository","com.springsecurity.model");
		//give jpa vendor adaptor
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		entity.setJpaVendorAdapter(adapter);
		//setting properties for entity
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update"); //update is set for creating tables on the fly
																	//is set to none on the fly creation gets disabled
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		//properties.setProperty("hibernate.show_sql", "true");
		entity.setJpaProperties(properties);
		return entity;
	}
		
	//jpa transaction manager
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entity) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entity);
		return manager;
	}
	
}










