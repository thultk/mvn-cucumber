package nopcommerce.stepDefinitions;

import cucumber.api.java.en.When;

public class RegexSteps {
	@When("^I input (first|second) Account ID$")
	public void iInputFirstAccountID(String account) {

	}

	@When("^I (?:transfer|withdraw) to \"([^\"]*)\" USD$")
	public void iTransferToUSD(String amount) {

	}
}
