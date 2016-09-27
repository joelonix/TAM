package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1109.java $
	$Id: AVRPT1109.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Estates;

/**
 * 	TAM-9491:AVRPT_1109 : Generate the Reports 'Statistics'
 * 
 */
public class AVRPT1109 extends AvailableReport {

	/**
	 * 	TAM-9491:AVRPT_1109 : Generate the Reports 'Statistics'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1109(String browser)
	{
		try {
			logger.info("AVRPT_1109 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			logger.info("Step 1 :");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing report
			deleAavilReprt(CALS24STATS);

			/* Click on the "Subscribe" button. Verify, a modal window 
			"Subscription generic reports" is displayed.*/
			logger.info("Step 2 :");
			verifyExpWinDisp("subs_butt_xpath",SUBSCGENRPT);

			/* Tick the check box in the 'Select' column of the table for the 
			 * report with the 'Reports name' '24h Calls Status' and 
			 * Subscribe*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, CALS24STATS);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/* Click on '24h Calls Status' under the 'Auto_Test' section.
			 * and verify the "Description" tab 
			 * */
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("exp_rep_xpath", text, CALS24STATS);
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			logger.info("Step 6 :");
			selUtils.clickOnWebElement("Launh_butt_xpath", repActsButts[0]);
			selUtils.verifyElementDisp("avail_start_date_id",startDte);
			selUtils.verifyElementDisp("avail_end_date_id", endDte);
			vDrpDnDefltSelcItem("estate_id", client, Estates.estLbl);
			selUtils.verifyElementDisp("estate_id", Estates.estName);
			vLaunchResetClsButts();

			/*Keep all the fields with their default values and click on the 
			 * "Launch" button.verify data in'Opening 24h_Calls_Status.xls'. 
			 **/
			logger.info("Step 7 :");
			selUtils.clickOnWebElement("avail_launch_id", repActsButts[0]);
			logger.info("Step 8 & 9 :");
			readAndVerifXlsSheets(getDownFilePath(CALSTATS,"xls"), sheets24);

			/*Delete '24h Calls Status' Report.*/
			logger.info("Step 10, 11 & 12 :");
			deleAavilReprt(CALS24STATS);

			logger.info("AVRPT_1109 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}