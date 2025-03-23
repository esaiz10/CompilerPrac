import java.util.*;

public class Ch4SymTable{

    // List of Java reserved words
    static final Set<String> reservedWords = new HashSet<>(Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
            "class", "const", "continue", "default", "do", "double", "else", "enum",
            "extends", "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "native", "new",
            "package", "private", "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while", "true", "false", "null"
    ));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<Integer>> symbolTable = new LinkedHashMap<>();
        int reservedWordCount = 0;
        int lineNumber = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineNumber++;

            // Split line into words (tokens)
            List<String> tokens = splitLineIntoTokens(line);

            for (String token : tokens) {
                if (reservedWords.contains(token)) {
                    reservedWordCount++;
                } else if (isIdentifier(token)) {
                    if (!symbolTable.containsKey(token)) {
                        symbolTable.put(token, new ArrayList<>());
                    }
                    symbolTable.get(token).add(lineNumber);
                }
            }
        }

        // Print symbol table
        System.out.println("*** SYMBOL TABLE ***");
        for (Map.Entry<String, List<Integer>> entry : symbolTable.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // Print summary
        System.out.println("*** SUMMARY ***");
        System.out.println("Total Number of Identifiers (No reserved words): " + symbolTable.size());
        System.out.println("Total Number of Java Reserved Words: " + reservedWordCount);
    }

    // Simple method to split a line into tokens (only letters, digits, underscores)
    public static List<String> splitLineIntoTokens(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (Character.isLetterOrDigit(ch) || ch == '_') {
                token.append(ch);
            } else {
                if (token.length() > 0) {
                    tokens.add(token.toString());
                    token.setLength(0);
                }
            }
        }

        if (token.length() > 0) {
            tokens.add(token.toString());
        }

        return tokens;
    }

    // Check if a token is a valid identifier (not starting with digit, not reserved word)
    public static boolean isIdentifier(String token) {
        if (token.length() == 0) return false;
        if (!Character.isLetter(token.charAt(0))) return false;
        for (int i = 1; i < token.length(); i++) {
            char ch = token.charAt(i);
            if (!Character.isLetterOrDigit(ch) && ch != '_') return false;
        }
        return !reservedWords.contains(token);
    }
}
