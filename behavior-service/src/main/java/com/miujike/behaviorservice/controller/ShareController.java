package com.miujike.behaviorservice.controller;

import com.miujike.behaviorservice.domain.Share;
import com.miujike.behaviorservice.service.IShareService;
import com.miujike.common.constants.BaseController;
import com.miujike.common.constants.ResponseConstants;
import com.miujike.common.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ls J
 * @date 2019/5/28 11:50 PM
 */
@RestController
@RequestMapping("api/v1/behavior/share")
public class ShareController extends BaseController {

    @Autowired
    private IShareService shareService;

    /**
     * 点赞（送鸡蛋）
     *
     * @param share
     * @return
     */
    @PostMapping("doThumb")
    public ResponseData doShare(Share share) {
        Share shareDid = shareService.doShare(share);
        return null != shareDid? new ResponseData<>(shareDid):new ResponseData<>(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL, -1);
    }

}
