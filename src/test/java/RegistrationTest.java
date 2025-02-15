import common.GenerateTestData;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.User;
import models.UserLogin;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ConstructorPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.ApiUtil;

public class RegistrationTest extends BaseTest {

    ChromeDriver driver;
    User user;
    GenerateTestData generate = new GenerateTestData();
    ValidatableResponse response;
    ApiUtil userApi = new ApiUtil();
    String authToken;

    @Before
    @Step("Инициализация пользователя и подготовка драйвера")
    public void setUp() {
        driver = new ChromeDriver();
        user = new User(generate.generateString(), generate.generateString() + "@gmail.com", generate.generateString());
        System.out.println(user.getEmail() + " " + user.getPassword() + " " + user.getName());
    }

    @Test
    @Description("Пользователь может быть успешно зарегистрирован")
    public void verifyUserCanRegisterSuccessfully() {
        RegisterPage registerPage = new RegisterPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        // Открыть главную страницу
        driver.get(constructorPage.getPage());
        // Кликнуть на кнопку "Войти в аккаунт"
        constructorPage.clickLoginToAccountButton();
        // Кликнуть на кнопку "Зарегистрироваться"
        loginPage.clickRegisterButton();
        // Выполнить регистрацию пользователя
        registerPage.userRegister(user.getName(), user.getEmail(), user.getPassword());
        loginPage.waitForPageLoaded();
        // Выполнить логин пользователя
        loginPage.userLogin(user.getEmail(), user.getPassword());
        constructorPage.waitForPageLoaded();
        // Проверить, что пользователь успешно авторизован
        Assert.assertTrue(driver.findElement(constructorPage.getCreateOrderButton()).isDisplayed());
        // Удалить пользователя
        UserLogin userLogin = new UserLogin(user.getEmail(), user.getPassword());
        authToken = userApi.getAccessToken(userLogin);
        if (authToken != null) {
            response = userApi.deleteUser(authToken);
        }
    }

    @Test
    @Description("При использовании пароля менее 6 символов показывается ошибка")
    public void verifyIncorrectPasswordShowsError() {
        RegisterPage registerPage = new RegisterPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        // Открыть главную страницу
        driver.get(constructorPage.getPage());
        // Кликнуть на кнопку "Войти в аккаунт"
        constructorPage.clickLoginToAccountButton();
        // Кликнуть на кнопку "Зарегистрироваться"
        loginPage.clickRegisterButton();
        // Выполнить регистрацию пользователя, использовав невалидный пароль
        registerPage.userRegister(user.getName(), user.getEmail(), "12345");
        // Проверить, что показывается валидация
        Assert.assertTrue(driver.findElement(registerPage.getIncorrectPasswordValidation()).isDisplayed());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}