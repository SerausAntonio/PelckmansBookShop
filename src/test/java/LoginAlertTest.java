
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginAlert {
    private static WebDriver driver;
    private WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://www.webdriveruniversity.com/Login-Portal/index.html?");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
       driver.quit();
    }
    @Test
    public void loginWithRightCredentialsTest(){
        driver.findElement(By.id("text")).sendKeys("webdriver");
        driver.findElement(By.id("password")).sendKeys("webdriver123");
        driver.findElement(By.id("login-button")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertTrue(alert.getText().equals("validation succeeded"));
        alert.accept();
    }
    @Test
    public void loginWithWrongCredentialsTest(){

        driver.findElement(By.id("text")).sendKeys("webdriver");
        driver.findElement(By.id("password")).sendKeys("webdriver1");
        driver.findElement(By.id("login-button")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertTrue(alert.getText().equals("validation failed"));
        alert.accept();
    }
}
