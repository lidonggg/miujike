package com.miujike.worksservice.vo;

import java.util.List;

/**
 * @author Ls J
 * @date 2019/5/31 12:00 AM
 */
public class FilterList {
    /**
     * 操作
     */
    private String groupOp;

    /**
     * 规则
     */
    private List<Rule> rules;

    /**
     * 操作组
     */
    private List<FilterList> groups;

    public List<FilterList> getGroups() {
        return groups;
    }

    public void setGroups(List<FilterList> groups) {
        this.groups = groups;
    }

    public String getGroupOp() {
        return groupOp;
    }

    public void setGroupOp(String groupOp) {
        this.groupOp = groupOp;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
