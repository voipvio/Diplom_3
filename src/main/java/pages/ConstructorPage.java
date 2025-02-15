package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage extends BasePage {

    private final String page = "https://stellarburgers.nomoreparties.site/";

    // Заголовок страницы "Соберите бургер"
    private final By mainHeader = By.xpath("//h1[text() = 'Соберите бургер']");

    // Кнопка "Войти в аккаунт"
    private final By loginToAccountButton = By.xpath("//button[text() = 'Войти в аккаунт']");

    // Кнопка "Оформить заказ"
    private final By createOrderButton = By.xpath("//button[text() = 'Оформить заказ']");
    private final By bunsButton = By.xpath("//span[text() = 'Булки']");
    private final By saucesButton = By.xpath("//span[text() = 'Соусы']");
    private final By fillingsButton = By.xpath("//span[text() = 'Начинки']");
    private final String selectedSection = "//div[contains(@class, 'current')]//span[text() = '%s']";

    public ConstructorPage (WebDriver driver){
        super(driver);
    }

    @Step("Кликнуть по кнопке Войти в аккаунт")
    public void clickLoginToAccountButton(){
        driver.findElement(loginToAccountButton).click();
    }

    @Step("Кликнуть по секции Булки")
    public void clickBunsButton(){
        driver.findElement(bunsButton).click();
    }

    @Step("Кликнуть по секции Cоусы")
    public void clickSaucesButton(){
        driver.findElement(saucesButton).click();
    }

    @Step("Кликнуть по секции Начинки")
    public void clickFillingsButton(){
        driver.findElement(fillingsButton).click();
    }

    @Step("Получить локатор открытой секции в разделе Соберите бургер")
    public By getOpenedTabLocator(String tabName) {
        return By.xpath(String.format(selectedSection, tabName));
    }

    public By getCreateOrderButton() {
        return createOrderButton;
    }

    public String getPage() {
        return page;
    }

    @Step("Подождать, пока страница подгрузится")
    public void waitForPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(mainHeader));
    }

}