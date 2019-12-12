package com.atguigu.gmall.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @return
 */

public class PmsProductInfo implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String productName;

    @Column
    private String description;

    @Column
    private  String catalog3Id;

    @Transient
    private List<PmsProductSaleAttr> spuSaleAttrList;
    @Transient
    private List<PmsProductImage> spuImageList;

    public String getId() {
        return id;
    }

    public PmsProductInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public PmsProductInfo setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PmsProductInfo setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCatalog3Id() {
        return catalog3Id;
    }

    public PmsProductInfo setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
        return this;
    }

    public List<PmsProductSaleAttr> getSpuSaleAttrList() {
        return spuSaleAttrList;
    }

    public PmsProductInfo setSpuSaleAttrList(List<PmsProductSaleAttr> spuSaleAttrList) {
        this.spuSaleAttrList = spuSaleAttrList;
        return this;
    }

    public List<PmsProductImage> getSpuImageList() {
        return spuImageList;
    }

    public PmsProductInfo setSpuImageList(List<PmsProductImage> spuImageList) {
        this.spuImageList = spuImageList;
        return this;
    }
}


