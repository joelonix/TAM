package com.ingenico.tam.objects;

/*
 $HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/CallScheduling.java $
 $Id: CallScheduling.java 14097 2015-07-10 06:26:34Z vkrachuri $
 */

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ingenico.base.TestBase;

/**
 * CallScheduling Class - Consists of methods related to CallScheduling module
 */
public class CallScheduling extends TestBase {
	public final static String CALLSCHD = "Call scheduling",
			LOADBALLBL = "Load Balancing",
			ENBLSCHDLBL = "Enable Scheduling",
			SCHDPARAMSLBL = "Scheduling parameters",
			CALLSCHSUCCMSG = "Call scheduling data have been added to terminal(s) or estate",
			TIMEZNADJLBL = "Time Zone Adjustement",
			TOLBL = "to",
			REJCALLLBL = "Reject Calls Outside",
			INVLDFREQ = "Invalid frequency supplied - must be in range 1 to 365",
			PROFROMPARLBL = "Protect from Parent Updates",
			WINFROMLBL = "Window from", FRCDTLBL = "Force Date",
			CLNDR = "Calendar", FREQLBL = "Frequency",
			CLOCKTOOLTIP = "Select a time (HH:MM)",
			SHWPREVMNTH = "Show the previous month",
			SHWDIFFYEAR = "Show a different year",
			SHWNXTMNTH = "Show the next month",
			CPCALLSCHDVAL = "Copy the call scheduling value",
			YEAR2020 = "2020", DAY15 = "15";

	public String frqVal = "", timeZnAdjVal = "", frStartHr = "",
			frStartMin = "", toEndHr = "", toEndMin = "";

	public static String[] callStatuses = { "Disconnected", failed, "Rejected",
			success, "Unknown" }, callTabHdrs = { callStatus, callStrtDt,
			callEndtDt, "Part Number", "Serial Number", "Model", actions };

	/**
	 * Click on the 'Call scheduling' button of the 'Terminals config.' buttons
	 * section. Verify 'Call scheduling' modal window is displayed containing:
	 * -An 'Enable Scheduling' field, -A 'Load Balancing' field, -A 'Protect
	 * from Parent Updates' field, -A 'Scheduling parameters' section containing
	 * -A 'Force Date' field with an associated calendar field, -A 'Frequency'
	 * field in 'days'-A 'Time Zone Adjustment' field in 'minutes', -A 'Window
	 * from' field and a 'to' field, -A 'Reject Calls Outside' field, -A 'Save'
	 * and a 'Close' button.
	 **/
	public void vCallSchdFldsAvailability() {
		verifyExpWinDisp("cl_schdlng_link", CALLSCHD);
		// containing
		Assert.assertTrue(selUtils.getObject("enbl_sched_xpath").isDisplayed(),
				"'" + ENBLSCHDLBL + "' field is not displayed.");
		logger.info("Verified, '" + ENBLSCHDLBL + "' field is displayed.");

		Assert.assertTrue(selUtils.getObject("load_bal_xpath").isDisplayed(),
				"'" + LOADBALLBL + "' field is not displayed.");
		logger.info("Verified, '" + LOADBALLBL + "' field is displayed.");

		Assert.assertTrue(selUtils.getObject("protc_parent_xpath")
				.isDisplayed(), "'" + PROFROMPARLBL
				+ "' field is not displayed.");
		logger.info("Verified, '" + PROFROMPARLBL + "' field is displayed.");

		Assert.assertTrue(selUtils.getObject("calsch_param_xpath").getText()
				.contains(SCHDPARAMSLBL));
		logger.info(" Verified that " + SCHDPARAMSLBL + " contains: ");

		Assert.assertTrue(selUtils.getObject("force_dt_with_chkbx_xpath")
				.isDisplayed(), "'" + FRCDTLBL
				+ "' field with 'check box' is not displayed.");
		Assert.assertTrue(selUtils.getObject("force_dt_with_clndr_xpath")
				.isDisplayed(), "'" + FRCDTLBL
				+ "' field with 'calendar' is not displayed.");
		logger.info("Verified, '" + FRCDTLBL
				+ "' field with 'check box' and 'calendar' is displayed.");

		Assert.assertTrue(selUtils.getObject("freq_withdays_xpath")
				.isDisplayed(), "'" + FREQLBL + "' field in '" + daysLbl
				+ "' is not displayed.");
		logger.info("Verified, '" + FREQLBL + "' field in '" + daysLbl
				+ "' is displayed.");

		Assert.assertTrue(selUtils.getObject("timznadjs_withmins_xpath")
				.isDisplayed(), "'" + TIMEZNADJLBL + "' field in '" + minsLbl
				+ "' is not displayed.");
		logger.info("Verified, '" + TIMEZNADJLBL + "' field in '" + minsLbl
				+ "' is displayed.");

		Assert.assertTrue(selUtils.getObject("windfrm_date_xpath")
				.isDisplayed(), "'" + WINFROMLBL
				+ "' date field is not displayed.");
		logger.info("Verified, '" + WINFROMLBL + "' date field is displayed.");

		Assert.assertTrue(selUtils.getObject("to_date_xpath").isDisplayed(),
				"'" + TOLBL + "' date field is not displayed.");
		logger.info("Verified, '" + TOLBL + "' date field is displayed.");

		Assert.assertTrue(selUtils.getObject("rej_calls_outside_xpath")
				.isDisplayed(), "'" + REJCALLLBL + "' field is not displayed.");
		logger.info("Verified, '" + REJCALLLBL + "' field is displayed.");

		selUtils.verifyElementDisp("save_callsch_id", saveButton);
		selUtils.verifyElementDisp("cls_callsch_id", closeButton);
	}

	/**
	 * If not ticked off, tick off the 'Enable Scheduling' check box. Verify,
	 * the 'Enable Scheduling' check box is ticked off. The 'Load Balancing'
	 * field is grayed and not editable.The 'Protect from Parent Updates' field
	 * is editable. The 'Force Date' field is grayed and not editable. The
	 * calendar field is grayed and not editable. The 'Frequency' field is
	 * grayed and not editable. The 'Time Zone Adjustment' field is grayed and
	 * not editable. The 'Window from' field is grayed and not editable. The
	 * 'to' field is grayed and not editable. The 'Reject Calls Outside' field
	 * is grayed and not editable.
	 **/
	public void vUnchkedEnblSchdDtls(String browser) {
		// The 'Enable Scheduling' check box is ticked off.
		vExpChkBxFunc("schenabled_name", checkNo, unCheck, ENBLSCHDLBL);

		if ("firefox".equalsIgnoreCase(browser)) {
			Assert.assertTrue(selUtils.getObject("load_bal_chk_name")
					.getAttribute("style").contains("rgb(221, 221, 221)"), "'"
					+ LOADBALLBL + "' check box is not having gray color.");
			Assert.assertTrue(selUtils.getObject("force_dt_chkbx_name")
					.getAttribute("style").contains("rgb(221, 221, 221)"), "'"
					+ FRCDTLBL + "' check box is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("frequency_id").getAttribute("style")
							.contains("rgb(221, 221, 221)"), "'" + FREQLBL
							+ "' text box is not having gray color.");
			Assert.assertTrue(selUtils.getObject("timezone_adj_id")
					.getAttribute("style").contains("rgb(221, 221, 221)"), "'"
					+ TIMEZNADJLBL + "' text box is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("start_hour_name").getAttribute("style")
							.contains("rgb(221, 221, 221)")
							&& selUtils.getObject("start_min_name")
									.getAttribute("style")
									.contains("rgb(221, 221, 221)"), "'"
							+ WINFROMLBL + "' date is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("end_hour_name").getAttribute("style")
							.contains("rgb(221, 221, 221)")
							&& selUtils.getObject("end_min_name")
									.getAttribute("style")
									.contains("rgb(221, 221, 221)"), "'"
							+ TOLBL + "' date is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("rej_call_name").getAttribute("style")
							.contains("rgb(221, 221, 221)"), "'" + REJCALLLBL
							+ "' check box is not having gray color.");
		}

		if ("internet explorer".equalsIgnoreCase(browser)) {
			Assert.assertTrue(selUtils.getObject("load_bal_chk_name")
					.getAttribute("style").contains("#dddddd"), "'"
					+ LOADBALLBL + "' check box is not having gray color.");
			Assert.assertTrue(selUtils.getObject("force_dt_chkbx_name")
					.getAttribute("style").contains("#dddddd"), "'" + FRCDTLBL
					+ "' check box is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("frequency_id").getAttribute("style")
							.contains("#dddddd"), "'" + FREQLBL
							+ "' text box is not having gray color.");
			Assert.assertTrue(selUtils.getObject("timezone_adj_id")
					.getAttribute("style").contains("#dddddd"), "'"
					+ TIMEZNADJLBL + "' text box is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("start_hour_name").getAttribute("style")
							.contains("#dddddd")
							&& selUtils.getObject("start_min_name")
									.getAttribute("style").contains("#dddddd"),
					"'" + WINFROMLBL + "' date is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("end_hour_name").getAttribute("style")
							.contains("#dddddd")
							&& selUtils.getObject("end_min_name")
									.getAttribute("style").contains("#dddddd"),
					"'" + TOLBL + "' date is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("rej_call_name").getAttribute("style")
							.contains("#dddddd"), "'" + REJCALLLBL
							+ "' check box is not having gray color.");
		}
		// The 'Load Balancing' field is grayed and not editable
		Assert.assertFalse(selUtils.getObject("load_bal_chk_name").isEnabled(),
				"'" + LOADBALLBL + "' check box is editable.");
		logger.info("Verified, '" + LOADBALLBL
				+ "' check box is having gray color, and not editable.");
		// The 'Protect from Parent Updates' field is editable.
		Assert.assertTrue(selUtils.getObject("pr_parent_upd_chk_name")
				.isEnabled(), "'" + PROFROMPARLBL
				+ "' check box is not editable.");
		logger.info("Verified, '" + PROFROMPARLBL + "' check box is editable.");
		// The 'Force Date' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("force_dt_chkbx_name")
				.isEnabled(), "'" + FRCDTLBL + "' check box is editable.");
		Assert.assertFalse(selUtils.getObject("frc_dt_clndr_inpbx_id")
				.isEnabled(), "'" + FRCDTLBL
				+ "' calendar input box is editable.");
		Assert.assertFalse(selUtils.getObject("frc_dt_clndr_icon_css")
				.getAttribute("title").equals("Click to select a date"), "'"
				+ FRCDTLBL + "' calendar icon is editable.");
		logger.info("Verified, '"
				+ FRCDTLBL
				+ "' check box, calendar are displayed in gray color, and not editable.");
		// The 'Frequency' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("frequency_id").isEnabled(), "'"
				+ FREQLBL + "' text box is editable.");
		logger.info("Verified, '" + FREQLBL
				+ "' text box is having gray color, and not editable.");
		// The 'Timezone Adjustement' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("timezone_adj_id").isEnabled(),
				"'" + TIMEZNADJLBL + "' text box is editable.");
		logger.info("Verified, '" + TIMEZNADJLBL
				+ "' text box is having gray color, and not editable.");
		// The 'Window from' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("start_hour_name").isEnabled()
				&& selUtils.getObject("start_min_name").isEnabled(), "'"
				+ WINFROMLBL + "' date is editable.");
		logger.info("Verified, '" + WINFROMLBL
				+ "' date is having gray color, and not editable.");
		// The 'to' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("end_hour_name").isEnabled()
				&& selUtils.getObject("end_min_name").isEnabled(), "'" + TOLBL
				+ "' date is editable.");
		logger.info("Verified, '" + TOLBL
				+ "' date is having gray color, and not editable.");
		// The 'Reject Calls Outside' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("rej_call_name").isEnabled(), "'"
				+ REJCALLLBL + "' check box is editable.");
		logger.info("Verified, '" + REJCALLLBL
				+ "' check box is having gray color, and not editable.");
	}

	/**
	 * Tick the 'Enable Scheduling' check box and if not ticked off tick off the
	 * 'Force Date' field. Verify, The 'Enable Scheduling' check box is ticked.
	 * The 'Load Balancing' field is editable. The 'Protect from Parent Updates'
	 * field is editable. The 'Force Date' field is editable.The calendar field
	 * is grayed and not editable. The 'Frequency' field is editable. The 'Time
	 * Zone Adjustment' field is editable. The 'Window from' field is
	 * editable.The 'to' field is editable. The 'Reject Calls Outside' field is
	 * editable.
	 **/
	public void vChkedEnblSchdDtls() {
		// The 'Enable Scheduling' check box is ticked.
		vExpChkBxFunc("schenabled_name", yes, check, ENBLSCHDLBL);
		// The 'Force Date' check box is ticked off.
		vExpChkBxFunc("force_dt_chkbx_name", checkNo, unCheck, FRCDTLBL);
		// The 'Load Balancing' field is editable.
		Assert.assertTrue(selUtils.getObject("load_bal_chk_name").isEnabled(),
				"'" + LOADBALLBL + "' check box is not editable.");
		logger.info("Verified, '" + LOADBALLBL + "' check box is editable.");
		// The 'Protect from Parent Updates' field is editable.
		Assert.assertTrue(selUtils.getObject("pr_parent_upd_chk_name")
				.isEnabled(), "'" + PROFROMPARLBL
				+ "' check box is not editable.");
		logger.info("Verified, '" + PROFROMPARLBL + "' check box is editable.");
		// The 'Force Date' field is editable.
		Assert.assertTrue(
				selUtils.getObject("force_dt_chkbx_name").isEnabled(), "'"
						+ FRCDTLBL + "' check box is not editable.");
		logger.info("Verified, '" + FRCDTLBL + "' check box is editable.");
		// The calendar field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("frc_dt_clndr_inpbx_id")
				.isEnabled(), "'" + FRCDTLBL
				+ "' calendar input box is editable.");
		Assert.assertFalse(selUtils.getObject("frc_dt_clndr_icon_css")
				.getAttribute("title").equals("Click to select a date"), "'"
				+ FRCDTLBL + "' calendar icon is editable.");
		logger.info("Verified, '" + FRCDTLBL
				+ "' calendar is displayed in gray color, and not editable.");
		// The 'Frequency' field is editable.
		Assert.assertTrue(selUtils.getObject("frequency_id").isEnabled(), "'"
				+ FREQLBL + "' text box is not editable.");
		logger.info("Verified, '" + FREQLBL + "' text box is editable.");
		// The 'Timezone Adjustement' field is editable.
		Assert.assertTrue(selUtils.getObject("timezone_adj_id").isEnabled(),
				"'" + TIMEZNADJLBL + "' text box is not editable.");
		logger.info("Verified, '" + TIMEZNADJLBL + "' text box is editable.");
		// The 'Window from' field is editable.
		Assert.assertTrue(selUtils.getObject("start_hour_name").isEnabled()
				&& selUtils.getObject("start_min_name").isEnabled(), "'"
				+ WINFROMLBL + "' date is not editable.");
		logger.info("Verified, '" + WINFROMLBL + "' date is editable.");
		// The 'to' field is editable.
		Assert.assertTrue(selUtils.getObject("end_hour_name").isEnabled()
				&& selUtils.getObject("end_min_name").isEnabled(), "'" + TOLBL
				+ "' date is not editable.");
		logger.info("Verified, '" + TOLBL + "' date is editable.");
		// The 'Reject Calls Outside' field is editable.
		Assert.assertTrue(selUtils.getObject("rej_call_name").isEnabled(), "'"
				+ REJCALLLBL + "' check box is not editable.");
		logger.info("Verified, '" + REJCALLLBL + "' check box is editable.");
	}

	/**
	 * Click on the 'Calendar' icon associated to the expected field. Verify, A
	 * calendar appears containing:-A 'Show the previous month' button, -A month
	 * drop down list field with all the months of the year,-A year field, -A
	 * 'Show the next month' button, -A 'Clear' button. Click on the 'Clear'
	 * button of the calendar.Verify, calendar disappears and the field is blank
	 **/
	public void vFrcDtClndrInfo(String clndrIcnLoc, String clndrLoc,
			String prevMnthLoc, String mnthsLstLoc, String yearFldLoc,
			String shwNxtMnthButtLoc, String clrButtLoc) {
		vDtClndrInfo(clndrIcnLoc, clndrLoc, prevMnthLoc, mnthsLstLoc,
				yearFldLoc, shwNxtMnthButtLoc, clrButtLoc);
		selUtils.getObject(clrButtLoc).click();
		logger.info("Clicked on clear button.");
		// waitMethods.waitForElementNotPresent(clndrLoc);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id(selUtils.getPath(clndrLoc))));
		Assert.assertTrue(selUtils.getObject(clndrLoc).getAttribute("style")
				.contains("none;"), "'" + CLNDR + "' is not disappeared.");
		logger.info("Verified, '" + CLNDR + "' is disappeared.");
	}

	/**
	 * Click on the 'Calendar' icon associated to the expected field. Verify, A
	 * calendar appears containing:-A 'Show the previous month' button not
	 * available for click, -A 'Show the next month' button, -A 'Clear' button.
	 * Click on the 'Clear' button of the calendar. Verify, the calendar
	 * disappears and the field is blank.
	 **/
	public void vClndrInfo(String clndrIcnLoc, String clndrLoc,
			String prevMnthLoc, String shwNxtMnthButtLoc, String clrButtLoc) {
		vDtClndrInfo(clndrIcnLoc, clndrLoc, prevMnthLoc, shwNxtMnthButtLoc,
				clrButtLoc);
		selUtils.getObject(clrButtLoc).click();
		logger.info("Clicked on clear button.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(selUtils.getPath(clndrLoc))));
//		waitMethods.waitForElementNotPresent(clndrLoc);
		Assert.assertTrue(selUtils.getObject(clndrLoc).getAttribute("style")
				.contains("none;"), "'" + CLNDR + "' is not disappeared.");
		logger.info("Verified, '" + CLNDR + "' is disappeared.");
	}

	/**
	 * Click on the 'Calendar' icon associated to the 'Force Date' field.
	 * Verify, A calendar appears containing:-A 'Show the previous month' button
	 * -A month drop down list field with all the months of the year, -A year
	 * field, -A 'Show the next month' button, -A 'Clear' button.
	 **/
	public void vDtClndrInfo(String clndrIcnLoc, String clndrLoc,
			String prevMnthLoc, String mnthsLstLoc, String yearFldLoc,
			String shwNxtMnthButtLoc, String clrButtLoc) {
		selUtils.getObject(clndrIcnLoc).click();
		Assert.assertTrue(selUtils.getObject(clndrLoc).isDisplayed(),
				"expected " + CLNDR + " is not displayed.");
		logger.info("Verified, expected " + CLNDR + " is displayed.");
		waitMethods.waitForElementDisplayed(prevMnthLoc);
		selUtils.vButtDisp(prevMnthLoc, SHWPREVMNTH);
		waitMethods.waitForElementDisplayed(mnthsLstLoc);
		verifyListItems(mnthsLstLoc, months);
		waitMethods.waitForElementDisplayed(yearFldLoc);
		selUtils.verifyElementDisp(yearFldLoc, year);
		waitMethods.waitForElementDisplayed(shwNxtMnthButtLoc);
		Assert.assertTrue(waitMethods.isElementPresent(shwNxtMnthButtLoc), "'"
				+ SHWNXTMNTH + "' is displayed.");
		logger.info("Verified, '" + SHWNXTMNTH + "' button is displayed.");
		waitMethods.waitForElementDisplayed(clrButtLoc);
		selUtils.verifyElementDisp(clrButtLoc, clear);
	}

	/**
	 * Click on the 'Calendar' icon associated to the expected field. Verify, A
	 * calendar appears containing: -A 'Show the previous month' button is not
	 * available for click, -A year field, -A 'Show the next month' button, -A
	 * 'Clear' button.
	 **/
	public void vDtClndrInfo(String clndrIcnLoc, String clndrLoc,
			String prevMnthLoc, String shwNxtMnthButtLoc, String clrButtLoc) {
		selUtils.getObject(clndrIcnLoc).click();
		Assert.assertTrue(selUtils.getObject(clndrLoc).isDisplayed(),
				"expected " + CLNDR + " is not displayed.");
		logger.info("Verified, expected " + CLNDR + " is displayed.");
		waitMethods.waitForElementDisplayed(prevMnthLoc);
		final String tagName = selUtils.getObject(prevMnthLoc).getTagName();
		Assert.assertFalse("a".equals(tagName) || "input".equals(tagName)
				|| "button".equals(tagName));
		logger.info("Verified, '" + SHWPREVMNTH
				+ "' button is not available for click.");
		waitMethods.waitForElementDisplayed(shwNxtMnthButtLoc);
		Assert.assertTrue(waitMethods.isElementPresent(shwNxtMnthButtLoc), "'"
				+ SHWNXTMNTH + "' is displayed.");
		logger.info("Verified, '" + SHWNXTMNTH + "' button is displayed.");
		waitMethods.waitForElementDisplayed(clrButtLoc);
		selUtils.verifyElementDisp(clrButtLoc, clear);
	}

	/**
	 * Set 'Call Scheduling' field value
	 */
	public void callSchsetFieldVal(String xpath, String field, String msg,
			String val) {
		selUtils.populateInputBox(xpath, val);
		logger.info(" Set the " + field + " Field Value " + val);
		Assert.assertFalse(selUtils.getObject(xpath).getAttribute("style")
				.contains(redColor), "Field: " + field
				+ " field becomes not red.");
		logger.info(" Verified that " + field + " field becomes Black.");
		Assert.assertFalse(selUtils.getObject("call_sch_errmsg_id").getText()
				.contains(msg));
		logger.info(" Verified that expected error message is not displayed: "
				+ msg);
	}

	/**
	 * Tick off the 'Enable Scheduling check box and click on the 'Save' button
	 * The modal window displays the message: 'Processing'. Then it displays the
	 * message: 'Call scheduling data have been added to terminal(s) or estate'
	 */
	public void callSchsave(String locator, String enblSch, String msg,
			String webEleName) {
		vExpChkBxFunc(locator, enblSch, msg, webEleName);
		verifyCallSchdAfterClkOnSave();
	}

	/**
	 * Click on the 'Save' button. Verify, the modal window displays the
	 * message: 'Processing'. Then it displays msg:'Call scheduling data have
	 * been added to terminal(s) or estate'.
	 */
	public void verifyCallSchdAfterClkOnSave() {
		selUtils.getObject("save_callsch_id").click();
		logger.info(" Clicked on Call Scheduling Save Button");
		selUtils.waitForTxtPresent("callsch_succ_cls_msg_id", CALLSCHSUCCMSG);
		Assert.assertTrue(selUtils.getObject("callsch_succ_cls_msg_id")
				.getText().equals(CALLSCHSUCCMSG),
				"expected successful message '" + CALLSCHSUCCMSG
						+ "' is not displayed.");
		logger.info(" Verified, expected successful message '" + CALLSCHSUCCMSG
				+ "' is displayed.");
	}

	/**
	 * Tick off the 'Enable Scheduling' check box and, click on the 'Save'
	 * button. Verify, the modal window displays the message: 'Processing'. Then
	 * it displays the message: 'Call scheduling data have been added to
	 * terminal(s) or estate' and only a 'Close' button is available at the
	 * bottom of the modal window.
	 */
	public void unchkEnblSchdAndVerifySaveProce() {
		callSchsave("schenabled_name", checkNo, unCheck, ENBLSCHDLBL);
		verifyOnlyCloseButtInSuccWin("callsch_succ_cls_butts_css");
		verifySpecifiedWinNotDisp("callsch_succ_cls_button_id");
	}

	/**
	 * Empty 'Call Scheduling' field value
	 */
	public void callSchEmtField(String xpath, String field, String msg) {
		selUtils.getObject(xpath).clear();
		logger.info(" Clear the " + field + " Field Value");
		selUtils.getObject("save_callsch_id").click();
		logger.info(" Clicked on Call Scheduling Save Button");
		Assert.assertTrue(selUtils.getObject(xpath).getAttribute("style")
				.contains(redColor), "Field: " + field
				+ " field becomes not red.");
		logger.info(" Verified that " + field + " field becomes red.");
		Assert.assertTrue(selUtils.getObject("call_sch_errmsg_id").getText()
				.contains(msg));
		Assert.assertTrue(selUtils.getObject("call_sch_errmsg_id").getCssValue("background-image").contains("rejected.gif"), "Error message is not displayed with error icon.");
		Assert.assertTrue(selUtils.getObject("call_sch_errmsg_id")
				.getAttribute("style").contains(redColor), "Field: " + field
				+ " field becomes not red.");
		logger.info(" Verified that expected message displayed: " + msg);
		/*Assert.assertTrue(selUtils.getObject("callsch_err_icon_css")
				.isDisplayed())*/;
		logger.info(" Verified that error message is displayed with error icon.");
	}

	/**
	 * Set Maximun and Minimun limit of 'Call Scheduling' field value
	 */
	public void callSchLmtField(String xpath, String field, String msg,
			String val) {
		selUtils.populateInputBox(xpath, val);
		logger.info(" Set the " + field + " Field Value " + val);
		selUtils.getObject("save_callsch_id").click();
		logger.info(" Clicked on Call Scheduling Save Button");
		Assert.assertTrue(selUtils.getObject(xpath).getAttribute("style")
				.contains(redColor), "Field: " + field
				+ " field becomes not red.");
		logger.info(" Verified that " + field + " field becomes red.");
		Assert.assertTrue(selUtils.getObject("call_sch_errmsg_id").getText()
				.contains(msg));
		Assert.assertTrue(selUtils.getObject("call_sch_errmsg_id").getCssValue("background-image").contains("rejected.gif"), "Error message is not displayed with error icon.");
		Assert.assertTrue(selUtils.getObject("call_sch_errmsg_id")
				.getAttribute("style").contains(redColor), "Field: " + field
				+ " field becomes not red.");
		logger.info(" Verified that expected message displayed in red color: "
				+ msg);
		/*Assert.assertTrue(selUtils.getObject("callsch_err_icon_css")
				.isDisplayed());*/
		logger.info(" Verified that error message is displayed with error icon.");
	}

	/**
	 * In the table, click on the Edit icon of the specified estate. Click on
	 * the 'Call scheduling' button of the 'Estate config.' buttons section.
	 * Verify, the 'Call scheduling' modal window is displayed. If not already,
	 * set the 'Call Scheduling' modal window as following and click on the
	 * 'Save' button: -'Enable Scheduling' : check box ticked, -'Load Balancing'
	 * : check box ticked off, -'Protect from Parent Updates': check box ticked,
	 * -'Frequency' = specified value, -'Timezone Adjustement' = specified
	 * value, -'Window from' = specified value, -'to' = specified value,
	 * -'Reject Calls Outside' : check box ticked off. Verify, the modal window
	 * displays the message: 'Processing'. Then it displays the message: 'Call
	 * scheduling data have been added to terminal(s) or estate' and only a
	 * 'Close' button is available at the bottom of the modal window. Click on
	 * the 'Close' button. Verify, the modal window is closed.
	 */
	public void setCallSchdAllfldsWithLdBalAndRejClUnchk(String estate,
			String page, String frqVal, String timeZnAdjVal, String frStartHr,
			String frStartMin, String toEndHr, String toEndMin) {
		// In the table, click on the Edit icon of the specified estate.
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);
		/*
		 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
		 * section. Verify, the 'Call scheduling' modal window is displayed
		 */
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// Set the Call Schedule all check boxes as unchecked.
		setCallSchdAllChkBoxsUnChked();
		// Tick the 'Enable Scheduling' check box.
		vExpChkBxFunc("schenabled_name", yes, check, ENBLSCHDLBL);
		// Tick the 'Load Balancing' check box.
		vExpChkBxFunc("load_bal_chk_name", checkNo, unCheck, LOADBALLBL);
		// Tick the 'Protect from Parent Updates' check box.
		vExpChkBxFunc("pr_parent_upd_chk_name", yes, check, PROFROMPARLBL);
		// Set the 'Frequency' field to specified value'.
		addAdVerifyVal("frequency_id", frqVal, freq);
		// Set the 'Time Zone Adjustement' field to specified value.
		addAdVerifyVal("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		// Set the 'Window from' field to specified values.
		selcAdVerfHrMinTime("start_hour_name", "start_min_name", frStartHr,
				frStartMin, WINFROMLBL);
		// Set the 'To' field to specified values.
		selcAdVerfHrMinTime("end_hour_name", "end_min_name", toEndHr, toEndMin,
				WINFROMLBL);
		// Tick the 'Reject Calls Outside' check box.
		vExpChkBxFunc("rej_call_name", checkNo, unCheck, REJCALLLBL);
		/*
		 * Click on the 'Save' button. Verify, the modal window displays the
		 * message: 'Processing'. Then it displays the message: 'Call scheduling
		 * data have been added to terminal(s) or estate' and only a 'Close'
		 * button is available at the bottom of the modal window.
		 */
		verifyCallSchdAfterClkOnSave();
		verifyOnlyCloseButtInSuccWin("callsch_succ_cls_button_id");
		// Click on the 'Close' button. Verify, the modal window is closed.
		verifySpecifiedWinNotDisp("callsch_succ_cls_button_id");
	}

	/**
	 * The 'Call scheduling' modal window is displayed showing the following
	 * fields with the following values: -'Enable Scheduling' : check box ticked
	 * -'Load Balancing' : check box ticked off. -'Protect from Parent Updates':
	 * check box ticked. -'Frequency' = specified value. -'Timezone Adjustement'
	 * = specified value.-'Window from' = specified value. -'to' = specified
	 * value. -'Reject Calls Outside' : check box ticked off.
	 */
	public void verifyAllCallSchdSetValsWithLdBalAndRejClUnchk(String frqVal,
			String timeZnAdjVal, String frStartHr, String frStartMin,
			String toEndHr, String toEndMin) {
		/*
		 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
		 * section. Verify, the 'Call scheduling' modal window is displayed
		 */
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// The 'Enable Scheduling' check box is ticked.
		selUtils.verifyChked("schenabled_name", yes, check, ENBLSCHDLBL);
		// The 'Load Balancing' : check box ticked off.
		selUtils.verifyChked("load_bal_chk_name", checkNo, unCheck, LOADBALLBL);
		// The 'Protect from Parent Updates' : check box ticked.
		selUtils.verifyChked("pr_parent_upd_chk_name", yes, check,
				PROFROMPARLBL);
		// The Frequency' = specified value.
		vValFrmInputBox("frequency_id", frqVal, freq);
		// The 'Timezone Adjustement' = specified value..
		vValFrmInputBox("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		// The 'Window from' = specified value.
		selUtils.vDrpDnSelcItem("start_hour_name", frStartHr);
		selUtils.vDrpDnSelcItem("start_min_name", frStartMin);
		logger.info("Verified, '" + WINFROMLBL + "' date is having '"
				+ frStartHr + ":" + frStartMin + "'.");
		// The 'to' = specified value.
		selUtils.vDrpDnSelcItem("end_hour_name", toEndHr);
		selUtils.vDrpDnSelcItem("end_min_name", toEndMin);
		logger.info("Verified, '" + TOLBL + "' date is having '" + toEndHr
				+ ":" + toEndMin + "'.");
		// The 'Reject Calls Outside' field : check box ticked off.
		selUtils.verifyChked("rej_call_name", checkNo, unCheck, REJCALLLBL);
	}

	/**
	 * The 'Call scheduling' modal window is displayed showing the following
	 * fields with the following values:-'Enable Scheduling' : check box ticked.
	 * -'Load Balancing' : check box ticked off. -'Protect from Parent Updates':
	 * check box ticked off. -'Frequency' = specified value. -'Timezone
	 * Adjustement' = specified value.-'Window from' = specified value. -'to' =
	 * specified value. -'Reject Calls Outside' : check box ticked off.
	 */
	public void verifyAllCallSchdSetValsWithEnblChk(String frqVal,
			String timeZnAdjVal, String frStartHr, String frStartMin,
			String toEndHr, String toEndMin) {
		/*
		 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
		 * section. Verify, the 'Call scheduling' modal window is displayed
		 */
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// The 'Enable Scheduling' check box is ticked.
		selUtils.verifyChked("schenabled_name", yes, check, ENBLSCHDLBL);
		// The 'Load Balancing' : check box ticked off.
		selUtils.verifyChked("load_bal_chk_name", checkNo, unCheck, LOADBALLBL);
		// The 'Protect from Parent Updates' : check box ticked off.
		selUtils.verifyChked("pr_parent_upd_chk_name", checkNo, unCheck,
				PROFROMPARLBL);
		// The Frequency' = specified value.
		vValFrmInputBox("frequency_id", frqVal, freq);
		// The 'Timezone Adjustement' = specified value..
		vValFrmInputBox("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		// The 'Window from' = specified value.
		selUtils.vDrpDnSelcItem("start_hour_name", frStartHr);
		selUtils.vDrpDnSelcItem("start_min_name", frStartMin);
		logger.info("Verified, '" + WINFROMLBL + "' date is having '"
				+ frStartHr + ":" + frStartMin + "'.");
		// The 'to' = specified value.
		selUtils.vDrpDnSelcItem("end_hour_name", toEndHr);
		selUtils.vDrpDnSelcItem("end_min_name", toEndMin);
		logger.info("Verified, '" + TOLBL + "' date is having '" + toEndHr
				+ ":" + toEndMin + "'.");
		// The 'Reject Calls Outside' field : check box ticked off.
		selUtils.verifyChked("rej_call_name", checkNo, unCheck, REJCALLLBL);
	}

	/**
	 * Click on 'Estates' in the left menu of the TMS page. Verify, the 'TMS >>
	 * Estates' page is displayed. In the table, click on the Edit icon of the
	 * specified estate. Verify, the 'TMS >> Edit estate >> ---' specified page
	 * is displayed. Click on 'Call scheduling' button of the 'Estate config.'
	 * buttons section. Verify, the 'Call scheduling' modal window is displayed
	 * showing the following values:-'Enable Scheduling' : check box ticked,
	 * -'Load Balancing' : check box ticked off, -'Protect from Parent Updates':
	 * check box ticked, -'Frequency' = specified value, -'Timezone Adjustement'
	 * = specified value,-'Window from' = specified value, -'to' = specified
	 * value, -'Reject Calls Outside' : check box ticked off. Click on the
	 * 'Close' button. Verify, the modal window is closed.
	 */
	public void verifyCallSchdFldsSetValsWithLdBalAndRejClUnchk(String estate,
			String page, String frqVal, String timeZnAdjVal, String frStartHr,
			String frStartMin, String toEndHr, String toEndMin) {
		// Click on 'Estates' in the left menu of the TMS page.
		selUtils.getObject("estates_link").click();
		selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);

		// In the table, click on the Edit icon of the specified estate.
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);

		/*
		 * The 'Call scheduling' modal window is displayed showing the following
		 * fields with the following values:-'Enable Scheduling' : check box
		 * ticked. -'Load Balancing' : check box ticked off. -'Protect from
		 * Parent Updates' : check box ticked. -'Frequency' = specified value.
		 * -'Timezone Adjustement' = specified value.-'Window from' = specified
		 * value. -'to' = specified value. -'Reject Calls Outside' : check box
		 * ticked off.
		 */
		verifyAllCallSchdSetValsWithLdBalAndRejClUnchk(frqVal, timeZnAdjVal,
				frStartHr, frStartMin, toEndHr, toEndMin);

		// Click on the 'Close' button. Verify, the modal window is closed.
		verifySpecifiedWinNotDisp("cls_callsch_id");
	}

	/**
	 * Click on 'Estates' in the left menu of the TMS page. Verify, the 'TMS >>
	 * Estates' page is displayed. In the table, click on the Edit icon of the
	 * specified estate. Verify, the 'TMS >> Edit estate >> ---' specified page
	 * is displayed. Click on the 'Call scheduling' button of the 'Estate
	 * config.' buttons section. Verify, the 'Call scheduling' modal window is
	 * displayed showing the following values:-'Enable Scheduling' : check box
	 * ticked, -'Load Balancing' : check box ticked off, -'Protect from Parent
	 * Updates' :check box ticked off, -'Frequency' = specified value,
	 * -'Timezone Adjustement'= specified value, -'Window from' = specified
	 * value, -'to' = specified value, -'Reject Calls Outside' : check box
	 * ticked off.Click on 'Close' button. Verify, the modal window is closed.
	 */
	public void verifyCallSchdFldsSetValsWithEnblChk(String estate,
			String page, String frqVal, String timeZnAdjVal, String frStartHr,
			String frStartMin, String toEndHr, String toEndMin) {
		selUtils.getObject("estates_link").click();
		selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);
		verifyAllCallSchdSetValsWithEnblChk(frqVal, timeZnAdjVal, frStartHr,
				frStartMin, toEndHr, toEndMin);
		verifySpecifiedWinNotDisp("cls_callsch_id");
	}

	/**
	 * Click on 'Estates' in the left menu of the TMS page. Verify, the 'TMS >>
	 * Estates' page is displayed.In the table, click on the Edit icon of the
	 * specified estate. Verify, the 'TMS >> Edit estate >> ---' specified page
	 * is displayed. Click on the 'Call scheduling' button of the 'Estate
	 * config.' buttons section. Verify, the 'Call scheduling' modal window is
	 * displayed showing the following values: -'Enable Scheduling'.
	 */
	public void verifyCallSchdEnbSchdSetVal(String estate, String page,
			String chk, String tick) {
		selUtils.getObject("estates_link").click();
		selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		selUtils.verifyChked("schenabled_name", chk, tick, ENBLSCHDLBL);
		verifySpecifiedWinNotDisp("cls_callsch_id");
	}

	/**
	 * Click on 'Estates' in the left menu of the TMS page. Verify, the 'TMS >>
	 * Estates' page is displayed. In the table, click on the Edit icon of the
	 * specified estate. Click on the 'Call scheduling' button of the 'Estate
	 * config.' buttons section. Verify, the 'Call scheduling' modal window is
	 * displayed. If not already, set the 'Call Scheduling' modal window as
	 * following and click on the 'Save' button: -'Enable Scheduling' : check
	 * box ticked, -'Load Balancing' : check box ticked, -'Protect from Parent
	 * Updates' : check box ticked off,-'Frequency' = specified value,
	 * -'Timezone Adjustement' = specified value, -'Window from' = specified
	 * value,-'to' = specified value, -'Reject Calls Outside' : check box ticked
	 * Verify, the modal window displays the message:'Processing'. Then it
	 * displays message: 'Call scheduling data have been added to terminal(s) or
	 * estate' and only a 'Close' button is available at the bottom of the modal
	 * window. Click on the 'Close' button. Verify, the modal window is closed.
	 */
	public void setCallSchdAllfldsWithPrParentUnChk(String estate, String page,
			String frqVal, String timeZnAdjVal, String frStartHr,
			String frStartMin, String toEndHr, String toEndMin) {
		// In the table, click on the Edit icon of the specified estate.
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);
		/*
		 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
		 * section. Verify, the 'Call scheduling' modal window is displayed
		 */
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// Set the Call Schedule all check boxes as unchecked.
		setCallSchdAllChkBoxsUnChked();
		// Tick the 'Enable Scheduling' check box.
		vExpChkBxFunc("schenabled_name", yes, check, ENBLSCHDLBL);
		// Tick the 'Load Balancing' check box.
		vExpChkBxFunc("load_bal_chk_name", yes, check, LOADBALLBL);
		// Tick the 'Protect from Parent Updates' check box.
		vExpChkBxFunc("pr_parent_upd_chk_name", checkNo, unCheck, PROFROMPARLBL);
		// Set the 'Frequency' field to specified value.
		addAdVerifyVal("frequency_id", frqVal, freq);
		// Set the 'Time Zone Adjustement' field to specified value.
		addAdVerifyVal("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		// Set the 'Window from' field to specified values.
		selcAdVerfHrMinTime("start_hour_name", "start_min_name", frStartHr,
				frStartMin, WINFROMLBL);
		// Set the 'To' field to specified values.
		selcAdVerfHrMinTime("end_hour_name", "end_min_name", toEndHr, toEndMin,
				WINFROMLBL);
		// Tick the 'Reject Calls Outside' check box.
		vExpChkBxFunc("rej_call_name", yes, check, REJCALLLBL);
		/*
		 * Click on the 'Save' button. Verify, the modal window displays the
		 * message: 'Processing'. Then it displays the message: 'Call scheduling
		 * data have been added to terminal(s) or estate' and only a 'Close'
		 * button is available at the bottom of the modal window.
		 */
		verifyCallSchdAfterClkOnSave();
		verifyOnlyCloseButtInSuccWin("callsch_succ_cls_button_id");
		// Click on the 'Close' button. Verify, the modal window is closed.
		verifySpecifiedWinNotDisp("callsch_succ_cls_button_id");
	}

	/**
	 * Click on 'Estates' in the left menu of the TMS page. Verify, the 'TMS >>
	 * Estates' page is displayed.In the table, click on the Edit icon of the
	 * specified estate. Click on the 'Call scheduling' button of the 'Estate
	 * config.' buttons section. Verify, the 'Call scheduling' modal window is
	 * displayed. If not already, set the 'Call Scheduling' modal window as
	 * following and click on the 'Save' button: -'Enable Scheduling' : check
	 * box ticked, -'Load Balancing' : check box ticked off, -'Protect from
	 * Parent Updates' : check box ticked off,-'Frequency'=specified value,
	 * -'Timezone Adjustement'=specified value, -'Window from' = specified value
	 * -'to' = specified value, -'Reject Calls Outside' : check box ticked off.
	 * Verify, the modal window displays the message:'Processing'. Then it
	 * displays message: 'Call scheduling data have been added to terminal(s) or
	 * estate' and only a 'Close' button is available at the bottom of the modal
	 * window. Click on the 'Close' button. Verify, the modal window is closed.
	 */
	public void setCallSchdAllfldsWithEnblChk(String estate, String page,
			String frqVal, String timeZnAdjVal, String frStartHr,
			String frStartMin, String toEndHr, String toEndMin) {
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		setCallSchdAllChkBoxsUnChked();
		vExpChkBxFunc("schenabled_name", yes, check, ENBLSCHDLBL);
		vExpChkBxFunc("load_bal_chk_name", checkNo, unCheck, LOADBALLBL);
		vExpChkBxFunc("pr_parent_upd_chk_name", checkNo, unCheck, PROFROMPARLBL);
		addAdVerifyVal("frequency_id", frqVal, freq);
		addAdVerifyVal("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		selcAdVerfHrMinTime("start_hour_name", "start_min_name", frStartHr,
				frStartMin, WINFROMLBL);
		selcAdVerfHrMinTime("end_hour_name", "end_min_name", toEndHr, toEndMin,
				WINFROMLBL);
		vExpChkBxFunc("rej_call_name", checkNo, unCheck, REJCALLLBL);
		verifyCallSchdAfterClkOnSave();
		verifyOnlyCloseButtInSuccWin("callsch_succ_cls_button_id");
		verifySpecifiedWinNotDisp("callsch_succ_cls_button_id");
	}

	/**
	 * Set the 'Call Scheduling' - 'Enable Scheduling' : specified check,
	 * -'Protect from Parent Updates' : specified check, in specified estates.
	 */
	public void setCallSchdEnblAndPrtParChkBoxes(String estate, String page,
			String enbChk, String enbTick, String prtParChk, String prtParTick) {
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		setCallSchdAllChkBoxsUnChked();
		unchkEnblSchdAdVPrPrtUpdAdVSave(enbChk, enbTick, prtParChk, prtParTick);
	}

	/**
	 * If not already, set the 'Call Scheduling' modal window as following and
	 * click on the 'Save' button:-'Enable Scheduling' : specified check,
	 * -'Protect from Parent Updates' : specified check.Verify, the modal window
	 * displays the message: 'Processing'. Then it displays the message: 'Call
	 * scheduling data have been added to terminal(s) or estate' and only a
	 * 'Close' button is available at the bottom of the modal window.
	 **/
	public void unchkEnblSchdAdVPrPrtUpdAdVSave(String enbChk, String enbTick,
			String prtParChk, String prtParTick) {
		vExpChkBxFunc("schenabled_name", enbChk, enbTick, ENBLSCHDLBL);
		vExpChkBxFunc("pr_parent_upd_chk_name", prtParChk, prtParTick,
				PROFROMPARLBL);
		verifyCallSchdAfterClkOnSave();
		verifyOnlyCloseButtInSuccWin("callsch_succ_cls_button_id");
		// Click on the 'Close' button. Verify, the modal window is closed.
		verifySpecifiedWinNotDisp("callsch_succ_cls_button_id");
	}

	/**
	 * Click on 'Estates' in the left menu of the TMS page. Verify, the 'TMS >>
	 * Estates' page is displayed.In the table, click on the Edit icon of the
	 * specified estate. Click on the 'Call scheduling' button of the 'Estate
	 * config.' buttons section. Verify, the 'Call scheduling' modal window is
	 * displayed. If not already, set the 'Call Scheduling' modal window as
	 * following and click on the 'Save' button: -'Enable Scheduling' : check
	 * box ticked, -'Load Balancing' : check box ticked off, -'Protect from
	 * Parent Updates' : check box ticked off,-'Frequency'=specified value,
	 * -'Timezone Adjustement'=specified value, -'Window from' = specified value
	 * -'to' = specified value, -'Reject Calls Outside' : check box ticked off.
	 * Verify, the modal window displays the message:'Processing'. Then it
	 * displays message: 'Call scheduling data have been added to terminal(s) or
	 * estate' and only a 'Close' button is available at the bottom of the modal
	 * window. Click on the 'Close' button. Verify, the modal window is closed.
	 */
	public void verifyCallSchdFldsSetVals(String estate, String page,
			String frqVal, String timeZnAdjVal, String frStartHr,
			String frStartMin, String toEndHr, String toEndMin) {
		selUtils.getObject("estates_link").click();
		selUtils.verifyBreadCrumb(Estates.breadCrumbEstates);
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);
		verifyAllCallSchdSetValsWithPrParentUnchk(frqVal, timeZnAdjVal,
				frStartHr, frStartMin, toEndHr, toEndMin);
		verifySpecifiedWinNotDisp("cls_callsch_id");
	}

	/**
	 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
	 * section.The 'Call scheduling' modal window is displayed containing:-An
	 * 'Enable Scheduling' field not editable -A 'Load Balancing' field not
	 * editable -A 'Protect from Parent Updates' field not editable -A
	 * 'Scheduling parameters' section containing: -A 'Frequency' field in
	 * 'days' not editable -A 'Timezone Adjustment' field in 'minutes' not
	 * editable -A 'Window from' field and a 'to' field not editable -A 'Reject
	 * Calls Outside' field not editable -A 'Close' button and There is no
	 * 'Save' button.
	 */
	public void calSchDisCheck() {
		Assert.assertTrue(selUtils.getObject("buttons_group_css").getText()
				.contains(Estates.estConfig));
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// containing
		Assert.assertFalse(selUtils.getObject("schenabled_name").isEnabled(),
				"'" + ENBLSCHDLBL + "' check box is editable.");
		logger.info(" '" + ENBLSCHDLBL + "' check box is not editable.");
		Assert.assertFalse(selUtils.getObject("load_bal_chk_name").isEnabled(),
				"'" + LOADBALLBL + "' check box is editable.");
		logger.info(" '" + LOADBALLBL + "' check box is not editable.");
		Assert.assertFalse(selUtils.getObject("pr_parent_upd_chk_name")
				.isEnabled(), "'" + PROFROMPARLBL + "' check box is editable.");
		logger.info(" '" + PROFROMPARLBL + "' check box is not editable.");
		Assert.assertTrue(selUtils.getObject("calsch_param_xpath").getText()
				.contains(SCHDPARAMSLBL));
		logger.info(" Verified that " + SCHDPARAMSLBL + " contains: ");
		Assert.assertFalse(selUtils.getObject("frequency_id").isEnabled(), "'"
				+ FREQLBL + "' text box is editable.");
		logger.info(" '" + FREQLBL + "' text box is not editable.");
		Assert.assertFalse(selUtils.getObject("timezone_adj_id").isEnabled(),
				"'" + TIMEZNADJLBL + "' text box is editable.");
		logger.info(" '" + TIMEZNADJLBL + "' text box is not editable.");
		Assert.assertFalse(selUtils.getObject("start_hour_name").isEnabled()
				&& selUtils.getObject("start_min_name").isEnabled(), "'"
				+ WINFROMLBL + "' date is editable.");
		logger.info(" '" + WINFROMLBL + "' date is not editable.");
		Assert.assertFalse(selUtils.getObject("end_hour_name").isEnabled()
				&& selUtils.getObject("end_min_name").isEnabled(), "'" + TOLBL
				+ "' date is editable.");
		logger.info(" '" + TOLBL + "' date is not editable.");
		Assert.assertFalse(selUtils.getObject("rej_call_name").isEnabled(), "'"
				+ REJCALLLBL + "' check box is editable.");
		logger.info(" '" + REJCALLLBL + "' check box is not editable.");
		Assert.assertTrue(waitMethods.isElementPresent("cls_link"), "'"
				+ closeButton + "' button is not displayed.");
		logger.info("Verified, '" + closeButton + "' button is displayed.");
		Assert.assertFalse(waitMethods.isElementPresent("save_link"), "'"
				+ saveButton + "' button is displayed.");
		logger.info("Verified, '" + saveButton + "' button is not displayed.");
	}

	/**
	 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
	 * section. Verify,'Call scheduling' modal window is displayed containing:
	 * -An 'Enable Scheduling' field,-A 'Load Balancing' field, -A 'Protect from
	 * Parent Updates' field, -A 'Scheduling parameters' section containing: -A
	 * 'Frequency' field in 'days', -A 'Timezone Adjustment' field in 'minutes',
	 * -A 'Window from' field and a 'to' field, -A 'Reject Calls Outside' field,
	 * -An 'Save' and a 'Close' buttonl.
	 **/
	public void verifyCallSchdFldsAvailability() {
		elements = selUtils.getObjects("buttons_group_css");
		Assert.assertTrue(elements.get(2).getText().contains(Estates.estConfig));

		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// containing
		Assert.assertTrue(selUtils.getObject("enbl_sched_xpath").isDisplayed(),
				"'" + ENBLSCHDLBL + "' field is not displayed.");
		logger.info("Verified, '" + ENBLSCHDLBL + "' field is displayed.");

		Assert.assertTrue(selUtils.getObject("load_bal_xpath").isDisplayed(),
				"'" + LOADBALLBL + "' field is not displayed.");
		logger.info("Verified, '" + LOADBALLBL + "' field is displayed.");

		Assert.assertTrue(selUtils.getObject("protc_parent_xpath")
				.isDisplayed(), "'" + PROFROMPARLBL
				+ "' field is not displayed.");
		logger.info("Verified, '" + PROFROMPARLBL + "' field is displayed.");

		Assert.assertTrue(selUtils.getObject("calsch_param_xpath").getText()
				.contains(SCHDPARAMSLBL));
		logger.info(" Verified that " + SCHDPARAMSLBL + " contains: ");

		Assert.assertTrue(selUtils.getObject("freq_withdays_xpath")
				.isDisplayed(), "'" + FREQLBL + "' field in '" + daysLbl
				+ "' is not displayed.");
		logger.info("Verified, '" + FREQLBL + "' field in '" + daysLbl
				+ "' is displayed.");

		Assert.assertTrue(selUtils.getObject("timznadjs_withmins_xpath")
				.isDisplayed(), "'" + TIMEZNADJLBL + "' field in '" + minsLbl
				+ "' is not displayed.");
		logger.info("Verified, '" + TIMEZNADJLBL + "' field in '" + minsLbl
				+ "' is displayed.");

		Assert.assertTrue(selUtils.getObject("windfrm_date_xpath")
				.isDisplayed(), "'" + WINFROMLBL
				+ "' date field is not displayed.");
		logger.info("Verified, '" + WINFROMLBL + "' date field is displayed.");

		Assert.assertTrue(selUtils.getObject("to_date_xpath").isDisplayed(),
				"'" + TOLBL + "' date field is not displayed.");
		logger.info("Verified, '" + TOLBL + "' date field is displayed.");

		Assert.assertTrue(selUtils.getObject("rej_calls_outside_xpath")
				.isDisplayed(), "'" + REJCALLLBL + "' field is not displayed.");
		logger.info("Verified, '" + REJCALLLBL + "' field is displayed.");

		selUtils.verifyElementDisp("save_callsch_id", saveButton);
		selUtils.verifyElementDisp("cls_callsch_id", closeButton);
	}

	/**
	 * Set the Call Schedule all check boxes as unchecked.
	 */
	public void setCallSchdAllChkBoxsUnChked() {
		selUtils.chkBxChk("schenabled_name", yes);
		selUtils.chkBxChk("load_bal_chk_name", checkNo);
		selUtils.chkBxChk("pr_parent_upd_chk_name", checkNo);
		selUtils.chkBxChk("rej_call_name", checkNo);
		selUtils.chkBxChk("schenabled_name", checkNo);
	}

	/**
	 * Verify Schedule parameter labels
	 */
	public void verifySchdParmFldLbl(String fieldLabel) {
		xpath = selUtils.getPath("sched_peram_label_xpath").replace(
				"SCHED_PERAM", fieldLabel);
		waitMethods.waitForElement(selUtils.getObjectDirect(By.xpath(xpath)));
		Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).getText()
				.equals(fieldLabel), "'" + fieldLabel
				+ "' field is not displayed.");
		logger.info("Verified, '" + fieldLabel + "' field label is displayed.");
	}

	/**
	 * In the table, click on the Edit icon of the specified estate. Click on
	 * the 'Call scheduling' button of the 'Estate config.' buttons section.
	 * Verify, the 'Call scheduling' modal window is displayed. If not already,
	 * set the 'Call Scheduling' modal window as following and click on the
	 * 'Save' button: -'Enable Scheduling' : check box ticked, -'Load Balancing'
	 * : check box ticked off, -'Protect from Parent Updates' : check box ticked
	 * off,-'Frequency'=specified value, -'Timezone Adjustement'=specified
	 * value, -'Window from' = specified value -'to' = specified value, -'Reject
	 * Calls Outside' : check box ticked off. Verify, the modal window displays
	 * the message:'Processing'. Then it displays message: 'Call scheduling data
	 * have been added to terminal(s) or estate' and only a 'Close' button is
	 * available at the bottom of the modal window. Click on the 'Close' button.
	 * Verify, the modal window is closed.
	 */
	public void setCallSchdAllfldsWithChked(String estate, String page,
			String frqVal, String timeZnAdjVal, String frStartHr,
			String frStartMin, String toEndHr, String toEndMin) {
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		setCallSchdAllChkBoxsUnChked();
		vExpChkBxFunc("schenabled_name", yes, check, ENBLSCHDLBL);
		vExpChkBxFunc("load_bal_chk_name", yes, check, LOADBALLBL);
		vExpChkBxFunc("pr_parent_upd_chk_name", yes, check, PROFROMPARLBL);
		addAdVerifyVal("frequency_id", frqVal, freq);
		addAdVerifyVal("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		selcAdVerfHrMinTime("start_hour_name", "start_min_name", frStartHr,
				frStartMin, WINFROMLBL);
		selcAdVerfHrMinTime("end_hour_name", "end_min_name", toEndHr, toEndMin,
				WINFROMLBL);
		vExpChkBxFunc("rej_call_name", yes, check, REJCALLLBL);
		verifyCallSchdAfterClkOnSave();
		verifyOnlyCloseButtInSuccWin("callsch_succ_cls_button_id");
		verifySpecifiedWinNotDisp("callsch_succ_cls_button_id");
	}

	/**
	 * Click on the 'Call scheduling' button of the 'Terminals config.' buttons
	 * section,The 'Call scheduling' modal window is displayed If not ticked,
	 * tick the 'Enable Scheduling' check box,The 'Enable Scheduling' check box
	 * is ticked.If not ticked, tick the 'Load Balancing' check box,The 'Load
	 * Balancing' check box is ticked If not ticked, tick the 'Protect from
	 * Parent Updates' check box,The 'Protect from Parent Updates' check box is
	 * ticked,If not ticked, tick the 'Force Date' check box.The 'Force Date'
	 * check box is ticked,Click on the calendar icon associated to the 'Force
	 * Date' field,A calendar appears.Set the year field of the calendar to 2020
	 * and click on the day '15' in the calendar,The calendar disappears and the
	 * year in the field is set to 2020 ,Set the 'Frequency' field to '50',The
	 * 'Frequency' field is set to '50' Set the 'Time Zone Adjustment' field to
	 * '120', The 'Time Zone Adjustment' field is set to '120'Set the 'Window
	 * from' field to '10:10',The 'Window from' field is set to '10:10' Set the
	 * 'to' field to '16:40',The 'to' field is set to '16:40'If not ticked, tick
	 * the 'Reject Calls Outside' check box, The 'Reject Calls Outside' check
	 * box is ticked Click on the 'Save' button,The modal window displays the
	 * message: 'Processing'. Then it displays the message: 'Call scheduling
	 * data have been added to terminal(s) or estate' and only a 'Close' button
	 * is available at the bottom of the modal window.Click on the 'Close'
	 * button,The modal window is closed.
	 * 
	 * @param frqVal
	 * @param timeZnAdjVal
	 * @param frStartHr
	 * @param frStartMin
	 * @param toEndHr
	 * @param toEndMin
	 */
	public void setCallSchdAllfldsWithChked(String frqVal, String timeZnAdjVal,
			String frStartHr, String frStartMin, String toEndHr, String toEndMin) {
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		setCallSchdAllChkBoxsUnChked();
		vExpChkBxFunc("schenabled_name", yes, check, ENBLSCHDLBL);
		vExpChkBxFunc("load_bal_chk_name", yes, check, LOADBALLBL);
		vExpChkBxFunc("pr_parent_upd_chk_name", yes, check, PROFROMPARLBL);
		vExpChkBxFunc("force_dt_chkbx_name", yes, check, FRCDTLBL);
		setCalto(YEAR2020, DAY15);
		addAdVerifyVal("frequency_id", frqVal, freq);
		addAdVerifyVal("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		selcAdVerfHrMinTime("start_hour_name", "start_min_name", frStartHr,
				frStartMin, WINFROMLBL);
		selcAdVerfHrMinTime("end_hour_name", "end_min_name", toEndHr, toEndMin,
				WINFROMLBL);
		vExpChkBxFunc("rej_call_name", yes, check, REJCALLLBL);
		verifyCallSchdAfterClkOnSave();
		verifyOnlyCloseButtInSuccWin("callsch_succ_cls_button_id");
		verifySpecifiedWinNotDisp("callsch_succ_cls_button_id");
	}

	/**
	 * set date field to expected year, expected date. Verify, the calendar
	 * disappears and the year in the field is set to same.
	 * 
	 * @param year
	 * @param date
	 */
	public void setCalto(String year, String date) {
		selUtils.getObject("frc_dt_clndr_icon_css").click();
		Assert.assertTrue(selUtils.getObject("frc_dt_clndr_icon_css")
				.isDisplayed(), "expected " + CLNDR + " is not displayed.");
		new Select(selUtils.getObject("year_fld_css"))
				.selectByVisibleText(year);
		//Action class is added to handle the click action on the linux machine,hence below code works for Linux and Windows
				action = new Actions(driver);
				action.moveToElement(selUtils.getObjectDirect(
						By.xpath(selUtils.getPath("date_xpath").replace("DATE", date)))).click().perform();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("datepick-div")));
		
//		selUtils.getObjectDirect(
//				By.xpath(selUtils.getPath("date_xpath").replace("DATE", date)))
//				.click();
//		waitMethods.waitForElementNotPresent("dt_clndr_id");
		Assert.assertTrue(
				selUtils.getObject("dt_clndr_id").getAttribute("style")
						.contains("none;"), "'" + CLNDR
						+ "' is not disappeared.");
		logger.info("Verified, '" + CLNDR + "' is disappeared.");
	}

	/**
	 * The 'Call scheduling' modal window is displayed showing the following
	 * fields with the following values: -'Enable Scheduling' : check box
	 * ticked. -'Load Balancing' : check box ticked. -'Protect from Parent
	 * Updates' : check box ticked. -'Frequency' = specified value. -'Timezone
	 * Adjustement' = specified value. -'Window from' = specified value. -'to' =
	 * specified value. -'Reject Calls Outside' : check box ticked.
	 */
	public void verifyAllCallSchdSetVals(String estate, String page,
			String frqVal, String timeZnAdjVal, String frStartHr,
			String frStartMin, String toEndHr, String toEndMin) {
		// In the table, click on the Edit icon of the specified estate.
		ObjectFactory.getEstateInstance().clickEditIconOfSpecificEstate(estate);
		selUtils.verifyBreadCrumb(page);
		/*
		 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
		 * section. Verify, the 'Call scheduling' modal window is displayed
		 */
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// The 'Enable Scheduling' check box is ticked off.
		selUtils.verifyChked("schenabled_name", yes, check, ENBLSCHDLBL);
		// The 'Load Balancing' : check box ticked.
		selUtils.verifyChked("load_bal_chk_name", yes, check, LOADBALLBL);
		// The 'Protect from Parent Updates' : check box ticked.
		selUtils.verifyChked("pr_parent_upd_chk_name", yes, check,
				PROFROMPARLBL);
		// The Frequency' = specified value.
		vValFrmInputBox("frequency_id", frqVal, freq);
		// The 'Timezone Adjustement' = specified value.
		vValFrmInputBox("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		// The 'Window from' = specified values.
		selUtils.vDrpDnSelcItem("start_hour_name", frStartHr);
		selUtils.vDrpDnSelcItem("start_min_name", frStartMin);
		logger.info("Verified, '" + WINFROMLBL + "' date is having '"
				+ frStartHr + ":" + frStartMin + "'.");
		// The 'to' = specified values.
		selUtils.vDrpDnSelcItem("end_hour_name", toEndHr);
		selUtils.vDrpDnSelcItem("end_min_name", toEndMin);
		logger.info("Verified, '" + TOLBL + "' date is having '" + toEndHr
				+ ":" + toEndMin + "'.");
		// The 'Reject Calls Outside' field : check box ticked.
		selUtils.verifyChked("rej_call_name", yes, check, REJCALLLBL);
	}

	/**
	 * Click on the 'Call scheduling' button of the 'Terminals config.' buttons
	 * section. Verify, the 'Call scheduling' modal window is displayed showing
	 * the following fields with the following values:-'Enable Scheduling' :
	 * check box ticked-'Load Balancing' : check box ticked -'Protect from
	 * Parent Updates' : check box ticked -'Force Date' : check box ticked -Date
	 * field set for the expected year. -'Frequency' = expected value. -'Time
	 * Zone Adjustment' = expected value. -'Window from' = expected value. -'to'
	 * = expected value. -'Reject Calls Outside' : check box ticked
	 **/
	public void verifyAllCallSchdSetVals(String frqVal, String timeZnAdjVal,
			String frStartHr, String frStartMin, String toEndHr, String toEndMin) {
		/*
		 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
		 * section. Verify, the 'Call scheduling' modal window is displayed
		 */
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// The 'Enable Scheduling' check box is ticked.
		selUtils.verifyChked("schenabled_name", yes, check, ENBLSCHDLBL);
		// The 'Load Balancing' : check box ticked.
		selUtils.verifyChked("load_bal_chk_name", yes, check, LOADBALLBL);
		// The 'Protect from Parent Updates' : check box ticked.
		selUtils.verifyChked("pr_parent_upd_chk_name", yes, check,
				PROFROMPARLBL);
		// 'Force Date' : check box ticked
		selUtils.verifyChked("force_dt_chkbx_name", yes, check, FRCDTLBL);
		// Date field set for the year 2020
		Assert.assertTrue(selUtils.getObject("frc_dt_clndr_inpbx_id")
				.getAttribute("value").contains(YEAR2020));
		logger.info("Verified calender is set to" + YEAR2020);
		// The Frequency' = specified value.
		vValFrmInputBox("frequency_id", frqVal, freq);
		// The 'Timezone Adjustement' = specified value.
		vValFrmInputBox("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		// The 'Window from' = specified values.
		selUtils.vDrpDnSelcItem("start_hour_name", frStartHr);
		selUtils.vDrpDnSelcItem("start_min_name", frStartMin);
		logger.info("Verified, '" + WINFROMLBL + "' date is having '"
				+ frStartHr + ":" + frStartMin + "'.");
		// The 'to' = specified values.
		selUtils.vDrpDnSelcItem("end_hour_name", toEndHr);
		selUtils.vDrpDnSelcItem("end_min_name", toEndMin);
		logger.info("Verified, '" + TOLBL + "' date is having '" + toEndHr
				+ ":" + toEndMin + "'.");
		// The 'Reject Calls Outside' field : check box ticked.
		selUtils.verifyChked("rej_call_name", yes, check, REJCALLLBL);
	}

	/**
	 * Click on the 'Call scheduling' button of the 'Terminals config.' buttons
	 * section. Verify, the 'Call scheduling' modal window is displayed showing
	 * the following fields with the following values:-'Enable Scheduling' :
	 * check box ticked. -'Protect from Parent Updates' : check box ticked.
	 * -'Frequency' not equal to '54'. -'Time Zone Adjustment' not equal t
	 * '810'. -'Window from' not equal t '05:04'. -'to' not equal t '08:10'.
	 **/
	public void vExpValInCallSchd(String frqVal, String timeZnAdjVal,
			String frStartHr, String frStartMin, String toEndHr, String toEndMin) {
		/*
		 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
		 * section. Verify, the 'Call scheduling' modal window is displayed
		 */
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// The 'Enable Scheduling' check box is ticked.
		selUtils.verifyChked("schenabled_name", yes, check, ENBLSCHDLBL);
		// The 'Protect from Parent Updates' : check box ticked.
		selUtils.verifyChked("pr_parent_upd_chk_name", yes, check,
				PROFROMPARLBL);
		// The Frequency' not equal to specified value.
		vValNtExpInInputBx("frequency_id", frqVal, FREQLBL);
		// The 'Timezone Adjustement' not equal to specified value.
		vValNtExpInInputBx("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		// The 'Window from' not equal to specified values.
		vExpValNtSelctd("start_hour_name", frStartHr);
		logger.info("Verified, '" + WINFROMLBL + "', '" + hour
				+ "' is not equal to '" + frStartHr + "'.");
		vExpValNtSelctd("start_min_name", frStartMin);
		logger.info("Verified, '" + WINFROMLBL + "', '" + minsLbl
				+ "' is not equal to '" + frStartMin + "'.");
		logger.info("Verified, '" + WINFROMLBL + "' date is not having '"
				+ frStartHr + ":" + frStartMin + "'.");
		// The 'to' not equal to specified values.
		vExpValNtSelctd("end_hour_name", toEndHr);
		logger.info("Verified, '" + TOLBL + "', '" + hour
				+ "' is not equal to '" + toEndHr + "'.");
		vExpValNtSelctd("end_min_name", toEndMin);
		logger.info("Verified, '" + TOLBL + "', '" + minsLbl
				+ "' is not equal to '" + toEndMin + "'.");
		logger.info("Verified, '" + TOLBL + "' date is not having '" + toEndHr
				+ ":" + toEndMin + "'.");
	}

	/**
	 * The 'Call scheduling' modal window is displayed showing the following
	 * fields with the following values: -'Enable Scheduling' : check box
	 * ticked. -'Load Balancing' : check box ticked. -'Protect from Parent
	 * Updates' : check box ticked off. -'Frequency' = specified value.
	 * -'Timezone Adjustement' = specified value. -'Window from' = specified
	 * value. -'to' = specified value. -'Reject Calls Outside' : check box
	 * ticked.
	 */
	public void verifyAllCallSchdSetValsWithPrParentUnchk(String frqVal,
			String timeZnAdjVal, String frStartHr, String frStartMin,
			String toEndHr, String toEndMin) {
		/*
		 * Click on the 'Call scheduling' button of the 'Estate config.' buttons
		 * section. Verify, the 'Call scheduling' modal window is displayed
		 */
		verifyExpWinDisp("call_sch_link", CALLSCHD);
		// The 'Enable Scheduling' check box is ticked.
		selUtils.verifyChked("schenabled_name", yes, check, ENBLSCHDLBL);
		// The 'Load Balancing' : check box ticked.
		selUtils.verifyChked("load_bal_chk_name", yes, check, LOADBALLBL);
		// The 'Protect from Parent Updates' : check box ticked off.
		selUtils.verifyChked("pr_parent_upd_chk_name", checkNo, unCheck,
				PROFROMPARLBL);
		// The Frequency' = specified value.
		vValFrmInputBox("frequency_id", frqVal, freq);
		// The 'Timezone Adjustement' = specified value..
		vValFrmInputBox("timezone_adj_id", timeZnAdjVal, TIMEZNADJLBL);
		// The 'Window from' = specified value.
		selUtils.vDrpDnSelcItem("start_hour_name", frStartHr);
		selUtils.vDrpDnSelcItem("start_min_name", frStartMin);
		logger.info("Verified, '" + WINFROMLBL + "' date is having '"
				+ frStartHr + ":" + frStartMin + "'.");
		// The 'to' = specified value.
		selUtils.vDrpDnSelcItem("end_hour_name", toEndHr);
		selUtils.vDrpDnSelcItem("end_min_name", toEndMin);
		logger.info("Verified, '" + TOLBL + "' date is having '" + toEndHr
				+ ":" + toEndMin + "'.");
		// The 'Reject Calls Outside' field : check box ticked.
		selUtils.verifyChked("rej_call_name", yes, check, REJCALLLBL);
	}

	/**
	 * If not ticked off, tick off the 'Enable Scheduling' check box. Verify,
	 * the 'Enable Scheduling' check box is ticked off. The 'Load Balancing'
	 * field is grayed and not editable. The 'Protect from Parent Updates' field
	 * is editable. The 'Frequency' field is grayed and not editable. The
	 * 'Timezone Adjustement' field is grayed and not editable. The 'Window
	 * from' field is grayed and not editable. The 'to' field is grayed and not
	 * editable. The 'Reject Calls Outside' field is grayed and not editable.
	 */
	public void verifyUnchkedEnblSchd(String browser) {
		// The 'Enable Scheduling' check box is ticked off.
		vExpChkBxFunc("schenabled_name", checkNo, unCheck, ENBLSCHDLBL);

		if ("firefox".equalsIgnoreCase(browser)) {
			Assert.assertTrue(selUtils.getObject("load_bal_chk_name")
					.getAttribute("style").contains("rgb(221, 221, 221)"), "'"
					+ LOADBALLBL + "' check box is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("frequency_id").getAttribute("style")
							.contains("rgb(221, 221, 221)"), "'" + FREQLBL
							+ "' text box is not having gray color.");
			Assert.assertTrue(selUtils.getObject("timezone_adj_id")
					.getAttribute("style").contains("rgb(221, 221, 221)"), "'"
					+ TIMEZNADJLBL + "' text box is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("start_hour_name").getAttribute("style")
							.contains("rgb(221, 221, 221)")
							&& selUtils.getObject("start_min_name")
									.getAttribute("style")
									.contains("rgb(221, 221, 221)"), "'"
							+ WINFROMLBL + "' date is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("end_hour_name").getAttribute("style")
							.contains("rgb(221, 221, 221)")
							&& selUtils.getObject("end_min_name")
									.getAttribute("style")
									.contains("rgb(221, 221, 221)"), "'"
							+ TOLBL + "' date is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("rej_call_name").getAttribute("style")
							.contains("rgb(221, 221, 221)"), "'" + REJCALLLBL
							+ "' check box is not having gray color.");
		}

		if ("internet explorer".equalsIgnoreCase(browser)) {
			Assert.assertTrue(selUtils.getObject("load_bal_chk_name")
					.getAttribute("style").contains("#dddddd"), "'"
					+ LOADBALLBL + "' check box is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("frequency_id").getAttribute("style")
							.contains("#dddddd"), "'" + FREQLBL
							+ "' text box is not having gray color.");
			Assert.assertTrue(selUtils.getObject("timezone_adj_id")
					.getAttribute("style").contains("#dddddd"), "'"
					+ TIMEZNADJLBL + "' text box is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("start_hour_name").getAttribute("style")
							.contains("#dddddd")
							&& selUtils.getObject("start_min_name")
									.getAttribute("style").contains("#dddddd"),
					"'" + WINFROMLBL + "' date is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("end_hour_name").getAttribute("style")
							.contains("#dddddd")
							&& selUtils.getObject("end_min_name")
									.getAttribute("style").contains("#dddddd"),
					"'" + TOLBL + "' date is not having gray color.");
			Assert.assertTrue(
					selUtils.getObject("rej_call_name").getAttribute("style")
							.contains("#dddddd"), "'" + REJCALLLBL
							+ "' check box is not having gray color.");
		}

		// The 'Load Balancing' field is grayed and not editable
		Assert.assertFalse(selUtils.getObject("load_bal_chk_name").isEnabled(),
				"'" + LOADBALLBL + "' check box is editable.");
		logger.info("Verified, '" + LOADBALLBL
				+ "' check box is having gray color, and not editable.");

		// The 'Protect from Parent Updates' field is editable.
		Assert.assertTrue(selUtils.getObject("pr_parent_upd_chk_name")
				.isEnabled(), "'" + PROFROMPARLBL
				+ "' check box is not editable.");
		logger.info("Verified, '" + PROFROMPARLBL + "' check box is editable.");

		// The 'Frequency' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("frequency_id").isEnabled(), "'"
				+ FREQLBL + "' text box is editable.");
		logger.info("Verified, '" + FREQLBL
				+ "' text box is having gray color, and not editable.");

		// The 'Timezone Adjustement' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("timezone_adj_id").isEnabled(),
				"'" + TIMEZNADJLBL + "' text box is editable.");
		logger.info("Verified, '" + TIMEZNADJLBL
				+ "' text box is having gray color, and not editable.");

		// The 'Window from' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("start_hour_name").isEnabled()
				&& selUtils.getObject("start_min_name").isEnabled(), "'"
				+ WINFROMLBL + "' date is editable.");
		logger.info("Verified, '" + WINFROMLBL
				+ "' date is having gray color, and not editable.");

		// The 'to' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("end_hour_name").isEnabled()
				&& selUtils.getObject("end_min_name").isEnabled(), "'" + TOLBL
				+ "' date is editable.");
		logger.info("Verified, '" + TOLBL
				+ "' date is having gray color, and not editable.");

		// The 'Reject Calls Outside' field is grayed and not editable.
		Assert.assertFalse(selUtils.getObject("rej_call_name").isEnabled(), "'"
				+ REJCALLLBL + "' check box is editable.");
		logger.info("Verified, '" + REJCALLLBL
				+ "' check box is having gray color, and not editable.");
	}

	/**
	 * Tick the 'Enable Scheduling' check box. Verify, The 'Enable Scheduling'
	 * check box is ticked. The 'Load Balancing' field is editable. The 'Protect
	 * from Parent Updates' field is editable. The 'Frequency' field is
	 * editable. The 'Timezone Adjustement' field is editable. The 'Window from'
	 * field is editable. The 'to' field is editable. The 'Reject Calls Outside'
	 * field is editable.
	 */
	public void verifyChkedEnblSchd() {
		// The 'Enable Scheduling' check box is ticked off.
		vExpChkBxFunc("schenabled_name", yes, check, ENBLSCHDLBL);
		// The 'Load Balancing' field is editable.
		Assert.assertTrue(selUtils.getObject("load_bal_chk_name").isEnabled(),
				"'" + LOADBALLBL + "' check box is not editable.");
		logger.info("Verified, '" + LOADBALLBL + "' check box is editable.");
		// The 'Protect from Parent Updates' field is editable.
		Assert.assertTrue(selUtils.getObject("pr_parent_upd_chk_name")
				.isEnabled(), "'" + PROFROMPARLBL
				+ "' check box is not editable.");
		logger.info("Verified, '" + PROFROMPARLBL + "' check box is editable.");
		// The 'Frequency' field is editable.
		Assert.assertTrue(selUtils.getObject("frequency_id").isEnabled(), "'"
				+ FREQLBL + "' text box is not editable.");
		logger.info("Verified, '" + FREQLBL + "' text box is editable.");
		// The 'Timezone Adjustement' field is editable.
		Assert.assertTrue(selUtils.getObject("timezone_adj_id").isEnabled(),
				"'" + TIMEZNADJLBL + "' text box is not editable.");
		logger.info("Verified, '" + TIMEZNADJLBL + "' text box is editable.");
		// The 'Window from' field is editable.
		Assert.assertTrue(selUtils.getObject("start_hour_name").isEnabled()
				&& selUtils.getObject("start_min_name").isEnabled(), "'"
				+ WINFROMLBL + "' date is not editable.");
		logger.info("Verified, '" + WINFROMLBL + "' date is editable.");
		// The 'to' field is editable.
		Assert.assertTrue(selUtils.getObject("end_hour_name").isEnabled()
				&& selUtils.getObject("end_min_name").isEnabled(), "'" + TOLBL
				+ "' date is not editable.");
		logger.info("Verified, '" + TOLBL + "' date is editable.");
		// The 'Reject Calls Outside' field is editable.
		Assert.assertTrue(selUtils.getObject("rej_call_name").isEnabled(), "'"
				+ REJCALLLBL + "' check box is not editable.");
		logger.info("Verified, '" + REJCALLLBL + "' check box is editable.");
	}

	/**
	 * Verify the Drop-down list items of the Minutes, and hours field
	 * 
	 * @param estatesListItems
	 */
	public void vHrAdMinMDrpDwnItems(String dropDownObject, String[] listItems,
			String browser) {
		String lstItemsStr = "", lstItemsStr1 = "";
		waitMethods.waitForElementDisplayed(dropDownObject);
		final List<String> items = selUtils.getLstItems(selUtils
				.getObjects(dropDownObject));
		if ("firefox".equals(browser) || "chrome".equals(browser)) {
			for (itemCount = 0; itemCount < listItems.length; itemCount++) {
				lstItemsStr = (lstItemsStr + "\n" + listItems[itemCount])
						.trim();
				lstItemsStr1 = (lstItemsStr + " " + listItems[itemCount])
						.trim();
			}
		} else {
			for (itemCount = 0; itemCount < listItems.length; itemCount++) {
				lstItemsStr = (lstItemsStr + listItems[itemCount]).trim();
				lstItemsStr1 = (lstItemsStr + " " + listItems[itemCount])
						.trim();
			}
		}
		Assert.assertTrue(items.contains(lstItemsStr.trim()),
				"Drop down is not displayed expected item.");
		logger.info("Verified,drop down list is displayed '" + lstItemsStr1
				+ "' items.");
	}

	/**
	 * Click on the Drop-down list icon of the 'Window' hour field, min field
	 * Verify The drop-down list shows a range of values correctly
	 */
	public void timeDropDownOptions(String xpath, String field, int range) {
		String times = null;
		final List<WebElement> options = new Select(selUtils.getObject(xpath))
				.getOptions();
		final int size = options.size();
		Assert.assertEquals(range, size, " " + field
				+ " Drop Down Options range is not correct");
		for (int cnti = 0; cnti < size; cnti++) {
			if (cnti < 10) {
				times = "0" + Integer.toString(cnti);
			} else {
				times = Integer.toString(cnti);
			}
			Assert.assertTrue(options.get(cnti).getText().contains(times), " "
					+ field + " Drop Down Options are not correct");
		}
		logger.info(" Verified That " + field
				+ " drop-down list shows a range of values correctly.");
	}
}