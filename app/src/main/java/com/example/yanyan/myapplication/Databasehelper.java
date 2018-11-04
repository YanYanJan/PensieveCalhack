package com.example.yanyan.myapplication;

/**
 * Created by yanyan on 11/3/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Memory.db";
    public static final String TABLE_NAME = "Info_table";

    //create the column names

    public static final String COL_1 ="ID";
    public static final String COL_2 ="DATE";
    public static final String COL_3 ="THOUGHT";
    public static final String COL_4 ="PHOTO";
    public static final String COL_5 ="VEDIO";
    public static final String COL_6 ="AUDIO";
    public static final String COL_7 ="LOCATION";


    public Databasehelper(Context context){
        super(context, DATABASE_NAME, null , 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, THOUGHT TEXT, PHOTO BLOB, VEDIO TEXT, AUDIO, TEXT LOCATION)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(//Post post)
        String date, String thought, byte[] photo, String vedio, String audio,String location){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put(COL_2, date);
        cv.put(COL_3, thought);
        cv.put(COL_4, photo);
        cv.put(COL_5, vedio);
        cv.put(COL_6, audio);
        cv.put(COL_7, location);


        long result = db.insert(TABLE_NAME,null,cv);
        return (result != -1);

    }

    public boolean updateData(String id,String date, String thought, byte[] photo, String vedio, String audio, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(COL_1, id);
        cv.put(COL_2, date);
        cv.put(COL_3, thought);
        cv.put(COL_4, photo);
        cv.put(COL_5, vedio);
        cv.put(COL_6, audio);
        //cv.put(COL_7, location);

        db.update(TABLE_NAME,cv,"ID = ?", new String[]{id});
        return true;
    }

    public void deleteData(String book, String datetime){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "+ COL_2 + " = '" + datetime + "'";
        db.execSQL(query);
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

}
