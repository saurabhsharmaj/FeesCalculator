package com.sapient.feescalculator.reader;

import java.io.File;
import java.util.List;

import com.sapient.feescalculator.modal.Transaction;

/**
 * 
 * @author Saurabh
 *
 */
public interface Reader {
	public abstract List<Transaction> readTransaction(File transactionFile);
}
