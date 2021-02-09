package com.example.multitenancy.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "repository")
public class MultiTenantProperties {

	private Map<String, Map<String, String>> datasources = new LinkedHashMap<>();

	public Map<String, Map<String, String>> getDatasources() {
		return datasources;
	}

	public void setDatasources(Map<String, Map<String, String>> datasources) {
		this.datasources = datasources;
	}

}
