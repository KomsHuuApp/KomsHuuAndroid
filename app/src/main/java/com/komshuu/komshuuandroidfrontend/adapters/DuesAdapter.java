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

import com.komshuu.komshuuandroidfrontend.R;
import com.komshuu.komshuuandroidfrontend.models.Dues;

import java.util.ArrayList;
import java.util.Collections;

public class DuesAdapter extends RecyclerView.Adapter<DuesAdapter.MyViewHolder> {

    ArrayList<Dues> mDuesList;
    LayoutInflater inflater;
    Context context;

    public DuesAdapter(Context context, ArrayList<Dues> dues) {
        inflater = LayoutInflater.from(context);
        this.mDuesList = dues;
        Collections.reverse(mDuesList);
        this.context = context;
    }


    @Override
    public DuesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dues_card, parent, false);
        DuesAdapter.MyViewHolder holder = new DuesAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DuesAdapter.MyViewHolder holder, int position) {
        Dues selectedProduct = mDuesList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mDuesList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView paymentDate, totalPayment;
        ImageView pay;

        private long duesId, apartmentId, flatNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            paymentDate = (TextView) itemView.findViewById(R.id.payment_date);
            totalPayment = (TextView) itemView.findViewById(R.id.total_payment);
            pay = (ImageView) itemView.findViewById(R.id.pay_button);
            pay.setOnClickListener(this);
        }

        public void setData(Dues selectedProduct, int position) {

            this.paymentDate.setText(selectedProduct.getPaymentDate());
            this.totalPayment.setText(selectedProduct.getTotalPayment());

            this.duesId = selectedProduct.getDuesId();
            this.flatNumber = selectedProduct.getFlatNumber();
            this.apartmentId = selectedProduct.getApartmentId();
        }

        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            if(viewId == R.id.pay_button) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Ödeme servisi devre dışı");
                builder.setPositiveButton("Geri", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        }
    }
}
