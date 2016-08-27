package com.example.routemapper.loader;

import android.content.Context;
import android.database.Cursor;
import android.content.AsyncTaskLoader;

import com.example.routemapper.data_model.RouteItem;
import com.example.routemapper.db.RouteDBHelper;

public class RouteItemLoader extends AsyncTaskLoader<RouteItem>
{
    private Context mContext;
    private int mRouteId;

    public RouteItemLoader(Context context, int id)
    {
        super(context);
        mContext = context;
        mRouteId = id;
    }

    @Override
    public RouteItem loadInBackground()
    {
        RouteDBHelper dbHelper = new RouteDBHelper(mContext);
        Cursor cursor = dbHelper.getAllRoutes();
        cursor.moveToPosition(mRouteId);

        return new RouteItem(cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("date")),
                cursor.getInt(cursor.getColumnIndex("color")),
                cursor.getString(cursor.getColumnIndex("location")),
                cursor.getString(cursor.getColumnIndex("grade")),
                cursor.getString(cursor.getColumnIndex("setter")));
    }
}