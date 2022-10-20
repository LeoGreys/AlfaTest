package AlfaTest.tests;

import AlfaTest.PO.AuthorizationPage;
import AlfaTest.utilities.AndroidDriverConfig;
import org.testng.Assert;
import org.testng.annotations.*;

public class AuthorizationTest extends AuthorizationPage {
    protected static final AndroidDriverConfig ad = new AndroidDriverConfig();

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        ad.init();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        ad.stop();
    }

    final String LOGIN = "Login";
    final String LOGIN_LOWER_CASE = "login";
    final String LOGIN_UPPER_CASE = "LOGIN";
    final String PASSWORD = "Password";
    final String PASSWORD_LOWER_CASE = "password";
    final String PASSWORD_UPPER_CASE = "PASSWORD";
    final String DEFAULT_LOGIN_PROMPT = "Логин";
    final String DEFAULT_PASSWORD_PROMPT = "Пароль";
    final String INCORRECT_LOGIN_VALUE = "123%^&";
    final String INCORRECT_LONG_PASSWORD_VALUE = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m";

    @Test(description = "Проверка авторизации с корректными параметрами")
    public void verificationAuthorizationWithCorrectParameters() {

        /* Окно авторизации открыто. Заголовок с текстом «Вход в Alfa-Test» отображается. Поля «Логин» и «Пароль» отображаются.
        Кнопка «Показать пароль» в поле «Пароль» отображается. Кнопка «Войти» отображается. */
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_TITLE));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_TITLE), Texts.AUTHORIZATION_TITLE_TEXT);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_LOGIN_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_PASSWORD_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.SHOW_PASSWORD_BUTTON));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_CONFIRM_BUTTON));

//      Значение введено и отображается корректно.
        fillElement(Elements.AUTHORIZATION_LOGIN_INPUT, LOGIN);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_LOGIN_INPUT), LOGIN);

//      Значение введено и скрыто символами '•'.
        fillElement(Elements.AUTHORIZATION_PASSWORD_INPUT, PASSWORD);
        Assert.assertEquals(getElementAttributeText(Elements.AUTHORIZATION_PASSWORD_INPUT, "password"), "true");

//      Значение, введенное в поле «Пароль», не скрыто и отображается корректно.
        clickOnElement(Elements.SHOW_PASSWORD_BUTTON);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_PASSWORD_INPUT), PASSWORD);
        Assert.assertEquals(getElementAttributeText(Elements.AUTHORIZATION_PASSWORD_INPUT, "password"), "false");

//      Значение, введенное в поле «Пароль» скрыто символами '•'.
        clickOnElement(Elements.SHOW_PASSWORD_BUTTON);
        Assert.assertEquals(getElementAttributeText(Elements.AUTHORIZATION_PASSWORD_INPUT, "password"), "true");

        /* Вход выполнен и открыта страница успешной авторизации.
           Текст «Вход в Alfa-Test выполнен» отображается на экране.*/
        clickOnElement(Elements.AUTHORIZATION_CONFIRM_BUTTON);
        Assert.assertTrue(isElementDisplayed(Elements.SUCCESS_AUTHORIZATION_TITLE));
        Assert.assertEquals(getElementText(Elements.SUCCESS_AUTHORIZATION_TITLE), Texts.AUTHORIZATION_TITLE_SUCCESS_TEXT);
    }

    @Test(description = "Проверка авторизации с пустыми полями")
    public void verificationAuthorizationWithoutParameters() {

        /* Окно авторизации открыто. Заголовок с текстом «Вход в Alfa-Test» отображается. Поля «Логин» и «Пароль» отображаются.
        Кнопка «Показать пароль» в поле «Пароль» отображается. Кнопка «Войти» отображается. */
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_TITLE));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_TITLE), Texts.AUTHORIZATION_TITLE_TEXT);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_LOGIN_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_PASSWORD_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.SHOW_PASSWORD_BUTTON));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_CONFIRM_BUTTON));

//      Вход не выполнен. Выведено сообщение валидации «Введены неверные данные».
        clickOnElement(Elements.AUTHORIZATION_CONFIRM_BUTTON);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_VALIDATION_ERROR));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_VALIDATION_ERROR), Texts.INVALID_VALUE_TEXT);

//      Значение в поле «Логин» введено и отображается корректно. Поле «Пароль» не заполнено.
        fillElement(Elements.AUTHORIZATION_LOGIN_INPUT, LOGIN);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_LOGIN_INPUT), LOGIN);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_PASSWORD_INPUT), DEFAULT_PASSWORD_PROMPT);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_VALIDATION_ERROR), "");

//      Вход не выполнен. Выведено сообщение валидации «Введены неверные данные».
        clickOnElement(Elements.AUTHORIZATION_CONFIRM_BUTTON);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_VALIDATION_ERROR));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_VALIDATION_ERROR), Texts.INVALID_VALUE_TEXT);

//      Значение, введенное в поле «Пароль» скрыто символами '•'. Поле «Логин» не заполнено.
        fillElement(Elements.AUTHORIZATION_LOGIN_INPUT, "");
        fillElement(Elements.AUTHORIZATION_PASSWORD_INPUT, PASSWORD);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_LOGIN_INPUT), DEFAULT_LOGIN_PROMPT);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_PASSWORD_INPUT), PASSWORD);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_VALIDATION_ERROR), "");

//      Вход не выполнен. Выведено сообщение валидации «Введены неверные данные».
        clickOnElement(Elements.AUTHORIZATION_CONFIRM_BUTTON);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_VALIDATION_ERROR));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_VALIDATION_ERROR), Texts.INVALID_VALUE_TEXT);
    }

    @Test(description = "Проверка авторизации с некорректным параметром поля «Логин» - запрещенные символы")
    public void verificationAuthorizationWithForbiddenSymbols() {

        /* Окно авторизации открыто. Заголовок с текстом «Вход в Alfa-Test» отображается. Поля «Логин» и «Пароль» отображаются.
        Кнопка «Показать пароль» в поле «Пароль» отображается. Кнопка «Войти» отображается. */
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_TITLE));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_TITLE), Texts.AUTHORIZATION_TITLE_TEXT);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_LOGIN_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_PASSWORD_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.SHOW_PASSWORD_BUTTON));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_CONFIRM_BUTTON));

//      Значение введено и отображается корректно.
        fillElement(Elements.AUTHORIZATION_LOGIN_INPUT, INCORRECT_LOGIN_VALUE);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_LOGIN_INPUT), INCORRECT_LOGIN_VALUE);

//      Значение введено и скрыто символами '•'.
        fillElement(Elements.AUTHORIZATION_PASSWORD_INPUT, PASSWORD);
        Assert.assertEquals(getElementAttributeText(Elements.AUTHORIZATION_PASSWORD_INPUT, "password"), "true");

//      Вход не выполнен. Выведено сообщение валидации «Введены неверные данные».
        clickOnElement(Elements.AUTHORIZATION_CONFIRM_BUTTON);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_VALIDATION_ERROR));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_VALIDATION_ERROR), Texts.INVALID_VALUE_TEXT);
    }

    @Test(description = "Проверка авторизации с некорректными параметрами поля «Пароль» - ограничение количества символов")
    public void verificationAuthorizationWithCharacterLimit() {

        /* Окно авторизации открыто. Заголовок с текстом «Вход в Alfa-Test» отображается. Поля «Логин» и «Пароль» отображаются.
        Кнопка «Показать пароль» в поле «Пароль» отображается. Кнопка «Войти» отображается. */
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_TITLE));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_TITLE), Texts.AUTHORIZATION_TITLE_TEXT);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_LOGIN_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_PASSWORD_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.SHOW_PASSWORD_BUTTON));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_CONFIRM_BUTTON));

//      Значение в поле «Логин» введено и отображается корректно.
        fillElement(Elements.AUTHORIZATION_LOGIN_INPUT, LOGIN);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_LOGIN_INPUT), LOGIN);

//      Значение введено и скрыто символами '•'.
        fillElement(Elements.AUTHORIZATION_PASSWORD_INPUT, INCORRECT_LONG_PASSWORD_VALUE);
        Assert.assertEquals(getElementAttributeText(Elements.AUTHORIZATION_PASSWORD_INPUT, "password"), "true");

//      Значение, введенное в поле «Пароль», не скрыто и отображается корректно. Количество символов в поле пароль – 50.
        clickOnElement(Elements.SHOW_PASSWORD_BUTTON);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_PASSWORD_INPUT), INCORRECT_LONG_PASSWORD_VALUE);
        Assert.assertEquals(getElementAttributeText(Elements.AUTHORIZATION_PASSWORD_INPUT, "password"), "false");
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_PASSWORD_INPUT).length(), 50);

//      Вход не выполнен. Выведено сообщение валидации «Введены неверные данные».
        clickOnElement(Elements.AUTHORIZATION_CONFIRM_BUTTON);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_VALIDATION_ERROR));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_VALIDATION_ERROR), Texts.INVALID_VALUE_TEXT);
    }

    @Test(description = "Проверка авторизации с корректными данными в разном регистре")
    public void verificationAuthorizationWithDifferentCase() {

        /* Окно авторизации открыто. Заголовок с текстом «Вход в Alfa-Test» отображается. Поля «Логин» и «Пароль» отображаются.
        Кнопка «Показать пароль» в поле «Пароль» отображается. Кнопка «Войти» отображается. */
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_TITLE));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_TITLE), Texts.AUTHORIZATION_TITLE_TEXT);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_LOGIN_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_PASSWORD_INPUT));
        Assert.assertTrue(isElementDisplayed(Elements.SHOW_PASSWORD_BUTTON));
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_CONFIRM_BUTTON));

//      Значение в поле «Логин» введено и отображается корректно.
        fillElement(Elements.AUTHORIZATION_LOGIN_INPUT, LOGIN_LOWER_CASE);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_LOGIN_INPUT), LOGIN_LOWER_CASE);

//      Значение введено и скрыто символами '•'.
        fillElement(Elements.AUTHORIZATION_PASSWORD_INPUT, PASSWORD_LOWER_CASE);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_PASSWORD_INPUT), PASSWORD_LOWER_CASE);
        Assert.assertEquals(getElementAttributeText(Elements.AUTHORIZATION_PASSWORD_INPUT, "password"), "true");

//      Вход не выполнен. Выведено сообщение валидации «Введены неверные данные».
        clickOnElement(Elements.AUTHORIZATION_CONFIRM_BUTTON);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_VALIDATION_ERROR));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_VALIDATION_ERROR), Texts.INVALID_VALUE_TEXT);

//      Значение в поле «Логин» введено и отображается корректно.
        fillElement(Elements.AUTHORIZATION_LOGIN_INPUT, LOGIN_UPPER_CASE);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_LOGIN_INPUT), LOGIN_UPPER_CASE);

//      Значение введено и скрыто символами '•'.
        fillElement(Elements.AUTHORIZATION_PASSWORD_INPUT, PASSWORD_UPPER_CASE);
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_PASSWORD_INPUT), PASSWORD_UPPER_CASE);
        Assert.assertEquals(getElementAttributeText(Elements.AUTHORIZATION_PASSWORD_INPUT, "password"), "true");

//      Вход не выполнен. Выведено сообщение валидации «Введены неверные данные».
        clickOnElement(Elements.AUTHORIZATION_CONFIRM_BUTTON);
        Assert.assertTrue(isElementDisplayed(Elements.AUTHORIZATION_VALIDATION_ERROR));
        Assert.assertEquals(getElementText(Elements.AUTHORIZATION_VALIDATION_ERROR), Texts.INVALID_VALUE_TEXT);
    }
}

