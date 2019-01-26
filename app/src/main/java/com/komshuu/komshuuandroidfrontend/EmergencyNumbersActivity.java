package com.komshuu.komshuuandroidfrontend;

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

public class EmergencyNumbersActivity extends AppCompatActivity {

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
                        String result = "";
                        try {
                            JSONArray emergencyNumbers = new JSONArray(response);
                            for(int i = 0; i < emergencyNumbers.length(); i++) {
                                JSONObject announcement = emergencyNumbers.getJSONObject(i);
                                String name = announcement.getString("name");
                                String phoneNumber = announcement.getString("phoneNumber");

                                result += "Phone Number Statement: " + name + "\nPhone Number" + phoneNumber + "\n\n";
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
