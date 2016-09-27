package com.ingenico.tam.testsuite.accesscode;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/testsuite/accesscode/ACCD1101.java $
	$Id: ACCD1101.java 7275 2014-05-20 10:53:18Z shkumar $
 */
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ingenico.tam.objects.AccessCode;
import com.ingenico.tam.objects.CallScheduling;
import com.ingenico.tam.objects.Estates;
import com.ingenico.tam.objects.Merchants;
import com.ingenico.tam.objects.ObjectFactory;
import com.ingenico.tam.objects.Terminals;

/**
 * Create and delete an access code.
 */
public class ACCD1101 extends AccessCode {

	String [] colVal={"virtual_pos_\\d{9}","UNK","UNK","virtual_pos_\\d{9}"};
	String tabData[][]={{ACCODE,"9"},{CDNAME, "ACCD_1101"},{TOKENNAME,"1101"},
			{sts,created}};
	/**
	 * TAM-9263:ACCD_1101 : Create and delete an access code
	 */
	@Parameters({"browser"})
	@Test
	public void accd1101(String browser){
		try {

			logger.info("ACCD_1101 Execution started");
			login(config.getProperty("superuser"),config.getProperty("superuserPassword"));


			//Delete if same access code already exist on Estate page.
			aCode = "ACCD_1101"; tokenNo = "1101";
			startingSetup("estates_link", Estates.breadCrumbEstates);
			ObjectFactory.getEstateInstance().delEstAccCod(aCode);

			logger.info("Step 1 :");
			/*Click on 'Access Code' in the left menu of the TMS page
			The 'TMS >> Access Code >> Auto_Test' page is displayed*/			
			pageNavigationAndvBreadCrumb("accd_link", ACCDBRDCRUMB);
			
			logger.info("Step 2 :");
			/*Click on the 'Create access code' popup verification*/
			vCreAccCodWin();

			/*Create access code. Set the code name field to ACCD_1101.*/
			logger.info("Step 3 :");
			//selUtils.populateInputBox("accd_name_xpath", aCode);
			addAdVerifyVal("accd_name_xpath", aCode, CDNAME);
			
			//Set the 'Token number' field to '1101'
			logger.info("Step 4 :");
			//selUtils.populateInputBox("accd_token_time_id", tokenNo);
			addAdVerifyVal("accd_token_time_id", tokenNo, TOKENNAME);
			
			/*Click on the 'Generate' button.Then it displays the message 
			 *'The access code has been created.*/
			logger.info("Step 5 :");
			cnfmPopupActContainsMsg("accd_gen_id", "accd_gen_succ_msg_id", ACCDGENSUCCMSG);
			
			/*Click on 'Close' button.The modal window is closed.Verify table
			 on 'TMS >> Access code >> Auto_Test' page with expected value*/
			logger.info("Step 6 :");
			verifySpecifiedWinNotDisp("accd_gen_succ_cls_id");
			selUtils.verifyBreadCrumb(ACCDBRDCRUMB);
			selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css",
					editPageDispTxt);
			selUtils.vExpValPresent("accd_code_nme_xpath",nam,aCode,aCode);
			Assert.assertEquals(selUtils.getObjectDirect(By.xpath(selUtils.getPath("accd_code_nme_xpath").replace(nam,aCode))).getText().length(),9);
			/*vDateCol("accd_code_cre_dat_xpath", aCode);
			vDateCol("accd_code_upd_dat_xpath", aCode);
			selUtils.vExpValPresent(selUtils.getObjectDirect(By.xpath(selUtils.getPath("accd_code_tokn_xpath").replace(nam,aCode))), tokenNo);*/
			vAccdColVal("accd_fieldVal_xpath", aCode,crtnDate);
			vAccdColVal("accd_fieldVal_xpath", aCode,updDate);
			vAccdColVal("accd_fieldVal_xpath", aCode,TOKENNAME);
			
			selUtils.vExpIcon("accd_edit_icn_xpath", aCode, edit);
			selUtils.vExpIcon("accd_del_icn_xpath", aCode, delete);
			selUtils.vExpIcon("accd_ren_icn_xpath", aCode, modify);
			
			//Delete access code window verification
			logger.info("Step 7, 8  :");
			vDelAccCodWin(aCode);

			//Delete access code window
			logger.info("Step 9, 10, 11 :");
			delAccCodFrmTable(aCode, browser);

			//estate page verification
			logger.info("Step 12 :");
			/*selUtils.clickOnWebElement("estates_link", Estates.estLbl);
			selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);*/
			pageNavigationAndvBreadCrumb("estates_link", Estates.breadCrumbEstates);
			selUtils.vExpValPresent("est_acc_code_tokn_xpath",nam,aCode, aCode);
			selUtils.vColVal("est_tab_col_val_xpath",aCode, frequency[2]);
			
			/*Click on the 'Edit' icon of the estate with the Name equals to 
			'ACCD_1101'.The 'TMS >> Edit estate' page is displayed with expected
			 value*/
			logger.info("Step 13 :");
			selUtils.clickOnWebElement("accd_est_edt_icn_xpath",nam,aCode ,edit+" "+aCode);
			selUtils.verifyBreadCrumb(Estates.breadCrumbEdit);
			selUtils.vExpValPresent("accd_edit_estate_name_id",aCode);
			selUtils.vExpValPresent("term_val_xpath",frequency[2]);
			selUtils.vEleNotPresent("accd_xpath", ACCODE);

			//verify corresponding terminal
			logger.info("Step 14 :");
			selUtils.clickOnWebElement("term_val_xpath",Terminals.terminal);
			selUtils.verifyBreadCrumb(Terminals.breadCrumEditTerm);
			selUtils.vBackToLstButt();
			selUtils.verifyElementDisp("accd_edtterm_est_css", estate);
			selUtils.verifyElementDisp("accd_edtterm_tech_css", Terminals.techngyLbl);
			selUtils.verifyElementDisp("accd_edtterm_model_css", "Model");
			Assert.assertTrue(selUtils.getObject("accd_edtterm_sign_css").getText().trim().matches(colVal[0]));
			selUtils.verifyElementDisp("accd_edtterm_part_css", Terminals.partNumLbl);
			selUtils.verifyElementDisp("accd_edtterm_srl_css", Terminals.srNumLbl);
			selUtils.verifyElementDisp("accd_edtterm_sts_css", sts);
			Assert.assertTrue(selUtils.getObject("accd_edtterm_name_css").getText().trim().matches(colVal[0]));
			vGrpButts("edt_trm_act_butts_xpath", Terminals.edtTrmActs, Terminals.edtTrmActButtsLbl);
			vGrpButts("edt_trm_tsk_butts_xpath", Terminals.edtTskButts, Terminals.edtTskButtsLbl);
			vExpButtInGrp("edt_trm_cnfg_butts_xpath", CallScheduling.CALLSCHD, Terminals.edtTrmCfgButLbl);	
			vExpTabFocused(Merchants.swPak);
			selUtils.vExpNotFocusedTabs(edtaccdTrmTabs);

			/*Click on 'Back To List' and 'Back To Estate' button.The 
			'TMS>>Edit estate' page is displayed*/
			logger.info("Step 15 :");
			selUtils.clickOnWebElement("back_list_link", bckToLst);
			/*selUtils.clickOnWebElement("back_est_link", BCKTOEST);
			selUtils. verifyBreadCrumb(Estates.breadCrumbEdit);*/
			pageNavigationAndvBreadCrumb("back_est_link", Estates.breadCrumbEdit);
			/*delete corresponding estate*/
			logger.info("Step 16, 17, 18 :");
			ObjectFactory.getEstateInstance().deleteEstFrmEdit();
			vExpValNotPresent("est_acc_code_tokn_xpath",nam,ACCODE, ACCODE);

			logger.info("ACCD_1101 successfully executed");		

		} catch (Throwable t) {
			handleException(t);
		}
	}
}