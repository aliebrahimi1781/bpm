package com.learning.activiti.rules;

import java.util.Arrays;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class TestPlanStatus {

//	public static void main(String[] args) {
//		try {
//           getFinalAction("O");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

    public static String getFinalAction(String statusValue) throws Exception {
        String drlFileLocaton = "C:/projects/activiti-demo/conf/planstatus.drl";
        Status status = new Status();
        status.setStatus(statusValue);
        invokeRuleEngine(status, drlFileLocaton);

        String finalAction = status.getAction();
        System.out.println("----- Final Action is ------" + finalAction);
        return finalAction;
    }

    /**
	 * This method invokes the rule engine.
	 *
	 * @param status - The input bean with data
	 * @param drlFile - The DRL rule file contains rules
	 * @throws Exception - Throws exception if there is any error
	 */
	private static void invokeRuleEngine(Status status, String drlFile) throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		// kbuilder.add( ResourceFactory.newClassPathResource("Applicant.drl", DroolsTest.class), ResourceType.DRL );
		kbuilder.add(ResourceFactory.newFileResource(drlFile), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		ksession.execute(Arrays.asList(new Object[] { status }));
	}

}
