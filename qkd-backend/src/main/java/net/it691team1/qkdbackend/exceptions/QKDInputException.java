package net.it691team1.qkdbackend.exceptions;

/**
 * Input Exception to handle bad input from the user other than the filter string
 * which is handled by it's own class. 
 * @author jaythomas
 *
 */
public class QKDInputException extends Exception{
	/**
	 * Custom error class to pass the old output messages from 
	 * the Methods legacy class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Error message String
	 */
	String error;
	/**
	 * DefaultConstructor, will need to be extened
	 */
	public QKDInputException(){
		super();
		error = "A input error occured";
		
	}
	/**
	 * Creates a new input exception with a string 
	 * @param s
	 */
	public QKDInputException(String s){
		this.error = s;
	}
	/**
	 * Returns the error message
	 * @return error
	 */
	public String getError(){
	    return error;
	}
	/**
	 * Sets the error message
	 * @param s
	 */
	public void setError(String s){
		this.error = s;
	}
}
