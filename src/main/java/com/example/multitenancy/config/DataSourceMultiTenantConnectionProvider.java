package com.example.multitenancy.config;

import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class DataSourceMultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Map<String, DataSource> multipleDataSources;

	@Override
	protected DataSource selectAnyDataSource() {
		return multipleDataSources.values().iterator().next();
	}

	@Override
	protected DataSource selectDataSource(String tenantName) {
		return multipleDataSources.get(tenantName);
	}
}