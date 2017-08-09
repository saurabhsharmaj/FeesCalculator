package com.sapient.feescalculator.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sapient.feescalculator.common.Utils;
import com.sapient.feescalculator.modal.Transaction;

public class TXTTransactionReader extends AbstractReader implements Reader {

	@Override
	public List<Transaction> readTransaction(File transactionFile) {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(transactionFile));
			while ((line = br.readLine()) != null) {
				String[] transactionAttributes = line.split(cvsSplitBy);
				Transaction transaction = getTransaction(transactionAttributes);
				transactionList.add(transaction);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return transactionList;
	}

	private Transaction getTransaction(String[] transactionAttributes) {
		return new Transaction.Builder().externalTransactionID(transactionAttributes[0])
				.clientId(transactionAttributes[1]).securityId(transactionAttributes[2])
				.transactionType(Utils.parseTransactionType(transactionAttributes[3]))
				.transactionDate(Utils.parseDate(transactionAttributes[4]))
				.marketValue(Utils.parseMarketValue(transactionAttributes[5]))
				.priority(Utils.getPriority(transactionAttributes[6])).build();

	}

}
