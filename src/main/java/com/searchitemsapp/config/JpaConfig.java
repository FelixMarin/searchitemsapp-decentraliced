package com.searchitemsapp.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.thomasvitale.jpa.demo.repository")
public class JpaConfig {
	
	@Autowired
	private IFCommonsProperties ifCommonsProperties;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("com.thomasvitale.jpa.demo.model");
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(ifCommonsProperties.getValue("hibernate.connection.url"));
		dataSource.setUsername(ifCommonsProperties.getValue("hibernate.connection.username"));
		dataSource.setPassword(ifCommonsProperties.getValue("hibernate.connection.password"));
		return dataSource;
	}
	
	@Bean
	public InstrumentationLoadTimeWeaver loadTimeWeaver()  throws Throwable {
		InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
		return loadTimeWeaver;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}
	
	@Bean
	public static PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", ifCommonsProperties.getValue("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", ifCommonsProperties.getValue("hibernate.dialect"));
		properties.setProperty("hibernate.current_session_context_class", ifCommonsProperties.getValue("hibernate.cache.provider_class"));
		properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", ifCommonsProperties.getValue("hibernate.jdbc.lob.non_contextual_creation"));
		properties.setProperty("hibernate.show_sql", ifCommonsProperties.getValue("hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql", ifCommonsProperties.getValue("hibernate.format_sql"));
		properties.setProperty("hibernate.connection.autocommit", ifCommonsProperties.getValue("hibernate.connection.autocommit"));
		properties.setProperty("hibernate.cache.use_second_level_cache", ifCommonsProperties.getValue("hibernate.cache"));
		properties.setProperty("hibernate.cache.use_query_cache", ifCommonsProperties.getValue("hibernate.query.cache"));
		properties.setProperty("hibernate.cache.provider_class", ifCommonsProperties.getValue("hibernate.cache.provider_class"));
		properties.setProperty("hibernate.cache.region.factory_class", ifCommonsProperties.getValue("hibernate.cache.region.factory_class"));
		properties.setProperty("hibernate.generate_statistics", ifCommonsProperties.getValue("hibernate.statistics"));
		properties.setProperty("net.sf.ehcache.configurationResourceName", ifCommonsProperties.getValue("/cache-config.xml"));
		return properties;
	}
}