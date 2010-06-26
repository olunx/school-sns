package cn.gdpu.util.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadExcel {

	/**
	 * 读取Excel的内容，返回原始数据，最后一位为属性列数
	 * 
	 * @param filePath
	 * @return
	 */
	public List<String> readExcel(String filePath) {

		// 用于保存返回的数据
		List<String> resultData = new ArrayList<String>();

		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(new File(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		HSSFWorkbook excel = null;
		try {
			excel = new HSSFWorkbook(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		//int sheetNo = excel.getNumberOfSheets();
		int sheetNo = 1;//只获取第一个工作表
		int sheetRows = 0;
		int rowCells = 0;
		for (int i = 0; i < sheetNo; i++) {
			sheet = excel.getSheetAt(i);
			sheetRows = sheet.getPhysicalNumberOfRows();
			for (int j = 0; j < sheetRows; j++) {
				row = sheet.getRow(j);
				rowCells = row.getPhysicalNumberOfCells();
				for (int k = 0; k < rowCells; k++) {
					cell = row.getCell(k);
					if (cell == null)
						continue;
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						// System.out.println("行  " + j + "  列  " + k + "  " +
						// (long)cell.getNumericCellValue());
						resultData.add(String.valueOf((long) cell.getNumericCellValue()));
						break;
					case HSSFCell.CELL_TYPE_STRING:
						// System.out.println("行  " + j + "  列  " + k + "  " +
						// cell);
						resultData.add(cell.toString());
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						// System.out.println("行  " + j + "  列  " + k + "  " +
						// "null");
						resultData.add("0");
						break;
					}
				}
			}
		}

		// 获取属性列数
		resultData.add(String.valueOf(rowCells));

		return resultData;
	}

}
