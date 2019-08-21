package com.simplicitydev.railofy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Station_code_adaptor extends ArrayAdapter<String>{

    Activity activity;
    ArrayList<String> station;
    ArrayList<String> code;

    public Station_code_adaptor(Activity activity,ArrayList<String> station, ArrayList<String> code){
        super(activity,android.R.layout.simple_list_item_1);
        this.activity=activity;
        this.station=station;
        this.code=code;
    }

    @Override
    public int getCount() {
        return station.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater obj=activity.getLayoutInflater();
        View view=obj.inflate(R.layout.station_code_layout,null);

        TextView st;
        TextView cd;

        st= (TextView) view.findViewById(R.id.station);
        cd= (TextView) view.findViewById(R.id.code);

        st.setText(station.get(position));
        cd.setText(code.get(position));

        return view;

    }

}
