package com.sapient.feescalculator.reader;

import java.util.ArrayList;
import java.util.List;

import com.sapient.feescalculator.business.FeeStrategyImpl;
import com.sapient.feescalculator.modal.Transaction;

/**
 * 
 * @author Saurabh
 *
 */
public abstract class AbstractReader implements Reader {
	public void saveAndCalTransaction(List<Transaction> transactionList, Transaction transaction){
		if(transactionList==null){
			transactionList = new ArrayList<Transaction>();
		}
		transactionList.add(new FeeStrategyImpl().calculateTransactionFee(transactionList, transaction));
	}
}
