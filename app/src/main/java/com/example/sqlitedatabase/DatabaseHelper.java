package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABAE_NAME = "Patients.db";
    private static final String TABLE_NAME = "registerClient";
    private static final String TABLE_ID = "ID";
    private static final String USER_NAME = "username";
    private static final String PASSWord = "password";
    private static final String Confirm_pass = "Conf_Pass";
    private static final String COL_1 = "age";

    public DatabaseHelper(Context context) {
        super(context, DATABAE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT,Conf_pass TEXT,age INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP  Table IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String username, String password, String Conf_Pass, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, username);
        contentValues.put(PASSWord, password);
        contentValues.put(Confirm_pass, Conf_Pass);
        contentValues.put(COL_1, age);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;

        else

            return true;


    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return res;


    }

    public boolean Updatedata(String id, String username, String password, String Conf_Pass, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ID, id);
        contentValues.put(USER_NAME, username);
        contentValues.put(PASSWord, password);
        contentValues.put(Confirm_pass, Conf_Pass);
        contentValues.put(COL_1, age);
        db.update(TABLE_NAME, contentValues, "ID= ?", new String[]{id});
        return true;
    }

    public Integer DeleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID= ?", new String[]{id});

    }
}
