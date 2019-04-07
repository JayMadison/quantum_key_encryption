// (C) Ronald I. Frank 2004
// Quantum Key Distribution Algoithm Simulation As Java Code
// Compiled in SDK 1.4.2 08/14/04
// V9. Not refactored or fully "objectified"
// 
// ********** Quantum Key Distribution Simulation **********
// *********************** main() **************************

// Version 8 (With an Eve & Using Detailed Measurement info)
//
// ********************************
/*
    * Futures:
    * 1) GUI contolled input	
	*    A) Option for auto random generate Alice's filters (bits)
	*	 B) Option for supressing intermediate output (see 2-A)	 
	* 2) GUI controlled output
	*	 A) Consolidated output - all vectors together with only headers
	* 3) Separate functions for Bob ans Eve and better headings for results
	* 4) Meta routine that can run many runs and count number of equal bits
*/ 
// ********************************
// Input is a number (of filters) and a string of filter choices
// 		therefore bit choices represented as:
// 		| for 1, - for 0
// 		\ for 1, \ for 0
//
// Example: -\//|\|--/|\ has length 12 
// 		standing for x8E3 == 010011100011
//
// Before  Alice's call to Bob
// String [] arg[0]			Input FILTER (PHOTON) designations
// 			 arg[1]			If non zero length - there is an Eve.
// int  N  					the number of input filters, 
//							and therefore bits
//							and photons too.
// char[N] alicesFilters	same vector of N filters input
// char[N] alicesFilterSets vector of N corresponding filter SET disigantions
// int [N] alicesBits		Vector of N bits Alice intends (sends)
// char[N] bobsFilters		vector of N filters chosen by Bob
// char[N] bobsFilterSets	vector of N corresponding filter SET disigantions
// char[N] evesFilters		vector of N filters chosen by optional Eve
// char[N] evesFilterSets	vector of N corresponding filter SET disigantions
// char[N] alicesFilters	Again! This time reset by Eve
//
// After Alice's call to Bob about filters
// char[N] setMatches		Which K of N filters match? Y, N, ~
// int [K] finalBits		What are the K of N bits for the matches?
//							Vector is length K in main().
// int [N] finalBitsSlots	What are the K=J of N indexes for the matches?
//							Vector is length N.

// After Alice's call to Bob about a subset of actual bits sent
// char[N] finalBitsEqual 	What are the M equal subset bits ? Filled with -3.
//
// ********** Simulation  Driver **********

/////////////////////////////////// Class ////////////////////////////////// 
///////////////////////////////// & main() ///////////////////////////////// 

import java.util.*; // For Arrays.asList(args).size();

////////////////////////////////// class ///////////////////////////////////


public class QuantumKeyDistributionSimulation	
{
	
	// args[0]	= 	The N filter choices;
	// 				a filter choice is one of '|', '-', '\', '/' ; 
	//				the bit interpretation  of the filter choices is:
	// 				'|' = 1, '-' = 0, '\' = 1, '/' = 0 ;
	// args[0]  =   If there is a second argument in the command line,
	//				then there is an Eve.  The argument is Eve.s name.
	//
	// N 		= 	args[0].length(); The number of filters being sent in.
	
/////////////////////////////////////////////////////////////////////////

	public static void main (String[] args)
	{
/////////////////////////////////////////////////////////////////////////
		int Eve = Arrays.asList(args).size();	// 1 = filters only
											  	// 2 = filters + Eve's name
	
		// Check that the input array of strings has only 1 or 2 input Strings
		switch(Eve)
		{
			case 0 : // will never happen, Java catches no args case
				System.out.println ("\nError.  No input arguments");
				System.out.println(" TERMINATING RUN");
				System.out.println(" in main()");
				System.out.println("===================================");
			break;
			
			case 1 :
				System.out.println ("\nThere is NO EVE in this run.");
				System.out.println("==== filters only =====");
			break;
			
			case 2 : 
				System.out.print  ("\nThere is EVE in this run named ");
				System.out.println("( " + args[1] + " ).");
				System.out.println("==== filters & Eves Name ====");
			break;
			default:  
				System.out.print  (" INPUT WRONG");
				System.out.print  (" There are more than 2 inputs");
				System.out.println(" Input parms are a String of filters");
				System.out.print  (" '|', '-', '\', '/'.");
				System.out.println(" ,and optionally a String name of Eve");
           		System.out.print  (" TERMINATING RUN in main.");
       			System.out.println("===================================");
				System.exit(0);
		} // end check input array of strings has only 1 or 2 input Strings
		
/////////////////////////////////////////////////////////////////////////	
		// 	Find N & print it out.  It is the length of the first input arg.
		// 	Set up Eve's arrays (Bob's & Alice's get set by methods that get
		// 	N passed implicitly to them as the length of the filter String).
				
			int	N	= 	args[0].length();
		
			System.out.println ("\nN = The number of filters chosen = " + N);
			
			char[] evesFilters 		= new char[N];
			char[] evesFilterSets 	= new char[N];	
				
/////////////////////////////////////////////////////////////////////////
		
		// 	Arbitrarily choose the correspondence betwee filter and bit 
		//	meaning.
			
			System.out.println ("\nFilter SETs & bit meaning = ");
			System.out.println ("\t{+} SET: (|) = 1, (-) = 0");
			System.out.println ("\t{X} SET: (\\) = 1, (/) = 0");

		// 	Read in Alices filter choices i.e., arg[0]. 
		//	These are also the photons she sends.
		
		System.out.println("\n============== ALICE ==============");
		System.out.print  ("\nAlice's Filters == Alice's Photons.\n\n");
		char[] alicesFilters = QuantumKeyDistributionSimulationMethods.
											getInputFiltersVector(args[0]);
											
/////////////////////////////////////////////////////////////////////////
		
		// Convert Alices filter choices to filter SET choices 
		// That she sends to Bob.
		
		System.out.print  ("\n\nAlice's Filter SETS sent to Bob.\n\n");
		char[] alicesFilterSets = QuantumKeyDistributionSimulationMethods.
											getAliceFilterSets(alicesFilters);
											
/////////////////////////////////////////////////////////////////////////
		// Convert Alices filter choices to bits (she started with
		// bits for a message and converted to filters!! 
		// Bob does not yet know these bits OR these filters.
 		
 		System.out.print  ("\nAlice's Bits.\n");				
		int[]  alicesBits 	= QuantumKeyDistributionSimulationMethods.
								getBitsVector(alicesFilters);
												
/////////////////////////////////////////////////////////////////////////
		
		// Randomly choose (generate) Bob's filters	from the set of 4 possible
		System.out.println("\n============== BOB ==============");
		System.out.print  ("\n\nBobs RANDOM Filter Choices.\n\n");
		char[] bobsFilters  = QuantumKeyDistributionSimulationMethods.
								genBobsFilters(N);

/////////////////////////////////////////////////////////////////////////	
		
		// Convert Bob's filter choices to filter SET choices 
		
		System.out.print  ("\nBob's Filter SETS.\n\n");
		char[] bobsFilterSets = QuantumKeyDistributionSimulationMethods.
								getBobsFilterSets(bobsFilters);
											
/////////////////////////////////////////////////////////////////////////

////////////////////////////////  Eve Before Call ///////////////////////180	
		if (Eve == 2)
		{
			// Randomly choose (generate) Eve's filters	
			// from the set of 4 possible.   Same process as for Bob.
			
			System.out.println("\n============== EVE ==============");
			System.out.print  ("\n\nEve's RANDOM Filter Choices.\n\n");
			evesFilters  = QuantumKeyDistributionSimulationMethods.
															genBobsFilters(N);
							
			// Convert Eve's filter choices to filter SET choices 
		
			System.out.print  ("\nEve's Filter SETS.\n\n");
			evesFilterSets = QuantumKeyDistributionSimulationMethods.
											getEvesFilterSets(evesFilters);
			
			// Due to ignorance, Eve can only forward on to Bob a
			// partially random choice of polarized photons (filters)
			// as if they were coming from Alice.  In effect she is
			// randomly redefining the set sent by Alice.
			
			System.out.print  ("\nEve sends out Alice's (MODIFIED) Photons to BOB.\n");
			alicesFilters = QuantumKeyDistributionSimulationMethods.
								evesFilterFixup(alicesFilters, evesFilters);
								
			
		} // end Eve's meddling
///////////////////////////// end   Eve Before Call //////////////////////207
	
////////////////////////////// filterSetMatch ///////////////////////////
		// Alice calls Bob and they match FILTER SETS (not filters or bits)
		
		System.out.println("\n=========== BOB From ALICE/EVE ===========");		
		System.out.print  ("\n\nAlice calls Bob With Filter SETS.");
		System.out.print  ("\nBob Says Which Filter SETS Match.");
		System.out.print  ("\nThis implies filter (& bit).\n\n");
		
		System.out.print  ("\nThese are Bob's filter sets matching Alice's.\n\n");
		
		char [] setMatches = QuantumKeyDistributionSimulationMethods.
							 filterSetMatch(alicesFilterSets, bobsFilterSets);
												
		// If Bob's filter SET matches then either he measured a photon or not.
		// If he measured a photon the FILTER matched. He knows the bit value.
		// If he measured NO photon, he knows his filter from the set is the
		//    opposite of the correct filter, so he still knows the correct
		//    filter and therefore the correct bit.
		
/////////////////////////// end filterSetMatch //////////////////////////

///////////////////////////// Bobs Final Bit Slots //////////////////////
		// Bob converts the matches into useable bits
		
		int[] finalBitsSlots = new int[N]; 
		
		// The original position of the finalBits matched sent by Alice 

		System.out.print  ("\nFilter Matches Imply Bits That Match.\n");
		int[] finalBits = QuantumKeyDistributionSimulationMethods.bitsAfterMatch 
												(alicesFilters, 
												 bobsFilters, setMatches, 
												 finalBitsSlots);
		// This will printout the bits and also where they come from in 
		// Alice's original input. 
		
		System.out.print  ("\nThese bit matches are Bob/Alice.\n\n");
		
		char[] finalBitsEqual= QuantumKeyDistributionSimulationMethods.
								bitsEqualTestAB(finalBitsSlots, setMatches,
												alicesBits, finalBits);
	
		/*
		 * After this Alice lets Bob know a subset of the actual bits sent.
		 * If ANY of them differ they have been compromised by EVE.
		 * All bits have to be dropped and they start over - or catch EVE!
		 */
		 
/////////////////////////// end Bobs Final Bit Slots //////////////////////


////////////////////////////////  Eve After Call ///////////////////////	
		if (Eve == 2)
		{
			System.out.println("\n=========== EVE From ALICE ===========");
			System.out.print ("\n\nEve listens in about Filter SETS.");
			System.out.print ("\nEve looks for filter SETS Match.");
			System.out.print ("\nThis implies filter (& bit).\n");
						
			System.out.print ("\nThese are Eve's filter sets matching Alice's.\n");
			System.out.print ("\nEVE KNOWS THESE.\n\n");
		
			char [] evesSetMatches = QuantumKeyDistributionSimulationMethods.
							filterSetMatch(alicesFilterSets, evesFilterSets);
							
			int[] evesFinalBitsSlots = new int[N]; 
			System.out.print("\nFilter Matches Give Eve's Maybe Matched Bits.\n\n");
			System.out.print("\nEVE DOES NOT KNOW ANY OF THIS.\n\n");
		
			int[] evesFinalBits = QuantumKeyDistributionSimulationMethods.
								bitsAfterMatch (alicesFilters, 
												evesFilters, 
												evesSetMatches, 
												evesFinalBitsSlots);
												
			System.out.print  ("\nThese bit matches are Eve/Alice.\n");
			System.out.print  ("\nThese are only a random guess.\n\n");									
			char[] evesfinalBitsEqual=QuantumKeyDistributionSimulationMethods.
								bitsEqualTestAE (evesFinalBitsSlots, 
												evesSetMatches,
												alicesBits,
												evesFinalBits);
		} // end Eve's meddling
///////////////////////////// end   Eve After Call //////////////////////
	
		
	} // end main
} // end public class QuantumEncryptionProcessSimulation

/////////////////////////////// end class ///////////////////////////////