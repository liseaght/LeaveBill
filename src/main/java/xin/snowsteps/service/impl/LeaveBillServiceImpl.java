package xin.snowsteps.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.snowsteps.bo.LeaveBillBo;
import xin.snowsteps.dao.EmployeMapper;
import xin.snowsteps.dao.LeaveBillMapper;
import xin.snowsteps.pojo.LeaveBill;
import xin.snowsteps.service.ILeaveBillService;
import xin.snowsteps.service.IWorkFlowService;

@Service("iLeaveBillService")
public class LeaveBillServiceImpl implements ILeaveBillService{
	
	private static final Logger logger = LoggerFactory.getLogger(LeaveBillServiceImpl.class);

	
	@Autowired
	private LeaveBillMapper leaveBillMapper;
	
	@Autowired
	private EmployeMapper employeMapper;
	
	@Autowired
	private IWorkFlowService iWorkFlowService;
	

	@Override
	public List<LeaveBillBo> queryLeaveBillList(String userName) {
		Integer userId = employeMapper.selectByUsername(userName).getUserId();
		
		List<LeaveBill> billList = leaveBillMapper.selectListByUserId(userId);
		List<LeaveBillBo> list = new ArrayList<>();
		for (LeaveBill leaveBill : billList) {
			LeaveBillBo leaveBillBo = new LeaveBillBo();
			leaveBillBo.setLeaveId(leaveBill.getLeaveId());
			leaveBillBo.setDays(leaveBill.getDays());
			leaveBillBo.setContent(leaveBill.getContent());
			leaveBillBo.setStarttime(leaveBill.getStarttime());
			leaveBillBo.setRemark(leaveBill.getRemark());
			leaveBillBo.setUsername(userName);
			leaveBillBo.setState(leaveBill.getState());
			
			list.add(leaveBillBo);
		}		
		return list;
	}

	@Override
	public void saveLeaveBill(String days, String content, String remark, String username) {
		Integer userId = employeMapper.selectByUsername(username).getUserId();
		LeaveBill leaveBill = new LeaveBill();
		leaveBill.setUserId(userId);
		leaveBill.setState(0);
		leaveBill.setRemark(remark);
		leaveBill.setDays(Integer.parseInt(days));
		leaveBill.setContent(content);
		
		leaveBillMapper.insertSelective(leaveBill);
	}

	@Override
	public void delLeaveBill(Integer leaveId) {	
		leaveBillMapper.deleteByPrimaryKey(leaveId);	
	}

	@Override
	public LeaveBill getLeaveBill(Integer leaveId) {
		return leaveBillMapper.selectByPrimaryKey(leaveId);	
	}

	@Override
	public void modLeaveBill(Integer leaveId, String days, String content, String remark) {
		LeaveBill leaveBill = new LeaveBill();
		leaveBill.setLeaveId(leaveId);
		leaveBill.setDays(Integer.parseInt(days));
		leaveBill.setContent(content);
		leaveBill.setRemark(remark);
		
		leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
	}

	@Override
	public void reqLeaveBill(Integer leaveId, String inputUser) {
		LeaveBill leaveBill = leaveBillMapper.selectByPrimaryKey(leaveId);		
		String key = leaveBill.getClass().getSimpleName();
		iWorkFlowService.startProcess(leaveId, inputUser, key);	
		
		leaveBill.setState(1);
		leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
	}

	@Override
	public LeaveBill getInfo(String taskId) {
		//使用任务id获取请假单id
		String leaveId = iWorkFlowService.getLeaveBillIdByTaskId(taskId);
		//System.out.println("请假单Id: "+leaveId);
		return leaveBillMapper.selectByPrimaryKey(Integer.parseInt(leaveId));	
	}
	
	@Override
	public void completeTask(String leaveId) {
		//流程结束之后，更新状态
		LeaveBill leaveBill = leaveBillMapper.selectByPrimaryKey(Integer.parseInt(leaveId));
		leaveBill.setState(2);
		leaveBillMapper.updateByPrimaryKeySelective(leaveBill);	
		logger.debug("完成任务 "+leaveId);
	}

	@Override
	public LeaveBill getLeaveBillByLeaveId(Integer leaveId) {
		return leaveBillMapper.selectByPrimaryKey(leaveId);
	}

	

}
