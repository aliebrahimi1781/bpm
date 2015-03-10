package com.learning.activiti.task;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.LoanApplication;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 1, 2014
 * Time: 8:41:13 AM
 */
public class EvaluateLoanRequestTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        LoanApplication loanApplication = (LoanApplication) execution.getVariable("loanApplication");
        System.out.println("---- loanApplication Data Eval ----" + loanApplication);

        String creditCheck = loanApplication.getCreditCheckOk();
        if("true".equalsIgnoreCase(creditCheck)) {
            loanApplication.setApprovalStatus(true);
        } else {
            loanApplication.setApprovalStatus(false);
        }

        execution.setVariable("loanApplicationEval", loanApplication);
    }

}
