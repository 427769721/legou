package com.legou.manager.pojo.dto;

import java.util.List;

/**
 * User: xwh
 * Date: 2018/7/26 Time: 20:05
 * Version:V1.0
 */
public class AdvertisementResult<T> {
    private int code;
    private String msg;
    private Long count;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public AdvertisementResult() {
    }

    @Override
    public String toString() {
        return "AdvertisementResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
