package com.example.routemapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RouteDBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "RouteMapper.db";
    private static final int DATABASE_VERSION = 1;
    public static final String ROUTE_TABLE_NAME = "route";
    public static final String ROUTE_COLUMN_ID = "_id";
    public static final String ROUTE_COLUMN_NAME = "name";
    public static final String ROUTE_COLUMN_DATE = "date";
    public static final String ROUTE_COLUMN_COLOR = "color";
    public static final String ROUTE_COLUMN_LOCATION = "location";
    public static final String ROUTE_COLUMN_GRADE = "grade";
    public static final String ROUTE_COLUMN_SETTER = "setter";

    public RouteDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + ROUTE_TABLE_NAME + "(" +
                        ROUTE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        ROUTE_COLUMN_NAME + " TEXT, " +
                        ROUTE_COLUMN_DATE + " TEXT, " +
                        ROUTE_COLUMN_COLOR + " INT, " +
                        ROUTE_COLUMN_LOCATION + " TEXT, " +
                        ROUTE_COLUMN_GRADE + " TEXT, " +
                        ROUTE_COLUMN_SETTER + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Here we drop existing route table and add the new one when upgrading
        // Todo: Figure out a better plan for moving from one schema to the next

        db.execSQL("DROP TABLE IF EXISTS " + ROUTE_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRoute(String name, String date, int color, String location, String grade,
                               String setter)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROUTE_COLUMN_NAME, name);
        contentValues.put(ROUTE_COLUMN_DATE, date);
        contentValues.put(ROUTE_COLUMN_COLOR, color);
        contentValues.put(ROUTE_COLUMN_LOCATION, location);
        contentValues.put(ROUTE_COLUMN_GRADE, grade);
        contentValues.put(ROUTE_COLUMN_SETTER, setter);
        db.insert(ROUTE_TABLE_NAME, null, contentValues);

        return true;
    }

    public boolean updateRoute(Integer id, String name, String date, int color, String location,
                               String grade, String setter)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROUTE_COLUMN_NAME, name);
        contentValues.put(ROUTE_COLUMN_DATE, date);
        contentValues.put(ROUTE_COLUMN_COLOR, color);
        contentValues.put(ROUTE_COLUMN_LOCATION, location);
        contentValues.put(ROUTE_COLUMN_GRADE, grade);
        contentValues.put(ROUTE_COLUMN_SETTER, setter);
        db.update(ROUTE_TABLE_NAME, contentValues, ROUTE_COLUMN_ID + " = ? ", new String[]
                {Integer.toString(id)});

        return true;
    }

    public Cursor getRoute(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "SELECT * FROM " + ROUTE_TABLE_NAME + " WHERE "
                + ROUTE_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
    }

    public Cursor getAllRoutes()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "SELECT * FROM " + ROUTE_TABLE_NAME, null );
    }

    public Integer deleteRoute(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ROUTE_TABLE_NAME, ROUTE_COLUMN_ID
                + " = ? ", new String[] { Integer.toString(id)} );
    }
}
