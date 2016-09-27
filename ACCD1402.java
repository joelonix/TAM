package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/estates/EST1402.java $
	$Id: EST1402.java 6545 2014-04-28 07:11:59Z jlakshmi $
 */


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;

/**
 * Admin role
 */
public class ACCD1402 extends AccessCode {

	/**
	 * TAM-5424:ACCD_1402: Admin role
	 */
	@Parameters({"browser"})
	@Test
	public void accd1402(String browser)
	{
		try {
			logger.info("ACCD_1402 Execution started");

			logger.info("Step 1 :");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));	

			/*Log in to ePortal with a TMS Admin user
			 * The 'Welcome to Ingenico e-Portal' page is displayed*/
			verifyWelcomePage();

			logger.info("Step 2, 3 :");
			/*Click on 'Access Code' in the left menu of the TMS page
			The 'TMS >> Access Code >> Auto_Test' page is displayed containing:*/
			startingSetup("accd_link", ACCDBRDCRUMB);

			/*Verify the access code page*/
			selUtils.verifyElementDisp("find_but_xpath", findButt);
			vExpButtInGrp("accd_code_btns_xpath", CRTACCD, CRTACCD);
			vExpColsInTab(selUtils.getTabColHds("acc_cod_hdr_css"), accTblhedr);
			selectMaxSizeinTable("job_show_res_id", browser);
			selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
			vExpColVals(accCod, browser, getTableHeadCursVals("accd_tab_col_val_xpath","accd_tab_col_hedr_xpath","INDEX", CDNAME));

			/*'Auto_ACCD_1', 'Auto_ACCD_2' and 'Auto_ACCD_3'  have an Edit 
			 * and Delete icons in their 'Actions' column.*/
			for(int cnti=0;cnti<accCod.length;cnti++)
			{
				selUtils.vExpIcon("accd_edit_icn_xpath", accCod[cnti], edit);
				selUtils.vExpIcon("accd_del_icn_xpath", accCod[cnti], delete);
			}
			logger.info("Step 4 :");

			logger.info("ACCD_1402 successfully executed");		
		}catch (Throwable t) {
			handleException(t);
		}
	}
}