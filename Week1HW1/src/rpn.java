import java.util.Stack;

public class rpn {

    public static int postFixCal(String input) {
        Stack<Integer> stack = new Stack<>();
        if (input == null || input.trim().isEmpty()) {
            throw new ArithmeticException("Input cannot be null or empty");
        }
        String[] elements = input.trim().split("\\s+");

        for (String element : elements) {
            if ("+-*/".contains(element)) {
                // Check if there are at least two operands on the stack before popping
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid RPN expression: Not enough operands before operator '" + element + "'");
                }

                Integer op1;
                Integer op2;

                op1 = stack.pop();
                op2 = stack.pop();
                switch (element) {
                    case "+":
                        stack.push(op1 + op2);
                        break;
                    case "-":
                        stack.push(op2 - op1);
                        break;
                    case "*":
                        stack.push(op1 * op2);
                        break;
                    case "/":
                        stack.push(op2 / op1);
                        break;
                }
            } else {
                try {
                    stack.push(Integer.parseInt(element));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid RPN expression: Invalid operand '" + element + "'");
                }
            }
        }

        return stack.peek();
    }

    public static void main(String[] args) {
        System.out.println(postFixCal(" 23 145 + 75 8 / * "));
    }
}






