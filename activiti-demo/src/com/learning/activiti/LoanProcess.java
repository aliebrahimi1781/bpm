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
 * Date: Mar 31, 2014
 * Time: 9:50:15 AM
 */
public class LoanProcess {

    public static void main(String[] args) {
        LoanProcess process = new LoanProcess();
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
//                .addClasspathResource("LoanProcess.bpmn20.xml").deploy().getId();
//        System.out.println("----- deploymentId -----" + deploymentId);

        // Start a process instance
        Map<String, Object> processVariables = new HashMap<String, Object>();
        processVariables.put("name", "John Smith");
        processVariables.put("income", 100L);
        processVariables.put("loanAmount", 15L);
        processVariables.put("emailAddress", "John.Smith@yahoo.com");

        System.out.println("--------- Process started ---------" + processVariables);
        runtimeService.startProcessInstanceByKey("LoanProcess", processVariables);


    }


}
