package com.learning.activiti.rules;

import java.io.Serializable;

public class Status implements Serializable {

    private static final long serialVersionUID = 12121L;

    private String accountNumber;
    private String status;
	private static String action = "No Data";   

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStatus() {
		return status;
	}

    public void setStatus(String status) {
		this.status = status;
	}

    public String getAction() {
		return action;
	}

    public void setAction(String action) {
		this.action = action;
	}

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        // Rule Data
        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" status: " + status + NEW_LINE);
        result.append(" action: " + action + NEW_LINE);
        result.append(" accountNumber: " + accountNumber + NEW_LINE);

        result.append("}");

        return result.toString();
    }

}
