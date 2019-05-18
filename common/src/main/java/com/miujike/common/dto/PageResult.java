package com.miujike.common.dto;

import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/5/18 10:31 PM
 */
public class PageResult<T> {
    /**
     * 下一页
     */
    private int nextPage;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页个数
     */
    private int rows;

    /**
     * 总条数
     */
    private int records;

    /**
     * 总页数
     */
    private int total;

    /**
     * 记录
     */
    private List<T> results;

    private Map<String,Object> userdata;

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

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

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Map<String, Object> getUserdata() {
        return userdata;
    }

    public void setUserdata(Map<String, Object> userdata) {
        this.userdata = userdata;
    }
}
