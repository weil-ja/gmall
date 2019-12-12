package com.atguigu.gmall.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class UmsMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String memberLevelId;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private int status;
    private Date createTime;
    private String icon;
    private int gender;
    private Date birthday;
    private String city;
    private String job;
    private String personalizedSignature;
    private int sourceType;
    private int integration;
    private int growth;
    private int luckeyCount;
    private int historyIntegration;

    public String getId() {
        return id;
    }

    public UmsMember setId(String id) {
        this.id = id;
        return this;
    }

    public String getMemberLevelId() {
        return memberLevelId;
    }

    public UmsMember setMemberLevelId(String memberLevelId) {
        this.memberLevelId = memberLevelId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UmsMember setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UmsMember setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UmsMember setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UmsMember setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public UmsMember setStatus(int status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UmsMember setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public UmsMember setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public int getGender() {
        return gender;
    }

    public UmsMember setGender(int gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public UmsMember setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UmsMember setCity(String city) {
        this.city = city;
        return this;
    }

    public String getJob() {
        return job;
    }

    public UmsMember setJob(String job) {
        this.job = job;
        return this;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public UmsMember setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
        return this;
    }

    public int getSourceType() {
        return sourceType;
    }

    public UmsMember setSourceType(int sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public int getIntegration() {
        return integration;
    }

    public UmsMember setIntegration(int integration) {
        this.integration = integration;
        return this;
    }

    public int getGrowth() {
        return growth;
    }

    public UmsMember setGrowth(int growth) {
        this.growth = growth;
        return this;
    }

    public int getLuckeyCount() {
        return luckeyCount;
    }

    public UmsMember setLuckeyCount(int luckeyCount) {
        this.luckeyCount = luckeyCount;
        return this;
    }

    public int getHistoryIntegration() {
        return historyIntegration;
    }

    public UmsMember setHistoryIntegration(int historyIntegration) {
        this.historyIntegration = historyIntegration;
        return this;
    }
}
