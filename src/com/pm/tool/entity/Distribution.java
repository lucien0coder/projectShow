package com.pm.tool.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: Distribution.java
 * @Date: 14-3-18
 * @Time: 下午4:01
 */
@Entity
@Table(name="distribution")
public class Distribution implements Serializable {
    private Integer dId;
    private String userAccount;
    private String bandViewId;
    private String userName;
    private String dStatus;
    private String dDesc;
    private String dSelfAppraisal;
    private String dSelfSummary;
    private String dReason;
    private Integer dProgressRate;
    private Timestamp dTime;
    private Integer tId;

    @Id
    @Column(name = "dId", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    @Basic
    @Column(name = "userAccount", nullable = false, insertable = true, updatable = true, length = 32, precision = 0)
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "bandViewId", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    public String getBandViewId() {
        return bandViewId;
    }

    public void setBandViewId(String bandViewId) {
        this.bandViewId = bandViewId;
    }

    @Basic
    @Column(name = "userName", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "dStatus", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public String getdStatus() {
        return dStatus;
    }

    public void setdStatus(String dStatus) {
        this.dStatus = dStatus;
    }

    @Basic
    @Column(name = "dDesc", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    public String getdDesc() {
        return dDesc;
    }

    public void setdDesc(String dDesc) {
        this.dDesc = dDesc;
    }

    @Basic
    @Column(name = "dSelfAppraisal", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    public String getdSelfAppraisal() {
        return dSelfAppraisal;
    }

    public void setdSelfAppraisal(String dSelfAppraisal) {
        this.dSelfAppraisal = dSelfAppraisal;
    }

    @Basic
    @Column(name = "dSelfSummary", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    public String getdSelfSummary() {
        return dSelfSummary;
    }

    public void setdSelfSummary(String dSelfSummary) {
        this.dSelfSummary = dSelfSummary;
    }

    @Basic
    @Column(name = "dReason", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    public String getdReason() {
        return dReason;
    }

    public void setdReason(String dReason) {
        this.dReason = dReason;
    }

    @Basic
    @Column(name = "dProgressRate", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    public Integer getdProgressRate() {
        return dProgressRate;
    }

    public void setdProgressRate(Integer dProgressRate) {
        this.dProgressRate = dProgressRate;
    }

    @Basic
    @Column(name = "dTime", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    public Timestamp getdTime() {
        return dTime;
    }

    public void setdTime(Timestamp dTime) {
        this.dTime = dTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Distribution that = (Distribution) o;

        if (bandViewId != null ? !bandViewId.equals(that.bandViewId) : that.bandViewId != null)
            return false;
        if (dDesc != null ? !dDesc.equals(that.dDesc) : that.dDesc != null)
            return false;
        if (dId != null ? !dId.equals(that.dId) : that.dId != null)
            return false;
        if (dProgressRate != null ? !dProgressRate.equals(that.dProgressRate) : that.dProgressRate != null)
            return false;
        if (dReason != null ? !dReason.equals(that.dReason) : that.dReason != null)
            return false;
        if (dSelfAppraisal != null ? !dSelfAppraisal.equals(that.dSelfAppraisal) : that.dSelfAppraisal != null)
            return false;
        if (dSelfSummary != null ? !dSelfSummary.equals(that.dSelfSummary) : that.dSelfSummary != null)
            return false;
        if (dStatus != null ? !dStatus.equals(that.dStatus) : that.dStatus != null)
            return false;
        if (dTime != null ? !dTime.equals(that.dTime) : that.dTime != null)
            return false;
        if (userAccount != null ? !userAccount.equals(that.userAccount) : that.userAccount != null)
            return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null)
            return false;

        return true;
    }
    @Override
    public int hashCode() {
        int result = dId != null ? dId.hashCode() : 0;
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        result = 31 * result + (bandViewId != null ? bandViewId.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (dStatus != null ? dStatus.hashCode() : 0);
        result = 31 * result + (dDesc != null ? dDesc.hashCode() : 0);
        result = 31 * result + (dSelfAppraisal != null ? dSelfAppraisal.hashCode() : 0);
        result = 31 * result + (dSelfSummary != null ? dSelfSummary.hashCode() : 0);
        result = 31 * result + (dReason != null ? dReason.hashCode() : 0);
        result = 31 * result + (dProgressRate != null ? dProgressRate.hashCode() : 0);
        result = 31 * result + (dTime != null ? dTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "tId", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }
}
