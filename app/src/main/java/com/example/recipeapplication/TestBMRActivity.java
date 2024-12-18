package com.example.recipeapplication;

import android.os.Bundle;

public class TestBMRActivity extends BMRActivity {

    private String selectedGender;

    // Konstruktor, który pozwala ustawić płeć w teście
    public TestBMRActivity(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    @Override
    protected String getSelectedGender() {
        return selectedGender;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

