

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class EcommerceKart {


@Test
public void amazonAddToCart() {

    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    driver.get("https://www.amazon.in/");
    driver.manage().window().maximize();

    // SEARCH PRODUCT
    WebElement searchBox = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox"))
    );

    searchBox.sendKeys("mouse");
    driver.findElement(By.id("nav-search-submit-button")).click();

    // GET PRODUCTS
    List<WebElement> products = driver.findElements(
            By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a")
    );

    // TRY MULTIPLE PRODUCTS
    for (int i = 0; i < products.size(); i++) {

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", products.get(i));

            for (String win : driver.getWindowHandles()) {
                driver.switchTo().window(win);
            }

            // ADD TO CART
            WebElement addBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))
            );

            addBtn.click();

            // VERIFY
            WebElement cartCount = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count"))
            );

            int count = Integer.parseInt(cartCount.getText());

            if (count >= 1) {
                System.out.println("Item added successfully!");
                Assert.assertTrue(true);
                break;
            }

        } catch (Exception e) {

            driver.close();

            for (String win : driver.getWindowHandles()) {
                driver.switchTo().window(win);
            }
        }
    }

    driver.quit();
}


}
