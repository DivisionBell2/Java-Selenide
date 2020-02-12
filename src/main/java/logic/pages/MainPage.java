package logic.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import logic.WebElements;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MainPage implements WebElements {
    private ElementsCollection elProducts = $$(byXpath("//app-product-card"));
    private ElementsCollection elProductCategoryTabs = $$(byXpath("//app-home-products//app-tabs-flow-in//span[contains(@class,\"tab-item\")]"));
    private SelenideElement elBanner = $(byXpath("//div[@class=\"banner-top\"]"));

    @Step("I select random product")
    public MainPage selectRandomProduct() {
        selectRandomElementFromCollection(elProducts.shouldBe(CollectionCondition.sizeGreaterThan(0))).click();
        return this;
    }

    @Step("I select product category bookmark {categoryName}")
    public MainPage selectProductCategoryBookmark(String categoryName) {
        selectElementFromCollectionByText(
                elProductCategoryTabs.shouldBe(CollectionCondition.sizeGreaterThan(0)),
                categoryName)
                .click();
        return this;
    }

    @Step("I check open Main page")
    public MainPage checkOpenMainPage() {
        elBanner
                .waitUntil(Condition.visible, 10000);
        return this;
    }

    @Step("I select product with invalid code and go to checkoutPage")
    public MainPage moveToCheckoutPageWithInvalidCode(String catalogURL) {
        String url = WebDriverRunner.url();
        open(url + catalogURL);
        return this;
    }
}
