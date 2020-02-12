package tests.checkout;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import logic.TextGenerator;
import logic.driver.DesktopDriver;
import logic.pages.CheckoutPage;
import logic.pages.MainPage;
import logic.pages.ProductPage;

public class test008NegativeOrderWithInvalidPIN implements DesktopDriver, TextGenerator {
    private MainPage mainPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    void setUp() {
        mainPage = new MainPage();
        productPage = new ProductPage();
        checkoutPage = new CheckoutPage();
    }

    @Test(description = "I enter the wrong code 5 times, check that the application is canceled ")
    void test() {

        mainPage
                .selectRandomProduct()
        ;

        productPage
                .clickCheckoutBtn()
        ;

        checkoutPage
                .enterEmail(generateUniqEmail())
                .enterPhone(generatePhone())
                .selectAllCheckboxes()
                .clickContinueButton();
        for (int i = 0; i < 5; i++) {
        checkoutPage
                .enterSMSCode("1234")
                .clickContinueButton()
                .waitForErrorMessageInput("Укажите корректный код из смс.");
        }
        checkoutPage
                .enterSMSCode("1234")
                .clickContinueButton()
                .checkOpenPageApplicationCanceled("Заявка была аннулирована")
        ;
    }
}
