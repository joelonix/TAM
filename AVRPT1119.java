package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1119.java $
	$Id: AVRPT1119.java 7587 2014-05-29 10:55:15Z jlakshmi $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

public class AVRPT1119 extends AvailableReport {

	/**
	 * TAM-9502:AVRPT_1119: Generate the Reports 'Calls by hour'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1119(String browser)
	{
		try {
			logger.info("AVRPT_1119 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on "Available Reports" in the left menu of the 'TMS' page
			The 'TMS >> Available Reports >> Auto_Test' page is displayed*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing dashboard report
			deleAavilReprt(CALSBYHRS);

			logger.info("Step 2 :");
			/*Click on the "Subscribe" button
			A modal window "Subscription generic reports" is displayed*/
			verifyExpWinDisp("subs_butt_xpath",SUBSCGENRPT);

			/*Tick the check box in the 'Select' column of the table for the
			 * report with the 'Reports name' 'Calls by hour' and Subscribe.*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, CALSBYHRS);
			cnfmPopupActMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id",SUBSSUCCMSG);

			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/*Click on 'Calls by hour' under the 'Auto_Entity' section,
			 * The "Description" tab is displayed.Click on the "Launch" button
			 * verify the model window*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, CALSBYHRS);
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);

			logger.info("Step 6 :");
			waitMethods.waitForElementDisplayed("Launh_butt_xpath");
			verifyExpWinDisp("Launh_butt_xpath",repActsButts[0]);
			selUtils.verifyElementDisp("avail_start_date_id",startDte);
			selUtils.verifyElementDisp("avail_end_date_id", endDte);
			selUtils.verifyElementDisp("timezone_id", timZone);
			vLaunchResetClsButts();

			/*Keep all the fields with their default values and 
			 *click on the "Launch" button,The modal window is closed.
			 * verify CallByHour.xls file*/
			logger.info("Step 7 :");
			selUtils.clickOnWebElement("avail_launch_id", repActsButts[0]);

			logger.info("Step 8, 9 :");
			readAndVerifXlsXmlCellOne(getDownFilePath(FILNAMECBH,"xls"), DISTOFDOWNCBH);

			logger.info("Step 10, 11, 12 :");
			/*Delete Calls by hour report*/
			deleAavilReprt(CALSBYHRS);
			logger.info("AVRPT_1119 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}