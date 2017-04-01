/**
 * Project: COMP3613_A00977249Assignment01
 * File: DataManager.java
 * Date: Oct 14, 2016
 * Time: 9:45:29 PM
 */
package a00977249.assignment.data;

import java.util.ArrayList;

import a00977249.assignment.data.product.Product;

/**
 * @author Siamak Pourian
 *
 * Stores an arraylist of the records in the remote table
 *
 * DataManager Class
 */
public class DataManager {
	
	private ArrayList<Product> productList;
	
	/**
	 * Default constructor
	 */
	public DataManager() {
		productList = new ArrayList<Product>();
	}
	
	/**
	 * Adds a product to the list
	 * 
	 * @param product to be added to the list
	 */
	public void addProduct(Product product) {
		productList.add(product);
	}

	/**
	 * @return the productList
	 */
	public ArrayList<Product> getProductList() {
		return productList;
	}
}
