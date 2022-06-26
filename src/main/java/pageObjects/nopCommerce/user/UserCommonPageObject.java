package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserBasePageUI;

public class UserCommonPageObject extends BasePage {
	WebDriver driver;

	public UserCommonPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void getHeaderPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
	}

	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ADDRESSES_LINK);
		clickToElement(driver, UserBasePageUI.ADDRESSES_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendKeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}

	public void selectDropdownByName(WebDriver driver, String dropdownName, String value) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
		selectItemInDefaultDropdown(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, value, dropdownName);
	}

	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}
}
