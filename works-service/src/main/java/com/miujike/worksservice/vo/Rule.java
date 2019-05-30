package com.miujike.worksservice.vo;

/**
 * 封装jqgrid多条件查询时页面传来的参数的查询规则
 *
 * @author ls J
 * @date 2019/5/30 23:55 AM
 */
public class Rule {
    private String field;
    private String op;
    private String data;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
