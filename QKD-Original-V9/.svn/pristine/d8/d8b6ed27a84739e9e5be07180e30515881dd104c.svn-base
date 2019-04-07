// (C) Ronald I. Frank 2004
// Quantum Key Distribution Algoithm Simulation As Java Code
// Compiled in SDK 1.4.2 08/14/04
// V9. Not refactored or fully "objectified"
// 
// ********** Method Lib File **********
//
// Version 9 (With an Eve, With better output)
//
// *************************************
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
// *************************************
//
// main() methods
// Before  Alice's call to Bob
// String [] arg[0]			Input FILTER (PHOTON) designations
// 			 arg[1]			If non zero length - there is an Eve.
// int  N  					the number of input filters, 
//							and therefore bits
//							and photons too.
// char[N] alicesFilters	same vector of N filters input
// char[N] FiltersVector	vector of N filters input
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
//
// After Alice's call to Bob about a subset of actual bits sent
// char[N] finalBitsEqual 	What are the M equal subset bits ? Filled with -3.
//
// Lib methods file is used to run simulator file.
//
// ********** Simulation  Driver **********

/////////////////////////////////// Method //////////////////////////////// 
/////////////////////////////////    Lib  /////////////////////////////////

//////////////////////////////// class ////////////////////////////////////
public class QuantumKeyDistributionSimulationMethods
{

//////////////////////////////// get Input ////////////////////////////////

	public static char [] getInputFiltersVector(String filters)
	{
	   	// Establish Filter array (1 per bit)

		int N = filters.length();

		char[] FiltersVector = new char[N];
		
		// Read in the N elements of Filter and at the same time
      	// Check each Filter for valididty by checking that
      	// the filter designation character
      	// is one of four characters '|', '-', '\', '/'

		for(int i = 1; i <= N; i++)
		{
			FiltersVector[i-1]	= filters.charAt(i-1);	 // Filter i-1
			
			switch(FiltersVector[i-1])
			{
				// Check each filter dedignator is ok
				case '|': 	break;
				case '-':	break;
				case '\\':	break;  // '\' is the escape char
				case '/':	break;
				default:  
					System.out.print  ("INPUT filter character wrong");
					System.out.print  ("in position i = " + i);
            		System.out.println(", INPUT[i] must be ");
            		System.out.print  ("'|', '-', '\', '/'.");
            		System.out.println(" TERMINATING RUN");
					System.out.println(" in getInputFiltersVector.");
					System.out.println("===== getInputFiltersVector ======");
					System.exit(0);
			} // end check each filter dedignator is ok
		} // end read and check parms input
		printCharVector(N, "FilterVector",FiltersVector);
		return 	FiltersVector;
		
	} // end getInputFiltersVector(String filters)
	
//////////////////////////////// end Input //////////////////////////////////
	
///////////////// getAliceFilterSets (char [] alicesFilters) ////////////////
	
		// Convert Alices filter choices to filter SET choices 
		// That she sends to Bob.
		
	public static char[] getAliceFilterSets (char [] alicesFilters)
	{
		//  convert '|' to '+', '-' to '+', '\' to 'X', '/' to 'X'. 
		
		int N = alicesFilters.length;
		
		char [] alicesFilterSets = new char [N];
	
		for(int i = 0; i <= N-1; i++)
		{				
			switch( alicesFilters[i])
			{
				// Check each filter designator is ok
				case '|' : alicesFilterSets[i] = '+'; break;
				case '-' : alicesFilterSets[i] = '+'; break;
				case '\\': alicesFilterSets[i] = 'X'; break;
				case '/' : alicesFilterSets[i] = 'X'; break;
				default:  
					System.out.print  ("INPUT filter character wrong");
					System.out.print  ("in position i = " + i);
					System.out.print  ("In getAliceFilterSets");
            		System.out.println(", INPUT[i] must be ");
            		System.out.print  ("'|', '-', '\', '/'. TERMINATING RUN.");
       				System.out.println("======= getAliceFilterSets =========");
					System.exit(0);
			} // end convert a filter to a folter set
		} // end convert all N filters to a vector of filter sets
		printCharVector(N, "alicesFilterSets",alicesFilterSets);
		return 	alicesFilterSets;
		
	} // end getBitsVector (char[] aliceFilters)
	
//////////////// end getAliceFilterSets (char [] alicesFilters) /////////////

										
///////////////////////////  getBitsVector (from Filters)////////////////////

	public static int [] getBitsVector (char[] alicesFilters)
	{
		//  convert '|' to 1, '-' to 0, '\' to 1, '/' to 0. 
		
		int N = alicesFilters.length;
		
		int [] bitsVector = new int [N];
	
		for(int i = 0; i <= N-1; i++)
		{
			switch(alicesFilters[i])
			{
				// Check each filter dedignator is ok
				case '|' : bitsVector[i] = 1; break;
				case '-' : bitsVector[i] = 0; break;
				case '\\': bitsVector[i] = 1; break;
				case '/' : bitsVector[i] = 0; break;
				default:  
					System.out.print  ("INPUT filter character wrong");
					System.out.print  ("in position i = " + i);
					System.out.print  ("In getBitsVector");
            		System.out.println(", INPUT[i] must be ");
            		System.out.print  ("'|', '-', '\', '/'. TERMINATING RUN.");
       				System.out.println("========= getBitsVector ===========");
					System.exit(0);
			} // end convert a filter to bit
		} // end convert all N filters to bitVector
		printIntVector(N, "bitsVector",bitsVector);
		return 	bitsVector;
		
	} // end getBitsVector (char[] aliceFilters)
///////////////////// end getBitsVector (from aliceFilters)////////////////

/////////////////////// genBobsFilters (randomly) /////////////////////////

	public static char [] genBobsFilters(int N)
	{
		//  choose randomly from the numbers 0 to 3 where
		// '|' ~~ 0, '-' ~~1, '\' ~~2, '/' ~~3. 
		
		char [] bobsFilters = new char [N];
		
		int number = (int)Math.round(Math.random()*3);
	
		for(int i = 1; i <= N; i++)
		{
		
			number = (int)Math.round(Math.random()*3);
				
			switch(number)
			{
				// Check each filter dedignator is ok
				case 0 : bobsFilters[i-1] = '|';  break;
				case 1 : bobsFilters[i-1] = '-';  break;
				case 2 : bobsFilters[i-1] = '\\'; break; // '\' is escape char
				case 3 : bobsFilters[i-1] = '/';  break;
				default:  
					System.out.print  ("INPUT random number i wrong");
					System.out.print  ("in position i = " + i);
					System.out.print  ("In genBobsFilters.");
            		System.out.println("Number must be 0, 1, 2, or 3. ");
            		System.out.print  ("to get '|', '-', '\', '/'.");
            		System.out.print  ("  TERMINATING RUN.");
					System.out.println("==== genBobsFilters ====");
					System.exit(0);
			} // end convert a random number (0 to 3) to a filter
		} // end generate all N filters
	
		printCharVector(N, "Filters",bobsFilters);	
		return 	bobsFilters;
		
	} // end genBobsFilters(int N)
	
////////////////////////////// end genBobsFilters /////////////////////////

///////////////  getBobsFilterSets (char [] BobsFilters) //////////////////
	
		// Convert Bob's filter choices to filter SET choices 

	public static char[] getBobsFilterSets(char [] bobsFilters)
	{
		//  convert '|' to '+', '-' to '+', '\' to 'X', '/' to 'X'. 
		
		int N = bobsFilters.length;
		
		char [] bobsFilterSets = new char [N];
	
		for(int i = 0; i <= N-1; i++)
		{				
			switch(bobsFilters[i])
			{
				// Check each filter designator is ok
				case '|' : bobsFilterSets[i] = '+'; break;
				case '-' : bobsFilterSets[i] = '+'; break;
				case '\\': bobsFilterSets[i] = 'X'; break;
				case '/' : bobsFilterSets[i] = 'X'; break;
				default:  
					System.out.print  ("INPUT filter character wrong");
					System.out.print  ("in position i = " + i);
					System.out.print  ("In getBobsFilterSets");
            		System.out.println(", INPUT[i] must be ");
            		System.out.print  ("'|', '-', '\', '/'. TERMINATING RUN.");
       				System.out.println("==== getBobsFilterSets ====");
					System.exit(0);
			} // end convert a filter to a folter set
		} // end convert all N filters to a vector of filter sets
		printCharVector(N, "bobsFilterSets",bobsFilterSets);
		return 	bobsFilterSets;
		
	} // end getBitsVector (char[] bobsFilterSets)
	
//////////////// end getBobsFilterSets(char [] BobsFilters) ///////////////

		
///////////////  getevesFilterSets (char [] evesFilters) //////////////////
	
		// Convert Eve's filter choices to filter SET choices 
	
	public static char[] getEvesFilterSets(char [] evesFilters)
	{
		//  convert '|' to '+', '-' to '+', '\' to 'X', '/' to 'X'. 
		
		int N = evesFilters.length;
		
		char [] evesFilterSets = new char [N];
	
		for(int i = 0; i <= N-1; i++)
		{				
			switch(evesFilters[i])
			{
				// Check each filter designator is ok
				case '|' : evesFilterSets[i] = '+'; break;
				case '-' : evesFilterSets[i] = '+'; break;
				case '\\': evesFilterSets[i] = 'X'; break;
				case '/' : evesFilterSets[i] = 'X'; break;
				default:  
					System.out.print  ("INPUT filter character wrong");
					System.out.print  ("in position i = " + i);
					System.out.print  ("In getevesFilterSets");
            		System.out.println(", INPUT[i] must be ");
            		System.out.print  ("'|', '-', '\', '/'. TERMINATING RUN.");
       				System.out.println("==== getEvesFilterSets ====");
					System.exit(0);
			} // end convert a filter to a folter set
		} // end convert all N filters to a vector of filter sets
		printCharVector(N, "evesFilterSets", evesFilterSets);
		return 	evesFilterSets;
		
	} // end getBitsVector (char[] evesFilterSets)
	
////////////////// end getevesFilterSets(evesFilters); ////////////////////
										
///////////////////////////// EvesFilterFixup  ////////////////////////////

	public static char [] evesFilterFixup(char[] alicesFilters, 
										  char[] evesFilters)
	{
		//  Fixup the missing photons.  At this point Eve does not know
		//  Alice's filter (photon) choices.  The Best she can do is correct
		//  the null photons to the correct ones.  This will not make any 
		//  difference if Bob chooses the correct filter pair.  HOWEVER, if
		//	he choses the wrong filter pair and gets a null photon he has
		//  perfect knowledge of a pair he will find out is wrong.  This is 
		//	a dead give away that there is an Eve.
		
		int N = evesFilters.length;
	
		char [] newFilters = new char [N];
		
		// When Eve chooses a filter and measures a photon, she has no idea
		// if it came form a matching filter or from one of the other pair.
		// All three cases look alike.
			
		int choice = (int)Math.round(Math.random()*2);
		
		char [] outputChoice1 = {'|',  '\\', '/'};
		char [] outputChoice2 = {'-',  '\\', '/'};
		char [] outputChoice3 = {'\\', '|',  '-'};
		char [] outputChoice4 = {'/',  '|',  '-'};
		
		int Eve = 2;
		
		System.out.print  ("Eve Measures Alice's Photons (filters).\n");
		System.out.print  ("If she gets a gift (NO PHOTON) she passes on\n");
		System.out.print  ("the known photon without a random choice.\n");
	
		// Eve either measures a photon polarization or get no photon.
	
		for(int i = 0; i <= N-1; i++)
		{
			switch(alicesFilters[i])
			{
				//  Check each of Alice's N choices from 4 
				//  possible filter choices - againts each of EVE's
				//  N choices for 4 possible filter choices.  Chances are
				//	1 in 4 that any one will match if every thing is random.
				case '|' : 
				{
					switch(evesFilters[i])
					{
						// Check each filter designator is ok
						case '|' : newFilters[i] = outputChoice1[choice];break;
						case '-' : newFilters[i] = '-'; break; // got no photon
						case '\\': newFilters[i] = outputChoice3[choice];break;
						case '/' : newFilters[i] = outputChoice4[choice];break;
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i +" ");
							System.out.println("in EvesFilterFixUp.");
           					System.out.print  ("Filter must be ");
           					System.out.print  ("'|' or '~'.");
           					System.out.print  ("\nTERMINATING RUN.");
							System.out.print  ("========== Eve's Filter ");
							System.out.println("Fix Up ==========");
							System.exit(0);
					} // end Eve's 4 filter choice cases
				} // end Alice's '|'
			
				break;
						
				case '-' : 
				{
					switch(evesFilters[i])
					{
						// Check each filter designator is ok
						case '|' : newFilters[i] = '|'; break; // got no photon
						case '-' : newFilters[i] = outputChoice2[choice];break;
						case '\\': newFilters[i] = outputChoice3[choice];break;
						case '/' : newFilters[i] = outputChoice4[choice];break;
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i +" ");
							System.out.println("in EvesFilterFixUp.");
           					System.out.print  ("Filter must be ");
           					System.out.print  ("'-' or '~'.");
           					System.out.print  ("\nTERMINATING RUN.");
							System.out.print  ("========== Eve's Filter ");
							System.out.println("Fix Up ==========");
							System.exit(0);
					} // end Eve's 4 filter choice cases
						
 				} // end Alice's '-'
			
				break;
			
			    case '\\' : 
				{
					switch(evesFilters[i])
					{
						// Check each filter designator is ok
						case '|' : newFilters[i] = outputChoice1[choice];break;
						case '-' : newFilters[i] = outputChoice2[choice];break;
						case '\\': newFilters[i] = outputChoice3[choice];break;
						case '/' : newFilters[i] = '/';break; // got no photon
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i +" ");
							System.out.println("in EvesFilterFixUp.");
           					System.out.print  ("Filter must be ");
           					System.out.print  ("'\\' or '~'.");
           					System.out.print  ("\nTERMINATING RUN.");
							System.out.print  ("========== Eve's Filter ");
							System.out.println("Fix Up ==========");
							System.exit(0); 
					} // end Eve's 4 filter choice cases
 				} // end Alice's '\'
			    
				break;
				
				case '/' : 
				{
					switch(evesFilters[i])
					{
						// Check each filter designator is ok
						case '|' : newFilters[i] = outputChoice1[choice];break;
						case '-' : newFilters[i] = outputChoice2[choice];break;
						case '\\': newFilters[i] = '/'; break; // got no photon
						case '/' : newFilters[i] = outputChoice4[choice];break;
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i +" ");
							System.out.println("in EvesFilterFixUp.");
           					System.out.print  ("Filter must be ");
           					System.out.print  ("'/' or '~'.");
           					System.out.print  ("\nTERMINATING RUN.");
							System.out.print  ("========== Eve's Filter ");
							System.out.println("Fix Up ==========");
							System.exit(0);
					} // end Eve's 4 filter choice cases
 				} // end Alice's '/'
 				
				break;
				
 				default: 
					System.out.print  ("Filter matching wrong");
					System.out.print  ("in position i = " + i +" ");
					System.out.println("in filterMatch.");
           			System.out.print  ("Filter must be ");
           			System.out.print  ("'|', '-', '\', '/'.");
           			System.out.print  ("\nTERMINATING RUN.");
					System.out.print  ("========== Eve's Filter ");
					System.out.println("Fix Up ==========");
					System.exit(0);
			} // end switch on char[i-1]
		} // end for all filters i
		
		System.out.print("\nEve REPLACES missing photons with");
		System.out.print("\nknown photons and passes them on to Bob");
		System.out.print("\nas if they were coming from Alice.\n");
		System.out.print("\nThe best she can do if she gets a ");
		System.out.print("\nfiltered photon is to pass on a random");
		System.out.print("\nchoice of either that filter she chose ");
		System.out.print("\nor one of the two complimentary pair.\n\n");
		printCharVector(N, "Eves Fixed Up Output Filters Go To Bob",
															newFilters);
		return 	newFilters;
		
	} // end evesFilterFixup(char[] alicesFilters, char [] evesFilters)
	
///////////////////////////// end EvesFilterFixup /////////////////////////

////////////////////////////////// filterMatch() //////////////////////////
 
	public static char [] filterMatch(char alicesFilters[], 
										char BobsFilters[])
	{
		int N = alicesFilters.length;
		
		char[] filterMatch = new char[N];  
			// Y == a match. ~ == other member of this pair
			// N == no match so could be either of 2
			// 		other possibilities.
		
 		for(int i = 1; i <= N; i++)
		{
			switch(alicesFilters[i-1])
			{
				//  Check each of Alice's N choices from 4 
				//  possible filter choices - againts each of Bob's
				//  N choices for 4 possible filter choices.  Chances are
				//	1 in 4 that any one will match if every thing is random.
				case '|' : 
				{
					switch(BobsFilters[i-1])
					{
						// Check each filter designator is ok
						case '|' : filterMatch[i-1] = 'Y'; break;
						case '-' : filterMatch[i-1] = '~'; break;
						case '\\': filterMatch[i-1] = 'Y'; break;
						case '/' : filterMatch[i-1] = 'Y'; break;
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i);
							System.out.print  (" in filterMatch.");
           					System.out.println("Filter must be ");
           					System.out.print  ("'|', '-', '\', '/'.");
           					System.out.print  (" TERMINATING RUN.");
							System.out.print  ("========== filter Match");
							System.out.println(" ==========");
							System.exit(0);
					} // end Bob's filters
 				} // end Alice's '|'
			
				break;
				
				case '-' : 
				{
					switch(BobsFilters[i-1])
					{
						// Check each filter designator is ok
						case '|' : filterMatch[i-1] = '~'; break;
						case '-' : filterMatch[i-1] = 'Y'; break;
						case '\\': filterMatch[i-1] = 'N'; break;
						case '/' : filterMatch[i-1] = 'N'; break;
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i);
							System.out.print  ("In filterMatch.");
           					System.out.println("Filter must be ");
           					System.out.print  ("'|', '-', '\', '/'.");
           					System.out.print  (" TERMINATING RUN.");
							System.out.print  ("========== filter Match");
							System.out.println(" ==========");
							System.exit(0);
					} // end Bob's filters
 				} // end Alice's '-'
			
				break;
				
				case '\\': 
				{
					switch(BobsFilters[i-1])
					{
						// Check each filter designator is ok
						case '|' : filterMatch[i-1] = 'N'; break;
						case '-' : filterMatch[i-1] = 'N'; break;
						case '\\': filterMatch[i-1] = 'Y'; break;
						case '/' : filterMatch[i-1] = '~'; break;
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i);
							System.out.print  ("In filterMatch.");
           					System.out.println("Filter must be ");
           					System.out.print  ("'|', '-', '\', '/'.");
           					System.out.print  (" TERMINATING RUN.");
							System.out.print  ("========== filter Match");
							System.out.println(" ==========");
							System.exit(0);
					} // end Bob's filters
 				} // end Alice's '\\'
			
				break;
				
				case '/' : 
				{
					switch(BobsFilters[i-1])
					{
						// Check each filter designator is ok
						case '|' : filterMatch[i-1] = 'N'; break;
						case '-' : filterMatch[i-1] = 'N'; break;
						case '\\': filterMatch[i-1] = '~'; break;	
						case '/' : filterMatch[i-1] = 'Y'; break;
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i);
							System.out.print  ("In filterMatch.");
           					System.out.println("Filter must be ");
           					System.out.print  ("'|', '-', '\', '/'.");
           					System.out.print  (" TERMINATING RUN.");
							System.out.print  ("========== filter Match");
							System.out.println(" ==========");
							System.exit(0);
					} // end Bob's filters
 				} // end Alice's '/'
			
				default:
				{  
					System.out.print  ("Filter matching wrong ");
					System.out.print  ("in position i = " + i);
					System.out.println(" in filterMatch.");
           			System.out.print  ("Filter must be ");
           			System.out.print  ("'|', '-', '\', '/'.");
           			System.out.println(" TERMINATING RUN.");
					System.out.print  ("========== filter Match");
					System.out.println(" ==========");
					System.exit(0);
				}
			} // end switch: Check Alice's filter i against Bob's filter i
		} // end for check all of Alice's filters against all of Bob's filters
		
		System.out.print("\n ` METHOD USED BY BOTH BOB AND EVE.");
		System.out.print("\n Y == Filter SET Match, N == No Filter SET Match");
		System.out.print("\n ~ == No Photon Measured");
		System.out.print("\n (So Bob/Eve knows the BIT).");
		System.out.println("\n It is the opposite to the filter chosen.");
		System.out.print("\n Filter SET Match means Bob/Eve knows the BIT.");
		System.out.print("\n If a photon was measured, it's filter matches");
		System.out.print("\n Alice's filter OR indicates the other filter");
		System.out.print("\n set (pair) BUT NOT BIT!\n\n");
		printCharVector(N, "filterSetsMatch", filterMatch);
		
		return 	filterMatch;
		
	} // end filterMatch(char aliceFilters[], char BobsFilters[])
	
//////////////////////////////// end filterMatch() ////////////////////////

//////////////////////////////// filterSetMatch() /////////////////////////
 
	public static char [] filterSetMatch(char [] alicesFilterSets, 
										 char [] bobsFilterSets)
	{
		int N = alicesFilterSets.length;
		
		char[] setMatches = new char[N];  
			// Y == a match of the pair X ==X or + = = +
			
 		for(int i = 1; i <= N; i++)
		{
			switch(alicesFilterSets[i-1])
			{
				//  Check each of Alice's N choices from 2 
				//  possible filter choices - againts each of Bob's or Eve's
				//  N choices for 2 possible filter choices.  Chances are
				//	1 in 2 that any one will match if every thing is random.
				case '+' : 
				{
					switch(bobsFilterSets[i-1])
					{
						// Check each filter designator is ok
						case '+' : setMatches[i-1] = 'Y'; break;
						case 'X' : setMatches[i-1] = 'N'; break;
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i);
							System.out.print  ("In filterMatch.");
           					System.out.println("Filter must be ");
           					System.out.print  ("'|', '-', '\', '/'.");
           					System.out.print  (" TERMINATING RUN.");
							System.out.print  ("========== filter Match");
							System.out.println(" ==========");
							System.exit(0);
					} // end Bob's filters sets
 				} // end Alice's '+'
			
				break;
			
				case 'X': 
				{
					switch(bobsFilterSets[i-1])
					{
						// Check each filter designator is ok
						case '+' : setMatches[i-1] = 'N'; break;
						case 'X' : setMatches[i-1] = 'Y'; break;
						default: 
							System.out.print  ("Filter matching wrong");
							System.out.print  ("in position i = " + i);
							System.out.print  ("In filterMatch.");
           					System.out.println("Filter must be ");
           					System.out.print  ("'|', '-', '\', '/'.");
           					System.out.print  (" TERMINATING RUN.");
							System.out.print  ("========== filter Match");
							System.out.println(" ==========");
							System.exit(0);
					} // end Bob's filter sets
 				} // end Alice's 'X'filter
 				
 				break;
 				
 				default:
				{  
					System.out.print  ("Filter matching wrong ");
					System.out.print  ("in position i = " + i);
					System.out.println(" in filterSetMatch.");
           			System.out.print  ("Filter must be ");
           			System.out.print  ("'+', 'X'.");
           			System.out.println(" TERMINATING RUN.");
					System.out.print  ("========== filterSetMatch");
					System.out.println(" ==========");
					System.exit(0);
				}
			} // end switch: Alice's filter set i against Bob's filter set i
		} // end for check all of Alice's sets against all of Bob's sets
		
		System.out.println("\n Y==Filter SET Match, N==No Filter SET Match.\n");
//		System.out.print("\n If No Photon Measured");
//		System.out.print("\n (So Bob/Eve knows the BIT).");
//		System.out.println("\n It is the opposite to the filter chosen.");
//		System.out.print("\n Filter SET Match means Bob/Eve knows the BIT.");
//		System.out.print("\n If a photon was measured, it's filter matches");
//		System.out.print("\n Alice's filter OR indicates the other filter");
//		System.out.print("\n set (pair) BUT NOT BIT!\n\n");
		printCharVector(N, "filterSetMatches", setMatches);
		
		return 	setMatches;
		
	} // end filterSetMatch(char [] alicesFilterSets, char [] bobsFilterSets)
/////////////////////////////end filterSetMatch() /////////////////////////
 
//////////////////////////////// bitsAfterMatch ///////////////////////////
 
	public static int [] bitsAfterMatch (char  	alicesFilters[],
										 char  	bobsFilters[],
										 char  	setMatches[],
										 int[] 	finalBitsSlots)
	{
		int N = alicesFilters.length;
		
		int[] finalBits = new int[N]; 	// A working array. we return a 
										// smaller array  
		
			// Y == a match. ~ == no bit measured so bit sent was from
			//      the ohter filter of the pair Bob chose
			// N == no match so could be either of 2 possibilities
			//      from the other FILTER SET
			// A finalBits has only 0 and 1 entries
			// now meaning real bits.
	
 		// initialize finalBitsSlots to an impossible index value
 	
 		int	K = 0;
 				
 		for(int i = 0; i <= N-1; i++)
		{
			finalBitsSlots[i] = -3;
		} // end init finalBitsSlots
 		
 		for(int i = 0; i <= N-1; i++)
		{
			if (setMatches[i] == 'Y')
			{
				finalBitsSlots[K] = i;
				
				// Bob knows the filter set so bit is correct
				switch(alicesFilters[i])
				{
					// filters mean what they say
					case '|' : finalBits[K] = 1; break;
					case '-' : finalBits[K] = 0; break;
					case '\\': finalBits[K] = 1; break;
					case '/' : finalBits[K] = 0; break;	
					default: 
						System.out.print  ("Filter matching wrong");
						System.out.print  ("in position i = " + i);
						System.out.print  (" in bitsAfterMatch.");
           				System.out.println(" Filter must be ");
           				System.out.print  ("'|', '-', '\', '/'.");
           				System.out.print  ("\nTERMINATING RUN. ");
						System.out.print  ("\n========== bitsAfterMatch = 1");
						System.out.println(" ==========");
						System.exit(0);
				} // end filters matched
				K = K+1; // bump up K, we have a good bit
			} // end match == 1
			
			else	
			
			if (setMatches[i] == '~')
			{
				
				finalBitsSlots[K] = i;
								
				switch(bobsFilters[i])
				{
					// we got a perfect NON MATCH in BobsFilters
					// notice the reversed meanings of the bits
					// from abave
					case '|' : finalBits[K] = 0; break;
					case '-' : finalBits[K] = 1; break;
					case '\\': finalBits[K] = 0; break;	
					case '/' : finalBits[K] = 1; break;	
					default: 
						System.out.print  ("Filter matching wrong");
						System.out.print  ("in position i = " + i);
						System.out.print  ("In filterMatch.");
           				System.out.println("Filter must be ");
           				System.out.print  ("'|', '-', '\', '/'.");
           				System.out.print  (" TERMINATING RUN.");
						System.out.print  ("========== bitsAfterMatch = 0");
						System.out.println(" ==========");
						System.exit(0);
				} // end reverse filters matched
				K = K+1;// bump up K, we have a good bit
			} // end match == 0
			
			else	
			
			if (setMatches[i] == 'N')
			{
				// Drop this possible bit! Make no change to j,
			} // end match == 'N'.  This is the ambiguous case.
			
		} // end for i, the whole match vector
		
		System.out.print("\n Final Number Of Matched Bits = " + K + ".\n");
		System.out.print("\n Same routine is used by both Bob & Eve");
		System.out.print("\n only the inputs are different.\n");
		
		printIntVector(K, "Final Bits", finalBits);
		
		System.out.print("\n Where the final bits came from in ");
		System.out.print("\n Alice's input or Bob's/Eve's ");
		System.out.print("\n filter vector index.\n");
		
		printIntVector(K, "finalBitsSlots", finalBitsSlots);
		
		// Shorten returned result so we can get K in bitsEqualTest
		
		int[] tempShortArray = new int [K];
	
		for(int k = 0; k <= K-1; k++)
		{
			tempShortArray[k] = finalBits[k];
		} // end init finalBitsSlots
		
	
		return tempShortArray; // RETURN JUST THE MATCHING BITS
		
	} // end bitsAfterMatch (char aliceFilters[],char  	BobsFilters[],
	  // 					 char match[],int[]	finalBitsSlots)
	  
////////////////////////////// bitsAfterMatch //////////////////////////////
							
////////////////////////////// bitsEqualTestAB ////////////////////////////
 
public static char [] bitsEqualTestAB(int  [] finalBitsSlots,
									char [] BobsMatches,
									int  [] alicesBitsVector,
									int  [] FinalBits)
{
	// Make comparison vector of 'Y' or 'N'

	int K = FinalBits.length;
	int total = 0;

	char[] finalBitsEqual = new char [K];
	
	for(int i = 0; i <= K-1; i++)
	{
		//  Compare Bob's FinalBits (short vector) to Alices bits (long vector)
		//  only where they were found to have a filter set equal.
		//  Set the ouput short vector element to 'Y' if the bits do match.
		//  Else set the ouput in that position to 'N'
				
		if(FinalBits[i] == alicesBitsVector[finalBitsSlots[i]])
		{
			finalBitsEqual[i] = 'Y';
			total++;
		}
		else
		{
			finalBitsEqual[i] = 'N';
			System.out.println("This bit " + "[" + i + "] " +  "not equal.");
			System.out.println("\t\t\tEVE DETECTED!");
		}
	} // end for i
	
	System.out.println ("\n");
	System.out.println ("The number of equal bits is: " + total + "\n");
	printCharVector(K, "finalBitsEqual", finalBitsEqual);
	System.out.print   ("\n If Eve, all final bits are PROBABLY not equal.");
	System.out.print   ("\n That's one reason why we use long bitstrings -");
	System.out.print   ("\n to reduce the probability close to 0.\n\n");
	System.out.print   (" If NO Eve, all final bits MUST be equal\n");
	System.out.print   (" by construction.\n\n");
	
	return finalBitsEqual;
} // end bitsEqualTest


							
////////////////////////////// bitsEqualTestAB ////////////////////////////

////////////////////////////// bitsEqualTestAE ////////////////////////////
 
public static char [] bitsEqualTestAE(int  [] finalBitsSlots,
									char [] BobsMatches,
									int  [] alicesBitsVector,
									int  [] FinalBits)
{
	// Make comparison vector of 'Y' or 'N'

	int K = FinalBits.length;
	int total = 0;

	char[] finalBitsEqual = new char [K];
	
	for(int i = 0; i <= K-1; i++)
	{
		//  Compare Eve's FinalBits (short vector) to Alices bits (long vector)
		//  only where they were found to have a filter set equal.
		//  Set the ouput short vector element to 'Y' if the bits do match.
		//  Else set the ouput in that position to 'N'  This is usually
		//	different from the results of Bob's final bits matched to Alice's.
				
		if(FinalBits[i] == alicesBitsVector[finalBitsSlots[i]])
		{
			finalBitsEqual[i] = 'Y';
			total++;
		}
		else
		{
			finalBitsEqual[i] = 'N';
			System.out.println("This bit " + "[" + i + "] " +  "not equal.");
		}
	} // end for i
	
	System.out.println ("\n");
	System.out.println ("The number of equal bits is: " + total + "\n");
	printCharVector(K, "finalBitsEqual", finalBitsEqual);
	System.out.print   ("\n If Eve, all final bits are PROBABLY not equal.");
	System.out.print   ("\n That's one reason why we use long bitstrings -");
	System.out.print   ("\n to reduce the probability close to 0.\n\n");
	
	return finalBitsEqual;
} // end bitsEqualTest


							
////////////////////////////// bitsEqualTestAE ////////////////////////////
 											
////////////////////////////// Print CharVector ///////////////////////////
 
	public static void printCharVector(int N, String name, char [] Vector)
	{
		System.out.println(" Vector Name = " 		+ name + ": " );
		
		for(int j = 0; j <= N-1; j++)
		{
			System.out.print("[j= " + j + "]" );
			System.out.println(" = " + Vector[j] );
		} // end for j
	} // end output of printCharVector

////////////////////////////// end Print CharVector ///////////////////////

////////////////////////////// Print IntVector ////////////////////////////
 
	public static void printIntVector(int N, String name, int[] Vector)
	{
		System.out.println("\n Vector Name = " 		+ name + ": " );
		
		for(int j = 0; j <= N-1; j++)
		{
			System.out.print("[j= " + j + "]" );
			System.out.println(" = " + Vector[j] );
		} // end for j
	} // end output of printIntVector

////////////////////////////// end Print IntVector ////////////////////////

} // end public class QuantumKeyDistributionSimulationMethods

//////////////////////////////// end class ////////////////////////////////