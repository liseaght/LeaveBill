package xin.snowsteps.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.web.multipart.MultipartFile;

import xin.snowsteps.pojo.LeaveBill;

public interface IWorkFlowService {
	
	public String deploy(MultipartFile file, String flowame);
	
	public List<Deployment> queryDeployment();
	
	public List<ProcessDefinition> queryProcessDefinition();
	
	public InputStream getFlowPic(String deploymentId, String diagramResourceName);
	
	public void delDeploy(String deploymentId);

	public void startProcess(Integer leaveId, String inputUser, String key);

	public List<Task> queryTask(String current);

	public String getLeaveBillIdByTaskId(String taskId);
	
	public List<String> getOutComeInfo(String taskId);

	public void completeTask(String taskId, String comment, String outcome, String userName);

	public List<Comment> getComments(String taskId);

	public List<Comment> getHisComment(LeaveBill leaveBill, Integer leaveId);

	public Map<String, Object> getTaskPicByTaskId(Integer taskId);

}
