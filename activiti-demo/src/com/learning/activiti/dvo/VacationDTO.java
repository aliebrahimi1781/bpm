package com.learning.activiti.dvo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 10, 2014
 * Time: 3:53:01 PM
 */
public class VacationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String employeeName;
    private long numberOfDays;
    private String startDate;
    private String vacationMotivation;
    private String managerName;

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getVacationMotivation() {
        return vacationMotivation;
    }

    public void setVacationMotivation(String vacationMotivation) {
        this.vacationMotivation = vacationMotivation;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        // Rule Data
        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" employeeName: " + employeeName + NEW_LINE);
        result.append(" numberOfDays: " + numberOfDays + NEW_LINE);
        result.append(" startDate: " + startDate + NEW_LINE);
        result.append(" vacationMotivation: " + vacationMotivation + NEW_LINE);

        result.append("}");

        return result.toString();
    }    
}
