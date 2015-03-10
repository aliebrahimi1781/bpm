package com.learning.activiti.task;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.LoanApplication;
import com.learning.activiti.dvo.VacationDTO;
import com.learning.activiti.ldap.ActiveDirectoryAPI;
import com.learning.activiti.ldap.UserProfileDto;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 10, 2014
 * Time: 3:55:42 PM
 */
public class VacationTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        VacationDTO vacationDTO = new VacationDTO();
        vacationDTO.setEmployeeName((String) execution.getVariable("employeeName"));
        vacationDTO.setNumberOfDays((Long) execution.getVariable("numberOfDays"));
        vacationDTO.setStartDate((String) execution.getVariable("startDate"));
        vacationDTO.setVacationMotivation((String) execution.getVariable("vacationMotivation"));

        // Calling AD for manager Name
        String managerName = getManagerName();
        vacationDTO.setManagerName(managerName);

        System.out.println("---- vacationDTO ----" + vacationDTO);
        execution.setVariable("vacationDTO", vacationDTO);
    }

    private String getManagerName() {
        String managerName = "";
        try {
            ActiveDirectoryAPI ada = new ActiveDirectoryAPI();
            String adminName = "AD-ENT\\asmudun";
            String adminPassword = "Welcome35";
            UserProfileDto userProfileDto = ada.callActiveDirectory("asmudun", adminName, adminPassword);
            managerName = userProfileDto.getManager();
            System.out.println(" Data FROM AD: " + userProfileDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return managerName;
        }
        return managerName;
    }

}