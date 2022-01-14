// Name: Haolun Cheng
// USC NetID: haolunch
// CS 455 PA4
// Fall 2021

import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   /**
    * Representation Invariants:
    * -- Dictionary file cannot be an empty file
    * -- getAnagramsOf() is case-sensitive
    * -- Dictionary CANNOT have duplicate words; checkIfDuplicate() == false
    * -- storeWords contains only english words with alphabetic letters
    */

   private Map<String, HashSet<String>> storeWords;
   private String duplicateWord;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      File file = new File(fileName);
      if(!file.exists()) throw new FileNotFoundException("ERROR: Dictionary file \"" + file.toString() + "\" does not exist.");
      Scanner in = new Scanner(file);
      this.duplicateWord = "";
      this.storeWords = new HashMap<>(); // use a hashmap as the internal representation of the Anagram Dictionary
      this.preprocessingDictionary(in);
      if(this.checkIfDuplicate(file)) {
         throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + this.duplicateWord);
      }
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      HashSet<String> allAnagrams = new HashSet<>();
      char[] letters = string.toCharArray();
      // sort each letter of the string in alphabetical order
      Arrays.sort(letters);
      StringBuilder sb = new StringBuilder();
      String input = sb.append(letters).toString();
      if(this.storeWords.containsKey(input)) allAnagrams = this.storeWords.get(input);
      return new ArrayList<String>(allAnagrams);
   }

   /**
    * Read in the dictionary txt file and store each word inside the dictionary in a hashmap.
    * @param in scanner object to scan the dictionary file
    * PRE: checkIfDuplicate == false
    */
   private void preprocessingDictionary(Scanner in){
      while(in.hasNextLine()){
         String word = in.nextLine();
         if(word.equals(".") || word.equals("")) break;
         char[] letters = word.toCharArray();
         Arrays.sort(letters); // sort each letter of the string in alphabetical order
         StringBuilder sb = new StringBuilder();
         String input = sb.append(letters).toString();
         HashSet<String> hashset = new HashSet<>();
         if(this.storeWords.containsKey(input)){
            hashset = this.storeWords.get(input);
            hashset.add(word);
            this.storeWords.put(input, hashset);
            continue;
         }
         hashset.add(word);
         this.storeWords.put(input, hashset);
      }
   }

   /**
    * Check if there exists duplicate words in a dictionary.
    * @param file dictionary file to read from
    * @return true if there are duplicate words and false otherwise
    * @throws FileNotFoundException
    */
   private boolean checkIfDuplicate(File file) throws FileNotFoundException{
      Scanner in = new Scanner(file);
      HashSet<String> check = new HashSet<>(); // use hashset to check if any collision happens
      boolean result = false;
      while(in.hasNextLine()){
         this.duplicateWord = in.nextLine();
         if(this.duplicateWord.equals(".") || this.duplicateWord.equals("")) break;
         if(check.contains(this.duplicateWord)){
            result = true;
            break;
         }
         check.add(this.duplicateWord);
      }
      in.close();
      return result;
   }
}
