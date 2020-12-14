package com.appium.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	static int rowcount;
	public static String ReadExcelwParameters(int row, int col,int sheetnumber) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream file = new FileInputStream("C:\\Automation\\Amazon\\InputDataAmazon.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(sheetnumber);
		rowcount = workbook.getSheetAt(1).getLastRowNum();
		return sheet.getRow(row).getCell(col).getStringCellValue();
	}
	public static void writeXLSXFile(int row, int col, String result, int sheetNumber) throws IOException {
		try {
			FileInputStream file = new FileInputStream(new File("C:\\Automation\\Amazon\\InputDataAmazon.xlsx"));
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
			Cell cell = null;
			XSSFRow sheetrow = sheet.getRow(row);
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAll();
			if(sheetrow == null){
				sheetrow = sheet.createRow(row);
			}
			cell = sheetrow.getCell(col);
			if(cell == null){
				cell = sheetrow.createCell(col);
			}
			cell.setCellValue(result);
			evaluator.evaluateAll();
			file.close();
			FileOutputStream outFile =new FileOutputStream("C:\\Automation\\Amazon\\InputDataAmazon.xlsx");
			workbook.write(outFile);
			evaluator.evaluateAll();
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}