package com.ingenico.tam.testsuite.availablereport;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

/**
 * TAM-10580:AVRPT_1125: Generate the Reports 'Calls by hour and estate''
 * 
 */
public class AVRPT1125 extends AvailableReport{

	/**
	 * TAM-10580:AVRPT_1125: Generate the Reports 'Calls by hour and estate''
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1125(String browser)
	{
		try {
			logger.info("AVRPT_1125 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on "Available Reports" in the left menu of the 'TMS' page
			The 'TMS >> Available Reports >> Auto_Test' page is displayed*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing dashboard report
			deleAavilReprt(CALSBYHRSEST);

			logger.info("Step 2 :");
			/*Click on the "Subscribe" button
			A modal window "Subscription generic reports" is displayed*/
			verifyExpWinDisp("subs_butt_xpath",SUBSCGENRPT);

			logger.info("Step 3 :");
			/*Tick the check box in the 'Select' column of the table for the 
			 * report with the 'Reports name' 'Calls by hour and estate' and 
			 * Subscribe*/
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, CALBYHRSEST);
			cnfmPopupActMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id",SUBSSUCCMSG);

			logger.info("Step 4 :");
			/*Click on the 'Close' button.The modal window is closed*/
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			logger.info("Step 5 :");
			/*Click on 'Calls by hour and estate' under the 
			 * 'Auto_Entity' section,The "Description" tab is displayed.*/
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, CALSBYHRSEST);
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);

			logger.info("Step 6 :");
			/* Click on the "Launch" button,A modal window "Launch" is displayed
			 * and verify the window*/
			waitMethods.waitForElementDisplayed("Launh_butt_xpath");
			verifyExpWinDisp("Launh_butt_xpath",repActsButts[0]);
			selUtils.verifyElementDisp("avail_start_date_id",startDte);
			selUtils.verifyElementDisp("avail_end_date_id", endDte);
			selUtils.verifyElementDisp("timezone_id", timZone);
			vLaunchResetClsButts();

			logger.info("Step 7, 8, 9 :");
			/*Keep all the fields with their default values and click on the 
			 * "Launch" button.The 'Opening CallByHourAndEstate.xls' and verify 
			 * files in zip file*/
			selUtils.clickOnWebElement("avail_launch_id", repActsButts[0]);
			unzipFile(getDownFilePath(FILNAMECBHAS,"zip"));
			readAndVerifXlsXmlCellOne(getDownFilePath(multiCust,"xls"),DISTOFDOWNCBHAE);
			
			logger.info("Step 13, 14, 15 :");
			//Delete 'Calls by hour and estate' Report
			deleAavilReprt(CALBYHRSEST);

			logger.info("AVRPT_1125 successfully executed");	

		}catch (Throwable t) {
			handleException(t);
		}
	}
}
