package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1402.java $
	$Id: AVRPT1402.java 7768 2014-06-04 12:49:38Z jlakshmi $
 */

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

/**
 * TAM-9321:AVRPT_1402 : Admin role
 * 
 */

public class AVRPT1402 extends AvailableReport {

     /**
	 * TAM-9321:AVRPT_1402 : Admin role
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1402(String browser)
	{
		try {
			logger.info("AVRPT1402 Execution started");
			
			logger.info("Step 1 :");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));
			verifyWelcomePage();
			
			logger.info("Step 2, 3 :");
			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			/* Verify, -A 'Reports actions' section containing the buttons: 
			 * "Launch" (greyed and disabled), "Subscribe" (green and activated)
			 * -Three sections: 'Ingenico', 'Auto_Entity', 'Auto_Test'.  */
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
			logger.info("AVRPT1402 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}