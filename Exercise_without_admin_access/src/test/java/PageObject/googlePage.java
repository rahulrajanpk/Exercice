package PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class googlePage {


    @FindBy(how= How.ID_OR_NAME,using = "q")
    static public WebElement searchField;

    @FindBy(how= How.ID,using = "hdtb-msb")
    static public  WebElement googleOption;

    @FindBy(how= How.CSS, className = "sbl1")
    static public  WebElement searchList;

    @FindBy(how= How.ID, using = "gsr")
    static public  WebElement searchButton;


}
