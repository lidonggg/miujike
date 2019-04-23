package com.miujike.behaviorservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lsj
 */
@SpringBootApplication
@EnableFeignClients
//hystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@MapperScan("com.miujike.behaviorservice.mapper")
public class BehaviorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BehaviorServiceApplication.class, args);
	}

}
