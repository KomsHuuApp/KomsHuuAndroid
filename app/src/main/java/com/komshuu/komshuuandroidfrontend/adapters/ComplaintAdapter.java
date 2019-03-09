package com.komshuu.komshuuandroidfrontend.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.R;
import com.komshuu.komshuuandroidfrontend.UserOrderActivity;
import com.komshuu.komshuuandroidfrontend.models.Complaint;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.MyViewHolder> {

    ArrayList<Complaint> mComplaintList;
    LayoutInflater inflater;
    Context context;

    public ComplaintAdapter(Context context, ArrayList<Complaint> products) {
        inflater = LayoutInflater.from(context);
        this.mComplaintList = products;
        this.context=context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.complaint_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ComplaintAdapter.MyViewHolder holder, final int position) {
        Complaint selectedProduct = mComplaintList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mComplaintList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView complaintDate, complaintDescription,personId,complaintId,apartmentId;
        ImageView deleteproduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            complaintDate = (TextView) itemView.findViewById(R.id.complaintDate);
            complaintDescription = (TextView) itemView.findViewById(R.id.complaintDescription);
            personId = (TextView) itemView.findViewById(R.id.personId);
            complaintId = (TextView) itemView.findViewById(R.id.complaintId);
            apartmentId = (TextView) itemView.findViewById(R.id.apartmentId);
            deleteproduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            deleteproduct.setOnClickListener(this);
        }

        public void setData(Complaint selectedProduct, int position) {

            this.complaintDate.setText(selectedProduct.getComplaintDate());
            this.complaintDescription.setText(selectedProduct.getComplaintDescription());
            this.personId.setText(selectedProduct.getPersonID() + "");
            this.complaintId.setText(selectedProduct.getComplaintID() + "");
            this.apartmentId.setText(selectedProduct.getApartmentID() + "");

        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Emin misiniz");
            builder.setMessage("Onaylandıktan sonra geri alınamaz");
            builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    RequestQueue queue = Volley.newRequestQueue(context);
                    String url = "https://enigmatic-atoll-89666.herokuapp.com/deleteComplaint?id=" + complaintId.getText() + "&apartmentId=" + apartmentId.getText();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(stringRequest);
                    mComplaintList.remove(getPosition());
                    notifyItemRemoved(getPosition());
                    notifyItemRangeChanged(getPosition(), mComplaintList.size());
                }
            });
            builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {

                    //İptal butonuna basılınca yapılacaklar.Sadece kapanması isteniyorsa boş bırakılacak

                }
            });
            builder.show();
        }


    }
}
