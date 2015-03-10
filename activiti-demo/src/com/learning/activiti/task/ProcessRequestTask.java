package com.learning.activiti.task;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.LoanApplication;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 1, 2014
 * Time: 10:29:13 AM
 */
public class ProcessRequestTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        LoanApplication loanApplication = (LoanApplication) execution.getVariable("loanApplication");
        System.out.println("---- ProcessRequestTask ----" + loanApplication);
        String approvalStatus = (String) execution.getVariable("approvalStatus");
        String explanation = (String) execution.getVariable("explanation");
        
        System.out.println("---- explanation ----" + explanation);
        System.out.println("---- approvalStatus ----" + approvalStatus);

        execution.setVariable("loanApplicationProcess", loanApplication);
    }

}