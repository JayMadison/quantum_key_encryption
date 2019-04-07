package core;

import net.it691team1.qkdbackend.core.QuantumKeyDistributionSimulationMethods;
import net.it691team1.qkdbackend.dto.EqualBits;
import net.it691team1.qkdbackend.dto.FilterFixupDto;
import net.it691team1.qkdbackend.dto.MatchBits;
import junit.framework.TestCase;

public class QuantumKeyDistributionSimulationMethodsTest extends TestCase {

	public void testGetInputFiltersVector() {
		int c = 0; 
		//Length will be 6 due to escapeing the backslash
		char [] a = QuantumKeyDistributionSimulationMethods.getInputFiltersVector("//||-\\");
		//Check input is with
		for(int i=0;i < a.length;i++){
			if (Character.toString(a[i]).matches("[\\\\|/-]*")){
				c++;	
			}
		}
		assertEquals(a.length, 6);
		assertEquals(a[0] , '/');
		assertEquals(c,6);
		
	}

	public void testGetBitsVector() {
		char [] a = {'|','-','/','\\'};
		int[] r = QuantumKeyDistributionSimulationMethods.getBitsVector(a);
		assertEquals(r[0],1);
		assertEquals(r[1],0);
		assertEquals(r[2],0);
		assertEquals(r[3],1);
	}

	public void testGenBobsFilters() {
		int count = 0;
		char [] b = QuantumKeyDistributionSimulationMethods.genBobsFilters(20);
		//Verify only acceptable chars are in the array
		for(int i=0;i < b.length;i++){
			if (Character.toString(b[i]).matches("[\\\\|/-]*")){
				count++;	
			}
		}
		//verify that all chars are correct and the length is the same as the input pram
		assertEquals(b.length,20);
		assertEquals(count, 20);
		
	}

	public void testGetFilterSets() {
		char [] a = {'|','-','/','\\'};
		char [] fs = QuantumKeyDistributionSimulationMethods.getFilterSets(a);
		assertEquals(fs[0],'+');
		assertEquals(fs[1],'+');
		assertEquals(fs[2],'X');
		assertEquals(fs[3],'X');
		
	}

	public void testEvesFilterFixup() {
		//Testing the only known value, all others are random
		char [] a = {'|','-','/','\\'};
		char [] e = {'-','|','\\','/'};
		FilterFixupDto fix = QuantumKeyDistributionSimulationMethods.evesFilterFixup(a, e);
		char [] res = fix.getModFilters();
		assertEquals(e[0],res[0]);
		assertEquals(e[1],res[1]);
		assertEquals(e[2],res[2]);
		assertEquals(e[3],res[3]);
		assertEquals(fix.getTotal(), 4);
	}

	public void testFilterMatch() {
		// Setup two differnt filters to 
		int count = 0;
	    char [] a = {'|','-','/','\\','|','-','/','\\','|','-','/','\\'};
		char [] b = {'-','|','\\','/','|','-','/','\\','/','\\','|','-'};
		char [] res = QuantumKeyDistributionSimulationMethods.filterMatch(a, b);
		//If the filters are from the same set but opposite then the photon is null
		for (int i=0;i < 4;i++){
			if (res[i] == '~'){count++;}
		}
		//If the filters are the same they are a match
		for (int i=4;i < 8;i++){
			if (res[i] == 'Y'){count++;}
		}
		//If the filter is from the other filter set it's not a match
		for (int i=8;i < 12;i++){
			if (res[i] == 'N'){count++;}
		}

		assertEquals(count,12);
	}

	public void testFilterSetMatch() {
		// Setup two differnt filters to 
	    char [] a = {'+','+','X','X'};
		char [] b = {'+','X','+','X'};
		char [] res = QuantumKeyDistributionSimulationMethods.filterSetMatch(a, b);
		
		assertEquals(res[0],'Y');
		assertEquals(res[3],'Y');
		assertEquals(res[1],'N');
		assertEquals(res[2],'N');
		

	}

	public void testBitsAfterMatch() {
		// Setup two differnt filters to 
		char [] fsm = {'Y','N','N','Y'};
		// setup filters
		char[] bf = {'|','-','/','/'};
		char[] af = {'|','/','|','/'};
		int[] slts = new int[4];
		//Original bit slots from alice 
		MatchBits m = QuantumKeyDistributionSimulationMethods.bitsAfterMatch(af, bf, fsm, slts);
		//Test the Match Bits object
		assertEquals(m.getK(),2);
		//verify the bits after makes info
		assertEquals(m.getBitsAfterMatch().length,2);
		assertEquals(m.getBitsAfterMatch()[0],1);
		assertEquals(m.getBitsAfterMatch()[1],0);
		//Check the Final Bits slots
		assertEquals(m.getFinalBitsSlots()[0],0);
		assertEquals(m.getFinalBitsSlots()[1],3);
		//CHeck the Final Bits Array
		assertEquals(m.getFinalBits()[0],1);
		assertEquals(m.getFinalBits()[1],0);
	}

	public void testBitsEqualTest() {
		// Alice's original bit vector
		int [] ab = {1,0,1,0};
		// final bits 
		int[] finbits = new int[]{1,0}; 
		//Slots of the original bits
		int [] aslt = {0,3};
		EqualBits res = QuantumKeyDistributionSimulationMethods.bitsEqualTest(aslt, ab, finbits);
		//Check the values in the bitsEqual object
		assertEquals(res.getK(),2);
		assertEquals(res.getTotal(),2);
		assertEquals(res.getBitsEqual().length,2);
		assertEquals(res.getBitsEqual()[0],'Y');
		assertEquals(res.getBitsEqual()[1],'Y');
		//change Values of ab to indicate a problem
		ab[3] = 1;
		res = QuantumKeyDistributionSimulationMethods.bitsEqualTest(aslt, ab, finbits);
		//Verify Object again
		assertEquals(res.getK(),2);
		assertEquals(res.getTotal(),1);
		assertEquals(res.getBitsEqual().length,2);
		assertEquals(res.getBitsEqual()[0],'Y');
		assertEquals(res.getBitsEqual()[1],'N');
		
	}

}
