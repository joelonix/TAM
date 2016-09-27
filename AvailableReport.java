package com.ingenico.tam.objects;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/AvailableReport.java $
	$Id: AvailableReport.java 14026 2015-07-02 06:57:30Z vkrachuri $
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.BeforeSuite;
import org.xml.sax.SAXException;

import com.ingenico.base.TestBase;

/**
 * AvailableReport Class -Consists of methods related to AvailableReport module
 */
public class AvailableReport extends TestBase {

	public final static String AVAILREPBRDCRUB = "TMS » Available Reports » Auto_Test", REPACT = "Reports actions",SUBSCGENRPT="Subscription generic reports",
			CALDESC="This reports region provides a view on the calls done.",GENEMOD="Generation mode:",
			SUBSSUCCMSG="Subscription of reports performed successfully.",CALLS="Calls",DESCPT="Description", SIGNMAND = "The field 'Signature' is mandatory",
			DASHDISC="This report region provides a view on the calls done by each sponsor and their status during a period.",
			RPTFMT="Report Format:",UNSUBGENRPT="Unsubscription generic report",UNSUBSUCCMSG="Unsubscription of report performed successfully.",
			APPMSEXCEL="application/vnd.ms-excel",CALSATSPON="Calls status by sponsor",ACCCDACT = "Access code activity", APPZIP = "application/zip",
			FILNAMEDASH="Dashboard_report", FILMERTERM = "MerchantTerminalsReport", FILNMACT = "accessCodeActivity", FILNAMECALBYPROD="CallByProduct",
			CALSBYPROD="Calls by product",QUALINDI="Quality indicators",QUALINDDESC="This report display the list of calls grouped by category.",
			DISTOFDOWNCBP="DISTRIBUTION OF DOWNLOADING'S CALLS BY PRODUCT",DISTOFDOWNCBH="DISTRIBUTION OF DOWNLOADING'S CALLS BY HOUR",
			DISTOFDOWNCBHAE= "DISTRIBUTION OF DOWNLOADING'S CALLS BY HOUR AND ESTATE",DISTDOWNPRODEST="DISTRIBUTION OF DOWNLOADING'S CALLS BY PRODUCT AND ESTATE",
			DASHBOARD = "Dashboard", QUALIND = "Quality indicators", CALSBYHRS = "Calls by hour",FILNAMECBH="CallByHour",FILNAMECBHAS= "CallByHourAndEstate",
			FILECALLPRODEST="CallByProductAndEstate",MERTERMS = "Merchant’s terminals", HEALTHCHK = "Health check", CALS24STATS= "24h Calls Status",
			AXISRPTS = "Axis reports",AXISRPT = "Axis report",
			DESC1 = "This report region provides a view on the calls done by each sponsor and their status during a period.",
			DESC2 = "This report displays the list of calls grouped by hour. Default dates used will be those of the first day of the current month to current date",
			DESC3 = "This report displays the list of calls grouped by products. Default dates used will be those of the first day of the current month to current date",
			DESC4 = "AXIS download specific report. Default dates used will be those of the first day of the current month to current date",
			DESC5 = "This report displays the list of calls registered between 2 dates. By default, it will return the call of the last 24 hours.",
			DESC6 = "This report displays the statistics of the terminals for which a call has been done between 2 dates. By default, it will return the data of the last 24 hours.",
			DESC7 = "Health check report provides information on a specific terminal: terminal properties, statistics and its downloaded packages.",
			DESC8 = "This report provides the list of terminals which are belonging to a given merchant.", HLTCHKRPT = "HealthCheckReport",
			DESC9 = "This report returns the list of downloaded software for an access code.",
			DESC10 = "This report displays the list of calls grouped by hour and estate. Default dates used will be those of the first day of the current month to current date",
			DESC11=	"This report displays the list of calls grouped by products and estates. Default dates used will be those of the first day of the current month to current date",
			CALSTATS = "24h_call_status", CALBYHRSEST="Calls by hour and estate",CALBYPRODEST="Calls by product and estate",
			NORPTAVAIL = "No report available", UNSUBSCRSUCCMSG = "Unsubscription of report performed successfully.",
			UNSUBSCGENRPT = "Unsubscription generic report", UNSUBSCPOPUPMSG = "Do you want the unsubscription from the selected report ?", AXISREPRT="AXIS reports.",
			ATTERSTACALDESC = "Generic reports describing the call status of terminals of the estate.",ACCCDDESC = "These reports returns information linked to access code."
			, FILNAMEAXISRPT="Axis_report",AXISDOWRPT="Axis download report", APPPDF="application/pdf",CALSBYHRSEST="Calls by hour and estat...",CALBYPROEST="Calls by product and es...",SECTION=" section";

	public static String[] repActsButts = {"Launch", "Subscribe"}, repSections = {ingenico, autoEntity, client},
			subscGenRepColHdr = {select, owner, "Reports group", "Reports name", desc, "Generation mode"},
			autoEntSec = {CALLS, QUALIND, AXISRPTS}, autoTestSec = {CALLS, HEALTHCHK, Merchants.merchants, AccessCode.ACCODE},
			qaInds = {CALSBYHRS, CALSBYPROD,CALSBYHRSEST,CALBYPROEST}, autoTestCalls = {CALS24STATS, statistics},
			allReps = {DASHBOARD, CALSBYHRS, CALSBYPROD,CALSBYHRSEST,CALBYPROEST, AXISRPTS.substring(0, AXISRPTS.length()-1), CALS24STATS, statistics, HEALTHCHK, MERTERMS, actvity},
			dash={DASHBOARD},axs={AXISRPT},hltChek={HEALTHCHK},merTer={MERTERMS},acti={actvity};
	
	public static String subscGenRepAllVal[][] = 
		{{region, CALLS, DASHBOARD, DESC1, onReq},
		{region, QUALIND, CALSBYHRS, DESC2, onReq},
		{region, QUALIND, CALSBYPROD, DESC3, onReq},
		{region, QUALIND, CALBYHRSEST, DESC10, onReq},
		{region, QUALIND, CALBYPRODEST, DESC11, onReq},
		{region, AXISRPTS, AXISRPTS.substring(0, AXISRPTS.length()-1), DESC4, onReq},
		{sponser, CALLS, CALS24STATS, DESC5, onReq},
		{sponser, CALLS, Terminals.edtTrmTabs[2], DESC6, onReq},
		{sponser, HEALTHCHK, HEALTHCHK, DESC7, onReq},
		{sponser, Merchants.merchants, MERTERMS, DESC8, onReq},
		{sponser, AccessCode.ACCODE, actvity, DESC9, onReq}};		

	/**
	 * Method to initialize the XML files of AvailableReport and CommonObjects
	 * @throws FileNotFoundException 
	 * @throws Exception
	 */
	@BeforeSuite(alwaysRun=true)
	public void initSetUp() throws FileNotFoundException, IOException {
		initialize();
	}

	/**
	 * Delete all reports from view tree.
	 * @param repName
	 */
	public void deleAavilReprt(String repName){
		waitNSec(1);
		if(selUtils.isElementPresentxpath(selUtils.getPath("exp_rep_del_icn_xpath").replace(nameLbl,repName))){
			selUtils.clickOnWebElement("exp_rep_del_icn_xpath", nameLbl, repName);
			vModWinDisp(UNSUBGENRPT);
			cnfmPopupActMsg("avail_dele_Confm_id", "avail_dele_succ_msg_id",UNSUBSUCCMSG );
			verifySpecifiedWinNotPresent("avail_dele_cls_butt_id");
			vExpValNotPresent("avail_exp_rep_xpath", nameLbl, repName,repName);	
		}
	}

	/*
	 *  Verify, the specified table contains specified values.  
	 */
	public void vExpValsInAllRow(String oldVal1, String oldVal2){
		for(cnti = 0; cnti < 9 ; cnti++){
			for(cntj = 1; cntj < 6 ; cntj++){
				path = selUtils.getPath("subscr_gen_rep_exp_cell_val_css").replace(oldVal1, Integer.toString(cnti+1));
				path = path.replace(oldVal2, Integer.toString(cntj+1));				
				selUtils.vExpValPresent(selUtils.getObject("subscr_gen_rep_exp_cell_val_css", path), subscGenRepAllVal[cnti][cntj-1]);
			}
		}
	}

	/**
	 * Unsubscribe the all reports
	 */
	public void unSubScrReps(){		
		while(waitMethods.isElementPresent("view_tree_1st_del_icon_xpath")){
			selUtils.getObject("view_tree_1st_del_icon_xpath").click();
			waitMethods.waitForElementDisplayed("unsubscr_cnfm_butt_id");
			selUtils.getObject("unsubscr_cnfm_butt_id").click();
			waitMethods.waitForElementDisplayed("unsubscr_succ_cls_butt_id");
			selUtils.getObject("unsubscr_succ_cls_butt_id").click();
		}
	}

	/**
	 * Unsubscribe the expected report
	 */
	public void unSubScrRepsProc(String objLoc, String expRep){
		vExpWinDisp(objLoc, expRep, UNSUBSCGENRPT);
		selUtils.vExpValPresent("unsubscr_popup_msg_css", UNSUBSCPOPUPMSG);
		selUtils.verifyElementDisp("unsubscr_cnfm_butt_id", confirm);
		selUtils.verifyElementDisp("unsubscr_cls_butt_id", closeButton);
		confrmPopupActionMsg("unsubscr_cnfm_butt_id", "unsubscr_succ_msg_id", UNSUBSCRSUCCMSG);
		verifySpecifiedWinNotDisp("unsubscr_succ_cls_butt_id");
	}

	/**
	 * Click on the "Launch" button a modal window. Verified, "Launch" is 
	 * displayed containing:-The fields: 'Merchant name' and 'Merchant number' 
	 * -The buttons: 'Launch', 'Reset', 'Close'. 
	 **/
	public void vLaunchPopUpFlds(){
		selUtils.clickOnWebElement("Launh_butt_xpath", repActsButts[0]);
		selUtils.verifyElementDisp("merc_name_id", Merchants.mrchNmLbl);
		selUtils.verifyElementDisp("merc_no_id", Merchants.mrchNumLbl);			
		vLaunchResetClsButts();
	}

	/**
	 * Verify 'Launch', 'Reset', 'Close' buttons.
	 **/
	public void vLaunchResetClsButts(){
		selUtils.verifyElementDisp("avail_launch_id", repActsButts[0]);
		selUtils.verifyElementDisp("avail_laun_rest_id", reset);
		selUtils.verifyElementDisp("avail_laun_cls_id", closeButton);
	}

	/**
	 * Set the 'Merchant name' field to expected value, Set 'Merchant number' 
	 * field to expected value, Click on "Launch" button. Verify, modal window 
	 * is closed. 'Opening MerchantTerminalsReport.xls' modal window is opened.  
	 * Open the 'MerchantTerminalsReport.xls' file with Microsoft Excel. Verify,
	 * the file is opened containing: expected data.
	 * @param mrctName
	 * @param mrctNum
	 * @param xlsData
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public void mrctTrmsRep(String mrctName, String mrctNum, String[] xlsData) throws IOException, ParserConfigurationException, SAXException{
		selUtils.populateInputBox("merc_name_id", mrctName);
		selUtils.populateInputBox("merc_no_id", mrctNum);
		selUtils.clickOnWebElement("avail_launch_id",repActsButts[0]);
		readAndVerifXlsXmlData(getDownFilePath(FILMERTERM,"xls"), xlsData);
	}
}
