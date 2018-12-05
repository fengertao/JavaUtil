package charlie.feng.testdemo.selenium;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Stepdefs {


    WebDriver driver = null;

    @Given("^open automationpractice$")
    public void open_automationpractice() throws Throwable {
//        The driver can be download from https://github.com/mozilla/geckodriver
//        System.setProperty("webdriver.gecko.driver","/home/charlie/Dev/SeleniumDriver/geckodriver");
//        WebDriver driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "/home/charlie/Dev/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @When("^click t-shirt link$")
    public void click_t_shirt_link() throws Throwable {
        WebElement searchInputBox = driver.findElement(By.id("search_query_top"));
        searchInputBox.sendKeys("dress");
        WebElement searchButton = driver.findElement(By.name("submit_search"));
        searchButton.click();

    }

    @Then("^find one t-shirt$")
    public void find_one_t_shirt() throws Throwable {
        WebElement resultLine = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[2]"));
        Assert.assertEquals("Should find 7 dress", resultLine.getText(), "7 results have been found.");
        driver.close();
    }

}
