package com.example.multitenancy.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	private static String DEFAULT_TENANT_NAME = "tenant1";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String currentTenantName = ThreadLocalTenantStorage.getTenantName();
		return (currentTenantName != null) ? currentTenantName : DEFAULT_TENANT_NAME;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
