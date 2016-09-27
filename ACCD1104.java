package com.ingenico.tam.testsuite.accesscode;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD1104.java $
$Id: ACCD1104.java 7275 2014-05-20 10:53:18Z shkumar $
*/
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;


/**
* Search an access code.
*/
public class ACCD1104 extends AccessCode {
/** TAM-9266:ACCD_1104 : Access code search with wild card
 *  **/
	@Parameters({"browser"})
	@Test	
	public void accd1104(String browser){
		try {
              
			logger.info("ACCD_1104 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));
		
			codeName = "Auto_ACCD_*";
			
			logger.info("Step 1 :");
			/*Click on 'Access Code' in the left menu of the TMS page
			The 'TMS >> Access Code >> Auto_Test' page is displayed*/
			startingSetup("accd_link", ACCDBRDCRUMB);
			
			logger.info("Step 2 :");
			/*Click on the 'Find' button at the top of the page.The 'Find access
			 *code' drop-down window is displayed*/
			selUtils.clickOnWebElement("find_but_xpath", findButt);
			selUtils.verifyElementDisp("find_acc_code_lbl_xpath", FINDACCCODE);
			
			logger.info("Step 3 :");
			/*Set the 'Code name' field to 'Auto_ACCD_*'.The field is set to 
			 *'Auto_ACCD_*'*/
			addAdVerifyVal("find_code_name_xpath", codeName, CDNAME);
			
			logger.info("Step 4 :");
			/*Click on the 'Search' button of the 'Find access code' drop-down 
			 *window.The 'TMS >> Access Code' page is displayed.The table 
			 *contains the access codes with the code names 'Auto_ACCD_1', 
			 *'Auto_ACCD_2' and 'Auto_ACCD_3'*/
			selUtils.clickOnWebElement("srch_link", srch);
			selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
			selUtils.vExpValNotPresent("find_acc_code_lbl_xpath", FINDACCCODE);
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);
			//vExpColVals(accCod, browser, "acode_list_css");
			selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css", editPageDispTxt);
			vExpVals(accCod, "acode_list_css");
			
			logger.info("ACCD_1104 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}
	
