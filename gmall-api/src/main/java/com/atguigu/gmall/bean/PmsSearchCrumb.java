package com.atguigu.gmall.bean;

import java.io.Serializable;

public class PmsSearchCrumb implements Serializable {

    private String valueId;
    private String valueName;
    private String urlParam;

    public String getValueId() {
        return valueId;
    }

    public PmsSearchCrumb setValueId(String valueId) {
        this.valueId = valueId;
        return this;
    }

    public String getValueName() {
        return valueName;
    }

    public PmsSearchCrumb setValueName(String valueName) {
        this.valueName = valueName;
        return this;
    }

    public String getUrlParam() {
        return urlParam;
    }

    public PmsSearchCrumb setUrlParam(String urlParam) {
        this.urlParam = urlParam;
        return this;
    }
}
