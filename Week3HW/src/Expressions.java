import java.util.*;

public class Expressions {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Expressions \"(expression)\"");
            return;
        }

        String input = args[0].replaceAll("\\s+", "");
        Node parseTree = parseExpression(input);
        System.out.println("Parse Tree: " + input);
        System.out.println("Result: " + evaluate(parseTree));
    }

    // parse expression and create parse tree
    public static Node parseExpression(String expr) {
        if (expr.matches("\\(\\d\\)")) {
            return new Node(Integer.parseInt(expr.substring(1, 2)));
        }

        char operator = expr.charAt(1);
        int splitIndex = findSplitIndex(expr, 3);
        Node left = parseExpression(expr.substring(3, splitIndex));
        Node right = parseExpression(expr.substring(splitIndex, expr.length() - 1));

        return new Node(operator, left, right);
    }

    // split index for left and right sub-expressions
    public static int findSplitIndex(String expr, int start) {
        int count = 0;
        for (int i = start; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') count++;
            if (expr.charAt(i) == ')') count--;
            if (count == 0) return i + 1;
        }
        return -1;
    }

    // evaluate parse tree
    public static int evaluate(Node node) {
        if (node.isLeaf()) {
            return node.value;
        }

        int leftValue = evaluate(node.left);
        int rightValue = evaluate(node.right);

        switch (node.operator) {
            case '+': return leftValue + rightValue;
            case '-': return leftValue - rightValue;
            case '*': return leftValue * rightValue;
            default: throw new IllegalArgumentException("Invalid operator");
        }
    }
}
class Node {
    char operator;
    int value;
    Node left, right;

    // Leaf node for integer
    public Node(int value) {
        this.value = value;
    }

    // Internal node for operator
    public Node(char operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}