/**
 * FileProcessor.java
 * 
 * Reads a file into a certain data type based on the type of the file (a synonym file vs. a file to be checked)
 * 
 * 
 * @author Ruanqianqian (Lisa) Huang
 * @since 2018/10/03
 * 
 */



import java.io.*;
import java.util.*;

public class FileProcessor {

    /**
     * Reads the content of a file to a one-line string. Converts all letters to lower case and onnects the lines with empty spaces.
     * Prints an error message when an IOException is caught.
     * 
     * @param filename the name of the file to be visited
     * @return a string representation of the content of the file
     */
    public static String readFileToString(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
                sb.append(line);
                sb.append(" ");
                line = br.readLine();
            }

            br.close();
            return sb.toString().toLowerCase();
        }   
        catch (IOException ex) {
            System.out.println("Please provide the correct file name(s) or format(s).");
            return null;
        }
    }

    /**
     * Reads the content of a synonym file to a HashMap. Since one line represent a group of synonym words, all but the first words are
     * added to the HashMap as keys while the first word is added as their corresnponding value.
     * Converts all letters to lower case.
     * Replace all non-alphabetic, non-numeric characters with spaces.
     * Prints an error message when an IOException is caught.
     * 
     * @param filename the name of the file to be read
     * @return a HashMap of synonyms
     */
    public static HashMap<String, String> readFileToMap(String filename) {
        try {
            HashMap<String, String> hm = new HashMap<String, String>();
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            while (line != null) {
                String[] words = line.toLowerCase().replaceAll("[^a-zA-Z0-9]+", " ").split(" ");
                for (int i = 1; i<words.length; i++) {
                    if (!hm.containsKey(words[i])) {
                        hm.put(words[i], words[0]);
                    }
                }
                line = br.readLine();
            }
            br.close();
            return hm;
        }
        catch (IOException ex) {
            System.out.println("Please provide the correct file name(s) or format(s).");
            return null;
        }
    }

}