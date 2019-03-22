package com.komshuu.komshuuandroidfrontend;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.adapters.UserAdapter;
import com.komshuu.komshuuandroidfrontend.models.PasswordGenerator;
import com.komshuu.komshuuandroidfrontend.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApartmentActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final User user = (User) intent.getSerializableExtra("user");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url1 = "https://enigmatic-atoll-89666.herokuapp.com/getAllPeople?apartmentId=" + user.getApartmentId();
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        users = new ArrayList<>();
                        try {
                            JSONArray userList = new JSONArray(response);
                            for (int i = 0; i < userList.length(); i++) {
                                JSONObject user = userList.getJSONObject(i);
                                User temp = new User();
                                temp.setId(user.getLong("id"));
                                System.out.println(user.getLong("id"));
                                temp.setName(user.getString("name"));
                                temp.setSurname(user.getString("surname"));
                                temp.setRole(user.getLong("role"));
                                temp.setRelativeNumber(user.getString("relativeNumber"));
                                temp.setNumber(user.getString("number"));
                                temp.setGender(user.getString("gender"));
                                temp.setApartmentId(user.getLong("apartmentId"));
                                temp.setUsername(user.getString("username"));
                                temp.setPassword(user.getString("password"));
                                temp.setFlatNumber(user.getInt("flatNumber"));
                                temp.setImageID(R.drawable.ic_menu_gallery);
                                users.add(temp);
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        recyclerView1 = (RecyclerView) findViewById(R.id.recylerview1);

                        UserAdapter userAdapter = new UserAdapter(ApartmentActivity.this, users);
                        recyclerView1.setAdapter(userAdapter);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ApartmentActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView1.setLayoutManager(linearLayoutManager);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Intent intent = getIntent();
        final User user = (User) intent.getSerializableExtra("user");
        if (user.getRole() == 1) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.user_menu, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent = getIntent();
        final User user = (User) intent.getSerializableExtra("user");
        int id = menuItem.getItemId();
        if (id == R.id.action_new_user) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.user_add_layout, null);
            final EditText editTextName = (EditText) view.findViewById(R.id.add_user_name);
            final EditText editTextSurname = (EditText) view.findViewById(R.id.add_user_surname);
            final EditText editTextRole = (EditText) view.findViewById(R.id.add_user_role);
            final EditText editTextNumber = (EditText) view.findViewById(R.id.add_user_number);
            final EditText editTextRelNumber = (EditText) view.findViewById(R.id.add_user_relnumber);
            final EditText editTextGender = (EditText) view.findViewById(R.id.add_user_gender);
            final EditText editTextUsername = (EditText) view.findViewById(R.id.add_user_username);
            final EditText editTextFlatnumber = (EditText) view.findViewById(R.id.add_user_flatnumber);
            builder.setView(view).setTitle("Yeni Kişi").setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RequestQueue queue = Volley.newRequestQueue(ApartmentActivity.this);
                    String url = "https://enigmatic-atoll-89666.herokuapp.com/addPerson";
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name", editTextName.getText());
                        jsonObject.put("surname", editTextSurname.getText());
                        jsonObject.put("role", editTextRole.getText());
                        jsonObject.put("relativeNumber", editTextRelNumber.getText());
                        jsonObject.put("number", editTextNumber.getText());
                        jsonObject.put("gender", editTextGender.getText());
                        jsonObject.put("flatNumber", editTextFlatnumber.getText());
                        jsonObject.put("gender", editTextGender.getText());
                        jsonObject.put("apartmentId", user.getApartmentId());
                        jsonObject.put("username", editTextUsername.getText());
                        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                                .useDigits(true)
                                .useLower(true)
                                .useUpper(true)
                                .build();
                        String password = passwordGenerator.generate(8);
                        jsonObject.put("password", password);
                        final String fromEmail = "noreplykomshuu@gmail.com";
                        final String password1 = "ntdY2sX9";
                        final String toEmail = editTextUsername.getText().toString();

                        String message = "Komshuu Uygulamasına başarıyla kaydoldunuz. Şifreniz aşağıdaki gibidir.\n" + "Şifreniz:" + password;
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{toEmail});
                        i.putExtra(Intent.EXTRA_SUBJECT, "Uygulama Kaydı hk.");
                        i.putExtra(Intent.EXTRA_TEXT   , message);
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(ApartmentActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(ApartmentActivity.this, "Kişi eklenmiştir", Toast.LENGTH_LONG).show();
                            recreate();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ApartmentActivity.this, "Kişi eklenemedi!", Toast.LENGTH_LONG).show();
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
