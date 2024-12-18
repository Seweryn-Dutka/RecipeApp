package com.example.recipeapplication;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(org.robolectric.RobolectricTestRunner.class)
public class TestAddRecipe {

    private AddRecipe addRecipe;

    @Mock
    private RecipeDAO mockRecipeDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Tworzymy instancję aktywności przy użyciu Robolectric
        addRecipe = Robolectric.setupActivity(AddRecipe.class);

        // Ustawienie mocków w aktywności
        addRecipe.recipeDAO = mockRecipeDAO;
    }

    @Test
    public void testAddRecipe_ValidInput_ShouldAddRecipe() {
        // Przygotowanie widoków w aktywności (editText)
        EditText editTextName = addRecipe.findViewById(R.id.editTextName);
        EditText editTextDescription = addRecipe.findViewById(R.id.editTextDescription);
        EditText editTextIngredients = addRecipe.findViewById(R.id.editTextIngredients);
        EditText editTextCalories = addRecipe.findViewById(R.id.editTextCalories);

        // Wprowadzenie danych do pól tekstowych
        editTextName.setText("Pizza");
        editTextDescription.setText("Delicious pizza with cheese");
        editTextIngredients.setText("Cheese, Tomato, Dough");
        editTextCalories.setText("800");

        // Symulowanie kliknięcia przycisku
        Button buttonAddRecipe = addRecipe.findViewById(R.id.buttonAddRecipe);
        buttonAddRecipe.performClick();

        // Weryfikacja, że metoda addRecipe została wywołana z poprawnymi argumentami
        verify(mockRecipeDAO).addRecipe("Pizza", "Delicious pizza with cheese", "Cheese, Tomato, Dough", 800);
    }

    @Test
    public void testAddRecipe_InvalidInput_ShouldNotAddRecipe() {
        // Przygotowanie widoków w aktywności (editText)
        EditText editTextName = addRecipe.findViewById(R.id.editTextName);
        EditText editTextDescription = addRecipe.findViewById(R.id.editTextDescription);
        EditText editTextIngredients = addRecipe.findViewById(R.id.editTextIngredients);
        EditText editTextCalories = addRecipe.findViewById(R.id.editTextCalories);

        // Wprowadzenie pustych danych do pól tekstowych
        editTextName.setText("");
        editTextDescription.setText("");
        editTextIngredients.setText("");
        editTextCalories.setText("");

        // Symulowanie kliknięcia przycisku
        Button buttonAddRecipe = addRecipe.findViewById(R.id.buttonAddRecipe);
        buttonAddRecipe.performClick();

        // Weryfikacja, że metoda addRecipe nie została wywołana
        verify(mockRecipeDAO, never()).addRecipe(anyString(), anyString(), anyString(), anyInt());
    }

    @Test
    public void testAddRecipe_NonNumericCalories_ShouldHandleError() {
        // Przygotowanie widoków w aktywności (editText)
        EditText editTextName = addRecipe.findViewById(R.id.editTextName);
        EditText editTextDescription = addRecipe.findViewById(R.id.editTextDescription);
        EditText editTextIngredients = addRecipe.findViewById(R.id.editTextIngredients);
        EditText editTextCalories = addRecipe.findViewById(R.id.editTextCalories);

        // Wprowadzenie niepoprawnej liczby kalorii
        editTextName.setText("Pizza");
        editTextDescription.setText("Delicious pizza with cheese");
        editTextIngredients.setText("Cheese, Tomato, Dough");
        editTextCalories.setText("NotANumber");

        // Symulowanie kliknięcia przycisku
        Button buttonAddRecipe = addRecipe.findViewById(R.id.buttonAddRecipe);
        buttonAddRecipe.performClick();

        // Weryfikacja, że metoda addRecipe nie została wywołana
        verify(mockRecipeDAO, never()).addRecipe(anyString(), anyString(), anyString(), anyInt());
    }
}
