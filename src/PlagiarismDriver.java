/**
 * PlagiarismDriver.java
 * 
 * Driver class for Plagiarism Detector. Ensures that:
 * 
 * 1. it's given three or four arguments, otherwise a usage instruction is printed
 * 2. it is given three .txt files when given three arguments, and that the fourth argument is an integer when given four arguments
 * 3. it's comparing the content of two distinct, non-empty files
 * 
 * @author Ruanqianqian (Lisa) Huang
 * @since 2018/10/03
 * 
 */


import java.io.*;
import java.util.*;

public class PlagiarismDriver {

    /**
     * Main method.
     * @param args unused
     */
    public static void main (String[] args) {
        if (args.length < 3 || args.length > 4) {
            System.out.println("Usage: java PlagiarismDriver <synonym_filename> <input1_filename> <input2_filename> [optional]<N_value>");
        }
        else {
            String synfile = (args[0].endsWith(".txt")) ? args[0] : args[0] + ".txt";
            String file1 = (args[1].endsWith(".txt")) ? args[1] : args[1] + ".txt";
            String file2 = (args[2].endsWith(".txt")) ? args[2] : args[2] + ".txt";

            HashMap<String, String> synonymMap = FileProcessor.readFileToMap(synfile);
            String input1 = FileProcessor.readFileToString(file1);
            String input2 = FileProcessor.readFileToString(file2);
            PlagiarismDetector detector;
            double percent;
            int n;

            if (file1.equals(file2)) {
                System.out.println("Please provide two different files for detection.");
                return;
            }

            if (input1 != null && input2 != null && synonymMap != null) {

                if (input1.equals("") || input2.equals("")) {
                    System.out.println("Please provide two non-empty files for detection.");
                    return;
                }

                n = (args.length == 3) ? 3 : getNValue(args[3]);
                detector = new PlagiarismDetector(input1, input2, synonymMap, n);
                percent = detector.calculateRepetitions();
                System.out.println(percent*100 + "%");
            }

        }
    }


    /**
     * Gets the numeric value of the fourth argument (if exists) or prints an error message when given a non-integer
     * @param n the string to be parsed into integer
     * @return the numeric value of string n
     */
    public static int getNValue(String n) {
        try {
            return Integer.parseInt(n);
        }
        catch (NumberFormatException ex) {
            System.out.println("Invalid N_value.");
            return 0;
        }
    }

}