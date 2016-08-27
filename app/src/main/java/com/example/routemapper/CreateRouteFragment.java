package com.example.routemapper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.thebluealliance.spectrum.SpectrumPalette;

import static android.view.View.*;
import static android.widget.TextView.*;


public class CreateRouteFragment extends Fragment implements OnClickListener, OnEditorActionListener,
        AdapterView.OnItemSelectedListener, SpectrumPalette.OnColorSelectedListener
{
    private static final String ARG_ROUTE_ID = "ArgRouteId";

    private String mName;
    private String mDate;
    private Integer mColor;
    private String mLocation;
    private String mGrade;
    private String mSetter;

    private EditText mNameEdit;
    private EditText mDateEdit;
    private SpectrumPalette mColorPalette;
    private Spinner mLocationSpinner;
    private Spinner mGradeSpinner;
    private Spinner mSetterSpinner;


    public static CreateRouteFragment newInstance(int id)
    {
        Bundle args = new Bundle();
        args.putInt(ARG_ROUTE_ID, id);
        CreateRouteFragment fragment = new CreateRouteFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_create_route, container, false);

        mNameEdit = (EditText)rootView.findViewById(R.id.edit_route_name);
        mNameEdit.setOnEditorActionListener(this);
        mDateEdit = (EditText)rootView.findViewById(R.id.edit_route_date);
        mDateEdit.setOnEditorActionListener(this);

        mColorPalette = (SpectrumPalette) rootView.findViewById(R.id.palette);
        mColorPalette.setColors(getResources().getIntArray(R.array.colors_array));
        mColorPalette.setOnColorSelectedListener(this);

        mLocationSpinner = (Spinner)rootView.findViewById(R.id.spinner_location);
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.locations_array, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLocationSpinner.setAdapter(locationAdapter);
        mLocationSpinner.setOnItemSelectedListener(this);

        mGradeSpinner = (Spinner)rootView.findViewById(R.id.spinner_grade);
        ArrayAdapter<CharSequence> gradeAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.grades_array, android.R.layout.simple_spinner_item);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGradeSpinner.setAdapter(gradeAdapter);
        mGradeSpinner.setOnItemSelectedListener(this);

        mSetterSpinner = (Spinner)rootView.findViewById(R.id.spinner_setter);
        ArrayAdapter<CharSequence> setterAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.setters_array, android.R.layout.simple_spinner_item);
        setterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSetterSpinner.setAdapter(setterAdapter);
        mSetterSpinner.setOnItemSelectedListener(this);

        Button addRoute = (Button)rootView.findViewById(R.id.button_add_route);
        addRoute.setOnClickListener(this);
        Button cancelRoute = (Button)rootView.findViewById(R.id.button_cancel_route);
        cancelRoute.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        Toast toast;
        String toastContent;

        switch(v.getId())
        {
            case R.id.button_add_route:
                if (mName != null && mDate != null && mColor != null && mLocation != null && mGrade != null && mSetter != null)
                {
                    toastContent = "New route " + mName + " created";
                    toast = Toast.makeText(getActivity(), toastContent, Toast.LENGTH_LONG);
                    toast.show();

                    RouteDBHelper dbHelper = new RouteDBHelper(getActivity());
                    dbHelper.insertRoute(mName, mDate, mColor, mLocation, mGrade, mSetter);

                    getActivity().finish();
                }
                else
                {
                    toastContent = "Please fill out all fields or Cancel";
                    toast = Toast.makeText(getActivity(), toastContent, Toast.LENGTH_LONG);
                    toast.show();
                }
                break;

            case R.id.button_cancel_route:
                getActivity().finish();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {
        switch(v.getId())
        {
            case R.id.edit_route_name:
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    mName = mNameEdit.getText().toString();
                }
                break;

            case R.id.edit_route_date:
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    mDate = mDateEdit.getText().toString();
                }
                break;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (parent.getId())
        {
            case R.id.spinner_location:
                mLocation = mLocationSpinner.getItemAtPosition(position).toString();
                break;
            case R.id.spinner_grade:
                mGrade = mGradeSpinner.getItemAtPosition(position).toString();
                break;
            case R.id.spinner_setter:
                mSetter = mSetterSpinner.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        // Do nothing. Yet.
    }

    @Override
    public void onColorSelected(int color)
    {
        mColor = color;
    }
}

