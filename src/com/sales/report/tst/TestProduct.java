package com.sales.report.tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sales.report.Product;

public class TestProduct {
	
	public static final String TEST_MSG1 = "apple at 10p";
	public static final String TEST_MSG2 = "20 sales of apples at 10p each";
	public static final String TEST_MSG3 = "Add 20p apples";
	public static final String TEST_MSG4 = "Subtract 5p apples";
	public static final String TEST_MSG5 = "Multiply 2p apples";
	
	public static final String PROD_BOTTLE = "Bottle";
	public static final String PROD_WIRE = "Wire";

	static Product prod1;
	Product prod2 = new Product();

	@BeforeClass
	
	public static void setUpBeforeClass() throws Exception {
		prod1 = new Product();	
		prod1.setProd_name(PROD_BOTTLE);
		prod1.setQuantity(45);
		prod1.setTotal_sale(45);
	}
	
	@Before
	public void setUp() throws Exception {
		prod2.setProd_name(PROD_WIRE);
		prod2.setQuantity(40);
		prod2.setTotal_sale(40);		
	}

	@Test
	public void testGetProd_name() {
		assertTrue(prod2.getProd_name()!=null);
		assertTrue(prod2.getProd_name().equals(PROD_WIRE));
	}

	@Test
	public void testSetProd_name() {
		try{
			prod2.setProd_name("MOBILES");
		}
		catch(Exception e)
		{
			assertFalse(e.getMessage(),false);
		}
	}
	
	@Test
	public void testSetProd_nameWithNull() {
		try{
			prod2.setProd_name(null);
		}
		catch(Exception e)
		{
			assertTrue(e.getMessage(),true);
		}
	}

	@Test
	public void testSetProd_nameWithEmpty() {
		try{
			prod2.setProd_name("");
		}
		catch(Exception e)
		{
			assertTrue(e.getMessage(),true);
		}
	}

	@Test
	public void testSetQuantityWithNegativeValues() {
		try{
			prod2.setQuantity(-3);
		}
		catch(Exception e)
		{
			assertFalse(e.getMessage(),true);
		}
	}

	@Test
	public void testGetTotal_sale() {
		prod1.setQuantity(4);
		prod1.setTotal_sale(10);
		prod1.modifyQuantity(2, 20);
		if(prod1.getTotal_sale() != 50)
		{
			assertFalse("Total sale did not match the expected value",true);
		}
		if(prod1.getQuantity() != 6)
		{
			assertFalse("Total sale did not match the expected value",true);
		}
	}

	@Test
	public void testSetTotal_sale() {
		try{
			prod2.setTotal_sale(90);
		}
		catch(Exception e)
		{
			assertFalse(e.getMessage(),true);
		}	
	}

	@Test
	public void testModifySaleVal() {
		prod1.setQuantity(10);
		try
		{
			prod1.setTotal_sale(10);		
			prod1.modifySaleVal(Product.ADD, 5);
			if(prod1.getTotal_sale() != 15)
			{
				assertFalse("Total sale did not match the expected value",true);
			}

			prod1.setTotal_sale(80);		
			prod1.modifySaleVal(Product.SUBTRACT, 5);
			if(prod1.getTotal_sale() != 75)
			{
				assertFalse("Total sale did not match the expected value",true);
			}

			prod1.setTotal_sale(80);		
			prod1.modifySaleVal(Product.MULTIPLY, 5);
			if(prod1.getTotal_sale() != 400)
			{
				assertFalse("Total sale did not match the expected value",true);
			}
		}
		catch(Exception e)
		{
			assertFalse(e.getMessage(),true);
		}
	
	}
	
	@Test
	public void testModifySaleValInvalidOperation() {
		try{
			prod1.modifySaleVal("DIVIDE", 5);
			}
			catch(Exception e)
			{
				assertTrue(e.getMessage().equals("Operation DIVIDE not supported."));
			}
	}

	@Test
	public void testModifyQuantity() {
		
		prod1.setQuantity(10);
		prod1.setTotal_sale(10);		
		prod1.modifyQuantity(2, 5);
		if(prod1.getQuantity() != 12 && prod1.getTotal_sale() != 20)
		{
			assertFalse("Total quantity or total sale value did not match the expected values",true);
		}
	}

}
