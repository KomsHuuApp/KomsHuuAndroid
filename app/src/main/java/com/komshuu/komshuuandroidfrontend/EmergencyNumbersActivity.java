package com.komshuu.komshuuandroidfrontend;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.adapters.EmergencyCallNumberAdapter;
import com.komshuu.komshuuandroidfrontend.models.Apartment;
import com.komshuu.komshuuandroidfrontend.models.EmergencyCallNumber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class EmergencyNumbersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<EmergencyCallNumber> emergencyCallNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_numbers);
        final TextView mTextView = (TextView) findViewById(R.id.textView2);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://enigmatic-atoll-89666.herokuapp.com/getEmergencyNumbers?apartmentId=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        emergencyCallNumbers = new ArrayList<>();
                        try {
                            JSONArray emergencyNumbers = new JSONArray(response);
                            for(int i = 0; i < emergencyNumbers.length(); i++) {
                                JSONObject announcement = emergencyNumbers.getJSONObject(i);
                                EmergencyCallNumber temp = new EmergencyCallNumber();
                                temp.setImageID(R.drawable.ic_menu_gallery);
                                temp.setEmergencyId(announcement.getLong("numberId"));
                                temp.setName(announcement.getString("name"));
                                temp.setPhoneNumber(announcement.getString("phoneNumber"));
                                temp.setApartmentId(announcement.getLong("apartmentId"));
                                emergencyCallNumbers.add(temp);
                            }
                            recyclerView = (RecyclerView) findViewById(R.id.recylerview);

                            EmergencyCallNumberAdapter productAdapter = new EmergencyCallNumberAdapter(EmergencyNumbersActivity.this, emergencyCallNumbers);
                            recyclerView.setAdapter(productAdapter);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EmergencyNumbersActivity.this);
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
        queue.add(stringRequest);

    }

    public void delete(View v) {
        TextView textView = (TextView) findViewById(R.id.emergencyId);
        TextView textView1 = (TextView) findViewById(R.id.apartmentId);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://enigmatic-atoll-89666.herokuapp.com/deleteEmergencyNumber?id=" + textView.getText() + "&apartmentId=" + textView1.getText();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        emergencyCallNumbers = new ArrayList<>();
                        try {
                            if (response.equals("true")) {
                                CardView cardView = (CardView) findViewById(R.id.cardView);
                                cardView.setVisibility(View.GONE);
                                finish();
                                startActivity(getIntent());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
