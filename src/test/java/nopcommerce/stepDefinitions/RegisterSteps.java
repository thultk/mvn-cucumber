package nopcommerce.stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import org.junit.Assert;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataUtil;

public class RegisterSteps {
	WebDriver driver;
	String email, password;
	TestContext testContext;
	UserRegisterPageObject registerPage;
	DataUtil dataTest;

	public RegisterSteps(TestContext testContext) {
		this.driver = Hooks.openAndQuitBrowser();
		registerPage = PageGeneratorManager.getUserRegisterPage(driver);
		dataTest = DataUtil.getDataUtil();
		testContext.getDataContext().setContext(Context.USER_ID, dataTest.getEmailAddress());
		password = dataTest.getPassword();
		this.testContext = testContext;
	}

	@When("^Input to First name textbox$")
	public void inputToFirstNameTextbox() {
		registerPage.inputToFirstnameTextbox(dataTest.getFirstName());
	}

	@When("^Input to Last name textbox$")
	public void inputToLastNameTextbox() {
		registerPage.inputToLastnameTextbox(dataTest.getLastName());
	}

	@When("^Input to Email textbox$")
	public void inputToEmailTextbox() {
		registerPage.inputToEmailTextbox(dataTest.getEmailAddress());
	}

	@When("^Input to Password textbox$")
	public void inputToPasswordTextbox() {
		registerPage.inputToPasswordTextbox(dataTest.getPassword());
	}

	@When("^Input to Confirm password textbox$")
	public void inputToConfirmPasswordTextbox() {
		registerPage.inputToConfirmPasswordTextbox(dataTest.getPassword());
	}

	@When("^Click to Register button$")
	public void clickToRegisterButton() {
		registerPage.clickToRegisterButton();
	}

	@Then("^Verify success message displays$")
	public void verifySuccessMessageDisplays() {
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@When("^Click to Log out link$")
	public void clickToLogOutLink() {
		registerPage.clickToLogoutLink();
	}

}
