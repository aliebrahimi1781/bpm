package com.learning.activiti;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 8, 2014
 * Time: 10:24:02 AM
 */
public class PizzaProcess {

    public static void main(String[] args) {
        PizzaProcess process = new PizzaProcess();
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
        String deploymentId = repositoryService.createDeployment()
                .addClasspathResource("PizzaOrderProcess.bpmn20.xml").deploy().getId();
        System.out.println("----- deploymentId -----" + deploymentId);       

        // Start a process instance
        Map<String, Object> ruleVariables = new HashMap<String, Object>();
        ruleVariables.put("pizzaName", "Margherita");
        ruleVariables.put("address", "3943 W.Roundabout Cir");
        ruleVariables.put("quantity", 3L);
        ruleVariables.put("deliveryStatus", new Boolean(true));

        runtimeService.startProcessInstanceByKey("PizzaOrderProcessNew", ruleVariables);

        // Get the first task
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
        System.out.println("---------- Fozzie tasks assigned : ----------" + tasks.size());
        for (Task task : tasks) {
            System.out.println("Following task is available for accountancy group: " + task.getName());

            // claim it
            taskService.claim(task.getId(), "fozzie");
        }

        // Verify Fozzie can now retrieve the task
        tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
        System.out.println("---------- Fozzie tasks retrived : ----------" + tasks.size());
        for (Task task : tasks) {
            System.out.println("Task for fozzie: " + task.getName());

            // Complete the task
            taskService.complete(task.getId());
        }
        System.out.println("Number of tasks for fozzie: " + taskService.createTaskQuery().taskAssignee("fozzie").count());

        // Verify that the process is actually finished
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(deploymentId).singleResult();
        System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());

    }
    


}
