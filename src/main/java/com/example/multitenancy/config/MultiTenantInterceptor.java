package com.example.multitenancy.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MultiTenantInterceptor extends HandlerInterceptorAdapter {

	private static final String TENANT_HEADER_NAME = "TENANT-NAME";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String tenantName = request.getHeader(TENANT_HEADER_NAME);
		ThreadLocalTenantStorage.setTenantName(tenantName);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ThreadLocalTenantStorage.clear();
	}
}