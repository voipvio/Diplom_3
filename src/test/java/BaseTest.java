import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.junit.Before;

public class BaseTest {
    private WebDriver driver;

    @Before
    public void setup() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        if ("yandex".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/yandexdriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver.exe");
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}