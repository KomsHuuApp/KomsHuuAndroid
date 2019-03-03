package com.komshuu.komshuuandroidfrontend.adapters;

import android.content.Context;
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
import com.komshuu.komshuuandroidfrontend.EmergencyNumbersActivity;
import com.komshuu.komshuuandroidfrontend.models.Apartment;
import com.komshuu.komshuuandroidfrontend.models.EmergencyCallNumber;
import com.komshuu.komshuuandroidfrontend.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class EmergencyCallNumberAdapter extends RecyclerView.Adapter<EmergencyCallNumberAdapter.MyViewHolder> {

    ArrayList<EmergencyCallNumber> mEmergencyCallNumberList;
    LayoutInflater inflater;

    public EmergencyCallNumberAdapter(Context context, ArrayList<EmergencyCallNumber> product) {
        inflater = LayoutInflater.from(context);
        this.mEmergencyCallNumberList = product;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.emergency_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        EmergencyCallNumber selectedProduct = mEmergencyCallNumberList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mEmergencyCallNumberList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView emergencyId, emergencyCallName, emergencyCallNumber, apartmentId;
        ImageView emergencyCallImage, deleteproduct, editproduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            emergencyId = (TextView) itemView.findViewById(R.id.emergencyId);
            emergencyCallName = (TextView) itemView.findViewById(R.id.emergencyname);
            emergencyCallNumber = (TextView) itemView.findViewById(R.id.emergencynumber);
            apartmentId = (TextView) itemView.findViewById(R.id.apartmentId);
            emergencyCallImage = (ImageView) itemView.findViewById(R.id.emergencyImage);
            deleteproduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            editproduct = (ImageView) itemView.findViewById(R.id.editproduct);
        }

        public void setData(EmergencyCallNumber selectedProduct, int position) {
            this.emergencyCallName.setText(selectedProduct.getName());
            this.emergencyCallNumber.setText(selectedProduct.getPhoneNumber());
            this.emergencyId.setText(selectedProduct.getEmergencyId() + "");
            this.apartmentId.setText(selectedProduct.getApartmentId() + "");
            this.emergencyCallImage.setImageResource(selectedProduct.getImageID());
        }


        @Override
        public void onClick(View v) {


        }

    }
}