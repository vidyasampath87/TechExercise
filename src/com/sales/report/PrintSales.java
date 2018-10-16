package com.sales.report;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**This class is used to print reports related to product and sale transactions.
 * @author Vidya
 *
 */
public class PrintSales {
	
	/**Print product details such as product name, quantity and total sale value
	 * @param product_detail
	 */
	public void printProductDetail( Map<String, Product> product_detail)
	{
		System.out.println("Logging report for every 10 messages received");
		System.out.println("------------------------------------------------------------------------");			
		System.out.printf("%30s %20s %20s","PRODUCT","QUANTITY","SALEVALUE(in p)");
		System.out.println("\n------------------------------------------------------------------------");
		Iterator<Entry<String, Product>> it = product_detail.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        Product prod = product_detail.get(pair.getKey()); 
		        System.out.printf("%30s %20d %20d", prod.getProd_name().toUpperCase() , prod.getQuantity() ,prod.getTotal_sale());
		        System.out.println();
		    }
		System.out.println("-------------------------------------------------------------------------");
	}

	/**Print the adjustments done for each product
	 * @param sale_adjustments
	 */
	public void printAdjustments(List<String> sale_adjustments)
	{
		System.out.println("Maximum number of sales recieved. Pausing to print the log report\n");
		System.out.println("---------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s","ADJUSTMENTS DONE");
		System.out.println("\n---------------------------------------------------------------------------------------------------------------");

		for(String adjustMsg: sale_adjustments)
		{
			System.out.printf("%10s", adjustMsg);
			System.out.println();
		}

		System.out.println("---------------------------------------------------------------------------------------------------------------");		
	}
}
