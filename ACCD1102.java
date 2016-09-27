package com.ingenico.tam.testsuite.accesscode;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD1102.java $
$Id: ACCD1102.java 7275 2014-05-20 10:53:18Z shkumar $
*/

import org.testng.annotations.Test;

import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.CallScheduling;

/**
* Search an access code.
*/
public class ACCD1102 extends AccessCode {
	
	String[][] fieldList = { { "find_acc_code_lbl_xpath", FINDACCCODE },
			{ "accd_acc_code_id", ACCODE},
			{ "find_code_name_xpath", CDNAME},
			{ "find_value_id", valueLbl },
			{ "reset_link", reset}, { "srch_link", srch},
			{ "exp_dateFrom_id", expiryDateFrom},{"exp_dateTo_id", CallScheduling.TOLBL} };
/**TAM-9264:ACCD_1102 : Access code search with each field.
 *  **/
	@Test	
	public void accd1102(){
		try {

			logger.info("ACCD_1102 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));
		
			logger.info("Step 1 :");
			/*Click on 'Access Code' in the left menu of the TMS page
			The 'TMS >> Access Code >> Auto_Test' page is displayed*/
			startingSetup("accd_link", ACCDBRDCRUMB);
			
			tokenNo= "="; codeVal = "0000000002";
			logger.info("Step 2 :");
			/*Click on the 'Find' button at the top of the page.The 'Find access 
			 * code' drop-down window is displayed containing expected*/
			selUtils.clickOnWebElement("find_but_xpath", findButt);
			vDrpDnDefltSelcItem("remainCondition_id", tokenNo, REMTOKEN);
			
			/*selUtils.verifyElementDisp("find_acc_code_lbl_xpath", FINDACCCODE);
			selUtils.verifyElementDisp("accd_acc_code_id", ACCODE);
			selUtils.verifyElementDisp("find_code_name_xpath", CDNAME);
			selUtils.verifyElementDisp("find_value_id", valueLbl);
			selUtils.verifyElementDisp("reset_link", reset);
			selUtils.verifyElementDisp("srch_link", srch);
			selUtils.verifyElementDisp("exp_dateFrom_id", expiryDateFrom);
			selUtils.verifyElementDisp("exp_dateTo_id", CallScheduling.TOLBL);*/
			
			for (int cnti = 0; cnti < fieldList.length; cnti++) {
				selUtils.verifyElementDisp(fieldList[cnti][0],
						fieldList[cnti][1]);
			}
			logger.info("Step 3 :");
			/*Set the 'Code name' field to 'Auto_ACCD_1'.The field is set to 
			 *'Auto_ACCD_1'.*/
			addAdVerifyVal("find_code_name_xpath", accCod[0], CDNAME);
			
			logger.info("Step 4 :");
			/*Click on the 'Search' button of the 'Find access code' drop-down 
			 *window.The 'TMS » Access Code' page is displayed.Only 
			 *'Auto_ACCD_1' is listed in the access code  table.*/
			vAcessCodenameSrcho(CDNAME,accCod[0]);
			//vOnlyExpTabVal(accCod[0]);
			vOnly1InstOfExpVal("col_val_xpath", nameLbl,
					accCod[0], accCod[0]);
			logger.info("Step 5 :");
			/*Click on the 'Find' button at the top of the page.The 'Find access
			 *code' drop-down window is displayed*/
			selUtils.clickOnWebElement("find_but_xpath", findButt);
			selUtils.verifyElementDisp("find_acc_code_lbl_xpath", FINDACCCODE);
			
			logger.info("Step 6 :");
			/*Click on the 'Reset' button.The 'Code name' field becomes blank.*/
			vRestAdBlnkFld("reset_link", "find_code_name_xpath",CDNAME);
			
			logger.info("Step 7 :");
			/*Keep the 'Remaining Token' field to '='.Set the 'Value' field to 
			 *'0000000002'.*/
			/*selUtils.populateInputBox("find_value_id", codeVal);
			vValFrmInputBox("find_value_id", codeVal, VALUE);*/
			addAdVerifyVal("find_value_id",codeVal, VALUE);
			
			logger.info("Step 8 :");
			/*Click on the 'Search' button of the 'Find access code' drop-down 
			 *window.The 'TMS >> Access Code' page is displayed.The table 
			 *contains only the access code with the code names 'Auto_ACCD_2'.*/
			
			vAcessCodenameSrcho(CDNAME,accCod[1]);
			//vOnlyExpTabVal(accCod[1]);
			vOnly1InstOfExpVal("col_val_xpath", nameLbl,
					accCod[1], accCod[1]);
			logger.info("ACCD_1102 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}
	
