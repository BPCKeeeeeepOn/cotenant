package com.youyu.cotenant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CotenantCollege implements Serializable {
    private Long id;

    private String collegeName;

    private String collegeDepartment;

    private String province;

    private String city;

    private String remark;

    private String cpllegeLevel;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeDepartment() {
        return collegeDepartment;
    }

    public void setCollegeDepartment(String collegeDepartment) {
        this.collegeDepartment = collegeDepartment;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCpllegeLevel() {
        return cpllegeLevel;
    }

    public void setCpllegeLevel(String cpllegeLevel) {
        this.cpllegeLevel = cpllegeLevel;
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
}