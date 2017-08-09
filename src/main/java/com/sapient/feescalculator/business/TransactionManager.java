package com.sapient.feescalculator.business;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sapient.feescalculator.common.FileType.FILETYPE;
import com.sapient.feescalculator.common.Utils;
import com.sapient.feescalculator.modal.Transaction;
import com.sapient.feescalculator.reader.CSVTransactionReader;
import com.sapient.feescalculator.reader.ExcelTransactionReader;
import com.sapient.feescalculator.reader.TXTTransactionReader;
import com.sapient.feescalculator.reader.XMLTransactionReader;

/**
 * This Transaction manager class is used to read & calculate the transaction fees.
 * @author Saurabh
 *
 */
public class TransactionManager {


	/**
	 * This methods used to display the transaction list.
	 * @param transactionList
	 */
	public void displayTransactionReport(List<Transaction> transactionList) {

		Collections.sort(transactionList,new Transaction());
		System.out.println("Calculated Fees:-");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Client Id | Transaction Type | Transaction Date | Priority | Processing Fee    |");
		for (Transaction transaction : transactionList) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println(transaction.getClientId()+"\t| "+Utils.getTypeName(transaction.getTransactionType())+"\t| "+
					transaction.getTransactionDate()+"\t| "+(transaction.getPriority()?"HIGH\t":"NORMAL")+ "\t| "+
					transaction.getTransactionFees()+"\t|");
		}
		System.out.println("--------------------------------------------------------------------------------");	

	}

	/**
	 * This method call the read the files & convert them into list of objects.
	 * @param ftype
	 * @param transactionfile
	 * @return
	 */
	public List<Transaction> readTransaction(FILETYPE ftype, File transactionfile) {
		if(ftype.equals(FILETYPE.EXCEL)){
			return new ExcelTransactionReader().readTransaction(transactionfile);
		}if(ftype.equals(FILETYPE.TEXT)){
			return new TXTTransactionReader().readTransaction(transactionfile);
		}else if(ftype.equals(FILETYPE.CSV)){
			return new CSVTransactionReader().readTransaction(transactionfile);
		} else if(ftype.equals(FILETYPE.XML)){
			return new XMLTransactionReader().readTransaction(transactionfile);
		}
		return null;
	}

	/**
	 * This method is used to save and calculate the actual fees over the transaction.
	 * @param transactions
	 * @return
	 */
	public List<Transaction> saveAndCalTransaction(List<Transaction> transactions){
		List<Transaction> results=new ArrayList<Transaction>(transactions.size());
		for (Transaction trans : transactions) {
			try{
			results.add(new FeeStrategyImpl().calculateTransactionFee(transactions, trans));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return results;
	}

}
