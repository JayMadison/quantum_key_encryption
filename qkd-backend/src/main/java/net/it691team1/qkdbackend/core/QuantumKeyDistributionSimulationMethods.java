package net.it691team1.qkdbackend.core;
import net.it691team1.qkdbackend.dto.*;

//(C) Ronald I. Frank 2004
//Quantum Key Distribution Algoithm Simulation As Java Code
//Compiled in SDK 1.4.2 08/14/04
//V9. Not refactored or fully "objectified"
//
//********** Method Lib File **********
//
//Version 9 (With an Eve, With better output)
//
//*************************************
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
//*************************************
//
//main() methods
//Before  Alice's call to Bob
//String [] arg[0]			Input FILTER (PHOTON) designations
//			 arg[1]			If non zero length - there is an Eve.
//int  N  					the number of input filters, 
//							and therefore bits
//							and photons too.
//char[N] alicesFilters	same vector of N filters input
//char[N] FiltersVector	vector of N filters input
//char[N] alicesFilterSets vector of N corresponding filter SET disigantions
//int [N] alicesBits		Vector of N bits Alice intends (sends)
//char[N] bobsFilters		vector of N filters chosen by Bob
//char[N] bobsFilterSets	vector of N corresponding filter SET disigantions
//char[N] evesFilters		vector of N filters chosen by optional Eve
//char[N] evesFilterSets	vector of N corresponding filter SET disigantions
//char[N] alicesFilters	Again! This time reset by Eve
//
//After Alice's call to Bob about filters
//char[N] setMatches		Which K of N filters match? Y, N, ~
//int [K] finalBits		What are the K of N bits for the matches?
//							Vector is length K in main().
//int [N] finalBitsSlots	What are the K=J of N indexes for the matches?
//							Vector is length N.
//
//After Alice's call to Bob about a subset of actual bits sent
//char[N] finalBitsEqual 	What are the M equal subset bits ? Filled with -3.
//
//Lib methods file is used to run simulator file.
//
//********** Simulation  Driver **********

/////////////////////////////////// Method //////////////////////////////// 
/////////////////////////////////    Lib  /////////////////////////////////

////////////////////////////////class ////////////////////////////////////
/**
 * Logic was written in 2004 by Dr. Frank this is a modified version of the 
 * QKD Methods class which is not coupled to the console. 
 * @author jaythomas
 *
 */
public class QuantumKeyDistributionSimulationMethods
{

////////////////////////////////get Input ////////////////////////////////
	/**
	 * Validates and returns an array of the input vector
	 * @param filters
	 * @return input vector char[] 
	 */
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
					//TODO Add throw
			} // end check each filter dedignator is ok
		} // end read and check parms input
		//printCharVector(N, "FilterVector",FiltersVector);
		return 	FiltersVector;
		
	} // end getInputFiltersVector(String filters)
	
////////////////////////////////end Input //////////////////////////////////
	
///////////////// getAliceFilterSets (char [] alicesFilters) ////////////////
	
		
	
////////////////end getAliceFilterSets (char [] alicesFilters) /////////////

										
///////////////////////////  getBitsVector (from Filters)////////////////////
/**
 * Converts photons to binary value array
 * @param alicesFilters
 * @return bitsVector
 */
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
					//TODO Add throw
			} // end convert a filter to bit
		} // end convert all N filters to bitVector
		//printIntVector(N, "bitsVector",bitsVector);
		return 	bitsVector;
		
	} // end getBitsVector (char[] aliceFilters)
///////////////////// end getBitsVector (from aliceFilters)////////////////

/////////////////////// genBobsFilters (randomly) /////////////////////////
/**
 * Generate Bob's Filters
 * @param N
 * @return
 */
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
					//TODO Add throw
			} // end convert a random number (0 to 3) to a filter
		} // end generate all N filters
	
		//printCharVector(N, "Filters",bobsFilters);	
		return 	bobsFilters;
		
	} // end genBobsFilters(int N)
	
//////////////////////////////end genBobsFilters /////////////////////////

///////////////  getBobsFilterSets (char [] BobsFilters) //////////////////
	
		// Convert Bob's filter choices to filter SET choices 
/**
 * Convert Bob's filter choices to filter SET choices 
 * @param bobsFilters
 * @return bobsfiltersets
 */
	public static char[] getFilterSets(char [] bobsFilters)
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
					//TODO Add throw
			} // end convert a filter to a folter set
		} // end convert all N filters to a vector of filter sets
		//printCharVector(N, "bobsFilterSets",bobsFilterSets);
		return 	bobsFilterSets;
		
	} // end getBitsVector (char[] bobsFilterSets)
	
////////////////end getBobsFilterSets(char [] BobsFilters) ///////////////

		
///////////////  getevesFilterSets (char [] evesFilters) //////////////////
	
		
	
//////////////////end getevesFilterSets(evesFilters); ////////////////////
										
///////////////////////////// EvesFilterFixup  ////////////////////////////
/**
 * Modifies photons due to eve's intervention
 * @param alicesFilters
 * @param evesFilters
 * @return evesfilterFixup
 */
	public static FilterFixupDto evesFilterFixup(char[] alicesFilters, 
										  char[] evesFilters)
	{
		//  Fixup the missing photons.  At this point Eve does not know
		//  Alice's filter (photon) choices.  The Best she can do is correct
		//  the null photons to the correct ones.  This will not make any 
		//  difference if Bob chooses the correct filter pair.  HOWEVER, if
		//	he choses the wrong filter pair and gets a null photon he has
		//  perfect knowledge of a pair he will find out is wrong.  This is 
		//	a dead give away that there is an Eve.
		FilterFixupDto fixup = new FilterFixupDto();
		int N = evesFilters.length;
		int known = 0;
	
		char [] newFilters = new char [N];
		
		// When Eve chooses a filter and measures a photon, she has no idea
		// if it came form a matching filter or from one of the other pair.
		// All three cases look alike.
			
		int choice = (int)Math.round(Math.random()*2);
		
		char [] outputChoice1 = {'|',  '\\', '/'};
		char [] outputChoice2 = {'-',  '\\', '/'};
		char [] outputChoice3 = {'\\', '|',  '-'};
		char [] outputChoice4 = {'/',  '|',  '-'};
		
	
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
						case '-' : newFilters[i] = '-'; known++; break; // got no photon
						case '\\': newFilters[i] = outputChoice3[choice];break;
						case '/' : newFilters[i] = outputChoice4[choice];break;
						default: 
							//TODO Add throw
					} // end Eve's 4 filter choice cases
				} // end Alice's '|'
			
				break;
						
				case '-' : 
				{
					switch(evesFilters[i])
					{
						// Check each filter designator is ok
						case '|' : newFilters[i] = '|'; known++ ;break; // got no photon
						case '-' : newFilters[i] = outputChoice2[choice];break;
						case '\\': newFilters[i] = outputChoice3[choice];break;
						case '/' : newFilters[i] = outputChoice4[choice];break;
						default: 
							//TODO Add throw
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
						case '/' : newFilters[i] = '/'; known++ ;break; // got no photon
						default: 
							//TODO Add throw
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
						case '\\': newFilters[i] = '\\';known++; break; // got no photon
						case '/' : newFilters[i] = outputChoice4[choice];break;
						default: 
							//TODO Add throw
					} // end Eve's 4 filter choice cases
				} // end Alice's '/'
				
				break;
				
				default: 
					//TODO Add throw
			} // end switch on char[i-1]
		} // end for all filters i
		
		fixup.setModFilters(newFilters);
		fixup.setTotal(known);
		return fixup;
		
	} // end evesFilterFixup(char[] alicesFilters, char [] evesFilters)
	
///////////////////////////// end EvesFilterFixup /////////////////////////

//////////////////////////////////filterMatch() //////////////////////////
/**
 * Compares two filters to see which filters match
 * @param alicesFilters
 * @param BobsFilters
 * @return filtermatch
 */
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
						case '\\': filterMatch[i-1] = 'N'; break;
						case '/' : filterMatch[i-1] = 'N'; break;
						default: 
							//TODO Add throw
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
							//TODO Add throw
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
							//TODO Add throw
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
							//TODO Add throw
					} // end Bob's filters
				} // end Alice's '/'
			
				default:
				{  
					//TODO Add throw
				}
			} // end switch: Check Alice's filter i against Bob's filter i
		} // end for check all of Alice's filters against all of Bob's filters
		
		return 	filterMatch;
		
	} // end filterMatch(char aliceFilters[], char BobsFilters[])
	
////////////////////////////////end filterMatch() ////////////////////////

////////////////////////////////filterSetMatch() /////////////////////////
/**
 * Checks for matches between two filter sets
 * @param alicesFilterSets
 * @param bobsFilterSets
 * @return fiterSetMatch
 */
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
							//TODO Add throw
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
							//TODO Add throw
					} // end Bob's filter sets
				} // end Alice's 'X'filter
				
				break;
				
				default:
				{  
					//TODO Add throw
				}
			} // end switch: Alice's filter set i against Bob's filter set i
		} // end for check all of Alice's sets against all of Bob's sets
		
		return 	setMatches;
		
	} // end filterSetMatch(char [] alicesFilterSets, char [] bobsFilterSets)
/////////////////////////////end filterSetMatch() /////////////////////////
////////////////////////////////bitsAfterMatch ///////////////////////////
/**
 * Replacement method to return the match checking as an object
 * @param alicesFilters
 * @param bobsFilters
 * @param setMatches
 * @param finalBitsSlots
 * @return bitsAfterMatch
 */
public static MatchBits bitsAfterMatch (
			 char [] alicesFilters,
			 char [] bobsFilters,
			 char [] setMatches,
			 int [] finalBitsSlots)
{
//Boolean obj is oinly being used to differentiate the method sigs for testing
int N = alicesFilters.length;
MatchBits mb = new MatchBits();

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
	//TODO Add throw
} // end filters matched
K = K+1; // bump up K, we have a good bit
} // end match == 1

else if (setMatches[i] == '~')
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
	//TODO Add throw
} // end reverse filters matched
K = K+1;// bump up K, we have a good bit
} // end match == 0

else	

if (setMatches[i] == 'N')
{
// Drop this possible bit! Make no change to j,
} // end match == 'N'.  This is the ambiguous case.

} // end for i, the whole match vector


int[] tempShortArray = new int [K];

for(int k = 0; k <= K-1; k++)
{
tempShortArray[k] = finalBits[k];
} // end init finalBitsSlots

mb.setBitsAfterMatch(tempShortArray);
mb.setFinalBits(finalBits);
mb.setFinalBitsSlots(finalBitsSlots);
mb.setK(K);
return mb; // RETURN JUST THE MATCHING BITS

} // end bitsAfterMatch (char aliceFilters[],char  	BobsFilters[],
// 					 char match[],int[]	finalBitsSlots)

//////////////////////////////bitsAfterMatch //////////////////////////////
							
//////////////////////////////bitsEqualTestAB ////////////////////////////




							
//////////////////////////////bitsEqualTestAB ////////////////////////////
//////////////////////////////bitsEqualTestAB ////////////////////////////
/**
 * Alice -> Bob test case for matching bits
 * @param finalBitsSlots
 * @param BobsMatches
 * @param alicesBitsVector
 * @param FinalBits
 * @return bitsEqualAfterTestAB
 */
public static EqualBits bitsEqualTest(int [] finalBitsSlots,
		int  [] bitsVector,
		int  [] FinalBits)
{
// Make comparison vector of 'Y' or 'N'
EqualBits eq = new EqualBits();
int K = FinalBits.length;
int total = 0;

char[] finalBitsEqual = new char [K];

for(int i = 0; i <= K-1; i++)
{
//  Compare Bob's FinalBits (short vector) to Alices bits (long vector)
//  only where they were found to have a filter set equal.
//  Set the ouput short vector element to 'Y' if the bits do match.
//  Else set the ouput in that position to 'N'

if(FinalBits[i] == bitsVector[finalBitsSlots[i]])
{
finalBitsEqual[i] = 'Y';
total++;
}
else
{
finalBitsEqual[i] = 'N';
//TODO Add throw if needed to indicate eve
}
} // end for i


eq.setK(K);
eq.setTotal(total);
eq.setBitsEqual(finalBitsEqual);
return eq;
} // end bitsEqualTest



//////////////////////////////bitsEqualTest ////////////////////////////

					
} // end public class QuantumKeyDistributionSimulationMethods

////////////////////////////////end class ////////////////////////////////
