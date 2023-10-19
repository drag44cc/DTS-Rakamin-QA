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

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("the user is on the Sauce Demo login page")
    public void the_user_is_on_the_sauce_demo_login_page(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //Assertion
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }

    @When("the user enter valid username")
    public void the_user_enter_valid_username() {
        driver.findElement(By.xpath("//*[@id='user-name']")).sendKeys("standard_user");
    }

    @And("the user enter valid password")
    public void the_user_enter_valid_password() {
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("secret_sauce");
    }

    @And("the user click on the login button")
    public void the_user_click_on_the_login_button() {
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
    }

    @Then("the user logged in to the Sauce Demo application")
    public void the_user_logged_in_to_the_sauce_demo_application() {
        driver.findElement(By.xpath("//DIV[@class='app_logo'][text()='Swag Labs']"));
        String product = driver.findElement(By.xpath("//SPAN[@class='title'][text()='Products']")).getText();
        Assert.assertEquals(product, "Products");
        driver.close();
    }

    @When("the user enter invalid username")
    public void theUserEnterInvalidUsername() {
        driver.findElement(By.id("user-name")).sendKeys("invalid_username");
    }


    @Then("the user see an error message that says")
    public void theUserSeeAnErrorMessageThatSays() {
        String errorLogin = driver.findElement(By.xpath("//H3[@data-test='error']")).getText();
        Assert.assertEquals(errorLogin,"Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }

    @When("the user enter (.*) username TDD$")
    public void the_user_enter_username_TDD(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("the user enter (.*) password TDD$")
    public void the_user_enter_password_TDD(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("the user (.*) logged in to the Sauce Demo application$")
    public void the_user_logged_in_to_the_sauce_demo_application(String status){
        if(status.equals("success")){
            String loginPageAssert = driver.findElement(By.xpath("//*[@id='header_container']/div[1]/div[2]/div")).getText();
            Assert.assertEquals(loginPageAssert, "Swag Labs");
        } else {
            String errorLogin = driver.findElement(By.xpath("//H3[@data-test='error']")).getText();
            Assert.assertEquals(errorLogin,"Epic sadface: Username and password do not match any user in this service");
        }
        driver.close();
    }
}
