package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/estates/EST1400.java $
	$Id: EST1400.java 6545 2014-04-28 07:11:59Z jlakshmi $
 */


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;

/**
 * Viewer role
 */
public class ACCD1400 extends AccessCode {
	/**
	 * TAM-5422:ACCD_1400: Viewer role
	 */
	@Parameters({"browser"})
	@Test
	public void accd1400(String browser)
	{
		try {
			logger.info("ACCD_1400 Execution started");

			logger.info("Step 1 :");
			login(config.getProperty("normaluser1"),config.getProperty("superuserPassword"));	

			/*Log in to ePortal with a TMS Admin user
			 *The 'Welcome to Ingenico e-Portal' page is displayed*/
			verifyWelcomePage();

			logger.info("Step 2, 3 :");
			//Navigate to access code page
			startingSetup("accd_link", ACCDBRDCRUMB);

			/*Verify access code page and default data in table*/
			selUtils.verifyElementDisp("find_but_xpath", findButt);
			vExpColsInTab(selUtils.getTabColHds("acc_cod_hdr_css"), accTblhedr);
			vExpColVals(accCod, browser, getTableHeadCursVals("accd_tab_col_val_xpath","accd_tab_col_hedr_xpath","INDEX", CDNAME));

			//There is the Find icon in the 'Actions' column of the access codes.
			webelements =getTableHeadCursVals("accd_fnd_val_xpath","acc_cod_hdr_css","INDEX",calHisCols[4]);
			for(cnti=0;cnti<webelements.size();cnti++){
				selUtils.verifyExpIconDisplayed(webelements.get(cnti),findButt);
			}

			//There is no Delete icon in the 'Actions' column of the access codes.
			//selUtils.vEleNotPresent("accd_del_icon_xpath",delete);
			for(cnti=0;cnti<accCod.length;cnti++){
			selUtils.vEleNotPresent("accd_del_icn_xpath", accCod[cnti], delete);
		    }
		
			/*There is no 'Access code actions' buttons section with the button 
			'Create access code'.*/
			selUtils.vEleNotPresent("acc_crt_xpath",CRTACCD);
			selUtils.vEleNotPresent("accd_cod_actn_xpath",ACCCODEACT);

			logger.info("Step 4 :");
			logger.info("ACCD_1400 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}