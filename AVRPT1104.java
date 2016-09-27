package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1104.java $
	$Id: AVRPT1104.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Merchants;
import com.ingenico.tam.objects.Terminals;

/**
 * TAM-9287:AVRPT_1104 : Add the Report 'Merchant’s terminals'
 * 
 */
public class AVRPT1104 extends AvailableReport {

	/**
	 * TAM-9287:AVRPT_1104 : Add the Report 'Merchant’s terminals'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1104(String browser)
	{
		try {
			logger.info("AVRPT_1104 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			// Delete all reports from view tree.
			deleAavilReprt(MERTERMS);

			logger.info("Step 2 :");
			/*Click on the "Subscribe" button. Verify, a modal window 
			"Subscription generic reports" is displayed.*/
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);
			
			/*Click on subscribe and complete it*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, MERTERMS);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			
			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");
			
			logger.info("Step 5 :");
			/* Click on 'Merchants' under the 'Auto_Test' section. Verify, 
			 * The 'Launch' button is still grayed and not available for clicking.
			 * verify the description section*/
			waitMethods.waitForElementDisplayed("view_tree_id");
			selUtils.clickOnWebElement("exp_rep_xpath", text, Merchants.merchants);
			Assert.assertTrue(selUtils.getObject("launch_link").getAttribute("class").contains("inactive_button"), " Expected element is not disabled");
			logger.info("Verified, '"+repActsButts[0]+"' button is disabled.");
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");	
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.vExpValPresent("text_xpath", text, Merchants.merchants, Merchants.merchants+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, descript, descript);
			selUtils.vExpValPresent("txt_td_xpath",text, DESC8, DESC8);
			selUtils.vExpValPresent("text_xpath", text, Terminals.edtTrmTabs[1], Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, GENEMOD,GENEMOD);
			selUtils.vExpValPresent("txt_xpath",text, onReq, onReq);
			selUtils.vExpValPresent("txt_xpath",text, timZone,timZone);
			selUtils.vExpValPresent("txt_td_xpath",text, cooUniTim,cooUniTim);
			
			logger.info("Step 6 :");
			/* Click on ' Merchant's terminals' under the 'Auto_Test' section. 
			 * Verify, the button 'Launch' is enabled and green.
			 * verify "Description" tab section */
			selUtils.clickOnWebElement("exp_rep_xpath", text, MERTERMS);
			waitMethods.waitForElementDisplayed("avail_desc_tab_xpath");
			selUtils.vEleEnabled("Launh_butt_xpath", repActsButts[0]);
			webEleExpBckgrdClr("Launh_butt_xpath", blueClr, "rgba(0, 57, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.vExpValPresent("text_xpath", text, MERTERMS, MERTERMS+SECTION);
			selUtils.vExpValPresent("txt_xpath",text, descript, descript);
			selUtils.vExpValPresent("txt_td_xpath",text, DESC8, DESC8);
			selUtils.vExpValPresent("text_xpath", text, Terminals.edtTrmTabs[1], Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValPresent("txt_xpath",text,RPTFMT,RPTFMT);
			selUtils.vExpValPresent("txt_td_xpath",text, APPMSEXCEL,APPMSEXCEL);
			
			logger.info("Step 7, 8, 9 :");
			/* Click on the "Delete" icon near the 'Merchant's terminals' Report. 
			 * Verify, a modal window "Unsubscription generic report" is displayed.*/
			unSubScrRepsProc("exp_rep_del_icn_xpath", MERTERMS);
			
			logger.info("AVRPT_1104 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}