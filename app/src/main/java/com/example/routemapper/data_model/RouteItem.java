package com.example.routemapper.data_model;

public class RouteItem
{
    public int id;
    public String name;
    public String date;
    public int color;
    public String location;
    public String grade;
    public String setter;

    public RouteItem(int id, String name, String date, int color, String location, String grade, String setter)
    {
        this.id = id;
        this.name = name;
        this.date = date;
        this.color = color;
        this.location = location;
        this.grade = grade;
        this.setter = setter;
    }
}