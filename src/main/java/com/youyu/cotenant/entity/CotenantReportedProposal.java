package com.youyu.cotenant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CotenantReportedProposal implements Serializable {
    private Long id;

    private Long cotenantUserId;

    private String content;

    private Integer proposeType;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProposeType() {
        return proposeType;
    }

    public void setProposeType(Integer proposeType) {
        this.proposeType = proposeType;
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