package com.prateekb.config;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.prateekb.DAO")
@PropertySource({"classpath:db_hibernate.properties"})
@ComponentScans(value = {
		@ComponentScan("com.prateekb.DAO"),
		@ComponentScan("com.prateekb.service")
})
public class HibernateConfig {

	@Autowired
	Environment env;
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
		lfb.setDataSource(restDataSource());
		lfb.setPersistenceProviderClass(HibernatePersistence.class);
		lfb.setPackagesToScan("com.prateekb.model");
		lfb.setJpaProperties(hibernateProperties());
		return lfb;
	}


	@Bean
	JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
 
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
       return new PersistenceExceptionTranslationPostProcessor();
    }
	
	private DataSource restDataSource() {
		  BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
	      dataSource.setUrl(env.getProperty("jdbc.databaseurl"));
	      dataSource.setUsername(env.getProperty("jdbc.username"));
	      dataSource.setPassword(env.getProperty("jdbc.password"));
	 
	      return dataSource;
	}

	private Properties hibernateProperties() {
		 Properties property = new Properties();
	     
		 property.setProperty(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		 property.setProperty(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		 
		 property.setProperty(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		 property.setProperty(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		 property.setProperty(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		 property.setProperty(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		 property.setProperty(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
		 
		 
		 return property;
	}
}