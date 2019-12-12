package com.atguigu.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @param
 * @return
 */
public class PmsProductImage implements Serializable {

    @Column
    @Id
    private String id;
    @Column
    private String productId;
    @Column
    private String imgName;
    @Column
    private String imgUrl;

    public String getId() {
        return id;
    }

    public PmsProductImage setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public PmsProductImage setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getImgName() {
        return imgName;
    }

    public PmsProductImage setImgName(String imgName) {
        this.imgName = imgName;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public PmsProductImage setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}