package AlfaTest.utilities;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidDriverConfig {
    public static AndroidDriver driver;

    public void init() {
        File appParentDirectory = new File("src/main/resources");
        File app = new File(appParentDirectory, "app-debug.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:appPackage", "com.alfabank.qapp");
        capabilities.setCapability("appium:appActivity", "com.alfabank.qapp.presentation.MainActivity");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:noReset", false);
        capabilities.setCapability("appium:app", app.getAbsolutePath());

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
    }

    public void stop() {
        driver.quit();
    }
}

