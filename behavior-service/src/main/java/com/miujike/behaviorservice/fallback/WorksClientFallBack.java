package com.miujike.behaviorservice.fallback;

import com.miujike.behaviorservice.service.IWorksClient;

/**
 * @author Ls J
 * @date 2019/5/14 11:10 PM
 * 针对作品服务做降级处理
 */
public class WorksClientFallBack implements IWorksClient {
    @Override
    public boolean addCommentNum(Long targetId, int targetType) {
        return false;
    }

    @Override
    public boolean addThumbNum(Long targetId, int targetType) {
        return false;
    }

    @Override
    public boolean addShareNum(Long targetId, int targetType) {
        return false;
    }
}
