package com.legou.manager.pojo.dto;

import java.util.Date;

/**
 * User: xwh
 * Date: 2018/7/26 Time: 20:05
 * Version:V1.0
 */
public class AdvertisementQuery {
    private  String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdvertisementQuery{" +
                "name='" + name + '\'' +
                '}';
    }

    public AdvertisementQuery() {
    }
}
