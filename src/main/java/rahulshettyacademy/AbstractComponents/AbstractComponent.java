package rahulshettyacademy.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	JavascriptExecutor js ;
	

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement myOrders;

	public void waitForElementToAppear(WebElement findBy, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToAppear(List<WebElement> findBy, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

		wait.until(ExpectedConditions.visibilityOfAllElements(findBy));
	}
	
	public void waitForElementToDisppear(List<WebElement> findBy, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

		wait.until(ExpectedConditions.invisibilityOfAllElements(findBy));
	}
	public CartPage goToCart() {
		js.executeScript("arguments[0].click();", cartHeader);
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		js.executeScript("arguments[0].click();", myOrders);
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	

}
