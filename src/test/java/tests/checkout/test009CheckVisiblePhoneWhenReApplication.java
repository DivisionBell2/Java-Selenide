package tests.checkout;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import logic.TextGenerator;
import logic.driver.DesktopDriver;
import logic.pages.CheckoutPage;
import logic.pages.MainPage;
import logic.pages.ProductPage;

public class test009CheckVisiblePhoneWhenReApplication implements DesktopDriver, TextGenerator {
    private MainPage mainPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    void setUp() {
        mainPage = new MainPage();
        productPage = new ProductPage();
        checkoutPage = new CheckoutPage();
    }

    @Test(description = "I check the display of the entered phone when re-applying")
    void test() {
        String phone = generatePhone();

        mainPage
                .selectRandomProduct()
        ;
        productPage
                .clickCheckoutBtn()
        ;
        checkoutPage
                .enterEmail(generateUniqEmail())
                .enterPhone(phone)
                .selectAllCheckboxes()
                .clickContinueButton()
                .enterSMSCode("1111")
                .clickContinueButton()
                .checkOpenContactInformationPage()
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
                .checkEnteredPhoneIsVisible(phone)
        ;

    }
}
