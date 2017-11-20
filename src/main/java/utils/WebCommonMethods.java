package main.java.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class WebCommonMethods {
	private static WebDriver driver;	
	private WebDriverWait wait;
	private String implicitWaitTime;
    private String pageLoadTimeout;
	private String sleepTimeMin;
	private String sleepTimeMin2;
	private String sleepTimeAverage;
	private String sleepTimeMax;
	private String sleepTimeMax2;



	//Constructor to initialize driver 
	public WebCommonMethods(WebDriver driver){
		this.driver=driver;
		implicitWaitTime = FilesAndFolders.getPropValue("implicitWaitTime");
        String explicitWaitTime = FilesAndFolders.getPropValue("explicitWaitTime");
		pageLoadTimeout = FilesAndFolders.getPropValue("pageLoadTimeout");
		sleepTimeMin = FilesAndFolders.getPropValue("sleepTimeMin");
		sleepTimeMin2 = FilesAndFolders.getPropValue("sleepTimeMin2");
		sleepTimeAverage = FilesAndFolders.getPropValue("sleepTimeAverage");
		sleepTimeMax = FilesAndFolders.getPropValue("sleepTimeMax");
		sleepTimeMax2 = FilesAndFolders.getPropValue("sleepTimeMax2");

		wait = new WebDriverWait(driver, Integer.parseInt(explicitWaitTime));
	}

	//Maximize window
	public void maximizeWindow(){
		driver.manage().window().maximize();
	}

	//Get Alert Text
	public String getAlertText(){
		return driver.switchTo().alert().getText();
	}

	//Accept Alert
	public void acceptAlert(){
		driver.switchTo().alert().accept();
	}

	//Dismiss Alert
	public void dismissAlert(){
		driver.switchTo().alert().dismiss();
	}


	//Close all browser instances
	public void quit(){
		driver.manage().deleteAllCookies();
		driver.quit(); 
	}

	//Close current browser
	public void close(){
		driver.manage().deleteAllCookies();
		driver.close();
	}

	/*
	 * Wait Methods
	 */

	//Wait for page to load
	public void implicitWait(){
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(implicitWaitTime), TimeUnit.SECONDS);
	}

	// Page load timeout
    public void  pageLoadTimeout(){
	    driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(pageLoadTimeout), TimeUnit.SECONDS);
    }

	//Wait for element to be clickable
	public void waitForElementToBeClickable(WebElement webElement){
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public void waitForElementToBeClickable(By locator){
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	//Wait for element to be selected
	public void elementToBeSelected(WebElement webElement){
		wait.until(ExpectedConditions.elementToBeSelected(webElement));
	}

	public void elementToBeSelected(By locator){
		wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	//Wait for visibility of Web Element in the page
	public void waitForVisibilityOfWebElement(WebElement webElement){
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitForVisibilityOfWebElement(By locator){
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	//Wait for invisibility of Web Element in the page
	public void waitForInvisibilityOfWebElement(By locator){
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForInvisibilityOfWebElement(By locator, String text){
		wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
	}

	//Wait for visibility of all Web Elements in the page
	public void waitForVisibilityOfAllWebElements(List<WebElement> webElement){
		wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
	}

	public void waitForVisibilityOfAllWebElements(By locator){
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	//Wait for text to be present in the Web Element
	public void waitForTextToBePresentInWebElement(WebElement webElement, String text){
		wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
	}

	//Wait for number of windows to be
	public void waitForNumberOfWindowsToBe(int numberOfWindows){
		wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
	}

	//Wait for element to be located
	public void waitForPresenceOfElementToBeLocated(By locator){
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	//Normal Wait Min
	public void sleepMin(){
		try {
			Thread.sleep(Integer.parseInt(sleepTimeMin));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//Normal Wait Min2
	public void sleepMin2(){
		try {
			Thread.sleep(Integer.parseInt(sleepTimeMin2));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//Normal Wait Average
	public void sleepTimeAverage(){
		try {
			Thread.sleep(Integer.parseInt(sleepTimeAverage));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//Normal Wait Max
	public void setSleepTimeMax(){
		try {
			Thread.sleep(Integer.parseInt(sleepTimeMax));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//Normal Wait Max2
	public void setSleepTimeMax2(){
		try {
			Thread.sleep(Integer.parseInt(sleepTimeMax2));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//Navigate to given URL
    public static void navigateToURL(String url){
        driver.navigate().to(url);
    }

    //Navigate to default URL
    public static void navigateToUrl(){
        navigateToURL(FilesAndFolders.getPropValue("url"));
    }
}
