package com.komshuu.komshuuandroidfrontend.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.komshuu.komshuuandroidfrontend.models.EmergencyCallNumber;
import com.komshuu.komshuuandroidfrontend.R;

import java.util.ArrayList;

public class EmergencyCallNumberAdapter extends RecyclerView.Adapter<EmergencyCallNumberAdapter.MyViewHolder> {

    ArrayList<EmergencyCallNumber> mEmergencyCallNumberList;
    LayoutInflater inflater;
    Context context;

    public EmergencyCallNumberAdapter(Context context, ArrayList<EmergencyCallNumber> product) {
        inflater = LayoutInflater.from(context);
        this.mEmergencyCallNumberList = product;
        this.context = context;
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
        ImageView emergencyCallImage, deleteProduct, editProduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            emergencyId = (TextView) itemView.findViewById(R.id.emergencyId);
            emergencyCallName = (TextView) itemView.findViewById(R.id.emergencyname);
            emergencyCallNumber = (TextView) itemView.findViewById(R.id.emergencynumber);
            apartmentId = (TextView) itemView.findViewById(R.id.apartmentId);
            emergencyCallImage = (ImageView) itemView.findViewById(R.id.emergencyImage);
            deleteProduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            editProduct = (ImageView) itemView.findViewById(R.id.editproduct);
            editProduct.setOnClickListener(this);
            deleteProduct.setOnClickListener(this);
            /*if (role != 1) {
                deleteProduct.setVisibility(View.INVISIBLE);
                editProduct.setVisibility(View.INVISIBLE);
            }*/
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
            int id = v.getId();
            if (id == R.id.deleteproduct) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?");
                builder.setMessage("After this process, the deleted data can not be retrieve");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RequestQueue queue = Volley.newRequestQueue(context);
                        System.out.println(emergencyId.getText() + " " + emergencyCallName.getText());
                        String url = "https://enigmatic-atoll-89666.herokuapp.com/deleteEmergencyNumber?id=" + emergencyId.getText() + "&apartmentId=" + apartmentId.getText();
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
                        mEmergencyCallNumberList.remove(getPosition());
                        notifyItemRemoved(getPosition());
                        notifyItemRangeChanged(getPosition(), mEmergencyCallNumberList.size());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();
            }
        }
    }
}
