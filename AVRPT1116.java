package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1116.java $
	$Id: AVRPT1116.java 7587 2014-05-29 10:55:15Z jlakshmi $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Terminals;

/**
 * 	TAM-9299:AVRPT_1116: Add the Report 'Calls by product'
 * 
 */

public class AVRPT1116 extends AvailableReport {

	/**
	 * 	TAM-9299:AVRPT_1116: Add the Report 'Calls by product'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1116(String browser)
	{
		try {
			logger.info("AVRPT_1116 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on "Available Reports" in the left menu of the 'TMS' page
			The 'TMS >> Available Reports >> Auto_Test' page is displayed*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			//delete existing dashboard report
			deleAavilReprt(CALSBYPROD);

			logger.info("Step 2 :");
			/*Click on the "Subscribe" button
			A modal window "Subscription generic reports" is displayed*/
			verifyExpWinDisp("subs_butt_xpath",SUBSCGENRPT);


			/*Tick the check box in the 'Select' column of the table for the report
			 *  with the 'Reports name' 'Calls by product' and Subscribe */
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, CALSBYPROD);
			cnfmPopupActMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id",SUBSSUCCMSG);

			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");

			logger.info("Step 5 :");
			/*Click on 'Quality indicators' under the 'Auto_Entity' section,
			 *The 'Launch' button is still greyed and not available for clicking.
			 *Verify the "Description" tab */
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, QUALINDI);
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.verifyElementDisp("avail_qual_indi_sectn_xpath", QUALINDI+SECTION);
			selUtils.vExpValContains ("avail_qual_indi_sectn_xpath",desc+":");
			selUtils.vExpValContains ("avail_qual_indi_sectn_xpath",QUALINDDESC);
			selUtils.verifyElementDisp("avail_prop_sectn_xpath", Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValContains ("avail_prop_gene_xpath",GENEMOD);
			selUtils.vExpValContains ("avail_prop_gene_xpath",onReq);
			selUtils.vExpValContains ("avail_prop_time_xpath",timZone);
			selUtils.vExpValContains ("avail_prop_time_xpath",cooUniTim);

			logger.info("Step 6 :");
			/*Click on 'Calls by product' under the 'Auto_Entity' section,
			 * The 'Launch' button is green and available for clicking and verify
			 * The "Description" tab */
			selUtils.clickOnWebElement("avail_auto_enty_sub_xpath", nameLbl, CALSBYPROD);
			waitMethods.waitForElementDisplayed("Launh_butt_xpath");
			webEleExpBckgrdClr("subs_butt_xpath", blueClr, "rgba(0, 57, 153");
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			selUtils.verifyElementDisp("avail_call_by_prod_sectn_xpath", CALSBYPROD+SECTION);
			selUtils.vExpValContains ("avail_call_by_Prod_desc_sectn_xpath",desc+":");
			selUtils.vExpValContains ("avail_call_by_Prod_desc_sectn_xpath",DESC3);
			selUtils.verifyElementDisp("avail_prop_sectn_xpath", Terminals.edtTrmTabs[1]+SECTION);
			selUtils.vExpValContains ("avail_prop_gene_xpath",RPTFMT);
			selUtils.vExpValContains ("avail_prop_gene_xpath",APPMSEXCEL);

			logger.info("Step 7, 8, 9:");
			//Delete 'Calls by product' Report
			deleAavilReprt(CALSBYPROD);
			logger.info("AVRPT_1116 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}