package com.avtest.food_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBrod extends SQLiteOpenHelper {

    List<Contacts> contactsList = new ArrayList<>();


    public DBrod(@Nullable Context context) {

        super(context, "Food.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table products( name TEXT primary key,price TEXT,discount TEXT, quantity TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists products");
    }

    public Boolean insertData(String name, String price, String discount,String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("name", name);
        contentvalues.put("price", price);
        contentvalues.put("discount", discount);
        contentvalues.put("quantity", quantity);

        long result = db.insert("products", null, contentvalues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean updateData(String name, String price, String discount, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();

        contentvalues.put("price", price);
        contentvalues.put("discount", discount);
        contentvalues.put("quantity", quantity);

        Cursor cursor = db.rawQuery("Select * from products where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.update("products", contentvalues, "name=?", new String[]{name});
            if (result == -1)
                return false;
            else
                return true;
        } else {
            return false;
        }
    }

    public Boolean deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from products where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.delete("products", "name=?", new String[]{name});
            if (result == -1)
                return false;
            else
                return true;
        } else {
            return false;
        }
    }
    public Cursor getinfo(){
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery("select * from products",null);
       return cursor;
    }



}
