package com.ingenico.tam.testsuite.availablereport;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;


/**
 * TAM-9502:AVRPT_1127: Generate the Reports 'Calls by hour'
 * 
 */
public class AVRPT1127 extends AvailableReport{

	/**
	 * TAM-9502:AVRPT_1127: Generate the Reports 'Calls by hour'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1127(String browser)
	{
		try {
			logger.info("AVRPT_1127 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on "Available Reports" in the left menu of the 'TMS' page
			The 'TMS >> Available Reports >> Auto_Test' page is displayed*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing dashboard report
			deleAavilReprt(CALBYPROEST);

			logger.info("Step 2 :");
			/*Click on the "Subscribe" button
			A modal window "Subscription generic reports" is displayed*/
			verifyExpWinDisp("subs_butt_xpath",SUBSCGENRPT);

			logger.info("Step 3 :");
			/*Tick the check box in the 'Select' column of the table for the 
			 * report with the 'Reports name' 'Calls by hour and estate',and 
			 * Subscribe.
			 */
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, CALBYPRODEST);
			cnfmPopupActMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id",SUBSSUCCMSG);

			logger.info("Step 4 :");
			/*Click on the 'Close' button.The modal window is closed*/
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			logger.info("Step 5 :");
			/*Click on 'Calls by hour and estate' under the 'Auto_Entity' section
			 * ,The "Description" tab is displayed.*/

			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, CALBYPROEST);
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);

			logger.info("Step 6 :");
			/* Click on the "Launch" button,A modal window "Launch" is displayed
			 *  containing:-A 'Start date' field-An 'End date' field,-A 'Time zone'
			 *   field-The buttons: 'Launch', 'Reset', 'Close'.*/

			waitMethods.waitForElementDisplayed("Launh_butt_xpath");
			verifyExpWinDisp("Launh_butt_xpath",repActsButts[0]);
			selUtils.verifyElementDisp("avail_start_date_id",startDte);
			selUtils.verifyElementDisp("avail_end_date_id", endDte);
			selUtils.verifyElementDisp("timezone_id", timZone);
			vLaunchResetClsButts();

			logger.info("Step 7, 8, 9 :");
			/*Keep all the fields with their default values and click on the 
			 * "Launch" button.The 'Opening CallByHourAndEstate.xls' modal window
			 *  is opened.and verify the data in the zip file*/
			selUtils.clickOnWebElement("avail_launch_id", repActsButts[0]);
			unzipFile(getDownFilePath(FILECALLPRODEST,"zip"));
			//readAndVerifXlsXmlCellOne(getDownFilePath(client,"xls"),DISTDOWNPRODEST);
			readAndVerifXlsXmlCellOne(getDownFilePath(multiCust,"xls"),DISTDOWNPRODEST);
			
			logger.info("Step 13, 14, 15 :");
			//Delete" the 'Calls by hour and estate' Report.
			deleAavilReprt(CALBYPROEST);

			logger.info("AVRPT_1125 successfully executed");	

		}catch (Throwable t) {
			handleException(t);
		}
	}
}
