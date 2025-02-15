import utils.ApiUtil;
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

public class PersonalAccountTest extends BaseTest {

    WebDriver driver;
    ApiUtil apiUtil;
    CommonPage commonPage;
    ConstructorPage constructorPage;
    LoginPage loginPage;
    PersonalAccountPage personalAccountPage;
    ValidatableResponse response;
    String authToken;
    private User user;
    GenerateTestData generate = new GenerateTestData();

    @Before
    @Step("Создание пользователя и инициализация страниц")
    public void setUp() {
        user = new User(generate.generateString(), generate.generateString() + "@yopmail.com", generate.generateString());

        apiUtil = new ApiUtil();
        response = apiUtil.userRegister(user);
        authToken = response.extract().path("accessToken");
        driver = new ChromeDriver();
        commonPage = new CommonPage(driver);
        constructorPage = new ConstructorPage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);

    }

    @Test
    @DisplayName("Переход по кнопке Личный кабинет неавторизованным пользователем")
    public void verifyNotAuthorizedUserClicksPersonalAccountButton() {
        // Открыть главную страницу
        driver.get(constructorPage.getPage());
        // Кликнуть по кнопке "Личный кабинет"
        commonPage.clickPersonalAccountButton();
        // Проверить, что открылась страница входа в аккаунт
        Assert.assertTrue(driver.findElement(loginPage.getLoginButton()).isDisplayed());
    }

    @Test
    @DisplayName("Переход по кнопке Личный кабинет авторизованным пользователем")
    public void verifyAuthorizedUserClicksPersonalAccountButton() {
        // Открыть страницу логина
        driver.get(loginPage.getPage());
        // Выполнить вход в аккаунт
        loginPage.userLogin(user.getEmail(), user.getPassword());
        constructorPage.waitForPageLoaded();
        // Кликнуть по кнопке "Личный кабинет" на главной странице
        commonPage.clickPersonalAccountButton();
        personalAccountPage.waitForPageLoaded();
        // Проверить, что открылась страница личного кабинета
        Assert.assertTrue(driver.findElement(personalAccountPage.getProfileButton()).isDisplayed());
    }

    @Test
    @DisplayName("Пользователь может перейти из личного кабинета в конструктор")
    public void verifyUserCanOpenConstructorFromPersonalAccount() {
        // Открыть страницу логина
        driver.get(loginPage.getPage());
        // Выполнить вход в аккаунт
        loginPage.userLogin(user.getEmail(), user.getPassword());
        constructorPage.waitForPageLoaded();
        // Кликнуть по кнопке "Личный кабинет" на главной странице
        commonPage.clickPersonalAccountButton();
        // Кликнуть по кнопке "Конструктор"
        commonPage.clickConstructorButton();
        constructorPage.waitForPageLoaded();
        // Проверить, что открылась страница конструктора
        Assert.assertTrue(driver.findElement(constructorPage.getCreateOrderButton()).isDisplayed());
    }

    @Test
    @DisplayName("Пользователь может перейти из личного кабинета на главную страницу")
    public void verifyUserCanOpenMainPageFromPersonalAccount() {
        // Открыть страницу логина
        driver.get(loginPage.getPage());
        // Выполнить вход в аккаунт
        loginPage.userLogin(user.getEmail(), user.getPassword());
        constructorPage.waitForPageLoaded();
        // Кликнуть по кнопке "Личный кабинет" на главной странице
        commonPage.clickPersonalAccountButton();
        // Кликнуть по заголоквку "Stellar Burgers"
        commonPage.clickStellarBurgersButton();
        constructorPage.waitForPageLoaded();
        // Проверить, что открылась страница конструктора
        Assert.assertTrue(driver.findElement(constructorPage.getCreateOrderButton()).isDisplayed());
    }

    @Test
    @DisplayName("Пользователь может выйти из аккаунта")
    public void verifyUserCanLogOutFromPersonalAccount() {
        // Открыть страницу логина
        driver.get(loginPage.getPage());
        // Выполнить вход в аккаунт
        loginPage.userLogin(user.getEmail(), user.getPassword());
        constructorPage.waitForPageLoaded();
        // Кликнуть по кнопке "Личный кабинет" на главной странице
        commonPage.clickPersonalAccountButton();
        personalAccountPage.waitForPageLoaded();
        // Кликнуть по кнопке "Выйти из аккаунта"
        personalAccountPage.clickLogOutButton();
        loginPage.waitForPageLoaded();
        // Проверить, что открылась страница входа в аккаунт
        Assert.assertTrue(driver.findElement(loginPage.getLoginButton()).isDisplayed());
    }

    @After
    public void tearDown(){
        driver.quit();
        apiUtil.deleteUser(authToken);
    }
}