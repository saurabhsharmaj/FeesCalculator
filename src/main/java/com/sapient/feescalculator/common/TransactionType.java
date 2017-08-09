package com.sapient.feescalculator.common;

/**
 * 
 * @author Saurabh
 *
 */
public class TransactionType {
	public enum TRANSACTIONTYPE {
		BUY("BUY", 1), SELL("SELL", 2), DEPOSIT("DEPOSITE", 3), WITHDRAW("WITHDRAW", 4);
		private int type;
		private String name;

		TRANSACTIONTYPE(String name, int type) {
			this.name = name;
			this.type = type;
		}

		public int getType() {
			return type;
		}

		public String getName() {
			return name;
		}
	};
}
