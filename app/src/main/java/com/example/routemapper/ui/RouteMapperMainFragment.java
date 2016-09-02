package com.example.routemapper.ui;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.routemapper.R;
import com.example.routemapper.configuration.ConfigureRouteBarChart;
import com.example.routemapper.data_model.RouteItem;
import com.example.routemapper.loader.RouteListLoader;
import com.github.mikephil.charting.charts.BarChart;

import java.util.List;

import static android.view.View.*;

public class RouteMapperMainFragment extends Fragment implements OnClickListener,
        LoaderCallbacks<List<RouteItem>>
{
    private static final int LOADER_ID = 3;

    private List<RouteItem> mRouteList;
    private BarChart mBarChart;

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
        ConfigureRouteBarChart configureChart = new ConfigureRouteBarChart(getActivity(),
                mRouteList, mBarChart);
        configureChart.configure();
    }

    @Override
    public void onLoaderReset(Loader<List<RouteItem>> loader)
    {
        // Do nothing. Yet.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mBarChart = (BarChart) rootView.findViewById(R.id.bar_chart);

        Button viewAllRoutesButton = (Button) rootView.findViewById(R.id.button_view_all_routes);
        viewAllRoutesButton.setOnClickListener(this);

        Button addRouteButton = (Button) rootView.findViewById(R.id.button_add_new_route);
        addRouteButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;

        switch (v.getId())
        {
            case R.id.button_view_all_routes:
                intent = new Intent(getActivity(), RouteListActivity.class);
                startActivity(intent);
                break;

            case R.id.button_add_new_route:
                intent = new Intent(getActivity(), CreateRouteActivity.class);
                startActivity(intent);
                break;

            // More buttons to come.
        }
    }
}