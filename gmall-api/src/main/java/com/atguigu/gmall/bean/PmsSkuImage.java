package com.atguigu.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @param
 * @return
 */
public class PmsSkuImage implements Serializable {

    @Id
    @Column
    String id;
    @Column
    String skuId;
    @Column
    String imgName;
    @Column
    String imgUrl;
    @Column
    String spuImgId;
    @Column
    String isDefault;

    public String getId() {
        return id;
    }

    public PmsSkuImage setId(String id) {
        this.id = id;
        return this;
    }

    public String getSkuId() {
        return skuId;
    }

    public PmsSkuImage setSkuId(String skuId) {
        this.skuId = skuId;
        return this;
    }

    public String getImgName() {
        return imgName;
    }

    public PmsSkuImage setImgName(String imgName) {
        this.imgName = imgName;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public PmsSkuImage setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getSpuImgId() {
        return spuImgId;
    }

    public PmsSkuImage setSpuImgId(String spuImgId) {
        this.spuImgId = spuImgId;
        return this;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public PmsSkuImage setIsDefault(String isDefault) {
        this.isDefault = isDefault;
        return this;
    }
}