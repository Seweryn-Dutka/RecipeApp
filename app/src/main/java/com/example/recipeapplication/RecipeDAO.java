package com.example.recipeapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    private SQLiteDatabase database;
    private RecipeDatabaseHelper dbHelper;

    public RecipeDAO(Context context) {
        dbHelper = new RecipeDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addRecipe(String name, String description, String ingredients, int calories) {
        ContentValues values = new ContentValues();
        values.put(RecipeDatabaseHelper.COLUMN_NAME, name);
        values.put(RecipeDatabaseHelper.COLUMN_DESCRIPTION, description);
        values.put(RecipeDatabaseHelper.COLUMN_INGREDIENTS, ingredients);
        values.put(RecipeDatabaseHelper.COLUMN_CALORIES, calories);

        return database.insert(RecipeDatabaseHelper.TABLE_RECIPES, null, values);
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        String[] columns = {
                RecipeDatabaseHelper.COLUMN_ID,
                RecipeDatabaseHelper.COLUMN_NAME,
                RecipeDatabaseHelper.COLUMN_DESCRIPTION,
                RecipeDatabaseHelper.COLUMN_INGREDIENTS,
                RecipeDatabaseHelper.COLUMN_CALORIES
        };

        Cursor cursor = database.query(RecipeDatabaseHelper.TABLE_RECIPES, columns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_DESCRIPTION));
                String ingredients = cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_INGREDIENTS));
                int calories = cursor.getInt(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_CALORIES));

                recipes.add(new Recipe(id, name, description, ingredients, calories));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return recipes;
    }

    public Recipe getRecipeById(int id) {
        Recipe recipe = null;

        String[] columns = {
                RecipeDatabaseHelper.COLUMN_ID,
                RecipeDatabaseHelper.COLUMN_NAME,
                RecipeDatabaseHelper.COLUMN_DESCRIPTION,
                RecipeDatabaseHelper.COLUMN_INGREDIENTS,
                RecipeDatabaseHelper.COLUMN_CALORIES
        };

        String selection = RecipeDatabaseHelper.COLUMN_ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = database.query(RecipeDatabaseHelper.TABLE_RECIPES, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_DESCRIPTION));
            String ingredients = cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_INGREDIENTS));
            int calories = cursor.getInt(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_CALORIES));

            recipe = new Recipe(id, name, description, ingredients, calories);
            cursor.close();
        }

        return recipe;
    }

    public Recipe getRecipeByName(String name) {
        Recipe recipe = null;

        String[] columns = {
                RecipeDatabaseHelper.COLUMN_ID,
                RecipeDatabaseHelper.COLUMN_NAME,
                RecipeDatabaseHelper.COLUMN_DESCRIPTION,
                RecipeDatabaseHelper.COLUMN_INGREDIENTS,
                RecipeDatabaseHelper.COLUMN_CALORIES
        };

        String selection = RecipeDatabaseHelper.COLUMN_NAME + "=?";
        String[] selectionArgs = {name};

        Cursor cursor = database.query(RecipeDatabaseHelper.TABLE_RECIPES, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_ID));
            String description = cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_DESCRIPTION));
            String ingredients = cursor.getString(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_INGREDIENTS));
            int calories = cursor.getInt(cursor.getColumnIndex(RecipeDatabaseHelper.COLUMN_CALORIES));

            recipe = new Recipe(id, name, description, ingredients, calories);
            cursor.close();
        }

        return recipe;
    }

    public boolean updateRecipe(int id, String name, String description, String ingredients, int calories) {
        ContentValues values = new ContentValues();
        values.put(RecipeDatabaseHelper.COLUMN_NAME, name);
        values.put(RecipeDatabaseHelper.COLUMN_DESCRIPTION, description);
        values.put(RecipeDatabaseHelper.COLUMN_INGREDIENTS, ingredients);
        values.put(RecipeDatabaseHelper.COLUMN_CALORIES, calories);

        String whereClause = RecipeDatabaseHelper.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};

        int rowsUpdated = database.update(RecipeDatabaseHelper.TABLE_RECIPES, values, whereClause, whereArgs);

        return rowsUpdated > 0;
    }

    public void clearAllRecipes() {
        database.delete(RecipeDatabaseHelper.TABLE_RECIPES, null, null);
    }
}
