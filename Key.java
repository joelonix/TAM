package com.ingenico.tam.objects;

/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/Key.java $
 $Id: Key.java 14253 2015-07-21 11:49:41Z amsingh $
 */

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.ingenico.base.TestBase;

public class Key extends TestBase {

	public static String addKey = taskTypes[1],
			keyLbl = "Key label",
			key = "key",
			injecModePinpad = "Pinpad",
			key1 = "key1",
			key2 = "key2",
			keyAdded = "Key added: key",
			key1Added = "Key added: key1",
			injMdTerIfNoPinpd = "Terminal if no pinpad",
			injcmode = "Injection mode",
			cancleKey = "Cancel key",
			canTrmKeyMsg = "Do you confirm the cancelation of the key for the current terminal?",
			keyMngmnt = "Key Management";

	public static String[] stchElmsOfPinpad = { "injectionMode", "pinpadToAdd",
			"pinpadTaskPriority", "dialogBoxAddKeyTo_0", "dialogBoxAddKeyTo_1" },
			stchElmsOfTerms = { "injectionMode", "keyToAdd", "taskPriority",
					"dialogBoxAddKeyTo_0", "dialogBoxAddKeyTo_1" },
			injectionmodalist = { Terminals.terminal, "Pinpad",
					"Terminal if no pinpad" },
			keyMngmentHdrs = { "Active", "Wait second call", "Pending", delVal,
					CallScheduling.callStatuses[1] },
			keyMngmentActHdrs = { "Key Label", statusDt, injcmode,
					Jobs.jobsTabcolsHds[4] },
			keyMngNtFocdTabs = { keyMngmentHdrs[1], delVal, keyMngmentHdrs[3],
					keyMngmentHdrs[4] };

	/**Click on the Drop-down list icon of the 'Injection mode' field 
	 * and choose 'Pinpad'. Verify, the expected results. */
	public void verifyInjectionmodeItemPinpadInfo(String browser) {
		selUtils.selectItem(selUtils.getObject("job_addkey_injectionmode_id"),
				injecModePinpad);
		Assert.assertTrue(
				selUtils.getSelectedItem(
						selUtils.getObject("job_addkey_injectionmode_id"))
						.equals(injecModePinpad),
				"'Injection Mode' field is not set by default to '"
						+ injecModePinpad + "'.");
		logger.info("Verified, 'Injection Mode' field is set by default to '"
				+ injecModePinpad + "'.");

		Assert.assertTrue(selUtils.getObject("job_addkey_pinpadlabel_id")
				.isEnabled(), "Pinpad label field is not editable.");
		Assert.assertTrue(selUtils.getObject("addkey_pinpad_task_id")
				.getAttribute("value").equals(priority100),
				"Pinpad label priority field is not set by default to '"
						+ priority100 + "'.");
		Assert.assertTrue(selUtils.getObject("addkey_pinpad_task_id")
				.isEnabled(), "Pinpad label priority field is not editable.");
		logger.info("Verified, Pinpad priority field is editable and set by default to '"
				+ priority100 + "'.");

		Assert.assertTrue(selUtils.getObject("job_addkey_keylabel_id")
				.getAttribute("disabled").equals("true"),
				"Key label field is editable.");
		if (browser.equalsIgnoreCase("firefox")) {
			Assert.assertTrue(selUtils.getObject("job_addkey_keylabel_id")
					.getAttribute("style").contains("rgb(221, 221, 221)"),
					"Key label field is not grayed.");
		}
		if (browser.equalsIgnoreCase("internet explorer")) {
			Assert.assertTrue(selUtils.getObject("job_addkey_keylabel_id")
					.getAttribute("style").contains("#dddddd"),
					"Key label field is not grayed.");
		}
		Assert.assertTrue(selUtils.getObject("job_addkey_key_pri_css")
				.getAttribute("value").equals(priority100),
				"Key label priority field is not set by default to '"
						+ priority100 + "'.");
		Assert.assertTrue(selUtils.getObject("job_addkey_key_pri_css")
				.getAttribute("disabled").equals("true"),
				"Key label priority field is editable.");
		if (browser.equalsIgnoreCase("firefox")) {
			Assert.assertTrue(selUtils.getObject("job_addkey_key_pri_css")
					.getAttribute("style").contains("rgb(221, 221, 221)"),
					"Key label field is not grayed.");
		}
		if (browser.equalsIgnoreCase("internet explorer")) {
			Assert.assertTrue(selUtils.getObject("job_addkey_key_pri_css")
					.getAttribute("style").contains("#dddddd"),
					"Key label field is not grayed.");
		}
		logger.info("Verified, Key label field is grayed, not editable and set by default to '"
				+ priority100 + "'.");
	}

	/**
	 * Click on the Drop-down list icon of the 'Injection mode' field. Verify,
	 * drop-down list is opened showing three choices: 'Pinpad', 'Terminal',
	 * 'Terminal if no pinpad'
	 * */
	public void vInjecModeDrpDn(String drpDnLoc, String[] injecmodalst) {
		String injecmodalstStr = "", injModDrpDnLstStr = "";
		List<WebElement> injecModeDrpDnLst = selUtils.getObjects(drpDnLoc);
		for (itemCount = 0; itemCount < injecModeDrpDnLst.size(); itemCount++) {
			injModDrpDnLstStr = (injModDrpDnLstStr + " " + injecModeDrpDnLst
					.get(itemCount).getText()).trim();
		}
		for (itemCount = 0; itemCount < injecmodalst.length; itemCount++) {
			injecmodalstStr = injecmodalstStr + " " + injecmodalst[itemCount];
			Assert.assertTrue(
					injModDrpDnLstStr.contains(injecmodalst[itemCount]),
					"drop-down list is not displayed as expected.");
		}
		logger.info("Verified, drop-down list is showing three choices: '"
				+ injecmodalstStr + "'.");
	}

	/**
	 * Verify, Add key functionality for listed invalid key lable priority :
	 * 'aA&%\<>'.
	 */
	public void vKeyNegFuncWithInvalVals(String inValValues,
			String expInjecMode, String keyLbl) {
		char[] inValValuesArry = inValValues.toCharArray();
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(getCommonPath("btn_spinner_xpath"))));
		for (itemCount = 0; itemCount < inValValuesArry.length; itemCount++) {
			waitMethods.waitForElementDisplayed("add_key_xpath");
			String expVal = String.valueOf(inValValuesArry[itemCount]);
			verifyExpWinDisp("add_key_xpath", addKey);
			selUtils.selectItem(
					selUtils.getObject("job_addkey_injectionmode_id"),
					expInjecMode);
			selUtils.vDrpDnSelcItem("job_addkey_injectionmode_id", expInjecMode);
			selUtils.selectItem(selUtils.getObject("job_addkey_keylabel_id"),
					keyLbl);
			selUtils.vDrpDnSelcItem("job_addkey_keylabel_id", keyLbl);
			selUtils.populateInputBox("key_tsk_prio_css", expVal);
			logger.info("Verified, 'Task Priority' field value set: " + expVal);
			confrmPopupActionMsg("addkey_add_butt_id",
					"key_err_msg_with_icon_xpath", priorLmtErMsg);
			vMsgAndFldAndLblInRedClr("key_err_msg_with_icon_xpath",
					"key_tsk_prio_css", "key_tsk_prio_lbl_css");
			verifySpecifiedWinNotDisp("addkey_cls_butt_id");
		}
	}

	/**
	 * Verify, the 'Key Label' field becomes gray and not editable and its
	 * associated 'Priority' field becomes gray and not editable. and the
	 * 'Pinpad Label' field becomes editable and its associated 'Priority' field
	 * becomes editable.
	 **/
	public void vKeyAndPnPadFldsClrAndEditable(String browser) {
		/*
		 * Verify, the 'Key Label' field becomes gray and not editable and its
		 * associated 'Priority' field becomes gray and not editable.
		 */
		if (browser.equalsIgnoreCase("firefox")) {
			Assert.assertTrue(selUtils.getObject("job_addkey_keylabel_id")
					.getAttribute("style").contains("rgb(221, 221, 221)"),
					"Key label field is not grayed.");
			Assert.assertTrue(selUtils.getObject("key_tsk_prio_css")
					.getAttribute("style").contains("rgb(221, 221, 221)"),
					"Key label field is not grayed.");
		}
		if (browser.equalsIgnoreCase("internet explorer")) {
			Assert.assertTrue(selUtils.getObject("job_addkey_keylabel_id")
					.getAttribute("style").contains("#dddddd"),
					"Key label field is not grayed.");
			Assert.assertTrue(selUtils.getObject("key_tsk_prio_css")
					.getAttribute("style").contains("#dddddd"),
					"Key label field is not grayed.");
		}
		Assert.assertTrue(selUtils.getObject("job_addkey_keylabel_id")
				.getAttribute("disabled").equals("true"),
				"Key label field is editable.");
		Assert.assertTrue(
				selUtils.getObject("key_tsk_prio_css").getAttribute("disabled")
						.equals("true"),
				"Key label priority field is editable.");
		logger.info("Verified, 'Key label', associated 'Priority' fields are displayed in gray color, and not editable.");

		// Verify, the 'Pinpad Label' field becomes editable and its associated
		// 'Priority' field becomes editable.
		Assert.assertTrue(selUtils.getObject("job_addkey_pinpadlabel_id")
				.isEnabled(), "Pinpad label field is not editable.");
		Assert.assertTrue(selUtils.getObject("addkey_pinpad_task_id")
				.isEnabled(), "Pinpad label priority field is not editable.");
		logger.info("Verified, 'Pinpad label', and 'Priority' fields are editable.");
	}

	/**
	 * Verify, Add key functionality for listed invalid pinpad lable priority :
	 * 'aA&%\<>'.
	 */
	public void vKeyNegFuncWithPnPdInvalVals(String inValValues,
			String expInjecMode, String keyLbl, String browser) {
		char[] inValValuesArry = inValValues.toCharArray();
		for (itemCount = 0; itemCount < inValValuesArry.length; itemCount++) {
			waitMethods.waitForElementDisplayed("add_key_xpath");
			String expVal = String.valueOf(inValValuesArry[itemCount]);
			verifyExpWinDisp("add_key_xpath", addKey);
			selUtils.selectItem(
					selUtils.getObject("job_addkey_injectionmode_id"),
					expInjecMode);
			selUtils.vDrpDnSelcItem("job_addkey_injectionmode_id", expInjecMode);
			vKeyAndPnPadFldsClrAndEditable(browser);
			selUtils.selectItem(
					selUtils.getObject("job_addkey_pinpadlabel_id"), keyLbl);
			selUtils.vDrpDnSelcItem("job_addkey_pinpadlabel_id", keyLbl);
			selUtils.populateInputBox("addkey_pinpad_task_id", expVal);
			logger.info("Verified, 'Task Priority' field value set: " + expVal);
			confrmPopupActionMsg("addkey_add_butt_id",
					"key_err_msg_with_icon_xpath", priorLmtErMsg);
			vMsgAndFldAndLblInRedClr("key_err_msg_with_icon_xpath",
					"addkey_pinpad_task_id", "key_pnpad_prio_lbl_id");
			verifySpecifiedWinNotDisp("addkey_cls_butt_id");
		}
	}

	/**
	 * Click on the 'Add' button of the modal window. Verify the modal window
	 * displays the message: 'Processing'. Then it displays a list of terminals
	 * Serial Numbers all starting with the pattern '110' in a section called
	 * 'Successful'.
	 */
	public void verifyAddKeyAddFunctionality() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		selUtils.clickOnWebElement("addkey_add_butt_id", addButt);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(
				By.id(selUtils.getPath("addkey_add_processmsg_id")),
				processingMsg));
		List<WebElement> successListItems = selUtils
				.getObjects("addscenario_success_listitems_css");
		if (successListItems.size() > 0) {
			int cnt = 0;
			for (itemCount = 0; itemCount < successListItems.size(); itemCount++) {
				if (successListItems.get(itemCount).getText().replace("- ", "")
						.startsWith("110")) {
					cnt++;
				}
			}
			if (cnt > 0) {
				logger.info(" List of terminals Serial Numbers at least starting with the pattern '110' ");
			} else {
				Assert.fail(" List of terminals Serial Numbers not starting with the pattern '110' ");
			}
			logger.info("Verified, terminal Serial Numbers at least starting with the pattern '110' in a section called 'Successful'.");
		} else {
			logger.error("Terminals are not displayed in a section called 'Successful'.");
			Assert.fail("Terminals are not displayed in a section called 'Successful'.");
		}
	}

	/**
	 * Click on the 'Pinpad Label' Drop-down list icon and choose 'key'.and
	 * Verify, the field is set to 'key'
	 */
	public void selectPinpadLabel() {
		selUtils.selectItem(selUtils.getObject("job_addkey_pinpadlabel_id"),
				key1);
		selUtils.vDrpDnSelcItem("job_addkey_pinpadlabel_id", key1);
	}

	/**
	 *verify Add key  window information
	 */
	public void vAddKeyWindInfo(String injectionModeItem, String browser) {
		vDrpDnDefltSelcItem("job_addkey_injectionmode_id", injectionModeItem,
				injcmode);
		vPnPadAndKeyFldsClrAndEditable(browser);
		Assert.assertTrue(selUtils.getObject("job_addkey_key_pri_css")
				.getAttribute("value").equals(priority100),
				"Key label priority field is not set by default to '"
						+ priority100 + "'.");
		logger.info("Verified, Priority field is set by default to '"
				+ priority100 + "'.");
		Assert.assertTrue(selUtils.getObject("addkey_pinpad_task_id")
				.getAttribute("value").equals(priority100),
				"Pinpad label priority field is not set by default to '100'.");
		logger.info("Verified, Pinpad label field is set by default to '"
				+ priority100 + "'.");
		selUtils.verifyElementDisp("addkey_add_butt_id", addButt);
		selUtils.verifyElementDisp("addkey_cls_butt_id", closeButton);
	}

	/**
	 * Verify, the 'Key Label' field becomes editable and its associated
	 * 'Priority' field becomes editable. The 'Pinpad Label' field becomes gray
	 * and not editable and its associated 'Priority' field becomes gray and not
	 * editable.
	 **/
	public void vPnPadAndKeyFldsClrAndEditable(String browser) {
		Assert.assertTrue(selUtils.getObject("job_addkey_keylabel_id")
				.isEnabled(), "'Keylabel' field is not editable.");
		Assert.assertTrue(selUtils.getObject("job_addkey_key_pri_css")
				.isEnabled(), "Key label priority field is not editable.");
		logger.info("Verified, 'Keylabel', and its associated 'Priority' fields are editable.");

		Assert.assertTrue(selUtils.getObject("job_addkey_pinpadlabel_id")
				.getAttribute("disabled").equals("true"),
				"Pinpad label field is editable.");
		if (browser.equalsIgnoreCase("firefox")) {
			Assert.assertTrue(selUtils.getObject("job_addkey_pinpadlabel_id")
					.getAttribute("style").contains("rgb(221, 221, 221)"),
					"Pinpad label field is not grayed.");
			Assert.assertTrue(selUtils.getObject("addkey_pinpad_task_id")
					.getAttribute("style").contains("rgb(221, 221, 221)"),
					"Pinpad label field is not grayed.");
		}
		if (browser.equalsIgnoreCase("internet explorer")) {
			Assert.assertTrue(selUtils.getObject("job_addkey_pinpadlabel_id")
					.getAttribute("style").contains("#dddddd"),
					"Pinpad label field is not grayed.");
			Assert.assertTrue(selUtils.getObject("addkey_pinpad_task_id")
					.getAttribute("style").contains("#dddddd"),
					"Pinpad label field is not grayed.");
		}
		Assert.assertTrue(selUtils.getObject("addkey_pinpad_task_id")
				.getAttribute("disabled").equals("true"),
				"Pinpad label priority field is editable.");
		logger.info("Verified, 'Pinpad Label', and its associated 'Priority' field are displyed in gray color, and not editable.");
	}

	/**Click on the 'Key management' tab and then on the 'Pending' one
	 *under it. The 'Pending' tab gets in focus showing at least the
	 *'key1' key in the table with the expected values. */
	public void vkeyMgmtInPrgceExpColsData(String keyName, String injMode) {
		vkeyMgmtInPrgceExpkeyDis(keyName);
		selUtils.vExpIcon("trm_edit_key_term_icon_xpath", keyName,
				Terminals.terminal);
		vDateCol("trm_edit_key_mgmt_date_xpath", keyName);
		selUtils.vColVal("trm_edit_key_mgmt_inj_xpath", keyName, injMode);
		selUtils.vExpIcon("trm_edit_key_mgmt_cnslt_job_icon_xpath", keyName,
				Jobs.cnsltJobPrgceIcn);
		selUtils.vExpIcon("trm_edit_key_mgmt_cancel_icon_xpath", keyName,
				cancel);
	}

	/**
	 * Click on the 'Key management' tab and then on the 'Pending' one under it
	 * The 'Pending' tab gets in focus showing at least the expected key in the
	 * table
	 * 
	 * @param keyName
	 */
	public void vkeyMgmtInPrgceExpkeyDis(String keyName) {
		vkeyMgmtIPendingPg();
		selUtils.waitForTxtPresent("keymangmnt_pending_xpath", numOfEleText);
		selUtils.vExpValPresent("trm_edit_key_mgmt_name_xpath", nameLbl,
				keyName, keyName);
	}

	/**
	 * Click on the 'Key management' tab and then on the 'Pending' one under it
	 * The 'Pending' tab gets in focus.
	 * 
	 * @param keyName
	 */
	public void vkeyMgmtIPendingPg() {
		selUtils.clickOnWebElement("trm_key_mgmt_link", keyMngmnt);
		selUtils.clickOnWebElement("pending_link", keyMngmentHdrs[2]);
		waitMethods.waitForElementFocus(selUtils
				.getObject("key_mgmt_pending_xpath"));
		selUtils.vExpTabInFocus("key_mgmt_pending_xpath");
	}

	/**
	 * Click on the 'Key management' tab and then on the 'Deleted' one under it
	 * The 'Deleted' tab is in focus showing at least the 'key1' field in the
	 * table
	 */
	public void vkeyMgmtDeleTabExpkeyDis(String keyName) {
		selUtils.clickOnWebElement("trm_key_mgmt_link", keyMngmnt);
		selUtils.getObject("deleted_link").click();
		logger.info("Clicked on '" + keyMngmnt + "', and '" + keyMngmentHdrs[3]
				+ "' tabs.");
		selUtils.vExpTabInFocus("key_mgmt_del_xpath");
		selUtils.waitForTxtPresent("pak_inprog_footer_css", numOfEleText);
		selUtils.vExpValPresent("trm_edit_key_mgmt_del_name_xpath", nameLbl,
				Key.key1, Key.key1);
	}
}