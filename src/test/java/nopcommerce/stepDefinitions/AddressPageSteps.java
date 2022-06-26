package nopcommerce.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Then;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.UserAddressPageObject;

public class AddressPageSteps {
	WebDriver driver;
	UserAddressPageObject addressPage;

	public AddressPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
	}

	@Then("^The valid text displays at \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void theValidTextDisplaysAtWithValue(String className, String displayedValue) {
		Assert.assertEquals(addressPage.getAddressInfoAtAddressList(className), displayedValue);
	}
}
