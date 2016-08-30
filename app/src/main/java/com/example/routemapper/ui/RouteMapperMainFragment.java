package com.example.routemapper.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.routemapper.R;

public class RouteMapperMainFragment extends Fragment implements View.OnClickListener
{
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button viewAllButton = (Button) rootView.findViewById(R.id.button_view_all_routes);
        viewAllButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button_view_all_routes:
                Intent intent = new Intent(getActivity(), RouteListActivity.class);
                startActivity(intent);
                break;
        }
    }
}