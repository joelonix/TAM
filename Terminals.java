package com.ingenico.tam.objects;

/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/Terminals.java $
 $Id: Terminals.java 14253 2015-07-21 11:49:41Z amsingh $
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import com.ingenico.base.TestBase;

/**
 * Terminals Class - Methods related to Terminals module
 */
public class Terminals extends TestBase {

	public static String brdCrbTerms = "TMS » Terminals » Auto_Test",
			trmTask = "Terminal tasks",
			bredEditTerm = "TMS » Terminals » Auto_Test",
			brdCrTrms111 = brdCrbTerms + " » " + Estates.autoEst111,
			swapTrm = "Swap/Clone a terminal - Confirmation",
			brdCrTrms11 = brdCrbTerms + " » " + Estates.autoEst11,
			mulPakSrc = "Multiple package search",
			brdCrTrms21 = brdCrbTerms + " » " + Estates.autoEst21,
			brdCrTrms2 = brdCrbTerms + " » " + Estates.autoEst2,
			trmClonePrgMsg = "Terminal cloning in progress ",
			terminals = "Terminals",
			trmsCsv = "terminals.csv",
			trmCloneSuccMsg = "The terminal has been cloned successfully ",
			trmSwapPrgMsg = "Terminal swapping in progress ",
			trmAct = "Terminal actions",
			trmConfig = "Terminals config.",
			breadCrumEditTerm = "TMS » Edit terminal",
			backToTrm = "Back To Terminal",
			backToList = "Back To List",
			addTrmToolTip = "Added on the terminal",
			prdLbl = "Product Label",
			thrErrMsg = "First serial number is greater than last serial number",
			termConfgButtsLbl = "Terminals config.",
			termActButtsLbl = "Terminals actions",
			termTskButtsLbl = "Terminals tasks",
			addTrms = "Add terminals",
			trmDelPopUpMsg = "Do you confirm the deletion of selected terminals?",
			searchTrmsLbl = "Find terminals",
			addATrm = "Add a terminal",
			techngyLbl = "Technology",
			prodLbl = "Product",
			srNumLbl = "Serial Number",
			partNumLbl = "Part Number",
			thrLbl = "Through",
			editNwTrmChkBxLbl = "Edit the new terminal after the creation",
			trmInfoMsg = "No terminal selected",
			trmProcMsg = "Creation of the terminal in progress",
			trmsProcMsg = "Creating group of terminals",
			trmsSuccMsg = "Terminals have been added successfully",
			asgnToTrmRadButt = "Assign to terminal(s)",
			delTrmPopUpMsg = "Do you confirm the deletion of the package for the current terminal?",
			edtTrmCfgButLbl = "Terminal config.",
			edtTrmActButtsLbl = "Terminal actions",
			edtTskButtsLbl = "Terminal tasks",
			delTrmPrcMsg = "Terminals deletion is running, please wait...",
			trmSuccMsg = "The terminal has been added successfully.",
			delTrmCnfmMsg = "Do you confirm the deletion of the terminal?",
			theTrm = "- The terminal '",
			alrExt = "' already exists",
			techMndMsg = "Technology is mandatory",
			srNoMndMsg = "Serial number is mandatory",
			updTerm = "Updating terminal...",
			edtDelTrmProcMsg = "Deletion of the terminal in progress...",
			updTrmSuccMsg = "Terminal modified with success",
			prNoMndMsg = "Part number is mandatory",
			terminal = "Terminal",
			model = "Model",
			moveprocMsg = "Moving of selected terminals in progress...",
			moveSuccMsg = "Terminal(s) moved to estate",
			thrMndrMsg = "Last serial number is mandatory",
			edtTrmDelSuccMsg = "The terminal has been deleted successfully.",
			thrErrMsgs = "Last serial number must be a number",
			sign003 = "2100000321000003",
			auto21 = "Auto_21000000",
			trmProdName = "iCT220 & iCT250",
			prd = "iCT220 & iCT250",
			sign21 = "2100000021000000",
			no21 = "21000000",
			trmSection = "Terminal selection",
			swapTrmMsg = "Retrieve informations from source terminal in progress",
			srcTrm = "Source terminal",
			swpTrmPopUpMsg = "Do you confirm the swapping of the terminal?",
			trgTrm = "Targeted terminal",
			cloneTrmPopUpMsg = "Do you confirm the cloning of the terminal?",
			swpCln = "Swap/Clone",
			prodM40 = "M40",
			edtTrgTrmSwpCkBx = "Edit targeted terminal after the swapping action",
			signature = "SIGNATURE",
			edtTrgTrmClnCkBx = "Edit targeted terminal after the cloning action",
			remove = "Remove",
			prdict220Ad250 = "iCT220 & iCT250",
			prdi3010 = "i3010",
			swapTerm = "Swap/Clone a terminal",
			prdipa280 = "iPA280",
			trgTrmMsg = "The signature of the targeted terminal is mandatory!",
			srcPakFnd = "This search will return the terminals in where at least one of the selected package is found.",
			swpClnTrmToolTip = "Click to swap or clone the terminal",
			addNew = "Add New", input = "input", selt = "select",
			trmsToMove = "Terminals to move",
			trmSwapSuccMsg = "The terminal has been swapped successfully ",
			configuration = "Configuration", summary = "Summary",
			execommand = "Executed command", logs = "Logs", campaigName= "Campaign name",
			cnfgNameErrMsg = "campaign name is mandatory";

	public String trmSign = "", trmDeletedMsg = "Terminal " + trmSign
			+ " has been deleted", name = "", thr = "", prodItm = "",
			prod = "", termNos = "", srNo1 = "", prNo1 = "", thr1 = "",
			nameAllowed = "", trmSign2 = "";

	public static String[] termActions = { actionMove, delete,
			"Add a terminal", addTrms },
			edtTrmActs = { modify, actionMove, delete },
			techngyLstItems = { "Unknown", "U32", "Telium", "WINCE","TETRA"},
			trmColsHdrs = { select, Jobs.jobsTabcolsHds[4], signLbl,
					partNumLbl, srNumLbl, model, prodLbl, techngyLbl, nameLbl,
					statusDate },
			termTskButts = { "Merchant", "Add scenario", taskTypes[2],
					"Add key", "Add campaign" },
			switchElements = { "configurationName_combobox",
					"dialogAddConfigurationTo_0", "dialogAddConfigurationTo_1" },
			edtTrmTabs = { Merchants.swPak, "Properties", statistics,
					"Key management", Scenarios.cmplxSce, "Call history",
					"Features" },
			edtTrmNtFocuTab = { edtTrmTabs[0], edtTrmTabs[1], edtTrmTabs[3],
					edtTrmTabs[4], edtTrmTabs[5], edtTrmTabs[6] },
			srchTrmTabs = { "General properties", Packages.packages,
					"Parameters Settings", "Hardware Statistics", calls },
			edtTskButts = { "Add scenario", taskTypes[2], taskTypes[3],
					taskTypes[1], "Add campaign" },
			edtTskButt = { "Add scenario", taskTypes[1], taskTypes[2] },
			defTrms2 = { "2100000021000000", "2100000121000001",
					"2100000221000002", "2100000321000003", "2100000421000004",
					"2100000521000005", "2100000621000006", "2100000721000007",
					"210000000008:210000000008", "2100000921000009" },
			defTrms1 = { "1100000011000000", "1100000111000001",
					"1100000211000002", "1100000311000003", "1100000411000004",
					"1100000511000005", "1100000611000006", "1100000711000007",
					"110000000008:110000000008", "1100000911000009" },
			names1 = { "Auto_11000000", "Auto_11000001", "Auto_11000002",
					"Auto_11000003", "Auto_11000004", "Auto_11000005",
					"Auto_11000006", "Auto_11000007", "Auto_110000000008",
					"Auto_11000009" },
			names2 = { "Auto_21000000", "Auto_21000001", "Auto_21000002",
					"Auto_21000003", "Auto_21000004", "Auto_21000005",
					"Auto_21000006", "Auto_21000007", "Auto_210000000008",
					"Auto_21000009" },

			nos = { "11000000", "11000001", "11000002", "11000003", "11000004",
					"11000005", "11000006", "11000007", "110000000008",
					"11000009", "21000000", "21000001", "21000002", "21000003",
					"21000004", "21000005", "21000006", "21000007",
					"210000000008", "21000009" },
			termActs = { actionMove, delete, addTrms },
			wildCharData = { "2100000*", "21000002*", "*1000001", "*100000*",
					"2100*001*" },
			hardStatHead = { "Field", "Condition", "Value" },
			unPFocSrcTab = { srchTrmTabs[0], srchTrmTabs[3], srchTrmTabs[4] },
			srchTrmTab = { srchTrmTabs[0], srchTrmTabs[1], srchTrmTabs[3] },
			unFocSrcTab = { Packages.packages, srchTrmTabs[3], srchTrmTabs[4] },
			srchStatuses = { "Activated", "Created", "Failed initialization",
					"Pending auto initialization", "Swapped" };

	public List<WebElement> elementSign;
	public List<String> elementNameVal, elementPrdVal, elementTechVal,
			elementPrdLblVal, elementPartVal, elementSerNoVal;

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

	/**Click on the 'Add terminals' button of the 'Terminals actions' buttons
	 * section The 'Add terminals' modal window is displayed.The 'Estate' field
	 * is set by default to 'Auto_Test'. Select and Verify 'Technology',
	 * 'Product', 'Serial Number','Through'and 'Part Number'.**/
	
	public void addTerms(String clnt, String tech, String prod, String serNo,
			String partNo, String thr) {
		verifyExpWinDisp("add_trms_xpath", addTrms);
		selUtils.waitForTxtPresent("trms_est_id", clnt);
		vDrpDnDefltSelcItem("trms_est_id", clnt, Estates.estLbl);
		selUtils.selectItem(selUtils.getObject("trms_tech_id"), tech);
		selUtils.vDrpDnSelcItem("trms_tech_id", tech);
		new Select(selUtils.getObject("trms_prod_id"))
				.selectByVisibleText(prod);
		selUtils.vDrpDnSelcItem("trms_prod_id", prod);
		selUtils.populateInputBox("trms_serial_id", serNo);
		Assert.assertEquals(
				selUtils.getObject("trms_serial_id").getAttribute("value"),
				serNo, " Serial number field is not updated");
		logger.info(" Verified that Serial number field is updated as expected");
		selUtils.populateInputBox("trms_thru_id", thr);
		Assert.assertEquals(
				selUtils.getObject("trms_thru_id").getAttribute("value"), thr,
				" Through field is not updated");
		logger.info(" Verified that Through field is updated as expected");
		selUtils.populateInputBox("trms_part_id", partNo);
		Assert.assertEquals(
				selUtils.getObject("trms_part_id").getAttribute("value"),
				partNo, " 'Part Number' field is not updated");
		logger.info(" Verified that 'Part Number' field is updated as expected");
	}

	/**Tick the check box in the 'Select' column of the table for the expected
	 * terminal. Verify the check box is ticked. **/
	public void selChkWithSign(String sign) {
		xpath = selUtils.getPath("trm_selc_chkb_xpath")
				.replace(signature, sign);
		if (!(selUtils.getObjectDirect(By.xpath(xpath)).isSelected())) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].click();",
					selUtils.getObjectDirect(By.xpath(xpath)));
			Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath))
					.isSelected(), " Check Box is not selected");
			logger.info("Verified, expected '" + selUtils.speCharReplc(sign)
					+ "' Check Box is selected.");
		}
	}

	/**Tick the check boxes in the 'Select' column of the table for the expected
	 * terminals. Verify the check box is ticked. **/
	public void selAdVrfChkBxsWithSigns(String[] signs) {
		for (itemCount = 0; itemCount < signs.length; itemCount++) {
			xpath = selUtils.getPath("trm_selc_chkb_xpath").replace(signature,
					signs[itemCount]);
			if (!(selUtils.getObjectDirect(By.xpath(xpath)).isSelected())) {
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].click();",
						selUtils.getObjectDirect(By.xpath(xpath)));
				Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath))
						.isSelected(), " Check Box is not selected");
			}
			logger.info("Verified, expected Check Boxes are selected.");
		}
	}

	/**Verify, the selected check box in terminal table.**/
	public void vselectedChkBxWithSign(String sign) {
		xpath = selUtils.getPath("trm_selc_chkb_xpath")
				.replace(signature, sign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath))
				.isSelected(), " Check Box is not selected");
		logger.info("Verified, expected '" + selUtils.speCharReplc(sign)
				+ "' Check Box is selected.");
	}

	/**Tick off the check box in the 'Select' column of the table for the
	 * expected terminal. Verify the check box is ticked.**/
	public void deSelAdVrfChkWithSign(String sign) {
		xpath = selUtils.getPath("trm_selc_chkb_xpath")
				.replace(signature, sign);
		if ((selUtils.getObjectDirect(By.xpath(xpath)).isSelected())) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].click();",
					selUtils.getObjectDirect(By.xpath(xpath)));
			Assert.assertTrue(
					!(selUtils.getObjectDirect(By.xpath(xpath)).isSelected()),
					" Check Box is selected");
			logger.info("Verified, expected '" + sign
					+ "' Check Box is deselected.");
		}
	}

	/**Keep 'Edit the new terminal after creation' check box ticked.Click on 
	 *'Confirm' button. Verify modal window displays message:'Creation of the 
	 *terminal in progress'.Then it displays the message 'The terminal has been 
	 *added successfully' and only a 'Close' button.Click on 'Close' button.*/
	
	public void vSelcChkBxAdEdtPage(String sign) {
		// Keep the 'Edit the new terminal after the creation' check box ticked.
		selUtils.vSelcChkBx("edit_nw_trm_chkb_xpath", editNwTrmChkBxLbl);
		
		/*Click on 'Confirm' button.Verify modal window displays expected 
		 *message and only a 'Close' button is available.Click on 'Close' button. 
		 */
		confrmPopupActionMsg("add_a_trm_cnfm_butt_id", "trm_procc_msg_id",
				"trm_succ_msg_id", trmProcMsg, trmSuccMsg);
		verifyOnlyCloseButtInSuccWin("trm_succ_cls_butt_id");
		verifySpecifiedWinNotDisp("trm_succ_cls_butt_id");
		selUtils.verifyBreadCrumb(breadCrumEditTerm + " » " + sign);
	}

	/**Click on 'Add package' button of 'Terminals tasks' buttons section.Click 
	 *on the 'Package name' Drop-down list icon and choose expected package.
	 *Click on the 'Add' button.Verify,modal window displays the message and 
	 *only a 'Close'.Click on the 'Close' button.*/
	
	public void vPkgCrInEdtTrmPg(String expPkg, String expMsg) {
		
		/*Click on the 'Add package' button of the 'Terminals tasks' buttons
		 *section. Verify 'Add package' modal window is displayed.Click on the 
		 *'Package name' Drop-down list icon and choose expected package.*/
		
		verifyExpWinDisp("add_pak_xpath", Packages.addPak);
		selUtils.vselectedItemInDrpDn("edt_pak_name_css", expPkg);
		
		/*Click on 'Add' button.Verify  modal window displays the message: 
		 *'Processing'.Then it displays the expected message. Package already 
		 *contained into 0 terminal(s)' and only 'Close' button is available.*/
		
		confrmPopupActionMsg("add_pak_id", "dialogbox_addpac_msg_id",
				"trm_pak_err_msg_css", processingMsg, expMsg);
		verifyOnlyCloseButtInSuccWin("trm_pak_res_butts_css");
		// Click on the 'Close' button. Verify, the modal window is closed.
		verifySpecifiedWinNotDisp("trm_pak_res_cls_id");
	}

	/**Click on 'Add package' button of the 'Terminals tasks' buttons section.
	 *Click on 'Package name' Drop-down list icon and choose expected package.
	  Click on 'Add' button.Verify, the modal window displays expected message 
	  and only a 'Close' button is available.
	 *Click on 'Close' button. */
	
	public void vPkgCrInEdtTrmPg(String expPkg, String expMsg, String tskPrio) {
		/*Click on the 'Add package' button of the 'Terminals tasks' buttons
		 * section. Verify, The 'Add package' modal window is displayed. Click
		 * on the 'Package name' Drop-down list icon and choose expected
		 * package. Verify, the field is set to same package. */
		
		verifyExpWinDisp("add_pak_xpath", Packages.addPak);
		selUtils.vselectedItemInDrpDn("edt_pak_name_css", expPkg);
		addAdVerifyVal("pack_tsk_prio_css", tskPrio, tskPriLbl);
		/*Click on the 'Add' button. Verify, the modal window displays the
		 * message: 'Processing'. Then it displays the message 'Package added to
		 * 1 terminal(s). Package already contained into 0 terminal(s)' and only
		 * a 'Close' button is available at the bottom of the modal window.*/
		
		confrmPopupActionMsg("add_pak_id", "dialogbox_addpac_msg_id",
				"trm_pak_err_msg_css", processingMsg, expMsg);
		verifyOnlyCloseButtInSuccWin("trm_pak_res_butts_css");
		// Click on the 'Close' button. Verify, the modal window is closed.
		verifySpecifiedWinNotDisp("trm_pak_res_cls_id");
	}

	/** Click on the 'Confirm' button of the modal window. Verify, the modal
	 * window displays the message: 'Terminals deletion is running, please
	 * wait...'.Then it displays the messages related to all terminals. Click
	 * on the 'Close' button. **/
	public void delTerms(String delTrmPrcMsg, String termcolsDetls[]) {
		List<String> delEleMsg;
		selUtils.clickOnWebElement("trm_del_cnfm_butt_id", delete);
		//selUtils.getObject("trm_del_cnfm_butt_id").click();
		logger.info(" Clicked on Delete Confirm button");
		waitMethods.waitForElementDisplayed("trm_succ_delmsg_cls_id");
		final List<WebElement> elementDelMsg = selUtils
				.getObjects("term_delmsg_xpath");
		delEleMsg = selUtils.getLstItems(selUtils
				.getObjects("term_delmsg_xpath"));
		for (cnti = 0; cnti < elementDelMsg.size(); cnti++) {
			Assert.assertTrue(delEleMsg
					.contains("- The terminal has been deleted ("
							+ termcolsDetls[cnti] + ")"));
		}
		logger.info(" Verified that delete succsess message is displayed as expected.");
		delEleMsg.clear();
		verifyOnlyCloseButtInSuccWin("trm_succ_delmsg_cls_id");
		verifySpecifiedWinNotDisp("trm_succ_delmsg_cls_id");
		waitForTerminalPageLoaded();
		final List<WebElement> elementSign2nd = selUtils
				.getObjects("term_signscol_xpath");
		for (cnti = 0; cnti < termcolsDetls.length; cnti++) {
			Assert.assertFalse(elementSign2nd.get(cnti).getText()
					.contains(termcolsDetls[cnti]));
		}
		logger.info(" Verified that newly created terminals are removed from the table.");
	}

	/**Verify, the terminal with the created Signature appears in the table of
	 * the 'TMS >> Terminals >> Auto_Test' page with the values in the 'Part
	 * Number', 'Serial Number', 'Model', 'Product', 'Technology', 'Name' and
	 * 'Status Date' columns with expected values.**/
	
	public void vNwTrmDetails(String browser, String trmSign, String partNo,
			String srNo, String model, String prod, String techngy, String name) {
		vNwTrmDtls(browser, trmSign, partNo, srNo, model, prod, techngy, name);
		vTrmStsDtFormat(trmSign);
	}

	/**Verify, the terminal with the created Signature appears in the table of
	 * the 'TMS >> Terminals >> Auto_Test' page with the values in the 'Part
	 * Number', 'Serial Number', 'Model', 'Product', 'Technology', 'Name'
	 * columns with expected values.**/
	public void vNwTrmDtls(String browser, String trmSign, String partNo,
			String srNo, String model, String prod, String techngy, String name) {
		selUtils.verifyBreadCrumb(brdCrbTerms);
		waitForTerminalPageLoaded();
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		xpath = selUtils.getPath("trm_sign_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(trmSign), "Expected '" + trmSign
				+ "' signature is not displayed.");
		xpath = selUtils.getPath("trm_partno_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(partNo), "Expected '" + partNo
				+ "' part number is not displayed.");
		xpath = selUtils.getPath("trm_srno_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(srNo), "Expected '" + srNo
				+ "' serial number is not displayed.");
		xpath = selUtils.getPath("trm_prodlbl_col_val_xpath").replace(
				signature, trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equalsIgnoreCase(model), "Expected '" + model
				+ "' product label is not displayed.");
		xpath = selUtils.getPath("trm_prod_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(prod), "Expected '" + prod
				+ "' product is not displayed.");
		xpath = selUtils.getPath("trm_techngy_col_val_xpath").replace(
				signature, trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equalsIgnoreCase(techngy), "Expected '" + techngy
				+ "' technology is not displayed.");
		xpath = selUtils.getPath("trm_name_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(name), "Expected '" + name
				+ "' name is not displayed.");
		logger.info("Verified, '" + trmSign + "', '" + partNo + "',  '" + srNo
				+ "', '" + model + "', '" + prod + "', " + "'" + techngy
				+ "', '" + name
				+ "' values of new terminal, are displayed as expected.");
	}

	/**Verify, the terminal with the created Signature appears in the table of
	 * the 'TMS >> Terminals >> Auto_Test' page with the values in the 'Part
	 * No', 'Serial No', 'Name'.**/
	public void vNwTrmDetails(String browser, String trmSign, String partNo,
			String srNo) {
		selUtils.verifyBreadCrumb(brdCrbTerms);
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		xpath = selUtils.getPath("trm_partno_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(partNo), "Expected '" + partNo
				+ "' part number is not displayed.");
		xpath = selUtils.getPath("trm_srno_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(srNo), "Expected '" + srNo
				+ "' serial number is not displayed.");
		xpath = selUtils.getPath("trm_name_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(trmSign), "Expected '" + trmSign
				+ "' name is not displayed.");
		logger.info("Verified, '" + partNo + "',  '" + srNo + "', '" + trmSign
				+ "' values of new terminal, are displayed as expected.");
	}

	/** Verify, the terminal with the created Signature appears in the table of
	 *the 'TMS >> Terminals >> Auto_Test' page with the values in the
	 *'Signature'. **/
	public void vNwTrmSign(String trmSign, String browser) {
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		objScrollDwn("trms_tbl_id");
		xpath = selUtils.getPath("trm_sign_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(trmSign), "Expected '" + trmSign
				+ "' signature is not displayed.");
		logger.info("Verified,  '" + trmSign
				+ "' new terminal is displayed as expected.");
	}

	/** Verify, the terminal with the created Signature appears in the table of
	 * the 'TMS >> Terminals >> Auto_Test' page with the values in the 'Name'.
	 **/
	public void vNwTrmName(String name, String trmSign) {
		xpath = selUtils.getPath("trm_name_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(name), "Expected '" + name
				+ "' name is not displayed.");
		logger.info("Verified, '" + name
				+ "' values of new terminal, is displayed as expected.");
	}

	/** Verify, the terminal with the created Signature appears in the table of
	 * the 'TMS >> Terminals >> Auto_Test' page with the values in the 'Product
	 * Label', 'Product', 'Name'.**/
	public void vNwTrmDetails(String browser, String trmSign, String model,
			String prod, String name) {
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		xpath = selUtils.getPath("trm_sign_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(trmSign), "Expected '" + trmSign
				+ "' signature is not displayed.");
		xpath = selUtils.getPath("trm_prodlbl_col_val_xpath").replace(
				signature, trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(model), "Expected '" + model
				+ "' product label is not displayed.");
		xpath = selUtils.getPath("trm_prod_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(prod), "Expected '" + prod
				+ "' product is not displayed.");
		xpath = selUtils.getPath("trm_techngy_col_val_xpath").replace(
				signature, trmSign);
		xpath = selUtils.getPath("trm_name_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(name), "Expected '" + name
				+ "' name is not displayed.");
		logger.info("Verified, '" + trmSign + "', '" + model + "', '" + prod
				+ "', '" + name
				+ "' values of new terminal, are displayed as expected.");
	}

	/**Click on the 'Confirm' button of the modal window. Verify, the modal
	 * window displays the message: 'Terminals deletion is running, please
	 * wait...'. Then it displays the expected message and only a 'Close' button
	 * is available at the bottom of the modal window.**/
	public void delTrm(String delTrmPrcMsg, String trmDeletedMsg) {
		selUtils.getObject("trm_del_cnfm_butt_id").click();
		logger.info("Clicked on Delete 'Confirm' button.");
		/*
		 * Assert.assertTrue(selUtils.getObject("trm_del_procmsg_id").getText().
		 * equals(delTrmPrcMsg),
		 * "Delete processing message is not displayed as expected.");
		 * logger.info
		 * ("Verified, delete processing message is displayed as expected.");
		 */wait.until(ExpectedConditions.invisibilityOfElementWithText(
				By.id(selUtils.getPath("trm_del_procmsg_id")), delTrmPrcMsg));
		// waitMethods.waitForElementDisplayed("trm_succ_delmsg_cls_id");
		Assert.assertTrue(selUtils.getObject("term_delmsg_xpath").getText()
				.equals(trmDeletedMsg),
				"Expected terminal deleted message is not displayed as expected.");
		logger.info("Verified, expected terminal deleted message is displayed as expected.");
	}

	/** Verify, the expected terminal is removed from the table (terminal is not
	 * displayed in the table)*/
	public void vExpTrmNotDisp(List<String> elementSignVal, String expTrm) {
		Assert.assertFalse(elementSignVal.contains(expTrm),
				selUtils.speCharReplc(expTrm)
						+ "' terminal is not removed from the table.");
		logger.info("Verified, '" + selUtils.speCharReplc(expTrm)
				+ "' terminal is removed from the table.");
	}

	/** Verify, the expected terminal is displayed in the table. */
	public void vExpTrmDisp(List<String> elementSignVal, String expTrm) {
		Assert.assertTrue(elementSignVal.contains(expTrm), expTrm
				+ "' terminal is not removed from the table.");
		logger.info("Verified, '" + expTrm
				+ "' terminal is present in the table.");
	}

	/** * Wait for terminal page loaded. */
	public void waitForTerminalPageLoaded() {
		selUtils.waitForTxtPresent("trm_tabl_foot_xpath",editPageDispTxt);
	}

	/**Verify, -A 'Find' button. -A 'Terminals actions' buttons section with the
	 * buttons: 'Move', 'Delete', 'Add a terminal', 'Add terminals'. -A
	 * 'Terminals tasks' buttons section with the buttons: 'Merchant', 'AddnumOfEleDisp
	 * scenario', 'Add package', 'Add key', 'Add SW config.'.... **/
	public void vTermPageInfo() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(selUtils.getCommonPath("btn_spinner_xpath"))));
		selUtils.verifyElementDisp("find_but_xpath", findButt);
		vGrpButts("term_act_butts_xpath", termActions, termActButtsLbl);
		vGrpButts("term_tsk_butts_xpath", termTskButts, termTskButtsLbl);
		vExpButtInGrp("term_confg_butts_xpath", CallScheduling.CALLSCHD,
				termConfgButtsLbl);
		vExpColsInTab(selUtils.getTabColHds("trm_cols_headings_css"),
				trmColsHdrs);
	}

	/**
	 * Verify, job created date format.
	 */
	public void vTrmStsDtFormat(String sign) {
		String stsDt = "";
		xpath = selUtils.getPath("trm_stsdt_col_val_xpath").replace(signature,
				sign);
		stsDt = selUtils.getObjectDirect(By.xpath(xpath)).getText();
		Assert.assertTrue(verifyDateTimeFormat(stsDt, "MM/dd/yyyy HH:MM:ss"),
				"Expected '" + stsDt
						+ "' date is not having expected date format.");
		logger.info("Verified, '" + stsDt
				+ "' date is having expected date formate.");
	}

	/**Verify, expected terminal is not exist.*/
	public void delExpTrm(List<String> elementSignVal, String expTrm) {
		waitForTerminalPageLoaded();
		if (elementSignVal.contains(expTrm)) {
			selChkWithSign(expTrm);
			selUtils.getObject("delete_link").click();
			selUtils.getObject("trm_del_cnfm_butt_id").click();
			waitMethods.waitForElementDisplayed("trm_succ_delmsg_cls_id");
			selUtils.getObject("trm_succ_delmsg_cls_id").click();
			logger.info(" Terminal Data is deleted Successfully :"
					+ selUtils.speCharReplc(expTrm));
		}

	}

	/** Get 'signature' entire column values.*/
	public List<String> getSignColVals() {
		waitForTerminalPageLoaded();
		final List<WebElement> elementSign = selUtils
				.getObjects("term_signscol_xpath");
		final List<String> elementSignVal = new ArrayList<String>();
		for (itemCount = 0; itemCount < elementSign.size(); itemCount++) {
			elementSignVal.add(elementSign.get(itemCount).getText());
		}
		return elementSignVal;
	}

	/** Delete expected terminal.*/
	public void expTrmDelProce(String expTrm) {
		String trmDeletedMsg = "";
		/*
		 * Tick the check box in the 'Select' column of the table for the new
		 * added terminal. Verify, the check box is ticked
		 */
		selecExpTrm(expTrm);

		/*
		 * Click on the 'Delete' button of the 'Terminals actions' buttons
		 * section. Verify, the 'Delete' modal window is displayed
		 */
		verifyExpWinDisp("delete_link", delete);

		/*Click on 'Confirm' button of modal window. Verify, the modal window 
		 *displays message: 'Terminals deletion is running, please wait...'.
		 *Then it displays expected message and only a 'Close' button. */
		
		trmDeletedMsg = "- The terminal has been deleted (" + expTrm + ")";
		delTrm(Terminals.delTrmPrcMsg, trmDeletedMsg);
		verifyOnlyCloseButtInSuccWin("trm_succ_delmsg_butts_css");

		/*Click on the 'Close' button. Verify, the modal window is closed. The
		 * expected terminal is removed from the table (you might need to look
		 * in all the pages of the table). */
		verifySpecifiedWinNotDisp("trm_succ_delmsg_cls_id");
		if ("abcdefghijklmnopqrstuvwxyz".equals(expTrm)) {
			waitNSec(1);
		}
		waitForTerminalPageLoaded();
		vExpTrmNotDisp(getSignColVals(), expTrm);
	}

	/**Tick the check box in the 'Select' column of the table for the new added
	 * terminal. Verify, the check box is ticked.**/
	public void selecExpTrm(String expTrm) {
		xpath = selUtils.getPath("trm_selc_chkb_xpath").replace(signature,
				expTrm);
		if (!(selUtils.getObjectDirect(By.xpath(xpath)).isSelected())) {
			selUtils.getObjectDirect(By.xpath(xpath)).sendKeys("");
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].click();",
					selUtils.getObjectDirect(By.xpath(xpath)));
			selUtils.waitForDirectEleSelected("trm_selc_chkb_xpath", signature,
					expTrm);
			vselectedChkBxWithSign(expTrm);
		}
	}

	/**Select the check boxes of expected terminals.**/
	public void selecExpTrms(String[] expTrms) {
		for (itemCount = 0; itemCount < expTrms.length; itemCount++) {
			xpath = selUtils.getPath("trm_selc_chkb_xpath").replace(signature,
					expTrms[itemCount]);
			if (!(selUtils.getObjectDirect(By.xpath(xpath)).isSelected())) {
				selUtils.getObjectDirect(By.xpath(xpath)).sendKeys("");
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].click();",
						selUtils.getObjectDirect(By.xpath(xpath)));
				logger.info("The check box is ticked for :"
						+ selUtils.speCharReplc(expTrms[itemCount]));
			}
		}
	}

	/**
	 * Select the check boxes of expected terminals.
	 **/
	public void vPrtNoForselecTrms(String[] expTrms, String partNo) {
		for (itemCount = 0; itemCount < expTrms.length; itemCount++) {
			xpath = selUtils.getPath("trm_selc_chkb_xpath").replace(signature,
					expTrms[itemCount]);
			if (selUtils.getObjectDirect(By.xpath(xpath)).isSelected()) {
				xpath = selUtils.getPath("trm_partno_col_val_xpath").replace(
						signature, expTrms[itemCount]);
				Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath))
						.getText().trim().equals(partNo), "Expected '" + partNo
						+ "' part number is not displayed.");
			} else {
				logger.error("'" + expTrms[itemCount]
						+ "' signature is not selected.");
				Assert.fail("'" + expTrms[itemCount]
						+ "' signature is not selected.");
			}
		}
		logger.info("Verified, selected signatures are having expected part number.");
	}

	/**Delete existed terminals which are not required.
	 * @param sign
	 * @param elementSignVal */
	public void delExtTerms(String sign[], List<String> elementSignVal) {
		flag = false;
		for (int cnti = 0; cnti < sign.length; cnti++) {
			if (elementSignVal.contains(sign[cnti])) {
				xpath = selUtils.getPath("trm_selc_chkb_xpath").replace(
						signature, sign[cnti]);
				if (!(selUtils.getObjectDirect(By.xpath(xpath)).isSelected())) {
					selUtils.getObjectDirect(By.xpath(xpath)).sendKeys("");
					selUtils.getObjectDirect(By.xpath(xpath)).click();
					flag = true;
				}
			}
		}
		if (flag) {
			selUtils.getObject("delete_link").click();
			selUtils.getObject("trm_del_cnfm_butt_id").click();
			waitMethods.waitForElementDisplayed("trm_succ_delmsg_cls_id");
			verifySpecifiedWinNotDisp("trm_succ_delmsg_cls_id");
		}
	}

	/** Delete all existed terminals. 
	 * @param elementSignVal
	 */
	public void delAllExtTerms(List<String> elementSignVal) {
		boolean flag = false;
		for (int cnti = 0; cnti < elementSignVal.size(); cnti++) {
			xpath = selUtils.getPath("trm_selc_chkb_xpath").replace(signature,
					elementSignVal.get(cnti));
			if (!(selUtils.getObjectDirect(By.xpath(xpath)).isSelected())) {
				selUtils.getObjectDirect(By.xpath(xpath)).click();
				flag = true;
			}
		}
		if (flag) {
			selUtils.clickOnWebElement("delete_link", delete);
			selUtils.clickOnWebElement("trm_del_cnfm_butt_id", confirm);
			waitMethods.waitForElementDisplayed("trm_succ_delmsg_cls_id");
			selUtils.clickOnWebElement("trm_succ_delmsg_cls_id", closeButton);
			logger.info(" Successfully deleted all existing terminals.");
		}
	}

	/** Verify,-A 'Terminals'specified buttons section with the specified
	 * buttons.
	 */
	public void vGrpButtsNotPresent(String buttsLocator, String[] termButts,
			String termButtsLbl) {
		StringBuffer webTermButtsStr = new StringBuffer(), termButtsStr = new StringBuffer();
		elements = selUtils.getObjects(buttsLocator);
		for (itemCount = 0; itemCount < elements.size(); itemCount++) {
			webTermButtsStr = webTermButtsStr.append(", ").append(
					elements.get(itemCount).getText());
			// webTermButtsStr = webTermButtsStr + ", " +
			// elements.get(itemCount).getText();
		}
		for (itemCount = 0; itemCount < termButts.length; itemCount++) {
			termButtsStr = termButtsStr.append(", ").append(
					termButts[itemCount]);
			// termButtsStr = termButtsStr + ", " + termButts[itemCount];
		}
		Assert.assertFalse(webTermButtsStr.equals(termButtsStr), "Expected '"
				+ termButtsLbl + "' buttons are displayed.");
		logger.info("Verified, " + termButtsLbl + " : '"
				+ termButtsStr.substring(2) + "' buttons are not displayed.");
	}

	/** Verify, -At least 20 terminals in the table.
	 * @param browser
	 */
	public void vMin20TrmsInTbl(String browser) {
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		Assert.assertTrue(
				selUtils.getLstItems(selUtils.getObjects("term_signscol_xpath"))
						.size() >= 20,
				"Terminals table is not displayed atlease 20 terminals.");
		logger.info("Verifid, terminals table is displayed atleast 20 terminals.");
	}

	/**The terminal with the Expected Signature, and name appeared in the table
	 * of the expected terminal page.
	 */
	public void vSignInTrmPage(String browser, String sign) {
		waitForTerminalPageLoaded();
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		elementSignVal = selUtils.getLstItems(selUtils
				.getObjects("term_signscol_xpath"));
		elementNameVal = selUtils.getLstItems(selUtils
				.getObjects("term_namescol_xpath"));
		Assert.assertTrue(elementSignVal.contains(sign),
				" Expected Signature is not present in table");
		Assert.assertTrue(elementNameVal.contains(sign),
				" Expected Name is not present in table");
		logger.info("Verified, expected Signature and Name are present in table.");
	}

	/**The terminal with the Signature 'name' appears in the table of the
	 * expected terminal page.
	 */
	public void vSceinEdtTrmPage(String browser, String sign, String locator) {
		elementNameVal = selUtils.getLstItems(selUtils.getObjects(locator));
		Assert.assertTrue(elementNameVal.contains(sign),
				" Expected Value is not present in table");
		logger.info(" Verified that Expected Value is present in table");
	}

	/**Click on 'Close' button.Verify The modal window is closed. and The
	 *terminal with the Signatures appears in the table of the 'TMS >>Terminals 
	 *>> Auto_Test page.*/
	
	public void vSignsInTrmPage(String browser, String sign[]) {
		verifySpecifiedWinNotDisp("trms_succ_cls_butt_id");
		waitForTerminalPageLoaded();
		selUtils.verifyBreadCrumb(brdCrbTerms);
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		elementSignVal = selUtils.getLstItems(selUtils
				.getObjects("term_signscol_xpath"));
		elementNameVal = selUtils.getLstItems(selUtils
				.getObjects("term_namescol_xpath"));
		waitForTerminalPageLoaded();
		for (cnti = 0; cnti < sign.length; cnti++) {
			Assert.assertTrue(elementSignVal.contains(sign[cnti]),
					" Expected Signature is not present in table");
			Assert.assertTrue(elementNameVal.contains(sign[cnti]),
					" Expected Name is not present in table");
		}
		logger.info(" Verified that Expected Signature and Name are present in table");
	}

	/**The terminal with the Name appears in the table of the 'TMS >> Terminals
	 * >> Auto_Test page */
	public void vnameInTrmPage(String browser, String name) {
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		final List<WebElement> elementName = selUtils
				.getObjects("term_namescol_xpath");
		final List<String> elementNameVal = new ArrayList<String>();
		for (cnti = 0; cnti < elementName.size(); cnti++) {
			elementNameVal.add(elementName.get(cnti).getText());
		}
		Assert.assertTrue(elementNameVal.contains(name),
				selUtils.speCharReplc(name) + " Name is not present in table");
		logger.info("Verified, '" + selUtils.speCharReplc(name)
				+ "' name is present in table");
	}

	/**Click on the 'Close' button.Verify The modal window is closed. and The
	 * terminal with the Signatures, names and PartNumbers appears in the table
	 * of the 'TMS >> Terminals >> Auto_Test page. */
	public void vTrmsSignNmPrtNo(String browser, String sign[], String srNo,
			String partno) {
		verifySpecifiedWinNotDisp("trms_succ_cls_butt_id");
		waitForTerminalPageLoaded();
		selUtils.verifyBreadCrumb(brdCrbTerms);
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		for (itemCount = 0; itemCount < sign.length; itemCount++) {
			Assert.assertTrue(
					selUtils.getLstItems(
							selUtils.getObjects("trm_sign_colvals_css"))
							.contains(sign[itemCount]),
					" Expected Signature is not present in table");
			Assert.assertTrue(
					selUtils.getLstItems(
							selUtils.getObjects("trm_sr_no_colvals_css"))
							.contains(srNo),
					" Expected Serial no is not present in table");
			Assert.assertTrue(
					selUtils.getLstItems(
							selUtils.getObjects("trm_pr_no_colvals_css"))
							.contains(partno),
					" Expected Part no is not present in table");
			Assert.assertTrue(
					selUtils.getLstItems(
							selUtils.getObjects("trm_nm_colvals_css"))
							.contains(sign[itemCount]),
					" Expected Name is not present in table");
		}
		logger.info("Verified, expected Signature, Name, and Serial no, Part Nos are present in table");
	}

	/** Verify, no terminal is added.
	 * @param browser
	 * @param trmSign
	 */
	public void vNotCrtedNwTrm(String browser, String trmSign) {
		selUtils.verifyBreadCrumb(brdCrbTerms);
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		xpath = selUtils.getPath("trm_sign_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertFalse(selUtils.isElementPresentxpath(xpath), "Expected '"
				+ trmSign + "' signature is displayed.");
		logger.info("Verified, expected '" + trmSign
				+ "' signature is not displayed.");
	}

	/** Go to edit page of expected terminal
	 */
	public void vEditPgOfExpTrm(String editLoc, String sign) {
		path = selUtils.getPath(editLoc).replace(signature, sign);
		webElement = selUtils.getObject(editLoc, path);
		waitMethods.waitForElement(webElement);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				webElement);
		logger.info("Clicked on '" + sign + "' edit icon.");
		selUtils.verifyBreadCrumb(breadCrumEditTerm + " » " + sign);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(selUtils.getCommonPath("btn_spinner_xpath"))));
	}

	/** Verify all fields of edit terminal page.
	 * @param est
	 * @param tech
	 * @param serNo
	 * @param sign
	 * @param trmProdName
	 * @param partNo
	 * @param name
	 */
	public void edTrmDetls(String est, String tech, String serNo, String sign,
			String trmProdName, String partNo, String name) {
		Assert.assertTrue(selUtils.getObject("trm_edit_pg_est_val_css")
				.getText().equals(est), " Expected value is not displayed ");
		Assert.assertTrue(selUtils.getObject("trm_edt_tech_id").getText()
				.equals(tech), " Expected value is not displayed ");
		Assert.assertTrue(selUtils.getObject("trm_edt_serno_id").getText()
				.equals(serNo), " Expected value is not displayed ");
		Assert.assertTrue(selUtils.getObject("trm_edit_sign_id").getText()
				.equals(sign), " Expected value is not displayed ");
		Assert.assertTrue(selUtils.getObject("trm_edt_prod_id").getText()
				.equals(trmProdName), " Expected value is not displayed ");
		Assert.assertTrue(selUtils.getObject("trm_edt_partno_id").getText()
				.equals(partNo), " Expected value is not displayed ");
		Assert.assertTrue(selUtils.getObject("trm_edt_name_id").getText()
				.equals(name), " Expected value is not displayed ");
		logger.info(" Verified that Expected value is displayed for all fields on edit terminal page.");
	}

	/**Verify terminal action sections along with buttons.
	 */
	public void trmActSects() {
		elements = selUtils.getObjects("buttons_group_css");
		Assert.assertTrue(elements.get(0).getText().contains(trmAct),
				" Expected value is not displayed ");
		selUtils.verifyElementDisp("modify_link", modify);
		selUtils.verifyElementDisp("move_link", actionMove);
		selUtils.verifyElementDisp("delete_link", delete);
		Assert.assertTrue(elements.get(1).getText().contains(trmTask),
				" Expected value is not displayed ");
		Assert.assertTrue(selUtils.getObject("edit_trm_add_sce_xpath")
				.isDisplayed(), " Expected link is not displayed ");
		Assert.assertTrue(selUtils.getObject("add_pak_xpath").isDisplayed(),
				" Expected link is not displayed ");
		Assert.assertTrue(selUtils.getObject("add_key_xpath").isDisplayed(),
				" Expected link is not displayed ");
		Assert.assertTrue(
				selUtils.getObject("add_sw_conf_xpath").isDisplayed(),
				" Expected link is not displayed ");
		Assert.assertTrue(
				elements.get(2).getText().contains(Terminals.edtTrmCfgButLbl),
				" Expected value is not displayed ");
		// Assert.assertTrue(selUtils.getObject("param_css").isDisplayed(),
		// " Expected link is not displayed ");
		Assert.assertTrue(selUtils.getObject("cl_schdlng_link").isDisplayed(),
				" Expected link is not displayed ");
		logger.info(" Verified that 'Add an estate',  'Add scenario', 'Add package', 'Add key', 'Add SW config.', 'Parameters' and 'Call scheduling' buttons"
				+ " are present on Terminal edit page");
	}

	/**Verify focused and not focused tabs trm_edit_tab_header_xpath 
	 * @param edtTermTabs
	 * @param edtLocator
	 */
	public void edTrmFoc(String[] edtTermTabs, String edtLocator) {
		final List<WebElement> elements = selUtils.getObjects(edtLocator);
		for (int cnti = 0; cnti < elements.size(); cnti++) {
			if (edtTermTabs[cnti].contains(elements.get(cnti).getText())) {
				if (elements.get(cnti).getText().equals(edtTermTabs[0])) {
					Assert.assertTrue(elements.get(0).getAttribute("class")
							.contains("tabs-selected"),
							" Expected tab is not in focus " + elements.get(0));
				} else {
					Assert.assertFalse(elements.get(cnti).getAttribute("class")
							.contains("tabs-selected"),
							" Expected tab is in focus " + elements.get(cnti));
				}
			} else {
				Assert.fail(" Expected tabs are not displayed. "
						+ elements.get(cnti).getText());
			}
		}
		logger.info(" Verified that tabs are focused and not focused as expectation");
	}

	/**Verify, -An 'Estate' field with the expected value, -A 'Technology' field
	 * with the expected value, -A 'Serial Number' field with the expected
	 * value, -A 'Signature' field with the expected value,.... **/
	public void vEditTrmDtls(String expEst, String expTchngy, String expSrNo,
			String expSign, String expPrd, String expPrNo, String expNm) {
		selUtils.vExpValPresent("edt_trm_est_xpath", expEst);
		selUtils.vExpValPresent("edt_trm_tchngy_xpath", expTchngy);
		selUtils.vExpValPresent("edt_trm_srno_xpath", expSrNo);
		selUtils.vExpValPresent("edt_trm_sign_xpath", expSign);
		selUtils.vExpValPresent("edt_trm_prd_xpath", expPrd);
		selUtils.vExpValPresent("edt_trm_prno_xpath", expPrNo);
		selUtils.vExpValPresent("edt_trm_nm_xpath", expNm);
	}

	/**Verify, -A 'Back To List' button, -An 'Estate' field with the expected
	 *value, -A 'Technology' field with the expected value, -A 'Serial Number'
	 * field with the expected value,....... **/
	public void vEdtTrmPgInfo() {
		vEditTrmDtls(Estates.autoEst21, techngyLstItems[2], no21, sign21,
				prdict220Ad250, no21, auto21);
		vGrpButts("edt_trm_act_butts_xpath", edtTrmActs, edtTrmActButtsLbl);
		vGrpButts("edt_trm_tsk_butts_xpath", edtTskButts, edtTskButtsLbl);
		vExpButtInGrp("edt_trm_cnfg_butts_xpath", CallScheduling.CALLSCHD,
				edtTrmCfgButLbl);
		vGrpButts("edt_trm_tabs_css", edtTrmTabs, "edit terminal tabs");
	}

	/**Verify, 'Status' column : it contains a 'Yellow ball' with the value
	 * 'Being downloaded' in the in-progress tab.
	 * @param name
	 */
	
	public void vInPrgceStatusColInEdtPg(String name, String locator) {
		xpath = selUtils.getPath(locator).replace(nameLbl, name);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath))
				.isDisplayed(), "'Status' is not displyed");
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath))
				.getAttribute("src").contains("bullet_orange.png"),
				"'Status' ball is not displyed in 'Yellow ball'.");
		logger.info("Verified, expected 'Being downloaded' status is displayed with 'Yellow ball'.");
	}

	/**Click on 'Modify' button of 'Terminal actions' buttons section.Set 'Name' 
	 *field to expected value.Click on 'Confirm' button.The modal window 
	 *displays expected message and only a 'Close' button is available.Click on
	 *the 'Close' button. Verify the modal window is closed. **/
	public void vMdfFunc(String sign, String name) {
		verifyExpWinDisp("modify_link", modify);
		selUtils.populateInputBox("trm_edit_mod_name_id", name);
		vValFrmInputBox("trm_edit_mod_name_id", name, nameLbl);
		confrmPopupActionMsg("trm_edit_mod_cnfrm_but_id",
				"trm_edit_mod_proc_msg_id", "trm_edit_mod_succ_msg_id",
				updTerm, updTrmSuccMsg);
		verifyOnlyCloseButtInSuccWin("trm_edit_mod_succ_butts_css");
		verifySpecifiedWinNotDisp("trm_edit_mod_succ_cls_but_id");
		selUtils.vExpValPresent("trm_edit_sign_id", sign);
		selUtils.vExpValPresent("trm_edt_name_id", name);
	}

	/**Click on 'delete' button of the 'Terminals actions' buttons section.
	 *Click on 'Confirm' button of the modal window.The modal window displays 
	 *expected message and only 'Close' button is available.Click on 'Close.**/
	public void vTrmDelPrceInEdtPg(String browser, String sign) {
		verifyExpWinDisp("delete_link", delete);
		confrmPopupActionMsg("trm_edit_del_cnfrm_button_id",
				"trm_edit_del_proc_msg_id", "trm_edit_del_succ_msg_id",
				edtDelTrmProcMsg, edtTrmDelSuccMsg);
		verifyOnlyCloseButtInSuccWin("trm_edit_del_succ_butts_css");
		verifySpecifiedWinNotDisp("trm_edit_del_succ_cls_butt_id");
		waitNSec(1);
		selUtils.verifyBreadCrumb(brdCrbTerms);
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		objScrollDwn("trms_tbl_id");
		vExpTrmNotDisp(getSignColVals(), sign);
	}

	/**Click on 'delete' button of 'Terminals actions' buttons section.Click on
	 *'Confirm'button of the modal window.The modal window displays expected
	 *message and only a 'Close' button is available.Click on 'Close' button.**/
	public void vTrmDelPrceInEdtPg(String browser) {
		verifyExpWinDisp("delete_link", delete);
		confrmPopupActionMsg("trm_edit_del_cnfrm_button_id",
				"trm_edit_del_proc_msg_id", "trm_edit_del_succ_msg_id",
				edtDelTrmProcMsg, edtTrmDelSuccMsg);
		verifySpecifiedWinNotDisp("trm_edit_del_succ_cls_butt_id");
		waitNSec(1);
		selUtils.verifyBreadCrumb(brdCrbTerms);
	}

	/**Click on link 'Auto_Estate_2_1' of the 'Estate' field at the top of page.
	 *Verify,'TMS >> Edit estate >> Auto_Estate_2_1' page is displayed.Click on 
	 *the 'Back To Terminal' button. Verify, the expected page is displayed. **/
	public void vEdtTrmEstsFunc(String browser, String expJob, String sign) {
		vEdtTrmEdtEstsPg(browser, Estates.autoEst21);
		vals.clear();
		waitMethods.waitForElementEnable("jobstab_desc_list_css");
		vals = selUtils.getLstItems(selUtils
				.getObjects("jobstab_desc_list_css"));
		Assert.assertFalse(vals.contains(expJob), "Expected job is displayed.");
		logger.info("Verified, no job is displayed with the '" + expJob
				+ "' description in the jobs table");
		clkBackToTrm(sign);
	}

	/**Click on link 'Auto_Estate_2_1' of the 'Estate' field at the top of page. 
	 *Verify 'TMS >> Edit estate >> Auto_Estate_2_1' page is displayed.Click on 
	 *'Back To Terminal' button. **/
	public void vEdtTrmEstsFunc(String browser, String[] expJobs, String sign) {
		vEdtTrmEdtEstsPg(browser, Estates.autoEst21);
		vals.clear();
		waitMethods.waitForElementEnable("jobstab_desc_list_css");
		for (int cnti = 0; cnti < expJobs.length; cnti++) {
			Assert.assertFalse(vals.contains(expJobs[cnti]),
					"Expected job is displayed.");
			logger.info("Verified, no job is displayed with the '"
					+ expJobs[cnti] + "' description in the jobs table");
			vals = selUtils.getLstItems(selUtils
					.getObjects("jobstab_desc_list_css"));
		}
		clkBackToTrm(sign);
	}

	/** Click on the link of the 'Estate' field at the top of the page. Verify,
	 * the expected edit estates page is displayed. **/
	public void vEdtTrmEdtEstsPg(String browser, String expEst) {
		webElement = selUtils.getObject("trm_edit_pg_est_val_css");
		Assert.assertEquals(webElement.getText(), expEst, expEst
				+ " estate is not displayed in Estate field.");
		scrollUp();
		webElement.click();
		selUtils.verifyBreadCrumb(Estates.breadCrumbEdit + expEst);
		//selectMaxSizeinTable("job_show_res_id", browser);
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
	}

	/** Click on the 'Back To Terminal' button. Verify, the expected page is
	 * displayed.
	 * @param sign */
	public void clkBackToTrm(String sign) {
		selUtils.clickOnWebElement("back_trm_link", backToTrm);
		selUtils.verifyBreadCrumb(breadCrumEditTerm + " » " + sign);
	}

	/**Click on 'Add terminals' button of 'Terminals actions' buttons section. 
	 *Verify 'Add terminals' modal window is displayed containing expected and 
	 *a 'Close' button **/
	public void vAddTrmsModalWindowInfo() {
		verifyExpWinDisp("add_trms_xpath", addTrms);
		selUtils.waitForTxtPresent("trms_est_lst_xpath", client);
		vDrpDnDefltSelcItem("trms_est_lst_xpath", client, Estates.estLbl);
		vDrpDnDefltSelcItem("trms_techngy_lst_xpath", unKnown, techngyLbl);
		vDrpDnDefltSelcItem("trms_model_lst_xpath", unKnown, model);
		selUtils.verifyElementDisp("trms_sr_no_xpath", srNumLbl);
		selUtils.verifyElementDisp("trms_part_no_xpath", partNumLbl);
		selUtils.verifyElementDisp("trms_thr_xpath", thrLbl);
		selUtils.verifyElementDisp("trms_cnfrm_butt_id", confirm);
		selUtils.verifyElementDisp("trms_cls_butt_id", closeButton);
	}

	/**Click on 'Add a terminal' button of 'Terminals actions' buttons section. 
	 *containing expected and a 'Close'button **/
	public void vAddATrmModalWindowInfo() {
		verifyExpWinDisp("add_a_trm_xpath", addATrm);
		selUtils.waitForTxtPresent("trm_est_lst_xpath", client);
		vDrpDnDefltSelcItem("trm_est_lst_xpath", client, Estates.estLbl);
		vDrpDnDefltSelcItem("trm_techngy_lst_xpath", unKnown, techngyLbl);
		vDrpDnDefltSelcItem("trm_model_lst_xpath", unKnown, model);
		selUtils.verifyElementDisp("trm_sr_no_xpath", srNumLbl);
		selUtils.verifyElementDisp("trm_part_no_xpath", partNumLbl);
		selUtils.verifyElementDisp("trm_sign_xpath", signLbl);
		selUtils.verifyElementDisp("trm_name_xpath", nameLbl);
		selUtils.vChkBxSelected("edit_nw_trm_chkb_xpath", editNwTrmChkBxLbl);
		selUtils.verifyElementDisp("add_a_trm_cnfm_butt_id", confirm);
		selUtils.verifyElementDisp("add_a_trm_cls_butt_id", closeButton);
	}

	/**The terminal with the expected Signature and Name, appears in the table
	 * of the 'TMS >> Terminals >> Auto_Test' page (you might need to look in
	 * all the pages of the table). **/
	public void vNwTrmSignAndNmVals(String browser, String trmSign, String name) {
		selUtils.verifyBreadCrumb(brdCrbTerms);
		waitForTerminalPageLoaded();
		selectMaxSizeinTable("trm_sel_max_res_css", browser);
		waitForTerminalPageLoaded();
		xpath = selUtils.getPath("trm_sign_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(trmSign),
				"Expected '" + selUtils.speCharReplc(trmSign)
						+ "' signature is not displayed.");
		xpath = selUtils.getPath("trm_name_col_val_xpath").replace(signature,
				trmSign);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.trim().equals(name),
				"Expected '" + selUtils.speCharReplc(name)
						+ "' name is not displayed.");
		logger.info("Verified, '" + selUtils.speCharReplc(trmSign)
				+ "' signature and, '" + selUtils.speCharReplc(name)
				+ "' name values of new terminal, are displayed as expected.");
	}

	/**Click on the 'Delete' button of the 'Terminals actions' buttons section.
	 * Verify, the 'Delete' modal window is displayed containing: -The message:
	 * 'Do you confirm the deletion of selected terminals?' -A 'Confirm' and a
	 * 'Close' button**/
	public void vDelModalWindowInfo(String msgLoc, String msg,
			String cnfmButtLoc, String clsButtLoc) {
		verifyExpWinDisp("delete_link", delete);
		Assert.assertTrue(selUtils.getObject(msgLoc).getText().equals(msg), "'"
				+ msg + "' message is not as expected.");
		logger.info("Verified, '" + msg + "' message is as expected.");
		selUtils.verifyElementDisp(cnfmButtLoc, confirm);
		selUtils.verifyElementDisp(clsButtLoc, closeButton);
	}

	/**Click on the number value of the 'Terminals' field. Verify, The expected
	 * page is displayed. The terminal with the expected Signature is present in
	 * the table of the page. **/
	public void moveTrmToEstPage(String browser, String objLoc, String brdScr,
			String selcMxSz, String signsLoc, String sign) {
		selUtils.getObject(objLoc).click();
		selUtils.verifyBreadCrumb(brdScr);
		selectMaxSizeinTable(selcMxSz, browser);
		waitForTerminalPageLoaded();
		Assert.assertTrue(selUtils.getListItems(signsLoc).contains(sign),
				" Expected Signature is not present in table");
		logger.info("Expected Signature is present in table");
	}

	/**Check that inside of table in 'Terminals' page,columns 'Actions','Part 
	 *Number','Serial Number','Product Label','Product','Technology', 'Name' 
	 *and 'Status Date' can be moved with mouse-click held over their column 
	 *title bar when mouse is moved and cursor is a quadruple sense arrow.**/
	
	public void vTrmColsSwapable(String objLoc) throws InterruptedException {
		WebElement col1, col2, col1AfterSwap, col2AfterSwap;
		List<WebElement> elementsAfterSwap;
		String col1Name, col2Name;
		waitMethods.waitForElementDisplayed(objLoc);
		elements = selUtils.getObjects(objLoc);
		action = new Actions(driver);
		for (itemCount = 1; itemCount < elements.size() - 3; itemCount++) {
			if (itemCount == 2) {
				continue;
			}
			col1 = selUtils.getObjects(objLoc).get(itemCount);
			col1Name = col1.getText();
			if (col1Name.equals(Jobs.jobsTabcolsHds[4])) {
				col2 = elements.get(itemCount + 2);
				col2Name = col2.getText();
				action.dragAndDrop(col1, col2).build().perform();
				elementsAfterSwap = selUtils.getObjects(objLoc);
				col1AfterSwap = elementsAfterSwap.get(itemCount + 1);
			} else {
				col2 = selUtils.getObjects(objLoc).get(itemCount + 1);
				col2Name = col2.getText();
				action.dragAndDrop(col1, col2).build().perform();
				elementsAfterSwap = selUtils.getObjects(objLoc);
				col1AfterSwap = elementsAfterSwap.get(itemCount);
			}
			if (col1AfterSwap.getText().equals("Part Number")) {
				selUtils.clkOnIcon("trm_restore_over_id");
				waitForTerminalPageLoaded();
			} else {
				col2AfterSwap = elementsAfterSwap.get(itemCount + 1);
				Assert.assertEquals(col1Name.trim(), col2AfterSwap.getText()
						.trim());
				Assert.assertEquals(col2Name.trim(), col1AfterSwap.getText()
						.trim());
				action.dragAndDrop(col1AfterSwap, col2AfterSwap).build()
						.perform();
			}
		}
		logger.info(" Verified, that Expected columns can swap places.");
	}

	/**Click on the Specified action button. Verify, the 'Information' modal
	 * window is displayed containing: -The message: 'No terminal selected' with
	 * an error icon, -A 'Close' button. Click on the 'Close' button. **/
	public void vInfoModWindFunc(String expButtLoc) {
		verifyExpWinDisp(expButtLoc, infoModWin);
		waitMethods.waitForElementDisplayed("trm_info_msg_css");
		vErrMsgAdIcon("trm_info_msg_css", "trm_info_err_icon_css", trmInfoMsg);
		selUtils.verifyElementDisp("trm_info_cls_butt_xpath", closeButton);
		verifySpecifiedWinNotDisp("trm_info_cls_butt_xpath");
	}

	/**Verify 'Add a Terminal' duplicate functionality. **/
	public void dupAddATrmFunc(String trmSign, String tchn, String srNo,
			String prNo) {
		/*Click on the 'Add a terminal' button of the 'Terminals actions'
		 * buttons section. Verify, the 'Add a terminal' modal window is
		 * displayed.*/
		verifyExpWinDisp("add_a_trm_xpath", addATrm);

		// Set the 'Signature' field to expected value. Verify, the field is set
		// to expected value.
		addAdVerifyVal("trm_sign_id", trmSign, signLbl);

		/*Click on the Drop-down list icon of the 'Technology' field and choose
		 * expected value. Verify, the field is set to expected value.*/
		selUtils.vselectedItemInDrpDn("trm_techngy_lst_id", tchn);

		// Set the 'Serial Number' field to expected value. Verify, the field is
		// set to expected value.
		addAdVerifyVal("trm_sr_no_id", srNo, srNumLbl);

		// Set the 'Part Number' field to expected value. Verify, the field is
		// set to expected value.
		addAdVerifyVal("trm_part_no_lst_id", prNo, partNumLbl);

		/*Click on 'Confirm' button.Verify, the modal window displays expected
		 *message and a 'Back'and a 'Close' button are available. */
		confrmNegFunc("add_a_trm_cnfm_butt_id", "trm_procc_msg_id",
				"trm_succ_msg_id", trmProcMsg, theTrm + trmSign + alrExt);
		selUtils.verifyElementDisp("trm_succ_cls_butt_id", closeButton);
		selUtils.verifyElementDisp("trm_back_id", "Back");
	}

	/** Add values in 'Add a Terminal' popup window.**/
	/*
	 * public void addValsInAddATrmPopup(String browser, String trmSign, String
	 * tchn, String srNo, String prNo){ //Verify, expected terminal is not
	 * exist. selectMaxSizeinTable("trm_sel_max_res_css", browser);
	 * delExpTrm(getSignColVals(), trmSign); wait1Sec();
	 * addValsInAddATrm(trmSign, tchn, srNo, prNo); }
	 */
	public void deleteExisTerm(String trmSign, String browser) {
		selectMaxSizeinTable("job_show_res_id", browser);
		//waitForTerminalPageLoaded();
		delExpTrm(getSignColVals(), trmSign);
		//waitNSec(1);
	}

	/**
	 * Add values in 'Add a Terminal' popup window.
	 **/
	public void addValsInAddATrm(String trmSign, String tchn, String srNo,
			String prNo) {
		/*Click on the 'Add a terminal' button of the 'Terminals actions'
		 * buttons section. Verify, the 'Add a terminal' modal window is
		 * displayed. */
		verifyExpWinDisp("add_a_trm_xpath", addATrm);
		selUtils.waitForTxtPresent("trm_est_lst_id", client);
		vDrpDnDefltSelcItem("trm_est_lst_id", client, Estates.estLbl);

		/*Click on the Drop-down list icon of the 'Technology' field and choose
		 * expected value. Verify, the field is set to expected value. */
		selUtils.vselectedItemInDrpDn("trm_techngy_lst_id", tchn);

		// Set the 'Serial Number' field to expected value. Verify, the field is
		// set to expected value.
		addAdVerifyVal("trm_sr_no_id", srNo, srNumLbl);

		// Set the 'Part Number' field to expected value. Verify, the field is
		// set to expected value.
		addAdVerifyVal("trm_part_no_lst_id", prNo, partNumLbl);

		/*Check that the 'Signature' and 'Name' fields have been automatically
		 *filled with Serial Number and Part Number values.Verify'Signature' 
		 *and 'Name' fields are set to same value.*/
		
		selUtils.getObject("trm_sign_id").sendKeys(Keys.TAB);
		vValFrmInputBox("trm_sign_id", trmSign, signLbl);
		vValFrmInputBox("trm_name_id", trmSign, nameLbl);
	}

	/**Add values in 'Add a Terminal' popup window.**/
	/*
	 * public void addValsInAddATrmPopup(String browser, String rootEst, String
	 * trmSign, String tchn, String srNo, String prNo){ //Verify, expected
	 * terminal is not exist. // vFindForm("findform_id");
	 * selectMaxSizeinTable("trm_sel_max_res_css", browser);
	 * delExpTrm(getSignColVals(), trmSign);
	 * 
	 * addValsInAddATrm(rootEst, trmSign, tchn, srNo, prNo); }
	 */
	/** Add values in 'Add a Terminal' popup window.**/
	public void addValsInAddATrm(String rootEst, String trmSign, String tchn,
			String srNo, String prNo) {
		/*Click on the 'Add a terminal' button of the 'Terminals actions'
		 * buttons section. Verify, the 'Add a terminal' modal window is
		 * displayed */
		verifyExpWinDisp("add_a_trm_xpath", addATrm);
		vDrpDnDefltSelcItem("trm_est_lst_id", client, Estates.estLbl);

		// Click on the drop-down list of the 'Estate' field and choose expected
		// value. Verify,the field is set to same value.
		selUtils.clkToSelecItm("trm_est_lst_itms_css", rootEst);
		vDrpDnDefltSelcItem("trm_est_lst_id", rootEst, Estates.estLbl);

		/*Click on the Drop-down list icon of the 'Technology' field and choose
		 * expected value. Verify, the field is set to expected value.*/
		selUtils.vselectedItemInDrpDn("trm_techngy_lst_id", tchn);

		// Set the 'Serial Number' field to expected value. Verify, the field is
		// set to expected value.
		addAdVerifyVal("trm_sr_no_id", srNo, srNumLbl);

		// Set the 'Part Number' field to expected value. Verify, the field is
		// set to expected value.
		addAdVerifyVal("trm_part_no_lst_id", prNo, partNumLbl);

		/* Check that the 'Signature' and 'Name' fields have been automatically
		 * filled with the Serial Number and Part Number values. Verify, the
		 * 'Signature' and 'Name' fields are set to same value.*/
		selUtils.getObject("trm_sign_id").sendKeys(Keys.TAB);
		vValFrmInputBox("trm_sign_id", trmSign, signLbl);
		vValFrmInputBox("trm_name_id", trmSign, nameLbl);
	}

	/**Tick off 'Edit the new terminal after the creation' check box.Verify
	 *check box is ticked off. Click on the 'Confirm' button.Verify modal window
	 *displays expected message and only a 'Close' button is available.**/
	public void vDSelctChkBxAdClsPopup() {
		// Tick off the 'Edit the new terminal after the creation' check box.
		// Verify, the check box is ticked off.
		selUtils.deSelecChkBx("edit_nw_trm_chkb_xpath");
		selUtils.vDeSelecChkBx("edit_nw_trm_chkb_xpath", editNwTrmChkBxLbl);

		/*Click on 'Confirm' button. Verify, the modal window displays expected
		 *message and only a 'Close' button is available.*/
		confrmPopupActionMsg("add_a_trm_cnfm_butt_id", "trm_procc_msg_id",
				"trm_succ_msg_id", "Creating of the terminal...", trmSuccMsg);
		// trmProcMsg
		verifyOnlyCloseButtInSuccWin("trm_succ_cls_butt_id");

		// Click on the 'Close' button. Verify, The modal window is closed.
		verifySpecifiedWinNotDisp("trm_succ_cls_butt_id");
	}

	/**Verify, -A 'Find' button. -A 'Terminals actions' buttons section with
	 *only the button 'Add a terminal', there are no 'Move', 'Delete' nor 'Add
	 *terminals' buttons. -A table with the columns: 'Select',etc....... **/
	public void vUserTermPageInfo() {
		selUtils.verifyElementDisp("find_but_xpath", findButt);
		Assert.assertTrue(selUtils.getObject("term_act_butts_xpath")
				.isDisplayed(), "'" + termActions[2]
				+ "' button is not displayed.");
		logger.info("Verified, " + termActions[2]
				+ "' button is displayed under '" + termActButtsLbl
				+ "' section.");
		vGrpButtsNotPresent("term_act_butts_xpath", termActs, termActButtsLbl);
		vExpColsInTab(selUtils.getTabColHds("trm_cols_headings_css"),
				trmColsHdrs);
	}

	/**Click on 'Find' button at top of the page.Verify 'Find terminals' 
	 *drop-down window is displayed containing: -A 'Find terminals'
	 * section,etc....... **/
	public void vSrchTrmPageInfo() {
		selUtils.clickOnWebElement("find_but_xpath", findButt);
		selUtils.vExpValPresent("find_trms_lbl_xpath", searchTrmsLbl);
		vDrpDnDefltSelcItem("srch_trm_est_id", client, Estates.estLbl);
		selUtils.verifyElementDisp("srch_trm_sr_no_id", srNumLbl);
		selUtils.verifyElementDisp("srch_trm_pr_no_id", partNumLbl);
		selUtils.verifyElementDisp("srch_trm_sign_id", signLbl);
		vDrpDnDefltSelcItem("srch_trm_prd_lbl_id", all, model);
		vDrpDnDefltSelcItem("srch_trm_pkg_id", all, Packages.pkgLbl);
		vDrpDnDefltSelcItem("srch_trm_key_lbl_id", all, Key.key);
		selUtils.verifyElementDisp("srch_trm_mrch_name_css",
				Merchants.mrchNmLbl);
		selUtils.verifyElementDisp("srch_trm_mrch_num_css",
				Merchants.mrchNumLbl);
		selUtils.verifyElementDisp("srch_link", srch);
		selUtils.verifyElementDisp("adv_srch_link", advSrch);
		selUtils.verifyElementDisp("reset_link", reset);
	}

	/** vTermHardStatPage it verifies the add new feature in terminal hardware
	 * statistics page.
	 * @param elements
	 * @param size
	 */
	public void vTermHardStatPage(int size) {
		cnti = size - 1;
		elements = getTableHeadCursVals("adv_hdr_col_val_xpath",
				"adv_hdr_col_xpath", "INDEX", hardStatHead[0]);
		waitMethods.waitForElement(elements.get(0));
		Assert.assertTrue(elements.get(cnti).getTagName().contains(selt)
				&& !(new Select(elements.get(cnti)).isMultiple()));
		elements = getTableHeadCursVals("adv_hdr_col_val_xpath",
				"adv_hdr_col_xpath", "INDEX", hardStatHead[1]);
		Assert.assertTrue(elements.get(cnti).getTagName().contains(selt)
				&& !(new Select(elements.get(cnti)).isMultiple()));
		elements = getTableHeadCursVals("adv_hdr_col_val_xpath",
				"adv_hdr_col_xpath", "INDEX", hardStatHead[2]);
		Assert.assertTrue(elements.get(cnti).getTagName().contains(input));
		xpath = selUtils.getPath("adv_hdr_col_val_xpath").replace("INDEX", "4")
				+ selUtils.getPath("adv_hard_stat_del_xpath");
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath))
				.getAttribute("alt").contains(delete));
		Assert.assertEquals(elements.size(), size);
		logger.info("The number of row's in table is " + size);
		logger.info("varified that the table header name and column type in as expected");
	}

	/**Click on 'Add terminals' button of the 'Terminals actions' buttons.The 
	 *'Estate' field is set to given client.Select and set the 'Technology', 
	 *'Product','Serial Number','Through'and 'Part Number'.**/
	public void addATermWithAllData(String client, String tech,
			String prodItem, String srNo, String prNo, String sign, String name) {
		verifyExpWinDisp("add_a_trm_xpath", addATrm);

		selUtils.waitForTxtPresent("trm_est_lst_id", client);
		vDrpDnDefltSelcItem("trm_est_lst_id", client, Estates.estLbl);

		selUtils.vselectedItemInDrpDn("trm_techngy_lst_id", tech);
		selUtils.vselectedItemInDrpDn("trm_prod_lst_id", prodItem);

		// Set the 'Serial Number' field to expected value. Verify, the field is
		// set to expected value.
		addAdVerifyVal("trm_sr_no_id", srNo, srNumLbl);

		// Set the 'Part Number' field to expected value. Verify, the field is
		// set to expected value.
		addAdVerifyVal("trm_part_no_lst_id", prNo, partNumLbl);

		// Set the 'Signature' field to expected value. Verify, the field is set
		// to expected value.
		selUtils.getObject("trm_sign_id").sendKeys(
				Keys.CONTROL + "a" + Keys.DELETE);
		addAdVerifyVal("trm_sign_id", sign, signature);

		// Set the 'Name' field to expected value. Verify, the field is set to
		// expected value.
		selUtils.getObject("trm_name_id").sendKeys(
				Keys.CONTROL + "a" + Keys.DELETE);
		addAdVerifyVal("trm_name_id", name, "Name");

	}

	/**
	 * Click on the 'CallHistory' tab of the edit 'Terminals' Verify the column
	 * values of the tab
	 **/
	public void vCallHistoryTab(String prNo, String srNo, String model) {
		selUtils.getObject("call_history_link").click();
		logger.info("Clicked on '" + edtTrmTabs[5] + "' tab.");
		selUtils.vExpTabInFocus("call_history_tab_xpath");
		selUtils.verifyExpIconDisplayed(
				"trm_edit_callhistory_success_icon_xpath", success);

		vCrDateFrmt(selUtils.getObject("trm_edit_callhistory_startdate_xpath"));
		vCrDateFrmt(selUtils.getObject("trm_edit_callhistory_enddate_xpath"));

		selUtils.vExpValPresent("trm_edit_callhistory_txtdata_xpath", nameLbl,
				prNo, partNumLbl);
		selUtils.vExpValPresent("trm_edit_callhistory_txtdata_xpath", nameLbl,
				srNo, srNumLbl);
		selUtils.vExpValPresent("trm_edit_callhistory_txtdata_xpath", nameLbl,
				model, model);

		selUtils.vExpIcon("trm_edit_callhistory_actdata_xpath", configuration,
				configuration);
		selUtils.vExpIcon("trm_edit_callhistory_actdata_xpath", summary,
				summary);
		selUtils.vExpIcon("trm_edit_callhistory_actdata_xpath", execommand,
				execommand);
		selUtils.vExpIcon("trm_edit_callhistory_actdata_xpath", logs, logs);
	}

}