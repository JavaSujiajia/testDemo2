package com.example.demo.core.lang;


import com.example.demo.core.utils.CommonUtils;

import java.util.List;

/**
 * @author maheng
 */
public class Session {
    private static ThreadLocal<User> holder = new ThreadLocal<>();

    public static User get() {
        return holder.get();
    }

    private static User getOrCreate() {
        User login = holder.get();
        if (login == null) {
            login = new User();
            set(login);
        }
        return login;
    }

    public static void set(User value) {
        holder.set(value);
    }

    public static void clear() {
        holder.remove();
    }

    public static String getEseCode() {
        User login = get();
        if (login != null) {
            return login.getEseCode();
        }
        return null;
    }

    public static void setEseCode(String eseCode) {
        User login = getOrCreate();
        login.setEseCode(eseCode);
    }

    public static String getSchema() {
        User login = get();
        if (login != null) {
            return login.getSchema();
        }
        return null;
    }

    public static void setSchema(String schema) {
        User login = getOrCreate();
        login.setSchema(schema);
    }

    public static void setUserId(Integer userId) {
        User login = getOrCreate();
        login.setUserId(userId);
    }

    public static void setFactoryIds(List<Integer> factoryIds) {
        User login = getOrCreate();
        login.setFactoryIds(factoryIds);
    }

    public static Integer getUserId() {
        User login = get();
        if (login != null) {
            return login.getUserId();
        }
        return null;
    }

    public static boolean isAdmin() {
        User login = get();
        if (login != null) {
            return login.isAdmin();
        }
        return false;
    }

    /**
     * 获取用户的数据约束
     *
     * @return
     */
    public static List<Integer> getUserFactoryIds() {
        User login = get();
        if (login != null && CommonUtils.isNotEmpty(login.getFactoryIds())) {
            return login.getFactoryIds();
        }
        return null;
    }

}
