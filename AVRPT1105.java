package com.ingenico.tam.testsuite.availablereport;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/availablereport/AVRPT1105.java $
	$Id: AVRPT1105.java 14025 2015-07-02 06:44:32Z vkrachuri $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AvailableReport;
import com.ingenico.tam.objects.Estates;
import com.ingenico.tam.objects.Merchants;
import com.ingenico.tam.objects.Terminals;


/**
 * TAM-9288:AVRPT_1105 : Generate the Report 'Merchant’s terminals'
 * 
 */
public class AVRPT1105 extends AvailableReport {
	private static String reportDate="Report title",merchTermRpt="Merchant's terminals report",rptDate="Report date",termStatus="Terminal status";
	
	private static String[] expData1 = {reportDate, merchTermRpt, Estates.estName, client, Merchants.mrchNmLbl, Merchants.mercList[2], 
										Merchants.mrchNumLbl, Merchants.mercNo[2], rptDate, termStatus, signLbl, nameLbl, Terminals.techngyLbl,
										Terminals.prdLbl, Merchants.mrchNmLbl, Merchants.mrchNumLbl,Terminals.partNumLbl, Terminals.srNumLbl, Terminals.prodLbl,
										created, Terminals.defTrms2[4], Terminals.names2[4], techng, Terminals.prd, Merchants.mercList[2], Merchants.mercNo[2],
										Terminals.nos[14], Terminals.nos[14], Terminals.prodM40, created, Terminals.defTrms2[3], Terminals.names2[3], techng, 
										Terminals.prd, Merchants.mercList[2], Merchants.mercNo[2], Terminals.nos[13], Terminals.nos[13], Terminals.prodM40},
							expData2 = {reportDate, merchTermRpt, Estates.estName, client, Merchants.mrchNmLbl, Merchants.mercList[2], 
										Merchants.mrchNumLbl, rptDate, termStatus, signLbl, nameLbl, Terminals.techngyLbl,
										Terminals.prdLbl, Merchants.mrchNmLbl, Merchants.mrchNumLbl,Terminals.partNumLbl, Terminals.srNumLbl, Terminals.prodLbl,
										created, Terminals.defTrms2[4], Terminals.names2[4], techng, Terminals.prd, Merchants.mercList[2], Merchants.mercNo[2],
										Terminals.nos[14], Terminals.nos[14], Terminals.prodM40, created, Terminals.defTrms2[3], Terminals.names2[3], techng, 
										Terminals.prd, Merchants.mercList[2], Merchants.mercNo[2], Terminals.nos[13], Terminals.nos[13], Terminals.prodM40},
							expData3 = {reportDate, merchTermRpt, Estates.estName, client, Merchants.mrchNmLbl, 
										Merchants.mrchNumLbl, Merchants.mercNo[2], rptDate, termStatus, signLbl, nameLbl, Terminals.techngyLbl,
										Terminals.prdLbl, Merchants.mrchNmLbl, Merchants.mrchNumLbl,Terminals.partNumLbl, Terminals.srNumLbl, Terminals.prodLbl,
										created, Terminals.defTrms2[4], Terminals.names2[4], techng, Terminals.prd, Merchants.mercList[2], Merchants.mercNo[2],
										Terminals.nos[14], Terminals.nos[14], Terminals.prodM40, created, Terminals.defTrms2[3], Terminals.names2[3], techng, 
										Terminals.prd, Merchants.mercList[2], Merchants.mercNo[2], Terminals.nos[13], Terminals.nos[13], Terminals.prodM40},	
							expData4 = {reportDate, merchTermRpt, Estates.estName, client, Merchants.mrchNmLbl, 
										Merchants.mrchNumLbl, rptDate, termStatus, signLbl, nameLbl, Terminals.techngyLbl,
										Terminals.prdLbl, Merchants.mrchNmLbl, Merchants.mrchNumLbl,Terminals.partNumLbl, Terminals.srNumLbl, Terminals.prodLbl,
										created, Terminals.defTrms2[4], Terminals.names2[4], techng, Terminals.prd, Merchants.mercList[2], Merchants.mercNo[2],
										Terminals.nos[14], Terminals.nos[14], Terminals.prodM40, created, Terminals.defTrms2[3], Terminals.names2[3], techng, 
										Terminals.prd, Merchants.mercList[2], Merchants.mercNo[2], Terminals.nos[13], Terminals.nos[13], Terminals.prodM40,
										created, Terminals.defTrms2[5], Terminals.names2[5], techng, Terminals.prd, Merchants.mercList[4], Merchants.mercNo[4],
										Terminals.nos[14], Terminals.nos[14], Terminals.prodM40, created, Terminals.defTrms2[3], Terminals.names2[3], techng, 
										Terminals.prd, Merchants.mercList[2], Merchants.mercNo[2], Terminals.nos[15], Terminals.nos[15], Terminals.prodM40};
	
	/**
	 * TAM-9288:AVRPT_1105 : Generate the Report 'Merchant’s terminals'
	 * 
	 */
	@Parameters({"browser"})
	@Test
	public void avrpt1105(String browser)
	{
		try {
			logger.info("AVRPT_1105 Execution started");

			login(config.getProperty("masteruser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			/*Click on 'Available Reports' in the left menu of the TMS page. 
			Verify, the 'TMS >> Available Reports >> Auto_Test' page is displayed.*/
			startingSetup("avail_report_link", AVAILREPBRDCRUB);

			// Delete all reports from view tree.
			deleAavilReprt(MERTERMS);

			logger.info("Step 2 :");
			/* Click on the "Subscribe" button. Verify, a modal window 
			"Subscription generic reports" is displayed.*/
			verifyExpWinDisp("subs_butt_xpath", SUBSCGENRPT);
			
			/*Complete subscription of available report.*/
			logger.info("Step 3 :");
			selUtils.clickOnWebElement("reprt_nme_chek_box_xpath", nameLbl, MERTERMS);
			confrmPopupActionMsg("avail_subsbe_popup_id", "avail_subsbe_succmsg_id", SUBSSUCCMSG);
			logger.info("Step 4 :");
			verifySpecifiedWinNotDisp("avail_subsbe_cls_id");
			
			/*Click on 'Merchant's terminals' under the 'Auto_Entity' section,
			 * The "Description" tab is displayed,Click on the "Launch" button
			 *  a modal window model window*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("exp_rep_xpath", text,MERTERMS);
			selUtils.verifyElementDisp("avail_desc_tab_xpath", desc);
			logger.info("Step 6 :");
			vLaunchPopUpFlds();
			
			/* Set the 'Merchant name' field to 'Auto_Merchant_3', 
			 * Set the 'Merchant number' field to '003', 
			 * Click on "Launch" button. Verify data in'Opening MerchantTerminalsReport.xls'*/
			mrctTrmsRep(Merchants.mercList[2], Merchants.mercNo[2], expData1);
					
			/* Click on the "Launch" button a modal window. Verified, "Launch" is
			 *  displayed containing:-The fields: 'Merchant name' and 'Merchant number'. 
			 * -The buttons: 'Launch', 'Reset', 'Close'. */
			logger.info("Step 10 :");
			vLaunchPopUpFlds();
			
			/* Set the 'Merchant name' field to 'Auto_Merchant_3', keep the 
			 * 'Merchant number' empty, Click on "Launch" button. Verify
			 * data in 'Opening MerchantTerminalsReport.xls'*/
			logger.info("Step 11, 12, 13 :");
			mrctTrmsRep(Merchants.mercList[2], "", expData2);
						
			/* Click on the "Launch" button a modal window. Verified, "Launch" 
			 * is displayed containing:-The fields: 'Merchant name' and 'Merchant number'. 
			 * -The buttons: 'Launch', 'Reset', 'Close'. */
			logger.info("Step 14 :");
			vLaunchPopUpFlds();
			
			/* Keep the 'Merchant name' empty. Set the 'Merchant number' field to '003', 
			 * Click on "Launch" button. Verify,Opening MerchantTerminalsReport.xls*/
			logger.info("Step 15, 16, 17 :");
			mrctTrmsRep("",Merchants.mercNo[2], expData3);
					
			/* Click on the "Launch" button a modal window. 
			 * Verified model window */
			logger.info("Step 18 :");
			vLaunchPopUpFlds();
			
			/* Keep the 'Merchant name' and the 'Merchant number' empty,
			 *Click on "Launch" button. Verify Opening MerchantTerminalsReport.xls
			expected data.*/
			logger.info("Step 19, 20, 21 :");
			mrctTrmsRep("","", expData4);
			
			/*Click on the "Delete" icon near the 'Merchant's terminals' Report. 
			 *unsubscribe the available report*/
			logger.info("Step 22, 23, 24 :");
			unSubScrRepsProc("exp_rep_del_icn_xpath", MERTERMS);
			
			logger.info("AVRPT_1105 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}