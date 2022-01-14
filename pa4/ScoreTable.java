// Name: Haolun Cheng
// USC NetID: haolunch
// CS 455 PA4
// Fall 2021

import java.util.*;

/**
 * A score table for each alphabetical letter
 */
public class ScoreTable {
    /**
    * Representation Invariants:
    * -- onePointLetters to tenPointsLetters only contain alphabetic letters
    * -- wordScore() is NOT case-sensitive
    */

    // declare letters and their respective scores as class constants
    private static final HashSet<Character> onePointLetters = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R'));
    private static final HashSet<Character> twoPointsLetters = new HashSet<>(Arrays.asList('D', 'G'));
    private static final HashSet<Character> threePointsLetters = new HashSet<>(Arrays.asList('B', 'C', 'M', 'P'));
    private static final HashSet<Character> fourPointsLetters = new HashSet<>(Arrays.asList('F', 'H', 'V', 'W', 'Y'));
    private static final HashSet<Character> fivePointsLetter = new HashSet<>(Arrays.asList('K'));
    private static final HashSet<Character> eightPointsLetters = new HashSet<>(Arrays.asList('J', 'X'));
    private static final HashSet<Character> tenPointsLetters = new HashSet<>(Arrays.asList('Q', 'Z'));
    private static final int onePoint = 1;
    private static final int twoPoints = 2;
    private static final int threePoints = 3;
    private static final int fourPoints = 4;
    private static final int fivePoints = 5;
    private static final int eightPoints = 8;
    private static final int tenPoints = 10;

    // global variables for scoretable representation and operations
    private HashMap<HashSet<Character>, Integer> scoretable;
    private String word;
    private int totalScore;

    /**
     * Look up the score table for the input string.
     * @param word input string to be evaluated
     */
    public ScoreTable(String word){
        this.word = word;
        this.totalScore = 0;
        // construct a hashmap as the score table representation
        this.scoretable = new HashMap<>();
        this.scoretable.put(onePointLetters, onePoint);
        this.scoretable.put(twoPointsLetters, twoPoints);
        this.scoretable.put(threePointsLetters, threePoints);
        this.scoretable.put(fourPointsLetters, fourPoints);
        this.scoretable.put(fivePointsLetter, fivePoints);
        this.scoretable.put(eightPointsLetters, eightPoints);
        this.scoretable.put(tenPointsLetters, tenPoints);
    }

    /**
     * Look up the score table and sum up the total score.
     * @return total score of the input string
     */
    public int wordScore(){
        for(int i = 0; i < this.word.length(); i++){
            for(HashSet<Character> findChar : scoretable.keySet()){
                if(findChar.contains(Character.toUpperCase(word.charAt(i)))){
                    // sum up the score of each char
                    this.totalScore += scoretable.get(findChar);
                }
            }
        }
        return this.totalScore;
    }
}
