package com.vedban.genericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class is used to store all the common methods of the external files
 * @author USER
 *
 */
public class FileLibrary {
	FileInputStream fis;
	FileInputStream fis1;
	Properties property;
	Workbook wb;
	public void openCommanDataFile(String filepath)
	{		
		try {
			 fis=new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error while opening the Common Data File");
		}
		 property=new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error during loading the properties");
		}
	}
	/**
	 * This method is used to fetch common data
	 * @param key
	 * @return
	 */
	public String getCommonData(String key)
	{
		return property.getProperty(key);		
	}
	/**
	 * This method is used to open the test data file
	 * @param filepath
	 */
	public void openTestDataFile(String filepath)
	{
		try {
			fis1=new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error while opening the Test Data File");
		}
		try {
			wb = WorkbookFactory.create(fis1);
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error while creating the Test Data File");
		}
	}
	/**
	 * This method is used to get the test data
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 */
	public String getTestData(String sheetName, int rowNumber, int cellNumber)
	{
		return wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
	}
	/**
	 * This method is used to set the data
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param value
	 */
	public void setTestCaseData(String sheetName, int rowNumber, int cellNumber, String value)
	{
		wb.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(value);
	}
	public void closeTestDataFile()
	{
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem during closing TestDataFile");
		}
	}
	public void openTestDataFileToWrite(String filepath)
	{
		FileOutputStream fos = null;
		try {
		 fos=new FileOutputStream(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error during opening File");
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error during writing in File");
		}
	}
}
