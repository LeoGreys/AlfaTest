package AlfaTest.PO;

import AlfaTest.utilities.AndroidDriverConfig;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class AuthorizationPage {
    public static final class Elements {
        public static final By AUTHORIZATION_TITLE = AppiumBy.id("com.alfabank.qapp:id/tvTitle");
        public static final By AUTHORIZATION_LOGIN_INPUT = AppiumBy.id("com.alfabank.qapp:id/etUsername");
        public static final By AUTHORIZATION_PASSWORD_INPUT = AppiumBy.id("com.alfabank.qapp:id/etPassword");
        public static final By SHOW_PASSWORD_BUTTON = AppiumBy.id("com.alfabank.qapp:id/text_input_end_icon");
        public static final By AUTHORIZATION_CONFIRM_BUTTON = AppiumBy.id("com.alfabank.qapp:id/btnConfirm");

        public static final By AUTHORIZATION_VALIDATION_ERROR = AppiumBy.id("com.alfabank.qapp:id/tvError");

        public static final By SUCCESS_AUTHORIZATION_TITLE = AppiumBy.xpath("//android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView");
    }

    public static final class Texts {
        public static final String AUTHORIZATION_TITLE_TEXT = "Вход в Alfa-Test";
        public static final String AUTHORIZATION_TITLE_SUCCESS_TEXT = "Вход в Alfa-Test выполнен";
        public static final String INVALID_VALUE_TEXT = "Введены неверные данные";
    }

    public Boolean isElementDisplayed(By by) {
        return AndroidDriverConfig.driver.findElement(by).isDisplayed();
    }

    public String getElementText(By by) {
        return AndroidDriverConfig.driver.findElement(by).getText();
    }

    public void fillElement(By by, String textValue) {
        AndroidDriverConfig.driver.findElement(by).sendKeys(textValue);
    }

    public String getElementAttributeText(By by, String attribute) {
        return AndroidDriverConfig.driver.findElement(by).getAttribute(attribute);
    }

    public void clickOnElement(By by) {
        AndroidDriverConfig.driver.findElement(by).click();
    }

    public void waitForLoad() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
