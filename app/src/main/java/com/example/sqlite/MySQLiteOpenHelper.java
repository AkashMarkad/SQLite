package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RIT.db";
    private static int DATABASE_VERSION = 1;
    private static String TABLE_NAME = "USER";
    private static String Col1_NAME = "EMAIL";
    private static String Col2_NAME = "PASSWORD";


    public MySQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("Create table '"+TABLE_NAME+"' ( '"+Col1_NAME+"' int , '"+Col2_NAME+"')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public void insertRecord(String email , String Password)
    {
        SQLiteDatabase sqLiteDatabase =  getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1_NAME, email);
        contentValues.put(Col2_NAME , Password);
        sqLiteDatabase.insert(TABLE_NAME , null, contentValues);
    }

    public Cursor selectRecords()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from  "+ TABLE_NAME, null);
        return cursor;
    }

    public void deleteRecord(String email)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME , Col1_NAME + "= ?", new String[]{email});

    }

    public void updateRecord(String email , String password)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1_NAME, email);
        contentValues.put(Col2_NAME , password);
        sqLiteDatabase.update(TABLE_NAME , contentValues , Col1_NAME + "= ?", new String[]{email});
    }
}
