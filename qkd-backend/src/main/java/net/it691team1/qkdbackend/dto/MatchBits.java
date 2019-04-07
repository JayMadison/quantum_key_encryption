package net.it691team1.qkdbackend.dto;
/**
 * DTO to move arround results of the match methods in the legacy QKD code. 
 * @author jaythomas
 *
 */
public class MatchBits {
	/**
	 * Array of bits remaining after the match logic is complete
	 */
	private int [] bitsAfterMatch;
	/**
	 * All bits (the superset from which bitsaftermatch is drawn) from the input
	 */
	private int [] finalBits;
	/**
	 * Slots (array index or vector) of the bits
	 */
	private int [] finalBitsSlots;
	/**
	 * Number of matches
	 */
	private int K;
	/**
	 * Generic Constructor
	 */
	public MatchBits() {
		super();
	}
	/**
	 * Getter 
	 * @return bitsaftermatch
	 */
	public int[] getBitsAfterMatch() {
		return bitsAfterMatch;
	}
	/**
	 * Setter
	 * @param bitsAfterMatch
	 */
	public void setBitsAfterMatch(int[] bitsAfterMatch) {
		this.bitsAfterMatch = bitsAfterMatch;
	}
	/**
	 * Getter
	 * @return FinalBits
	 */
	public int[] getFinalBits() {
		return finalBits;
	}
	/**
	 * Setter
	 * @param finalBits
	 */
	public void setFinalBits(int[] finalBits) {
		this.finalBits = finalBits;
	}
	/**
	 * Getter
	 * @return FinalBitSlots
	 */
	public int[] getFinalBitsSlots() {
		return finalBitsSlots;
	}
	/**
	 * Setter
	 * @param finalBitsSlots
	 */
	public void setFinalBitsSlots(int[] finalBitsSlots) {
		this.finalBitsSlots = finalBitsSlots;
	}
	/**
	 * Setter
	 * @param K
	 */
	public void setK(int i){
		K = i;
	}
	/**
	 * Getter
	 * @return K
	 */
	public int getK(){
		return K;
	}
}