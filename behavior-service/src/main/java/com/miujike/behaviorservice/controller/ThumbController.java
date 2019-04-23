package com.miujike.behaviorservice.controller;

import com.miujike.behaviorservice.service.IThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ls J
 * @date 2019/4/13 12:34 PM
 */
@Controller
@RequestMapping("api/v1/behavior/thumb")
public class ThumbController {

    @Autowired
    private IThumbService thumbService;

}
