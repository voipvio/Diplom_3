package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class LoginPage extends BasePage {

    private final String page = "https://stellarburgers.nomoreparties.site/login";
    private final By emailField = By.xpath("//label[text() = 'Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text() = 'Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[text() = 'Войти']");
    private final By registerButton = By.xpath("//a[text() = 'Зарегистрироваться']");

    public LoginPage (WebDriver driver){
        super(driver);
    }

    @Step("Заполнить поля на форме Вход")
    public void userLogin(String email, String password) {
        fillEmailField(email);
        fillPasswordField(password);
        clickLoginButton();
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

    @Step("Кликнуть по кнопке Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Кликнуть по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public By getLoginButton() {
        return loginButton;
    }

    public String getPage() {
        return page;
    }

    @Step("Подождать, пока страница подгрузится")
    public void waitForPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }
}