package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPage extends BasePage {

    private final By constructorButton = By.xpath("//a[contains(., 'Конструктор')]");
    private final By stellarBurgersButton = By.xpath("//div[contains(@class, 'logo')]//a");
    private final By personalAccountButton = By.xpath("//p[text() = 'Личный Кабинет']");


    public CommonPage (WebDriver driver){
        super(driver);
    }

    @Step("Клик по кнопке Конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по кнопке Stellar Burgers")
    public void clickStellarBurgersButton() {
        driver.findElement(stellarBurgersButton).click();
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }
}