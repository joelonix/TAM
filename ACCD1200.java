package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD1200.java $
	$Id: ACCD1200.java 13732 2015-06-04 10:52:35Z sparween $
 */
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;

/**
 * Try to create an access code omitting to fill the mandatory fields.
 *
 */
public class ACCD1200 extends AccessCode {
	/**
	 * TAM-9268:ACCD_1200.
	 * Try to create an access code omitting to fill the mandatory fields.
	 */
	@Parameters({"browser"})
	@Test
	public void accd1200(String browser){
		try {
			logger.info("ACCD_1200 Execution started");

			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			logger.info("Step 1 :");
			//Move to Access Code module.
			startingSetup("accd_link", ACCDBRDCRUMB);	

			aCode = "ACCD_1200";

			logger.info("Step 2 :");
			// Click on the 'Create access code' window verification.
			verifyExpWinDisp("acc_crt_xpath", CRTACCD);

			logger.info("Step 3 :");
			/*Error testing for Code name*/
			verifyPopUpErrMsgWithInvaliData("accd_gen_id", "acc_crt_err_msg_id", CODEERRMSG, "acc_crt_err_icon_css", "accd_code_name_css");

			logger.info("Step 4 :");
			/* Error testing for Token number OR Expiry date  */
			selUtils.populateInputBox("accd_code_name_css", aCode);
			verifyPopUpErrMsgWithInvaliData("accd_gen_id", "acc_crt_err_msg_id", TOKENEXPIREERRMSG, "acc_crt_err_icon_css", "accd_token_time_id");
			vExpFldClr("acc_crt_exp_date_id", redColor);

			logger.info("Step 5 :");
			// Click on the 'Close' button. Verify, the modal window is closed.
			verifySpecifiedWinNotDisp("accd_crt_cls_butt_id");

			logger.info("ACCD_1200 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}