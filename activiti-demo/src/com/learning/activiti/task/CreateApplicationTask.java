package com.learning.activiti.task;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.LoanApplication;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Mar 31, 2014
 * Time: 10:21:45 AM
 */
public class CreateApplicationTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        LoanApplication la = new LoanApplication();
        la.setCreditCheckOk((String) execution.getVariable("creditCheckOk"));
        la.setCustomerName((String) execution.getVariable("name"));
        la.setIncome((Long) execution.getVariable("income"));
        la.setLoanAmount((Long) execution.getVariable("loanAmount"));
        la.setEmailAddress((String) execution.getVariable("emailAddress"));

        System.out.println("---- LoanApplication 1 ----" + execution.getVariable("creditCheckOk"));
        System.out.println("---- LoanApplication ----" + la);

        execution.setVariable("loanApplication", la);
    }

}
