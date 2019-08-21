package com.simplicitydev.railofy;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.simplicitydev.railofy.R.id.timetable;
import static com.simplicitydev.railofy.R.id.trainno;


public class TimeTable extends Fragment {


    public TimeTable() {
        // Required empty public constructor
    }

    TextView trainno_val, trainname, source, destination;
    ListView timetable_val;

    CardView train_details;

    EditText trno;
    Button submit;

    String train_no;
    String trainname_val;
    String source_val;
    String destination_val;
    String station;
    String arrival;
    String depart;
    String distance;

    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);

        trainname = (TextView) view.findViewById(R.id.trainname);
        trainno_val = (TextView) view.findViewById(trainno);
        source = (TextView) view.findViewById(R.id.trainsource);
        destination = (TextView) view.findViewById(R.id.destination);

        train_details= (CardView) view.findViewById(R.id.details);
        train_details.setVisibility(View.INVISIBLE);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        timetable_val= (ListView) view.findViewById(timetable);

        trno = (EditText) view.findViewById(R.id.trno);

        submit = (Button) view.findViewById(R.id.submit_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String trainnum = trno.getText().toString();

                if(trainnum.isEmpty()){
                    Toast.makeText(getActivity(),"Enter Train No.",Toast.LENGTH_SHORT).show();
                }

                else {
                    final ProgressDialog pd = new ProgressDialog(getActivity());
                    pd.setMessage("Please Wait...");
                    pd.show();
                    train_details.setVisibility(View.VISIBLE);
                    String url = "https://api.data.gov.in/resource/13051d52-05c2-4130-9e7b-891bdde84076?api-key=579b464db66ec23bdd00000102b7560567124ea5650210867e456e7a&format=json&offset=0&filters[train_no]=" + trainnum;

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            ArrayList<String> st = new ArrayList<String>();
                            ArrayList<String> ar = new ArrayList<String>();
                            ArrayList<String> dt = new ArrayList<String>();
                            ArrayList<String> ds = new ArrayList<String>();
                            st.add("Station");
                            ar.add("Arrival");
                            dt.add("Depart.");
                            ds.add("Distance(km)");
                            try {
                                JSONObject jobj = new JSONObject(response);
                                JSONArray jarray = jobj.getJSONArray("records");

                                for (int i = 0; i < jarray.length(); i++) {
                                    JSONObject obj = jarray.getJSONObject(i);

                                    train_no = obj.getString("train_no");
                                    trainname_val = obj.getString("train_name");
                                    source_val = obj.getString("source_station_name");
                                    destination_val = obj.getString("destination_station_name");
                                    station = obj.getString("station_name");
                                    arrival = obj.getString("arrival_time");
                                    depart = obj.getString("departure_time");
                                    distance = obj.getString("_distance");

                                    st.add(station);
                                    ar.add(arrival);
                                    dt.add(depart);
                                    ds.add(distance);
                                }

                                trainno_val.setText("Train No: " + train_no);
                                trainname.setText("Train Name: " + trainname_val);
                                source.setText("Source: " + source_val);
                                destination.setText("Destination: " + destination_val);

                                Time_Table_adaptor adaptor = new Time_Table_adaptor(getActivity(), st, ar, dt, ds);
                                timetable_val.setAdapter(adaptor);
                                pd.dismiss();

                            } catch (Exception e) {
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(request);
                }
            }
        });

        return view;
    }

}
