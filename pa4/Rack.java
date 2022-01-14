// Name: Haolun Cheng
// USC NetID: haolunch
// CS 455 PA4
// Fall 2021

import java.util.ArrayList;
import java.util.HashMap;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
   /**
    * Representation Invariants:
    * -- If multiset == ".", program terminates itself
    */

   private String multiset;
   private String unique;
   private int[] mult;

   /**
    * Create a rack for the input string letters.
    * @param letters input string to be put on the rack
    */
   public Rack(String letters){
      this.multiset = letters;
      this.unique = "";
      this.mult = new int[this.multiset.length()];
   }

   /**
    * Find all subsets of the input string starting at index k.
    * @param k starting index to find all subsets
    * @return An arraylist of strings representing all subsets
    */
   public ArrayList<String> getAllSubsets(int k){
      this.getUniqueandMult(); // prepare all necessary inputs for the allSubsets method
      return allSubsets(this.unique, this.mult, k);
   }

   /**
    * Find a string of unique letters from the input string.
    * Find the multiplicity of each letter from unique.
    * This method is to find all necessary inputs for the allSubsets method.
    */
   private void getUniqueandMult(){
      // put all input string letters into a hashmap
      HashMap<Character, Integer> hashmap = new HashMap<>();
      for(int i = 0; i < this.multiset.length(); i++){
         if(!hashmap.containsKey(this.multiset.charAt(i))) hashmap.put(this.multiset.charAt(i), 1);
         else hashmap.put(this.multiset.charAt(i), hashmap.get(this.multiset.charAt(i)) + 1);
      }
      StringBuilder sb = new StringBuilder();
      char[] temp = new char[hashmap.size()];
      int index = 0;
      // get the multiplicity of each unique letter from the hashmap values
      Object[] mul = hashmap.values().toArray();
      for(int j = 0; j < mul.length; j++) this.mult[j] = (int)mul[j];
      // get a string of unique letters from the hashmap key set
      for(char x : hashmap.keySet()){
         temp[index] = x;
         index++;
      }
      this.unique = sb.append(temp).toString();
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
