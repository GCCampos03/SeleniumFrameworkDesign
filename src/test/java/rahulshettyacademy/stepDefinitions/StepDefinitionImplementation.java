package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest {
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplictaion();
	}

	@Given("^Login in with username (.+) and password (.+)$")
	public void loginUsernamePassword(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) from Cart$")
	public void addProduct(String productName) {
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void submitOrder(String productName) {
		// Product Catalog - Go to the Cart Page
		CartPage cartPage = productCatalogue.goToCart();

		// CartPage - Verify Cart Products
		Boolean match = cartPage.VerifyProductIsDisplayed(productName);
		Assert.assertTrue(match);

		// CartPage - Go to Checkout
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		// CheckoutPage
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();

	}
	
	@Then("^(.+) is displayed on Confirmation Page$")
	public void confirmMessage(String message ) {
		String confirmMessage = confirmationPage.VerifyConfirmationMessage();
		Assert.assertEquals(confirmMessage, message);
		driver.quit();
	}

}
