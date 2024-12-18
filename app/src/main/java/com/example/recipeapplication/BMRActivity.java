package com.example.recipeapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMRActivity extends AppCompatActivity {

    private EditText etHeight, etWeight, etAge;
    private CheckBox cbMale, cbFemale;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        // Inicjalizacja widoków
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        etAge = findViewById(R.id.et_age);
        cbMale = findViewById(R.id.cb_male);
        cbFemale = findViewById(R.id.cb_female);
        tvResult = findViewById(R.id.tv_result);

        // Przycisk oblicz
        Button btnCalculate = findViewById(R.id.btn_calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pobieranie danych wejściowych
                String weight = etWeight.getText().toString();
                String height = etHeight.getText().toString();
                String age = etAge.getText().toString();

                // Sprawdzanie, czy wszystkie pola są wypełnione
                if (weight.isEmpty() || height.isEmpty() || age.isEmpty()) {
                    Toast.makeText(BMRActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Sprawdzanie, która płeć jest wybrana
                String gender = getSelectedGender();
                if (gender == null) {
                    Toast.makeText(BMRActivity.this, "Proszę wybrać płeć!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Obliczanie BMR
                    double bmr = calculateBMR(weight, height, age, gender);

                    // Wyświetlanie wyniku
                    tvResult.setText("Twoje BMR: " + String.format("%.2f", bmr));

                } catch (IllegalArgumentException e) {
                    // Obsługa błędów, np. nieprawidłowe dane
                    Toast.makeText(BMRActivity.this, "Błąd obliczania BMR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Metoda do obliczania BMR na podstawie danych wejściowych (waga, wysokość, wiek i płeć).
     * @param weight Waga użytkownika
     * @param height Wysokość użytkownika
     * @param age Wiek użytkownika
     * @param gender Płeć użytkownika
     * @return Obliczone BMR
     */
    public double calculateBMR(String weight, String height, String age, String gender) {
        try {
            double w = Double.parseDouble(weight);
            double h = Double.parseDouble(height);
            double a = Double.parseDouble(age);

            // Obliczanie BMR przy użyciu klasy BMRCalculator
            return BMRCalculator.calculateBMR(w, h, a, gender);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Wprowadź prawidłowe liczby!");
        }
    }

    /**
     * Ta metoda zwraca płeć użytkownika na podstawie wybranych checkboxów.
     * @return Płeć użytkownika ("male" lub "female")
     */
    protected String getSelectedGender() {
        if (cbMale.isChecked()) {
            return "male";
        } else if (cbFemale.isChecked()) {
            return "female";
        }
        return null;  // Brak wybranej płci
    }
}
