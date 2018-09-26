package xin.snowsteps.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import xin.snowsteps.common.Const;
import xin.snowsteps.service.IWorkFlowService;

@Controller
public class WorkFlowController {
	
	@Autowired
	private IWorkFlowService iWorkFlowService;
	
	//部署管理
	@RequestMapping("workflow.do")
	public String workflow_deploy(HttpSession session) {
		//TODO QUERY
		List<Deployment> depList = iWorkFlowService.queryDeployment();
		List<ProcessDefinition> pdList = iWorkFlowService.queryProcessDefinition();
		session.setAttribute("depList", depList);
		session.setAttribute("pdList", pdList);
		return "workflow/workflow";
	}
	
	//部署流程,部署成功后，发送到部署列表上
	@RequestMapping("workflow_deploy.do")
	@ResponseBody
	public String deploy(@RequestParam(value = "upload_file",required = false) MultipartFile file, String flowName) {		
		String msg = iWorkFlowService.deploy(file, flowName);	
		return msg;
	}
	
	@RequestMapping("workflow_getPic.do")
	@ResponseBody
	public String getPic(String deploymentId, String diagramResourceName, HttpServletResponse resp) {		
		InputStream in = iWorkFlowService.getFlowPic(deploymentId, diagramResourceName);
		OutputStream out = null;
		try {
			out = resp.getOutputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			while((len=in.read(buff))!=-1)
				out.write(buff, 0, len);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	@RequestMapping("workflow_del_deploy.do")
	public String delDeploy(String deploymentId) {		
		iWorkFlowService.delDeploy(deploymentId);	
		return "workflow/workflow";
	}
	
	
		
	@RequestMapping("workflow_list_task.do")
	public String listTask(HttpSession session) {		
		String current = (String) session.getAttribute(Const.CURRENT_USER);
		List<Task> taskList = iWorkFlowService.queryTask(current);	
		session.setAttribute("taskList", taskList);
		return "workflow/task";
	}
	

}
