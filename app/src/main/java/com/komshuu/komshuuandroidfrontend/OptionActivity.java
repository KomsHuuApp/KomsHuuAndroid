package com.komshuu.komshuuandroidfrontend;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.models.Options;
import com.komshuu.komshuuandroidfrontend.models.Poll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OptionActivity extends AppCompatActivity {

    ArrayList<Options> optionsList;
    View mHeaderView;
    TextView textViewName;
    Options option = new Options();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

/*
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraint_layout);
        RadioGroup group = (RadioGroup) constraintLayout.findViewById(R.id.radio_group);
        RadioButton button = (RadioButton) group.findViewById(R.id.choice_1);
        button.setText("Irmak Ersevinç");
        String url2 ="https://enigmatic-atoll-89666.herokuapp.com/getOptions?apartmentId=1";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        optionsList = new ArrayList<>();
                        try {
                            JSONArray options = new JSONArray(response);
                            for(int i = 0; i < options.length(); i++) {
                                JSONObject poll = options.getJSONObject(i);
                                Options temp = new Options();
                                temp.setOptionId(poll.getLong("optionId"));
                                temp.setPollId(poll.getLong("pollId"));
                                temp.setStatus(poll.getString("status"));
                                temp.setPollNumber(poll.getInt("pollNumber"));
                                optionsList.add(temp);
                                textViewName.setText(option.getStatus());

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
        queue.add(stringRequest2);
*/
    }

}
