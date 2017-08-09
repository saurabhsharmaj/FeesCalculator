package com.sapient.feescalculator.business;

import java.util.List;

import com.sapient.feescalculator.modal.Transaction;

/**
 * 
 * @author Saurabh
 *
 */
public interface FeeStrategy {
	Transaction calculateTransactionFee(List<Transaction> transactionList, Transaction transaction);
	boolean isIntradayTransaction(List<Transaction> transactionList, Transaction transaction);
}
