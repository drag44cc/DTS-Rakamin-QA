package cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class logout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("The User logged in to the Sauce Demo application")
    public void theUserLoggedInToTheSauceDemoApplication() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //AssertionLoginPage
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        //Login
        driver.findElement(By.xpath("//*[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();

        //AssertionSuccessLogin
        driver.findElement(By.xpath("//DIV[@class='app_logo'][text()='Swag Labs']"));
        String product = driver.findElement(By.xpath("//SPAN[@class='title'][text()='Products']")).getText();
        Assert.assertEquals(product, "Products");
    }

    @When("The User click on the Logout button")
    public void theUserClickOnTheLogoutButton() {
        driver.findElement(By.xpath("//*[@id='react-burger-menu-btn']")).click();
        driver.findElement(By.xpath("//*[@id='logout_sidebar_link']")).click();
    }

    @Then("The User logged out of the Sauce Demo application")
    public void theUserLoggedOutOfTheSauceDemoApplication() {
        //AssertionLoginPage
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
        driver.close();
    }
}
