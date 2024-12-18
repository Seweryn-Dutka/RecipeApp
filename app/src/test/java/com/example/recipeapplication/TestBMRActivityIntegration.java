package com.example.recipeapplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {33})
public class TestBMRActivityIntegration {

    private TestBMRActivity bmrActivity;

    @Before
    public void setUp() {
        // Domyślnie ustawiamy płeć na "male"
        bmrActivity = new TestBMRActivity("male"); // Można zmienić płeć w testach indywidualnie
    }

    @Test
    public void testCalculateBMR_ValidMaleInput_ShouldReturnCorrectValue() {
        bmrActivity = new TestBMRActivity("male"); // Jeśli chcesz zmienić płeć w tym teście na "male"
        double bmr = bmrActivity.calculateBMR("70", "175", "25", "male"); // Dodano parametr płci
        double expectedBMR = (9.99 * 70) + (6.25 * 175) - (4.92 * 25) + 5;
        assertEquals(expectedBMR, bmr, 0.01);
    }

    @Test
    public void testCalculateBMR_ValidFemaleInput_ShouldReturnCorrectValue() {
        bmrActivity = new TestBMRActivity("female"); // Ustawiamy płeć na "female"
        double bmr = bmrActivity.calculateBMR("60", "165", "30", "female"); // Dodano parametr płci
        double expectedBMR = (9.99 * 60) + (6.25 * 165) - (4.92 * 30) - 161;
        assertEquals(expectedBMR, bmr, 0.01);
    }

    @Test
    public void testCalculateBMR_InvalidInput_ShouldThrowException() {
        bmrActivity = new TestBMRActivity("male");
        String weight = "-70";
        String height = "175";
        String age = "25";
        String gender = "male"; // Dodano parametr płci

        assertThrows(IllegalArgumentException.class, () -> bmrActivity.calculateBMR(weight, height, age, gender));
    }

    @Test
    public void testCalculateBMR_InvalidGender_ShouldThrowException() {
        bmrActivity = new TestBMRActivity("invalid_gender"); // Ustawiamy nieprawidłową płeć
        String weight = "70";
        String height = "175";
        String age = "25";
        String gender = "invalid_gender"; // Dodano parametr płci

        assertThrows(IllegalArgumentException.class, () -> bmrActivity.calculateBMR(weight, height, age, gender));
    }
}
