package xin.snowsteps.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.task.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.snowsteps.bo.LeaveBillBo;
import xin.snowsteps.common.Const;
import xin.snowsteps.pojo.LeaveBill;
import xin.snowsteps.service.ILeaveBillService;
import xin.snowsteps.service.IWorkFlowService;

@Controller
public class LeaveBillController {
	
	private static final Logger logger = LoggerFactory.getLogger(LeaveBillController.class);

	@Autowired
	private ILeaveBillService iLeaveBillService;
	
	@Autowired
	private IWorkFlowService iWorkFlowService;
	
	@RequestMapping("leaveBill_list.do")
	public String leaveBill(HttpSession session) {
		String username = (String) session.getAttribute(Const.CURRENT_USER);
		List<LeaveBillBo> leaveBillList = iLeaveBillService.queryLeaveBillList(username);
		session.setAttribute("leaveBillList", leaveBillList);
		return "leaveBill/list";
	}
	
	@RequestMapping("addLeaveRequest.do")
	public String addLeaveBillPage() {	
		return "leaveBill/input";	
	}
	
	@RequestMapping("leaveBill_save.do")
	@ResponseBody
	public String saveLeaveBill(String days, String content, String remark, HttpSession session) {
		String username = (String) session.getAttribute(Const.CURRENT_USER);
		iLeaveBillService.saveLeaveBill(days, content, remark, username);
		logger.info(username+"添加请假单成功: "+"[天数:"+days+","+"内容:"+content+","+"备注:"+remark+"]");
		return "添加请假单成功...";	
	}
	
	@RequestMapping("leaveBill_delete.do")
	@ResponseBody
	public String deleteLeaveBill(Integer leaveId) {
		iLeaveBillService.delLeaveBill(leaveId);
		logger.info("成功删除请假单"+leaveId);
		return "删除成功...";	
	}
	
	@RequestMapping("leaveBill_update_page.do")
	public String updateLeaveBillPage(Integer leaveId, HttpSession session) {	
		LeaveBill leaveBill = iLeaveBillService.getLeaveBill(leaveId);
		session.setAttribute("modLeaveBill", leaveBill);
		return "leaveBill/update";	
	}
	
	@RequestMapping("leaveBill_update.do")
	@ResponseBody
	public String updateLeaveBill(Integer leaveId, String days, String content, String remark, HttpSession session) {
		iLeaveBillService.modLeaveBill(leaveId,days,content,remark);
		session.removeAttribute("modLeaveBill");
		return "修改成功...";	
	}
	
	
	@RequestMapping("leaveBill_request.do")
	@ResponseBody
	public String requestLeaveBill(Integer leaveId, HttpSession session) {
		String inputUser = (String) session.getAttribute(Const.CURRENT_USER);
		iLeaveBillService.reqLeaveBill(leaveId, inputUser);
		logger.info(inputUser+"申请请假成功");
		return "申请请假成功...";	
	}
	
	@RequestMapping("leaveBill_task_work.do")
	public String workTask(String taskId, HttpSession session) {
		//保存当前需要办理的任务Id
		session.setAttribute(Const.CURRENT_TASKID, taskId);
		//获取请假单信息
		LeaveBill leaveBill = iLeaveBillService.getInfo(taskId);
		session.setAttribute("leaveBillTask", leaveBill);
		
		//获取当前任务完成之后的连线名称
		List<String> outComeList = iWorkFlowService.getOutComeInfo(taskId);
		session.setAttribute("outComeList", outComeList);
		
		//获取历史批注信息
		List<Comment> commentList = iWorkFlowService.getComments(taskId);
		session.setAttribute("commentList", commentList);
		
		return "workflow/taskForm";	
	}
	
	@RequestMapping("leaveBill_task_ complete.do")
	@ResponseBody
	public String completeTask(String comment, String outcome, HttpSession session) {
		//从session中取taskId，在办理指定任务时设置
		String taskId = (String) session.getAttribute(Const.CURRENT_TASKID);
		String userName = (String) session.getAttribute(Const.CURRENT_USER);
		//完成任务
		iWorkFlowService.completeTask(taskId, comment, outcome, userName);
		
		return "任务已成功办理...";	
	}
	
	@RequestMapping("leaveBill_task_reviewe.do")
	public String revieweTask(Integer leaveId, HttpSession session) {
		
		LeaveBill leaveBill = iLeaveBillService.getLeaveBillByLeaveId(leaveId);
		session.setAttribute("leaveBillHis", leaveBill);
		
		List<Comment> hisComment = iWorkFlowService.getHisComment(leaveBill,leaveId);
		session.setAttribute("hisComment", hisComment);
		
		return "workflow/taskFormHis";	
	}
	
	
	@RequestMapping("leaveBill_task_pic.do")
	public String seeTaskPic(Integer taskId, HttpSession session) {
		Map<String,Object> taskPic = iWorkFlowService.getTaskPicByTaskId(taskId);
		session.setAttribute("dpid", taskPic.get("dpid"));
		session.setAttribute("drn", taskPic.get("drn"));
		session.setAttribute("x", taskPic.get("x"));
		session.setAttribute("y", taskPic.get("y"));
		session.setAttribute("width", taskPic.get("width"));
		session.setAttribute("height", taskPic.get("height"));
		
		return "workflow/image";	
	}

}
