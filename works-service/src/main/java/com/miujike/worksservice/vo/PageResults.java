package com.miujike.worksservice.vo;

import java.util.List;
import java.util.Map;

/**
 * 分页封装类
 * 用于做分页查询的基础类，封装了一些分页的相关属性
 *
 * @author ls J
 * @date 2019/5/30 23:01 AM
 */
public class PageResults<T> {

    /**
     * 下一页
     */
    private int nextPage;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页个个数
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

    private Map<String, Object> userdata;

    public Map<String, Object> getUserdata() {
        return userdata;
    }

    public void setUserdata(Map<String, Object> userdata) {
        this.userdata = userdata;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNextPage() {
        if (nextPage <= 0) {
            return 1;
        } else {
            return nextPage > total ? total : nextPage;
        }
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
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
        this.rows = rows <= 0 ? 10 : rows;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public void resetNextPage() {
        nextPage = page + 1;
        total = records % rows == 0 ? records / rows
                : records / rows + 1;
    }

}
