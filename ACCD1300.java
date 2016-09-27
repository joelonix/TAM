package com.ingenico.tam.testsuite.accesscode;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD1300.java $
	$Id: ACCD1300.java 13732 2015-06-04 10:52:35Z sparween $
 */
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.Estates;
import com.ingenico.tam.objects.ObjectFactory;

/**
 * Try to add an access code with more than the maximum number of characters allowed for 'Code name' (max 100) 
 * and 'Token number' (max 10) at access code creation.
 */
public class ACCD1300 extends AccessCode {
	/**
	 * TAM-9271:ACCD_1300.
	 * Try to add an access code with more than the maximum number of characters allowed for 'Code name' (max 100) and 'Token number' (max 10) at 
	 * access code creation.
	 */
	@Parameters({"browser"})
	@Test
	public void accd1300(String browser){
		try {
			logger.info("ACCD_1300 Execution started");

			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			aCode = "ACCD1300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
			tokenNo = "13000000000";

			//Delete if same access code already exist on Estate page.
			startingSetup("estates_link", Estates.breadCrumbEstates);
			ObjectFactory.getEstateInstance().delEstAccCod(aCode.substring(0, aCode.length()-1));

			logger.info("Step 1 :");
			// Click on 'Access Code' in the left menu of the TMS page. Verify, the 'TMS >> Access Code >> Auto_Test' page is displayed.
			/*selUtils.clickOnWebElement("accd_link", ACCODE);
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);*/					
			pageNavigationAndvBreadCrumb("accd_link", ACCDBRDCRUMB);
			logger.info("Step 2, 3 and 4 :");
			/*Test maximum number of characters allowed for 'Code name'(max 100)*/
			cretAccCode(aCode, tokenNo, false);
			selUtils.vExpValPresent("accd_code_name_xpath", nameLbl, aCode.substring(0, aCode.length()-1), aCode.substring(0, aCode.length()-1));
			selUtils.vExpValPresent("accd_code_name_xpath", nameLbl, tokenNo.substring(0, tokenNo.length()-1), tokenNo.substring(0, tokenNo.length()-1));

			logger.info("Step 5 :");
			/*Test maximum number of characters allowed for 'Token number'(max 10)*/
		/*	selUtils.clickOnWebElement("estates_link", Estates.estLbl);
			selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);*/
			pageNavigationAndvBreadCrumb("estates_link", Estates.breadCrumbEstates);
			ObjectFactory.getEstateInstance().waitforEstPageLoded();
			selUtils.vExpValPresent("col_val_xpath", nameLbl, aCode.substring(0, aCode.length()-1), aCode.substring(0, aCode.length()-1));
			selUtils.vColVal("est_tab_col_val_xpath",aCode.substring(0, aCode.length()-1), frequency[2]);

			logger.info("Step 6, 7 and 8 :");
			/*AccessCode deletion*/
			ObjectFactory.getEstateInstance().delEstAccCod(aCode.substring(0, aCode.length()-1));	

			logger.info("ACCD_1300 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}