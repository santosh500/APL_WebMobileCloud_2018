package com.example.santosh.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLLiteDB extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Questions.db";


    public static final String TABLE_NAME = "contact";
    public static final String COLUMN_ID = "contact_id";
    public static final String COLUMN_NAME = "contact_name";
    public static final String COLUMN_CATEGORY = "contact_category";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT, " + COLUMN_CATEGORY + " TEXT )";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SQLLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //fill this method to create the database table
        // use the constants provided above

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void deleteEntries()
    {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void create(String name,String category){
        //fill this method to insert the row

        // Create map of values
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CATEGORY, category);
        // Insert row (returns primary key)
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(TABLE_NAME, null, values);
    }

    public Cursor retrieve(){
        SQLiteDatabase db = getReadableDatabase();
        String[] cols = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_CATEGORY};

        String sortOrder = COLUMN_NAME + " ASC";

        Cursor c = db.query(
                TABLE_NAME,                    // The table to query
                cols,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                sortOrder                                   // The sort order
        );

        return c;
    }

    public void update(int id, String name, String category){

        //fill this method to update the row

        // value map
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CATEGORY, category);

        // Get rowid to update that row
        String whereClause = COLUMN_ID + " = ?";
        String[] args = { String.valueOf(id) };

        //returns #rows updated
        SQLiteDatabase db = getReadableDatabase();
        int count = db.update(
                TABLE_NAME,
                values,
                whereClause,
                args);
    }

}
