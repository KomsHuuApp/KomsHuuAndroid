package com.komshuu.komshuuandroidfrontend;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.adapters.EmergencyCallNumberAdapter;
import com.komshuu.komshuuandroidfrontend.models.EmergencyCallNumber;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.emergency_number_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.action_new_emergency) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.emergency_add_layout, null);
            final EditText editTextEmergencyDescription = (EditText) view.findViewById(R.id.add_emergency_description);
            final EditText editTextEmergencyNumber = (EditText) view.findViewById(R.id.add_emergency_number);
            builder.setView(view).setTitle("Yeni Çağrı Numarası").setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RequestQueue queue = Volley.newRequestQueue(EmergencyNumbersActivity.this);
                    String url = "https://enigmatic-atoll-89666.herokuapp.com/addEmergencyCallNumber";
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name", editTextEmergencyDescription.getText());
                        jsonObject.put("phoneNumber", editTextEmergencyNumber.getText());
                        jsonObject.put("apartmentId", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(EmergencyNumbersActivity.this, "Çağrı Numarası paylaşıldı.", Toast.LENGTH_LONG).show();
                            recreate();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EmergencyNumbersActivity.this, "Çağrı Numarası paylaşılamadı!", Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(jsonObjectRequest);
                }
            });
            AlertDialog addDialog = builder.create();
            addDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
