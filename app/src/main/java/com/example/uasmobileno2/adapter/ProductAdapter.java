package com.example.uasmobileno2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobileno2.R;
import com.example.uasmobileno2.objects.Products;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<Products> productsList = new ArrayList<>();
    private Context context;

    public ProductAdapter(Context context,ArrayList<Products> list) {
        this.context = context;
        this.productsList = list;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productslayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Products product = productsList.get(position);
        holder.itemName.setText(product.getProductName());
        holder.itemPrice.setText(String.valueOf(product.getProductPrice()));
        holder.itemCount.setText("0");

        holder.min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getAmount() > 0) {
                    product.setAmount(product.getAmount() - 1);
                    holder.itemCount.setText(String.valueOf(product.getAmount()));
                }
                else Toast.makeText(context, "Item quantity is already 0", Toast.LENGTH_SHORT).show();
            }
        });

        holder.max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getAmount() >= 0) {
                    product.setAmount(product.getAmount() + 1);
                    holder.itemCount.setText(String.valueOf(product.getAmount()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemCount, min, max;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemPrice = (TextView) itemView.findViewById(R.id.totalPriceText);
            itemCount = (TextView) itemView.findViewById(R.id.itemCount);
            min = (TextView) itemView.findViewById(R.id.subtractItem);
            max = (TextView) itemView.findViewById(R.id.addItem);

        }
    }
}
