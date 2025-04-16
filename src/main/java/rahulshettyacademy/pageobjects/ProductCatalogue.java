package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
WebDriver driver;
	
	public ProductCatalogue(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	By cartButton = By.cssSelector(".card-body button:last-of-type");
	
	@FindBy(css="#toast-container")
	WebElement confirmationMessage;
	
	public List<WebElement> getProductList(){
		waitForElementToAppear(products,5);
		
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		List<WebElement> products = getProductList();
		WebElement productChoosed= products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return productChoosed;
	}
	
	public void addProductToCart(String productName) {
		WebElement productChoosed = getProductByName(productName);
		productChoosed.findElement(cartButton).click();
		waitForElementToAppear(confirmationMessage,5);
	}
	
	

}
