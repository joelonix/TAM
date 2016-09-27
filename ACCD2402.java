package com.ingenico.tam.testsuite.accesscode;
/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD2402.java $
	$Id: ACCD2402.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
/**
 * Check that the Admin role can Edit and delete an access code.
 */
public class ACCD2402 extends AccessCode {

	/**
	 * TAM-9282:ACCD_2402.
	 * Check that the Admin role can Edit and delete an access code
	 * 
	 */
	@Test
	public void accd2402(){
		try {
			logger.info("ACCD_2402 Execution started");
			logger.info("Step 1 :");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

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
			vEditAccdPageDetails(aCode, "accd_edit_icn_xpath");
			vGrpButts("accd_acts_btns_xpath", actsButts, ACCCODEACT);

			logger.info("Step 5 :");
			logger.info("ACCD_2402 successfully executed");	

		} catch (Throwable t) {
			handleException(t);
		}
	}
}