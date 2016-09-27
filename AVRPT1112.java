package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1112.java $
	$Id: AVRPT1112.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Terminals;

/**
 * TAM-9295:AVRPT_1112 : Add the Report 'Activity'
 * 
 */
public class AVRPT1112 extends AvailableReport {

	/**
	 * TAM-9295:AVRPT_1112 : Add the Report 'Activity'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1112(String browser)
	{
		try {
			logger.info("AVRPT_1112 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link",AVAILREPBRDCRUB);

			// Delete all reports from view tree.
			deleAavilReprt(actvity);

			logger.info("Step 2 :");
			/*Click on the "Subscribe" button. 
			Verify, a modal window "Subscription generic reports" is displayed.*/
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/* Tick the check box in the 'Select' column of the table for the 
			 * report with the 'Reports name' 'Activity' and Subscribe */
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, actvity);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);

			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			logger.info("Step 5 :");
			/* Click on 'Access code' under the 'Auto_Test' section. Verify,
			 * The 'Launch' button is still grayed and not available for clicking.
			 * verify description tab */
			selUtils.clickOnWebElement("exp_rep_xpath", text, AccessCode.ACCODE);
			Assert.assertTrue(selUtils.getObject("launch_link").getAttribute("class").contains("inactive_button"), " Expected element is not disabled");
			logger.info("Verified, '"+repActsButts[0]+"' button is disabled.");
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");		
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.vExpValPresent("text_xpath", text, AccessCode.ACCODE, AccessCode.ACCODE+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, descript, descript);
			selUtils.vExpValPresent("txt_td_xpath",text, ACCCDDESC, ACCCDDESC);
			selUtils.vExpValPresent("text_xpath", text, Terminals.edtTrmTabs[1], Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, GENEMOD,GENEMOD);
			selUtils.vExpValPresent("txt_xpath",text, onReq, onReq);
			selUtils.vExpValPresent("txt_xpath",text, timZone,timZone);
			selUtils.vExpValPresent("txt_td_xpath",text, cooUniTim,cooUniTim);

			logger.info("Step 6 :");
			/* Click on 'Activity' under the 'Auto_Test' section. 
			 * Verify, the button 'Launch' is enabled and green.
			 * The "Description" tab displays.*/
			selUtils.clickOnWebElement("exp_rep_xpath", text, actvity);
			waitMethods.waitForElementDisplayed("avail_desc_tab_xpath");
			selUtils.vEleEnabled("Launh_butt_xpath", repActsButts[0]);
			webEleExpBckgrdClr("Launh_butt_xpath", blueClr, "rgba(0, 57, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.vExpValPresent("text_xpath", text, actvity, actvity+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, descript, descript);
			selUtils.vExpValPresent("txt_td_xpath",text, DESC9, DESC9);
			selUtils.vExpValPresent("text_xpath", text, Terminals.edtTrmTabs[1], Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValPresent("txt_xpath",text,RPTFMT,RPTFMT);
			selUtils.vExpValPresent("txt_td_xpath",text, APPMSEXCEL,APPMSEXCEL);

			logger.info("Step 7, 8, 9 :");
			/* Delete Statistics Report*/
			unSubScrRepsProc("exp_rep_del_icn_xpath", actvity);

			logger.info("AVRPT_1112 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}