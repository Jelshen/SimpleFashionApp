package com.example.uasmobileno2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobileno2.R;
import com.example.uasmobileno2.objects.Products;

import java.util.ArrayList;

public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.ViewHolder> {

    private ArrayList<Products> productsList = new ArrayList<Products>();
    private Context context;

    public FragmentAdapter(Context context,ArrayList<Products> list) {
        this.context = context;
        this.productsList = list;
    }

    @NonNull
    @Override
    public FragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragmentrvlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentAdapter.ViewHolder holder, int position) {
        Products product = productsList.get(position);

        holder.itemName.setText(product.getProductName());
        holder.itemPrice.setText(String.valueOf(product.getProductPrice()));
        holder.itemCount.setText(String.valueOf(product.getAmount()));

        holder.itemTotal.setText(String.valueOf(product.getProductPrice() * product.getAmount()));

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName, itemPrice, itemCount, itemTotal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemPrice = (TextView) itemView.findViewById(R.id.totalPriceText);
            itemCount = (TextView) itemView.findViewById(R.id.itemCount);
            itemTotal = (TextView) itemView.findViewById(R.id.itemTotalPrice);
        }
    }
}
