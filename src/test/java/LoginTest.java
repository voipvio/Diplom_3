import common.GenerateTestData;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utils.ApiUtil;


public class LoginTest extends BaseTest {

    WebDriver driver;
    CommonPage commonPage;
    ConstructorPage constructorPage;
    RegisterPage registerPage;
    LoginPage loginPage;
    ForgotPasswordPage forgotPasswordPage;
    ApiUtil userApi;
    ValidatableResponse response;
    String authToken;
    private User user;
    GenerateTestData generate = new GenerateTestData();

    @Before
    @Step("Создание пользователя и инициализация страниц")
    public void setUp() {
        user = new User(generate.generateString(), generate.generateString() + "@yopmail.com", generate.generateString());
        userApi = new ApiUtil();
        response = userApi.userRegister(user);
        authToken = response.extract().path("accessToken");
        driver = new ChromeDriver();
        commonPage = new CommonPage(driver);
        constructorPage = new ConstructorPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Пользователь может успешно войти в аккаунт по кнопке «Войти в аккаунт» на главной странице")
    public void VerifyUserLoginFromLoginToAccountButton() {
        // Открыть главную страницу
        driver.get(constructorPage.getPage());
        // Кликнуть по кнопке "Войти в аккаунт"
        constructorPage.clickLoginToAccountButton();
        loginPage.waitForPageLoaded();
        // Залогиниться пользователем
        loginPage.userLogin(user.getEmail(), user.getPassword());
        constructorPage.waitForPageLoaded();
        // Проверить, что логин выполнен успешно
        Assert.assertTrue(driver.findElement(constructorPage.getCreateOrderButton()).isDisplayed());
    }

    @Test
    @DisplayName("Пользователь может успешно войти в аккаунт через кнопку «Личный кабинет»")
    public void VerifyUserLoginFromPersonalAccountButton() {
        commonPage = new CommonPage(driver);
        // Открыть главную страницу
        driver.get(constructorPage.getPage());
        // Кликнуть по кнопке "Личный кабинет"
        commonPage.clickPersonalAccountButton();
        loginPage.waitForPageLoaded();
        // Залогиниться пользователем
        loginPage.userLogin(user.getEmail(), user.getPassword());
        constructorPage.waitForPageLoaded();
        // Проверить, что логин выполнен успешно
        Assert.assertTrue(driver.findElement(constructorPage.getCreateOrderButton()).isDisplayed());
    }

    @Test
    @DisplayName("Пользователь может успешно войти в аккаунт через кнопку в форме регистрации")
    public void VerifyUserLoginFromButtonOnRegistrationPage() {
        registerPage = new RegisterPage(driver);
        // Открыть страницу регистрации
        driver.get(registerPage.getPage());
        // Кликнуть на кнопку "Войти"
        registerPage.clickLoginButton();
        loginPage.waitForPageLoaded();
        // Залогиниться пользователем
        loginPage.userLogin(user.getEmail(), user.getPassword());
        constructorPage.waitForPageLoaded();
        // Проверить, что логин выполнен успешно
        Assert.assertTrue(driver.findElement(constructorPage.getCreateOrderButton()).isDisplayed());
    }

    @Test
    @DisplayName("Пользователь может успешно войти в аккаунт через кнопку в форме восстановления пароля")
    public void VerifyUserLoginFromButtonOnForgotPasswordPage() {
        forgotPasswordPage = new ForgotPasswordPage(driver);
        // Открыть страницу восстановления пароля
        driver.get(forgotPasswordPage.getPage());
        // Кликнуть на кнопку "Войти"
        forgotPasswordPage.clickLoginButton();
        loginPage.waitForPageLoaded();
        // Залогиниться пользователем
        loginPage.userLogin(user.getEmail(), user.getPassword());
        constructorPage.waitForPageLoaded();
        // Проверить, что логин выполнен успешно
        Assert.assertTrue(driver.findElement(constructorPage.getCreateOrderButton()).isDisplayed());
    }

    @After
    public void tearDown(){
        driver.quit();
        userApi.deleteUser(authToken);
    }
}