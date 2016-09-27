package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD1202.java $
	$Id: ACCD1202.java 13732 2015-06-04 10:52:35Z sparween $
 */
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;

/**
 * Try to search for an access code with unauthorized characters in the 'Find access code' drop-down window fields.
 */
public class ACCD1202 extends AccessCode {

	/**
	 * TAM-9270:ACCD_1202.
	 * Try to search for an access code with unauthorized characters in the 'Find access code' drop-down window fields:.
	 */
	@Parameters({"browser"})
	@Test
	public void accd1202(String browser){
		try {
			logger.info("ACCD_1202 Execution started");

			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			// Move to Access Code module.
			startingSetup("accd_link", ACCDBRDCRUMB);	

			logger.info("Step 2 :");
			// Click on the 'Find' button at the top of the page. Verify, the 'Find access code' drop-down window is displayed.
			selUtils.clickOnWebElement("find_but_xpath", findButt);
			selUtils.vExpValPresent("find_acc_code_lbl_xpath", FINDACCCODE);		

			logger.info("Step 3 and 4 :");
			/*Error testing with 'Access code' field with special char */			
			for (cnti = 0; cnti < spelChar.length; cnti++) {
				selUtils.populateInputBox("accd_acc_code_id", spelChar[cnti]);
				vValFrmInputBox("accd_acc_code_id", spelChar[cnti], ACCODE);
				verifyPopUpErrMsgWithInvaliData("srch_link", "accd_find_inval_err_msg_id", unAuthErrMsg1, "accd_find_inval_err_icn_css", "accd_acc_code_id");
				vRestAdBlnkFld("reset_link", "accd_acc_code_id", ACCODE);			
			}

			logger.info("Step 5 and 6 :");
			/*Error testing with 'Code name' field with special char */
			for (cnti = 0; cnti < spelChar.length; cnti++) {
				selUtils.populateInputBox("find_code_name_xpath", spelChar[cnti]);
				vValFrmInputBox("find_code_name_xpath", spelChar[cnti], CDNAME);
				verifyPopUpErrMsgWithInvaliData("srch_link", "accd_find_inval_err_msg_id", unAuthErrMsg1, "accd_find_inval_err_icn_css", "find_code_name_xpath");
				vRestAdBlnkFld("reset_link", "find_code_name_xpath", CDNAME);			
			}

			logger.info("Step 7, 8 and 9 :");
			/*Error testing with 'value' field with special char */			
			for (cnti = 0; cnti < spelChar.length; cnti++) {
				selUtils.populateInputBox("find_value_id", spelChar[cnti]);
				vValFrmInputBox("find_value_id", spelChar[cnti], valueLbl);
				verifyPopUpErrMsgWithInvaliData("srch_link", "accd_find_inval_err_msg_id", unAuthErrMsg1, "accd_find_inval_err_icn_css", "find_value_id");
				vRestAdBlnkFld("reset_link", "find_value_id", valueLbl);
			}

			logger.info("ACCD_1202 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}