package com.example.multitenancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class }, scanBasePackages = { "com.example.multitenancy.config",
				"com.example.multitenancy.controller" })
public class SampleMultienancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleMultienancyApplication.class, args);

	}

}
