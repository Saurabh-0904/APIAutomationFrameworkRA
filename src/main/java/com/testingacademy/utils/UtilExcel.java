package com.testingacademy.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class UtilExcel {

    //Workbook, Sheet, Row and Column, Cells

    //Steps to follow
    //Open the Stream
    //Understand Workbook
    //Sheet
    //Rows and Columns
    //Cells
    //Close Stream - give data to the 2D object

    static Workbook book;
    static Sheet sheet;

    public static String FILE_NAME = "src/test/resources/TestData.xlsx"; //path given of Excel sheet

    public static Object[][] getTestData (String sheetName) {

            //Write the logic to read the excel file and convert this to 2D array

            FileInputStream file = null;
            try {
                file = new FileInputStream(FILE_NAME);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                book = WorkbookFactory.create(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            sheet = book.getSheet(sheetName);

            Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    data[i][j] = sheet.getRow((i + 1)).getCell(j).toString(); // i+1 coz to skip headers which is in 1st row
                }
            }
            return data;
        }

    @DataProvider
    public Object[][] getData() {
    return getTestData("Sheet1");

    }


}
