package com.example.todo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.todo.model.NoteModel;

import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBOpenHelper";
    SQLiteDatabase sqLiteDatabase;
    public static final String databaseName = "secondNote.db";
    private static final int version = 1;

    public DBOpenHelper(Context context) {
        super(context,databaseName, null, version);
        Log.e(TAG, "DBOpenHelper: is created");
    }
    public static final String tableName = "secondNote";
    public static final String n_id = "ID";
    public static final String n_Title = "title";
    public static final String n_Category = "category";
    private static final String n_Description = "description";
    private static final String n_Time = "Time";

    private String createSQL = " CREATE TABLE " + tableName +
            " ( " +
            "" + n_id + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "" + n_Title + " TEXT NOT NULL," +
            "" + n_Category + " TEXT NOT NULL" +
            ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE " + tableName);
        onCreate(db);
    }

    public void insert(String title, String description, String category, String time){
        ContentValues contentValues = new ContentValues();

        contentValues.put(n_Title, title);
        contentValues.put(n_Category, category);

        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(tableName, null, contentValues);
        sqLiteDatabase.close();
    }
    public ArrayList<NoteModel> selectAllItem(){
        ArrayList<NoteModel> listData = new ArrayList<>();
        String sql = " SELECT * FROM " + tableName + " ORDER BY " + n_id + " DESC";
        // TODO: 4/23/2020 check this
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(cursor.getInt(cursor.getColumnIndex(n_id)));

                noteModel.setTitle(cursor.getString(cursor.getColumnIndex(n_Title)));
                noteModel.setCategory(cursor.getString(cursor.getColumnIndex(n_Category)));

                listData.add(noteModel);
            }while (cursor.moveToNext());
        }
        return listData;
    }
}
