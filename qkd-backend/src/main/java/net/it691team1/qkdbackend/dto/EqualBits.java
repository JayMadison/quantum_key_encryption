package net.it691team1.qkdbackend.dto;

/**
 * 
 * @author jaythomas
 * This class holds the results of the comparisons of quality by the QKD simulator
 * and is essentially a DTO.
 *
 */
public class EqualBits {
	/**
	 * k is the total number of items in the filter set
	 */
	private int K;
	/**
	 * Total is the total number of matching array items
	 */
	private int total; 
	/**
	 * This array holds the results of the equality test (Y/N)
	 */
	private char [] bitsEqual;
	/**
	 * Generic Constructor 
	 */
	public EqualBits(){
		super();
	}
	/**
	 * Returns the current value of K
	 * @return K
	 */
	public int getK() {
		return K;
	}
	/**
	 * Sets K
	 * @param k
	 */
	public void setK(int k) {
		K = k;
	}
	/**
	 * Returns the total
	 * @return total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * Sets the total
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * Returns Bits Equal
	 * @return BitsEqual
	 */
	public char[] getBitsEqual() {
		return bitsEqual;
	}
	/**
	 * Sets bitsequal
	 * @param bitsEqual
	 */
	public void setBitsEqual(char[] bitsEqual) {
		this.bitsEqual = bitsEqual;
	}
}
