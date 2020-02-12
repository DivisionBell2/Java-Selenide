package tests.checkout;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import logic.driver.DesktopDriver;
import logic.pages.MainPage;
import logic.pages.ProductPage;

public class test016InvalidProductCodeInChannel implements DesktopDriver {
    MainPage mainPage;
    ProductPage productPage;

    @Parameters({"samsungUrl"})
    @BeforeMethod
    void setup(@Optional("linkName") String samsungUrl) {
        Selenide.open(samsungUrl);
        mainPage = new MainPage();
        productPage = new ProductPage();
    }

    @Test(description = "I check invalid device code in another channel")
    void test() {
        String invalidUrlWithCode = "/linkName";

        mainPage
                .moveToCheckoutPageWithInvalidCode(invalidUrlWithCode)
        ;

        productPage
                .checkDeviceImageBlockInvisible()
                .checkCheckoutButtonInvisible()
        ;
    }
}
