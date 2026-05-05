import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RediffLoginTest {

    @Test
    public void rediffLogin() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
        driver.manage().window().maximize();

        WebElement username = driver.findElement(By.id("login1"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("subhrajeet900");
        password.sendKeys("Jayant@900");

        driver.findElement(By.name("proceed")).click();

        Thread.sleep(2000);

        driver.quit();
    }
}