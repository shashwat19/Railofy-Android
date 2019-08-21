package com.simplicitydev.railofy;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class StationCode extends Fragment {


    public StationCode() {
        // Required empty public constructor
    }

    EditText place_val;
    Button submit;

    ListView codes;

    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_station_code, container, false);

        place_val= (EditText) view.findViewById(R.id.place);

        submit= (Button) view.findViewById(R.id.submit_btn);

        codes= (ListView) view.findViewById(R.id.stationcodes);

        requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p=place_val.getText().toString().toLowerCase();

                if(p.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Place Name!", Toast.LENGTH_SHORT).show();
                }

                else {
                    final ProgressDialog pd=new ProgressDialog(getActivity());
                    pd.setMessage("Please Wait...");
                    pd.show();

                    String url = "https://indianrailways.p.rapidapi.com/findstations.php?station=" + p;

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            ArrayList<String> stations = new ArrayList<String>();
                            ArrayList<String> code = new ArrayList<String>();
                            stations.add("Station");
                            code.add("Code");
                            try {
                                JSONObject jobj = new JSONObject(response);
                                JSONArray jarray = jobj.getJSONArray("stations");

                                for (int i = 0; i < jarray.length(); i++) {
                                    JSONObject obj = jarray.getJSONObject(i);

                                    String stname = obj.getString("stationName");
                                    String stcode = obj.getString("stationCode");

                                    stations.add(stname);
                                    code.add(stcode);
                                }

                                Station_code_adaptor adaptor = new Station_code_adaptor(getActivity(), stations, code);
                                codes.setAdapter(adaptor);
                                pd.dismiss();
                            } catch (Exception e) {
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                            Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> data = new HashMap<String, String>();
                            data.put("x-rapidapi-host", "indianrailways.p.rapidapi.com");
                            data.put("x-rapidapi-key", "2632c6aa11mshe51309cdcb5f007p17c097jsn51e2f325d6bb");
                            return data;
                        }
                    };

                    requestQueue.add(request);
                }
            }
        });

        return view;
    }

}
