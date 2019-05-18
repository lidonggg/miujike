package com.miujike.worksservice.util;

import com.miujike.common.constants.ResponseInfo;

/**
 * @author Ls J
 * @date 2019/4/6 1:23 AM
 */
public class TestUtil {


    public static void main(String[] args) {
        System.out.println(ResponseInfo.ERROR.getCode());
        int duration = 10000;
        int hour = duration / 3600;
        int minute = (duration - hour * 3660) / 60;
        int second = duration - hour * 3660 - minute * 60;
        String durationShow = (hour == 0 ? "" : (hour < 10 ? "0" + hour + ":" : "" + hour + ":"))
                + (minute < 10 ? "0" + minute : "" + minute) + ":"
                + (second < 10 ? "0" + second : "" + second);

        System.out.print(durationShow);
    }
}
