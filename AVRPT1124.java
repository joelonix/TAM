package com.ingenico.tam.testsuite.availablereport;

/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/tam/com/ingenico/tam/testsuite/scenariowizard/AVRPT1124.java $
$Id: AVRPT1124.java 9678 2014-09-15 11:55:09Z sparween $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Terminals;

public class AVRPT1124 extends AvailableReport {

	/**
	 * TAM-10579:AVRPT_1124: Add the Report 'Calls by hour and estate'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1124(String browser)
	{
		try {
			logger.info("AVRPT_1124 Execution started");

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
			 *report with the 'Reports name' 'Calls by hour and estate' and
			 *Subscribe*/
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, CALBYHRSEST);
			cnfmPopupActMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id",SUBSSUCCMSG);

			logger.info("Step 4 :");
			/*Click on the 'Close' button.The modal window is closed*/
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			logger.info("Step 5 :");
			/*Click on 'Quality indicators' under the 'Auto_Entity' section.The 
			 * 'Launch' button is still greyed and not available for clicking.
			 * Verify the "Description" tab.*/
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, QUALINDI);
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.verifyElementDisp("avail_qual_indi_sectn_xpath", QUALINDI+" section");
			selUtils.vExpValContains ("avail_qual_indi_sectn_xpath",desc+":");
			selUtils.vExpValContains ("avail_qual_indi_sectn_xpath",QUALINDDESC);
			selUtils.verifyElementDisp("avail_prop_sectn_xpath", Terminals.edtTrmTabs[1]+" section");
			selUtils.vExpValContains ("avail_prop_gene_xpath",GENEMOD);
			selUtils.vExpValContains ("avail_prop_gene_xpath",onReq);
			selUtils.vExpValContains ("avail_prop_time_xpath",timZone);
			selUtils.vExpValContains ("avail_prop_time_xpath",cooUniTim);

			logger.info("Step 6 :");
			/*Click on 'Calls by hour and estate' under the 'Auto_Entity' section.
			 * The 'Launch' button is green and available for clicking.
			 * verify Description tab*/
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, CALSBYHRSEST);
			waitMethods.waitForElementDisplayed("Launh_butt_xpath");
			webEleExpBckgrdClr("Launh_butt_xpath", blueClr, "rgba(0, 57, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.verifyElementDisp("avail_call_by_hourAndEstat_sectn_xpath", CALBYHRSEST+" section");
			selUtils.vExpValContains ("avail_call_by_hourAndEstate_desc_sectn_xpath",desc+":");
			selUtils.vExpValContains ("avail_call_by_hourAndEstate_desc_sectn_xpath",DESC10);
			selUtils.verifyElementDisp("avail_prop_sectn_xpath", Terminals.edtTrmTabs[1]+" section");
			selUtils.vExpValContains ("avail_prop_gene_xpath",RPTFMT);
			selUtils.vExpValContains ("avail_prop_gene_xpath",APPZIP);

			logger.info("Step 7, 8, 9 :");
			/*Delete" icon near the Calls by hour and estate report*/
			deleAavilReprt(CALSBYHRSEST);
			logger.info("AVRPT_1124 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}

}
