// Sentence.java
import java.util.Random;

public class Sentence {
    // Private attributes
    private String subjectMarker;
    private String adjective;
    private String subject;
    private String adverb;
    private String verb;
    private String directObjectMarker;
    private String directObject;

    private String[] sentenceTable; // Array to store the attributes in the specified order

    // Constructor
    public Sentence(String input) {
        sentenceTable = new String[7]; // Initialize sentenceTable with 7 slots
        parse(input); // Parse the input sentence
    }

    // The parse method
    public void parse(String input) {
        StringBuilder buffer = new StringBuilder();
        int index = 0; // To track the current attribute position (0-6)

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == ' ' || i == input.length() - 1) {
                // If it's the end of a word, or the last character in the input
                if (i == input.length() - 1 && currentChar != ' ') {
                    buffer.append(currentChar);
                }

                // Assign the attribute based on the word's position
                String word = buffer.toString().trim();
                switch (index) {
                    case 0:
                        subjectMarker = word;
                        break;
                    case 1:
                        adjective = word;
                        break;
                    case 2:
                        subject = word;
                        break;
                    case 3:
                        adverb = word;
                        break;
                    case 4:
                        verb = word;
                        break;
                    case 5:
                        directObjectMarker = word;
                        break;
                    case 6:
                        directObject = word;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected position in sentence parsing");
                }

                buffer.setLength(0); // Clear the buffer for the next word
                index++;
            } else {
                buffer.append(currentChar); // Append character to buffer
            }
        }
    }

    // The toString method: Combines attributes into a full sentence
    public String toString() {
        return String.join(" ",
                subjectMarker, adjective, subject, adverb, verb, directObjectMarker, directObject);
    }

    // The shuffle method: Shuffles the attributes in sentenceTable
    public void shuffle() {
        if (sentenceTable == null) {
            sentenceTable = new String[7];
        }

        // Load the attributes into sentenceTable
        sentenceTable[0] = subjectMarker;
        sentenceTable[1] = adjective;
        sentenceTable[2] = subject;
        sentenceTable[3] = adverb;
        sentenceTable[4] = verb;
        sentenceTable[5] = directObjectMarker;
        sentenceTable[6] = directObject;

        // Random shuffle of sentenceTable
        Random random = new Random();
        for (int i = sentenceTable.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap elements i and j
            String temp = sentenceTable[i];
            sentenceTable[i] = sentenceTable[j];
            sentenceTable[j] = temp;
        }
    }

    // The getSentenceTableContents method: Returns the string representation of sentenceTable
    public String getSentenceTableContents() {
        if (sentenceTable == null) {
            return "Sentence table is empty!";
        }
        return String.join(" ", sentenceTable);
    }
}