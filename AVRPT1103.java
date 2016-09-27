package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1103.java $
	$Id: AVRPT1103.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Merchants;

/**
 * TAM-9286:AVRPT_1103 : One or no report is selected, The button 'Launch' is enabled/disabled
 * 
 */
public class AVRPT1103 extends AvailableReport {

	/**
	 * TAM-9286:AVRPT_1103 : One or no report is selected, The button 'Launch' is enabled/disabled
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1103(String browser)
	{
		try {
			logger.info("AVRPT_1103 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on 'Available Reports' in the left menu of the TMS page.
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			// Delete all reports from view tree.
			deleAavilReprt(MERTERMS);

			logger.info("Step 2 :");
			/*The 'tree view' contains no reports. Verify, The button 'Launch'
			is disabled and grey.*/
			vExpVals(NORPTAVAIL, "no_rep_avail_css");
			Assert.assertTrue(selUtils.getObject("launch_link").getAttribute("class").contains("inactive_button"), " Expected element is not disabled");
			logger.info("Verified, '"+repActsButts[0]+"' button is disabled.");
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");

			logger.info("Step 3 :");
			/*Click on the "Subscribe" button. Verify, a modal 
			window "Subscription generic reports" is displayed.*/
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);

			/* Click on subscribe and complete it*/
			logger.info("Step 4 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, MERTERMS);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);

			logger.info("Step 5 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			/* Click on ' Merchant's terminals' under the 'Auto_Test' section.
			 * un subscribe it */
			logger.info("Step 6 :");
			selUtils.clickOnWebElement("sub_sec_two_xpath", nameLbl, Merchants.merchants, MERTERMS);
			selUtils.vEleEnabled("Launh_butt_xpath", repActsButts[0]);
			webEleExpBckgrdClr("Launh_butt_xpath", blueClr, "rgba(0, 57, 153");

			logger.info("Step 7, 8, 9 :");
			unSubScrRepsProc("exp_rep_del_icn_xpath", MERTERMS);

			logger.info("AVRPT_1103 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}