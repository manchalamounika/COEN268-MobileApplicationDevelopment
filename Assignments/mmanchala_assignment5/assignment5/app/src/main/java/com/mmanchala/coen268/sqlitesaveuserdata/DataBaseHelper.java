package com.mmanchala.coen268.sqlitesaveuserdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    public static final String DATABASE_NAME = "info.db";
    public static final String TABLE_NAME = "user_content";
    public static final String Name = "name";
    public static final String Email = "email";
    public static final String TVShow = "tvShow";
    public static final String ID = "id";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " " +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Email TEXT, TVShow TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String name, String email, String tvShow) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name, name);
        contentValues.put(Email, email);
        contentValues.put(TVShow, tvShow);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor retriveData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }

    public boolean updateData(String id, String name, String email, String tvShow) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(Name, name);
        contentValues.put(Email, email);
        contentValues.put(TVShow, tvShow);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[] { id });
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{ id });
    }

}
