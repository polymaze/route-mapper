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
     * Gets the count of a specific grade among all routes in mRouteList
     * @param grade: the grade for which a count is returned
     * @return a count of the number of routes of the specified grade
     */

    public int getSpecificGradeCount(String grade)
    {
        int count = 0;

        for (int i = 0; i < getListSize(); i++)
        {
            if (mRouteList.get(i).grade.equals(grade))
            {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the count of each grade among all routes in mRouteList
     * @return a list containing the counts of each grade for all routes in mRouteList
     */

    public List<Integer> getGradeTotals()
    {
        List<Integer> data = new ArrayList<>();
        String[] grades = mContext.getResources().getStringArray(R.array.grades_array);

        int gradeCount;

        for (String grade : grades)
        {
            gradeCount = getSpecificGradeCount(grade);
            data.add(gradeCount);
        }
        return data;
    }

    public void configure()
    {
        List<BarEntry> entries = new ArrayList<>();
        List<Integer> gradeTotals = getGradeTotals();

        for (int i = 0; i < gradeTotals.size(); i++)
        {
            entries.add(new BarEntry((float)i, (float)gradeTotals.get(i)));
        }

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        BarData barData = new BarData(set);
        barData.setBarWidth(1.2f); // set custom bar width

        // configure chart

        mBarChart.setData(barData);
        mBarChart.setFitBars(true); // make the x-axis fit exactly all bars
        mBarChart.setDrawValueAboveBar(true); // show default value above each bar
        mBarChart.setDrawGridBackground(false); // remove grid background
        mBarChart.setDrawBorders(false); // remove borders around the entire chart
        mBarChart.setDescription("Routes by Grade"); // set description text
        mBarChart.setDrawBarShadow(true); // set a shadow around each bar

        // configure x-axis

        String[] grades = mContext.getResources().getStringArray(R.array.grades_array);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setValueFormatter(new XAxisValueFormatter(grades));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // move to the bottom of the chart
        xAxis.setDrawGridLines(false); // remove x-axis grid lines

        // configure y-axis

        YAxis left = mBarChart.getAxisLeft();
        YAxis right = mBarChart.getAxisRight();
        right.setEnabled(false); // disable the right y-axis
        left.setDrawGridLines(false); // remove left y-axis grid lines
        left.setAxisMinValue(0); // set left min to 0

        // refresh the chart

        mBarChart.invalidate();
    }

    /**
     * This class is provided by the MPAndroidChart library
     */

    public class XAxisValueFormatter implements AxisValueFormatter
    {
        private String[] mValues;

        public XAxisValueFormatter(String[] values)
        {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis)
        {
            return mValues[(int) value];
        }

        // this is only needed if numbers are returned, else return 0
        @Override
        public int getDecimalDigits()
        {
            return 0;
        }
    }
}
