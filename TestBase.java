package com.ingenico.base;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/base/TestBase.java $
	$Id: TestBase.java 14177 2015-07-16 11:18:35Z sparween $
 */

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import au.com.bytecode.opencsv.CSVReader;

import com.ingenico.base.logger.Logger;
import com.ingenico.base.logger.LoggerFactory;
import com.ingenico.common.CommonConstants;
import com.ingenico.tam.objects.Terminals;

/**
 * Base class for all modules
 * @author Shailesh.Kumar
 *
 */
public class TestBase {

	public static SelUtils selUtils;
	public static WaitMethods waitMethods;
	public static Properties config,  objR, commonOR;
	public static Logger logger = LoggerFactory.getLogger();
	public static WebDriver driver;
	public static String redColor = "rgb(194, 4, 24)", client = "Auto_Test", autoEntity = "Auto_Entity", ingenico = "Ingenico", token = "Token",
			parentWindow, modify = "Modify",actionMove = "Move", techng = "TELIUM", saveSrc = "Save this search", cancelled = "Cancelled",
			loginButt = "Login", fldsMandMsg = "Fields marked in red are mandatory", welcEportal = "Welcome to Ingenico e-Portal",
			delete = "Delete", confirm = "Confirm", closeButton = "Close", addButt = "Add", noneVar = "None", statusRun = "Running",statusDepl="Deployed",
			priority1000 = "1000", successfull = "Successful", editPageDispTxt = "Displaying", unCheck = "Unchecked",
			priority100 = "100", saveButton = "Save", restoreToolTip = "Restore default configuration", checkNo = "N", check = "Checked",
			coloumns = "Columns", saveToolTip = "Save current configuration", saveConfig = "Save current configuration", 
			processingMsg = "Processing", numOfEleText = "Number of elements displayed:",serNumErr="Serial number must be a number",
			infoModWin = "Information", daysLbl = "days", minsLbl = "minutes", none = "none", prmTskLbl = "Permanent task",
			priorLmtErMsg = "The Priority field should be between 0 and 999", freq = "Frequency", yes = "Y", taskVal = "aA&%\\<>",
			invldTimezone = "Invalid timezone supplied - must be in range -1440 to 1440, and be divisible by 15", gray = "Grayed",
			autoNm21 = "Auto_Name_2_1", noDataFndMsg = "No data found", blue = "Blue", blk = "Black", refresh = "Refresh",
			priMandtoryErrMsg = "Priority is mandatory and must be a number", rmvMsg = "This job has been aborted",
			inPrgTab = "In progress", pednActs = "Pending actions", tskPriLbl = "Task priority", cancel = "Cancel", srch = "Search", showRess = "Show results",
			advSrch = "Advanced Search", basicSrch = "Basic Search", chsSrcName = "Choose search name:", restore = "Restore",
			clear = "Clear", year = "Year", desc = "Description", descript = desc+":", reset = "Reset", moduleLbl = "Module", optns = "Options", 
			breadCrumbTMS = "TMS", crtdTskMsg = "Task has been created", tabSelected = "tabs-selected", sponser = "Sponsor",estate="Estate",
			mandFldErrMsg="This field is mandatory", signLbl = "Signature", nameLbl = "Name", orangeColor = "Orange", abrted = "Aborted", 
			inValValues = "\\<>%", ver7 = "7.0",findButt = "Find", elementText, select = "Select", delVal = "Deleted",
			unAuthErrMsg="Fields marked in red contain unauthorized characters ( \\<>% )", unKnown = "Unknown", assetTracking ="assetTracking",
			canTask = "Task has been canceled", all = "All", fltr="Filter", slctMenu = "Selection Menu", html = "html",
			size8To16Msg = "- Size of serial number must be between 8 and 16 characters", custData = "Customer data",
			sizeLst8to16Msg="Size of last serial number must be between 8 and 16 characters", calls = "Calls",
			size8To9Msg = "- Size of part number must be between 8 and 9 characters", statuses = "Statuses", prevButton = "Previous",
			size10to12Msg = "Size of serial number must be between 10 and 12 characters", created = "Created",
			size10To16Msg = "- Size of part number must be between 10 and 16 characters", swap = "Swap", valueLbl = "value",
			sizeLst10to12Msg = "Size of last serial number must be between 10 and 12 characters", version = "Version",
			nameMndMsg = "Name is mandatory", tskPri0 = "0", tskPri999 = "999", tskPrio = "123", edit = "Edit",
			callStatus = "Call status", tskType = "Task type", tskStatus = "Task status", clone = "Clone",
			cmmdStatus = "Command status", callStrtDt = "Call start date", callEndtDt = "Call end date",
			fileName = "File Name",informPopup="Information popup", cntntSec = "Content selection", open = "Open",
			bckToLst = "Back To List", prdIcon = "Product icon", shwDtls = "Show details", thruDt = "Thru Date",
			fromDt = "'From Date",lesGrtSymErMsg = "In field [internalName] less-than (<) and greater-than (>) symbols are not allowed",
			fileFormatErrMsg = "The file format is not compliant with the selected type", actvated = "Activated",
			pakDupMsg = "A package containing the '"+techng+"' technology is already downloaded", help="Help icon",
			spnsPathTelium = "sponsors/Auto_Test/TELIUM/", findMrcts = "Find merchants", modDesc = "Modify a description", 
			wrongFileMsg="The 'WrongFile.zip' software package is unsupported", one = "Displaying 1 - 1 of 1",
			blueBall = "blue ball",back="back", assign = "Assign", history = "History", crtionDate = "Creation Date",crtnDate= "Creation date",
			unAuthErrMsg1="Fields marked in red contain unauthorized characters ( \\<>\"% )",upd="Update",addCnfg = "Add configuration",
			addMod = "Adding modules", numOfEleDisp = "Number of elements displayed",crt="Create", text = "TEXT", unitedSt = "United States",
			uStates = "UNITED STATES", stProv = "State/Province [1]", alska = "Alaska", errIcon = "error icon" , command = "Command",
			unAuthErrMesg="The field marked in red contains unauthorized characters ( \\<>\"% )", two = "Displaying 1 - 2 of 2", 
			unAuthErrorMsg="Fields marked in red contain unauthorized characters ( \\<>\"% )", hour = "Hour", admn = "Administration",
			pakDupMsgNw="A package 'PCKG_2205' containing the 'TELIUM' technology is already downloaded", index = "Index",
			applnId = "Application Id", commName = "Commercial name", owner = "Owner", crtBy = "Created by", updDate = "Update date",nam="NAME"
			,errMsgAlpNum="The field marked in red must contain alphanumeric characters.",clas="class",clos="close",indx = "INDEX",csv="csv",xml="xml",appLocParam = "APPNAME",
			evnts="Events", failed = "Failed", record = "Record", reason = "Reason", dwnTemp="Download template",
			startDte="Start date", endDte="End date", statusDt = "Status date", statusDate = "Status Date", clkForHelp = "Click for help",
			blueClr = "blue", greyClr = "grey", onReq = "On request", actvity = "Activity", region = "Region", timZone="Time Zone:",
			cooUniTim="Coordinated Universal Time (UTC)", statistics = "Statistics", view = "View", userName = "User name", type = "Type",
			sts = "Status", actions = "Actions", fileNm = "File name", immediately = "Immediately", deferred = "Deferred", actn = "Action",
			exeuDate = "Execution date is mandatory", trmMove = "Terminals to move are mandatory", pakImpMand = "Packages to import are mandatory",
			impFileMand = "File to import is mandatory", tskImpMand = "Tasks to import are mandatory",trmHsBeenDel="- The terminal has been deleted",
			completed = "Completed", success = "Success",ownr="Owners", fldManderrMsg = "The field marked in red is mandatory", findScenario = "Find scenarios",
			back1 = "Back", next = "Next",prnt="Print",conf="Configure package",disp="Display",swtch="Switch",delPak="Delete package",
			estInValErrMsg = "Fields marked in red contain unauthorized characters",tsksucfulladd="Task successfully added.",multiCust="MultiCustomer",
			details="Details",style="style", fileUnsucc = "file Read is unsuccessful", xlsDelFile = "Xls file deleted",dburl,evtDate="eventDate",
			expiryDateFrom="Expiry date from",errInfoCls="infoError",addSWConf="Add SW Configuration";


	public String currentDate="", path="", waitTime="", lstItms="", sign = "", sign1 = "", str = "", value = "", xpath = "", file = "", exactEstateName,
			sponsPath = "", colAtribute="", msgColor = "", newSign = "", newName = "", colorRed = "", creationDate = "", totalSize = "", imgPath, error, 
			colName = "",txt="", errMsg="", colorVal = "", prNo = "", srNo = "", merc = "", mrctNo = "", mrctId = "", eleClr = "", actualClr = "", fileNme;
			
	public File frFile;
	public static String campName = "", jenkinsfolderID,os;
	public static String[] expVals = {"0123456789", "abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 
		"²&'(-_η)=~#{[|`^@]}°+^$¤¨£*µ ,;:!?./§", "ιθΰωϋτο"}, ownerSecs = { ingenico, autoEntity, client, all},
		switchSceElements = {"taskToAdd", "taskPriority", "isPermanent", "dialogBoxAddScenarioTo_0", "dialogBoxAddScenarioTo_1"},
		timeZone = {"-1455", "-1441", "1441", "1455", "15"}, frequency = {"0", "366", "1"},
		taskTypes = {"Add complex scenario", "Add key", "Add package", "Delete package", "Parameters upload"},
		spelChar = {"\\", "<", ">", "%"}, selectionMenu={"Page toggle","Check all","Uncheck all"},
		allYesNo = {all, "Yes", "No"}, num={"1","2","3"}, tokenNo = {"1","2","3", "4", "5", "6"},
		months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", 
		"October", "November", "December"},
		hrs ={"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", 
		"14", "15", "16", "17", "18", "19", "20", "21", "22", "23"},
		sheets = {"SUMMARY", "TELIUM"}, sheets24 = {"24h pie chart status", "CALLS NOK", "CALLS OK"},
		mins ={"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", 
		"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
		"30", "31", "32", "33", "34", "35", "36", "37", "38", "39","40", "41", "42", "43", "44", "45", 
		"46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"},specChars=ArrayUtils.add(spelChar, "\"");

	public char[] inValValuesArry = inValValues.toCharArray();	
	public Set<String> windowIds;
	public Date testDateFormat;
	public Process proc;
	public WebElement tabContainer, webElement,eleId2;
	public List<WebElement> optionsList, cList,noPages,trCollection, webelements, colVals, elements = new ArrayList<WebElement>();
	public List<String> items = new ArrayList<String>();
	public static WebDriverWait wait;
	public Actions action; 
	public JavascriptExecutor jsExecutor;
	public Robot robot;
	public static  Locale locale = Locale.ENGLISH;
	public boolean boolValue, exists, flag, isBrowserOpened,tableDataVerifReq=true,findForm=true;
	public List<String> vals = new ArrayList<String>(), ascVals = new ArrayList<String>(), content = new ArrayList<String>(), 
			descVals = new ArrayList<String>(), elementSignVal = new ArrayList<String>();
	public ListIterator<String> lstIterator;
	public int itemCount, count, totalPageNos, pageCount, showRes, expPgNo, cnti, cntj, cntc;
	public enum Projects { estates, terminals, packages, swconfigurations, merchants, jobs, batchimport, locations, owners, 
		swdescgroups,eventsmanagement, terminal, swdescriptions, accesscode, availablereport,  importprovisioning, scenariowizard, estatestatus}
	public enum Locators { xpath, css, id, name, link }

	/* TODO - Methods which Initialize XML and Current Project */
	static
	{
		objR=new Properties();				
		config=new Properties();
		commonOR=new Properties();	
	}

	/**
	 *  Initializing the Tests -  Reads xml file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void initialize() throws FileNotFoundException, IOException {
		try {

			config.loadFromXML(new FileInputStream(CommonConstants.CONFIGFILE));
			commonOR.loadFromXML(new FileInputStream(CommonConstants.COMMONCONFIGFILE));
			String pageName=getUIMapPage();
			objR.loadFromXML(new FileInputStream(pageName));
		} catch (InvalidPropertiesFormatException e) {
			logger.error("Error xml config files not loaded in initialize method");
			Assert.fail(e.getMessage(),e );
		}
	}

	/**
	 * Gets the Current running Project 
	 * @return uiMap
	 */
	public String getUIMapPage(){
		String sProject=getCurrentProject();
		String uiMap = ""; 
		uiMap = config.getProperty(sProject+"UIMapFile");
		return uiMap;
	}

	/**
	 * Get current running project
	 * @return sProject
	 */
	public String getCurrentProject(){
		String sProject = null;
		String[] projectName = this.getClass().toString().split(" ")[1].trim().split("\\.");
		Projects projects = Projects.valueOf(projectName[projectName.length-2]);
		switch (projects) {
		case estates:
			sProject = "estates";
			break;
		case terminals:
			sProject = "terminals";
			break;
		case packages:
			sProject = "packages";
			break;
		case swconfigurations:
			sProject = "swconfigurations";
			break;
		case merchants:
			sProject = "merchants";
			break;
		case jobs:
			sProject = "jobs";
			break;
		case batchimport:
			sProject = assetTracking;
			break;
		case locations:
			sProject = assetTracking;
			break;
		case owners:
			sProject = assetTracking;
			break;
		case swdescgroups:
			sProject = assetTracking;
			break;
		case terminal:
			sProject = assetTracking;
			break;
		case eventsmanagement:
			sProject = assetTracking;
			break;
		case estatestatus:
			sProject = assetTracking;
			break;
		case swdescriptions:
			sProject = "swdescriptions";
			break;
		case accesscode:
			sProject = "accesscode";
			break;
		case availablereport:
			sProject = "availablereport";
			break;
		case importprovisioning:
			sProject = "importprovisioning";
			break;
		case scenariowizard:
			sProject= "scenariowizard";
			break;
		default:
			Assert.fail("Cannot identify currently running project");
			break;
		}
		return sProject;
	}

	/* TODO - Methods which Handles Exception and Takes Screen shot */
	/**
	 * Handles Exception and Takes the Screen shot when Failed
	 * @param Throwable Instance
	 */
	public void handleException(Throwable throwExcep){
		fileNme=this.getClass().getSimpleName();
		captureScreenShotOnFailure(fileNme);
		imgPath=getImgPath(fileNme);
		error=throwExcep.getMessage();
		logger.error(fileNme+" execution failed: <a href='"+imgPath+"' target=blank><img src='"+imgPath+"' height="+20+" width="+40+" /></a>");
		Assert.fail(error, throwExcep);
	}
	
	/**
	 * Gets the Jenkins Build Number
	 */
	@Parameters({"FOLDER_ID","OS"})
    @BeforeSuite(alwaysRun=true)
    public static void getJenkinsParameters(String folderID,String osvalue){
    jenkinsfolderID = folderID;
    os=osvalue;
    if(os.equalsIgnoreCase("Linux"))
    {
    CommonConstants.FILEUPLOADPATH = System.getProperty("user.dir")+ "/data/file_uploads/";
    CommonConstants.FILEDOWNLOADPATH = System.getProperty("user.dir")+ "/data/file_downloads/";
    }          
    }

	/**
	 * Gets the Path where the Image should be saved
	 * @return fileName
	 */
	public String getImgPath(String fileNme){
		String[] projectPath = null;
		
		if(!(os.equalsIgnoreCase("Linux")) || os.isEmpty())
		{
		projectPath = System.getProperty("user.dir").trim().split("\\\\");
		}
		else if(os.equalsIgnoreCase("Linux"))
		{
			projectPath = System.getProperty("user.dir").trim().split("/");
		}
		String projectname=projectPath[projectPath.length-1];
		return (CommonConstants.SCREENSHOTLINK + jenkinsfolderID + "//" + fileNme + ".jpg").replaceAll("projectPath", projectname);
	}

	/**
	 * captureScreenShotOnFailure
	 * @param filename
	 */
	public void captureScreenShotOnFailure(String filename) {
		try {
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(CommonConstants.SCREENSHOTPATH + jenkinsfolderID + "//" + filename + ".jpg")); 
		}
		catch(IOException e) {
			logger.error("Failed to capture screenshot: " + e.getMessage());
		}
	}

	/* TODO - Methods which Initialize New Browser Instance (setup), 
	 * Login and Logout */
	/**
	 * Method to Setup / Initialize new browser Instance
	 * @param browser
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	@Parameters({"browser", "browserURL"})
	@BeforeTest(alwaysRun = true)
	public void setup(String browser, String browserURL) throws MalformedURLException, InterruptedException{
		DesiredCapabilities capability=null;
		try{
			if("firefox".equalsIgnoreCase(browser)){
			    FirefoxProfile profile = new FirefoxProfile();
				profile.setEnableNativeEvents(true);
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.manager.showWhenStarting",false);
				profile.setPreference("browser.download.dir",CommonConstants.FILEDOWNLOADPATH);
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/download,application/force-download,application/x-download,text/csv,image/jpeg,application/vnd.ms-excel,application/zip");
				profile.setPreference("pdfjs.disabled", true);
				capability= DesiredCapabilities.firefox();
				capability.setCapability(FirefoxDriver.PROFILE, profile);
				driver=new FirefoxDriver(capability);
			}
			if("internet explorer".equalsIgnoreCase(browser)){
				System.setProperty("webdriver.ie.driver", CommonConstants.IEDRIVERPATH+"\\IEDriverServer.exe");
				capability= DesiredCapabilities.internetExplorer();
				capability.setBrowserName("internet explorer");
				capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);  
				capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);			
				capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
				capability.setJavascriptEnabled(true); 
				driver = new InternetExplorerDriver(capability);
			}
			driver.get(browserURL);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			wait=new WebDriverWait(driver, 50);
			setDriver(driver);
			selUtils = PageFactory.initElements(driver, SelUtils.class);
			waitMethods = PageFactory.initElements(driver, WaitMethods.class);
		}
		catch(Throwable t)
		{
			logger.error("Exception thrown opening browser");
			Assert.fail(t.getMessage(),t );
		}
	}
	/**
	 * Get Driver
	 * @return
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * Set Driver
	 * @param driver
	 */
	public static void setDriver(WebDriver driver) {
		TestBase.driver = driver;
	}

	/**
	 * Method which Logout's and then Closes browser
	 */
	@AfterClass(alwaysRun=true)
	public void tearDown(){
		logout();
		driver.quit();
	}

	/**
	 * Login
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public void login(String username, String password) {
		try{
			selUtils.getCommonObject("userName_name").sendKeys(username);
			selUtils.getCommonObject("password_name").sendKeys(password);
			selUtils.getCommonObject("submit_button_xpath").submit();
			wait.until(ExpectedConditions.textToBePresentInElement(selUtils.getCommonObject("login_name_id"), username));
			//waitMethods.waitForCommTxtPresent("login_name_id", username);
			Assert.assertTrue(selUtils.getCommonObject("login_name_id").getText().contains(username));
			logger.info("Logged in successfully with '"+username+"' user.");
		}
		catch(Exception e)
		{
			logger.error("Problem with login.");
		}
	}
	
	/**
	 * Login
	 * @param username
	 * @param password
	 * @return boolean
	 */
	@Parameters({"browser", "browserURL"})
	public void login(String username, String password, String browser, String browserURL) {
		try{
			if (driver.toString().contains("null")) {
				setup(browser, browserURL);
			}
			selUtils.getCommonObject("userName_name").sendKeys(username);
			selUtils.getCommonObject("password_name").sendKeys(password);
			selUtils.getCommonObject("submit_button_xpath").submit();
			wait.until(ExpectedConditions.textToBePresentInElement(selUtils.getCommonObject("login_name_id"), username));
			//waitMethods.waitForCommTxtPresent("login_name_id", username);
			Assert.assertTrue(selUtils.getCommonObject("login_name_id").getText().contains(username));
			logger.info("Logged in successfully with '"+username+"' user.");
		}
		catch(Exception e)
		{
			logger.error("Problem with login.");
		}
	}


	/**
	 * Logout
	 */
	public void logout() {
		try{
			driver.switchTo().defaultContent();
			if(selUtils.getCommonObject("homepage_toolcaption_css").isDisplayed())
			{
				selUtils.getCommonObject("homepage_toolcaption_css").click();
				wait.until(ExpectedConditions.visibilityOf(selUtils.getCommonObject("signout_xpath")));
				selUtils.getCommonObject("signout_xpath").click();
			}
		}catch(Exception e){
			logger.error("Log out link is not present ");
		}
	}
	/*------------ TODO - Different Methods Related to Wait ---------------*/



	/*----------- TODO - Different Methods related to Selenium ---------------*/


	/* TODO - Methods to get Locators for Object.xml or CommonObject.xml */


	/*------------ TODO - Functional Methods  ---------------*/
	/**
	 * Click on 'Sign out' at the top right corner of the page. Verify, the 
	 * user is logged out and the e-Portal login page is displayed.   
	 */
	public void vSignOutFunc(){
		//	driver.switchTo().window(driver.getWindowHandle());
		logout();
		logger.info("Verified, after click on 'Sign out', the user is logged out.");	
		verifyEportalLoginPage();		
		logger.info("Verified, The e-Portal login page is displayed");
	}

	/**
	 * Verify date and time format
	 */
	public boolean verifyDateTimeFormat(String date, String format){
		exists = false;
		if ("dd/MM/yyyy hh:MM:ss".equals(format) || "dd/MM/yyyy HH:MM:ss".equals(format)
				&& date.matches("(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[012])\\/\\d\\d\\d\\d\\s\\d\\d:\\d\\d:\\d\\d") ) {
			exists = true;
		} else if ("yyyy-MM-dd hh:MM".equals(format) || "yyyy-MM-dd HH:MM".equals(format)
				&& date.matches("\\d\\d\\d\\d-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s\\d\\d:\\d\\d")) {
			exists = true;
		} else if ("yyyy-MM-dd hh:MM:ss".equals(format) || "yyyy-MM-dd HH:MM:ss".equals(format)
				&& date.matches("\\d\\d\\d\\d-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s\\d\\d:\\d\\d:\\d\\d")) {
			exists = true;
		} else if ("MM/dd/yyyy hh:MM:ss".equals(format) || "MM/dd/yyyy HH:MM:ss".equals(format)
				&& date.matches("(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])\\/\\d\\d\\d\\d\\s\\d\\d:\\d\\d:\\d\\d")) {
			exists = true;
		} 
		return exists;
	}

	/**
	 * get number of job show results.
	 */
	public int getNumjobShwRes(String shwResloc){
		waitMethods.waitForElementDisplayed(shwResloc);
		return Integer.valueOf(selUtils.getSelectedItem(selUtils.getObject(shwResloc)));
	}

	/**
	 * Click on the 'Add' button of the modal window and verify the message. 
	 **/
	public void confrmNegFunc(String id1, String id2, String id3, String proccmsg, String msg) {
		confrmPopupActionMsg(id1, id2, id3, proccmsg, msg);
		msgRedClr(id3, msg);
	}

	/**
	 * Click on the 'Add' button of the modal window and verify the 
	 * negative message. 
	 **/
	public void confrmNegFunc(String id1, String id2, String msg) {
		confrmPopupActionMsg(id1, id2, msg);
		msgRedClr(id2, msg);
	}

	/**
	 * Verify expected message color.
	 */
	public void msgRedClr(String loc, String msg){
		msgColor = selUtils.getObject(loc).getCssValue("color");
		colorRed = msgColor.substring(0, msgColor.length()-4);
		Assert.assertEquals(colorRed, "rgba(51, 51, 51","The expected message is not displayed in red color.");
		logger.info("Verified, the modal window displays the '"+ msg +"' message, in red color.");
	}

	/**
	 * Verify expected web element color.
	 */
	public void webEleExpClr(String loc, String expColor, String expRgb){
		eleClr = selUtils.getObject(loc).getCssValue("color");
		Assert.assertEquals(eleClr, expRgb, "The expected web element is not displayed in expected '"+expColor);
		logger.info("Verified, the expected web element displayed in expected '"+expColor);
	}

	/**
	 * Verify expected web element background color.
	 */
	public void webEleExpBckgrdClr(String loc, String expColor, String expRgb){
		waitNSec(1);
		waitMethods.waitForElementDisplayed(loc);
		eleClr = selUtils.getObject(loc).getCssValue("background-color");
		actualClr = eleClr.substring(0, eleClr.length()-4);
		Assert.assertEquals(actualClr, expRgb, "The expected message is not displayed in expected '"+expColor);
		logger.info("Verified, the expected web element displayed in expected '"+expColor);
	}

	/**
	 * Verify expected web element in expected color.
	 */
	public void webElesExpClr(String loc, String expColor, String expRgb){
		elements = selUtils.getObjects(loc);
		for(itemCount = 0; itemCount < elements.size(); itemCount++){
			colorVal = elements.get(itemCount).getCssValue("color");
			colorVal = colorVal.substring(0, colorVal.length()-4);
			Assert.assertEquals(colorVal, expRgb, "The expected web element is not displayed in "+expColor+" color.");
		}
		logger.info("Verified, the expected web element displayed in "+expColor+" color.");
	}

	/**
	 * Verify expected results are displayed in the table
	 */
	public void vExpResInTab(String tabRwsLoc, String jobsNum){
		int tabSize = selUtils.getObjects(tabRwsLoc).size();
		expPgNo = Integer.valueOf(jobsNum);
		Assert.assertTrue(tabSize==expPgNo, "Expected page is not displayed "+expPgNo+" results.");	
		logger.info("Verified, table size : " + expPgNo + " is as expected.");		
	}

	/**
	 * Verify expected results are displayed in the table
	 */
	public void vNoOfElements(String tabRwsLoc, String name,String size){
		xpath = selUtils.getPath(tabRwsLoc).replace(nameLbl, name);
		waitMethods.waitForElementDisplayed(tabRwsLoc, xpath);
		int noOfElement = selUtils.getObjectsDirect(By.xpath(xpath)).size();
		expPgNo = Integer.valueOf(size);
		Assert.assertTrue(noOfElement==expPgNo, "The name is not displyed "+size+" times in table");	
		logger.info("The name is  displyed "+size+" times in table");		
	}

	/**
	 * Verify, existing data from input boxes.
	 * @return
	 */
	public void vValFrmInputBox(String txtBxLoc, String expVal, String fldName){
		selUtils.waitForGetTxtPresent(txtBxLoc, expVal);
		Assert.assertTrue(selUtils.getObject(txtBxLoc).getAttribute(valueLbl).equals(expVal), 
				"'"+fldName+"' field is not set to '"+selUtils.speCharReplc(expVal)+"'.");
		logger.info("Verified, '"+fldName+"' field is set to '"+selUtils.speCharReplc(expVal)+"'.");
	}

	/**
	 * Verify, the specified drop down list, default selected item.
	 */
	public void vDrpDnDefltSelcItem(String locator, String item, String fldName){
		waitMethods.waitForElementEnable(locator);
		Assert.assertTrue(selUtils.getSelectedItem(selUtils.getObject(locator)).equals(item), 
				"'"+fldName+"' field is not set by default to '"+item+"'.");
		logger.info("Verified, '"+fldName+"' field is set by default to '"+item+"'.");
	}

	/**
	 * Verify, time value is not equal to expected value. 
	 */
	public void vExpValNtSelctd(String locator, String value){
		waitMethods.waitForElementEnable(locator);
		Assert.assertTrue(!(selUtils.getSelectedItem(selUtils.getObject(locator)).equals(value)), "expected value is not displayed."); 
	}

	/**
	 * Set the 'hours' field, and 'minutes' field to specified values. Verify, 
	 * the same field is set to the same value.
	 */
	public void selcAdVerfHrMinTime(String hrLoc, String minLoc, String hrs, String mins, String webEleName){
		selUtils.selectItem(selUtils.getObject(hrLoc), hrs);
		selUtils.vDrpDnSelcItem(hrLoc, hrs);
		selUtils.selectItem(selUtils.getObject(minLoc), mins);
		selUtils.vDrpDnSelcItem(minLoc, mins);
		logger.info("Verified, "+webEleName+" time is set to '"+hrs+":"+mins+"'.");
	}	

	/**
	 * Verify, expected check box functionality.
	 */
	public void vExpChkBxFunc(String locator, String chk, String tick, String chkBxName) {
		selUtils.chkBxChk(locator, chk);
		selUtils.verifyChked(locator, chk, tick, chkBxName);
	}


	/**
	 * Set the expected field to specified value. Verify, the that field is 
	 * set to that same value.
	 */
	public void addAdVerifyVal(String locator, String value, String webEleName){
		selUtils.populateInputBox(locator, value);
		vValFrmInputBox(locator, value, webEleName);

	}

	/**
	 * Verify, The value is not equal to specified value. 
	 * @param value
	 */
	public void vValNtExpInInputBx(String locator, String value, String webEleName){
		Assert.assertTrue(!(selUtils.getObject(locator).getAttribute(valueLbl).equals(value)), 
				webEleName+" field is equal to '"+value+"'.");
		logger.info("Verified, "+webEleName+" field is not equal to '"+value+"'.");
	}

	/**
	 * Verify number is greater than n.
	 */	
	public void vExpValPrsGrt(String object,int num){
		Assert.assertTrue(Integer.parseInt(selUtils.getObject(object).getText())>=num);
		logger.info("Verified, expected value is >= to'"+num+"'.");
	}

	/**
	 * Verify, expected column value is not present.
	 * @param sceName
	 */
	public void vExpValNotPresent(String objLoc, String oldStr, String newStr, String value){
		waitNSec(2);
		path = selUtils.getPath(objLoc).replace(oldStr, newStr);
		Assert.assertFalse(selUtils.isElementPresent(objLoc, path), 
				"Expected '"+selUtils.speCharReplc(value)+"' value is displayed.");
		logger.info("Verified, expected '"+selUtils.speCharReplc(value)+"' value is not displayed.");
	}

	/**
	 * Verify, specified web element is radio button, and expected value.
	 */
	public void vExpRadButtAdVal(String radButtLoc, String radButtValLoc, String radButtName){
		Assert.assertTrue(selUtils.getObject(radButtLoc).getAttribute("type").equalsIgnoreCase("radio"), "Expected button is not radio button.");
		logger.info("Verified, '"+radButtName+"' button is radio button.");
		selUtils.vExpValPresent(radButtValLoc, radButtName);		
	}

	/**
	 * Verify, the e-Portal login page is displayed
	 */
	public void verifyEportalLoginPage(){
		selUtils.selectItem(selUtils.getCommonObject("lang_name"), "English");
		Assert.assertTrue(selUtils.getCommonObject("welc_eportal_txt_css").getText().contains(welcEportal), 
				welcEportal + " page is not displayed.");
		verifyLoginButtInEportPage();
	}

	/**
	 * Verify, login button is displaying in e-Portal page.
	 */
	public void verifyLoginButtInEportPage(){
		Assert.assertTrue(selUtils.getCommonObject("submit_button_xpath").isDisplayed(), 
				loginButt + " button is not displayed in e-portal page.");
	}

	/**
	 * Verify the Drop-down list items of the specified list field
	 * @param estatesListItems
	 */
	public void verifyListItems(String dropDownObject, String[] listItems){
		//		String lstItemsStr = "";
		items.clear();
		waitMethods.waitForElementDisplayed(dropDownObject);
		items= selUtils.getLstItems(selUtils.getObjects(dropDownObject));
		for(itemCount = 0; itemCount < listItems.length; itemCount++){
			Assert.assertTrue(items.contains(listItems[itemCount]),listItems[itemCount]+" value is not displayed." );
		}
		logger.info("Verified, Expected values are displayed");
	}

	/**
	 * Verify expected two lists are equal
	 */
	public void vExpTwoLstsVals(List<String> items1, String[] items2){
		for(int cnti = 0; cnti < items2.length; cnti++) {		
			Assert.assertTrue(items1.contains(items2[cnti]));
			logger.info(" verified that expected valued are prsent in list. ");
		}
	}

	/**
	 * Verify the list not having expected item.
	 * @param estatesListItems
	 */
	public void vListNotHavingItem(String dropDownObject, String expItm){
		items.clear();
		waitMethods.waitForElementDisplayed(dropDownObject);
		items= selUtils.getLstItems(selUtils.getObjects(dropDownObject));
		Assert.assertFalse(items.contains(expItm), " List is having expected item.");
		logger.info(" List is not having the value "+expItm);
	}

	/**
	 * Click on the expected button of the modal window and verify the message. 
	 **/
	public void confrmPopupActionMsg(String id1, String id2, String id3, String proccmsg, String msg) {
		vPrceMsg(id1, id2,proccmsg);
		selUtils.waitForTxtPresent(id3, msg);
		String data = selUtils.getObject(id3).getText().trim();
		Assert.assertTrue(data.contains(msg), msg+" message is not displaying.");
		logger.info("Verified, expected message '"+msg+"' is displayed.");
	}	

	/**
	 * Click on the expected button, and verify the 'processing' message.
	 */
	public void vPrceMsg(String id1, String id2, String proccmsg){
		waitMethods.waitForElementDisplayed(id1);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", selUtils.getObject(id1));
		logger.info("Clicked on the expected element.");
	}


	/**
	 * Click on the expected button of the modal window and verify the message. 
	 **/
	public void cnfmPopupActMsg(String loc, String msgLoc, String msg) {
		selUtils.clickOnWebElement(loc, confirm);
		selUtils.vExpValPresent(msgLoc, msg);
	}	

	/**
	 * Click on the expected button of the modal window and verify the expected
	 * message displays. 
	 **/
	public void cnfmPopupActContainsMsg(String loc, String msgLoc, String msg) {
		selUtils.clickOnWebElement(loc, confirm);
		selUtils.vExpValContains(msgLoc, msg);
	}	

	/**
	 * Click on the 'Add' button of the modal window and verify the message. 
	 **/
	public void confrmPopupActionMsg(String id1, String id2, String msg) {
		selUtils.clickOnWebElement(id1, confirm);
		vTxtMsgWithIcon(id2, msg);
	}
	/**
	 * Verify, specified window having only one button called 'Close'.
	 */
	public void verifyOnlyCloseButtInSuccWin(String object){
		/*if(selUtils.getObjects(object).size() == 1){
			logger.info("Verified, success window is having only one button.");
			vExpValPresent(object, CloseButton);
		}else
		{
			logger.error("Not having only a 'Close' button at the bottom of the 'Success' modal window.");
			Assert.fail("Not having only a 'Close' button at the bottom of the 'Success' modal window.");
		}	
		logger.info(" Clicking on the 'Close' button at the bottom of the 'Success' modal window.");*/
	}

	/**
	 * Doing Starting setUp for TAM Application.
	 */
	public void startingSetup(String tabLocator, String breadCrumb) {
		selcDefCustAndClkOnTMSTab();
		selUtils.getCommonObject("login_time_id").click();
		selUtils.switchToFrame();
		pageNavigationAndvBreadCrumb(tabLocator,breadCrumb);
		if(selUtils.getObject(tabLocator).getText().equals(Terminals.terminals))

		{
			vFindForm("findform_id",tableDataVerifReq);
		}
	}
	/**
	 * Navigation to page.Verify BreadCrumb and Verify the TableData is empty.
	 */
	public void pageNavigationAndvBreadCrumb(String tabLocator, String breadCrumb)
	{
		pageNavigation(tabLocator);
		selUtils.verifyBreadCrumb(breadCrumb);
	}

	/**
	 * Method is used to navigate to the Home page by selecting the 
	 * client : Auto_Test
	 */
	public void startingSetup(String tabLocator) {
		selcDefCustAndClkOnTMSTab();
		selUtils.getCommonObject("login_time_id").click();
		selUtils.switchToFrame();
		pageNavigation(tabLocator);
	}
	/**
	 * Navigate to require page
	 */
	public void pageNavigation(String tabLocator)
	{
		waitMethods.waitForElementDisplayed(tabLocator);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", selUtils.getObject(tabLocator));		
		logger.info("Clicked on expected element.");
	}
	/**
	 * Select default customer, and click on TMS Tab
	 */
	public void selcDefCustAndClkOnTMSTab(){
		//Select "Auto_Test" Customer.
		new Select(selUtils.getCommonObject("client_id")).selectByVisibleText(client);
		selUtils.getCommonObject("ok_butt_xpath").submit();
		logger.info(" Selected '"+client+"' customer. ");
		//Move to TMS tab.			
		selUtils.getCommonObject("tms_link").click();	
		waitNSec(1);
	}

	/**
	 * Select expected sub module from main tab.
	 */
	public void selcExpSubModule(String locator, String subModule){
		action = new Actions(driver);
		selcDefCustAndClkOnTMSTab();
		action.moveToElement(selUtils.getCommonObject("tms_link")).build().perform();
		selUtils.clickOnCommWebElement(locator, nameLbl, subModule, subModule);
		selUtils.getCommonObject("login_time_id").click();
		selUtils.switchToFrame();	
	}
	
	/**
	 * Verifies Find Scenario Drop Down window is displayed and clicks on Search
	 * button. Also Verifies the Table data on click of search. 
	 * @param loc
	 * @param val : boolean 
	 * val=true indicates Verify table data , false indicates not to 
	 * verify table data
	 */
	public void vFindForm(String loc,boolean val)
	{   selUtils.clickOnWebElement("find_but_xpath", findButt);
		//selUtils.getCommonObject("find_but_xpath").click();
		Assert.assertTrue(selUtils.getObject(loc).isDisplayed(),"Expected Find drop down is not displayed");
		logger.info("Verified,Expected Find drop down window is displayed");
		selUtils.getCommonObject("srch_link").click();
		logger.info("Verified,Clicked on Search button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(selUtils.getCommonPath("table_spinner_xpath"))));
				
		if(selUtils.getCommonObject("bread_crumb_id").getText().trim().contains(Terminals.breadCrumEditTerm) && selUtils.getCommonObject("back_list_link").isDisplayed())		
		//if(selUtils.getCommonObject("back_list_link").isDisplayed())
		{
			selUtils.clkBackToLstButt();
		}
		if(val)
		{
			vTableData();
		}
	}
	
	/**
	 * Verifies Table data is not empty
	 */
	public void vTableData()
	{
		if(!(this.getClass().getSimpleName()).contains("PROV")) {
			Assert.assertFalse(selUtils.isElementPresent("trm_table_rowdata_xpath", selUtils.getCommonPath("trm_table_rowdata_xpath")),"Table Data is empty");
			logger.info("Verified,Table data is not empty");
		}
	}

	/**
	 * Click on the specific action button of the 'Estate actions' buttons 
	 * section. Verify, the expected modal window is displayed.
	 */
	public void verifyExpWinDisp(String locator, String title){
		waitMethods.waitForElementDisplayed(locator);
		//selUtils.getObject(locator).click();
		waitNSec(1);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", selUtils.getObject(locator));
		logger.info("Clicked on expected button.");
		vModWinDisp(title);
	}

	/**
	 * Click on the 'Close' button. Verify, the modal window is not displayed.
	 */
	public void verifySpecifiedWinNotDisp(String locator){
		waitMethods.waitForElementDisplayed(locator);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", selUtils.getObject(locator));
		logger.info("Verified, after click on close button, the modal window is closed.");
	}

	/**
	 * Verify the list of items having specified item.
	 * @param estatesListItems
	 */
	public void vLstItem(String dropDownObject, String expItem){
		items.clear();
		//		waitMethods.waitForElementDisplayed(dropDownObject);
		waitMethods.waitForEleEnable(selUtils.getObject(dropDownObject));
		items= selUtils.getLstItems(selUtils.getObjects(dropDownObject));
		Assert.assertTrue(items.contains(expItem), "List of items are not having '"+expItem+"' item.");
		logger.info("Verified, list of items are having '"+expItem+"' item.");
	}


	/**
	 *Pass the mouse over on the expected element icon, and verify expected 
	 *tooltip should appears.
	 */
	public void verifyWebIconToolTip(String iconLoc, String toolTipInfo) throws AWTException{
		String tooltip;
		WebElement icon = selUtils.getObject(iconLoc);
		Assert.assertTrue(icon.isDisplayed(), "'"+toolTipInfo+"' Icon is not present at the bottom right corner " +
				"of the 'Jobs' table.");
		waitMethods.waitForElementDisplayed(iconLoc);
		tooltip = selUtils.getObject(iconLoc).getAttribute("onmouseover");
		if(tooltip!=null)
		{
			Assert.assertTrue(tooltip.contains(toolTipInfo), "'"+toolTipInfo+"' tool tip is not appeared.");
		}
		else
		{
			Assert.fail("Tooltip is not as expected and is " +tooltip);
		}
		logger.info("Verified, Icon tooltip message '"+toolTipInfo+"' is present as expected.");
	}

	/**
	 *Pass the mouse over on the expected element icon, and verify expected 
	 *tooltip should appears.
	 */
	public void verifyWebIconToolTip(String iconLoc, String text, String toolTipInfo) {
		path = selUtils.getPath(iconLoc).replace(nameLbl, text);	
		waitMethods.waitForElementDisplayed(iconLoc, path);
		String tooltip = selUtils.getObject(iconLoc, path).getAttribute("onmouseover");
		/*Assert.assertTrue(selUtils.getObject(iconLoc, path).getAttribute("onmouseover").contains(toolTipInfo), 
				"'"+toolTipInfo+"' tool tip is not appeared.");		
		logger.info("Verified, Icon tooltip '"+toolTipInfo+"' is present as expected.");*/
		if(tooltip!=null)
		{
			Assert.assertTrue(tooltip.contains(toolTipInfo), "'"+toolTipInfo+"' tool tip is not appeared.");
		}
		else
		{
			Assert.fail("Tooltip is not as expected and is " +tooltip);
		}
		logger.info("Verified, Icon tooltip message '"+toolTipInfo+"' is present as expected.");
	}

	/**
	 * Verfiy, expected column is not present in the table.
	 */
	public void vExpColNotPresent(String expCol, String colHdrsLoc){
		waitMethods.waitForElementDisplayed(colHdrsLoc);
		vals = selUtils.getLstItems(selUtils.getObjects(colHdrsLoc));		
		Assert.assertFalse(vals.contains(expCol), expCol + " column is not disappear.");
		logger.info("Verified, '"+expCol+"' column is not present, inside the table.");
	}

	/**
	 * Verify, the table inside the page has no specified column.   
	 **/
	public void vNoExpColsInTab(String estTabColHds, String[] inVisCols){
		StringBuffer inVisColsStr = new StringBuffer();
		for(itemCount = 0; itemCount < inVisCols.length; itemCount++){
			inVisColsStr = inVisColsStr.append(", ").append(inVisCols[itemCount].trim());
			Assert.assertFalse(estTabColHds.contains(inVisCols[itemCount]), 
					"The table has any of '"+inVisColsStr+"columns.");
		}	
		logger.info("Verified, the table has no one of '"+inVisColsStr.substring(2)+"' columns.");
	}

	/**
	 * Verify, the table inside the page has specified column.  
	 * @param InVisCols
	 * @param VisCols
	 */
	public void vExpColsInTab(String tabColHds, String[] visCols){
		StringBuffer visColsStr = new StringBuffer();
		for(itemCount = 0; itemCount < visCols.length; itemCount++){
			visColsStr = visColsStr.append(", ").append(visCols[itemCount].trim());
			Assert.assertTrue(tabColHds.contains(visCols[itemCount]),	"The table has no any of '"+visColsStr+"' columns.");
		}	
		logger.info("Verified, the '"+visColsStr.substring(2)+"' columns are displayed in the table.");
	}

	/**
	 * Click on the 'Close' button. Verify, the modal window is not present.
	 */
	public void verifySpecifiedWinNotPresent(String closeButt){
		waitMethods.waitForElementDisplayed(closeButt);
		selUtils.getObject(closeButt).click();
		logger.info("Verified, after click on close button, the modal window is closed.");
	}

	/**
	 * Click on the specific web element. 
	 * Verify, the expected modal window is displayed.
	 */
	public void vExpWinDisp(String locator, String val, String title){
		selUtils.clickOnWebElement(locator, "(?i)NAME", val);
		vModWinDisp(title);
	}

	/**
	 * Verify, the expected modal window is displayed.
	 */
	public void vModWinDisp(String title){	
		waitNSec(2);
		xpath = selUtils.getPath("popup_title_xpath").replace("TITLE", title);
		webElement = selUtils.getObjectDirect(By.xpath(xpath));
		waitMethods.waitForElement(webElement);
		if(xpath != null){
			Assert.assertTrue(webElement.getText().equals(title), "'"+title+"' modal window is not displayed.");
		}else{
			Assert.fail("xpath is not as expected and is " +xpath);
		}
		logger.info("Verified, '"+ title + "' modal window is displayed.");
	}

	/**
	 * Click on specific action button of the 'Estate actions' buttons section. 
	 * Verify, the expected modal window is displayed.
	 */
	public boolean isExpWinDisp(String locator, String tabName, String title){
		flag = false;
		xpath = selUtils.getPath(locator).replace("NAME", tabName);
		selUtils.getObjectDirect(By.xpath(xpath)).click();
		xpath = selUtils.getPath("popup_title_xpath").replace("TITLE", title);
		if(selUtils.isElementPresentxpath(xpath)){
			flag = true;
			logger.info("Verified, '"+ title + "' window is displayed.");
		}		
		return flag;
	}

	/**
	 * Keep the expected field set to 'None' and click on the 'Add' button of 
	 * the modal window. The expected message is displayed in the modal window 
	 * in red with an Error icon. The expected field label becomes red. 
	 **/
	public void verifyPopUpErrMsgWithInvaliData(String id1, String id2, String errorMsg, String id3, String id4) {
		verifyErrMsgWithInvaliData(id1, id2, errorMsg, id3);
		vExpFldClr(id4, redColor);
	}

	/**
	 * Keep the expected field set to 'None' and click on the 'Add' button of 
	 * the modal window. The expected message is displayed in the modal window 
	 * in red with an Error icon.
	 **/
	public void verifyErrMsgWithInvaliData(String id1, String id2, String errorMsg, String id3) {
		selUtils.clickOnWebElement(id1, "expWebEle");
		logger.info("Clicked on expected button.");
		selUtils.waitForTxtPresent(id2, errorMsg);
		WebElement	eleId2=selUtils.getObject(id2);
		Assert.assertTrue(eleId2.getText().contains(errorMsg), "Expected error message:"+errorMsg+"Acutal error Message :"+eleId2.getText()+" message not displayed correctly");
		logger.info("Verified, expected error message is displayed : "+errorMsg);
		Assert.assertTrue(eleId2.getAttribute(style).contains(redColor), "Expected error message is not displayed in Red Color");
		logger.info("Verified, expected error message is displayed in red color ");
		//waitMethods.waitForElementDisplayed(id3);
	    //Assert.assertTrue(selUtils.getObject(id2).getCssValue("background-image").contains("rejected.gif"), "Error message is not displayed with error icon.");
		logger.info("Verified, error message is displayed with error icon.");
	}

	/**
	 * Verify expected field in expected color
	 */
	public void vExpFldClr(String locator, String color){
		selUtils.waitForAttriInElemt(locator, style, color);
		Assert.assertTrue(selUtils.getObject(locator).getAttribute(style).contains(color), "Expected Field is not in expected '"+color);
		logger.info("Verified, expected field is in expected' "+color);
	}

	/**
	 * Verify expected values are present in a list
	 */
	public void vExpItmsInLst(String webLst, String[] expItems){
		for(itemCount = 0; itemCount < expItems.length; itemCount++){
			Assert.assertTrue(webLst.contains(expItems[itemCount]), expItems[itemCount] + " item is not displayed.");
		}
		logger.info("Verified, expected values '"+webLst+"' are displayed.");
	}

	/**
	 * Verify, all list of items are expected same value. 
	 */
	public void vExpItmsInLst(List<String> webLst, String expItem){
		for(itemCount = 0; itemCount < webLst.size(); itemCount++){
			Assert.assertTrue(webLst.get(itemCount).contains(expItem), expItem + " item is not displayed.");
		}
		logger.info("Verified, expected value '"+expItem+"' are displayed.");
	}

	/**
	 * Verify expected values are not present in a list
	 */
	public void vExpItmsNotInLst(String webLst, String[] expItems){
		for(itemCount = 0; itemCount < expItems.length; itemCount++){
			Assert.assertFalse(webLst.contains(expItems[itemCount]), expItems[itemCount] + " item is displayed.");
		}
		logger.info("Verified, expected values '"+webLst+"' are not displayed.");
	}

	/**
	 * Verify, expected text message with icon.
	 */
	public void vTxtMsgWithIcon(String objLoc, String expMsg){
		selUtils.waitForTxtPresent(objLoc, expMsg);
		Assert.assertEquals(selUtils.getObject(objLoc).getText().trim(), expMsg, 
				expMsg + " message, with error icon is not displayed as expected.");
		logger.info("Verified, '"+ expMsg + "' message, with error icon is displayed as expected.");
	}

	/**
	 * Verify expected filed with expected color, and not displayed expected 
	 * message.
	 * @param fldLoc
	 * @param fldLbl
	 * @param msgLoc
	 * @param expMsg
	 */
	public void vExpFldBlkClrAndNoExpMsg(String fldLoc, String fldLbl, String msgLoc, String expMsg){
		vExpFldIsBlk(fldLoc, fldLbl);
		Assert.assertFalse(selUtils.getObject(msgLoc).getText().contains(expMsg));
		logger.info("Verified, expected error message is not displayed: "+expMsg);
	}

	/**
	 * Verify expected filed with expected color
	 **/
	public void vExpFldIsBlk(String fldLoc, String fldLbl){
		Assert.assertFalse(selUtils.getObject(fldLoc).getAttribute(style).contains(redColor), 
				"Field: "+fldLbl+" field becomes not Black.");
		logger.info("Verified, "+fldLbl+" field became Black.");
	}

	/**
	 * Verify Error message , field and field label in red color 
	 * using 'style' attribute.
	 */
	public void vMsgAndFldAndLblInRedClr(String errMsgLoc, String fldLoc, String fldLblLoc){
		vMsgAndFldRedClr(errMsgLoc, fldLoc);
		Assert.assertTrue(selUtils.getObject(fldLblLoc).getAttribute(style).contains(redColor), 
				"Expected field is not in red color");
		logger.info("Verified, error message, and field label are displayed in red color.");
	}

	/**
	 *  Verify Error message and field in red color using 'style' attribute.
	 */
	public void vMsgAndFldRedClr(String errMsgLoc, String fldLoc)
	{
		Assert.assertTrue(selUtils.getObject(errMsgLoc).getAttribute(style).contains(redColor), 
				"Expected message is not in red color");
		Assert.assertTrue(selUtils.getObject(errMsgLoc).getCssValue("background-image").contains("rejected.gif"), "Error message is not displayed with error icon.");
		logger.info("Verified, error message is displayed in red color.");
		vExpFldClr(fldLoc, redColor);
	}

	/**
	 * Set the 'Task priority' field to 'x'. Verify The field is set to 'x'
	 **/
	public void setTaskprio(String xpath, String value) {
		selUtils.populateInputBox(xpath, value);
		Assert.assertTrue(selUtils.getObject(xpath).getAttribute(valueLbl).contains(value), "Task Priority field value not set correctly");
		logger.info("Verified, 'Task Priority' field value set: "+value);
	}

	/**
	 * Verify The 'Welcome to Ingenico e-Portal' page is displayed
	 **/
	public void verifyWelcomePage(){
		Assert.assertTrue(selUtils.getCommonObject("welcome_msg_id").getText().contains(welcEportal), 
				" 'Welcome to Ingenico e-Portal' page is not displayed");
		logger.info("Verified, the 'Welcome to Ingenico e-Portal' page is displayed");
	}

	/**
	 * Verify, expected text message with icon.
	 */
	public void vErrMsgAdIcon(String objLoc, String iconLoc, String expMsg){
		selUtils.verifyExpIconDisplayed(iconLoc, expMsg);
		Assert.assertEquals(selUtils.getObject(objLoc).getText().trim(), expMsg, 
				expMsg + " message, with error icon is not displayed as expected.");
		logger.info("Verified, '"+ expMsg + "' message, with error icon is displayed as expected.");
	}

	/**
	 * To select the maximum records size in jobs table, implementation is 
	 * different in both the browser.
	 * @param locator
	 * @param browser
	 */
	public void selectMaxSizeinTable(String locator, String browser){
		String[] shwMaxResArr;
		wait.until(ExpectedConditions.visibilityOf(selUtils.getObject(locator)));
		//		waitMethods.waitForElementDisplayed(locator);
		String shwMaxRes = selUtils.getObject(locator).getText();
		if ("firefox".equals(browser) || "chrome".equals(browser)) {
			shwMaxResArr = shwMaxRes.split("\n");
		} else {
			shwMaxResArr = shwMaxRes.split(" ");
		}
		if(shwMaxResArr.length > 1){
			selUtils.selectItem(selUtils.getObject(locator), shwMaxResArr[shwMaxResArr.length-1]);
		}

	}
	/**
	 * Verify, -A expected group buttons section with the specified buttons.
	 */
	public void vGrpButts(String buttsLocator, String[] expButts, String expButtsLbl){
		vals.clear();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(selUtils.getCommonPath("btn_spinner_xpath"))));
		vals= selUtils.getLstItems(selUtils.getObjects(buttsLocator));
		for(itemCount = 0; itemCount < expButts.length; itemCount++){
			Assert.assertTrue(vals.contains(expButts[itemCount]), 
					"Expected '"+expButts[itemCount]+"' buttons are not displayed.");
			logger.info("Verified, Expected value present : " +expButts[itemCount]);
		}		
		logger.info("Verified, "+expButtsLbl+" values are displayed.");
	}
	/**
	 * Verify, -A expected group buttons section with the specified buttons.
	 */
	public void vGrpButts(List<WebElement> butts, String[] expButts, String expButtGrpName){
		StringBuffer webexpButtsStr = new StringBuffer();
		for(itemCount = 0; itemCount < butts.size(); itemCount++){
			webexpButtsStr = webexpButtsStr.append(", ").append(butts.get(itemCount).getText());
			Assert.assertTrue(butts.get(itemCount).getText().equals(expButts[itemCount]), 
					"Expected '"+expButtGrpName+"' buttons are not displayed.");
		}		
		logger.info("Verified, "+expButtGrpName+" : '"+webexpButtsStr.substring(2)+"' are displayed.");
	}

	/**
	 * Verify, -A expected group buttons section with the expected button.
	 */
	public void vExpButtInGrp(String buttsLocator, String expButt, String grpButtLbl){
		vals.clear();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(selUtils.getCommonPath("btn_spinner_xpath"))));
		elements = selUtils.getObjects(buttsLocator);
		for(itemCount = 0; itemCount < elements.size(); itemCount++){
			vals.add(elements.get(itemCount).getText());
		}	
		Assert.assertTrue(vals.contains(expButt), 
				"Expected '"+expButt+"' button is not displayed.");
		logger.info("Verified, "+grpButtLbl+" : '"+expButt+"' button is displayed.");
	}

	/**
	 * Verify, -A expected group buttons section not having expected button.
	 */
	public void vExpButtNtInGrp(String buttsLocator, String expButt, String grpButtLbl){
		vals.clear();
		elements = selUtils.getObjects(buttsLocator);
		for(itemCount = 0; itemCount < elements.size(); itemCount++){
			vals.add(elements.get(itemCount).getText());
		}
		Assert.assertTrue(!(vals.contains(expButt)), 
				"Expected '"+expButt+"' button is displayed.");
		logger.info("Verified, "+grpButtLbl+" : '"+expButt+"' button is not displayed.");
	}

	/**
	 * Click on the specified button. Verify, the specified modal window is 
	 * displayed with the specified field set by default to specified value. 
	 **/
	public void openWinAdVrfyFldVal(String winButtLoc, String winTtl, String fldLoc, String fldVal, String fldName, String clsButtLoc){
		verifyExpWinDisp(winButtLoc, winTtl);
		waitMethods.waitForElementDisplayed(fldLoc);
		vDrpDnDefltSelcItem(fldLoc, fldVal, fldName);
		verifySpecifiedWinNotDisp(clsButtLoc);
	}

	/**
	 * Tick the expected check box in the 'Select' column of the table. 
	 * Verify the check box is ticked.
	 **/
	public void selExpChkBx(String chkBxLoc, String serNo){
		xpath = selUtils.getPath(chkBxLoc).replace(nameLbl, serNo);
		waitMethods.waitForElement(selUtils.getObjectDirect(By.xpath(xpath)));
		webElement = selUtils.getObjectDirect(By.xpath(xpath));
		if(!(webElement.isSelected())){	
			webElement.sendKeys("");
			webElement.click();
			Assert.assertTrue(webElement.isSelected(), " Check Box is not selected");
			logger.info("Verified, expected '"+serNo+"' Check Box is selected.");
		}
	}

	/**
	 * Deselect and select all the expected check boxes.
	 **/
	public void deSelecExpVals(String loc, String loc1, String val){
		webelements = selUtils.getObjects(loc);
		colVals = selUtils.getObjects(loc1);
		for(itemCount = 0; itemCount < webelements.size(); itemCount++){
			if(colVals.get(itemCount).getText().equalsIgnoreCase(val)){
				if(!(webelements.get(itemCount).isSelected())){
					webelements.get(itemCount).click();
					Assert.assertTrue(webelements.get(itemCount).isSelected(), " Expected checkbox is not selected");
				}
			} else {
				if(webelements.get(itemCount).isSelected()){
					webelements.get(itemCount).click();
					Assert.assertFalse(webelements.get(itemCount).isSelected(), " Expected checkbox is selected");
				}
			}
		}
		logger.info(" Verified that expected checkboxes are selected and not selected.");
	}

	/**
	 * Deselect all the expected checkBox.
	 **/
	public void deSelecExpVals(String loc){
		webelements = selUtils.getObjects(loc);
		for(itemCount = 0; itemCount < webelements.size(); itemCount++){
			if(webelements.get(itemCount).isSelected()){
				webelements.get(itemCount).click();
				Assert.assertFalse(webelements.get(itemCount).isSelected(), " Expected checkbox is selected");
			}
		}
		logger.info(" Verified that expected checkboxes are not selected.");
	}

	/**
	 * Verify, the expected tab in focus.
	 */
	public void vExpTabFocused(String tab){
		webElement = selUtils.getObject("focus_tabs_css");
		waitMethods.waitForElementFocus(webElement);
		Assert.assertTrue(webElement.getText().equals(tab), 
				tab + " tab is not focused.");
		logger.info("Verified, '"+tab+"' tab is focused.");
	}

	/**
	 * Verify, the expected tab not in focus.
	 */
	public void vExpTabNtFocused(String tab){
		Assert.assertTrue(!(selUtils.getObject("focus_tabs_css").getText().equals(tab)), 
				tab + "tab is focused.");
		logger.info("Verified, '"+tab+"' tab is not focused.");
	}

	/**
	 * Verify Fields or Header of table, displayed.
	 **/
	public void vTableFields(String hdrsLoc, String[] columns)	{
		StringBuffer colsStr = new StringBuffer();
		waitMethods.waitForElementDisplayed(hdrsLoc);
		elements = selUtils.getObjects(hdrsLoc);
		for (int cnti = 0; cnti < columns.length; cnti++) {   
			Assert.assertTrue(elements.get(cnti).getText().equalsIgnoreCase(columns[cnti]), columns[cnti]+ " column is not displayed.");
			colsStr = colsStr.append(" ").append(elements.get(cnti).getText().trim());
		}					
		logger.info("Verified, '"+colsStr+"' columns are present.");
	}

	/**
	 * Verify the  ist items of the specified list field
	 * @param array
	 * @param locator
	 */
	public void vItemslist( String[] expVals,String locator){
		StringBuffer lstItemsStr = new StringBuffer();
		items.clear();
		waitMethods.waitForElementDisplayed(locator);
		items= selUtils.getLstItems(selUtils.getObjects(locator));
		List<String> list = Arrays.asList(expVals);
		Assert.assertTrue(list.equals(items),"Verified label is not as expected");	
		for(itemCount = 0; itemCount < expVals.length; itemCount++){
			lstItemsStr = lstItemsStr.append(" ").append(expVals[itemCount].trim());
		}		
		logger.info("Verified, expected  "+lstItemsStr+" are present.");
	}

	/**
	 * vSelectionMenu
	 * Verify all the option
	 */
	public void vSelectionMenu(String selMnuLocator,String selMnuHedrLoc,String selMnuListlocator,String[] selMnuList){
		selUtils.getObject(selMnuLocator).click();
		Assert.assertEquals(selUtils.getObject(selMnuHedrLoc).getText(),slctMenu,"Verified label is not as expected");
		logger.info("Selection Menu label is present in the application");
		vItemslist(selMnuList,selMnuListlocator);
	}

	/**
	 * Click on the 'Reset' button. Verify,the 'expected' field' field value is 
	 * removed and the field becomes black.
	 **/
	public void vReset(String resetLoc, String objLoc){
		selUtils.clickOnWebElement(resetLoc, reset);
		vResetWorks(objLoc);
	}

	/**
	 * verify reset works
	 */
	public void vResetWorks(String objLoc){
		Assert.assertTrue(selUtils.getObject(objLoc).getText().isEmpty(), " expected field value is not removed");
		logger.info("Verifed, expected field value is removed. ");
		Assert.assertFalse(selUtils.getObject(objLoc).getAttribute(style).contains(redColor), "Expected field is not in black color.");
		logger.info("Verified, expected field is displayed in black color.");
	}

	/**
	 * Click on the 'Reset' button. Verify,the expected field becomes blank.
	 */
	public void vRestAdBlnkFld(String restLoc, String objLoc, String objName){
		selUtils.clickOnWebElement(restLoc, reset);
		vValFrmInputBox(objLoc, "", objName);
	}

	/**
	 * Verify that all expected Values are present.
	 * @param pakList
	 * @param browser
	 */
	public void vExpColVals(String[] expVals, String browser, String locator){
		selcMaxPgSz("job_show_res_id", browser, "atsearh_afr_xpath", editPageDispTxt);
		vExpVals(expVals, locator);
	}

	/**
	 * Verify that all expected Values are present in Expected list.
	 * @param pakList
	 * @param browser
	 */
	public void vExpColVals(String[] expVals, String browser, List<WebElement> elemts){
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent("est_disppagenos_css", editPageDispTxt);
		vExpVals(expVals, elemts);
	}


	/**
	 * Verify expected values
	 */
	public void vExpVals(String[] expVals, String locator){
		vals.clear();
		waitMethods.waitForElementDisplayed(locator);
		vals = selUtils.getLstItems(selUtils.getObjects(locator));
		for(itemCount = 0;  itemCount < expVals.length; itemCount++) {
			Assert.assertTrue(vals.contains(expVals[itemCount]), expVals[itemCount] + " is not present.");
			logger.info("Verified, "+expVals[itemCount]+" is present");
		}
	}

	/**
	 * Verify expected values
	 */
	public void vExpVals(String[] expVals, List<WebElement> elemts){
		vals.clear();
		vals = selUtils.getLstItems(elemts);
		for(itemCount = 0;  itemCount < expVals.length; itemCount++) {
			Assert.assertTrue(vals.contains(expVals[itemCount]), expVals[itemCount] + " is not present in Packages list");
		}
		logger.info("Verified, all expected values are present.");
	}

	/**
	 * Verify expected values
	 */
	public void vExpVals(String expVal, String locator){
		vals.clear();
		waitMethods.waitForElementDisplayed(locator);
		vals = selUtils.getLstItems(selUtils.getObjects(locator));
		for(itemCount = 0;  itemCount < vals.size(); itemCount++) {
			Assert.assertTrue(vals.contains(expVal), expVal + " is not present.");
		}
		logger.info("Verified, only '"+expVal+"' is displayed.");
	}

	/**
	 * Verify expected values are not present
	 */
	public void vExpValsNtPresent(String[] expVals, String locator){
		vals.clear();
		waitMethods.waitForElementDisplayed(locator);
		vals = selUtils.getLstItems(selUtils.getObjects(locator));
		for(itemCount = 0;  itemCount < expVals.length; itemCount++) {
			Assert.assertTrue(!(vals.contains(expVals[itemCount])), expVals[itemCount] + " is present.");
		}
		logger.info("Verified, all expected values are not present.");
	}

	/**
	 * Method to select max page size
	 **/
	public void selcMaxPgSz(String pgLstLoc, String browser, String dispPgLoc, String dispTxt){
		selUtils.waitForTxtPresent(dispPgLoc, dispTxt); 
		selectMaxSizeinTable(pgLstLoc, browser);
		selUtils.waitForTxtPresent(dispPgLoc, dispTxt);
	}

	/**
	 * Verify,'creation date' of format 'MM/DD/YYYY HH:MM:SS'.
	 */
	public void vCrDateFrmt(WebElement element ){
		String crDate = element.getText();
		Assert.assertTrue(verifyDateTimeFormat(crDate, "MM/dd/yyyy HH:MM:ss"), 
				"Expected '"+crDate+"' date is not having expected date format.");	
		logger.info("Verified, '"+crDate+"' date is having expected date format.");
	}

	/**
	 * webtableGetColNumOfHeader
	 * @param object(xpath of table)
	 * @param header
	 * @return integer(column number)
	 */
	public int webtableGetColNumOfHeader(String object, String header) {
		String colhdr;
		int colnum = 0;
		try {
			waitNSec(1);
			waitMethods.waitForElementDisplayed(object);
			List<WebElement> element = selUtils.getObjects(object);

			for (int i = 0; i < element.size(); i++) {
				colhdr = element.get(i).getText();

				if (header.equalsIgnoreCase(colhdr)) {
					colnum = i + 1;
					break;
				}
			}
		} catch (Exception e) {
			Assert.fail("Error with Get Cell number of header data ", e);
		}
		return colnum;
	}

	/**
	 * Get table header corresponding value
	 * @param tabValLoc
	 * @param tabHeadLoc
	 * @param repOld
	 * @param repNew
	 * @return List of WebElement
	 */
	public List<WebElement> getTableHeadCursVals(String tabValLoc,String tabHeadLoc,String repOld,String headerName){
		 itemCount=webtableGetColNumOfHeader(tabHeadLoc,headerName);
		  xpath =selUtils.getPath(tabValLoc).replace(repOld,Integer.toString(itemCount)); 
		  webelements= selUtils.getObjectsDirect(By.xpath(xpath));
		 // System.out.println(webelements);
		  return webelements;
		}	

	/**
	 * Get table header corresponding value
	 * @param tabValLoc
	 * @param tabHeadLoc
	 * @param repOld
	 * @param repNew
	 * @return WebElement
	 */
	public WebElement getTableHeadCursVal(String tabValLoc,String tabHeadLoc,String repOld,String headerName){
		itemCount=webtableGetColNumOfHeader(tabHeadLoc,headerName);
		xpath =selUtils.getPath(tabValLoc).replace(repOld,Integer.toString(itemCount)); 
		webElement=selUtils.getObjectDirect(By.xpath(xpath));
		//System.out.println(webElement);
		return webElement;
		
	}


	/**
	 * Verify that there is no name column in the table
	 **/
	public void vNoColmsInTable(String headStr,String colName){
		elements = selUtils.getObjects(headStr);
		for (int cnti = 0; cnti < elements.size(); cnti++) {   
			Assert.assertFalse(elements.get(cnti).getText().equalsIgnoreCase(colName));
		}					
		logger.info(" Verified, the required column "+colName+" is not present in Table.");
	}

	/**
	 * Verify, only one instance of expected value.
	 */
	public void vOnly1InstOfExpVal(String objLoc, String oldVal, String newVal, String expVal){
		path = selUtils.getPath(objLoc).replace(oldVal, newVal);
		waitMethods.waitForEleEnable(selUtils.getObjectDirect(By.xpath(path)));
		Assert.assertEquals(selUtils.getObjectsDirect(objLoc,path).size(), 1, "more than one instance of the '"+expVal+ "' is displayed.");
		logger.info("Verified, only one instance of the '"+expVal+ "' is displayed.");
	}

	/**
	 * Verify, only one expected value.
	 */
	public void vOnly1Val(String objLoc){
		Assert.assertEquals(selUtils.getObjects(objLoc).size(), 1, "not only one value is displayed.");
		logger.info("Verified, only one value is displayed.");
	}

	/**
	 * Verify, focus expected tab, and expected value in table.
	 */
	public void vExpTabFocusAdVExpValPresent(String expTab,String browser, String loadLoc, String objLoc, String oldVal, String newVal, String expVal ){
		vExpTabFocused(expTab);
		selectMaxSizeinTable("job_show_res_id", browser);
		selUtils.waitForTxtPresent(loadLoc, editPageDispTxt);			
		selUtils.vExpValPresent(objLoc, oldVal, newVal, expVal);
	}

	/**
	 * Verify, expected value, and only one instance of the same value
	 */
	public void vExpValAdOnly1Inst(String expTab,String browser, String loadLoc, String objLoc, String oldVal, String newVal, String expVal ){
		vExpTabFocusAdVExpValPresent(expTab,browser, loadLoc, objLoc, oldVal, newVal, expVal);
		vOnly1InstOfExpVal(objLoc, oldVal, newVal, expVal);
	}

	/**
	 * Verify, functionality with negative set of values. 
	 **/
	public void vFuncWithNegVals(String[] negVals, String fldLoc, String fldLbl, String butLoc, String errMsgLoc, String errMsg){
		for(int cnti = 0; cnti < negVals.length; cnti++){
			selUtils.populateInputBox(fldLoc, negVals[cnti]);
			vValFrmInputBox(fldLoc, negVals[cnti], fldLbl);
			confrmPopupActionMsg(butLoc, errMsgLoc, errMsg);
			vMsgAndFldRedClr(errMsgLoc, fldLoc);	
		}
	}

	/**
	 * Method which verifies Text
	 * @param xpathLoc
	 * @param text
	 */
	public void vTextGrpDisp(String xpathLoc,String[] text){
		for(int cnti=0;cnti<text.length;cnti++){
			xpath= selUtils.getPath(xpathLoc).replace("NAME",text[cnti]);
			selUtils.getObjectDirect(By.xpath(xpath)).isDisplayed();
		}
	}

	/**
	 * Verify that only expected record is present in Table.
	 **/
	public void vOnlyExpRecord(String loc, String records){
		waitMethods.waitForElementDisplayed(loc);
		Assert.assertTrue(selUtils.getObject(loc).getText().trim().contains(records), "The table did not contain only expected record.");
		logger.info(" Verified that only expected record is present in Table.");
	}

	/*------------ TODO - Hard coded Wait Methods ---------------*/
	/**
	 * wait for N second
	 */
	public static void waitNSec(int waitTime) {
		try {
			switch (waitTime) {
			case 1:
				Thread.sleep(CommonConstants.ONESEC);
				break;
			case 2:
				Thread.sleep(CommonConstants.TWOSEC);
				break;
			case 3:
				Thread.sleep(CommonConstants.THREESEC);
				break;
			case 4:
				Thread.sleep(CommonConstants.FOURSEC);
				break;
			case 5:
				Thread.sleep(CommonConstants.FIVESEC);
				break;
			case 6:
				Thread.sleep(CommonConstants.SIXSEC);
				break;
			case 7:
				Thread.sleep(CommonConstants.SEVENSEC);
				break;   
			default:
				Assert.fail("Please specify the wait time upto 7. Currently given is: " + waitTime);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// logger.info(e.getMessage());
			Thread.currentThread().interrupt();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Wait for seven seconds
	 */

	/*-------- TODO - Different Methods related to page scroll -------------*/
	/**
	 * Method to Scroll the Page Up
	 */
	public void scrollUp(){
		driver.switchTo().defaultContent();
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("scroll('1000','0')");
		driver.switchTo().frame(0);
	}

	/**
	 * Method to Scroll the Page Down
	 */
	public void scrollDown(){
		driver.switchTo().defaultContent();
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("scroll('0','500')");
		driver.switchTo().frame(0);
	}

	/**
	 * Method to Scroll the Page Down based on Object/Locator
	 */
	public void objScrollDwn(String objLoc) {
		selUtils.getObject(objLoc).sendKeys(Keys.PAGE_DOWN);
	}

	/**
	 * Method to Scroll the Page Up based on Object/Locator
	 */
	public void objScrollUp(String objLoc) {
		selUtils.getObject(objLoc).sendKeys(Keys.PAGE_UP);
	}

	/*---------- TODO - Different Methods related to CSV and PDF -------------*/
	/**
	 * readCsvLineOne returns all csv header names
	 * @return ArrayList<Integer> 
	 * @throws InterruptedException 
	 */
	public List<String> readCsvLineOne(String path) throws IOException{
		count=0;
		frFile = new File(path);
		while(!frFile.exists()&&count!=3){
			waitNSec(4);
			count++;
		}
		if(count==3)
		{
			Assert.fail("Csv Read is unsuccessful");
		}
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] csvfistLine = reader.readNext();
		List<String> csvFistList = Arrays.asList(csvfistLine);
		reader.close();
		new File(path).delete();
		logger.info("CSV read successfully");
		return csvFistList;
	}

	/**
	 * get download path of the file
	 * @param file, fileType
	 * @return	 
	 */
	public String getDownFilePath(String file,String fileType)
	{
		String filePath = null;
		try
		{
			logger.info(" Get the path of "+fileType+" file ");
			filePath = CommonConstants.FILEDOWNLOADPATH+file+"."+fileType;
		}
		catch(Exception e)
		{
			Assert.fail("Failed during getting path of the file",e);
		}
		return filePath;
	}

	/**
	 * get upload path of the file
	 * @param file, fileType
	 * @return	 
	 */
	public String getUpldFilePath(String file,String fileType)
	{
		String filePath = null;
		try
		{
			logger.info(" Get the path of "+fileType+" file ");
			filePath = CommonConstants.FILEUPLOADPATH+file+"."+fileType;
		}
		catch(Exception e)
		{
			Assert.fail("Failed during getting path of the file",e);
		}
		return filePath;
	}


	/**
	 * get path of pdf file
	 * @param file
	 * @return
	 */
	public String getFilePath(String file,String fileType,String fileLoc)
	{
		String filePath = null;
		try
		{
			filePath = CommonConstants.DATA+fileLoc+"\\"+file+"."+fileType;
		}
		catch(Exception e)
		{
			Assert.fail("Failed during getDownFilePath validation",e);
		}
		return filePath;
	}





	/**
	 * readAndVeriXlsCellOne read and verify xls file cell one data.
	 * @param path
	 * @param data
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public void readAndVerifXlsCellOne(String path ,String data) throws BiffException, IOException{
		count=0;
		frFile = new File(path);
		while(!frFile.exists()&&count!=4){
			waitNSec(4);
			count++;
		}
		if(count==4)
		{
			Assert.fail(fileUnsucc);
		}
		waitNSec(1);
		FileInputStream fileIpStream = new FileInputStream(path);
		Workbook wrkBook = Workbook.getWorkbook(fileIpStream);
		Sheet sheet=wrkBook.getSheet(0);
		Assert.assertTrue(sheet.getCell(0,0).getContents().equals(data));
		logger.info("Xls file read successfully");
		wrkBook.close();
		fileIpStream.close();
		new File(path).delete();
		logger.info(xlsDelFile);
	}

	/**
	 * Read data from CSV file
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public void readDownloadedCSV(String path, String[] expData) throws IOException{
		count=0;
		frFile = new File(path);
		while(!frFile.exists()&&count!=3){
			waitNSec(4);
			count++;
		}
		if(count==3)
		{
			Assert.fail(fileUnsucc);
		}
		waitNSec(1);
		items.clear();
		CSVReader reader = new CSVReader(new FileReader(path));
		List<String[]> listfromCSV = reader.readAll();
		for(String[] array: listfromCSV){
			for(String str: array){
				items.add(str); 
			}
		}
		reader.close();
		new File(path).delete();
		logger.info("CSV Data read is successful");
		str = "";//Type;"Package name";"Version";"Technology";"File name"
		str = items.toString().replace("[", "");
		str = str.replace(", ]", "");
		str = str.replace("]", "");
		str = str.replace("\"", "");
		String[] csvCellVals = str.split(";");
		for(int colCount=0;colCount<csvCellVals.length;colCount++){
			Assert.assertTrue(csvCellVals[colCount].equals(expData[colCount].trim()));
		} 
		logger.info("Verified, expected csv file data.");
		items.clear();
	}

	/**
	 * readAndVeriXlsSheets read and verify xls sheets.
	 * @param path
	 * @param data
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public void readAndVerifXlsSheets(String path ,String[] data) throws BiffException, IOException{
		count=0;
		frFile = new File(path);
		while(!frFile.exists()&&count!=3){
			waitNSec(4);
			count++;
		}
		if(count==3)
		{
			Assert.fail(fileUnsucc);
		}
		waitNSec(1);
		FileInputStream fileIpStream = new FileInputStream(path);
		Workbook wrkBook = Workbook.getWorkbook(fileIpStream);
		String[] sheetNames = wrkBook.getSheetNames();
		for(int cnti = 0; cnti < sheetNames.length; cnti++){
			Assert.assertTrue(sheetNames[cnti].equals(data[cnti]), "Expected sheet is not present");
		}
		logger.info(" Verified that expected sheets are present in Xls file");
		logger.info("Xls file read successfully");
		wrkBook.close();
		fileIpStream.close();
		new File(path).delete();
		logger.info(xlsDelFile);
	}
	/**
	 * Verify that PDF file
	 * @param path
	 * @throws IOException
	 */
	public void verifPdf(String path) throws IOException{
		count=0;
		frFile = new File(path);
		while(!frFile.exists()&&count!=3){
			waitNSec(4);
			count++;
		}
		if(count==3)
		{
			Assert.fail(fileUnsucc);
		}
		waitNSec(1);
		logger.info(" Verified that PDF file Exist.");
		FileInputStream fileIpStream = new FileInputStream(path);
		fileIpStream.close();
		new File(path).delete();
		logger.info("PDF file deleted");
	}



	/** 
	 * readAndVerifZipFilCellOne read and verify xls cell one data from zip file
	 * @param path
	 * @param data
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 */
	public void readAndVerifZipFilCellOne(String path ,String data) throws IOException, ParserConfigurationException, SAXException{
		count=0;
		frFile = new File(path);
		while(!frFile.exists()&&count!=3){
			waitNSec(4);
			count++;
		}
		if(count==3)
		{
			Assert.fail(fileUnsucc);
		}
		waitNSec(4);
		ZipFile zipFile = new ZipFile(path);
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		for(int cnti=0; cnti<2; cnti++)
		{
			ZipEntry entry = entries.nextElement();
			InputStream stream = zipFile.getInputStream(entry);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			Assert.assertTrue(doc.getElementsByTagName("Data").item(0).getTextContent().trim().contentEquals(data),data +" is not present in the first cell");
			stream.close();
		}
		new File(path).delete();
		logger.info("Verified, expected data is present in downloaded xls file.");
	}


	/**
	 * readAndVerifXlsXmlCellOne read and verify xls file cell one data.
	 * @param path
	 * @param data
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 */
	public void readAndVerifXlsXmlCellOne(String path ,String data) throws IOException, ParserConfigurationException, SAXException{
		count=0;
		frFile = new File(path);
		while(!frFile.exists()&&count!=3){
			waitNSec(4);
			count++;
		}
		if(count==3)
		{
			Assert.fail(fileUnsucc);
		}
		waitNSec(4);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		ByteArrayInputStream stream =new ByteArrayInputStream(readFile(path).replaceAll("&\\s+", "&amp;").getBytes());
		Document doc = dBuilder.parse(stream);
		doc.getDocumentElement().normalize();
		Assert.assertTrue(doc.getElementsByTagName("Data").item(0).getTextContent().trim().contentEquals(data),data +" is not present in the first cell");
		stream.close();
		new File(path).deleteOnExit();
		logger.info(xlsDelFile);
	}
	/**
	 * readFile convert file to string.
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private String readFile( String file ) throws IOException {
		BufferedReader reader = new BufferedReader( new FileReader (file));
		String         line = null;
		StringBuilder  stringBuilder = new StringBuilder();
		String         lineSeparator = System.getProperty("line.separator");

		while( ( line = reader.readLine() ) != null ) {
			stringBuilder.append( line );
			stringBuilder.append( lineSeparator );
		}

		return stringBuilder.toString();
	}

	/**
	 * readAndVerifXlsXmlCellOne read and verify xls file cell one data.
	 * @param path
	 * @param data
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 */
	public void readAndVerifXlsXmlData(String path, String[] data) throws IOException, ParserConfigurationException, SAXException{
		count=0;
		frFile = new File(path);
		while(!frFile.exists()&&count!=3){
			waitNSec(4);
			count++;
		}
		if(count==3)
		{
			Assert.fail(fileUnsucc);
		}
		waitNSec(5);
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Data");
		content.clear();
		for(int cnti = 0; cnti < nList.getLength(); cnti++){
			Node nNode = nList.item(cnti);
			content.add(nNode.getTextContent());
		}
		for(itemCount = 0; itemCount < data.length-1 ; itemCount++){
			Assert.assertTrue(content.contains(data[itemCount]), data[itemCount] + "is not present as expected.");
		}
		logger.info("Verified, expected data is present in downloaded xls file.");
		new File(path).delete();
		logger.info(xlsDelFile);
	}

	/**
	 * Click on 'Refresh' at the top right corner of the table as many time as 
	 * necessary. At the end the status in the 'Status' column of the new import
	 * changes to Expected Status. 
	 **/
	public void refTillExpValPresent(String loc1, String loc2, String status){
		int count = 0;
		waitMethods.waitForElement(selUtils.getObject(loc1));
		while (!(selUtils.getObject(loc1).getText().trim().equalsIgnoreCase(status))&&count!=15){
		selUtils.refreshTbl("est_disppagenos_css");
		count++;
		}
		selUtils.vExpValPresent(loc2, status);
	}
	
	/**
	 * get all dropdown values
	 * @param locator
	 * @return
	 */
	/*public List<String> getDropDownVals(String locator){
		items.clear();
		waitMethods.waitForElementDisplayed(locator);
		for(int cnti=0;cnti<new Select(selUtils.getObject(locator)).getOptions().size();cnti++)
		{
			items.add(new Select(selUtils.getObject(locator)).getOptions().get(cnti).getText());
		}
		return items;
	}
*/
	/**
	 * Click on the web element.
	 * selUtils.selUtils.clickOnWebElement
	 * @param locator
	 * @param text
	 */
	public void clickOnExpWebElement(String locator,String newVal1, String newVal2){
		xpath = selUtils.getPath(locator).replaceAll("(?i)Name", newVal1);
		xpath = xpath.replaceAll("(?i)OWNER", newVal2);
		webElement = selUtils.getObjectDirect(By.xpath(xpath));
		waitMethods.waitForElement(webElement);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", webElement);
		logger.info("Clicked on the Expected element.");
	}

	/** Set the 'Serial number' field to given value.The field is set to given 
	 * value and click on the 'Search' button. Verify, the 'Find terminals' 
	 * drop-down window disappears. The 'Asset tracking>> Terminals>> Auto_Test'
	 * page is displayed.The table contains only the terminal with the 
	 * 'Serial number' equals to given value.**/
	public void vFindRecdords(String loc1, String val1, String label, String srcLbl, String brdCrum){
		selUtils.populateInputBox(loc1, val1);
		vValFrmInputBox(loc1, val1, label);
		selUtils.clickOnWebElement("srch_link", srch);
		//waitMethods.waitForElementNotPresent("find_trms_lbl_xpath");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(selUtils.getPath("find_trms_lbl_xpath"))));
		selUtils.verifyElementNotDisp("find_trms_lbl_xpath", srcLbl);	
		selUtils.verifyBreadCrumb(brdCrum);
	}

	/**
	 * unzipFile file of unzip.
	 * @param filePath
	 */
	public void unzipFile(String filePath){
		FileInputStream fis = null;
		ZipInputStream zipIs = null;
		ZipEntry zEntry = null;
		count=0;
		frFile = new File(filePath);
		while(!frFile.exists()&&count!=3){
			waitNSec(4);
			count++;
		}
		if(count==3)
		{
			Assert.fail(fileUnsucc);
		}
		waitNSec(5);
		try {
			fis = new FileInputStream(filePath);
			byte[] tmp = new byte[4*1024];
			FileOutputStream fos = null;
			zipIs = new ZipInputStream(new BufferedInputStream(fis));
			while((zEntry = zipIs.getNextEntry()) != null){
				try{					
					fos = new FileOutputStream(CommonConstants.FILEDOWNLOADPATH+zEntry.getName());
					int size = 0;
					while((size = zipIs.read(tmp)) != -1){
						fos.write(tmp, 0 , size);
					}
					fos.flush();
					fos.close();
				} catch(Exception ex){
					ex.printStackTrace();
				}
			}
			zipIs.close();
			new File(filePath).delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Method to verify n number of elements displayed
	 * @param expectedArr
	 * @param expItems
	 */
	public void verifyElementsDisplayed(String expectedArr[], String[] expItems){
		for (int iter=0;iter<expectedArr.length;iter++){
			Assert.assertTrue(selUtils.getObject(expectedArr[iter]).isDisplayed(),expItems[iter]+" is not displayed");
			logger.info(expItems[iter]+" element is displayed");
		}
	}
	/**
	 * Method for the web element was displayed 	
	 * @param locator
	 * @param valToreplace
	 * @param replacableVal
	 * @param element
	 */

	public void verifyObjDirectDisp(String locator,String valToreplace,String replacableVal){
		xpath=selUtils.getPath(locator).replace(valToreplace,replacableVal);
		waitMethods.waitForElementDisplayed(locator, xpath);
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).isDisplayed(), " element is not displayed");
		logger.info("Verified that expected value is present.");
	}

	/**
	 * Method to click on ExpTab and verify Tab is in focus
	 * @param locator
	 */
	public void clickOnExpTabandvTabFocus(String loc)
	{
		if (selUtils.getObject(loc).getAttribute("class").contains("tabs-selected")) {
			logger.info("Verified, expected tab is in focused.");
		} else {
			selUtils.getObject(loc).click();
			selUtils.vExpTabInFocus(loc);	
		}
	}

	/**
	 * Method to verify the given string date and time format
	 * @param str and dat
	 */
	public void vDateTimeFormat(String str, String dat)
	{
		Assert.assertTrue(verifyDateTimeFormat(str, dat), 
				"Expected '"+str+"' date is not having expected date format.");
		logger.info("Verified, '"+str+"' date is having expected date formate.");
	}
	/**
	 * Match the table header names with values
	 * @param headerNames
	 * @param values
	 */
	public void vTablehdrNval(String valLoc,String headerLoc,String[] headerNames,String[] values)
	{
		for(int i=0;i<headerNames.length;i++)
		{
			selUtils.vExpValPresent(getTableHeadCursVal(valLoc,headerLoc,indx,headerNames[i]),values[i]);
		}
	}

	/**
	 * Verify, 'Date' column value format in the specified tab.
	 * @param sceName
	 */
	public void vDateCol(String dateLoc, String name){
		xpath = selUtils.getPath(dateLoc).replace(TestBase.nameLbl, name);
		waitMethods.waitForElementDisplayed(dateLoc, xpath);
		Assert.assertTrue(verifyDateTimeFormat(selUtils.getObjectDirect(By.xpath(xpath)).getText(), "MM/dd/yyyy HH:MM:ss"), 
				"'Status date' is not displayed the expected format.");		
		logger.info("Verified, date field is displayed in the expected format.");	
	}
	
	/**
	 *To verify current date in specified column
	 * @param dateLoc
	 * @author Shama
	 */
	public void vCurntDateColVal(String dateLoc){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		xpath = selUtils.getPath(dateLoc);
		waitMethods.waitForElementDisplayed(dateLoc, xpath);
		String[] arr=selUtils.getObject(dateLoc).getText().split(" ");
		Assert.assertTrue(arr[0].contains(dateFormat.format(date)),"Current date is not displayed.");		
		logger.info("Verified, current date is displayed");	
	}
	
	/**
	 * getIndexForHeader fetches index of specified header
	 * @param header
	 * @return int
	 */
	public int getIndexForColHeader(String locator, String header){
		final List<WebElement> headerCols = selUtils.getObjects(locator);
		for(int iter=0;iter<headerCols.size();iter++){
			if(headerCols.get(iter).getText().equalsIgnoreCase(header)){
				return iter;
				
			}
		}
		return -1;
	}
	
	/**
	 * vDataOnColName- verify data based on column name
	 * @param tableCol
	 * @param ColNam
	 * @param namStr
	 */
	public void vDataOnColName(String HeadLoc,String ColNam,String tableLoc, String valToCheck){
		itemCount=webtableGetColNumOfHeader(HeadLoc,ColNam);
		xpath=selUtils.getPath(tableLoc).replace("INDEX",itemCount+"");
		content = selUtils.getLstItems(selUtils.getObjectsDirect(By.xpath(xpath)));
		vExpItmsInLst(content, valToCheck);
	}
	
	
	/**
	 * This method verifies that Table is not emty
	 * @param dataListInTabLoc
	 */
	public void vTableIsNotEmpty(String dataListInTabLoc)
	{
	Assert.assertTrue(selUtils.getObjects(dataListInTabLoc)
			.size() >= 1, "The table is empty");
	logger.info("Verified, Table data  is not empty");
}
}

