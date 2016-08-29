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

    private TextView mGradeView;
    private TextView mDateView;
    private TextView mColorView;
    private TextView mSetterView;
    private ImageView mLocationImage;

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
        mGradeView.setText(mRouteItem.grade);
        mDateView.setText(mRouteItem.date);
        mColorView.setTextColor(mRouteItem.color);
        mSetterView.setText(mRouteItem.setter);
        int imageId = getRouteLocationResourceId(mRouteItem.location);
        mLocationImage.setImageResource(imageId);
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

        mGradeView = (TextView)rootView.findViewById(R.id.route_detail_grade);
        mDateView = (TextView)rootView.findViewById(R.id.route_detail_date);
        mColorView = (TextView)rootView.findViewById(R.id.route_detail_color);
        mSetterView = (TextView)rootView.findViewById(R.id.route_detail_setter);
        mLocationImage = (ImageView)rootView.findViewById(R.id.route_location_image);

        return rootView;
    }

    public int getRouteLocationResourceId(String location)
    {
        int id = 0;

        switch (location)
        {
            case "Slab Wall":
                id = R.drawable.slab;
                break;

            case "Near Slab Wall":
                id = R.drawable.near_slab;
                break;

            case "The Chimney":
                id = R.drawable.chimney;
                break;

            case "The Big Roof":
                id = R.drawable.big_roof;
                break;

            case "Slant Wall":
                id = R.drawable.slant;
                break;

            case "Vert Wall - Right":
                id = R.drawable.vert_right;
                break;

            case "The Dihedral":
                id = R.drawable.dihedral;
                break;

            case "Vert Wall - Left":
                id = R.drawable.vert_left;
                break;

            case "The Small Roof":
                id = R.drawable.small_roof;
                break;

            case "The Double Arete":
                id = R.drawable.double_arete;
                break;

            case "Near Lead Wall":
                id = R.drawable.near_lead;
                break;

            case "Lead Wall":
                id = R.drawable.lead;
                break;

            case "Exit Wall":
                id = R.drawable.exit;
                break;
        }
        return id;
    }


}
