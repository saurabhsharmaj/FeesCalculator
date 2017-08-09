package com.sapient.feescalculator.business;

import java.util.List;

import com.sapient.feescalculator.common.TransactionFee.TRANSACTIONFEES;
import com.sapient.feescalculator.common.TransactionType.TRANSACTIONTYPE;
import com.sapient.feescalculator.modal.Transaction;

/**
 * 
 * @author Saurabh
 *
 */
public class FeeStrategyImpl implements FeeStrategy {
	/**
	 * This method will be update the fee according to transaction.
	 * @param transaction
	 */
	public Transaction calculateTransactionFee(List<Transaction> transactionList, Transaction transaction) {
		System.out.println(transaction.getClientId()+" - "+transaction.getSecurityId() +" - "+transaction.getTransactionType());
		if(isIntradayTransaction(transactionList, transaction)){
			transaction.setTransactionFees(TRANSACTIONFEES.TEN.getFees());
		} else {

			if(transaction.getPriority()){
				transaction.setTransactionFees(TRANSACTIONFEES.FIVE_HUNDREAD.getFees());

			} else{				
				if(transaction.getTransactionType() == TRANSACTIONTYPE.SELL.getType() ||
						transaction.getTransactionType() == TRANSACTIONTYPE.WITHDRAW.getType()){

					transaction.setTransactionFees(TRANSACTIONFEES.HUNDREAD.getFees());

				} else if(transaction.getTransactionType() == TRANSACTIONTYPE.BUY.getType() ||
						transaction.getTransactionType() == TRANSACTIONTYPE.DEPOSIT.getType()){

					transaction.setTransactionFees(TRANSACTIONFEES.FIFTY.getFees());			
				}

			}

		}
		return transaction;
	}
	
	/**
	 * This method check weather transaction is IntraDay or not.
	 * @param transaction
	 * @return
	 */
	public boolean isIntradayTransaction(List<Transaction> transactionList, Transaction transaction) {
		boolean isIntraDayTransaction= false;
		Transaction temp = null;
		if(transactionList.size() > 0 ){
			for (Transaction trans : transactionList) {
				if(trans.getClientId().equals(transaction.getClientId())&&
						trans.getSecurityId().equals(transaction.getSecurityId()) &&
						trans.getTransactionDate().equals(transaction.getTransactionDate())){
					if((trans.getTransactionType()==TRANSACTIONTYPE.BUY.getType() && 
							transaction.getTransactionType()==TRANSACTIONTYPE.SELL.getType()) ||
							(trans.getTransactionType()==TRANSACTIONTYPE.SELL.getType() && 
							transaction.getTransactionType()==TRANSACTIONTYPE.BUY.getType())){
						isIntraDayTransaction= true;
						temp= trans;						
						break;
					}
				}

			}

			if(temp!=null){
				transactionList.remove(temp);
				temp.setTransactionFees(TRANSACTIONFEES.TEN.getFees());
				transactionList.add(temp);
			}

		} else {
			isIntraDayTransaction= false;
		}
		return isIntraDayTransaction;
	}
}
