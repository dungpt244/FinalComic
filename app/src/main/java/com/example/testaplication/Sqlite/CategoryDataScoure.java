package com.example.testaplication.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.testaplication.Account.Account;
import com.example.testaplication.Manga.Category;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CategoryDataScoure {
    private SQLiteDatabase database;
    private CategorySQLiteHelper dbHelper;
    private String[] allColumns = {
            CategorySQLiteHelper.COLUMN_ID,
            CategorySQLiteHelper.COLUMN_NAME,
            CategorySQLiteHelper.COLUMN_DESCRIPTION
    };

    public CategoryDataScoure(Context context) {
        dbHelper = new CategorySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();

        Cursor cursor = database.query(CategorySQLiteHelper.TABLE_CATEGORY,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            categories.add(category);
            cursor.moveToNext();
        }
        // Nhớ đóng con trỏ lại nhé.
        cursor.close();
        return categories;
    }

    public boolean insert(String name, String description) {
        ContentValues values = new ContentValues();
        values.put(CategorySQLiteHelper.COLUMN_NAME, name);
        values.put(CategorySQLiteHelper.COLUMN_DESCRIPTION, description);

        long newRowId = database.insert(CategorySQLiteHelper.TABLE_CATEGORY, null, values);
        return newRowId != -1;
    }

    private Category cursorToCategory(Cursor cursor) {
        Category category = new Category();
        category.setName(cursor.getString(1));
        category.setDescription(cursor.getString(2));
        return category;
    }
}