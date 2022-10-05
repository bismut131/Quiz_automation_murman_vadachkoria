import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class WebFormsTest {
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
    public void exercise1() throws InterruptedException {
        driver.navigate().to("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");

        //choose dropdowns
        Select dropdown1 = new Select(driver.findElement(By.id("dropdowm-menu-1")));
        Select dropdown2 = new Select(driver.findElement(By.id("dropdowm-menu-2")));
        Select dropdown3 = new Select(driver.findElement(By.id("dropdowm-menu-3")));
        //select elements from dropdwons
        dropdown1.selectByValue("sql");
        Thread.sleep(1000);
        dropdown2.selectByValue("maven");
        Thread.sleep(1000);
        dropdown3.selectByValue("javascript");
        Thread.sleep(1000);
        //check if selected
        WebElement sqlRadio = driver.findElement(By.xpath("//option[@value='sql']"));
        System.out.println("SQL has been selected: " + sqlRadio.isSelected());
        WebElement mavenRadio = driver.findElement(By.xpath("//option[@value='maven']"));
        System.out.println("maven has been selected: " + mavenRadio.isSelected());
        WebElement javascriptRadio = driver.findElement(By.xpath("//option[@value='javascript']"));
        System.out.println("javascript has been selected: " + javascriptRadio.isSelected());

        //select all checkboxes
        List<WebElement> options = driver.findElements(By.cssSelector("#checkboxes input"));
        //check if checkbox is checked if not check it
        options.forEach(
            (option) -> {
                if(!option.isSelected()) {
                    option.click();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );

        //find and click on yellow radio button
        WebElement yellowRadio = driver.findElement(By.cssSelector("#radio-buttons input[value='yellow']"));
        yellowRadio.click();
        Thread.sleep(500);

        //check if orange option is disabled in dropdown
        WebElement orangeOption = driver.findElement(By.cssSelector("#fruit-selects option[disabled='disabled']"));
        System.out.println(orangeOption.getText() + " option is enabled: " + orangeOption.isEnabled());
        Thread.sleep(500);
    }
}
