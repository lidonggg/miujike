package com.miujike.worksservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

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
@Configuration
public class WorksServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorksServiceApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        DataSize maxFileSize = DataSize.ofMegabytes(100);
        DataSize maxRequestSize = DataSize.ofMegabytes(120);
        // 单个数据大小
        factory.setMaxFileSize(maxFileSize);
        /// 总上传数据大小
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }
}
