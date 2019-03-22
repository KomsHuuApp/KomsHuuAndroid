package com.komshuu.komshuuandroidfrontend.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.komshuu.komshuuandroidfrontend.R;
import com.komshuu.komshuuandroidfrontend.models.UserOrderList;

import java.util.ArrayList;

public class UserOrderListAdapter extends RecyclerView.Adapter<UserOrderListAdapter.ExampleViewHolder> {
    private ArrayList<UserOrderList> mExampleList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onDeleteClick(int position);


    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mDeleteImage;


        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.FlatNumber);
            mTextView2 = itemView.findViewById(R.id.Order);
            mDeleteImage = itemView.findViewById(R.id.image_delete);

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }

                    }

                }
            });


        }
    }

    public UserOrderListAdapter(ArrayList<UserOrderList> exampleList) {
        mExampleList = exampleList;

    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list_card, viewGroup,false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        UserOrderList currentItem = mExampleList.get(i);

        exampleViewHolder.mTextView1.setText(currentItem.getFlatNumber());
        exampleViewHolder.mTextView2.setText(currentItem.getOrder());


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
