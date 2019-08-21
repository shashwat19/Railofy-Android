package com.simplicitydev.railofy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Time_Table_adaptor extends ArrayAdapter<String>{

    Activity activity;
    ArrayList<String> station;
    ArrayList<String> arrival;
    ArrayList<String> depart;
    ArrayList<String> distance;

    public Time_Table_adaptor(Activity activity,ArrayList<String> station, ArrayList<String> arrival, ArrayList<String> depart, ArrayList<String> distance){
        super(activity,android.R.layout.simple_list_item_1);
        this.activity=activity;
        this.station=station;
        this.arrival=arrival;
        this.depart=depart;
        this.distance=distance;
    }

    @Override
    public int getCount() {
        return station.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater obj=activity.getLayoutInflater();
        View view=obj.inflate(R.layout.time_table_layout,null);

        TextView st;
        TextView ar;
        TextView dt;
        TextView ds;

        st= (TextView) view.findViewById(R.id.station);
        ar= (TextView) view.findViewById(R.id.arrival);
        dt= (TextView) view.findViewById(R.id.depart);
        ds= (TextView) view.findViewById(R.id.distance);

        st.setText(station.get(position));
        ar.setText(arrival.get(position));
        dt.setText(depart.get(position));
        ds.setText(distance.get(position));

        return view;

    }

}
