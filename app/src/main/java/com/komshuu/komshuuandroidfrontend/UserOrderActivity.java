package com.komshuu.komshuuandroidfrontend;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
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

public class UserOrderActivity extends AppCompatActivity {


    EditText siparisInput;
    Button submitButton;

    String server_url = "https://enigmatic-atoll-89666.herokuapp.com/addOrder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);



        siparisInput = (EditText) findViewById(R.id.siparisInput);
        submitButton = (Button) findViewById(R.id.submitButton);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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
                    eventObject.put("orderType",siparisInput.getText().toString());
                    eventObject.put("apartmentId", new Long(2));
                    eventObject.put("orderDate", time);
                    String json = eventObject.toString();

                    byte[] outputInBytes = json.getBytes("UTF-8");
                    OutputStream os = httpCon.getOutputStream();
                    os.write( outputInBytes );
                    os.close();

                    int responseCode = httpCon.getResponseCode();
                    System.out.println("response code: " + responseCode);
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
