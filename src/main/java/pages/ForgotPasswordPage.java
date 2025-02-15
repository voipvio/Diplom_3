package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final String page = "https://stellarburgers.nomoreparties.site/forgot-password";

    private final By loginButton = By.xpath("//a[text() = 'Войти']");

    public ForgotPasswordPage (WebDriver driver){
        super(driver);
    }

    @Step("Кликнуть по кнопке Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getPage() {
        return page;
    }
}