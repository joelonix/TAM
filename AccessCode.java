package com.ingenico.tam.objects;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/AccessCode.java $
	$Id: AccessCode.java 14204 2015-07-17 07:04:56Z sparween $
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * AccessCode Class - Consists of all methods related to AccessCode module
 */
public class AccessCode extends TestBase {

	public final static String ACCDBRDCRUMB = "TMS » Access Code » Auto_Test", ACCDEDTBRDCRUMB = "TMS » Edit access code » ", ACCODE = "Access code", 
			CDNAME = "Code name", TOKENNAME = "Token number", EXPDATE = "Expiry date", ACCCODEACT = "Access code actions", ACCDHIS = "Historical", 
			CRTACCD = "Create access code", ADTOKEN = "Added token", ACCDGENSUCCMSG = "The access code has been created", ACCDGEN = "Generate",
			ACCDRENEWSUCCMSG = "The access code has been updated", CODEERRMSG = "Code name is mandatory", FINDACCCODE ="Find access code",
			TOKENEXPIREERRMSG = "Token number OR Expiry date is mandatory", INVALTOKENERRMSG = "Invalid token number - must be an strictly positive number",
			DELACCODEMSG = "Delete the access code?", ACCDDELMSG = "The access code has been deleted", EITTOLTIP ="Edit the current access code",
			DELTOLTIP="Delete the current access code", ADDTOKENERRMSG = "Invalid add token number - must be an positive number", ROTEST="Root estate",
			DELACCCODE="Delete an access code", MODACCCODE="Modify an access code", ADDTOKNERRMSG="Invalid add token number - must be a positive number",
			BCKTOEST="Back To Estate",ACCCODSUCC=".*\n.*A terminal with the signature virtual_pos_\\d{9} has been added.",REMTOKEN= "Remaining Token",VALUE="Value",
		    ACCERRMSG="Invalid add token number - must be a positive number";


	public static String[] actsButts = {modify, "Delete"}, calHisCols = {signLbl, "Start call date", "End call date", "Downloaded applications", actions},
			hisCols={"User", "Added token", "Max token number", "Remaining", crtnDate, "Update date"},accCod={"Auto_ACCD_1","Auto_ACCD_2","Auto_ACCD_3"}
	,tokNum={"1","2","3"},accTblhedr={ACCODE,CDNAME,"Token","Expiry date",hisCols[4],updDate,calHisCols[4]},
			edtaccdTrmTabs = {"Properties", statistics, "Key management", Scenarios.cmplxSce, "Call history"};

	public String aCode="", tokenNo = "", codeVal = "", codeName= "";

	/**
	 * Method to initialize the XML files of AccessCode and CommonObjects
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws FileNotFoundException, IOException{
		initialize();
	}

	/**
	 * cretAccCode create access code with or with out check box.
	 * @param aCode
	 * @param tokenNo
	 */
	public void cretAccCode(String aCode,String tokenNo, boolean flag){
		verifyExpWinDisp("acc_crt_xpath", CRTACCD);
		ObjectFactory.getEstateInstance().selectRootEst(client, "accd_rtest_opts_css");
		waitMethods.waitForElementNotPresent("accd_rtest_opts_css");
		selUtils.populateInputBox("accd_name_xpath", aCode);
		selUtils.populateInputBox("accd_token_time_id", tokenNo);
		if(flag){
			selUtils.selecChkBx("accd_edt_chk_id");
		}
		cnfmPopupActContainsMsg("accd_gen_id", "accd_gen_succ_msg_id", ACCDGENSUCCMSG);
		Assert.assertTrue(selUtils.getObject("accd_gen_succ_msg_id").getText().matches(ACCCODSUCC));
		verifySpecifiedWinNotDisp("accd_gen_succ_cls_id");
	}

	/* Click on the Edit icon in the 'Actions' column of the table for access 
	 * code with the given Code name. verify that expected breadcrum, buttons, 
	 * fields and columns are present.*/
	/**
	 * vEditAccdPageDetails - Edits Access Code Page Details
	 * @param aCode
	 * @param locator
	 */
	public void vEditAccdPageDetails(String aCode, String locator){
		selUtils.clickOnWebElement(locator , nameLbl, aCode);
		selUtils.verifyBreadCrumb(ACCDEDTBRDCRUMB+aCode);
		selUtils.vBackToLstButt();
		selUtils.verifyElementDisp("accd_acc_code_id", ACCODE);
		selUtils.vExpValPresent("accd_code_name_id",aCode);
		selUtils.vExpValPresent("accd_token_time_id",frequency[2]);
		selUtils.verifyElementDisp("accd_exp_date_xpath", EXPDATE);
		vExpColsInTab(selUtils.getTabColHds("accd_call_his_cols_css"),calHisCols);
		selUtils.verifyElementDisp("accd_his_tab_sel_xpath", ACCDHIS);
	}

	/**
	 * vCreAccCodWin 
	 * verify create access code window
	 */
	public void vCreAccCodWin(){
		verifyExpWinDisp("acc_crt_xpath", CRTACCD);
		vDrpDnDefltSelcItem("accd_rot_est_msg_id", client, ROTEST);
		selUtils.verifyElementDisp("accd_name_xpath", CDNAME);
		selUtils.verifyElementDisp("accd_token_time_id", TOKENNAME);
		selUtils.verifyElementDisp("acc_crt_exp_date_fld_id", accTblhedr[3]);
		selUtils.verifyExpIconDisplayed("accd_exp_cal_icn_xpath", accTblhedr[3]+" calender icon");
		selUtils.vChkbxsUnSlcted("accd_edt_chk_id");
		selUtils.verifyElementDisp("accd_gen_id", ACCDGEN);
		selUtils.verifyElementDisp("accd_crt_cls_butt_id", closeButton);
	}

	/**
	 * vDelAccCodWin
	 * verify delete access code window
	 */
	public void vDelAccCodWin(String aCode){
		selUtils.clickOnWebElement("accd_del_icn_xpath","Name", aCode, delete);
		vModWinDisp(DELACCCODE);
		selUtils.vExpValContains("accd_edt_del_id", DELACCODEMSG);			
		selUtils.verifyElementDisp("accd_edt_del_cnfrm_id", confirm);
		selUtils.verifyElementDisp("accd_edt_del_cls_id", closeButton);
		verifySpecifiedWinNotDisp("accd_edt_del_cls_id");
	}
	
	/**
	 * Deletes the access code and verify it is deleted from the table
	 * @param aCode
	 * @param browser
	 */
	public void delAccCodFrmTable(String aCode,String browser){
		selUtils.clickOnWebElement("accd_del_icn_xpath","Name", aCode, delete);
		cnfmPopupActContainsMsg("accd_edt_del_cnfrm_id", "accd_edt_del_succ_msg_id", ACCDDELMSG);
		verifySpecifiedWinNotDisp("accd_edt_del_succ_cls_id");
		selUtils.verifyBreadCrumb(ACCDBRDCRUMB);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vExpValNotPresent("accd_code_name_xpath", nameLbl, aCode, aCode);
	}
	
	
	/**Click on the 'Search' button of the 'Find access code' drop-down 
	   window.The 'TMS » Access Code' page is displayed
	 * @param header
	 * @param value
	 */
	public void vAcessCodenameSrcho(String header,String value)
	{
	selUtils.clickOnWebElement("srch_link", srch);
	selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
	selUtils.vExpValNotPresent("find_acc_code_lbl_xpath", FINDACCCODE);
	selUtils.verifyBreadCrumb(ACCDBRDCRUMB);
	//selUtils.vExpValPresent(getTableHeadCursVal("acodelisttabval_xpath","acodelistheader_xpath",indx,header),value);
	
}		

	/**This method will find access code column value based on header and name
	 * @param fieldLoc
	 * @param name
	 * @param headrName
	 */
	
	public void vAccdColVal(String fieldLoc, String name, String headrName){
		itemCount=webtableGetColNumOfHeader("acc_cod_hdr_css",headrName);
		xpath = selUtils.getPath(fieldLoc).replace(TestBase.nameLbl, name);
		waitMethods.waitForElementDisplayed(fieldLoc, xpath);
		path = xpath.replace("INDEX", Integer.toString(itemCount-2));		
		if(headrName==TOKENNAME)
		{
			Assert.assertTrue(selUtils.getObjectDirect(By.xpath(path)).getText().contains(tokenNo),"'Token Number' is not displayed."); 
			logger.info("Verified, expected value :'Token Number' is displayed.");
		}
		else
		{
		Assert.assertTrue(verifyDateTimeFormat(selUtils.getObjectDirect(By.xpath(path)).getText(), "MM/dd/yyyy HH:MM:ss"), 
				"'Date' is not displayed the expected format.");		
		logger.info("Verified, date field is displayed in the expected format.");
		}
	}
	}


