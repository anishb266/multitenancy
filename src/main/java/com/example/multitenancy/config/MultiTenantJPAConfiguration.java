package com.example.multitenancy.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "com.example.multitenancy.dao" }, transactionManagerRef = "multiTenantTxManager")
@EnableConfigurationProperties({ MultiTenantProperties.class, JpaProperties.class })
@EnableTransactionManagement
public class MultiTenantJPAConfiguration {

	@Autowired
	private JpaProperties jpaProperties;

	@Autowired
	private MultiTenantProperties multiTenantProperties;

	@Bean
	public MultiTenantConnectionProvider multiTenantConnectionProvider() {
		return new DataSourceMultiTenantConnectionProvider();
	}

	@Bean
	public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
		return new TenantIdentifierResolver();
	}

	@Bean(name = "repositoryDataSources")
	public Map<String, DataSource> repositoryDataSources() {
		Map<String, DataSource> datasources = new HashMap<>();
		multiTenantProperties.getDatasources().forEach((key, value) -> datasources.put(key, createDataSource(value)));
		return datasources;
	}

	private DataSource createDataSource(Map<String, String> source) {
		return DataSourceBuilder.create().url(source.get("url")).driverClassName(source.get("driverClassName"))
				.username(source.get("username")).password(source.get("password")).build();
	}

	@Bean
	public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return entityManagerFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager multiTenantTxManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
			MultiTenantConnectionProvider multiTenantConnectionProvider,
			CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {

		Map<String, Object> hibernateProperties = new LinkedHashMap<>();
		hibernateProperties.putAll(this.jpaProperties.getProperties());
		hibernateProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
		hibernateProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		hibernateProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPackagesToScan("com.example.multitenancy.entity");
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setJpaPropertyMap(hibernateProperties);
		return entityManagerFactoryBean;
	}

}
