package com.avtest.food_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder> {


    Context context;
    List<Contacts> contactsList;
    RecyclerView recyclerView;
    final View.OnClickListener onClickListener = new MyOnClickListener();
    public static  class ViewHolder extends  RecyclerView.ViewHolder{

        TextView name;
        TextView price;
        TextView discount;
        TextView Quantity;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rname);
            price = itemView.findViewById(R.id.rprice);
            discount = itemView.findViewById(R.id.rdis);
            Quantity = itemView.findViewById(R.id.rdes);
        }
    }

    public productAdapter(Context context, List<Contacts> contactsList, RecyclerView recyclerView){
        this.context = context;
        this.contactsList = contactsList;
        this.recyclerView = recyclerView;
    }

    @NonNull

    @Override
    public productAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup viewGroup , int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_row, viewGroup,false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapter.ViewHolder viewHolder, int i) {
        Contacts contacts = contactsList.get(i);
        viewHolder.name.setText(""+contacts.getName());
        viewHolder.price.setText(contacts.getPrice());
        viewHolder.discount.setText(contacts.getDiscount());
        viewHolder.Quantity.setText(contacts.getQuantity());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildLayoutPosition(v);
            String item = contactsList.get(itemPosition).getName();
            Toast.makeText(context, item,Toast.LENGTH_SHORT).show();
        }
    }
}
