package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage extends BasePage {

    private final By profileButton = By.xpath("//a[text() = 'Профиль']");
    private final By logOutButton = By.xpath("//button[text() = 'Выход']");

    public PersonalAccountPage (WebDriver driver){
        super(driver);
    }

    public By getProfileButton() {
        return profileButton;
    }

    @Step("Клик на кнопку Выйти")
    public void clickLogOutButton(){
        driver.findElement(logOutButton).click();
    }
    @Step("Подождать, пока страница подгрузится")
    public void waitForPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }
}