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
import com.komshuu.komshuuandroidfrontend.models.Options;
import com.komshuu.komshuuandroidfrontend.models.Poll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OptionActivity extends AppCompatActivity {

    ArrayList<Options> optionsList;
    View mHeaderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);







    }

}
