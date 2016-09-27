package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1108.java $
	$Id: AVRPT1108.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Terminals;

/**
 * TAM-9291 : Add the Report '24h Calls Status'
 * 
 */

public class AVRPT1108 extends AvailableReport {

	/**
	 * TAM-9291 : Add the Report '24h Calls Status'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1108(String browser)
	{
		try {
			logger.info("AVRPT_1108 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			logger.info("Step 1 :");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			// Delete all reports from view tree.
			deleAavilReprt(CALS24STATS);

			/*Click on the "Subscribe" button. Verify, 
			a modal window "Subscription generic reports" is displayed.*/
			logger.info("Step 2 :");
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/* Tick the check box in the 'Select' column of the table for the
			 *  report with the 'Reports name' '24h Calls Status' and "Subscribe"
			 *  and verify */
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, CALS24STATS);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/* Click on 'Calls' under the 'Auto_Test' section. Verify, 
			 * The 'Launch' button is still greyed and not available for clicking.
			 *and verify Description tab*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("exp_rep_xpath", text, CALLS);
			Assert.assertTrue(selUtils.getObject("launch_link").getAttribute("class").contains("inactive_button"), " Expected element is not disabled");
			logger.info("Verified, '"+repActsButts[0]+"' button is disabled.");
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");		
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.vExpValPresent("text_xpath", text, CALLS, CALLS+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, descript, descript);
			selUtils.vExpValPresent("txt_td_xpath",text, ATTERSTACALDESC, ATTERSTACALDESC);
			selUtils.vExpValPresent("text_xpath", text, Terminals.edtTrmTabs[1], Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, GENEMOD,GENEMOD);
			selUtils.vExpValPresent("txt_xpath",text, onReq, onReq);
			selUtils.vExpValPresent("txt_xpath",text, timZone,timZone);
			selUtils.vExpValPresent("txt_td_xpath",text, cooUniTim,cooUniTim);

			/* Click on '24h Calls Status' under the 'Auto_Test' section.
			 *  Verify, the button 'Launch' is enabled and green.
			 *  verify the "Description" tab */
			logger.info("Step 6 :");
			selUtils.clickOnWebElement("exp_rep_xpath", text, CALS24STATS);
			waitMethods.waitForElementDisplayed("avail_desc_tab_xpath");
			selUtils.vEleEnabled("Launh_butt_xpath", repActsButts[0]);
			webEleExpBckgrdClr("Launh_butt_xpath", blueClr, "rgba(0, 57, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.vExpValPresent("text_xpath", text, CALS24STATS, CALS24STATS+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, descript, descript);
			selUtils.vExpValPresent("txt_td_xpath",text, DESC5, DESC5);
			selUtils.vExpValPresent("text_xpath", text, Terminals.edtTrmTabs[1], Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValPresent("txt_xpath",text,RPTFMT,RPTFMT);
			selUtils.vExpValPresent("txt_td_xpath",text, APPMSEXCEL,APPMSEXCEL);

			/* Delete '24h Calls Status' Report*/
			logger.info("Step 7, 8 & 9 :");
			unSubScrRepsProc("exp_rep_del_icn_xpath", CALS24STATS);

			logger.info("AVRPT_1108 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}