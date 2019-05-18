package com.miujike.worksservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lsj
 */
@SpringBootApplication
@EnableFeignClients
//hystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@MapperScan("com.miujike.worksservice.mapper")
@EnableTransactionManagement
public class WorksServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorksServiceApplication.class, args);
    }

}
