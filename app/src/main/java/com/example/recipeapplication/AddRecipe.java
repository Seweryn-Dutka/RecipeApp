package com.example.recipeapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddRecipe extends AppCompatActivity {

    // Deklaracje widoków
    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextIngredients;
    private EditText editTextCalories;
    private Button buttonAddRecipe;

    // DAO do zarządzania bazą danych
    RecipeDAO recipeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        // Inicjalizacja widoków
        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextIngredients = findViewById(R.id.editTextIngredients);
        editTextCalories = findViewById(R.id.editTextCalories);
        buttonAddRecipe = findViewById(R.id.buttonAddRecipe);

        // Inicjalizacja DAO
        recipeDAO = new RecipeDAO(this);
        recipeDAO.open();

        // Obsługa kliknięcia przycisku dodawania przepisu
        buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecipe();
            }
        });
    }

    /**
     * Metoda odpowiedzialna za dodanie przepisu do bazy danych.
     */
    private void addRecipe() {
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String ingredients = editTextIngredients.getText().toString().trim();
        String caloriesString = editTextCalories.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description) ||
                TextUtils.isEmpty(ingredients) || TextUtils.isEmpty(caloriesString)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int calories;
        try {
            calories = Integer.parseInt(caloriesString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Calories must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = recipeDAO.addRecipe(name, description, ingredients, calories);

        if (result > 0) {
            Toast.makeText(this, "Recipe added successfully!", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Error adding recipe", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Czyści pola formularza po dodaniu przepisu.
     */
    private void clearFields() {
        editTextName.setText("");
        editTextDescription.setText("");
        editTextIngredients.setText("");
        editTextCalories.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recipeDAO.close();
    }

    // Getter do testów
    public EditText getEditTextName() {
        return editTextName;
    }

    public EditText getEditTextDescription() {
        return editTextDescription;
    }

    public EditText getEditTextIngredients() {
        return editTextIngredients;
    }

    public EditText getEditTextCalories() {
        return editTextCalories;
    }

    public Button getButtonAddRecipe() {
        return buttonAddRecipe;
    }
}
