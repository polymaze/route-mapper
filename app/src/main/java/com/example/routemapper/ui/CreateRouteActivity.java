package com.example.routemapper.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.routemapper.R;

public class CreateRouteActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_route);
        setTitle("New Route");
    }
}