package main.java.pageObjectLib;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LandingPage {
    private WebDriver driver;
    private Actions action;
    
    public LandingPage(WebDriver driver){
        this.driver = driver;
        action = new Actions(this.driver);
    }

    @FindBy (name = "q")
    private WebElement searchBox;

    public void enterSearchKey(String searchKey){
        searchBox.clear();
        searchBox.sendKeys(searchKey);
        action.sendKeys(Keys.ENTER).perform();
    }

}
