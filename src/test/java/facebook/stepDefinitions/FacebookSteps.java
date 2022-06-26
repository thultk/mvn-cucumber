package facebook.stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookSteps {
	WebDriver driver;

	@Before("@parameter")
	public void openFacebookApplication() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After("@parameter")
	public void closeApplication() {
		driver.quit();
	}

	@Given("^Input to Username textbox$")
	public void inputToUsernameTextbox() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("auto@gmail.com");
	}

	@Given("^Input to Password textbox$")
	public void inputToPasswordTextbox() {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("123456");
	}

	@Given("^Input to Username textbox with \"([^\"]*)\"$")
	public void inputToUsernameTextboxWith(String email) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
	}

	@Given("^Input to Password textbox with \"([^\"]*)\"$")
	public void inputToPasswordTextboxWith(String password) {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@Given("^Input to Username textbox with ([^\"]*)$")
	public void inputToUsernameTextbox(String email) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
	}

	@Given("^Input to Password textbox with ([^\"]*)$")
	public void inputToPasswordTextbox(String password) {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@Given("^Input to Username with \"([^\"]*)\" and Password with \"([^\"]*)\"$")
	public void inputToUsernameWithAndPasswordWith(String email, String password) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@Given("^Input to Username and Password$")
	public void inputToUsernameAndPassword(DataTable table) {
		// List<Map<String, String>> data = table.asMaps(String.class, String.class);
		for (Map<String, String> loginInfo : table.asMaps(String.class, String.class)) {
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(loginInfo.get("Username"));

			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys(loginInfo.get("Password"));
		}
	}

	@Given("^Click to Submit button$")
	public void clickToSubmitButton() {
		driver.findElement(By.name("login")).click();
	}
}
