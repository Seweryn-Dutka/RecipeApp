package com.example.recipeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.recipeapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;
    private RecipeDAO recipeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();
        recipeDAO = new RecipeDAO(this);

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            loadRecipes();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnBMR = findViewById(R.id.btn_bmr);

        btnBMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BMRActivity.class);
                startActivity(intent);
            }
        });

        Button addRecipe = findViewById(R.id.btn_add_recipe);

        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRecipe.class);
                startActivity(intent);
            }
        });


    }

    private void loadRecipes() {
        recipeDAO.open();
        List<Recipe> recipes = recipeDAO.getAllRecipes();
        StringBuilder displayText = new StringBuilder();

        if (recipes.isEmpty()) {
            displayText.append("No recipes found.");
        } else {
            for (Recipe recipe : recipes) {
                displayText.append("Name: ").append(recipe.getName()).append("\n")
                        .append("Description: ").append(recipe.getDescription()).append("\n")
                        .append("Ingredients: ").append(recipe.getIngredients()).append("\n")
                        .append("Calories: ").append(recipe.getCalories()).append("\n\n");
            }
        }

        textView.setText(displayText.toString());
        recipeDAO.close();
    }
}