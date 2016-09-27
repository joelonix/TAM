package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/tam/com/ingenico/tam/testsuite/availablereport/AVRPT1201.java $
	$Id: AVRPT1201.java 9896 2014-09-29 09:07:16Z shkumar $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

/**
 * 	TAM-9306:AVRPT_1201: Unauthorized characters in 'Merchant number' field
 * 
 */
public class AVRPT1201 extends AvailableReport {
	
	String[] mercNam={"AVRPT_1201%","%AVRPT_1201","\\AVRPT_1201","AVRPT_1201\\","<AVRPT_1201","AVRPT_1201>","\"AVRPT_1201","AVRPT_1201\""};

	/**
	 * 	TAM-9306:AVRPT_1201: Unauthorized characters in 'Merchant number' field
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1201(String browser)
	{
		try {
			logger.info("AVRPT_1201 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			/*Click on "Available Reports" in the left menu of the 'TMS' page,
			The 'TMS >> Available Reports >> Auto_Test' page is displayed*/
			logger.info("Step 1 :");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing report
			deleAavilReprt(MERTERMS);

			//Click on the "Subscribe" button,A modal window "Subscription generic reports" is displayed
			logger.info("Step 2 :");
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/*Tick the check box in the 'Select' column of the table for the report with the 'Reports name' 'Merchant's terminals' 
			  and click on the 'Subscribe' button of the modal window,The modal window displays the message 'Subscription of reports
			  performed successfully' and only a 'Close' button is available at the bottom of the modal window*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, MERTERMS);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			
			/*Click on the 'Close' button,The modal window is closed*/
			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/*Select 'Merchant's terminals' under the 'Auto_Test' section,Click on the "Launch" button.The modal window "Launch" is displayed*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("exp_rep_xpath", text, MERTERMS);
			selUtils.clickOnWebElement("Launh_butt_xpath", repActsButts[0]);
			vModWinDisp(repActsButts[0]);

			/*Set the 'Merchant number' field to 'AVRPT_1201%'.Click on the 'Launch' button.An error message is displayed 'Fields marked in red
			 *contain unauthorized characters' in red color.Repeat the step 6 with each of those strings for the 'Merchant number' field:
			 *%AVRPT_1201,\AVRPT_1201,AVRPT_1201\,<AVRPT_1201,AVRPT_1201>,"AVRPT_1201,AVRPT_1201"
			 *The Expected Results of the repeated steps are correct*/
			logger.info("Step 6, 7 :");
			for(itemCount = 0; itemCount < mercNam.length; itemCount++) {
				selUtils.populateInputBox("merc_no_id", mercNam[itemCount]);
				verifyErrMsgWithInvaliData("avail_launch_id", "avr_sign_launch_err_id", estInValErrMsg, "avr_crt_err_icn_css");
			}
			
			/*Click on the 'Close' button of the modal window.The modal window is closed*/
			logger.info("Step 8 :");
			verifySpecifiedWinNotDisp("avail_laun_cls_id");

			/*Click on the "Delete" icon near the 'Merchant's terminals' Report,A modal window "Unsubscription generic report" is displayed
			 * Click on the 'Confirm' buttonThe modal window displays the message 'Unsubscription of report performed successfully' and 
			 * only a 'Close' button is available at the bottom of the modal window.Click on the 'Close' button.The report is deleted*/
			logger.info("Step 9, 10 & 11 :");
			deleAavilReprt(MERTERMS);

			logger.info("AVRPT_1201 successfully executed");	

		} catch (Throwable t) {
			handleException(t);
		}
	}
}