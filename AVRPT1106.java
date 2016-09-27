package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1106.java $
	$Id: AVRPT1106.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Terminals;

/**
 * TAM-9289:AVRPT_1106 : Add the Report 'Statistics'
 * 
 */
public class AVRPT1106 extends AvailableReport {

	/**
	 * TAM-9289:AVRPT_1106 : Add the Report 'Statistics'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1106(String browser)
	{
		try {
			logger.info("AVRPT_1106 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			// Delete all reports from view tree.
			deleAavilReprt(statistics);

			logger.info("Step 2 :");
			/*Click on the "Subscribe" button. Verify,
			a modal window "Subscription generic reports" is displayed.*/
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/* Add the Report 'Statistics' */
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, statistics);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);

			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			logger.info("Step 5 :");
			/* Click on 'Calls' under the 'Auto_Test' section. Verify,The 'Launch' 
			 * button is still greyed and not available for clicking. and verify
			 * "Description" tab section */
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

			logger.info("Step 6 :");
			/* Click on 'Statistics' under the 'Auto_Test' section.
			 *  Verify, the button 'Launch' is enabled and green.verify
			 * "Description" tab section */
			selUtils.clickOnWebElement("exp_rep_xpath", text, statistics);
			waitMethods.waitForElementDisplayed("avail_desc_tab_xpath");
			selUtils.vEleEnabled("Launh_butt_xpath", repActsButts[0]);
			webEleExpBckgrdClr("Launh_butt_xpath", blueClr, "rgba(0, 57, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.vExpValPresent("text_xpath", text, statistics, statistics+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, descript, descript);
			selUtils.vExpValPresent("txt_td_xpath",text, DESC6, DESC6);
			selUtils.vExpValPresent("text_xpath", text, Terminals.edtTrmTabs[1], Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValPresent("txt_xpath",text,RPTFMT,RPTFMT);
			selUtils.vExpValPresent("txt_td_xpath",text, APPMSEXCEL,APPMSEXCEL);

			logger.info("Step 7, 8, 9 :");
			/*Delete 'Statistics' Report*/
			unSubScrRepsProc("exp_rep_del_icn_xpath", statistics);

			logger.info("AVRPT_1106 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}