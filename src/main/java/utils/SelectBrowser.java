
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
import org.testng.Assert;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class SelectBrowser {

	/**
	 * Created by Shaswat Patitpawnam on 12 Nov 2017
	 * Instantiates a new select browser.
	 */

	
	/**
	 * Gets the browser.
	 * @return the browser
	 */
	
	@SuppressWarnings("deprecation")
	public static WebDriver getBrowser()  {
		WebDriver driver = null;
		String browserName = FilesAndFolders.getPropValue("driverName");
		Reporter.log("Browser name is : '" + browserName + "'", true);

		//Select Browser logic

		if(browserName != null && browserName != "" && !browserName.contains("html")){
			System.setProperty(FilesAndFolders.getBrowserPropValue(browserName), 
					FilesAndFolders.getPropValue(browserName));
		}
		
		//Chrome
		if(browserName.equalsIgnoreCase("chromeWinx32")){
			driver = new ChromeDriver(setDesiredCapabilities());
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
		}

		//IE
		else if(browserName.contains("ieWinx")){
			driver = new InternetExplorerDriver(new InternetExplorerOptions(setDesiredCapabilities()));
		}

		//Microsoft Edge
		else if(browserName.equalsIgnoreCase("edgeWinx")){
			driver = new EdgeDriver(setDesiredCapabilities());
		}
		
		else {
			Reporter.log("Invalid Browser Name: '" + browserName + "'", true);
			Assert.fail("Failed due to 'Invalid Browser Name...!!'");
		}
		
		Reporter.log("Browser session initiated...", true);
		
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
