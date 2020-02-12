package logic.driver;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;
import logic.Env;

import static com.codeborne.selenide.Selenide.open;

public interface DesktopDriver extends Capabilities, Env {
    @BeforeMethod(alwaysRun = true)
    default void initDriver() {
        String grid = getEnv("grid_url");
        if (grid.equals("default")) {
            initLocalCapabilities();
        } else {
            initRemoteCapabilities(grid);
        }
        open("linkName");

        Selenide.executeJavaScript("window.navigator.geolocation.getCurrentPosition = function(success){" +
                "var position = {\"coords\":{\"latitude\":\"555\",\"longitude\":\"999\"}};success(position);}");
    }
}