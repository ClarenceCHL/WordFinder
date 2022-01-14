// Name: Haolun Cheng
// USC NetID: haolunch
// CS 455 PA4
// Fall 2021

import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class is for handling all print out messages and command line arguments.
 * It contains the main method for running the whole project.
 */
public class WordFinder {
    /**
    * Representation Invariants:
    * -- Output shoule be in reverse order of the score values (i.e from highest to lowest)
    * -- Words with same score should be in alphabetical order
    * -- The hardcode dictionary file should be a valid txt file
    */

    /**
     * Main method.
     * @param arg
     */
    public static void main(String[] arg){
        try{
            Scanner in = new Scanner(System.in);
            AnagramDictionary dictionary;
            if(arg.length != 0) dictionary = new AnagramDictionary(arg[0]); // user specified dictionary file
            else dictionary = new AnagramDictionary("sowpods.txt");
            System.out.println("Type . to quit.");
            while(true){
                System.out.print("Rack? ");
                String word = in.nextLine().trim();
                if(word.equals(".")) break;
                Rack rack = new Rack(word);
                ArrayList<String> allsubsets = rack.getAllSubsets(0);
                ArrayList<String> findAnagram = new ArrayList<>();
                for(String string : allsubsets) findAnagram.addAll(dictionary.getAnagramsOf(string));
                System.out.println("We can make " + findAnagram.size() + " words from \"" + word + "\"");
                if(findAnagram.size() != 0) System.out.println("All of the words with their scores (sorted by score):");
                findScoreAndWords(findAnagram);
            }
            in.close();
        } catch(FileNotFoundException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println("Exiting program.");
        } catch(IllegalDictionaryException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println("Exiting program.");
        }
    }

    /**
     * Find score for each word and list out all words from highest score to lowest score.
     * @param findAnagram list of anagram words
     */
    private static void findScoreAndWords(ArrayList<String> findAnagram){
        // use treemap to maintain the reverse order of scores
        Map<Integer, List<String>> multiMap = new TreeMap<>(Collections.reverseOrder());
        for(String anagram : findAnagram){
            ScoreTable scoreTable = new ScoreTable(anagram);
            int score = scoreTable.wordScore();
            List<String> ls = new ArrayList<>();
            if(multiMap.containsKey(score)){
                ls = multiMap.get(score);
                ls.add(anagram);
                multiMap.put(score, ls);
                continue;
            }
            ls.add(anagram);
            multiMap.put(score, ls);
        }
        printScoreAndWords(multiMap);
    }

    /**
     * Maintain an alphabetical order for words with same scores.
     * Print out all scores and words in required order.
     * @param multiMap map to store scores and words in order
     */
    private static void printScoreAndWords(Map<Integer, List<String>> multiMap){
        for(int score : multiMap.keySet()){
            Collections.sort(multiMap.get(score), new Comparator<String>() {
                public int compare(String s1, String s2){
                    return s1.compareTo(s2);
                }
            });
            for(String word : multiMap.get(score)){
                System.out.println(score + ": " + word);
            }
        }
    }
}