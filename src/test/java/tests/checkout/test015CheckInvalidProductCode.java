package tests.checkout;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import logic.TextGenerator;
import logic.driver.DesktopDriver;
import logic.pages.MainPage;
import logic.pages.ProductPage;

public class test015CheckInvalidProductCode implements DesktopDriver, TextGenerator {
    private MainPage mainPage;
    private ProductPage productPage;

    @BeforeMethod
    void setUp(){
        mainPage = new MainPage();
        productPage = new ProductPage();
    }

    @Test
    void test01() {
        String invalidUrlWithCode = "/linkName";

        mainPage
                .moveToCheckoutPageWithInvalidCode(invalidUrlWithCode)
        ;

        productPage
                .check404page()
        ;
    }
}
