package com.example.recipeapplication;

public class BMRCalculator {
    public static double calculateBMR(double weight, double height, double age, String gender) {
        if (weight <= 0 || height <= 0 || age <= 0 || gender == null || gender.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (gender.equalsIgnoreCase("male")) {
            return (9.99 * weight) + (6.25 * height) - (4.92 * age) + 5;
        } else if (gender.equalsIgnoreCase("female")) {
            return (9.99 * weight) + (6.25 * height) - (4.92 * age) - 161;
        } else {
            throw new IllegalArgumentException("Invalid gender");
        }
    }
}

