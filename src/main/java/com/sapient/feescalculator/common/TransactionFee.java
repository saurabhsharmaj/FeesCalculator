package com.sapient.feescalculator.common;

/**
 * 
 * @author Saurabh
 *
 */
public class TransactionFee {
	public enum TRANSACTIONFEES {
		FIVE_HUNDREAD(500), HUNDREAD(100), FIFTY(50), TEN(10);
		private double fees;

		TRANSACTIONFEES(double fees) {
			this.fees = fees;
		}

		public double getFees() {
			return fees;
		}
	};
}
