package com.example.routemapper.ui;

        import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.routemapper.R;
import com.example.routemapper.configuration.ConfigureRouteBarChart;
import com.example.routemapper.data_model.RouteItem;
import com.example.routemapper.loader.RouteListLoader;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;

public class RouteMapperMainFragment extends Fragment implements OnClickListener,
        LoaderCallbacks<List<RouteItem>>
{
    private static final int LOADER_ID = 3;

    private List<RouteItem> mRouteList;
    private BarChart mBarChart;

    private int mSelectedFilter;
    private List <MenuItem> mFilterItems;
    private ConfigureRouteBarChart mConfigureChart;
    private int[] filterIds = {R.id.color_filter,
            R.id.grade_filter,
            R.id.location_filter,
            R.id.setter_filter};

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
        setHasOptionsMenu(true);
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
        mSelectedFilter = R.id.grade_filter;
        mConfigureChart = new ConfigureRouteBarChart(getActivity(), mRouteList, mBarChart);
        mConfigureChart.configure(mSelectedFilter);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_filter_item_list, menu);
        MenuItem item = menu.findItem(R.id.grade_filter);
        item.setChecked(true);
        mFilterItems = new ArrayList<>();
        for (int id : filterIds)
        {
            mFilterItems.add(menu.findItem(id));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        for (MenuItem menuItem : mFilterItems)
        {
            if (item == menuItem)
            {
                item.setChecked(true);
                mSelectedFilter = item.getItemId();
                mConfigureChart.configure(mSelectedFilter);
            }
            else
            {
                menuItem.setChecked(false);
            }
        }
        return true;
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