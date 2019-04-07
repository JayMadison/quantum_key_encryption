package net.it691team1.qkdbackend.core;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.it691team1.qkdbackend.dto.SimulatorDto;
import net.it691team1.qkdbackend.dto.EqualBits;
import net.it691team1.qkdbackend.dto.MatchBits;
import net.it691team1.qkdbackend.dto.SuiteSimulatorDto;

@WebService(endpointInterface = "net.it691team1.qkdbackend.core.QKEndpoint")
public class QKEndpointImpl implements QKEndpoint {

	public QKEndpointImpl() {
		// TODO Auto-generated constructor stub
	}

	@WebMethod
	public
	char[] getInputFilters(@WebParam(name = "filterString") String filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	char[] getfilterSets(@WebParam(name = "inputFilter") char[] alicesFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	int[] getBitsVector(@WebParam(name = "inputFilter") char[] alicesFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	char[] genFilters(@WebParam(name = "filterLength") int N) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	char[] evesFilterFixup(
			@WebParam(name = "alicesFilters") char[] alicesFilters,
			@WebParam(name = "evesFilters") char[] evesFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	char[] filterMatch(@WebParam(name = "filter1") char[] alicesFilters,
			@WebParam(name = "filter2") char[] BobsFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	char[] filterSetMatch(
			@WebParam(name = "filterSet1") char[] alicesFilterSets,
			@WebParam(name = "filterSet1") char[] bobsFilterSets) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	MatchBits bitsAfterMatch(@WebParam(name = "filter1") char[] alicesFilters,
			@WebParam(name = "filter2") char[] bobsFilters,
			@WebParam(name = "filterSetMatches") char[] setMatches,
			@WebParam(name = "finalbitSlot") int[] finalBitsSlots) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	EqualBits bitsEqualTest(
			@WebParam(name = "finalBitSlots") int[] finalBitsSlots,
			@WebParam(name = "bitsVector") int[] bitsVector,
			@WebParam(name = "finalBits") int[] FinalBits) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	int createSuite(@WebParam(name = "numberOfTests") int numTests) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@WebMethod
	public
	boolean completeSuite(@WebParam(name = "suiteId") int suiteId) {
		// TODO Auto-generated method stub
		return false;
	}

	@WebMethod
	public
	int insertTest(@WebParam(name = "test") SimulatorDto test) {
		// TODO Auto-generated method stub
		return 0;
	}

	@WebMethod
	public
	SimulatorDto[] queryTestHisotry(
			@WebParam(name = "simulatorSearch") SimulatorSearch test) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public
	SuiteSimulatorDto queryTestSuite(
			@WebParam(name = "simulatorSearch") SimulatorSearch test) {
		// TODO Auto-generated method stub
		return null;
	}



}
