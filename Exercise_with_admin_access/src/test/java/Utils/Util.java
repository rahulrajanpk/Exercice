package Utils;

import PageObject.googlePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.io.IOException;


public class Util {

    String pageName;
    WebDriver driver;
    WebElement webElement;
    googlePage pageUnderTest;
    Screen screen;
    Pattern image;

    public Util on(String url)
    {
        System.setProperty("webdriver.chrome.driver","src/test/resources/Drivers/Chrome/chromedriver.exe");
        driver =new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().setScriptTimeout(2l,TimeUnit.SECONDS);
        return this;
    }
    public Util atPage(String pageName)
    {
        pageUnderTest= PageFactory.initElements(this.driver,googlePage.class);
        return this;
    }
    public Util given()
    {
        return this;
    }
    public Util on(WebElement webElement)
    {
        this.webElement=webElement;
         return this;
    }
    public Util type(String fieldData)
    {
        this.webElement.sendKeys(fieldData);
        return this;
    }
    public Util click()
    {
        this.webElement.click();
        return this;
    }
    public Util waitUntil(WebElement WebElement,String fieldData)
    {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(Exception.class);

       wait.until(new Function<WebDriver, WebElement>(){
            public WebElement apply(WebDriver driver ) {
                 WebElement.isDisplayed();
                 return WebElement;
            }
        });
        return this;
    }
    public Util then()
    {
        return this;
    }

    public Util verify(String choice,String fieldData){
        switch(choice)
        {
            case "text":
                Assert.assertEquals(webElement.getAttribute("value"), fieldData);
        }
        return this;
    }

    public void end()
    {
        this.driver.close();
        this.driver=null;
        this.image=null;
        this.screen=null;
    }
    public Util and(){return this;}

    public Util with(String process) throws Exception
    {
        String sysroot = System.getenv("SystemRoot");
        Runtime.getRuntime().exec(sysroot + "/system32/osk.exe");
        return this;
    }
    public Util press(String imageName) throws Exception
    {
        screen =new Screen();
        image=new Pattern("src/test/resources/Data/Properties/Images/"+imageName+".jpeg");
        screen.click(image);
        return this;
    }

}
