package com.ingenico.tam.testsuite.accesscode;
/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD1105.java $
 $Id: ACCD1105.java 13732 2015-06-04 10:52:35Z sparween $
 */
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.Estates;
import com.ingenico.tam.objects.ObjectFactory;

/**
 * Delete an access code from the Estates page.
 */
public class ACCD1105 extends AccessCode {
	/**
	 * TAM-5439:ACCD_1105. Delete an access code from the Estates page and see
	 * it deleted from the Access Code page.
	 */
	@Parameters({"browser"})
	@Test
	public void accd1105(String browser){
		try {
			logger.info("ACCD_1105 Execution started");

			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			aCode = "ACCD_1105";
			tokenNo = "1105";

			//Delete if same access code already exist on Estate page.
			startingSetup("estates_link", Estates.breadCrumbEstates);
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);

			logger.info("Step 1 :");
			// Click on 'Access Code' verify breadcrumb displayed.
			/*selUtils.clickOnWebElement("accd_link", ACCODE);
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);*/			
			pageNavigationAndvBreadCrumb("accd_link", ACCDBRDCRUMB);
			//Click on the 'Create access code' ACCD_1105.
			logger.info("Step 2, 3 and 4 :");
			cretAccCode(aCode, tokenNo, false);

			logger.info("Step 5 :");
			/* Click on 'Estates' in the left menu of the TMS page.verify bread-
			 * crumb and'ACCD_1105'.its Terminals column value set to '1'.*/
			/*selUtils.clickOnWebElement("estates_link", Estates.estLbl);
			selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);*/
			pageNavigationAndvBreadCrumb("estates_link", Estates.breadCrumbEstates);
			ObjectFactory.getEstateInstance().waitforEstPageLoded();
			selUtils.vExpValPresent("col_val_xpath", nameLbl, aCode, aCode);
			selUtils.vColVal("est_tab_col_val_xpath",aCode, frequency[2]);

			logger.info("Step 6, 7 and 8 :");
			//delete  ACCD_1105 verify accescode ACCD_1105 is deleted .
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);	
			logger.info("Step 9 :");
			
			/* Click on 'Access Code'. Verify there is no Access Code with the
			 *  Code name equals to 'ACCD_1105'in the table of the page.*/
		/*	selUtils.clickOnWebElement("accd_link", ACCODE);
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);*/	
			pageNavigationAndvBreadCrumb("accd_link", ACCDBRDCRUMB);
			vExpValNotPresent("accd_code_name_xpath", nameLbl, aCode, aCode);

			logger.info("ACCD_1105 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}