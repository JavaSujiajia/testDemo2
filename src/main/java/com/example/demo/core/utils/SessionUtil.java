package com.example.demo.core.utils;


import com.example.demo.core.lang.Assert;
import com.example.demo.core.lang.MessageException;

import java.util.List;

public class SessionUtil {

    public static Integer validateFactory(Integer paramFactory, List<Integer> sessionFactory) {
        if (CommonUtils.isEmpty(paramFactory) && CommonUtils.isEmpty(sessionFactory)) {
            throw new MessageException("工厂ID不能为空");
        }
        if (CommonUtils.isEmpty(paramFactory)) {
            paramFactory = sessionFactory.get(0);
        } else {
            if (CommonUtils.isNotEmpty(sessionFactory)) {
                Assert.isTrue(sessionFactory.contains(paramFactory), "工厂ID错误");
            }
        }
        return paramFactory;
    }
}
