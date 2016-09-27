package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1113.java $
	$Id: AVRPT1113.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.AvailableReport;

/**
 * TAM-9288:AVRPT_1105 : Generate the Report 'Merchant’s terminals'
 * 
 */
public class AVRPT1113 extends AvailableReport {

	/**
	 * TAM-9288:AVRPT_1105 : Generate the Report 'Merchant’s terminals'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1105(String browser)
	{
		try {
			logger.info("AVRPT_1105 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			logger.info("Step : 2");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			// Delete all reports from view tree.
			deleAavilReprt(actvity);

			/*Click on the "Subscribe" button. Verify, a modal window 
			"Subscription generic reports" is displayed.*/
			logger.info("Step : 3");
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/* Tick the check box in the 'Select' column of the table for the 
			 * report with the 'Reports name' 'activity' and Subscribe*/
			logger.info("Step : 4");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, actvity);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			logger.info("Step : 5");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/*Click on 'activity' under the 'Auto_Entity' 
			section,The "Description" tab is displayed.*/
			logger.info("Step : 6");
			selUtils.clickOnWebElement("exp_rep_xpath", text, actvity);
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);

			/* Click on the "Launch" button. Verify, a modal window "Launch". */
			logger.info("Step : 9");
			waitMethods.waitForElementEnable("Launh_butt_xpath");
			selUtils.clickOnWebElement("Launh_butt_xpath", repActsButts[0]);
			selUtils.verifyElementDisp("access_code_id", AccessCode.ACCODE);
			selUtils.verifyElementDisp("start_date_id", startDte);
			selUtils.verifyElementDisp("end_date_id", endDte);
			vLaunchResetClsButts();

			/* Keep all the fields with their default values and click on the
			 * "Launch" button. Verify, the modal window is closed.and verify
			 * 'Opening accessCodeActivity.xls' .*/
			logger.info("Steps : 10,11,12");
			selUtils.clickOnWebElement("avail_launch_id", repActsButts[0]);
			readAndVerifXlsXmlCellOne(getDownFilePath(FILNMACT,"xls"), ACCCDACT);

			/* Delete the 'activity' Report*/
			logger.info("Steps : 13,14,15");
			unSubScrRepsProc("exp_rep_del_icn_xpath", actvity);

			logger.info("AVRPT_1105 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}