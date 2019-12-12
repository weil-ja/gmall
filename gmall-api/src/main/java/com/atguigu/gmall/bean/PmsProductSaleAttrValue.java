package com.atguigu.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

public class PmsProductSaleAttrValue implements Serializable {
    @Id
    @Column
    String id ;

    @Column
    String productId;

    @Column
    String saleAttrId;

    @Column
    String saleAttrValueName;

    @Transient
    String isChecked;

    public String getId() {
        return id;
    }

    public PmsProductSaleAttrValue setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public PmsProductSaleAttrValue setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getSaleAttrId() {
        return saleAttrId;
    }

    public PmsProductSaleAttrValue setSaleAttrId(String saleAttrId) {
        this.saleAttrId = saleAttrId;
        return this;
    }

    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }

    public PmsProductSaleAttrValue setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
        return this;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public PmsProductSaleAttrValue setIsChecked(String isChecked) {
        this.isChecked = isChecked;
        return this;
    }
}
