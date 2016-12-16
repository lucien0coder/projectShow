package com.pm.tool.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: Project.java
 * @Date: 14-3-18
 * @Time: 下午4:01
 */
@Entity
@Table(name="project")
public class Project implements Serializable {
	private Integer pId;
    private String bobjid;
    private String pName;
    private String pDesc;
    private String pOwnerAccount;
    private String pLeaderAccount;
    private Timestamp pCreateTime;
    private Timestamp pStartTime;
    private Timestamp pFinishTime;
    private Timestamp pExpectFinishTime;
    
	private BigDecimal pProgress = BigDecimal.valueOf(0);
    private String pSummary;
    private String pProblem;
    private String employee_name;

    public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	//timeLine
    private String id;
    private String title;
    private String description;
    private String focus_date = "today";
    private String initial_zoom = "16";
    private Boolean open_modal = false;
    private String bottom;
    private List<Task> events;

    private Integer temId;

    //private List<Integer> initial_timelines;

    private String project = "project";

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Id
    @Column(name = "pId", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
        id = pId.toString();
        /*initial_timelines = new ArrayList<Integer>();
        initial_timelines.add(pId);*/
    }

    @Basic
    @Column(name = "bobjid", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
         public String getBobjid() {
        return bobjid;
    }

    public void setBobjid(String bobjid) {
        this.bobjid = bobjid;
    }

    @Basic
    @Column(name = "pName", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
        title = pName;
    }

    @Basic
    @Column(name = "pDesc", nullable = false, insertable = true, updatable = true, length = 2147483647, precision = 0)
    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
        //description = pDesc;
    }

    @Basic
    @Column(name = "pOwnerAccount", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    public String getpOwnerAccount() {
        return pOwnerAccount;
    }

    public void setpOwnerAccount(String pOwnerAccount) {
        this.pOwnerAccount = pOwnerAccount;
    }

    @Basic
    @Column(name = "pLeaderAccount", nullable = false, insertable = true, updatable = true, length = 32, precision = 0)
    public String getpLeaderAccount() {
        return pLeaderAccount;
    }

    public void setpLeaderAccount(String pLeaderAccount) {
        this.pLeaderAccount = pLeaderAccount;
    }

    @Basic
    @Column(name = "pCreateTime", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Timestamp getpCreateTime() {
        return pCreateTime;
    }

    public void setpCreateTime(Timestamp pCreateTime) {
        this.pCreateTime = pCreateTime;
    }

    @Basic
    @Column(name = "pStartTime", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    public Timestamp getpStartTime() {
        return pStartTime;
    }

    public void setpStartTime(Timestamp pStartTime) {
        this.pStartTime = pStartTime;
    }

    @Basic
    @Column(name = "pFinishTime", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    public Timestamp getpFinishTime() {
        return pFinishTime;
    }

    public void setpFinishTime(Timestamp pFinishTime) {
        this.pFinishTime = pFinishTime;
    }
    
    @Basic
    @Column(name = "pExpectFinishTime", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    public Timestamp getpExpectFinishTime() {
		return pExpectFinishTime;
	}

	public void setpExpectFinishTime(Timestamp pExpectFinishTime) {
		this.pExpectFinishTime = pExpectFinishTime;
	}

    @Basic
    @Column(name = "pProgress", nullable = false, insertable = true, updatable = true, length = 3, precision = 2)
    public BigDecimal getpProgress() {
        return pProgress;
    }

    public void setpProgress(BigDecimal pProgress) {
        this.pProgress = pProgress;
    }

    @Basic
    @Column(name = "pSummary", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    public String getpSummary() {
        return pSummary;
    }

    public void setpSummary(String pSummary) {
        this.pSummary = pSummary;
    }

    @Basic
    @Column(name = "pProblem", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    public String getpProblem() {
        return pProblem;
    }

    public void setpProblem(String pProblem) {
        this.pProblem = pProblem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        try {
            pId = Integer.valueOf(id);
        } catch (Exception e) {
            pId = -1;
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        pName = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        pDesc = description;
    }

    public String getFocus_date() {
        return focus_date;
    }

    public void setFocus_date(String focus_date) {
        this.focus_date = focus_date;
    }

    public String getInitial_zoom() {
        return initial_zoom;
    }

    public void setInitial_zoom(String initial_zoom) {
        this.initial_zoom = initial_zoom;
    }

    public Boolean getOpen_modal() {
        return open_modal;
    }

    public void setOpen_modal(Boolean open_modal) {
        this.open_modal = open_modal;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public List<Task> getEvents() {
        return events;
    }

    public void setEvents(List<Task> events) {
        this.events = events;
    }

    /*public List<Integer> getInitial_timelines() {
        return initial_timelines;
    }

    public void setInitial_timelines(List<Integer> initial_timelines) {
        this.initial_timelines = initial_timelines;
    }
*/
    public Integer getTemId() {
        return temId;
    }

    public void setTemId(Integer temId) {
        this.temId = temId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (bobjid != null ? !bobjid.equals(project.bobjid) : project.bobjid != null)
            return false;
        if (pCreateTime != null ? !pCreateTime.equals(project.pCreateTime) : project.pCreateTime != null)
            return false;
        if (pDesc != null ? !pDesc.equals(project.pDesc) : project.pDesc != null)
            return false;
        if (pFinishTime != null ? !pFinishTime.equals(project.pFinishTime) : project.pFinishTime != null)
            return false;
        if (pId != null ? !pId.equals(project.pId) : project.pId != null)
            return false;
        if (pLeaderAccount != null ? !pLeaderAccount.equals(project.pLeaderAccount) : project.pLeaderAccount != null)
            return false;
        if (pName != null ? !pName.equals(project.pName) : project.pName != null)
            return false;
        if (pOwnerAccount != null ? !pOwnerAccount.equals(project.pOwnerAccount) : project.pOwnerAccount != null)
            return false;
        if (pProblem != null ? !pProblem.equals(project.pProblem) : project.pProblem != null)
            return false;
        if (pProgress != null ? !pProgress.equals(project.pProgress) : project.pProgress != null)
            return false;
        if (pStartTime != null ? !pStartTime.equals(project.pStartTime) : project.pStartTime != null)
            return false;
        if (pSummary != null ? !pSummary.equals(project.pSummary) : project.pSummary != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pId != null ? pId.hashCode() : 0;
        result = 31 * result + (bobjid != null ? bobjid.hashCode() : 0);
        result = 31 * result + (pName != null ? pName.hashCode() : 0);
        result = 31 * result + (pDesc != null ? pDesc.hashCode() : 0);
        result = 31 * result + (pOwnerAccount != null ? pOwnerAccount.hashCode() : 0);
        result = 31 * result + (pLeaderAccount != null ? pLeaderAccount.hashCode() : 0);
        result = 31 * result + (pCreateTime != null ? pCreateTime.hashCode() : 0);
        result = 31 * result + (pStartTime != null ? pStartTime.hashCode() : 0);
        result = 31 * result + (pFinishTime != null ? pFinishTime.hashCode() : 0);
        result = 31 * result + (pProgress != null ? pProgress.hashCode() : 0);
        result = 31 * result + (pSummary != null ? pSummary.hashCode() : 0);
        result = 31 * result + (pProblem != null ? pProblem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "pId=" + pId +
                ", bobjid='" + bobjid + '\'' +
                ", pName='" + pName + '\'' +
                ", pDesc='" + pDesc + '\'' +
                ", pOwnerAccount='" + pOwnerAccount + '\'' +
                ", pLeaderAccount='" + pLeaderAccount + '\'' +
                ", pCreateTime=" + pCreateTime +
                ", pStartTime=" + pStartTime +
                ", pFinishTime=" + pFinishTime +
                ", pProgress=" + pProgress +
                ", pSummary='" + pSummary + '\'' +
                ", pProblem='" + pProblem + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", focus_date=" + focus_date +
                ", initial_zoom=" + initial_zoom +
                ", open_modal=" + open_modal +
                ", bottom=" + bottom +
                ", events=" + events +
                '}';
    }
}
