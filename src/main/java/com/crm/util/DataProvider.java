package com.crm.util;

import java.io.File;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

public class DataProvider
{
	public static ArrayList<Object[]> getTestData()throws Exception
	{
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		Workbook wb = Workbook.getWorkbook(new File(System.getProperty("user.dir")+"/resources/PersonDetails.xls"));
		Sheet sh = wb.getSheet("PersonDetails");
		for(int row = 1; row<sh.getRows(); row++)
		{
			String initialTitle = sh.getCell(0, row).getContents();
			String fname = sh.getCell(1, row).getContents();
			String lname = sh.getCell(2, row).getContents();
			
			Object[] ob = {initialTitle,fname,lname};
			
			myData.add(ob);
		}
		
		
		
		return myData;
	}
}
