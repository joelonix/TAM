package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD2201.java $
	$Id: ACCD2201.java 13732 2015-06-04 10:52:35Z sparween $
 */

import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.Estates;
import com.ingenico.tam.objects.ObjectFactory;

/**
 * 
 * Try to modify an access code with unauthorized characters for Add token.
 *
 */
public class ACCD2201 extends AccessCode {
	
	/**
	 * TAM-5428:ACCD_2201.
	 * Try to modify an access code with unauthorized characters for Add token:\<>%A-Za-z&é"'(-è_çà)=+°/*,;:!§/.?£µù^
	 */
	@Test
	public void accd2201(){
		try {
			logger.info("ACCD_2201 Execution started");
			final String[] tokenNos = {"\\", "<", ">", "%", "\""}, tokenNoAlpa = { "A", "Z", "a", "z", "é"};
			
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			//Delete if same access code already exist on Estate page.
			aCode = "ACCD_2201"; tokenNo = "2201";
			startingSetup("estates_link", Estates.breadCrumbEstates);
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);

			logger.info("Step 1 :");
			//Move to Access Code module.
			pageNavigationAndvBreadCrumb("accd_link", ACCDBRDCRUMB);

			
			logger.info("Step 2, 3, 4 :");
			/*create access code create access code*/
			cretAccCode(aCode, tokenNo, true);
			selUtils.verifyBreadCrumb(ACCDEDTBRDCRUMB+aCode);

			/*Click on the 'Modify' button.
			 * The 'Modify an access code' modal window is displayed.*/
			logger.info("Step 5 :");
			verifyExpWinDisp("modify_link", MODACCCODE);

			/*Check 'Add token' field with unauthorized characters ( \<>"% )*/
			logger.info("Step 6, 7 :");
			for(cnti = 0; cnti < tokenNos.length; cnti++) {
				selUtils.populateInputBox("accd_add_token_id", tokenNos[cnti]);
				verifyErrMsgWithInvaliData("accd_gen_token_id", "accd_renew_err_msg_id", unAuthErrorMsg, "accd_renew_crt_err_icn_css");
			}

			/*Check 'Add token' field with unauthorized characters : Z, a, z, é*/
			logger.info("Step 8, 9 :");
			for(cnti = 0; cnti < tokenNoAlpa.length; cnti++) {
				selUtils.populateInputBox("accd_add_token_id", tokenNoAlpa[cnti]);
				verifyErrMsgWithInvaliData("accd_gen_token_id", "accd_renew_err_msg_id", ADDTOKNERRMSG, "accd_renew_crt_err_icn_css");
			}
			/*Click on the 'Close' button,The 'Modify an access code' 
			 * modal window is closed*/
			logger.info("Step 10 :");
			verifySpecifiedWinNotDisp("accd_edt_cls_id");

			/*Delete ACCD_2201 form estate*/
			logger.info("Step 11 :");
			selUtils.clickOnWebElement("estates_link", Estates.estLbl);
			selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);
			selUtils.vExpValPresent("accd_est_name_xpath", nameLbl, aCode, aCode);

			logger.info("Step 12, 13, 14 :");
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);

			logger.info("ACCD_2201 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}