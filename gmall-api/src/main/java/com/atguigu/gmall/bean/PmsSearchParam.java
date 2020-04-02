package com.atguigu.gmall.bean;

import java.io.Serializable;

public class PmsSearchParam implements Serializable{

    private String catalog3Id;

    private String keyword;

    private String[] valueId;

    public String getCatalog3Id() {
        return catalog3Id;
    }

    public PmsSearchParam setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public PmsSearchParam setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String[] getValueId() {
        return valueId;
    }

    public PmsSearchParam setValueId(String[] valueId) {
        this.valueId = valueId;
        return this;
    }
}
