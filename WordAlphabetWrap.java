import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**.
*
* This file contains 2 different programs.
* The  first program will display a sentence on
* one line with certain amounts of characters
* and the second program will display
* a certain amount of letters in the alphabet.
* both reading and writing to a file.
*
* @author Remy Skelton
* @version 1.0
* @since 2025-05-1
*/

final class WordAlphabetWrap {

    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
    */
    private WordAlphabetWrap() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */

    public static void main(final String[] args) throws Exception {
        // Print the welcome message
        System.out.println("This file contains 2 different programs.");
        System.out.print("The first program will display a sentence on ");
        System.out.println("one line with a certain number of characters.");
        System.out.print("The second program will display ");
        System.out.print("a certain amount of letters in the alphabet. ");
        System.out.println("Both programs read from and write to a file.");

        //  Set user input string
        String userInput = "";

        // Ask user to enter  1 or 2 for the different programs
        System.out.print("Please enter 1 for the first program "
                + "or 2 for the second program: ");

        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Set userInput to the next line
        userInput = scanner.nextLine();

        // Check if userInput is 1 or 2
        if (userInput.equals("1")) {
            // Call wordWrap method
            wordWrap();
        } else if (userInput.equals("2")) {
            // Call alphabetWrap method
            alphabetWrap();
        } else {
            System.out.println("Invalid input. Please enter 1 or 2.");
        }
    }

    /**
     * This method will read a sentence from a file
     * and display it on one line with a certain number
     * of characters.
     *
     * @throws Exception if an error occurs
     */
    public static void wordWrap() throws Exception {
        // Read from input.txt
        File inputFile = new File("inputWord.txt");
        Scanner scanner = new Scanner(inputFile);

        // Make outputWriter to write to outputWord.txt
        FileWriter outputWriter = new FileWriter("outputWord.txt");

        // Create outputStr
        String outputStr = "";

        // While loop to read String from inputWord.txt
        while (scanner.hasNextLine()) {

            // Set line equal to the next line
            String sentence = scanner.nextLine();

            // Check if the line is empty
            if (sentence.isEmpty()) {
                // Print an error message
                outputStr += "Invalid: "
                        + sentence
                        + " is not a valid sentence.\n";
                continue;
            } else {
                if (scanner.hasNextLine()) {
                    // Set the next line to the max
                    // number of characters on a line
                    String lineCharsStr = scanner.nextLine();

                    // Try to convert lineCharsStr to int
                    try {
                        // Convert lineCharsStr to int
                        int lineChars = Integer.parseInt(lineCharsStr);
                        // Check if lineChars is less than 1
                        if (lineChars <= 0) {
                            // Add error message to outputStr
                            outputStr += sentence + "\n"
                                    + "Invalid: "
                                    + lineCharsStr
                                    + " is not a valid number of characters.\n";
                            continue;
                        } else {
                            // Call the recWordWrap method
                            String newSentence =
                            recWordWrap(sentence, lineChars);
                            // Add the new sentence to outputStr
                            outputStr += "Valid case: \n" + newSentence + "\n";
                        }
                    } catch (NumberFormatException e) {
                        // Print an error message
                        outputStr += sentence + "\n"
                                + "Invalid: "
                                + lineCharsStr
                                + " is not a valid number of characters.\n";
                        continue;
                    }
                }
            }
        }

        // Write the output string to output.txt
        outputWriter.write(outputStr);

        // Print the Word wrap results to the console
        System.out.println("Word wrap results written to outputWord.txt.");

        // Close the scanner
        scanner.close();

        // Close the output writer
        outputWriter.close();
    }

    /**
     * This method is the recursive function for word wrap.
     *
     * @param sentence the sentence to wrap
     * @param maxChars the maximum number of characters per line
     * @return the wrapped sentence
     */
    public static String
    recWordWrap(final String sentence, final int maxChars) {
        // Make sure there are no leading or trailing spaces
        String newSentence = sentence.trim();

        // Checks and finds if there is a space before or at maxChars
        int breakIndex = newSentence.lastIndexOf(" ", maxChars);

        // Base case: empty string
        if (newSentence.isEmpty()) {
            return "";
        } else if (newSentence.length() <= maxChars) {
            // If the sentence is shorter than maxChars, return it
            return newSentence + "\n";
        } else if (breakIndex == -1) {
            // If the breakIndex is -1, there is no space
            // before maxChars, so we have to break at maxChars
            return newSentence.substring(0, maxChars) + "\n"
                + recWordWrap(newSentence.substring(maxChars), maxChars);
        } else {
            // If there is a space before maxChars,
            // break the sentence at the last space
            return newSentence.substring(0, breakIndex) + "\n"
                + recWordWrap(newSentence.substring(breakIndex + 1), maxChars);
            }
    }

    /**
     * This method will read a number from a file
     * and display it on one line with a certain number
     * of letters in the alphabet.
     *
     * @throws Exception if an error occurs
     */
    public static void alphabetWrap() throws Exception {
        // Read from input.txt
        File inputFile = new File("inputAlphabet.txt");
        Scanner scanner = new Scanner(inputFile);

        // Make outputWriter to write to outputAlphabet.txt
        FileWriter outputWriter = new FileWriter("outputAlphabet.txt");

        // Create outputStr
        String outputStr = "";

        // While loop to read String from inputAlphabet.txt
        while (scanner.hasNextLine()) {
            // Set the letter equal to the next line
            String letterStr = scanner.nextLine();

            // Check if the line is empty
            if (letterStr.isEmpty()) {
                // Print an error message
                outputStr += "Invalid: "
                        + letterStr
                        + " is not a valid letter.\n";
                continue;
            } else if (letterStr.length() > 1) {
                // Print an error message
                outputStr += "Invalid: "
                        + letterStr
                        + " is not a valid letter.\n";
                continue;
            }

            // Try to convert letter to char
            try {
                // Convert letter to char and set it to lower case
                char letter = letterStr.toLowerCase().charAt(0);

                // Check if  letter is in the alphabet
                if (letter < 'a' || letter > 'z') {
                    // Print an error message
                    outputStr += letterStr + "\n"
                            + "Invalid: "
                            + letterStr
                            + " is not a valid letter.\n";
                    continue;
                } else {
                    //Check if there is a next line
                    if (scanner.hasNextLine()) {
                        //  Set numberOfLettersStr to the next line
                        String numberOfLettersStr = scanner.nextLine();
                        // Try to convert numberOfLettersStr to int
                        try {
                            // Convert numberOfLettersStr to int
                            int numberOfLetters =
                            Integer.parseInt(numberOfLettersStr);
                            // Check if numberOfLetters is less than 1
                            if (numberOfLetters <= 0) {
                                // Print an error message
                                outputStr += letterStr + "\n"
                                        + "Invalid: "
                                        + numberOfLettersStr
                                        + " is not a valid number "
                                        + "of letters.\n";
                                continue;
                            } else {
                                // Call the recAlphabetWrap method
                                String lettersOutput =
                                recAlphabetWrap(letter, numberOfLetters);
                                // Add the new letter to outputStr
                                outputStr += "Valid case: \n"
                                    + lettersOutput + "\n";
                            }
                        } catch (NumberFormatException e) {
                            // Print an error message
                            outputStr += letterStr + "\n"
                                    + "Invalid: "
                                    + numberOfLettersStr
                                    + " is not a valid number of letters.\n";
                            continue;
                        }
                    }

                }

            } catch (NumberFormatException e) {
                // Print an error message
                outputStr += "Invalid " + letterStr
                + " is not a valid letter.\n";
                continue;
            }

        }

        // Write the output string to output.txt
        outputWriter.write(outputStr);

        // Print the Alphabet wrap results to the console
        System.out.println("Alphabet wrap results written "
        + "to outputAlphabet.txt.");

        // Close the scanner
        scanner.close();

        // Close the output writer
        outputWriter.close();
    }

    /**
     * This method is the recursive function for alphabet wrap.
     *
     * @param letter the letter to wrap
     * @param numberOfLetters the number of letters to wrap
     * @return the wrapped letters
     */
    public static String
    recAlphabetWrap(final char letter, final int numberOfLetters) {
        // Set the nextChar to the next letter in the alphabet
        char nextChar = (char) ((letter - 'a' + 1) % 26 + 'a');
        // Check if the letter is empty
        if (numberOfLetters == 0) {
            return "";
        } else {
            // Return the letter and call the method again
            return letter + "\n"
                    + recAlphabetWrap(nextChar, numberOfLetters - 1);
        }
    }
}
