package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD1201.java $
	$Id: ACCD1201.java 13732 2015-06-04 10:52:35Z sparween $
 */
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;

/**
 * Try to create an access code with unauthorized characters for 'Code name' and 'Token number'.
 */
public class ACCD1201 extends AccessCode {

	/**
	 * TAM-9269:ACCD_1201.
	 * Try to create an access code with unauthorized characters for 'Code name' and 'Token number':.
	 */
	@Parameters({"browser"})
	@Test
	public void accd1201(String browser){
		try {
			logger.info("ACCD_1201 Execution started");
			final String tokenNos[] = {"%1201", "1201%", "\\1201", "1201\\", "<1201", "1201>"},
					aCodes[] = {"%ACCD_1201", "ACCD_1201%", "\\ACCD_1201", "ACCD_1201\\", "<ACCD_1201", "ACCD_1201>"};

			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			//Move to Access Code module.
			startingSetup("accd_link", ACCDBRDCRUMB);	

			aCode = "ACCD_1201";
			tokenNo = "1201";

			logger.info("Step 2 :");
			/*Click on the 'Create access code' button. Verify, 
			the 'Create access code' modal window is displayed.*/
			verifyExpWinDisp("acc_crt_xpath", CRTACCD);

			logger.info("Step 3 :");
			/*unauthorized char for Code name field */
			/*selUtils.populateInputBox("accd_code_name_css", aCode);
			vValFrmInputBox("accd_code_name_css", aCode, CDNAME);*/
			addAdVerifyVal("accd_code_name_css", aCode, CDNAME);
			
			logger.info("Step 4, 5 :");
			for(itemCount = 0; itemCount < tokenNos.length; itemCount++) {
				selUtils.populateInputBox("accd_token_time_id", tokenNos[itemCount]);
				verifyPopUpErrMsgWithInvaliData("accd_gen_id", "acc_crt_err_msg_id", unAuthErrMsg1, "acc_crt_err_icon_css", "accd_token_time_id");
			}

			/*unauthorized char for Token number field*/
			logger.info("Step 6 :");
			/*selUtils.populateInputBox("accd_token_time_id", tokenNo);
			vValFrmInputBox("accd_token_time_id", tokenNo, TOKENNAME);*/
			addAdVerifyVal("accd_token_time_id", tokenNo, TOKENNAME);
			
			logger.info("Step 7, 8 :");
			for(itemCount = 0; itemCount < aCodes.length; itemCount++) {
				selUtils.populateInputBox("accd_code_name_css", aCodes[itemCount]);
				verifyPopUpErrMsgWithInvaliData("accd_gen_id", "acc_crt_err_msg_id", unAuthErrMsg1, "acc_crt_err_icon_css", "accd_code_name_css");
			}

			logger.info("Step 9 :");
			// Click on the 'Close' button. Verify, the modal window is closed.
			verifySpecifiedWinNotDisp("accd_crt_cls_butt_id");

			logger.info("ACCD_1201 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}