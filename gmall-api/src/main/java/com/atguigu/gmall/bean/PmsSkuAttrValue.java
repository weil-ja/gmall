package com.atguigu.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PmsSkuAttrValue implements Serializable {

    @Id
    @Column
    String id;

    @Column
    String attrId;

    @Column
    String valueId;

    @Column
    String skuId;

    public String getId() {
        return id;
    }

    public PmsSkuAttrValue setId(String id) {
        this.id = id;
        return this;
    }

    public String getAttrId() {
        return attrId;
    }

    public PmsSkuAttrValue setAttrId(String attrId) {
        this.attrId = attrId;
        return this;
    }

    public String getValueId() {
        return valueId;
    }

    public PmsSkuAttrValue setValueId(String valueId) {
        this.valueId = valueId;
        return this;
    }

    public String getSkuId() {
        return skuId;
    }

    public PmsSkuAttrValue setSkuId(String skuId) {
        this.skuId = skuId;
        return this;
    }
}
