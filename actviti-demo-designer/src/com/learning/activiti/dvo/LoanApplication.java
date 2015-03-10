package com.learning.activiti.dvo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Srinivas
 * Date: Mar 28, 2014
 * Time: 3:00:21 PM
 */
public class LoanApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean creditCheckOk;
    private String customerName;
    private Long income;
    private Long loanAmount;
    private String emailAddress;

    public boolean getCreditCheckOk() {
        return creditCheckOk;
    }

    public void setCreditCheckOk(boolean creditCheckOk) {
        this.creditCheckOk = creditCheckOk;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        // Loan Process Data
        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" creditCheckOk: " + creditCheckOk + NEW_LINE);
        result.append(" customerName: " + customerName + NEW_LINE);
        result.append(" income: " + income + NEW_LINE);
        result.append(" loanAmount: " + loanAmount + NEW_LINE);
        result.append(" emailAddress: " + emailAddress + NEW_LINE);

        result.append("}");

        return result.toString();
    }

}
