package com.ingenico.tam.objects;

/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/Packages.java $
 $Id: Packages.java 14253 2015-07-21 11:49:41Z amsingh $
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * Packages Class - Methods related to Packages module
 */
public class Packages extends TestBase {

	public static String pakProgDelMsg = "Software package deactivation, please wait...",
			breadCrumPak = "TMS » Software Packages » Auto_Test",
			breadCrumEdtPak = "TMS » Edit software package",
			packsActs = "Software package actions",
			crtPakmodalWin = "Create a software package",
			pkgNmDrpDnItm11 = "Auto_Package_1 - 1.0",
			pkgNmDrpDnItm12 = "Auto_Package_12 - 12.0",
			addPak = taskTypes[2],
			addpkgAddedMsg = "Job to add package has been added to the estate",
			pkgLbl = "Package",
			addPackMsg = "Once the task has been executed, each compliant terminal will contain this product",
			pakVer12 = "12.0",
			addPakMsg = "Adding software package file",
			addPakFileWin = "Add SW package",
			delPakDActMsg = "Software package deactivated successfully",
			pakCrt = "Creation of the software package in progress...",
			pakSucMsg = "Software package has been created successfully.",
			packName = "Package name",
			selcPackMsg = "Select a package",
			pak212 = "Auto_Package_2_12",
			pak213 = "Auto_Package_2_13",
			pakSuccMsg = "Package added to 1 terminal(s)",
			delPakSuccMsg = "Package has been deleted",
			delPakPrcMsg = "Package deletion is running...",
			paksSuccMsg = "Package added to 2 terminal(s).",
			pak1TrmMsg = "Package added to 0 terminal(s). Package rejected from 1 terminal(s).",
			atoPack211 = "Auto_Package_2_11 - 11.0",
			atoPack212 = "Auto_Package_2_12 - 12.0",
			atoPack213 = "Auto_Package_2_13 - 13.0",
			pakErrMsg1 = "The package '",
			pakErrMsg2 = "' is wrong. Add a file before creating a task.",
			expPackErrMsg = pakErrMsg1 + atoPack211 + pakErrMsg2,
			descPkgJob = "Package added: ",
			unKnwFlsTabMsg = "Files on this list have been found in the terminal but could not be associated with an existing package in your repository",
			pckgVerErrMsg = "The size of the field 'Version' must be lower than '25'",
			pkgLesGrtNameErr = "In field [internalName] less-than (<) and greater-than (>) symbols are not allowed.",
			pkgLesGrtVerErr = "In field [version] less-than (<) and greater-than (>) symbols are not allowed.",
			pckgUnauthErr = "Package name contains unauthorized characters",
			versUnauthErr = "Version contains unauthorized characters",
			regPak = "Region's package",
			edtPakChk = "Edit the new package after the creation",
			extPak = "Existing packages",
			fndPak = "Find software packages",
			deact = "Deactivate",
			delPakPopUpMsg = "All the pending download tasks will be deleted if this software package is deactivated. Please confirm",
			pckgVerDuplErr = "This version number already exists for this package",
			packages = "Packages",
			paksAdded = "Software Packages",
			addIcon = "Add icon",
			modPakProcMsg = "Updating of the current package in progress...",
			modPakSuccMsg = "Software package has been updated successfully",
			edtPakMsg = "The files deletion is not available. This product is already linked with a task.",
			pakDelRunningMsg = "Package file deletion is running",
			unSupportPkg = "software package is unsupported",
			pakModifErrMsg = "Operation not possible, the format of downloaded files is not consistent with the new type",
			pakAlreadyExtMsg = "Package with this name already exists. If it's a new version, select its name in the list or rename it",
			pkgReps1905 = "sponsors/Auto_Test/TELIUM/CHEQUE_1905_Auto_1.zip",
			softPack = "Software Packages",
			rmvPkg = "Remove Package",
			typeMnd = "Type is mandatory",
			extNameMnd = "Existing product or new name is mandatory",
			versMnd = "Version number is mandatory",
			exstPac = "Existing package",
			dupPkgMsg = "The product package has been already added",
			pak1TrmMsgNw = "- Package not added to 1 terminal(s).";

	public static String pacOption[] = { "Auto_Package_1 - 1.0",
			"Auto_Package_10 - 10.0", "Auto_Package_2 - 2.0",
			"Auto_Package_3 - 3.0", "Auto_Package_4 - 4.0",
			"Auto_Package_5 - 5.0", "Auto_Package_6 - 6.0",
			"Auto_Package_7 - 7.0", "Auto_Package_8 - 8.0",
			"Auto_Package_9 - 9.0" }, pac2Options[] = {
			"Auto_Package_2_1 - 1.0", "Auto_Package_2_10 - 10.0",
			"Auto_Package_2_2 - 2.0", "Auto_Package_2_3 - 3.0",
			"Auto_Package_2_4 - 4.0", "Auto_Package_2_5 - 5.0",
			"Auto_Package_2_6 - 6.0", "Auto_Package_2_7 - 7.0",
			"Auto_Package_2_8 - 8.0", "Auto_Package_2_9 - 9.0" }, paks2[] = {
			"Auto_Package_2_1", "Auto_Package_2_10", "Auto_Package_2_2",
			"Auto_Package_2_3", "Auto_Package_2_4", "Auto_Package_2_5",
			"Auto_Package_2_6", "Auto_Package_2_7", "Auto_Package_2_8",
			"Auto_Package_2_9" }, paks1[] = { "Auto_Package_1",
			"Auto_Package_10", "Auto_Package_2", "Auto_Package_3",
			"Auto_Package_4", "Auto_Package_5", "Auto_Package_6",
			"Auto_Package_7", "Auto_Package_8", "Auto_Package_9" },
			pakActTabColHdrs[] = { "Internal Name", version, crtionDate,
					"Updating date", sts, Jobs.jobsTabcolsHds[4] },
			runSwTabColHdrs[] = { applnId, commName }, pkNtFocdSubTbs[] = {
					pednActs, history }, packColHdr[] = { edit, packName,
					version, type, deact }, pakType[] = { "Application",
					"DataFile", "Multimedia", "Operating system" },
			edtPkgNtFocdTbs[] = { "Sub-package", "Restriction" },
			edtPkgColHdrs[] = { Terminals.techngyLbl,
					"Software package repository", actions }, pakList2[] = {
					"Auto_Package_1", "Auto_Package_2", "Auto_Package_3",
					"Auto_Package_4", "Auto_Package_5", "Auto_Package_6",
					"Auto_Package_7", "Auto_Package_8", "Auto_Package_9",
					"Auto_Package_10", "Auto_Package_2_1", "Auto_Package_2_2",
					"Auto_Package_2_3", "Auto_Package_2_4", "Auto_Package_2_5",
					"Auto_Package_2_6", "Auto_Package_2_7", "Auto_Package_2_8",
					"Auto_Package_2_9", "Auto_Package_2_10",
					"Auto_SW_Conf_Package_1", "Auto_SW_Conf_Package_2",
					"Auto_SW_Conf_Package_3", "Auto_SW_Conf_Package_2_1",
					"Auto_SW_Conf_Package_2_2", "Auto_SW_Conf_Package_2_3" },
			pakList1[] = { "Auto_Package_DataFile", "Auto_Package_Multimedia",
					"Auto_Package_OperatingSystem" }, pakList[] = ArrayUtils
					.addAll(pakList2, pakList1), pakActs[] = { modify, deact,
					addPakFileWin }, pakActs1[] = ArrayUtils.addAll(pakActs,
					addIcon);
	public String pckgName = "", pckgver = "";

	/**
	 * Method to initialize the XML files
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws FileNotFoundException, IOException {
		initialize();
	}

	// Verify, Add package functionality for listed invalid priority : 'aA&%\<>'
	public void vPakNegFuncWithInvalVals(String inValValues, String expPack) {
		char[] inValValuesArry = inValValues.toCharArray();
		for (itemCount = 0; itemCount < inValValuesArry.length; itemCount++) {
			String expVal = String.valueOf(inValValuesArry[itemCount]);
			/*
			 * Click on the 'Add package' button of the 'Terminals tasks'
			 * buttons section. Verify, The 'Add package' modal window is
			 * displayed.
			 */
			verifyExpWinDisp("edt_est_pak_xpath", addPak);
			/*
			 * Click on the 'Package name' Drop-down list icon and choose
			 * expected package. Verify, the field is set to same package.
			 */
			selUtils.selectItem(selUtils.getObject("trm_edt_pak_xpath"),
					expPack);
			selUtils.vDrpDnSelcItem("trm_edt_pak_xpath", expPack);
			selUtils.populateInputBox("pack_tsk_prio_css", expVal);
			logger.info("'Task priority' field value is set to '" + expVal
					+ "'.");
			confrmPopupActionMsg("add_pak_id", "pack_err_msg_with_icon_xpath",
					priorLmtErMsg);
			vMsgAndFldAndLblInRedClr("pack_err_msg_with_icon_xpath",
					"pack_tsk_prio_css", "pak_tskprio_lbl_fld_css");
			// Click on the 'Close' button. Verify, the modal window is closed.
			verifySpecifiedWinNotDisp("cls_pak_id");
		}
	}

	/**
	 * Click on 'Add package' button of 'Terminals tasks' buttons section Click
	 * on 'Package name' Drop-down list icon and choose 'Auto_Package_x_xx -
	 * xx.x'.Click on 'Add' button.
	 */
	public void vNegPakFuncWithInvalTskPri(String expPack, String inValTskPri) {
		verifyExpWinDisp("edt_est_pak_xpath", addPak);
		selUtils.selectItem(selUtils.getObject("trm_edt_pak_xpath"), expPack);
		selUtils.vDrpDnSelcItem("trm_edt_pak_xpath", expPack);
		selUtils.populateInputBox("pack_tsk_prio_css", inValTskPri);
		logger.info("'Task priority' field value is set to '" + inValTskPri
				+ "'.");
		confrmPopupActionMsg("add_pak_id", "pack_err_msg_with_icon_xpath",
				priorLmtErMsg);
		vMsgAndFldAndLblInRedClr("pack_err_msg_with_icon_xpath",
				"pack_tsk_prio_css", "pak_tskprio_lbl_fld_css");
	}

	/**
	 * Click on 'Add package' button of 'Terminals tasks' buttons section Verify
	 * 'Add package' modal window is displayed.Keep 'Package name' field set to
	 * 'None'.Keep 'Task priority' field set to 100.Click on the 'Add' button.
	 */
	public void vNegPakFuncWithBlnkPakAdTskPri(String expPack, String expTskPri) {
		verifyExpWinDisp("edt_est_pak_xpath", addPak);
		selUtils.selectItem(selUtils.getObject("trm_edt_pak_xpath"), noneVar);
		selUtils.vDrpDnSelcItem("trm_edt_pak_xpath", noneVar);
		selUtils.populateInputBox("pack_tsk_prio_css", expTskPri);
		vValFrmInputBox("pack_tsk_prio_css", expTskPri, "Task priority");
		confrmPopupActionMsg("add_pak_id", "pack_err_msg_with_icon_xpath",
				selcPackMsg);
		vMsgAndFldAndLblInRedClr("pack_err_msg_with_icon_xpath",
				"trm_edt_pak_xpath", "trm_edt_pak_lbl_xpath");
		selUtils.selectItem(selUtils.getObject("trm_edt_pak_xpath"), expPack);
		selUtils.vDrpDnSelcItem("trm_edt_pak_xpath", expPack);
		vExpFldBlkClrAndNoExpMsg("trm_pak_lbl_fld_xpath", packName,
				"pack_err_msg_with_icon_xpath", selcPackMsg);
		selUtils.populateInputBox("pack_tsk_prio_css", "");
		logger.info("'Task priority' field value is set to blank");
		confrmPopupActionMsg("add_pak_id", "pack_err_msg_with_icon_xpath",
				priMandtoryErrMsg);
		vMsgAndFldAndLblInRedClr("pack_err_msg_with_icon_xpath",
				"pack_tsk_prio_css", "pak_tskprio_lbl_fld_css");
	}

	/**
	 * Click on the 'Package name' Drop-down list icon and choose Package Name.
	 * 
	 * @throws InterruptedException
	 */
	public void setPakField(String pakName) throws InterruptedException {

		// Click on the 'Package name' Drop-down list icon and choose Package
		// Name.
		new Select(selUtils.getObject("pak_id")).selectByVisibleText(pakName);
		Assert.assertTrue(new Select(selUtils.getObject("pak_id"))
				.getFirstSelectedOption().getText().contains(pakName));
		logger.info(" Verified that field 'Package name' is set to " + pakName);
	}

	/**
	 * Set the 'Task priority' field to '1000'.
	 */
	public void setTaskthous(String xpath, String xpath1, String xpath2) {
		// Set the 'Task priority' field to '1000'
		selUtils.populateInputBox("pack_tsk_prio_css", priority1000);
		logger.info(" Set the 'Task priority' field to '1000'");
		// Click on the 'Add' button.
		selUtils.getObject(xpath2).click();
		logger.info(" Clicked on the 'Add' button.");
		// Verify error icon and error msg.
		selUtils.waitForTxtPresent(xpath, priorLmtErMsg);
		Assert.assertTrue(selUtils.getObject(xpath).getText()
				.contains(priorLmtErMsg));
		Assert.assertTrue(selUtils.getObject(xpath).getAttribute("style")
				.contains(redColor));
		Assert.assertTrue(selUtils.getObject(xpath1).isDisplayed());
		Assert.assertTrue(selUtils.getObject("pack_tsk_prio_css")
				.getAttribute("style").contains(redColor));
		logger.info(" Verified that error icon, error message: "
				+ priorLmtErMsg
				+ " are present and 'Task priority' field label becomes red");
	}

	/**
	 * Verify delete estate functionality
	 */
	public void verifyDeletePakFunctionality() {
		vModWinDisp(deact);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				selUtils.getObject("pak_del_id"));
		/*
		 * Assert.assertTrue(selUtils.getObject("pak_prog_del_msg_id").getText().
		 * equals(pakProgDelMsg), "'" + pakProgDelMsg +
		 * "' message is not displayed as expected."); logger.info("Verified, '"
		 * + pakProgDelMsg + "' message is displayed as expected.");
		 */
		selUtils.waitForTxtPresent("pak_succ_del_msg_id", delPakDActMsg);
		Assert.assertTrue(selUtils.getObject("pak_succ_del_msg_id").getText()
				.equals(delPakDActMsg), "'" + delPakDActMsg
				+ "' message is not displayed as expected.");
		logger.info("Verified, '" + delPakDActMsg
				+ "' message is displayed as expected.");
	}

	/**
	 * Click on the 'Add' button of the modal window. Verify, the modal window
	 * displays the message: 'Processing'. Then it displays the message: 'Job to
	 * add package has been added to the estate'.
	 **/
	public void verifyAddPackageFunc() {
		selUtils.clickOnWebElement("add_pak_id", addButt);
		// selUtils.getObject("add_pak_id").click();
		/*
		 * Assert.assertTrue(selUtils.getObject("dialogbox_addpac_msg_id").getText
		 * ().equals(processingMsg), "After click on 'add' button, '" +
		 * selUtils.getObject("dialogbox_addpac_msg_id").getText() +
		 * "'	message is not as expected.");
		 * logger.info("Verified, After click on 'add' button, '"
		 * +selUtils.getObject("dialogbox_addpac_msg_id").getText() +
		 * "'	message is displyed as expected.");
		 */
		selUtils.waitForTxtPresent("addpackage_successmsg_css", addpkgAddedMsg);
		Assert.assertTrue(selUtils.getObject("addpackage_successmsg_css")
				.getText().replace("- ", "").equals(addpkgAddedMsg),
				"After proccessed, '" + addpkgAddedMsg
						+ "'	message is not as expected.");
		logger.info("Verified, after proccessed, '" + addpkgAddedMsg
				+ "'	message is displayed as expected.");
	}

	/**
	 * Deleting Existing Packages
	 */
	public void deleteExistPak(String signature) {
		xpath = selUtils.getPath("pak_name_del_xpath").replace("NAME",
				signature);
		if (selUtils.isElementPresentxpath(xpath)) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].click();",
					selUtils.getObjectDirect(By.xpath(xpath)));
			logger.info(signature + "Deleting the Existing Package");
			verifyDeletePakFunctionality();
			verifyOnlyCloseButtInSuccWin("pak_del_succ_butts_css");
			verifySpecifiedWinNotPresent("pak_succ_del_cls_id");
			selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		}
	}

	/**
	 * Click on 'Add package' button of the 'Terminals tasks' section. Verify
	 * all fields and buttons on 'Add package' modal window.
	 */
	public void vAddTrmPakDets(String pckNameLoc) {
		verifyExpWinDisp("edt_est_pak_xpath", addPak);
		selUtils.vExpValContains("sel_trm_sce_list_xpath", selcPackMsg);
		selUtils.vDrpDnSelcItem(pckNameLoc, noneVar);
		selUtils.vExpValContains("trm_sce_opts_xpath", optns);
		vValFrmInputBox("pack_tsk_prio_css", priority100, tskPriLbl);
		selUtils.verifyEleNotSelected("trm_pak_perm_css");
		selUtils.vExpValContains("trm_pak_win_msg_xpath", addPackMsg);
		selUtils.verifyElementDisp("add_pak_id", addButt);
		selUtils.verifyElementDisp("cls_pak_id", closeButton);
	}

	/**
	 * Verify all fields of 'Add package' modal window.
	 */
	public void fieldsofAddPacPopupwin() {
		selUtils.vDrpDnSelcItem("pak_id", noneVar);
		Assert.assertTrue(selUtils.getObject("pack_tsk_prio_css")
				.getAttribute("value").contains(priority100));
		Assert.assertTrue(selUtils.getObject("addpac_isperma_xpath")
				.getAttribute("value").contains(checkNo));
		selUtils.vExpValContains("addpac_msg_xpath", addPackMsg);
		selUtils.verifyElementDisp("add_pak_id", addButt);
		selUtils.verifyElementDisp("cls_pak_id", closeButton);
		selUtils.verifyBreadCrumb(Estates.brdCrmbEdtEst11);
	}

	/**
	 * Select 'Software package' tab and click on 'Pending actions' tab. At
	 * least the 'xxxx' package in the table with the verify the values.
	 */
	public void vPakInPrgceExpColsData(String pakName, String version) {
		vNavigatedToPakInPrgs();
		vPakInPrgceColsData(pakName, version);
	}

	/**
	 * verify column data in Software package>> Pending actions
	 * 
	 * @param pakName
	 * @param version
	 */
	public void vPakInPrgceColsData(String pakName, String version) {
		selUtils.waitForTxtPresent("pak_inprog_footer_css", numOfEleText);
		selUtils.vExpValPresent("trm_edit_pak_name_xpath", nameLbl, pakName,
				pakName);
		selUtils.vExpIcon("trm_edit_pak_trm_icon_xpath", pakName,
				Terminals.terminal);
		selUtils.vExpColData("trm_edit_pak_vrs_xpath", pakName, version);
		vDateCol("trm_edit_pak_crt_date_xpath", pakName);
		vDateCol("trm_edit_pak_updt_date_xpath", pakName);
		ObjectFactory.getTerminalsInstance().vInPrgceStatusColInEdtPg(pakName,
				"trm_edit_pak_in_prg_status_xpath");
		selUtils.vExpIcon("trm_edit_pak_in_prg_cnslt_job_icn_xpath", pakName,
				Jobs.cnsltJobPrgceIcn);
		selUtils.vExpIcon("trm_edit_pak_in_prg_del_icn_xpath", pakName, delete);
	}

	/**
	 * Click on the 'Software package' tab and then on the 'Pending action' one
	 * under it. Verify, the 'Pending actions' tab gets in focus showing at
	 * least the expected package in the table.
	 **/
	public void vNavigatedToPakInPrgs() {
		selUtils.clickOnWebElement("edt_trm_pack_xpath", Merchants.swPak);
		vExpTabClkAdFocus("pending_actions_tab_xpath", pednActs,
				"pending_actions_xpath");
	}

	/**
	 * Click on the expected tab, and verify focus.
	 */
	public void vExpTabClkAdFocus(String tabLoc, String tabName,
			String focussedTabLoc) {
		waitNSec(1);
		selUtils.clickOnWebElement(tabLoc, tabName);
		selUtils.vExpTabInFocus(focussedTabLoc);
	}

	/**
	 * Click on 'Delete' tab under 'Software package' one,The 'Pending actions'
	 * tab gets in focus showing at least package with Internal expected Names
	 * in the table, both with expected values.
	 * 
	 * @param pakName
	 * @param version
	 * @throws InterruptedException
	 **/
	public void vPakDeletedExpColsData(String pakName, String version)
			throws InterruptedException {
		selUtils.clickOnWebElement("history_link", history);
		selUtils.vExpTabInFocus("del_pak_tab_xpath");
		vPakDelExpColsData(pakName, version);
	}

	/**
	 * Click on 'Delete' tab under 'Software package' one,The 'Pending actions'
	 * tab gets in focus showing at least package with Internal expected Names
	 * in the table, both with expected values.
	 */
	public void vPakDelExpColsData(String pakName, String version)
			throws InterruptedException {
		selUtils.waitForTxtPresent("pak_inprog_footer_css", numOfEleText);
		selUtils.vExpValPresent("trm_del_pak_name_xpath", nameLbl, pakName,
				pakName);
		selUtils.vExpIcon("trm_del_pak_icon_xpath", pakName, Terminals.terminal);
		selUtils.vExpColData("trm_del_pak_ver_xpath", pakName, version);
		vDateCol("trm_del_pak_cdate_xpath", pakName);
		vDateCol("trm_del_pak_udate_xpath", pakName);
		selUtils.vExpIcon("trm_del_pak_status_icon_xpath", pakName, statusRun);
		selUtils.vExpColData("trm_del_pak_status_val_xpath", pakName, delVal);
		selUtils.vExpIcon("trm_del_pak_cnslt_job_icon_xpath", pakName,
				Jobs.cnsltJobPrgceIcn);
	}

	/**
	 * Verify,the 'Software package' tab gets in focus.If not already in focus,
	 * click on 'Pending actions' tab, it gets in focus showing at least 'xxx'
	 * package in table.
	 */
	public void vPakInPrgceExpJobNmTrmIcnAnBlueBall(String pakName) {
		vNavigatedToPakInPrgs();
		selUtils.vExpValPresent("trm_edit_pak_name_xpath", nameLbl, pakName,
				pakName);
		selUtils.vExpIcon("trm_edit_pak_icon_xpath", pakName,
				Terminals.terminal);
		selUtils.vExpIcon("trm_edit_pak_ball_xpath", pakName, blue);
		xpath = selUtils.getPath("trm_edit_pak_ball_xpath").replace(
				TestBase.nameLbl, pakName);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath))
				.getAttribute("src").contains("permanent.png"),
				" color ball is not displayed.");
		logger.info("Verified, ball is displayed.");
	}

	/**
	 * Click on the 'Software package' tab and then on the 'Pending actions' one
	 * under it. Verify, the 'Pending actions' tab is in focus containing at
	 * least the packages with the Internal names from expected list in the
	 * table.
	 **/
	public void vPakInPrgceExpPkgNames(String browser, String[] expPaks) {
		selUtils.clickOnWebElement("pending_actions_tab_xpath", pednActs);
		selUtils.vExpTabInFocus("pending_actions_xpath");
		selUtils.waitForTxtPresent("pak_inprog_footer_css", numOfEleText);
		for (itemCount = 0; itemCount < 10; itemCount++) {
			objScrollDwn("edt_trm_pak_inprg_tbl_id");
			selUtils.vExpValPresent("trm_edit_pak_name_xpath", nameLbl,
					expPaks[itemCount], expPaks[itemCount]);
		}
	}

	/**
	 * Create a package set mandatory values Set the 'Type' field to
	 * 'Application'. Set the 'Package name' field to string Set the 'Version'
	 * field to a string
	 */
	public void setPakManVal(String paktype, String pckgName, String pckgver) {
		selUtils.vselectedItemInDrpDn("pak_type_id", paktype);
		selUtils.populateInputBox("pak_name_id", pckgName);
		wait.until(ExpectedConditions.textToBePresentInElementValue(
				By.id(selUtils.getPath("pak_name_id")), pckgName));
		vValFrmInputBox("pak_name_id", pckgName, packName);
		selUtils.populateInputBox("pak_ver_id", pckgver);
		vValFrmInputBox("pak_ver_id", pckgver, pakActTabColHdrs[1]);
	}

	/**
	 * Create a package set mandatory values Set the 'Type' field to
	 * 'Application'. Set the 'Existing package' field to string Set the
	 * 'Version' field to a string
	 */
	public void setExtPakManVal(String paktype, String pckgName, String pckgver) {
		selUtils.selectItem(selUtils.getObject("pak_type_id"), paktype);
		selUtils.vDrpDnSelcItem("pak_type_id", paktype);
		selUtils.selectItem(selUtils.getObject("ext_pack_id"), pckgName);
		selUtils.vDrpDnSelcItem("ext_pack_id", pckgName);
		waitMethods.waitForElementEnable("pack_ver_xpath");
		selUtils.populateInputBox("pack_ver_xpath", pckgver);
		vValFrmInputBox("pack_ver_xpath", pckgver, pakActTabColHdrs[1]);
	}

	/**
	 * Go to edit page of expected package
	 */
	public void vEditPgOfExpPkg(String editLoc, String expPkg, String expVer) {
		xpath = selUtils.getPath(editLoc).replace("Name", expPkg);
		waitNSec(2);
		waitMethods.waitForEleEnable(selUtils.getObjectDirect(By.xpath(xpath)));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				selUtils.getObjectDirect(By.xpath(xpath)));
		logger.info("Clicked on '" + expPkg + "' edit icon.");
		/*selUtils.waitForTxtPresent("bread_crumb_id", breadCrumEdtPak + " » "
				+ expPkg);*/
		selUtils.verifyBreadCrumb(breadCrumEdtPak + " » " + expPkg + " - "
				+ expVer);

	}
    /** verify package added*/
	public void vNwpakg(String pckgName) {
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		selUtils.vExpValPresent("pack_name_xpath", nameLbl, pckgName, pckgName);
	}

	/** Verify, the page is displayed containing: edit page package 
	 * details */
	public void vEdtPkgDtls(String pkgType, String pkgName, String pkgVer,
		String focusedTab, String ntFocusedTab) {
		selUtils.vExpValPresent("edt_pak_type_id", pkgType);
		selUtils.vExpValPresent("edt_pak_name_id", pkgName);
		selUtils.vExpValPresent("edt_pak_ver_id", pkgVer);
		vCrDateFormat("edt_pak_cr_dt_id");
		vExpTabFocused(focusedTab);
		vExpTabNtFocused(ntFocusedTab);
		selUtils.vExpValContains("pak_added_xpath", paksAdded);
		waitMethods.waitForElementEnable("edt_pak_cols_hdrs_css");
		vExpColsInTab(selUtils.getTabColHds("edt_pak_cols_hdrs_css"),
				edtPkgColHdrs);
	}

	/**Verify,'creation date' format.
	 */
	public void vCrDateFormat(String crDateLoc) {
		waitMethods.waitForElementDisplayed(crDateLoc);
		String crDate = selUtils.getObject(crDateLoc).getText();

		if (crDate.contains(" -")) {
			crDate = crDate.substring(0, crDate.indexOf(" -"));
			Assert.assertTrue(
					verifyDateTimeFormat(crDate, "yyyy-MM-dd HH:MM:ss"),
					"Expected '" + crDate
							+ "' date is not having expected date format.");
		} else {
			crDate = crDate.substring(0, 19);
			Assert.assertTrue(
					verifyDateTimeFormat(crDate, "dd/MM/yyyy HH:MM:ss")
							|| verifyDateTimeFormat(crDate,
									"MM/dd/yyyy HH:MM:ss"), "Expected '"
							+ crDate
							+ "' date is not having expected date format.");
		}

		logger.info("Verified, '" + crDate
				+ "' date is having expected date formate.");
	}

	
	/**
	 * Verify, table with the following columns and expected values and,
	 * 'Actions' : it contains a 'Find' icon.
	 * 
	 * @param techngy
	 * @param pkgRep
	 */
	public void vEdtPkgTblExpRowVals(String techngy, String pkgRep) {
		selUtils.vExpValPresent("edt_pak_tech_col_val_css", techngy);
		selUtils.vExpValPresent("edt_pak_rep_col_val_css", pkgRep);
		selUtils.verifyExpIconDisplayed("edt_pak_find_icon_css", findButt);
	}

	/**
	 * Verify that The table contains only one package.
	 * 
	 * @param objLoc
	 * @param val
	 * @param browser
	 */
	public void vOneValinPak(String objLoc, String val, String browser) {
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		waitMethods.waitForElementDisplayed(objLoc);
		vals = selUtils.getLstItems(selUtils.getObjects(objLoc));
		Assert.assertTrue(vals.size() == 1,
				" Table not contains only the package with the name " + val);
		Assert.assertTrue(vals.contains(val), val
				+ " is not present in Packages list");
		logger.info(" Verified that only expected package is present in list");
	}

	/**
	 * Find a package set values Set the 'Type' field to 'Application'. Set the
	 * 'Package name' field to string Set the 'Version' field to a string
	 */
	public void setPakVal(String paktype, String pckgName, String pckgver) {
		selUtils.selectItem(selUtils.getObject("find_pak_type_id"), paktype);
		selUtils.vDrpDnSelcItem("find_pak_type_id", paktype);
		selUtils.populateInputBox("find_pak_pack_id", pckgName);
		vValFrmInputBox("find_pak_pack_id", pckgName, packName);
		selUtils.populateInputBox("find_pak_ver_id", pckgver);
		vValFrmInputBox("find_pak_ver_id", pckgver, pakActTabColHdrs[1]);
	}

	/**
	 * Verify that deleted packages is not present in packages Table.
	 * @param pckgName
	 */
	public void vPakNotPresent(String pckgName) {
		waitMethods.waitForElementDisplayed("pack_name_list_xpath");
		vals = selUtils
				.getLstItems(selUtils.getObjects("pack_name_list_xpath"));
		Assert.assertFalse(vals.contains(pckgName), pckgName
				+ " is present in Packages list");
		logger.info(" Verified that " + pckgName
				+ " is not present in packages list.");
	}

	/** Create a software package with edit check box 
	 * @param pkgType
	 * @param expPkg
	 * @param expVer
	 * @throws InterruptedException
	 */
	public void crtPkgWithEdtChkBx(String pkgType, String expPkg, String expVer)
			throws InterruptedException {
		verifyExpWinDisp("crt_sw_pak_xpath", crtPakmodalWin);
		setPakManVal(pkgType, expPkg, expVer);
		selUtils.vSelcChkBx("pack_edit_crt_id", edtPakChk);
		confrmPopupActionMsg("crt_pak_id", "add_pak_proc_msg_id",
				"add_pak_succ_msg_id", pakCrt, pakSucMsg);
		verifyOnlyCloseButtInSuccWin("add_pak_cls_id");
		verifySpecifiedWinNotPresent("add_pak_cls_id");
		selUtils.verifyBreadCrumb(breadCrumEdtPak + " » " + expPkg + " - "
				+ expVer);
	}

	/**
	 * Verify, the message: 'All the pending download tasks will be deleted if
	 * this package is deactivated. Please confirm'. -A 'Confirm' and a 'Close'
	 * button. Click on the 'Close' button. Verify, the modal window is closed.
	 **/
	public void vDeActWindInfo() {
		selUtils.vExpValPresent("pack_deact_msg_id", delPakPopUpMsg);
		selUtils.verifyElementDisp("pak_del_id", confirm);
		selUtils.verifyElementDisp("pack_deact_msg_cls_id", closeButton);
		verifySpecifiedWinNotPresent("pack_deact_msg_cls_id");
	}

	/**Create a software package' with out edit check box
	 * @param pakType
	 * @param pckgName
	 * @param pckgver
	 * @param browser
	 */
	public void crtPkgWithoutEdtChkBx(String pakType, String pckgName,
			String pckgver, String browser) {
		verifyExpWinDisp("crt_sw_pak_xpath", crtPakmodalWin);
		setPakManVal(pakType, pckgName, pckgver);
		selUtils.deSelecChkBx("pack_edit_crt_id");
		selUtils.vDeSelecChkBx("pack_edit_crt_id", edtPakChk);
		confrmPopupActionMsg("crt_pak_id", "add_pak_proc_msg_id",
				"add_pak_succ_msg_id", pakCrt, pakSucMsg);
		verifyOnlyCloseButtInSuccWin("add_pak_cls_id");
		verifySpecifiedWinNotPresent("add_pak_cls_id");
		selectMaxSizeinTable("job_show_res_id", browser);
		vNwpakg(pckgName);
	}

	/**Click on the Delete icon in the 'Deactivate' column and validate 
	then Click on the 'Confirm' and 'Close' button then validate*/
	public void vDelPak(String pckgName, String browser) {
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vExpWinDisp("del_pack_xpath", pckgName, deact);
		verifyDeletePakFunctionality();
		verifyOnlyCloseButtInSuccWin("pak_del_succ_butts_css");
		verifySpecifiedWinNotPresent("pak_succ_del_cls_id");
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vPakNotPresent(pckgName);
	}

	/**Click on the 'Add SW package' button of the 'Software package 
	 * actions'Click on the 'Browse' button, select the package file 
	 *'TELIUM.zip' from your desktop Click on the 'Add' button of the 
	 * modal window
	 * @param file
	 * @param path
	 */
	public void vFileAdd(String file, String path, String rPath) {
		vFileAddFromBrowse(file, path);
		selUtils.waitForTxtPresent("pak_added_xpath", paksAdded);
		selUtils.vExpValContains("pak_added_xpath", paksAdded);
		waitMethods.waitForElementDisplayed("pak_file_added_xpath");
		selUtils.vExpValContains("pak_file_added_xpath", rPath + file);
		logger.info("Verified, '" + rPath + file
				+ "' file, successfully added. ");
	}

	/**Click on the 'Add SW package' button of the 'Software package 
	 * actions' lick on the 'Browse' button, select the package file 
	 * 'TELIUM_2.zip',Click on the 'Add' button*/
	public void vFileAddFromBrowse(String file, String path) {
		verifyExpWinDisp("pak_add_file_link", Packages.addPakFileWin);
		selUtils.getObject("browse_file_xpath").sendKeys(path + file);
		vValFrmInputBox("browse_file_xpath", file, pkgLbl);
		selUtils.getObject("pak_add_file_id").click();
		logger.info("Clicked on '" + addButt + "' button.");
	}

	/** Deactivate software package*/
	public void vPkgDeActProcess(String expPkg, String browser) {
		verifyDeletePakFunctionality();
		verifyOnlyCloseButtInSuccWin("pak_del_succ_butts_css");
		verifySpecifiedWinNotPresent("pak_succ_del_cls_id");
		selUtils.verifyBreadCrumb(breadCrumPak);
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vPakNotPresent(expPkg);
	}

	/**
	 * Deleting Existing Packages with Version*/
	public void deleteExistPakWithVer(String pak, String ver, String locator) {
		xpath = selUtils.getPath(locator).replace("Name", pak);
		xpath = xpath.replace("Ver", ver);
		if (selUtils.isElementPresentxpath(xpath)) {
			selUtils.getObjectDirect(By.xpath(xpath)).click();
			logger.info(" Deleting the Existing Package");
			verifyDeletePakFunctionality();
			verifyOnlyCloseButtInSuccWin("pak_del_succ_butts_css");
			verifySpecifiedWinNotPresent("pak_succ_del_cls_id");
		}
	}

	/**
	 * Verify that Deleted newly create Packages with Version
	 */
	public void vDelPakWithVer(String pak, String ver, String locator) {
		xpath = selUtils.getPath(locator).replace("Name", pak);
		xpath = xpath.replace("Ver", ver);
		Assert.assertFalse(selUtils.isElementPresentxpath(xpath), pak
				+ " is presentr in Packages List.");
		logger.info(" Verified that " + pak
				+ " is not present in packages list with version " + ver);
	}

	/**
	 * Verify that only two packages are presnet in the table*/
	public void vTwoValinPak(String packOne, String packTwo, String browser) {
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vals = selUtils
				.getLstItems(selUtils.getObjects("pack_name_list_xpath"));
		Assert.assertTrue(vals.size() == 2, " Table not contains 2 packages.");
		Assert.assertTrue(vals.contains(packOne), packOne
				+ " is not present in Packages list");
		Assert.assertTrue(vals.contains(packTwo), packTwo
				+ " is not present in Packages list");
		logger.info("Verified, expected '" + packOne + "', '" + packTwo
				+ "' packages are present in list.");
	}

	/**
	 * Verify that only two packages are presnet in the table*/
	public void vOneValinPak(String packOne, String browser) {
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vals = selUtils
				.getLstItems(selUtils.getObjects("pack_name_list_xpath"));
		Assert.assertTrue(vals.size() == 1, " Table not contains 1 packages.");
		Assert.assertTrue(vals.contains(packOne), packOne
				+ " is not present in Packages list");
		logger.info("Verified, expected '" + packOne
				+ "' packages are present in list.");
	}

	/**
	 * vCretPakWin Verify create package window.
	 */
	public void vCretPakWin() {
		selUtils.vDeSelecChkBx("pack_reg_name", regPak);
		selUtils.verifyElementDisp("pack_type_id", packColHdr[3]);
		selUtils.verifyElementDisp("pak_name_id", packColHdr[1]);
		selUtils.verifyElementDisp("pak_ver_id", packColHdr[2]);
		selUtils.vChkBxSelected("pack_edit_crt_id", edtPakChk);
		selUtils.verifyElementDisp("crt_pak_id", confirm);
		selUtils.verifyElementDisp("cls_mod_pak_id", closeButton);
	}

	/**
	 * If not already in focus, click on the 'Pending actions' tab The 'In
	 * progress' tab gets in focus showing at least the 'PCKG_2106' package in
	 * the table.
	 */
	public void selPendActnTab() {
		waitMethods.waitForElementDisplayed("pending_actions_xpath");
		if (selUtils.getObject("pending_actions_xpath").getAttribute("class")
				.contains("tabs-selected")) {
			logger.info("Verified, expected tab is in focused.");
		} else {
			selUtils.getObject("pending_actions_xpath").click();
			selUtils.vExpTabInFocus("pending_actions_xpath");
		}
	}

	/**verify edit package  page */
	public void vPakEditPage(String browser) {
		selectMaxSizeinTable("job_show_res_id", browser);
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		objScrollDwn("paks_tbl_id");
		vEditPgOfExpPkg("pak_edt_icon_xpath", pakList1[1],
				SWConfigurations.pakVer10);
		selUtils.vBackToLstButt();
		vEdtPkgDtls(pakType[2], pakList1[1], SWConfigurations.pakVer10,
				softPack, edtPkgNtFocdTbs[1]);
		selUtils.verifyElementDisp("edt_pak_prd_icon_xpath", prdIcon);
		vGrpButts("edt_pak_act_butts_xpath", pakActs1, packsActs);
		selUtils.vExpValPresent("edt_pak_err_msg_css", noDataFndMsg);
	}

	/**verify edit package  page */
	public void vPakDetails(String browser) {
		selUtils.verifyElementDisp("find_but_xpath", findButt);
		vExpButtInGrp("packg_actio_but_xpath", crtPakmodalWin, crtPakmodalWin);
		vExpColsInTab(selUtils.getTabColHds("pack_cols_headings_css"),
				packColHdr);
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vals = selUtils
				.getLstItems(selUtils.getObjects("pack_name_list_xpath"));
		for (itemCount = 0; itemCount < pakList.length; itemCount++) {
			Assert.assertTrue(vals.contains(pakList[itemCount]),
					pakList[itemCount] + " is not present in Packages list");
			selUtils.vExpIcon("edit_pack_xpath", pakList[itemCount],
					packColHdr[0]);
			selUtils.vExpIcon("del_pack_xpath", pakList[itemCount], delete);
		}
		logger.info(" Verified that all expected packages are present in list");
	}
}
