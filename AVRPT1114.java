package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1114.java $
	$Id: AVRPT1114.java 7587 2014-05-29 10:55:15Z jlakshmi $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Terminals;

/**
 * 	TAM-9297:AVRPT_1114: Add the Report 'Dashboard'
 * 
 */
public class AVRPT1114 extends AvailableReport {

	/**
	 * 	TAM-9297:AVRPT_1114: Add the Report 'Dashboard'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1114(String browser)
	{
		try {
			logger.info("AVRPT_1114 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on "Available Reports" in the left menu of the 'TMS' page
			The 'TMS >> Available Reports >> Auto_Test' page is displayed*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing dashboard report
			deleAavilReprt(DASHBOARD);

			logger.info("Step 2 :");
			/*Click on the "Subscribe" button
			A modal window "Subscription generic reports" is displayed*/
			verifyExpWinDisp("subs_butt_xpath",SUBSCGENRPT);

			/*Tick the check box in the 'Select' column of the table for the 
			 * report with the 'Reports name' 'Dashboard' and and Subscribe.*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, DASHBOARD);
			cnfmPopupActMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id",SUBSSUCCMSG);

			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			logger.info("Step 5 :");
			/*The 'Launch' button is still greyed and not available for clicking.
			  Verify "description" tab */
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, CALLS);
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.verifyElementDisp("avail_calls_sectn_xpath", CALLS+SECTION);
			selUtils.vExpValContains ("avail_calls_desc_sectn_xpath",desc+":");
			selUtils.vExpValContains ("avail_calls_desc_sectn_xpath",CALDESC);
			selUtils.verifyElementDisp("avail_prop_sectn_xpath",Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValContains ("avail_prop_gene_xpath",GENEMOD);
			selUtils.vExpValContains ("avail_prop_gene_xpath",onReq);
			selUtils.vExpValContains ("avail_prop_time_xpath",timZone);
			selUtils.vExpValContains ("avail_prop_time_xpath",cooUniTim);

			logger.info("Step 6 :");
			/*Click on 'Dashboard' under the 'Auto_Entity' section,The 'Launch' 
			 * button is green and available for clicking. Verify "description"
			 *  tab*/
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, DASHBOARD);
			waitMethods.waitForElementDisplayed("Launh_butt_xpath");
			webEleExpBckgrdClr("subs_butt_xpath", blueClr, "rgba(0, 57, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.verifyElementDisp("avail_dash_sectn_xpath", DASHBOARD+SECTION);
			selUtils.vExpValContains ("avail_dash_desc_sectn_xpath",desc+":");
			selUtils.vExpValContains ("avail_dash_desc_sectn_xpath",DESC1 );
			selUtils.verifyElementDisp("avail_prop_sectn_xpath", Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValContains ("avail_prop_gene_xpath",RPTFMT);
			selUtils.vExpValContains ("avail_prop_gene_xpath",APPMSEXCEL);

			logger.info("Step 7, 8, 9 :");
			//Delete the Dashboard Report
			deleAavilReprt(DASHBOARD);
			logger.info("AVRPT_1114 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}