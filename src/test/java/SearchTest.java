package test.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.commonMethods.SearchMethod;
import main.java.utils.FilesAndFolders;
import main.java.utils.SelectBrowser;
import main.java.utils.WebCommonMethods;

public class SearchTest {
    private WebDriver driver;
    WebCommonMethods wcm;
    private SearchMethod sm;

    @BeforeClass
    public void setUp() throws Exception{
        driver = SelectBrowser.getBrowser();
        wcm = PageFactory.initElements(driver, WebCommonMethods.class);
        wcm.implicitWait();
        sm = PageFactory.initElements(driver, SearchMethod.class);
    }

    @AfterMethod
    public void close(){
    	driver.close();
    }
    @AfterClass
    public void stop(){
    }
    
    @Test
    public void loginTest() throws Exception {
        sm.search(FilesAndFolders.readExcel("testData", "test1")[1].getContents());
    }
}
