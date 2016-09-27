package com.ingenico.tam.objects;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/Jobs.java $
	$Id: Jobs.java 14026 2015-07-02 06:57:30Z vkrachuri $
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * Jobs Class - Methods related to Jobs module
 */
public class Jobs extends TestBase {

	public static String edtJobDelMsg = "Job aborted successfully", jobTbColAdCpxSce = taskTypes[0],  
			removeJob = "Remove job", suspendJob = "Suspend job", activeJob = "Activate job",
			jobtbRdblTolTip = "Frozen", availableColY = yes, availableColN = checkNo, jobActs = "Job actions",
			suspdModWin = "Suspend job", suspndPreCfmMsg = "Linked tasks will be frozen. Do you want to continue?",
			supdCfmProcsMsg = "Job freezing in progress...", abrt = "Abort", frz = "Freeze", reActJob="Reactivate",
			suspendSuccMsg = "Job frozen successfully", actCfmPrsMsg = "Job activation in progress...",
			actPreCfmMsg = "Do you want to reactivate this job?", actCfmSucMsg = "Job reactivated successfully",
			deleteModalWindow = "Remove job", editJobBrdc = "TMS » Job details", prodDtlsLbl = "Display product details",
			delPreCfmMsg = "Linked tasks will be canceled. Do you want to abort this job linked?",
			edtJobModSucMsg="Modification of priority and/or permanent property performed successfully",
			edtJobModPrcMsg="Modification of priority and/or permanent property in progress", jobTbDesClPkAdCfg = "Package added: ",
			autoPack10 = jobTbDesClPkAdCfg+"Auto_Package_10 - 10.0", breadCrumJobDtls = "TMS » Job details", 
			cnsltJobPrgceIcn = "Consult job progress", edtJobRmv = "Job removing in progress.", jobsFound = "Jobs found",
			bckJobDtls = "Back To Job Details", jobBrdcrum = "TMS » Campaigns Progress » Auto_Test", jobsPrgs = "Find jobs",delGif="Deleted.gif",jobsFstEdt="Edit First job",
			application="Application",user="user", jobTyp="Job type",blkButt="bullet_black";

	public final static String JOBSNUM = "30";

	public static String[] runningTable = {signLbl, "Technology", "Product", crtionDate},
			edtJobTabs = {"Success", "Failed","Disconnected", "Rejected","Unknown", "Canceled"},
			edtJobButts = {modify, activeJob, removeJob},
		jobsTabcolsHds = {edit, "Job", desc, crtionDate, actions},
		jobsTablecolsHds = {"Campaign type", desc, crtnDate, sts, "User Login", actions},
		jobsTablecolsHdrs = {edit, jobsTablecolsHds[1], desc, sponser, crtionDate},
		status = {"Created","Deployed","Aborted","Frozen","Finished"},
		edtJobActButts = {modify, frz, abrt},edtJobActButt = {modify, reActJob, abrt};

	public static List<String> defaulJobs = Arrays.asList("Auto_Package_1 - 1.0", "Auto_Package_2 - 2.0",
			"Auto_Package_3 - 3.0", "Auto_Package_4 - 4.0",	"Auto_Package_5 - 5.0", "Auto_Package_6 - 6.0",
			"Auto_Package_7 - 7.0", "Auto_Package_8 - 8.0", "Auto_Package_9 - 9.0", "Auto_Package_10 - 10.0",
			"Auto_Scenario_1", "Auto_Scenario_2", "Auto_Scenario_3", "Auto_Scenario_4",	"Auto_Scenario_5", 
			"Auto_Scenario_6", "Auto_Scenario_7", "Auto_Scenario_8","Auto_Scenario_9", "Auto_Scenario_10", "key1");
	
	public static String[] packgWithVersion = {"Auto_Package_1 - 1.0","Auto_Package_10 - 10.0", "Auto_Package_2 - 2.0", "Auto_Package_3 - 3.0", 
			"Auto_Package_4 - 4.0", "Auto_Package_5 - 5.0",	"Auto_Package_6 - 6.0", "Auto_Package_7 - 7.0","Auto_Package_8 - 8.0",
			"Auto_Package_9 - 9.0","Auto_Package_2_1 - 1.0", "Auto_Package_2_10 - 10.0", "Auto_Package_2_2 - 2.0","Auto_Package_2_3 - 3.0", 
			"Auto_Package_2_4 - 4.0", "Auto_Package_2_5 - 5.0",	"Auto_Package_2_6 - 6.0", "Auto_Package_2_7 - 7.0", "Auto_Package_2_8 - 8.0", 
			"Auto_Package_2_9 - 9.0","Auto_SW_Conf_Package_1 - 1.0","Auto_SW_Conf_Package_2 - 1.0","Auto_SW_Conf_Package_3 - 1.0",
			"Auto_SW_Conf_Package_2_1 - 1.0","Auto_SW_Conf_Package_2_2 - 1.0","Auto_SW_Conf_Package_2_3 - 1.0","Auto_Package_DataFile - 1.0", 
			 "Auto_Package_Multimedia - 1.0","Auto_Package_OperatingSystem - 1.0"};
	
	/**
	 * Method to initialize the XML files
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws FileNotFoundException, IOException {
		initialize();
	}

	/**
	 * Verify all job, description, creation date, Available, completed
	 * and action columns details in Jobs table, 
	 * after added job.
	 */
	public void verifyAfterClickOnSuccessfullCloseButton(String addeddJob, String description){		
		Assert.assertTrue(selUtils.getObject("addscenario_jobcol_text_css").getText().equals(addeddJob), 
				addeddJob +" text is not displayed in job coulumn.");
		logger.info("Verified, '"+addeddJob +"' text is displayed in job coulumn.");
		Assert.assertTrue(selUtils.getObject("jobstable_jobcol_ball_css").getAttribute("src").contains("bullet_orange.png"), 
				"'"+orangeColor+"' color ball is not displayed.");
		logger.info("Verified, '"+orangeColor+"' color ball is displayed, beside the '"+
				addeddJob+"' text in job column.");
		Assert.assertTrue(selUtils.getObject("addscenario_desc_text_css").getText().equals(description), 
				"Description is not displyed as expected.");
		logger.info("Verified, '" + description +"'text is displyed in description column, as expected.");
		creationDate = selUtils.getObject("addscenario_creationdate_css").getText();
		Assert.assertTrue(verifyDateTimeFormat(creationDate, "MM/dd/yyyy HH:MM:ss"), 
				"Creation date is not having the expected format.");
		logger.info("Verified, 'Creation Date' format is as expected.");
		/*Assert.assertTrue(selUtils.getObject("job_available_col_txt_css").getText().equals(Jobs.availableColY), 
				"'"+Jobs.availableColY+"' is not displyed in 'Available' column.");
		logger.info("Verified, '"+Jobs.availableColY+"' is displyed in 'Available' column.");
		Assert.assertTrue(selUtils.getObject("addscenario_completed_text_css").getText().equals(Jobs.availableColN), 
				"'"+Jobs.availableColN+"' is not displyed in 'Completed' column.");
		logger.info("Verified, '"+Jobs.availableColN+"' is displyed in 'Completed' column.");*/	
		selUtils.verifyExpIconDisplayed("addscenario_jobtab_deleteicon_css", abrt);	
		//selUtils.verifyExpIconDisplayed("addscenario_jobtab_suspendicon_xpath", suspdModWin);	
	}

	/**
	 * After an auto-refresh the 1st job appears in the 'Jobs' table
	 */
	public void vNewCreatedJob(String expJob){
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		Assert.assertTrue(selUtils.getObject("addscenario_desc_text_css").getText().contains(expJob), 
				expJob + " job is not added in job table.");
		logger.info("Verified, '" + expJob +"'job is added in job table.");
	}

	/**
	 * After an auto-refresh the 2nd job appears in the 'Jobs' table
	 */
	public void verifyTwoNewJobAfterCreatedJob(String firstJob, String secondJob, String thirdJob){
		StringBuffer jobsStrBuff = new StringBuffer();
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		jobsStrBuff.append(selUtils.getObject("addscenario_desc_text_css").getText());
		jobsStrBuff.append(selUtils.getObject("2ndjob_desc_text_css").getText());
		jobsStrBuff.append(selUtils.getObject("3rdjob_desc_text_css").getText());
		Assert.assertTrue(jobsStrBuff.toString().contains(firstJob), firstJob + " job is not added in job table.");
		logger.info("Verified, '" + firstJob +"'job is added in job table.");
		Assert.assertTrue(jobsStrBuff.toString().contains(secondJob), secondJob + " job is not added in job table.");
		logger.info("Verified, '" + secondJob +"'job is added in job table.");
		Assert.assertTrue(jobsStrBuff.toString().contains(thirdJob), thirdJob + " job is not added in job table.");
		logger.info("Verified, '" + thirdJob +"'job is added in job table.");
	}

	/**
	 * After an auto-refresh the job - 'package' not appears in the 'Jobs' table
	 */
	public void verifyNewJobNotPresentInTable(String expJob){
		Assert.assertFalse(selUtils.getObject("addscenario_desc_text_css").getText().contains(expJob), 
				expJob + " New job is added in job table.");
		logger.info("Verified, '" + expJob +"'job is not present in job table.");
	}

	/**
	 * Click on the Suspend icon in the 'Actions' column of the new added job, 
	 * verify, 'Suspend job' modal window is displayed
	 * containing: The message: 'Linked tasks will be suspended. 
	 * Do you want to continue?', A 'Confirm' and a 'Close' button.
	 */
	public void verifySuspendJobModalWindowInfo(){
		waitMethods.waitForElementDisplayed("addscenario_jobtab_suspendicon_xpath");
		selUtils.verifyExpIconDisplayed("addscenario_jobtab_suspendicon_xpath", frz);	
		verifyExpWinDisp("addscenario_jobtab_suspendicon_xpath", frz);
		Assert.assertTrue(selUtils.getObject("job_suspend_modal_window_msg_css").getText().
				equals(suspndPreCfmMsg), "'" +
						selUtils.getObject("job_suspend_modal_window_msg_css").getText() + "' is not as expected.");
		selUtils.verifyElementDisp("suspwind_cnfm_butt_id", confirm);
		selUtils.verifyElementDisp("suspwind_cls_butt_id", closeButton);
	}

	/**
	 * Click on the 'Close' button of suspend job window, verify, 
	 * the modal window is closed and nothing changes for the job.
	 */
	public void verifyCloseSpecificJobAction(String closeButtLocator, String mouseOverStr){
		selUtils.verifyElementDisp(closeButtLocator, closeButton);
		selUtils.getObject(closeButtLocator).click();
		waitMethods.waitForElementDisplayed("addscenario_desc_text_css");
		Assert.assertTrue(selUtils.getObject("addscenario_suspend_activate_icon_tooltip_css").getAttribute("onmouseover").contains(mouseOverStr),
				"After closed the "+mouseOverStr+" modal window, some changes happened for the job.");
		logger.info("Verified, after closed the delete modal window, nothing changes happened for the job.");
	}

	/**
	 * Click on the 'Confirm' button, verify the modal window displays the message: 'Job suspension in progress...'.
	 * Then the modal window is closed and after an auto-refresh of the 'Jobs' table the Suspend icon in the 
	 * 'Actions' column of the job is replaced by the Activate icon, the yellow ball in the 'job' column of the job is replaced 
	 * by a red ball, and the value in the 'Available' column of the job changed to 'N'.
	 **/
	public void verifyConfirmSuspendJobAction(String locator){
		waitMethods.waitForElementDisplayed("addscenario_jobcol_text_css");
		verifyExpWinDisp(locator, frz);		
		selUtils.verifyElementDisp("suspwind_cnfm_butt_id", confirm);
		vExpSuspendJobDetails();
	}	

	/**
	 * Click on the 'Confirm' button. The modal window displays the message: 'Job suspension in progress...'.
	 * Then the modal window is closed and after an auto-refresh of the 'Jobs' table the Suspend icon in the 'Actions' column of the 
	 * job is replaced by the Activate icon, the yellow ball in the 'Job' column of the job is replaced by a red ball,
	 *  and the value in the 'Active' column of the job changed to 'N'.
	 **/
	public void vExpSuspendJobDetails(){
		selUtils.getObject("suspwind_cnfm_butt_id").click();
		/*Assert.assertTrue(selUtils.getObject("job_suspension_msg_id").getText().equals(supdCfmProcsMsg),
				"After clicked on confirm button, job suspension processing message is not displayed as expected.");
		logger.info("Verified, After clicked on confirm button, job/ suspension processing message is displayed as expected.");	*/
		waitMethods.waitForElementDisplayed("job_addscenario_activateicon_xpath");
		selUtils.verifyExpIconDisplayed("job_addscenario_activateicon_xpath", activeJob);	
		logger.info("Verified, after an auto-refresh of the 'Jobs' table the 'Suspend icon' in the "+
				"'Actions' column of the job is replaced by the 'Activate icon'.");
		//selUtils.waitForTxtPresent("job_available_col_txt_css", Jobs.availableColN);
		
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		waitMethods.waitForElementDisplayed("jobstable_jobcol_ball_css");
		

		/*Assert.assertTrue(selUtils.getObject("job_available_col_txt_css").getText().equals(Jobs.availableColN), 
				"'Available' status is not changed to '"+Jobs.availableColN+"'.");
		logger.info("Verified, the value in the 'Available' column of the job changed to '"+Jobs.availableColN+"'.");*/
		Assert.assertTrue(selUtils.getObject("jobstable_jobcol_ball_css").getAttribute("src").contains("bullet_red.png"), 
				"'"+orangeColor+"' color ball is displayed.");
		logger.info("Verified, the '"+orangeColor+"' color ball in the 'job' column of the job is replaced by a '"+redColor+"' ball.");
	}

	/** Click on the Activate icon in the 'Actions' column of the new added job. An 'Activate job' modal window is displayed.
	 * Click on the 'Confirm' button, verify the modal window displays the message: 'Job activation in progress...'.
	 * Then the modal window is closed and after an auto-refresh of the 'Jobs' table the Activate icon in the 
	 * 'Actions' column of the job is replaced by the Suspend icon, the red ball in the 'job' column of the job is replaced 
	 * by a yellow ball, and the value in the 'Available' column of the job changed to 'Y'.*/
	public void verifyConfirmActivateJobAction(String locator){
		waitMethods.waitForElementDisplayed(locator);
		verifyExpWinDisp(locator, reActJob);
		waitMethods.waitForElementDisplayed("actvwind_cnfm_butt_id");
		selUtils.verifyElementDisp("actvwind_cnfm_butt_id", confirm);
		selUtils.getObject("actvwind_cnfm_butt_id").click();
		/*Assert.assertTrue(selUtils.getObject("activejob_confirm_msg_id").getText().equals(activateConfirmPrecessMsg),
				"After clicked on confirm button, job Activate processing message is not displayed as expected.");
		logger.info("Verified, After clicked on confirm button, job Activate processing message is displayed as expected.");*/
		waitMethods.waitForElementDisplayed(locator);
		selUtils.verifyExpIconDisplayed(locator, Jobs.frz);	
		logger.info("Verified, the modal window is closed. And, after an auto-refresh of the 'Jobs' table " +
				"the 'Activate icon' in the 'Actions' column of the job is replaced by the 'Freeze icon'.");
		//waitMethods.waitForEleTxtPresent(selUtils.getObject("job_available_col_txt_css"), availableColY);
		waitNSec(5);
		/*Assert.assertTrue(selUtils.getObject("job_available_col_txt_css").getText().equals(Jobs.availableColY), "Available status is not changed to '"+Jobs.availableColY+"'.");
		logger.info("Verified, the value in the 'Available' column of the job changed to '"+Jobs.availableColY+"'.");*/
		Assert.assertTrue(selUtils.getObject("jobstable_jobcol_ball_css").getAttribute("src").contains("bullet_orange.png"), 
				"'"+orangeColor+"' color ball is not displayed.");
		logger.info("Verified, the '"+redColor+"' color ball in the 'job' column, is replaced with '"+orangeColor+"'.");
	}

	/** Click on the Delete icon in the 'Actions' column of the new added job. A 'Remove job' modal window is displayed 
	 * containing: The message: 'Linked tasks will be canceled. Do you want to remove this job linked?'
	 * A 'Confirm' and a 'Close' button  */
	public void verifyDeleteJobWindowInfo(){
		waitMethods.waitForElementDisplayed("addscenario_jobtab_deleteicon_css");
		selUtils.verifyExpIconDisplayed("addscenario_jobtab_deleteicon_css", abrt);	
		verifyExpWinDisp("addscenario_jobtab_deleteicon_css", abrt);
		Assert.assertTrue(selUtils.getObject("delete_modalwindow_msg_css").getText().trim().
				equals(delPreCfmMsg), "'" +
						selUtils.getObject("delete_modalwindow_msg_css").getText() + "' message is not displayed as expected.");
		Assert.assertTrue(selUtils.getObject("dialogbox_del_job_id").isDisplayed(), 
				selUtils.getObject("dialogbox_del_job_id").getText() +" button is not displyed.");
		Assert.assertTrue(selUtils.getObject("dialogbox_del_cls_job_id").isDisplayed(), 
				selUtils.getObject("dialogbox_del_cls_job_id") +" button is not displyed.");
		logger.info("Verified, '"+ Estates.addAnEstModlWin + "' window is displayed containg, '" + delPreCfmMsg + "' message, '" 
				+ selUtils.getObject("dialogbox_del_job_id").getText() + "', '" + 
				selUtils.getObject("dialogbox_del_cls_job_id").getText() + "' buttons as expected.");
	}
	/** Click on the Delete icon in the 'Actions' column of the new added job, A 'Remove job' modal window is displayed.
	 * Click on the 'Confirm' button, The modal window displays the message: 'Processing'. Then the modal window is closed 
	 * and after an auto-refresh the job is removed and disappears from the 'Jobs' table.  
	 * */
	public void verifyConfirmDeleteJobAction(String totalSize, String expJob){
		final WebDriverWait wait=new WebDriverWait(driver,60);
		if(selUtils.getObject("addscenario_desc_text_css").getText().contains(expJob)){
			selUtils.getObject("addscenario_jobtab_deleteicon_css").click();
			waitMethods.waitForElementDisplayed("dialogbox_del_job_id");
			selUtils.verifyElementDisp("dialogbox_del_job_id", confirm);
			selUtils.getObject("dialogbox_del_job_id").click();
			wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id(selUtils.getPath("job_delete_confirm_msg_id")), processingMsg));
			/*	Assert.assertTrue(selUtils.getObject("job_delete_confirm_msg_id").getText().equals(processingMsg),
					"After clicked on '"+confirm+"' button, '"+processingMsg+"' message is not displayed.");
			logger.info("Verified, After clicked on '"+confirm+"' button, '"+processingMsg+"' message is displayed.");*/
			//Here, we cannot able to keep conditional wait.
			waitNSec(7);
			ObjectFactory.getEstateInstance().waitforEditPageLoaded();
			//Job table total size.
			Assert.assertTrue(Integer.valueOf(countJobsinJobTable()) < Integer.valueOf(totalSize), 
					" Deleted job is available in job table, Expected :"+Integer.valueOf(countJobsinJobTable())+"Actual :"+Integer.valueOf(totalSize));
			logger.info("Verified, '"+expJob+"' job is deleted from 'jobs' table.");
		} else if(selUtils.getObject("2ndjob_desc_text_css").getText().contains(expJob)){
			selUtils.getObject("2ndjob_jobtab_deleteicon_css").click();
			waitMethods.waitForElementDisplayed("dialogbox_del_job_id");
			selUtils.verifyElementDisp("dialogbox_del_job_id", confirm);
			selUtils.getObject("dialogbox_del_job_id").click();
			/*	Assert.assertTrue(selUtils.getObject("job_delete_confirm_msg_id").getText().equals(processingMsg),
					"After clicked on confirm button, job Remove 'processing' message is not displayed as expected.");
			logger.info("Verified, After clicked on confirm button, job Remove processing message is displayed as expected.");*/
			//Here, we cannot able to keep conditional wait.
			waitNSec(7);
			Assert.assertFalse(selUtils.getObject("modal_window_css").isDisplayed(), "the modal window is not closed.");
			logger.info("Verified, the modal window is closed.");
			ObjectFactory.getEstateInstance().waitforEditPageLoaded();

			//Job table total size.
			Assert.assertTrue(Integer.valueOf(countJobsinJobTable()) < Integer.valueOf(totalSize), 
					" Deleted job is available in job table.");
			logger.info("Verified, '"+expJob+"' job is deleted from 'jobs' table.");
		}
		else
		{
			logger.error("Expected job is not displayed.");
			Assert.fail("Expected job is not displayed.");
		}
	}

	/** Click on the Activate icon in the 'Actions' column of the new added job, an 'Activate job' modal window is displayed 
	 * containing: The message: 'Do you want to activate this job?', A 'Confirm' and a 'Close' button */
	public void verifyActivateJobModalWindowInfo(){
		waitMethods.waitForElementDisplayed("addscenario_jobtab_activateicon_xpath");
		selUtils.verifyExpIconDisplayed("addscenario_jobtab_activateicon_xpath", activeJob);	
		verifyExpWinDisp("addscenario_jobtab_activateicon_xpath", reActJob);
		Assert.assertTrue(selUtils.getObject("activatemsg_css").getText().equals(actPreCfmMsg), "'" +
				selUtils.getObject("activatemsg_css").getText() + "' is not displayed as expected.");
		Assert.assertTrue(selUtils.getObject("actvwind_cls_butt_id").isDisplayed(), 
				selUtils.getObject("actvwind_cls_butt_id").getText() +" button is not displyed.");
		Assert.assertTrue(selUtils.getObject("actvwind_cnfm_butt_id").isDisplayed(), 
				selUtils.getObject("actvwind_cnfm_butt_id") +" button is not displyed.");
		logger.info("Verified, '"+ activeJob + "' window is displayed containg: '" + actPreCfmMsg + "' message, '" 
				+ selUtils.getObject("actvwind_cnfm_butt_id").getText() + "', '" + 
				selUtils.getObject("actvwind_cls_butt_id").getText() + "' buttons as expected.");
	}

	/**
	 * Verify, keep default jobs in job table.
	 */
	public void keepDefaultJobs(String expecJob, String browser){
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		final ArrayList<String> table = new ArrayList<String>();
		table.clear();
		List<WebElement> descList;
		String job = "", exactJob = "";
		selectMaxSizeinTable("job_show_res_id", browser);
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		if (defaulJobs.contains(expecJob)) {
			descList = selUtils.getObjects("jobstab_desc_list_css");	
			for(itemCount = 0; itemCount < descList.size(); itemCount++){
				exactJob = descList.get(itemCount).getText();
				job = exactJob.substring(exactJob.indexOf(": ")+2);
				if(job.equals(expecJob)){
					table.add(descList.get(itemCount).getText());
				}
			}

			for (itemCount = 0; itemCount < table.size()-1; itemCount++) {
				deleteJob(table.get(itemCount));
				selUtils.refreshTbl("est_disppagenos_css");
			}
		} else {
			descList = selUtils.getObjects("jobstab_desc_list_css");	
			for(itemCount = 0; itemCount < descList.size(); itemCount++){
				exactJob = descList.get(itemCount).getText();
				job = exactJob.substring(exactJob.indexOf(": ")+2);
				if(job.equals(expecJob)){
					table.add(descList.get(itemCount).getText());
				}
			}
			for (itemCount = 0; itemCount < table.size(); itemCount++) {
				deleteJob(table.get(itemCount));
				selUtils.refreshTbl("est_disppagenos_css");
			}
		}
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
	}

	/**
	 * Click on delete icon of specific job in jobs table.
	 */
	public void deleteJob(String job){
		xpath = selUtils.getPath("job_delete_icon_xpath").replace("JOBDESC", job);
		selUtils.getObjectDirect(By.xpath(xpath)).click();
		waitMethods.waitForElementDisplayed("dialogbox_del_job_id");	
		selUtils.getObject("dialogbox_del_job_id").click();
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
	}

	/**
	 * Verify jobs in Table.
	 * @throws InterruptedException  
	 */
	public void verifyJobsinTable(String job, String xpath) throws InterruptedException{
		//Click on the 'Close' button.
		verifySpecifiedWinNotDisp(xpath);
		vNewCreatedJob(job);
	}

	
	/** Verify expected signatures in Edit Job Page*/
	public void vEdtJobSigns(String signs[]){
		/*	int cnti = 0;
		List<WebElement> elementSign = selUtils.getObjects("estates_list_sign_css");
		List<String> elementSignVal = new ArrayList<String>();
		for(cnti = 0; cnti < elementSign.size(); cnti++) {
			elementSignVal.add(elementSign.get(cnti).getText().trim());
		}*/
		waitMethods.waitForElementDisplayed("estates_list_sign_css");
		elementSignVal = selUtils.getLstItems(selUtils.getObjects("estates_list_sign_css"));
		for (cnti = 0; cnti < signs.length; cnti++) {
			Assert.assertTrue(elementSignVal.contains(signs[cnti]), signs[cnti]+" Expected Signature is not present in table");
		}
		logger.info(" Verified that Expected Signatures are present in table");
	}

	/** Check that in the 'Jobs' table at least 21 jobs present. and have 
	 *  only the Suspend icon are present in the 'Actions' column of 21 jobs, 
	 *  there are no Delete icon in the 'Actions' column of those 21 jobs*/
	public void noIconInJobTable(String browser){
		List<WebElement> delList;
		List<WebElement> suspList;
		editPageDefaultJobs(browser);
		suspList = selUtils.getObjects("job_table_susp_xpath");
		delList = selUtils.getObjects("job_table_del_xpath");
		if ((suspList.size() == 0) || (delList.size() == 0)) {
			logger.info(" Verified that, all jobs in the job table having no icon. ");
		} else {
			Assert.fail(" Verified that, jobs in the job table having icon.");
		}
	}

	/**
	 * In the 'Jobs' table, look for the expected job with the description and 
	 * click on its Edit icon.Verify The 'TMS >> Job details' page is displayed
	 **/
	public void editSpecJob(String browser, String job){
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		selectMaxSizeinTable("job_show_res_id", browser);	
		ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		xpath = selUtils.getPath("job_edit_icon_xpath").replace("JOBDESC", job);
		selUtils.getObjectDirect(By.xpath(xpath)).click();
		logger.info("Clicked on '"+job+"' edit icon.");
		selUtils.verifyBreadCrumb(Jobs.editJobBrdc);
	}

	//Verify that, At least 21 jobs in the table
	/**
	 * editPageDefaultJobs
	 * @param browser
	 */
	public void editPageDefaultJobs(String browser){
		//ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		List<WebElement> descList;
		//selectMaxSizeinTable("job_show_res_id", browser);
		//ObjectFactory.getEstateInstance().waitforEditPageLoaded();
		selcMaxPgSz("job_show_res_id", browser, "est_disppagenos_css", editPageDispTxt);
		descList = selUtils.getObjects("jobstab_desc_list_css");	
		if (descList.size() >= 21) {
			logger.info(" Verified that, At least 21 jobs in the table");
		} else {
			Assert.fail(" Verified that, In table less than 21 jobs");
		}
	}

	/** Verify that there is no 'Actions' column in the table*/
	public void noActionColmsInJobTable(){
		waitMethods.waitForElementDisplayed("job_cols_headings_css");
		elements = selUtils.getObjects("job_cols_headings_css");
		for (int cnti = 0; cnti < elements.size(); cnti++) {   
			Assert.assertFalse(elements.get(cnti).getText().equalsIgnoreCase(jobsTabcolsHds[4]));
		}					
		logger.info(" Verified, that required column Actions is not present in Job Tab.");
	}


	/** The 'Job type' field of the 'TMS >> Job details' page has now a color ball close to its 'Add complex scenario' value.
	 * The 'Job actions' buttons section has now the buttons: 'Modify', 'Activate job', 'Remove job'*/
	public void editJobDetailsVerification(String ball, String xpath, String color){
		vjobTypBall(ball, color);
		selUtils.verifyElementDisp("modify_link", modify);
		selUtils.verifyElementDisp(xpath, reActJob);
		selUtils.verifyElementDisp("remv_job_link", abrt);
		logger.info("Verified that 'Job actions' buttons section having the required buttons");
	}

	/**
	 * Verify, 'Job type' field is having exepected ball
	 */
	public void vjobTypBall(String ball, String color){
		Assert.assertTrue(selUtils.getObject("job_type_ball_xpath").getAttribute("src").contains(ball), 
				color+" color ball is not displayed.");
		logger.info("Verified, ball is displayed in '"+color+"' color.");
	}

	/**
	 * Verify, 'Job type' field value
	 */
	public void vjobTypFld(String value){
		Assert.assertTrue(selUtils.getObject("job_type_xpath").getText().equals(value), 
				value +" value is not displayed in 'Job type field.");
		logger.info("Verified, 'Job type' field is set to '"+value +"'.");
	}

	/**
	 * Verify, job 'Description' field value
	 */
	public void vjobDescFld(String locator, String jobDesc, String descLbl){
		Assert.assertTrue(selUtils.getObject(locator).getText().contains(jobDesc), "'Description' field is not set to '"+jobDesc+"'.");
		logger.info("Verified, '"+descLbl+"' field is set to '"+jobDesc+"'.");
	}

	/**
	 * Verify, job created date format.
	 */
	public void vjobcrDateFldFormate(){
		creationDate = selUtils.getObject("job_crea_date_xpath").getText();
		Assert.assertTrue(verifyDateTimeFormat(creationDate, "dd/MM/yyyy hh:MM:ss"), "Creation date is not having the expected format.");
		logger.info("Verified, 'Creation Date' format is as expected.");
	}

	/**
	 * Look at the edit page of specified job. Verify, The 'TMS >> Job details' page is displayed containing: 
	 * -A 'Back To Estate' button. -A 'Job type' field with the value: a yellow ball and 'Add complex scenario'. 
	 * -A 'Description' field with the value: specified value. -An 'Estate' field with the value: specified value
	 * . -A 'Priority' field with the value: specified value. -A 'Permanent task' field with the value: specified value. 
	 * -A 'User' field with the value: specified value. -A 'Creation Date' field with a value at the format: 
	 * 'YYYY-MM-DD HH:MM'. -A 'Running' tab displaying a table with the columns: 'Signature',
	 * Technology', 'Product', 'Creation Date'. -The 'Success', 'Failed', 'Disconnected', 'Rejected', 'Unknown', 'Canceled'
	 * tabs not in focus. There is no 'Job actions' buttons section with the buttons: 'Modify', 'Suspend job', 'Remove job'. 	 
	 */
	public void viewerUserEditJobDtlsPg(String browser, String jobTypVal, String jobDesc, String descLbl, String locator, String est, String prio, String permTsk, String user){
		editSpecJob(browser, jobDesc);
		ObjectFactory.getEstateInstance().vBackToEstButt();
		vjobTypBall("bullet_orange.png", orangeColor);
		vjobTypFld(jobTypVal);
		vjobDescFld(locator, jobDesc, descLbl);
		selUtils.vExpValContains("job_est_xpath",est);
		//	vjobEstFld(est);
		selUtils.vExpValContains("job_pri_xpath", prio);
		//vjobPrioFld(prio);
		selUtils.vExpValContains("job_perm_tsk_xpath",permTsk);
		//vjobPermTskFld(permTsk);
		selUtils.vExpValContains("job_user_xpath",user);
		//vjobUserFld(user);
		vjobcrDateFldFormate();
		verifyListItems("edt_job_run_tab_xpath",runningTable);
		selUtils.vExpNotFocusedTabs(edtJobTabs);
		selUtils.vEleNotPresent("modify_link", modify);
		selUtils.vEleNotPresent("susp_job_link", Jobs.suspdModWin);
		selUtils.vEleNotPresent("remv_job_link", Jobs.deleteModalWindow);
	}

	/**  The 'job' modal window is displayed containing:
	 * -The message: 'Linked tasks will be suspended. Do you want to continue?'
	 * -A 'Confirm' and a 'Close' button*/
	public void modalWindowDetailsVerification(String objLoc1, String objLoc2, String objLoc3, String succMsg, String popUpWin){
		Assert.assertTrue(selUtils.getObject(objLoc1).getText().contains(succMsg), succMsg + "' message, ' is not displayed");
		logger.info("Verified, '"+ popUpWin + "' window is displayed containg, '" + succMsg + "' message, '" );
		selUtils.verifyElementDisp(objLoc2, confirm);
		selUtils.verifyElementDisp(objLoc3, closeButton);
	}


	/** Click on the Edit icon of the new created job and Verify details.*/
	public void editJobDetailsPage(String modalWindow, String jobName, String descLbl, String edtEst, String tskPrio, String prm, String user, String xpath){
		selUtils.verifyBreadCrumb(editJobBrdc);
		ObjectFactory.getEstateInstance().vBackToEstButt();
		vjobTypBall("coregModif2.gif", orangeColor);
		vjobTypFld(modalWindow);
		vjobDescFld(xpath, jobName, descLbl);
		selUtils.vExpValContains("job_est_xpath", edtEst);
		selUtils.vExpValContains("job_pri_xpath", tskPrio);
		selUtils.vExpValContains("job_perm_tsk_xpath",prm);
		selUtils.vExpValContains("job_user_xpath",user);
		vjobcrDateFldFormate();
		Assert.assertTrue(selUtils.getObject("edit_job_box_xpath").getText().contains(statusRun));
		logger.info("Verified that 'Job progress' pie chart showing the jobs as 'Running'");
		selUtils.verifyElementDisp("modify_link", modify);
		selUtils.verifyElementDisp("susp_job_link", Jobs.suspdModWin);
		selUtils.verifyElementDisp("remv_job_link", deleteModalWindow);
		verifyListItems("edt_job_run_tab_xpath",runningTable);
		selUtils.vExpNotFocusedTabs(edtJobTabs);
	}

	/** Click on the Edit icon of the new created job and Verify details.*/
	public void editJobDetailsPage(String browser, String modalWindow, String jobName, String descLbl, String edtEst, String tskPrio, String prm, String user, String xpath, boolean term){
		editSpecJob(browser, jobName);
		ObjectFactory.getEstateInstance().vBackToEstButt();
		vjobTypBall("bullet_orange.png", orangeColor);
		vjobTypFld(modalWindow);
		vjobDescFld(xpath, jobName, descLbl);
		selUtils.vExpValContains("job_est_xpath", edtEst);
		selUtils.vExpValContains("job_pri_xpath", tskPrio);
		selUtils.vExpValContains("job_perm_tsk_xpath",prm);
		selUtils.vExpValContains("job_user_xpath",user);
		vjobcrDateFldFormate();
		selUtils.verifyElementDisp("modify_link", modify);
		selUtils.verifyElementDisp("frz_job_link", Jobs.frz);
		selUtils.verifyElementDisp("remv_job_link", Jobs.abrt);
		verifyListItems("edt_job_run_tab_xpath",runningTable);
		selUtils.vExpNotFocusedTabs(edtJobTabs);
	}


	/** Click on the 'Confirm' button of remove button of edit job page.
	 * The modal window displays the message: 'Job removing in progress...'.
	 * Then it displays the message: 'Job removed successfully'.*/
	public void removeJobInEditJobpage(){
		selUtils.getObject("job_edt_rem_cnfrm_id").click();
		logger.info(" Clicked on confirm Button");
		selUtils.waitForTxtPresent("job_edt_rmv_msg_id", edtJobDelMsg);
		Assert.assertTrue(selUtils.getObject("job_edt_rmv_msg_id").getText().contains(edtJobDelMsg));
		logger.info(" Verify That displays the message: "+edtJobDelMsg);
	}

	/** Click on the 'Close' button. The modal window is closed and after an
	 *  auto-refresh the job appears in the 'Jobs' table
	 */
	public void verifyKeyinJobTable() {
		selUtils.getObject("addkey_successbuttons_css").click();
		if(selUtils.getCommonObject("modal_window_css").isDisplayed()){
			logger.error("After clicked on 'close' button, the modal window is not closed.");
			Assert.fail("After clicked on 'close' button, the modal window is not closed.");
		}else
		{
			logger.info("Verified, the modal window is closed.");
			ObjectFactory.getEstateInstance().waitforEditPageLoaded();
			Assert.assertTrue(selUtils.getObject("addscenario_jobcol_text_css").getText().equals(Key.addKey), 
					Key.addKey +" text is not displayed in job coulumn.");
			logger.info("Verified, '"+Key.addKey +"' text is displayed in job coulumn.");
			Assert.assertTrue(selUtils.getObject("addscenario_desc_text_css").getText().equals(Key.key1Added), 
					"Description is not displyed as expected.");
			logger.info("Verified, '" + Key.key1Added +"'text is displyed in description column, as expected.");
		}
	}

	/** 
	 * Count the number of jobs in job table.
	 */
	public String countJobsinJobTable() {
		//Job table total size.
		final String jobsTableSize = selUtils.getObject("jobs_table_size_css").getText();
		final String[] jobsTableSizeArr = jobsTableSize.split(" ");
		totalSize = jobsTableSizeArr[jobsTableSizeArr.length-1];
		return totalSize;
	}

	/**
	 * Verify all 2nd job, description, creation date, Available, completed 
	 * and action columns details in Jobs table, 
	 * after added job.
	 */
	public void verify1stAnd2ndJobDetails(String addeddJob, String[] descriptions){	
		for(itemCount = 0; itemCount <descriptions.length ; itemCount++){
			
			final String addSWConfigDesc = jobTbDesClPkAdCfg + descriptions[itemCount];
			if(selUtils.getObject("addscenario_desc_text_css").getText().equals(addSWConfigDesc)){
				verifyAfterClickOnSuccessfullCloseButton(Packages.addPak, addSWConfigDesc);
			}
			else if(selUtils.getObject("2ndjob_desc_text_css").getText().equals(addSWConfigDesc)){
				Assert.assertTrue(selUtils.getObject("2ndjob_jobcol_text_css").getText().equals(addeddJob), 
						addeddJob +" text is not displayed in 2nd job coulumn.");
				logger.info("Verified, '"+addeddJob+"' text is displayed in 2nd job coulumn.");
				Assert.assertTrue(selUtils.getObject("2nd_jobcol_ball_css").getAttribute("src").contains("bullet_orange.png"), 
						"Yellow color ball is not displayed.");
				logger.info("Verified, orange ball is displayed, beside the '"+addeddJob+"' text in 2nd job column.");
				Assert.assertTrue(selUtils.getObject("2ndjob_desc_text_css").getText().equals(addSWConfigDesc), 
						"Description is not displyed as expected.");
				logger.info("Verified, '" + addSWConfigDesc +"'text is displyed in description column, as expected.");
				/*Assert.assertTrue(selUtils.getObject("job_available_col_txt_css").getText().equals(availableColY), 
						"'"+availableColY+"' is not displyed in 'Available' column.");
				logger.info("Verified, '"+availableColY+"' is displyed in 'Available' column.");
				Assert.assertTrue(selUtils.getObject("2ndjob_completed_text_css").getText().equals(availableColN), 
						"'"+availableColN+"' is not displyed in 'Completed' column.");
				logger.info("Verified, '"+availableColN+"' is displyed in 'Completed' column.");*/
				creationDate = selUtils.getObject("2ndjob_creationdate_css").getText();
				Assert.assertTrue(verifyDateTimeFormat(creationDate, "MM/dd/yyyy HH:MM:ss"), 
						"Creation date is not having the expected format.");
				logger.info("Verified, 'Creation Date' format is as expected.");
				//selUtils.verifyExpIconDisplayed("2ndjob_suspend_activate_icon_tooltip_css", suspendJob);
				//selUtils.verifyExpIconDisplayed("2ndjob_jobtab_deleteicon_css", abrt);
				selUtils.vExpIcon("edt_est_abrt_xpath", addSWConfigDesc, abrt);
			}
		}
	}

	/** Click on the 'Close' button.Verify the modal window is closed, the 
	 * 'Root Estate' field value changed to 'Auto_Test' and There are no jobs in the 'Jobs' table. */
	public void rtEstWithJobs(String exactEstateName){
		verifySpecifiedWinNotPresent("move_res_cls_butt_id");
//		exactEstateName = client.substring(client.indexOf("A"));
		Assert.assertTrue(selUtils.getObject("editestatepage_rootestate_xpath").getText().equals(exactEstateName), 
				exactEstateName+" estate is not expected root estate.");
		logger.info("Verified, '"+exactEstateName+"' estate is expected root estate.");
		Assert.assertTrue(selUtils.getObject("nodata_jobtable_xpath").getText().contains(noDataFndMsg), " Verified that, Jobs in Job table");
		logger.info(" Verified that, No Jobs in Job table");
	}

	/** Click on the 'Confirm' button.
	 * Then the modal window is closed.*/
	public void tableJobSusp(){
		selUtils.getObject("suspwind_cnfm_butt_id").click();
		Assert.assertTrue(selUtils.getObject("job_suspension_msg_id").getText().equals(supdCfmProcsMsg),
				"After clicked on confirm button, job suspension processing message is not displayed as expected.");
		logger.info("Verified, After clicked on confirm button, job suspension processing message is displayed as expected.");
		//Here, we cannot able to keep conditional wait.
		waitNSec(7);
		Assert.assertFalse(selUtils.getObject("modal_window_css").isDisplayed(), "the modal window is not closed.");
		logger.info("Verified, the modal window is closed.");	
	}

	/** Click on the Consult job icon in the 'Actions' column of the table for the expected job.
	 * The 'TMS >> Job details' page is displayed. The 'expected' field has the expected value.*/
	public void vDetlsJob(String cnsltIcnLoc, String jobName, String prmTskLoc, String prmTskVal){
		vConsJobDetls(cnsltIcnLoc, jobName);
		Assert.assertTrue(selUtils.getObject(prmTskLoc).getText().equals(prmTskVal));
		logger.info("Verified, '"+prmTskLbl+"'field is set to '"+prmTskVal+"'. ");
	}

	/** Click on the Consult job icon in the 'Actions' column of the table for the expected job.
	 * The 'TMS >> Job details' page is displayed
	 */
	public void vConsJobDetls(String cnsltIcnLoc,String name){
		xpath = selUtils.getPath(cnsltIcnLoc).replace("Name", name);
		selUtils.getObjectDirect(By.xpath(xpath)).click();
		selUtils.verifyBreadCrumb(Jobs.breadCrumJobDtls);
	}

	/**
	 * Verify 'Back To Job Details' button.
	 * @param columns
	 */
	public void vBackJobDtlsButt(){
		Assert.assertTrue(selUtils.getCommonObject("back_job_dtls_link").isDisplayed(), "'"+bckJobDtls+"' button is not displayed.");
		logger.info("Verified, '"+bckJobDtls+"' button is displayed.");
	}
	
	/**
	 * vJobsPageElemt
	 * Verify if job page contains the following elements. 
	 * The 'TMS >> Jobs Progress >> Auto_Test' page is displayed containing:-A 'Find' button.-A 'Jobs found' section
	 * -A table of jobs with the columns: 'Edit', 'Job type', 'Description', 'Sponsor', 'Creation Date', 'Actions'
	 */
	public void vJobsPageElemt(){
		waitMethods.waitForElementDisplayed("job_cols_hdrs_css");
		selUtils.verifyElementDisp("find_but_xpath", findButt);
		selUtils.verifyElementDisp("sec_title_xpath", jobsFound);
		vExpColsInTab(selUtils.getTabColHds("job_cols_hdrs_css"), jobsTablecolsHds);		
	}
	/**
	 * vEditJobsPageElemt
	 * Verify if edit job page contains the following elements.
	 * Click on the Edit icon in the 'Edit' column of the table for the first job
	 * The 'TMS >> Job details' page is displayed containing: -A 'Back To List' button
	 *-A 'Job type' field with the value: a yellow ball and 'Add package'
	 *-A 'Display product details' field,-A 'Priority' field,-A 'Permanent task' field
	 *-A 'User' field,-A 'Creation date' field with a value at the format: 'YYYY-MM-DD HH:MM'
	 *-A 'Job actions' buttons section with the buttons: 'Modify', 'Suspend job', 'Remove job'
	 *-A 'Running' tab displaying a table with the columns: 'Signature', Technology', 'Product', 'Creation Date'
	 *-The 'Success', 'Failed', 'Disconnected', 'Rejected', 'Unknown', 'Canceled' tabs not in focus
	 */
	public void vEditJobsPageElemt(){
		selUtils.verifyBreadCrumb(editJobBrdc);
		selUtils.vBackToLstButt();
		selUtils.vExpValPresent("edt_job_desc_xpath", nameLbl, prodDtlsLbl, prodDtlsLbl);
		selUtils.verifyElementDisp("job_edt_taskprio_id", tskPriLbl);			
		selUtils.verifyElementDisp("job_edt_perm_id", prmTskLbl);
		selUtils.verifyElementDisp("job_user_xpath", user);
		vjobcrDateFldFormate();
		verifyListItems("edt_job_run_tab_xpath",runningTable);
		selUtils.vExpNotFocusedTabs(edtJobTabs);
	}
	
	
	/**
	 * vJobsTabData
	 * verify table data
	 */
	public void vJobsTabData(){
		itemCount=webtableGetColNumOfHeader("job_tab_hdr_xpath",sts);
		count=webtableGetColNumOfHeader("job_tab_hdr_xpath",desc);
		xpath=selUtils.getPath("job_stat_ver_xpath").replace("Name",Jobs.jobTbDesClPkAdCfg+Packages.pkgNmDrpDnItm12);
		webElement=selUtils.getObjectsDirect(By.xpath(xpath)).get(itemCount-count-1);	
		selUtils.vExpValPresent(webElement,abrted);
		itemCount=webtableGetColNumOfHeader("job_tab_hdr_xpath",actions);
		webElement=selUtils.getObjectsDirect(By.xpath(xpath)).get(itemCount-count-1);
		Assert.assertEquals(webElement.findElements(By.tagName("img")).size(),1);
		Assert.assertTrue(webElement.findElements(By.tagName("img")).get(0).getAttribute("src").contains("edit2"));
	}
	
}
