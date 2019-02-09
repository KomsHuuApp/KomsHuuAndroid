package com.komshuu.komshuuandroidfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KomshuuActivity extends AppCompatActivity {

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
                        String result = "";
                        try {
                            JSONArray people = new JSONArray(response);
                            for(int i = 0; i < people.length(); i++) {
                                JSONObject person = people.getJSONObject(i);
                                String name = person.getString("name");
                                String surname = person.getString("surname");
                                String flatNumber = person.getString("flatNumber");
                                String phoneNumber = person.getString("number");

                                result += "Person Name: " + name + "\nPerson Surname: " + surname + "\nPerson Flat Number: " + flatNumber + "\nPerson Phone Number: " + phoneNumber + "\n\n";
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mTextView.setText(result);
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
