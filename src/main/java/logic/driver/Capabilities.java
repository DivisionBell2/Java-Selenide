package logic.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public interface Capabilities {
    default void initLocalCapabilities(){
        Configuration.timeout = 10000;
        Configuration.startMaximized = true;
        Configuration.driverManagerEnabled = true;
    }

    default void initRemoteCapabilities(String grid){
        Configuration.browser = "chrome";
        Configuration.browserVersion = "78.0";
        Configuration.remote = grid.concat("/wd/hub");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities = desiredCapabilities;
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().enableLogs(LogType.BROWSER, Level.ALL));
    }
}
