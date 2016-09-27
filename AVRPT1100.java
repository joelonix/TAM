package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1100.java $
	$Id: AVRPT1100.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

     /**
	 * 	TAM-9283:AVRPT_1100 : Check the 'Available Reports' page
	 * 
	 */
public class AVRPT1100 extends AvailableReport {

     /**
	 * 	TAM-9283:AVRPT_1100 : Check the 'Available Reports' page
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1100(String browser)
	{
		try {
			logger.info("AVRPT_1100 Execution started");
			
			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			/* Verify buttons: "Launch" (greyed and disabled), "Subscribe" 
			 *(green and activated)-Three sections: 'Ingenico', 'Auto_Entity','Auto_Test'*/
			vGrpButts("reports_acts_btns_xpath", repActsButts, REPACT);
			Assert.assertTrue(selUtils.getObject("launch_link").getAttribute("class").contains("inactive_button"), " Expected element is not disabled");
			logger.info("Verified, '"+repActsButts[0]+"' button is disabled.");
			webEleExpBckgrdClr("launch_link", greyClr, "rgba(153, 153, 153");
			selUtils.vEleEnabled("subs_butt_xpath", repActsButts[1]);
			webEleExpBckgrdClr("subs_butt_xpath", blueClr, "rgba(0, 57, 153");
			vExpVals(repSections, "rep_sections_css");
			
			logger.info("AVRPT_1100 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}