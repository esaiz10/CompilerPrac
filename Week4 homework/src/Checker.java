import java.util.*;

public class Checker {
    static List<String> tokens = new ArrayList<>();
    static String[] keywords = {"if", "else", "while", "do", "for"};
    static String[] symbols = {"(", ")", "<", ">", "=", ";", "+" , "-", "*", "/"};

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ControlChecker \"<control statement>\"");
            return;
        }

        String input = args[0];
        if (!lexicalAnalysis(input)) {
            System.out.println("LEXICAL ERROR: The control statement is not in the correct format");
            return;
        }

        if (!syntaxAnalysis()) {
            System.out.println("SYNTAX ERROR: The control statement is not in the correct format");
            return;
        }

        System.out.println("SUCCESS: The control statement is in the correct format");
    }

    static boolean lexicalAnalysis(String input) {
        tokens.clear();
        StringTokenizer st = new StringTokenizer(input, " ()<>=;+*/-", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.isEmpty()) continue;

            if (isKeyword(token) || isSymbol(token) || isIdentifier(token) || isNumber(token)) {
                tokens.add(token);
            } else {
                return false; // Invalid token
            }
        }
        return true;
    }

    static boolean isKeyword(String token) {
        for (String k : keywords) {
            if (k.equals(token)) return true;
        }
        return false;
    }

    static boolean isSymbol(String token) {
        for (String s : symbols) {
            if (s.equals(token)) return true;
        }
        return false;
    }

    static boolean isIdentifier(String token) {
        return token.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    static boolean isNumber(String token) {
        return token.matches("[0-9]+");
    }

    static boolean syntaxAnalysis() {
        // Simple DFA-style checker based on first token
        if (tokens.isEmpty()) return false;
        String first = tokens.get(0);

        if (first.equals("if")) return checkIfElse(false);
        else if (first.equals("while")) return checkSimpleLoop();
        else if (first.equals("do")) return checkDoWhile();
        else if (first.equals("for")) return checkForLoop();
        else return false;
    }

    static boolean checkIfElse(boolean hasElse) {
        int i = 1;
        if (!tokens.get(i++).equals("(")) return false;
        while (i < tokens.size() && !tokens.get(i).equals(")")) i++; // skip condition
        if (i >= tokens.size() || !tokens.get(i++).equals(")")) return false;

        // assignment
        if (!checkAssignment(i)) return false;
        i += 4;

        if (!hasElse && i < tokens.size() && tokens.get(i).equals("else")) {
            return checkIfElse(true);
        }

        return i == tokens.size();
    }

    static boolean checkSimpleLoop() {
        int i = 1;
        if (!tokens.get(i++).equals("(")) return false;
        while (i < tokens.size() && !tokens.get(i).equals(")")) i++;
        if (i >= tokens.size() || !tokens.get(i++).equals(")")) return false;

        return checkAssignment(i) && tokens.size() == i + 4;
    }

    static boolean checkDoWhile() {
        int i = 1;
        if (!checkAssignment(i)) return false;
        i += 4;
        if (i >= tokens.size() || !tokens.get(i++).equals("while")) return false;
        if (i >= tokens.size() || !tokens.get(i++).equals("(")) return false;
        while (i < tokens.size() && !tokens.get(i).equals(")")) i++;
        if (i >= tokens.size() || !tokens.get(i++).equals(")")) return false;
        return i < tokens.size() && tokens.get(i).equals(";") && tokens.size() == i + 1;
    }

    static boolean checkForLoop() {
        int i = 1;
        if (!tokens.get(i++).equals("(")) return false;
        // init
        if (!checkAssignment(i)) return false;
        i += 4;
        // cond
        while (i < tokens.size() && !tokens.get(i).equals(";")) i++;
        if (i >= tokens.size() || !tokens.get(i++).equals(";")) return false;
        // update
        while (i < tokens.size() && !tokens.get(i).equals(")")) i++;
        if (i >= tokens.size() || !tokens.get(i++).equals(")")) return false;
        return checkAssignment(i) && tokens.size() == i + 4;
    }

    static boolean checkAssignment(int index) {
        if (index + 3 >= tokens.size()) return false;
        return isIdentifier(tokens.get(index)) &&
                tokens.get(index + 1).equals("=") &&
                (isIdentifier(tokens.get(index + 2)) || isNumber(tokens.get(index + 2))) &&
                tokens.get(index + 3).equals(";");
    }
}