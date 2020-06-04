package com.sree.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sree.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retriveLimitsFromConfigurtion() {
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
	/**
	 * Fault Tolerance Example with Hystrix
	 * 
	 * Mention the fallback method here --> @HystrixCommand(fallbackMethod="[methodname]")
	 * @return
	 */
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetriveConfiguration")
	public LimitConfiguration retriveConfigurtion() {
		throw new RuntimeException("Method not available");
	}
	//fallback method body
	public LimitConfiguration fallbackRetriveConfiguration() {
		return new LimitConfiguration(9,999);
	}

}
