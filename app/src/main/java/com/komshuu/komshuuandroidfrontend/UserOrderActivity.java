package com.komshuu.komshuuandroidfrontend;

import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText siparisInput;
    Button submitButton;
    Button deleteButton;
    String text = "";
    String text1 = "";
    int check = 0;
    int TamBugday = 0;
    int Cavdar = 0;
    int Kepekli = 0;
    int BeyazEkmek = 0;

    String server_url = "https://enigmatic-atoll-89666.herokuapp.com/addOrder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);


        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        submitButton = (Button) findViewById(R.id.submitButton);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UserOrderActivity.this);
                builder.setTitle("Siparis Ver");
                builder.setMessage("Siparisi Vermek istediginizden Emin misiniz?" + "\n" + "Mevcut siparisiniz: " + "\n" + text)
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Calendar cal = Calendar.getInstance();
                                cal.add(Calendar.HOUR_OF_DAY, 3);
                                String time= new SimpleDateFormat("dd MMM yyyy HH:mm").format(cal.getTime());

                                try{
                                    URL url = new URL(server_url);
                                    HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                                    httpCon.setDoOutput(true);
                                    httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
                                    httpCon.setRequestMethod("POST");
                                    httpCon.setRequestProperty("Content-Type","application/json");
                                    JSONObject eventObject = new JSONObject();
                                    eventObject.put("orderType",text);
                                    eventObject.put("apartmentId", new Long(2));
                                    eventObject.put("orderDate", time);
                                    String json = eventObject.toString();

                                    byte[] outputInBytes = json.getBytes("UTF-8");
                                    OutputStream os = httpCon.getOutputStream();
                                    os.write( outputInBytes );
                                    os.close();

                                    int responseCode = httpCon.getResponseCode();
                                    System.out.println("response code: " + responseCode);
                                    if (responseCode == 200) {
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(UserOrderActivity.this);
                                        builder1.setMessage("Siparisiniz Basarıyla iletilmistir");
                                        builder1.setPositiveButton("Tamam", null);

                                        AlertDialog alert2 = builder1.create();
                                        alert2.show();

                                    }
                                } catch (ProtocolException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        })
                        .setNegativeButton("Hayır",null);

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder1 = new AlertDialog.Builder(UserOrderActivity.this);
                builder1.setTitle("Siparis İptali");
                builder1.setMessage("Siparisi iptal etmek istediginizden Emin misiniz?" + "\n" + "Mevcut siparisiniz: " + "\n" + text);
                builder1.setCancelable(true)
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                text = "";
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(UserOrderActivity.this);
                                builder2.setMessage("Siparisiniz iptal edilmistir");
                                builder2.setPositiveButton("Tamam", null);

                                AlertDialog alert2 = builder2.create();
                                alert2.show();

                            }
                        })
                        .setNegativeButton("Hayır", null);

                        AlertDialog alert = builder1.create();
                        alert.show();


            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(++check > 1) {
            if (parent.getItemAtPosition(position).toString().equals("TamBugday"))
                TamBugday++;
            else if (parent.getItemAtPosition(position).toString().equals("Cavdar"))
                Cavdar++;
            else if (parent.getItemAtPosition(position).toString().equals("Kepekli"))
                Kepekli++;
            else if (parent.getItemAtPosition(position).toString().equals("BeyazEkmek"))
                BeyazEkmek++;

                text1 = parent.getItemAtPosition(position).toString() + " Sepete Eklendi.";
                text += parent.getItemAtPosition(position).toString() + "\n";
                Toast.makeText(parent.getContext(), text1, Toast.LENGTH_SHORT).show();



        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
