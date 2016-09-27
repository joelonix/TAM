package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1121.java $
	$Id: AVRPT1121.java 7587 2014-05-29 10:55:15Z jlakshmi $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Estates;

public class AVRPT1121 extends AvailableReport {

	/**
	 * TAM-9503:AVRPT_1121: Generate the Reports 'Axis report'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1121(String browser)
	{
		try {
			logger.info("AVRPT_1121 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on "Available Reports" in the left menu of the 'TMS' page
			The 'TMS >> Available Reports >> Auto_Test' page is displayed*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing dashboard report
			deleAavilReprt(AXISRPT);

			logger.info("Step 2 :");
			/*Click on the "Subscribe" button
			A modal window "Subscription generic reports" is displayed*/
			verifyExpWinDisp("subs_butt_xpath",SUBSCGENRPT);

			/*Tick the check box in the 'Select' column of the table for the 
			 * report with the 'Reports name' 'Axis report' and Subscribe.*/

			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, AXISRPT);
			cnfmPopupActMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id",SUBSSUCCMSG);

			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/*Click on 'Axis report' under the 'Auto_Entity' section,
			 * Verify the Description tab is displayed*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, AXISRPT);
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);

			logger.info("Step 6 :");
			waitMethods.waitForElementDisplayed("Launh_butt_xpath");
			verifyExpWinDisp("Launh_butt_xpath",repActsButts[0]);
			selUtils.verifyElementDisp("avail_start_date_id",startDte);
			selUtils.verifyElementDisp("avail_end_date_id", endDte);
			selUtils.verifyElementDisp("reptimezone_id", timZone);
			selUtils.verifyElementDisp("estate_id",Estates.estName);
			vLaunchResetClsButts();

			/*Keep all the fields with their default values and click on the 
			 * "Launch" button,The modal window is closed.and verify 
			 * Axis_report.xls file.*/
			logger.info("Step 7 :");
			selUtils.clickOnWebElement("avail_launch_id", repActsButts[0]);

			logger.info("Step 8, 9 :");
			readAndVerifXlsXmlCellOne(getDownFilePath(FILNAMEAXISRPT,"xls"),AXISDOWRPT);

			logger.info("Step 10, 11, 12 :");
			/*Delete the Axis report Report*/
			deleAavilReprt(AXISRPT);
			logger.info("AVRPT_1121 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}