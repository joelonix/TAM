package com.ingenico.tam.testsuite.accesscode;

/*$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/estates/EST1100.java $
$Id: EST1100.java 6545 2014-04-28 07:11:59Z jlakshmi $ */

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.Estates;
/**
 * Verify details of 'Access Code' page and table.
 * @author Shailesh.Kumar
 *
 */
public class ACCD1100 extends AccessCode {
	
	String dispData[][]={{Estates.estName,"Auto_ACCD_1"},{Estates.estName,"Auto_ACCD_2"},{Estates.estName,"Auto_ACCD_3"}};
	/**
	 * TAM-5411:ACCD_1100 : 'Access Code' page and table
	 */
	@Parameters({"browser"})
	@Test
	public void accd1100(String browser)
	{
		try {
			logger.info("ACCD_1100 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));

			logger.info("Step 1:");
			//Click on 'Access Code' in the left menu and verify breadcrumb
			startingSetup("accd_link", ACCDBRDCRUMB);
			
			/*verify the access code page*/
			selUtils.verifyElementDisp("find_but_xpath", findButt);
			vExpButtInGrp("accd_code_btns_xpath", CRTACCD, CRTACCD);
			vExpColsInTab(selUtils.getTabColHds("acc_cod_hdr_css"), accTblhedr);
			vExpColVals(accCod, browser, getTableHeadCursVals("accd_tab_col_val_xpath","accd_tab_col_hedr_xpath",indx, CDNAME));

			/*verify the default data page*/
			for(int cnti=0;cnti<accCod.length;cnti++)
			{
				selUtils.vExpValPresent(selUtils.getObjectDirect(By.xpath(selUtils.getPath("accd_code_tokn_xpath").replace(nam,accCod[cnti]))), num[cnti]);
				selUtils.vExpIcon("accd_edit_icn_xpath", accCod[cnti], edit);
				selUtils.vExpIcon("accd_del_icn_xpath", accCod[cnti], delete);
			}

			//verify if Edit the current access code' tooltip appears
			logger.info("Step 2:");
			verifyWebIconToolTip("accd_edit_icnlik_xpath",accCod[0],EITTOLTIP);

            //verify 'Delete the current access code' tooltip appears.
			logger.info("Step 3:");	
			verifyWebIconToolTip("accd_del_icnlik_xpath",accCod[0],DELTOLTIP);

			/*verify if the The table of estates contains the three estates 
			  with the Name: 'Auto_ACCD_1, Auto_ACCD_2, Auto_ACCD_3'.*/
			logger.info("Step 4:");			
			pageNavigationAndvBreadCrumb("estates_link", Estates.breadCrumbEstates);			
			vExpVals(accCod, getTableHeadCursVals("accd_colVal_xpath","accd_est_header_xpath",indx, Estates.estatesColsArr[2]));
			//Click on 'Edit' icon of the estate  'Auto_ACCD_1' and verify breadcrumb*/
			logger.info("Step 5:");
			selUtils.clickOnWebElement("accd_est_edt_icn_xpath",nam,accCod[0] ,edit+" "+accCod[0]);
			selUtils.verifyBreadCrumb(Estates.breadCrumbEdit);			
			selUtils.vExpValPresent("accd_edit_estate_name_id",accCod[0]);
			selUtils.verifyExpIconDisplayed("accd_edit_est_acc_xpath", ACCODE);

			logger.info("ACCD_1100 successfully executed");		

		}catch (Throwable t) {
			handleException(t);
		}
	}
}

