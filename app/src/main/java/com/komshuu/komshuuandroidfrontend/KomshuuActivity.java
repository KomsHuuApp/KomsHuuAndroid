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
import com.komshuu.komshuuandroidfrontend.adapters.KomshuuAdapter;
import com.komshuu.komshuuandroidfrontend.models.Komshuu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KomshuuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Komshuu> komshuus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komshuu);
        final TextView mTextView = (TextView) findViewById(R.id.textView3);
        Intent intent = getIntent();
        String flatNumber = intent.getStringExtra("flatNumber");
        System.out.println(flatNumber + " Komshuu");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://enigmatic-atoll-89666.herokuapp.com/getPeopleByFlatNo?flatNumber=" + flatNumber + "&apartmentId=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        komshuus = new ArrayList<>();
                        try {
                            JSONArray people = new JSONArray(response);
                            for(int i = 0; i < people.length(); i++) {
                                JSONObject person = people.getJSONObject(i);
                                Komshuu komshuu = new Komshuu();
                                komshuu.setImageID(R.drawable.ic_menu_gallery);
                                komshuu.setName(person.getString("name") + " " + person.getString("surname"));
                                komshuu.setPhoneNumber(person.getString("number"));
                                komshuu.setFlatNumber(person.getString("flatNumber"));
                                komshuus.add(komshuu);
                            }
                            recyclerView = (RecyclerView) findViewById(R.id.recylerview);

                            KomshuuAdapter productAdapter = new KomshuuAdapter(KomshuuActivity.this, komshuus);
                            recyclerView.setAdapter(productAdapter);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(KomshuuActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
}
