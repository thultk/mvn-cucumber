package nopcommerce.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.UserHomePageObject;

public class HomePageSteps {
	WebDriver driver;
	UserHomePageObject homePage;

	public HomePageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Given("^Open Register page$")
	public void openRegisterPage() {
		homePage.clickToRegisterLink();
	}

	@Then("^Verify My account link displays$")
	public void verifyMyAccountLinkDisplays() {
		Assert.assertTrue(homePage.isMyAccountDisplayed());
		// Assert.assertFalse("My account link is not displayed", homePage.isMyAccountDisplayed());
	}

	@When("^Click to Log in link$")
	public void clickToLogInLink() {
		homePage.clickToLoginLink();
	}
}
