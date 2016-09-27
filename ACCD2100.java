package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD2100.java $
	$Id: ACCD2100.java 13732 2015-06-04 10:52:35Z sparween $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;

/** 
 * Test the 'TMS >> Edit Access Code' page and table.
 */
public class ACCD2100 extends AccessCode {

	/**
	 * TAM-5425:ACCD_2100.
	 * Test the 'TMS >> Edit Access Code' page and table
	 */
	@Parameters({"browser"})
	@Test
	public void accd2100(String browser){
		try {
			logger.info("ACCD_2100 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			//Move to Access Code module.
			startingSetup("accd_link", ACCDBRDCRUMB);

			logger.info("Step 2 :");
			/*Access code edit page verification*/
			aCode = "Auto_ACCD_1";
			selUtils.clickOnWebElement("accd_edit_icn_xpath", nameLbl, aCode);
			selUtils.verifyBreadCrumb(ACCDEDTBRDCRUMB+aCode);
			selUtils.vBackToLstButt();
			selUtils.verifyElementDisp("accd_acc_code_id", ACCODE);
			selUtils.vExpValPresent("accd_code_name_id",aCode);
			selUtils.vExpValPresent("accd_token_time_id",frequency[2]);
			selUtils.verifyElementDisp("accd_exp_date_xpath", EXPDATE);
			vGrpButts("accd_acts_btns_xpath", actsButts, ACCCODEACT);
			vExpColsInTab(selUtils.getTabColHds("accd_call_his_cols_css"),calHisCols);

			logger.info("Step 3 :");
			/*'Historical' tab verification */
			selUtils.clickOnWebElement("accd_his_link", ACCDHIS);
			vExpColsInTab(selUtils.getTabColHds("accd_call_his_cols_css"),hisCols);

			logger.info("Step 4 :");
			selUtils.clkBackToLstButt();
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);

			logger.info("ACCD_2100 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}