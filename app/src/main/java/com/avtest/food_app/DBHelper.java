package com.avtest.food_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Login.db",  null,  1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table register( name TEXT primary key,email TEXT, phone TEXT,password TEXT, repass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("drop Table if exists register");
    }

    public Boolean insertData(String name, String email, String phone, String password, String repass ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("name", name);
        contentvalues.put("email", email);
        contentvalues.put("phone", phone);
        contentvalues.put("password", password);
        contentvalues.put("repass", repass);

        long result = db.insert("register", null, contentvalues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean checks(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from register where name = ?", new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUP(String username,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from register where name= ? and password= ?", new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
