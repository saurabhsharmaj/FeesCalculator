package com.sapient.feescalculator;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.sapient.feescalculator.business.TransactionManager;
import com.sapient.feescalculator.common.FileType.FILETYPE;
import com.sapient.feescalculator.modal.Transaction;

public class TestTransactionReader {

	@Test
	public void testReader() {
		File transactionfile = new File(new File("").getAbsolutePath(),"resource/sampleData.xlsx");
		List<Transaction> list = new TransactionManager().readTransaction(FILETYPE.EXCEL,transactionfile);	
		assert(list.size()==20);
	}

}
