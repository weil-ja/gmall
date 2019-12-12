package com.atguigu.gmall.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @param
 * @return
 */
public class PmsSkuInfo implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    String id;

    @Column
    String productId;

//    临时数据
    @Transient
    String spuId;

    @Column
    BigDecimal price;

    @Column
    String skuName;

    @Column
    BigDecimal weight;

    @Column
    String skuDesc;

    @Column
    String catalog3Id;

    @Column
    String skuDefaultImg;

    @Transient
    List<PmsSkuImage> skuImageList;

    @Transient
    List<PmsSkuAttrValue> skuAttrValueList;

    @Transient
    List<PmsSkuSaleAttrValue> skuSaleAttrValueList;

    public String getId() {
        return id;
    }

    public PmsSkuInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public PmsSkuInfo setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getSpuId() {
        return spuId;
    }

    public PmsSkuInfo setSpuId(String spuId) {
        this.spuId = spuId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PmsSkuInfo setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getSkuName() {
        return skuName;
    }

    public PmsSkuInfo setSkuName(String skuName) {
        this.skuName = skuName;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public PmsSkuInfo setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public String getSkuDesc() {
        return skuDesc;
    }

    public PmsSkuInfo setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
        return this;
    }

    public String getCatalog3Id() {
        return catalog3Id;
    }

    public PmsSkuInfo setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
        return this;
    }

    public String getSkuDefaultImg() {
        return skuDefaultImg;
    }

    public PmsSkuInfo setSkuDefaultImg(String skuDefaultImg) {
        this.skuDefaultImg = skuDefaultImg;
        return this;
    }

    public List<PmsSkuImage> getSkuImageList() {
        return skuImageList;
    }

    public PmsSkuInfo setSkuImageList(List<PmsSkuImage> skuImageList) {
        this.skuImageList = skuImageList;
        return this;
    }

    public List<PmsSkuAttrValue> getSkuAttrValueList() {
        return skuAttrValueList;
    }

    public PmsSkuInfo setSkuAttrValueList(List<PmsSkuAttrValue> skuAttrValueList) {
        this.skuAttrValueList = skuAttrValueList;
        return this;
    }

    public List<PmsSkuSaleAttrValue> getSkuSaleAttrValueList() {
        return skuSaleAttrValueList;
    }

    public PmsSkuInfo setSkuSaleAttrValueList(List<PmsSkuSaleAttrValue> skuSaleAttrValueList) {
        this.skuSaleAttrValueList = skuSaleAttrValueList;
        return this;
    }
}
