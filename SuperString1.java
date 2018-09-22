/**
 * Shortest Common Super String Problem 
 * DATA SET 1 (in abba land)
 * @author Leszek A Gasieniec
 */

// find the shortest string which contains 

import java.util.*;

public class SuperString1 {
    

   public static void main(String[] args) {
	   
	    int fail=0, evid=-1; // failure flag + evidence
		
		float REF=25; // reference point
		
		float Quotient=100;
		
	    String Solution=""; // initialisation of the solution
		
		String[] S = new String[20];
		
		S[0] = "babbabbababba";
		S[1] = "bbabbababbabb";	  
		S[2] = "ababbabbababb";	 
		S[3] = "abbababbabbab";
		S[4] = "bababbababbab";
		S[5] = "babbababbabab";
		S[6] = "babbabbababba";
		S[7] = "bbababbabbaba";
		S[8] = "ababbababbabb";
		S[9] = "abbababbababb";
		
		S[10] = "abbabbababbab";
		S[11] = "bababbabbabab";	  
		S[12] = "babbababbabba";	 
		S[13] = "babbabbababba";
		S[14] = "bbababbabbaba";
		S[15] = "ababbababbabb";
		S[16] = "abbababbababb";
		S[17] = "abbabbababbab";
		S[18] = "bababbabbabab";
		S[19] = "babbababbabba";
		
		
	    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// DO NOT CHANGE ANYTHING ABOVE THIS LINE
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		

		// Insert your solution below
		// In the current solution all strings from S[] are simply concatenated. 
		// Try to improve this solution. Go as close as possible with quality quotient to (or even below) 1
		

		// Solution="babbababbababbabbababbabb";

		
		// Variables
		float upper_bound = REF;			// Upper bound of solution to remove words
		int remove_cnt = 0;				// Count the number of removals

		
		// Code will loop and reset the procedure if the check reaches n number of removals
		// This ensure that the code does not continue in a infinite loop
		boolean reset = true;	// Reset check variable
		while (reset == true){
			
			
			// =======  INITIALIZE  =======
			
			// Set linked list to store the words in the current list
			LinkedList<String> cur_list = new LinkedList<String>();		// Current words to check
			int cur_size = 0;
			LinkedList<String> sol_list = new LinkedList<String>();		// Words in the solution
			int sol_size = 0;
			

			// Add all words to linked list
			for (int i = 0 ; i <= 19; i++){
				cur_list.add(S[i]);
				cur_size += 1;
			}
			
			
			// Remove duplicates
			for (int iWord = 0; iWord < cur_size; iWord++){
				
				// Get word
				String check_word = cur_list.get(iWord);
				int jCheck = iWord + 1;
				
				// Check word in linked list
				while(jCheck < cur_size){ 
					
					// if duplicate found, remove
					if (check_word.equals(cur_list.get(jCheck))){
						cur_list.remove(jCheck);
						cur_size--;
					// else, check next word
					}else{
						jCheck += 1;
					}
				}
			}
			
			// Set first value as initial position and remove from list
			Solution = cur_list.getFirst();
			sol_list.add(Solution);
			sol_size++;
			
			cur_list.remove(0);
			cur_size--;
			
			
			// =======  CHECK  =======
			
			// Loop until all words in current list has been used, or interrupted
			while (cur_size > 0){
				
				
				// UPDATE
				// Check all words to see if any makes up solution and move to solution list
				for(int iString = 0; iString < cur_size; iString++){
					String check_string = cur_list.get(iString);	// Current word
					int index = Solution.indexOf(check_string);		// Get index position in solution
					
					// if position identified in solution
					if (index != -1)
					{
						// Set array to store index position of all solution to find the insert position
						int[] inx = new int[sol_size];
						for(int i=0;i<sol_size;i++){
							inx[i] = Solution.indexOf(sol_list.get(i));
						}
						
						// Loop all index positions
						for (int i = 0; i < inx.length;i++){
							
							// if index is greater than the position of the check word, insert
							if (inx[i] > index){
								sol_list.add(i,cur_list.get(iString));
								sol_size++;
								
								cur_list.remove(iString);
								cur_size--;
								
								break;
							}	
						}			
					}
				}
							
				
				// Set array to store value of addition to Solution and position for each word
				int[] sVal = new int[cur_size];				// Value of word added to solution
				String[] s_tail = new String[cur_size];		// Suffix trail 
					
				// Check variables
				int remain_val = 0;
				String str_tail = "";
				
				// =====  STRING CHECK  =====
				
				// Loop for each remaining word in current list
				for(int iString = 0; iString < cur_size; iString++){
					
					
					// ~~~~~  Variables  ~~~~~
					String comp_string = cur_list.get(iString);	// Get string from current list
					sVal[iString] = comp_string.length() + 1;	// Set value as max, to ensure argmin function does not select a invalid word
					int strLeng = Solution.length();			// Get length of solution

								
					// Loop and compare for every letter in the string
					for( int iPart = 0; iPart <= comp_string.length();iPart++){
						
						
						// Get prefix from check word, and suffix from solution to check
						String comp_prefix = comp_string.substring(0,iPart);		// Compare string prefix
						String sol_suffix = Solution.substring(Solution.length()-iPart,Solution.length());		// Solution suffix
						
						// if the prefix and suffix match
						if (sol_suffix.equals(comp_prefix)){
							// Store value and trail in the variables to be set in array
							remain_val = comp_string.length() - iPart;
							str_tail = comp_string.substring(iPart,comp_string.length());
						
						// else, set as max values
						} else {
							remain_val = comp_string.length();
							str_tail = comp_string;
						}
						
						// Check if the current value is less that the stored value
						if (remain_val < sVal[iString] ){
							// Update value and trail
							sVal[iString] = remain_val;
							s_tail[iString] = str_tail;
						}	
					}
				}	

				// =====  ARGMIN  =====
				// identify the minimum value from array to apply to solution
				int argmin = sVal[0];	// argmin value
				int arr_pos = 0;			// array position
				
				// Loop for all words in array
				for (int i=1; i < cur_size; i++){
					// if value is less then current min, set variables
					if (sVal[i] < argmin){
						argmin = sVal[i];
						arr_pos = i;
					}
				}


				// Check Null
				int solLen = 0;
				if (s_tail[arr_pos] == null){
					System.out.println("Null Found");
				} else{
					solLen = s_tail[arr_pos].length();
				}
				
				
				// =====  MOVE TO SOLUTION  =====
				// The solution is restricted by an upper bound to help reduce the overall quotient
				// when the upper bound is reached, left most word in solution is removed back to current list to be checked again.
				
				int sol_len = s_tail[arr_pos].length();	// get value of word to be added to solution
				
				// check if new solution will be greater than the upper bound.
				if (Solution.length() + sol_len > upper_bound) {
					
					// get SECOND word from solution to identify the new start point
					String Pos2 = sol_list.get(1);	
					int index = Solution.indexOf(Pos2);		// get index from second word
					// remove first word from solution by setting second word as start position
					Solution = Solution.substring(index);	
					
					// Remove word from solution list and add back to current list
					cur_list.add(sol_list.getFirst());
					cur_size++;
					
					sol_list.removeFirst();
					sol_size--;
					// Add counter to reset if counter limit reached
					remove_cnt++;
				
				// Else if, word can be added to solution
				} else {
					
					// Set to new Solution with trail end 
					Solution = Solution + s_tail[arr_pos];
					
					// Move word from current list to solution list
					sol_list.add(cur_list.get(arr_pos));
					sol_size++;
					
					// REmove from current list
					cur_list.remove(arr_pos);
					cur_size--;
					
				}
				
			
				// =====  RESET  =====
				// Reset and increase upper bound, if word was removed n number of times
				if (remove_cnt > 500){
					cur_size = 0;		// Set size zero to exit word loop
					remove_cnt = 0;		// Reset count
					upper_bound += 1;	// increase upper bound
				
				// Else if all words has been used, exit reset loop
				} else if(cur_size == 0 ){
					reset = false;
				}

			}
		
		}		
        
		// Print final solution
		System.out.println("Solution found: "+Solution);
		
		
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// DO NOT CHANGE ANYTHING BELOW THIS LINE
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
			   
		// computes how many string are not included
		
		for (int i = 0; i < 20; i++) {
			if (!Solution.contains(S[i])) {fail++;evid=i;}
		}
		
		// Calculation of the quality quotient (wrt the reference REF)
		
		Quotient=((float)Solution.length())/REF;
		
		if (fail>0) {System.out.println(" This is not a superstring, string S["+evid+"] is not included, #fails= "+fail);} 
			else {System.out.println(" Well done! Your quality quotient= "+Quotient);}
			  
			   
   }

}



