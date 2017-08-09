package com.sapient.feescalculator.exception;

/**
 * 
 * @author Saurabh
 *
 */
public class TransactionException extends Exception {	

	public TransactionException() {
		super();
	}
	
	public TransactionException(Exception ex) {
		super(ex);
	}
}
