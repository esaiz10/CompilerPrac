import java.util.*;

//Chapter 5 Homework

public class Checker {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please give one equation as input.");
            return;
        }

        String equation = args[0];
        List<String> tokens = tokenize(equation);

        if (tokens == null) {
            System.out.println("LEXICAL ERROR: Invalid format");
            return;
        }

        if (!checkSyntax(tokens)) {
            System.out.println("SYNTAX ERROR: Invalid format");
            return;
        }

        System.out.println("SUCCESS: Correct format");
    }

    // This method splits the equation into parts (tokens)
    public static List<String> tokenize(String equation) {
        String[] parts = equation.split(" ");
        List<String> tokens = new ArrayList<>();

        for (String part : parts) {
            if (isLetter(part) || isDigit(part) || isOperator(part) || part.equals("=")) {
                tokens.add(part);
            } else {
                return null;
            }
        }

        return tokens;
    }

    public static boolean checkSyntax(List<String> tokens) {
        boolean needOperand = true;

        for (String token : tokens) {
            if (needOperand) {
                if (isLetter(token) || isDigit(token)) {
                    needOperand = false;
                } else {
                    return false;
                }
            } else {
                if (isOperator(token) || token.equals("=")) {
                    needOperand = true;
                } else {
                    return false;
                }
            }
        }

        // Equation should end with operand, not operator
        return !needOperand;
    }

    public static boolean isLetter(String s) {
        return s.matches("[a-z]");
    }

    public static boolean isDigit(String s) {
        return s.matches("\\d");
    }

    public static boolean isOperator(String s) {
        return s.matches("[+\\-*/]");
    }
}