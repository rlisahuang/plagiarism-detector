/**
 * PlagiarismDetector.java
 * 
 * Given a collection of synonyms, using the N-tuple algorithm, this program detects plagiarism in file 1 by comparing it to file 2 
 * and count the number of repeated tuples:
 * 
 * 1. Replaces each synonym in both files with its corresponding key word (the "main" synonym)
 * 2. Obtains a list (might contain duplicates) of N-tuples from file 1 and a set (without duplicates) of N-tuples from file 2
 * 3. Counts the number of tuples from file 1 that also appear in file 2
 * 4. Calculates the plagiarism rate by dividing the number of repetitions by the total number of tuples in file 1
 * 
 * NOTE:
 * If given a non-positive N-value or an N-value that is no more than the minimum number of words in either file, no calculation is performed.
 * 
 * @author Ruanqianqian (Lisa) Huang
 * @since 2018/10/03
 * 
 */


import java.io.*;
import java.util.*;

public class PlagiarismDetector {
    String file1;
    String file2;
    int minWordCount; // records the minimum word count in either input file
    HashMap<String,String> synonymMap;
    HashSet<String> tupleSet;
    ArrayList<String> tupleList;
    int tupleSize;
    int repetition;

    
    /**
     * Constructor for PlagiarismDetector
     * 
     * @param file1 the string content of file1
     * @param file2 the string content of file2
     * @param synonymMap the hashmap of synonyms, with each key pointing to its corresponding key word (the "main" synonym)
     * @param n the size of a tuple
     */
    public PlagiarismDetector(String file1, String file2, HashMap<String, String> synonymMap, int n) {
        this.file1 = file1;
        this.file2 = file2;
        this.minWordCount = Integer.MAX_VALUE;
        this.synonymMap = synonymMap;
        this.tupleSize = n;
        this.repetition = 0;
        this.tupleSet = new HashSet<String>();
        this.tupleList = new ArrayList<String>();
    }

    /**
     * Replaces synonyms in both files with their corresponding key words (the "main" synonyms) and updates the minimum word count
     * with the number of words in the input string
     * @param file the string content of a file
     * @return a string array of words containing only letters and numbers
     */
    public String[] replaceSynonyms(String file) {
        String[] words = file.replaceAll("[^a-zA-Z0-9]+", " ").split(" ");
        for (int i = 0; i < words.length; i++) {
            if (synonymMap.containsKey(words[i])) {
                words[i] = synonymMap.get(words[i]);
            }
        }
        if (words.length < minWordCount) {
            minWordCount = words.length;
        }
        return words;
    }

    /**
     * Obtains a list (might contain duplicates) of N-tuples from a file
     * @param words a string array of words in the file
     */
    public void updateTupleSet(String[] words) {
        for (int i = 0; i <= words.length - tupleSize; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(words[i]);
            for (int j = i+1; j-i < tupleSize; j++) {
                sb.append(" ");
                sb.append(words[j]);
            }
            String tuple = sb.toString();

            if (!tupleSet.contains(tuple)) {
                tupleSet.add(tuple);
            }
        }
    }

    /**
     * Obtains a set (without duplicates) of N-tuples from a file
     * @param words a string array of words in the file
     */
    public void updateTupleList(String[] words) {
        for (int i = 0; i <= words.length - tupleSize; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(words[i]);
            for (int j = i+1; j-i < tupleSize; j++) {
                sb.append(" ");
                sb.append(words[j]);
            }
            String tuple = sb.toString();
            tupleList.add(tuple);
        }
    }

    /**
     * Counts the number of tuples from file 1 that also appear in file 2
     * @return the number of repeated tuples in file 1
     */
    public int numOfRepetitions() {
        String[] file1_updated = replaceSynonyms(file1);
        String[] file2_updated = replaceSynonyms(file2);
        updateTupleList(file1_updated);
        updateTupleSet(file2_updated);

        for (int i = 0; i < tupleList.size(); i++) {
            String tuple = tupleList.get(i);
            if (tupleSet.contains(tuple)) {
                repetition++;
            }
        }
        return repetition;
    }

    /**
     * Calculates the plagiarism rate by dividing the number of repeteated tuples by the total number of tuples in file 1.
     * Ensures that the calculation is only processed when given a tuple size that is 1) positive and 2) no more than the minimum word count
     * @return the proportion of repeated tuples
     */
    public double calculateRepetitions() {

        if (tupleSize > 0) {
            int rep = numOfRepetitions();
            if (tupleSize <= minWordCount) {
                return (double)(rep) / (double)(tupleList.size());

            }
        }
        System.out.println("Please provide a N_Value smaller than the word count of both files.");
        return -1.0;
    }


}