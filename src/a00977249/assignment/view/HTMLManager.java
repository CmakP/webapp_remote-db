/**
 * Project: COMP3613_A00977249Assignment01
 * File: HTMLManager.java
 * Date: Oct 15, 2016
 * Time: 2:17:50 PM
 */
package a00977249.assignment.view;

import a00977249.assignment.data.product.Product;

/**
 * @author Siamak Pourian
 *
 * Manages the generated result to be presented in the body section of 
 * the html/jsp page
 *  
 * HTMLManager Class
 */
public class HTMLManager {
	
	private StringBuffer outputData;
	
	public HTMLManager() {
		outputData = new StringBuffer();
	}
	
	/**
	 * Appends the attributes of the scoped variable to the current table row
	 * 
	 * @param product to be added to the current table row
	 */
	public void addRowToOutputData(Product product) {
		outputData.append("<form class='center' action='assignment01' method='post' onSubmit='return submitIt(this)'>\n");
		outputData.append("<input type='hidden' name='update' value='update'>\n");
		outputData.append("<tr>\n");
		outputData.append("<td><input type='text' name='Id' size='3' value='" + product.getId() + "' readonly></td>\n");
		outputData.append("<td><input type='text' name='Name' value='" + product.getName() + "'></td>\n");
		outputData.append("<td><input type='text' name='Price' size='12' value='" + product.getPrice() +"'></td>\n");
		outputData.append("<td><input type='text' name='Weight' size='12' value='" + product.getWeight() + "'></td>\n");
		outputData.append("<td><input type='text' name='Code' size='12' value='" + product.getCode() + "'></td>\n");
		outputData.append("<td><input type='text' name='Manufacturer' size='12' value='" + product.getManufacturer() + "'></td>\n");
		outputData.append("<td><input type='text' name='MadeIn' size='12' value='" + product.getMadeIn() + "'></td>\n");
		outputData.append("<td><input type='text' name='Description' size='40' value='" + product.getDescription() + "'></td>\n");
		outputData.append("<td><input type='submit' value='Update' class='button buttonTb'></td>\n");
		outputData.append("</form>\n");
		outputData.append("<form class='center' action='assignment01' method='post'>\n");
		outputData.append("<td>\n");
		outputData.append("<input type='hidden' name='id' value=" + product.getId() + ">\n");
		outputData.append("<input type='hidden' name='delete' value='delete'>\n");
		outputData.append("<input type='submit' value='Delete' class='button buttonTb'>\n");
		outputData.append("</td>\n");
		outputData.append("</form>\n");
		outputData.append("</tr>\n");
	}
	
	/**
	 * Closes the table tag and returns the HTML body
	 * 
	 * @return the completed buffer as HTML body
	 */
	public StringBuffer getOutputData() {
		return outputData;		  
	}
}
