package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import setups.BrowserSetup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static setups.BrowserPool.getBrowserInstance;

public class LoginStep {

	LoginPage login =new LoginPage(getBrowserInstance());
	Properties envi = new Properties();
	InputStream input = BrowserSetup.class.getClassLoader().getResourceAsStream("env.properties");
	

	/**
	 * ======== Scenario 1 Positive ========
	 **/
	
	@Given("User navigate to Login Page")
	public void UserNavigateToLoginPage() throws IOException {
		envi.load(input);
		String login = envi.getProperty("urlLogin");
		getBrowserInstance().navigate().to(login);
	}

	@When("User input correct credential")
	public void UserInputCorrectCredential() throws IOException {
		envi.load(input);
		String email = envi.getProperty("mainEmail");
		String password = envi.getProperty("mainPassword");
		login.inputEmail(email);
		login.inputPassword(password);
		login.loginButton();
	}

	@Then("Success login into page")
	public void SuccessLoginIntoPage(){
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
	
	@And("Success to logged out")
	public void SuccessToLoggedOut() {
		login.logoutButton();
		try {
			Assert.assertEquals(envi.getProperty("urlLogin"), login.getCurrentUrl());
		} catch (Exception e) {
			System.out.println("assertion not expected");
		}

	}

	/**
	 * ======== Scenario 3 Positive ========
	 **/

	@When("User click link text Sign Up")
	public void UserClickLinkTextSignUp() {
		login.signUpButton();
	}

	@Then("Success navigate to sign up page")
	public void successNavigateToSignUpPage() {
		try {
			Assert.assertEquals(envi.getProperty("urlRegister"), login.getCurrentUrl());
		} catch (Exception e) {
			System.out.println("assertion not expected");
		}
	}
	
	/**
	 * ======== Scenario 1 Negative ========
	 **/

    @When("^User input incorrect (.+) email$")
    public void UserInputIncorrect(String email) throws Throwable {
		envi.load(input);
		login.inputEmail(email);
		String password = envi.getProperty("mainPassword");
		login.inputPassword(password);
		login.loginButton();
	}

	@Then("Show popup incorrect credential")
	public void showPopUpIncorrectCredential() throws InterruptedException {
		login.incorrectAccountPopUp();
		try {
			Assert.assertEquals("Wrong email or password", login.incorrectAccountPopUp());
		} catch (Exception e) {
			System.out.println("assertion not expected");
		}
		login.OKButton();
	}

	/**
	 * ======== Scenario 2 Negative ========
	 **/

	@When("^User input incorrect (.+) password$")
	public void UserInputIncorrectPassword(String password) throws Throwable {
		envi.load(input);
		String email = envi.getProperty("mainEmail");
		login.inputEmail(email);
		login.inputPassword(password);
		login.loginButton();
	}

}
