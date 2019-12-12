package com.atguigu.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @param
 * @return
 */
public class PmsSkuSaleAttrValue implements Serializable {

    @Id
    @Column
    String id;

    @Column
    String skuId;

    @Column
    String saleAttrId;

    @Column
    String saleAttrValueId;

    @Column
    String saleAttrName;

    @Column
    String saleAttrValueName;

    public String getId() {
        return id;
    }

    public PmsSkuSaleAttrValue setId(String id) {
        this.id = id;
        return this;
    }

    public String getSkuId() {
        return skuId;
    }

    public PmsSkuSaleAttrValue setSkuId(String skuId) {
        this.skuId = skuId;
        return this;
    }

    public String getSaleAttrId() {
        return saleAttrId;
    }

    public PmsSkuSaleAttrValue setSaleAttrId(String saleAttrId) {
        this.saleAttrId = saleAttrId;
        return this;
    }

    public String getSaleAttrValueId() {
        return saleAttrValueId;
    }

    public PmsSkuSaleAttrValue setSaleAttrValueId(String saleAttrValueId) {
        this.saleAttrValueId = saleAttrValueId;
        return this;
    }

    public String getSaleAttrName() {
        return saleAttrName;
    }

    public PmsSkuSaleAttrValue setSaleAttrName(String saleAttrName) {
        this.saleAttrName = saleAttrName;
        return this;
    }

    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }

    public PmsSkuSaleAttrValue setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
        return this;
    }
}
