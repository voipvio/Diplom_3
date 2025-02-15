package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {

    private final String page = "https://stellarburgers.nomoreparties.site/register";
    // Поле "Имя"
    private final By nameField = By.xpath("//label[text() = 'Имя']/following-sibling::input");
    // Поле "Email"
    private final By emailField = By.xpath("//label[text() = 'Email']/following-sibling::input");
    // Поле "Пароль"
    private final By passwordField = By.name("Пароль");
    // Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath("//button[text() = 'Зарегистрироваться']");
    // Кнопка "Войти"
    private final By loginButton = By.xpath("//a[text() = 'Войти']");
    // Ошибка для некорректного пароля
    private final By incorrectPasswordValidation = By.xpath("//p[text() = 'Некорректный пароль']");

    public RegisterPage (WebDriver driver){
        super(driver);
    }

    @Step("Выполнить резгистрацию пользователем")
    public void userRegister(String name, String email, String password) {
        fillNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
        clickRegisterButton();
    }

    @Step("Заполнить поле Имя")
    public void fillNameField (String inputValue) {
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(inputValue);
    }

    @Step("Заполнить поле Email")
    public void fillEmailField(String inputValue) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(inputValue);
    }

    @Step("Заполнить поле Пароль")
    public void fillPasswordField(String inputValue) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(inputValue);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Клик по кнопке Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public By getIncorrectPasswordValidation() {
        return incorrectPasswordValidation;
    }

    public String getPage() {
        return page;
    }

    @Step("Подождать, пока страница подгрузится")
    public void waitForPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(nameField)));
    }
}