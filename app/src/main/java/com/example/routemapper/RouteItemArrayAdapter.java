package com.example.routemapper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RouteItemArrayAdapter extends ArrayAdapter<RouteItem>
{
    private LayoutInflater mInflater;
    private List<RouteItem> routes = null;

    public RouteItemArrayAdapter(Context context, int resourceId, List<RouteItem> routes)
    {
        super(context, resourceId, routes);

        this.routes = routes;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_route_list, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        RouteItem route = routes.get(position);

        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.routeName.setText(route.name);
        holder.routeGrade.setText(String.valueOf(route.grade));

        return convertView;
    }

    private class ViewHolder
    {
        private final TextView routeName;
        private final TextView routeGrade;

        public ViewHolder (View view)
        {
            routeName = (TextView) view.findViewById(R.id.routeName);
            routeGrade = (TextView) view.findViewById(R.id.routeGrade);
        }
    }
}
