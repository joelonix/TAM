package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD2101.java $
	$Id: ACCD2101.java 13732 2015-06-04 10:52:35Z sparween $
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.Estates;
import com.ingenico.tam.objects.ObjectFactory;

/**
 * Create, Modify and delete an access code.
 */
public class ACCD2101 extends AccessCode {

	/**
	 * TAM-5426:ACCD_2101.
	 * Create, Modify and delete an access code.
	 */
	@Parameters({"browser"})
	@Test
	public void accd2101(String browser){
		try {
			logger.info("ACCD_2101 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));


			//Delete if same access code already exist on Estate page.
			aCode = "ACCD_2101"; tokenNo = "2101";
			startingSetup("estates_link", Estates.breadCrumbEstates);
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);

			logger.info("Step 1 :");
			//Move to Access Code module.
			pageNavigationAndvBreadCrumb("accd_link", ACCDBRDCRUMB);

			logger.info("Step 2, 3, 4 :");
			/*Create access code' with edit ticked*/
			cretAccCode(aCode, tokenNo, true);
			selUtils.verifyBreadCrumb(ACCDEDTBRDCRUMB+aCode);

			logger.info("Step 5 :");
			/*Verify 'Modify' popup */
			verifyExpWinDisp("modify_link", MODACCCODE);
			vValFrmInputBox("accd_add_token_id", tskPri0, ADTOKEN);
			selUtils.verifyElementDisp("accd_gen_token_id", ACCDGEN);
			selUtils.verifyElementDisp("accd_edt_cls_id", closeButton);

			logger.info("Step 6 :");
			verifySpecifiedWinNotDisp("accd_edt_cls_id");

			/*Validate modify access code*/
			tokenNo = "1";

			logger.info("Step 7 :");
			verifyExpWinDisp("modify_link", MODACCCODE);

			logger.info("Step 8 :");
			selUtils.populateInputBox("accd_add_token_id", tokenNo);
			vValFrmInputBox("accd_add_token_id", tokenNo, ADTOKEN);

			logger.info("Step 9 :");
			cnfmPopupActContainsMsg("accd_gen_token_id", "accd_renew_succ_msg_id", ACCDRENEWSUCCMSG);

			logger.info("Step 10 :");
			verifySpecifiedWinNotDisp("accd_renew_succ_cls_id");
			tokenNo = "2102";
			selUtils.vExpValPresent("accd_edt_rem_col_xpath", nameLbl, tokenNo, tokenNo);
			tokenNo = "1";
			selUtils.vExpValPresent("accd_edt_add_token_xpath", nameLbl, tokenNo, tokenNo);
			tokenNo = "2101";
			selUtils.vExpValPresent("accd_edt_add_token_xpath", nameLbl, tokenNo, tokenNo);

			logger.info("Step 11 :");
			/* Delete access code window verification*/
			verifyExpWinDisp("accd_del_link", DELACCCODE);
			selUtils.vExpValContains("accd_edt_del_id", DELACCODEMSG);			
			selUtils.verifyElementDisp("accd_edt_del_cnfrm_id", confirm);
			selUtils.verifyElementDisp("accd_edt_del_cls_id", closeButton);

			logger.info("Step 12 :");
			verifySpecifiedWinNotDisp("accd_edt_del_cls_id");

			/*delete access code verification*/
			logger.info("Step 13 :");
			verifyExpWinDisp("accd_del_link", DELACCCODE);

			logger.info("Step 14 :");
			cnfmPopupActContainsMsg("accd_edt_del_cnfrm_id", "accd_edt_del_succ_msg_id", ACCDDELMSG);

			logger.info("Step 15 :");
			verifySpecifiedWinNotDisp("accd_edt_del_succ_cls_id");
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);
			selectMaxSizeinTable("job_show_res_id", browser);
			selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
			vExpValNotPresent("accd_code_name_xpath", nameLbl, aCode, aCode);

			/*Delete the access code from estate page*/
			logger.info("Step 16 :");
			selUtils.clickOnWebElement("estates_link", Estates.estLbl);
			selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);
			selUtils.vExpValPresent("accd_est_name_xpath", nameLbl, aCode, aCode);

			logger.info("Step 17, 18, 19 :");
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);	

			logger.info("ACCD_2101 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}