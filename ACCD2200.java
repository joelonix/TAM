package com.ingenico.tam.testsuite.accesscode;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD2200.java $
	$Id: ACCD2200.java 7275 2014-05-20 10:53:18Z shkumar $
 */
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.Estates;
import com.ingenico.tam.objects.ObjectFactory;
/**
 * Create and delete an access code.
 */
public class ACCD2200 extends AccessCode {
	/**
	 * TAM-9277:ACCD_2200: Mandatory fields at access code modification
	 */
	@Test
	public void accd2200(){
		try {

			logger.info("ACCD_2200 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			//Delete if same access code already exist on Estate page.
			aCode = "ACCD_2200"; tokenNo = "2200";
			startingSetup("estates_link", Estates.breadCrumbEstates);
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);

			logger.info("Step 1 :");
			/*Click on 'Access Code' in the left menu of the TMS page
			The 'TMS >> Access Code >> Auto_Test' page is displayed*/
			selUtils.clickOnWebElement("accd_link", ACCODE);
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);

			logger.info("Step 2, 3, 4 :");
			/*Create access code' with edit box ticked*/
			cretAccCode(aCode, tokenNo, true);
			selUtils.verifyBreadCrumb(ACCDEDTBRDCRUMB+aCode);
			
			logger.info("Step 5 :");
			/*Click on the 'Modify' button.The 'Modify an access code' modal 
			 *window is displayed.*/
			verifyExpWinDisp("modify_link", MODACCCODE);
			
			logger.info("Step 6 :");
			/*Remove value inside the 'Add token' field to keep it blank and
			 *click on the 'Generate' button.*/
			selUtils.getObject("accd_add_token_id").clear();
			verifyPopUpErrMsgWithInvaliData("accd_gen_token_id","accd_renew_err_msg_id", ACCERRMSG, "mod_err_icon_xpath", "accd_add_token_css");
		
			logger.info("Step 7 :");
			/*Click on the 'Close' button.The modal window is closed*/
			selUtils.verifyElementDisp("accd_edt_cls_id", closeButton);
			
			logger.info("Step 8 :");
			/*Delete the access code from estate page*/
			logger.info("Step 9 :");
			selUtils.clickOnWebElement("estates_link", Estates.estLbl);
			selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);
			selUtils.vExpValPresent("accd_est_name_xpath", nameLbl, aCode, aCode);
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);	
			
		
			logger.info("ACCD_2200 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}