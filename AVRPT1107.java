package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1107.java $
	$Id: AVRPT1107.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Estates;


/**
 * 	TAM-9491:AVRPT_1107 : Generate the Reports 'Statistics'
 * 
 */
public class AVRPT1107 extends AvailableReport {

	/**
	 * 	TAM-9491:AVRPT_1107 : Generate the Reports 'Statistics'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1107(String browser)
	{
		try {
			logger.info("AVRPT_1107 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			logger.info("Step 1 :");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing report
			deleAavilReprt(statistics);

			/*Click on the "Subscribe" button. Verify, 
			a modal window "Subscription generic reports" is displayed.*/
			logger.info("Step 2 :");
			verifyExpWinDisp("subs_butt_xpath",SUBSCGENRPT);

			/* Tick the check box in the 'Select' column of the table for the
			 * report with the 'Reports name' 'Statistics' and click on 
			 * Subscribe and complete*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, statistics);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/* Click on 'Statistics' under the 'Auto_Test' section.The "Description" 
			 * tab is displayed.Click on the "Launch" button modal window and verify.*/
			selUtils.clickOnWebElement("exp_rep_xpath", text, statistics);
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			logger.info("Step 6 :");
			selUtils.clickOnWebElement("Launh_butt_xpath", repActsButts[0]);
			selUtils.verifyElementDisp("avail_start_date_id",startDte);
			selUtils.verifyElementDisp("avail_end_date_id", endDte);
			vDrpDnDefltSelcItem("estate_id", client, Estates.estLbl);
			selUtils.verifyElementDisp("estate_id", Estates.estName);
			vLaunchResetClsButts();

			/* Keep all the fields with their default values and click on the 
			 * "Launch" button verify Opening Statistics.xls file*/
			logger.info("Step 7 :");
			selUtils.clickOnWebElement("avail_launch_id", repActsButts[0]);
			logger.info("Step 8 & 9 :");
			readAndVerifXlsSheets(getDownFilePath(statistics,"xls"), sheets);

			/* delete 'Statistics' Report static report */
			logger.info("Step 10, 11 & 12 :");
			deleAavilReprt(statistics);
			logger.info("AVRPT_1107 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}