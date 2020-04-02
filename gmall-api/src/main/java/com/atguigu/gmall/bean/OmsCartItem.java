package com.atguigu.gmall.bean;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OmsCartItem implements Serializable{

    @Id
    private String id;
    private String productId;
    private String productSkuId;
    private String memberId;
    private BigDecimal quantity;
    // 商品价格
    private BigDecimal price;
    private String sp1;
    private String sp2;
    private String sp3;
    private String productPic;
    private String productName;
    private String productSubTitle;
    private String productSkuCode;
    private String memberNickname;
//    创建时间
    private Date createDate;
//    更新时间
    private Date modifyDate;
//    删除状态
    private int deleteStatus;
    private String productCategoryId;
//    商标
    private String productBrand;
    private String productSn;
//    商品销售属性json
    private String productAttr;
    
    private String isChecked;

    @Transient
    private BigDecimal totalPrice;

    public String getId() {
        return id;
    }

    public OmsCartItem setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public OmsCartItem setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getProductSkuId() {
        return productSkuId;
    }

    public OmsCartItem setProductSkuId(String productSkuId) {
        this.productSkuId = productSkuId;
        return this;
    }

    public String getMemberId() {
        return memberId;
    }

    public OmsCartItem setMemberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public OmsCartItem setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OmsCartItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getSp1() {
        return sp1;
    }

    public OmsCartItem setSp1(String sp1) {
        this.sp1 = sp1;
        return this;
    }

    public String getSp2() {
        return sp2;
    }

    public OmsCartItem setSp2(String sp2) {
        this.sp2 = sp2;
        return this;
    }

    public String getSp3() {
        return sp3;
    }

    public OmsCartItem setSp3(String sp3) {
        this.sp3 = sp3;
        return this;
    }

    public String getProductPic() {
        return productPic;
    }

    public OmsCartItem setProductPic(String productPic) {
        this.productPic = productPic;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public OmsCartItem setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getProductSubTitle() {
        return productSubTitle;
    }

    public OmsCartItem setProductSubTitle(String productSubTitle) {
        this.productSubTitle = productSubTitle;
        return this;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public OmsCartItem setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
        return this;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public OmsCartItem setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public OmsCartItem setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public OmsCartItem setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public OmsCartItem setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
        return this;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public OmsCartItem setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
        return this;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public OmsCartItem setProductBrand(String productBrand) {
        this.productBrand = productBrand;
        return this;
    }

    public String getProductSn() {
        return productSn;
    }

    public OmsCartItem setProductSn(String productSn) {
        this.productSn = productSn;
        return this;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public OmsCartItem setProductAttr(String productAttr) {
        this.productAttr = productAttr;
        return this;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public OmsCartItem setIsChecked(String isChecked) {
        this.isChecked = isChecked;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OmsCartItem setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
