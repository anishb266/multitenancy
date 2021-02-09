package com.example.multitenancy.config;

public class ThreadLocalTenantStorage {

	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

	public static void setTenantName(String tenantName) {
		currentTenant.set(tenantName);
	}

	public static String getTenantName() {
		return currentTenant.get();
	}

	public static void clear() {
		currentTenant.remove();
	}

}
