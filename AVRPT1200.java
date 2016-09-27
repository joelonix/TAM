package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/tam/com/ingenico/tam/testsuite/availablereport/AVRPT1200.java $
	$Id: AVRPT1200.java 9896 2014-09-29 09:07:16Z shkumar $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

/**
 * 	TAM-9305:AVRPT_1200: Unauthorized characters in 'Merchant name' field
 * 
 */
public class AVRPT1200 extends AvailableReport {
	
	String[] mercNam={"AVRPT_1200%","%AVRPT_1200","\\AVRPT_1200","AVRPT_1200\\","<AVRPT_1200","AVRPT_1200>","\"AVRPT_1200","AVRPT_1200\""};

	/**
	 * 	TAM-9305:AVRPT_1200: Unauthorized characters in 'Merchant name' field
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1200(String browser)
	{
		try {
			logger.info("AVRPT_1200 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			/*Click on "Available Reports" in the left menu of the 'TMS' page,
			The 'TMS >> Available Reports >> Auto_Test' page is displayed*/
			logger.info("Step 1 :");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing report
			deleAavilReprt(MERTERMS);

			/*Click on the "Subscribe" button,A modal window "Subscription 
			generic reports" is displayed
*/			logger.info("Step 2 :");
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/*Tick the check box in the 'Select' column of the table for the 
			 * report with the 'Reports name' 'Merchant's terminals'and Subscribe*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, MERTERMS);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			
			/*Click on the 'Close' button,The modal window is closed*/
			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/*Select 'Merchant's terminals' under the 'Auto_Test' section,
			 * Click on the "Launch" button.The modal window "Launch" is displayed*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("exp_rep_xpath", text, MERTERMS);
			selUtils.clickOnWebElement("Launh_butt_xpath", repActsButts[0]);
			vModWinDisp(repActsButts[0]);

		    //Unauthorized characters in 'Merchant name' field
			logger.info("Step 6, 7 :");
			for(itemCount = 0; itemCount < mercNam.length; itemCount++) {
				selUtils.populateInputBox("merc_name_id", mercNam[itemCount]);
				verifyErrMsgWithInvaliData("avail_launch_id", "avr_sign_launch_err_id", estInValErrMsg, "avr_crt_err_icn_css");
			}
			
			/*Click on the 'Close' button of the modal window.
			 * The modal window is closed*/
			logger.info("Step 8 :");
			verifySpecifiedWinNotDisp("avail_laun_cls_id");

			//Delete 'Merchant's terminals' report
			logger.info("Step 9, 10 & 11 :");
			deleAavilReprt(MERTERMS);

			logger.info("AVRPT_1200 successfully executed");	

		} catch (Throwable t) {
			handleException(t);
		}
	}
}