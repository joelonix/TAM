package com.ingenico.tam.objects;

/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/Scenarios.java $
 $Id: Scenarios.java 14253 2015-07-21 11:49:41Z amsingh $
 */

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * Scenarios Class - Methods related to Scenarios module
 */
public class Scenarios extends TestBase {

	public static String complexScenItem1 = "Auto_Scenario_1",
			complexScenItem11 = "Auto_Scenario_11",
			jbTbDesColCpxSce = "Complex scenario: ",
			brdCrmSceWzd = "TMS » Scenario Wizard » Auto_Test",
			addScenWinMsg = "Commands defined in the scenario will be applied to the compliant terminals",
			addScnModWin = "Add scenario",
			addScenErrMsg = "Select a scenario in the list",
			saveAs = "Save as...",
			saveBtn = "Save",
			cmplxSce = "Complex scenario",
			sceLstTxt = "Scenarios list loaded.",
			sceEditMsg = "Edit the new scenario after the creation",
			scenName = "Scenario name",
			sceWzrd = "Scenario wizard",
			selcSce = "Select a scenario",
			fndScen = "Find scenarios",
			crtScen = "Create a scenario",
			delSecToltip = "Delete the selected scenario",
			renmSecToltip = "Rename the selected scenario",
			savSecToltip = "Save a new scenario from the selected scenario",
			edtSecToltip = "Edit the selected scenario",
			ownSelc = "Owner selection",
			newSceSel = "New scenario selection",
			sceName = "Scenario name",
			edtSceCretn = "Edit the scenario after the creation",
			backToList = "Back To List",
			findSce = "Find scenarios",
			scenSaving = "Saving of the new scenario in progress...",
			alreadyexit = " already exist",
			hasbeen = "has been",
			sceCrterrMesg = "The same scenario 'Auto_SCNWZD_1' already exist",
			expectedElem = "Expected Element",
			renam = "Rename",
			sceSuccMsg = "The new scenario has been created",
			del = "Delete",
			delSuccMsg = "The scenario has been deleted",
			selectApackge = "Select a package",
			msgsce = "The same scenario " + "\'" + nam + "\'" + alreadyexit,
			savAsScnErrMsg = "The name must be different of the source scenario.",
			scendelmsg = "The scenario has been deleted",
			sceRenam = "The scenario name has been renamed",
			validate = "Validate",
			format = "Format",
			custoTelium = "Custo. TELIUM",
			savasSuccMsg = "The new scenario has been saved",
			reslt = "Result",
			nwOnrSelc = "New owner selection",
			nwSecCrt = "New scenario to create",
			edtScnwzdBrdcrm = "TMS » Edit scenario wizard » ",
			prntMsg = "<print>\\s*<line align=\"left\" text=\"\" />\\s*</print>",
			clrMsg = "<clear target=\"repository:/%1\" />",
			downMsg = "<downloadDataFile dstFilename=\"%1\" source=\"%2\"/>",
			sceNmeMsg = "The scenario has been saved",
			switMsg = "<switch>\\s*<condition check=\"property\" name=\"\"/>\\s*<case value=\"\">\\s*</case>\\s*</switch>",
			dispMsg = "<display clearDisplay=\"false\">\\s*<line lineNumber=\"1\" align=\"center\" text=\"\" />\\s*</display>",
			delmsg = "<deleteApplication package=\"%1\" />",
			valSyntx = ": Validating syntax of all commands",
			maintainApp = "<maintainApplication>:   Command validated successfully",
			cmpltVal = "Completed validation of command syntax",
			installPak = "<installPackage>:   Command validated successfully",
			display = "Display",
			displaytxt = "<display>:   Command validated successfully",
			quittxt = "<quit>:   Command validated successfully",
			cleartxt = "<clear>:   Command validated successfully",
			tetraTab = "TETRA",
			edtPageBottonInfo = "Do not forget to click the   Save   button to save your changes and additions!",
			scnName = "Scenario name", scview = "Scenario view",
			scview_msg = "<SCENARIO>" + "\n" + "</SCENARIO>",
			savTxt = "Do not forget to click the", autotest = "Auto_Test",
			quit = "Quit", scAddKey = "Add a key", scMsg = "Message",
			scClean = "Clean", scDisp = "Display message",
			scPrint = "Print message";


	public static String[] complexScenarios = { complexScenItem1,
		"Auto_Scenario_2", "Auto_Scenario_3", "Auto_Scenario_4",
		"Auto_Scenario_5", "Auto_Scenario_6", "Auto_Scenario_7",
		"Auto_Scenario_8", "Auto_Scenario_9", "Auto_Scenario_10" },
		showResDrpDnItems = { "5", "10", "20", "30" }, sceList = {
		"Auto_Scenario_2_1", "Auto_Scenario_2_2",
		"Auto_Scenario_2_3", "Auto_Scenario_2_4",
		"Auto_Scenario_2_5", "Auto_Scenario_2_6",
		"Auto_Scenario_2_7", "Auto_Scenario_2_8",
		"Auto_Scenario_2_9", "Auto_Scenario_2_10" },
		scenarios = { complexScenItem1, "Auto_Scenario_2",
		"Auto_Scenario_3", "Auto_Scenario_4", "Auto_Scenario_5",
		"Auto_Scenario_6", "Auto_Scenario_7", "Auto_Scenario_8",
		"Auto_Scenario_9", "Auto_Scenario_10", complexScenItem11,
	"Auto_Scenario_12" }, scenarios2 = { "Auto_Scenario_2_1",
		"Auto_Scenario_2_2", "Auto_Scenario_2_3",
		"Auto_Scenario_2_4", "Auto_Scenario_2_5",
		"Auto_Scenario_2_6", "Auto_Scenario_2_7",
		"Auto_Scenario_2_8", "Auto_Scenario_2_9",
		"Auto_Scenario_2_10", "Auto_Scenario_2_11",
	"Auto_Scenario_2_12" }, cmplxSceHdrs = {
		Jobs.jobsTabcolsHds[0], nameLbl, Key.keyMngmentActHdrs[1],
		Packages.pakActTabColHdrs[4] }, scenarioWizard = {
		"Auto_SCNWZD_1", "Auto_SCNWZD_2", "Auto_SCNWZD_3" },
		sceColHdr = { scenName, owner, actions, crtBy, crtnDate, updDate },
		scenarioFinds = { scenarioWizard[0], scenarioWizard[1] },
		reslSet1 = { techng, "switch", Terminals.techngyLstItems[1],
		"display", Terminals.techngyLstItems[3],
	"deleteApplication" }, u32Set = { taskTypes[2],
		"DeleteDFSDirectory", "DeleteDFSFile", disp, prnt,
		"UpdateSystem", clear, conf, delPak, swtch,
		"CloseSessionMode", scMsg, quit, scClean }, teliSet = {
		taskTypes[2], clear, conf, delPak, scAddKey, swtch, scMsg,
		quit }, teliSet1 = { taskTypes[2], clear, conf, delPak,
		scAddKey, swtch, quit }, wincSet = { taskTypes[2], clear,
		conf, delPak, swtch, quit }, teliTabTxts = { clear, conf,
		scAddKey, swtch, scMsg, quit }, u32TabTxts = {
		"DeleteDFSDirectory", "DeleteDFSFile", disp, prnt, clear,
		conf, swtch, "CloseSessionMode", scMsg, quit, scClean },
		wincTabtxts = { clear, conf, swtch, quit }, reslTabtxts = { techng,
		"switch", Terminals.techngyLstItems[1], "display",
		Terminals.techngyLstItems[3], "deleteApplication" },
		u32DisbldTxts = { taskTypes[2], "UpdateSystem", delPak },
		teliDisblbdTxts = { taskTypes[2], delPak }, tetraSet = {
		taskTypes[2], scDisp, scPrint, "Transaction", conf, delPak,
		swtch, scMsg, quit }, tetraTabtxts = { scDisp, scPrint,
		"Transaction", conf, swtch, scMsg, quit }, scnActBtns = {
		renam, del, saveBtn, saveAs }, comdActions = { validate,
		format, selectApackge, custoTelium },edtRadBtuttns= {"sce_edit_ingenico_radBtn_id", "sce_edit_autoEntity_radBtn_id","sce_edit_autoTest_radBtn_id"},
	    scnEdtRadBtns = {"sce_edt_ingenicoBtnVal_xpath", "sce_edt_autoEntBtnVal_xpath","sce_edt_autoTestBtnVal_xpath"},
	    tabLoc= { "sce_u32_tab_xpath","sce_telim_tab_xpath",  "sce_wince_tab_xpath", "sce_tetra_tab_xpath"},
	    helpLoc= {"sce_u32_helpIcon_xpath","sce_tel_helpIcon_xpath", "sce_wince_helpIcon_xpath", "sce_tetra_helpIcon_xpath"},
	    tabTextsLoc= { "edt_u32txts_xpath", "sce_telTxts_xpath","sce_winceTxts_xpath", "sce_tetraTxts_xpath"},
	    scenarioActions = { "Create a scenario","Scenario actions", "Command actions" }, ownerSecs = { ingenico, autoEntity, client, all };
	
	public static String[][] tabTexts= {u32Set ,teliSet , wincSet, tetraSet},tabTexts1= {u32Set ,teliSet1 , wincSet, tetraSet};;	   

	public String scenariName;
	/*public static String[] scenarioActions = { "Create a scenario",
		"Scenario actions", "Command actions" }, ownerSecs = { ingenico,
		autoEntity, client, all };*/
	public String scenarioName = "";

	/**
	 * Method to initialize the XML files
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@BeforeSuite(alwaysRun = true)
	public void initSetUp() throws FileNotFoundException, IOException {
		initialize();
	}

	/**
	 * Click on the 'Add scenario' button of the 'Terminals tasks' buttons
	 * section The 'Add scenario' modal window is displayed containing expected.
	 */
	public void vAddTrmSceDets(String sceLocator) {
		verifyExpWinDisp(sceLocator, addScnModWin);
		Assert.assertTrue(selUtils.getObject("sel_trm_sce_list_xpath")
				.getText().contains(addScenErrMsg), addScenErrMsg
				+ " is not displayed ");
		logger.info(" Verified that " + addScenErrMsg + " is displayed");
		selUtils.vDrpDnSelcItem("task_add_id", noneVar);
		Assert.assertTrue(selUtils.getObject("trm_sce_opts_xpath").getText()
				.contains(optns), optns + " is not displayed ");
		logger.info(" Verified that " + optns + " is displayed");
		Assert.assertTrue(
				selUtils.getObject("sce_tsk_prio_css").getAttribute("value")
				.contains(priority100),
				" Expected value is not displayed ");
		logger.info(" Verified that Expected value is displayed");
		selUtils.verifyEleNotSelected("trm_sce_prmtsk_css");
		Assert.assertTrue(selUtils.getObject("trm_sce_msg_xpath").getText()
				.contains(addScenWinMsg), addScenWinMsg + " is not displayed ");
		logger.info(" Verified that " + addScenWinMsg + " is displayed");
		selUtils.verifyElementDisp("sce_add_butt_id", addButt);
		selUtils.verifyElementDisp("sce_cls_butt_id", "Close");
	}

	/**
	 * Click on 'Close' button.The modal window is closed an 'Complex scenario'
	 * tab of page gets in focus showing at least 'Auto_Scenario_2_11' scenario
	 * in table with expected values
	 */
	public void vEdtSceDet(String browser, String sceName) {
		selUtils.getObject("trm_edit_scen_cls_id").click();
		vCmplxSceTabData(Scenarios.scenarios2[10], browser);
		xpath = selUtils.getPath("trm_edit_scen_icon_xpath").replace("Name",
				sceName);
		Assert.assertTrue(selUtils.isElementPresentxpath(xpath),
				"Expected terminal icon is not displayed.");
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath))
				.getAttribute("onmouseover").contains(Terminals.addTrmToolTip),
				" Expected tooltip message is not displyed.");
		logger.info("Verified, expected icon and tooltip message are displayed.");
	}

	/**
	 * Click on 'Add scenario'.Set the 'Complex scenario' field.Click on 'Add'
	 * button of the modal window'.Click on the 'Close' button
	 */
	public void vAddSceFunc(String cmpxSce, String sign, String sceLocator) {
		verifyExpWinDisp(sceLocator, addScnModWin);
		selUtils.selectItem(selUtils.getObject("task_add_id"), cmpxSce);
		selUtils.vDrpDnSelcItem("task_add_id", cmpxSce);
		confrmPopupActionMsg("trm_edit_scen_add_id",
				"trm_edit_scen_proc_msg_id", "term_addsce_msgres_id",
				processingMsg, tsksucfulladd);
		verifyOnlyCloseButtInSuccWin("trm_edit_scen_butts_css");
		verifySpecifiedWinNotDisp("trm_edit_scen_cls_id");
	}

	/**
	 * Click on 'Add scenario'.Set the 'Complex scenario' field.Click on 'Add'
	 * button of the modal window'.Click on the 'Close' button
	 */
	public void vAddSceFunc(String cmpxSce, String[] signs) {
		verifyExpWinDisp("add_sce_xpath", addScnModWin);
		selUtils.selectItem(selUtils.getObject("task_add_id"), cmpxSce);
		selUtils.vDrpDnSelcItem("task_add_id", cmpxSce);
		confrmPopupActionMsg("trm_edit_scen_add_id",
				"trm_edit_scen_proc_msg_id", "term_addsce_msgres_id",
				processingMsg, tsksucfulladd);
		verifyOnlyCloseButtInSuccWin("trm_edit_scen_butts_css");
		verifySpecifiedWinNotDisp("trm_edit_scen_cls_id");
	}

	/**
	 * Click on 'Add scenario'.Set the 'Complex scenario' field.Click on 'Add'
	 * button of the modal window'.Click on the 'Close' button
	 */
	public void vAddSceFunc(String expCmpxSce, String expTskPri, String sign,
			String sceLocator) {
		verifyExpWinDisp(sceLocator, addScnModWin);
		selUtils.selectItem(selUtils.getObject("task_add_id"), expCmpxSce);
		selUtils.vDrpDnSelcItem("task_add_id", expCmpxSce);
		selUtils.populateInputBox("sce_tsk_prio_css", expTskPri);
		logger.info("Verified, 'Task priority' field is set to '" + expTskPri
				+ "'.");
		confrmPopupActionMsg("trm_edit_scen_add_id",
				"trm_edit_scen_proc_msg_id", "term_addsce_msgres_id",
				processingMsg, tsksucfulladd);
		// Assert.assertTrue(selUtils.getObject("trm_edit_scen_succ_msg_xpath").getText().contains(sign),
		// " Expected value is displyed");
		verifyOnlyCloseButtInSuccWin("trm_edit_scen_butts_css");
		verifySpecifiedWinNotDisp("trm_edit_scen_cls_id");
	}

	/**
	 * Verify, the 'Complex scenario' tab of the page
	 */
	public void vCmplxSceTabData(String sceName, String browser) {
		selUtils.vExpTabInFocus("trm_focus_tabs_css");
		// ObjectFactory.getTerminalsInstance().vExpTabFocused(cmplxSce);
		selectMaxSizeinTable("trm_edt_sel_max_res_css", browser);
		ObjectFactory.getTerminalsInstance().waitForTerminalPageLoaded();
		selUtils.vExpValPresent("trm_edit_scen_name_xpath", nameLbl, sceName,
				sceName);
		vDateCol("trm_edit_scen_date_xpath", sceName);
		selUtils.vExpColData("trm_edit_scen_status_xpath", sceName, statusRun);
	}

	/**
	 * Verify, Add Scenario functionality for listed invalid priority :
	 * 'aA&%\<>'.
	 */
	public void vNegSceFuncWithInvalVals(String inValValues, String sceLoc,
			String expSce) {
		char[] inValValuesArry = inValValues.toCharArray();
		for (itemCount = 0; itemCount < inValValuesArry.length; itemCount++) {
			String expVal = String.valueOf(inValValuesArry[itemCount]);
			/*
			 * Click on the 'Add scenario' button of the 'Terminals tasks'
			 * buttons section. Verify, The 'Add scenario' modal window is
			 * displayed.
			 */
			verifyExpWinDisp(sceLoc, addScnModWin);
			/*
			 * Click on the 'Complex scenario' Drop-down list icon and choose
			 * expected scenario. Verify, the field is set to expected scenario.
			 */
			selUtils.selectItem(selUtils.getObject("task_add_id"), expSce);
			selUtils.vDrpDnSelcItem("task_add_id", expSce);
			selUtils.populateInputBox("sce_tsk_prio_css", expVal);
			logger.info("'Task priority' field value is set to '" + expVal
					+ "'.");
			confrmPopupActionMsg("sce_add_butt_id",
					"sce_err_msg_with_icon_xpath", priorLmtErMsg);
			vMsgAndFldAndLblInRedClr("sce_err_msg_with_icon_xpath",
					"sce_tsk_prio_css", "sce_tskprio_lbl_fld_css");
			// Click on the 'Close' button. Verify, the modal window is closed.
			// The 'Signature' field value is still 'EST_2204_Sign'.
			verifySpecifiedWinNotDisp("sce_cls_butt_id");
		}
	}

	/**
	 * Click on the 'Add scenario' button of 'Terminals tasks' buttons section.
	 * Click on 'Complex scenario' Drop-down list icon and choose
	 * 'Auto_Scenario_2_11'.Set 'Task priority' field to '1000'.
	 */
	public void vNegSceFuncWithInvalTskPri(String expSce, String expTskPri,
			String sceLocator) {
		verifyExpWinDisp(sceLocator, addScnModWin);
		selUtils.selectItem(selUtils.getObject("task_add_id"), expSce);
		selUtils.vDrpDnSelcItem("task_add_id", expSce);
		selUtils.populateInputBox("sce_tsk_prio_css", expTskPri);
		logger.info("'Task priority' field value is set to '" + expTskPri
				+ "'.");
		confrmPopupActionMsg("sce_add_butt_id", "sce_err_msg_with_icon_xpath",
				priorLmtErMsg);
		vMsgAndFldAndLblInRedClr("sce_err_msg_with_icon_xpath",
				"sce_tsk_prio_css", "sce_tskprio_lbl_fld_css");
	}

	/**
	 * Verify the mandatory fields functionality in 'Add Scenario' modal window,
	 * and 'Add' functionality.
	 */
	public void vAddSceMndtryFldsFunc(String expSce, String expTskPri,
			String sceLocator) {
		verifyExpWinDisp(sceLocator, addScnModWin);
		selUtils.selectItem(selUtils.getObject("task_add_id"), noneVar);
		selUtils.vDrpDnSelcItem("task_add_id", noneVar);
		selUtils.populateInputBox("sce_tsk_prio_css", expTskPri);
		vValFrmInputBox("sce_tsk_prio_css", expTskPri, "Task priority");
		confrmPopupActionMsg("sce_add_butt_id", "sce_err_msg_with_icon_xpath",
				addScenErrMsg);
		vMsgAndFldAndLblInRedClr("sce_err_msg_with_icon_xpath", "task_add_id",
				"sce_cmplxsce_lbl_id");
		selUtils.selectItem(selUtils.getObject("task_add_id"), expSce);
		selUtils.vDrpDnSelcItem("task_add_id", expSce);
		vExpFldBlkClrAndNoExpMsg("task_add_id", cmplxSce,
				"sce_err_msg_with_icon_xpath", addScenErrMsg);
		selUtils.populateInputBox("sce_tsk_prio_css", "");
		logger.info("'Task priority' field value is set to blank");
		confrmPopupActionMsg("sce_add_butt_id", "sce_err_msg_with_icon_xpath",
				priMandtoryErrMsg);
		vMsgAndFldAndLblInRedClr("sce_err_msg_with_icon_xpath",
				"sce_tsk_prio_css", "sce_tskprio_lbl_fld_css");
	}

	/**
	 * Click on the 'Add' button of the modal window, verify the modal window
	 * displays the message: 'Processing'. Then it displays a list of terminals
	 * Serial Numbers all starting with the pattern '110' in a section, called
	 * 'Successful'..
	 */
	public void verifyAddScenarioAddFunctionality() {
		selUtils.clickOnWebElement("sce_add_butt_id", addButt);
		selUtils.waitForTxtPresent("addsce_succ_lititms_css", crtdTskMsg);
		Assert.assertTrue(selUtils.getObject("addsce_succ_lititms_css")
				.getText().equals(crtdTskMsg),
				"After processing finished, susscessful window is not dispplayed.");
		verifyOnlyCloseButtInSuccWin("addsce_succ_cls_id");
	}

	/**
	 * Verify mandatory fields behavior in Add Scenario modal window.
	 */
	public void verifyAddSceMandatoryFunctionality(String fieldTxtBox,
			String fieldLabel, String errMsg, String field) {
		Assert.assertTrue(selUtils.getObject("addscenario_errormsg_id")
				.getText().equals(errMsg),
				"In 'Add Scenario' window, error message is not displayed as expected.");
		Assert.assertTrue(selUtils.getObject("addscenario_errormsg_id")
				.getAttribute("style").contains(redColor),
				"In 'Add Scenario' window, error message is not displayed in red color.");
		Assert.assertTrue(selUtils.getObject("addscenario_error_icon_css")
				.isDisplayed(), "In '" + errMsg
				+ "' window, error icon is not displayed.");
		waitMethods.waitForPageLoaded();
		Assert.assertTrue(selUtils.getObject(fieldLabel).getAttribute("style")
				.contains(redColor), "'" + field
				+ "' label is not displayed in " + redColor + " color.");
		Assert.assertTrue(selUtils.getObject(fieldTxtBox).getAttribute("style")
				.contains(redColor), "'" + field
				+ "' text box is not displayed in " + redColor + " color.");
		logger.info("Verified, In '"
				+ errMsg
				+ "' window, error message, and icon are displayed in red color.");
		logger.info("Verified, '" + field
				+ "' field label and text box are displayed in red color.");
	}

	/**
	 * Click on the 'Add scenario' button and verify details.
	 */
	public void verifyAddScenarioModalWindowInfo() {
		verifyExpWinDisp("edt_est_sce_xpath", addScnModWin);
		waitMethods.waitForElementDisplayed("task_add_id");
		Assert.assertTrue(selUtils.getObject("task_add_id").isDisplayed(),
				"Complex scenario field is not displayed.");
		Assert.assertTrue(
				selUtils.getSelectedItem(selUtils.getObject("task_add_id"))
				.equals(noneVar),
				"Complex scenario field is not set by default to '" + noneVar
				+ "'.");
		logger.info("Verified, Complex scenario field is set by default to '"
				+ noneVar + "'.");

		Assert.assertTrue(selUtils.getObject("sce_tsk_prio_css").isDisplayed(),
				"Task Priority field is not displayed.");
		Assert.assertTrue(
				selUtils.getObject("sce_tsk_prio_css").getAttribute("value")
				.equals(priority100),
				"Task Priority field is not set by default to '" + priority100
				+ "'.");
		logger.info("Verified, Task Priority field is set by default to '"
				+ priority100 + "'.");

		Assert.assertFalse(selUtils.getObject("addsce_perm_task_chkbox_id")
				.isSelected(),
				"'Permanent Task' check box is selected by default.");
		logger.info("Verified, 'Permanent Task' check box is not selected by default.");

		Assert.assertTrue(selUtils.getObject("addsce_info_msg_css").getText()
				.equals(addScenWinMsg), "Expected message is not '"
						+ addScenWinMsg + "'.");
		logger.info("Verified, expected message is '" + addScenWinMsg + "'.");

		Assert.assertTrue(selUtils.getObject("sce_add_butt_id").isDisplayed(),
				"'Add' button is not displayed in '" + addScnModWin
				+ "' modal window.");
		logger.info("Verified, 'Add' button is displayed in '" + addScnModWin
				+ "' modal window.");

		Assert.assertTrue(selUtils.getObject("sce_cls_butt_id").isDisplayed(),
				closeButton + " button is not displayed in '" + addScnModWin
				+ "' modal window.");
		logger.info("Verified, '" + closeButton + "' button is displayed in '"
				+ addScnModWin + "' modal window.");
	}

	/**
	 * Wait for scenario wizard open window to load
	 */
	public void waitforSceWzrdOpenWinLoaded() {
		selUtils.waitForTxtPresent("sce_lst_loaded_xpath", sceLstTxt);
	}

	/**
	 * Select and verify radio button and click on .
	 */
	public void selecRadioButtAndClkOnDrpDn() {
		selUtils.selcAndVRadButt("auto_ent_radbutt_xpath", autoEntity);
		selUtils.clickOnWebElement("selec_sce_drpdn_id", selcSce);
		waitforSceWzrdOpenWinLoaded();
	}

	/**
	 * Click on the Consult job icon in the 'Edit' column of the 'Complex
	 * scenario' table for the expected scenario. Verify, the 'TMS >> Job
	 * details' page is displayed. The expected value of the 'Priority' field.
	 **/
	public void vCmplxScePriValInJobDtlsPg(String expCmplxSce, String expTskPri) {
		xpath = selUtils.getPath("trm_cmplxscen_edt_icon_xpath").replace(
				"Name", expCmplxSce);
		selUtils.getObjectDirect(By.xpath(xpath)).click();
		selUtils.verifyBreadCrumb(Jobs.breadCrumJobDtls);
		Assert.assertTrue(selUtils.getObject("job_edt_tskprio_id").getText()
				.equals(expTskPri));
		logger.info("Verified, 'Priority' field is '" + expTskPri + "'.");
	}

	/**
	 * Click on the 'Complex scenario' tab. Verify, the 'Complex scenario' tab
	 * of the page gets in focus showing at least the expected scenario in the
	 * table.
	 **/
	public void vCmplxSceFocus(String browser) {
		selUtils.clickOnWebElement("cmplx_scen_link", cmplxSce);
		vExpTabFocused(cmplxSce);
		selcMaxPgSz("trm_edt_sel_max_res_css", browser, "trm_edt_scen_act_disppagenos_css",
				editPageDispTxt);
	}

	/**
	 * Click on the 'Complex scenario' tab. Verify, the 'Complex scenario' tab
	 * of the page gets in focus showing at least the expected scenario in the
	 * table.
	 **/
	public void vCmplxSceInEdtPg(String browser, String expCmplxSce) {
		vCmplxSceFocus(browser);
		selUtils.vExpValPresent("trm_edit_scen_name_xpath", nameLbl,
				expCmplxSce, expCmplxSce);
	}

	/**
	 * Click on the 'Complex scenario' tab.The 'Complex scenario' tab is in
	 * focus. All the scenarios with the Name from expected scenarios, have an
	 * Estate icon associated in their Name column.
	 **/
	public void vCmplxScesAdEstIcns(String browser, String[] expCmplxSces) {
		vCmplxSceFocus(browser);
		objScrollDwn("edt_trm_cmplxsce_tbl_id");
		for (itemCount = 0; itemCount < 10; itemCount++) {
			selUtils.vExpValPresent("trm_edit_scen_name_xpath", nameLbl,
					expCmplxSces[itemCount], expCmplxSces[itemCount]);
			selUtils.vExpIcon("trm_edit_scen_icon_xpath",
					expCmplxSces[itemCount], expCmplxSces[itemCount]);
		}
	}

	/**
	 * Click on the 'Complex scenario' tab.The 'Complex scenario' tab is in
	 * focus. All the scenarios with the Name from expected scenarios.
	 **/
	public void vCmplxSces(String browser, String[] expCmplxSces) {
		vCmplxSceFocus(browser);
		objScrollDwn("edt_trm_cmplxsce_tbl_id");
		for (itemCount = 0; itemCount < 10; itemCount++) {
			selUtils.vExpValPresent("trm_edit_scen_name_xpath", nameLbl,
					expCmplxSces[itemCount], expCmplxSces[itemCount]);
		}
	}

	/**
	 * Verifies Find Scenario Drop Down window is displayed and clicks on Search
	 * button
	 */
	public void vFindForm(String loc) {

		selUtils.getCommonObject("find_but_xpath").click();
		Assert.assertTrue(selUtils.getObject(loc).isDisplayed());
		logger.info("Verified,Expected Find drop down window is displayed");
		selUtils.getCommonObject("srch_link").click();
		logger.info("Verified,Clicked on Search button");
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
	}

	/**
	 * Verifies Table data is not empty
	 */
	//Moved to TestBase
	/*public void vTableData(String loctor) {
		Assert.assertFalse(
				selUtils.isElementPresent(loctor, selUtils.getPath(loctor)),
				"Table Data is empty");
		logger.info("Verified,Table data is not empty");
	}*/

	/**
	 * Method is used to Create the Scenario by passing the scenario name
	 * individually and then verify the success message of the creation
	 * 
	 * @param : scenario Name
	 * @param : msgsce
	 */
	public void createScenario(String scenarios12, String owner, boolean flag) {
		wait.until(ExpectedConditions.elementToBeClickable(selUtils
				.getObject("cre_scen_but_xpath")));
		verifyExpWinDisp("cre_scen_but_xpath", crtScen);
		if (owner.equalsIgnoreCase(ingenico)) {
			selUtils.clickOnWebElement("createscenario_ingenico_id", ingenico);
		} else if (owner.equalsIgnoreCase(client)) {
			selUtils.clickOnWebElement("createscenario_autotest_id", client);
		} else {
			selUtils.clickOnWebElement("createscenario_autoentity_id",
					autoEntity);
		}
		selUtils.populateInputBox("scenarioname_xpath", scenarios12);
		if (flag) {

			selUtils.deSelecChkBx("createscenario_editchck_id");
		}
		cnfmPopupActMsg("createpopupconfirm_btn_id", "createscemsgrslt_id",
				sceSuccMsg);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(
				By.id(selUtils.getPath("createscewaiting_id")), scenSaving));
		verifySpecifiedWinNotDisp("createpopupclose_button_id");
	}

	/**
	 * Method is used to Create the scenarios
	 * 
	 * */
	public void createScenario(String[] scenarios12) {
		for (cnti = 0; cnti < scenarios12.length; cnti++) {
			wait.until(ExpectedConditions.elementToBeClickable(selUtils
					.getObject("cre_scen_but_xpath")));
			selUtils.clickOnWebElement("cre_scen_but_xpath",
					"CreateScenarioButton");
			verifyExpWinDisp("cre_scen_but_xpath", crtScen);
			if (scenarios12[cnti].equals(Scenarios.scenarioWizard[0])) {
				selUtils.clickOnWebElement("createscenario_ingenico_id",
						ingenico);
			} else if (scenarios12[cnti].equals(Scenarios.scenarioWizard[2])) {
				selUtils.clickOnWebElement("createscenario_autotest_id", client);
			} else {
				selUtils.clickOnWebElement("createscenario_autoentity_id",
						autoEntity);
			}
			selUtils.populateInputBox("scenarioname_xpath", scenarios12[cnti]);
			selUtils.deSelecChkBx("createscenario_editchck_id");
			cnfmPopupActMsg("createpopupconfirm_btn_id", "createscemsgrslt_id",
					sceSuccMsg);
			wait.until(ExpectedConditions.invisibilityOfElementWithText(
					By.id(selUtils.getPath("createscewaiting_id")), scenSaving));
			verifySpecifiedWinNotDisp("createpopupclose_button_id");
		}
		logger.info("Expected Scenarios are added successfully");
	}

	/**
	 * Method is used to verify the message which will be populated during the
	 * Scenario creation
	 * 
	 * @param : msgsce
	 */
	public void vScenarioSuccessMsg(String msgsce, String loc, String loc1) {
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject(loc)));

		if (selUtils.getObject(loc).getText().contains(alreadyexit.trim())) {
			selUtils.vExpValContains(loc, msgsce);
		} else if (selUtils.getObject(loc).getText().trim().contains(hasbeen)) {
			selUtils.vExpValContains(loc, msgsce);
		} else {
			Assert.fail("Create Scearnario message showing incorrectly");
		}
		//selUtils.clickOnWebElement(loc1, expectedElem);
		selUtils.clickOnWebElement(loc1, closeButton);
	}

	/**
	 * To verify error With wildChar error validation
	 * 
	 * @param eleLoc
	 * @param errMsg
	 */
	public void verifyErrWithWildChar(String eleLoc, String errMsg) {
		for (cnti = 0; cnti < spelChar.length; cnti++) {
			selUtils.populateInputBox(eleLoc, spelChar[cnti]);
			vValFrmInputBox(eleLoc, spelChar[cnti], findScenario);
			verifyPopUpErrMsgWithInvaliData("srch_link",
					"scen_find_errmesg_loc_id", unAuthErrMesg,
					"scen_fnd_err_icon_id", eleLoc);
			selUtils.clickOnWebElement("reset_link", reset);
			Assert.assertTrue(selUtils.getObject(eleLoc).getAttribute("value")
					.equals(""), " expected field value is not empty.");
			logger.info("Verifed, expected field value is removed. ");
		}
	}

	/**
	 * Create duplicate scenario and verify the error message.
	 * 
	 * @param sceName
	 */
	public void vCrtDupliScenario(String sceName, String owner, String msg) {
		verifyExpWinDisp("cre_scen_but_xpath", crtScen);
		if (owner.equalsIgnoreCase(ingenico)) {
			selUtils.selecRadButt("createscenario_ingenico_id");
		} else if (owner.equalsIgnoreCase(client)) {
			selUtils.selecRadButt("createscenario_autotest_id");
		} else {
			selUtils.selecRadButt("createscenario_autoentity_id");
		}
		selUtils.populateInputBox("sce_name_xpath", sceName);
		cnfmPopupActMsg("createpopupconfirm_btn_id", "createscemsgrslt_id", msg);
	}

	/**
	 * Method is used to verify the Scenario Rename model window display for the
	 * given scenario name and Click on the given button
	 * 
	 * @param sceName
	 *            , sceVal, loc
	 */
	public void scenarioRename(String sceName, String sceVal, String loc) {
		vExpWinDisp("scewzd_name_to_renam_xpath", sceName, renam);
		selUtils.populateInputBox("sce_name_xpath", sceVal);
		logger.info("Scenario name field is set to " + sceVal);
		//selUtils.clickOnWebElement(loc, expectedElem);
		selUtils.clickOnWebElement(loc, confirm);
	}

	/**
	 * Method is used to verify the Scenario delete model window display for the
	 * given scenario name and click on the confirm button. Verifies,Scenario
	 * name disappears after the delete
	 * 
	 * @param sceName
	 *            , msg,browser
	 */
	public void scenarioDeleteAndvElemNotPresent(String sceName, String msg,
			String browser) {
		vExpWinDisp("scewzd_name_to_delete_xpath", sceName, del);
		selUtils.clickOnWebElement("sce_del_confm_id", confirm);
		vScenarioSuccessMsg(msg, "sce_del_resultmsg_id", "sce_del_cls_id");
		selUtils.verifyBreadCrumb(brdCrmSceWzd);
		selcMaxPgSz("sce_sel_max_res_css", browser, "est_disppagenos_css",
				editPageDispTxt);
		selUtils.vEleNotPresent("scewzd_name_xpath", sceName, sceName);
	}

	/**
	 * Click on Save as icon from Actions column for the scenario called
	 * SCNWZD_1206 Set the Owner radio buttons to Ingenico.Set Scenario name
	 * field to Auto_SCNWZD_1.click Confirm' button and verify
	 */
	public void saveasScenarioAndConfirm(String sceName, String radioLoc,
			String name, String msg) {
		vExpWinDisp("sce_list_save_xpath", sceName, saveAs);
		selUtils.selecRadButt(radioLoc);
		selUtils.populateInputBox("sce_name_xpath", name);
		logger.info("Scenario name field is set to " + name);
		msgsce = msgsce.replaceAll(nam, scenarioWizard[0]);
		cnfmPopupActMsg("sce_save_cnfrm_id", "sce_saveas_resultmsg_id", msg);
	}

	/**
	 * Delete the Existing scenario and Navigate to Scenario Wizard by clicking
	 * on the Scenario wizard link
	 * 
	 * @param scenarioname
	 *            , browser
	 */
	public void deleteExistingScen(String scenarioname, String browser) {
		selUtils.getCommonObject("find_but_xpath").click();
		selUtils.getCommonObject("srch_link").click();
		selcMaxPgSz("sce_sel_max_res_css", browser, "est_disppagenos_css",
				editPageDispTxt);
		vals.clear();
		if ((vals = tableData()) != null && vals.contains(scenarioname)) {
			String scexpath = selUtils.getPath("scewzd_name_to_delete_xpath")
					.replace(nam, scenarioname);
			webElement = selUtils.getObjectDirect(By.xpath(scexpath));
			waitMethods.waitForElement(webElement);
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].click();", webElement);
			selUtils.getObject("sce_del_confm_id").click();
			selUtils.getObject("sce_del_cls_id").click();
			selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		}
	}

	/**
	 * Method is used to Delete the Existing scenarios
	 * 
	 **/
	public void deleteExistScenario(String[] scenarios12) {
		vals.clear();
		if ((vals = tableData()) != null) {
			for (cnti = 0; cnti < scenarios12.length; cnti++) {
				if (vals.contains(scenarios12[cnti])) {
					String xpath = selUtils.getPath("sce_name_del_xpath")
							.replace(nameLbl, scenarios12[cnti]);
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].click();",
							selUtils.getObjectDirect(By.xpath(xpath)));
					Assert.assertTrue(
							selUtils.getObject("deletescneario_popup_id")
							.isDisplayed(),
							"Expected : Delete model window is displayed");
					logger.info("Verified, Delete Model window is displayed");
					cnfmPopupActMsg("delpopupconfirm_button_id",
							"deletescenario_msg_id", scendelmsg);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By
							.id(selUtils.getPath("delpopupclose_button_id"))));
					selUtils.clickOnWebElement("delpopupclose_button_id",
							"Close button");
					Assert.assertFalse(
							selUtils.getObject("deletescneario_popup_id")
							.isDisplayed(),
							"Expected : Delete model window is closed");
					logger.info("Verified, Delete model window is closed");
					waitNSec(1);
					if (!(waitMethods.isElementPresent("no_datarow_id"))) {
						selUtils.waitForTxtPresent("est_disppagenos_css",
								editPageDispTxt);
					}
				}
			}
			logger.info("Existing Scenarios are deleted successfully");
		}

	}

	/**
	 * Method is used to return the records of the table
	 * 
	 * */
	public List<String> tableData() {
		if (!(waitMethods.isElementPresent("no_datarow_id"))) {
			waitMethods.waitForElementDisplayed("estates_list_sign_css");
			vals = selUtils.getLstItems(selUtils
					.getObjects("estates_list_sign_css"));
		}
		return vals;
	}

	/**
	 * Method is used to verify the Edit Scenario delete model window display
	 * for the given scenario name and click on the confirm button.
	 * Verifies,Scenario name disappears from the table after the delete
	 * 
	 * @param sceName
	 *            , msg
	 */
	public void editScenarioDeleteAndvElemNotPresent(String scecName,
			String browser) {
		verifyExpWinDisp("sce_del_butt_id", del);
		selUtils.clickOnWebElement("sce_del_confm_id", confirm);
		vScenarioSuccessMsg(delSuccMsg, "sce_del_resultmsg_id",
				"sce_del_cls_id");
		selUtils.verifyBreadCrumb(brdCrmSceWzd);
		selcMaxPgSz("sce_sel_max_res_css", browser, "est_disppagenos_css",
				editPageDispTxt);
		selUtils.vEleNotPresent("scewzd_name_xpath", scecName, scecName);
	}

	/**
	 * Validate the textarea in edit scenario.
	 * 
	 * @param txtAreaLoc
	 * @param msg
	 */
	public void txtAreaEdtScn(String txtAreaLoc, String msg) {
		waitMethods.waitForElementDisplayed(txtAreaLoc);
		waitNSec(1);
		Assert.assertTrue(Pattern.matches(msg, selUtils.getObject(txtAreaLoc)
				.getAttribute("value").trim()));
	}

	/**
	 * Click on the Edit icon in the 'Actions' column of the table for the
	 * scenario name 'SCNWZD_2102' and verify
	 */
	public void vTabsWithData(String sceName, String browser) {
		selectMaxSizeinTable("sce_sel_max_res_css", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		selUtils.clickOnWebElement("sce_edit_xpath", "Name", sceName, sceName
				+ " edit");
		selUtils.verifyBreadCrumb(edtScnwzdBrdcrm + sceName);
		selUtils.clickOnWebElement("sce_telim_tab_xpath",
				Terminals.techngyLstItems[2] + " tab");
		for (int cnti = 0; cnti < teliSet.length; cnti++) {
			waitMethods.waitForElementDisplayed("sce_teli_hlp_liks_xpath");
			Assert.assertEquals(
					selUtils.getLstItems(
							selUtils.getObjects("sce_teli_hlp_liks_xpath"))
							.get(cnti), teliSet[cnti]);
		}
			logger.info("Verified, all items are present under Telium tab");	
			
		txtAreaEdtScn("sce_teli_txt_area_id", switMsg);
		selUtils.clickOnWebElement("sce_u32_tab_xpath",
				Terminals.techngyLstItems[1] + " tab");
		for (int cnti = 0; cnti < u32Set.length; cnti++) {
			waitMethods.waitForElementDisplayed("sce_u32_hlp_liks_xpath");
			Assert.assertEquals(
					selUtils.getLstItems(
							selUtils.getObjects("sce_u32_hlp_liks_xpath")).get(
									cnti), u32Set[cnti]);
		}
			logger.info("Verified, all items are present under U32 tab");
		
		txtAreaEdtScn("sce_u32_txt_area_id", dispMsg);
		selUtils.clickOnWebElement("sce_wince_tab_xpath",
				Terminals.techngyLstItems[3] + " tab");
		for (int cnti = 0; cnti < wincSet.length; cnti++) {
			waitMethods.waitForElementDisplayed("sce_winc_hlp_liks_xpath");
			Assert.assertEquals(
					selUtils.getLstItems(
							selUtils.getObjects("sce_winc_hlp_liks_xpath"))
							.get(cnti), wincSet[cnti]);
		}
			logger.info("Verified, all items are present under WINCE tab");
		
		txtAreaEdtScn("sce_winc_txt_area_id", delmsg);
		selUtils.clickOnWebElement("sce_tetra_tab_xpath", tetraTab + " tab");
		for (int cnti = 0; cnti < tetraSet.length; cnti++) {
			waitMethods.waitForElementDisplayed("sce_tetra_hlp_liks_xpath");
			/*
			 * Assert.assertEquals( selUtils.getLstItems(
			 * selUtils.getObjects("sce_tetra_hlp_liks_xpath")) .get(cnti),
			 * tetraSet[cnti]);
			 */

			Assert.assertTrue(
					selUtils.getLstItems(
							selUtils.getObjects("sce_tetra_hlp_liks_xpath"))
							.contains(tetraSet[cnti]), "Expected "
									+ tetraSet[cnti] + " value is not displayed.");
		}
			logger.info("Verified, all items are present under TERTA tab");

		txtAreaEdtScn("sce_winc_txt_area_id", delmsg);
		selUtils.clickOnWebElement("sce_res_tab_xpath", reslt);
		for (int cnti = 0; cnti < reslSet1.length; cnti++) {
			Assert.assertTrue(selUtils.getObject("sce_resul_area_id")
					.getAttribute("value").contains(reslSet1[cnti]));
		}
			logger.info("Verified, XML data under RESULT tab are present");
		
	}

	/**
	 * Validate the Scenario for given text area of the TELIUM, U32 and WINCE
	 * 
	 * @param locator
	 *            ,tab and valMsg
	 */
	public void validateScenario(String tab, String valMsg) {
		selUtils.vExpValContains("sce_tel_val_pak_msg_id", tab + valSyntx);
		selUtils.vExpValContains("sce_tel_val_pak_msg_id", valMsg);
		selUtils.vExpValContains("sce_tel_val_pak_msg_id", cmpltVal);

	}

	/**
	 * verify table data
	 * 
	 * @param tabLoc
	 *            , expWebEle, helpIconLoc, allTablistLoc, enableTxt and disTxt
	 * @throws AWTException
	 */
	public void vEditTabData(String tabLoc, String expWebEle,
			String helpIconLoc, String allTablistLoc, String[] enableTxt,
			String[] disTxt) throws AWTException {

		selUtils.clickOnWebElement(tabLoc, expWebEle);
		verifyWebIconToolTip(helpIconLoc, clkForHelp);
		List<WebElement> tablists = selUtils.getObjects(allTablistLoc);
		for (int cnti = 0; cnti < tablists.size(); cnti++) {
			boolean enable = false;
			String appTxt = tablists.get(cnti).getText();
			for (int cntj = 0; cntj < enableTxt.length; cntj++) {
				if (appTxt.equals(enableTxt[cntj])) {
					logger.info("Verified the text " + appTxt
							+ " present under the Tab");
					enable = true;
					break;
				}
			}
			if (enable == false) {
				for (int k = 0; k < disTxt.length; k++) {
					if (appTxt.equals(disTxt[k])) {
						List<WebElement> list = tablists.get(cnti)
								.findElements(By.tagName("div"));
						Assert.assertTrue(list.get(1).getAttribute("class")
								.contains("arrowlistmenuA_Inactiv"), appTxt
								+ " is enabled");
						logger.info("Verified " + appTxt + " is disabled");
						break;

					}
				}
			}

		}
		// selUtils.vExpValPresent("sce_saveIconInfo_xpath", edtPageBottonInfo);
		selUtils.vExpValPresent("sce_saveIconInfo_xpath", nam, savTxt,
				edtPageBottonInfo);
	}

	/**
	 * verify edit scenario tabs data
	 * @param tabLoc, expWebEle, helpIconLoc, allTablistLoc, enableTxt and
	 * @throws AWTException
	 */
	public void vEditTabTexts(String tabLoc, String expWebEle,
			String helpIconLoc, String allTablistLoc, String[] enableTxt)
					throws AWTException {
		selUtils.clickOnWebElement(tabLoc, expWebEle);
		verifyWebIconToolTip(helpIconLoc, clkForHelp);
		List<WebElement> tablists = selUtils.getObjects(allTablistLoc);
		for (int cnti = 0; cnti < tablists.size(); cnti++) {
			String appTxt = tablists.get(cnti).getText();
			for (int cntj = 0; cntj < enableTxt.length; cntj++) {
				if (appTxt.contains(enableTxt[cntj])) {
					logger.info("Verified the text " + appTxt
							+ " present under the Tab");
					break;
				}
			}
		}
		//	selUtils.vExpValPresent("sce_saveIconInfo_xpath", nam, savTxt, edtPageBottonInfo);
	}

	
	/**
	 * verify edit page data
	 * @param scname, ownrselct
	 */
	/*public void editItemVerification(String scname, String ownrselct,
			String ownrselct_xpath) {
		try {
			// TODO Auto-generated method stub
			selUtils.clickOnWebElement("sce_edit_xpath", "Name", scname, edit);
			selUtils.verifyElementDisp("sce_edit_backToListBtn_id", backToList);
			vExpRadButtAdVal("sce_edit_ingenico_radBtn_id",
					"sce_edt_ingenicoBtnVal_xpath", ownerSecs[0]);
			vExpRadButtAdVal("sce_edit_autoEntity_radBtn_id",
					"sce_edt_autoEntBtnVal_xpath", ownerSecs[1]);
			vExpRadButtAdVal("sce_edit_autoTest_radBtn_id",
					"sce_edt_autoTestBtnVal_xpath", ownerSecs[2]);
			selUtils.vRadButtSelected(ownrselct_xpath, ownrselct);
			selUtils.vExpValPresent("sce_editName_val_xpath", scname);
			vGrpButts("edt_scn_btn_xpath", scnActBtns, scenarioActions[1]);
			vGrpButts("edt_scn_btn_xpath", comdActions, scenarioActions[2]);
			vEditTabTexts("sce_telim_tab_xpath", Terminals.techngyLstItems[2],
					"sce_tel_helpIcon_xpath", "sce_telTxts_xpath", teliSet1);
			vEditTabTexts("sce_u32_tab_xpath", Terminals.techngyLstItems[1],
					"sce_u32_helpIcon_xpath", "edt_u32txts_xpath", u32Set);
			vEditTabTexts("sce_wince_tab_xpath", Terminals.techngyLstItems[3],
					"sce_wince_helpIcon_xpath", "sce_winceTxts_xpath", wincSet);
			vEditTabTexts("sce_tetra_tab_xpath", tetraTab,
					"sce_tetra_helpIcon_xpath", "sce_tetraTxts_xpath", tetraSet);
			selUtils.vExpValPresent("sce_saveIconInfo_xpath", nam, savTxt, edtPageBottonInfo);
		} catch (Throwable t) {
			handleException(t);
		}
	}
*/
	/**
	 * view and verify page of particular scenario number
	 * @param scname
	 */

	public void vscnview(String scname) {
		// TODO Auto-generated method stub
		objScrollDwn("scelist_id");
		selUtils.clickOnWebElement("sce_view_xpath", "Name", scname, scview);
		vModWinDisp(scview);
		Assert.assertEquals(
				selUtils.getObject("sce_view_msg_id").getAttribute("value")
				.trim(), scview_msg,
				"The expected message is not displayed in expected '"
						+ scview_msg);
		selUtils.verifyElementDisp("sce_view_result_cls_id", closeButton);
		selUtils.clickOnWebElement("sce_view_result_cls_id", closeButton);

	}

	/**
	 * verifies breadcrumb, selected radio button, command actions, scenario actions and scenario name field value on edit scenario page
	 * @param radButtLoc, scnEdtRadBTn, ownSecs and  ownSelected
	 */
	public void vedtScePage(String expVal,String[] radButtLoc, String[] scnEdtRadBTn, String[] ownSecs, String ownSelectedLoc, String ownSelected ) 
	{	
	selUtils.verifyBreadCrumb(edtScnwzdBrdcrm + expVal);
	selUtils.verifyElementDisp("sce_edit_backToListBtn_id", backToList);
	selUtils.vExpValPresent("sce_editName_val_xpath", expVal);
	for(cnti = 0; cnti <ownSecs.length-1; cnti++) {
		vExpRadButtAdVal(radButtLoc[cnti], scnEdtRadBTn[cnti], ownSecs[cnti]);
	}
	selUtils.vRadButtSelected(ownSelectedLoc, ownSelected);
	vGrpButts("edt_scn_btn_xpath", scnActBtns, scenarioActions[1]);
	vGrpButts("edt_scn_btn_xpath", comdActions, scenarioActions[2]);
	}


	/**
	 * verifies 'click for help icon', texts under TELIUM, U32, WINCE and TETRA tabs.
	 * Also erifies information message at the bottom of the page. 
	 * @param tabLoc, tabName, helpLoc, tabTextsLoc, tabsTexts 
	 */
	public void vedtTabTxts(String[] tabLoc, String[] tabName, String[] helpLoc, String[] tabTextsLoc, String[][] tabsTexts) throws AWTException

	{
		for(cnti=0; cnti<tabLoc.length; cnti++)
		{
			vEditTabTexts(tabLoc[cnti], tabName[cnti+1], helpLoc[cnti], tabTextsLoc[cnti], tabsTexts[cnti]);
		}
		selUtils.vExpValPresent("sce_saveIconInfo_xpath", nam, savTxt, edtPageBottonInfo);
	}
	
}


