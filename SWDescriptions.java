package com.ingenico.tam.objects;

/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/SWDescriptions.java $
 $Id: SWDescriptions.java 14269 2015-07-22 11:30:11Z sparween $
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import com.ingenico.base.TestBase;

/**
 * SWDescriptions Class - Methods related to SWDescriptions module
 */
public class SWDescriptions extends TestBase {

	public final static String BRDCRMSFTDES = "TMS » Software Description » Auto_Test",
			SWDESACTS = "Software description actions",
			SWDESEDTTOLTIP = "Edit the current description",
			SWDESDELTOLTIP = "Deletion of the selected description",
			SWDESSUCCMSG = "The description has been created",
			SWDESMODSUCMSG = "Description information has been modified",
			ACCD = "Access code information",
			DELDES = "Delete a description",
			DELDSCSUCCMSG = "The description has been deleted",
			DSCVALS = "Description values",
			OWNERSEL = "Owner selection",
			BRDCRMSFTDSC = "TMS » Software Description",
			FNDSOFDSC = "Find software description",
			SWDESCHRERRMSG = "The field marked in red must contain alphanumeric characters",
			DUPLIERRMSG = "This application id already exist to this owner.",
			SWDESCRIPTION = "Software Description",
			ADMSN = "Administration",
			MODDESC = "Modify a description",
			ACCDCHK = "Prohibit the entry of the token at lower levels",
			TOKNTOLTIP = "Edit the current access code information",
			MODTOKN = "Modify the access code information",
			ACCDSUCCMSG = "The access code has been modified",
			MODMODALWAR = "Modify the access code information - Warning",
			ACCDINF = "Access code information of lower levels will be overwritten. Do you want to continue?",
			UNAUTHMOD = "Unauthorized modification by higher levels",
			TKNERRMSG = "The token value must be between 0 and 100.",
			AUTOSWDSCAPID1 = "AutoSWDSCAppId1",
			SWDSCPROCMMG = "Loading higher levels information in progress...";

	public static String[] swDscActions = { "Create a description" },
			swDscColsHdrs = { Jobs.jobsTabcolsHds[4], applnId, commName, owner,
					"Token", "File type", crtBy, crtnDate, updDate },
			swDscs = { "AutoSWDSC1", "AutoSWDSC2", "AutoSWDSC3", "AutoSWDSC4",
					"AutoSWDSC5", "AutoSWDSC6" }, swDsc = { "AutoSWDSC1" },
			swDsc2 = { "AutoSWDSC2" }, swDsc3 = { "AutoSWDSC3" }, owners = {
					"GROUP", "REGION", "SPONSOR" }, swAppDscs = {
					AUTOSWDSCAPID1, "AutoSWDSCAppId2", "AutoSWDSCAppId3",
					"AutoSWDSCAppId4", "AutoSWDSCAppId5", "AutoSWDSCAppId6" },
			swApp1Dscs = { swAppDscs[0] }, swApp3Dscs = { swAppDscs[2] },
			swApp36Dscs = { swAppDscs[2], swAppDscs[5] }, fileType = {
					"Application", "Firmware" }, radios = {
					"swdsc_inge_radio_xpath", "swdsc_auto_ent_xpath",
					"swdsc_auto_test_xpath" };

	public String applId = "", comName = "";

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

	/** verify software description page information */
	public void vSwDscPageInfo(String browser) {
		vSwDscPgInfo(browser);
		for (cnti = 0; cnti < swAppDscs.length; cnti++) {
			if (cnti > owners.length - 1) {
				vSwDSCOwner(swDscs[cnti], owners[cnti - 3]);
				selUtils.vColVal("swdsc_filetype_col_val_xpath", swDscs[cnti],
						fileType[1]);
			} else {
				vSwDSCOwner(swDscs[cnti], owners[cnti]);
				selUtils.vColVal("swdsc_filetype_col_val_xpath", swDscs[cnti],
						fileType[0]);
			}
			selUtils.vColVal("swdsc_token_col_val_xpath", swDscs[cnti],
					tokenNo[cnti]);
		}
	}

	/** verify software description page information */
	public void vSwDscPgInfo(String browser) {
		selUtils.verifyElementDisp("find_but_xpath", findButt);
		vGrpButts("swdsc_act_butts_xpath", swDscActions, SWDESACTS);
		vExpColsInTab(selUtils.getTabColHds("swdsc_cols_headings_css"),
				swDscColsHdrs);
		//vExpColVals(swAppDscs, browser, "swdsc_applnid_col_vals_css");
		selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css", editPageDispTxt);
		vExpVals(swAppDscs, "swdsc_applnid_col_vals_css");
		vExpVals(swDscs, "swdsc_comm_nm_col_vals_css");
		for (itemCount = 0; itemCount < swDscs.length; itemCount++) {
			selUtils.vExpIcon("exp_swdsc_edt_icon_xpath", swAppDscs[itemCount],
					edit);
			selUtils.vExpIcon("exp_swdsc_del_icon_xpath", swAppDscs[itemCount],
					delete);
		}
	}

	/**
	 * Verify the owner for the expected application ID.
	 */
	public void vSwDSCOwner(String val1, String val2) {
		path = selUtils.getPath("swdsc_exp_own_col_val_xpath").replace(nameLbl,
				val1);
		path = path.replace("Text", val2);
		waitMethods
				.waitForElementDisplayed("swdsc_exp_own_col_val_xpath", path);
		Assert.assertTrue(selUtils.isElementPresent(
				"swdsc_exp_own_col_val_xpath", path));
		logger.info("Verified, '" + val2 + "' owner is set to '" + val1 + "' "
				+ applnId);
	}

	/**
	 * vValInTable -verify the number of rows and value is present in table.
	 * @param objLoc
	 * @param val
	 * @param size
	 * @param browser
	 */
	public void vValInTable(String objLoc, String[] val, int size,
			String browser) {
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vals = selUtils.getLstItems(selUtils.getObjects(objLoc));
		for (cnti = 0; cnti < val.length; cnti++) {
			// Assert.assertTrue(vals.size()==size,
			// " The no of rows in table is not "+size);
			Assert.assertTrue(vals.contains(val[cnti]), val[cnti]
					+ " is not present in value list");
		}
		logger.info(" Verified that expected  value is present in list");
	}
	
	/**
	 * vValInTable -verify the number of rows and value is present in table.
	 */
	public void vValInTable(String objLoc, String[] val, String browser) {
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vals = selUtils.getLstItems(selUtils.getObjects(objLoc));
		for (cnti = 0; cnti < val.length; cnti++) {
			Assert.assertTrue(vals.contains(val[cnti]), val[cnti]
					+ " is not present in value list");
		}
		logger.info(" Verified that expected  value is present in list");
	}

	/**
	 * Delete the record, if same record exists in table.
	 **/
	public void delExtSWDsc(String val, String browser) {
		selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css",
				editPageDispTxt);
		xpath = selUtils.getPath("swdsc_del_col_val_xpath").replace(nameLbl,
				val);
		if (selUtils.isElementPresentxpath(xpath)) {
			vExpWinDisp("swdsc_del_col_val_xpath", val, DELDES);
			cnfmPopupActMsg("swdsc_del_cnfrm_id",
					"swdsc_del_cnfrm_succ_msg_id", DELDSCSUCCMSG);
			verifySpecifiedWinNotDisp("swdsc_del_cnfrm_succ_cls_id");
			//waitMethods.waitForElementNotPresent("swdsc_col_val_xpath");
			selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
			selUtils.verifyBreadCrumb(BRDCRMSFTDES);
			selUtils.verifyElementNotPresent("swdsc_col_val_xpath", val);
		}
	}

	/**
	 *  verify delete software description
	 **/
	public void vDelSWDsc(String applId, String browser) {
		selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css",
				editPageDispTxt);
		xpath = selUtils.getPath("swdsc_del_col_val_xpath").replaceAll(
				"(?i)NAME", applId);
		vExpWinDisp("swdsc_del_col_val_xpath", applId, DELDES);
		cnfmPopupActMsg("swdsc_del_cnfrm_id", "swdsc_del_cnfrm_succ_msg_id",
				DELDSCSUCCMSG);
		verifySpecifiedWinNotDisp("swdsc_del_cnfrm_succ_cls_id");
		selUtils.waitForElementNotPresent("swdsc_del_col_val_xpath", nam,
				applId);
		selUtils.verifyBreadCrumb(BRDCRMSFTDES);
		selUtils.vEleNotPresent("swdsc_del_col_val_xpath", applId, applId);
	}

	/**
	 * Create a software description and verify
	 * */
	public void vCrtSWDsc(String applId, String comName, String loc,
			String loc1, String val, boolean flag, String browser) {
		crtSWDscWitChek(applId, comName, loc, loc1, val, flag);
		vSWDscInTable(applId, comName, browser);
	}

	/**
	 * vSWDscInTable swdesc verify in table
	 * @param applId
	 * @param comName
	 */
	public void vSWDscInTable(String applId, String comName, String browser) {
		selUtils.verifyBreadCrumb(BRDCRMSFTDES);
		//Added selcMaxPgSz as per review comment
		selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css",
				editPageDispTxt);
		selUtils.vExpValPresent("swdsc_col_val_xpath", nameLbl, applId, applId);
		selUtils.vExpValPresent("swdsc_col_val_xpath", nameLbl, comName,
				comName);
	}

	/**
	 * crtSWDsc creation with check of swdesc successfully.
	 * @param applId
	 * @param comName
	 */
	public void crtSWDscWitChek(String applId, String comNam, String loc,
			String loc1, String val, boolean flag) {
		verifyExpWinDisp("swdsc_crt_xpath", swDscActions[0]);
		selUtils.selecRadButt(loc);
		selUtils.populateInputBox("swdsc_mod_appl_xpath", applId);
		selUtils.populateInputBox("swdsc_mod_commr_xpath", comNam);
		selUtils.selecRadButt(loc1);
		selUtils.clickOnWebElement("swdsc_crt_next_id", next);
		selUtils.populateInputBox("token_id", val);
		if ( flag==true)
		{
		selUtils.selecChkBx("swdsc_edt_accd_chk_id");
		}
		cnfmPopupActMsg("swdsc_crt_dsc_cnfrm_id", "swdsc_crt_dsc_cnfrm_msg_id",
				SWDESSUCCMSG);
		verifySpecifiedWinNotDisp("swdsc_crt_dsc_cls_id");
	}

	/**
	 * crtSWDscWitOutChek Creation without check
	 * @param applId
	 * @param comNam
	 * @param LOC
	 */
	public void crtSWDscWitOutChek(String applId, String comNam) {
		verifyExpWinDisp("swdsc_crt_xpath", swDscActions[0]);
		selUtils.selecRadButt("swdsc_auto_test_xpath");
		selUtils.populateInputBox("swdsc_mod_appl_xpath", applId);
		selUtils.populateInputBox("swdsc_mod_commr_xpath", comNam);
		selUtils.selecRadButt("swdsc_app_radio_xpath");
		selUtils.clickOnWebElement("swdsc_crt_next_id", next);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(
				By.id(selUtils.getPath("swdsc_crt_dsc_prcoss_msg_id")),
				SWDSCPROCMMG));
		cnfmPopupActMsg("swdsc_crt_dsc_cnfrm_id", "swdsc_crt_dsc_cnfrm_msg_id",
				SWDESSUCCMSG);
		verifySpecifiedWinNotDisp("swdsc_crt_dsc_cls_id");
	}

	/**Modify and reset Software description.
	 **/
	public void vRModSWDsc(String applId, String comName) {
		selUtils.selecRadButt("swdsc_auto_test_xpath");
		selUtils.populateInputBox("swdsc_mod_appl_xpath", applId);
		selUtils.populateInputBox("swdsc_mod_commr_xpath", comName);
		selUtils.selecRadButt("swdsc_firm_radio_xpath");
		selUtils.clickOnWebElement("swdsc_mod_reset_id", reset);
		selUtils.vRadButtSelected("swdsc_auto_ent_xpath", ownerSecs[1]);
	}

	/**
	 * Modify and reset Software description.
	 **/
	public void vModSWDsc(String applId, String comName, String owner,
			String fileType, String browser) {
		selUtils.selecRadButt("swdsc_auto_test_xpath");
		selUtils.populateInputBox("swdsc_mod_appl_xpath", applId);
		selUtils.populateInputBox("swdsc_mod_commr_xpath", comName);
		selUtils.selecRadButt("swdsc_firm_radio_xpath");
		cnfmPopupActMsg("swdsc_mod_cnfrm_id", "swdsc_mod_succ_msg_id",
				SWDESMODSUCMSG);
		verifySpecifiedWinNotDisp("swdsc_mod_succ_cls_id");
		selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css",
				editPageDispTxt);
		selUtils.vExpValPresent("swdsc_col_val_xpath", nameLbl, applId, applId);
		selUtils.vExpValPresent("swdsc_col_val_xpath", nameLbl, comName,
				comName);
		vSwDSCOwner(applId, owner);
		selUtils.vColVal("swdsc_ft_col_val_xpath", applId, fileType);
		selUtils.verifyBreadCrumb(BRDCRMSFTDES);
	}

	/**
	 * Create duplicate software description and verify the error message.
	 * @param applId
	 * @param comName
	 */
	public void vCrtDupliSWDsc(String applId, String comName, String radioLoc,
			String browser) {
		verifyExpWinDisp("swdsc_crt_xpath", swDscActions[0]);
		selUtils.selecRadButt(radioLoc);
		selUtils.populateInputBox("swdsc_mod_appl_xpath", applId);
		selUtils.populateInputBox("swdsc_mod_commr_xpath", comName);
		selUtils.selecRadButt("swdsc_app_radio_xpath");
		cnfmPopupActMsg("swdsc_crt_next_id", "swdsc_crt_err_msg_id",
				DUPLIERRMSG);
		verifySpecifiedWinNotDisp("swdsc_crt_dsc_cls_id");
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vOnly1InstOfExpVal("swdsc_appli_xpath", nam, applId, applId);
	}

	/**Click on the 'Search' button of the 'Find' drop-down window and
	 * verify table data
	 */
	public void vSrcVal(String browser, String[] swDsc, String locator) {
		selUtils.clickOnWebElement("srch_link", srch);
		//selUtils.waitForElementNotDisp("find_pack_xpath");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(selUtils.getPath("find_pack_xpath"))));
		selUtils.verifyElementNotDisp("find_pack_xpath", FNDSOFDSC);
		selUtils.verifyBreadCrumb(BRDCRMSFTDSC);
		vValInTable(locator, swDsc, 1, browser);

	}

	/**
	 * valiTextbxWithWildChar error validation
	 * @param eleLoc
	 * @param errMsg
	 */
	public void valiTextbxWithWildChar(String eleLoc, String errMsg) {
		for (cnti = 0; cnti < spelChar.length; cnti++) {
			selUtils.populateInputBox(eleLoc, spelChar[cnti]);
			vValFrmInputBox(eleLoc, spelChar[cnti], desc);
			verifyPopUpErrMsgWithInvaliData("srch_link",
					"swdsc_fnd_err_msg_id", errMsg, "swdsc_fnd_erricon_xpath",
					eleLoc);
			//as per review comment
			//selUtils.clickOnWebElement("reset_link", reset);
			vReset("reset_link", "swdsc_fnd_comm_xpath");
			Assert.assertTrue(selUtils.getObject(eleLoc).getAttribute("value")
					.equals(""), " expected field value is not empty.");
			logger.info("Verifed, expected field value is removed. ");
		}
	}
}
