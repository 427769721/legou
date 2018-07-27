package com.legou.manager.pojo.dto;

/**
 * User: xwh
 * Date: 2018/7/20 Time: 18:01
 * Version:V1.0
 */


public class MemberQuery {

    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String title) {
        this.uname = title;
    }

    @Override
    public String toString() {
        return "MemberQuery{" +
                "uname='" + uname + '\'' +
                '}';
    }

    public MemberQuery() {
    }
}
