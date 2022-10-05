import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

public class WaitsTest {
    WebDriver driver;

    @BeforeMethod
    public void open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void exercise2() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/progress-bar ");

        //click start button
        WebElement startButton = driver.findElement(By.cssSelector("#startStopButton"));
        startButton.click();

        //print when bar hits 50%
        WebElement progressBar = driver.findElement(By.cssSelector(".progress-bar.bg-info"));
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofHours(1))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, "50%"));
        System.out.println("Progressbar has reached: " + progressBar.getText());

        Thread.sleep(5000);

    }
}
