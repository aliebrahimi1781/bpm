package com.learning.activiti;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 10, 2014
 * Time: 3:05:12 PM
 */
public class VacationProcess {

    public static void main(String[] args) {
        VacationProcess process = new VacationProcess();
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
//                .addClasspathResource("VacationProcess.bpmn20.xml").deploy().getId();
//        System.out.println("----- deploymentId -----" + deploymentId);

        // Start a process instance
        Map<String, Object> processVariables = new HashMap<String, Object>();
        processVariables.put("employeeName", "John Smith");
        processVariables.put("numberOfDays", 2L);
        processVariables.put("startDate", "04-31-2014");
        processVariables.put("vacationMotivation", "Need a break");

        System.out.println("--------- Process started ---------" + processVariables);
        runtimeService.startProcessInstanceByKey("VecationProcess", processVariables);
        System.out.println("--------- Process completed -------");

    }

}
