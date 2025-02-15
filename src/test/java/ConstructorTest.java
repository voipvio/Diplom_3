import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

public class ConstructorTest extends BaseTest{

    WebDriver driver;
    ConstructorPage constructorPage;


    @Before
    @Step("Подготовка драйвера")
    public void setUp() {
        driver = new ChromeDriver();
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    @DisplayName("Пользователь может перейти к разделу Булки")
    public void verifyUserCanOpenBunsSection() {
        // Открыть главную страницу
        driver.get(constructorPage.getPage());
        constructorPage.waitForPageLoaded();
        // Кликнуть по вкладке "Соусы"
        constructorPage.clickSaucesButton();
        // Кликнуть по вкладке "Булки"
        constructorPage.clickBunsButton();
        // Проверить, что открылась вкладка "Булки"
        Assert.assertTrue(driver.findElement(constructorPage.getOpenedTabLocator("Булки")).isDisplayed());
    }

    @Test
    @DisplayName("Пользователь может перейти к разделу Соусы")
    public void verifyUserCanOpenSaucesSection() {
        // Открыть главную страницу
        driver.get(constructorPage.getPage());
        constructorPage.waitForPageLoaded();
        // Кликнуть по вкладке "Соусы"
        constructorPage.clickSaucesButton();
        // Проверить, что открылась вкладка "Соусы"
        Assert.assertTrue(driver.findElement(constructorPage.getOpenedTabLocator("Соусы")).isDisplayed());
    }

    @Test
    @DisplayName("Пользователь может перейти к разделу Начинки")
    public void verifyUserCanOpenFillingsSection() {
        // Открыть главную страницу
        driver.get(constructorPage.getPage());
        constructorPage.waitForPageLoaded();
        // Кликнуть по вкладке "Начинки"
        constructorPage.clickFillingsButton();
        // Проверить, что открылась вкладка "Начинки"
        Assert.assertTrue(driver.findElement(constructorPage.getOpenedTabLocator("Начинки")).isDisplayed());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}