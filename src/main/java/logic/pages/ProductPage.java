package logic.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {
    private SelenideElement elCheckoutBtn = $x("//span[text()=\"Оформить заявку\"]");
    private SelenideElement el404Info = $x("//div[@class=\"text-404\"]");
    private SelenideElement elCatalogImageBlock = $x("//app-catalog-detail-carousel");
    private SelenideElement elSamsungChannelCheckoutBtn = $x("//div[@class=\"btn-sticky\"]//span[contains(class, btn-order)]");

    @Step("I click 'checkout' btn")
    public ProductPage clickCheckoutBtn(){
        elCheckoutBtn.click();
        return this;
    }

    @Step("I check 404 page in catalog")
    public ProductPage check404page() {
        el404Info
                .waitUntil(Condition.visible, 5000);
        return this;
    }

    @Step("I check device image block is not visible")
    public ProductPage checkDeviceImageBlockInvisible() {
        elCatalogImageBlock
                .waitUntil(Condition.disappear, 5000);
        return this;
    }

    @Step("I check checkout button is not visible")
    public ProductPage checkCheckoutButtonInvisible() {
        elSamsungChannelCheckoutBtn
                .waitUntil(Condition.disappear, 5000);
        return this;
    }
}
