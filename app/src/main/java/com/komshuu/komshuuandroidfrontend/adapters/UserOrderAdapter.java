package com.komshuu.komshuuandroidfrontend.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.komshuu.komshuuandroidfrontend.R;
import com.komshuu.komshuuandroidfrontend.models.User;
import com.komshuu.komshuuandroidfrontend.models.UserOrder;

import java.util.ArrayList;

public class UserOrderAdapter extends RecyclerView.Adapter<UserOrderAdapter.ExampleViewHolder> {
    private ArrayList<UserOrder> mUserOrderList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onAddClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {mListener = listener;}

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public ImageView mAddImage;
        public ImageView mRemoveImage;


        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.Option);
            mAddImage = itemView.findViewById(R.id.image_add);
            mRemoveImage = itemView.findViewById(R.id.image_remove);

            mAddImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddClick(position);
                        }
                    }
                }
            });
            mRemoveImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }

                }
            });

        }
    }

    public UserOrderAdapter(ArrayList<UserOrder> userOrderList) {
        mUserOrderList = userOrderList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_card, viewGroup, false);
       ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
       return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        UserOrder currentItem = mUserOrderList.get(i);

        exampleViewHolder.mTextView1.setText(currentItem.getText());

    }

    @Override
    public int getItemCount() {
        return mUserOrderList.size();
    }
}
