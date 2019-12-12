package com.atguigu.gmall.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @return
 */
public class PmsBaseAttrInfo implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;
    @Column
    private String isEnabled;
    @Transient
    List<PmsBaseAttrValue> attrValueList;

    public String getId() {
        return id;
    }

    public PmsBaseAttrInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getAttrName() {
        return attrName;
    }

    public PmsBaseAttrInfo setAttrName(String attrName) {
        this.attrName = attrName;
        return this;
    }

    public String getCatalog3Id() {
        return catalog3Id;
    }

    public PmsBaseAttrInfo setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
        return this;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public PmsBaseAttrInfo setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public List<PmsBaseAttrValue> getAttrValueList() {
        return attrValueList;
    }

    public PmsBaseAttrInfo setAttrValueList(List<PmsBaseAttrValue> attrValueList) {
        this.attrValueList = attrValueList;
        return this;
    }
}
