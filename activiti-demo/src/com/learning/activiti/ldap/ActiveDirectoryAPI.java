package com.learning.activiti.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import java.util.Hashtable;

import javax.naming.ldap.InitialLdapContext;
import javax.naming.directory.DirContext;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class ActiveDirectoryAPI {
	
	//private String managerName = null;
	String searchBase = null;

	public UserProfileDto callActiveDirectory(String lanId, String adminName, String adminPassword) throws NamingException {
		//String getFinalResults=null;
		//try{
			// private String userId=lanId;
			Hashtable<String, String> environment = this.getADEnviromentDetails(adminName, adminPassword);
			// Create the initial directory context
	
			DirContext ctx = new InitialLdapContext(environment, null);
	
			Attributes adApplicatnResponse = this.getADUserResultSet(ctx, lanId);
	
			String managerName = this.fetchMangerNameAttributes(adApplicatnResponse);
	
			Attributes adManagerResponse = this.adManagerResponse(ctx, managerName);
	
			//getFinalResults = this.getFinalResults(adApplicatnResponse, adManagerResponse);
			//}catch(NamingException ne){				
			
		return getFinalResults(adApplicatnResponse, adManagerResponse);
	}
	

	//Active Directory (AD) Environment Details
	private Hashtable<String, String> getADEnviromentDetails(String adminName, String adminPassword) {
		
		Hashtable<String, String> env = new Hashtable<String, String>();
		//String adminName = "AD-ENT\\asmudun";
		//String adminPassword = "Welcome35";
		// Port number 389 or 636 for SSL
		String ldapURL = "ldap://adldapmnsv.ent.wfb.bank.corp:389";

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

		// Set security credentials
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, adminName);
		env.put(Context.SECURITY_CREDENTIALS, adminPassword);

		// connect to my domain controller
		env.put(Context.PROVIDER_URL, ldapURL);
		return env;
	}
	

	//
	private Attributes getADUserResultSet(DirContext context, String LanID) throws NamingException {
		
		SearchControls searchCtls = new SearchControls();

		// Specify the attributes to return
		String returnedAtts[] = { "givenName", "sn", "manager", "initials", "mail", "telephoneNumber", "sAMAccountName" };
		searchCtls.setReturningAttributes(returnedAtts);

		// Specify the search scope
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		// specify the LDAP search filter
		String searchFilterApplicant = "(&(objectCategory=user)(sAMAccountName=" + LanID + "))";

		// Specify the Base for the search
		searchBase = "DC=ent,DC=wfb,DC=bank,DC=corp";

		// Search for objects using the filter
		NamingEnumeration<SearchResult> answer = context.search(searchBase, searchFilterApplicant, searchCtls);
		
		return answer.next().getAttributes();
	}
	

	// Getting Applicant Manager Name
	private String fetchMangerNameAttributes(Attributes adApplicatnResponse) throws NamingException {
		String managerDetailsarray[] = (adApplicatnResponse.get("manager").get()).toString().split(",");
		return managerDetailsarray[0].replace("CN=", "");
	}
	

	//Getting Manager Details
	private Attributes adManagerResponse(DirContext context, String applicantManager) throws NamingException {
		SearchControls searchCtls = new SearchControls();
		String returnedAtts[] = { "givenName", "sn", "manager", "initials", "mail", "telephoneNumber", "sAMAccountName" };
		searchCtls.setReturningAttributes(returnedAtts);

		// Specify the search scope
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		String searchFilterManager = "(&(objectCategory=user)(cn=" + applicantManager + "))";

		// Search for objects using the filter
		NamingEnumeration<SearchResult> managerDetails = context.search(searchBase, searchFilterManager, searchCtls);
		return managerDetails.next().getAttributes();
	}

	
	//Printing the Applicant and Manager Details
	private UserProfileDto getFinalResults(Attributes adApplicatnResponse, Attributes adManagerResponse) throws NamingException {

		UserProfileDto createUserDTO=new UserProfileDto();
		if (adApplicatnResponse != null) {
			if(adApplicatnResponse.get("sAMAccountName") != null)
				createUserDTO.setLanId(adApplicatnResponse.get("sAMAccountName").get().toString());
        	
			if(adApplicatnResponse.get("givenName") != null)
        		createUserDTO.setFirstName(adApplicatnResponse.get("givenName").get().toString());
			
			if(adApplicatnResponse.get("sn") != null)
        		createUserDTO.setLastName(adApplicatnResponse.get("sn").get().toString());
			
			if(adApplicatnResponse.get("mail") != null)
        		createUserDTO.setEmailId(adApplicatnResponse.get("mail").get().toString());
			
			String midleinitials=null;
			if(adApplicatnResponse.get("initials") != null)
            {
				midleinitials = (String) adApplicatnResponse.get("initials").get();
				createUserDTO.setInitials(midleinitials);
            }
            else
            {
            	midleinitials="";
            	createUserDTO.setInitials(midleinitials);
            }
			
			if(adApplicatnResponse.get("telephoneNumber") != null)
        		createUserDTO.setPhoneNumber(adApplicatnResponse.get("telephoneNumber").get().toString());
			
			String managerDetailsarray[] = (adApplicatnResponse.get("manager").get()).toString().split(",");
			
			if((managerDetailsarray) != null)
        		createUserDTO.setManager(managerDetailsarray[0].replace("CN=", ""));
			
			
		}
			
//			System.out.println("====== PRINTING APPLICANT DETAILS ======");
//			System.out.printf("%n");
//			System.out.println("  lan ID: " + adApplicatnResponse.get("sAMAccountName").get());
//        	System.out.println("  Last Name: " + adApplicatnResponse.get("sn").get());
//            System.out.println("  First name: " + adApplicatnResponse.get("givenName").get());
//            System.out.println("  Mail: " + adApplicatnResponse.get("mail").get());
//            String applicantManager=null;
//            if(adApplicatnResponse.get("initials") != null)
//            {
//	            applicantManager = (String) adApplicatnResponse.get("initials").get();
//	            System.out.printf("Initials:" +applicantManager);
//            }
//            else
//            {
//            	applicantManager="";
//            	System.out.println("Initials:" +applicantManager);
//            }
//            System.out.println("  Phone Number: " + adApplicatnResponse.get("telephoneNumber").get());
//            System.out.println("  Manager Name: " + managerName);
//
//		}
		
	        	

			
			
		if (adManagerResponse != null) {
			if(adManagerResponse.get("sAMAccountName") != null)
				createUserDTO.setManagerlanId(adManagerResponse.get("sAMAccountName").get().toString());
			
			if(adManagerResponse.get("givenName") != null)
				createUserDTO.setManagerFirstName(adManagerResponse.get("givenName").get().toString());
			
			if(adManagerResponse.get("sn") != null)
				createUserDTO.setManagerLastName(adManagerResponse.get("sn").get().toString());
			
			
			  String applicantManagerInitials=null;
	            if(adManagerResponse.get("initials") != null)
	            {
	            	applicantManagerInitials = (String) adManagerResponse.get("initials").get();
	            	createUserDTO.setManagerInitials(applicantManagerInitials);
	            }
	            else
	            {
	            	applicantManagerInitials="";
	            	createUserDTO.setManagerInitials(applicantManagerInitials);
	            }
			
	            if(adManagerResponse.get("mail") != null)
					createUserDTO.setManagerEmailId(adManagerResponse.get("mail").get().toString());
		}
//			System.out.printf("%n");
//			System.out.println("====== PRINTING MANAGER DETAILS ======");
//			System.out.printf("%n");
//			System.out.println("  lan ID: " + adManagerResponse.get("sAMAccountName").get());
//        	System.out.println("  Last Name: " + adManagerResponse.get("sn").get());
//            System.out.println("  First name: " + adManagerResponse.get("givenName").get());
//            System.out.println("  Mail: " + adManagerResponse.get("mail").get());
//            
//            
//            
//            String applicantManager=null;
//            if(adManagerResponse.get("initials") != null)
//            {
//	            applicantManager = (String) adManagerResponse.get("initials").get();
//	            System.out.println("Initials:" +applicantManager);
//            }
//            else
//            {
//            	applicantManager="";
//            	System.out.println("Initials:" +applicantManager);
//            }
//            String managerDetailsArray[] = (adManagerResponse.get("manager").get()).toString().split(",");
//            String managerManager = managerDetailsArray[0].replace("CN=", "");
//            System.out.println("  Phone Number: " + adManagerResponse.get("telephoneNumber").get());
//            System.out.println("  Manager Name: " + managerManager);
//		}
		
		
		return createUserDTO;

	}

}
