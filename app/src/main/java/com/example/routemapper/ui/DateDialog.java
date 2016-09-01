package com.example.routemapper.ui;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created by polymaze on 9/1/2016.
 */
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText txtDate;
    public DateDialog(View view){
        txtDate = (EditText)view;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this,year,month,day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day){
        String date = day+"-"+(month+1)+"-"+year;
        txtDate.setText(date);
    }
}
