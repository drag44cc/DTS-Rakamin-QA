package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class checkout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("The User logged in to the Sauce Demo application with valid credentials")
    public void TheUserloggedintotheSauceDemoapplicationwithvalidcredentials(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //Assertion
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        driver.findElement(By.xpath("//*[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();

        //Assertion dashboard Page
        driver.findElement(By.xpath("//DIV[@class='app_logo'][text()='Swag Labs']"));
        String product = driver.findElement(By.xpath("//SPAN[@class='title'][text()='Products']")).getText();
        Assert.assertEquals(product, "Products");
    }
    @When("The User add the 'Sauce Labs Backpack' item to the cart")
    public void TheUseraddtheSauceLabsBackpackitemtothecart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String chartAssert = driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a/span")).getText();
        Assert.assertEquals(chartAssert, "1");
    }

    @And("The User click on the cart icon then 'Checkout' button")
    public  void TheUserclickonthecarticonthenCheckoutbutton() {

        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
        driver.findElement(By.xpath("//*[@id='checkout']")).click();
    }

    @And("The User enter shipping information")
    public void TheUserentershippinginformation() {

        driver.findElement(By.xpath("//*[@id='first-name']")).sendKeys("Tiko");
        driver.findElement(By.xpath("//*[@id='last-name']")).sendKeys("Aprilianto");
        driver.findElement(By.xpath("//*[@id='postal-code']")).sendKeys("52122");
    }

    @And("The User click on the 'Continue' button")
    public void TheUserclickontheContinuebutton() {
        driver.findElement(By.xpath("//*[@id='continue']")).click();
    }

    @And("The User see Checkout: Overview then click 'Finish' button")
    public void TheUserseeCheckoutOverviewthenclickFinishbutton() {
        driver.findElement(By.xpath("//DIV[@class='app_logo'][text()='Swag Labs']"));
        String product = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(product, "Checkout: Overview");

        driver.findElement(By.xpath("//*[@id='finish']")).click();
    }

    @Then("The User see a confirmation message that says 'Thank you for your order!'")
    public void TheUserseeaconfirmationmessagethatsaysThankyouforyourorder() {
        driver.findElement(By.xpath("//DIV[@class='app_logo'][text()='Swag Labs']"));
        String product = driver.findElement(By.xpath("//*[@id='checkout_complete_container']/h2")).getText();
        Assert.assertEquals(product, "Thank you for your order!");
        driver.close();
    }


    @And("The User enter invalid shipping information")
    public void theUserEnterInvalidShippingInformation() {
        driver.findElement(By.xpath("//*[@id='first-name']")).sendKeys("");
        driver.findElement(By.xpath("//*[@id='last-name']")).sendKeys("");
        driver.findElement(By.xpath("//*[@id='postal-code']")).sendKeys("");
    }

    @Then("The User see an error message that says 'Error: First Name is required'")
    public void theUserSeeAnErrorMessageThatSaysErrorFirstNameIsRequired() {
        String errorCheckout = driver.findElement(By.xpath("//*[@id='checkout_info_container']/div/form/div[1]/div[4]/h3")).getText();
        Assert.assertEquals(errorCheckout,"Error: First Name is required");
        driver.close();
    }
}
