package com.youyu.cotenant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CotenantUserCollege implements Serializable {
    private Long id;

    private Long cotenantUserId;

    private Long cotenantCollegeId;

    private Boolean isDefault;

    private String degree;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCotenantUserId() {
        return cotenantUserId;
    }

    public void setCotenantUserId(Long cotenantUserId) {
        this.cotenantUserId = cotenantUserId;
    }

    public Long getCotenantCollegeId() {
        return cotenantCollegeId;
    }

    public void setCotenantCollegeId(Long cotenantCollegeId) {
        this.cotenantCollegeId = cotenantCollegeId;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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