package com.avtest.food_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Dbhandler extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String DB_NAME = "fooda";
    private static final String TABLE_NAME = "fooda";

    //Column
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";


    public Dbhandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    //contructor eka run wela database elak hadanawa mulinma
    //itapasse oncreate run wela table eka hadanawa
    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " " +
                "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT,"
                + DESCRIPTION + " TEXT,"
                + STARTED + " TEXT,"
                + FINISHED + " TEXT" +
                ");";

        //RUN DBquary
        db.execSQL(TABLE_CREATE_QUERY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //DROP OLDER TABLE IF EXISTED
        db.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(db);

    }

    //meka call karanna onanam AddtoCart eke object ekek hadanna ona
    public void addToCart(model mod) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE, mod.getTitle());
        contentValues.put(DESCRIPTION, mod.getDescription());
        contentValues.put(STARTED, mod.getStarted());
        contentValues.put(FINISHED, mod.getFinished());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        //close database
        sqLiteDatabase.close();
    }

    //Count  table record
    public int countToDo() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    //Get all todos into a ist
    public List<model> getAllToDos() {

        List<model> mods = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                //create new todomodel
                model mod = new model();
                //mm
                mod.setId(cursor.getInt(0));
                mod.setTitle(cursor.getString(1));
                mod.setDescription(cursor.getString(2));
                mod.setStarted(cursor.getLong(3));
                mod.setStarted(cursor.getLong(4));

                //toDos = [obj.objs]
                mods.add(mod);

            } while (cursor.moveToNext());
        }
        return mods;
    }

    //Delete item
    public void deletecart(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID + " =?", new String[]{String.valueOf(id)});
        db.close();
    }

    //get single item
    public model getSingleitem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,TITLE,DESCRIPTION, STARTED, FINISHED},
                ID + "= ?", new String[]{String.valueOf(id)}, null, null, null );

        model mod;
        if(cursor != null){
            //String id = cursor.getInt(0);
            cursor.moveToFirst();
            mod = new model(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)
            );
            return mod;
        }
        return null;




    }
    //update an item
    public int updateSingleitem(model mod){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,mod.getTitle());

        // contentValues.put(TITLE,todomodel.getTitle());
        contentValues.put(DESCRIPTION,mod.getDescription());
        contentValues.put(STARTED,mod.getStarted());
        contentValues.put(FINISHED,mod.getFinished());

        int status = db.update(TABLE_NAME,contentValues, ID +" =?",
                new String[]{String.valueOf(mod.getId())});

        db.close();
        return status;

    }


}
