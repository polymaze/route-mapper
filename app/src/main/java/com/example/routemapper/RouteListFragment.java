package com.example.routemapper;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import static android.view.View.OnClickListener;
import static android.widget.AdapterView.OnItemClickListener;

public class RouteListFragment extends Fragment implements OnClickListener, OnItemClickListener, LoaderManager.LoaderCallbacks<List<RouteItem>>
{
    public final static String KEY_EXTRA_ROUTE_ID = "KEY_EXTRA_ROUTE_ID";
    public final static String KEY_ROUTE_ID = "KEY_ROUTE_ID";

    private static final int LOADER_ID = 1;
    private ListView mListView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<List<RouteItem>> onCreateLoader(int id, Bundle args)
    {
        return new RouteLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<RouteItem>> loader, List<RouteItem> data)
    {
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

        Button button = (Button)rootView.findViewById(R.id.button_add_new_route);
        button.setOnClickListener(this);
        mListView = (ListView)rootView.findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button_add_new_route:
                Intent intent = new Intent(getActivity(), CreateRouteActivity.class);
                intent.putExtra(KEY_EXTRA_ROUTE_ID, 0);
                startActivity(intent);
                break;

            // Other buttons to come.
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(getActivity(), RouteDetailActivity.class);
        intent.putExtra(KEY_ROUTE_ID, id);
        startActivity(intent);
    }
}
