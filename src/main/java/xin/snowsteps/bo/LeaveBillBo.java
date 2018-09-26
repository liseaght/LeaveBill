package xin.snowsteps.bo;

import java.util.Date;

public class LeaveBillBo {
	
	private Integer leaveId;

    private Integer days;

    private String content;

    private Date starttime;

    private String remark;

    private String username;
    
    private Integer state;

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
		this.content = content;
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
		this.remark = remark;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public LeaveBillBo(Integer leaveId, Integer days, String content, Date starttime, String remark, String username,
			Integer state) {
		super();
		this.leaveId = leaveId;
		this.days = days;
		this.content = content;
		this.starttime = starttime;
		this.remark = remark;
		this.username = username;
		this.state = state;
	}

	public LeaveBillBo() {
		super();
	}

	@Override
	public String toString() {
		return "LeaveBillBo [leaveId=" + leaveId + ", days=" + days + ", content=" + content + ", starttime="
				+ starttime + ", remark=" + remark + ", username=" + username + ", state=" + state + "]";
	}

	

}
