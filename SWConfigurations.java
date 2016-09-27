package com.ingenico.tam.objects;

/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/SWConfigurations.java $
 $Id: SWConfigurations.java 14117 2015-07-10 11:22:28Z amsingh $
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * SWConfigurations Class - Methods related to SWConfigurations module
 */
public class SWConfigurations extends TestBase {

	public static String swpak1 = "Auto_SW_Configuration_1",
			swpak3 = "Auto_SW_Configuration_3",
			configFailMsg = "The package 'Auto_SW_Conf_Package_2 - 1.0' is wrong. Add a file before creating a task.",
			confgSuccMsg = "The configuration has been added",
			swCfgPak1 = "Auto_SW_Conf_Package_1 - 1.0",
			swCfgPak3 = "Auto_SW_Conf_Package_3 - 1.0",
			delSwCnfgWin = "Delete a configuration",
			breadCrumbSwCnfg = "TMS » SW Configurations",
			swCnfgDelMsg = "Delete the current configuration",
			swCnfgDelResMsg = "The configuration has been deleted",
			crCnfgButt = "Create a configuration",
			crCnfgResMsg = "The configuration has been created",
			addPkgCnfgResMsg = "The product package has been added",
			activateWind = "Activate a configuration",
			activateTheWind = "Activate the configuration",
			cnfgActvResMsg = "The configuration has been activated",
			configError = "A configuration must be selected!",
			swCnfgprcmsg = "Adding of the selected configuration in progress.",
			addSwCnfgButt = "Add SW config.",
			swList2 = "Auto_SW_Configuration_2",
			brdCrbdtSwCfg = "TMS » Edit SW Configuration",
			cnfgErrMsg = "The package 'Auto_SW_Conf_Package_2_2 - 1.0' is wrong. Add a file before creating a task",
			addSwCfgMsg = "Select a configuration name and click on the 'Add' button",
			pakVer10 = "1.0",
			addMod = "Adding modules",
			configName = "Configuration name",
			configTemp = "Configuration template",
			stasGreen = "green",
			activated = "ACTIVATED",
			sfEdtToolTip = "Edit the current configuration",
			act = "Activate",
			swCfdelPopUpMsg = "Delete the configuration",
			swExisErr = "Auto_SW_Configuration_1 configuration is already exist",
			swActToolTipMsg = "Activate the current configuration",
			findWin = "Find Window",
			swcfgUnautChErMg = "This field must contain only a-z,A-Z, 0-9,. ,- ,_ characters",
			estSwcnfgAct = "Configuration actions",
			swPak = "Auto_SW_Configuration_*",
			modcnfgSuccMsg = "The configuration has been updated",
			edSwcfDlTolTipMg = "Remove the product package from this configuration",
			fidSwConfg = "Find SW configurations",
			modCnfg = "Modify a configuration",
			cnfgActs = "Configuration actions",
			addModls = "Adding modules",
			swCfgPak7Ver = Packages.pac2Options[7],
			swCfgPak7 = Packages.paks2[7],
			toDate = "To date",
			creDatFrm = "Creation date from",
			cnfgSuccMsg = "The configuration has been assigned to the merchant",
			cnfgDupMsg1 = "- The configuration ",
			cnfgDupMsg2 = " is already associated with the merchant ",
			swConfEr = "Configuration with same name already exist",
			pakgs = "Packages", campaigName=  "Campaign name",
			swconfgact = "SW configuration actions";

	public static String[] configNamesList = { swpak1, swpak3 },
			swCfgPaks = { "Auto_SW_Conf_Package_1 - 1.0",
					"Auto_SW_Conf_Package_3 - 1.0" },
			swList = { "Auto_SW_Configuration_2_1", "Auto_SW_Configuration_2_3" },
			sWCnfgs = { swpak1, "Auto_SW_Configuration_2", swpak3,
					"Auto_SW_Configuration_2_1", "Auto_SW_Configuration_2_2",
					"Auto_SW_Configuration_2_3" }, expSWCnfgPkgs = {
					"Auto_SW_Conf_Package_1 - 1.0",
					"Auto_SW_Conf_Package_2 - 1.0",
					"Auto_SW_Conf_Package_3 - 1.0",
					"Auto_SW_Conf_Package_2_1 - 1.0",
					"Auto_SW_Conf_Package_2_2 - 1.0",
					"Auto_SW_Conf_Package_2_3 - 1.0" }, swConfPackage = {
					"Auto_SW_Conf_Package_2_1", "Auto_SW_Conf_Package_2_3" },
			swCnfPkgVer10 = { "Package added: Auto_SW_Conf_Package_2_1 - 1.0",
					"Package added: Auto_SW_Conf_Package_2_3 - 1.0" },
			swConfigColHdr = { "Description", "User login", sts, crtnDate,
					updDate, actions }, status = { "CREATED", activated },
			swcnfgActBut = { modify, delete }, edtSwcfIntComs = {
					Packages.packName, version, crtnDate }, swConfPackage3 = {
					swConfPackage[0], "Auto_SW_Conf_Package_2_2",
					swConfPackage[1] },
			swcnfgActButs = { modify, delete, act }, edtSwcfIntColms = {
					edtSwcfIntComs[0], edtSwcfIntComs[1], edtSwcfIntComs[2],
					updDate }, edtSwcfgAddMod = { taskTypes[2] },
			edtSwcfIntColm = { edtSwcfIntComs[0], edtSwcfIntComs[1],
					edtSwcfIntComs[2], actions }, configLst = ArrayUtils
					.addAll(swList, configNamesList);

	public String swcfDesc, swcfDescEdt;

	/**
	 * Method to initialize the XML files
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws FileNotFoundException, IOException {
		initialize();
	}

	/**Click on the 'Add SW config.' button of the 'Terminal tasks' 
	 *Verify all the fields and buttons.*/
	public void vAddTrmconfgDets() {
		verifyExpWinDisp("add_sw_conf_xpath", Terminals.termTskButts[4]);
		selUtils.verifyElementDisp("trm_cfg_name_id", configName);
		selUtils.verifyElementDisp("trm_immediately_xpath", immediately);
		selUtils.verifyElementDisp("trm_deffered_xpath", "deferred");
		selUtils.verifyElementDisp("trm_cfg_add_button_id", addButt);
		selUtils.verifyElementDisp("trm_adComp_cls_btn_id", "Close");
	}

	/**
	 * Click on the Drop-down list icon of the 'Configuration name' field. A
	 * drop-down list is opened showing at least 'Auto_SW_Configuration_2_1' and
	 * 'Auto_SW_Configuration_2_3'. There is no 'Auto_SW_Configuration_2' within
	 * the list.
	 */
	public void vCofigDropDnListEle() {
		selUtils.vDropDnListOpened("trm_swcfg_lst_itms_drpdwn_xpath",
				"trm_swcfg_lst_itms_xpath", "Configuration Name");
		List<WebElement> cfgNames = selUtils
				.getObjects("trm_swcfg_lst_itms_xpath");
		List<String> cfgList = new ArrayList<String>();
		for (int cnti = 0; cnti < cfgNames.size(); cnti++) {
			cfgList.add(cfgNames.get(cnti).getText());
		}
		Assert.assertTrue(cfgList.contains(swList[0]), swList[0]
				+ "is not present in list");
		Assert.assertTrue(cfgList.contains(swList[1]), swList[1]
				+ "is not present in list");
		Assert.assertFalse(cfgList.contains(swList2), swList2
				+ "is not present in list");
		logger.info(" Verified that " + swList[0] + "  " + swList[1]
				+ " are present in configuration name drop down list");
		logger.info(" Verified that " + swList2
				+ " is not present in configuration name drop down list");
	}

	/**
	 * Verify, Add Configuration functionality with specified name
	 */
	public void verifyAddConfigFuncWithAutoConfig1() {
		// Click on the 'Add SW config' button, verify, the 'Add configuration'
		// modal window is displayed.
		verifyExpWinDisp("edt_est_swconfg_xpath", Estates.estEdtTskButts[3]);

		/*
		 * Click on the 'Configuration name' Drop-down list icon and choose
		 * specified name The field is set to specified name
		 */
		setConfigName("config_jobsw1_link", swpak1);
		
		/*
		 * Click on the 'Add' button of the modal window. Verify, the modal
		 * window displays the message: 'Adding of the selected configuration in
		 * progress'. Then it displays the message 'The configuration has been
		 * added' and only a 'Close' button is available at the bottom of the
		 * modal window.
		 */
		configNameWithConfgValue(confgSuccMsg, campName);
		verifyOnlyCloseButtInSuccWin("dialog_addconfig_cls_id");

		/*
		 * Click on the 'Close' button. Verify, the modal window is closed and
		 * after an auto-refresh two new jobs appear in the 'Jobs' table. They
		 * have the following values in the different table columns: -'Job'
		 * column: Yellow ball 'Add package' for both of the jobs. 'Description'
		 * column: 'Package added: Auto_SW_Conf_Package_3 - 1.0 ' and 'Package
		 * added: Auto_SW_Conf_Package_1 - 1.0'
		 */
		verifySpecifiedWinNotDisp("dialog_addconfig_cls_id");
		ObjectFactory.getJobsInstance().verifyTwoNewJobAfterCreatedJob(
				swCfgPak1, swCfgPak3, campName);
	}

	/**
	 * Click on the 'Configuration name' Drop-down list icon and choose
	 * specified name The field is set to specified name
	 */
	public void setConfigName(String objLoc, String swval) {
		clickOnConfigDrpDnIcon();
		waitMethods.waitForElementDisplayed(objLoc);
		selUtils.getObject(objLoc).click();
		Assert.assertTrue(selUtils.getObject("cnfg_name_txtbox_css")
				.getAttribute("value").contains(swval),
				" Configuration Value is not selected as expected");
		logger.info(" Verified that Selected Configuration Value id : " + swval);
	}

	/**
	 * Click on the 'Configuration name' Drop-down list icon
	 * */
	public void clickOnConfigDrpDnIcon() {
		waitMethods.waitForElementDisplayed("config_list_xpath");
		selUtils.getObject("config_list_xpath").click();
		logger.info("Clicked on '" + SWConfigurations.configName
				+ "' drop down list icon.");
	}

	/**
	 * Keep the 'Configuration name' field set to 'None' and click on the 'Add'
	 * 'A configuration must be selected is displayed in the modal window in red
	 *  with an Error icon. The 'Configuration name' field label becomes red.
	 **/
	public void configNameWithConfgValue(String msg, String value) {
		addAdVerifyVal("campaign_id", value, Terminals.campaigName);
		selUtils.clickOnWebElement("dialog_add_config_id", addButt);
		Assert.assertTrue(selUtils.getObject("dialog_addconfig_msg_id")
				.getText().contains(swCnfgprcmsg));
		logger.info("Verified, expected message is displayed : " + swCnfgprcmsg);
		selUtils.waitForTxtPresent("dialog_addconfig_succmsg_id", msg);
		Assert.assertTrue(selUtils.getObject("dialog_addconfig_succmsg_id")
				.getText().contains(msg));
		logger.info("Verified, expected successful message '" + msg
				+ "' is displayed.");
	}

	/**
	 * Verify, a 'Configuration name' field, An 'Add' and a 'Close' buttons are
	 * displayed.
	 */
	public void verifyAddSWConfigModalWinInfo() {
		Assert.assertTrue(selUtils.getObject("cnfg_name_txtbox_css")
				.isDisplayed(), "'Configuration name' field is not displyed.");
		logger.info(" Verified, 'Configuration name' field is displyed.");
		selUtils.verifyElementDisp("dialog_add_config_id", addButt);
		selUtils.verifyElementDisp("dialog_cls_config_id", closeButton);
	}

	/**
	 * Successfully deleted all provisioning software config data and verified 
	 **/
	public void vSwCnfgDelFunc(String[] swCnfgList) {
		elements = selUtils.getObjects("swcnfg_desc_col_lst_css");
		for (int cnti = elements.size() - 1; cnti >= 0; cnti--) {
			for (itemCount = 0; itemCount < swCnfgList.length; itemCount++) {
				elements = selUtils.getObjects("swcnfg_desc_col_lst_css");
				if (elements.get(cnti).getText().equals(swCnfgList[itemCount])) {
					vExpWinDisp("swcnfg_del_icon_xpath", swCnfgList[itemCount],
							delSwCnfgWin);
					selUtils.clickOnWebElement("swcnfg_del_cnf_butt_id", delete);
					selUtils.waitForTxtPresent("swcnfg_del_res_msg_id",
							swCnfgDelResMsg);
					Assert.assertEquals(
							selUtils.getObject("swcnfg_del_res_msg_id")
									.getText(), swCnfgDelResMsg,
							"Expected deleted result message is not displayed.");
					logger.info("Verified, '" + swCnfgDelResMsg
							+ "' message is displayed as expected.");
					verifyOnlyCloseButtInSuccWin("swcnfg_del_butts_css");
					verifySpecifiedWinNotDisp("swcnfg_del_butts_css");
					if (elements.size() == 1) {
						waitNSec(7);
					}
					if (elements.size() == 2) {
						selUtils.verifyBreadCrumb(breadCrumbTMS);
						if (selUtils.getCommonObject("back_list_link")
								.isDisplayed()) {
							selUtils.clkBackToLstButt();
							selUtils.verifyBreadCrumb(breadCrumbSwCnfg);
							selUtils.waitForTxtPresent("est_disppagenos_css",
									editPageDispTxt);
						}
					}
					if (elements.size() > 2) {
						selUtils.waitForTxtPresent("est_disppagenos_css",
								editPageDispTxt);
					}
					Assert.assertFalse(
							selUtils.getLstItems(
									selUtils.getObjects("swcnfg_desc_col_lst_css"))
									.contains(swCnfgList[itemCount]),
							swCnfgList[itemCount]
									+ " is not disappear from the table.");
					logger.info("Verified '" + swCnfgList[itemCount]
							+ "' disappears from the table.");
					break;
				}
			}
		}
	}

	/**
	 * deleted all provisioning software configuration data and verify.
	 * @param swCnfgList
	 * @param browser
	 */
	public void vSwCnfgDeleteFunc(String[] swCnfgList, String browser) {

		if (waitMethods.isElementPresent("job_show_res_id")) {
			selectMaxSizeinTable("job_show_res_id", browser);
			selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
			for (int itemCount = 0; itemCount < swCnfgList.length; itemCount++) {
				TestBase.waitNSec(2);
				if (selUtils.isElementPresent("back_list_link",
						selUtils.getCommonPath("back_list_link"))) {
					TestBase.waitNSec(1);
					clkOnCnfgBkToLst();
					if(selUtils.isElementPresentxpath(selUtils.getPath(
							"edt_cf_del_icon_tltip_xpath").replaceAll("(?i)NAME",
							swCnfgList[itemCount]))) {
						delConfig(swCnfgList[itemCount]);
					}
					break;
				} 
				
			else if (selUtils.isElementPresentxpath(selUtils.getPath(
						"edt_cf_del_icon_tltip_xpath").replaceAll("(?i)NAME",
						swCnfgList[itemCount]))) {
					delConfig(swCnfgList[itemCount]);
				}
		}
			if (selUtils.isElementPresent("back_list_link",
					selUtils.getCommonPath("back_list_link"))) {
				TestBase.waitNSec(1);
				clkOnCnfgBkToLst();
				}
		}
		logger.info(" Verified that successfully deleted all provisioning software config data");
		
	}
	
	/**
	 * Delete software configuration
	 */
	public void delConfig(String scNme){
		vExpWinDisp("edt_cf_del_icon_tltip_xpath", scNme,
				delSwCnfgWin);
		selUtils.clickOnWebElement("swcnfg_del_cnf_butt_id", delete);
		selUtils.waitForTxtPresent("swcnfg_del_res_msg_id",
				swCnfgDelResMsg);
		Assert.assertEquals(
				selUtils.getObject("swcnfg_del_res_msg_id")
						.getText(), swCnfgDelResMsg,
				"Expected deleted result message is not displayed.");
		logger.info("Verified, '" + swCnfgDelResMsg
				+ "' message is displayed as expected.");
		verifyOnlyCloseButtInSuccWin("swcnfg_del_butts_css");
		verifySpecifiedWinNotDisp("swcnfg_del_butts_css");
		logger.info(scNme +"has been successfully deleted");
	}
	/**
	 * add software Configuration and verify
	 **/
	public void addCnfgFunc(String[] swCnfgLst, String[] expSwCnfgPkg) {
		for (itemCount = 0; itemCount < swCnfgLst.length; itemCount++) {
			addCnfgFunc(swCnfgLst[itemCount]);
			waitMethods.waitForElementDisplayed("cnfg_edt_dsce_css");
			String expCnfg = selUtils.getObject("cnfg_edt_dsce_css").getText()
					.trim();
			if (expCnfg.equals(sWCnfgs[0])) {
				addCnfgPkgsFunc(expSwCnfgPkg[0]);
				addCnfgPkgsFunc(expSwCnfgPkg[2]);
				cnfgActvtFunc();
				clkOnCnfgBkToLst();
			} else if (expCnfg.equals(sWCnfgs[1])) {
				addCnfgPkgsFunc(expSwCnfgPkg[0]);
				clkOnCnfgBkToLst();
			} else if (expCnfg.equals(sWCnfgs[2])) {
				addCnfgPkgsFunc(expSwCnfgPkg[0]);
				addCnfgPkgsFunc(expSwCnfgPkg[1]);
				addCnfgPkgsFunc(expSwCnfgPkg[2]);
				cnfgActvtFunc();
				clkOnCnfgBkToLst();
			} else if (expCnfg.equals(sWCnfgs[3])) {
				addCnfgPkgsFunc(expSwCnfgPkg[3]);
				addCnfgPkgsFunc(expSwCnfgPkg[5]);
				cnfgActvtFunc();
				clkOnCnfgBkToLst();
			} else if (expCnfg.equals(sWCnfgs[4])) {
				addCnfgPkgsFunc(expSwCnfgPkg[3]);
				clkOnCnfgBkToLst();
			} else if (expCnfg.equals(sWCnfgs[5])) {
				addCnfgPkgsFunc(expSwCnfgPkg[3]);
				addCnfgPkgsFunc(expSwCnfgPkg[4]);
				addCnfgPkgsFunc(expSwCnfgPkg[5]);
				cnfgActvtFunc();
				clkOnCnfgBkToLst();
			}
		}
	}

	/**
	 * Add Sw configuration addCnfgFunc
	 * @param swCnfgDesc
	 * */
	public void addCnfgFunc(String swcfDesc) {
		waitMethods.waitForElementDisplayed("cnfg_crt_xpath");
		verifyExpWinDisp("cnfg_crt_xpath", crCnfgButt);
		selUtils.populateInputBox("cr_cnfg_desc_txtbox_id", swcfDesc);
		confrmPopupActionMsg("cr_cnfg_add_butt_id", "cr_cnfg_proc_msg_id",
				"cr_cnfg_res_msg_id", processingMsg, crCnfgResMsg);
		verifyOnlyCloseButtInSuccWin("cr_swcnfg_succ_cls_butt_id");
		verifySpecifiedWinNotPresent("cr_swcnfg_succ_cls_butt_id");
		selUtils.verifyBreadCrumb(brdCrbdtSwCfg + " » " + swcfDesc);
	}

	/**
	 * Click on the 'Add package' button,Click on the 'Add' button,
	 * Set the 'Package name' field to 'Auto_Package_x_x - x.x',
	 * Click on the 'Add' button and verify
	 */
	public void addCnfgPkgsFunc(String expSwCnfgPkg) {
		String subStr = "", expStr = "";
		waitMethods.waitForElementDisplayed("add_swcnfg_pak_xpath");
		verifyExpWinDisp("add_swcnfg_pak_xpath", Packages.addPak);
		waitMethods.waitForElementDisplayed("pak_id");
		selUtils.selectItem(selUtils.getObject("pak_id"), expSwCnfgPkg);
		selUtils.vExpValNotPresent("add_pak_err_icon_id",mandFldErrMsg);
		webEleExpBckgrdClr("pak_id", blk, "rgba(255, 255, 255");
		confrmPopupActionMsg("add_pak_id", "dialogbox_addpac_msg_id",
				"merc_pak_add_succ_msg_id", processingMsg, addPkgCnfgResMsg);
		verifyOnlyCloseButtInSuccWin("addpackage_successbuttons_css");
		verifySpecifiedWinNotDisp("addpackage_successbuttons_css");
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		subStr = expSwCnfgPkg.substring(expSwCnfgPkg.indexOf(" "));
		expStr = expSwCnfgPkg.replace(subStr, "");
		Assert.assertTrue(selUtils.getListItems("addpkg_intname_lst_css")
				.contains(expStr), expStr
				+ " is not appear in the 'Packages' table.");
		logger.info("Verified '" + expStr
				+ "' appears in the 'Packages' table.");
	}

	/**
	 * According to the 'Active' table column , click on the 'Activate'
	 * button of the 'Configuration actions' button sections. and verify
	 **/
	public void cnfgActvtFunc() {
		verifyExpWinDisp("cnfg_actvt_butt_xpath", activateWind);
		cnfActvSubFunc();
	}

	/**
	 * According to the 'Active' table column  click on the 'Activate'
	 * button of the 'Configuration actions' button sections. and verify
	 **/
	public void cnfActvSubFunc() {
		confrmPopupActionMsg("cnfg_actv_cnfm_butt_id",
				"cnfg_actv_proce_msg_id", "cnfg_actv_res_msg_id",
				processingMsg, cnfgActvResMsg);
		verifyOnlyCloseButtInSuccWin("cnfg_actv_butts_css");
		verifySpecifiedWinNotPresent("cnfg_actv_butts_css");
	}

	/**
	 * Click on the 'Back To List' button. Verify, the 'TMS >> SW
	 * Configurations' page is displayed
	 **/
	public void clkOnCnfgBkToLst() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				selUtils.getCommonObject("back_list_link"));
		selUtils.verifyBreadCrumb(breadCrumbSwCnfg);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
	}

	/**
	 * Verify software configuration window vSwCnfgActiWin
	 * @param cofgDesc
	 */
	public void vSwCnfgActiWin(String cofgDesc) {
		selUtils.getObjectDirect(
				By.xpath(selUtils.getPath("cf_act_icon_xpath").replace("Name",
						cofgDesc))).click();
		vActWindInfo();
	}

	/**
	 * Verify, the 'Activate a configuration' modal window is displayed
	 * containing:-The message: 'Activate the configuration', -A 'Confirm' and a
	 * 'Close' button. Click on the 'Close' button,The modal window is closed.
	 **/
	public void vActWindInfo() {
		vModWinDisp(activateWind);
		selUtils.vExpValPresent("sw_acti_cnfg_msg_id", activateTheWind);
		selUtils.verifyElementDisp("cnfg_actv_close_butt_id", closeButton);
		selUtils.verifyElementDisp("cnfg_actv_cnfm_butt_id", confirm);
		verifySpecifiedWinNotPresent("cnfg_actv_close_butt_id");
	}

	/**
	 * Verify, a 'Description' field, An 'Create' and a 'Close' buttons are
	 * displayed.
	 */
	public void vCrtConfigModalWinInfo() {
		Assert.assertTrue(selUtils.getObject("cr_cnfg_desc_txtbox_id")
				.isDisplayed(), "'Description name' field is not displyed.");
		logger.info(" Verified, 'Description name' field is displyed.");
		selUtils.verifyElementDisp("cr_cnfg_add_butt_id", addButt);
		selUtils.verifyElementDisp("cr_cnfg_cls_butt_id", closeButton);
		selUtils.getObject("cr_cnfg_cls_butt_id").click();
	}

	/**
	 * Delete the existing SWConfiguration.
	 * 
	 * @param val
	 * @param browser
	 */
	public void vDelSWCF(String val, String browser) {
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		xpath = selUtils.getPath("swcnfg_del_icon_xpath").replace("Name", val);
		if (selUtils.isElementPresentxpath(xpath)) {
			vExpWinDisp("swcnfg_del_icon_xpath", val, delSwCnfgWin);
			clkDelAndVerfy();
		}
	}

	/**
	 * Delete the swfg form edit page deleteSwcfgFromEditPage
	 */
	public void deleteSwcfgFromEditPage() {
		verifyExpWinDisp("delete_link", delSwCnfgWin);
		clkDelAndVerfy();
	}

	/**
	 * Update the swfg modSwcfgEditPage
	 * @param swcfDesc
	 */
	public void modSwcfgEditPage(String swcfDesc) {
		waitMethods.waitForElementDisplayed("modify_link");
		verifyExpWinDisp("modify_link", modCnfg);
		modSwcfg(swcfDesc);
	}

	/**
	 * Update the swfg with new name modSwcfg
	 * @param swcfDesc
	 */
	public void modSwcfg(String swcfDesc) {
		selUtils.populateInputBox("cr_cnfg_desc_txtbox_xpath", swcfDesc);
		selUtils.vExpValNotPresent("add_pak_err_icon_id",
				mandFldErrMsg);
		webEleExpBckgrdClr("cr_cnfg_desc_txtbox_xpath", blk, "rgba(255, 255, 255");
		confrmPopupActionMsg("cr_cnfg_conf_butt_id", "cr_cnfg_upd_proc_msg_id",
				"cr_cnfg_updt_res_msg_id", processingMsg, modcnfgSuccMsg);
		verifyOnlyCloseButtInSuccWin("mod_swc_cls_id");
		verifySpecifiedWinNotPresent("mod_swc_cls_id");
		selUtils.verifyBreadCrumb(brdCrbdtSwCfg + " » " + swcfDesc);
		selUtils.vExpValContains("cr_cnfg_desc_txt_xpath", swcfDesc);
	}

	/**
	 * Click on the 'Confirm' button,verify SW configuration disappears from
	 * the table (you might need to look in all the pages of the table).
	 */
	public void clkDelAndVerfy() {
		selUtils.getObject("swcnfg_del_cnf_butt_id").click();
		// Assert.assertEquals(selUtils.getObject("swcnfg_del_msg_id").getText(),
		// swCnfgDelMsg, "Expected delete message is not displayed.");
		// logger.info("Verified, '" + swCnfgDelMsg +
		// "' message is displayed as expected.");
		selUtils.waitForTxtPresent("swcnfg_del_res_msg_id", swCnfgDelResMsg);
		Assert.assertEquals(selUtils.getObject("swcnfg_del_res_msg_id")
				.getText(), swCnfgDelResMsg,
				"Expected deleted result message is not displayed.");
		logger.info("Verified, '" + swCnfgDelResMsg
				+ "' message is displayed as expected.");
		verifyOnlyCloseButtInSuccWin("swcnfg_del_butts_css");
		verifySpecifiedWinNotDisp("swcnfg_del_butts_css");
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		selUtils.verifyBreadCrumb(breadCrumbSwCnfg);
		Assert.assertFalse(
				selUtils.getLstItems(
						selUtils.getObjects("swcnfg_desc_col_lst_css"))
						.contains(swcfDesc), swcfDesc
						+ " is not disappear from the table.");
		logger.info("Verified '" + swcfDesc + "' disappears from the table.");
	}

	/**
	 * vSwcnfgFind Verify if all element are displayed in find window.
	 */
	public void vSwcnfgFind() {
		selUtils.verifyElementDisp("find_but_xpath", findButt);
		selUtils.getObject("find_but_xpath").click();
		waitMethods.waitForElementDisplayed("swcnfg_desc_id");
		selUtils.verifyElementDisp("swcnfg_desc_id", desc);
		vDrpDnDefltSelcItem("swcnfg_status_id", all, swConfigColHdr[2]);
		selUtils.verifyElementDisp("swcnfg_cre_datefrm_id", creDatFrm);
		selUtils.verifyElementDisp("swcnfg_cre_dateto_id", toDate);
		selUtils.verifyElementDisp("reset_link", reset);
		selUtils.verifyElementDisp("srch_link", srch);
	}

	/**
	 * Verify no of row in table
	 * @param locator
	 * @param size
	 */
	public void vNoOfRowsInTable(String locator, int size) {
		Assert.assertTrue(selUtils.getObjects(locator).size() == size,
				"More then " + size + " is displyed in table");
		logger.info("Search result is showing only " + size + " name");
	}

	/**
	 * Verify status colour
	 * @param sceName
	 */
	public void vStautColor(String xpathLoc, String swcgName, String colour) {
		Assert.assertTrue(
				selUtils.getObjectDirect(
						By.xpath(selUtils.getPath(xpathLoc).replace("Name",
								swcgName))).getAttribute("class")
						.contains(colour), "Stauts not in " + colour
						+ " colour");
		logger.info("Verified the status color is " + colour);
	}

	/**Click on the Edit icon in the 'Actions' column of the table for
	 * the SW configuration with the Description
	 * 'Auto_SW_Configuration_2_2' and verify*/
	public void vEditSoftConfPage() {
		selUtils.clickOnWebElement("cf_edt_icon_xpath", nameLbl, sWCnfgs[4],
				Jobs.jobsTabcolsHds[0]);
		selUtils.verifyBreadCrumb(brdCrbdtSwCfg + " » " + sWCnfgs[4]);
		selUtils.vBackToLstButt();
		selUtils.vExpValPresent("cr_cnfg_desc_txtbox_id", sWCnfgs[4]);
		vCrDateFrmt(selUtils.getObject("cre_date_id"));
		selUtils.vExpValPresent("edt_use_crt_id",
				config.getProperty("superuser"));
		vGrpButts("edt_swcnfg_acts_buttons_xpath", swcnfgActButs, estSwcnfgAct);
		vGrpButts("edt_swcnfg_acts_addmod_xpath", Terminals.edtTskButt, addMod);
		selUtils.vExpValPresent("pack_link", pakgs);
		vExpColsInTab(selUtils.getTabColHds("edt_swcnfg_cols_hdrs_css"),
				edtSwcfIntColm);
		selUtils.vExpIcon("edt_cf_del_icon_xpath", swConfPackage[0], delete);
	}
   /**verify edit software add pak*/
	public void vEditSwAddPak() {
		waitMethods.waitForElementDisplayed("add_swcnfg_pak_xpath");
		verifyExpWinDisp("add_swcnfg_pak_xpath", Packages.addPak);
		waitMethods.waitForElementDisplayed("pak_id");
		selUtils.vDrpDnSelcItem("pak_id", noneVar);
		selUtils.verifyElementDisp("add_pak_id", addButt);
		selUtils.verifyElementDisp("add_pack_cls_id", closeButton);
		selUtils.clickOnWebElement("add_pack_cls_id", closeButton);
		addCnfgPkgsFunc(Packages.pkgNmDrpDnItm11);
		selUtils.verifyElementDisp("activte_id", activated);
		eleClr = selUtils.getObject("activte_id").getCssValue(
				"background-color");
		Assert.assertEquals(eleClr, "rgba(0, 57, 153, 1)",
				"The expected message is not displayed in expected '" + eleClr);
		logger.info("Verified, the expected web element displayed in expected '"
				+ eleClr);
	}
	
	/*
	 * Click on the Edit icon in the 'Actions' column of the table for
	 * the SW configuration with the DescriptionSW configuration actions
	 * 'Auto_SW_Configuration_2_3'
	 */
	public void vEditSoftSWConfPage(String browser){
		selUtils.clickOnWebElement("cf_edt_icon_xpath", nameLbl, sWCnfgs[5], Jobs.jobsTabcolsHds[0]);
		selUtils.verifyBreadCrumb(brdCrbdtSwCfg+" » "+sWCnfgs[5]);
		selUtils.vBackToLstButt();
		selUtils.vExpValPresent("cr_cnfg_desc_txtbox_id", sWCnfgs[5]);
		vCrDateFrmt(selUtils.getObject("cre_date_id"));
		selUtils.vExpValPresent("edt_use_crt_id", config.getProperty("superuser"));
		vGrpButts("edt_swcnfg_acts_buttons_xpath", swcnfgActBut, estSwcnfgAct);
		selUtils.vExpValPresent("pack_link", pakgs);
		vExpColsInTab(selUtils.getTabColHds("edt_swcnfg_cols_hdrs_css"), edtSwcfIntComs);
		selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css", editPageDispTxt);
		vExpVals(swConfPackage3, "edt_swcnfg_paks_xpath");
		
	}
	// SW Configurations page verified
	public void vSwConfigPage(String browser){
		selUtils.verifyElementDisp("find_but_xpath", findButt);
		vExpButtInGrp("sw_conf_butt_xpath", crCnfgButt, swconfgact);
		vExpColsInTab(selUtils.getTabColHds("swcf_col_hdr_css"), swConfigColHdr);
		selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css", editPageDispTxt);
		verifyListItems("swcnfg_desc_col_lst_css", sWCnfgs);
		for(itemCount = 0;  itemCount < sWCnfgs.length; itemCount++) {
			if(itemCount == 1 || itemCount == 4){
				selUtils.vExpIcon("cf_edt_icon_xpath", sWCnfgs[itemCount], Jobs.jobsTabcolsHds[0]);
				selUtils.vExpIcon("cf_act_icon_xpath", sWCnfgs[itemCount], act);
				selUtils.vExpIcon("cf_del_icon_xpath", sWCnfgs[itemCount], delete);
			} else {
				selUtils.vExpIcon("cf_edt_icon_xpath", sWCnfgs[itemCount], Jobs.jobsTabcolsHds[0]);
				selUtils.vExpIcon("cf_del_icon_xpath", sWCnfgs[itemCount], delete);
			}
		}
		
	}
}
