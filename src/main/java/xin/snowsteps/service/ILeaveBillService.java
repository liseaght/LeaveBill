package xin.snowsteps.service;

import java.util.List;
import xin.snowsteps.bo.LeaveBillBo;
import xin.snowsteps.pojo.LeaveBill;

public interface ILeaveBillService {
	
	public List<LeaveBillBo> queryLeaveBillList(String username);
	
	public void saveLeaveBill(String days, String content, String remark, String username);
	
	public void delLeaveBill(Integer leaveId);
	
	public LeaveBill getLeaveBill(Integer leaveId);

	public void modLeaveBill(Integer leaveId, String days, String content, String remark);

	public void reqLeaveBill(Integer leaveId, String inputUser);

	public LeaveBill getInfo(String taskId);

	void completeTask(String leaveId);

	public LeaveBill getLeaveBillByLeaveId(Integer leaveId);

}
