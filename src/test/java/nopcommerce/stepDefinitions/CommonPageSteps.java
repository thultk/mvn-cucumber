package nopcommerce.stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.UserCommonPageObject;

public class CommonPageSteps {
	WebDriver driver;
	UserCommonPageObject commonPage;

	public CommonPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		commonPage = PageGeneratorManager.getUserCommonPage(driver);
	}

	@Given("^Open \"([^\"]*)\" page$")
	public void openPage(String pageName) {
		commonPage.getHeaderPageByName(driver, pageName);
	}

	@Given("^Open Address page$")
	public void openAddressPage() {
		commonPage.openAddressPage(driver);
	}

	@When("^Click to \"([^\"]*)\" button$")
	public void clickToButton(String buttonText) {
		commonPage.clickToButtonByText(driver, buttonText);
	}

	@When("^Input to \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
	public void inputToTextboxWithValue(String textboxID, String inputValue) {
		commonPage.inputToTextboxByID(driver, textboxID, inputValue);
	}

	@When("^Select in \"([^\"]*)\" dropdown with value \"([^\"]*)\"$")
	public void selectInDropdownWithValue(String dropdownName, String inputValue) {
		commonPage.selectDropdownByName(driver, dropdownName, inputValue);
	}
}
