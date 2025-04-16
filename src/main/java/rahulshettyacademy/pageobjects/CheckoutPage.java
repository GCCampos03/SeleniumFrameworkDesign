package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryInput;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(css = ".ta-results")
	List<WebElement> countryList;
	
	@FindBy(css = ".ngx-spinner-overlay ng-tns-c31-2 ng-trigger ng-trigger-fadeIn ng-star-inserted")
	List<WebElement> waitingScreen;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement countryChoosen;

	public void selectCountry(String countryName) {
		waitForElementToDisppear(waitingScreen,5);
		countryInput.click();
		countryInput.sendKeys(countryName);
		waitForElementToAppear(countryList, 5);
		countryChoosen.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmationPage= new ConfirmationPage(driver);
		return confirmationPage;
	}

}
