package com.ingenico.tam.testsuite.accesscode;
/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD2400.java $
	$Id: ACCD2400.java 13732 2015-06-04 10:52:35Z sparween $
 */
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
/**
 * Check that the Viewer role can only view the edit access code.
 */
public class ACCD2400 extends AccessCode {
	/**
	 * TAM-9280:ACCD_2400.
	 * Check that the Viewer role can only view the edit access code.
	 */
	@Test
	public void accd2400(){
		try {
			logger.info("ACCD_2400 Execution started");
			logger.info("Step 1 :");
			login(config.getProperty("normaluser1"),config.getProperty("superuserPassword"));

			/*Log in to TMS with a Viewer user
			The 'Welcome to Ingenico e-Portal' page is displayed*/
			verifyWelcomePage();

			logger.info("Step 2, 3 :");
			/*Set the client' field to 'Auto_Test' Click on 'Access Code' 
			 * Navigate to 'TMS >> Access Code >> Auto_Test' page is displayed*/
			startingSetup("accd_link", ACCDBRDCRUMB);

			logger.info("Step 4 :");
			/* Click on the edit icon in the 'Actions' column of the table 
			 * for access code Auto_ACCD_1 and verify the data*/
			aCode = "Auto_ACCD_1";
			vEditAccdPageDetails(aCode, "accd_zoom_icn_xpath");
			selUtils.vEleNotPresent("accd_renew_link",actsButts[0]);
			selUtils.vEleNotPresent("accd_del_link",actsButts[1]);

			logger.info("Step 5 :");
			logger.info("ACCD_2400 successfully executed");	

		} catch (Throwable t) {
			handleException(t);
		}
	}
}