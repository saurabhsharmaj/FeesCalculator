package com.sapient.feescalculator.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sapient.feescalculator.common.Utils;
import com.sapient.feescalculator.modal.Transaction;

/**
 * This class is used to read transaction from the excel sheet.
 * @author Saurabh
 *
 */
public class ExcelTransactionReader implements Reader {

	@Override
	public List<Transaction> readTransaction(File transactionFile) {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		Workbook workbook = null;
		try{
			FileInputStream inputStream = new FileInputStream(transactionFile);

			workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				if(nextRow.getRowNum()==0){
					continue;
				}

				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Transaction transacton = getTransaction(cellIterator);
				transactionList.add(transacton);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				if(workbook!=null){
					workbook.close();
				}
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		return transactionList;

	}

	private Transaction getTransaction(Iterator<Cell> cellIterator) {
		Transaction.Builder builder = new Transaction.Builder();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();			
			if(cell.getColumnIndex()==0){
				builder.externalTransactionID(cell.getStringCellValue());
			}
			if(cell.getColumnIndex()==1){
				builder.clientId(cell.getStringCellValue());	
			}
			if(cell.getColumnIndex()==2){
				builder.securityId(cell.getStringCellValue());
			}
			if(cell.getColumnIndex()==3){
				builder.transactionType(Utils.parseTransactionType(cell.getStringCellValue()));
			}
			if(cell.getColumnIndex()==4){
				builder.transactionDate(cell.getDateCellValue());
			}
			if(cell.getColumnIndex()==5){
				builder.marketValue(cell.getNumericCellValue());
			}
			if(cell.getColumnIndex()==6){
				builder.priority(Utils.getPriority(cell.getStringCellValue()));
			}
		}
		return builder.build();
	}
}
