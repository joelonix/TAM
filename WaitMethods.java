package com.ingenico.base;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ingenico.base.TestBase.Locators;

/**
 * WaitMethods class contains all methods related to wait
 * @author Shailesh.Kumar
 *
 */
public class WaitMethods {

	private boolean exists, boolValue;
	public String xpath = "";
	public WebDriver driver;
	private Properties objR = TestBase.objR;
	private String objFmt = "Object locator format is not proper";

	/**
	 *  Get Driver from TestBase Class
	 */
	public WaitMethods() {
		this.driver = TestBase.getDriver();
	}

	/**
	 * Wait for page to load.
	 */
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			/**
			 * Wait till document in ready state.
			 * @param driver
			 * @return
			 */
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver,50);
		try { 
			wait.until(expectation);
		} catch(Throwable error) {
			Assert.assertFalse(true,"page did not load");
		}
	}

	/** The waitMethods.waitForElementfocus function will wait for the element 
	 * for a default duration of customized seconds To increase or decrease this
	 * time change the value of the integer 'timeoutSec' in "Common.java" 
	 * @param Locator
	 * @param driver
	 */
	public boolean waitForElementFocus(WebElement webElement) {     
		exists = false;
		for(int cnti=0;cnti<10;cnti++)  {   
			try{
				if(webElement.getAttribute("class").contains("tabs-selected")) {                                       
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
	 * The waitForEleTxttPresent function will wait for the element text for a 
	 * default duration of customized seconds To increase or decrease this time
	 * change the value of the integer 'timeoutSec' in "Common.java"	
	 * @param Locator
	 **/
	public boolean waitForEleTxtPresent(WebElement object, String text) {    
		exists = false;
		for(int cnti=0;cnti<10;cnti++)  {      
			try{
				if(object.getText().contains(text))   {                                       
					exists = true;
					break;
				}else
				{
					TestBase.waitNSec(1);
				}
			}catch(Exception exp){
				TestBase.waitNSec(1);
			}
		}
		return exists;
	}

	/** The waitMethods.waitForElementPresent function will wait for the element
	 * for a default duration of customized seconds To increase or decrease this
	 * time change the value of the integer 'timeoutSec' in "Common.java" 
	 * @param Locator
	 * @param driver
	 */
	public void waitForElement(WebElement element) {           
		for(int counter=0;counter<10;counter++)  {     
			try {
				if(element.isDisplayed())   { 
					break;
				} else {
					TestBase.waitNSec(1);
				}
			} catch(Exception e) {
				TestBase.waitNSec(1);
			}

		}	
	}

	/** The waitMethods.waitForElementEnable function will wait for the element 
	 * for a default duration of customized seconds To increase or decrease this
	 * time change the value of the integer 'timeoutSec' in "Common.java" 
	 * @param Locator
	 * @param driver
	 */
	public void waitForEleEnable(WebElement element) {           
		for(int counter=0;counter<10;counter++)  {     
			try {
				if(element.isEnabled())   {  
					break;
				} else {
					TestBase.waitNSec(1);
				}
			} catch(Exception e) {
				TestBase.waitNSec(1);
			}
		}	
	}

	/** The waitMethods.waitForElementNotPresent function will wait for the 
	 * element for a default duration of customized seconds To increase or 
	 * decrease this time change the value of the integer 'timeoutSec' in 
	 * "Common.java"
	 * @param Locator
	 * @param driver
	 */
	public void waitForElementNotPresent(String locator) {	
		for(int counter=0;counter<15;counter++)  {
			try{
				if(!isElementPresent(locator))   {
					break;
				}
			}catch (Exception excp){
				TestBase.waitNSec(1);
			}
		}	
	}

	/** The waitMethods.waitForElementSelected function will wait for the 
	 * element for a default duration of customized seconds To increase or 
	 * decrease this time change the value of the integer 'timeoutSec' in 
	 * "Common.java" 
	 * @param Locator
	 * @param driver
	 */
	public void waitForDirectEleSelected(WebElement webElement) {
		for(int counter=0;counter<10;counter++)  {
			try{
				if(webElement.isSelected())   
				{  
					break;
				}
			}catch (Exception excp){
				TestBase.waitNSec(1);
			}
		}	
	}

	/**
	 * waitMethods.waitForElementDisplayed till the element is displayed
	 * Returns the 
	 * @param object
	 * @return x
	 */
	public boolean waitForElementDisplayed(String object){
		boolValue = false;
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));

		for(int cnti=0; cnti<15; cnti++)  {  
			try {
				switch (locators) {
				case link:
					boolValue =	driver.findElement(By.linkText(objR.getProperty(object))).isDisplayed();
					break;
				case xpath:
					boolValue	= driver.findElement(By.xpath(objR.getProperty(object))).isDisplayed();
					break;
				case css:
					boolValue= driver.findElement(By.cssSelector(objR.getProperty(object))).isDisplayed();
					break;
				case id:
					boolValue= driver.findElement(By.id(objR.getProperty(object))).isDisplayed();
					break;
				case name:
					boolValue=driver.findElement(By.name(objR.getProperty(object))).isDisplayed();
					break;
				default:
					Assert.fail(objFmt);
					break;
				}
				if (boolValue) {
					return boolValue;
				}
			} catch(Exception e){
				TestBase.waitNSec(1);
			}
		}
		return boolValue;
	}

	/**
	 * waitMethods.waitForElementDisplayed till the element is displayed
	 * Returns the 
	 * @param object
	 * @return x
	 */
	public boolean waitForElementDisplayed(String object, String value){
		boolValue = false;
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
		for(int cnti=0; cnti<10; cnti++)  {  
			try {
				switch (locators) {
				case link:
					boolValue =	driver.findElement(By.linkText(value)).isDisplayed();
					break;
				case xpath:
					boolValue	= driver.findElement(By.xpath(value)).isDisplayed();
					break;
				case css:
					boolValue= driver.findElement(By.cssSelector(value)).isDisplayed();
					break;
				case id:
					boolValue= driver.findElement(By.id(value)).isDisplayed();
					break;
				case name:
					boolValue=driver.findElement(By.name(value)).isDisplayed();		
					break;
				default:
					Assert.fail(objFmt);
					break;
				}
				if (boolValue) {
					return boolValue;
				}
			} catch(Exception e){
				TestBase.waitNSec(1);
			}
		}
		return boolValue;
	}

	/**
	 * waitMethods.waitForElementEnable.
	 * Returns the 
	 * @param object
	 * @return
	 */
	public boolean waitForElementEnable(String object){
		boolValue = false;
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
		for(int cnti=0; cnti<10; cnti++)  {  
			try{
				switch (locators) {
				case link:
					boolValue =	driver.findElement(By.linkText(objR.getProperty(object))).isEnabled();
					break;
				case xpath:
					boolValue	= driver.findElement(By.xpath(objR.getProperty(object))).isEnabled();
					break;
				case css:
					boolValue= driver.findElement(By.cssSelector(objR.getProperty(object))).isEnabled();
					break;
				case id:
					boolValue= driver.findElement(By.id(objR.getProperty(object))).isEnabled();
					break;
				case name:
					boolValue=driver.findElement(By.name(objR.getProperty(object))).isEnabled();		
					break;
				default:
					Assert.fail(objFmt);
					break;
				}
				if (boolValue) {
					TestBase.waitNSec(1);
					return boolValue;
				} else {
					TestBase.waitNSec(1);
				}
			} catch(Exception e){
				TestBase.waitNSec(1);
			}
		}
		TestBase.waitNSec(1);
		return boolValue;
	}

	/**
	 * waitMethods.waitForElementDisable.
	 * Returns the 
	 * @param object
	 * @return
	 */
	public boolean waitForElementDisable(String object){
		boolValue = false;
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
		for(int cnti=0; cnti<10; cnti++)  {  
			try{
				switch (locators) {
				case link:
					boolValue =	!(driver.findElement(By.linkText(objR.getProperty(object))).isEnabled());
					break;
				case xpath:
					boolValue	= !(driver.findElement(By.xpath(objR.getProperty(object))).isEnabled());
					break;
				case css:
					boolValue= !(driver.findElement(By.cssSelector(objR.getProperty(object))).isEnabled());
					break;
				case id:
					boolValue= !(driver.findElement(By.id(objR.getProperty(object))).isEnabled());
					break;
				case name:
					boolValue=!(driver.findElement(By.name(objR.getProperty(object))).isEnabled());		
					break;
				default:
					Assert.fail(objFmt);
					break;
				}
				if (boolValue) {
					return boolValue;
				} else {
					TestBase.waitNSec(1);
				}
			} catch(Exception e){
				TestBase.waitNSec(1);
			}
		}
		return boolValue;
	}

	/**
	 * isElementPresent checks if the element is present in the application
	 * Returns the 
	 * @param object
	 * @return x
	 */
	public boolean isElementPresent(String  object) {
		boolValue = false;
		Locators  locators = Locators.valueOf(object.substring(object.lastIndexOf('_')+1));
		try{
			switch (locators) {
			case link:
				driver.findElement(By.linkText(objR.getProperty(object)));
				boolValue=true;
				break;
			case xpath:
				driver.findElement(By.xpath(objR.getProperty(object)));
				boolValue=true;
				break;
			case css:
				driver.findElement(By.cssSelector(objR.getProperty(object)));
				boolValue=true;
				break;
			case id:
				driver.findElement(By.id(objR.getProperty(object)));
				boolValue=true;
				break;
			case name:
				driver.findElement(By.name(objR.getProperty(object)));
				boolValue=true;	
				break;
			default:
				Assert.fail(objFmt);
				break;
			}
			return boolValue;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}

	
}
