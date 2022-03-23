package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

	private static WebDriver browser;

	public LoginPage(WebDriver browser) {
		this.browser = browser;
		PageFactory.initElements(browser, this);
	}

	private By emailUs = By.id("your_email");
	private By passwordUs = By.id("password");
	private By login = By.className("register");
	private By signUp = By.xpath("//*[text()='Sign Up']");
	private By success = By.xpath("//*[text()='Success']");
	private By incorrectAcc = By.xpath("//*[text()='Wrong email or password']");
	private By OK = By.xpath("//*[text()='OK']");
	private By logout = By.xpath("//*[text()='Logout']");

	public void inputEmail(String email) {
		browser.findElement(emailUs).sendKeys(email);
	}

	public void inputPassword(String password) {
		browser.findElement(passwordUs).sendKeys(password);
	}

	public void loginButton() {
		browser.findElement(login).click();
	}

	public void signUpButton() {
		browser.findElement(signUp).click();
	}

	public void logoutButton() {
		browser.findElement(logout).click();
	}

	public String validSuccessPopUp() {
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated((success)));
		return browser.findElement(success).getText();
	}

	public String incorrectAccountPopUp() {
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated((incorrectAcc)));
		return browser.findElement(incorrectAcc).getText();
	}

	public void OKButton() {
		browser.findElement(OK).click();
	}

	public String getCurrentUrl() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlContains(browser.getCurrentUrl()));
		return browser.getCurrentUrl();
	}
}

