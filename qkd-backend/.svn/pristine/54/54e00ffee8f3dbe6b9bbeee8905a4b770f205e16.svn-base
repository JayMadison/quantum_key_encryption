package net.it691team1.qkdbackend.core;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import net.it691team1.qkdbackend.dto.EqualBits;
import net.it691team1.qkdbackend.dto.MatchBits;
import net.it691team1.qkdbackend.dto.SimulatorDto;
import net.it691team1.qkdbackend.dto.SuiteSimulatorDto;
 
/**
 * Interface for the soap methods for the backend. 
 * @author Jay
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface QKEndpoint{
	/**
	 * Converts a sting into an array
	 * @param filters
	 * @return char [] filters
	 */
	@WebMethod char [] getInputFilters(@WebParam (name="filterString") String filters);
	
	/**
	 * Gets filter sets based on an input array (filter vector)
	 * @param alicesFilters
	 * @return char [] ilterSet
	 */
	@WebMethod char[] getfilterSets (@WebParam (name="inputFilter") char [] alicesFilters);
	
	/**
	 * Gets a filter set vector from a filter vector
	 * @param alicesFilters
	 * @return int [] bitsVector
	 */
	@WebMethod int [] getBitsVector (@WebParam (name="inputFilter") char[] alicesFilters);
	
	/**
	 * Generates a random filter set of length N
	 * @param N
	 * @return char [] filterSet
	 */
	@WebMethod char [] genFilters(@WebParam (name="filterLength") int N);
	
	/**
	 * Generates the set of filters eve would send to Bob, only null bits are known
	 * the rest are random.
	 * @param alicesFilters
	 * @param evesFilters
	 * @return char [] filters
	 */
	@WebMethod char [] evesFilterFixup(@WebParam (name="alicesFilters") char[] alicesFilters, 
			@WebParam (name="evesFilters") char[] evesFilters);
	
	/**
	 * Checks two filter sets to see if they match returning a Y/N flag
	 * @param alicesFilters
	 * @param BobsFilters
	 * @return char [] filterMatch
	 */
	@WebMethod char [] filterMatch(@WebParam (name="filter1" ) char alicesFilters[],
									@WebParam (name="filter2" ) char BobsFilters[]);
	
	/**
	 * Checks to see if the correct Filter set was used
	 * @param alicesFilterSets
	 * @param bobsFilterSets
	 * @return char [] filterSets
	 */
	@WebMethod char [] filterSetMatch(@WebParam (name="filterSet1" ) char [] alicesFilterSets, 
			 						  @WebParam (name="filterSet1" ) char [] bobsFilterSets);
	
	/**
	 * Checks for matched bits given two fitler sets, a match set and the slot locations
	 * of the bits
	 * @param alicesFilters
	 * @param bobsFilters
	 * @param setMatches
	 * @param finalBitsSlots
	 * @return MatchBits matchedBits
	 */
	@WebMethod MatchBits bitsAfterMatch (@WebParam  (name="filter1" ) char [] alicesFilters, @WebParam  (name="filter2" ) char [] bobsFilters,
										@WebParam  (name="filterSetMatches" ) char [] setMatches, @WebParam  (name="finalbitSlot" ) int[] finalBitsSlots);
	
	/**
	 * Checks for equal bits vs expected equal bits
	 * @param finalBitsSlots
	 * @param bitsVector
	 * @param FinalBits
	 * @return EqualBits equalBits
	 */
	@WebMethod EqualBits bitsEqualTest  (@WebParam  (name="finalBitSlots") int  [] finalBitsSlots,@WebParam  (name="bitsVector") int  [] bitsVector, 
										@WebParam  (name="finalBits") int  [] FinalBits);
	
	/**
	 * Create a new test Suite
	 * @param numTests
	 * @return suite_id
	 */
	@WebMethod int createSuite (@WebParam  (name="numberOfTests") int numTests);
	
	/**
	 * Updates the completion time when the test pack is complete
	 * @param suiteId
	 * @return boolean success 
	 */
	@WebMethod boolean completeSuite (@WebParam  (name="suiteId") int suiteId);
	
	/**
	 * Insets a new test given a SimulatorDto Object
	 * @param test
	 * @return int testId
	 */
	@WebMethod int insertTest (@WebParam  (name="test") SimulatorDto test);
	
	/**
	 * Returns a result set given a Dto object
	 * @param test
	 * @return ResultSet
	 */
	@WebMethod SimulatorDto[] queryTestHisotry (@WebParam  (name="simulatorSearch") SimulatorSearch test);
	
	/**
	 * Returns a result set given a Dto object
	 * @param test
	 * @return TestSuite
	 */
	@WebMethod SuiteSimulatorDto queryTestSuite (@WebParam (name="simulatorSearch") SimulatorSearch test);
	
	
}
