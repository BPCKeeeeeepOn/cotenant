package com.youyu.cotenant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CotenantGroup implements Serializable {
    private Long id;

    private String title;

    private String province;

    private String city;

    private String district;

    private String addressName;

    private String addressDetail;

    private String addressLongitude;

    private String addressLatitude;

    private Integer cotenantCount;

    private Integer cotenantType;

    private Integer status;

    private String cotenantDescription;

    private String chamberImgUrl;

    private String chamberDescription;

    private String chamberVideoUrl;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressLongitude() {
        return addressLongitude;
    }

    public void setAddressLongitude(String addressLongitude) {
        this.addressLongitude = addressLongitude;
    }

    public String getAddressLatitude() {
        return addressLatitude;
    }

    public void setAddressLatitude(String addressLatitude) {
        this.addressLatitude = addressLatitude;
    }

    public Integer getCotenantCount() {
        return cotenantCount;
    }

    public void setCotenantCount(Integer cotenantCount) {
        this.cotenantCount = cotenantCount;
    }

    public Integer getCotenantType() {
        return cotenantType;
    }

    public void setCotenantType(Integer cotenantType) {
        this.cotenantType = cotenantType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCotenantDescription() {
        return cotenantDescription;
    }

    public void setCotenantDescription(String cotenantDescription) {
        this.cotenantDescription = cotenantDescription;
    }

    public String getChamberImgUrl() {
        return chamberImgUrl;
    }

    public void setChamberImgUrl(String chamberImgUrl) {
        this.chamberImgUrl = chamberImgUrl;
    }

    public String getChamberDescription() {
        return chamberDescription;
    }

    public void setChamberDescription(String chamberDescription) {
        this.chamberDescription = chamberDescription;
    }

    public String getChamberVideoUrl() {
        return chamberVideoUrl;
    }

    public void setChamberVideoUrl(String chamberVideoUrl) {
        this.chamberVideoUrl = chamberVideoUrl;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}