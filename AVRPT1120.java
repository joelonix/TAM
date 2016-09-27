package com.ingenico.tam.testsuite.availablereport;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Terminals;

public class AVRPT1120 extends AvailableReport {

	/**
	 * TAM-9303:AVRPT_1120: Add the Report 'Axis report'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1120(String browser)
	{
		try {
			logger.info("AVRPT_1120 Execution started");

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
			 * report with the 'Reports name' 'Axis report' and Subscribe*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, AXISRPT);
			cnfmPopupActMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id",SUBSSUCCMSG);

			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			logger.info("Step 5 :");
			/*Click on 'Axis reports' under the 'Auto_Entity' section,The Launch
			 * button is still greyed and not available for clicking.and verify
			 * the description tab */
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, AXISRPTS);
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.verifyElementDisp("avail_axis_reprts_sectn_xpath", AXISRPTS+SECTION);
			selUtils.vExpValContains("avail_axis_reports_sectn_xpath",desc+":");
			selUtils.vExpValContains("avail_axis_reports_sectn_xpath",AXISREPRT);
			selUtils.verifyElementDisp("avail_prop_sectn_xpath", Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValContains("avail_prop_gene_xpath",GENEMOD);
			selUtils.vExpValContains("avail_prop_gene_xpath",onReq);
			selUtils.vExpValContains("avail_prop_time_xpath",timZone);
			selUtils.vExpValContains("avail_prop_time_xpath",cooUniTim);

			logger.info("Step 6 :");
			/*Click on 'Axis report' under the 'Auto_Entity' section,The Launch 
			 *button is green and available for clicking.verify "Description tab*/
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, AXISRPT);
			waitMethods.waitForElementDisplayed("Launh_butt_xpath");
			webEleExpBckgrdClr("Launh_butt_xpath", blueClr, "rgba(0, 57, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.verifyElementDisp("avail_axis_reprt_sectn_xpath", AXISRPT+SECTION);
			selUtils.vExpValContains("avail_axis_report_desc_sectn_xpath",desc+":");
			selUtils.vExpValContains("avail_axis_report_desc_sectn_xpath",DESC4);
			selUtils.verifyElementDisp("avail_prop_sectn_xpath", Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValContains("avail_prop_gene_xpath",RPTFMT);
			selUtils.vExpValContains("avail_prop_gene_xpath",APPMSEXCEL);

			logger.info("Step 7, 8, 9 :");
			/*Delete'Axis report' Report */
			deleAavilReprt(AXISRPT);
			logger.info("AVRPT_1120 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}