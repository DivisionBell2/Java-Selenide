package logic.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import logic.TextGenerator;
import logic.WebElements;
import logic.api.SelfieUploader;

import java.io.File;
import java.util.Set;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class CheckoutPage implements TextGenerator, SelfieUploader, WebElements {
    private SelenideElement elPhoneInput = $(byXpath("//input[@name=\"mobilePhone\"]"));
    private SelenideElement elEmailInput = $(byXpath("//input[@name=\"email\"]"));
    private ElementsCollection elCheckBoxes = $$(byXpath("//app-ui-checkbox/div"));
    private SelenideElement elSMSCodeInput = $(byXpath("//input[@max=\"9999\"]"));
    private SelenideElement elSelfieInput = $(byXpath("//input[@name=\"selfie-loaded\"]"));
    private SelenideElement elPassportInput = $(byXpath("//input[@type=\"file\"]"));
    private SelenideElement elUploadedPassportImage = $(byXpath("//img[contains(@class,\"trig\")]"));
    private SelenideElement elContinueButton = $(byXpath("//button"));
    private SelenideElement elAddressInput = $(byXpath("//input[@name=\"reg-add\"]"));
    private SelenideElement elSalaryInput = $(byXpath("//input[@name=\"monthlySalary\"]"));
    private SelenideElement elOccupationTypeBtn = $(byXpath("//div[@class=\"select__value\"]"));
    private ElementsCollection elOccupationTypeItemsList = $$(byXpath("//div[contains(@class,\"select__item ng-star-inserted\")]"));
    private SelenideElement elSuccess = $(byXpath("//div[@class=\"result-recognize__text-green ng-star-inserted\"]"));
    private SelenideElement elOrderedItem = $(byXpath("//div[contains(@class, \"order-list__item\")]"));
    private SelenideElement elFormBlockError = $(byXpath("//app-block-form-errors"));
    private SelenideElement elPassportForm = $(byXpath("//app-form-file-passport"));
    private SelenideElement elLogo = $(byXpath("//div[@class=\"forward\"]"));
    private SelenideElement elFile = $(byXpath("//div[@class=\"file\"]"));
    private SelenideElement elApplicationCancelled = $(byXpath("//div[@class=\"error\"]/following-sibling::h2"));
    private SelenideElement elSurnameInput = $x("//input[@name=\"surname\"]");

    @Step("I select random element from occupation list")
    public CheckoutPage selectRandomOccupation() {
        selectRandomElementFromCollection(elOccupationTypeItemsList.shouldBe(CollectionCondition.sizeGreaterThan(0))).click();
        return this;
    }

    @Step("I click occupation button")
    public CheckoutPage clickOccupationBtn() {
        elOccupationTypeBtn.click();
        return this;
    }

    @Step("I enter address {address}")
    public CheckoutPage enterAddress(String address) {
        elAddressInput
                .waitUntil(Condition.visible, 15000)
                .sendKeys(address);
        sleep(1000);
        elAddressInput.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("I enter salary {salary}")
    public CheckoutPage enterSalary(Integer salary) {
        elSalaryInput.sendKeys(salary.toString());
        return this;
    }

    @Step("I click continue button")
    public CheckoutPage clickContinueButton() {
        elContinueButton
                .shouldBe(Condition.enabled)
                .click();
        return this;
    }

    @Step("I check button 'Continue' not Active")
    public CheckoutPage checkBtnContinueNotActive() {
        elContinueButton
                .shouldBe(Condition.visible)
                .shouldBe(Condition.disabled);
        return this;
    }

    @Step("I upload passport")
    public CheckoutPage uploadPassportImage() {
        elPassportInput
                .uploadFile(new File("src/main/resources/pass.jpeg"));
        elUploadedPassportImage
                .waitUntil(Condition.visible, 10000)
        ;
        elSurnameInput
                .waitUntil(Condition.visible, 15000);
        return this;
    }

    @Step("I upload invalid passport image")
    public CheckoutPage uploadInvalidPassportImage() {
        elPassportInput
                .uploadFile(new File("src/main/resources/invalidPass.png"));
        return this;
    }

    @Step("I upload selfie")
    public CheckoutPage uploadSelfie() {
        Set<Cookie> mp = WebDriverRunner.getWebDriver().manage().getCookies();
        mp.forEach(
                val -> {
                    if (val.getName().equals("fwd_basketId")) {
                        selfieUploadApi(val.getValue(),
                                executeJavaScript(String.format("return window.localStorage.getItem('%s');", "auth-token")).toString());
                    }
                }
        );
        refresh();
        return this;
    }

    @Step("I enter phone {phone}")
    public CheckoutPage enterPhone(String phone) {
        elPhoneInput
                .sendKeys(phone);
        return this;
    }

    @Step("I enter email {email}")
    public CheckoutPage enterEmail(String email) {
        elEmailInput
                .waitUntil(Condition.visible, 15000)
                .sendKeys(email);
        return this;
    }

    @Step("I select all checkboxes")
    public CheckoutPage selectAllCheckboxes() {
        elCheckBoxes
                .filter(Condition.visible)
                .forEach(SelenideElement::click);
        return this;
    }

    @Step("I enter sms code {smsCode}")
    public CheckoutPage enterSMSCode(String smsCode) {
        elSMSCodeInput
                .sendKeys(smsCode);
        return this;
    }

    @Step("I wait for error {message} is visible")
    public CheckoutPage waitForErrorMessageInput(String message) {
        $(byText(message))
                .waitUntil(Condition.visible, 10000)
                .shouldBe(Condition.text(message));
        return this;
    }

    @Step("I wait for fill form error message {message}")
    public CheckoutPage waitForFillFormErrorMessage(String message) {
        elFormBlockError
                .waitUntil(Condition.visible, 10000)
                .shouldBe(Condition.text(message));
        return this;
    }

    @Step("I check that address input is visible")
    public CheckoutPage checkAddressInputVisible() {
        elAddressInput.shouldBe(Condition.not(Condition.disappear));
        return this;
    }

    @Step("I check that passport form is visible")
    public CheckoutPage checkPassportFormVisible() {
        elPassportForm.shouldBe(Condition.not(Condition.disappear));
        return this;
    }

    @Step("I check passport input is not visible")
    public void waitForPassportInputNotExist() {
        elPassportInput.shouldBe(Condition.not(Condition.visible));
    }

    @Step("I check that order item has {expectedName} name")
    public CheckoutPage checkOrderItemName(String expectedName) {
        elOrderedItem
                .shouldBe(Condition.text(expectedName));
        return this;
    }

    @Step("I check open page for added contact information")
    public CheckoutPage checkOpenContactInformationPage() {
        elFile.waitUntil(Condition.visible, 10000);
        return this;
    }

    @Step("I check fields phone and email is Visible")
    public CheckoutPage checkFieldsPhoneAndEmailIsVisible() {
        elPhoneInput.waitUntil(Condition.visible, 10000);
        elEmailInput.waitUntil(Condition.visible, 10000);
        return this;
    }

    @Step("I click logo")
    public CheckoutPage clickLogo() {
        elLogo.click();
        return this;
    }

    public CheckoutPage checkEnteredPhoneIsVisible(String phone) {
        Assert.assertEquals(elPhoneInput.getAttribute("value").replaceAll("\\D", ""), "7".concat(phone));
        return this;
    }

    @Step("I check open page 'application canceled'")
    public CheckoutPage checkOpenPageApplicationCanceled(String message) {
        elApplicationCancelled
                .waitUntil(Condition.visible, 10000)
                .shouldBe(Condition.text(message));
        return this;

    }

    @Step("I get cookie 'fwd_basketId'")
    public String getCookie() {
        return WebDriverRunner.getWebDriver().manage().getCookieNamed("fwd_basketId").getValue();
    }

    @Step("I check that the cookies 'fwd_basketId' are different")
    public CheckoutPage checkCookiesAreDifferent(String cookie1, String cookie2) {
        Assert.assertNotEquals(cookie1, cookie2);
        return this;
    }


}