package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp(){
        openBrowser(baseUrl);

    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        //sending email field
        WebElement emailField = driver.findElement(By.id("username"));
        emailField.sendKeys("tomsmith");
        //sending password field
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        //
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();
        //verifying the text Secure Area
        String expected = "Secure Area";
        WebElement actualText = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/h2[1]"));
        String actualTextDisplay = actualText.getText();
        Assert.assertEquals("Secure Area properly displayed" , expected,actualTextDisplay);
    }
    //verifyTheUsernameErrorMessage
    //* Enter “tomsmith1” username
    //* Enter “SuperSecretPassword!” password
    //* Click on ‘LOGIN’ button
    //* Verify the error message “Your username
    // is invalid!”
    @Test
    public void verifyTheUsernameErrorMessage(){
        String expectedErrorMessage = "Your username is invalid!\n" +
                "×";
        //sending email field
        WebElement emailField = driver.findElement(By.id("username"));
        emailField.sendKeys("tomsmith1");
        //sending password field
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        //clicking on Login button
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']\n"));
        loginButton.click();
        //verifying the message if username is invalid
        String  actualErrorMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        Assert.assertEquals("Error Messages Does Not Match" ,expectedErrorMessage,actualErrorMessage);

    }
    @Test
    public void verifyThePasswordErrorMessage(){
        String expectedErrorMessage = "Your password is invalid!\n" +
                "×";
        //sending email field
        WebElement emailField = driver.findElement(By.id("username"));
        emailField.sendKeys("tomsmith");
        //sending password field
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("SuperSecretPassword");
        //clicking on Login button
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();
        //verifying the message if password is invalid

        String actualErrorMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        //String  actualMessage = actualMessageDisplay.getText();
        Assert.assertEquals("Error Messages Does Not Match" ,expectedErrorMessage,actualErrorMessage);
    }
    @After
    public void tearDown()

    {
        closeBrowser();
    }
}
