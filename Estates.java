package com.ingenico.tam.objects;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/Estates.java $
	$Id: Estates.java 14253 2015-07-21 11:49:41Z amsingh $
 */

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * Estates Class - Consists of all methods related to Estates module
 */
public class Estates extends TestBase {

	public static String estLbl = "Estate",  breadCrumbEdit = "TMS » Edit estate » ", autoEst11 = "Auto_Estate_1_1", 
			brdCrmbEdtEst11 = breadCrumbEdit + autoEst11, estate21 = "    |---Auto_Estate_2_1", 
			autoEst111 = "Auto_Estate_1_1_1", autoEst21 = "Auto_Estate_2_1", autoName111 = "Auto_Name_1_1_1",
			autoEst2 = "Auto_Estate_2", brdCrmbEdtEst21 = breadCrumbEdit + autoEst21, autoEst211 = "Auto_Estate_2_1_1",
			estate2 = "|---Auto_Estate_2",	brdCrmbEdtEst2 = breadCrumbEdit + autoEst2,	breadCrumbEstates = "TMS » Estates", 
			estate11 = "    |---Auto_Estate_1_1", autoEst1 = "Auto_Estate_1", autoName11 = "Auto_Name_1_1", autoName1 = "Auto_Name_1",
			brdCrmbEdtEst1 = breadCrumbEdit + autoEst1, processingESTMsg = "Creation of the new estate in progress...",
			brdCrbEdtEst111 = breadCrumbEdit + autoEst111, estate1 = "|---Auto_Estate_1", breadCrumbEst = "Estates", 
			brdCrmEdtEst11 = "Edit estate » Auto_Estate_1_1", estName="Estate name",
			estTerminalsCol = Terminals.terminals, estate111 = "        |---Auto_Estate_1_1_1", 
			autoEst31 = "Auto_Estate_3_1", autoEst3 = "Auto_Estate_3",
			estate211 = "        |---Auto_Estate_2_1_1",  actionName = "Estates", signMandErrMsg = "Signature is mandatory", 
			moveMsg = "Do you confirm the moving of the current estate to the estate selected in the list above?", 
			moveEstErrMsg = "The estate can not be moved into itself", estate3 = "|---Auto_Estate_3", estate31 = "    |---Auto_Estate_3_1",
			estMvrunningMsg = "Estate moving is running, please wait...", rootEstateLabel = "Root Estate",
			estMvSuccMsg = "The current estate moved to the selected estate successfully.",
			addAnEstSucResMsg = "Operation done with success", 
			delEstPopUpMsg = "Estates and terminals contained in this estate will be deleted. Do you confirm the deletion of this estate?",
			modifyEstProgrMsg = "Saving of the current estate in progress...", modifyEstSavedMsg = "The estate has been saved successfully.", 
			estSignMsg1 = "Signature ", estSignMsg2 = " already exists",
			createEstMsg = "Creation of the new estate in progress...", createEstSuccMsg = "The estate has been created with success", 
			delEstSuccMsg = "Estates and terminals are deleted successfully.",
			delEstMsg = "Deleting estate", estTask = "Estate tasks", estAct = "Estate actions",addAnEstModlWin = "Add an estate",
			estConfig = "Estate config", estToolTip = "Added on an estate",scenDelMsg="The scenario has been deleted",
			AUTOESTOOLT="The selected estate is enabled. Click to disable this estate.",ACCODTOLGR="The selected estate is enabled",
			ACCODTOLBL="Click to edit the access code linked to this estate",editEstStsTool="Estate enabled",brdCrmEdt="TMS » Edit estate",
			autEstat="Auto_Estate",satEnbToolTip="Estate enabled (Automatic deactivation by access code)";

	public String estate = "", subEstate,  rootEst, name, newEst="", editPage, subEst1, subEst2, signature;
	//	estateListStr = "", newEst1 = "",

	public static String[] expEstates,
	edtEstActs = {modify, actionMove, delete, "Add an estate"},
	expEsts1 = {"Auto_Test", estate1, estate11, estate111},
	expEsts2 = {estate2, estate21, estate211},
	root = ArrayUtils.addAll(expEsts1, expEsts2),
	estatesColsArr = {"Edit", signLbl, "Name", Terminals.terminals, sts, crtionDate, delete},
	estateheaders={"Edit",signLbl,"Name",Terminals.terminals,sts, crtionDate},
	expEsts = {client, estate1, estate11, estate111, estate2, estate21, estate211},
	estEdtTskButts = {"Add scenario", taskTypes[2], taskTypes[1], "Add campaign"};

	public List<WebElement> rootEstList, list, rootDrpDnList = new ArrayList<WebElement>();
	//	public List<String> scnearioNames = new ArrayList<String>();
	//	public List<String> tableData = new ArrayList<String>();

	/**
	 * Method to initialize the XML files of Estates and CommonObjects
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws FileNotFoundException, IOException {
		initialize();
	}

	/**
	 * Verify, a sub estate to specific estate.  
	 **/
	public void vSubEstForAllEst(String lstItms, String[] expEsts){
		String parent, child;
		items.clear();
		for (int cnti = 0; cnti < expEsts.length; cnti++) {
			Assert.assertTrue(lstItms.contains(expEsts[cnti]), "Expected estate is not displayed.");
		}
		for (int cnti = 0; cnti < expEsts.length-1; cnti++) {
			parent = expEsts[cnti];
			child = expEsts[cnti+1];
			if (parent.equals(client)){
				for (int cntj = cnti; cntj < expEsts.length; cntj++) {
					if (child.startsWith("|---")) {
						items.add(child);
						break;
					}
				}
				Assert.assertTrue(items.contains(child), 
						"'"+child + "'  estate is not present in table as a child of "+ parent+" estate.");
				logger.info("Verified, '"+child +"' estate is a child of "+parent +" estate.");
				items.clear();
			} else if (parent.startsWith("|---"))	{
				for (int cntj = cnti; cntj < expEsts.length; cntj++) {
					if (child.startsWith("    |---")) {
						items.add(child);
						break;
					}
				}
				Assert.assertTrue(items.contains(child), 
						"'"+child +"' estate is not present in table as a child of '"+ parent+"' estate");
				logger.info("Verified, '"+child +"' estate is a child of '"+parent +"' estate.");
				items.clear();
			} else if (parent.startsWith("    |---"))	{
				for (int cntj = cnti; cntj < expEsts.length; cntj++) {
					if (child.startsWith("        |---")) {
						items.add(child);
						break;
					}
				}
				Assert.assertTrue(items.contains(child), 
						"'"+child + "'  estate is not present in table as a child of "+ parent+" estate.");
				logger.info("Verified, "+child +" estate is a child of "+parent +" estate.");
				items.clear();
			}else {
				logger.error("Child Estate '"+child+ "' estate is not present in table.");
				Assert.fail();
			}
		} 
	}

	/**
	 * Verify, edit estate created date format.
	 */
	public void vEdtEstcrDateFormate(){
		String creationDate = selUtils.getObject("trm_dat_xpath").getText();
		final String subStr = creationDate.substring(creationDate.indexOf("\n"));
		creationDate = creationDate.replace(subStr, "");
		Assert.assertTrue(verifyDateTimeFormat(creationDate,"MM/dd/yyyy HH:MM:ss"), 
				"Creation date is not having the expected format.");
		logger.info("Verified, 'Creation Date' format is as expected.");	
	}

	
	public void vEditEstcrDateFormate(){
		final String creationDate = selUtils.getObject("trm_dat_xpath").getText();
		final String subStr = creationDate.substring(0,19);
		Assert.assertTrue(verifyDateTimeFormat(subStr,"MM/dd/yyyy HH:MM:ss"), 
				"Creation date is not having the expected format.");
		logger.info("Verified, 'Creation Date' format is as expected.");	
	}

	/**
	 * Verify, A 'Terminals' field with a numerical value > or = to 10	
	 */
	public void edtEstTrmsVal(){
		Assert.assertTrue(Integer.valueOf(selUtils.getObject("nb_ternl_id").getText())>=10,
				"Terminals field not displyed with a numerical value > or = to 10.");
		logger.info("Verified, Terminals field is displyed with a numerical value > or = to 10");
	}

	/**
	 * Verify, all given fields are having expected value. -An 'Estate actions'
	 * buttons section having expected buttons. -An 'Estate tasks' buttons 
	 * section having expected buttons. -An 'Estate config.' buttons section 
	 * having expected buttons.-A 'Jobs' tab displaying a table with the 
	 * columns:'Edit', 'Job','Description', 'Creation Date', 'Available', 
	 * 'Completed', 'Actions'. 
	 **/
	public void vEditEstPgInfo(){		
		selUtils.vExpValPresent("trm_root_nam_xpath", autoEst2);
		selUtils.vExpValPresent("edit_estate_sign_id", autoEst21);
		selUtils.vExpValPresent("edit_estate_name_id", autoNm21);	
		//vEdtEstcrDateFormate();	
		vEditEstcrDateFormate();
		edtEstTrmsVal();
		vGrpButts("edt_est_act_butts_xpath", edtEstActs, estAct);
		vGrpButts("edt_est_tsk_butts_xpath", estEdtTskButts, estTask);
		vExpButtInGrp("edt_est_cnfg_butts_xpath", CallScheduling.CALLSCHD, estConfig);
		vTableFields("job_cols_headings_css", Jobs.jobsTabcolsHds);
	}

	/**
	 * Select the root estate item from list box in Terminal page
	 * @param rootEst
	 */
	public void selectRootEst(String rootEst, String locator){
		boolean flag = false;
		waitMethods.waitForElementEnable(locator);
		rootEstList = selUtils.getObjects(locator);
		for(itemCount = 0; itemCount<rootEstList.size() ; itemCount++){
			if(rootEstList.get(itemCount).getText().equals(rootEst)){
				waitMethods.waitForEleEnable(rootEstList.get(itemCount));
				rootEstList.get(itemCount).sendKeys("");
				rootEstList.get(itemCount).click();
				flag = true;
				break;
			}
		}
		if(!flag){
			logger.error("Expected root estate is not displayed in list box.");
			Assert.fail("Expected root estate is not displayed in list box.");
		}
	}

	/**
	 * In the table, click on the Edit icon of the estate.
	 */
	public void clickEditIconOfSpecificEstate(String estate){
		waitforEstPageLoded();
		String estsList = "";
		list = selUtils.getObjects("ests_list_css");
		for(itemCount = 0; itemCount < list.size(); itemCount++){
			estsList = estsList + " " + list.get(itemCount).getText();
			if(list.get(itemCount).getText().equals(estate)){
				xpath = selUtils.getPath("estate_edit_icon_xpath").replace(nameLbl, estate);
				webElement = selUtils.getObjectDirect(By.xpath(xpath));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", webElement);
				//webElement.click();
				break;
			}
		}
		if(!(estsList.contains(estate))){
			logger.error(estate+ " estate is not displayed in Estates table.");
			Assert.fail(estate+ " estate is not displayed in Estates table.");
		}
	}

	/**
	 * Verify 'Back to Estate' button.
	 * @param columns
	 */
	public void vBackToEstButt(){
		Assert.assertTrue(selUtils.getCommonObject("back_est_link").isDisplayed(), "'Back to Estate' button is not displayed.");
		logger.info("Verified, 'Back to Estate' button is displayed.");
	}

	/**
	 * Wait for estate page to load
	 */
	public void waitforEstPageLoded(){
		selUtils.waitForTxtPresent("est_disppagenos_css", numOfEleText);
	}

	/**
	 * Wait for estate page to load
	 */
	public void waitforEditPageLoaded(){
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
	}	

	/**
	 * Verify delete estate functionality
	 */
	public void verifyDeleteEstateFunctionality(){
		selUtils.clickOnWebElement("dialogbox_delest_id","dialog delete");
		//selUtils.getObject("dialogbox_delest_id").click();
		selUtils.waitForTxtPresent("prog_del_msg_res_id", delEstSuccMsg);
		Assert.assertTrue(selUtils.getObject("prog_del_msg_res_id").getText().equals(delEstSuccMsg), "'" +
				delEstSuccMsg + "' message is not displayed as expected.");
		logger.info("Verified, '" +	delEstSuccMsg + "' message is displayed as expected.");
	}

	/** 
	 * Keep the 'Root Estate' field set to 'Auto_Test', set the 'Signature', and 'Name' field with specified values,  
	 * click on the 'Confirm' button. Verify, the modal window displays the message: 'Creation of the new estate in progress...'. 
	 * Then it displays the message 'The estate has been created with success'.  
	 * */
	public void addEstInEstPage(String rootEst, String sign, String name){
		selectRootEst(rootEst, "est_rootest_list_css");
		waitMethods.waitForElementNotPresent("est_rootest_list_css");
		selUtils.vDrpDnSelcItem("root_est_id", rootEst);
		selUtils.populateInputBox("estate_sign_id", sign);
		selUtils.populateInputBox("estate_name_id", name);
		selUtils.getObject("dialogbox_add_estate_id").click();
		selUtils.waitForTxtPresent("estates_suc_crea_xpath", createEstSuccMsg);
		selUtils.vExpValPresent("estates_suc_crea_xpath", createEstSuccMsg);
	}	

	/**
	 * Verify that new estate is present in estates table.
	 */
	public void verifyEstPresence(String sign){
		waitforEstPageLoded();
		String estateListStr = "";
		optionsList = selUtils.getObjects("ests_list_css");
		for(itemCount = 0; itemCount < optionsList.size(); itemCount++){
			selUtils.getObjects("ests_list_css");
			estateListStr = (estateListStr + " " +(optionsList.get(itemCount).getText()).trim()).trim();
		}
		Assert.assertTrue(estateListStr.contains(sign), selUtils.speCharReplc(sign) +" estate is not appeared in the table of the 'TMS >> Estates' page.");
		logger.info("Verified, '"+selUtils.speCharReplc(sign)+" estate is appeared in the table of the 'TMS >> Estates' page.");
		optionsList.clear();
	}

	/**
	 * Verify that new estate name is present in estates table.
	 */
	public void verifyEstName(String estate, String name){
		waitforEstPageLoded();
		String estName = "";
		objScrollDwn("est_table_id");
		xpath = selUtils.getPath("estate_name_xpath").replace("ESTATE", estate);
		estName = selUtils.getObjectDirect(By.xpath(xpath)).getText();
		Assert.assertTrue(estName.equals(name), selUtils.speCharReplc(name) +" estate name is not appeared in the table of the 'TMS >> Estates' page.");
		logger.info("Verified, '"+selUtils.speCharReplc(name) +" estate name is appeared in the table of the 'TMS >> Estates' page.");
	}

	/**
	 * Verify that new estate name is present in estates table.
	 */
	public void verifyEstTerm(String estate, int term){
		waitforEstPageLoded();
		String estTerm = "";		
		xpath = selUtils.getPath("estate_term_xpath").replace("TERMINAL", estate);
		estTerm = selUtils.getObjectDirect(By.xpath(xpath)).getText();
		final int terms = Integer.parseInt(estTerm);
		Assert.assertTrue(terms >= term, terms +" estate Terminal is not appeared in the table of the 'TMS >> Estates' page.");
		logger.info("Verified, '"+term +" estate Terminal is appeared in the table of the 'TMS >> Estates' page.");
	}

	/**
	 * Verify, new estate 'creation date' pattern.
	 */
	public void verifyEstCreaDate(String estate){
		xpath = selUtils.getPath("estate_crea_date_xpath").replace("ESTATE", estate);
		creationDate = selUtils.getObjectDirect(By.xpath(xpath)).getText();
		Assert.assertTrue(verifyDateTimeFormat(creationDate, "MM/dd/yyyy HH:MM:ss"), "Creation date is not having the expected formate.");
		logger.info("Verified, 'Creation Date' formate is displayed as expected.");
	}

	/**
	 * Number of elements displayed in estates page.
	 */
	public void numOfElesDisp(){
		int count, rowsCount;
		waitforEstPageLoded();
		rowsCount = selUtils.getObjects("est_rows_xpath").size();
		count = Integer.valueOf(selUtils.getObject("est_disppagenos_css").getText().replaceAll("[A-z:,]","").trim());
		if(rowsCount == count){
			logger.info("Verified, 'Number of elements displayed:', and number of rows in estates table is same.");
		}
		else{
			logger.error("'"+numOfEleText+"', and number rows in estates is not same.");
			Assert.fail("'"+numOfEleText+"', and number rows in estates is not same.");
		}
	}

	/**
	 * Click on the Drop-down list icon of the 'Root Estate' field. Verify, a 
	 * drop-down list is opened showing estate among other Signatures. 
	 * */
	public void verifyRootEstDrpDnWithNewEst(String estate){
		String rootDrpDnListStr = "";
		selUtils.getObject("root_est_id").click();
		rootDrpDnList.clear();
		rootDrpDnList = selUtils.getObjects("est_rootest_list_css");
		for(itemCount = 0; itemCount < rootDrpDnList.size(); itemCount++){
			rootDrpDnListStr = (rootDrpDnListStr + " " + (rootDrpDnList.get(itemCount).getText()).trim()).trim();
		}
		Assert.assertTrue(rootDrpDnListStr.contains(estate.trim()), 
				"'Root Estae' drop down is not displayed "+estate+" estate.");
		logger.info("Verified, 'Root Estae' drop down is displayed "+estate+" estate.");
		selUtils.getObject("root_est_id").click();
	}

	/**
	 * Click on the 'Add an estate' button of the 'TMS >> Estates' page. Verify,
	 * the 'Add an estate' modal window is displayed.Click on the Drop-down list
	 * icon of the 'Root Estate' field. Verify, A drop-down list is opened not 
	 * containing expected estate. Click on the 'Close' button. 
	 * Verify, the modal window is closed.
	 * @param estate
	 */
	public void verifyRootEstDrpDnWithOutNewEst(String estate){
		String rootDrpDnListStr = "";
		selUtils.getObject("root_est_id").click();
		rootDrpDnList.clear();
		rootDrpDnList = selUtils.getObjects("est_rootest_list_css");
		for(itemCount = 0; itemCount < rootDrpDnList.size(); itemCount++){
			rootDrpDnListStr = (rootDrpDnListStr + " " + (rootDrpDnList.get(itemCount).getText()).trim()).trim();
		}
		Assert.assertFalse(rootDrpDnListStr.contains(estate), "'Root Estae' drop down is displayed the deleted estate.");
		logger.info("Verified, 'Root Estae' drop down is not displayed the deleted estate.");
		selUtils.getObject("root_est_id").click();
	}

	/** Click on the Delete icon in the 'Delete' column of the specific estate. 
	 * Verify, the 'Delete' modal window is displayed containing: The message: 
	 * 'Estates and terminals contained in this estate will be deleted. Do you 
	 * confirm the deletion of this estate?', A 'Confirm' and a 'Close' button*/
	public void verifyEstDelWindinfo(String estate){
		clickOnEstDelIcon(estate);		
		Assert.assertTrue(selUtils.getObject("estdelete_msg_css").getText().equals(delEstPopUpMsg), "Estate 'delete' window is not displayed the message as expected.");
		logger.info("Verified, estate 'delete' window is displayed the message as expected.");
		Assert.assertTrue(selUtils.getObject("dialogbox_delest_id").isDisplayed(), "'Coinfirm' button is not displayed.");
		Assert.assertTrue(selUtils.getObject("dialogbox_delest_cls_id").isDisplayed(), "'Close' button is not displayed.");
		logger.info("Verified, 'Coinfirm', 'Close' buttons are displayed in estates delete window.");
	}

	/**
	 * Click on Estate delete icon.
	 */
	public void clickOnEstDelIcon(String estate){
		waitforEstPageLoded();
		objScrollDwn("est_table_id");
		waitNSec(1);
		xpath = selUtils.getPath("estate_delete_icon_xpath").replace("ESTATE", estate);
		if(selUtils.getObjectDirect(By.xpath(xpath)).isDisplayed()){
			selUtils.getObjectDirect(By.xpath(xpath)).click();
			xpath = selUtils.getPath("popup_title_xpath").replace("TITLE", delete);
			if(selUtils.getObjectDirect(By.xpath(xpath)).getText().equals(delete)){
				logger.info("Verified, '"+delete+"' modal window is displayed");
			}else
			{
				logger.error("'"+delete+"' modal window is not displayed.");
				Assert.fail("'"+delete+"' modal window is not displayed.");
			}
		}
	}

	/**
	 * The estates table of the page contains only one instance of the 
	 * specific estate.
	 */
	public void verifyOnlyOneInstOfEstInEstPage(String estate){
		count = 0;
		optionsList = selUtils.getObjects("ests_list_css");
		for(itemCount = 0; itemCount < optionsList.size(); itemCount++){
			if(optionsList.get(itemCount).getText().equals(estate)){
				count++;
			}
		}
		Assert.assertTrue(count == 1, " estates page not contians only one instance of the '"+estate+"'.");
		logger.info("Verified, the estates table of the page contains only one instance of the'"+estate+"'.");
		optionsList.clear();
	}


	/**
	 * The estate table of the page, contains only one instance of the 
	 * specified set of estates among other.
	 */
	public void verifyOnlyOneInstOfSetOfEstsInEstPage(String[] signs){
		for(int estCount = 0; estCount < signs.length; estCount++){
			verifyOnlyOneInstOfEstInEstPage(signs[estCount]);
		}
	}

	/**
	 * Verify Details of edit estate page
	 */
	public void verifyEditEstFields(String sign, String name, String root, String roots)
	{
		//Verify details of edit page
		waitforEditPageLoaded();
		String dateFor;
		elements = selUtils.getObjects("buttons_group_css");
		if (root == client) {
			selUtils.vBackToLstButt();
			Assert.assertTrue(selUtils.getObject("edit_estate_sign_id").getText().equals(sign));
			Assert.assertTrue(selUtils.getObject("edit_estate_name_id").getText().equals(name));	
			logger.info(" Verified that  Signature and Name fields are present on estate edit page");
			Assert.assertTrue(selUtils.getObject("edit_estate_term_xpath").getText().matches("\\d+"));
			dateFor = selUtils.getObject("edit_est_dt_xpath").getText();
			dateFor = dateFor.substring(0, 19);
			Assert.assertTrue(dateFor.matches("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}"));	 
			logger.info(" Verified that 'Creation date' field value having correct format(MM/DD/YYYY HH:MM:SS).");
			elements = selUtils.getObjects("buttons_group_css");
			Assert.assertTrue(elements.get(0).getText().contains(estAct));
			Assert.assertTrue(selUtils.getObject("add_estate_link").isDisplayed());
			Assert.assertTrue(elements.get(1).getText().contains(estTask));
			Assert.assertTrue(selUtils.getObject("edt_est_sce_xpath").isDisplayed());
			Assert.assertTrue(selUtils.getObject("edt_est_pak_xpath").isDisplayed());
			Assert.assertTrue(selUtils.getObject("add_key_xpath").isDisplayed());
			Assert.assertTrue(selUtils.getObject("edt_est_swconfg_xpath").isDisplayed());
			Assert.assertTrue(elements.get(2).getText().contains(estConfig));
			//Assert.assertTrue(selUtils.getObject("param_css").isDisplayed());
			Assert.assertTrue(selUtils.getObject("call_sch_link").isDisplayed());
			logger.info(" Verified that  'Add an estate',  'Add scenario', 'Add package', 'Add key', 'Add SW config.', and 'Call scheduling' buttons" +
					" are present on estate edit page");
		} else {
			Assert.assertTrue(selUtils.getObject("back_list_link").isDisplayed());
			logger.info(" Verified that  'Back To List' button is present on estate edit page");
			Assert.assertTrue(selUtils.getObject("edit_estate_sign_id").getText().equals(sign));
			Assert.assertTrue(selUtils.getObject("edit_estate_name_id").getText().equals(name));
			Assert.assertTrue(selUtils.getObject("est_edit_stas_xpath").isDisplayed());
			Assert.assertTrue(selUtils.getObject("editestatepage_rootestate_xpath").getText().equals(roots));	
			logger.info(" Verified that  Root, Signature and Name fields are present on estate edit page");
			Assert.assertTrue(selUtils.getObject("edit_estate_term_xpath").getText().matches("\\d+"));
			dateFor = selUtils.getObject("edit_est_dt_xpath").getText();
			dateFor = dateFor.substring(0, 19);
			Assert.assertTrue(dateFor.matches("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}"));	 
			logger.info(" Verified that 'Creation date' field value having correct format(MM/DD/YYYY HH:MM:SS).");
			/*elements = selUtils.getObjects("buttons_group_css");
			Assert.assertTrue(elements.get(0).getText().contains(estAct));
			selUtils.verifyElementDisp("edt_est_deact_id",Packages.deact);
			Assert.assertTrue(selUtils.getObject("add_estate_link").isDisplayed());
			selUtils.verifyElementDisp("modify_link", modify);
			selUtils.verifyElementDisp("edt_est_move_xpath", actionMove);
			selUtils.verifyElementDisp("delete_link", delete);
			Assert.assertTrue(elements.get(1).getText().contains(estTask));
			Assert.assertTrue(selUtils.getObject("edt_est_sce_xpath").isDisplayed());
			Assert.assertTrue(selUtils.getObject("edt_est_pak_xpath").isDisplayed());
			Assert.assertTrue(selUtils.getObject("add_key_xpath").isDisplayed());
			Assert.assertTrue(selUtils.getObject("edt_est_swconfg_xpath").isDisplayed());
			Assert.assertTrue(elements.get(2).getText().contains(estConfig));
			//Assert.assertTrue(selUtils.getObject("param_css").isDisplayed());
			Assert.assertTrue(selUtils.getObject("call_sch_link").isDisplayed());*/	
			
			vGrpButts("edt_est_act_butts_xpath", edtEstActs, estAct);
			selUtils.verifyElementDisp("edt_est_deact_id",Packages.deact);
			vGrpButts("edt_est_tsk_butts_xpath", estEdtTskButts, estTask);
			vExpButtInGrp("edt_est_cnfg_butts_xpath", CallScheduling.CALLSCHD, estConfig);
			logger.info(" Verified that 'Add an estate',  'Add scenario', 'Add package', 'Add key', 'Add SW config.', and 'Call scheduling' buttons" +
					" are present on estate edit page");
		}
	}

	/**
	 * Click on the 'Back To List' button and verify that 
	 * 'TMS >> Estates' page is displayed.
	 */
	public void backtoList(){
		scrollUp();
		selUtils.getObject("back_list_link").click();
		logger.info("Clicked on 'Back to list' button.");
		waitMethods.waitForPageLoaded();
		selUtils.verifyBreadCrumb(breadCrumbEstates);
	}

	/** 
	 * Set the 'Root Estate' field , 'Signature' field, 'Name' field and click 
	 * on the 'Confirm' button. Verify, the modal window displays the message: 
	 * 'Creation of the new estate in progress...'.
	 */
	public void addEstInEstsPageWithChk(String rootEst, String sign, String name){
		waitMethods.waitForElementDisplayed("est_rootest_list_css");
		selectRootEst(rootEst, "est_rootest_list_css");
		selUtils.vDrpDnSelcItem("root_est_id", rootEst);
		selUtils.populateInputBox("estate_sign_id", sign);
		selUtils.populateInputBox("estate_name_id", name);
		selUtils.getObject("edit_estate_id").click();
		selUtils.getObject("dialogbox_add_estate_id").click();
		editPage = breadCrumbEdit+sign;
		//selUtils.waitForTxtPresent("bread_crumb_id", editPage);
		selUtils.verifyBreadCrumb(editPage);
	}	

	/**
	 * Set the 'Signature' field and 'Name' field.Click on the 'Confirm' button.
	 * Verify, the modal window displays the message: 'Saving of the current 
	 * estate in progress...'. Then it displays the message 
	 * 'The estate has been saved successfully'.
	 * */
	public void verifyModifyEditEstFunc(String sign, String name){
		selUtils.populateInputBox("estate_sign_id", sign);
		selUtils.populateInputBox("modify_est_name_id", name);
		selUtils.getObject("modify_conf_butt_id").click();
		selUtils.waitForTxtPresent("modify_saved_msg_css", modifyEstSavedMsg);
		selUtils.vExpValPresent("modify_saved_msg_css", modifyEstSavedMsg);
	}

	/**
	 * Set the 'Signature' field, 'Name' field and Click on the 'Confirm' button.
	 * The modal window displays the message: 'Processing'.Then it displays the 
	 * message 'Operation done with success' and only a 'Close' button is 
	 * available at the bottom of the modal window. 
	 * Click on the 'Close' button and verify The modal window is closed.
	 */
	public void addEstOnEditEstPage(String sign, String name){
		selUtils.populateInputBox("newname_id", name);
		selUtils.populateInputBox("newestate_id", sign);
		selUtils.getObject("dialogbox_add_newestate_id").click();
		selUtils.waitForTxtPresent("addestate_success_result_id", createEstSuccMsg);
		Assert.assertTrue(selUtils.getObject("addestate_success_result_id").getText().equals(createEstSuccMsg),
				"After 'processing' message, '"+createEstSuccMsg+"' message is not displayed as expected.");	
		logger.info("Verified, after clicked on confirm button, '"+createEstSuccMsg+"' message is displayed as expected.");
		
		//Click on the 'Close' button. Verify, the modal window is closed.
		verifySpecifiedWinNotDisp("diabox_addnewest_res_cls_id");
	}

	/**
	 * Verify mandatory one field behaviour in 'modify' modal window.
	 */
	public void verifyModifyMandFunc(String errMsg, String fieldTxtBox, String field){
		selUtils.getObject("modify_conf_butt_id").click();		
		Assert.assertTrue(selUtils.getObject("modify_err_msg_id").getText().contains(errMsg), 
				"In 'modify' window, error message is not displayed as expected.");
		Assert.assertTrue(selUtils.getObject("modify_err_msg_id").getAttribute("style").contains(redColor), 
				"In 'modify' window, error message is not displayed in red color.");
		Assert.assertTrue(selUtils.getObject("modify_err_msg_id").getCssValue("background-image").contains("rejected.gif"), "Error message is not displayed with error icon.");
		//Assert.assertTrue(selUtils.getObject("modify_err_icon_css").isDisplayed(), "In '"+errMsg+
		//		"' section, error icon is not displayed.");
		Assert.assertTrue(selUtils.getObject(fieldTxtBox).getAttribute("style").contains(redColor), "'"+errMsg+
				"', and '"+field+"' field is not displayed in "+redColor+" color.");
		logger.info("Verified, '"+errMsg+"'error message, and '"+field+"' field is displayed in "+redColor+" color.");
		logger.info("Verified, '"+field+"' field text box is displayed in red color.");
	}	

	/**
	 * Deleting newly created estate
	 */
	public void deleteEstates(String signature, String newEst){
		waitforEstPageLoded();
		clickOnEstDelIcon(signature);
		verifyDeleteEstateFunctionality();
		verifyOnlyCloseButtInSuccWin("delete_resultbuttons_css");
		verifySpecifiedWinNotDisp("delete_resultbuttons_css");
		waitforEstPageLoded();
		verifyEstatesnotPresent(newEst);
	}

	/**
	 * Deleting estate from edit page 
	 */
	public void deleteEstFromEditPage(String estate){
		selUtils.waitForTxtPresent("jobs_table_size_css", editPageDispTxt);
		deleteEstFrmEdit();
		verifyEstatesnotPresent(estate);
	}
	
	/**
	 * deleteEstFrmEdit
	 */
	public void deleteEstFrmEdit(){
		//Click on the 'Delete' button of the 'Estate actions' buttons section.
		verifyExpWinDisp("delete_link", delete);

		// Click on the 'Confirm' button. Verify, the expected results.
		verifyDeleteEstateFunctionality();
		verifyOnlyCloseButtInSuccWin("delete_resultbuttons_css");

		// Click on the 'Close' button. Verify, the expected results.
		verifySpecifiedWinNotPresent("delete_resultbuttons_css");			
		selUtils.verifyBreadCrumb(breadCrumbEstates);
	}


	/**
	 * Adding new estate
	 * InterruptedException
	 */
	public void addEstInEstPage(String sign, String name) throws InterruptedException {
		scrollUp();
		selUtils.getObject("add_est_xpath").click();
		selUtils.populateInputBox("estate_sign_id", sign);
		selUtils.populateInputBox("estate_name_id", name);
		selUtils.getObject("dialogbox_add_estate_id").click();
		//selUtils.waitForTxtPresent("prog_msg_id", createEstMsg);
		Assert.assertTrue(selUtils.getObject("prog_msg_id").getText().contains(createEstMsg));
		selUtils.waitForTxtPresent("estates_suc_crea_xpath", createEstSuccMsg);
		Assert.assertTrue(selUtils.getObject("estates_suc_crea_xpath").getText().contains(createEstSuccMsg));
		verifyOnlyCloseButtInSuccWin("addest_reswin_cls_butt_id");
		verifySpecifiedWinNotDisp("addest_reswin_cls_butt_id");
		logger.info(" Created new estates: "+selUtils.speCharReplc(sign));
		waitforEstPageLoded();
		verifyEstPresence(sign);
	}

	/**
	 * Verify that newly added data is not present in table.
	 */
	public void verifyEstatesnotPresent(String signature) {
		waitforEstPageLoded();
		items.clear();
		waitMethods.waitForElementDisplayed("ests_list_css");
		items = selUtils.getLstItems(selUtils.getObjects("ests_list_css"));
		Assert.assertFalse(items.contains(signature), "After deleted, the '"+ selUtils.speCharReplc(signature) + "' signature is present in table.");	
		logger.info("Verified, deleted '"+selUtils.speCharReplc(signature)+"' signature is not present in table.");	
	}

	/**
	 * Verify that Duplicate estates is not present in table.
	 */
	public void verifyDuplicateEstatesnotPresent(String signature) {
		waitforEstPageLoded();
		int eleCount = 0, count = 0;
		items.clear();
		// Verify that new estates present in table.
		webelements = selUtils.getObjects("ests_list_css");
		for (eleCount = 0; eleCount < webelements.size(); eleCount++) {
			items.add(webelements.get(eleCount).getText().trim());
		}
		for (eleCount = 0; eleCount < webelements.size(); eleCount++) {
			if (items.get(eleCount).equalsIgnoreCase(signature.trim())) {
				count++;
			}
		}
		if (count == 1){
			logger.info(" Verified that no duplicate estate created. ");
		} else {
			Assert.fail("Duplicate estate created");
		}
		items.clear();
	}

	/**
	 * Adding new estate which is having more than 50 chars for signature field 
	 * and more than 100 chars for Name field
	 * InterruptedException
	 */
	public void addEstateWithMaxLim(String sign, String name) throws InterruptedException {
		selUtils.getObject("add_est_xpath").click();
		selUtils.populateInputBox("estate_sign_id", sign);
		selUtils.populateInputBox("estate_name_id", name);
		selUtils.getObject("dialogbox_add_estate_id").click();
		Assert.assertTrue(selUtils.getObject("prog_msg_id").getText().contains(createEstMsg));
		waitMethods.waitForElementDisplayed("addest_reswin_cls_butt_id");
		Assert.assertTrue(selUtils.getObject("estates_suc_crea_xpath").getText().contains(createEstSuccMsg));	
		verifyOnlyCloseButtInSuccWin("addest_reswin_cls_butt_id");
		verifySpecifiedWinNotDisp("addest_reswin_cls_butt_id");
		//String signature = "|---" + sign;
		String signLocal = "", nameLocal = "";
		if(sign.length() > 50) {
			signLocal = sign.substring(0, 50);
		}
		logger.info(" Created new estates: "+signLocal);
		if(name.length() > 100) {
			nameLocal = name.substring(0, 100);
		}

		// Verify that new estates Signature is present in table.
		verifyEstPresence(signLocal);

		// Verify that new estates Name is present in table.
		verifyEstName(signLocal, nameLocal);
	}

	/**
	 * Adding new estate with only one input or all fields blank
	 * err_msg_id, ests_err_icon_xpath, estate_sign_id, estate_name_id
	 */
	public void addEstInEstPageWithMandotryField(String sign, String name, String roots, boolean signFlag, boolean nameFlag) {
		//Click on Add an estate button and verify details.
		waitforEstPageLoded();
		scrollUp();
		selUtils.getObject("add_est_xpath").click();
		Assert.assertTrue(selUtils.getObject("add_estates_popup_xpath").isDisplayed());
		logger.info(" Clicked on Add an estate button and Verified that Add an estate modal window is displayed.");
		selectRootEst(roots, "est_rootest_list_css");
		selUtils.vDrpDnSelcItem("root_est_id", roots);
		selUtils.populateInputBox("estate_sign_id", sign);
		selUtils.populateInputBox("estate_name_id", name);
		logger.info(" Entered the value for 'Signature' field: "+sign +" and 'Name' field: "+name +" and selected the root: "+roots );
		selUtils.getObject("dialogbox_add_estate_id").click();
		logger.info(" Clicked on confirm button");
		selUtils.waitForTxtPresent("err_msg_id", unAuthErrMsg1);		
		Assert.assertTrue(selUtils.getObject("err_msg_id").getText().contains(unAuthErrMsg1));
		Assert.assertTrue(selUtils.getObject("err_msg_id").getCssValue("background-image").contains("rejected.gif"), "Error message is not displayed with error icon.");
		//Assert.assertTrue(selUtils.getObject("ests_err_icon_xpath").isDisplayed());
		logger.info(" Verified that modal window displaying error icon and error message: " +unAuthErrMsg1);
		if (signFlag) {
			Assert.assertTrue(selUtils.getObject("estate_sign_id").getAttribute("style").contains(redColor));
			logger.info(" Verified that 'Signature' field of modal window becomes red: "+signFlag);
		} else if(nameFlag) {
			Assert.assertTrue(selUtils.getObject("estate_name_id").getAttribute("style").contains(redColor));
			logger.info(" Verified that 'Name' field of modal window becomes red: "+nameFlag);
		} else if (signFlag && nameFlag){
			Assert.assertTrue(selUtils.getObject("estate_sign_id").getAttribute("style").contains(redColor));
			Assert.assertTrue(selUtils.getObject("estate_name_id").getAttribute("style").contains(redColor));
			logger.info(" Verified that both 'Signature: '"+signFlag+ "and 'Name: '"+nameFlag +" field of modal window becomes red: ");
		} else {
			logger.info(" Fields of modal window is not becomes red");
			Assert.fail();
		}
		verifySpecifiedWinNotDisp("dialogbox_cls_estate_id");
		verifyEstatesnotPresent(sign);
	}

	/**
	 * Adding new estate with Existing Estates
	 */
	public void addEstInEstPageWithExtEst(String sign, String name, String roots) {
		//Click on Add an estate button and verify details.
		waitforEstPageLoded();
		selUtils.getObject("add_est_xpath").click();
		Assert.assertTrue(selUtils.getObject("add_estates_popup_xpath").isDisplayed());
		logger.info(" Clicked on Add an estate button and Verified that Add an estate modal window is displayed.");
		selectRootEst(roots, "est_rootest_list_css");
		selUtils.vDrpDnSelcItem("root_est_id", roots);
		selUtils.populateInputBox("estate_sign_id", sign);
		selUtils.populateInputBox("estate_name_id", name);
		selUtils.getObject("dialogbox_add_estate_id").click();
		Assert.assertTrue(selUtils.getObject("prog_msg_id").getText().contains(createEstMsg));
		logger.info(" Verified that Creation of the new estate in progress...");
		selUtils.waitForTxtPresent("estates_suc_crea_xpath", "Signature "+sign+" already exists");
		Assert.assertTrue(selUtils.getObject("estates_suc_crea_xpath").getText().contains("Signature "+sign+" already exists"));		
		logger.info(" Verified that Signature "+sign+" already exists");		
		msgColor = selUtils.getObject("estates_suc_crea_xpath").getCssValue("color");
		colorRed = msgColor.substring(0, ((msgColor.length()-4)));
		Assert.assertEquals(colorRed, "rgba(255, 0, 0", "In 'Estate' window, error message is not displayed in red color.");
		logger.info("Verified, error message is displayed in red color as expected for existing estate : '"+sign+"'.");		
		verifyOnlyCloseButtInSuccWin("addest_reswin_cls_butt_id");
		verifySpecifiedWinNotDisp("addest_reswin_cls_butt_id");
	}

	/** Click on the 'Add an estate' button. Verify, the 'Add an estate' modal 
	 * window is displayed.Set the 'Root Estate' field , 'Signature' field, 
	 * 'Name' field and click on the 'Confirm' button. Verify, the modal window 
	 * displays the message: 'Creation of the new estate in progress...'.
	 */
	public void addEstInEditPageWithChk(String sign, String name){
		selUtils.populateInputBox("newname_id", name);
		selUtils.populateInputBox("newestate_id", sign);
		selUtils.getObject("edit_est_aftercreation_id").click();
		selUtils.getObject("dialogbox_add_newestate_id").click();
		Assert.assertTrue(selUtils.getObject("addestate_confirm_process_id").getText().equals(processingESTMsg),
				"After clicked on confirm button, '"+processingESTMsg+"' message is not displayed as expected.");	
		logger.info("Verified, after clicked on confirm button, '"+processingESTMsg+"' message is displayed as expected.");
		waitforEditPageLoaded();
		editPage = breadCrumbEdit+sign;
		//selUtils.waitForTxtPresent("bread_crumb_id", editPage);
		selUtils.verifyBreadCrumb(editPage);
	}

	/**
	 * Deleting Existing estate
	 */
	public void deleteExistEstates(String signature){
		boolean flag = false;
		objScrollDwn("est_table_id");
		waitforEstPageLoded();
		webelements = selUtils.getObjects("ests_list_css");
		for (int cnti = 0; cnti < webelements.size(); cnti++) {
			selUtils.getObjects("ests_list_css");
			estate = webelements.get(cnti).getText();
			if(estate.equals(signature)){
				flag = true;
				break;
			}			
		}
		if(flag)
		{
			//xpath = selUtils.getPath("list_estate_delete_xpath").replace(nameLbl, signature);
			selUtils.clickOnWebElement("list_estate_delete_xpath", nameLbl, signature);
			//selUtils.getObjectDirect(By.xpath(xpath)).click();
			logger.info(" Deleting the Existing estates");			
			verifyDeleteEstateFunctionality();
			verifyOnlyCloseButtInSuccWin("delete_resultbuttons_css");
			verifySpecifiedWinNotDisp("delete_resultbuttons_css");
			waitforEstPageLoded();	
		}
	}

	/**
	 * Set the 'Signature' field to given value.. Keep the 'Name' field set to 
	 * as it is. Click on the 'Confirm' button. Verify, the expected messages.
	 * */
	public void verifyModifyEstFunc(String sign, String name){
		selUtils.populateInputBox("estate_sign_id", sign);
		selUtils.populateInputBox("modify_est_name_id", name);
		selUtils.getObject("modify_conf_butt_id").click();
		waitMethods.waitForElementDisplayed("modify_saved_msg_css");
		selUtils.vExpValPresent("modify_saved_msg_css", modifyEstSavedMsg);
	}

	/**
	 * Set the 'Root Estate' field to expected value and click on the 'Confirm' 
	 * button. Verify, the modal window displays the expected messages. 
	 * */
	public void verifyEstMoveFunc(String estate){
		selUtils.selectItem(selUtils.getObject("move_root_est_id"), estate);
		selUtils.clickOnWebElement("move_cnfm_butt_id", confirm);
		selUtils.waitForTxtPresent("est_mv_succ_msg_id", estMvSuccMsg);
		Assert.assertTrue(selUtils.getObject("est_mv_succ_msg_id").getText().equalsIgnoreCase(estMvSuccMsg), estMvSuccMsg + " is not displayed as expected.");
		logger.info("Verified, '"+estMvSuccMsg+"' message is displayed as expected.");
	}

	/**
	 * Verify Add an Estate Functionality in Estate Page.
	 */
	public void verifyAddEstFuncInEstPage(String rootEst, String sign, String name){
		//Click on the 'Add an estate' button.
		verifyExpWinDisp("add_est_xpath", addAnEstModlWin);

		/* Select specific estate from the drop-down list.Set the 'Signature' 
		 * field to specified sign'. Set the 'Name' field to specified name.
		 * Click on the 'Confirm' button. Verify, the expected results. */
		addEstInEstPage(rootEst, sign, name);
		verifyOnlyCloseButtInSuccWin("addest_reswin_cls_butt_id");

		/* Click on the 'Close' button. Verify, the modal window is closed and 
		 * the  sub estate appears in the table of the 'TMS >> Estates' page as 
		 * a sub-estate of root estate. */
		verifySpecifiedWinNotDisp("addest_reswin_cls_butt_id");
		verifyEstPresence(sign);
	}

	//Verify There are no 'Estate actions' or 'Estate tasks' button sections.
	public void verifyNotPresentEstActTask(){
		Assert.assertFalse(selUtils.getObject("buttons_group_css").getText().contains(estAct));
		Assert.assertFalse(selUtils.getObject("buttons_group_css").getText().contains(estTask));
	}

	/**
	 * Verify The details of  'TMS >> Edit estate >> Auto_Estate_1_1' page.
	 */
	public void detailsOfEditEstPage(){
		waitforEditPageLoaded();
		selUtils.vBackToLstButt();
		Assert.assertTrue(selUtils.getObject("edit_estate_sign_id").getText().equals(autoEst11));
		Assert.assertTrue(selUtils.getObject("edit_estate_name_id").getText().equals(autoName11));	
		Assert.assertTrue(selUtils.getObject("editestatepage_rootestate_xpath").getText().equals(autoEst1));	
		logger.info(" Verified that  Root, Signature and Name fields are present on estate edit page with valid value.");
		Assert.assertTrue(selUtils.getObject("edit_estate_term_xpath").getText().matches("\\d+"));
		logger.info(" Verified that 'Terminals' field with a numerical value representing the number of terminals in the estate");
		String dateFor = selUtils.getObject("edit_est_dt_xpath").getText();
		dateFor = dateFor.substring(0, 19);
		Assert.assertTrue(dateFor.matches("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}"));	 
		logger.info(" Verified that 'Creation date' field value having correct format(MM/DD/YYYY HH:MM:SS).");
		Assert.assertTrue(selUtils.getObject("buttons_group_css").getText().contains(estConfig));
		Assert.assertTrue(selUtils.getObject("call_sch_link").isDisplayed());
		logger.info(" Verified that 'Call scheduling' buttons is present on estate edit page");
	}

	/**
	 * Verify mandatory one field behaviour in Add An Estate modal window.
	 */
	public void verifyAddEstMandFunc(String errMsg, String fieldTxtBox, String field){
		selUtils.getObject("dialogbox_add_newestate_id").click();
		Assert.assertTrue(selUtils.getObject("addestate_errormsg_id").getText().equals(errMsg), 
				"In 'Add an estate' window, error message is not displayed as expected.");
		Assert.assertTrue(selUtils.getObject("addestate_errormsg_id").getAttribute("style").contains(redColor), 
				"In 'Add an estate' window, error message is not displayed in red color.");
		Assert.assertTrue(selUtils.getObject("addestate_errormsg_id").getCssValue("background-image").contains("rejected.gif"), "In '"+errMsg+
				"' section, error icon is not displayed.");
		Assert.assertTrue(selUtils.getObject(fieldTxtBox).getAttribute("style").contains(redColor), "'"+errMsg+
				"', and '"+field+"' field is not displayed in "+redColor+" color.");
		logger.info("Verified, '"+errMsg+"'error message, and '"+field+"' field is displayed in "+redColor+" color.");
		logger.info("Verified, '"+field+"' field text box is displayed in red color.");
	}	

	/**
	 * Verify mandatory all fields behaviour in Add An Estate modal window.
	 */
	public void verifyAddEstMandFunc(String errMsg, String field1TxtBox, String field1, String field2TxtBox, String field2){
		selUtils.getObject("dialogbox_add_newestate_id").click();
		Assert.assertTrue(selUtils.getObject("addestate_errormsg_id").getText().equals(errMsg), 
				"In 'Add an estate' window, error message is not displayed as expected.");
		Assert.assertTrue(selUtils.getObject("addestate_errormsg_id").getAttribute("style").contains(redColor), 
				"In 'Add an estate' window, error message is not displayed in red color.");
		Assert.assertTrue(selUtils.getObject("addanestate_error_icon_css").isDisplayed(), "In '"+errMsg+
				"' section, error icon is not displayed.");
		Assert.assertTrue(selUtils.getObject(field1TxtBox).getAttribute("style").contains(redColor), "'"+errMsg+
				"', and '"+field1+"' field is not displayed in "+redColor+" color.");
		Assert.assertTrue(selUtils.getObject(field2TxtBox).getAttribute("style").contains(redColor), "'"+errMsg+
				"', and '"+field2+"' field is not displayed in "+redColor+" color.");
		logger.info("Verified, '"+errMsg+"' error message, '"+field1+"', and '"+field2+"' fields are displayed in "+redColor+" color.");
		logger.info("Verified, '"+field1+"', and '"+field2+"' fields text boxes are displayed in red color.");
	}	

	/**
	 * Verify mandatory all fields behaviour in modify modal window.
	 */
	public void verifyModifyMandFunc(String errMsg, String field1TxtBox, String field1, String field2TxtBox, String field2){
		selUtils.getObject("modify_conf_butt_id").click();
		Assert.assertTrue(selUtils.getObject("modify_err_msg_id").getText().equals(errMsg), 
				"In 'modify' window, error message is not displayed as expected.");
		Assert.assertTrue(selUtils.getObject("modify_err_msg_id").getAttribute("style").contains(redColor), 
				"In 'modify' window, error message is not displayed in red color.");
		Assert.assertTrue(selUtils.getObject("modify_err_msg_id").getCssValue("background-image").contains("rejected.gif"), 
				"Error message is not displayed with error icon");
		/*Assert.assertTrue(selUtils.getObject("modify_err_icon_css").isDisplayed(), "In '"+errMsg+
				"' section, error icon is not displayed.");*/
		Assert.assertTrue(selUtils.getObject(field1TxtBox).getAttribute("style").contains(redColor), "'"+errMsg+
				"', and '"+field1+"' field is not displayed in "+redColor+" color.");
		Assert.assertTrue(selUtils.getObject(field2TxtBox).getAttribute("style").contains(redColor), "'"+errMsg+
				"', and '"+field2+"' field is not displayed in "+redColor+" color.");
		logger.info("Verified, '"+errMsg+"' error message, '"+field1+"', and '"+field2+"' fields are displayed in "+redColor+" color.");
		logger.info("Verified, '"+field1+"', and '"+field2+"' fields text boxes are displayed in red color.");
	}	

	/**
	 * Verify that new estate name is present in estates table.
	 */
	public void verifyEstNamePresence(String name, String sign){
		String estateListStr = "";
		waitMethods.waitForElementDisplayed("estnames_list_css");
		optionsList = selUtils.getObjects("estnames_list_css");
		for(itemCount = 0; itemCount < optionsList.size(); itemCount++){
			estateListStr = (estateListStr + " " + (optionsList.get(itemCount).getText()).trim()).trim();
		}
		if (name.isEmpty()) {
			Assert.assertTrue(estateListStr.contains(sign), sign +" estate is not appeared in the table of the 'TMS >> Estates' page.");
			logger.info("Verified, '"+sign +" estate is appeared in the table of the 'TMS >> Estates' page.");
			optionsList.clear();
		} else {
			Assert.assertTrue(estateListStr.contains(name), name +" estate is not appeared in the table of the 'TMS >> Estates' page.");
			logger.info("Verified, '"+name +" estate is appeared in the table of the 'TMS >> Estates' page.");
			optionsList.clear();
		}
	}

	/**
	 * Verify, Click on the 'Move' button of the 'Estate actions' button section
	 */
	public void verifyactionMovedowInfo(){
		selUtils.waitForTxtPresent("move_root_est_id", estate11);
		selUtils.verifyElementDisp("move_root_est_id", rootEstateLabel);
		//selUtils.vDrpDnSelcItem("move_root_est_id", estate1_1);
		selUtils.vExpValPresent("move_msg_css", moveMsg);
		selUtils.verifyElementDisp("move_cnfm_butt_id", confirm);
		selUtils.verifyElementDisp("move_cls_butt_id", closeButton);
	}	

	/**
	 * Verify default estates presence in estate page.
	 */
	public void verifyDeletedEstsNotInEstPage(String[] root){	
		waitforEstPageLoded();
		String estateListStr = "";
		optionsList = selUtils.getObjects("estates_list_css");
		for(itemCount = 0; itemCount < optionsList.size(); itemCount++){
			selUtils.getObjects("estates_list_css");
			estateListStr = (estateListStr + optionsList.get(itemCount).getText()).trim();
		}
		for(itemCount = 0; itemCount < root.length; itemCount++){
			Assert.assertFalse(estateListStr.contains(root[itemCount]), " estate table is displayed deleted estates.");
			optionsList.clear();
		}		
		logger.info("Verified, estate table is not displayed deleted estates.");
	}

	/**
	 * Click on add an estate button and verify all fields of estates.
	 */
	public void verifyEstFields()
	{
		waitforEstPageLoded();
		scrollUp();
		selUtils.getObject("add_estate_link").click();			
		Assert.assertTrue(selUtils.getObject("dialogbox_add_newestate_id").isDisplayed());
		Assert.assertTrue(selUtils.getObject("newestate_id").isDisplayed());
		Assert.assertTrue(selUtils.getObject("newname_id").isDisplayed());
		Assert.assertTrue(selUtils.getObject("edit_est_aftercreation_id").isDisplayed());
		Assert.assertTrue(selUtils.getObject("dialogbox_add_newestate_id").isDisplayed());
		Assert.assertTrue(selUtils.getObject("dialogbox_cls_newestate_id").isDisplayed());
		logger.info(" Verified that all field of Estates are present.");	
	}

	/**
	 * Click on the Drop-down list icon of the 'Root Estate' field to expand it.
	 * Verify, in the drop-down list, the specified estate and under it, 
	 * its two sub-estates are sub-estates of that estate.
	 * */
	public void verifyRootEstDrpDnSpecifiedSubEstsUnderEst(String estate, String[] subEsts){
		String rootDrpDnItem = "", rootDrpDnItemsStr = "", subEstsStr = "";
		int count = 0;
		for(itemCount = 0; itemCount < subEsts.length; itemCount++){
			subEstsStr = (subEstsStr + " " + (subEsts[itemCount]).trim()).trim();
		}
		selUtils.getObject("move_root_est_id").click();
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id(selUtils.getPath("move_root_est_id")), all));
		//waitForTxtNotPresent("move_root_est_id", all);
		rootDrpDnList.clear();
		rootDrpDnList = selUtils.getObject("move_root_est_id").findElements(By.tagName("option"));
		for(itemCount = 0; itemCount < rootDrpDnList.size(); itemCount++){
			rootDrpDnItem = rootDrpDnList.get(itemCount).getText();
			if(rootDrpDnItem.equals(estate)){
				logger.info("Verified, 'Root Estae' drop down is displayed '"+estate+"' estate.");
				count = 1;
				continue;
			}else if(count == 1){
				rootDrpDnItemsStr = (rootDrpDnItemsStr + " " + (rootDrpDnList.get(itemCount).getText()).trim()).trim();
			}			
		}
		Assert.assertTrue(rootDrpDnItemsStr.contains(subEstsStr), 
				"'Root Estae' drop down is not displayed "+subEstsStr+" as sub estates.");
		logger.info("Verified, 'Root Estae' drop down is displayed '"+subEstsStr+"' sub estate.");
		selUtils.getObject("move_root_est_id").click();
	}

	/** 
	 * Set the specific 'Signature', and 'Name' field. Click on the 'Confirm' 
	 * button. Verify, the modal window displays the expected messages. 
	 **/
	public void verifyModifyExtEstNegFunc(String sign, String name){
		final String modyExtEstErMsg = signLbl+" " + sign + estSignMsg2;
		selUtils.populateInputBox("estate_sign_id", sign);
		selUtils.populateInputBox("modify_est_name_id", name);
		selUtils.getObject("modify_conf_butt_id").click();
		waitMethods.waitForElementDisplayed("modify_saved_msg_css");		
		selUtils.vExpValPresent("modify_saved_msg_css", modyExtEstErMsg);
		msgColor = selUtils.getObject("modify_saved_msg_css").getCssValue("color");
		colorRed = msgColor.substring(0, ((msgColor.length()-4)));
		Assert.assertEquals(colorRed, "rgba(255, 0, 0", "In 'Modify' window, error message is not displayed in red color.");
		logger.info("Verified, error message is displayed in red color as expected for existing estate : '"+sign+"'.");
	}

	/** 
	 * Click on the 'Add an estate' button of the 'Estate actions' buttons 
	 * section. Set the 'Signature' field to each of those characters: '\<>%'.
	 * Set the 'Name' field to 'EST_2202_Name'. Click on the 'Confirm' button. 
	 * Verify, the expected messages in red with an Error icon. The 'Signature' 
	 * field becomes red. Click on the 'Close' button. Verify, the modal window 
	 * is closed, no estate has been created.
	 **/
	public void verifyAddEstFuncWithInvalSign(String signs, String name){
		final char[] signsArry = signs.toCharArray();
		String sign;
		for(itemCount = 0; itemCount < signsArry.length; itemCount++){
			sign = String.valueOf(signsArry[itemCount]);
			verifyExpWinDisp("add_estate_link", addAnEstModlWin);
			selUtils.populateInputBox("newestate_id", sign);
			logger.info("Verified, the '"+signLbl+"' field is set to '"+sign+"'");
			selUtils.populateInputBox("newname_id", name);
			logger.info("Verified, the '"+nameLbl+"' field is set to '"+name+"'");
			verifyAddEstMandFunc(unAuthErrMsg1, "newestate_id", signLbl);
			verifySpecifiedWinNotDisp("dialogbox_cls_newestate_id");
		}
		logger.info("Verified, for each of '"+signs+"' signature values, expected error message, and error icon is displayed.");
	}

	/** 
	 * Click on the 'Add an estate' button of the 'Estate actions' buttons 
	 * section. Verify, the 'Add an estate' modal window is displayed. Set the 
	 * 'Signature' field to ''EST_2202_Sign'. Set the 'Name' field to each of 
	 * those characters:'\<>%'. Click on the 'Confirm' button. Verify, the 
	 * message 'The field marked in red contains unauthorized characters' is 
	 * displayed in the modal window in red with an Error icon. The 'Signature' 
	 * field becomes red. Click on the 'Close' button. Verify, the modal window
	 * is closed, no estate has been created.
	 **/
	public void verifyAddEstFuncWithInvalName(String sign, String names){
		final char[] namesArry = names.toCharArray();
		String name;
		for(itemCount = 0; itemCount < namesArry.length; itemCount++){
			name = String.valueOf(namesArry[itemCount]);
			verifyExpWinDisp("add_estate_link", addAnEstModlWin);
			selUtils.populateInputBox("newestate_id", sign);
			selUtils.populateInputBox("newname_id", name);
			logger.info("Verified, the '"+nameLbl+"' field is set to '"+name+"'");
			verifyAddEstMandFunc(unAuthErrMsg1, "newname_id", nameLbl);			
			verifySpecifiedWinNotDisp("dialogbox_cls_newestate_id");
		}
		logger.info("Verified, for each of '"+names+"' Name values, expected error message, and error icon is displayed.");
	}

	/** 
	 * Click on the 'Add an estate' button of the 'Estate actions' buttons 
	 * section. Verify, the 'Add an estate' modal window is displayed. Set the 
	 * 'Signature' field to each of those characters: '\<>%'. Set the 'Name' 
	 * field to each of those characters: '\<>%'. Click on the 'Confirm' button.
	 * Verify, the message 'The field marked in red contains unauthorized 
	 * characters' is displayed in the modal window in red with an Error icon. 
	 * The 'Signature' field becomes red. Click on the 'Close' button. Verify, 
	 * the modal window is closed, no estate has been created.
	 **/
	public void verifyAddEstFuncWithInvalSignAndName(String signAndNames){
		final char[] signAndNamesArry = signAndNames.toCharArray();
		String signAndName;
		for(itemCount = 0; itemCount < signAndNamesArry.length; itemCount++){
			signAndName = String.valueOf(signAndNamesArry[itemCount]);
			verifyExpWinDisp("add_estate_link", addAnEstModlWin);
			selUtils.populateInputBox("newestate_id", signAndName);
			selUtils.populateInputBox("newname_id", signAndName);
			logger.info("Verified, the '"+signLbl+"', and '"+nameLbl+"' fields are set to '"+signAndName+"'");
			verifyAddEstMandFunc(unAuthErrMsg, "newestate_id", signLbl, "newname_id", nameLbl);		
			verifySpecifiedWinNotDisp("dialogbox_cls_newestate_id");
		}
		logger.info("Verified, for each of '"+signAndNames+"' Signatues and Name values, expected error message, and error icon is displayed.");
	}

	/** 
	 * Set the 'Signature' field to 'Auto_Estate_1'. Set the 'Name' field to 
	 * 'EST_2201_Name'. Click on the 'Confirm' button.  Verify, the modal window
	 *  displays the message: 'Processing'. Then it displays the message
	 *  'The estate signature Auto_Estate_1 already exists' in red color and
	 *  only a 'Close' button is available at the bottom of the modal window.  
	 */
	public void verifyExtEstNegFunc(String sign, String name){
		String extEstMsg;
		extEstMsg = estSignMsg1 + sign + estSignMsg2;
		selUtils.populateInputBox("newestate_id", sign);
		selUtils.populateInputBox("newname_id", name);
		selUtils.getObject("dialogbox_add_newestate_id").click();
		/*Assert.assertTrue(selUtils.getObject("addestate_confirm_process_id").getText().equals(processingESTMsg),
				"After clicked on confirm button, '"+processingESTMsg+"' message is not displayed as expected.");	
		logger.info("Verified, after clicked on confirm button, '"+processingESTMsg+"' message is displayed as expected.");*/
		selUtils.waitForTxtPresent("addestate_success_result_id", extEstMsg);
		Assert.assertTrue(selUtils.getObject("addestate_success_result_id").getText().contains(extEstMsg),
				"After 'processing' message, '"+extEstMsg+"' message is not displayed as expected.");	
		msgColor = selUtils.getObject("ext_est_err_msg_id").getCssValue("color");
		colorRed = msgColor.substring(0, ((msgColor.length()-4)));
		Assert.assertEquals(colorRed, "rgba(51, 51, 51", "In 'Add an estate' window, error message is not displayed in red color.");
		logger.info("Verified, '"+extEstMsg+"' error message is displayed as expected in red color.");
	}

	/**
	 * Verify mandatory fields behaviour in Add An Estate modal window.
	 */
	public void verifyAddEstMandFunc(String errMsg, String fieldTxtBox, String fieldLabel, String field){
		selUtils.getObject("dialogbox_add_newestate_id").click();
		Assert.assertTrue(selUtils.getObject("addestate_errormsg_id").getText().equals(errMsg), 
				"In 'Add an estate' window, error message is not displayed as expected.");
		Assert.assertTrue(selUtils.getObject("addestate_errormsg_id").getAttribute("style").contains(redColor), 
				"In 'Add an estate' window, error message is not displayed in red color.");
		/*Assert.assertTrue(selUtils.getObject("addanestate_error_icon_css").isDisplayed(), "In '"+errMsg+
				"' window, error icon is not displayed.");*/
		Assert.assertTrue(selUtils.getObject("addestate_errormsg_id").getCssValue("background-image").contains("rejected.gif"), "Error message is not displayed with error icon.");
		Assert.assertTrue(selUtils.getObject(fieldTxtBox).getAttribute("style").contains(redColor), "In '"+errMsg+
				"' section, '"+field+"' text box is not displayed in "+redColor+" color.");
		Assert.assertTrue(selUtils.getObject(fieldLabel).getAttribute("style").contains(redColor), "'"+errMsg+
				"', and '"+field+"' field label is not displayed in "+redColor+" color.");
		logger.info("Verified, '"+errMsg+"' error message, and icon are displayed in red color.");
		logger.info("Verified, '"+field+"' field label and text box are displayed in red color.");
	}	

	/**
	 * Keep the 'Root Estate' field value set to specific estate and click on 
	 * the 'Confirm' button. Verify, The message 'The estate can not be moved 
	 * into itself' is displayed in the modal window in red with an Error icon 
	 **/
	public void verifyEstMoveNegFunc(String estate){
		selUtils.selectItem(selUtils.getObject("move_root_est_id"), estate);
		selUtils.getObject("move_cnfm_butt_id").click();
		Assert.assertTrue(selUtils.getObject("est_mv_err_msg_id").getText().equalsIgnoreCase(moveEstErrMsg), 
				"Error message is not displayed as expected.");
		Assert.assertTrue(selUtils.getObject("est_mv_err_msg_id").getCssValue("background-image").contains("rejected.gif"), "In 'Move' window, error icon is not displayed.");
		Assert.assertTrue(selUtils.getObject("est_mv_err_msg_id").getAttribute("style").contains(redColor), 
				"error message is not displayed in "+redColor+" color.");
		logger.info("Verified, error message, and icon are displayed in red color as expected.");
	}

	/**
	 * Sort the Estates maximum show result column data  in ascending as well 
	 * as in descending.
	 */
	public void sortColData(String browser, String locator, String defVal, String shwResLoc){
		ascVals.clear();
		descVals.clear();
		vals.clear();
		selectMaxSizeinTable("job_show_res_id", browser);
		waitforEditPageLoaded();
		colVals = selUtils.getObjects(locator);
		for (itemCount = 0; itemCount < colVals.size(); itemCount++) {
			if(colVals.get(itemCount).getText().equals(Jobs.autoPack10)){
				continue;
			}
			vals.add(colVals.get(itemCount).getText());
		}
		Collections.sort(vals);
		ascVals.addAll(vals);
		final Comparator<String> mycomparator = Collections.reverseOrder();
		Collections.sort(vals,mycomparator);
		descVals.addAll(vals);

		//Keep Default value of show result
		selUtils.selectItem(selUtils.getObject(shwResLoc), defVal);
		waitforEditPageLoaded();
	}

	/**
	 * delEstAccCod delete the estate based on the acesscode name
	 * @param accCod
	 */
	public void delEstAccCod(String accCod){
		webelements=selUtils.getObjectsDirect("accd_est_del_icn_xpath",selUtils.getPath("accd_est_del_icn_xpath").replace(nam,accCod));
		for(int k=0;k<webelements.size();k++){
			if(selUtils.isElementPresentxpath(selUtils.getPath("accd_est_del_icn_xpath").replace(nam,accCod)))
			{
				selUtils.clickOnWebElement("accd_est_del_icn_xpath", nam, accCod);
				vModWinDisp(delete);
				logger.info(" Deleting the Existing estates");			
				ObjectFactory.getEstateInstance().verifyDeleteEstateFunctionality();
				verifySpecifiedWinNotDisp("delete_resultbuttons_css");
				ObjectFactory.getEstateInstance().waitforEstPageLoded();	
			}
		}
		vExpValNotPresent("accd_code_tokn_xpath",nam,accCod, accCod);
		logger.info("Deleted the estates successfully.");
	}
	/**
	 * Verify Estates Header
	 *  */
	public void vEstHdrs(String[] estateheaders){
		waitforEstPageLoded();
		elements.clear();
		elements = selUtils.getObjects("est_header_xpath");
		for(int cnti=0;cnti<estateheaders.length;cnti++)
		{
			Assert.assertTrue(elements.get(cnti).getText().equalsIgnoreCase(estateheaders[cnti]));
			logger.info("Estate contains header "+estateheaders[cnti]);
		}
		elements.clear();
	}
	/**
	 * Removed Job from job page and verify same in estates page.
	 * @param pakName
	 */
	public void vRmvJob(String pakName){
		ObjectFactory.getJobsInstance().removeJobInEditJobpage();
		verifySpecifiedWinNotDisp("job_edt_rem_cls_id");
		selUtils.getObject("back_est_link").click();
		waitforEditPageLoaded();
		selUtils.verifyBreadCrumb(brdCrmbEdtEst11);
		ObjectFactory.getJobsInstance().verifyNewJobNotPresentInTable(pakName);
	}

	/**
	 * Verify all actions of job and delete the same.
	 * @param addpak
	 * @throws AWTException 
	 * @throws Exception
	 */
	public void vAndDelJob(String addpak) throws AWTException {
		verifyWebIconToolTip("addscenario_suspend_activate_icon_tooltip_css", Jobs.reActJob);

		//Pass the mouse over the red ball in the 'Job' column of new added job
		logger.info("Step 15 : ");
		verifyWebIconToolTip("addscenario_jobtab_yellowball_css", Jobs.jobtbRdblTolTip);

		/* Click on the Activate icon in the 'Actions' column of new added job, 
		 * an 'Activate job' modal window is displayed containing: The message: 
		 * Do you want to activate this job?', 'Confirm' and a 'Close' button*/
		logger.info("Step 16 : ");
		ObjectFactory.getJobsInstance().verifyActivateJobModalWindowInfo();

		//Click on the 'Close' button of Activate job window.
		logger.info("Step 17 : ");
		ObjectFactory.getJobsInstance().verifyCloseSpecificJobAction("actvwind_cls_butt_id", Jobs.reActJob);

		/* Click on the Activate icon in the 'Actions' column of the new added 
		 * job. Click on the 'Confirm' button, verify the expected results.*/
		logger.info("Step 18,19 : ");
		ObjectFactory.getJobsInstance().verifyConfirmActivateJobAction("addscenario_jobtab_activateicon_xpath");

		/* Click on the Delete icon in the 'Actions' column of the new added 
		 * job. A 'Remove job' modal window is displayed containing: The message
		 * 'Linked tasks will be canceled. Do you want to remove this job 
		 * linked?' A 'Confirm' and a 'Close' button  */
		logger.info("Step 20 : ");
		ObjectFactory.getJobsInstance().verifyDeleteJobWindowInfo();

		//Click on the 'Close' button of Remove job window.
		logger.info("Step 21 : ");
		ObjectFactory.getJobsInstance().verifyCloseSpecificJobAction("dialogbox_del_cls_job_id", Jobs.frz);

		/* Click on the Delete icon in the 'Actions' column of the new added job
		 * Click on the 'Confirm' button, The modal window displays the expected
		 * message.after an auto-refresh the job is removed and disappears from 
		 * the 'Jobs' table.  */
		logger.info("Step 22,23 : ");
		ObjectFactory.getJobsInstance().verifyConfirmDeleteJobAction(ObjectFactory.getJobsInstance().countJobsinJobTable(), addpak);
	}

	/**
	 * Create and verify Scenario
	 * @param browser
	 * @param scenario
	 */
	public void crtAndVerifyScenario(String browser, String scenario){
		clickEditIconOfSpecificEstate(autoEst11);
		selUtils.verifyBreadCrumb(brdCrmbEdtEst11);

		//Keep default jobs only, in jobs table.
		ObjectFactory.getJobsInstance().keepDefaultJobs(scenario, browser);

		//Click on the 'Add scenario' button.
		logger.info("Step 3 : ");
		verifyExpWinDisp("edt_est_sce_xpath", Scenarios.addScnModWin);

		/* Click on the 'Complex scenario' Drop-down list icon and choose given
		 * scenario and verify the same. */
		logger.info("Step 4 : ");
		selUtils.vselectedItemInDrpDn("task_add_id", scenario);

		/*Click on the 'Add' button of the modal window, verify the modal window
		 * displays the expected message.only a 'Close' button is available at 
		 * the bottom of the modal window. */
		logger.info("Step 5 : ");
		ObjectFactory.getScenariosInstance().verifyAddScenarioAddFunctionality();

		/*Click on the 'Close' button. Verify, the modal window is closed
		 * and after an auto-refresh the Job appears in the 'Jobs' table */
		logger.info("Step 6 : ");
		verifySpecifiedWinNotDisp("addsce_succ_cls_id");
		ObjectFactory.getJobsInstance().vNewCreatedJob(scenario);

		/* Click on the Edit icon of the new created job and Verify Breadcrum.*/
		logger.info("Step 7 : ");
		selUtils.getObject("job_edit_css").click();
		selUtils.verifyBreadCrumb(Jobs.editJobBrdc);
	}
	/**
	 * Verifies Find Scenario Drop Down window is displayed and clicks on 
	 * Search button.
	 */
	public void verifyFindScenarioForm()
	{
		selUtils.getObject("find_button_id").click();
		Assert.assertTrue(selUtils.getObject("findscenario_form_id").isDisplayed());
		logger.info("Verified,Find Scenarios drop down window is displayed");
		selUtils.getObject("search_button_xpath").click();
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);

	}
}