package com.example.demo.core.lang;

import com.example.demo.core.utils.CommonUtils;

/**
 * 校验参数
 *
 * @author maheng
 */
public class Assert implements HttpCode {

    public static void isTrue(boolean flag, String code, String message) {
        if (flag == false)
            throw new MessageException(code, message);
    }

    public static void isTrue(boolean flag, String message) {
        if (flag == false)
            throw new MessageException(message);
    }

    public static void equals(Number a, Number b, String code, String message) {
        if (!a.equals(b))
            throw new MessageException(code, message);
    }

    public static void equals(Number a, Number b, String message) {
        if (!a.equals(b))
            throw new MessageException(message);
    }

    /**
     * 验证对象不能为null, 模板信息可能有占位符
     */
    public static void notNull(Object ob, String message) {
        if (CommonUtils.isEmpty(ob))
            throw new MessageException(message);
    }

    public static void notNull(Object ob, Message message) {
        if (CommonUtils.isEmpty(ob))
            throw new MessageException(message.code, message.msg);
    }

    /**
     * @param ob
     * @param code    返回的错误编码
     * @param message 返回的错误信息
     */
    public static void notNull(Object ob, String code, String message) {
        if (CommonUtils.isEmpty(ob))
            throw new MessageException(code, message);
    }

    /**
     * @param ob
     * @param message
     */
    public static void gt0(Number ob, String message) {
        if (CommonUtils.isEmpty(ob) || ob.longValue() <= 0)
            throw new MessageException(message);
    }
}
