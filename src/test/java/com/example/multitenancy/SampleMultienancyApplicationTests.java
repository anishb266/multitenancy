package com.example.multitenancy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

@SpringBootTest
public class SampleMultienancyApplicationTests {

	//@Test
	//@EnabledIf("#{T(com.example.multitenancy.config.Constants).TEST == true}")
	@EnabledIf("#{T(com.example.multitenancy.config.Constants).TEST == true}")
	@Test
	public void theMeasurementSampleScreenFromPickConfirm() throws InterruptedException {
	       System.out.println("Hi");
	}

}