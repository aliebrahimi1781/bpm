package com.learning.activiti.ldap;

import java.io.Serializable;

public class UserProfileDto implements Serializable {

    //Applicant details from Active Directory
    private String lanId;
    private String firstName;
    private String lastName;
    private String preferredName;
    private String initials;
    private String emailId;
    private String phoneNumber;
    private String alternatePhoneNumber;
    private String manager;
    private String approver;
    private String active;

    //Manager Details from Active Directory
    private String managerlanId;
    private String managerFirstName;
    private String managerLastName;
    private String managerInitials;
    private String managerEmailId;

    //Group Table Details
    private String groupName;
    private int groupId;

    //Company Table Details
    private String companyName;
    private int companyId;

    //Division table details
    private String divisionName;
    private int divisionId;

    //LOB table details
    private String lobName;
    private int lobId;

    //BU table details
    private String buName;
    private int buId;

    //FA table details
    private String faName;
    private int faId;


    //FA table setters and getters
    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }

    public int getFaId() {
        return faId;
    }

    public void setFaId(int faId) {
        this.faId = faId;
    }


    //BU table setters and getters
    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public int getBuId() {
        return buId;
    }

    public void setBuId(int buId) {
        this.buId = buId;
    }


    //LOB table setters and getters
    public String getLobName() {
        return lobName;
    }

    public void setLobName(String lobName) {
        this.lobName = lobName;
    }

    public int getLobId() {
        return lobId;
    }

    public void setLobId(int lobId) {
        this.lobId = lobId;
    }

    //Group table setters and getters
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    //Company table setters and getters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    //Division table setters and getters


    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    //Applicant setters and getters

    public String getLanId() {
        return lanId;
    }


    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public void setAlternatePhoneNumber(String alternatePhoneNumber) {
        this.alternatePhoneNumber = alternatePhoneNumber;
    }

    public void setLanId(String lanId) {
        this.lanId = lanId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    //Manager setters and getters

    public String getManagerlanId() {
        return managerlanId;
    }

    public void setManagerlanId(String managerlanId) {
        this.managerlanId = managerlanId;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getManagerInitials() {
        return managerInitials;
    }

    public void setManagerInitials(String managerInitials) {
        this.managerInitials = managerInitials;
    }

    public String getManagerEmailId() {
        return managerEmailId;
    }

    public void setManagerEmailId(String managerEmailId) {
        this.managerEmailId = managerEmailId;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        // Rule Data
        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" lanId: " + lanId + NEW_LINE);
        result.append(" firstName: " + firstName + NEW_LINE);
        result.append(" lastName: " + lastName + NEW_LINE);
        result.append(" lastName: " + lastName + NEW_LINE);
        result.append(" preferredName: " + preferredName + NEW_LINE);
        result.append(" initials: " + initials + NEW_LINE);
        result.append(" initials: " + initials + NEW_LINE);
        result.append(" emailId: " + emailId + NEW_LINE);
        result.append(" phoneNumber: " + phoneNumber + NEW_LINE);
        result.append(" alternatePhoneNumber: " + alternatePhoneNumber + NEW_LINE);
        result.append(" manager: " + manager + NEW_LINE);

        result.append("}");

        return result.toString();
    }


}
