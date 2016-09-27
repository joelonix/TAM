package com.ingenico.tam.objects;

/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/Merchants.java $
 $Id: Merchants.java 14253 2015-07-21 11:49:41Z amsingh $
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * Merchants Class - Methods related to Merchants module
 */
public class Merchants extends TestBase {

	public static String mercProgMsg = "Merchant deleting in progress...",
			breadCrumMerc = "TMS » Merchants",
			brdCrmMrtAtoTst = breadCrumMerc + " » " + client,
			brdCrumbEdtMrct = "TMS » Edit merchant » ",
			mercAssig = "The terminal has been assigned to the merchant",
			crtMerchant = "Create a merchant",
			delMercSuccMsg = "The merchant has been deleted",
			pakVer20 = "2.0",
			pakVer30 = "3.0",
			mercAssigning = "Assigning merchant to terminal...",
			mercAssigned = "The terminal(s) has/have been assigned to the merchant",
			mrchNmLbl = "Merchant name",
			mrchNumLbl = "Merchant number",
			merchant = "Merchant",
			mercFou = "Merchants found",
			automerc1 = "Auto_merchant_1",
			succMsg = "The merchant has been created",
			merchants = "Merchants",
			swPak = "Software package",
			mercAssignMsg = "By confirming this action, configurations linked to this merchant will be assigned",
			edtChk = "Edit the new merchant after creation",
			mechData = "Merchant data",
			unAsgnMrchToolTip = "Unassign the merchant",
			trmSign = "Terminal signature",
			asgnMrchTooltip = "Assign a merchant",
			mercAsgnedTrm = "The terminal has been assigned to the merchant",
			mrchUnLnkedTrmMsg = "Actual merchant will be unlinked from this terminal",
			delMrct = "Delete the merchant",
			breadCruEdtMerc = "TMS » Edit merchant » ",
			viewAsignTrms = "View assigned terminals",
			mrhCfmPkgPrmMsg = "By confirming this action, merchant will be unlinked from the terminal. Packages and parameters specific to this merchant will be unassigned",
			mrchDelMsg = "Deleting link between terminal and merchant...",
			mrctActs = "Merchant actions",
			edtMrct = "Edit the merchant",
			exstMrctNmMsg = "- The merchant name already exists for the customer Auto_Test.",
			exstMrctIdMsg = "- The merchant id already exists for the customer Auto_Test.",
			pubConTab = "Public configuration",
			confgrtn = "Configuration",
			addDat = "Added dated",
			user = "User",
			locatn = "Locations",
			privConTab = "Private configuration",
			pstAddMang = "Postal address management",
			trmSgnToAsgnMrt = "Terminal signature to assign at the merchant",
			assoTerm = "Associated terminals",
			bckToMer = "Back to merchant",
			updMerc = "Update the merchant",
			merUpdSucc = "The merchant has been updated",
			mercDelMsg = "Remove the merchant",
			addrLine1 = "Address Line 1 *",
			addrLine2 = "Address Line 2",
			city = "City *",
			cntry = "Country *",
			zipPostalCode = "Zip/Postal Code *",
			nwLocSuccMsg = "This location has been created",
			mrctAddCnfgMsg = "Packages and parameter modules of this configuration will be added automatically to terminals of the merchant.",
			mrctAddCnfgMsg1 = "Packages, scenarios, keys and parameter modules of this configuration will be added automatically to terminals of the merchant.",
			pblcCnfgDelMsg = "Remove the public configuration from this merchant",
			adPkToTmOfMrtMsg = "Selected package will be added automatically to terminals of the merchant.",
			pkgRmvInfoMsg = "Remove the product package form this private configuration",
			errTerSig = "- Impossible to find the terminal with the signature ";

	public static String[] mercList = { "Auto_Merchant_1", "Auto_Merchant_2",
			"Auto_Merchant_3", "Auto_Merchant_4", "Auto_Merchant_5",
			"Auto_Merchant_6", "Auto_Merchant_7", "Auto_Merchant_8",
			"Auto_Merchant_9", "Auto_Merchant_10" }, mercHeader = { mrchNmLbl,
			mrchNumLbl, "Merchant id", "Merchant creation", "Select" },
			mrctsHdrs = { mrchNmLbl, mrchNumLbl, mercHeader[2], mercHeader[3],
					Terminals.terminals, actions }, mercNo = { "001", "002",
					"003", "004", "005", "006", "007", "008", "009", "010" },
			mercID = { "Auto_Merchant_1_ID", "Auto_Merchant_2_ID",
					"Auto_Merchant_3_ID", "Auto_Merchant_4_ID",
					"Auto_Merchant_5_ID", "Auto_Merchant_6_ID",
					"Auto_Merchant_7_ID", "Auto_Merchant_8_ID",
					"Auto_Merchant_9_ID", "Auto_Merchant_10_ID" }, mercAct = {
					upd, delete, "Quick assignation", "New location" },
			addModBut = { addCnfg, taskTypes[2] }, merTabHedr = { confgrtn,
					addDat, user, mrctsHdrs[5] }, merNonFctab = { privConTab,
					locatn }, edtMerPcTabHedr = { Packages.packName, version,
					crtnDate, actions }, edtMerPcVal = { "Auto_Package_2_3",
					"3.0" }, merTabHedrVier = { confgrtn, addDat, user },
			edMerPcTabHedrVer = { Packages.packName, version, crtnDate };

	/**
	 * Method to initialize the XML files of AccessCode and CommonObjects
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws FileNotFoundException, IOException {
		initialize();
	}

	/**
	 * Set the 'Software package' field to expected value and click on the
	 * 'Find' button Verify Only expected value is listed in the table
	 **/
	public void selMercVal(String locator, String val, String colVal) {
		selUtils.selectItem(selUtils.getObject(locator), val);
		logger.info(" Seleced the value " + val);
		selUtils.getObject("trm_merc_find_button_xpath").click();
		logger.info(" Clicked on Find Button");
		waitForMrchPageLoaded();
		List<WebElement> element = selUtils.getObjects("merc_find_colname_css");
		Assert.assertEquals(element.size(), 1, "Only " + colVal
				+ " is not listed in the table");
		Assert.assertTrue(element.get(0).getText().contains(colVal), colVal
				+ " is not listed in the table");
		logger.info("Verifed, only " + colVal + " is listed in the table.");
	}

	/**
	 * Wait for merchant page loaded.
	 */
	public void waitForMrchPageLoaded() {
		selUtils.waitForTxtPresent("mer_dis_txt_xpath", editPageDispTxt);
	}

	/**
	 * Verify that new Merchant is not present in table.
	 */
	public void verifyMercsnotPresent(String signature) {
		List<String> table = new ArrayList<String>();
		waitMethods.waitForElementDisplayed("estates_list_sign_css");
		table = selUtils.getLstItems(selUtils
				.getObjects("estates_list_sign_css"));
		Assert.assertFalse(table.contains(signature), "After deleted, the '"
				+ signature + "' Merchant is present in table.");
		table.clear();
		logger.info(" Verified that deleted Merchant is not present in table: "
				+ signature);
	}

	/**
	 * Verify that new Merchant is present in table.
	 */
	public void verifyMercsPresent(String signature) {
		List<String> table = new ArrayList<String>();
		waitMethods.waitForElementDisplayed("estates_list_sign_css");
		table = selUtils.getLstItems(selUtils
				.getObjects("estates_list_sign_css"));
		Assert.assertTrue(table.contains(signature), "After Created, the '"
				+ signature + "' Merchant is not present in table.");
		table.clear();
		logger.info(" Verified that after created Merchant is present in table: "
				+ signature);
	}

	/**
	 * Verify delete estate functionality
	 */
	public void verifyDeleteMercFunctionality() {
		selUtils.getObject("merc_del_cnfrm_id").click();
		/*
		 * Assert.assertTrue(selUtils.getObject("merc_prog_del_msg_id").getText()
		 * .equals(mercProgMsg), "'" + mercProgMsg +
		 * "' message is not displayed as expected."); logger.info("Verified, '"
		 * + mercProgMsg + "' message is displayed as expected.");
		 */
		selUtils.waitForTxtPresent("merc_succ_del_msg_id", delMercSuccMsg);
		Assert.assertTrue(selUtils.getObject("merc_succ_del_msg_id").getText()
				.equals(delMercSuccMsg), "'" + delMercSuccMsg
				+ "' message is not displayed as expected.");
		logger.info("Verified, '" + delMercSuccMsg
				+ "' message is displayed as expected.");
	}

	/**
	 * Deleting Existing Packages
	 */
	public void deleteExistMerc(String signature) {
		boolean flag = false;
		List<WebElement> webelements = selUtils
				.getObjects("estates_list_sign_css");
		int eleCount = 0;
		for (eleCount = 0; eleCount < webelements.size(); eleCount++) {
			selUtils.getObjects("estates_list_sign_css");
			ObjectFactory.getEstateInstance().estate = webelements
					.get(eleCount).getText();
			if (ObjectFactory.getEstateInstance().estate.equals(signature)) {
				eleCount = eleCount + 1;
				flag = true;
				break;
			}
		}
		if (flag) {
			String index = eleCount + "";
			xpath = selUtils.getPath("list_merc_delete_xpath").replace("INDEX",
					index);
			if (selUtils.isElementPresent("list_merc_delete_xpath", xpath)) {
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].click();",
						selUtils.getObjectDirect(By.xpath(xpath)));
				logger.info(" Deleting the Existing Merchant");
				verifyDeleteMercFunctionality();
				verifySpecifiedWinNotDisp("merc_succ_del_cls_id");
				waitForMrchPageLoaded();
			}
		}
	}
 /**
 * Deleting the Existing Merchant
 */
	public void deleteExistMerc() {
		logger.info(" Deleting the Existing Merchant");
		verifyExpWinDisp("delete_link", delete);
		deletComVerify();
	}

	/**
	 * Deleting the Existing Merchant
	 */
	public void deletComVerify() {
		verifyDeleteMercFunctionality();
		verifyOnlyCloseButtInSuccWin("merc_succ_del_cls_id");
		verifySpecifiedWinNotDisp("merc_succ_del_cls_id");
		waitForMrchPageLoaded();
		logger.info(" Deleting the Existing Merchant");

	}

	/**
	 * click on the 'Find' button Only 'expected value' is listed in the table
	 * Click on the 'Reset' button The 'expected' field' field value is removed
	 */
	public void mercSearch(String locator, String val, String colVal) {
		selUtils.populateInputBox(locator, val);
		logger.info(" Seleced the value " + val);
		selUtils.getObject("trm_merc_find_button_xpath").click();
		logger.info(" Clicked on Find Button");
		waitForMrchPageLoaded();
		List<WebElement> element = selUtils.getObjects("merc_find_colname_css");
		Assert.assertEquals(element.size(), 1, " Only " + colVal
				+ " is not listed in the table");
		Assert.assertTrue(element.get(0).getText().contains(colVal), colVal
				+ " is not listed in the table");
		logger.info("  Verifed that Only " + colVal + " is listed in the table");
		selUtils.getObject("trm_merc_reset_button_xpath").click();
		logger.info(" Clicked on Reset Button");
		Assert.assertTrue(selUtils.getObject(locator).getText().isEmpty(),
				" expected field value is not removed");
		logger.info("  Verifed that expected field value is removed");
	}

	/**Click on Find icon of 'Merchant' field in 'Merchant data' section.
	*containing expected data. */
	public void vAddMercDets(String objLoc, String browser) {
		int merCount = 0;
		Assert.assertTrue(selUtils.getObject("sel_trm_sce_list_xpath")
				.getText().contains(findMrcts), findMrcts
				+ " is not displayed ");
		logger.info(" Verified that " + findMrcts + " is displayed");
		selUtils.verifyElementDisp("merc_name_css", mrchNmLbl);
		selUtils.verifyElementDisp("merc_no_css", "Merchant number");
		selUtils.verifyElementDisp("mercid_id", "Merchant ID");
		selUtils.verifyElementDisp("merc_term_sign_id", "Terminal signature");
		selUtils.vDrpDnSelcItem("merc_sw_pak_id", all);
		selUtils.vDrpDnSelcItem("merc_params_id", all);
		selUtils.verifyElementDisp("trm_merc_reset_button_xpath", reset);
		selUtils.verifyElementDisp("trm_merc_find_button_xpath", findButt);
		Assert.assertTrue(selUtils.getObject("trm_sce_opts_xpath").getText()
				.contains(mercFou), mercFou + " is not displayed ");
		logger.info(" Verified that '" + mercFou + "' is displayed");
		List<WebElement> mercElements = selUtils
				.getObjects("trm_merc_table_header_xpath");
		for (merCount = 0; merCount < mercElements.size(); merCount++) {
			Assert.assertTrue(
					mercElements.get(merCount).getText()
							.contains(mercHeader[merCount]),
					mercHeader[merCount] + " column is not displayed.");
		}
		logger.info("Verified, all expected coloumns are displayed.");
		selUtils.clickOnWebElement("trm_mrc_popup_find_xpath", findButt);
		selcMaxPgSz("merc_sel_max_res_css", browser, "mer_dis_txt_xpath",
				editPageDispTxt);
		verifyListItems("merc_find_colname_css", mercList);
		selUtils.verifyElementDisp("merc_cls_id", closeButton);
	}

	/**
	 * Click on the 'Show results' drop-down list and select '5'. Verify, the
	 * field is set to '5'. Five results are displayed in the table.*/
	public void vMrchExpShowRes(String jobsNum) {
		expPgNo = Integer.valueOf(jobsNum);
		selectMrchNumjobShwRes(jobsNum);
		waitForMrchPageLoaded();
		showRes = Integer.valueOf(selUtils.getSelectedItem(selUtils
				.getObject("job_show_res_xpath")));
		Assert.assertEquals(showRes, expPgNo,
				"Show results dropdown list field is not set to " + expPgNo);
		logger.info("Verified, in jobs table, 'Show results' dropdown list field is set to "
				+ expPgNo);
		vMrchExpResInTab(jobsNum);
		logger.info("Verified, expected page is displayed " + expPgNo
				+ " results.");
	}

	/**
	 * Select number of job show results.*/
	public void selectMrchNumjobShwRes(String number) {
		new Select(selUtils.getObject("job_show_res_xpath"))
				.selectByVisibleText(number);
		waitForMrchPageLoaded();
	}

	/**
	 * Verify expected results are displayed in the table*/
	public void vMrchExpResInTab(String jobsNum) {
		expPgNo = Integer.valueOf(jobsNum);
		Assert.assertTrue(
				selUtils.getObjects("estnames_list_css").size() == expPgNo,
				"Expected page is not displayed " + expPgNo + " results.");
	}

	/**
	 * Verify merchant assignment popup vMercAssignPopup
	 * @param mercName
	 */
	public void vMercAssignPopup(String mercName) {
		selUtils.clickOnWebElement("trm_mrc_popup_find_xpath", findButt);
		selUtils.waitForTxtPresent("mer_dis_txt_xpath",
				editPageDispTxt);
		xpath = selUtils.getPath("merc_sel_xpath")
				.replace("MERCHANT", mercName);
		selUtils.getObjectDirect(By.xpath(xpath)).click();
		selUtils.verifyElementDisp("trm_allpopup_title_xpath", assign);
		selUtils.vExpValContains("merc_popup_conf_text_id",
				Merchants.mercAssignMsg);
		selUtils.verifyElementDisp("merc_sel_cnfrm_id", confirm);
		selUtils.verifyElementDisp("merc_clos_button_id", cancel);
		verifySpecifiedWinNotDisp("merc_clos_button_id");
	}

	/**
	 * merchant assignment to terminal mercAssignment
	 * @param mercName
	 */
	public void mercAssignment(String mercName) {
		selUtils.clickOnWebElement("trm_mrc_popup_find_xpath", findButt);
		selUtils.waitForTxtPresent("mer_dis_txt_xpath",
				editPageDispTxt);
		Assert.assertFalse(selUtils.isElementPresent(
				"trm_mrch_table_rowdata_xpath",
				selUtils.getCommonPath("trm_mrch_table_rowdata_xpath")),
				"Merchant found table is not empty");
		logger.info("Verified, Merchant found table is not empty");
		xpath = selUtils.getPath("merc_sel_xpath")
				.replace("MERCHANT", mercName);
		selUtils.getObjectDirect(By.xpath(xpath)).click();
		selUtils.verifyElementDisp("trm_allpopup_title_xpath", assign);
		confrmPopupActionMsg("merc_sel_cnfrm_id", "merc_sel_procc_msg_id",
				"merc_sel_succ_msg_id", Merchants.mercAssigning,
				Merchants.mercAssigned);
		verifyOnlyCloseButtInSuccWin("merc_assin_close_butt_css");
		verifySpecifiedWinNotDisp("merc_sel_cls_id");
	}

	/**
	 * edit terminal merchant verification. editTerMercVeri
	 * @param mercName
	 */
	public void editTerMercVeri(String mercName) {
		selUtils.clickOnWebElement("prop_link", Terminals.edtTrmTabs[1]);
		// selUtils.waitForTxtPresent("merc_selected_id", mercName);
		vValFrmInputBox("merc_selected_id", mercName, Merchants.merchant);
		selUtils.verifyExpIconDisplayed("merc_del_xpath", delete);
		selUtils.verifyExpIconDisplayed("merc_fnd_xpath", findButt);
	}

	/** Set the 'Merchant name' field to 'Auto_Merchant_2',Click on the 
	 * 'Search' button of the 'Find merchants' drop-down window and 
	 * verify,Click on the 'Back To List' button and verify*/
	public void vMercFnd(String objLoc1, String val1, String val2, String val3,
			String val4, boolean select) {
		if (select) {
			selUtils.selectItem(selUtils.getObject(objLoc1), val1);
			logger.info(" Seleced the value " + val1);
		} else {
			selUtils.populateInputBox(objLoc1, val1);
			vValFrmInputBox(objLoc1, val1, val2);
		}
		vsrchFunc(val1, val3, val4);
	}

	/**Click on the 'Search' button of the 'Find merchants' drop-down 
	 * window and Click on the 'Back To List' button*/
	public void vsrchFunc(String val1, String val2, String val3) {
		selUtils.getObject("srch_link").click();
		wait.until(ExpectedConditions.visibilityOf(selUtils.getCommonObject("bread_crumb_xpath")));
		selUtils.verifyBreadCrumb(val2);
		//selUtils.verifyBreadCrumb(val2);
		selUtils.verifyElementNotDisp("find_merc_xpath", findMrcts);
		selUtils.clkBackToLstButt();
		selUtils.verifyBreadCrumb(breadCrumMerc);
		waitMethods.waitForElementDisplayed("merc_find_colname_css");
		List<WebElement> element = selUtils.getObjects("merc_find_colname_css");
		Assert.assertEquals(element.size(), 1, " Only " + val1
				+ " is not listed in the table");
		Assert.assertTrue(element.get(0).getText().contains(val3), val3
				+ " is not listed in the table");
		logger.info("  Verifed that Only " + val3 + " is listed in the table");
	}

	/**
	 * Click on the 'Find' button at the top of the page.The 'Filter' drop-down
	 * window is displayed Click on the 'Reset' button.The 'Merchant id' field
	 * becomes blank
	 */
	public void vFndRst(String objLoc1) {
		selUtils.getObject("find_but_xpath").click();
		logger.info(" Clicked on Find Button");
		selUtils.waitForTxtPresent("find_merc_xpath", findMrcts);
		selUtils.verifyElementDisp("find_merc_xpath", findMrcts);
		selUtils.clickOnWebElement("trm_merc_reset_button_xpath", reset);
		Assert.assertTrue(selUtils.getObject(objLoc1).getText().isEmpty(),
				" expected field value is not removed");
		logger.info("  Verifed that expected field value is removed");
	}

	/**
	 * Add Merchant Details Without ChkBox
	 * */
	public void addMrctDtlsWithOutChkBox(String mrctName, String mrctNo,
			String mrctId) {
		verifyExpWinDisp("mrc_crt_xpath", crtMerchant);
		addMrchNameNumIdAndDesl(mrctName, mrctNo, mrctId);
	}

	/**
	 * addFldValAndDesl fill all the fields and deselect edit checkbox
	 * @param mrctName
	 * @param mrctNo
	 * @param mrctId
	 */
	public void addMrchNameNumIdAndDesl(String mrctName, String mrctNo,
			String mrctId) {
		selUtils.populateInputBox("merc_crt_name_xpath", mrctName);
		selUtils.populateInputBox("merc_crt_num_xpath", mrctNo);
		selUtils.populateInputBox("merc_crt_id_xpath", mrctId);
		logger.info("Expected values are '" + mrctName + "', '" + mrctNo
				+ "',  '" + mrctId + "' added. ");
		selUtils.deSelecChkBx("merc_edt_chk_id");
		selUtils.vDeSelecChkBx("merc_edt_chk_id", edtChk);
	}

	/**
	 * Set the 'Merchant name' field to expected value. Set the 'Merchant
	 * number' to expected value. Set the 'Merchant id' to expected value.
	 **/
	public void addMrchNameNumId(String nameLoc, String numLoc, String idLoc,
			String nameVal, String numVal, String idVal) {
		selUtils.populateInputBox(nameLoc, nameVal);
		vValFrmInputBox(nameLoc, nameVal, mrchNmLbl);
		selUtils.populateInputBox(numLoc, numVal);
		vValFrmInputBox(numLoc, numVal, mrchNumLbl);
		selUtils.populateInputBox(idLoc, idVal);
		vValFrmInputBox(idLoc, idVal, mercHeader[2]);
	}

	/**Create a merchant Keep the 'Edit the new merchant after the 
	 * creation' check box ticked.*/
	public void addMrctDtlsWithChkBox(String mrctName, String mrctNo,
			String mrctId) {
		verifyExpWinDisp("mrc_crt_xpath", crtMerchant);
		addMrchNameNumId("merc_crt_name_xpath", "merc_crt_num_xpath",
				"merc_crt_id_xpath", merc, mrctNo, mrctId);
		selUtils.vSelcChkBx("merc_edt_chk_id", edtChk);
		cnfmPopupActMsg("merc_crt_id", "merc_crt_succ_msg_id", succMsg);
		verifyOnlyCloseButtInSuccWin("merc_crt_succ_cls_id");
		verifySpecifiedWinNotPresent("merc_crt_succ_cls_id");
		selUtils.verifyBreadCrumb(brdCrumbEdtMrct + mrctName);
	}

	/**Create a merchant Keep the 'Edit the new merchant after the 
	 * creation' check box ticked.*/
	public void delExistMrctFrmEdtPage(String mrctName, String browser) {
		verifyExpWinDisp("delete_link", delete);
		cnfmPopupActMsg("merc_del_cnfrm_id", "merc_succ_del_msg_id",
				delMercSuccMsg);
		verifyOnlyCloseButtInSuccWin("merc_succ_del_cls_id");
		verifySpecifiedWinNotPresent("merc_succ_del_cls_id");
		selUtils.verifyBreadCrumb(brdCrmMrtAtoTst);
		waitForMrchPageLoaded();
		selectMaxSizeinTable("job_show_res_id", browser);
		waitForMrchPageLoaded();
		vExpValNotPresent("mrct_col_name_xpath", "Name", mrctName, mrctName);
	}

	/**
	 * Verify merchant edit page values
	 */
	public void vMercPage(String brdCrum, String merName, String merNum,
			String merId) {
		selUtils.verifyBreadCrumb(brdCrum);
		selUtils.vExpValPresent("merc_crt_name_id", merName);
		selUtils.vExpValPresent("merc_crt_num_id", merNum);
		selUtils.vExpValPresent("merc_crt_id_id", merId);
	}

	/**
	 * Verify merchant update input box
	 */
	public void vMercUpdInputValu(String merName, String merNum, String merId) {
		vValFrmInputBox("merc_crt_name_xpath", merName, mrchNmLbl);
		vValFrmInputBox("merc_crt_num_xpath", merNum, mrchNumLbl);
		vValFrmInputBox("merc_crt_id_xpath", merId, mercHeader[2]);
	}

	/**
	 * Set the 'Terminal signature to assign at the merchant' field to expected
	 * value and click on the 'Confirm' button The modal window displays the
	 * message: 'Assignation to the merchant in progress' and verify
	 */
	public void vNumOfAssoTrms(String expTrm, String expNum) {
		assignMrctToExpTrm(expTrm, mercAssigned);
		verifySpecifiedWinNotPresent("qck_asgn_cls_butt_id");
		vAssoTerm(expNum);
	}

	/**
	 * Set the 'Terminal signature to assign at the merchant' field to expected
	 * value and click on the 'Confirm' button The modal window displays the
	 * message: 'Assignation to the merchant in progress' and verify
	 */
	public void assignMrctToExpTrm(String expTrm, String expMsg) {
		selUtils.populateInputBox("signature_id", expTrm);
		vValFrmInputBox("signature_id", expTrm, trmSgnToAsgnMrt);
		cnfmPopupActMsg("sign_assgn_cnfm_id", "assgn_succ_msg_id", expMsg);
	}

	/**
	 * Verify no of associated teminals.
	 * @param expNum
	 */
	public void vAssoTerm(String expNum) {
		Assert.assertEquals(selUtils.getObject("term_num_id").getText(),
				expNum, assoTerm + " field is not set to '" + expNum + "'.");
		logger.info("Verified, '" + assoTerm + "' field is set to '" + expNum
				+ "'.");
	}

	/**
	 * Verify edit Merchant Page
	 */
	public void vEditMerhPage() {
		selUtils.clickOnWebElement("merc_edit_xpath", "NAME", mercList[2], edit
				+ " " + mercList[2]);
		selUtils.verifyBreadCrumb(breadCruEdtMerc + mercList[2]);
		selUtils.vBackToLstButt();
		selUtils.vExpValPresent("merc_crt_name_id", mercList[2]);
		selUtils.vExpValPresent("merc_crt_id_id", mercID[2]);
		selUtils.vExpValPresent("merc_crt_num_id", mercNo[2]);
		vExpValPrsGrt("term_num_id", itemCount);
		vGrpButts("merc_act_xpath", mercAct, mrctActs);
		vGrpButts("edt_swcnfg_acts_addmod_xpath", addModBut, addMod);
		vExpTabFocused(pubConTab);
		vExpColsInTab(selUtils.getTabColHds("edit_table_headr_css"), merTabHedr);
		selUtils.vExpNotFocusedTabs(merNonFctab);
	}

}