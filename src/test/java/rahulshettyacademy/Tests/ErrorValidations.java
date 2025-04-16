package rahulshettyacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


import rahulshettyacademy.TestComponent.Retry;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)

	public void InvalidLogin() throws IOException {

		// Invalid Login
		landingPage.loginApplication("goncaloccampos@live.com.pt", "Passrd1");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test

	public void ProductErrorValidation() throws IOException {

		String productName = "ADIDAS ORIGINAL";

		// Login
		ProductCatalogue productCatalogue = landingPage.loginApplication("goncaloccampos@live.com.pt", "Password1");

		// Product Catalog - Add Product to the Cart
		productCatalogue.addProductToCart(productName);

		// Product Catalog - Go to the Cart Page
		CartPage cartPage = productCatalogue.goToCart();

		// CartPage - Verify Cart Products
		Boolean match = cartPage.VerifyProductIsDisplayed("NIKE");
		Assert.assertFalse(match);

	}
}
