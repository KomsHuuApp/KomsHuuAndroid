package com.komshuu.komshuuandroidfrontend.adapters;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
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

    public ComplaintAdapter(Context context, ArrayList<Complaint> products) {
        inflater = LayoutInflater.from(context);
        this.mComplaintList = products;
    }


    @Override
    public ComplaintAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.complaint_card, parent, false);
        ComplaintAdapter.MyViewHolder holder = new ComplaintAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ComplaintAdapter.MyViewHolder holder, final int position) {
        Complaint selectedProduct = mComplaintList.get(position);
        holder.setData(selectedProduct, position);

        holder.deleteproduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Complaint theRemovedItem = mComplaintList.get(position);

                // remove your item from data base
                mComplaintList.remove(position);  // remove the item from list
                notifyItemRemoved(position); // notify the adapter about the removed item
            }
        });
    }

    @Override
    public int getItemCount() {
        return mComplaintList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView complaintDate, complaintDescription;
        ImageView deleteproduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            complaintDate = (TextView) itemView.findViewById(R.id.complaintDate);
            complaintDescription = (TextView) itemView.findViewById(R.id.complaintDescription);
            deleteproduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
        }

        public void setData(Complaint selectedProduct, int position) {

            this.complaintDate.setText(selectedProduct.getComplaintDate());
            this.complaintDescription.setText(selectedProduct.getComplaintDescription());

        }

        @Override
        public void onClick(View v) {


        }


    }
}
