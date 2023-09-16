import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Pelckmans {

    WebDriver driver;
    @BeforeMethod
    public void setUp() throws IOException {
        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "\\resources\\CONF.properties");
        prop.load(fs);
        if (prop.getProperty("Browser").equals("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void menuItemsTest(){

    driver.get("https://www.pelckmans.be/");
    driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonAccept")).click();
    WebElement nav = driver.findElement(By.xpath("//*[@class ='landing-page-menu']"));
    System.out.println(nav.findElements(By.tagName("li")).size());
    List<WebElement> menulist =  (nav.findElements(By.tagName("li")));
    for(WebElement menu: menulist){
        System.out.println(menu.getText());
    }

    }
}
