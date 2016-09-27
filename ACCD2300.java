package com.ingenico.tam.testsuite.accesscode;
/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD2300.java $
	$Id: ACCD2300.java 13732 2015-06-04 10:52:35Z sparween $
 */
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.Estates;
import com.ingenico.tam.objects.ObjectFactory;

/**
 * Try to modify an access code with more than the maximum numeric number allowed for 'Add token' field (max 10).
 */
public class ACCD2300 extends AccessCode {
	/**
	 * TAM-5429:ACCD_2300.
	 * Try to modify an access code with more than the maximum numeric number allowed for 'Add token' field (max 10).
	 */
	@Test
	public void accd2300(){
		try {
			logger.info("ACCD_2300 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			//Delete if same access code already exist on Estate page.
			aCode = "ACCD_2300"; tokenNo = "2300";
			startingSetup("estates_link", Estates.breadCrumbEstates);
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);

			logger.info("Step 1 :");
			//Move to Access Code module.
			pageNavigationAndvBreadCrumb("accd_link", ACCDBRDCRUMB);

			logger.info("Step 2, 3, 4 :");
			/*Create access code  and go to edit page */
			cretAccCode(aCode, tokenNo, true);
			selUtils.verifyBreadCrumb(ACCDEDTBRDCRUMB+aCode);

			/* Click on the 'Modify' button.Test the modification of access code*/
			tokenNo = "11111111111";

			logger.info("Step 5 :");
			verifyExpWinDisp("modify_link", MODACCCODE);

			logger.info("Step 6 :");
			selUtils.populateInputBox("accd_add_token_id", tokenNo);
			cnfmPopupActContainsMsg("accd_gen_token_id", "accd_renew_succ_msg_id", ACCDRENEWSUCCMSG);

			logger.info("Step 7 :");
			verifySpecifiedWinNotDisp("accd_renew_succ_cls_id");
			tokenNo = "2300";
			selUtils.vExpValPresent("accd_edt_add_token_xpath", nameLbl, tokenNo, tokenNo);
			tokenNo = "1111111111";
			selUtils.vExpValPresent("accd_edt_add_token_xpath", nameLbl, tokenNo, tokenNo);

			/* Click on 'Estates' in the left menu of the TMS page navigate to estate
			 * page delete the access ACCD_2300 code from the table  */
			logger.info("Step 8 :");
			selUtils.clickOnWebElement("estates_link", Estates.estLbl);
			selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);
			selUtils.vExpValPresent("accd_est_name_xpath", nameLbl, aCode, aCode);

			logger.info("Step 9, 10, 11 :");
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);

			logger.info("ACCD_2300 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}