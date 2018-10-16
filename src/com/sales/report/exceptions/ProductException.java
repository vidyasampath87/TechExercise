package com.sales.report.exceptions;

public class ProductException extends Exception {

	private static final long serialVersionUID = 1L;
	String message;
	public ProductException(String msg)
	{
		super(msg);
		this.message = msg;
	}
}
