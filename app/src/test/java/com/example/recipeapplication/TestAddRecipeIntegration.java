package com.example.recipeapplication;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(org.robolectric.RobolectricTestRunner.class)
@Config(sdk = {33})
public class TestAddRecipeIntegration {

    private AddRecipe addRecipe;
    private RecipeDAO recipeDAO;
    private Context context;

    @Before
    public void setUp() {
        // Pobieramy kontekst aplikacji
        context = ApplicationProvider.getApplicationContext();

        // Inicjalizacja DAO
        recipeDAO = new RecipeDAO(context);
        recipeDAO.open();

        // Tworzymy aktywność AddRecipe
        addRecipe = Robolectric.setupActivity(AddRecipe.class);
        addRecipe.recipeDAO = recipeDAO; // Ustawienie DAO w aktywności
    }

    @After
    public void tearDown() {
        // Czyścimy bazę danych po każdym teście
        recipeDAO.clearAllRecipes();
        recipeDAO.close();
    }

    @Test
    public void testAddRecipe_ValidInput_ShouldSaveToDatabase() {
        // Symulacja wprowadzenia danych
        addRecipe.getEditTextName().setText("Pancakes");
        addRecipe.getEditTextDescription().setText("Delicious pancakes with syrup");
        addRecipe.getEditTextIngredients().setText("Flour, Eggs, Milk, Sugar");
        addRecipe.getEditTextCalories().setText("500");

        // Kliknięcie przycisku dodawania przepisu
        addRecipe.getButtonAddRecipe().performClick();

        // Pobranie przepisów z bazy danych
        Recipe addedRecipe = recipeDAO.getRecipeByName("Pancakes");

        // Sprawdzanie, czy przepis został dodany do bazy danych
        assertNotNull(addedRecipe);
        assertEquals("Pancakes", addedRecipe.getName());
        assertEquals("Delicious pancakes with syrup", addedRecipe.getDescription());
        assertEquals("Flour, Eggs, Milk, Sugar", addedRecipe.getIngredients());
        assertEquals(500, addedRecipe.getCalories());
    }

    @Test
    public void testAddRecipe_InvalidInput_ShouldNotSaveToDatabase() {
        // Symulacja wprowadzenia niepełnych danych
        addRecipe.getEditTextName().setText("");
        addRecipe.getEditTextDescription().setText("");
        addRecipe.getEditTextIngredients().setText("");
        addRecipe.getEditTextCalories().setText("");

        // Kliknięcie przycisku dodawania przepisu
        addRecipe.getButtonAddRecipe().performClick();

        // Pobranie wszystkich przepisów z bazy danych
        int recipeCount = recipeDAO.getAllRecipes().size();

        // Sprawdzanie, że nie dodano przepisu
        assertEquals(0, recipeCount);
    }

    @Test
    public void testAddRecipe_NonNumericCalories_ShouldNotSaveToDatabase() {
        // Symulacja wprowadzenia błędnych danych
        addRecipe.getEditTextName().setText("Cake");
        addRecipe.getEditTextDescription().setText("A sweet cake");
        addRecipe.getEditTextIngredients().setText("Flour, Sugar, Eggs");
        addRecipe.getEditTextCalories().setText("NotANumber");

        // Kliknięcie przycisku dodawania przepisu
        addRecipe.getButtonAddRecipe().performClick();

        // Pobranie wszystkich przepisów z bazy danych
        int recipeCount = recipeDAO.getAllRecipes().size();

        // Sprawdzanie, że nie dodano przepisu
        assertEquals(0, recipeCount);
    }
}
