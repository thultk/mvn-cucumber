package commons;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductPreviewsPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;
import pageObjects.nopCommerce.user.UserSearchPageObject;
import pageObjects.nopCommerce.user.UserShippingAndReturnPageObject;
import pageObjects.nopCommerce.user.UserSiteMapPageObject;
import pageObjects.nopCommerce.user.UserWishlistPageObject;
import pageUIs.nopCommerce.user.UserBasePageUI;

public class BasePage {
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;

	protected static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageỦl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchToWindowByIDs(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String ID : allWindowIDs) {
			if (!ID.equals(windowID)) {
				driver.switchTo().window(ID);
			}
		}
	}

	public void switchToWindowByTitles(WebDriver driver, String windowTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String ID : allWindowIDs) {
			driver.switchTo().window(ID);
			if (driver.getTitle().equals(windowTitle)) {
				break;
			}
		}
	}

	public void closeAllWindows(WebDriver driver, String parentID) {
		Set<String> listWindowIDs = driver.getWindowHandles();
		for (String ID : listWindowIDs) {
			if (!ID.equals(parentID)) {
				driver.switchTo().window(ID).close();
			}
			driver.switchTo().window(parentID);
		}
	}

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	public WebElement getWebElement(WebDriver driver, String xpathLocator, String... params) {
		return driver.findElement(getByXpath(getDynamicLocator(xpathLocator, params)));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(By.xpath(xpathLocator));
	}

	public void clickToElement(WebDriver driver, String xpathLocator) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, xpathLocator);
			sleepInSecond(2);
		} else {
			getWebElement(driver, xpathLocator).click();
		}
	}

	public String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void clickToElement(WebDriver driver, String xpathLocator, String... params) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, xpathLocator, params);
			sleepInSecond(2);
		} else {
			getWebElement(driver, getDynamicLocator(xpathLocator, params)).click();
		}
	}

	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void pressKey(WebDriver driver, String xpathLocator, Keys key, String... params) {
		WebElement element = getWebElement(driver, xpathLocator, params);
		element.sendKeys(key);
	}

	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue, String... params) {
		WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem, String... params) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(xpathLocator, params)));
		select.selectByVisibleText(textItem);
	}

	public String getFirstSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	public String getFirstSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator, String... params) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(xpathLocator, params)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}

	public void actionsOnDropdownList(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		JavascriptExecutor jsExecuter = (JavascriptExecutor) driver;
		List<WebElement> listItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));
		for (WebElement element : listItems) {
			if (element.getText().equals(expectedTextItem)) {
				System.out.print(element.getText());
				jsExecuter.executeScript("arguments[0].scrollIntoView(true);", element);
				sleepInSecond(2);
				element.click();
				break;
			}
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);

		} catch (InterruptedException e) {

		}
	}

	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}

	public String getElementText(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).getText().trim();
	}

	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	public int getElementSize(WebDriver driver, String xpathLocator, String... params) {
		return getListWebElement(driver, getDynamicLocator(xpathLocator, params)).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator, String... params) {
		WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));
		if (!element.isSelected()) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(driver, xpathLocator, params);
				sleepInSecond(3);
			} else {
				element.click();
			}
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		try {
			System.out.println("Start time" + new Date().toString());
			overrideGlobalTimeout(driver, shortTimeOut);
			return getWebElement(driver, xpathLocator).isDisplayed();
		} catch (Exception e) {
			return false;
		} finally {
			overrideGlobalTimeout(driver, longTimeOut);
			System.out.println("End time" + new Date().toString());
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, shortTimeOut);
		System.out.println("Start time" + new Date().toString());
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		overrideGlobalTimeout(driver, longTimeOut);
		if (elements.size() == 0) {
			System.out.println("Element does not display and does not exist in DOM");
			System.out.println("End time" + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !(elements.get(0).isDisplayed())) {
			System.out.println("Element does not display but exist in DOM");
			System.out.println("End time" + new Date().toString());
			return true;
		} else {
			System.out.println("Element displays and exists in DOM");
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator, String... params) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicLocator(xpathLocator, params))).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style',arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style',arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator, String... params) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicLocator(xpathLocator, params)));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String attributeRemove, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String attributeRemove, String xpathLocator, String... params) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, getDynamicLocator(xpathLocator, params)));
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	public boolean areExpectedTextInInnnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !='undefined' && arguments[0].naturalWidth >0  ", getWebElement(driver, xpathLocator));
	}

	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		JavascriptExecutor jsExecuter = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecuter.executeScript("return(window.jQuery!=null) && (jQuery.active==0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void waitForElementVisibleFluentWait(WebDriver driver, String xpathLocator, String... params) {
		FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(15000)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);
		fluentDriver.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}

	public void waitForElementInVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public void waitForElementInVisible(WebDriver driver, String xpathLocator, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void waitForAllElementsInVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public UserMyProductPreviewsPageObject openMyProductPreviewsPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.MY_PRODUCT_REVIEWS_LINK);
		clickToElement(driver, UserBasePageUI.MY_PRODUCT_REVIEWS_LINK);
		return PageGeneratorManager.getUserProductReviewsPage(driver);
	}

	public UserRewardPointsPageObject openRewardPointsPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.REWARD_POINTS_LINK);
		clickToElement(driver, UserBasePageUI.REWARD_POINTS_LINK);
		return PageGeneratorManager.getUserRewardPointsPage(driver);
	}

	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ADDRESSES_LINK);
		clickToElement(driver, UserBasePageUI.ADDRESSES_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserCustomerInfoPageObject openCustomerInfoPageOnHeader(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.USER_LOGOUT_LINK);
		clickToElement(driver, UserBasePageUI.USER_LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public UserSearchPageObject openSearchPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.SEARCH_LINK);
		clickToElement(driver, UserBasePageUI.SEARCH_LINK);
		return PageGeneratorManager.getUserSearchPage(driver);
	}

	public UserShippingAndReturnPageObject openShippingAndSearchPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.SHIPPING_AND_RETURN_LINK);
		clickToElement(driver, UserBasePageUI.SHIPPING_AND_RETURN_LINK);
		return PageGeneratorManager.getUserShippingAndReturnPage(driver);
	}

	public UserSiteMapPageObject openSiteMapPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.SITEMAP_LINK);
		clickToElement(driver, UserBasePageUI.SITEMAP_LINK);
		return PageGeneratorManager.getUserSiteMapPage(driver);
	}

	public UserWishlistPageObject openWishlistPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.HEADER_WISHLIST_LINK);
		clickToElement(driver, UserBasePageUI.HEADER_WISHLIST_LINK);
		return PageGeneratorManager.getUserWishlistPage(driver);
	}

	public UserLoginPageObject openLoginPageObject(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.FOOTER_MY_ACCOUNT_LINK);
		clickToElement(driver, UserBasePageUI.FOOTER_MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public BasePage openFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		if (pageName.equals("Search")) {
			return PageGeneratorManager.getUserSearchPage(driver);
		} else if (pageName.equals("My account")) {
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		} else {
			return PageGeneratorManager.getUserHomePage(driver);
		}
	}

	public void getFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
	}

	public void getHeaderPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
	}

	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendKeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}

	public void clickToRadioButtonByID(WebDriver driver, String radioButtonID) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonID);
		clickToElement(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonID);
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
