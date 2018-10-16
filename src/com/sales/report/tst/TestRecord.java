package com.sales.report.tst;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sales.report.Product;
import com.sales.report.SaleRecord;

public class TestRecord {

	SaleRecord salerecord = new SaleRecord();
	public static final String SALE_MESSAGE_1 = "apple at 10p";
	public static final String SALE_MESSAGE_2 = "20 sales of apples at 10p each";
	public static final String SALE_MESSAGE_3 = "Add 20p apples";
	public static final String SALE_MESSAGE_4 = "Subtract 5p apples";
	public static final String SALE_MESSAGE_5 = "Multiply 2p apples";
	
	public static final String INVALID_SALE_MESSAGE_1 = " ";
	public static final String INVALID_SALE_MESSAGE_2 = null;
	public static final String INVALID_SALE_MESSAGE_3 = "orange of 10p";
	public static final String INVALID_SALE_MESSAGE_4 = "\\apple of 10p";
	public static final String INVALID_SALE_MESSAGE_5 = "apple of oranges";
	public static final String INVALID_SALE_MESSAGE_6 = "Divide apple of oranges";
	public static final String INVALID_SALE_MESSAGE_7 = "20 sales of apples";
	public static final String INVALID_SALE_MESSAGE_8 = "Sub 5p apples";


	@Test
	public void testSaleMessage() {
		try{
			
			//SALE MESSAGE 1
			salerecord.processMsg(SALE_MESSAGE_1);
			Map<String, Product> prodrecord = salerecord.getProductDetails();
			Product prod = prodrecord.get("apples");
			if(prod==null)
			{
				assertFalse("Null product obtained.",true);
			}
			else
			{
				if(prod.getQuantity()!=1)
				{
					assertFalse("Incorrect quantity of product.",true);
				}
				if(prod.getTotal_sale()!=10)
				{
					assertFalse("Incorrect total sale value of product.",true);
				}
				
			}
			
			//SALE MESSAGE 2
			salerecord.processMsg(SALE_MESSAGE_2);
			prodrecord = salerecord.getProductDetails();
			prod = prodrecord.get("apples");
			if(prod==null)
			{
				assertFalse("Null product obtained.",true);
			}
			else
			{
				if(prod.getQuantity()!=21)
				{
					assertFalse("Incorrect quantity of product.",true);
				}
				if(prod.getTotal_sale()!=210)
				{
					assertFalse("Incorrect total sale value of product.",true);
				}
				
			}
			
			//SALE MESSAGE 3
			salerecord.processMsg(SALE_MESSAGE_3);
			prodrecord = salerecord.getProductDetails();
			prod = prodrecord.get("apples");
			if(prod==null)
			{
				assertFalse("Null product obtained.",true);
			}
			else
			{
				if(prod.getQuantity()!=21)
				{
					assertFalse("Incorrect quantity of product.",true);
				}
				if(prod.getTotal_sale()!=230)
				{
					assertFalse("Incorrect total sale value of product.",true);
				}
				
			}
			
			//SALE MESSAGE 4
			salerecord.processMsg(SALE_MESSAGE_4);
			prodrecord = salerecord.getProductDetails();
			prod = prodrecord.get("apples");
			if(prod==null)
			{
				assertFalse("Null product obtained.",true);
			}
			else
			{
				if(prod.getQuantity()!=21)
				{
					assertFalse("Incorrect quantity of product.",true);
				}
				if(prod.getTotal_sale()!=225)
				{
					assertFalse("Incorrect total sale value of product.",true);
				}
				
			}
			
			//SALE MESSAGE 5
			salerecord.processMsg(SALE_MESSAGE_5);
			prodrecord = salerecord.getProductDetails();
			prod = prodrecord.get("apples");
			if(prod==null)
			{
				assertFalse("Null product obtained.",true);
			}
			else
			{
				if(prod.getQuantity()!=21)
				{
					assertFalse("Incorrect quantity of product.",true);
				}
				if(prod.getTotal_sale()!=450)
				{
					assertFalse("Incorrect total sale value of product.",true);
				}
				
			}
			
		}
		catch(Exception e)
		{
			assertFalse(e.getMessage(),true);
		}
	}
	
	@Test
	public void testgetMessagetype() {
		
		try{
		if(salerecord.getMsgType(SALE_MESSAGE_1) == -1)
		{
			assertFalse("Incorrect Message type.",true);
		}
		if(salerecord.getMsgType(SALE_MESSAGE_2) == -1)
		{
			assertFalse("Incorrect Message type.",true);
		}
		if(salerecord.getMsgType(SALE_MESSAGE_3.toLowerCase()) == -1)
		{
			assertFalse("Incorrect Message type.",true);
		}
		if(salerecord.getMsgType(SALE_MESSAGE_4.toLowerCase()) == -1)
		{
			assertFalse("Incorrect Message type.",true);
		}
		if(salerecord.getMsgType(SALE_MESSAGE_5.toLowerCase()) == -1)
		{
			assertFalse("Incorrect Message type.",true);
		}
		
		if(salerecord.getMsgType(INVALID_SALE_MESSAGE_1) != -1)
		{
			assertFalse("Incorrect Validation of messages",true);
		}
		if(salerecord.getMsgType(INVALID_SALE_MESSAGE_2) != -1)
		{
			assertFalse("Incorrect Validation of messages",true);
		}
		if(salerecord.getMsgType(INVALID_SALE_MESSAGE_3) != -1)
		{
			assertFalse("Incorrect Validation of messages",true);
		}
		if(salerecord.getMsgType(INVALID_SALE_MESSAGE_4) != -1)
		{
			assertFalse("Incorrect Validation of messages",true);
		}
		if(salerecord.getMsgType(INVALID_SALE_MESSAGE_5) != -1)
		{
			assertFalse("Incorrect Validation of messages",true);
		}
		if(salerecord.getMsgType(INVALID_SALE_MESSAGE_6) != -1)
		{
			assertFalse("Incorrect Validation of messages",true);
		}
		if(salerecord.getMsgType(INVALID_SALE_MESSAGE_7) != -1)
		{
			assertFalse("Incorrect Validation of messages",true);
		}
		if(salerecord.getMsgType(INVALID_SALE_MESSAGE_8) != -1)
		{
			assertFalse("Incorrect Validation of messages",true);
		}
		}
		catch(Exception e)
		{
			assertFalse(e.getMessage(), true);
		}
		
		
	}

}
