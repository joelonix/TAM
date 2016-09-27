package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1204.java $
	$Id: AVRPT1204.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

/**
 * 	TAM-9294:AVRPT_1204 : Generate the Report 'Health check'.
 * 
 */

public class AVRPT1204 extends AvailableReport {

	/**
	 * 	TAM-9294:AVRPT_1204 : Generate the Report 'Health check'.
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1204(String browser)
	{
		try {
			logger.info("AVRPT_1204 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			logger.info("Step 1 :");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing report
			deleAavilReprt(HEALTHCHK);

			/*Click on the "Subscribe" button. Verify, a modal window "Subscription
			generic reports" is displayed.*/
		    logger.info("Step 2 :");
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/*Subscribe Health check report*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, HEALTHCHK);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/*Click on 'Health check' under the 'Auto_Test' section. Click on 
			the "Launch" button.A modal window "Launch" is displayed.*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("exp_rep_xpath", text, HEALTHCHK);
			selUtils.clickOnWebElement("Launh_butt_xpath", repActsButts[0]);
			vModWinDisp(repActsButts[0]);

			/* Keep the 'Signature' empty.Click on the "Launch" button.The error message 
			 * 'The field 'Signature' is mandatory' is displayed in red color.
			 */
			logger.info("Step 6 :");
			selUtils.clickOnWebElement("avail_launch_id", repActsButts[0]);
			verifyPopUpErrMsgWithInvaliData("avail_launch_id", "avr_sign_launch_err_id", SIGNMAND, "avr_crt_err_icn_css", "avr_sign_id");
			logger.info("Step 7 :");
			verifySpecifiedWinNotDisp("avail_laun_cls_id");

			/*Delete 'Health check' Report*/
			logger.info("Step 8, 9 & 10 :");
			deleAavilReprt(HEALTHCHK);

			logger.info("AVRPT_1204 successfully executed");	

		} catch (Throwable t) {
			handleException(t);
		}
	}
}