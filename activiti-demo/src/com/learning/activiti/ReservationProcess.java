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
 * Date: May 2, 2014
 * Time: 8:41:26 AM
 */
public class ReservationProcess {

    public static void main(String[] args) {
        ReservationProcess process = new ReservationProcess();
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
//                .addClasspathResource("ReservationProcess.bpmn20.xml").deploy().getId();
//        System.out.println("----- deploymentId -----" + deploymentId);

        // Start a process instance
        Map<String, Object> ruleVariables = new HashMap<String, Object>();
        ruleVariables.put("customerName", "John Smith");
        ruleVariables.put("cardNumber", "1234567544");
        ruleVariables.put("customerAddress", "2500 S.Price Road, Chandler, AZ");
        System.out.println("----- Started -----" + ruleVariables);

        runtimeService.startProcessInstanceByKey("ReservationProcess", ruleVariables);
        System.out.println("----- Completed -----");       

    }

}
