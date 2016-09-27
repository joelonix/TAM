package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1400.java $
	$Id: AVRPT1400.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;

/**
	 * 	TAM-9319:AVRPT_1400 : Check that the Viewer role can not view buttons 'Launch', 'Subscribe' in the 'Reports actions' section
	 * 
	 */
public class AVRPT1400 extends AvailableReport {

     /**
	 * 	TAM-9319:AVRPT_1400 : Check that the Viewer role can not view buttons 'Launch', 'Subscribe' in the 'Reports actions' section
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1400(String browser)
	{
		try {
			logger.info("AVRPT_1400 Execution started");
			
			logger.info("Step 1 :");
			login(config.getProperty("normaluser1"),config.getProperty("superuserPassword"));
			
			// Verify The 'Welcome to Ingenico e-Portal' page is displayed
			verifyWelcomePage();

			logger.info("Step 2, 3 :");
			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			/* Verify, -Three sections: 'Ingenico', 'Auto_Entity', 'Auto_Test'. 
			 * There is no 'Reports actions' section with the buttons "Launch" 
			 * and "Subscribe".*/
			vExpVals(repSections, "rep_sections_css");
			selUtils.vEleNotPresent("reports_acts_btns_xpath", REPACT);
			
			logger.info("Step 4 :");
			logger.info("AVRPT_1400 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}