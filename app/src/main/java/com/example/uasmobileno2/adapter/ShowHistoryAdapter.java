package com.example.uasmobileno2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobileno2.R;
import com.example.uasmobileno2.ShowHistoryDetails;
import com.example.uasmobileno2.objects.Orders;
import com.example.uasmobileno2.objects.Products;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShowHistoryAdapter extends RecyclerView.Adapter<ShowHistoryAdapter.ViewHolder> {

    private ArrayList<Products> productList = new ArrayList<>();
    private Context context;

    public ShowHistoryAdapter(Context context, ArrayList<Products> list) {
        this.context = context;
        this.productList = list;
    }

    @NonNull
    @Override
    public ShowHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showhistorylayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowHistoryAdapter.ViewHolder holder, int position) {
        Products product = productList.get(position);

        holder.itemName.setText(product.getProductName());
        holder.itemPrice.setText(String.valueOf(product.getProductPrice()));
        holder.itemCount.setText(String.valueOf(product.getAmount()));
        holder.itemTotal.setText((String.valueOf(product.getProductPrice() * product.getAmount())));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName, itemPrice, itemCount, itemTotal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemNameHistory);
            itemPrice = (TextView) itemView.findViewById(R.id.totalPriceTextHistory);
            itemCount = (TextView) itemView.findViewById(R.id.itemCountHistory);
            itemTotal = (TextView) itemView.findViewById(R.id.itemTotalPriceHistory);
        }
    }
}
