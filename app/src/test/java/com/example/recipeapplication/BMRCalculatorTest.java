package com.example.recipeapplication;

import org.junit.Test;
import static org.junit.Assert.*;

public class BMRCalculatorTest {
    @Test
    public void givenValidInput_whenCalculateBMR_thenReturnCorrectResult() {
        double weight = 70;
        double height = 175;
        double age = 25;
        String gender = "male";

        double expectedBMR = (9.99 * weight) + (6.25 * height) - (4.92 * age) + 5;
        double actualBMR = BMRCalculator.calculateBMR(weight, height, age, gender);

        assertEquals(expectedBMR, actualBMR, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidInput_whenCalculateBMR_thenThrowException() {
        BMRCalculator.calculateBMR(-70, 175, 25, "male");
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidGender_whenCalculateBMR_thenThrowException() {
        BMRCalculator.calculateBMR(70, 175, 25, "unknown");
    }
}

