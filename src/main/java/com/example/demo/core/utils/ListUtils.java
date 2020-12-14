package com.example.demo.core.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ListUtils
 * @Description: list转换
 */
public class ListUtils {

    /**
     * @param fromList
     * @param tClass
     * @return
     * @Title: entityListToModelList
     * @Description: 将fromList转换成tClassList
     * @author liyanyong
     * @date 2018年12月13日 下午2:19:26
     */
    public static <F, T> List<T> entityListToModelList(List<F> fromList, Class<T> tClass) {
        if (fromList.isEmpty() || fromList == null) {
            return null;
        }
        List<T> tList = new ArrayList<>();
        for (F f : fromList) {
            T t = entityToModel(f, tClass);
            BeanUtils.copyProperties(f, t);
            tList.add(t);
        }
        return tList;
    }

    public static <F, T> T entityToModel(F entity, Class<T> modelClass) {
        T model = null;
        if (entity == null || modelClass == null) {
            return null;
        }
        try {
            model = modelClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
