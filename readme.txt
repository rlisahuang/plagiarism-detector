*************************
Description
*************************
This is a command-line program that performs plagiarism detection using a N-tuple comparison algorithm.
It takes in three required arguments and one optional. 

1) file name for a list of synonyms
2) input file 1
3) input file 2
4) (optional) the number N, the tuple size.  If not supplied, the default should be N=3.

In other cases, a usage instruction would be printed:
    Usage: java PlagiarismDriver <synonym_filename> <input1_filename> <input2_filename> [optional]<N_value>

The synonym file has lines each containing one group of synonyms.  For example a line saying "run sprint jog" means these words should be treated as equal.

The input files should be declared plagiarized based on the number of N-tuples in file1 that appear in file2, where the tuples are compared by accounting for synonyms as described above.  For example, the text "go for a run" has two 3-tuples, ["go for a", "for a run"] both of which appear in the text "go for a jog".

The output of the program should be the percent of tuples in file1 which appear in file2.  So for the above example, the output would be one line saying "100%".  In another example, for texts "go for a run" and "went for a jog" and N=3 we would output "50%" because only one 3-tuple in the first text appears in the second one.

*************************
Program Design
*************************
- a PlagiarismDriver class serves as the driver class of the program
- a FileProcessor class that reads a file into a certain data type based on the type of the file (a synonym file vs. a file to be checked)
- a PlagiarismDetector class that performs the calculation of plagiarism rate

*************************
Algorithm
*************************
1. Read the synonym file into a HashMap and the files being compared into two one-line strings using FileProcessor
2. Replace the synonyms in the two content strings with main words, i.e. the first word for each synonym group
3. Obtain a list of N-tuples from the content of file 1
4. Obtain a set of N-tuples from the content of file 2
5. Calculate the number of repeated tuples in file 1 that also appear in file 2 and divide the repetition by the total number of tuples in file 1

*************************
Assumptions
*************************
- Only English letters and numbers are considered as candidates for repeated tuples. Other characters are replaced with spaces when processing the data.
- The detector is not case sensitive
- Two tuples are considered to be equal if and only if all words in the tuples are the same and appear in the same order

*************************
Limitations
*************************
- The program does not detect semantical plagiarism. For example, "I ate an apple" and "An apple was eaten" were not considered as equal tuples.
- The program only detects plagiarism in English

*************************
Edge Cases
*************************
- When provided with a non-numeric, non-integer, non-positive N_value or an N_value that is greater than the fewest word count of the two comparing files, the program prints -100.0%.