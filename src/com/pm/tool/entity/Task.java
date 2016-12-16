package com.pm.tool.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: Task.java
 * @Date: 14-3-18
 * @Time: 下午4:01
 */
@Entity
@Table(name="task")
public class Task implements Serializable {

    private Integer tId;
    private String tName;
    private String tDesc;
    private Timestamp tCreateTime;
    private String tPlanStartTime;
    private String tPlanFinishTime;
    private String tFactStartTime;
    private String tFactFinishTime;
    private String tExpectFinishTime;
    private String tDistributeStatus;
    private String tDevelopStatus;
    private Integer tProgressRate;
    private String tTool;
    private Timestamp tProgressTime;
    private Integer pId;
    private String tType;
    private Double tCostTime;
    private String bandInfo;
    private String tCreator;
    
    

	//event
    private String id;
    private String title;
    private String description;
    private String startdate;
    private String enddate;
    private String importance = "40";
    private String image;
    private String link;
    private String date_display;
    private String icon;
    private List<Integer> timelines;

    private List<String> distribute;

    private Boolean isAll = false;

    @Id
    @Column(name = "tId", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
        this.id = tId.toString();
    }

    @Basic
    @Column(name = "tName", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
        this.title = tName;
    }

    @Basic
    @Column(name = "tDesc", nullable = false, insertable = true, updatable = true, length = 2147483647, precision = 0)
    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    @Basic
    @Column(name = "tCreateTime", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Timestamp gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Timestamp tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    @Basic
    @Column(name = "tPlanStartTime", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public String gettPlanStartTime() {
        return tPlanStartTime;
    }

    public void settPlanStartTime(String tPlanStartTime) {
        startdate = tPlanStartTime;
        this.tPlanStartTime = tPlanStartTime;
    }

    @Basic
    @Column(name = "tPlanFinishTime", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public String gettPlanFinishTime() {
        return tPlanFinishTime;
    }

    public void settPlanFinishTime(String tPlanFinishTime) {
        enddate = tPlanFinishTime;
        this.tPlanFinishTime = tPlanFinishTime;
    }

    @Basic
    @Column(name = "tFactStartTime", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    public String gettFactStartTime() {

        return tFactStartTime;
    }

    public void settFactStartTime(String tFactStartTime) {
        this.tFactStartTime = tFactStartTime;
    }

    @Basic
    @Column(name = "tFactFinishTime", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    public String gettFactFinishTime() {
        return tFactFinishTime;
    }

    public void settFactFinishTime(String tFactFinishTime) {
        this.tFactFinishTime = tFactFinishTime;
    }

    @Basic
    @Column(name = "tExpectFinishTime", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    public String gettExpectFinishTime() {
        return tExpectFinishTime;
    }

    public void settExpectFinishTime(String tExpectFinishTime) {
        this.tExpectFinishTime = tExpectFinishTime;
    }

    @Basic
    @Column(name = "tDistributeStatus", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public String gettDistributeStatus() {
        return tDistributeStatus;
    }

    public void settDistributeStatus(String tDistributeStatus) {
        this.tDistributeStatus = tDistributeStatus;
    }

    @Basic
    @Column(name = "tDevelopStatus", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public String gettDevelopStatus() {
        return tDevelopStatus;
    }

    public void settDevelopStatus(String tDevelopStatus) {
        this.tDevelopStatus = tDevelopStatus;
    }

    @Basic
    @Column(name = "tProgressRate", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public Integer gettProgressRate() {
        return tProgressRate;
    }

    public void settProgressRate(Integer tProgressRate) {
        this.tProgressRate = tProgressRate;
    }

    @Basic
    @Column(name = "tTool", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    public String gettTool() {
        return tTool;
    }

    public void settTool(String tTool) {
        this.tTool = tTool;
    }
    
    @Basic
    @Column(name = "tType", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }
   
    @Basic
    @Column(name = "tCostTime", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    public Double gettCostTime() {
        return tCostTime;
    }

    public void settCostTime(Double tCostTime) {
        this.tCostTime = tCostTime;
    }
    
    @Basic
    @Column(name = "bandInfo", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    public String getBandInfo() {
        return bandInfo;
    }

    public void setBandInfo(String bandInfo) {
        this.bandInfo = bandInfo;
    }

    @Basic
    @Column(name = "tProgressTime", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    public Timestamp gettProgressTime() {
        return tProgressTime;
    }

    public void settProgressTime(Timestamp tProgressTime) {
        this.tProgressTime = tProgressTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.tId = Integer.valueOf(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.tName = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate_display() {
        return date_display;
    }

    public void setDate_display(String date_display) {
        this.date_display = date_display;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Integer> getTimelines() {
        return timelines;
    }

    public void setTimelines(List<Integer> timelines) {
        this.timelines = timelines;
    }

    public Boolean getIsAll() {
        return isAll;
    }

    public void setIsAll(Boolean isAll) {
        this.isAll = isAll;
    }
    @Basic
    @Column(name = "tCreator", insertable = true, updatable = true, length = 20, precision = 0)
    public String gettCreator() {
		return tCreator;
	}

	public void settCreator(String tCreator) {
		this.tCreator = tCreator;
	}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (tCreateTime != null ? !tCreateTime.equals(task.tCreateTime) : task.tCreateTime != null)
            return false;
        if (tDesc != null ? !tDesc.equals(task.tDesc) : task.tDesc != null)
            return false;
        if (tDevelopStatus != null ? !tDevelopStatus.equals(task.tDevelopStatus) : task.tDevelopStatus != null)
            return false;
        if (tDistributeStatus != null ? !tDistributeStatus.equals(task.tDistributeStatus) : task.tDistributeStatus != null)
            return false;
        if (tExpectFinishTime != null ? !tExpectFinishTime.equals(task.tExpectFinishTime) : task.tExpectFinishTime != null)
            return false;
        if (tFactFinishTime != null ? !tFactFinishTime.equals(task.tFactFinishTime) : task.tFactFinishTime != null)
            return false;
        if (tFactStartTime != null ? !tFactStartTime.equals(task.tFactStartTime) : task.tFactStartTime != null)
            return false;
        if (tId != null ? !tId.equals(task.tId) : task.tId != null)
            return false;
        if (tName != null ? !tName.equals(task.tName) : task.tName != null)
            return false;
        if (tPlanFinishTime != null ? !tPlanFinishTime.equals(task.tPlanFinishTime) : task.tPlanFinishTime != null)
            return false;
        if (tPlanStartTime != null ? !tPlanStartTime.equals(task.tPlanStartTime) : task.tPlanStartTime != null)
            return false;
        if (tProgressRate != null ? !tProgressRate.equals(task.tProgressRate) : task.tProgressRate != null)
            return false;
        if (tProgressTime != null ? !tProgressTime.equals(task.tProgressTime) : task.tProgressTime != null)
            return false;
        if (tTool != null ? !tTool.equals(task.tTool) : task.tTool != null)
            return false;
        if (tType != null ? !tType.equals(task.tType) : task.tType != null)
            return false;
        if (tCostTime != null ? !tCostTime.equals(task.tType) : task.tCostTime != null)
            return false;
        if (bandInfo != null ? !bandInfo.equals(task.tType) : task.bandInfo != null)
            return false;
        if (tCreator != null ? !tCreator.equals(task.tCreator) : task.tCreator != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = tId != null ? tId.hashCode() : 0;
        result = 31 * result + (tName != null ? tName.hashCode() : 0);
        result = 31 * result + (tDesc != null ? tDesc.hashCode() : 0);
        result = 31 * result + (tCreateTime != null ? tCreateTime.hashCode() : 0);
        result = 31 * result + (tPlanStartTime != null ? tPlanStartTime.hashCode() : 0);
        result = 31 * result + (tPlanFinishTime != null ? tPlanFinishTime.hashCode() : 0);
        result = 31 * result + (tFactStartTime != null ? tFactStartTime.hashCode() : 0);
        result = 31 * result + (tFactFinishTime != null ? tFactFinishTime.hashCode() : 0);
        result = 31 * result + (tExpectFinishTime != null ? tExpectFinishTime.hashCode() : 0);
        result = 31 * result + (tDistributeStatus != null ? tDistributeStatus.hashCode() : 0);
        result = 31 * result + (tDevelopStatus != null ? tDevelopStatus.hashCode() : 0);
        result = 31 * result + (tProgressRate != null ? tProgressRate.hashCode() : 0);
        result = 31 * result + (tTool != null ? tTool.hashCode() : 0);
        result = 31 * result + (tType != null ? tType.hashCode() : 0);
        result = 31 * result + (tCostTime != null ? tCostTime.hashCode() : 0);
        result = 31 * result + (bandInfo != null ? bandInfo.hashCode() : 0);
        result = 31 * result + (tProgressTime != null ? tProgressTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "pId", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public List<String> getDistribute() {
        return distribute;
    }

    public void setDistribute(List<String> distribute) {
        this.distribute = distribute;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tId=" + tId +
                ", tName='" + tName + '\'' +
                ", tDesc='" + tDesc + '\'' +
                ", tCreateTime=" + tCreateTime +
                ", tPlanStartTime=" + tPlanStartTime +
                ", tPlanFinishTime=" + tPlanFinishTime +
                ", tFactStartTime=" + tFactStartTime +
                ", tFactFinishTime=" + tFactFinishTime +
                ", tExpectFinishTime=" + tExpectFinishTime +
                ", tDistributeStatus='" + tDistributeStatus + '\'' +
                ", tDevelopStatus='" + tDevelopStatus + '\'' +
                ", tProgressRate=" + tProgressRate +
                ", tTool='" + tTool + '\'' +
                 ", tType='" + tType + '\'' +
                 ", tCostTime='" + tCostTime + '\'' +
                 ", bandInfo='" + bandInfo + '\'' +
                ", tProgressTime=" + tProgressTime +
                ", pId=" + pId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", importance=" + importance +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", date_display='" + date_display + '\'' +
                ", icon='" + icon + '\'' +
                ", tCreator='" + tCreator + '\'' +
                '}';
    }
}
