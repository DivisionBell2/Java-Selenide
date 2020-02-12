package tests.checkout;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import logic.TextGenerator;
import logic.driver.DesktopDriver;
import logic.pages.CheckoutPage;
import logic.pages.MainPage;
import logic.pages.ProductPage;

public class test007NegativeOrderWithInvalidPassport implements DesktopDriver, TextGenerator {
    private MainPage mainPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    void setUp(){
        mainPage = new MainPage();
        productPage = new ProductPage();
        checkoutPage = new CheckoutPage();
    }

    @Test
    void test01(){
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
                .clickContinueButton()
                .enterSMSCode("1111")
                .clickContinueButton()
                .uploadInvalidPassportImage()
                .waitForErrorMessageInput("Загрузите корректное фото паспорта.")
                .enterSalary(generateInt(10000000))
                .clickOccupationBtn()
                .selectRandomOccupation()
                .clickContinueButton()
                .waitForFillFormErrorMessage("Пожалуйста, заполните все выделенные поля")
                .waitForFillFormErrorMessage("Паспорт не загружен.")
                .checkPassportFormVisible()
        ;
    }
}
