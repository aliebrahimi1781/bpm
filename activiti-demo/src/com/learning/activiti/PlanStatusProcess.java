package com.learning.activiti;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 4, 2014
 * Time: 1:41:17 PM
 */
public class PlanStatusProcess {

    public static void main(String[] args) {
        PlanStatusProcess process = new PlanStatusProcess();
        process.startProcess();
    }

    private void startProcess() {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext-activiti.xml");

        // Getting repository service
        RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");

        // Getting process engine
        ProcessEngine processEngine = (ProcessEngine) applicationContext.getBean("processEngine");

        // Getting runtime service
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // Deploy the process definition
//        String deploymentId = repositoryService.createDeployment()
//                .addClasspathResource("RuleDemoProcess.bpmn20.xml").deploy().getId();
//        System.out.println("----- deploymentId -----" + deploymentId);

        // Start a process instance
        Map<String, Object> ruleVariables = new HashMap<String, Object>();
        ruleVariables.put("accountNumber", "123456");
        ruleVariables.put("status", "P");
        ruleVariables.put("action", "No Data");
        System.out.println("----- Started -----" + ruleVariables);

        runtimeService.startProcessInstanceByKey("RuleDemoProcess", ruleVariables);
        System.out.println("----- Completed -----");
        
        
    }

}
