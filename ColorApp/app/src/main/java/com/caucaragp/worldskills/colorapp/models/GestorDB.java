package com.caucaragp.worldskills.colorapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GestorDB extends SQLiteOpenHelper{
    public GestorDB(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.TABLE_SCORE);
    }

    //Método para el ingreso del campo puntaje en la tabla SCORE
    public void inputData (Score score){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PUNTAJE",score.getPuntaje());
        db.insert("SCORE",null,values);
        db.close();
    }

    //Método para el listado de puntajes de la tabla SCORE
    public List<Score> scoreList(){
        List<Score> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SCORE ORDER BY PUNTAJE DESC;",null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score();
                score.setPuntaje(cursor.getInt(0));
                results.add(score);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return results;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
