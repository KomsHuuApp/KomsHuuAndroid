package com.komshuu.komshuuandroidfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.adapters.UserOrderListAdapter;
import com.komshuu.komshuuandroidfrontend.models.EmergencyCallNumber;
import com.komshuu.komshuuandroidfrontend.models.User;
import com.komshuu.komshuuandroidfrontend.models.UserOrderList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UserOrderListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<UserOrderList> userOrderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_list);
        Intent intent = getIntent();
        final User user = (User) intent.getSerializableExtra("user");
        userOrderList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 3);
        String time = new SimpleDateFormat("dd MMM yyyy").format(cal.getTime());
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://enigmatic-atoll-89666.herokuapp.com/getOrdersByDate?apartmentId=" + user.getApartmentId() + "&role=" + user.getRole() + "&date=" + time;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray orders = new JSONArray(response);
                            for (int i = 0; i < orders.length(); i++) {
                                JSONObject order = orders.getJSONObject(i);
                                UserOrderList temp = new UserOrderList();
                                temp.setOrder(order.getString("orderType"));
                                Long flatnumber = new Long(order.getLong("flatId"));
                                String str = flatnumber + "";
                                temp.setFlatNumber("Daire Numarasi: " + str);
                                System.out.println(temp.getFlatNumber());
                                System.out.println(temp.getOrder());
                                userOrderList.add(temp);
                            }
                            mRecyclerView = findViewById(R.id.recyclerView2);
                            mRecyclerView.setHasFixedSize(true);
                            mLayoutManager = new LinearLayoutManager(UserOrderListActivity.this);
                            mAdapter = new UserOrderListAdapter(userOrderList);

                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(mAdapter);

                        } catch (Exception ex) {
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



