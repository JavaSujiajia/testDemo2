/*
 * Conditions Of Use
 *
 * This software was developed by employees of the Sigmatrix(Beijing) Corporation.
 * This software is provided by sigmatrix as a service and is expressly
 * provided "AS IS."  Sigmatrix MAKES NO WARRANTY OF ANY KIND, EXPRESS, IMPLIED
 * OR STATUTORY, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTY OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT
 * AND DATA ACCURACY.  Sigmatrix does not warrant or make any representations
 * regarding the use of the software or the results thereof, including but
 * not limited to the correctness, accuracy, reliability or usefulness of
 * the software.
 *
 * Permission to use this software is contingent upon your acceptance
 * of the terms of this agreement.
 *
 */
package com.example.demo.core.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author
 */
public class DateUtil {


    public static String getCurrentDateStr(String dateFormat) {
        return DateFormatUtils.format(new Date(), dateFormat);
    }


    /**
     * 获取当前年的天数
     *
     * @return 天数
     */
    public static int getDaysForYear(Integer year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return 366;
        } else {
            return 365;
        }
    }

    /**
     * 返回当前的年月日
     *
     * @return
     */
    public static String getNowDate() {
        Date date = new Date();
        String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        return nowDate;
    }

    public static int getYear() {
        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        return Integer.parseInt(year);
    }

    //返回当前月份
    public static int getMonth() {
        Date date = new Date();
        String month = new SimpleDateFormat("MM").format(date);
        return Integer.parseInt(month);
    }

    //判断闰年
    public static boolean isLeap(int year) {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }

    /**
     * 返回当月天数
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static int getDays(int year, int month) {
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }
}
