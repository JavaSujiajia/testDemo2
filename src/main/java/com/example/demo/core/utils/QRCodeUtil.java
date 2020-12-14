package com.example.demo.core.utils;

import java.util.UUID;

/**
 * 码工具类
 */
public class QRCodeUtil {

    /**
     * @description 生成码, prefix+15位数字
     * @author wangyunlong
     * @date 2018/10/22 13:27
     **/
    public static String generatedCode(final String prefix) {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            //有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0     
        // 15 代表长度为15     
        // d 代表参数为正数型
        return prefix + String.format("%015d", hashCodeV);
    }
}
