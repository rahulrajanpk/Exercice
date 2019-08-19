package com.interview;

//PageModel
import PageObject.googlePage;
import org.openqa.selenium.WebDriver;

//TestNg
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//Utility and exception
import Utils.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Exercise

{
    //Driver Paramter
    WebDriver driver;
    Util verify;
    InputStream input;
    Properties properties;

    @BeforeTest
    void before() throws FileNotFoundException, IOException
    {
        //Calling utilities
        verify=new Util();

        //Data stream for test cases
        input = new FileInputStream("src/test/resources/Data/Properties/data.properties");
        properties = new Properties();

        // load a properties file
        properties.load(input);
    }
    @AfterTest
    void after() throws IOException
    {
        //free the space
        verify=null;
        input.close();
        properties=null;
        input=null;
        System.gc();
    }

    //Function to fetch data from properties file
     String data(String fieldData)
     {
        return properties.getProperty("tc."+fieldData);
     }

     //Test Case No 1
    @Test(groups = {"Interview"},priority = 1)
    void tc1_search_a_keyword_on_google()
    {
        verify
            .on(data("url")).atPage("Google search")

            .given()
            .on(googlePage.searchField).type(data("searchText"))
                .on(googlePage.searchList).click()

            .then()
                .waitUntil(googlePage.searchField,"is visible")
                .on(googlePage.searchField).verify("text",data("searchText"))
             .end();
    }

    //Test Case No2
    @Test(groups = {"Interview"},priority = 2)
    void tc2_search_a_keyword_on_google_with_mouse_action() throws Exception
    {

        verify
        .with("Onscreen Keyboard")
        .on(data("url")).atPage("Google search")
        .given()
	    .on(googlePage.searchField).click()
            .press("I")
            .press("D")
            .press("K")
            .on(googlePage.searchList).click()
        .then()
                .on(googlePage.searchField).verify("text",data("searchText"))
                .end();
    }
}