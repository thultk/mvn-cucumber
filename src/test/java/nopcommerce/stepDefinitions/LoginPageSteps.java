package nopcommerce.stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class LoginPageSteps {
	WebDriver driver;
	UserLoginPageObject loginPage;
	TestContext testContext;

	public LoginPageSteps(TestContext testContext) {
		this.driver = Hooks.openAndQuitBrowser();
		this.testContext = testContext;
		loginPage = PageGeneratorManager.getUserLoginPage(driver);
	}

	@When("^Input to Email textbox in Log in page$")
	public void inputToEmailTextboxInLogInPage() {
		loginPage.inputToEmailTextbox((String) testContext.getDataContext().getContext(Context.USER_ID));

	}

	@When("^Input to Password textbox in Log in page$")
	public void inputToPasswordTextboxInLogInPage() {
		loginPage.inputToPasswordTextbox((String) testContext.getDataContext().getContext(Context.PASSWORD));
	}

	@When("^Click to Login button$")
	public void clickToLoginButton() {
		loginPage.clickToLoginButton();

	}
}
