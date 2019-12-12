package com.atguigu.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @param
 * @return
 */
public class PmsBaseAttrValue implements Serializable {
    @Id
    @Column
    private String id;
    @Column
    private String valueName;
    @Column
    private String attrId;
    @Column
    private String isEnabled;

    @Transient
    private String urlParam;

    public String getId() {
        return id;
    }

    public PmsBaseAttrValue setId(String id) {
        this.id = id;
        return this;
    }

    public String getValueName() {
        return valueName;
    }

    public PmsBaseAttrValue setValueName(String valueName) {
        this.valueName = valueName;
        return this;
    }

    public String getAttrId() {
        return attrId;
    }

    public PmsBaseAttrValue setAttrId(String attrId) {
        this.attrId = attrId;
        return this;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public PmsBaseAttrValue setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public String getUrlParam() {
        return urlParam;
    }

    public PmsBaseAttrValue setUrlParam(String urlParam) {
        this.urlParam = urlParam;
        return this;
    }
}
