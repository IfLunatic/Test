import java.util.Scanner;


public class CheckEquations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the math equation");
        String equation = scanner.nextLine();

        //Check block
        Check check = new Check();

        if (!check.regexCheckedX(equation)) {
            System.out.println("Your equation have another symbols");
            System.exit(0);
        }
        if (!equation.contains("x")) {
            System.out.println("You entered an equation without X");
            System.exit(0);
        }
        if (!check.checkBrackets(equation)) {
            System.out.println("You entered an equation with incorrect brackets");
            System.exit(0); // exit the program
        }
        if (!check.checkOperators(equation)) {
            System.out.println("You have some problems with operators in ur equation");
            System.exit(0);
        }
        //And check block
        System.out.println("Your equation is valid");

        //- Count the number of numbers in the equation entered by the user.
        System.out.println("<-------------------------------------->");
        System.out.println("Count the number of numbers in the equation entered by the user.");
        System.out.println(check.countDigit(equation));
    }
}

class Check {
    public static boolean checkBrackets(String equation) {
        int openBrackets = 0, closedBrackets = 0;
        for (char c : equation.toCharArray()) {
            if (c == '(') {
                openBrackets++;
            }
            if (c == ')') {
                closedBrackets++;
            }
        }
        return openBrackets == closedBrackets;
    }
    public static boolean checkOperators(String equation) {
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);

            if (c == '*') {
                if (equation.charAt(i+1) == '-') {
                    return true;
                }
            } else if (c == '+' || c == '-' || c == '/') {
                if (equation.charAt(i+1) == '+' || equation.charAt(i+1) == '-' || equation.charAt(i+1) == '/' || equation.charAt(i+1) == '*') {
                    return false;
                }
            }
        }
        return true;
    }
    public static int countDigit (String equation) {
        int count = 0;

        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (Character.isDigit(c)) {
                count++;
            }
            if (i + 1 < equation.length() && Character.isDigit(c) && Character.isDigit(equation.charAt(i + 1))) {
                count--;
            }
            if (i + 1 < equation.length() && Character.isDigit(c) && equation.charAt(i+1) == '.' && Character.isDigit(equation.charAt(i+2))) {
                count--;
            }
        }
        return count;
    }

    public static boolean regexCheckedX (String equation){
        String regex = "^[xхXХ0-9+\\-*/=()\\s.]+$";
        return equation.matches(regex);
    }
}