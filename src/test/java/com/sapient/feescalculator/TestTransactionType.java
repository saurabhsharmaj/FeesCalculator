package com.sapient.feescalculator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sapient.feescalculator.business.FeeStrategy;
import com.sapient.feescalculator.business.FeeStrategyImpl;
import com.sapient.feescalculator.modal.Transaction;

public class TestTransactionType {

	List<Transaction> transactionList = new ArrayList<Transaction>();
	@Test
	public void testIntraDayTransaction() {
		FeeStrategy fs = new FeeStrategyImpl();
		Transaction tran= new Transaction();
		tran.setClientId("AS");
		tran.setExternalTransactionID("SAPEXTXN1");
		tran.setMarketValue(101.93);
		tran.setPriority(true);
		tran.setSecurityId("ICICI");
		tran.setTransactionDate(new Date());
		tran.setTransactionFees(10.0);
		tran.setTransactionType(1);
		transactionList.add(tran);
		Transaction tran1= new Transaction();
		tran1.setClientId("AS");
		tran1.setExternalTransactionID("SAPEXTXN2");
		tran1.setMarketValue(103.93);
		tran1.setPriority(true);
		tran1.setSecurityId("ICICI");
		tran1.setTransactionDate(new Date());
		tran1.setTransactionFees(10.0);
		tran1.setTransactionType(2);
		transactionList.add(tran1);
		boolean res = fs.isIntradayTransaction(transactionList, tran1);
		Assert.assertEquals(res,true);
	}
	
	@Test
	public void testNotIntraDayTransaction() {
		FeeStrategy fs = new FeeStrategyImpl();
		Transaction tran= new Transaction();
		tran.setClientId("AS");
		tran.setExternalTransactionID("SAPEXTXN1");
		tran.setMarketValue(101.93);
		tran.setPriority(true);
		tran.setSecurityId("ICICI");		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		tran.setTransactionDate(cal.getTime());
		tran.setTransactionFees(10.0);
		tran.setTransactionType(1);
		transactionList.add(tran);
		Transaction tran1= new Transaction();
		tran1.setClientId("AS");
		tran1.setExternalTransactionID("SAPEXTXN2");
		tran1.setMarketValue(103.93);
		tran1.setPriority(true);
		tran1.setSecurityId("ICICI");
		tran1.setTransactionDate(new Date());
		tran1.setTransactionFees(10.0);
		tran1.setTransactionType(2);
		transactionList.add(tran1);
		boolean res = fs.isIntradayTransaction(transactionList, tran1);
		Assert.assertEquals(res,false);
	}

}
