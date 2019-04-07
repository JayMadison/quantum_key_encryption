package net.it691team1.qkdbackend.core;

/**
 * Utilities for checking input to the web services
 * @author jaythomas
 *
 */
public class QKUtils {

	public QKUtils() {
		super();
	}
	/**
	 * Checks a string to make sure the it is not empty and only has chars matching
	 * @param filters
	 * @return boolean
	 */
	public static boolean checkFilter (char[] filters){
		boolean valid = false; 
		if (filters != null && filters.length > 0){
			valid = new String(filters).matches("[\\\\|/-]*");
		}
		return valid; 
	}
	
	/**
	 * Checks a filter set to ensure it is not null and has only
	 * valid chars 
	 * @param fSet
	 * @return boolean
	 */
	public static boolean checkFilterSet(char[] fSet){
		boolean valid = false; 
		if (fSet != null && fSet.length > 0){
			valid = new String(fSet).matches("[+X]*");
		}
		return valid; 
	}
	
	/**
	 * Checks a match string for null photons or Y/N
	 * @param match
	 * @return boolean
	 */
	public static boolean checkMatch(char[] match){
		boolean valid = false; 
		if (match != null && match.length > 0){
			valid = new String(match).matches("[YN~]*");
		}
		return valid; 
	}
	
	/**
	 * Checks a bit array to make sure only valid values exist
	 * (1 or 0) and that it is not null
	 * @param bits
	 * @return boolean
	 */
	public static boolean checkBits(int[] bits){
		boolean valid = false; 
		int c = 0;
		if (bits != null && bits.length > 0){
			for(int i = 0; i < bits.length; i++){
				if (bits[i] == 1 || bits[i] == 0){
					c++;
				}
			}
		 //If all bits are valid return true 
		  if (c == bits.length){valid = true;}
		}
		return valid; 
	}

}
