package com.learning.activiti;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Mar 21, 2014
 * Time: 10:17:59 AM
 */
public class FinancialReportProcess {

    public static void main(String[] args) {
        FinancialReportProcess process = new FinancialReportProcess();
        process.startProcess();
    }

    private void startProcess() {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext-activiti.xml");

        // Getting repository service
        RepositoryService repositoryService =
                (RepositoryService) applicationContext.getBean("repositoryService");

        // Getting process engine
        ProcessEngine processEngine =
                (ProcessEngine) applicationContext.getBean("processEngine");

        // Getting runtime service
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // Deploy the process definition
//        String deploymentId = repositoryService.createDeployment()
//                .addClasspathResource("FinancialReportProcess.bpmn20.xml")
//                .deploy().getId();
//        System.out.println("----- deploymentId -----" + deploymentId);

        // Start a process instance 
        String procId = runtimeService.startProcessInstanceByKey("financialReport").getId();
        System.out.println("----- procId -----" + procId);

        // Get the first task
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
        for (Task task : tasks) {
            System.out.println("Following task is available for accountancy group: " + task.getName());

            // claim it
            taskService.claim(task.getId(), "fozzie");
        }

        // Verify Fozzie can now retrieve the task 
        tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
        for (Task task : tasks) {
            System.out.println("Task for fozzie: " + task.getName());

            // Complete the task
            taskService.complete(task.getId());
        }
        System.out.println("Number of tasks for fozzie: " + taskService.createTaskQuery().taskAssignee("fozzie").count());


        // Retrieve and claim the second task
        tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            System.out.println("Following task is available for accountancy group: " + task.getName());
            taskService.claim(task.getId(), "kermit");
        }

        // Completing the second task ends the process
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }

        // Verify that the process is actually finished
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
        System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());


        

    }

}
