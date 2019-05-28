package com.miujike.behaviorservice.service;

import com.miujike.behaviorservice.fallback.WorksClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ls J
 * @date 2019/5/28 9:45 PM
 */
@FeignClient(name = "works-service",fallback = WorksClientFallBack.class)
public interface IWorksClient {

    /**
     * 添加点赞数
     * @param targetId   targetId
     * @param targetType targetType
     * @return
     */
    @GetMapping("api/v1/works/behavior/addCommentNum")
    boolean addCommentNum(@RequestParam(value = "targetId")Long targetId, @RequestParam(value = "targetType")int targetType);
}
