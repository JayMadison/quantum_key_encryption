package net.it691team1.qkdbackend.core;

import net.it691team1.qkdbackend.dao.SimulatorDao;
import net.it691team1.qkdbackend.dao.impl.SimulatorDaoImpl;
import net.it691team1.qkdbackend.dto.*;

import java.io.IOException;
import java.io.PrintWriter;
import net.it691team1.qkdbackend.core.QuantumKeyDistributionSimulationMethods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/QKServer")

/**
 * Phase 1 servlet to move the existing legacy code onto a simple http post servlet
 * Phase 2 will replace with with a soap (java-wx) servlet that will interface with a 
 * broker service which will interface with the front end.
 * @author jaythomas
 *
 */
public class QKServer extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	  /**
	   * Defualt contructor 
	   */
	  public QKServer() {
	    super();
	  }
	  /**
	   * Standard http post method to hand the code that was in the 
	   * main class of the console application. 
	   */
	  protected void doPost(HttpServletRequest request, HttpServletResponse   
			    response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			SimulatorDto sim = new SimulatorDto();
			int eveInt=0;
			int key = 0; 
			int N = 0;
			char[] alicesFilters = null;
			String filter = request.getParameter("filter");
			String eve = request.getParameter("eve");
			String len = request.getParameter("keyLength");

			try{
				if (len != null && !len.isEmpty()){
					key = Integer.parseInt(len);					
				}
			} catch (NumberFormatException e) {
			}
			if (key > 0) {
				alicesFilters = net.it691team1.qkdbackend.core.QuantumKeyDistributionSimulationMethods.genBobsFilters(key);
			} else if (filter != null) {
				//remove any whitespace if filter exists
				filter.trim();
				if (!filter.isEmpty() && !filter.matches("[\\\\|/-]*")){
					out.println("An Invalid Filter was used");
					return;
				} else if (filter.isEmpty()) {
					eveInt = 0; 
				}
			} else {
				out.println("An Invalid Filter length was used. Filter length must be greater than zero and less then 2147483647");
				out.println(" TERMINATING RUN");
				out.println("===================================");
				return;
			}
			
            if (eve == null || eve.isEmpty()) {
				eveInt = 1; 
			} else if (!eve.isEmpty()){
				eveInt = 2;
				eve.trim();
				sim.setEve(true);
			}

			//Print filter after it's been checked for a null
			switch(eveInt)
			{
				case 0 : 
					out.println ("\nError.  No input filter");
					out.println(" TERMINATING RUN");
					out.println("===================================");
					return;
				
				case 1 :
					out.println ("\nThere is NO EVE in this run.");
					out.println("==== filters only =====");
				break;
				
				case 2 : 
					out.print  ("\nThere is EVE in this run named ");
					out.println("( " + eve + " ).");
				    out.println("==== filters & Eves Name ====");
				break;
				default:  
				    out.print  (" INPUT WRONG");
					out.print  (" There are more than 2 inputs");
					out.println(" Input parms are a String of filters");
					out.print  (" '|', '-', '\', '/'.");
					out.println(" ,and optionally a String name of Eve");
	        		out.print  (" TERMINATING RUN in main.");
	    			out.println("===================================");
	    		    return;
			}
			
			
			// 	Find N & print it out.  It is the length of the filer 
			// 	Set up Eve's arrays (Bob's & Alice's get set by methods that get
			// 	N passed implicitly to them as the length of the filter String).
			    if (filter != null){
				N	= 	filter.length();
			    } else {
			    N = alicesFilters.length;	
			    }
			
				out.println ("\nN = The number of filters chosen = " + N);
				
				char[] evesFilters 		= new char[N];
				char[] evesFilterSets 	= new char[N];	
				
				// 	Arbitrarily choose the correspondence between filter and bit 
				//	meaning.
					
					out.println ("\nFilter SETs & bit meaning = ");
					out.println ("\t{+} SET: (|) = 1, (-) = 0");
					out.println ("\t{X} SET: (\\) = 1, (/) = 0");
					
			    // 	Read in Alices filter choices
				//	These are also the photons she sends.
					
					out.println("\n============== ALICE ==============");
					out.print  ("\nAlice's Filters == Alice's Photons.\n\n");
					if (alicesFilters == null){
					alicesFilters = QuantumKeyDistributionSimulationMethods.
														getInputFiltersVector(filter);
					}
					printCharVector(N, "FilterVector",alicesFilters, out);
					sim.setFilterSize(alicesFilters.length);
				// Convert Alices filter choices to filter SET choices 
				// That she sends to Bob.
					
					out.print  ("\n\nAlice's Filter SETS sent to Bob.\n\n");
					char[] alicesFilterSets = QuantumKeyDistributionSimulationMethods.
														getFilterSets(alicesFilters);
					printCharVector(N, "alicesFilterSets",alicesFilterSets,out);
					
				// Convert Alices filter choices to bits (she started with
			    // bits for a message and converted to filters!! 
			    // Bob does not yet know these bits OR these filters.
					
					out.print  ("\nAlice's Bits.\n");				
					int[]  alicesBits 	= QuantumKeyDistributionSimulationMethods.
											getBitsVector(alicesFilters);

					printIntVector(N, "bitsVector",alicesBits, out);
					
				// Randomly choose (generate) Bob's filters	from the set of 4 possible
					out.println("\n============== BOB ==============");
					out.print  ("\n\nBobs RANDOM Filter Choices.\n\n");
					char[] bobsFilters  = QuantumKeyDistributionSimulationMethods.
											genBobsFilters(N);
					printCharVector(N, "Filters",bobsFilters, out);
					
				// Convert Bob's filter choices to filter SET choices 
					
					out.print  ("\nBob's Filter SETS.\n\n");
					char[] bobsFilterSets = QuantumKeyDistributionSimulationMethods.
											getFilterSets(bobsFilters);
					printCharVector(N, "bobsFilterSets",bobsFilterSets,out);									
					if (eveInt == 2)
					{
						// Randomly choose (generate) Eve's filters	
						// from the set of 4 possible.   Same process as for Bob.
						
						out.println("\n============== EVE ==============");
						out.print  ("\n\nEve's RANDOM Filter Choices.\n\n");
						evesFilters  = QuantumKeyDistributionSimulationMethods.
																		genBobsFilters(N);
						printCharVector(N, "Filters",evesFilters, out);
										
						// Convert Eve's filter choices to filter SET choices 
					
						out.print  ("\nEve's Filter SETS.\n\n");
						evesFilterSets = QuantumKeyDistributionSimulationMethods.
														getFilterSets(evesFilters);
						printCharVector(N, "evesFilterSets", evesFilterSets,out);
						// Due to ignorance, Eve can only forward on to Bob a
						// partially random choice of polarized photons (filters)
						// as if they were coming from Alice.  In effect she is
						// randomly redefining the set sent by Alice.
						
						out.print  ("\nEve sends out Alice's (MODIFIED) Photons to BOB.\n");
						FilterFixupDto fix = QuantumKeyDistributionSimulationMethods.
											evesFilterFixup(alicesFilters, evesFilters);
											
						alicesFilters = fix.getModFilters();
						sim.setEveKnownFilters(fix.getTotal());
						out.print  ("Eve Measures Alice's Photons (filters).\n");
						out.print  ("If she gets a gift (NO PHOTON) she passes on\n");
						out.print  ("the known photon without a random choice.\n");
						
						out.print("\nEve REPLACES missing photons with");
						out.print("\nknown photons and passes them on to Bob");
						out.print("\nas if they were coming from Alice.\n");
						out.print("\nThe best she can do if she gets a ");
						out.print("\nfiltered photon is to pass on a random");
						out.print("\nchoice of either that filter she chose ");
						out.print("\nor one of the two complimentary pair.\n\n");
						printCharVector(N, "Eves Fixed Up Output Filters Go To Bob",alicesFilters, out);
						
						
					} // end Eve's meddling
					out.println("\n=========== BOB From ALICE/EVE ===========");		
					out.print  ("\n\nAlice calls Bob With Filter SETS.");
					out.print  ("\nBob Says Which Filter SETS Match.");
					out.print  ("\nThis implies filter (& bit).\n\n");
					
					out.print  ("\nThese are Bob's filter sets matching Alice's.\n\n");
					
					char [] setMatches = QuantumKeyDistributionSimulationMethods.
							 filterSetMatch(alicesFilterSets, bobsFilterSets);
					
					out.print("\n Y == Filter SET Match, N == No Filter SET Match");
					out.print("\n");
					printCharVector(N, "filterSetsMatch", setMatches,out);
					
					int[] finalBitsSlots = new int[N]; 
					int fsMatch = 0; 
				//Increment up matches
					for(int i = 0; i < setMatches.length; i++){
						if(setMatches[i] == 'Y'){fsMatch++;}
					}
					sim.setFilterSetMatch(fsMatch);
					// The original position of the finalBits matched sent by Alice 

					out.print  ("\nFilter Matches Imply Bits That Match.\n");
					MatchBits  finalBits = QuantumKeyDistributionSimulationMethods.bitsAfterMatch 
															(alicesFilters, 
															 bobsFilters, 
															 setMatches,
															 finalBitsSlots);
					
					out.print("\n Final Number Of Matched Bits = " + finalBits.getBitsAfterMatch().length + ".\n");
					out.print("\n Same routine is used by both Bob & Eve");
					out.print("\n only the inputs are different.\n");

					printIntVector(finalBits.getBitsAfterMatch().length, "Final Bits", finalBits.getBitsAfterMatch(),out);
					
					out.print("\n Where the final bits came from in ");
					out.print("\n Alice's input or Bob's/Eve's ");
					out.print("\n filter vector index.\n");

					printIntVector(finalBits.getBitsAfterMatch().length, "finalBitsSlots", finalBits.getFinalBitsSlots(),out);
					out.print  ("\nThese bit matches are Bob/Alice.\n\n");
					EqualBits finalBitsEqual = QuantumKeyDistributionSimulationMethods.
							bitsEqualTest(finalBitsSlots, alicesBits, finalBits.getBitsAfterMatch());
					int eveImp = 0;
					for (int i=0;i< finalBitsEqual.getBitsEqual().length;i++){
						if ( finalBitsEqual.getBitsEqual()[i] == 'N'){
							out.println("This bit " + "[" + i + "] " +  "not equal.");
							out.println("EVE DETECTED!");
							sim.setEveDet(true);
							eveImp++;
						}
					}
					sim.setBitsCorrect( finalBitsEqual.getTotal());
					sim.setEveImpactedBits(eveImp);
					out.println ("\n");
					out.println ("The number of equal bits is: " + finalBitsEqual.getTotal() + "\n");
					printCharVector(finalBitsEqual.getBitsEqual().length, "finalBitsEqual", finalBitsEqual.getBitsEqual(),out);
					out.print   ("\n If Eve, all final bits are PROBABLY not equal.");
					out.print   ("\n That's one reason why we use long bitstrings -");
					out.print   ("\n to reduce the probability close to 0.\n\n");
					out.print   (" If NO Eve, all final bits MUST be equal\n");
					out.print   (" by construction.\n\n");
					
			
			// Eve's Code
					if (eveInt == 2)
					{
						out.println("\n=========== EVE From ALICE ===========");
						out.print ("\n\nEve listens in about Filter SETS.");
						out.print ("\nEve looks for filter SETS Match.");
					    out.print ("\nThis implies filter (& bit).\n");
									
						out.print ("\nThese are Eve's filter sets matching Alice's.\n");
						out.print ("\nEVE KNOWS THESE.\n\n");
						
						char [] evesSetMatches = QuantumKeyDistributionSimulationMethods.
								filterSetMatch(alicesFilterSets, evesFilterSets);
						out.print("\n Y == Filter SET Match, N == No Filter SET Match");
						out.print("\n");
						printCharVector(N, "filterSetsMatch", evesSetMatches,out);
						int[] evesFinalBitsSlots = new int[N]; 
						out.print("\nFilter Matches Give Eve's Maybe Matched Bits.\n\n");
						out.print("\nEVE DOES NOT KNOW ANY OF THIS.\n\n");
						MatchBits evesFinalBits = QuantumKeyDistributionSimulationMethods.
								bitsAfterMatch (alicesFilters, 
												evesFilters,  
												setMatches,
												finalBitsSlots);
						
						out.print("\n Final Number Of Matched Bits = " + evesFinalBits.getBitsAfterMatch().length + ".\n");
						out.print("\n Same routine is used by both Bob & Eve");
						out.print("\n only the inputs are different.\n");
						
						printIntVector(evesFinalBits.getBitsAfterMatch().length, "Final Bits", evesFinalBits.getBitsAfterMatch(),out);
					    //out.print(finalBits.getBitsAfterMatch().length);
						out.print  ("\nThese bit matches are Eve/Alice.\n");
						out.print  ("\nThese are only a random guess.\n\n");
						
						EqualBits evesfinalBitsEqual = QuantumKeyDistributionSimulationMethods.
								bitsEqualTest (evesFinalBitsSlots, 
										alicesBits,
										evesFinalBits.getBitsAfterMatch());
						
						for (int i = 0; i < evesfinalBitsEqual.getBitsEqual().length;i++){
							if (evesfinalBitsEqual.getBitsEqual()[i] == 'N'){
								out.println("This bit " + "[" + i + "] " +  "not equal.");
							}
						}
							
							out.println ("\n");
							out.println ("The number of equal bits is: " + evesfinalBitsEqual.getTotal() + "\n");
							printCharVector(evesfinalBitsEqual.getK(), "finalBitsEqual", evesfinalBitsEqual.getBitsEqual(), out);
							out.print   ("\n If Eve, all final bits are PROBABLY not equal.");
							out.print   ("\n That's one reason why we use long bitstrings -");
							out.print   ("\n to reduce the probability close to 0.\n\n");
							
								
						}//End Eve's actions
					// Now we need to create the DTO object and pass it to 
					//finally close out the printWriter
					out.println("\n");
					out.println ("Here are the Values from the DTO:\n\n");
					out.println ("Eve: "+ sim.isEve());
					out.println ("Eve Detected: "+ sim.isEveDet());
					out.println ("Eve's Known Correct Filters: " + sim.getEveKnownFilters());
					out.println ("Eve's Impacted Bits: "+ sim.getEveImpactedBits());
					out.println ("Filter Size: " + sim.getFilterSize());
					out.println ("Filter Set Match: " + sim.getFilterSetMatch());
					out.println ("Eve's Impacted Bits: " + sim.getEveImpactedBits());
					out.println ("Final Correct Bits: " + sim.getBitsCorrect());
					
					SimulatorDao db = new SimulatorDaoImpl();
					int simNumber = 0;
					out.println("Adding the test to the Database");
					try{
					simNumber = db.insertSim(sim, out);
					} catch (Exception e){
						out.println("Unable to Insert the Test");
					}
					if (simNumber > 0){
						out.println("The Simultation Number is : "+simNumber);
					} else {
						out.println("Unable to Insert the Test");
					}
					out.println("Test Complete");
					out.close();
					}
			//Finally Close the output stream
	

//////////////////////////////Print CharVector ///////////////////////////
/**
 * Prints the array representing a char vector for output purposes
 * The method was in the legacy methods class, but was moved here as it will
 * eventually live in the frontend.
 * @param N
 * @param name
 * @param Vector
 * @param out
 */
public static void printCharVector(int N, String name, char [] Vector, PrintWriter out)
{
out.println("Vector Name = " 		+ name + ": " );
if (N == 0){
	return;
}
for(int j = 0; j <= N-1; j++)
{
out.print("[j= " + j + "]" );
out.println(" = " + Vector[j] );
} // end for j
} // end output of printCharVector

//////////////////////////////end Print CharVector ///////////////////////

//////////////////////////////Print IntVector ////////////////////////////
/**
 * Prints the int arrays represending vectors
 * The method was in the legacy methods class, but was moved here as it will
 * eventually live in the frontend.
 * @param N
 * @param name
 * @param Vector
 * @param out
 */
public static void printIntVector(int N, String name, int[] Vector, PrintWriter out)
{
out.println("\n Vector Name = " 		+ name + ": " );
if (N == 0){
	return;
}
for(int j = 0; j <= N-1; j++)
{
out.print("[j= " + j + "]" );
out.println(" = " + Vector[j] );
} // end for j
} // end output of printIntVector

//////////////////////////////end Print IntVector ////////////////////////
}

