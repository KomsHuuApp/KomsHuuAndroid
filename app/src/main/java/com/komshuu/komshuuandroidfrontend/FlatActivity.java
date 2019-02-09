package com.komshuu.komshuuandroidfrontend;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class FlatActivity extends AppCompatActivity {

    EditText daireInput;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat);

        daireInput = (EditText) findViewById(R.id.daireInput);
        submitButton = (Button) findViewById(R.id.submitButton);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KomshuuActivity.class);
                System.out.println(daireInput.getText().toString() + " Flat");
                intent.putExtra("flatNumber", daireInput.getText().toString());
                startActivity(intent);
            }
        });
    }
}
