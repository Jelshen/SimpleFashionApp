package com.example.uasmobileno2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobileno2.DBHandler;
import com.example.uasmobileno2.R;
import com.example.uasmobileno2.objects.Orders;
import com.example.uasmobileno2.objects.Products;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<Orders> orderList = new ArrayList<>();
    private Context context;
    private OnItemListener onItemListener;

    public HistoryAdapter(ArrayList<Orders> list, Context context, OnItemListener onItemListener) {
        this.orderList = list;
        this.context = context;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.historylayout, parent, false);
        return new ViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Orders orders = orderList.get(position);

        String[] prices = orders.getPrice().split(", ");
        String[] amount = orders.getAmount().split(", ");

        int totalPrice = 0;
        for(int i = 0; i < prices.length; i++) {
           totalPrice += Integer.parseInt(prices[i]) * Integer.parseInt(amount[i]);
        }

        holder.itemName.setText("Order " + String.valueOf(position + 1));
        holder.itemTotalPrice.setText(String.valueOf(totalPrice));
        holder.orderDate.setText(orders.getDate());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemName, itemTotalPrice, orderDate;
        OnItemListener onItemListener;
        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            this.onItemListener = onItemListener;
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemTotalPrice = (TextView) itemView.findViewById(R.id.itemTotalPrice);
            orderDate = (TextView) itemView.findViewById(R.id.orderDate);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }

}
