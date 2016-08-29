package com.example.routemapper.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.routemapper.R;

public class RouteDetailActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        String title = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        setTitle(title);
    }
}
