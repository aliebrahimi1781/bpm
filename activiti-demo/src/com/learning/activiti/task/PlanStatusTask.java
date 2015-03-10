package com.learning.activiti.task;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.rules.Status;
import com.learning.activiti.rules.TestPlanStatus;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 4, 2014
 * Time: 1:21:35 PM
 */
public class PlanStatusTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        Status status = new Status();
        String inputStatus = (String) execution.getVariable("status");
        String accountNumber = (String) execution.getVariable("accountNumber");

        // Invoking rule engine - Drools Expert
        String finalAction = getFinalAction(inputStatus);

        status.setAction(finalAction);
        status.setStatus(inputStatus);
        status.setAccountNumber(accountNumber);

        System.out.println("------ status ------" + status);        
        execution.setVariable("planStatus", status);
    }

    public String getFinalAction(String statusValue) {
        String finalAction = "";
        try {
            finalAction = TestPlanStatus.getFinalAction(statusValue);
        } catch(Exception ex) {
            finalAction = ex.getMessage();
        }
        return finalAction;
    }

}