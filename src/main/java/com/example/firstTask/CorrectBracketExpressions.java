package com.example.firstTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CorrectBracketExpressions {

    public static void main(String[] args) {

        // Using BufferedReader to read input from the console
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            // Infinite loop to take user input
            while (true) {
                // Prompting the user to enter N
                System.out.print("Enter N (or type 'stop' to exit): ");
                String input = br.readLine(); // Reading the input

                // Exit condition if the user types 'stop'
                if (input.equalsIgnoreCase("stop")) {
                    System.out.println("Program terminated.");
                    break; // Exit the loop and terminate the program
                }

                // Trying to convert the input into an integer
                try {
                    int N = Integer.parseInt(input); // Convert the string to an integer

                    // If the number is negative, display an error and continue the input
                    if (N < 0) {
                        System.out.println("N must be non-negative!");
                        continue; // Proceed to the next iteration of the loop
                    }

                    // Generate all possible bracket combinations of length 2*N
                    List<String> combinations = generateAllCombinations(N);
                    int correctCount = 0; // Counter for valid expressions
                    System.out.println("Valid bracket expressions:");

                    // Loop through all generated combinations and display only valid ones
                    for (String combo : combinations) {
                        if (isValid(combo)) { // Check if the expression is valid
                            correctCount++; // Increment the counter for valid expressions
                            System.out.println(combo); // Print the valid expression
                        }
                    }

                    // Display the total count of valid expressions
                    System.out.println("Number of valid bracket expressions: " + correctCount);

                } catch (NumberFormatException e) {
                    // If the input is invalid (not a number), display an error
                    System.out.println("Invalid input! Please enter an integer or 'stop'.");
                }
            }
        } catch (Exception e) {
            // If there is an error reading from the console, print an error message
            System.out.println("Invalid input! Please enter an integer or 'stop'.");
        }
    }

    // Generate all possible combinations of brackets of length 2*N
    public static List<String> generateAllCombinations(int N) {
        List<String> result = new ArrayList<>();
        // Recursively generate all combinations
        generateAllHelper(result, "", 0, 0, N);
        return result;
    }

    // Recursive helper function to generate all valid bracket combinations
    private static void generateAllHelper(List<String> result, String current, int open, int close, int max) {
        // If the length of the current string is 2*N, add it to the result
        if (current.length() == max * 2) {
            result.add(current); // Add the completed string to the result
            return; // Return as the string is fully formed
        }

        // If the number of opening brackets is less than N, add an opening bracket
        if (open < max) {
            generateAllHelper(result, current + "(", open + 1, close, max);
        }

        // If the number of closing brackets is less than the number of opening ones, add a closing bracket
        if (close < max) {
            generateAllHelper(result, current + ")", open, close + 1, max);
        }
    }

    // Check if the expression is valid
    public static boolean isValid(String expression) {
        int balance = 0; // Variable to track the balance of opening and closing brackets
        // Loop through each character in the string
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                balance++; // If it's an opening bracket, increase the balance
            } else if (c == ')') {
                balance--; // If it's a closing bracket, decrease the balance
            }
            // If the balance becomes negative, it means there are more closing than opening brackets
            if (balance < 0) {
                return false; // The expression is invalid
            }
        }
        // If the balance is 0, the number of opening and closing brackets is equal
        return balance == 0; // Return true if the expression is valid
    }
}


