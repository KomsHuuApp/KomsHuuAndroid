package com.komshuu.komshuuandroidfrontend.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.komshuu.komshuuandroidfrontend.R;
import com.komshuu.komshuuandroidfrontend.models.Komshuu;

import java.util.ArrayList;

public class KomshuuAdapter extends RecyclerView.Adapter<KomshuuAdapter.MyViewHolder>{

    ArrayList<Komshuu> komshuus;
    LayoutInflater inflater;

    public KomshuuAdapter(Context context, ArrayList<Komshuu> products) {
        inflater = LayoutInflater.from(context);
        this.komshuus = products;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.komshuu_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Komshuu selectedProduct = komshuus.get(position);
        holder.setData(selectedProduct,position);
    }

    @Override
    public int getItemCount() {
        return komshuus.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, surname, phoneNumber, flatNumber;
        ImageView komshuuImage, deleteproduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.komshuuName);
            phoneNumber = (TextView) itemView.findViewById(R.id.komshuuPhone);
            flatNumber = (TextView) itemView.findViewById(R.id.komshuuFlat);
            komshuuImage = (ImageView) itemView.findViewById(R.id.komshuuImage);
            deleteproduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            deleteproduct.setOnClickListener(this);

        }

        public void setData(Komshuu selectedProduct, int position) {
            this.name.setText(selectedProduct.getName());
            this.phoneNumber.setText(selectedProduct.getPhoneNumber());
            this.flatNumber.setText(selectedProduct.getFlatNumber());
            this.komshuuImage.setImageResource(selectedProduct.getImageID());


        }


        @Override
        public void onClick(View v) {


        }
    }
}
