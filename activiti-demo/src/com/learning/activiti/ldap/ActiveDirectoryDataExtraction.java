package com.learning.activiti.ldap;

import javax.naming.NamingException;

public class ActiveDirectoryDataExtraction {

	public static void main(String[] args) throws NamingException {
		ActiveDirectoryAPI ada= new ActiveDirectoryAPI();
        String adminName = "AD-ENT\\asmudun";
		String adminPassword = "Welcome35";
        UserProfileDto userProfileDto = ada.callActiveDirectory("asmudun", adminName, adminPassword);
		System.out.println("My name is:"+(userProfileDto.getManager()));
        System.out.println(" Data FROM AD: " + userProfileDto);
    }
}
