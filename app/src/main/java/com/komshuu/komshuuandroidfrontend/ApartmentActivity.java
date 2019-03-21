package com.komshuu.komshuuandroidfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.adapters.ApartmentAdapter;
import com.komshuu.komshuuandroidfrontend.models.Apartment;
import com.komshuu.komshuuandroidfrontend.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApartmentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Apartment> apartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final User user = (User) intent.getSerializableExtra("user");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_numbers);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://enigmatic-atoll-89666.herokuapp.com/getApartmentById?apartmentId=" + user.getApartmentId();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        apartments = new ArrayList<>();
                        try {
                            JSONObject announcement = new JSONObject(response);
                            Apartment temp = new Apartment();
                            temp.setImageID(R.drawable.ic_menu_gallery);
                            temp.setName(announcement.getString("name"));
                            temp.setAddress(announcement.getString("address"));
                            temp.setFlatNumber(announcement.getString("flatNumber"));
                            apartments.add(temp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        recyclerView = (RecyclerView) findViewById(R.id.recylerview);

                        ApartmentAdapter productAdapter = new ApartmentAdapter(ApartmentActivity.this, apartments);
                        recyclerView.setAdapter(productAdapter);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ApartmentActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(linearLayoutManager);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
