package tests.checkout;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import logic.TextGenerator;
import logic.driver.DesktopDriver;
import logic.pages.CheckoutPage;
import logic.pages.MainPage;
import logic.pages.ProductPage;

public class test014CheckBasketId implements DesktopDriver, TextGenerator {
    private MainPage mainPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    void setUp() {
        mainPage = new MainPage();
        productPage = new ProductPage();
        checkoutPage = new CheckoutPage();
    }

    @Test(description = "I check that every time you add an item to the cart changes basketId")
    void test() {
        String basketIdCookie;
        String basketIdCookie2;

        mainPage
                .selectRandomProduct()
        ;
        productPage
                .clickCheckoutBtn()
        ;
        checkoutPage
                .checkFieldsPhoneAndEmailIsVisible()
        ;

        basketIdCookie = checkoutPage.getCookie();

        checkoutPage
                .clickLogo()
        ;
        mainPage
                .checkOpenMainPage()
                .selectProductCategoryBookmark("Samsung")
                .selectRandomProduct()
        ;
        productPage
                .clickCheckoutBtn()
        ;
        checkoutPage
                .checkFieldsPhoneAndEmailIsVisible()
        ;

        basketIdCookie2 = checkoutPage.getCookie();

        checkoutPage
                .checkCookiesAreDifferent(basketIdCookie, basketIdCookie2)
        ;
    }
}
