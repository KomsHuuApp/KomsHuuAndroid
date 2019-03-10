package com.komshuu.komshuuandroidfrontend.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.komshuu.komshuuandroidfrontend.models.Announcement;
import com.komshuu.komshuuandroidfrontend.R;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder> {

    ArrayList<Announcement> mAnnouncementList;
    LayoutInflater inflater;
    Context context;
    long role;

    public AnnouncementAdapter(Context context, ArrayList<Announcement> products, long role) {
        inflater = LayoutInflater.from(context);
        this.mAnnouncementList = products;
        this.context = context;
        this.role = role;
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
        ImageView announcementImage, deleteproduct, edit;

        private long announcementId, announcerId, apartmentId;
        private int announcementImportance;


        public MyViewHolder(View itemView) {
            super(itemView);
            announcementDate = (TextView) itemView.findViewById(R.id.announcementDate);
            announcementDescription = (TextView) itemView.findViewById(R.id.announcementDescription);
            announcementImage = (ImageView) itemView.findViewById(R.id.announcementImage);
            deleteproduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            edit = (ImageView) itemView.findViewById(R.id.edit_announcement);
            deleteproduct.setOnClickListener(this);
            edit.setOnClickListener(this);
            if (role != 1) {
                deleteproduct.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.INVISIBLE);
            }
        }

        public void setData(Announcement selectedProduct, int position) {

            this.announcementDate.setText(selectedProduct.getAnnouncementDate());
            this.announcementDescription.setText(selectedProduct.getAnnouncementDescription());
            this.announcementImage.setImageResource(selectedProduct.getImageID());

            this.announcementId = selectedProduct.getAnnouncementId();
            this.announcerId = selectedProduct.getAnnouncerId();
            this.apartmentId = selectedProduct.getApartmentId();
            this.announcementImportance = selectedProduct.getAnnouncementImportance();
        }


        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            if(viewId == R.id.deleteproduct){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Silmek istediğinize emin misiniz?");
                builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RequestQueue queue = Volley.newRequestQueue(context);
                        String url ="https://enigmatic-atoll-89666.herokuapp.com/deleteAnnouncement?id=" +
                                announcementId + "&apartmentId=" + apartmentId;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        queue.add(stringRequest);
                        mAnnouncementList.remove(getPosition());
                        notifyItemRemoved(getPosition());
                        notifyItemRangeChanged(getPosition(), mAnnouncementList.size());
                    }
                });
                builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = inflater.inflate(R.layout.announcement_edit_layout, null);
                EditText editTextAnnouncementDescription = (EditText) view.findViewById(R.id.edit_announcement_description);
                editTextAnnouncementDescription.setText(announcementDescription.getText());
                builder.setView(view)
                        .setTitle("Düzenle")
                        .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog editDialog = builder.create();
                editDialog.show();
            }
        }
    }
}