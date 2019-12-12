package com.atguigu.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

public class PmsProductSaleAttr implements Serializable {

    @Id
    @Column
    String id ;

    @Column
    String productId;

    @Column
    String saleAttrId;

    @Column
    String saleAttrName;


    @Transient
    List<PmsProductSaleAttrValue> spuSaleAttrValueList;

    public String getId() {
        return id;
    }

    public PmsProductSaleAttr setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public PmsProductSaleAttr setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getSaleAttrId() {
        return saleAttrId;
    }

    public PmsProductSaleAttr setSaleAttrId(String saleAttrId) {
        this.saleAttrId = saleAttrId;
        return this;
    }

    public String getSaleAttrName() {
        return saleAttrName;
    }

    public PmsProductSaleAttr setSaleAttrName(String saleAttrName) {
        this.saleAttrName = saleAttrName;
        return this;
    }

    public List<PmsProductSaleAttrValue> getSpuSaleAttrValueList() {
        return spuSaleAttrValueList;
    }

    public PmsProductSaleAttr setSpuSaleAttrValueList(List<PmsProductSaleAttrValue> spuSaleAttrValueList) {
        this.spuSaleAttrValueList = spuSaleAttrValueList;
        return this;
    }
}
