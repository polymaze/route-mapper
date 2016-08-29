package com.example.routemapper.ui;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.routemapper.R;
import com.example.routemapper.data_model.RouteItem;
import com.example.routemapper.loader.RouteItemLoader;

public class RouteDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<RouteItem>
{
    private static final int LOADER_ID = 2;

    private String routeNameId;

    private RouteItem mRouteItem;
    private TextView mTitle;

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<RouteItem> onCreateLoader(int id, Bundle args)
    {
        return new RouteItemLoader(getActivity(), routeNameId);
    }

    @Override
    public void onLoadFinished(Loader<RouteItem> loader, RouteItem data)
    {
        mRouteItem = data;
        mTitle.setText(mRouteItem.name);
    }

    @Override
    public void onLoaderReset(Loader<RouteItem> loader)
    {
        // Do nothing. Yet.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_route_detail, container, false);

        Intent intent = getActivity().getIntent();
        routeNameId = intent.getStringExtra(Intent.EXTRA_TEXT);

        mTitle = (TextView)rootView.findViewById(R.id.route_detail_title);
        ImageView imageView = (ImageView)rootView.findViewById(R.id.route_location_image);

        int locations[] = {
                R.drawable.slab,
                R.drawable.near_slab,
                R.drawable.chimney,
                R.drawable.big_roof,
                R.drawable.slant,
                R.drawable.vert_right,
                R.drawable.dihedral,
                R.drawable.vert_left,
                R.drawable.small_roof,
                R.drawable.double_arete,
                R.drawable.near_lead,
                R.drawable.lead,
                R.drawable.exit};

        imageView.setImageResource(locations[0]);

        return rootView;
    }


}
