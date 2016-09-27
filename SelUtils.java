package com.ingenico.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ingenico.base.TestBase.Locators;
import com.ingenico.base.logger.Logger;


/**
 * SetUtils class contains common methods related to object, common object, element and web element.
 * @author Shailesh.Kumar
 *
 */
public class SelUtils {

	public boolean exists;
	private String path="", xpath = "";
	private boolean boolValue;
	private WebElement webElement;
	private List<WebElement> elements, webelements;
	private int itemCount, count;
	public List<String> items = new ArrayList<String>();
	private List<String> vals = new ArrayList<String>();
	public WaitMethods waitMethods = new WaitMethods();
	public WebDriver driver;
	private Logger logger = TestBase.logger;
	private Properties objR = TestBase.objR, commonOR = TestBase.commonOR;
    public String[] breadCrm;
	
	public SelUtils() {
		this.driver = TestBase.getDriver();
	}

	/**
	 * It's for common method for common objects
	 * @param commobject
	 * @return
	 */
	public WebElement getCommonObject(String commobject) {
		int counter =0;
		WebElement wbElement=null;		
		try
		{
			Locators  locators = Locators.valueOf(commobject.substring(commobject.lastIndexOf('_')+1));
			forloop:	for(counter=0;counter<4;counter++)
			{
				try {
					switch (locators) {
					case link:
						wbElement = driver.findElement(By.linkText(commonOR.getProperty(commobject)));
						break forloop;
					case xpath:
						wbElement = driver.findElement(By.xpath(commonOR.getProperty(commobject)));
						break forloop;
					case css:
						wbElement = driver.findElement(By.cssSelector(commonOR.getProperty(commobject)));
						break forloop;
					case id:
						wbElement = driver.findElement(By.id(commonOR.getProperty(commobject)));
						break forloop;
					case name:
						wbElement = driver.findElement(By.name(commonOR.getProperty(commobject)));
						break forloop;
					default:
						Assert.fail("Object locator format is not proper");
						break;
					}
				}
				catch (Exception e1)
				{				
					TestBase.waitNSec(1);
				}
			}
			if(counter==4)
			{
				Assert.assertFalse(wbElement.equals(""));
			}
		} catch (Exception e)
		{					
			logger.error("Cannot find object with key -- " + commobject);
			Assert.fail("Cannot find object with key -- " + commobject);
		}//end of for							
		return wbElement;
	}

	/**
	 * It's for common method for common objects
	 * @param object
	 * @return x
	 */
	public WebElement getObject(String object) {
		int counter =0;
		WebElement wbElement=null;		
		try {
			Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
			forloop:	for(counter=0;counter<4;counter++)
			{
				try{
					switch (locators) {
					case link:
						wbElement = driver.findElement(By.linkText(objR.getProperty(object)));
						break forloop;
					case xpath:
						wbElement = driver.findElement(By.xpath(objR.getProperty(object)));
						break forloop;
					case css:
						wbElement = driver.findElement(By.cssSelector(objR.getProperty(object)));
						break forloop;
					case id:
						wbElement = driver.findElement(By.id(objR.getProperty(object)));
						break forloop;
					case name:
						wbElement = driver.findElement(By.name(objR.getProperty(object)));	
						break forloop;
					default:
						Assert.fail("Object locator format is not proper");
						break;
					}
				}
				catch (Exception e1)
				{				
					TestBase.waitNSec(1);
				}
			}
			if(counter==4)
			{
				Assert.assertFalse(wbElement.equals(""));
			}
		} catch (Exception e)
		{					
			logger.error("Cannot find object with key -- " + object);
			Assert.fail("Cannot find object with key -- "+ object);

		}//end of for							
		return wbElement;
	}

	/**
	 * It's for common method for common objects
	 * @param object
	 * @return x
	 */
	public WebElement getObject(String object, String value) {
		int counter =0;
		WebElement wbElement=null;		
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
		try {
			forloop:	for(counter=0;counter<4;counter++)
			{
				try{
					switch (locators) {
					case link:
						wbElement = driver.findElement(By.linkText(value));
						break forloop;
					case xpath:
						wbElement = driver.findElement(By.xpath(value));
						break forloop;
					case css:
						wbElement = driver.findElement(By.cssSelector(value));
						break forloop;
					case id:
						wbElement = driver.findElement(By.id(value));
						break forloop;
					case name:
						wbElement = driver.findElement(By.name(value));	
						break forloop;
					default:
						Assert.fail("Object locator format is not proper");
						break;
					}
				}
				catch (Exception e1)
				{				
					TestBase.waitNSec(1);
				}
			}
		if(counter==4)
		{
			Assert.assertFalse(wbElement.equals(""));
		}
		} catch (Exception e)
		{					
			logger.error("Cannot find object with key -- " + object);
			Assert.fail("Cannot find object with key -- "+ object);
		}//end of for							
		return wbElement;
	}

	/**
	 * It's for common method for common objects
	 * @param object
	 * @return x
	 */
	public List<WebElement> getObjects(String object) {
		int counter =0;
		List<WebElement> wbElementList=null;	
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
		try {
			forloop:	for(counter=0;counter<4;counter++)
			{
				try{
					switch (locators) {
					case link:
						wbElementList = driver.findElements(By.linkText(objR.getProperty(object)));
						break forloop;
					case xpath:
						wbElementList = driver.findElements(By.xpath(objR.getProperty(object)));
						break forloop;
					case css:
						wbElementList = driver.findElements(By.cssSelector(objR.getProperty(object)));
						break forloop;
					case id:
						wbElementList = driver.findElements(By.id(objR.getProperty(object)));
						break forloop;
					case name:
						wbElementList = driver.findElements(By.name(objR.getProperty(object)));		
						break forloop;
					default:
						Assert.fail("Object locator format is not proper");
						break;
					}
				}
				catch (Exception e1)
				{				
					TestBase.waitNSec(1);
				}
			}
		if(counter==4)
		{
			Assert.assertFalse(wbElementList.equals(""));
		}
		} catch (Exception e)
		{					
			logger.error("Cannot find object with key -- " + object);
			Assert.fail("Cannot find object with key -- " + object);
		}//end of for							
		return wbElementList;
	}

	/**
	 * It's for common method for common objects
	 * @param object
	 * @return x
	 */
	public List<WebElement> getObjects(String object, String value) {
		int counter =0;
		List<WebElement> wbElementList=null;
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
		try {
			forloop:	for(counter=0;counter<4;counter++)
			{
				try{
					switch (locators) {
					case link:
						wbElementList = driver.findElements(By.linkText(value));
						break forloop;
					case xpath:
						wbElementList = driver.findElements(By.xpath(value));
						break forloop;
					case css:
						wbElementList = driver.findElements(By.cssSelector(value));
						break forloop;
					case id:
						wbElementList = driver.findElements(By.id(value));
						break forloop;
					case name:
						wbElementList = driver.findElements(By.name(value));
						break forloop;
					default:
						Assert.fail("Object locator format is not proper");
						break;
					}
				}
				catch (Exception e1)
				{				
					TestBase.waitNSec(1);
				}
			}
		if(counter==4)
		{
			Assert.assertFalse(wbElementList.equals(""));
		}
		} catch (Exception e)
		{					
			logger.error("Cannot find object with key -- " + object);
			Assert.fail("Cannot find object with key -- "+ object);
		}//end of for							
		return wbElementList;
	}


	/**
	 * isElementPresent checks if the element is present in the application
	 * Returns the 
	 * @param object
	 * @return x
	 */
	public boolean isWebElementPresent(String  object) {
		boolValue = false;
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
		try{
			switch (locators) {
			case link:
				driver.findElement(By.linkText(object));
				boolValue=true;
				break;
			case xpath:
				driver.findElement(By.xpath(object));
				boolValue=true;
				break;
			case css:
				driver.findElement(By.cssSelector(object));
				boolValue=true;
				break;
			case id:
				driver.findElement(By.id(object));
				boolValue=true;
				break;
			case name:
				driver.findElement(By.name(object));
				boolValue=true;	
				break;
			default:
				Assert.fail("Object locator format is not proper");
				break;
			}
			return boolValue;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * isElementNotPresent checks if element is not present in the application
	 * Returns the 
	 * @param object
	 * @return x
	 */
	public boolean isEleNotPresent(WebElement element) {
		boolValue = false;
		try{
			if(!(element.isDisplayed()))   {  
				boolValue=true;
			}
			return boolValue;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Returns the web element
	 * @param locator
	 * @return x
	 */
	public WebElement getObjectDirect(final By locator) {
		int counter =0;
		WebElement wbElement=null;
		try
		{
			for(counter=0;counter<4;counter++)
			{
				try{
					wbElement = driver.findElement(locator);
					break;
				}
				catch (Exception e1)
				{				
					TestBase.waitNSec(1);
				}
			}
			if(counter==4)
			{
				Assert.assertFalse(wbElement.equals(""));
			}

		} catch (Exception e)
		{					
			logger.error("Cannot find object with key -- " + locator);
		}//end of for							
		return wbElement;
	}

	/**
	 * Returns the web element
	 * @param locator
	 * @return x
	 */
	public List<WebElement> getObjectsDirect(final By locator) {
		int counter =0;
		List<WebElement> wbElementList=null;
		try
		{
			for(counter=0;counter<4;counter++)
			{
				try{
					wbElementList = driver.findElements(locator);
					break;
				}
				catch (Exception e1)
				{				
					TestBase.waitNSec(1);
				}
			}
			if(counter==4)
			{
				Assert.assertFalse(wbElementList.equals(""));
			}

		} catch (Exception e)
		{					
			logger.error("Cannot find object with key -- " + locator);
		}//end of for							
		return wbElementList;
	}

	/**
	 * Returns the web element
	 * @param locator
	 * @return x
	 */
	public List<WebElement> getObjectsDirect(String objLoc, String val) {
		List<WebElement> wbElementList=null;
		Locators  locators = Locators.valueOf(objLoc.substring(objLoc.lastIndexOf('_')+1));
		try
		{
			switch (locators) {
			case link:
				wbElementList = driver.findElements(By.linkText(val));
				break;
			case xpath:
				wbElementList = driver.findElements(By.xpath(val));	
				break;
			case css:
				wbElementList = driver.findElements(By.cssSelector(val));	
				break;
			case id:
				wbElementList = driver.findElements(By.id(val));
				break;
			case name:
				wbElementList = driver.findElements(By.name(val));		
				break;
			default:
				Assert.fail("Object locator format is not proper");
				break;
			}
		}
		catch (Exception e1)
		{				
			logger.error("Cannot find object with key -- " + objLoc);
		}	
		return wbElementList;
	}

	/**
	 * Verify, specified button is displayed.
	 */
	public void vButtDisp(String locator, String eleName) {
		waitMethods.waitForElementDisplayed(locator);
		Assert.assertTrue(getObject(locator).getAttribute("onmouseover").contains(eleName), 
				"'"+eleName+"' button is not displayed.");
		logger.info("Verified, '"+eleName+"' button is displayed.");		
	}

	/**
	 * Verify specified drop down list is opened, after, click on the 
	 * Drop-down list icon of the field.
	 */
	public void vDropDnListOpened(String drpDnEleLoc, String drpDnLstLoc, String eleName){
		waitMethods.waitForElementDisplayed(drpDnEleLoc);
		getObject(drpDnEleLoc).click();
		waitMethods.waitForElement(getObject(drpDnLstLoc));
		int drpDnLstSize = getObjects(drpDnLstLoc).size();
		Assert.assertTrue(drpDnLstSize != 0,"Drop-down list is not opened.");
		logger.info("Verified, '"+eleName+"' drop-down list is opened.");
	}

	/**
	 * Select any of the option from the list
	 * @param object
	 * @param value
	 */
	public void selectItem(WebElement object,String value){
		waitMethods.waitForEleEnable(object);
		Select listbox=new Select(object);
		listbox.selectByVisibleText(value);
	}

	/**
	 * It returns the currently selected item 
	 * @param object
	 * @return
	 */
	public String getSelectedItem(WebElement object){
		Select listbox=new Select(object);
		return listbox.getFirstSelectedOption().getText();
	}

	/**
	 * Click on the expected item from Drop-down list icon and choose specified 
	 * scenario, verify the field is set to specified scenario.
	 **/
	public void vselectedItemInDrpDn(String locator, String expDrpdnItm){
		selectItem(getObject(locator), expDrpdnItm);
		vDrpDnSelcItem(locator, expDrpdnItm);
	}
	/**
	 * Method for verifying the list box Default value and also the expected 
	 * Items presence in the application list items
	 * @param listelement
	 * @param defaultVal
	 * @param expItems
	 */
	public void verifyDefaultValNLvlItems(WebElement listelement,String defaultVal,String[] expItems,String elementName){
		String selVal=getSelectedItem(listelement);
		Assert.assertTrue(selVal.equalsIgnoreCase(defaultVal), defaultVal+" is not as Default value ");
		vMultiListOptions(listelement, expItems);
		logger.info("Verified the Default value as '"+defaultVal+"' for the '"+elementName+"' and also the list items");
	}
	/**
	 * validate multi list values
	 * @param listelement
	 * @param expItems
	 */
	public void vMultiListOptions(WebElement listelement,String[] expItems )
	{
		String[] appItems=   getSelListItems(listelement);
		List<String>  appList = Arrays.asList(appItems);  
		for(int iter=0;iter<expItems.length;iter++){
			Assert.assertTrue(appList.contains(expItems[iter]),"Expected listItem "+expItems[iter]+" is not Present");
		}
	}
	/**
	 * gets list of options as String items
	 * @param object
	 * @return drop down options
	 */
	public String[] getSelListItems(WebElement element)
	{
		Select dropDown = new Select(element);           
		int iter=0;
		List<WebElement> options = dropDown.getOptions();
		String[] listitems=new String[options.size()];
		try
		{
			for(WebElement option:options){
				listitems[iter]=option.getText();
				iter++;
			}
		}catch (Throwable t) {
			Assert.fail("Failed during getting list Items from drop down");
		}
		return listitems;
	}

	/**
	 * It's for common method for enter data in input boxes
	 * @return
	 */
	public void populateInputBox(String object, String value) {
		//TestBase.waitNSec(1);
		waitMethods.waitForElementDisplayed(object);
		if (!(getObject(object).getAttribute("value").isEmpty())) {
			getObject(object).clear();
		}
		//getObject(object).click();
		getObject(object).sendKeys(value);
		logger.info("Input box is populated with " + speCharReplc(value));
	}


	/**
	 * Replaces text with any below mentioned special character
	 * @param value
	 * @return String
	 */
	public String speCharReplc(String value){
		String text = "Special Char";
		if(!value.matches("\\w+( \\w+)*$"))
		{
			return text;
		}
		return value;
	}

	/**
	 * It's for common method for enter data in input boxes
	 * @return
	 */
	public void populateInputBox(String objLoc, String oldVal, String newVal, String value){
		path = getPath(objLoc).replace(oldVal, newVal);
		waitMethods.waitForElementDisplayed(objLoc,path);
		if(!(getObject(objLoc,path).getAttribute("value").isEmpty())){
			getObject(objLoc,path).clear();
		}
		getObject(objLoc,path).sendKeys(value);
	}

	/**
	 * Get list of options 
	 * @param object
	 * @return drop down options
	 */
	public String getListItems(String objLoc){
		StringBuffer lstItemsStr = new StringBuffer();
		waitMethods.waitForElementDisplayed(objLoc);
		List<WebElement> lstItems = getObjects(objLoc);
		waitMethods.waitForEleEnable(lstItems.get(count));
		for (int cnti = 0; cnti < lstItems.size(); cnti++) {
			lstItemsStr = lstItemsStr.append(" ").append(lstItems.get(cnti).getText());
		}
		return lstItemsStr.toString();
	}

	/**
	 * Verify check box is selected
	 */	
	public void vChkBxSelected(String element, String elementName){
		Assert.assertTrue(getObject(element).isSelected(), elementName+" check box is not selected.");
		logger.info("Verified, '"+elementName+"' check box is selected.");	
	}

	/**
	 * Verify, specified check box fic.
	 */
	public void vSelcChkBx(String chkBxLoc, String chkBoxName) {
		waitMethods.waitForEleEnable(getObject(chkBxLoc));
		selecChkBx(chkBxLoc);
		vChkBxSelected(chkBxLoc, chkBoxName);
	}

	/**
	 * Verify check box is selected
	 */	
	public void vDeSelecChkBx(String chkBxLoc, String chkBxName){
		Assert.assertFalse(getObject(chkBxLoc).isSelected(), chkBxName+" check box is not deselected.");
		logger.info("Verified, '"+chkBxName+"' check box is deselected.");	
	}

	/**
	 * Select specified check box.
	 */	
	public void selecChkBx(String chkBxLoc){
		if(!getObject(chkBxLoc).isSelected()){
			getObject(chkBxLoc).click();
		}
	}

	/**
	 * Deselect specified check box.
	 */	
	public void deSelecChkBx(String chkBxLoc){
		if(getObject(chkBxLoc).isSelected()){
			getObject(chkBxLoc).click();
		}
	}

	/**
	 * Verify, check box is checked.
	 */
	public void verifyChked(String locator, String chk, String tick, String chkBxName){
		Assert.assertTrue(getObject(locator).getAttribute("value").equals(chk), 
				locator+" check box is not "+tick);
		logger.info("Verified, '"+chkBxName+"' check box is "+tick);	
	}	

	/**
	 * Select the check box
	 * @param chk
	 */
	public void chkBxChk(String locator, String chk){
		waitMethods.waitForElementEnable(locator);
		webElement = getObject(locator);
		if(!(webElement.getAttribute("value").equals(chk))){
			webElement.click();
		}
	}

	/**
	 * Select drop down option
	 */	
	public void selctDrpDnItem(String object,String value){
		waitMethods.waitForElementEnable(object);
		Select listbox=new Select(getObject(object));
		listbox.deselectAll();
		listbox.selectByVisibleText(value);
	}

	/**
	 * Verify Element is present
	 */	
	public void verifyElementDisp(String element, String eleName){
		TestBase.waitNSec(2);
		waitMethods.waitForElementDisplayed(element);
		//TestBase.wait.until(ExpectedConditions.elementToBeClickable(getObject(element)));
		Assert.assertTrue(getObject(element).isDisplayed(), eleName+" element is not displayed.");
		logger.info("Verified, '"+eleName+"' element is displayed.");
	}

	/**
	 * Verify Element is not Displayed
	 */	
	public void verifyElementNotDisp(String objLoc, String eleName){
		TestBase.waitNSec(1);
		Assert.assertFalse(getObject(objLoc).isDisplayed(), eleName + " element is displayed.");
		logger.info("Verified, '"+eleName+"' element is not displayed.");
	}

	/**
	 * Verify Element is not present
	 */	
	public void verifyElementNotPresent(String objLoc, String eleName){
		Assert.assertFalse(waitMethods.isElementPresent(objLoc), eleName + " element is displayed.");
		logger.info("Verified, '"+eleName+"' element is not displayed.");
	}

	/**
	 * Verify Element Text is present
	 */	
	public void vExpValPresent(String object, String text){
//		waitMethods.waitForElementDisplayed(object);
		waitForTxtPresent(object, text);
		Assert.assertTrue(getObject(object).getText().trim().equalsIgnoreCase(text), "expected value :'"+speCharReplc(text)+"' is not displayed.");
		logger.info("Verified, expected value :'"+speCharReplc(text)+"' is displayed.");
	}

	/**
	 * Verify Element is not selected
	 */
	public void verifyEleNotSelected(String element) {
		Assert.assertFalse(getObject(element).isSelected());
		logger.info("Verified, expected element is not selected.");
	}

	/**
	 * Verify Element Text is present
	 */	
	public void vExpValPresent(String objLoc, String oldVal, String newVal, String val){
		path = getPath(objLoc).replaceAll("(?i)"+oldVal, newVal);
		waitMethods.waitForElementDisplayed(objLoc, path);
		Assert.assertTrue(isElementPresent(objLoc, path), "Expected '"+speCharReplc(val)+"' value is not displayed.");
		logger.info("Verified, expected value is displayed.");
	}

	/**
	 * Verify Element Text is present
	 */	
	public void vExpValPresent(WebElement element, String val){
		waitMethods.waitForElement(element);
		Assert.assertTrue(element.getText().equalsIgnoreCase(val), "Expected '"+val+"' value is not displayed.");
		logger.info("Verified, expected '"+val+"' value is displayed.");
	}

	/**
	 * Verify Element values are present
	 */	
	public void vExpValsPresent(String objLoc, String oldVal, String newVal, String[] vals){
		path = getPath(objLoc).replace(oldVal, newVal);
		waitMethods.waitForElementDisplayed(objLoc, path);
		elements = getObjectsDirect(objLoc, path);		
		for(itemCount = 0; itemCount < elements.size(); itemCount++){
			Assert.assertTrue(elements.get(itemCount).getText().equals(vals[itemCount]), "Expected '"+vals[itemCount]+"' value is not displayed.");
		}
		logger.info("Verified, all expected values are displayed.");
	}

	/**
	 * Verify Element Text is present
	 */	
	public void vExpValContains(String object, String text){
		waitForTxtPresent(object, text);
		Assert.assertTrue(getObject(object).getText().contains(text));
		logger.info("Verified, expected value :'"+text+"' is displayed.");
	}

	/**
	 * Verify Element Text is not present
	 */	
	public void vExpValNotPresent(String object, String text){
		Assert.assertFalse(getObject(object).getText().equalsIgnoreCase(text));
		logger.info("Verified, expected value :'"+text+"' is displayed.");
	}

	/**
	 * Verify Element is enabled
	 */	
	public void vEleEnabled(String objLoc, String objName)	{
		waitMethods.waitForEleEnable(getObject(objLoc));
		Assert.assertTrue(getObject(objLoc).isEnabled(), "'"+objName+"' is not editable.");
		logger.info("Verified, '"+objName+"' is editable.");
	}

	/**
	 * Verify that expected sting present in the breadcrum
	 * @param text
	 */
	public void verifyBreadCrumb(String text) {
		waitMethods.waitForEleEnable(getCommonObject("bread_crumb_id"));
		//waitForCommTxtPresent("bread_crumb_id", text);
	    //webelements=getCommonObject("bread_crumb_id").findElements(By.tagName("p"));
	    breadCrm= text.split("»");
	    if(breadCrm.length==1){
	    	waitForCommTxtPresent(getObjectsDirect(By.xpath(getCommonPath("bread_crumb_xpath"))).get(2), breadCrm[0].trim());
	    	Assert.assertTrue(getObjectsDirect(By.xpath(getCommonPath("bread_crumb_xpath"))).get(2).getText().trim().contains(breadCrm[0].trim()), "Expected text is not present in bread crumb.");
	    }else{
	    for(int i=0;i<breadCrm.length;i++){
	    waitForCommTxtPresent(getObjectsDirect(By.xpath(getCommonPath("bread_crumb_xpath"))).get(i), breadCrm[i].trim());
	    Assert.assertTrue(getObjectsDirect(By.xpath(getCommonPath("bread_crumb_xpath"))).get(i).getText().trim().contains(breadCrm[i].trim()), "Expected text is not present in bread crumb.");
	    }}
		logger.info("Verified, expected breadcrumb is displayed");
	}

	/**
	 * Switch to frame.
	 */
	public void switchToFrame(){
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
	}

	/**
	 * Verify Element is editable
	 */	
	public void vEleEditble(String objLoc, String objName)	{
		webElement = getObject(objLoc);
		String tagName = webElement.getTagName().toLowerCase();
		boolean acceptableTagName = "input".equals(tagName) || "select".equals(tagName);
		boolean edit = false;
		String readonly = "";
		if ("input".equals(tagName)) {
			readonly = webElement.getAttribute("readonly");
			if (readonly == null || "false".equals(readonly)) {
				readonly = "";
			}
		}
		edit = getObject(objLoc).isEnabled() && acceptableTagName && "".equals(readonly);
		Assert.assertTrue(edit, "'"+objName+"' is not editable.");
	}

	/**
	 * Verify Element is disabled
	 */	
	public void verifyEltDisabled(String object)	{
		Assert.assertFalse(getObject(object).isEnabled(), " Expected element is not disabled");
		logger.info(" Verified that expected element is disabled");
	}

	/**
	 * Refresh the table
	 */
	public void refreshTbl(String locator){
		TestBase.waitNSec(1);
		new Actions(driver).moveToElement(getCommonObject("refresh_link")).click().build().perform();
		/*webElement = getCommonObject("refresh_link");
		waitMethods.waitForEleEnable(webElement);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", webElement);*/
		waitForTxtPresent(locator, TestBase.editPageDispTxt);
	}

	/**
	 * Select specified radio button.
	 */	
	public void selecRadButt(String radButtLoc)	{
		if(!getObject(radButtLoc).isSelected()){
			getObject(radButtLoc).click();
		}
	}

	/**
	 * Verify radio button is selected
	 */	
	public void vRadButtSelected(String radButtLoc, String radButtName)	{
		Assert.assertTrue(getObject(radButtLoc).isSelected(), radButtName+" radio button is not selected.");
		logger.info("Verified, '"+radButtName+"' radio button is selected.");	
	}

	/**
	 * Select and verify specified radio button.
	 */
	public void selcAndVRadButt(String radButtLoc, String radButtName) {
		selecRadButt(radButtLoc);
		vRadButtSelected(radButtLoc, radButtName);
	}

	/**
	 * Select the item from list box.
	 * @param rootEst
	 */
	public void clkToSelecItm(String locator, String expItem){
		boolean flag = false;
		List<WebElement> drpDnlst = getObjects(locator);
		for(itemCount = 0; itemCount<drpDnlst.size() ; itemCount++){
			waitMethods.waitForElement(drpDnlst.get(itemCount));
			TestBase.waitNSec(1);
			if(drpDnlst.get(itemCount).getText().equals(expItem)){
				drpDnlst.get(itemCount).sendKeys("");
				drpDnlst.get(itemCount).click();
				flag = true;
				break;
			}
		}
		if(!flag){
			logger.error(expItem + " item is not displayed in list box.");
			Assert.fail(expItem + " item is not displayed in list box.");
		}			
	}

	/**
	 * Verify expected icon is displayed.
	 * @param iconLocator
	 */
	public void verifyExpIconDisplayed(String iconLocator, String iconName){
		Assert.assertTrue(getObject(iconLocator).isDisplayed(), "Expected icon is not displayed.");
		logger.info("Verified, expected icon '"+iconName+"' is displayed.");
	}

	/**
	 * Verify expected icon is displayed.
	 * @param iconLocator
	 */
	public void verifyExpIconDisplayed(WebElement elemnt, String iconName){
		Assert.assertTrue(elemnt.isDisplayed(), "Expected icon is not displayed.");
		logger.info("Verified, expected icon '"+iconName+"' is displayed.");
	}

	/**
	 * Verify, specified element is not displayed.
	 */
	/*public void vExpButtNotDisp(String locator, String eleName){
		Assert.assertFalse(waitMethods.isElementPresent(locator), "'"+eleName+"' is displayed.");
		logger.info("Verified, '"+eleName+"' is not displayed.");				
	}*/

	/**
	 * Click expected icon on the table .
	 */
	public void clkOnIcon(String locator){
		waitMethods.waitForElementDisplayed(locator);
		getObject(locator).click();
		logger.info("Clicked on icon.");
	}

	/**
	 * Click expected icon on the table .
	 */
	public void clkOnIcon(String iconLoc, String oldVal, String newVal, String val){
		path = getPath(iconLoc).replace(oldVal, newVal);	
		waitMethods.waitForElementDisplayed(iconLoc, path);
		waitMethods.waitForEleEnable(getObjectDirect(By.xpath(path)));
		clickOnWebElement(iconLoc, oldVal, newVal, val);
		logger.info("Clicked on '"+val+"' icon.");
	}

	/**
	 * Method which returns the Attribute of given Locator
	 * @param eleLoc
	 * @param attr
	 * @return String attribute
	 */
	public String  getAttribute(String eleLoc,String attr ){
		return getObject(eleLoc).getAttribute(attr);
	}

	/**
	 * Verify 'Back to List' button.
	 * @param columns
	 */
	public void vBackToLstButt(){
		Assert.assertTrue(getCommonObject("back_list_link").isDisplayed(), "'"+ TestBase.bckToLst +"' button is not displayed.");
		logger.info(" Verified, '"+ TestBase.bckToLst +"' button is present on expected edit page");
	}

	/**
	 * Click on 'Back to List' button.
	 * @param columns
	 **/
	public void clkBackToLstButt(){
		webElement = getCommonObject("back_list_link");
		waitMethods.waitForElement(webElement);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", webElement);
		logger.info("Clicked on '"+ TestBase.bckToLst +"' button.");
	}

	/**
	 * Get list of items 
	 * @param object
	 * @return drop down list
	 */
	public List<String> getLstItems(List<WebElement> expVals){
		vals.clear();
		for(int cnti = 0; cnti < expVals.size(); cnti++) {
			vals.add(expVals.get(cnti).getText());
		}
		return vals;
	}

	/**
	 * Get the table columns headers.  
	 * @param InVisCols
	 * @param VisCols
	 */
	public String getTabColHds(String objLoc){
		StringBuffer tabCols = new StringBuffer();
		waitMethods.waitForElementDisplayed(objLoc);
		elements = getObjects(objLoc);
		for(itemCount = 0; itemCount < elements.size(); itemCount++){
			waitMethods.waitForEleEnable(elements.get(itemCount));
			tabCols = tabCols.append(", ").append(elements.get(itemCount).getText());
		}
		return tabCols.toString();
	}	

	/**
	 * Verify, expected tab is in focus.
	 */
	public void vExpTabInFocus(String expTabLoc){
		webElement = getObject(expTabLoc);
		waitMethods.waitForElementFocus(webElement);
		Assert.assertTrue(webElement.getAttribute("class").contains("tabs-selected"), 
				"Expected tab is not in focused.");
		logger.info("Verified, expected tab is in focused.");				
	}

	/**
	 * Verify, the expected tabs not in focus.
	 */
	public void vExpNotFocusedTabs(String[] expTabs){
		waitMethods.waitForElementDisplayed("not_focus_tabs_css");
		vals = getLstItems(getObjects("not_focus_tabs_css"));
		for(itemCount =0 ; itemCount < expTabs.length; itemCount++) {
			Assert.assertTrue(vals.contains(expTabs[itemCount]), 
					expTabs[itemCount] + " tab is in focus.");
		}
		logger.info("Verified, '"+vals+"' tabs are not focused.");
	}

	/**
	 * Verify, expected icon.
	 * @param sceName
	 */
	public void vExpIcon(String iconLoc, String name, String iconName){
		xpath = getPath(iconLoc).replace(TestBase.nameLbl, name);	
		waitMethods.waitForElementDisplayed(iconLoc, xpath);
		Assert.assertTrue(getObjectDirect(By.xpath(xpath)).isDisplayed(), iconName + " icon is not displyed");
		logger.info("Verified, '"+iconName+"' icon is displayed for "+name);	
	}

	/**
	 * Verify, expected icon not present.
	 * @param sceName
	 */
	public void vEleNotPresent(String objLoc, String name, String eleName){
		path = getPath(objLoc).replaceAll("(?i)"+TestBase.nameLbl, name);	
		Assert.assertFalse(isElementPresent(objLoc, path), eleName + " is displyed for "+name);
		logger.info("Verified, '"+eleName+"' is not displayed.");	
	}

	/**
	 * Verify, expected icon not present.
	 * @param sceName
	 */
	public void vEleNotPresent(String objLoc, String val, String renVal, String eleName){
		xpath = getPath(objLoc).replace(val, renVal);	
		Assert.assertTrue(isEleNotPresent(getObjectDirect(By.xpath(objLoc))), eleName + " web element is displyed.");
		logger.info("Verified, '"+eleName+"' web element is not displayed.");	
	}

	/**
	 * Verify, expected icon not present.
	 * @param sceName
	 */
	public void vEleNotPresent(String objLoc, String eleName){
		Assert.assertFalse(waitMethods.isElementPresent(objLoc), eleName + " is displyed.");
		logger.info("Verified, '"+eleName+"' is not displayed.");	
	}

	/**
	 * verifies total records are same in the table as displayed at the bottom.
	 */
	public void verifyNumOfRows(String objLoc)
	{
		elements = getObjects(objLoc);
		Assert.assertEquals(getObject("disp_rows_xpath").getText().replaceAll("[A-z:,]","").trim(), 
				Integer.toString(elements.size()));
	}
	/**
	 * Verify, expected column value is in the specified tab.
	 * @param sceName
	 */
	public void vColVal(String nameLoc, String name,String value){
		xpath = getPath(nameLoc).replace(TestBase.nameLbl, name);
		waitMethods.waitForElement(getObjectDirect(By.xpath(xpath)));
		Assert.assertTrue(getObjectDirect(By.xpath(xpath)).getText().equalsIgnoreCase(value), "Expected '"+value+"' value is not displayed.");
		logger.info("Verified, expected '"+value+"' value is displayed.");
	}

	/**
	 * Verify, expected column value in the specified tab.
	 * @param name
	 */
	public void vExpColData(String colLoc, String name, String colVal){
		xpath = getPath(colLoc).replace(TestBase.nameLbl, name);
		waitMethods.waitForElementDisplayed(colLoc, xpath);
		Assert.assertTrue(getObjectDirect(By.xpath(xpath)).getText().contains(colVal),
				"'Status' is not displyed");
		//logger.info("Verified, '"+colVal+"' expected status is displayed.");
		logger.info("Verified, '" +name+" : " +colVal+"' expected status is displayed.");
	}
	
	/**
	 * Click on the web element.
	 * selUtils.clickOnWebElement
	 * @param locator
	 * @param text
	 */
	public void clickOnWebElement(String locator,String oldVal, String newVal){
		xpath = getPath(locator).replaceAll(oldVal, newVal);
		webElement = getObjectDirect(By.xpath(xpath));
		waitMethods.waitForElement(webElement);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", webElement);
		logger.info("Clicked on the Expected element.");
	}


	/**
	 * Click on the web element.
	 * selUtils.clickOnWebElement
	 * @param locator
	 * @param text
	 */
	public void clickOnWebElement(String locator, String expWebEle){
		waitMethods.waitForElementDisplayed(locator);
		//TestBase.waitNSec(1);
		//getObject(locator).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", getObject(locator));
		logger.info("Clicked on the '"+expWebEle+"' element.");
	}

	/**
	 * Click on the web element.
	 * selUtils.clickOnWebElement
	 * @param locator
	 * @param text
	 */
	public void clickOnWebElement(String locator,String oldVal, String newVal, String expWebEle){
		xpath = getPath(locator).replaceAll("(?i)"+oldVal, newVal);
		waitMethods.waitForElementDisplayed(locator, xpath);
		/*webElement = getObjectDirect(By.xpath(xpath));
		waitForElement(webElement);*/
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", getObjectDirect(By.xpath(xpath)));
		logger.info("Clicked on the '"+speCharReplc(expWebEle)+"'.");
	}

	/**
	 * Click on the web element.
	 * selUtils.clickOnWebElement
	 * @param locator
	 * @param text
	 */
	public void clickOnCommWebElement(String locator,String oldVal, String newVal, String expWebEle){
		xpath = getCommonPath(locator).replace(oldVal, newVal);
		waitMethods.waitForElementDisplayed(locator, xpath);
		/*webElement = getObjectDirect(By.xpath(xpath));
		waitForElement(webElement);*/
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", getObjectDirect(By.xpath(xpath)));
		logger.info("Clicked on the '"+expWebEle+"'.");
	}

	/**
	 * Verify all checkbox is selected
	 * vChkbxsSlcted 
	 * @param locator
	 */
	public void vChkbxsSlcted(String locator){
		waitMethods.waitForElementDisplayed(locator);
		webelements = getObjects(locator);
		for (int cnti = 0; cnti < webelements.size(); cnti++) {
			Assert.assertTrue(webelements.get(cnti).isSelected(),"Verified checkbox is unselected");
		}
		logger.info("Verified all checkbox is selected");
	}

	/**
	 * Verify all checkbox is unselected
	 * vChkbxsUnSlcted
	 * @param locator
	 */
	public void vChkbxsUnSlcted(String locator){
		waitMethods.waitForElementDisplayed(locator);
		webelements = getObjects(locator);
		for (int cnti = 0; cnti < webelements.size(); cnti++) {
			Assert.assertFalse(webelements.get(cnti).isSelected(),"Verified checkbox is selected");
		}
		logger.info("Verified all checkbox is unselected");
	}	

	/**
	 * Verify, the specified drop down list, specified selected item.
	 */
	public void vDrpDnSelcItem(String locator, String item){
		waitMethods.waitForElementEnable(locator);
		Assert.assertTrue(getSelectedItem(getObject(locator)).equals(item), "Drop down field is not set to '"+item+"'.");
		logger.info("Verified, specified drop down field is set to '"+item+"'.");
	}

	/**
	 * Method for returning object Locator
	 * @param objLocator
	 * @return
	 */
	public String getPath(String objLocator) {
		String objPath="";
		try {
			objPath = objR.getProperty(objLocator);
			if("".equals(objPath)){
				Assert.fail("Path is Null");
			}
		} catch (Exception t) {
			logger.error("Cannot find object with key -- " + objLocator);
		}
		return objPath;

	}

	/**
	 * Method for returning common object Locator
	 * @param objLocator
	 * @return
	 */
	public String getCommonPath(String commObjLocator) {
		String objPath="";
		try {
			objPath = commonOR.getProperty(commObjLocator);
			if("".equals(objPath)){
				Assert.fail("Path is Null");
			}
		} catch (Exception t) {
			logger.error("Cannot find object with key -- " + commObjLocator);

		}
		return objPath;
	}

	/** The waitForElementNotPresent function will wait for the element for a
	 * default duration of customized seconds To increase or decrease this time
	 * change the value of the integer 'timeoutSec' in "Common.java"	 * 
	 * @param Locator
	 * @param driver
	 */
	public void waitForElementNotDisp(String object) {
		for(int counter=0;counter<10;counter++)  {
			try{
				if(!(getObject(object).isDisplayed()))   {
					break;
				}
			}catch (Exception excp){
				TestBase.waitNSec(1);
			}
		}	
	}

	/** The waitForElementNotPresent function will wait for the element for a
	 * default duration of customized seconds To increase or decrease this time
	 * change the value of the integer 'timeoutSec' in "Common.java"
	 * @param Locator
	 * @param driver
	 */
	public void waitForElementNotPresent(String locator, String oldVal, String newVal) {
		xpath = getPath(locator).replace(oldVal, newVal);
		for(int counter=0;counter<10;counter++)  {
			try{
				if(!isElementPresentxpath(xpath))   {  
					break;
				}
			}catch (Exception excp){
				TestBase.waitNSec(1);
			}
		}	
	}


	/** The waitForElementSelected function will wait for the element for a
	 * default duration of customized seconds To increase or decrease this time
	 * change the value of the integer 'timeoutSec' in "Common.java"	 * 
	 * @param Locator
	 * @param driver
	 */
	public void waitForDirectEleSelected(String locator, String oldVal, String newVal) {
		xpath = getPath(locator).replace(oldVal, newVal);
		for(int counter=0;counter<10;counter++)  {
			try{
				if(getObjectDirect(By.xpath(xpath)).isSelected())   
				{  
					break;
				}
			}catch (Exception excp){
				TestBase.waitNSec(1);
			}
		}	
	}

	/**
	 * The waitForTxttPresent function will wait for the element text for a 
	 * default duration of customized seconds To increase or decrease this time
	 * change the value of the integer 'timeoutSec' in "Common.java"	 
	 * @param Locator
	 **/
	public boolean waitForTxtPresent(String locator, String text) {  
		exists = false;
		int cnti=0;
		for(cnti=0; cnti<20; cnti++)  
		{ 
			try {
				if(getObject(locator).getText().contains(text)) { 
					exists = true;
					break;
				}else {
					TestBase.waitNSec(1);
				}
			}catch(Exception e){
				TestBase.waitNSec(1);
			}
		}			
			/*if (cnti==20)
				{
				    logger.info("Associated text is not found with the web element");
					Assert.assertTrue(getObject(locator).getText().contains(text));
				}	*/
		//}
		return exists;
	}
	
	/**
	 * The waitForgetTxttPresent function will wait for the element text present
	 * change the value of the integer 'timeoutSec' in "Common.java"	 
	 * @param Locator
	 **/
	public boolean waitForGetTxtPresent(String locator, String text) {  
		exists = false;
		for(int cnti=0; cnti<10; cnti++)  { 
			try {
				if(getObject(locator).getAttribute("value").contains(text)) {                                       
					exists = true;
					break;
				}else {
					TestBase.waitNSec(1);
				}
			}catch(Exception e){
				TestBase.waitNSec(1);
			}
		}
		return exists;
	}

	/**
	 * The waitForTxttPresent function will wait for the element text for a 
	 * default duration of customized seconds To increase or decrease this time
	 * change the value of the integer 'timeoutSec' in "Common.java"	
	 * @param Locator
	 **/
	public boolean waitForCommTxtPresent(String locator, String text) {       
		exists = false;
		for(int cnti=0; cnti<10; cnti++)  { 
			try {
				if(getCommonObject(locator).getText().contains(text)) {                                       
					exists = true;
					break;
				}else {
					TestBase.waitNSec(1);
				}
			}catch(Exception e){
				TestBase.waitNSec(1);
			}
		}
		return exists;
	}

	public boolean waitForCommTxtPresent(WebElement webEle, String text) {       
		exists = false;
		for(int cnti=0; cnti<2; cnti++)  { 
			try {
				if(webEle.getText().contains(text)) {                                       
					exists = true;
					break;
				}else {
					TestBase.waitNSec(1);
				}
			}catch(Exception e){
				TestBase.waitNSec(1);
			}
		}
		return exists;
	}
	
	/**
	 * isElementPresent checks if the element is present in the application
	 * Returns the 
	 * @param object
	 * @return x
	 */
	public boolean isElementPresentxpath(String  object) {
		boolValue = false;
		try{
			driver.findElement(By.xpath(object));
			boolValue=true;
		}
		catch (NoSuchElementException e) {
			return false;
		}
		return boolValue;
	}

	/**
	 * isElementPresent checks if the element is present in the application
	 * Returns the 
	 * @param object
	 * @return x
	 */
	public boolean isElementPresent(String  object, String value ) {
		boolValue = false;
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
		try{
			switch (locators) {
			case link:
				driver.findElement(By.linkText(value));
				boolValue=true;
				break;
			case xpath:
				driver.findElement(By.xpath(value));
				boolValue=true;
				break;
			case css:
				driver.findElement(By.cssSelector(value));
				boolValue=true;
				break;
			case id:
				driver.findElement(By.id(value));
				boolValue=true;
				break;
			case name:
				driver.findElement(By.name(value));
				boolValue=true;	
				break;
			default:
				Assert.fail("Object locator format is not proper");
				break;
			}
			return boolValue;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean waitForAttriInElemt(String locator,String attri,String val){
		for( int i=0;i<10;i++)  {   
			try{
				if(getObject(locator).getAttribute(attri).contains(val)){                                       
					exists = true;
					break;
				} else {
					TestBase.waitNSec(1);
				}
			} catch(Exception e){
				TestBase.waitNSec(1);
			}
		}
		return exists;
	}
	
	/**
	 * Convert web elements list to string list 
	 * @param ele
	 * @return list string options
	 *//*
	public List<String> getListItemsAsString(List<WebElement> ele){		          
		int iter=0;
		List<WebElement> options = ele;		
		List<String> listitems=new ArrayList<String>();
		for(iter=0;iter<options.size();iter++)
		{			
			listitems.add(options.get(iter).getText().trim());

		}		
		return listitems;
	}*/


}