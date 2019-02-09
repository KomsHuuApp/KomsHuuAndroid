package com.komshuu.komshuuandroidfrontend.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.komshuu.komshuuandroidfrontend.models.Announcement;
import com.komshuu.komshuuandroidfrontend.R;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder> {

    ArrayList<Announcement> mAnnouncementList;
    LayoutInflater inflater;

    public AnnouncementAdapter(Context context, ArrayList<Announcement> products) {
        inflater = LayoutInflater.from(context);
        this.mAnnouncementList = products;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.announcement_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Announcement selectedProduct = mAnnouncementList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mAnnouncementList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView announcementDate, announcementDescription;
        ImageView announcementImage, deleteproduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            announcementDate = (TextView) itemView.findViewById(R.id.announcementDate);
            announcementDescription = (TextView) itemView.findViewById(R.id.announcementDescription);
            announcementImage = (ImageView) itemView.findViewById(R.id.announcementImage);
            deleteproduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            deleteproduct.setOnClickListener(this);

        }

        public void setData(Announcement selectedProduct, int position) {

            this.announcementDate.setText(selectedProduct.getAnnouncementDate());
            this.announcementDescription.setText(selectedProduct.getAnnouncementDescription());
            this.announcementImage.setImageResource(selectedProduct.getImageID());


        }


        @Override
        public void onClick(View v) {


        }


    }
}