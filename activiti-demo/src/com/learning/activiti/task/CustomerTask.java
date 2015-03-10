package com.learning.activiti.task;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.LoanApplication;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 1, 2014
 * Time: 10:28:30 AM
 */
public class CustomerTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        LoanApplication loanApplication = (LoanApplication) execution.getVariable("loanApplication");
        String approvalStatus = (String) execution.getVariable("approvalStatus");
        String explanation = (String) execution.getVariable("explanation");

        System.out.println("---- approvalStatus ----" + approvalStatus);
        System.out.println("---- explanation ----" + explanation);
        System.out.println("---- CustomerTask ----" + loanApplication);

        execution.setVariable("loanApplicationCust", loanApplication);
    }

}