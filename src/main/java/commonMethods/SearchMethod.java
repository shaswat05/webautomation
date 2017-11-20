package main.java.commonMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import main.java.pageObjectLib.LandingPage;
import main.java.utils.WebCommonMethods;


public class SearchMethod {
    WebDriver driver;
    LandingPage lp;
    public SearchMethod(WebDriver driver){
        this.driver = driver;
        lp = PageFactory.initElements(this.driver, LandingPage.class);
    }

    public void search(String searchKey){
        WebCommonMethods.navigateToUrl();
        lp.enterSearchKey(searchKey);
    }

}
