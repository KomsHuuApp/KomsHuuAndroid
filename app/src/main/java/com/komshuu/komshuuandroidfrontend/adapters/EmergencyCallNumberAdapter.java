package com.komshuu.komshuuandroidfrontend.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.komshuu.komshuuandroidfrontend.models.EmergencyCallNumber;
import com.komshuu.komshuuandroidfrontend.R;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EmergencyCallNumber selectedProduct = mEmergencyCallNumberList.get(position);
        holder.setData(selectedProduct, position);
    }

    @Override
    public int getItemCount() {
        return mEmergencyCallNumberList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView emergencyCallName, emergencyCallNumber;
        ImageView emergencyCallImage, deleteproduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            emergencyCallName = (TextView) itemView.findViewById(R.id.emergencyname);
            emergencyCallNumber = (TextView) itemView.findViewById(R.id.emergencynumber);
            emergencyCallImage = (ImageView) itemView.findViewById(R.id.emergencyImage);
            deleteproduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            deleteproduct.setOnClickListener(this);

        }

        public void setData(EmergencyCallNumber selectedProduct, int position) {

            this.emergencyCallName.setText(selectedProduct.getName());
            this.emergencyCallNumber.setText(selectedProduct.getPhoneNumber());
            this.emergencyCallImage.setImageResource(selectedProduct.getImageID());


        }


        @Override
        public void onClick(View v) {


        }


    }
}
