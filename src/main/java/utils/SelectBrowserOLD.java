
package main.java.utils;

import java.util.HashMap;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class SelectBrowserOLD {

	/**
	 * Created by Shaswat Patitpawnam on 12 Nov 2017
	 * Instantiates a new select browser.
	 */

	public static String getBrowserAndVersion(WebDriver wd) {
		String browser_version = null;
		Capabilities cap = ((RemoteWebDriver) wd).getCapabilities();

		String browsername = cap.getBrowserName();
		// This block to find out IE Version number
		if (browsername.equalsIgnoreCase("internet explorer") ) {
			String uAgent = (String) ((JavascriptExecutor) wd).executeScript("return navigator.userAgent;");
			System.out.println(uAgent);
			//uAgent return as "MSIE 8.0 Windows" for IE8
			if (uAgent.contains("MSIE") && uAgent.contains("Windows")) {
				browser_version = uAgent.substring(uAgent.indexOf("MSIE")+5, uAgent.indexOf("Windows")-2);
			} else if (uAgent.contains("Trident/7.0")) {
				browser_version = "11.0";
			} else {
				browser_version = "0.0";
			}
		} else
		{
			//Browser version for Firefox and Chrome
			browser_version = cap.getVersion().toString();// .split(".")[0];
		}
		//String browserversion = browser_version.substring(0, browser_version.indexOf("."));
		String browserversion = browser_version;

		return "Browser Details: " + browsername + "_v" + browserversion;
	}

	/**
	 * Gets the browser.
	 * @return the browser
	 */
	
	static boolean flag = false;
	@SuppressWarnings("deprecation")
	public static WebDriver getBrowser()  {
		WebDriver driver = null;
		String browserName = FilesAndFolders.getPropValue("driverName");
		Reporter.log("Browser name is : '" + browserName + "'", true);

		//Select Browser logic

		if(browserName != null && browserName != "" && !browserName.contains("html")){
			if(!flag){
				System.setProperty(FilesAndFolders.getBrowserPropValue(browserName), 
						FilesAndFolders.getPropValue(browserName));
			}
		}else {
			Reporter.log("Invalid Browser Name : " + "'"+ browserName + "'", true);
		}
		
		//Chrome
		if(browserName.equalsIgnoreCase("chromeWinx32")){
			driver = new ChromeDriver(setDesiredCapabilities());
			
			Reporter.log("Browser session initiated...", true);
			driver.manage().deleteAllCookies();
			Reporter.log(getBrowserAndVersion(driver),true);
		}

		//Headless HTMLUnitDriver
		else if(browserName.equalsIgnoreCase("htmlUnitDriver")){
			return new HtmlUnitDriver();
		}

		//Headless HTMLUnitDriver Emulate Chrome
		else if(browserName.equalsIgnoreCase("htmlUnitDriverChrome")){
			return new HtmlUnitDriver(BrowserVersion.CHROME);
		}

		//Headless HTMLUnitDriver Emulate Firefox v52
		else if(browserName.equalsIgnoreCase("htmlUnitDriverFirefox_v52")){
			return new HtmlUnitDriver(BrowserVersion.FIREFOX_52);
		}
		
		//Headless HTMLUnitDriver Emulate Firefox v45
		else if(browserName.equalsIgnoreCase("htmlUnitDriverFirefox_v45")){
			return new HtmlUnitDriver(BrowserVersion.FIREFOX_45);
		}
		
		//Headless HTMLUnitDriver Emulate Edge
		else if(browserName.equalsIgnoreCase("htmlUnitDriverEdge")){
			return new HtmlUnitDriver(BrowserVersion.EDGE);
		}

		//Headless HTMLUnitDriver Emulate Internet Explorer
		else if(browserName.equalsIgnoreCase("htmlUnitDriverIE")){
			return new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER);
		}

		//Firefox
		else if(browserName.contains("firefoxGeckoWin")){	
			driver = new FirefoxDriver(setDesiredCapabilities());
			Reporter.log("Browser session initiated...", true);
			Reporter.log(getBrowserAndVersion(driver),true);
		}

		//IE
		else if(browserName.contains("ieWinx")){
			driver = new InternetExplorerDriver(new InternetExplorerOptions(setDesiredCapabilities()));
			Reporter.log("Browser session initiated...", true);
			Reporter.log(getBrowserAndVersion(driver),true);
		}

		//Microsoft Edge
		else if(browserName.equalsIgnoreCase("edgeWinx")){
			driver = new EdgeDriver(setDesiredCapabilities());
			Reporter.log("Browser session initiated...", true);
			Reporter.log(getBrowserAndVersion(driver),true);
		}
		
		else {
			Reporter.log("Invalid Browser Name: '" + browserName + "'", true);
		}
		
		//Returns the selected browser instance
		return driver;
	}

	/**
	 * Sets the Desired Capabilities of Browser
	 * @returns DesiredCapabilities instance
	 */
	public static DesiredCapabilities setDesiredCapabilities(){
		String downloadFilepath = System.getProperty("user.dir") + FilesAndFolders.getPropValue("downloadLocationWeb");
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", downloadFilepath);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-notifications");
		
		options.setExperimentalOption("prefs", prefs);

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		return cap;
	}
}
