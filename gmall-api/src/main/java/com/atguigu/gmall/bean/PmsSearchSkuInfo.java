package com.atguigu.gmall.bean;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PmsSearchSkuInfo implements Serializable {

    @Id
    private long id;
    private String skuName;
    private String skuDesc;
    private String catalog3Id;
    private BigDecimal price;
    private String skuDefaultImg;
    private double hotScore;
    private String productId;
    private List<PmsSkuAttrValue> skuAttrValueList;

    public long getId() {
        return id;
    }

    public PmsSearchSkuInfo setId(long id) {
        this.id = id;
        return this;
    }

    public String getSkuName() {
        return skuName;
    }

    public PmsSearchSkuInfo setSkuName(String skuName) {
        this.skuName = skuName;
        return this;
    }

    public String getSkuDesc() {
        return skuDesc;
    }

    public PmsSearchSkuInfo setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
        return this;
    }

    public String getCatalog3Id() {
        return catalog3Id;
    }

    public PmsSearchSkuInfo setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PmsSearchSkuInfo setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getSkuDefaultImg() {
        return skuDefaultImg;
    }

    public PmsSearchSkuInfo setSkuDefaultImg(String skuDefaultImg) {
        this.skuDefaultImg = skuDefaultImg;
        return this;
    }

    public double getHotScore() {
        return hotScore;
    }

    public PmsSearchSkuInfo setHotScore(double hotScore) {
        this.hotScore = hotScore;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public PmsSearchSkuInfo setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public List<PmsSkuAttrValue> getSkuAttrValueList() {
        return skuAttrValueList;
    }

    public PmsSearchSkuInfo setSkuAttrValueList(List<PmsSkuAttrValue> skuAttrValueList) {
        this.skuAttrValueList = skuAttrValueList;
        return this;
    }

    @Override
    public String toString() {
        return "PmsSearchSkuInfo{" +
                "id=" + id +
                ", skuName='" + skuName + '\'' +
                ", skuDesc='" + skuDesc + '\'' +
                ", catalog3Id='" + catalog3Id + '\'' +
                ", price=" + price +
                ", skuDefaultImg='" + skuDefaultImg + '\'' +
                ", hotScore=" + hotScore +
                ", productId='" + productId + '\'' +
                ", skuAttrValueList=" + skuAttrValueList +
                '}';
    }
}
