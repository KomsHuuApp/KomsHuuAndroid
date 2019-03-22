package com.komshuu.komshuuandroidfrontend.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.komshuu.komshuuandroidfrontend.R;
import com.komshuu.komshuuandroidfrontend.models.User;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    ArrayList<User> users;
    LayoutInflater inflater;
    Context context;

    public UserAdapter(Context context, ArrayList<User> product) {
        inflater = LayoutInflater.from(context);
        this.users = product;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        User selectedProduct = users.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView userId, apartmentId, name, role, number, relnumber, gender, username, flatnumber;
        ImageView userImage, deleteProduct, editProduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            userId = (TextView) itemView.findViewById(R.id.userId);
            apartmentId = (TextView) itemView.findViewById(R.id.apartmentId);
            name = (TextView) itemView.findViewById(R.id.name);
            role = (TextView) itemView.findViewById(R.id.role);
            number = (TextView) itemView.findViewById(R.id.number);
            relnumber = (TextView) itemView.findViewById(R.id.relnumber);
            gender = (TextView) itemView.findViewById(R.id.gender);
            username = (TextView) itemView.findViewById(R.id.username);
            flatnumber = (TextView) itemView.findViewById(R.id.flatnumber);
            userImage = (ImageView) itemView.findViewById(R.id.userimage);
            deleteProduct = (ImageView) itemView.findViewById(R.id.deleteproduct);
            editProduct = (ImageView) itemView.findViewById(R.id.editproduct);
        }

        public void setData(User selectedProduct, int position) {
            this.userId.setText(Long.toString(selectedProduct.getId()));
            this.apartmentId.setText(Long.toString(selectedProduct.getApartmentId()));
            this.name.setText(selectedProduct.getName() + " " + selectedProduct.getSurname());
            if (selectedProduct.getRole() == 1) {
                this.role.setText("Yönetici");

            } else if (selectedProduct.getRole() == 2) {
                this.role.setText("Apartman Sakini");
            } else
                this.role.setText("Kapıcı");

            this.number.setText(selectedProduct.getNumber());
            this.relnumber.setText(selectedProduct.getRelativeNumber());
            this.gender.setText(selectedProduct.getGender());
            this.username.setText(selectedProduct.getUsername());
            this.flatnumber.setText(Integer.toString(selectedProduct.getFlatNumber()) + " No'lu daire");
            this.userImage.setImageResource(selectedProduct.getImageID());
        }


        @Override
        public void onClick(View v) {
            /*int id = v.getId();
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
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View view = inflater.inflate(R.layout.emergency_edit_layout, null);
                final String emergencyIdText = (String) emergencyId.getText();
                final String apartmentIdText = (String) apartmentId.getText();
                final EditText editTextEmergencyDescription = (EditText) view.findViewById(R.id.edit_emergency_description);
                final EditText editTextEmergencyNumber = (EditText) view.findViewById(R.id.edit_emergency_number);
                editTextEmergencyDescription.setText(emergencyCallName.getText());
                editTextEmergencyNumber.setText(emergencyCallNumber.getText());
                builder.setView(view).setTitle("Düzenleme")
                        .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestQueue queue = Volley.newRequestQueue(context);
                        String url = "https://enigmatic-atoll-89666.herokuapp.com/updateEmergencyNumber";
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("numberId", emergencyIdText);
                            jsonObject.put("name", editTextEmergencyDescription.getText());
                            jsonObject.put("phoneNumber", editTextEmergencyNumber.getText());
                            jsonObject.put("apartmentId", apartmentIdText);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(context, "Acil Çağrı numarası güncellenmiştir.", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, "Acil Çağrı numarası güncellenemedi!", Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(jsonObjectRequest);
                    }
                });
                AlertDialog editDialog = builder.create();
                editDialog.show();
                return;
            }
        }*/
        }
    }
}
