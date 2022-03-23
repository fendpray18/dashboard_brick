package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegisterPage {

	private static WebDriver browser;

	public RegisterPage(WebDriver browser) {
		this.browser = browser;
		PageFactory.initElements(browser, this);
	}


	int getIndex = 1;
	int index = 0;

	private By firstName = By.id("firstName");
	private By lastName = By.id("lastName");
	private By emailData = By.id("email");
	private By phoneNumber = By.id("phoneNumber");
	private By countryCode = By.className("iti__selected-flag");
	private By listCountryCode = By.xpath("//*[contains(@class,'iti__country-name')]");
	private By addressData = By.cssSelector("input#address.input-text");
	private By pwd = By.id("password");
	private By confirm_pwd = By.id("confirm_password");
	private By register = By.className("register");
	private By login = By.xpath("//*[text()='Login']");
	private By errorMessage = By.xpath("//*[@class='error']");
	private By success = By.xpath("//*[text()='Success']");
	private By OK = By.xpath("//*[text()='OK']");

	public void setFirstName(String firstNm) {
		browser.findElement(firstName).sendKeys(firstNm);
	}

	public void setLastName(String lastNm) {
		browser.findElement(lastName).sendKeys(lastNm);
	}

	public void setEmail(String email) {
		browser.findElement(emailData).sendKeys(email);
	}

	public void setPhoneNumber(String phone) {
		browser.findElement(phoneNumber).sendKeys(phone);
	}

	public void chooseCountryCode() {
		browser.findElement(countryCode).click();
	}

	private void setCountryCode(String country) {
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(listCountryCode));
		List<WebElement> countCode = browser.findElements(listCountryCode);
		for (WebElement code : countCode) {
			String codes = code.getText();
			if (codes.equals(country)) {
				code.click();
				//System.out.println(" =" + getIndex);
				break;
			} else {
				getIndex++;
			}
		}
		System.out.println("index =" + getIndex);
	}

	public void setAddress(String address) {
		browser.findElement(addressData).sendKeys(address);
	}

	public void setPassword(String password) {
		browser.findElement(pwd).sendKeys(password);
	}

	public void setConfirmPwd(String confirmPwd) {
		browser.findElement(confirm_pwd).sendKeys(confirmPwd);
	}

	public void registerButton() {
		browser.findElement(register).click();
	}

	public void loginButton() {
		browser.findElement(login).click();
	}

	public void setErrorValid() {
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated((errorMessage)));
		List<WebElement> errorCount= browser.findElements(errorMessage);
		String[] getData = new String[errorCount.size()];

		for(WebElement error : errorCount){
			getData[index++]=error.getText();
			System.out.println(error.getText());
		}
	}

	public String setError() {
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated((errorMessage)));
		return browser.findElement(errorMessage).getText();
	}

	public String validSuccessPopUp() {
		WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated((success)));
		return browser.findElement(success).getText();
	}

	public void OKButton() {
		browser.findElement(OK).click();
	}
}

