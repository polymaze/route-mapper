package com.example.routemapper.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.routemapper.R;
import com.example.routemapper.data_model.RouteItem;
import com.example.routemapper.adapter.RouteItemArrayAdapter;
import com.example.routemapper.loader.RouteListLoader;

import java.util.List;

import static android.view.View.OnClickListener;
import static android.widget.AdapterView.OnItemClickListener;

public class RouteListFragment extends Fragment implements OnClickListener, OnItemClickListener, LoaderManager.LoaderCallbacks<List<RouteItem>>
{
    private static final int LOADER_ID = 1;
    private ListView mListView;
    private List<RouteItem> mRouteList;

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<List<RouteItem>> onCreateLoader(int id, Bundle args)
    {
        return new RouteListLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<RouteItem>> loader, List<RouteItem> data)
    {
        mRouteList = data;
        RouteItemArrayAdapter adapter = new RouteItemArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<RouteItem>> loader)
    {
        // Do Nothing. Yet.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_route_list, container, false);

        mListView = (ListView)rootView.findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            // Other buttons to come.
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        RouteItem route = mRouteList.get(position);
        String routeName = route.name;
        int routeId = route.id;

        Intent intent = new Intent(getActivity(), RouteDetailActivity.class);
        intent.putExtra(Intent.EXTRA_KEY_EVENT, routeId);
        intent.putExtra(Intent.EXTRA_TEXT, routeName);
        startActivity(intent);
    }
}