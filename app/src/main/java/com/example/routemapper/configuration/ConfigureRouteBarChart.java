package com.example.routemapper.configuration;

import android.content.Context;

import com.example.routemapper.R;
import com.example.routemapper.data_model.RouteItem;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to setup a bar chart of routes via the MPAndroidChart library found at
 * ----------------https://github.com/PhilJay/MPAndroidChart/------------------
 */

public class ConfigureRouteBarChart
{
    private List<RouteItem> mRouteList;
    private BarChart mBarChart;
    private Context mContext;
    private String[] mResourceArray;

    public ConfigureRouteBarChart(Context context, List<RouteItem> list, BarChart barChart)
    {
        mRouteList = list;
        mBarChart = barChart;
        mContext = context;
    }

    public int getListSize()
    {
        return mRouteList.size();
    }

    /**
     * Sets the resource array used for getting a list of totals
     * @param filter the item's data displayed on the bar chart
     */

    public void getResources(int filter)
    {
        switch (filter)
        {
            case R.id.color_filter:
                mResourceArray = mContext.getResources().getStringArray(R.array.color_hex_array);
                break;
            case R.id.location_filter:
                mResourceArray = mContext.getResources().getStringArray(R.array.locations_array);
                break;
            case R.id.grade_filter:
                mResourceArray = mContext.getResources().getStringArray(R.array.grades_array);
                break;
            case R.id.setter_filter:
                mResourceArray = mContext.getResources().getStringArray(R.array.setters_array);
                break;
        }
    }

    /**
     * Gets the count of a specific item among all routes in mRouteList
     * @param item: the item for which a count is returned
     * @return a count of the number of routes containing the specified item
     */

    public int getSpecificItemCount(int filter, String item)
    {
        int count = 0;

        for (int i = 0; i < getListSize(); i++)
        {
            switch(filter)
            {
                case R.id.color_filter:
                    int color = mRouteList.get(i).color;
                    String hexColor = String.format("#%06X", (0xFFFFFF & color));
                    if (hexColor.equals(item)){ count++; }
                    break;

                case R.id.grade_filter:
                    if (mRouteList.get(i).grade.equals(item)){ count++; }
                    break;

                case R.id.location_filter:
                    if (mRouteList.get(i).location.equals(item)){ count++; }
                    break;

                case R.id.setter_filter:
                    if (mRouteList.get(i).setter.equals(item)){ count++; }
                    break;
            }
        }
        return count;
    }

    /**
     * Gets the count of each grade among all routes in mRouteList
     * @return a list containing the counts of each grade for all routes in mRouteList
     */

    public List<Integer> getTotals(int filter)
    {
        int count;
        List<Integer> data = new ArrayList<>();

        for (String item : mResourceArray)
        {
            count = getSpecificItemCount(filter, item);
            data.add(count);
        }

        return data;
    }

    public void configure(int filter)
    {
        getResources(filter);

        List<BarEntry> entries = new ArrayList<>();
        List<Integer> totals = getTotals(filter);

        for (int i = 0; i < totals.size(); i++)
        {
            entries.add(new BarEntry((float)i, (float)totals.get(i)));
        }

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        BarData barData = new BarData(set);

        // configure chart

        mBarChart.setData(barData);
        mBarChart.setFitBars(true); // make the x-axis fit exactly all bars
        mBarChart.setDrawValueAboveBar(true); // show default value above each bar
        mBarChart.setDrawGridBackground(false); // remove grid background
        mBarChart.setDrawBorders(false); // remove borders around the entire chart
        mBarChart.setDescription(""); // remove description text
        mBarChart.setDrawBarShadow(true); // set a shadow around each bar
        mBarChart.setScaleYEnabled(false); // disable scaling on the y-axis

        // configure x-axis

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // move to the bottom of the chart
        xAxis.setDrawGridLines(false); // remove x-axis grid lines
        xAxis.setGranularity(1f);

        AxisLabelValueFormatter formatter;
        // TODO: Set value formatter after finding bug that causes ArrayIndexOutOfBounds exception.

        if (filter == R.id.color_filter)
        {
            formatter = new AxisLabelValueFormatter(mContext.getResources().getStringArray(R.array.color_names_array));
            // xAxis.setValueFormatter(formatter);
        }
        else if (filter == R.id.location_filter)
        {
            formatter = new AxisLabelValueFormatter(mContext.getResources().getStringArray(R.array.location_names_array));
            // xAxis.setValueFormatter(formatter);
        }
        else
        {
            formatter = new AxisLabelValueFormatter(mResourceArray);
            // xAxis.setValueFormatter(formatter);
        }

        // configure y-axis

        YAxis left = mBarChart.getAxisLeft();
        YAxis right = mBarChart.getAxisRight();
        right.setEnabled(false); // disable the right y-axis
        left.setEnabled(false); // disable the left y-axis
        left.setAxisMinValue(0); // set left min to 0

        // refresh the chart

        barData.notifyDataChanged();
        mBarChart.notifyDataSetChanged();
        mBarChart.invalidate();
    }

    /**
     * This class is provided by the MPAndroidChart library
     */

    public class AxisLabelValueFormatter implements AxisValueFormatter
    {
        private String[] mValues;

        public AxisLabelValueFormatter(String[] values)
        {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis)
        {
            return mValues[(int)value];
        }

        // this is only needed if numbers are returned, else return 0
        @Override
        public int getDecimalDigits()
        {
            return 0;
        }
    }
}