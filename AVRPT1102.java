package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1102.java $
	$Id: AVRPT1102.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Merchants;

/**
 * TAM-9285:AVRPT_1102 : Test the 'Subscribe' button
 * 
 */

public class AVRPT1102 extends AvailableReport {

	/**
	 * TAM-9285:AVRPT_1102 : Test the 'Subscribe' button
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1102(String browser)
	{
		try {
			logger.info("AVRPT_1102 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			// Delete all reports from view tree.
			unSubScrReps();

			logger.info("Step 2 :");
			/*The 'tree view' contains no reports. Verify,
			the button 'Subscribe' is enabled and green.*/
			vExpVals(NORPTAVAIL, "no_rep_avail_css");
			selUtils.vEleEnabled("subs_butt_xpath", repActsButts[1]);
			webEleExpBckgrdClr("subscribe_id", blueClr, "rgba(0, 57, 153");

			logger.info("Step 3 :");
			/*Click on the "Subscribe" button. Verify, a modal 
			window "Subscription generic reports" is displayed.*/
			verifyExpWinDisp("subscribe_link", SUBSCGENRPT);

			logger.info("Step 4 :");
			/* Select all available reports from the table and
			 *  click on the "Subscribe" button*/
			waitMethods.waitForElementDisplayed("selc_menu_xpath");
			List<WebElement> chkboxes = selUtils.getObjects("selc_menu_xpath");
			for(int cnt=0;cnt<chkboxes.size();cnt++)
			{
				chkboxes.get(cnt).click();
			}
			//selUtils.getObject("selc_menu_css").click();
			logger.info("Clicked on '"+slctMenu+"'");
			//selUtils.clickOnWebElement("text_xpath", text, selectionMenu[1], selectionMenu[1]);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);

			logger.info("Step 5 :");
			/*verify the following available report is available in table*/
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");
			vExpVals(repSections, "rep_sections_css");
			selUtils.vExpValPresent("ing_no_rep_avail_css", NORPTAVAIL);
			selUtils.vExpValsPresent("avail_tree_sub_sec_xpath", nameLbl, repSections[1], autoEntSec);
			selUtils.vExpValsPresent("sub_sec_one_xpath", nameLbl, autoEntSec[0], dash);
			selUtils.vExpValsPresent("sub_sec_one_xpath", nameLbl, autoEntSec[1], qaInds);
			selUtils.vExpValsPresent("sub_sec_one_xpath", nameLbl, autoEntSec[2], axs);
			objScrollDwn("view_tree_id");
			selUtils.vExpValsPresent("avail_tree_sub_sec_xpath", nameLbl, repSections[2], autoTestSec);
			selUtils.vExpValsPresent("sub_sec_two_xpath", nameLbl, autoEntSec[0], autoTestCalls);
			selUtils.vExpValsPresent("sub_sec_two_xpath", nameLbl,HEALTHCHK, hltChek);
			selUtils.vExpValsPresent("sub_sec_two_xpath", nameLbl,Merchants.merchants, merTer);
			selUtils.vExpValsPresent("sub_sec_two_xpath", nameLbl,AccessCode.ACCODE, acti);
		
			logger.info("Step 6, 7, 8, 9 :");
			/* Delete the available report by unsubscribing  */
			objScrollUp("view_tree_id");
			for(itemCount = 0; itemCount < allReps.length; itemCount++){
				unSubScrRepsProc("exp_rep_del_icn_xpath", allReps[itemCount]);
			}
			logger.info("AVRPT_1102 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}