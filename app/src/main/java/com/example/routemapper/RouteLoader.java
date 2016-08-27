package com.example.routemapper;

import android.content.Context;
import android.database.Cursor;
import android.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class RouteLoader extends AsyncTaskLoader<List<RouteItem>>
{
    private Context mContext;

    public RouteLoader(Context context)
    {
        super(context);
        mContext = context;
    }

    @Override
    public List<RouteItem> loadInBackground()
    {
        RouteDBHelper dbHelper = new RouteDBHelper(mContext);
        Cursor cursor = dbHelper.getAllRoutes();

        ArrayList<RouteItem> routes = new ArrayList<>();

        while (cursor.moveToNext())
        {
            routes.add(new RouteItem(cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("date")),
                    cursor.getInt(cursor.getColumnIndex("color")),
                    cursor.getString(cursor.getColumnIndex("location")),
                    cursor.getString(cursor.getColumnIndex("grade")),
                    cursor.getString(cursor.getColumnIndex("setter"))));
        }
        return routes;
    }
}
