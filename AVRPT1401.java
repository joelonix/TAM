package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1401.java $
	$Id: AVRPT1401.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

/**
 * 	 TAM-9320:AVRPT_1401 : User role
 * 
 */
public class AVRPT1401 extends AvailableReport {

	/**
	 * 	 TAM-9320:AVRPT_1401 : User role
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1401(String browser)
	{
		try {
			logger.info("AVRPT1401 Execution started");

			// Log in to TAM with a User role. Verify The 'Welcome to Ingenico e-Portal' page is displayed.
			logger.info("Step 1 :");
			login(config.getProperty("normaluser2"),config.getProperty("superuserPassword"));			
			verifyWelcomePage();

			//Available report lading page verification 
			logger.info("Step 2 & 3 :");
			startingSetup("avail_report_link", AVAILREPBRDCRUB);
			vGrpButts("reports_acts_btns_xpath", repActsButts, REPACT);
			Assert.assertTrue(selUtils.getObject("launch_link").getAttribute("class").contains("inactive_button"), " Expected element is enabled");
			logger.info("Verified, '"+repActsButts[0]+"' button is disabled.");
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");
			waitMethods.waitForElementDisplayed("subs_butt_xpath");
			Assert.assertFalse(selUtils.getObject("subscribe_id").getAttribute("class").contains("inactive_button"), " Expected element is disabled");
			logger.info("Verified, '"+repActsButts[1]+"' button is enabled.");
			webEleExpBckgrdClr("subscribe_id", blueClr, "rgba(0, 57, 153");
			vExpVals(repSections, "rep_sections_css");
			
			logger.info("Step 4 :");
			logger.info("AVRPT1401 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}