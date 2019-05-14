package com.miujike.behaviorservice.service;

import com.miujike.behaviorservice.fallback.WorksClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Ls J
 * @date 2019/5/14 11:09 PM
 * feign调用作品客户端
 */
@FeignClient(name = "works-service",fallback = WorksClientFallBack.class)
public interface IWorksClient {


}
