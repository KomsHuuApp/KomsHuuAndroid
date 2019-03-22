package com.komshuu.komshuuandroidfrontend;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.adapters.UserOrderAdapter;
import com.komshuu.komshuuandroidfrontend.models.User;
import com.komshuu.komshuuandroidfrontend.models.UserOrder;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UserOrderActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private UserOrderAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<UserOrder> mUserOrderList;

    TextView showValue;
    EditText siparisInput;
    Button submitButton;
    Button deleteButton;
    String text = "";
    String not = "";



    String server_url = "https://enigmatic-atoll-89666.herokuapp.com/addOrder";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);




        createUserOrderList();
        buildRecyclerView();


        submitButton = (Button) findViewById(R.id.submitButton);


        Intent parentIntent = getIntent();
        final User user = (User) parentIntent.getSerializableExtra("user");
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createOrder();
                AlertDialog.Builder builder = new AlertDialog.Builder(UserOrderActivity.this);
                builder.setTitle("Siparis Ver");
                builder.setMessage("Siparisi Vermek istediginizden Emin misiniz?" + "\n" + "Mevcut siparisiniz: " + "\n" + text + "Siparis Notunuz:" + not)
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Calendar cal = Calendar.getInstance();
                                cal.add(Calendar.HOUR_OF_DAY, 3);
                                String time= new SimpleDateFormat("dd MMM yyyy").format(cal.getTime());
                                if (text.equals("")) {
                                    AlertDialog.Builder builder4 = new AlertDialog.Builder(UserOrderActivity.this);
                                    builder4.setMessage("Mevcut Siparisiniz Bulunmamaktadir");
                                    builder4.setPositiveButton("Tamam", null);

                                    AlertDialog alert3 = builder4.create();
                                    alert3.show();
                                }
                                else {
                                    try {

                                        URL url = new URL(server_url);
                                        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                                        httpCon.setDoOutput(true);
                                        httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                        httpCon.setRequestMethod("POST");
                                        httpCon.setRequestProperty("Content-Type", "application/json");
                                        JSONObject eventObject = new JSONObject();
                                        eventObject.put("orderType", text + not);
                                        eventObject.put("apartmentId", user.getApartmentId());
                                        eventObject.put("flatId", user.getFlatNumber());
                                        eventObject.put("orderDate", time);
                                        String json = eventObject.toString();

                                        byte[] outputInBytes = json.getBytes("UTF-8");
                                        OutputStream os = httpCon.getOutputStream();
                                        os.write(outputInBytes);
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
                                        text = "";

                                    } catch (ProtocolException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        })
                        .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                text = "";
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrder();
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(UserOrderActivity.this);
                builder1.setTitle("Siparis İptali");
                builder1.setMessage("Siparisi iptal etmek istediginizden Emin misiniz?" + "\n" + "Mevcut siparisiniz: " + "\n" + text + "Siparis Notunuz:" + not);
                builder1.setCancelable(true)
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (text.equals("") && not.equals("")) {
                                    AlertDialog.Builder builder3 = new AlertDialog.Builder(UserOrderActivity.this);
                                    builder3.setMessage("Mevcut Siparisiniz Bulunmamaktadir");
                                    builder3.setPositiveButton("Tamam", null);

                                    AlertDialog alert3 = builder3.create();
                                    alert3.show();
                                }
                                else {
                                    not = "";
                                    text = "";
                                    resetAll();
                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(UserOrderActivity.this);
                                    builder2.setMessage("Siparisiniz iptal edilmistir");
                                    builder2.setPositiveButton("Tamam", null);

                                    AlertDialog alert2 = builder2.create();
                                    alert2.show();
                                }

                            }
                        })
                        .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                text = "";
                            }
                        });
                        AlertDialog alert = builder1.create();
                        alert.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item1:
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(UserOrderActivity.this);
                builder1.setTitle("Siparis Notunuzu Giriniz");
                final EditText et = new EditText(UserOrderActivity.this);
                et.setHint("Notu giriniz.");
                builder1.setView(et);


                builder1.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        not = et.getText().toString();
                        if (not.equals("")) {
                            AlertDialog.Builder builder3 = new AlertDialog.Builder(UserOrderActivity.this);
                            builder3.setMessage("Bos Not yollayamassınız. Lutfen tekrar deneyiniz.");
                            builder3.setPositiveButton("Tamam", null);

                            AlertDialog alert3 = builder3.create();
                            alert3.show();
                        }
                        else {
                            AlertDialog.Builder builder3 = new AlertDialog.Builder(UserOrderActivity.this);
                            builder3.setMessage("Notunuz Basarıyla Eklenmistir.");
                            builder3.setPositiveButton("Tamam", null);

                            AlertDialog alert3 = builder3.create();
                            alert3.show();

                        }
                    }
                });
                builder1.setNegativeButton("Hayir",null);
                AlertDialog a = builder1.create();
                a.show();

                return true;
            case R.id.item2:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(UserOrderActivity.this);
                text = "";
                createOrder();
                builder3.setMessage("Mevcut siparisiniz: " + "\n" + text + "Siparis Notunuz:" +  "\n" + not);

                builder3.setPositiveButton("Tamam", null);

                text = "";
                AlertDialog alert3 = builder3.create();
                alert3.show();


                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }



    public void createUserOrderList() {
        mUserOrderList = new ArrayList<>();
        mUserOrderList.add(new UserOrder("Beyaz Ekmek","0"));
        mUserOrderList.add(new UserOrder("Kepek Ekmegi","0"));
        mUserOrderList.add(new UserOrder("Cavdar Ekmegi","0"));
        mUserOrderList.add(new UserOrder("TamBugday Ekmegi","0"));
        mUserOrderList.add(new UserOrder("TorkuSüt","0"));
        mUserOrderList.add(new UserOrder("PınarSüt","0"));
        mUserOrderList.add(new UserOrder("IcımSüt","0"));
        mUserOrderList.add(new UserOrder("SutasSüt","0"));
        mUserOrderList.add(new UserOrder("Hürriyet Gazetesi","0"));
        mUserOrderList.add(new UserOrder("Cumhuriyet Gazetesi","0"));
        mUserOrderList.add(new UserOrder("Posta Gazetesi","0"));


    }

    public void changeItem(int position, int count) {

        mUserOrderList.get(position).changeCount(""+count);
        mAdapter.notifyItemChanged(position);

    }

    public void resetAll() {
        for (int i = 0; i < mUserOrderList.size(); i++) {
            mUserOrderList.get(i).changeCount("0");
            mAdapter.notifyItemChanged(i);
        }
    }

    public void createOrder() {
        for (int i = 0; i < mUserOrderList.size(); i++) {
            int sayi = Integer.parseInt(mUserOrderList.get(i).getCount());
            if (sayi > 0) {
                text += mUserOrderList.get(i).getText() + " X" + sayi + "\n";
            }
        }


    }


    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new UserOrderAdapter(mUserOrderList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new UserOrderAdapter.OnItemClickListener() {
            @Override
            public void onAddClick(int position) {
                UserOrder click;
                click = mUserOrderList.get(position);
                String str = mUserOrderList.get(position).getText();
                if (str.equals("Beyaz Ekmek")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("Kepek Ekmegi")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("Cavdar Ekmegi")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("TamBugday Ekmegi")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("TorkuSüt")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("PınarSüt")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("IcımSüt")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("SutasSüt")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("Hürriyet Gazetesi")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("Cumhuriyet Gazetesi")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }
                else  if (str.equals("Posta Gazetesi")) {
                    changeItem(position, Integer.parseInt(click.getCount())+ 1);
                }



            }

            @Override
            public void onDeleteClick(int position) {
                UserOrder click;
                click = mUserOrderList.get(position);
                String str = mUserOrderList.get(position).getText();
                if (str.equals("Beyaz Ekmek") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("Kepek Ekmegi") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("Cavdar Ekmegi") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("TamBugday Ekmegi") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("TorkuSüt")&& Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("PınarSüt") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("IcımSüt") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("SutasSüt") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("Hürriyet Gazetesi") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("Cumhuriyet Gazetesi") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }
                else  if (str.equals("Posta Gazetesi") && Integer.parseInt(click.getCount()) > 0) {
                    changeItem(position, Integer.parseInt(click.getCount())- 1);
                }



            }
        });

    }


}
