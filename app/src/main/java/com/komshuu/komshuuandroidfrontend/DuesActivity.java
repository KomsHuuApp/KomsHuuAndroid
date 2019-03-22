package com.komshuu.komshuuandroidfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.adapters.AnnouncementAdapter;
import com.komshuu.komshuuandroidfrontend.adapters.DuesAdapter;
import com.komshuu.komshuuandroidfrontend.models.Dues;
import com.komshuu.komshuuandroidfrontend.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DuesActivity extends AppCompatActivity {

    String url = "https://enigmatic-atoll-89666.herokuapp.com/";
    ArrayList<Dues> duesList;
    RecyclerView recyclerView;
    DuesAdapter duesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dues);


        Intent intent = getIntent();
        final User user = (User) intent.getSerializableExtra("user");

        url += "getDues?apartmentId=" + user.getApartmentId() + "&flatNumber=" + user.getFlatNumber();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                duesList = new ArrayList<>();
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Dues dues = new Dues();
                        dues.setDuesId(object.getLong("duesId"));
                        dues.setApartmentId(object.getLong("apartmentId"));
                        dues.setFlatNumber(object.getInt("flatNumber"));
                        dues.setPaymentDate(object.getString("paymentDate"));
                        dues.setTotalPayment(object.getString("totalPayment"));
                        duesList.add(dues);
                    }
                    recyclerView = (RecyclerView) findViewById(R.id.recylerview);
                    duesAdapter = new DuesAdapter(DuesActivity.this, duesList);
                    recyclerView.setAdapter(duesAdapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DuesActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}
