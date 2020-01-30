import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public static String baseUrl;
    public static Properties properties = TestProperties.getInstance().getProperties();


    @BeforeClass
    public static void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        baseUrl = properties.getProperty("app.url");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void cleanUp() throws Exception{
        driver.close();
        driver.quit();
    }

    public int formatPrice(WebElement element){
        return Integer.parseInt(element.getText().replaceAll("\\s",""));
    }

}
