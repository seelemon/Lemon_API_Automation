package com.lemon.until;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author Automation engineer
 * @Description The God of automation
 * @Date 2021/8/28 19:37
 */

/*
映射实体类，类属性应该与Excel表头名字一致
 */
public class ExcelData {
    @Excel(name = "case_id")
    private int case_id;

    @Excel(name = "module")
    private String module;

    @Excel(name = "title")
    private String title;

    @Excel(name = "method")
    private String method;

    @Excel(name = "address")
    private String address;

    @Excel(name = "headers")
    private String headers;

    @Excel(name = "data")
    private String data;

    @Excel(name = "expect")
    private String expect;

    @Excel(name = "sqlCheck")
    private String sqlCheck;

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getSqlCheck() {
        return sqlCheck;
    }

    public void setSqlCheck(String sqlCheck) {
        this.sqlCheck = sqlCheck;
    }

    @Override
    public String toString() {
        return "ExcelData{" +
                "case_id=" + case_id +
                ", module='" + module + '\'' +
                ", title='" + title + '\'' +
                ", method='" + method + '\'' +
                ", address='" + address + '\'' +
                ", headers='" + headers + '\'' +
                ", data='" + data + '\'' +
                ", expect='" + expect + '\'' +
                ", sqlCheck='" + sqlCheck + '\'' +
                '}';
    }
}
