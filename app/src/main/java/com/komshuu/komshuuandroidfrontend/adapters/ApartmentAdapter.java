package com.komshuu.komshuuandroidfrontend.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.komshuu.komshuuandroidfrontend.R;
import com.komshuu.komshuuandroidfrontend.models.Apartment;

import java.util.ArrayList;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.MyViewHolder> {

    ArrayList<Apartment> apartments;
    LayoutInflater inflater;

    public ApartmentAdapter(Context context, ArrayList<Apartment> products) {
        inflater = LayoutInflater.from(context);
        this.apartments = products;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.apartment_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Apartment selectedProduct = apartments.get(position);
        holder.setData(selectedProduct, position);
    }

    @Override
    public int getItemCount() {
        return apartments.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, address, flatNumber;
        ImageView apartmentImage, deleteproduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.apartmentname);
            address = (TextView) itemView.findViewById(R.id.apartmentadress);
            flatNumber = (TextView) itemView.findViewById(R.id.flatnumber);
            apartmentImage = (ImageView) itemView.findViewById(R.id.apartmentImage);
            deleteproduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            deleteproduct.setOnClickListener(this);

        }

        public void setData(Apartment selectedProduct, int position) {

            this.name.setText(selectedProduct.getName());
            this.address.setText(selectedProduct.getAddress());
            this.flatNumber.setText(selectedProduct.getFlatNumber());
            this.apartmentImage.setImageResource(selectedProduct.getImageID());


        }


        @Override
        public void onClick(View v) {


        }


    }
}
