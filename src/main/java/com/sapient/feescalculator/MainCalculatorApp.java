package com.sapient.feescalculator;

import java.io.File;
import java.util.List;

import com.sapient.feescalculator.business.TransactionManager;
import com.sapient.feescalculator.common.FileType.FILETYPE;
import com.sapient.feescalculator.modal.Transaction;

/**
 * This is main class which read & display the report.
 * @author Saurabh
 *
 */
public class MainCalculatorApp {

	public static void main(String[] args) {
		TransactionManager tManager = new TransactionManager();
		//Read Excel/CSV/TEXT/XML
		File transactionfile = new File(new File("").getAbsolutePath(),"resource/SampleData.xlsx");
		
		List<Transaction> list = tManager.readTransaction(FILETYPE.EXCEL,transactionfile);
		List<Transaction> results=tManager.saveAndCalTransaction(list);
		tManager.displayTransactionReport(results);
	}

}
