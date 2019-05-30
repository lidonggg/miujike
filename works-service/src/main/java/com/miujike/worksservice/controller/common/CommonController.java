package com.miujike.worksservice.controller.common;

import com.miujike.common.constants.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ls J
 * @date 2019/5/30 9:43 PM
 */
@Controller
@RequestMapping("api/v1/works/page/common")
public class CommonController extends BaseController {

    @RequestMapping(value = {"{first}"})
    public String toPage(@PathVariable("first") String first) {
        return first;
    }

    @RequestMapping(value = {"{first}/{second}"})
    public String toPage(@PathVariable("first") String first, @PathVariable("second") String second) {
        return first + "/" + second;
    }

    @RequestMapping(value = {"{first}/{second}/{third}"})
    public String toPage(@PathVariable("first") String first, @PathVariable("second") String second,
                         @PathVariable("third") String third) {
        return first + "/" + second + "/" + third;
    }
}
