package com.example.routemapper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RouteDetailFragment extends Fragment
{
    private static final String ARG_ROUTE_ID = "ArgRouteId";

    public static RouteDetailFragment newInstance(int id)
    {
        Bundle args = new Bundle();
        args.putInt(ARG_ROUTE_ID, id);
        RouteDetailFragment fragment = new RouteDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_route_detail, container, false);
        TextView title = (TextView)rootView.findViewById(R.id.route_detail_title);
        title.setText("Success!");
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
