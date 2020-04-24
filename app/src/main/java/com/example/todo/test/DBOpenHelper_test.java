package com.example.todo.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper_test extends SQLiteOpenHelper {
    Context context;
    private static final String dataBaseName_test = "note_test";
    private static final int version_test = 1;

    public DBOpenHelper_test(Context context) {
        super(context, dataBaseName_test, null, version_test);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
