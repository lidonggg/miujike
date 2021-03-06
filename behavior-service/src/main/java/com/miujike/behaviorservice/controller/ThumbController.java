package com.miujike.behaviorservice.controller;

import com.miujike.behaviorservice.domain.Thumb;
import com.miujike.behaviorservice.service.IThumbService;
import com.miujike.common.constants.BaseController;
import com.miujike.common.constants.ResponseConstants;
import com.miujike.common.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ls J
 * @date 2019/4/13 12:34 PM
 */
@RestController
@RequestMapping("api/v1/behavior/thumb")
public class ThumbController extends BaseController {

    @Autowired
    private IThumbService thumbService;

    /**
     * 点赞（送鸡蛋）
     *
     * @param thumb
     * @return
     */
    @PostMapping("doThumb")
    public ResponseData doThumb(Thumb thumb) {
        log.info("======>{}", thumb.toString());
        Thumb thumbDid = thumbService.doThumb(thumb);
        return null != thumbDid ? new ResponseData<>(thumbDid) : new ResponseData<>(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL, -1);
    }

}
