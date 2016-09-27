package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/estates/EST1401.java $
	$Id: EST1401.java 6545 2014-04-28 07:11:59Z jlakshmi $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;

/**
 * User role
 * */
public class ACCD1401 extends AccessCode {

	/**
	 * TAM-5423:ACCD_1401: User role
	 */
	@Parameters({"browser"})
	@Test
	public void accd1401(String browser)
	{
		try {
			logger.info("ACCD_1401 Execution started");
			logger.info("Step 1 :");
			login(config.getProperty("normaluser2"),config.getProperty("superuserPassword"));	

			/*Log in to ePortal with a TMS Viewer user
			 * The 'Welcome to Ingenico e-Portal' page is displayed*/
			verifyWelcomePage();

			logger.info("Step 2, 3 :");
			/*Navigate to access code page*/
			startingSetup("accd_link", ACCDBRDCRUMB);

			/*Verify the access code page*/
			selUtils.verifyElementDisp("find_but_xpath", findButt);
			vExpButtInGrp("accd_code_btns_xpath", CRTACCD, CRTACCD);
			vExpColsInTab(selUtils.getTabColHds("acc_cod_hdr_css"), accTblhedr);

			//There is the Edit icon in the 'Actions' column of the access codes.
			webelements =getTableHeadCursVals("accd_edt_val_xpath","acc_cod_hdr_css","INDEX",calHisCols[4]);
			for(cnti=0;cnti<webelements.size();cnti++){
				selUtils.verifyExpIconDisplayed(webelements.get(cnti),edit);
			}

			//There is no Delete icon in the 'Actions' column of the access codes.
			//selUtils.vEleNotPresent("accd_del_icon_xpath",delete);	
			for(cnti=0;cnti<accCod.length;cnti++){
				selUtils.vEleNotPresent("accd_del_icn_xpath", accCod[cnti], delete);
			    }
			/* The table contains at least the code names: 
			 * 'Auto_ACCD_1', 'Auto_ACCD_2' and 'Auto_ACCD_3'*/
			selectMaxSizeinTable("job_show_res_id", browser);
			selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
			vExpColVals(accCod, browser, getTableHeadCursVals("accd_tab_col_val_xpath","accd_tab_col_hedr_xpath","INDEX", CDNAME));
			logger.info("Step 4 :");

			logger.info("ACCD_1401 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}