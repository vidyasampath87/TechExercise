package com.sales.report;

import com.sales.report.exceptions.ProductException;

/**This class represents a product.
 * Product has the following properties:
 * product name, quantity of products and the total sale value.
 * @author Vidya
 *
 */
public class Product {
	
	//Constants
	public static final String ADD = "add";
	public static final String SUBTRACT = "subtract";
	public static final String MULTIPLY = "multiply";
	
	//Name of the product
	private String prod_name;
	//Quantity of the product sold
	private int quantity;
	//Total sale value
	private int total_sale;
	
	/**
	 * Returns the name of the product
	 * @return prod_name
	 */
	public String getProd_name() {
		return prod_name;
	}
	/**
	 * Set the name of the product
	 * @param prod_name
	 * @throws Exception 
	 */
	public void setProd_name(String prod_name) throws ProductException {
		if(prod_name == null || prod_name.isEmpty())
		{
			throw new ProductException("Product name cannot be null or empty");
		}
		this.prod_name = prod_name;
		//no_of_sales++;
	}	
	
	/**Getter method for variable quantity
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	
	/**Setter method for setting the quantity of product
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	/**Getter method for getting tht total sale of a product
	 * @return
	 */
	public int getTotal_sale() {
		return total_sale;
	}
	
	/**set the total sale of a product
	 * @param total_sale
	 */
	public void setTotal_sale(int total_sale) {
		this.total_sale = total_sale;
	}	 
	
	/**This method modifies the total sale value of a product depending on the operation to be performed.
	 * @param operation
	 * @param value
	 * @throws ProductException 
	 * @throws Exception 
	 */
	public void modifySaleVal(String operation, int value) throws ProductException
	{
		switch(operation)
		{
		case ADD: 		this.total_sale = this.total_sale  + value;
				  		break;
		case SUBTRACT: 	this.total_sale = this.total_sale  - value;
				       	break;
				       	
		case MULTIPLY:  this.total_sale = this.total_sale  * value;
						break;
						
		default: throw new ProductException("Operation "+operation.toUpperCase()+" not supported.");
		}
		//no_of_sales++;
	}
	
	/**This method modifies the quantity and value of a particular product sale .
	 * @param quantity
	 * @param saleVal
	 */
	public void modifyQuantity(int quantity, int saleVal)
	{
		this.quantity += quantity;
		this.total_sale = this.total_sale + (quantity * saleVal);
		//no_of_sales++;
	}
}
