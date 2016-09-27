package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1101.java $
	$Id: AVRPT1101.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;


 /**
 * AM-9284:AVRPT_1101 :
 * 'Available Reports/Reports actions' section - Display the Modal window 'Subscription generic reports' with columns configurations
 * 
 */
public class AVRPT1101 extends AvailableReport {

	/**
	 * AM-9284:AVRPT_1101 :
	 * 'Available Reports/Reports actions' section - Display the Modal window 'Subscription generic reports' with columns configurations
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1101(String browser)
	{
		try {
			logger.info("AVRPT_1101 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/* Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Access Code >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			// Delete all reports from view tree.
			unSubScrReps();

			logger.info("Step 2 :");
			/* Click on the "Subscribe" button. Verify, a modal window 
			 * "Subscription generic reports" window verification*/
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);
			vExpColsInTab(selUtils.getTabColHds("subscr_gen_rep_hdrs_css"), subscGenRepColHdr);
			vExpValsInAllRow("ROWINDEX", "COLINDEX");		

			logger.info("AVRPT_1101 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}