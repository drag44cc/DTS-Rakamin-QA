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

public class addtocard {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("The user logged in to the Sauce Demo application with valid credentials")
    public void the_user_logged_in_to_the_sauce_demo_application_with_valid_credentials(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //Assertion Login Page
        String loginPageAssert = driver.findElement(By.xpath("//DIV[@class='login_logo'][text()='Swag Labs']")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();

        //Assertion dashboard Page
        driver.findElement(By.xpath("//DIV[@class='app_logo'][text()='Swag Labs']"));
        String product = driver.findElement(By.xpath("//SPAN[@class='title'][text()='Products']")).getText();
        Assert.assertEquals(product, "Products");
    }
    @When("The user click on the 'Add to cart' button for the 'Sauce Labs Backpack' item")
    public void the_user_click_on_the_add_to_cart_button_for_the_sauce_labs_backpack_item(){
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

    }

    @Then("the item is added to the cart")
    public void the_item_is_added_to_the_cart() {
        String chartnumber = driver.findElement(By.xpath("//*[@id='remove-sauce-labs-backpack']")).getText();
        Assert.assertEquals(chartnumber, "Remove");
        driver.close();
    }

    @Given("The user logged in to the Sauce Demo application with error account")
    public void the_user_logged_in_to_the_sauce_demo_application_with_error_account(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //Assertion Login Page
        String loginPageAssert = driver.findElement(By.xpath("//DIV[@class='login_logo'][text()='Swag Labs']")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();

        //Assertion dashboard Page
        driver.findElement(By.xpath("//DIV[@class='app_logo'][text()='Swag Labs']"));
        String product = driver.findElement(By.xpath("//SPAN[@class='title'][text()='Products']")).getText();
        Assert.assertEquals(product, "Products");
    }

    @And("I click on the button remove")
    public void iClickOnTheButtonRemove() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    @Then("I see an button remove function is not working")
    public void iSeeAnButtonRemoveFunctionIsNotWorking() {
        String chartnumber = driver.findElement(By.xpath("//*[@id='remove-sauce-labs-backpack']")).getText();
        Assert.assertEquals(chartnumber, "Remove");
        driver.close();
    }
}
