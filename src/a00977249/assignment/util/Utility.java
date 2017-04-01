/**
 * Project: COMP3613_A00977249Assignment01
 * File: Validator.java
 * Date: Oct 14, 2016
 * Time: 1:23:04 PM
 */
package a00977249.assignment.util;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * @author Siamak Pourian
 *
 * Validates the user input
 * 
 * Validator Class
 */
public class Utility {
	
	/**
	 * Checks the validation of user input string against being null or empty
	 * 
	 * @param input the user input to be validated
	 */
    public static boolean validateInput(String input) {
    	return (input == null || input.trim().length() == 0) ? false : true;
    }
    
    /**
	 * 
	 * Rounds and formats the number to two decimal places
	 * 
	 * @param number to be rounded up and converted to two decimal format
	 * @return number with two decimal places
	 */
    public static double twoDecimals(double number) {
    	return Double.valueOf(new DecimalFormat("#.##").format(Math.round(number * 100.0) / 100.0));
    }
    
    /**
	 * Validates the input based on the desired pattern.
	 * 
	 * @param input to be validated
	 * @param pattern to be used as a regex
	 * @return true if the input matches the regex and false otherwise
	 */
	public static boolean checkPattern(final String input, final String pattern) {
		return Pattern.compile(pattern).matcher(input).matches();
	}
	
	/**
	 * Checks if the number is positive
	 * 
	 * @param number to be checked
	 * @return true if positive and false otherwise
	 */
	public static boolean isPositive(double number) {
		return (int) number > 0;
	}
}
