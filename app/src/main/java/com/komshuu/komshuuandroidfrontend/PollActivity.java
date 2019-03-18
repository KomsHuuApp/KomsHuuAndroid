package com.komshuu.komshuuandroidfrontend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.models.Poll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PollActivity extends AppCompatActivity {

    ArrayList<Poll> pollList;
    View mHeaderView;
    TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

        mHeaderView = getLayoutInflater().inflate(R.layout.activity_poll, null);;
        textViewName =(TextView) mHeaderView.findViewById(R.id.poll_title);
        System.out.println( "halo");

        String url2 ="https://enigmatic-atoll-89666.herokuapp.com/getPollTitle?apartmentId=1";
        System.out.println("response");

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("girdi");

                        textViewName.setText(response);
                        System.out.println(response);
                        System.out.println("çıktı");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //bos iken
            }
        });queue.add(stringRequest2);

        RequestQueue queue2 = Volley.newRequestQueue(this);
        String url ="https://enigmatic-atoll-89666.herokuapp.com/getOptions?apartmentId=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pollList = new ArrayList<>();
                        try {
                            JSONArray polls = new JSONArray(response);
                            for(int i = 0; i < polls.length(); i++) {
                                JSONObject poll = polls.getJSONObject(i);
                                Poll temp = new Poll();
                                temp.setApartmentID(poll.getLong("apartmentId"));
                                temp.setPollID(poll.getLong("pollId"));
                                temp.setPollName(poll.getString("pollName"));
                                temp.setPollDate(poll.getString("pollDate"));
                                boolean stat = poll.getBoolean("pollState");
                                temp.setPollState(stat);
                                pollList.add(temp);
                            }

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
        queue2.add(stringRequest);

    }

}

