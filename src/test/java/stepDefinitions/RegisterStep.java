package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.LoginPage;
import pages.RegisterPage;
import setups.BrowserSetup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static setups.BrowserPool.getBrowserInstance;

public class RegisterStep {

	LoginPage login =new LoginPage(getBrowserInstance());
	RegisterPage register =new RegisterPage(getBrowserInstance());
	Properties envi = new Properties();
	InputStream input = BrowserSetup.class.getClassLoader().getResourceAsStream("env.properties");
	

	/**
	 * ======== Scenario 1 Positive ========
	 **/
	
	@Given("User navigate to Register Page")
	public void UserNavigateToRegisterPage() throws IOException {
		envi.load(input);
		String register = envi.getProperty("urlRegister");
		getBrowserInstance().navigate().to(register);
	}

	@When("^User set all fields (.+) (.+) (.+) (.+) (.+) (.+) (.+)$")
	public void UserSetAllFields(String first, String last, String email, String phone, String address, String password, String confpassword) throws IOException {
		register.setFirstName(first);
		register.setLastName(last);
		register.setEmail(email);
		register.setPhoneNumber(phone);
		register.setAddress(address);
		register.setPassword(password);
		register.setConfirmPwd(confpassword);
		register.registerButton();
	}

	@Then("Success registered into page")
	public void SuccessRegisteredIntoPage(){
		login.validSuccessPopUp();
		try {
			Assert.assertEquals("Success", login.validSuccessPopUp());
		} catch (Exception e) {
			System.out.println("assertion not expected");
		}
		login.OKButton();
	}

	/**
	 * ======== Scenario 2 Positive ========
	 **/

	@When("User click link text Login")
	public void UserClickLinkTexLogin() {
		register.loginButton();
	}

	@Then("Success navigate to Login page")
	public void successNavigateToLoginpage() {
		try {
			Assert.assertEquals(envi.getProperty("urlLogin"), login.getCurrentUrl());
		} catch (Exception e) {
			System.out.println("assertion not expected");
		}
	}


	/**
	 * ======== Scenario 1 Negative ========
	 **/

    @When("^User sets invalid (.+)$")
    public void UserSetsInvalid(String email) throws Throwable {
		register.setEmail(email);
		register.setPhoneNumber("123456");
		register.registerButton();
	}

	@Then("Show error message invalid email")
	public void showErrosMessageInvalidEmail() throws InterruptedException {
		register.setError();
		try {
			Assert.assertEquals("Please enter a valid email address.", register.setError());
		} catch (Exception e) {
			System.out.println("assertion not expected");
		}
	}

	/**
	 * ======== Scenario 2 Negative ========
	 **/

	@When("^User sets less six char (.+)$")
	public void UserSetsLessSixChar(String password) throws Throwable {
		register.setPassword(password);
		register.setAddress("jkt");
	}

	@Then("Show error message incorrect password")
	public void showErrosMessageIncorrectPassword() throws InterruptedException {
		register.setError();
		try {
			Assert.assertEquals("Please enter at least 6 characters.", register.setError());
		} catch (Exception e) {
			System.out.println("assertion not expected");
		}
	}

	/**
	 * ======== Scenario 3 Negative ========
	 **/

	@When("^User sets different confirm (.+)$")
	public void UserSetsDifferent(String password) throws Throwable {
		register.setPassword("123456");
		register.setConfirmPwd(password);
		register.setAddress("jkt");
	}

	@Then("Show error message not match password")
	public void showErrosMessageNotMatchPassword() throws InterruptedException {
		register.setError();
		try {
			Assert.assertEquals("Password need to match", register.setError());
		} catch (Exception e) {
			System.out.println("assertion not expected");
		}
	}

	/**
	 * ======== Scenario 4 Negative ========
	 **/

	@When("User directly to click Register Button")
	public void UserDirectlyToClick() throws Throwable {
		register.registerButton();
	}

	@Then("Show all error messages")
	public void ShowAllErrorMessages() throws InterruptedException {
		register.setErrorValid();
	}
}
