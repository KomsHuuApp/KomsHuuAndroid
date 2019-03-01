package com.komshuu.komshuuandroidfrontend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.adapters.AnnouncementAdapter;
import com.komshuu.komshuuandroidfrontend.adapters.ComplaintAdapter;
import com.komshuu.komshuuandroidfrontend.models.Announcement;
import com.komshuu.komshuuandroidfrontend.models.Complaint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ComplaintActivity extends AppCompatActivity {
    ArrayList<Complaint> complaintList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://enigmatic-atoll-89666.herokuapp.com/getComplaints?apartmentId=2";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        complaintList = new ArrayList<>();
                        try {
                            JSONArray complaints = new JSONArray(response);
                            for(int i = 0; i < complaints.length(); i++) {
                                JSONObject complaint = complaints.getJSONObject(i);
                                Complaint temp = new Complaint();
                                temp.setComplaintDate(complaint.getString("date"));
                                temp.setComplaintDescription(complaint.getString("text"));
                                complaintList.add(temp);
                            }
                            recyclerView = (RecyclerView) findViewById(R.id.recylerview);

                            ComplaintAdapter productAdapter = new ComplaintAdapter(ComplaintActivity.this, complaintList);
                            recyclerView.setAdapter(productAdapter);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ComplaintActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //bos iken
            }
        });
        queue.add(stringRequest);

    }
}

