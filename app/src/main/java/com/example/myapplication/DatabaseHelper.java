package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cinefast.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SNACKS = "snacks";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_PRICE = "price";
    private static final String COL_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_SNACKS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_PRICE + " INTEGER, " +
                COL_IMAGE + " INTEGER)";
        db.execSQL(createTableQuery);
        insertInitialSnacks(db);
    }

    private void insertInitialSnacks(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(COL_NAME, "Popcorn");
        cv.put(COL_PRICE, 8);
        cv.put(COL_IMAGE, R.drawable.snack1);
        db.insert(TABLE_SNACKS, null, cv);

        cv.put(COL_NAME, "Nachos");
        cv.put(COL_PRICE, 7);
        cv.put(COL_IMAGE, R.drawable.snack2);
        db.insert(TABLE_SNACKS, null, cv);

        cv.put(COL_NAME, "Soft Drink");
        cv.put(COL_PRICE, 5);
        cv.put(COL_IMAGE, R.drawable.snack3);
        db.insert(TABLE_SNACKS, null, cv);

        cv.put(COL_NAME, "Candy Mix");
        cv.put(COL_PRICE, 6);
        cv.put(COL_IMAGE, R.drawable.snack4);
        db.insert(TABLE_SNACKS, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SNACKS);
        onCreate(db);
    }
    public ArrayList<Snack> getAllSnacks() {
        ArrayList<Snack> snackList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SNACKS, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int image = cursor.getInt(cursor.getColumnIndexOrThrow(COL_IMAGE));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                int price = cursor.getInt(cursor.getColumnIndexOrThrow(COL_PRICE));

                snackList.add(new Snack(image, name, price));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return snackList;
    }
}