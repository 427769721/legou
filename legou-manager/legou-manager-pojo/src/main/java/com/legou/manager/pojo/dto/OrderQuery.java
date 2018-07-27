package com.legou.manager.pojo.dto;

/**
 * @Author: ZH
 * @Date: 2018/7/23 19:39
 * @Description:
 */
public class OrderQuery {
    private String start;
    private String end;
    private int status;
    private String orderId;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
