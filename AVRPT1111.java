package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1111.java $
	$Id: AVRPT1111.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Terminals;

/**
 * 	TAM-9294:AVRPT_1111 : Generate the Report 'Health check'.
 * 
 */
public class AVRPT1111 extends AvailableReport {

	/**
	 * 	TAM-9294:AVRPT_1111 : Generate the Report 'Health check'.
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1111(String browser)
	{
		try {
			logger.info("AVRPT_1111 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			logger.info("Step 1 :");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing report
			deleAavilReprt(HEALTHCHK);

			 /*Click on the "Subscribe" button. Verify, a modal window
			"Subscription generic reports" is displayed.*/
			logger.info("Step 2 :");
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/* Tick the check box in the 'Select' column of the table for the report
			 *  with the 'Reports name' 'Health check' and  'Subscribe'*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, HEALTHCHK);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/* Click on 'Health check' under the 'Auto_Test' section.
			 * The "Description" tab is displayed.Click on the "Launch" button.
			 * A modal window "Launch" is verified*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("exp_rep_xpath", text, HEALTHCHK);
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			logger.info("Step 6 :");
			selUtils.clickOnWebElement("Launh_butt_xpath", repActsButts[0]);
			selUtils.verifyElementDisp("avr_sign_id", signLbl);
			vLaunchResetClsButts();

			/* Set the 'Signature' field to '2100000321000003'.
			 * Click on the "Launch" button.The modal window is closed.
			 * verify the 'Open HealthCheckReport.pdf' modal window is displayed.*/
			logger.info("Step 7 :");
			selUtils.populateInputBox("avr_sign_id", Terminals.sign003);
			selUtils.clickOnWebElement("avail_launch_id", repActsButts[0]);
			logger.info("Step 8 & 9 :");
			verifPdf(getDownFilePath(HLTCHKRPT,"pdf"));

			//Delete the 'Health check' Report
			logger.info("Step 10, 11 & 12 :");
			deleAavilReprt(HEALTHCHK);

			logger.info("AVRPT_1111 successfully executed");	

		} catch (Throwable t) {
			handleException(t);
		}
	}
}