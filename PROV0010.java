package com.ingenico.tam.testsuite.accesscode;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/estates/EST1100.java $
$Id: EST1100.java 6545 2014-04-28 07:11:59Z jlakshmi $
 */
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.Estates;
import com.ingenico.tam.objects.ObjectFactory;

/**
 * Create the Access Codes required for the automated regression testing.
 */
public class PROV0010 extends AccessCode {
	/**
	 * TAM-8789:PROV_0010 : Access Codes
	 * Create the Access Codes required for the automated regression testing.
	 * @param browser
	 */
	@Parameters({"browser", "browserURL"})
	@Test(groups = {"CreateAccessCode"})
	public void prov0010(String browser, String browserURL){

		try {
			logger.info("PROV_0010 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"), browser, browserURL);

			/*Delete the existing access code form the estate page*/
			logger.info("Step 1 :");
			startingSetup("estates_link", Estates.breadCrumbEstates);

			logger.info("Step 2, 3, 4 :");
			for ( cnti = 0; cnti < accCod.length; cnti++) {
				ObjectFactory.getEstateInstance().delEstAccCod(accCod[cnti]);
			}
			selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);

			/*Add the default access code needed*/
			logger.info("Step 5 :");
			selUtils.clickOnWebElement("accd_link", ACCODE);
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);

			logger.info("Step 6, 7, 8, 9 :");
			for(cnti=0;cnti<accCod.length;cnti++){
				cretAccCode(accCod[cnti],tokNum[cnti], false);
				selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
				selectMaxSizeinTable("job_show_res_id", browser);
				selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
				selUtils.vExpValPresent("accd_code_nme_xpath",nam,accCod[cnti],accCod[cnti]);
				selUtils.vExpValPresent(selUtils.getObjectDirect(By.xpath(selUtils.getPath("accd_code_tokn_xpath").replace(nam,accCod[cnti]))), num[cnti]);
			}
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);

			logger.info("PROV_0010 successfully executed");		
		}catch (Throwable t) {
			handleException(t);
		}
	}
}