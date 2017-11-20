package main.java.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import jxl.Cell;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FilesAndFolders {

	//Get value of Key from config.properties file
	public static String getPropValue(String key) 
	{
		FileReader reader = null;
		try {
			reader = new FileReader("./src/main/resources/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		Properties prop = new Properties();
		try {
			prop.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} 

		String propValue = prop.getProperty(key);
		return propValue;
	}

	//Get value of Key from browser.properties file
		public static String getBrowserPropValue(String key) 
		{
			FileReader reader = null;
			try {
				reader = new FileReader("./src/main/resources/browser.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			Properties prop = new Properties();
			try {
				prop.load(reader);
			} catch (IOException e) {
				e.printStackTrace();
			} 

			String propValue = prop.getProperty(key);
			return propValue;
		}

	//Read from Excel
	public static Cell[] readExcel(String sheetName, String uniqueValue) throws BiffException, IOException,Exception
	{
		Workbook wrk1;
		Sheet sheet1;
		//Cell colRow;

		String testDataForDefaultReadExcel = getPropValue("testDataForDefaultReadExcel");
		wrk1 = Workbook.getWorkbook(new File(testDataForDefaultReadExcel)); // Connecting to excel workbook.
		sheet1 = wrk1.getSheet(sheetName); // Connecting to the sheet

		LabelCell cell=sheet1.findLabelCell(uniqueValue);
		int row=cell.getRow();
		Cell[] record = sheet1.getRow(row);
		return record;
	}

	//Read from Specified Excel
	public static Cell[] readExcel(String filePath, String sheetName, String uniqueValue) throws BiffException, IOException, Exception
	{
		Workbook wrk1;
		Sheet sheet1;

		wrk1 = Workbook.getWorkbook(new File(filePath)); // Connecting to excel workbook.
		sheet1 = wrk1.getSheet(sheetName); // Connecting to the sheet

		LabelCell cell=sheet1.findLabelCell(uniqueValue);
		int row=cell.getRow();
		Cell[] record = sheet1.getRow(row);
		return record;
	}

	//Upload File
	public static void uploadAttachment(String attachmentPath){
		StringSelection selectedString = new StringSelection(attachmentPath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selectedString, selectedString);
		Robot robot = null;
		try {robot = new Robot();} catch (AWTException e) {	e.printStackTrace();}
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	public static boolean fileExists(String fileNameWithPath)
	{
		// Example format for "fileNameWithPath" = System.getProperty("user.dir")+ "\\downloads\\abc.xls";

		File tmpFile = new File(fileNameWithPath);
		boolean fileExists = tmpFile.exists();
		
		Assert.assertTrue(fileExists == true, "Error!! File doesn't exist at location '" + fileNameWithPath + "'"); 
		
		return fileExists;
	}

}
