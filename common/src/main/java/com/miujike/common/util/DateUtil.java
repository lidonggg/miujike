package com.miujike.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ls J
 * @date 2019/5/19 4:27 PM
 */
public class DateUtil {

    /**
     * 判断时间是不是今天
     * @param date
     * @return    是返回true，不是返回false
     */
    public static boolean isNowDate(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);

        return day.equals(nowDay);
    }
}
