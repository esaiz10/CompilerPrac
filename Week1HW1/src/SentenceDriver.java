// SentenceDriver.java
import java.util.Scanner;

public class SentenceDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sentence sentence = null; // The Sentence object
        boolean exit = false;

        // Menu
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("(1) Enter a sentence");
            System.out.println("(2) Shuffle the sentence");
            System.out.println("(3) Display the original sentence");
            System.out.println("(4) Display the sentence table contents");
            System.out.println("(5) Exit the program");
            System.out.print("Enter a menu option (0 for menu): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (option) {
                case 0:
                    break; // Redisplay menu
                case 1:
                    System.out.print("Enter a valid sentence: ");
                    String inputSentence = scanner.nextLine();
                    sentence = new Sentence(inputSentence);
                    System.out.println("Sentence saved!");
                    break;
                case 2:
                    if (sentence == null) {
                        System.out.println("Error: No sentence entered yet!");
                    } else {
                        sentence.shuffle();
                        System.out.println("Sentence shuffled!");
                    }
                    break;
                case 3:
                    if (sentence == null) {
                        System.out.println("Error: No sentence entered yet!");
                    } else {
                        System.out.println("Original sentence: " + sentence.toString());
                    }
                    break;
                case 4:
                    if (sentence == null) {
                        System.out.println("Error: No sentence entered yet!");
                    } else {
                        System.out.println("Sentence table contents: " + sentence.getSentenceTableContents());
                    }
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        scanner.close();
    }
}