import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class WebElementsTest {
    @Test
    public void exercise1 () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //navigate to website
        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");

        //find and click on button
        WebElement buttonElement = driver.findElement(By.xpath("/html/body/div/div/div/form[2]/button"));
        buttonElement.click();

        //wait after click
        Thread.sleep(5000);

        //check input element
        WebElement inputElement = driver.findElement(By.xpath("/html/body/div/div/div/form[2]/input"));
        System.out.println("Input element is enabled: " + inputElement.isEnabled());

        //check text under button
        WebElement messageElement = driver.findElement(By.xpath("/html/body/div/div/div/form[2]/p"));
        System.out.println("Text displays: " + messageElement.getText());

        //Check button text after clicking
        System.out.println("Button text is: " + buttonElement.getText());

        //write in input
        inputElement.sendKeys("Test Automation");
        //wait
        Thread.sleep(5000);
        //clear
        inputElement.clear();

        Thread.sleep(1000);

        driver.quit();
    }

    @Test
    public void exercise2 () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //navigate to website
        driver.navigate().to("http://the-internet.herokuapp.com/add_remove_elements/");

        //click on the button 3 times
        WebElement addElementButton = driver.findElement(By.cssSelector(".example button"));
        for (int i = 0; i < 3; i++) {
            addElementButton.click();
        }

        Thread.sleep(1000);

        //print last delete buttons element's text
        WebElement lastDeleteButton = driver.findElement(By.cssSelector("#elements .added-manually:last-child"));
        System.out.println("Button text is: " + lastDeleteButton.getText());

        //find out the number of delete buttons
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("#elements .added-manually"));
        System.out.println("Number of buttons is: " + deleteButtons.size());

        //click on last delete button
        lastDeleteButton.click();

        Thread.sleep(1000);

        //check the number of delete buttons after click
        List<WebElement> deleteButtonsAfterClick = driver.findElements(By.cssSelector("#elements .added-manually"));
        System.out.println(deleteButtonsAfterClick == deleteButtons ? "Number of buttons didn't change" : "Number of buttons has been changed");

        Thread.sleep(1000);
        driver.quit();
    }
}
