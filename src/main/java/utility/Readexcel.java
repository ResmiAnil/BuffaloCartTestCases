package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readexcel {

	public Readexcel() {

	}

	public void read() throws IOException {
		FileInputStream fileIn = new FileInputStream("C:\\Working folder\\selenium\\ExcelTest.xlsx");

		XSSFWorkbook workBook = new XSSFWorkbook(fileIn);
		XSSFSheet readSheet = workBook.getSheet("Sheet1");

		for (Row row : readSheet) {
			for (Cell cell : row) {

				CellType type = cell.getCellType();
				if (type == CellType.STRING) {
					System.out.print(cell.getStringCellValue());
				} else if (type == CellType.NUMERIC) {
					System.out.print(cell.getNumericCellValue());
				}

				System.out.print(" ");
			}
			System.out.println();
		}
		workBook.close();
	}

	// BuffaloCart read excel data based on the Row and Column parameters
	public String readExcelRowCloumn(int row, int column) throws IOException {
		FileInputStream fileIn = new FileInputStream("C:\\Working folder\\selenium\\BuffaloCart.xlsx");

		XSSFWorkbook workBook = new XSSFWorkbook(fileIn);
		XSSFSheet readSheet = workBook.getSheet("Sheet1");

		Row tRow = readSheet.getRow(row);
		Cell cellObj = tRow.getCell(column);

		String retrunString = "";

		CellType type = cellObj.getCellType();
		if (type == CellType.STRING) {
			retrunString = cellObj.getStringCellValue();
		} else if (type == CellType.NUMERIC) {
			retrunString = String.valueOf(cellObj.getNumericCellValue());
		}
		workBook.close();
		return retrunString;
	}
}
