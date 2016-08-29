package com.example.routemapper.loader;

import android.content.Context;
import android.database.Cursor;
import android.content.AsyncTaskLoader;

import com.example.routemapper.data_model.RouteItem;
import com.example.routemapper.db.RouteDBHelper;

public class RouteItemLoader extends AsyncTaskLoader<RouteItem>
{
    private Context mContext;
    private String mRouteName;

    public RouteItemLoader(Context context, String name)
    {
        super(context);
        mContext = context;
        mRouteName = name;
    }

    @Override
    public RouteItem loadInBackground()
    {
        RouteDBHelper dbHelper = new RouteDBHelper(mContext);
        Cursor cursor = dbHelper.getRouteByName(mRouteName);
        cursor.moveToFirst();

        return new RouteItem(cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("date")),
                cursor.getInt(cursor.getColumnIndex("color")),
                cursor.getString(cursor.getColumnIndex("location")),
                cursor.getString(cursor.getColumnIndex("grade")),
                cursor.getString(cursor.getColumnIndex("setter")));
    }
}