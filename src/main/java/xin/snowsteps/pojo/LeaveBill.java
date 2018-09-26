package xin.snowsteps.pojo;

import java.util.Date;

public class LeaveBill {
    private Integer leaveId;

    private Integer days;

    private String content;

    private Date starttime;

    private String remark;

    private Integer userId;

    private Integer state;

    public LeaveBill(Integer leaveId, Integer days, String content, Date starttime, String remark, Integer userId, Integer state) {
        this.leaveId = leaveId;
        this.days = days;
        this.content = content;
        this.starttime = starttime;
        this.remark = remark;
        this.userId = userId;
        this.state = state;
    }

    public LeaveBill() {
        super();
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}