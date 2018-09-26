package xin.snowsteps.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import xin.snowsteps.pojo.LeaveBill;
import xin.snowsteps.service.ILeaveBillService;
import xin.snowsteps.service.IWorkFlowService;

@Service("iWorkFlowService")
public class WorkFlowServiceImpl implements IWorkFlowService{
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ILeaveBillService iLeaveBillService;
	
	@Autowired
	private HistoryService historyService;
	

	@Override
	public String deploy(MultipartFile file, String flowName) {
		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(file.getInputStream());
			repositoryService.createDeployment().name(flowName).addZipInputStream(zipInputStream).deploy();
		} catch (IOException e) {
			e.printStackTrace();
			return "流程部署失败...";
		} finally {
			try {
				zipInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return "流程部署成功...";	
		
	}

	@Override
	public List<Deployment> queryDeployment() {
		return repositoryService.createDeploymentQuery().list();
	}
	
	@Override
	public List<ProcessDefinition> queryProcessDefinition() {
		return repositoryService.createProcessDefinitionQuery().list();
	}

	@Override
	public InputStream getFlowPic(String deploymentId, String diagramResourceName) {
		return repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
	}

	@Override
	public void delDeploy(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);//级联删除	
	}

	@Override
	public void startProcess(Integer leaveId, String inputUser, String key) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("inputUser", inputUser);
		String objId = key + "." + leaveId;
		variables.put("objId", objId);
		runtimeService.startProcessInstanceByKey(key, variables);//流程变量绑定业务	
	}
	
	@Override
	public List<Task> queryTask(String current) {
		return taskService.createTaskQuery().taskAssignee(current).orderByTaskCreateTime().asc().list();
	}

	@Override
	public String getLeaveBillIdByTaskId(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//String processInstanceId = task.getProcessInstanceId();
		String executionId = task.getExecutionId();
		//ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		Map<String,Object> map = runtimeService.getVariables(executionId);
		String objId = (String) map.get("objId");
		String leaveBillId = "";
		if(StringUtils.isNotBlank(objId)) {
			leaveBillId = objId.split("\\.")[1];
		}
		
		return leaveBillId;
	}

	@Override
	public List<String> getOutComeInfo(String taskId) {
		List<String> list = new ArrayList<>();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String activityId = pi.getActivityId();
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
		if(pvmTransitions!=null && pvmTransitions.size()>0) {
			for (PvmTransition pvm : pvmTransitions) {
				String name = (String) pvm.getProperty("name");
				if(StringUtils.isNotBlank(name)) {
					list.add(name);
				}else {
					list.add("默认提交");
				}
			}
		}
		return list;
	}

	@Override
	public void completeTask(String taskId, String comment, String outcome, String userName) {
		//在完成之前，添加一个批注信息，用于记录当前申请人的一些审核信息
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		Authentication.setAuthenticatedUserId(userName);
		taskService.addComment(taskId, processInstanceId, comment);
		
		//完成任务之前，获取任务数据
		String leaveBillId = getLeaveBillIdByTaskId(taskId);//这里上面任务已经完成了，不能查了
		
		//在完成任务之前，设置流程变量，按照连线的名称去完成任务，如果为默认提交不设置
		if(!outcome.equals("默认提交")) {
			Map<String,Object> variables = new HashMap<>();
			variables.put("outcome", outcome);
			taskService.complete(taskId, variables);
		}else {
			taskService.complete(taskId);
		}
		
		//当任务完成之后。需要指定下一个任务的办理人(已使用类的方式指定)
		
		//完成任务之后，判断流程是否结束，若结束，更新请假单状态1>2
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(pi==null) {
			iLeaveBillService.completeTask(leaveBillId);
		}		
	}

	@Override
	public List<Comment> getComments(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		return taskService.getProcessInstanceComments(processInstanceId);
	}
	
	@Override
	public List<Comment> getHisComment(LeaveBill leaveBill, Integer leaveId) {
		String objectName = leaveBill.getClass().getSimpleName();
		String objId = objectName + "." + leaveId;
		
		//使用历史的流程变量查询，返回历史的流程变量的对象，获取流程实例ID
		HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery()
				.variableValueEquals("objId", objId).singleResult();
		
		//获取流程实例ID，查看批注
		String processInstanceId = hvi.getProcessInstanceId();
		
		return taskService.getProcessInstanceComments(processInstanceId);
	}
	
	@Override
	public Map<String, Object> getTaskPicByTaskId(Integer taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId.toString()).singleResult();
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		
		//获取部署ID和资源名称
		String dpid = pd.getDeploymentId();
		String drn = pd.getDiagramResourceName();
		
		//获取流程定义的实体对象
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		//流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//使用流程实例ID，查询正在执行的执行对象表，获取当前活动对于的流程实例对象
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		//获取当前活动对象
		String activityId = processInstance.getActivityId();
		//获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		//获取坐标
		Map<String,Object> map = new HashMap<>();
		map.put("dpid", dpid);
		map.put("drn", drn);
		map.put("x", activityImpl.getX());
		map.put("y", activityImpl.getY());
		map.put("width", activityImpl.getWidth());
		map.put("height", activityImpl.getHeight());
		return map;
	}
	

}
