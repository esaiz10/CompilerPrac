import java.io.*;
import java.util.regex.*;

public class CodeStream  {
    public static void main(String[] args) {
        // Input and output file paths for Java and Pascal source files
        String inputJavaFile = "TestJava.java"; // Replace with your Java source file path
        String outputJavaFile = "CleansedJavaFile.java";
        String inputPascalFile = "OriginalPascalFile.pas"; // Replace with your Pascal source file path
        String outputPascalFile = "CleansedPascalFile.pas";

        try {
            // Removing comments from Java file
            removeComments(inputJavaFile, outputJavaFile);
            System.out.println("Cleansed Java file has been saved to: " + outputJavaFile);

            // Removing comments from Pascal file
            removeComments(inputPascalFile, outputPascalFile);
            System.out.println("Cleansed Pascal file has been saved to: " + outputPascalFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the file, removes comments (using regex), and saves the result to another file.
     *
     * @param inputFile  Path to the original source file (Java or Pascal)
     * @param outputFile Path to the cleansed source file
     * @throws IOException If an error occurs while reading or writing the file
     */
    public static void removeComments(String inputFile, String outputFile) throws IOException {
        // Read the file contents into a String
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        }

        // Remove all comments using regex
        String cleansedContent = removeCommentsFromCode(fileContent.toString());

        // Write the cleansed content to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(cleansedContent);
        }
    }

    /**
     * Removes comments from source code using regular expressions.
     *
     * @param code Original source code
     * @return Cleansed source code without comments
     */
    public static String removeCommentsFromCode(String code) {
        // Regex for block comments: /* ... */
        // Regex for line comments: //
        String regex = "(?s)/\\*.*?\\*/|//.*(?=\n)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);
        return matcher.replaceAll(""); // Replace matched comments with empty string
    }
}