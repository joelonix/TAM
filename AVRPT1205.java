package com.ingenico.tam.testsuite.availablereport;

/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1205.java $
$Id: AVRPT1205.java 12143 2015-01-27 09:35:58Z dwilliam $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

/**
 * TAM-9310:AVRPT_1205: Unauthorized characters in 'Signature' field at 'Health check' report name
 * 
 */
public class AVRPT1205 extends AvailableReport  {

	private final String[] data ={"AVRPT_1205%","%AVRPT_1205","\\AVRPT_1205","AVRPT_1205\\","<AVRPT_1205","AVRPT_1205>","\"AVRPT_1205","AVRPT_1205\""};

	/**
	 * TAM-9310:AVRPT_1205: Unauthorized characters in 'Signature' field at 'Health check' report name
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1205(String browser)
	{
		try {
			logger.info("AVRPT_1205 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			logger.info("Step 1 :");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//Data Cleanup Process
			deleAavilReprt(HEALTHCHK);

			/*Click on the "Subscribe" button. 
			Verify, a modal window "Subscription generic reports" is displayed.*/
			logger.info("Step 2 :");
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/* Tick the check box in the 'Select' column of the table for the 
			 * report with the 'Reports name' 'Health check' and  'Subscribe'.
			 */
			logger.info("Step 3, 4 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, HEALTHCHK);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/*Click on 'Health check' under the 'Auto_Test' section.
			Click on the "Launch" button.A modal window "Launch" is displayed.*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("exp_rep_xpath", text, HEALTHCHK);
			selUtils.clickOnWebElement("Launh_butt_xpath", repActsButts[0]);
			vModWinDisp(repActsButts[0]);

			/* Unauthorized characters in 'Signature' field at 'Health check' 
			 * report name*/
			logger.info("Step 6, 7, 8 :");
			for(cnti=0;cnti<data.length;cnti++)
			{
				selUtils.populateInputBox("avr_sign_id",data[cnti] );
				String errMsg="Fields marked in red contain unauthorized characters";
				verifyPopUpErrMsgWithInvaliData("avail_launch_id", "avr_sign_launch_err_id", errMsg, "avr_crt_err_icn_css", "avr_sign_id");
			}
			verifySpecifiedWinNotDisp("avail_laun_cls_id");

			//Delete Health check' Report  
			logger.info("Step 9, 10, 11 :");
			deleAavilReprt(HEALTHCHK);

			logger.info("AVRPT_1205 successfully executed");
		}
		catch (Throwable t) {
			handleException(t);
		}
	}
}	
