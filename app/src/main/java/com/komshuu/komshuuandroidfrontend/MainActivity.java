package com.komshuu.komshuuandroidfrontend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button getValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear_layout);

                final Snackbar snackbar = Snackbar.make(linearLayout, "", Snackbar.LENGTH_INDEFINITE);

                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                LayoutInflater objLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View snackView = objLayoutInflater.inflate(R.layout.activity_complaint_box, null); // custom_snac_layout is your custom xml

                layout.addView(snackView, 0);
                snackbar.show();

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final TextView mTextView = (TextView) findViewById(R.id.text);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://enigmatic-atoll-89666.herokuapp.com/getAnnouncements?apartmentId=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result = "";
                        try {
                            JSONArray announcements = new JSONArray(response);
                            for(int i = 0; i < announcements.length(); i++) {
                                JSONObject announcement = announcements.getJSONObject(i);
                                String text = announcement.getString("text");
                                String date = announcement.getString("announcementDate");
                                String announcer = announcement.getString("announcerId");
                                String importance = announcement.getString("announcementImportance");

                                result += "Text: " + text + "\nDate: " + date + "\nAnnouncer: " + announcer + "\nImportance: " + importance + "\n\n";
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_apartment) {
            // Handle the camera action
        } else if (id == R.id.nav_flat) {

        } else if (id == R.id.nav_dues) {

        } else if (id == R.id.nav_order) {
            Intent intent = new Intent(this, UserOrderActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_numbers) {
            Intent intent = new Intent(this, EmergencyNumbersActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_poll) {

        } else if (id == R.id.nav_warn) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
