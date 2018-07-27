package com.legou.manager.pojo.dto;

/**
 * User: xwh
 * Date: 2018/7/18 Time: 20:36
 * Version:V1.0
 */
public class PageParam {


    private int page;

    private int limit;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public PageParam(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getOffset() {
        return (page-1)*limit;
    }


    public PageParam() {
    }
}
