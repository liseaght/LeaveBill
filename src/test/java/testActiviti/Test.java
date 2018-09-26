package testActiviti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	@org.junit.Test
	public void test1() {
		ClassPathXmlApplicationContext applicationContext = 
			    new ClassPathXmlApplicationContext("applicationContext-activiti.xml");
		ProcessEngine processEngine = (ProcessEngine) applicationContext.getBean("processEngine");
		Deployment deployment = processEngine.getRepositoryService().createDeployment()
			.name("测试spring-activiti")
			.addClasspathResource("diagrams/helloworld.bpmn")
			.addClasspathResource("diagrams/helloworld.png")
			.deploy();
		
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		
		applicationContext.close();
	}

}
