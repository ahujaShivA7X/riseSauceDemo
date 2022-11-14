package stepDefinitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
public class SauceDemo {

    public WebDriver driver;

    public void waitAndClick(String object) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(object))).click();
    }

    public void waitAndType(String object, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(object))).sendKeys(text);
    }

    public void Init() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    @Given("^user is on the login page$")
    public void user_is_on_the_login_page() throws Throwable {
        Init();
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals("Swag Labs", title);
    }

    @When("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enters_and(String arg1, String arg2) throws Throwable {
        waitAndType("//input[@id='user-name']", arg1);
        waitAndType("//input[@id='password']", arg2);
        waitAndClick("//input[@id='login-button']");
    }

    @Then("^user selects three selection randomly$")
    public void user_selects_three_selection_randomly() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//*[contains(text(), 'Add to cart')]"));
        int countProducts = listOfProducts.size();
        int max = countProducts - 1;
        int min = 0;
        for (int i = 1; i <= 3; i++) {
            int a = (int) (Math.random() * (max - min + 1) + min);
            System.out.println(a);
            driver.findElement(By.id("item_"+a+"_img_link")).click();
            driver.findElement(By.id("back-to-products")).click();
        }
        driver.quit();
    }
}
