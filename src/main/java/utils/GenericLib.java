package main.java.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class GenericLib {
	private WebDriver driver;
	public GenericLib(WebDriver driver){
		this.driver = driver;
	}

	/**
	 * This method copies the selected text to clipboard
	 * @param text to be copied to clipboard
	 */
	public static void copyTextToClipboard(String text){
		StringSelection selectedString = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selectedString, selectedString);
	}

	/**
	 * This method pastes the content from clipboard to active pointer location
	 */
	public static void pasteTextFromClipboard(){
		Robot robot = null;
		try {robot = new Robot();} catch (AWTException e) {	e.printStackTrace();}
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * This method returns the current year
	 * @return
	 */
	public static int getCurrentYear(){
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
	}

	/**
	 * This method returns the current month
	 * @return
	 */
	public static int getCurrentMonth(){
		Calendar now = Calendar.getInstance();
		return ((now.get(Calendar.MONTH))+1);
	}
	
	/**
	 * This method returns the current date i.e. DD
	 * @return
	 */
	public static int getCurrentDate(){
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.DATE));
	}

	/**
	 * This method returns the current Hour
	 * @return
	 */
	public static int getCurrentHour(){
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.HOUR));
	}

	/**
	 * This method returns the current Minute
	 * @return
	 */
	public static int getCurrentMinute(){
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.MINUTE));
	}

	/**
	 * This method returns the current Second
	 * @return
	 */
	public static int getCurrentSecond(){
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.SECOND));
	}

	/**
	 * This method returns the current MiliSecond
	 * @return
	 */
	public static int getCurrentMiliSecond(){
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.MILLISECOND));
	}

	//Take Screenshot on failure and save in "failure_screenshots" folder
	public void screenshot(String methodName) throws Exception
	{
		Thread.sleep(3000);
		try{
			driver.switchTo().alert().accept();
		}catch(Exception e){}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		String screenshotLocationWeb = FilesAndFolders.getPropValue("screenshotLocationWeb");
		try {
			FileUtils.copyFile(scrFile, new
					File((screenshotLocationWeb + methodName + "_" + formater.format(calendar.getTime())+".png")));
			Reporter.log("<a href='" +
					screenshotLocationWeb + methodName + "_" + formater.format(calendar.getTime()) + ".png'> <imgsrc='" + screenshotLocationWeb + methodName + "_" + formater.format(calendar.getTime()) + ".png' /> </a>");
			Reporter.setCurrentTestResult(null);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
