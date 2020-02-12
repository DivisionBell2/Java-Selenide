package tests.checkout;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import logic.TextGenerator;
import logic.driver.DesktopDriver;
import logic.pages.CheckoutPage;
import logic.pages.MainPage;
import logic.pages.ProductPage;

public class test012CheckOrderWithEmptyPhone implements DesktopDriver, TextGenerator {
    private MainPage mainPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    void setUp() {
        mainPage = new MainPage();
        productPage = new ProductPage();
        checkoutPage = new CheckoutPage();
    }

    @Test(description = "I check error displayed with empty phone")
    void test() {
        mainPage
                .selectRandomProduct()
        ;
        productPage
                .clickCheckoutBtn()
        ;
        checkoutPage
                .enterEmail(generateUniqEmail())
                .enterPhone("")
                .selectAllCheckboxes()
                .clickContinueButton()
                .waitForErrorMessageInput("Заполните все поля правильно")
        ;
    }
}
