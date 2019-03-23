package com.komshuu.komshuuandroidfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.models.Options;
import com.komshuu.komshuuandroidfrontend.models.Poll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class PollActivity extends AppCompatActivity {

    ArrayList<Poll> pollList;
    TextView textViewName;
    ArrayList<Options> optionsList;
    TextView textViewName2;
    Button voteButton ;
    Options temp;
    ConstraintLayout constraintLayout;
    private RadioGroup group;
    private RadioButton radioButton;
    RadioButton button, button2, button3;
    int voteNum=0,voteNum2=0,voteNum3=0;
    int vote1, vote2, vote3;
    int totalVotes=0;
    ContentLoadingProgressBar progressBar3,progressBar2,progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);



        String url2 ="https://enigmatic-atoll-89666.herokuapp.com/getPollTitle?apartmentId=1";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraint_layout);
                        textViewName =(TextView) constraintLayout.findViewById(R.id.poll_title);
                        textViewName.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //bos iken
            }
        });queue.add(stringRequest2);


        String url3 ="https://enigmatic-atoll-89666.herokuapp.com/getOptions?apartmentId=1";
        RequestQueue queue3 = Volley.newRequestQueue(this);
        StringRequest stringRequest3 = new StringRequest(Request.Method.GET, url3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        optionsList = new ArrayList<>();
                        try {
                            constraintLayout = (ConstraintLayout) findViewById(R.id.constraint_layout);
                            group = (RadioGroup) constraintLayout.findViewById(R.id.radio_group);
                            JSONArray options = new JSONArray(response);
                            for(int i = 0; i < options.length(); i++) {
                                JSONObject option = options.getJSONObject(i);
                                temp = new Options();
                                temp.setOptionId(option.getLong("optionId"));
                                temp.setStatus(option.getString("status"));
                                temp.setPollId(option.getLong("pollId"));
                                temp.setPollNumber(option.getInt("pollNumber"));
                                optionsList.add(temp);
                                if(i==0) {
                                    button = (RadioButton) group.findViewById(R.id.choice_1);
                                    button.setText(temp.getStatus());
                                }else if(i==1) {
                                    button2 = (RadioButton) group.findViewById(R.id.choice_2);
                                    button2.setText(temp.getStatus());
                                }else {
                                    button3 = (RadioButton) group.findViewById(R.id.choice_3);
                                    button3.setText(temp.getStatus());
                                }
                            }
                                voteButton = (Button) constraintLayout.findViewById(R.id.vote);

                                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                            StrictMode.setThreadPolicy(policy);

                                            voteButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                   // if (temp.getPollNumber() == 0) {

                                                        int selectedId = group.getCheckedRadioButtonId();
                                                        System.out.println("selectedId" + selectedId);

                                                        // find the radiobutton by returned id
                                                        radioButton = (RadioButton) findViewById(selectedId);

                                                        Toast.makeText(PollActivity.this,
                                                                radioButton.getText(), Toast.LENGTH_SHORT).show();

                                                        if (radioButton.getText().equals(button.getText())) {

                                                            try {
                                                                URL url = new URL("https://enigmatic-atoll-89666.herokuapp.com/voteAnOption?optionId=" + 1);
                                                                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                                                                httpCon.setDoOutput(true);
                                                                httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                                                httpCon.setRequestMethod("POST");
                                                                httpCon.setRequestProperty("Content-Type", "application/json");
                                                                JSONObject eventObject = new JSONObject();
                                                                eventObject.put("optionId", temp.getOptionId());
                                                                eventObject.put("status", temp.getStatus());
                                                                eventObject.put("pollId", temp.getPollId());
                                                                eventObject.put("pollNumber", temp.getPollNumber());

                                                                String json = eventObject.toString();

                                                                byte[] outputInBytes = json.getBytes("UTF-8");
                                                                OutputStream os = httpCon.getOutputStream();
                                                                os.write(outputInBytes);
                                                                os.close();

                                                                totalVotes++;
                                                                vote1++;
                                                                voteNum3= ((vote3)*100)/totalVotes;
                                                                voteNum2= ((vote2)*100)/totalVotes;
                                                                voteNum= ((vote1)*100)/totalVotes;

                                                                progressBar3 = (ContentLoadingProgressBar) findViewById(R.id.progress_choice_3);
                                                                progressBar3.setProgress(voteNum3);
                                                                progressBar2 = (ContentLoadingProgressBar) findViewById(R.id.progress_choice_2);
                                                                progressBar2.setProgress(voteNum2);
                                                                progressBar = (ContentLoadingProgressBar) findViewById(R.id.progress_choice_1);
                                                                progressBar.setProgress(voteNum);

                                                                int responseCode = httpCon.getResponseCode();
                                                                System.out.println("response code: " + responseCode);
                                                                if (responseCode == 200) {
                                                                    Toast.makeText(PollActivity.this, "Oy kullandığınız için teşekkür ederiz.\n Son durum:\n" +
                                                                                    button3.getText()+": %" + voteNum3 + "\n" + button2.getText() + ": %" + voteNum2 + "\n" + button.getText() + ": %" + voteNum,
                                                                            Toast.LENGTH_LONG).show();
                                                                }

                                                            } catch (ProtocolException e) {
                                                                e.printStackTrace();
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        } else if (radioButton.getText().equals(button2.getText())) {
                                                            try {

                                                                URL url = new URL("https://enigmatic-atoll-89666.herokuapp.com/voteAnOption?optionId=" + 2);
                                                                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                                                                httpCon.setDoOutput(true);
                                                                httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                                                httpCon.setRequestMethod("POST");
                                                                httpCon.setRequestProperty("Content-Type", "application/json");
                                                                JSONObject eventObject = new JSONObject();
                                                                eventObject.put("optionId", temp.getOptionId());
                                                                eventObject.put("status", temp.getStatus());
                                                                eventObject.put("pollId", temp.getPollId());
                                                                eventObject.put("pollNumber", temp.getPollNumber());

                                                                String json = eventObject.toString();

                                                                byte[] outputInBytes = json.getBytes("UTF-8");
                                                                OutputStream os = httpCon.getOutputStream();
                                                                os.write(outputInBytes);
                                                                os.close();
                                                                totalVotes++;
                                                                vote2++;
                                                                voteNum3= ((vote3)*100)/totalVotes;
                                                                voteNum2= ((vote2)*100)/totalVotes;
                                                                voteNum= ((vote1)*100)/totalVotes;

                                                                progressBar3 = (ContentLoadingProgressBar) findViewById(R.id.progress_choice_3);
                                                                progressBar3.setProgress(voteNum3);
                                                                progressBar2 = (ContentLoadingProgressBar) findViewById(R.id.progress_choice_2);
                                                                progressBar2.setProgress(voteNum2);
                                                                progressBar = (ContentLoadingProgressBar) findViewById(R.id.progress_choice_1);
                                                                progressBar.setProgress(voteNum);

                                                                int responseCode = httpCon.getResponseCode();
                                                                System.out.println("response code: " + responseCode);
                                                                if (responseCode == 200) {
                                                                    Toast.makeText(PollActivity.this, "Oy kullandığınız için teşekkür ederiz.\n Son durum:\n" +
                                                                                    button3.getText()+": %" + voteNum3 + "\n" + button2.getText() + ": %" + voteNum2 + "\n" + button.getText() + ": %" + voteNum,
                                                                            Toast.LENGTH_LONG).show();
                                                                }

                                                            } catch (ProtocolException e) {
                                                                e.printStackTrace();
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        } else if (radioButton.getText().equals(button3.getText())) {
                                                            try {

                                                                URL url = new URL("https://enigmatic-atoll-89666.herokuapp.com/voteAnOption?optionId=" + 3);
                                                                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                                                                httpCon.setDoOutput(true);
                                                                httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                                                httpCon.setRequestMethod("POST");
                                                                httpCon.setRequestProperty("Content-Type", "application/json");
                                                                JSONObject eventObject = new JSONObject();
                                                                eventObject.put("optionId", temp.getOptionId());
                                                                eventObject.put("status", temp.getStatus());
                                                                eventObject.put("pollId", temp.getPollId());
                                                                eventObject.put("pollNumber", temp.getPollNumber());

                                                                String json = eventObject.toString();

                                                                byte[] outputInBytes = json.getBytes("UTF-8");
                                                                OutputStream os = httpCon.getOutputStream();
                                                                os.write(outputInBytes);
                                                                os.close();

                                                                totalVotes++;
                                                                vote3++;
                                                                voteNum3= ((vote3)*100)/totalVotes;
                                                                voteNum2= ((vote2)*100)/totalVotes;
                                                                voteNum= ((vote1)*100)/totalVotes;

                                                                progressBar3 = (ContentLoadingProgressBar) findViewById(R.id.progress_choice_3);
                                                                progressBar3.setProgress(voteNum3);
                                                                progressBar2 = (ContentLoadingProgressBar) findViewById(R.id.progress_choice_2);
                                                                progressBar2.setProgress(voteNum2);
                                                                progressBar = (ContentLoadingProgressBar) findViewById(R.id.progress_choice_1);
                                                                progressBar.setProgress(voteNum);

                                                                int responseCode = httpCon.getResponseCode();
                                                                System.out.println("response code: " + responseCode);
                                                                if (responseCode == 200) {
                                                                    Toast.makeText(PollActivity.this, "Oy kullandığınız için teşekkür ederiz.\n Son durum:\n" +
                                                                                    button3.getText()+": %" + voteNum3 + "\n" + button2.getText() + ": %" + voteNum2 + "\n" + button.getText() + ": %" + voteNum,
                                                                            Toast.LENGTH_LONG).show();
                                                                }

                                                            } catch (ProtocolException e) {
                                                                e.printStackTrace();
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }

                                                        }

                                                    //}
                                                    else {
                                                        Toast.makeText(PollActivity.this, "Zaten oy kullandınız.", Toast.LENGTH_LONG).show();
                                                        recreate();
                                                    }
                                                    }

                                            });





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
        queue3.add(stringRequest3);


/*
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
*/
    }

}

