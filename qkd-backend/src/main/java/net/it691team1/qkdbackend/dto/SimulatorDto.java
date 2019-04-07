package net.it691team1.qkdbackend.dto;

public class SimulatorDto {
	private int simId;
	private boolean eve;
	private boolean eveDet; 
	private int eveKnownFilters;
	private int filterSize;
	private int filterSetMatch;
	private int eveImpactedBits;
	private int bitsCorrect;
	private int suiteId;
	
	public SimulatorDto(){
		//Setup Inital Values
		simId = 0;
		eve = false;
		eveDet = false;
		eveKnownFilters = 0;
		filterSize = 0;
		filterSetMatch = 0;
		eveImpactedBits = 0;
		bitsCorrect = 0;
		suiteId = 0;
	}
	public int getSimId() {
		return simId;
	}
	public void setSimId(int simId) {
		this.simId = simId;
	}
	public boolean isEve() {
		return eve;
	}
	public void setEve(boolean eve) {
		this.eve = eve;
	}
	public boolean isEveDet() {
		return eveDet;
	}
	public void setEveDet(boolean eveDet) {
		this.eveDet = eveDet;
	}
	public int getEveKnownFilters() {
		return eveKnownFilters;
	}
	public void setEveKnownFilters(int eveKnownFilters) {
		this.eveKnownFilters = eveKnownFilters;
	}
	public int getFilterSize() {
		return filterSize;
	}
	public void setFilterSize(int filterSize) {
		this.filterSize = filterSize;
	}
	public int getFilterSetMatch() {
		return filterSetMatch;
	}
	public void setFilterSetMatch(int filterSetMatch) {
		this.filterSetMatch = filterSetMatch;
	}
	public int getEveImpactedBits() {
		return eveImpactedBits;
	}
	public void setEveImpactedBits(int eveImpactedBits) {
		this.eveImpactedBits = eveImpactedBits;
	}
	public int getBitsCorrect() {
		return bitsCorrect;
	}
	public void setBitsCorrect(int bitsCorrect) {
		this.bitsCorrect = bitsCorrect;
	}
	public int getSuiteId() {
		return suiteId;
	}
	public void setSuiteId(int suiteId) {
		this.suiteId = suiteId;
	}
	

}
