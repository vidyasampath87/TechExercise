package com.sales.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sales.report.exceptions.RecordException;

/**This Class 'Record' receives the sale notification messages and processes them.
 * For every new product received Product object is created and stored in the record.
 * @author Vidya
 *
 */
public class SaleRecord {

	
	/** counter to store the number of sale notification messages */
	static int sale_msg_count;
	
	/** Map having a product name and product details */
	private static Map<String, Product> product_detail =  new HashMap<String, Product>();
	
	/** List of adjustments done */
	private static List<String> sale_adjustments = new ArrayList<String>();
	
	/** Constants */
	static final String ADD = "add";
	static final String SUBTRACT = "subtract";
	static final String MULTIPLY = "multiply";
	static final int MAX_SALE = 50;
	public static final String USAGE = "USAGE: Please provide the sale message in one of the following formats:\n"+
									   "MESSAGE TYPE 1: Details of 1 sale E.g apple at 10p\n"+
									   "MESSAGE TYPE 2: Details of a sale and the number of occurrences of that sale. E.g 20 sales of apples at 10p each.\n"+
									   "MESSAGE TYPE 3: Details of a sale and an adjustment operation to be applied to all stored sales of this product type.  Ex: Add 20p apples";
	public static final String INVALID_MSG = "ERROR: Invalid Message type received.";

	/**
	 * Generates a report of sales and prints to the console
	 */
	public void generateReport()
	{
		PrintSales util = new PrintSales();
		//For every 10th message received print a report of the product details and total sale value.
		if(sale_msg_count%10 == 0)		
		{
			util.printProductDetail(product_detail);	
		}
		//If sale count reaches the maximum value print a report of the sale detail and the adjustment made.
		if(	sale_msg_count == MAX_SALE)
		{
			util.printAdjustments(sale_adjustments);
		}
	}
	
	
	/** To process message of type 1:
	 *  Message Type 1 – contains the details of 1 sale E.g apple at 10p
	 * @param input
	 * @throws RecordException 
	 */
	public void processMsgType1(String input) throws RecordException
	{
		Product prod ;
		StringTokenizer msgtoken = new StringTokenizer(input, " ");
		String prod_name;
		int sale_value;

		try
		{
			while(msgtoken.hasMoreTokens())
			{
				//Ex: apple at 10p
				//Extract the product name
				prod_name = msgtoken.nextToken();

				//to skip word "at"
				msgtoken.nextToken();


				String sale_val = msgtoken.nextToken();
				sale_value = Integer.parseInt(sale_val.substring(0, sale_val.length() - 1));

				//If product is already present, increment the quantity and sale value
				if(product_detail.get(prod_name+'s') != null)
				{
					prod = product_detail.get(prod_name+'s');
					prod.modifyQuantity(1, sale_value);
				}
				else
				{
					//Add new product
					prod = new Product();
					prod_name = prod_name + 's';
					prod.setProd_name(prod_name);
					prod.setQuantity(1);
					prod.setTotal_sale(sale_value);
					product_detail.put(prod_name, prod);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("ERROR:Unable to process message");
			throw new RecordException(e.getMessage());
		}
	}
	
	
	/**To process message of type 2:
	 * Message Type 2 – contains the details of a sale and the number of occurrences of that sale. E.g 20 sales of apples at 10p each.
	 * @param input
	 * @throws RecordException 
	 */
	public void processMsgType2(String input) throws RecordException
	{
		Product prod ;
		input = input.toLowerCase();
		
		StringTokenizer msgtoken = new StringTokenizer(input, " ");
		String prod_name;
		String modify_val;
		try
		{
			while(msgtoken.hasMoreTokens())
			{
				//Extract the quantity of product
				int prod_quantity = Integer.parseInt(msgtoken.nextToken());
				//Skip the word 'sales'
				msgtoken.nextToken();
				//Skip the word 'of'
				msgtoken.nextToken();
				//Extract the product name
				prod_name =  msgtoken.nextToken();
				//Skip the word 'at'
				msgtoken.nextToken();
				//Extract the amount
				modify_val = msgtoken.nextToken();
				int op_val = Integer.parseInt(modify_val.substring(0, modify_val.length() - 1));
				//Skip the word 'each'
				msgtoken.nextToken();

				if(product_detail.get(prod_name) == null)
				{
					//Add new product
					prod = new Product();
					prod.setProd_name(prod_name);
					prod.modifyQuantity(prod_quantity, op_val);
					product_detail.put(prod_name, prod);
				}
				else
				{			
					prod = product_detail.get(prod_name);
					prod.modifyQuantity(prod_quantity, op_val);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("ERROR:Unable to process message");
			throw new RecordException(e.getMessage());
		}
	}
	
	/**To process message of type 3:
	 * Message Type 3 – contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. 
	 * @param input
	 */
	public void processMsgType3(String input)
	{
		Product prod ;
		StringTokenizer msgtoken = new StringTokenizer(input, " ");
		String prod_name;
		String operation, modify_val;
		try{
			// Ex: Add 20p apples
			while(msgtoken.hasMoreTokens())
			{
				//Extract operation
				operation = msgtoken.nextToken();
				//Extract the amount
				modify_val = msgtoken.nextToken();
				int op_val = Integer.parseInt(modify_val.substring(0, modify_val.length() - 1));
				//Extract the product name
				prod_name =  msgtoken.nextToken();

				//If no previous record of the product is present print error message
				
				if(product_detail.get(prod_name) == null)
				{
					System.out.println("\nERROR: No previous record of the product "+ prod_name + " found.");
					return;
				}

				//Get the Product object from Map and modify its values  
				prod = product_detail.get(prod_name);
				prod.modifySaleVal(operation, op_val);

				//Record the adjustment made for this sale
				String adjustment = null;
				switch(operation.toLowerCase())
				{
				case ADD: adjustment = "Product "+prod_name+" total sale value added with "+ modify_val;
						break;
				case SUBTRACT: adjustment = "Product "+prod_name+" total sale value subtracted by "+ modify_val;
						break;
				case MULTIPLY: adjustment = "Product "+prod_name+" total sale value multiplied with "+ modify_val;
						break;
				}
				
				sale_adjustments.add(adjustment);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR:Unable to process message");
			System.out.println(e.getMessage());
			System.out.print(e.getStackTrace());
		}
	}
		
	
	/**Get message type.
	 * @param input
	 * @return
	 */
	public int getMsgType(String input)
	{
		if(input == null)
			return -1;
		StringTokenizer msgtoken = new StringTokenizer(input, " ");
		int token_count = msgtoken.countTokens();
		
		
		if(token_count != 3 && token_count != 7)
			return -1;
		
		//E.g apple at 10p
		Pattern pattern1 = Pattern.compile(".*\\s\\bat\\b.*p");
		//Ex:20 sales of apples at 10p each
		Pattern pattern2 = Pattern.compile(".*\\s\\bsales\\b\\s\\bof\\b\\s.*\\s\\bat\\b\\s.*p\\s\\beach");
		//e.g Add 20p apples
		Pattern pattern3 = Pattern.compile("^(add|subtract|multiply)\\s.*p\\s.*");		
		
		
		Matcher matcher = pattern1.matcher(input);
		if(matcher.matches())
		{
			return 1;
		}
		
		matcher = pattern2.matcher(input);
		if(matcher.matches())
		{
			return 2;
		}
		matcher = pattern3.matcher(input);
		if(matcher.matches())
		{
			return 3;
		}
		
		else 
			return -1;
	}
	
	/**This method is used to process the sale message. Sale message type can be one of these:
	 * Message Type 1 – contains the details of 1 sale E.g apple at 10p
	 * Message Type 2 – contains the details of a sale and the number of occurrences of that sale. E.g 20 sales of apples at 10p each.
	 * Message Type 3 – contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. 
	 * Operations can be add, subtract, or multiply e.g Add 20p apples would instruct your application to add 20p to each sale of apples you have recorded.
	 * @param input
	 */
	public void processMsg(String input)
	{
		//Increase the message counter for every message received.
		sale_msg_count++;
		
		input = input.toLowerCase();
		try
		{   int msgType = getMsgType(input);
		
			switch(msgType)
			{
			case 1: processMsgType1(input);
					break;
			case 2: processMsgType2(input);
					break;
			case 3: processMsgType3(input);
					break;
			default:System.out.println(INVALID_MSG);
					break;
			}		
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		//Generate report
		generateReport();
	}
	
	/**Return the map having sale and product details
	 * @return
	 */
	public Map<String, Product> getProductDetails() {
		return product_detail;
	}
	
	/**Returns the adjustments done.
	 * @return
	 */
	public  List<String> getSaleRecordDetails() {
		return sale_adjustments;
	}
	
	public static void main(String[] args) {

		System.out.println(USAGE);
		//Read the messages from Console
		Scanner sc = new Scanner(System.in);
		SaleRecord rec = new SaleRecord();
		try{
			//Read input from console till you reach maximum sale count
			while (sale_msg_count<MAX_SALE && sc.hasNextLine())
			{
				rec.processMsg(sc.nextLine());
			}
			sc.close();
		}
		catch(Exception e)
		{
			System.out.println("Unable to read inputs: "+e.getMessage());
		}
	}

	
//FURTHER IMPROVEMENTS:	
//1. Encapsulate message processing activity to a separate MessageProcessor class.
//2. Create a SaleDTO object to transfer sale details between classes.
//3. Refactor functions of message types 1 and 2 into single method
//4. Usage of delegation pattern
//5. Decoupling of SaleRecord class


}

