package com.imad.retailstore.error;

public class RetailStoreException extends Exception {
	private static final long serialVersionUID = 1L;

	public RetailStoreException(String msg, Throwable e) {
		super(msg, e);
	}

	public RetailStoreException(String msg) {
		super(msg);
	}

	public RetailStoreException(Throwable e) {
		super(e);
	}

}
