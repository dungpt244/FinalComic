package com.example.testaplication.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.testaplication.Adapter.AdapterManga;
import com.example.testaplication.Manga.Manga;

import java.util.ArrayList;
import java.util.List;

public class MangaSQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_MANGA = "manga";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_IMAGE = "image";

    private static final String DATABASE_NAME = "app_manga_promax.db";
    private static final int DATABASE_VERSION = 2;

    // Câu lệnh khởi tạo Database.
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MANGA + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " nvarchar(100) not null, "
            + COLUMN_CATEGORY + " nvarchar(100) not null, "
            + COLUMN_IMAGE + " int)";

    public MangaSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void insert_Value(int image, String name, String category) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_MANGA + " WHERE " + COLUMN_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name});

        if (cursor.getCount() == 0) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME, name);
            contentValues.put(COLUMN_CATEGORY, category);
            contentValues.put(COLUMN_IMAGE, image);
            db.insert(TABLE_MANGA, null, contentValues);
        }

        cursor.close();
        db.close();
    }

    public List<Manga> displayCategoryManga(String category) {
        List<Manga> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MANGA + " WHERE " + COLUMN_CATEGORY + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{category});
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String categoryManga = cursor.getString(2);
            int image = cursor.getInt(3);
            list.add(new Manga(name, category, image));
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<AdapterManga> get_data() {
        List<AdapterManga> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MANGA;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE));
            list.add(new AdapterManga(image, name, category));
        }

        cursor.close();
        db.close();
        return list;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LoginSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANGA);
        onCreate(db);
    }
}
