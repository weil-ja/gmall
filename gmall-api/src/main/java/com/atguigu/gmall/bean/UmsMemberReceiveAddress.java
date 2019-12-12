package com.atguigu.gmall.bean;

import javax.persistence.Id;
import java.io.Serializable;

public class UmsMemberReceiveAddress implements Serializable {

    @Id
    private String id;
    private String memberId;
    private String  name;
    private String  phoneNumber;
    private int defaultStatus;
    private String postCode;
    private String province;
    private String city;
    private String region;
    private String detailAddress;

    public String getId() {
        return id;
    }

    public UmsMemberReceiveAddress setId(String id) {
        this.id = id;
        return this;
    }

    public String getMemberId() {
        return memberId;
    }

    public UmsMemberReceiveAddress setMemberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

    public String getName() {
        return name;
    }

    public UmsMemberReceiveAddress setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UmsMemberReceiveAddress setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public int getDefaultStatus() {
        return defaultStatus;
    }

    public UmsMemberReceiveAddress setDefaultStatus(int defaultStatus) {
        this.defaultStatus = defaultStatus;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UmsMemberReceiveAddress setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public UmsMemberReceiveAddress setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UmsMemberReceiveAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public UmsMemberReceiveAddress setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public UmsMemberReceiveAddress setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
        return this;
    }
}
