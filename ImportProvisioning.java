package com.ingenico.tam.objects;

/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/ImportProvisioning.java $
 $Id: ImportProvisioning.java 14234 2015-07-20 11:46:54Z rjadhav $
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import com.ingenico.base.TestBase;

/**
 * ImportProvisioning Class - Methods related to ImportProvisioning module
 */
public class ImportProvisioning extends TestBase {
	public final static String IMPPROBRDCRM = "TMS » Import/Provisioning » Auto_Test",
			IMPACTIONS = "Import actions",
			IMPTRMS = "Import terminals",
			IMPEVE = "Import events",
			IMPPKGS = "Import Software packages",
			IMPTSKS = "Import tasks",
			IMPPRV = "Import/Provisioning",
			IMPSWDEC = "Import software descriptions",
			BTCHMODE = "Batch Mode",
			EXEDATE = "Execution Date",
			FILEUPLDERR = "- File extension support only (.csv)",
			IMPRTSW = "Import Software packages",
			IMPRTTASK = "Import tasks",
			IMPREASON1 = "Package technology is not supported",
			TASKTOIMP = "Tasks to import (.csv)",
			IMPREASON2 = "Package type is not supported",
			IMPREASON3 = "ERROR: (In field [type] less-than (<) and greater-than (>) symbols are not allowed.)",
			IMPREASON4 = "ERROR: (In field [technology] less-than (<) and greater-than (>) symbols are not allowed.)",
			IMPREASON5 = "Type contains unauthorized characters ( \\\"<>% )",
			IMPREASON6 = "Technology contains unauthorized characters ( \\\"<>% )",
			IMPREASON7 = "Could not find file containing the package with name 'moneov3710.zip%'",
			IMPREASON8 = "Package name contains unauthorized characters ( \\\"<>% )",
			IMPREASON9 = "Version contains unauthorized characters",
			SWPKGTOIMP = "Software packages to import (.csv)",
			FILETOIMPT = "File to import (.zip)",
			TERMTOIMPT = "Terminals to import (.csv)",
			TERMIMPFINDERR = "Terminal not found 'WrongSignature1'",
			IMPRTSVALS = "WrongSignature1;WrongSignature2",
			FNDIMPRTS = "Find imports",
			IMPPROVIEWBRDCRM = "TMS » Import/Provisioning » View Request",
			TERMALRDYINPROG = "Terminals having the same tasks are already in progress",
			BACHIMPSUCCMSG1 = "Batch created with success.",
			IMPTERMMAND = "Terminals to import are mandatory";

	public static String[] repActsButts = { "Launch", "Subscribe" },
			impProvActs = { IMPTRMS, IMPPKGS, IMPTSKS,IMPSWDEC },
			impProvActs2 = { IMPTRMS, IMPPKGS, IMPTSKS },btchModeButts = {
					immediately, deferred }, impProvColHds = { view, userName,
					type, fileNm, sts, crtnDate, startDte, endDte, actions },
			acts = { "Import (Create, Update, Move)", actionMove },
			impTrmXlsCols = { signLbl, Terminals.partNumLbl,
					Terminals.srNumLbl, Terminals.prodLbl,
					Terminals.techngyLbl, nameLbl }, impTrmTaskXlsCols = {
					signLbl, "Package name", "Version", "Priority",
					"Permanent task" }, impTrmPakXlsCols = { "Type",
					"Package name", "Version", "Technology", "File name" },
			impTermSpecChar = { "1100000011000000\\", "1100000011000000\"",
					"1100000011000000<", "1100000011000000>",
					"1100000011000000%" };

	public static String[][] pckImpVwTblVals = {
			{ Packages.pkgLbl,
					"APP;Moneo_IMP_1208_1;3.7.10;WRONGTECHNO;moneov3710.zip",
					failed, IMPREASON1 },
			{ Packages.pkgLbl,
					"WRONGTYPE;Moneo_IMP_1208_2;3.7.10;TELIUM;moneov3710.zip",
					failed, IMPREASON2 },
			{ Packages.pkgLbl,
					"<APP;Moneo_IMP_1208_3;3.7.10;TELIUM;moneov3710.zip",
					failed, IMPREASON3 },
			{ Packages.pkgLbl,
					"APP;Moneo_IMP_1208_4;3.7.10;TELIUM<;moneov3710.zip",
					failed, IMPREASON4 },
			{ Packages.pkgLbl,
					">APP;Moneo_IMP_1208_5;3.7.10;TELIUM;moneov3710.zip",
					failed, IMPREASON3 },
			{ Packages.pkgLbl,
					"APP;Moneo_IMP_1208_6;3.7.10;TELIUM>;moneov3710.zip",
					failed, IMPREASON4 },
			{ Packages.pkgLbl,
					"\\APP;Moneo_IMP_1208_7;3.7.10;TELIUM;moneov3710.zip",
					failed, IMPREASON5 },
			{ Packages.pkgLbl,
					"APP;Moneo_IMP_1208_8;3.7.10;TELIUM\\;moneov3710.zip",
					failed, IMPREASON6 },
			{ Packages.pkgLbl,
					"%APP;Moneo_IMP_1208_9;3.7.10;TELIUM;moneov3710.zip",
					failed, IMPREASON5 },
			{ Packages.pkgLbl,
					"APP;Moneo_IMP_1208_10;3.7.10;TELIUM%;moneov3710.zip",
					failed, IMPREASON6 },
			{ Packages.pkgLbl,
					"\"APP;Moneo_IMP_1208_11;3.7.10;TELIUM;moneov3710.zip",
					failed, IMPREASON5 },
			{ Packages.pkgLbl,
					"APP;Moneo_IMP_1208_12;3.7.10;TELIUM\";moneov3710.zip",
					failed, IMPREASON6 },
			{ Packages.pkgLbl,
					"APP;Moneo_IMP_1208_13;3.7.10;TELIUM;moneov3710.zip%",
					failed, IMPREASON7 },
			{ Packages.pkgLbl,
					"APP;Moneo_IMP_1208_14%;3.7.10;TELIUM;moneov3710.zip",
					failed, IMPREASON8 },
			{ Packages.pkgLbl,
					"APP;Moneo_IMP_1208_15;3.7.10%;TELIUM\";moneov3710.zip",
					failed, IMPREASON9 } },

			pckImpTerVals = {
					{
							Terminals.terminal,
							"IMPR_1104_1;111111111104;000000001104;I3010;U32;IMPR_1104_1",
							failed,
							"ERROR : Terminal having the same signature is already in progress" },
					{
							Terminals.terminal,
							"IMPR_1104_1;111111111104;000000001104;I3010;U32;IMPR_1104_1",
							CallScheduling.callStatuses[3], " " } },

			impTerVewReqst = {
					{
							Terminals.terminal,
							"IMPR_1104_1;111111111104;000000001104;I3010;U32;IMPR_1104_1",
							success, " " },
					{
							Terminals.terminal,
							"IMPR_1104_2;11111104;00001104;M40;TELIUM;IMPR_1104_2",
							success, " " },
					{
							Terminals.terminal,
							"IMPR_1104_3;11111104;00001104;M81;WINCE;IMPR_1104_3",
							success, " " } },

			impPacVewReqst = { { Packages.pkgLbl,
					"APP;Moneo_IMPR_1106;3.7.10;TELIUM;moneov3710.zip",
					success, " " } },

			sigTerMov = {
					{ Terminals.terminal, "WrongSignature1", failed,
							"Terminal not found 'WrongSignature1'" },
					{ Terminals.terminal, "WrongSignature2", failed,
							"Terminal not found 'WrongSignature2'" } },

			impSWdupliVals = {
					{
							Packages.pkgLbl,
							"APP;U32_IMPR_1211;1.0.1;U32;appES0B00_GAME_v1.0.1.zip",
							failed,
							"A package 'U32_IMPR_1211' containing the 'U32' technology is already downloaded" },
					{
							Packages.pkgLbl,
							"APP;U32_IMPR_1211;1.0.1;U32;appES0B00_GAME_v1.0.1.zip",
							CallScheduling.callStatuses[3], " " } },
			impSWMoreMax= {
					{
						Packages.pkgLbl,
						"APP;Moneo2;12345678901234567890123456;TELIUM;moneov3710.zip",
						failed,
						"The size of the field 'Version' must be lower than '25'" },
				   {
						Packages.pkgLbl,
						"APP;1234567890123456789012345678901234567890123456789012345678901;3.7.10;TELIUM;moneov3710.zip",
						failed, "The size of the field 'Package name' must be lower than '60'" } };
	

	public List<WebElement> webEleType, webEleRec, webEleSts, webEleReason;

	/**
	 * Method to initialize the XML files
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws FileNotFoundException, IOException {
		initialize();
	}

	/**Click on 'Confirm' button of modal window.Verify modal window displays 
	 *expected message:'Terminals deletion is running, please wait...'.Then it 
	 *displays the messages related to all terminals.Click on 'Close' button.
    **/
	public void imprtDelTerms(String termcolsDetls[]) {
		int eleCount = 0;
		selUtils.getObject("trm_del_cnfm_butt_id").click();
		logger.info(" Clicked on Delete Confirm button");
		waitMethods.waitForElementDisplayed("trm_succ_delmsg_cls_id");
		List<WebElement> elementDelMsg = selUtils
				.getObjects("term_delmsg_xpath");
		List<String> delEleMsg = new ArrayList<String>();
		for (eleCount = 0; eleCount < elementDelMsg.size(); eleCount++) {
			delEleMsg.add(elementDelMsg.get(eleCount).getText());
		}
		for (eleCount = 0; eleCount < elementDelMsg.size(); eleCount++) {
			Assert.assertTrue(delEleMsg
					.contains("- The terminal has been deleted ("
							+ termcolsDetls[eleCount] + ")"));
		}
		logger.info(" Verified that delete succsess message is displayed as expected.");
		delEleMsg.clear();
		verifySpecifiedWinNotDisp("trm_succ_delmsg_cls_id");
	}

	/**Verify, -A 'Find' button, -An 'Import actions' buttons section with the
	 * buttons: 'Import terminals', 'Import packages', 'Import tasks' -A table
	 * of imports with the columns: 'View', 'User name', 'Type',etc....**/
	
	public void vimpProvPageInfo() {
		selUtils.verifyElementDisp("find_but_xpath", findButt);
		vGrpButts("imp_act_butts_xpath", impProvActs, IMPACTIONS);
		vExpColsInTab(selUtils.getTabColHds("imp_col_hds_css"), impProvColHds);
	}

	/**Click on 'Import terminals' button of 'Import actions' buttons section.
	 *Verify, the 'Import terminals' modal window is displayed containing 
	 *expected fields and buttons. **/
	
	public void vImpTrmModalWindInfo() {
		verifyExpWinDisp("impr_term_xpath", IMPTRMS);
		path = selUtils.getPath("exp_trm_radio_butts_xpath").replace(nameLbl,
				BTCHMODE);
		elements = null;
		elements = selUtils.getObjects("exp_trm_radio_butts_xpath", path);
		vGrpButts(elements, btchModeButts, BTCHMODE);
		Assert.assertTrue(selUtils.getObject("immed_radio_butt_xpath")
				.getAttribute("checked").equals("true"), immediately
				+ " radio button is not selected.");
		logger.info("Verified, '" + immediately
				+ "' radio button is selected  by default.");

		// The 'Execution Date' field is grayed and not editable
		vExpFldClr("exe_date_id", greyClr);
		Assert.assertFalse(selUtils.getObject("exe_date_txt_box_id")
				.isEnabled(), "'" + EXEDATE + "' check box is editable.");
		logger.info("Verified, '" + EXEDATE
				+ "' check box is having gray color, and not editable.");
		selUtils.verifyElementDisp("impr_root_est_id", Estates.rootEstateLabel);

		path = selUtils.getPath("exp_trm_radio_butts_xpath").replace(nameLbl,
				actn);
		elements = null;
		elements = selUtils.getObjects("exp_trm_radio_butts_xpath", path);
		vGrpButts(elements, acts, actn);
		Assert.assertTrue(selUtils.getObject("imp_radio_butt_xpath")
				.getAttribute("checked").equals("true"), acts[0]
				+ " radio button is not selected.");
		logger.info("Verified, '" + acts[0]
				+ "' radio button is selected  by default.");
		selUtils.verifyElementDisp("trm_imp_csv_xpath", TERMTOIMPT);

		// The 'Terminals to move' field is grayed and not editable
		vExpFldClr("imprt_trm_move_id", greyClr);
		Assert.assertFalse(selUtils.getObject("imprt_trm_move_id").isEnabled(),
				"'" + Terminals.trmsToMove + "' text box is editable.");
		logger.info("Verified, '" + Terminals.trmsToMove
				+ "' text box is having gray color, and not editable.");
		selUtils.verifyElementDisp("cnfrm_link", confirm);
		selUtils.verifyElementDisp("cls_link", closeButton);
	}

	/**Click on 'Import packages' button of 'Import actions' buttons section. 
	 *Verify 'Import packages' modal window is displayed containing expected.**/
	
	public void vImpPkgsModalWindInfo() {
		verifyExpWinDisp("impr_sftwr_pak_xpath", IMPPKGS);
		vGrpButts(selUtils.getObjects("pkg_btch_radio_butts_xpath"),
				btchModeButts, BTCHMODE);
		Assert.assertTrue(selUtils.getObject("immed_radio_butt_xpath")
				.getAttribute("checked").equals("true"), immediately
				+ " radio button is not selected.");
		logger.info("Verified, '" + immediately
				+ "' radio button is selected  by default.");

		// The 'Execution Date' field is grayed and not editable
		vExpFldClr("pkg_exe_date_id", greyClr);
		Assert.assertFalse(selUtils.getObject("pkg_exe_date_txt_box_id")
				.isEnabled(), "'" + EXEDATE + "' check box is editable.");
		logger.info("Verified, '" + EXEDATE
				+ "' check box is having gray color, and not editable.");
		selUtils.verifyElementDisp("sw_pkg_imp_csv_xpath", SWPKGTOIMP);
		selUtils.verifyElementDisp("file_imp_csv_xpath", FILETOIMPT);
		selUtils.verifyElementDisp("cnfrm_link", confirm);
		selUtils.verifyElementDisp("cls_link", closeButton);
	}

	/**Click on 'Import tasks' button of the 'Import actions' buttons section. 
	 *Verify 'Import tasks' modal window is displayed containing expected fields
	 *and buttons. **/
	public void vImpTsksModalWindInfo() {
		verifyExpWinDisp("imp_task_link", IMPTSKS);
		vGrpButts(selUtils.getObjects("tsks_btch_radio_butts_xpath"),
				btchModeButts, BTCHMODE);
		Assert.assertTrue(selUtils.getObject("immed_radio_butt_xpath")
				.getAttribute("checked").equals("true"), immediately
				+ " radio button is not selected.");
		logger.info("Verified, '" + immediately
				+ "' radio button is selected  by default.");

		// The 'Execution Date' field is grayed and not editable
		vExpFldClr("tsks_exe_date_id", greyClr);
		Assert.assertFalse(selUtils.getObject("tsks_exe_date_txt_box_id")
				.isEnabled(), "'" + EXEDATE + "' check box is editable.");
		logger.info("Verified, '" + EXEDATE
				+ "' check box is having gray color, and not editable.");
		selUtils.verifyElementDisp("tsks_imp_csv_xpath", TASKTOIMP);
		selUtils.verifyElementDisp("cnfrm_link", confirm);
		selUtils.verifyElementDisp("cls_link", closeButton);
	}

	/**Verify, -Record: expected value, -Status: expected value,-Reason:
	 * expected message.**/
	public void vExpValsInAllRow(String oldVal1, String oldVal2,
			String pckItems[][]) {
		for (itemCount = 0; itemCount < pckItems.length; itemCount++) {
			elements = selUtils.getObjects("impr_row_vals_xpath");
			for (cnti = 0; cnti < elements.size(); cnti++) {
				path = selUtils.getPath("btch_imp_vw_exp_cell_val_css")
						.replace(oldVal1, cnti + 1 + "");
				path = path.replace(oldVal2, 2 + "");
				if (!(selUtils.getObject("btch_imp_vw_exp_cell_val_css", path)
						.getText().equalsIgnoreCase(pckItems[itemCount][1]))) {
					continue;
				} else {
					String firstPath = selUtils.getPath(
							"btch_imp_vw_exp_cell_val_css").replace(oldVal1,
							cnti + 1 + "");
					String path0 = firstPath.replace(oldVal2, 1 + "");
					String path1 = firstPath.replace(oldVal2, 2 + "");
					String path2 = firstPath.replace(oldVal2, 3 + "");
					String path3 = firstPath.replace(oldVal2, 4 + "");
					selUtils.vExpValPresent(selUtils.getObject(
							"btch_imp_vw_exp_cell_val_css", path0),
							pckItems[itemCount][0]);
					selUtils.vExpValPresent(selUtils.getObject(
							"btch_imp_vw_exp_cell_val_css", path1),
							pckItems[itemCount][1]);
					selUtils.vExpValPresent(selUtils.getObject(
							"btch_imp_vw_exp_cell_val_css", path2),
							pckItems[itemCount][2]);
					selUtils.vExpValPresent(selUtils.getObject(
							"btch_imp_vw_exp_cell_val_css", path3),
							pckItems[itemCount][3]);
				}
			}
		}
	}

	/** Verify table values,import table
	 * @param termVal */
	public void verifyImpTableData(String termVal[][]) {
		webEleType = getTableHeadCursVals("imp_tab_col_val_xpath",
				"imp_tab_hdr_xpath", "INDEX", type);
		webEleRec = getTableHeadCursVals("imp_tab_col_val_xpath",
				"imp_tab_hdr_xpath", "INDEX", record);
		webEleSts = getTableHeadCursVals("imp_tab_col_val_xpath",
				"imp_tab_hdr_xpath", "INDEX", sts);
		webEleReason = getTableHeadCursVals("imp_tab_col_val_xpath",
				"imp_tab_hdr_xpath", "INDEX", reason);
		waitNSec(4);
		for (cnti = 0; cnti < webEleType.size(); cnti++) {
			selUtils.vExpValPresent(webEleType.get(cnti), termVal[cnti][0]);
			selUtils.vExpValPresent(webEleRec.get(cnti), termVal[cnti][1]);
			selUtils.vExpValPresent(webEleSts.get(cnti), termVal[cnti][2]);
			selUtils.vExpValPresent(webEleReason.get(cnti), termVal[cnti][3]);
		}
	}

	/**Click on close button.The new import appears in the table with following 
	 *values in the following columns:-User name: The name of the user connected
	 *for this test as value-Type:'Import terminals'-File name: expected -Status
	 *:'Created'.-Action: It contains a Delete icon.**/
	
	public void vNewImpTrm(String locator, String value, String file) {
		verifySpecifiedWinNotDisp(locator);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		selUtils.vExpValPresent("imprt_status_1st_row_css", created);
		selUtils.vExpValPresent("imprt_uname_1st_row_css",
				config.getProperty("superuser"));
		selUtils.vExpValPresent("imprt_type_1st_row_css", value);
		selUtils.vExpValPresent("imprt_fname_1st_row_css", file + "." + csv);
		selUtils.verifyElementDisp("imprt_del_icn_1st_row_css", delete);
	}

	/** Verify details of Import Provisioning popup
	 *  */
	
	public void vImpProvDetails(String locator, String locator1,
			String dataFile, String values[]) throws IOException {
		verifyWebIconToolTip(locator1, clkForHelp, clkForHelp);
		verifyWebIconToolTip(locator1, dwnTemp, dwnTemp);
		selUtils.clickOnWebElement(locator1, nameLbl, clkForHelp, help);
		selUtils.vExpValContains("imp_trm_help_title_xpath", command);
		verifySpecifiedWinNotDisp("help_cls_butt_css");
		selUtils.clickOnWebElement(locator1, nameLbl, dwnTemp);
		readDownloadedCSV(getDownFilePath(dataFile, "csv"), values);
		selUtils.selcAndVRadButt(locator, deferred);
	}
}