package com.example.demo.core.lang;

import java.util.List;

public class User {

    private String schema;

    private String eseCode;

    private List<Integer> factoryIds;

    private boolean admin;

    private Integer userId;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getEseCode() {
        return eseCode;
    }

    public void setEseCode(String eseCode) {
        this.eseCode = eseCode;
    }

    public List<Integer> getFactoryIds() {
        return factoryIds;
    }

    public void setFactoryIds(List<Integer> factoryIds) {
        this.factoryIds = factoryIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
