package com.sales.report.exceptions;

public class RecordException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	String message;
	public RecordException(String msg)
	{
		super(msg);
		this.message = msg;
	}

}
