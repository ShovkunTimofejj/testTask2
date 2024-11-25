package com.example.thirdTask;

import java.math.BigInteger;

public class FactorialDigitSum {
    public static void main(String[] args) {
        // Calculate the factorial of 100 using BigInteger for handling large numbers
        BigInteger factorial = BigInteger.ONE;  // Initialize the factorial with 1 (by definition of factorial)

        // Loop from 2 to 100 to calculate the factorial
        for (int i = 2; i <= 100; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i)); // Multiply the current factorial by i
        }

        // Convert the calculated factorial to a string and calculate the sum of its digits using Stream API
        int sum = factorial.toString()  // Convert the factorial to a string
                .chars()  // Convert the string to a stream of characters (char)
                .map(Character::getNumericValue)  // Convert each character (digit) to its numeric value (e.g., '3' -> 3)
                .sum();  // Sum all the digits in the stream

        // Output the result to the console
        System.out.println("The sum of the digits in 100! is: " + sum);  // Print the calculated sum of the digits
    }
}

