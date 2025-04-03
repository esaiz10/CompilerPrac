import java.util.*;

public class Declarations {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> types = new ArrayList<String>();
        types.add("int");
        types.add("double");
        types.add("boolean");
        types.add("char");
        types.add("String");
        types.add("float");
        types.add("long");
        types.add("short");
        types.add("byte");

        int lineNumber = 0;

        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            lineNumber++;

            if (line.startsWith("for")) {
                continue; // ignore variables in for loops
            }

            for (String type : types) {
                if (line.startsWith(type + " ") || line.startsWith(type + "[]")) {
                    line = line.replace(";", "");
                    String[] parts = line.split("=", 2);
                    String[] declaration = parts[0].split(" ");

                    String varType = declaration[0];
                    String varName = declaration[1].replace("[]", "");
                    String value;

                    if (parts.length > 1) {
                        value = parts[1].trim();
                        if (value.startsWith("new")) {
                            value = "object instance";
                        }
                    } else {
                        if (varType.equals("int") || varType.equals("double") ||
                                varType.equals("float") || varType.equals("long") ||
                                varType.equals("short") || varType.equals("byte")) {
                            value = "0";
                        } else if (varType.equals("boolean")) {
                            value = "false";
                        } else if (varType.equals("char")) {
                            value = "null";
                        } else {
                            value = "null";
                        }
                    }

                    System.out.println("[" + varName + "] Line: " + lineNumber +
                            ", Type: " + varType + ", Value: " + value);
                }
            }
        }
        input.close();
    }
}
