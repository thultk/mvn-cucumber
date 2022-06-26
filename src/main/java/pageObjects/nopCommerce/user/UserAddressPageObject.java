package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.AddressesPageUI;

public class UserAddressPageObject extends BasePage {
	WebDriver driver;

	public UserAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getAddressInfoAtAddressList(String className) {
		waitForElementVisible(driver, AddressesPageUI.DYNAMIC_ADDRESS_INFO_BY_TEXT, className);
		if (className.equals("email")) {
			return getElementText(driver, AddressesPageUI.DYNAMIC_ADDRESS_INFO_BY_TEXT, className).substring(7);
		} else if (className.equals("phone")) {
			return getElementText(driver, AddressesPageUI.DYNAMIC_ADDRESS_INFO_BY_TEXT, className).substring(14);
		} else if (className.equals("fax")) {
			return getElementText(driver, AddressesPageUI.DYNAMIC_ADDRESS_INFO_BY_TEXT, className).substring(12);
		} else {
			return getElementText(driver, AddressesPageUI.DYNAMIC_ADDRESS_INFO_BY_TEXT, className);
		}
	}
}
