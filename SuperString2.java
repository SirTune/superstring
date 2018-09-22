/**
 * Shortest Common Super String Problem 
 * DATA SET 2 (connect the pairs)
 * @author Leszek A Gasieniec
 */

// find the shortest string which contains 

import java.util.*;

public class SuperString2 {
    

   public static void main(String[] args) {
	   
	    int fail=0, evid=-1; // failure flag + evidence
		
		float REF=21; // reference point
		
		float Quotient=100;
		
	    String Solution=""; // initialisation of the solution
		
		String[] S = new String[20];
		
		S[0] = "ab";
		S[1] = "ac";
		S[2] = "ad";
		
		S[3] = "ae";	 
		S[4] = "af";		
		S[5] = "ba";
		S[6] = "bc";	  
		S[7] = "ca";
		S[8] = "cb";	 
		S[9] = "cd";
		
		S[10] = "da";			
		S[11] = "dc";
		S[12] = "de";
		S[13] = "ea";
		S[14] = "ed";
		S[15] = "ef";		
		S[16] = "fa";
		S[17] = "fe";
		S[18] = "fg";
		S[19] = "gf";

		
		
		
		
	    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// DO NOT CHANGE ANYTHING ABOVE THIS LINE
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		

		// Insert your solution below
		// In the current solution all strings from S[] are simply concatenated. 
		// Try to improve this solution. Go as close as possible with quality quotient to (or even below) 1

		//Solution="abacadcbcdeaedafefgfa";
		//Solution=S[0]+S[1]+S[2]+S[8]+S[9]+S[13]+S[14]+S[4]+S[15]+S[19]+"a";
		
		//Solution="abacbcdcadeaedafefgfa";
		Solution="abacbcdcadaedefeafgfa";
		
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



