package com.ingenico.tam.testsuite.accesscode;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD1103.java $
$Id: ACCD1103.java 7275 2014-05-20 10:53:18Z shkumar $
*/
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;

/**
* Search an access code.
*/
public class ACCD1103 extends AccessCode {
/**TAM-9265:ACCD_1103 : Access code search with all the field
 * **/
	@Test	
	public void accd1103(){
		try {

			logger.info("ACCD_1103 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));
		
			logger.info("Step 1 :");
			/*Click on 'Access Code' in the left menu of the TMS page
			The 'TMS >> Access Code >> Auto_Test' page is displayed*/
			startingSetup("accd_link", ACCDBRDCRUMB);
			
			tokenNo= ">"; codeVal = "0000000002";
			logger.info("Step 2 :");
			/*Click on the 'Find' button at the top of the page.The 'Find access
			 *code' drop-down window is displayed*/
			selUtils.clickOnWebElement("find_but_xpath", findButt);
			selUtils.verifyElementDisp("find_acc_code_lbl_xpath", FINDACCCODE);
			
			logger.info("Step 3 :");
			/*'Set the 'Code name' field to 'Auto_ACCD_3''.The field is set to 
			 * 'Auto_ACCD_3'.*/
			addAdVerifyVal("find_code_name_xpath", accCod[2], CDNAME);
			
			logger.info("Step 4 :");
			/*Set the 'Remaining Token' field to '>' Set the 'Value' field to 
			 *'0000000002'.The field is set to '>'. The field is set to 
			 *'0000000002'*/
			
			selUtils.vselectedItemInDrpDn("remainCondition_id", tokenNo);
			
			logger.info("Step 5 :");
			/*Click on the 'Search' button of the 'Find access code' drop-down 
			 *window.The 'TMS >> Access Code' page is displayed.The table 
			 *contains only the access code with the code names 'Auto_ACCD_3'.*/
			
			vAcessCodenameSrcho(CDNAME,accCod[2]);
			//vOnlyExpTabVal(accCod[2]);
			vOnly1InstOfExpVal("col_val_xpath", nameLbl,
					accCod[2], accCod[2]);
			logger.info("ACCD_1103 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}
	
