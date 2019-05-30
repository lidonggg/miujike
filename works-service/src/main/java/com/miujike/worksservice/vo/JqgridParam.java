package com.miujike.worksservice.vo;

/**
 * @author Ls J
 * @date 2019/5/31 12:01 AM
 */
public class JqgridParam {

    /**
     * 当前页
     */
    private int page = 1;
    /**
     * 每页行数
     */
    private int rows = 10;

    private String sidx;
    /**
     * 排序方式（asc/desc）
     */
    private String sord;
    /**
     * 可查询的属性
     */
    private String searchField;
    private String searchOper;
    private String searchString;
    /**
     * 查询条件
     */
    private String filters;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        return "JqgridParam{" +
                "page=" + page +
                ", rows=" + rows +
                ", sidx='" + sidx + '\'' +
                ", sord='" + sord + '\'' +
                ", searchField='" + searchField + '\'' +
                ", searchOper='" + searchOper + '\'' +
                ", searchString='" + searchString + '\'' +
                ", filters='" + filters + '\'' +
                '}';
    }
}
